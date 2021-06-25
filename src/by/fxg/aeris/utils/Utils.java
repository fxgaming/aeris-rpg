package by.fxg.aeris.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Utils {
	public NBTTagCompound getTag(ItemStack par1) {
		if (par1.hasTagCompound()) return par1.getTagCompound();
		else {
			NBTTagCompound tagCompound = new NBTTagCompound();
			par1.setTagCompound(tagCompound);
			return par1.getTagCompound();
		}
	}
}
