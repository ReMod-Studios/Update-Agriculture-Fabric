package com.remodstudios.updagri;

import com.remodstudios.updagri.item.WateringPailItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraft.util.registry.Registry.ITEM;
import static net.minecraft.util.registry.Registry.register;

public class Updagri implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("Update Agriculture");
	public static final String MODID = "updagri";

	public static final WateringPailItem WATERING_PAIL = new WateringPailItem();

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Update Agriculture");
		register(ITEM, new Identifier(MODID, "watering_pail"), WATERING_PAIL);
	}
}
