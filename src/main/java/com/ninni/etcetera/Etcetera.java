package com.ninni.etcetera;

import com.google.common.reflect.Reflection;
import com.ninni.etcetera.registry.EtceteraBlockEntityType;
import com.ninni.etcetera.registry.EtceteraBlocks;
import com.ninni.etcetera.registry.EtceteraCreativeModeTab;
import com.ninni.etcetera.registry.EtceteraEntityType;
import com.ninni.etcetera.registry.EtceteraItems;
import com.ninni.etcetera.registry.EtceteraPaintingVariants;
import com.ninni.etcetera.registry.EtceteraScreenHandlerType;
import com.ninni.etcetera.registry.EtceteraSoundEvents;
import com.ninni.etcetera.registry.EtceteraStats;
import com.ninni.etcetera.registry.EtceteraStatusEffects;
import com.ninni.etcetera.registry.EtceteraVanillaIntegration;
import com.ninni.etcetera.registry.EtceteraWorldgen;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Etcetera implements ModInitializer {
	public static final String MOD_ID = "etcetera";
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		Reflection.initialize(
				EtceteraSoundEvents.class,
				EtceteraStats.class,
				EtceteraBlockEntityType.class,
				EtceteraScreenHandlerType.class,
				EtceteraStatusEffects.class,
				EtceteraEntityType.class,
				EtceteraPaintingVariants.class,
				EtceteraCreativeModeTab.class,
				EtceteraItems.class,
				EtceteraBlocks.class
		);

		EtceteraVanillaIntegration.serverInit();
		EtceteraWorldgen.init();
	}
}