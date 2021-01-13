package com.remodstudios.updagri;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Updagri implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("Update Agriculture");
	public static final String MODID = "updagri";

	@Override
	public void onInitialize() {
		LOGGER.info("Hello from Updagri");
	}
}
