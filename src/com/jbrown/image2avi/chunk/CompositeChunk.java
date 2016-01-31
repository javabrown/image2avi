package com.jbrown.image2avi.chunk;

import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.stream.ImageOutputStream;

import com.jbrown.image2avi.core.DataChunkOutputStream;
import com.jbrown.image2avi.core.FilterImageOutputStream;

/**
 * A CompositeChunk contains an ordered list of Chunks.
 */
public class CompositeChunk extends Chunk {

    /**
     * The type of the composite. A String with the length of 4 characters.
     */
    protected String compositeType;
    private LinkedList<Chunk> children;
    private boolean finished;

    /**
     * Creates a new CompositeChunk at the current position of the
     * ImageOutputStream.
     * @param compositeType The type of the composite.
     * @param chunkType The type of the chunk.
     */
    public CompositeChunk(ImageOutputStream ioStream, String compositeType, String chunkType) throws IOException {
        super(ioStream, chunkType);
        this.compositeType = compositeType;
        //out.write
        super.getIOStream().writeLong(0); // make room for the chunk header
        super.getIOStream().writeInt(0); // make room for the chunk header
        children = new LinkedList<Chunk>();
    }

    public void add(Chunk child) throws IOException {
        if (children.size() > 0) {
            children.getLast().finish();
        }
        children.add(child);
    }

    /**
     * Writes the chunk and all its children to the ImageOutputStream
     * and disposes of all resources held by the chunk.
     * @throws java.io.IOException
     */
    public void finish() throws IOException {
        if (!finished) {
            if (size() > 0xffffffffL) {
                throw new IOException("CompositeChunk \"" + getChunkType() + "\" is too large: " + size());
            }

            long pointer =  super.getIOStream().getStreamPosition();
            super.getIOStream().seek(getOffset());

            DataChunkOutputStream headerData = new DataChunkOutputStream(new FilterImageOutputStream( super.getIOStream()));
            headerData.writeType(compositeType);
            headerData.writeUInt(size() - 8);
            headerData.writeType(getChunkType());
            for (Chunk child : children) {
                child.finish();
            }
            super.getIOStream().seek(pointer);
            if (size() % 2 == 1) {
              super.getIOStream().writeByte(0); // write pad byte
            }
            finished = true;
        }
    }

    public long size() {
        long length = 12;
        for (Chunk child : children) {
            length += child.size() + child.size() % 2;
        }
        return length;
    }
}