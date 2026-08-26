package net.avidbiscuit.tutorialmod;

import net.avidbiscuit.tutorialmod.block.ModBlocks;
import net.avidbiscuit.tutorialmod.item.custom.ManaManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;


public class TutorialModClient implements ClientModInitializer {

    private static final Identifier MANA_FRAME =
            new Identifier("tutorialmod","textures/gui/mana_frame.png");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_WOOD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_WOOD_TRAP_DOOR, RenderLayer.getCutout());
        ClientTickEvents.END_CLIENT_TICK.register(client ->
            ManaManager.tick()
        );

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {


            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) {
                return;
            }

            int getMana = ManaManager.getMana();
            int maxMana = ManaManager.MAX_MANA;

            int manaScreenXCoord = 430;
            int manaScreenYCoord = 40;

            int manaScreenWidth = 12;
            int manaScreenHeight = 100;

            // Background
            drawContext.fill(
                    manaScreenXCoord,
                    manaScreenYCoord,
                    manaScreenXCoord + manaScreenWidth,
                    manaScreenYCoord + manaScreenHeight,
                    0xFF222222
            );
            String manaText = getMana + " / " + maxMana;

            int textWidth = client.textRenderer.getWidth(manaText);

            int textX = manaScreenXCoord + (manaScreenWidth - textWidth) / 2;

            drawContext.drawText(
                    client.textRenderer,
                    manaText,
                    textX,
                    manaScreenYCoord + manaScreenHeight + 3,
                    0xFFFFFFFF,
                    true
            );

            // Mana
            int manaHeight = (int) (
                    (getMana / (float) maxMana) * manaScreenHeight
            );

            drawContext.fill(
                    manaScreenXCoord,
                    manaScreenYCoord + manaScreenHeight - manaHeight,
                    manaScreenXCoord + manaScreenWidth,
                    manaScreenYCoord + manaScreenHeight,
                    0xFFAA00FF
            );
            drawContext.drawTexture(
                    MANA_FRAME,
                    manaScreenXCoord,
                    manaScreenYCoord,
                    0, 0,
                    manaScreenWidth,
                    manaScreenHeight,
                    manaScreenWidth,
                    manaScreenHeight
            );

        });
    }
}


