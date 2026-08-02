package net.avidbiscuit.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.ChestMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Settings settings) {
        super(settings);
    }



    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();

            boolean foundMinecart = findMinecart(context.getWorld(), positionClicked, player);
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockState state = context.getWorld().getBlockState(positionClicked.down(i));

                if(isValuableBlock(state)) {
                    outputValuableCoordinates(positionClicked.down(i), player, state.getBlock());
                    foundBlock = true;

                    break;
                }
            }



            if(!foundBlock && !foundMinecart){
                player.sendMessage(Text.literal("No Valuables Found!"));
            }
        }



        context.getStack().damage(1, context.getPlayer(),
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));


        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), false);
    }

    //New method to find a minecart with a chest
    private boolean findMinecart(World world, BlockPos pos, PlayerEntity player) {
        List<ChestMinecartEntity> minecarts = world.getEntitiesByClass(
                ChestMinecartEntity.class,
                new net.minecraft.util.math.Box(pos).expand(5),
                entity -> true

        );

        if (!minecarts.isEmpty()) {
            ChestMinecartEntity cart = minecarts.get(0);

            player.sendMessage(Text.literal(
                    "Found Minecart Chest at (" +
                            cart.getBlockX() + ", " +
                            cart.getBlockY() + ", " +
                            cart.getBlockZ() + ")"
            ), false);

                return true;
        }
            return false;
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isOf(Blocks.IRON_ORE) || state.isOf(Blocks.DIAMOND_ORE);


    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.metal_detector.tooltip").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);


    }



}


