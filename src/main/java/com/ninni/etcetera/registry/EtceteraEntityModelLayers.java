package com.ninni.etcetera.registry;

import com.ninni.etcetera.Etcetera;
import com.ninni.etcetera.client.model.ChappleModel;
import com.ninni.etcetera.client.model.CobwebProjectileModel;
import com.ninni.etcetera.client.model.CottonArmorModel;
import com.ninni.etcetera.client.model.GoldenGolemModel;
import com.ninni.etcetera.client.model.RubberChickenModel;
import com.ninni.etcetera.client.model.TurtleRaftModel;
import com.ninni.etcetera.client.model.WeaverModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public interface EtceteraEntityModelLayers {

    EntityModelLayer TURTLE_RAFT = main("turtle_raft", TurtleRaftModel::getTexturedModelData);
    EntityModelLayer CHAPPLE = main("chapple", ChappleModel::getTexturedModelData);
    EntityModelLayer WEAVER = main("weaver", WeaverModel::getTexturedModelData);
    EntityModelLayer COBWEB = main("cobweb", CobwebProjectileModel::getTexturedModelData);
    EntityModelLayer PLAYER_COTTON = main("cotton", CottonArmorModel::getTexturedModelData);
    EntityModelLayer GOLDEN_GOLEM = main("golden_golem", GoldenGolemModel::getTexturedModelData);
    EntityModelLayer RUBBER_CHICKEN = main("rubber_chicken", RubberChickenModel::getTexturedModelData);

    private static EntityModelLayer register(String id, String name, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        EntityModelLayer layer = new EntityModelLayer(new Identifier(Etcetera.MOD_ID, id), name);
        EntityModelLayerRegistry.registerModelLayer(layer, provider);
        return layer;
    }
    private static EntityModelLayer main(String id, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        return register(id, "main", provider);
    }
}
