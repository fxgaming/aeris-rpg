package by.fxg.aeris;

import java.util.EnumSet;
import java.util.Iterator;

import by.fxg.aeris.player.CharStats;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.src.ModLoader;

public class ClientTickHandler implements ITickHandler {
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}
	
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	public String getLabel() {
		return this.getClass().getSimpleName();
	}
}
