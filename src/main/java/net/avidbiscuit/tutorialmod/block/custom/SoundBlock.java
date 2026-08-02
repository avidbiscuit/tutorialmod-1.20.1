package net.avidbiscuit.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoundBlock extends Block {

    public SoundBlock(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            playCowbell(world, pos);
        }
        return ActionResult.SUCCESS;
    }

    // Play sound when redstone above or powering it changes
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos,
                               Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            BlockPos abovePos = pos.up();
            BlockState aboveState = world.getBlockState(abovePos);

            boolean isRedstoneBlock = aboveState.isOf(Blocks.REDSTONE_BLOCK);
            boolean isPoweredDust = aboveState.isOf(Blocks.REDSTONE_WIRE)
                    && aboveState.get(RedstoneWireBlock.POWER) > 0;
            boolean poweredFromAnySide = world.isReceivingRedstonePower(pos);

            if (isRedstoneBlock || isPoweredDust || poweredFromAnySide) {
                playCowbell(world, pos);
            }
        }
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }

    // Helper method to play the sound
    private void playCowbell(World world, BlockPos pos) {
        world.playSound(
                null, // null = play for all nearby players
                pos,
                SoundEvents.BLOCK_NOTE_BLOCK_COW_BELL.value(),
                SoundCategory.BLOCKS,
                1f,
                1f
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.sound_block.tooltip").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, options);
    }
}