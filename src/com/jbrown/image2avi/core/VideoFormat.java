package com.jbrown.image2avi.core;

public enum VideoFormat {
    RAW("row"), JPG ("jpeg"), PNG("png");

    private final String _name;

    VideoFormat(String name){
      _name = name;
    }

    public static VideoFormat getInstance(String format) {
      for(VideoFormat type : VideoFormat.values()){
        if(type.getFormat().equalsIgnoreCase(format)){
          return type;
        }
      }

      throw new RuntimeException("Unknown VideoFormat:"+ format);
    }

    public boolean typeOf(VideoFormat format){
      return getInstance(_name).equals(format);
    }

    public String getFormat(){
      return _name;
    }
}
