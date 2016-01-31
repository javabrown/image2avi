package com.jbrown.image2avi.chunk;

import java.io.IOException;

import javax.imageio.stream.ImageOutputStream;

import com.jbrown.image2avi.core.DataChunkOutputStream;
import com.jbrown.image2avi.core.FilterImageOutputStream;

public class FixedSizeDataChunk extends Chunk {

    private DataChunkOutputStream data;
    private boolean finished;
    private long fixedSize;

    /**
     * Creates a new DataChunk at the current position of the
     * ImageOutputStream.
     * @param chunkType The chunkType of the chunk.
     */
    public FixedSizeDataChunk(ImageOutputStream ioStream,
           String chunkType, long fixedSize) throws IOException {
        super(ioStream, chunkType);
        this.fixedSize = fixedSize;
        data = new DataChunkOutputStream(
            new FilterImageOutputStream(super.getIOStream()));
        data.writeType(chunkType);
        data.writeUInt(fixedSize);
        data.clearCount();

        // Fill fixed size with nulls
        byte[] buf = new byte[(int) Math.min(512, fixedSize)];
        long written = 0;
        while (written < fixedSize) {
            data.write(buf, 0, (int) Math.min(buf.length, fixedSize - written));
            written += Math.min(buf.length, fixedSize - written);
        }
        if (fixedSize % 2 == 1) {
          super.getIOStream().writeByte(0); // write pad byte
        }
        seekToStartOfData();
    }

    public DataChunkOutputStream getOutputStream() {
        /*if (finished) {
        throw new IllegalStateException("DataChunk is finished");
        }*/
        return data;
    }

    /**
     * Returns the offset of this chunk to the beginning of the random access file
     * @return
     */
    public long getOffset() {
        return super.getOffset();
    }

    public void seekToStartOfData() throws IOException {
      super.getIOStream().seek(super.getOffset() + 8);
        data.clearCount();
    }

    public void seekToEndOfChunk() throws IOException {
      super.getIOStream().seek(super.getOffset() + 8 + fixedSize + fixedSize % 2);
    }

    @Override
    public void finish() throws IOException {
        if (!finished) {
            finished = true;
        }
    }

    @Override
    public long size() {
        return 8 + fixedSize;
    }
}