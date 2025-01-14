package com.aetherteam.protectyourmoa.data;

import com.aetherteam.protectyourmoa.data.generators.ProtectItemModelData;
import com.aetherteam.protectyourmoa.data.generators.ProtectLanguageData;
import com.aetherteam.protectyourmoa.data.generators.ProtectRecipeData;
import net.minecraft.SharedConstants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Map;

public class ProtectData {
    public static void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();

        // Client Data
        generator.addProvider(event.includeClient(), new ProtectItemModelData(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ProtectLanguageData(packOutput));

        // Server Data
        generator.addProvider(event.includeServer(), new ProtectRecipeData(packOutput));

        // pack.mcmeta
        PackMetadataGenerator packMeta = new PackMetadataGenerator(packOutput);
        Map<PackType, Integer> packTypes = Map.of(PackType.SERVER_DATA, SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
        packMeta.add(PackMetadataSection.TYPE, new PackMetadataSection(Component.translatable("pack.aether_protect_your_moa.mod.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES), packTypes));
        generator.addProvider(true, packMeta);
    }
}
