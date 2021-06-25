package by.fxg.aeris.container;

import by.fxg.aeris.inventory.CharInventory;
import by.fxg.aeris.player.CharStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerCharInventory extends Container {
	public EntityPlayer thePlayer;
	public CharStats stats;
	public CharInventory inv;
	
	public ContainerCharInventory(EntityPlayer par1, CharStats par2, CharInventory par3) {
		this.thePlayer = par1;
		this.stats = par2;
		this.inv = par3;
		for (int i = 0; i != 4; i++) this.addSlotToContainer(new Slot(par2.inventory.armor, i, 8, 8 + (i * 18)));
		for (int i = 0; i != 4; i++) this.addSlotToContainer(new Slot(par2.inventory.armor, 4 + i, 8 + (i * 18), 80));
		for (int i = 0; i != 5; i++) this.addSlotToContainer(new Slot(par2.inventory.armor, 9 + i, 80, 80 - (i * 18)));
		for (int i = 0; i != 3; i++) {
			for (int j = 0; j != 9; j++) {
				this.addSlotToContainer(new Slot(par2.inventory.main, j + (i * 9), 8 + (j * 18), 106 + (i * 18)));
			}
		}
		this.addSlotToContainer(new Slot(par2.inventory.hotbar, 0, 152, 8));
		for (int i = 0; i != 3; i++) {
			for (int j = 0; j != 3; j++) {
				this.addSlotToContainer(new Slot(par2.inventory.hotbar, 1 + j + (i * 3), 116 + (j * 18), 27 + (i * 18)));
			}
		}
		//this.addSlotToContainer(new Slot(par1InventoryPlayer, var4, 8 + var4 * 18, 142));
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		if (var4 != null && var4.getHasStack()) {
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if (par2 == 0) {
				if (!this.mergeItemStack(var5, 9, 45, true)) {
					return null;
				}
				var4.onSlotChange(var5, var3);
			} else if (par2 >= 1 && par2 < 5) {
				if (!this.mergeItemStack(var5, 9, 45, false)) {
					return null;
				}
			} else if (par2 >= 5 && par2 < 9) {
				if (!this.mergeItemStack(var5, 9, 45, false)) {
					return null;
				}
			} else if (var3.getItem() instanceof ItemArmor && !((Slot) this.inventorySlots.get(5 + ((ItemArmor) var3.getItem()).armorType)).getHasStack()) {
				int var6 = 5 + ((ItemArmor) var3.getItem()).armorType;

				if (!this.mergeItemStack(var5, var6, var6 + 1, false)) {
					return null;
				}
			} else if (par2 >= 9 && par2 < 36) {
				if (!this.mergeItemStack(var5, 36, 45, false)) {
					return null;
				}
			} else if (par2 >= 36 && par2 < 45) {
				if (!this.mergeItemStack(var5, 9, 36, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(var5, 9, 45, false)) {
				return null;
			}

			if (var5.stackSize == 0) {
				var4.putStack((ItemStack) null);
			} else {
				var4.onSlotChanged();
			}

			if (var5.stackSize == var3.stackSize) {
				return null;
			}
			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}
		return var3;
	}
}
