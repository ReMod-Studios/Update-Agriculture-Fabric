package com.remodstudios.updagri;

import com.remodstudios.updagri.item.WateringPailItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Updagri implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("Update Agriculture");
	public static final String MODID = "updagri";

	public static final WateringPailItem WATERING_PAIL = new WateringPailItem();

	@Override
	public void onInitialize() {
		LOGGER.info("Hello from Updagri");
		Registry.register(Registry.ITEM, new Identifier(MODID, "watering_pail"), WATERING_PAIL);
	}
}
