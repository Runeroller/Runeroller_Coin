package com.runeroller.coins;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

public final class CoinDropHandler {

    // 100% guaranteed: gives coins on every eligible kill
    public static void onLivingDrops(LivingDropsEvent event) {
        LivingEntity victim = event.getEntity();
        Level level = victim.level();
        if (level.isClientSide) return;

        // Respect vanilla doMobLoot
        if (!level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) return;

        // Don't give coins for killing players
        if (victim instanceof Player) return;

        // Must be killed by a player (melee or projectile). If you want farms to work, remove this check.
        Player killer = getKillerPlayer(event.getSource());
        if (killer == null) return;

        // Always 1 coin (change to any number you want)
        int amount = 1;

        ItemStack coins = new ItemStack(RunerollerCoins.COIN.get(), amount);

        // Give directly to inventory (most reliable)
        boolean added = killer.addItem(coins.copy());

        // If inventory full, drop at player's feet
        if (!added) {
            ItemEntity drop = new ItemEntity(level, killer.getX(), killer.getY() + 0.5, killer.getZ(), coins);
            drop.setPickUpDelay(0);
            level.addFreshEntity(drop);
        }

        // Optional: show a short message (remove if you don't want spam)
        killer.sendSystemMessage(Component.literal("+ " + amount + " coin"));
    }

    private static Player getKillerPlayer(DamageSource source) {
        Entity attacker = source.getEntity();
        if (attacker instanceof Player p) return p;

        Entity direct = source.getDirectEntity();
        if (direct instanceof Projectile proj && proj.getOwner() instanceof Player p) return p;

        return null;
    }
}
