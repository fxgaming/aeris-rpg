package net.minecraft.client.aeris;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelCharacter;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AerisRenderHelper {
	public static boolean renderCharacterAttack(Entity par1, ModelCharacter par2, float par3, float... par4) {
		if (par1 instanceof EntityPlayer) {
			if (((EntityPlayer)par1).getHeldItem() != null && ((EntityPlayer)par1).getHeldItem().getItem() instanceof IActionRender) {
				return ((IActionRender)((EntityPlayer)par1).getHeldItem().getItem()).renderCharacterAttack(par1, par2, par3, par4);
			}
		}
		return true;
	}
	
	public static boolean renderCharacterHands(Entity par1, ModelCharacter par2, boolean par3Sprint, float... par4) {
		if (par1 instanceof EntityPlayer) {
			if (((EntityPlayer)par1).getHeldItem() != null && ((EntityPlayer)par1).getHeldItem().getItem() instanceof IActionRender) {
				return ((IActionRender)((EntityPlayer)par1).getHeldItem().getItem()).renderCharacterHands(par1, par2, par3Sprint, par4);
			}
		}
		return false;
	}
	
	public static boolean renderFirstPerson(EntityPlayer par1, RenderPlayer par2) {
		if (par1.getHeldItem() != null && par1.getHeldItem().getItem() instanceof IActionRender) {
			return ((IActionRender)par1.getHeldItem().getItem()).renderFirstPerson(par1, par2);
		}
		return false;
	}
	
	public static boolean hasCustomPlayerRendering(Entity par1, ModelCharacter par2, float... par3) {
		if (par1 instanceof EntityPlayer) {
			if (((EntityPlayer)par1).getHeldItem() != null && ((EntityPlayer)par1).getHeldItem().getItem() instanceof ItemRemodel) {
				return ((ItemRemodel)((EntityPlayer)par1).getHeldItem().getItem()).hasCustomPlayerRendering(par1, par2, par3);
			}
		}
		return false;
	}
	
	
	
	
	public static interface IActionRender {
		abstract public boolean renderCharacterAttack(Entity par1, ModelCharacter par2, float par3, float[] par4);
		abstract public boolean renderCharacterHands(Entity par1, ModelCharacter par2, boolean par3Sprint, float[] par4);
		abstract public boolean renderFirstPerson(EntityPlayer par1, RenderPlayer par2);
	}
	
	public static class ItemRemodel extends Item {
		public ItemRemodel(int par1) {
			super(par1);
		}

		public boolean hasCustomPlayerRendering(Entity par1, ModelCharacter par2, float[] par3) {
			return false;
		}
	}
	
	public static interface IHelmetOverlay {
		abstract public void renderHelmetOverlay(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, ScaledResolution par3ScaledResolution, float par4PartialTick, boolean par5HasScreen, int par6PosX, int par7PosY);
	}
}
