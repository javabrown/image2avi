package com.jbrown.image2avi.utils;

public interface KeysI {
  String DW_CHUNK_NAME = "idx1";
  String DW_CHUNK_ID = "00dc";
  long DW_CHUNK_FLAG = 0x10;

  //fccHandler - DIB for Motion JPEG
  String FCC_HANDLER_WRITE_TYPE_DIB = "DIB ";

  // fccHandler - MJPG for Motion JPEG
  String FCC_HANDLER_WRITE_TYPE_MJPG = "MJPG";

  //fccHandler - png for PNG
  String FCC_HANDLER_WRITE_TYPE_PNG = "png ";

  //fccType - vids for video stream
  String FCC_TYPE = "vids";

  String SPACE_K = " ";
  String EMPTY_K = "";
  String COMMA_K = ",";
  String COLON_K = ":";
}
