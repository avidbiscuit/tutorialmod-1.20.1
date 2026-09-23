package net.avidbiscuit.tutorialmod;

import net.avidbiscuit.tutorialmod.block.ModBlocks;
import net.avidbiscuit.tutorialmod.item.ModItemGroups;
import net.avidbiscuit.tutorialmod.item.ModItems;
import net.avidbiscuit.tutorialmod.util.ModCustomTrades;
import net.avidbiscuit.tutorialmod.util.ModLootTableModifiers;
import net.avidbiscuit.tutorialmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModLootTableModifiers.modifyLootTables();
		ModCustomTrades.registerCustomTrades();

		ModWorldGeneration.generateModWorldGen();

		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200);
	}
}
