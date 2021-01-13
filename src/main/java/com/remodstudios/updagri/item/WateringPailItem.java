package com.remodstudios.updagri.item;

import com.remodstudios.updagri.Updagri;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WateringPailItem extends Item {

	public WateringPailItem() {
		super(new FabricItemSettings().maxCount(1).group(ItemGroup.MISC));
	}

	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) {
		CompoundTag tag = stack.getOrCreateTag();
		tag.putBoolean("hasWater", false);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient()) {
			CompoundTag tag = user.getStackInHand(hand).getOrCreateTag();
			if (tag.getBoolean("hasWater")) {
				BlockPos playerPos = user.getBlockPos();
				//TODO: keep it like this for 300 ticks (might need mixin tbh)
				for (BlockPos pos : BlockPos.iterate(playerPos.east().north().up(), playerPos.west().south().down())) {
					Updagri.LOGGER.info(pos.toShortString() + world.getBlockState(pos).getBlock());
					if (world.getBlockState(pos).getBlock() == Blocks.FARMLAND) {
						world.setBlockState(pos, Blocks.FARMLAND.getDefaultState().with(Properties.MOISTURE, 7));
					}
				}
				tag.putBoolean("hasWater", false);
				return TypedActionResult.success(user.getStackInHand(hand));
			}
			return TypedActionResult.pass(user.getStackInHand(hand));
		}
		return TypedActionResult.fail(user.getStackInHand(hand));
	}
}
