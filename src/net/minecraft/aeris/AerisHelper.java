package net.minecraft.aeris;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class AerisHelper {
	public static NBTTagCompound getRenderCompound(Entity par1) {
		return par1.getEntityData().hasKey("renderCompound") ? par1.getEntityData().getCompoundTag("renderCompound") : createCompound(par1);
	}
	
	public static NBTTagCompound createCompound(Entity par1) {
		NBTTagCompound tagCompound = new NBTTagCompound();
		par1.getEntityData().setCompoundTag("renderCompound", tagCompound);
		return par1.getEntityData().getCompoundTag("renderCompound");
	}
	
	public static class TagRenderer {
		public NBTTagCompound tagCompound;
		public float sneakAngle;
		public float rideAngle;
		public float bowAngle;
		
		public TagRenderer(Entity par1) {
			this.tagCompound = getRenderCompound(par1);
		}
		
		public TagRenderer(NBTTagCompound par1) {
			this.tagCompound = par1;
		}
	}
}
