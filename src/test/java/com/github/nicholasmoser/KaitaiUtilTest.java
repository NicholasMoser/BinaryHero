package com.github.nicholasmoser;

import com.google.common.io.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class KaitaiUtilTest {

  @Test
  public void test() throws Exception {
    KaitaiUtil.readFile(Paths.get("C:/ffmpeg/bin/ffmpeg.exe"));
  }

  @Test
  public void test2() throws Exception {
    Path path = Paths.get("D:\\Videos\\Futurama\\Futurama Season 1 S01 DVDRip x264\\Futurama.S01E02.The.Series.Has.Landed.DVDRip.x264.mkv");
    System.out.println(Files.getFileExtension(path.toString()));
  }
}
