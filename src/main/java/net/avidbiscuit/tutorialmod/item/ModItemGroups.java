package net.avidbiscuit.tutorialmod.item;

import net.avidbiscuit.tutorialmod.TutorialMod;
import net.avidbiscuit.tutorialmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
        new Identifier(TutorialMod.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                    .icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.RUBY_ORE);
                        entries.add(ModItems.RUBY);

                        entries.add(ModItems.RAW_RUBY);
                        entries.add(ModBlocks.RAW_RUBY_BLOCK);
                        entries.add(ModBlocks.RUBY_BLOCK);
                        entries.add(ModBlocks.RUBY_WALL);

                        entries.add(ModBlocks.WEATHERED_BRICK);
                        entries.add(ModBlocks.WEATHERED_WOOD);
                        entries.add(ModBlocks.WEATHERED_WOOD_PLANK);
                        entries.add(ModBlocks.WEATHERED_WOOD_DOOR);
                        entries.add(ModBlocks.WEATHERED_WOOD_TRAP_DOOR);
                        entries.add(ModBlocks.WEATHERED_WOOD_STAIRS);
                        entries.add(ModBlocks.WEATHERED_WOOD_FENCE);
                        entries.add(ModBlocks.WEATHERED_WOOD_FENCE_GATE);
                        entries.add(ModBlocks.WEATHERED_WOOD_SLAB);
                        entries.add(ModBlocks.WEATHERED_WOOD_BUTTON);
                        entries.add(ModBlocks.WEATHERED_WOOD_PRESSURE_PLATE);


                        entries.add(ModItems.COIN);
                        entries.add(ModBlocks.SOUND_BLOCK);
                        entries.add(ModItems.TOMATO);
                        entries.add(ModItems.RUBY_STAFF);

                        entries.add(ModItems.COAL_BRIQUETTE);
                        entries.add(ModItems.METAL_DETECTOR);




                    }).build());

    public static void registerItemGroups(){
        TutorialMod.LOGGER.info("Registering Item Group");
    }
}
