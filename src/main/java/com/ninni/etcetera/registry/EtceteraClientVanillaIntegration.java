package com.ninni.etcetera.registry;

import com.google.common.reflect.Reflection;
import com.ninni.etcetera.client.TidalHelmetHud;
import com.ninni.etcetera.client.gui.screen.PricklyCanScreen;
import com.ninni.etcetera.client.particles.GoldenParticle;
import com.ninni.etcetera.client.particles.RubberParticle;
import com.ninni.etcetera.client.render.block.entity.ItemStandBlockEntityRenderer;
import com.ninni.etcetera.client.render.entity.AdventurerArmorRenderer;
import com.ninni.etcetera.client.render.entity.ChappleRenderer;
import com.ninni.etcetera.client.render.entity.CobwebProjectileEntityRenderer;
import com.ninni.etcetera.client.render.entity.CottonArmorRenderer;
import com.ninni.etcetera.client.render.entity.GoldenGolemRenderer;
import com.ninni.etcetera.client.render.entity.RubberChickenRenderer;
import com.ninni.etcetera.client.render.entity.SilkArmorRenderer;
import com.ninni.etcetera.client.render.entity.TidalArmorRenderer;
import com.ninni.etcetera.client.render.entity.TurtleRaftRenderer;
import com.ninni.etcetera.client.render.entity.WeaverRenderer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;

public class EtceteraClientVanillaIntegration {
    public static void clientInit() {
        registerBlockEntityRenderer();
        registerArmor();
        registerBlockRenderLayers();
        registerScreens();
        registerEntityModelLayers();
        registerModelPredicates();
        registerColorProviders();
        registerParticles();
    }

    // client

    private static void registerParticles() {
        ParticleFactoryRegistry instance = ParticleFactoryRegistry.getInstance();
        instance.register(EtceteraParticleTypes.GOLDEN_HEART, GoldenParticle.Factory::new);
        instance.register(EtceteraParticleTypes.GOLDEN_SHEEN, GoldenParticle.Factory::new);
        instance.register(EtceteraParticleTypes.DRIPPING_RUBBER, sprites -> (type, world, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            SpriteBillboardParticle drippingRubber = RubberParticle.createDrippingRubber(world, x, y, z, xSpeed, ySpeed, zSpeed);
            drippingRubber.setSprite(sprites);
            return drippingRubber;
        });
        instance.register(EtceteraParticleTypes.FALLING_RUBBER, sprites -> (type, world, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            SpriteBillboardParticle fallingRubber = RubberParticle.createFallingRubber(world, x, y, z, xSpeed, ySpeed, zSpeed);
            fallingRubber.setSprite(sprites);
            return fallingRubber;
        });
        instance.register(EtceteraParticleTypes.LANDING_RUBBER, sprites -> (type, world, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            SpriteBillboardParticle landingRubber = RubberParticle.createLandingRubber(world, x, y, z, xSpeed, ySpeed, zSpeed);
            landingRubber.setSprite(sprites);
            return landingRubber;
        });
    }

    private static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(EtceteraItems.GOLDEN_GOLEM, new Identifier("broken"), (stack, world, entity, seed) -> {
            if (stack.hasNbt() && stack.getNbt().contains("Broken") && stack.getNbt().getBoolean("Broken")) return 1;
            return 0;
        });

    }

    @SuppressWarnings("deprecation")
    private static void registerBlockEntityRenderer() {
        BlockEntityRendererRegistry.register(EtceteraBlockEntityType.ITEM_STAND, ItemStandBlockEntityRenderer::new);
    }

    private static void registerBlockRenderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                EtceteraBlocks.IRIDESCENT_GLASS,
                EtceteraBlocks.IRIDESCENT_GLASS_PANE,
                EtceteraBlocks.LIGHT_BULB,
                EtceteraBlocks.TINTED_LIGHT_BULB,
                EtceteraBlocks.FOOTSTEPS
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                EtceteraBlocks.BISMUTH_BARS,
                EtceteraBlocks.BOUQUET,
                EtceteraBlocks.COTTON,
                EtceteraBlocks.POTTED_BOUQUET,
                EtceteraBlocks.ITEM_STAND,
                EtceteraBlocks.GLOW_ITEM_STAND,
                EtceteraBlocks.FRAME,
                EtceteraBlocks.DREAM_CATCHER,
                EtceteraBlocks.PRICKLY_CAN,
                EtceteraBlocks.COPPER_TAP
        );
    }

    private static void registerArmor() {
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.WHITE_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.LIGHT_GRAY_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.GRAY_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.BLACK_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.BROWN_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.RED_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.ORANGE_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.YELLOW_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.LIME_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.GREEN_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.CYAN_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.LIGHT_BLUE_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.BLUE_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.PURPLE_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.MAGENTA_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.PINK_SWEATER);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.TRADER_ROBE);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.WHITE_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.LIGHT_GRAY_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.GRAY_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.BLACK_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.BROWN_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.RED_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.ORANGE_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.YELLOW_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.LIME_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.GREEN_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.CYAN_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.LIGHT_BLUE_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.BLUE_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.PURPLE_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.MAGENTA_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.PINK_HAT);
        ArmorRenderer.register(new CottonArmorRenderer(), EtceteraItems.TRADER_HOOD);
        ArmorRenderer.register(new TidalArmorRenderer(), EtceteraItems.TIDAL_HELMET);
        ArmorRenderer.register(new SilkArmorRenderer(), EtceteraItems.SILKEN_SLACKS);
        ArmorRenderer.register(new AdventurerArmorRenderer(), EtceteraItems.ADVENTURERS_BOOTS);
        TidalHelmetHud.init();
    }

    private static void registerScreens() {
        HandledScreens.register(EtceteraScreenHandlerType.PRICKLY_CAN, PricklyCanScreen::new);
    }

    private static void registerEntityModelLayers() {
        Reflection.initialize(EtceteraEntityModelLayers.class);
        EntityRendererRegistry.register(EtceteraEntityType.TURTLE_RAFT, TurtleRaftRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.CHAPPLE, ChappleRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.EGGPLE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.WEAVER, WeaverRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.COBWEB, CobwebProjectileEntityRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.GOLDEN_GOLEM, GoldenGolemRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.THROWN_GOLDEN_GOLEM, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.RUBBER_CHICKEN, RubberChickenRenderer::new);
    }

    private static void registerColorProviders() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem)stack.getItem()).getColor(stack), EtceteraItems.TURTLE_RAFT);
    }
}
