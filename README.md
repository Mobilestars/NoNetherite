# NetheriteBlocker

**Version:** 1.0.0  
**Minecraft Version:** 1.18+  
**Authors:** Mobilestars  

## Description

NetheriteBlocker is a simple Spigot/Paper plugin that prevents players from using Netherite armor and tools.  
It blocks:

- Equipping Netherite armor
- Using Netherite tools
- Receiving Netherite armor via dispensers
- Moving Netherite items in inventories

Configurable through `config.yml`.

---

## Features

- Blocks equipping Netherite armor
- Prevents using Netherite tools for mining and combat
- Stops dispensers from giving Netherite armor
- Configurable via `noNetherite` option
- Lightweight and easy to use

---

## Installation

1. Download the plugin from [Modrinth](https://modrinth.com/project/netheriteblocker).  
2. Place the `.jar` file in your server's `plugins` folder.  
3. Restart your server or use `/reload`.  
4. Edit `config.yml` if needed:

```yaml
noNetherite: true
