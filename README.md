# Binary Hero

Go on an adventure in your computer's file system! This game is a submission for the
[GMTK Game Jam 2022](https://itch.io/jam/gmtk-jam-2022).

## What is this game?

In this game you navigate through your computer's file system and encounter items and
battles based on the files you encounter. Every file has a unique encounter defined for it.
This game is heavily inspired by
[Sonic 3 & Knuckles Lock-On Technology](https://segaretro.org/Lock-On_Technology),
which let you plug in any Sega Genesis game and get a unique
[Blue Sphere](https://sonic.fandom.com/wiki/Blue_Sphere)
stage. It was further inspired by games like [AudioSurf](https://www.audio-surf.com/) which
generate a unique level based on the music track.

![Example 1](/docs/example1.png?raw=true "Example 1")

## How to Play

Simply use your mouse or keyboard to interact with the icons to explore.

The main objective is to become as strong as possible and accumulate as much gold as possible.
Here's a description of the different encounters you may see:

- **Doors** - Doors simply are folders/directories. Clicking on one enters that folder/directory.
- **Locked Doors** - Folders/directories or files that are inaccessible by the game.
- **Paper** - Source code, text, or structured text files. These encounters are non-combat scenarios.
- **Swords** - Image, audio, or video files. These encounters are combat scenarios.
- **Potion** - Executable files. These encounters are items that you find.
- **Question Mark** - All other files. These encounters are random.

The end-game can be found in your Windows folder (e.g. C:/Windows). Encounters in this folder
will be **much** harder but with higher rewards.

![Example 2](/docs/example2.png?raw=true "Example 2")

## Other Thoughts

The idea of the game is that there are many paths in your file system you can explore. Users may
also create their own groups of files to create unique repeatable dungeons that others can play.
New file type support and more advanced RPG mechanics would be added over time.

The code of the game currently does have the ability to look at the binary structure of certain
files using [Kaitai](https://kaitai.io/). Had I more time, I would tailor each encounter to the
attributes of the files themselves, such as image size or video quality. As of now, the encounter
characteristics is determined by a [CRC32 hash](https://en.wikipedia.org/wiki/Cyclic_redundancy_check).

## Ideas

- Maybe randomize the order of the icons and don't show the file name. That way there is randomness
  to selecting the next encounter
- Use gold for something
- Save game

## Attribution

Icons and graphics created by **Freepik**.

- Flaticon License: Free for personal and commercial purpose with attribution.
  - [Locked Door](https://www.flaticon.com/premium-icon/locked-door_2790347)
  - [Unlocked Door](https://www.flaticon.com/premium-icon/dungeon_2790344)
  - [Upgrade](https://www.flaticon.com/premium-icon/upgrade_2790406)
  - [Chest](https://www.flaticon.com/premium-icon/chest_3021631)
  - [Log Out](https://www.flaticon.com/premium-icon/log-out_2574151)
  - [Swords](https://www.flaticon.com/premium-icon/swords_3763558)
  - [Papyrus](https://www.flaticon.com/premium-icon/papyrus_3763514)
  - [Game Over](https://www.flaticon.com/premium-icon/game-over_2790415)
  - [Final Boss](https://www.flaticon.com/premium-icon/final-boss_2790435)
  - [Buff](https://www.flaticon.com/premium-icon/buff_2790394)
  - [Item](https://www.flaticon.com/premium-icon/potion_2790362)
  - [Buff](https://www.flaticon.com/premium-icon/buff_2790394)
  - [Nerf](https://www.flaticon.com/premium-icon/nerf_2790400)
  - [Slime](https://www.flaticon.com/premium-icon/green-slime_5980291)
  - [Skeleton](https://www.flaticon.com/free-icon/skull_3325103)
  - [Knight](https://www.flaticon.com/premium-icon/knight_2701795)
- CC0-1.0, MIT, BSD-3-Clause-Attribution
  - [Kaitai Struct Formats](https://github.com/kaitai-io/kaitai_struct_formats)
- CC0 License
  - [Boardgame Pack](https://www.kenney.nl/assets/boardgame-pack)