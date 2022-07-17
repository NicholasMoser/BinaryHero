package com.github.nicholasmoser;

import com.github.nicholasmoser.game.Encounter;
import com.github.nicholasmoser.game.Enemy;
import com.github.nicholasmoser.game.EnemyEncounter;
import com.github.nicholasmoser.game.Item;
import com.github.nicholasmoser.game.ItemEncounter;
import com.github.nicholasmoser.game.Scenario;
import com.github.nicholasmoser.game.ScenarioEncounter;
import com.google.common.io.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

public class KaitaiUtil {

  public static Encounter readFile(Path filePath) throws IOException {
    String extension = Files.getFileExtension(filePath.toString()).toLowerCase(Locale.ROOT);
    switch (extension) {
      // Structured Text
      //case "json", "_json" -> System.out.println("json");
      //case "xml", "vcxproj", "csproj", "html" -> System.out.println("xml");
      //case "xaml" -> System.out.println("xaml");

      // Text - Description Encounters
      case "txt", "log", "md5", "gitconfig", "gradle", "sha512", "gitignore" -> {
        return scenario(filePath);
      }
      case "properties", "settings", "config", "conf" -> {
        return scenario(filePath);
      }
      case "lua", "go", "c++", "java", "bat", "sh", "ps1", "php", "c", "js", "h", "cs", "py", "cpp", "css" -> {
        return scenario(filePath);
      }

      // Executable - Items
      case "class" -> {
        return item(filePath); // executable/class.ksy
      }
      case "exe", "dll", "sys" -> {
        return item(filePath); // executable/microsoft_pe.ksy
      }
      case "elf" -> {
        return item(filePath); // executable/elf.ksy
      }

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
      case "bmp" -> {
        return enemy(filePath); // image/bmp.ksy
      }
      case "gif" -> {
        return enemy(filePath); // image/gif.ksy
      }
      case "ico" -> {
        return enemy(filePath); // image/ico.ksy
      }
      case "png" -> {
        return enemy(filePath); // image/png.ksy
      }
      case "jpg", "jpeg" -> {
        return enemy(filePath); // image/jpeg.ksy
      }
      //case "tif", "tiff" -> System.out.println("tif");
      //case "svg" -> System.out.println("svg");
      //case "webp" -> System.out.println("webp");

      // Audio - Combat
      case "mid", "midi", "smf" -> {
        return enemy(filePath); // media/standard_midi_file.ksy
      }
      case "ogg" -> {
        return enemy(filePath); // media/ogg.ksy
      }
      //case "wav" -> System.out.println("wav"); // media/wav.ksy Java class seems to have some issues
      //case "mp3" -> System.out.println("mp3");

      // Video - Combat
      case "avi" -> {
        return enemy(filePath); //avi.ksy
      }
      //case "webm" -> System.out.println("webm");
      //case "mkv" -> System.out.println("mkv");

      // Other - Random
      case "ttf" -> {
        return random(filePath); // font/ttf.ksy
      }
      case "res" -> {
        return random(filePath); // windows/windows_resource_file.ksy
      }
      //case "torrent" -> System.out.println("torrent");
      //case "iso" -> System.out.println("iso");
      //case "mui" -> System.out.println("mui");
      //case "mum" -> System.out.println("mum");
      //case "cat" -> System.out.println("cat");
      //case "manifest" -> System.out.println("manifest");
      //case "meta" -> System.out.println("meta");
      //case "wem" -> System.out.println("wem");
      default -> {
        return random(filePath);
      }
    }
  }

  private static Encounter random(Path filePath) throws IOException {
    int hash = CRC32.getHash(filePath);
    if (hash % 11 == 0) {
      return new ScenarioEncounter(Scenario.NOTHING, isEndGame(filePath));
    } else if (hash % 11 == 1) {
      return new ScenarioEncounter(Scenario.INJURY, isEndGame(filePath));
    } else if (hash % 11 == 2) {
      return new ScenarioEncounter(Scenario.TRAINING, isEndGame(filePath));
    } else if (hash % 11 == 3) {
      return new EnemyEncounter(Enemy.SLIME, isEndGame(filePath));
    } else if (hash % 11 == 4) {
      return new EnemyEncounter(Enemy.SKELETON, isEndGame(filePath));
    } else if (hash % 11 == 5) {
      return new EnemyEncounter(Enemy.KNIGHT, isEndGame(filePath));
    } else if (hash % 11 == 6) {
      return new EnemyEncounter(Enemy.DRAGON, isEndGame(filePath));
    } else if (hash % 11 == 7) {
      return new ItemEncounter(Item.POTION, isEndGame(filePath));
    } else if (hash % 11 == 8) {
      return new ItemEncounter(Item.POISON, isEndGame(filePath));
    } else if (hash % 11 == 9) {
      return new ItemEncounter(Item.BIGGER_SWORD, isEndGame(filePath));
    } else {
      return new ItemEncounter(Item.SWORD_BREAKS, isEndGame(filePath));
    }
  }

  private static Encounter scenario(Path filePath) throws IOException {
    int hash = CRC32.getHash(filePath);
    if (hash % 3 == 0) {
      return new ScenarioEncounter(Scenario.NOTHING, isEndGame(filePath));
    } else if (hash % 3 == 1) {
      return new ScenarioEncounter(Scenario.INJURY, isEndGame(filePath));
    } else {
      return new ScenarioEncounter(Scenario.TRAINING, isEndGame(filePath));
    }
  }

  private static Encounter enemy(Path filePath) throws IOException {
    int hash = CRC32.getHash(filePath);
    if (hash % 4 == 0) {
      return new EnemyEncounter(Enemy.SLIME, isEndGame(filePath));
    } else if (hash % 4 == 1) {
      return new EnemyEncounter(Enemy.SKELETON, isEndGame(filePath));
    } else if (hash % 4 == 2) {
      return new EnemyEncounter(Enemy.KNIGHT, isEndGame(filePath));
    } else {
      return new EnemyEncounter(Enemy.DRAGON, isEndGame(filePath));
    }
  }

  private static Encounter item(Path filePath) throws IOException {
    int hash = CRC32.getHash(filePath);
    if (hash % 4 == 0) {
      return new ItemEncounter(Item.POTION, isEndGame(filePath));
    } else if (hash % 4 == 1) {
      return new ItemEncounter(Item.POISON, isEndGame(filePath));
    } else if (hash % 4 == 2) {
      return new ItemEncounter(Item.BIGGER_SWORD, isEndGame(filePath));
    } else {
      return new ItemEncounter(Item.SWORD_BREAKS, isEndGame(filePath));
    }
  }

  private static boolean isEndGame(Path filePath) {
    return !filePath.toString().contains(":\\Windows");
  }


}
