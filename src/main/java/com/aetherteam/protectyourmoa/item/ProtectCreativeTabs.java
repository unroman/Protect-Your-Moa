package com.aetherteam.protectyourmoa.item;

import com.aetherteam.aether.item.AetherCreativeTabs;
import com.aetherteam.protectyourmoa.ProtectYourMoa;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProtectYourMoa.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProtectCreativeTabs {
    @SubscribeEvent
    public static void buildCreativeModeTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if (tab == AetherCreativeTabs.AETHER_ARMOR_AND_ACCESSORIES.getKey()) {
            event.getEntries().put(new ItemStack(ProtectItems.ZANITE_MOA_ARMOR.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(ProtectItems.ZANITE_MOA_ARMOR.get()), new ItemStack(ProtectItems.GRAVITITE_MOA_ARMOR.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
