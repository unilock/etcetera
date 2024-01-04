package com.ninni.etcetera.client.model;

import com.ninni.etcetera.client.render.animation.GoldenGolemAnimations;
import com.ninni.etcetera.entity.GoldenGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.EntityPose;
import net.minecraft.util.math.MathHelper;

import static net.minecraft.client.render.entity.model.EntityModelPartNames.*;

@SuppressWarnings("FieldCanBeLocal, unused")
@Environment(value= EnvType.CLIENT)
public class GoldenGolemModel<T extends GoldenGolemEntity> extends SinglePartEntityModel<T> {
    public static final String ALL = "all";

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart head;
    private final ModelPart arms;
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public GoldenGolemModel(ModelPart root) {
        this.root = root;


        this.all = root.getChild(ALL);
        this.head = all.getChild(HEAD);
        this.arms = all.getChild(ARMS);
        this.rightWing = all.getChild(RIGHT_WING);
        this.leftWing = all.getChild(LEFT_WING);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData all = modelPartData.addChild(
                ALL,
                ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 18.0F, 0.0F)
        );

        ModelPartData head = all.addChild(
                HEAD,
                ModelPartBuilder.create()
                        .uv(0, 13)
                        .cuboid(-3.0F, 0.0F, -2.5F, 6.0F, 1.0F, 0.0F)
                        .uv(0, 0)
                        .cuboid(-3.0F, -5.0F, -2.5F, 6.0F, 5.0F, 5.0F),
                ModelTransform.pivot(0.0F, 0.0F, -0.5F)
        );

        ModelPartData arms = all.addChild(
                ARMS,
                ModelPartBuilder.create()
                        .uv(20, 8)
                        .cuboid(-2.0F, 2.0F, -1.5F, 4.0F, 2.0F, 2.0F)
                        .uv(17, 0)
                        .cuboid(1.0F, -1.0F, -1.5F, 1.0F, 3.0F, 2.0F)
                        .uv(17, 0)
                        .mirrored()
                        .cuboid(-2.0F, -1.0F, -1.5F, 1.0F, 3.0F, 2.0F)
                        .mirrored(false),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightWing = all.addChild(
                RIGHT_WING,
                ModelPartBuilder.create()
                        .uv(0, 2)
                        .mirrored()
                        .cuboid(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 8.0F)
                        .mirrored(false),
                ModelTransform.pivot(-0.5F, 0.5F, 1.0F)
        );

        ModelPartData leftWing = all.addChild(
                LEFT_WING,
                ModelPartBuilder.create()
                        .uv(0, 2)
                        .cuboid(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 8.0F),
                ModelTransform.pivot(0.5F, 0.5F, 1.0F)
        );

        return TexturedModelData.of(modelData, 32, 16);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        if (entity.getPose() != EntityPose.CROAKING) {
            float l = MathHelper.cos(animationProgress * 20.0F * 0.017453292F + limbAngle) * 3.1415927F * 0.15F + limbDistance;
            float n = animationProgress * 9.0F * 0.017453292F;
            float tilt = Math.min(limbDistance / 0.3F, 1.0F);
            float p = 1.0F - tilt;

            this.head.pitch = headPitch * ((float) Math.PI / 180);
            this.head.yaw = headYaw * ((float) Math.PI / 180);

            this.rightWing.pitch = tilt;
            this.arms.pitch = tilt;
            this.leftWing.pitch = tilt;

            this.rightWing.yaw = -0.7853982F + l;
            this.leftWing.yaw = 0.7853982F - l;
            this.all.pivotY += (float) Math.cos(n) * 0.25F * p;
            this.arms.roll = MathHelper.cos(n + 4.712389F) * 3.1415927F * 0.025F * p;
        } else {
            this.updateAnimation(entity.grantingAnimationState, GoldenGolemAnimations.GRANT, animationProgress);
        }

    }


    @Override
    public ModelPart getPart() {
        return this.root;
    }
}