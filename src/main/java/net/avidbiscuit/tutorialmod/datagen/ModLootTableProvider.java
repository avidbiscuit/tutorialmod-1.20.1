package net.avidbiscuit.tutorialmod.datagen;

import net.avidbiscuit.tutorialmod.block.ModBlocks;
import net.avidbiscuit.tutorialmod.block.custom.TomatoCropBlock;
import net.avidbiscuit.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;


public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.RUBY_BLOCK);
        addDrop(ModBlocks.SOUND_BLOCK);
        addDrop(ModBlocks.WEATHERED_BRICK);
        addDrop(ModBlocks.WEATHERED_WOOD);
        addDrop(ModBlocks.WEATHERED_WOOD_PLANK);

        addDrop(ModBlocks.RAW_RUBY_BLOCK, copperLikeOreDrops(ModBlocks.RAW_RUBY_BLOCK, ModItems.RAW_RUBY));
        addDrop(ModBlocks.RUBY_ORE, copperLikeOreDrops(ModBlocks.RUBY_ORE, ModItems.RAW_RUBY));
        addDrop(ModBlocks.END_STONE_RUBY_ORE, copperLikeOreDrops(ModBlocks.END_STONE_RUBY_ORE, ModItems.RAW_RUBY));

        addDrop(ModBlocks.WEATHERED_WOOD_BUTTON);
        addDrop(ModBlocks.WEATHERED_WOOD_PRESSURE_PLATE);
        addDrop(ModBlocks.WEATHERED_WOOD_STAIRS);
        addDrop(ModBlocks.WEATHERED_WOOD_FENCE);
        addDrop(ModBlocks.WEATHERED_WOOD_FENCE_GATE);
        addDrop(ModBlocks.RUBY_WALL);
        addDrop(ModBlocks.WEATHERED_WOOD_TRAP_DOOR);

        addDrop(ModBlocks.WEATHERED_WOOD_DOOR, doorDrops(ModBlocks.WEATHERED_WOOD_DOOR));
        addDrop(ModBlocks.WEATHERED_WOOD_SLAB, slabDrops(ModBlocks.WEATHERED_WOOD_SLAB));

        BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition.builder(ModBlocks.TOMATO_CROP).properties(StatePredicate.Builder.create()
                .exactMatch(TomatoCropBlock.AGE, 5));
        addDrop(ModBlocks.TOMATO_CROP, cropDrops(ModBlocks.TOMATO_CROP, ModItems.TOMATO, ModItems.TOMATO_SEEDS, builder));


    }
    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return dropsWithSilkTouch(
                drop,
                (LootPoolEntry.Builder<?>)this.applyExplosionDecay(
                        drop,
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }

}
