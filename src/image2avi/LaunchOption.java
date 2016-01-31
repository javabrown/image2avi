package image2avi;

import com.jbrown.image2avi.utils.KeysI;
import com.jbrown.image2avi.utils.StringUtils;


public enum LaunchOption {
  SRC("-src"), DEST("-dest"), REVERSE("-reverse"), DEFAULT("default");

  private String _name;

  LaunchOption(String name) {
    _name = name;
  }

  public String getName(){
    return _name;
  }


  public static LaunchOption find(String optionName) {
    if (!StringUtils.isEmpty(optionName)) {
      for (LaunchOption opt : values()) {
        if (opt.getName().equalsIgnoreCase(optionName)) {
          return opt;
        }
      }
    }

    return DEFAULT;
  }

  public boolean typeOf(String optionName) {
    LaunchOption opt = find(optionName);
    if (this.equals(opt)) {
      return true;
    }
    return false;
  }

  public static String getAllOptions() {
     StringBuilder str = new StringBuilder();
     int index = 0;

     for(LaunchOption opt : values()){
        if(index > 0 ) {
           str.append(KeysI.COMMA_K).append(KeysI.SPACE_K);
        }

        str.append(opt);
        index++;
     }
     return String.format(" [ %s ]", str.toString());
  }
}