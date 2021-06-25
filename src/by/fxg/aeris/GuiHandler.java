package by.fxg.aeris;

import by.fxg.aeris.container.ContainerCharInventory;
import by.fxg.aeris.gui.GuiCharInventory;
import by.fxg.aeris.player.CharStats;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		CharStats chars = CharStats.get(player);
		switch(ID) {
			case 0:
				break;
			case 1: return new ContainerCharInventory(player, chars, chars.inventory);
		}
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		CharStats chars = CharStats.get(player);
		switch(ID) {
			case 0:
				break;
			case 1: return new GuiCharInventory(player, chars, chars.inventory);
		}
		return null;
	}
}
