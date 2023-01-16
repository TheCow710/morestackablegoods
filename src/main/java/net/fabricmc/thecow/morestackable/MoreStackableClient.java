package net.fabricmc.thecow.morestackable;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.thecow.morestackable.handler.ToolScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class MoreStackableClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        HandledScreens.register(MoreStackableGoods.TOOL_BELT_HANDLER, ToolScreen::new);
    }
    
}
