package by.fxg.aeris;

import java.util.EnumSet;

import by.fxg.aeris.network.PacketCharacter_Update;
import by.fxg.aeris.player.CharStats;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class ServerTickHandler implements ITickHandler {
	public int tick = 0;
	public int dataTimer = 0;
	
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		this.tick++;
		if (this.tick % 5 == 0) {
			if (tickData[0] instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)tickData[0];
				CharStats cs = CharStats.get(player);
				PacketDispatcher.sendPacketToAllPlayers(new PacketCharacter_Update(player.entityId, cs).create());
				
			}
		}
		
		if (this.tick >= 20) {
			this.tick = 0;
			if (tickData[0] instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)tickData[0];
				player.getFoodStats().addStats(20, 10.0F);
			}
		}
	}

	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
	}
	
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}

	public String getLabel() {
		return this.getClass().getSimpleName();
	}
}
