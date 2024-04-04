package com.aetherteam.protectyourmoa.client.renderer.entity.layers;

import com.aetherteam.aether.client.renderer.entity.model.MoaModel;
import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.protectyourmoa.capability.armor.MoaArmor;
import com.aetherteam.protectyourmoa.client.renderer.entity.ProtectModelLayers;
import com.aetherteam.protectyourmoa.item.combat.MoaArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;

public class MoaArmorLayer extends RenderLayer<Moa, MoaModel> {
    private final MoaModel model;

    public MoaArmorLayer(RenderLayerParent<Moa, MoaModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new MoaModel(modelSet.bakeLayer(ProtectModelLayers.MOA_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Moa moa, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        MoaArmor.get(moa).ifPresent(moaArmor -> {
            ItemStack itemStack = moaArmor.getArmor();
            if (itemStack != null && !itemStack.isEmpty() && itemStack.getItem() instanceof MoaArmorItem moaArmorItem) {
                this.getParentModel().copyPropertiesTo(this.model);
                this.model.prepareMobModel(moa, limbSwing, limbSwingAmount, partialTick);
                this.model.setupAnim(moa, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                float f = 1.0F;
                float f1 = 1.0F;
                float f2 = 1.0F;
//                if (horsearmoritem instanceof DyeableHorseArmorItem) {
//                    int i = ((DyeableHorseArmorItem)horsearmoritem).getColor(itemstack);
//                    f = (float)(i >> 16 & 255) / 255.0F;
//                    f1 = (float)(i >> 8 & 255) / 255.0F;
//                    f2 = (float)(i & 255) / 255.0F;
//                } else {
//                    f = 1.0F;
//                    f1 = 1.0F;
//                    f2 = 1.0F;
//                }

                VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(moaArmorItem.getTexture()));
                this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, f, f1, f2, 1.0F);
            }
        });
    }
}
