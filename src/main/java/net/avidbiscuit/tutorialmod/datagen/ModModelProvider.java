package net.avidbiscuit.tutorialmod.datagen;

import net.avidbiscuit.tutorialmod.block.ModBlocks;
import net.avidbiscuit.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool rubypool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_RUBY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.END_STONE_RUBY_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SOUND_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WEATHERED_BRICK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_ORE);
        BlockStateModelGenerator.BlockTexturePool weatheredwoodpool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.WEATHERED_WOOD_PLANK);

        blockStateModelGenerator.registerLog(ModBlocks.WEATHERED_WOOD).log(ModBlocks.WEATHERED_WOOD);

        rubypool.wall(ModBlocks.RUBY_WALL);

        weatheredwoodpool.stairs(ModBlocks.WEATHERED_WOOD_STAIRS);
        weatheredwoodpool.slab(ModBlocks.WEATHERED_WOOD_SLAB);
        weatheredwoodpool.button(ModBlocks.WEATHERED_WOOD_BUTTON);
        weatheredwoodpool.pressurePlate(ModBlocks.WEATHERED_WOOD_PRESSURE_PLATE);
        weatheredwoodpool.fence(ModBlocks.WEATHERED_WOOD_FENCE);
        weatheredwoodpool.fenceGate(ModBlocks.WEATHERED_WOOD_FENCE_GATE);

        blockStateModelGenerator.registerDoor(ModBlocks.WEATHERED_WOOD_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.WEATHERED_WOOD_TRAP_DOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_RUBY, Models.GENERATED);

        itemModelGenerator.register(ModItems.COAL_BRIQUETTE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO, Models.GENERATED);

        itemModelGenerator.register(ModItems.METAL_DETECTOR, Models.GENERATED);
    }
}
