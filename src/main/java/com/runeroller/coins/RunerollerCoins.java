package com.runeroller.coins;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(RunerollerCoins.MODID)
public class RunerollerCoins {

    public static final String MODID = "runeroller_coins";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredItem<Item> COIN =
            ITEMS.registerSimpleItem("coin", new Item.Properties().stacksTo(64));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RUNEROLLER_COINS_TAB =
            CREATIVE_MODE_TABS.register("runeroller_coins_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.runeroller_coins"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> COIN.get().getDefaultInstance())
                    .displayItems((parameters, output) -> output.accept(COIN.get()))
                    .build());

    public RunerollerCoins(IEventBus modEventBus, ModContainer modContainer) {
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, RunerollerCoinsConfig.SPEC);

        // ✅ Guaranteed event registration
        NeoForge.EVENT_BUS.addListener(CoinDropHandler::onLivingDrops);

        LOGGER.info("Runeroller Coins loaded. Coin drops listener registered.");
    }
}
