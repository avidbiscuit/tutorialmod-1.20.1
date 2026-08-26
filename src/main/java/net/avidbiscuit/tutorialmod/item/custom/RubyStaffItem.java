package net.avidbiscuit.tutorialmod.item.custom;

import net.avidbiscuit.tutorialmod.block.ModBlocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Random;

public class RubyStaffItem  extends Item {
    public RubyStaffItem(Settings settings) {
        super(settings);

    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (!ManaManager.hasMana(9)) {
            if (player != null) {
                player.sendMessage(
                        Text.literal("Not enough mana!"),
                        true
                );
            }
            return ActionResult.FAIL;
        }

        if(!context.getWorld().isClient()){
            var pos = context.getBlockPos();
            world.setBlockState(
                    pos,
                    ModBlocks.RUBY_BLOCK.getDefaultState(),
                    3
            );
            assert context.getPlayer() != null;
            applyEffects(context.getPlayer());
            playerHasEffect(context.getPlayer());
        }
        assert context.getPlayer() != null;
        context.getPlayer().getItemCooldownManager().set(this, 100);
        ManaManager.useMana(10);
        return ActionResult.SUCCESS;

    }
    void applyEffects(PlayerEntity player){
        Random random = new Random();

        // Slowness
        if (random.nextBoolean()) {
            player.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.SLOWNESS,
                            200,
                            1
                    )
            );
        }

        // Blindness
        if (random.nextBoolean()) {
            player.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.BLINDNESS,
                            200,
                            0
                    )
            );
        }

        // Mining Fatigue
        if (random.nextBoolean()) {
            player.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.MINING_FATIGUE,
                            200,
                            1
                    )
            );
        }
    }
    void playerHasEffect(PlayerEntity player) {
        if (player.hasStatusEffect(StatusEffects.SLOWNESS)|| player.hasStatusEffect(StatusEffects.MINING_FATIGUE) || player.hasStatusEffect(StatusEffects.BLINDNESS)) {
            player.sendMessage(Text.literal("The Gods are displeased"), true);
        }
    }
}

