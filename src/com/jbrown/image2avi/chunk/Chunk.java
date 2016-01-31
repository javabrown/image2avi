package com.jbrown.image2avi.chunk;

import java.io.IOException;

import javax.imageio.stream.ImageOutputStream;

public abstract class Chunk {

    private final ImageOutputStream _ioStream;

    /**
     * The chunkType of the chunk. A String with the length of 4 characters.
     */
    protected String _chunkType;

    /**
     * The offset of the chunk relative to the start of the
     * ImageOutputStream.
     */
    protected long _offset;

    /**
     * Creates a new Chunk at the current position of the ImageOutputStream.
     * @param chunkType The chunkType of the chunk. A string with a length of 4 characters.
     */
    public Chunk(ImageOutputStream ioStream, String chunkType) throws IOException {
        _chunkType = chunkType;
        _ioStream = ioStream;
        _offset = _ioStream.getStreamPosition();
    }

    public final ImageOutputStream getIOStream(){
      return _ioStream;
    }

    public String getChunkType(){
      return _chunkType;
    }

    public long getOffset(){
     return _offset;
    }


    /**
     * Writes the chunk to the ImageOutputStream and disposes it.
     */
    public abstract void finish() throws IOException;

    /**
     * Returns the size of the chunk including the size of the chunk header.
     * @return The size of the chunk.
     */
    public abstract long size();
}
