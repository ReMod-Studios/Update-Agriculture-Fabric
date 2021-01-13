package com.remodstudios.updagri.item;

import com.remodstudios.updagri.Updagri;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
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
				BlockPos playerPos = user.getBlockPos().down();
				Iterable<BlockPos> positions = BlockPos.iterate(playerPos.east().north(), playerPos.west().south());
				//TODO: keep it like this for 300 ticks (might need mixin tbh)
				positions.forEach((pos) -> {
					if (world.getBlockState(pos).getBlock() == Blocks.FARMLAND && world.getBlockState(pos).get(FarmlandBlock.MOISTURE) < 7) {
						world.setBlockState(pos, world.getBlockState(pos).with(FarmlandBlock.MOISTURE, 7));
					}
				});
				return TypedActionResult.success(user.getStackInHand(hand));
			}
			return TypedActionResult.pass(user.getStackInHand(hand));
		}
		return TypedActionResult.fail(user.getStackInHand(hand));
	}
}
