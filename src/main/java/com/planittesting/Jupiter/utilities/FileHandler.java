package com.planittesting.Jupiter.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<String> readFile(String path)
    {
      Path folderPath = Paths.get(path);

      List<String> fileNames = new ArrayList<>();
      try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
          for (Path filepath : directoryStream)
          {
              fileNames.add(filepath.toString());
          }
      } catch (IOException e) {
          System.err.println("Error Reading the File");
          e.printStackTrace();
      }
      return fileNames;
    }

    public static String readFileString(String path) throws IOException{
        byte[] str = Files.readAllBytes(Paths.get(path));
        return new String(str);
    }
}
