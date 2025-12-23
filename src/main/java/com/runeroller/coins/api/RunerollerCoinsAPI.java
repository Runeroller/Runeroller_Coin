package com.runeroller.coins.api;

import com.runeroller.coins.RunerollerCoins;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Public API for other Runeroller mods.
 * Use this from other mods to give/take/count coins.
 */
public final class RunerollerCoinsAPI {
    private RunerollerCoinsAPI() {}

    /** The coin Item (for comparisons / recipes / etc). */
    public static Item getCoinItem() {
        return RunerollerCoins.COIN.get();
    }

    /** Convenience: create a coin stack. */
    public static ItemStack coinStack(int amount) {
        return new ItemStack(getCoinItem(), Math.max(0, amount));
    }

    /** Count how many coins a player has in their inventory. */
    public static int countCoins(Player player) {
        int total = 0;
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty() && stack.is(getCoinItem())) {
                total += stack.getCount();
            }
        }
        return total;
    }

    /**
     * Try to remove coins from inventory.
     * @return true if the player had enough coins and we removed them.
     */
    public static boolean takeCoins(Player player, int amount) {
        if (amount <= 0) return true;

        int have = countCoins(player);
        if (have < amount) return false;

        int remaining = amount;

        // Shrink stacks until we've removed enough
        for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack stack = player.getInventory().items.get(i);
            if (stack.isEmpty()) continue;
            if (!stack.is(getCoinItem())) continue;

            int take = Math.min(stack.getCount(), remaining);
            stack.shrink(take);
            remaining -= take;

            if (remaining <= 0) break;
        }
        return true;
    }

    /**
     * Give coins to the player (inventory first; if full, drops at player).
     */
    public static void giveCoins(Player player, int amount) {
        if (amount <= 0) return;

        ItemStack stack = coinStack(amount);

        // player.addItem returns false if it couldn't fully add
        boolean added = player.addItem(stack.copy());
        if (!added) {
            player.drop(stack, false);
        }
    }

    /**
     * Checks if the player can afford a cost.
     */
    public static boolean canAfford(Player player, int cost) {
        return countCoins(player) >= cost;
    }

    /**
     * Atomic "purchase": checks cost, removes coins if possible.
     * @return true if paid successfully.
     */
    public static boolean tryPay(Player player, int cost) {
        return takeCoins(player, cost);
    }
}
