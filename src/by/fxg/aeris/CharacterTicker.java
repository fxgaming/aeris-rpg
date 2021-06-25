package by.fxg.aeris;

import java.util.EnumSet;

import by.fxg.aeris.player.Characters;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.world.World;

public class CharacterTicker implements ITickHandler {
	@SideOnly(Side.CLIENT)
	private void onClientTick(World world, Object... tickData) {
		if (world != null) {
			EntityPlayer player = (EntityPlayer) tickData[0];
			if (player == null) {
				return;
			}
			Characters $char = Characters.get(player);
			if ($char != null) {
				if (player.isSneaking()) if ($char.renderSneakAngle < 1.0F) $char.renderSneakAngle += 0.25F;
				else $char.renderSneakAngle = 1.0F;
				else if ($char.renderSneakAngle > 0.0F) $char.renderSneakAngle -= 0.25F;
				else $char.renderSneakAngle = 0.0F;
				
				if (player.isRiding()) if ($char.renderRideAngle < 1.0F) $char.renderRideAngle += 0.15F;
				else $char.renderRideAngle = 1.0F;
				else if ($char.renderRideAngle > 0.0F) $char.renderRideAngle -= 0.15F;
				else $char.renderRideAngle = 0.0F;
				if (player.getHeldItem() != null && player.getHeldItem().getItemUseAction() == EnumAction.bow && player.getItemInUseDuration() > 0) {
					if ($char.renderBowAngle < 1.0F) $char.renderBowAngle += 0.05F;
					else $char.renderBowAngle = 1.0F;
				} else $char.renderBowAngle = 0.0F;
			}
		}
	}

	public void tickStart(EnumSet type, Object... tickData) {}
	public void tickEnd(EnumSet type, Object... tickData) {
		this.tickClient(type, tickData);
	}
	
	@SideOnly(Side.CLIENT)
	public void tickClient(EnumSet type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.PLAYER))) {
			this.onClientTick(Minecraft.getMinecraft().theWorld, tickData);
		}
	}

	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}
	
	public String getLabel() {
		return this.getClass().getSimpleName();
	}
}
