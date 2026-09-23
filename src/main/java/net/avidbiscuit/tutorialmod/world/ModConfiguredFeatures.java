package net.avidbiscuit.tutorialmod.world;

import net.avidbiscuit.tutorialmod.TutorialMod;
import net.avidbiscuit.tutorialmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> DAHLIA_FLOWER_KEY = registerKey("dahlia");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context){

        register(context, DAHLIA_FLOWER_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.DAHLIA.getDefaultState())),
                        List.of(Blocks.GRASS_BLOCK)
                ));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(TutorialMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                    RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}

