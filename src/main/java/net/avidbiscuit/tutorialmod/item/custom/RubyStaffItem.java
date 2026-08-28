package net.avidbiscuit.tutorialmod.item.custom;

import net.avidbiscuit.tutorialmod.block.ModBlocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class RubyStaffItem  extends Item {
    public RubyStaffItem(Settings settings) {
        super(settings.maxDamage(1));

    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();

        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        if (!ManaManager.hasMana(10)) {
            if (player != null) {
                player.playSound(
                        SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
                        1f,
                        1f
                );

                System.out.println("Thunder sound played");

                if (!world.isClient()) {
                    world.createExplosion(
                            null,
                            pos.getX() + 0.5,
                            pos.getY() + 1.0,
                            pos.getZ() + 0.5,
                            2.0F,
                            World.ExplosionSourceType.TNT
                    );
                }

                player.sendMessage(
                        Text.literal("Not enough mana!"),
                        true
                );
                breakStaff(context);
            }

            return ActionResult.FAIL;
        }

        if (!context.getWorld().isClient()) {
            world.setBlockState(
                    pos,
                    ModBlocks.RUBY_BLOCK.getDefaultState(),
                    3
            );

            assert context.getPlayer() != null;

            applyEffects(context.getPlayer());
            playerHasEffect(context.getPlayer());

            context.getPlayer().getItemCooldownManager().set(this, 100);

            // ONLY consumes mana on the server
            ManaManager.useMana(10);
            //Breaks the staff on successful use
            breakStaff(context);
        }

        return ActionResult.SUCCESS;

    }
    void breakStaff(ItemUsageContext context){
        assert context.getPlayer() != null;
        context.getStack().damage(1, context.getPlayer(),
                player -> player.sendToolBreakStatus(context.getHand()));
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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.ruby_staff.tooltip").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}

