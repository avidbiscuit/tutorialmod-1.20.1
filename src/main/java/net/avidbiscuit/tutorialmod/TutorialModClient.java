package net.avidbiscuit.tutorialmod;

import net.fabricmc.api.ClientModInitializer;

public class TutorialModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_WOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_WOOD_TRAP_DOOR, RenderLayer.getCutout());
    }
}

