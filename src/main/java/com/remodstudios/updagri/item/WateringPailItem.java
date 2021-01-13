package com.remodstudios.updagri.item;

import com.remodstudios.updagri.Updagri;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WateringPailItem extends Item {

	public WateringPailItem() {
		super(new FabricItemSettings().maxCount(1).group(ItemGroup.MISC));
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		CompoundTag tag = user.getStackInHand(hand).getOrCreateTag();
		if (!tag.contains("hasWater")) {
			tag.putBoolean("hasWater", false);
		}
		if (tag.getBoolean("hasWater")) {
			Updagri.LOGGER.info("hasWater");
		}
		return TypedActionResult.pass(user.getStackInHand(hand));
	}
}
