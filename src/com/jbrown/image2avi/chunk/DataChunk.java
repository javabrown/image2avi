package com.jbrown.image2avi.chunk;

import java.io.IOException;

import javax.imageio.stream.ImageOutputStream;

import com.jbrown.image2avi.core.DataChunkOutputStream;
import com.jbrown.image2avi.core.FilterImageOutputStream;

public class DataChunk extends Chunk {

    private DataChunkOutputStream data;
    private boolean finished;

    /**
     * Creates a new DataChunk at the current position of the
     * ImageOutputStream.
     * @param chunkType The chunkType of the chunk.
     */
    public DataChunk(ImageOutputStream ioStream, String name) throws IOException {
        super(ioStream, name);
        super.getIOStream().writeLong(0); // make room for the chunk header
        data = new DataChunkOutputStream(new FilterImageOutputStream(
            super.getIOStream()));
    }

    public DataChunkOutputStream getOutputStream() {
        if (finished) {
            throw new IllegalStateException("DataChunk is finished");
        }
        return data;
    }

    /**
     * Returns the offset of this chunk to the beginning of the random access file
     * @return
     */
    public long getOffset() {
        return super.getOffset();
    }

    @Override
    public void finish() throws IOException {
        if (!finished) {
            long sizeBefore = size();

            if (size() > 0xffffffffL) {
                throw new IOException("DataChunk \"" + getChunkType() + "\" is too large: " + size());
            }

            long pointer =  super.getIOStream().getStreamPosition();
            super.getIOStream().seek(super.getOffset());

            DataChunkOutputStream headerData = new DataChunkOutputStream(new FilterImageOutputStream( super.getIOStream()));
            headerData.writeType(getChunkType());
            headerData.writeUInt(size() - 8);
            super.getIOStream().seek(pointer);
            if (size() % 2 == 1) {
              super.getIOStream().writeByte(0); // write pad byte
            }
            finished = true;
            long sizeAfter = size();
            if (sizeBefore != sizeAfter) {
                System.err.println("size mismatch " + sizeBefore + ".." + sizeAfter);
            }
        }
    }

    @Override
    public long size() {
        return 8 + data.size();
    }
}
