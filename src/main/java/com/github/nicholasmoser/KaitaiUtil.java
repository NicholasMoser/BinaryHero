package com.github.nicholasmoser;

import com.github.nicholasmoser.game.Encounter;
import com.github.nicholasmoser.kaitai.MicrosoftPe;
import com.github.nicholasmoser.kaitai.MicrosoftPe.Section;
import com.google.common.io.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

// Make any files in C:\Windows 50x harder
public class KaitaiUtil {

  public static Encounter readFile(Path filePath) throws IOException {
    String extension = Files.getFileExtension(filePath.toString()).toLowerCase(Locale.ROOT);
    switch (extension) {
      // Structured Text
      //case "json", "_json" -> System.out.println("json");
      //case "xml", "vcxproj", "csproj", "html" -> System.out.println("xml");
      //case "xaml" -> System.out.println("xaml");

      // Text - Description Encounters
      case "txt", "log", "md5", "gitconfig", "gradle", "sha512", "gitignore" -> System.out.println("text");
      case "properties", "settings", "config", "conf" -> System.out.println("probably text");
      case "lua", "go", "c++", "java", "bat", "sh", "ps1", "php", "c", "js", "h", "cs", "py", "cpp", "css" -> System.out.println("code");

      // Executable - Items
      case "class" -> System.out.println("class"); // executable/class.ksy
      case "exe", "dll", "sys" -> {
        return parseMicrosoftPe(filePath); // executable/microsoft_pe.ksy
      }
      case "elf" -> System.out.println("elf"); // executable/elf.ksy

      // Archive - Challenge
      // TODO
      //case "zip", "odt", "docx", "jar" -> System.out.println("zip"); // archive/zip.ksy
      //case "rar" -> System.out.println("rar"); // archive/rar.ksy
      //case "gzip" -> System.out.println("gzip"); // archive/gzip.ksy
      //case "tar" -> System.out.println("tar");
      //case "7z" -> System.out.println("7z");

      // Document
      //case "rtf" -> System.out.println("rtf");
      //case "csv" -> System.out.println("csv");
      //case "doc" -> System.out.println("doc");

      // Image - Combat
      case "bmp" -> System.out.println("bmp"); // image/bmp.ksy
      case "gif" -> System.out.println("gif"); // image/gif.ksy
      case "ico" -> System.out.println("gif"); // image/ico.ksy
      case "png" -> System.out.println("png"); // image/png.ksy
      case "jpg", "jpeg" -> System.out.println("jpeg"); // image/jpeg.ksy
      //case "tif", "tiff" -> System.out.println("tif");
      //case "svg" -> System.out.println("svg");
      //case "webp" -> System.out.println("webp");

      // Audio - Combat
      case "mid", "midi", "smf" -> System.out.println("midi"); // media/standard_midi_file.ksy
      case "ogg" -> System.out.println("ogg"); // media/ogg.ksy
      //case "wav" -> System.out.println("wav"); // media/wav.ksy Java class seems to have some issues
      //case "mp3" -> System.out.println("mp3");

      // Video - Combat
      case "avi" -> System.out.println("avi"); //avi.ksy
      //case "webm" -> System.out.println("webm");
      //case "mkv" -> System.out.println("mkv");

      // Other - Random
      case "ttf" -> System.out.println("ttf"); // font/ttf.ksy
      case "res" -> System.out.println("res"); // windows/windows_resource_file.ksy
      //case "torrent" -> System.out.println("torrent");
      //case "iso" -> System.out.println("iso");
      //case "mui" -> System.out.println("mui");
      //case "mum" -> System.out.println("mum");
      //case "cat" -> System.out.println("cat");
      //case "manifest" -> System.out.println("manifest");
      //case "meta" -> System.out.println("meta");
      //case "wem" -> System.out.println("wem");
      default -> System.out.println("binary");
    }
    return null;
  }

  private static Encounter parseMicrosoftPe(Path filePath) throws IOException {
    MicrosoftPe bin = MicrosoftPe.fromFile(filePath.toString());
    for (Section section : bin._root().pe().sections()) {

    }
    return null;
  }
}
