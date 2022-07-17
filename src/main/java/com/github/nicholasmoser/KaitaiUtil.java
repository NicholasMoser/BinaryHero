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
      // Structured Text - Description Encounters
      case "json", "_json", "xml", "vcxproj", "csproj", "html", "txt", "log", "md5", "gitconfig", "gradle", "sha512", "gitignore", "xaml", "properties", "settings", "config", "conf", "lua", "go", "c++", "java", "bat", "cmd", "sh", "ps1", "php", "c", "js", "h", "cs", "py", "cpp", "css" -> {
        return scenario(filePath);
      }

      // Text - Description Encounters

      // Executable - Items
      case "class", "exe", "dll", "sys", "elf" -> {
        return item(filePath); // executable/class.ksy
      }
      // executable/microsoft_pe.ksy
      // executable/elf.ksy

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
      case "bmp", "gif", "ico", "png", "jpg", "jpeg", "tif", "tiff", "svg", "webp", "mid", "midi", "smf", "wav", "ogg", "mp3", "avi", "webm", "mkv", "mp4" -> {
        return enemy(filePath); // image/bmp.ksy
      }
      // image/gif.ksy
      // image/ico.ksy
      // image/png.ksy
      // image/jpeg.ksy

      // Audio - Combat
      // media/standard_midi_file.ksy
      // media/ogg.ksy
      // media/wav.ksy Java class seems to have some issues

      // Video - Combat
      //avi.ksy

      // Other - Random
      //case "ttf" -> {
      //  return random(filePath); // font/ttf.ksy
      //}
      //case "res" -> {
      //  return random(filePath); // windows/windows_resource_file.ksy
      //}
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
