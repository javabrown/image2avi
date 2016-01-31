package image2avi;

import java.io.IOException;
import java.util.Scanner;

import com.jbrown.image2avi.utils.StringUtils;

import static image2avi.LaunchOption.*;

public class CommandLineLaunch {

  public String getCLIInput(String[] args, LaunchOption lookupOption) {
    int maxArgCount = args.length;

    for (int i = 0; i < args.length; i++) {
      boolean isOptionFound = lookupOption.typeOf(args[i]);
      if (isOptionFound && i < maxArgCount) {
        String cliOptions = args[i + 1];

        if (!StringUtils.isEmpty(cliOptions)) {
          return cliOptions;
        }
      }
    }

    return null;
  }

  public void processCommand(String[] cmd) {
    String src = getCLIInput(cmd, SRC);
    String dest = getCLIInput(cmd, DEST);

    System.out.printf("Valid launch options are : %s",
            LaunchOption.getAllOptions());
  }

}