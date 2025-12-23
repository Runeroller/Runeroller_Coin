# Runeroller Coins

Runeroller Coins is a NeoForge mod for Minecraft **1.21.1** that adds a reusable **coin-based currency system** designed to be shared across multiple Runeroller mods and modpacks.

This mod is intended as a **core dependency** for other mods that need money, shops, NPCs, or progression systems.

---

## ✨ Features

- 💰 Coin item dropped by mobs
- ⚔ Coins drop from:
    - Common mobs
    - Animals
    - Bosses
- 🎯 Configurable drop chances and amounts
- 🧩 Public API for other mods
- 🛠 Looting enchantment support
- ⚙ Fully configurable via config file

---

## 📦 Minecraft & Loader

- **Minecraft:** 1.21.1
- **Loader:** NeoForge
- **Mod ID:** `runeroller_coins`

---

## 🪙 Coin Drops

Coins are dropped automatically when mobs are killed.

Drop behavior:
- Controlled via config
- Can require player kills
- Supports Looting enchantment
- Uses entity tags:
    - `coin_drops_common`
    - `coin_drops_animals`
    - `coin_drops_bosses`

---

## 🔌 API Usage (For Other Mods)

This mod provides a public API:

```java
import com.runeroller.coins.api.RunerollerCoinsAPI;
