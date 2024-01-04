package com.ninni.etcetera.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;

@SuppressWarnings("FieldCanBeLocal, unused")
@Environment(value= EnvType.CLIENT)
public class CottonArmorModel<T extends LivingEntity> extends BipedEntityModel<T> {
    public CottonArmorModel(ModelPart modelPart) {
        super(modelPart);
    }
    
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0f);
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(EntityModelPartNames.HEAD,
                ModelPartBuilder.create()
                        .uv(58, 66)
                        .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(1.0F))
                        .uv(56, 48)
                        .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.51F))
                        .uv(31, 79)
                        .cuboid(-4.5F, 0.3163F, -4.5F, 9.0F, 0.0F, 9.0F)
                        .uv(-12, 64)
                        .cuboid(-6.0F, -5.21F, -6.0F, 12.0F, 0.0F, 12.0F)
                        .uv(24, 73)
                        .cuboid(-4.5F, -5.21F, -10.5F, 9.0F, 0.0F, 6.0F)
                        .uv(50, 71)
                        .cuboid(-1.0F, -9.5F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.51F))
                        .uv(40, 64)
                        .cuboid(5.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.51F))
                        .uv(50, 68)
                        .cuboid(-1.0F, -9.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                        .uv(50, 64)
                        .cuboid(-0.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F)
                        .uv(24, 64)
                        .cuboid(-2.0F, -11.0F, -2.0F, 4.0F, 3.0F, 4.0F)
                        .uv(0, 76)
                        .cuboid(-5.0F, -15.0F, -5.0F, 10.0F, 5.0F, 10.0F),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );
        
        modelPartData.addChild(
                EntityModelPartNames.BODY,
                ModelPartBuilder.create()
                        .uv(16, 48)
                        .cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.51F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );

        modelPartData.addChild(
                EntityModelPartNames.LEFT_ARM, 
                ModelPartBuilder.create()
                        .uv(40, 48)
                        .mirrored()
                        .cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.35F))
                        .mirrored(false),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F)
        );
        
        modelPartData.addChild(
                EntityModelPartNames.RIGHT_ARM,
                ModelPartBuilder.create()
                        .uv(40, 48)
                        .cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.35F)),
                ModelTransform.pivot(-5.0F, 2.0F, 0.0F)
        );

        return TexturedModelData.of(modelData, 96, 96);
    }


    public static TexturedModelData getSlimTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0f);
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(EntityModelPartNames.HEAD,
                ModelPartBuilder.create()
                        .uv(58, 66)
                        .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(1.0F))
                        .uv(56, 48)
                        .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.51F))
                        .uv(31, 79)
                        .cuboid(-4.5F, 0.3163F, -4.5F, 9.0F, 0.0F, 9.0F)
                        .uv(-12, 64)
                        .cuboid(-6.0F, -5.21F, -6.0F, 12.0F, 0.0F, 12.0F)
                        .uv(24, 73)
                        .cuboid(-4.5F, -5.21F, -10.5F, 9.0F, 0.0F, 6.0F)
                        .uv(50, 71)
                        .cuboid(-1.0F, -9.5F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.51F))
                        .uv(40, 64)
                        .cuboid(5.0F, -6.0F, -1.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.51F))
                        .uv(50, 68)
                        .cuboid(-1.0F, -9.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                        .uv(50, 64)
                        .cuboid(-0.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F)
                        .uv(24, 64)
                        .cuboid(-2.0F, -11.0F, -2.0F, 4.0F, 3.0F, 4.0F)
                        .uv(0, 76)
                        .cuboid(-5.0F, -15.0F, -5.0F, 10.0F, 5.0F, 10.0F),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );
        
        modelPartData.addChild(
                EntityModelPartNames.BODY,
                ModelPartBuilder.create()
                        .uv(16, 48)
                        .cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.51F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );
        
        modelPartData.addChild(
                EntityModelPartNames.LEFT_ARM,
                ModelPartBuilder.create()
                        .uv(0, 48)
                        .mirrored().cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.35F))
                        .mirrored(false),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F)
        );

        modelPartData.addChild(
                EntityModelPartNames.RIGHT_ARM,
                ModelPartBuilder.create()
                        .uv(0, 48)
                        .cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.35F)),
                ModelTransform.pivot(-5.0F, 2.0F, 0.0F)
        );

        return TexturedModelData.of(modelData, 96, 96);
    }
}

