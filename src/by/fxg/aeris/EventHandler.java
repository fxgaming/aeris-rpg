package by.fxg.aeris;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import by.fxg.aeris.gui.intro.GuiIntro;
import by.fxg.aeris.player.CharStats;
import by.fxg.aeris.player.Characters;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventHandler {
	@ForgeSubscribe
	public void onConstruct(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			if (CharStats.get((EntityPlayer)event.entity) == null) CharStats.register((EntityPlayer) event.entity);
			if (Characters.get((EntityPlayer)event.entity) == null) Characters.register((EntityPlayer) event.entity);
			if (event.entity.getExtendedProperties(CharStats.STAT_PROP_NAME) == null) event.entity.registerExtendedProperties(CharStats.STAT_PROP_NAME, new CharStats((EntityPlayer)event.entity));
			if (event.entity.getExtendedProperties(Characters.CHAR_PROP_NAME) == null) event.entity.registerExtendedProperties(Characters.CHAR_PROP_NAME, new Characters((EntityPlayer)event.entity));
		}
	}
	
	@ForgeSubscribe
	public void onDeath(LivingDeathEvent event) {
		if (event.entityLiving != null && !event.entityLiving.worldObj.isRemote) {
			if (event.entityLiving instanceof EntityPlayer) {
				CharStats.get((EntityPlayer)event.entityLiving).deaths += 1;
				CharStats.get((EntityPlayer)event.entityLiving).deathTimer.update(60000L);
			}
		}
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
			CharStats.get((EntityPlayer)event.source.getEntity()).pvpkills += 1;
		}
	}
	
	@ForgeSubscribe
	public void onHurt(LivingHurtEvent event) {
		if (event.source != null && event.entityLiving != null && !event.entityLiving.worldObj.isRemote) {
			EntityLiving living = event.entityLiving;
			Entity source = event.source.getEntity();
			if (event.entityLiving instanceof EntityPlayer) {
				EntityPlayer attacked = (EntityPlayer)living;
				CharStats statD = CharStats.get(attacked);
				statD.health.setCurrent(statD.health.getCurrent() - event.ammount > statD.health.getMin() ? statD.health.getCurrent() - event.ammount : statD.health.getMin());
				if (statD.health.getCurrent() <= statD.health.getMin()) {
					//attacked.onDeath(event.source);
					attacked.setEntityHealth(0);
				}
				event.ammount = 0;
				if (source instanceof EntityPlayer) {
					EntityPlayer attacker = (EntityPlayer)source;
					CharStats statA = CharStats.get(attacker);
				}
			}
		}
	}
	
	@ForgeSubscribe
 	public void onUpdateLiving(LivingUpdateEvent event) {
		//if (event.entityLiving instanceof EntityPlayer) {
			//PacketDispatcher.sendPacketToAllPlayers(new PacketCharacter_Update(event.entityLiving.entityId, CharStats.get((EntityPlayer)event.entityLiving)).create());
		//}
	}
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void renderOverlay(Post event) {
		if (event.type == event.type.HOTBAR) {
			if (Minecraft.getMinecraft().thePlayer != null) {
				CharStats cs = CharStats.get(Minecraft.getMinecraft().thePlayer);
				if (cs != null) {
					try {
						if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
							String text;
							Minecraft.getMinecraft().fontRenderer.drawString(text = "You're in creative mode[Non-RPG]", event.resolution.getScaledWidth() / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(text) / 2), 2, Color.WHITE.getRGB());
							Minecraft.getMinecraft().fontRenderer.drawString(text = "CTRL+" + Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindInventory.keyCode) + " To open creative gui", event.resolution.getScaledWidth() / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(text) / 2), 12, Color.WHITE.getRGB());
						}
					} catch (Exception e) {}
					Minecraft.getMinecraft().fontRenderer.drawString("hp: " + cs.health.getCurrent(), 10, 10, Color.WHITE.getRGB());
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onGuiEvent(GuiOpenEvent event) {
		if (event.gui != null && event.gui instanceof GuiGameOver) {
			if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null) {
				Minecraft.getMinecraft().thePlayer.respawnPlayer();
				event.setCanceled(true);
			}
		} else if (event.gui != null && event.gui instanceof GuiInventory && !GuiScreen.isCtrlKeyDown()) {
			if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null) {
				Minecraft.getMinecraft().thePlayer.openGui(Aeris.instance, 1, Minecraft.getMinecraft().theWorld, (int)Minecraft.getMinecraft().thePlayer.posX, (int)Minecraft.getMinecraft().thePlayer.posY, (int)Minecraft.getMinecraft().thePlayer.posZ);
				event.setCanceled(true);
			}
		} else if (event.gui != null && event.gui instanceof GuiMainMenu) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiIntro());
			event.setCanceled(true);
		}
	}
}
