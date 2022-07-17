package com.github.nicholasmoser;

import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class KaitaiUtilTest {

  @Test
  public void test() throws Exception {
    KaitaiUtil.readFile(Paths.get("C:/ffmpeg/bin/ffmpeg.exe"));
  }
}
