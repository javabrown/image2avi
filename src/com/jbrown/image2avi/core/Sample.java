package com.jbrown.image2avi.core;

/**
 * AVI stores media data in samples. A sample is a single element in a sequence
 * of time-ordered data.
 */
public class Sample {
  private long _offset;   // Offset of sample relative to the start of AVI file.
  private long _length;   // Data length of the sample.
  private long _duration; // The duration of the sample in time scale units.

  public Sample(int duration, long offset, long length) {
    _duration = duration;
    _offset = offset;
    _length = length;
  }

  public long getOffset() {
    return _offset;
  }

  public long getLength() {
    return _length;
  }

  public long getDuration() {
    return _duration;
  }
}