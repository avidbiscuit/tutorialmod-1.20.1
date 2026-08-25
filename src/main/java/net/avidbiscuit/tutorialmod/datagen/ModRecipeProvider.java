package net.avidbiscuit.tutorialmod.datagen;

import net.avidbiscuit.tutorialmod.block.ModBlocks;
import net.avidbiscuit.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public static final List<ItemConvertible> RUBY_SMELTABLES = List.of(ModItems.RAW_RUBY,
            ModBlocks.END_STONE_RUBY_ORE, ModBlocks.RUBY_ORE);
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
0.7f, 200, "ruby");              ;
        offerBlasting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
                0.7f, 100, "ruby");
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY,
                RecipeCategory.DECORATIONS, ModBlocks.RUBY_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUBY_PICKAXE, 1)
                .pattern("RRR")
                .pattern(" # ")
                .pattern(" # ")
                .input('R', ModItems.RUBY)
                .input('#', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RUBY_PICKAXE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUBY_AXE, 1)
                .pattern(" RR")
                .pattern(" #R")
                .pattern(" # ")
                .input('R', ModItems.RUBY)
                .input('#', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RUBY_AXE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUBY_SWORD, 1)
                .pattern(" R ")
                .pattern(" R ")
                .pattern(" # ")
                .input('R', ModItems.RUBY)
                .input('#', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RUBY_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUBY_HOE, 1)
                .pattern(" RR")
                .pattern(" # ")
                .pattern(" # ")
                .input('R', ModItems.RUBY)
                .input('#', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RUBY_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUBY_SHOVEL, 1)
                .pattern(" R ")
                .pattern(" # ")
                .pattern(" # ")
                .input('R', ModItems.RUBY)
                .input('#', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RUBY_SHOVEL)));

    }
}
