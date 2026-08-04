package net.avidbiscuit.tutorialmod.block;

import net.avidbiscuit.tutorialmod.TutorialMod;
import net.avidbiscuit.tutorialmod.block.custom.SoundBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;





public class ModBlocks {

    public static final Block WEATHERED_BRICK = registerBlock("weathered_brick",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));

    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));

    public static final Block RAW_RUBY_BLOCK = registerBlock("raw_ruby_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));

    public static final Block SOUND_BLOCK = registerBlock("sound_block",
            new SoundBlock(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));

    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));

    public static final Block END_STONE_RUBY_ORE = registerBlock("end_stone_ruby_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));

    public static final Block WEATHERED_WOOD = registerBlock("weathered_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(4f)));

    public static final Block WEATHERED_WOOD_PLANK = registerBlock("weathered_wood_plank",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).strength(4f)));

    public static final Block WEATHERED_WOOD_BUTTON = registerBlock("weathered_wood_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).strength(4f), BlockSetType.IRON, 10, true));

    public static final Block WEATHERED_WOOD_PRESSURE_PLATE = registerBlock("weathered_wood_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).strength(4f), BlockSetType.IRON));

    public static final Block WEATHERED_WOOD_STAIRS = registerBlock("weathered_wood_stairs",
            new StairsBlock(ModBlocks.WEATHERED_WOOD_PLANK.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).strength(4f)));

    public static final Block WEATHERED_WOOD_SLAB = registerBlock("weathered_wood_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB).strength(4f)));

    public static final Block WEATHERED_WOOD_FENCE = registerBlock("weathered_wood_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE).strength(4f)));

    public static final Block WEATHERED_WOOD_FENCE_GATE = registerBlock("weathered_wood_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE).strength(4f), WoodType.OAK));

    public static final Block RUBY_WALL = registerBlock("ruby_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_WALL).strength(4f)));

    public static final Block WEATHERED_WOOD_DOOR = registerBlock("weathered_wood_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR).strength(4f).nonOpaque(), BlockSetType.OAK));

    public static final Block WEATHERED_WOOD_TRAP_DOOR = registerBlock("weathered_wood_trap_door",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR).strength(4f).nonOpaque(), BlockSetType.OAK));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TutorialMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));

    }


    public static void registerModBlocks(){
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);
    }



}


