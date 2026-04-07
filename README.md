# Gravity Plugin

A custom [Paper](https://papermc.io/) plugin built for the **Astro Miner** Minecraft server. It uses WorldGuard regions to create special gravity zones, where each area can have its own gravity strength and physics rules. Custom physics logic prevents vanilla Minecraft from overriding the plugin's gravity behaviour.

> ⚠️ This project is in early development (`0.1.0-SNAPSHOT`). Expect breaking changes.

---

## Features

- Define custom gravity zones using WorldGuard regions
- Set unique gravity strength per region
- Restrict gravity zones to specific worlds (e.g. overworld, nether, custom planets)
- Custom physics engine that overrides vanilla Minecraft block and player physics within zones

---

## Requirements

| Dependency | Version |
|---|---|
| Java | 21+ |
| Paper | 1.21.x |
| WorldEdit | 7.4.0 |
| WorldGuard | 7.0.15 |

---

## Commands

| Command | Description | Permission |
|---|---|---|
| `/crt` | Creates a gravity region from your current WorldGuard selection, applying the specified gravity strength to a given world | `gravity.crt` |

**Usage:**
```
/crt <region> <gravity> <world>
```

- `<region>` — The WorldGuard region you are currently in
- `<gravity>` — The gravity value to apply in that region
- `<world>` — The world the region belongs to (e.g. `overworld`, `nether`, or a custom planet world)

---

## Building from Source

### Prerequisites
- Java 21 JDK
- Maven 3.x

### Steps

```bash
# Clone the repository
git clone https://github.com/nurbu/Beginging.git
cd Beginging

# Build the plugin JAR
mvn clean package
```

The output JAR will be at:
```
target/io.github.Gravity-0.1.0-SNAPSHOT.jar
```

---

## Installation

1. Make sure **WorldEdit** and **WorldGuard** are installed in your server's `plugins/` folder.
2. Drop the Gravity plugin JAR into `plugins/`.
3. Start or restart your server.
4. Configuration files will be generated in `plugins/Gravity/`.

---

## Project Structure

```
src/
└── main/
    ├── java/
    │   └── me/nurbu/gravity/   ← Plugin source code
    └── resources/              ← plugin.yml, config files
```

---

## Author

**nurbu** — Built for [Astro Miner](https://github.com/nurbu)
