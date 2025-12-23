package com.runeroller.coins;

import net.neoforged.neoforge.common.ModConfigSpec;

public class RunerollerCoinsConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_COIN_DROPS = BUILDER
            .comment("Enable coin drops from mobs/animals/bosses.")
            .define("coinDrops.enabled", true);

    public static final ModConfigSpec.BooleanValue REQUIRE_PLAYER_KILL = BUILDER
            .comment("If true, coins only drop when a player is the killer.")
            .define("coinDrops.requirePlayerKill", true);

    public static final ModConfigSpec.IntValue LOOTING_BONUS_PER_LEVEL = BUILDER
            .comment("Extra coins added per Looting level (adds 0..N per level).")
            .defineInRange("coinDrops.lootingBonusPerLevel", 1, 0, 16);

    // Common mobs
    public static final ModConfigSpec.DoubleValue COMMON_CHANCE = BUILDER
            .comment("Chance that an eligible common mob drops coins.")
            .defineInRange("tiers.common.chance", 0.35D, 0.0D, 1.0D);

    public static final ModConfigSpec.IntValue COMMON_MIN = BUILDER
            .comment("Minimum coins dropped by common mobs.")
            .defineInRange("tiers.common.min", 0, 0, 64);

    public static final ModConfigSpec.IntValue COMMON_MAX = BUILDER
            .comment("Maximum coins dropped by common mobs.")
            .defineInRange("tiers.common.max", 2, 0, 64);

    // Animals
    public static final ModConfigSpec.DoubleValue ANIMALS_CHANCE = BUILDER
            .comment("Chance that an eligible animal drops coins.")
            .defineInRange("tiers.animals.chance", 0.20D, 0.0D, 1.0D);

    public static final ModConfigSpec.IntValue ANIMALS_MIN = BUILDER
            .comment("Minimum coins dropped by animals.")
            .defineInRange("tiers.animals.min", 0, 0, 64);

    public static final ModConfigSpec.IntValue ANIMALS_MAX = BUILDER
            .comment("Maximum coins dropped by animals.")
            .defineInRange("tiers.animals.max", 1, 0, 64);

    // Bosses
    public static final ModConfigSpec.DoubleValue BOSSES_CHANCE = BUILDER
            .comment("Chance that an eligible boss drops coins.")
            .defineInRange("tiers.bosses.chance", 1.0D, 0.0D, 1.0D);

    public static final ModConfigSpec.IntValue BOSSES_MIN = BUILDER
            .comment("Minimum coins dropped by bosses.")
            .defineInRange("tiers.bosses.min", 25, 0, 999);

    public static final ModConfigSpec.IntValue BOSSES_MAX = BUILDER
            .comment("Maximum coins dropped by bosses.")
            .defineInRange("tiers.bosses.max", 75, 0, 999);

    static final ModConfigSpec SPEC = BUILDER.build();
}
