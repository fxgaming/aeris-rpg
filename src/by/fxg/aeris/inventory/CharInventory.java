package by.fxg.aeris.inventory;

import java.util.ArrayList;

import by.fxg.aeris.network.PacketInventory_Update;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CharInventory {
	public MainInventory main;
	public ArmorInventory armor;
	public HotbarInventory hotbar;
	
	public CharInventory(EntityPlayer player) {
		this.main = new MainInventory(player);
		this.armor = new ArmorInventory(player);
		this.hotbar = new HotbarInventory(player);
	}

	public void saveToNBT(NBTTagCompound tags) {
		NBTTagList tagList = new NBTTagList();
		for (int i = 0; i < this.main.inventory.size(); ++i) {
			if (this.main.inventory.get(i) != null) {
				NBTTagCompound slot = new NBTTagCompound();
				slot.setByte("Slot", (byte) i);
				this.main.inventory.get(i).writeToNBT(slot);
				tagList.appendTag(slot);
			}
		}
		tags.setTag("mainInventory", tagList);
		tagList = new NBTTagList();
		for (int i = 0; i < this.armor.inventory.size(); ++i) {
			if (this.armor.inventory.get(i) != null) {
				NBTTagCompound slot = new NBTTagCompound();
				slot.setByte("Slot", (byte) i);
				this.armor.inventory.get(i).writeToNBT(slot);
				tagList.appendTag(slot);
			}
		}
		tags.setTag("armorInventory", tagList);
		tagList = new NBTTagList();
		for (int i = 0; i < this.hotbar.inventory.size(); ++i) {
			if (this.hotbar.inventory.get(i) != null) {
				NBTTagCompound slot = new NBTTagCompound();
				slot.setByte("Slot", (byte) i);
				this.hotbar.inventory.get(i).writeToNBT(slot);
				tagList.appendTag(slot);
			}
		}
		tags.setTag("hotbarInventory", tagList);
	}

	public void loadNBTData(NBTTagCompound tags) {
		NBTTagList tagList = tags.getTagList("mainInventory");
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = (NBTTagCompound) tagList.tagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
			if (itemstack != null) {
				this.main.inventory.set(j, itemstack);
			}
		}
		tagList = tags.getTagList("armorInventory");
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = (NBTTagCompound) tagList.tagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
			if (itemstack != null) {
				this.armor.inventory.set(j, itemstack);
			}
		}
		tagList = tags.getTagList("hotbarInventory");
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = (NBTTagCompound) tagList.tagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
			if (itemstack != null) {
				this.hotbar.inventory.set(j, itemstack);
			}
		}
	}
	
	public class MainInventory implements IInventory {
		public ArrayList<ItemStack> inventory = new ArrayList<ItemStack>(27);
		public EntityPlayer player;

		public MainInventory(EntityPlayer player) {
			this.player = player;
			for (int i = 0; i != 27; i++) {
				if (this.inventory.size() > i) this.inventory.set(i, null);
				else this.inventory.add(null);
			}
		}

		public int getSizeInventory() {
			return this.inventory.size();
		}

		public boolean hasStack(int slot) {
			return this.inventory.size() < slot && this.inventory.get(slot) != null;
		}

		public ItemStack getStackInSlot(int slot) {
			return this.inventory.size() < slot ? this.inventory.get(slot) : null;
		}

		public ItemStack decrStackSize(int slot, int quantity) {
			if (this.inventory.size() < slot && this.inventory.get(slot) != null) {
				ItemStack split;
				if (this.inventory.get(slot).stackSize <= quantity) {
					split = this.inventory.get(slot);
					this.inventory.set(slot, null);
					return split;
				} else {
					split = this.inventory.get(slot).splitStack(quantity);
					if (this.inventory.get(slot).stackSize == 0) {
						this.inventory.set(slot, null);
					}
					return split;
				}
			} else {
				return null;
			}
		}

		public void setInventorySlotContents(int slot, ItemStack itemstack) {
			this.inventory.set(slot, itemstack);
			if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
				itemstack.stackSize = this.getInventoryStackLimit();
			}
		}

		public void onInventoryChanged() {
			if (!this.player.worldObj.isRemote && this.player instanceof EntityPlayerMP) {
				for (int slot = 0; slot < this.getSizeInventory(); ++slot) {
					ItemStack stack = this.inventory.get(slot);
					PacketDispatcher.sendPacketToAllPlayers((new PacketInventory_Update(this.player.entityId, 1, slot, stack)).create());
				}
			}
		}
		public ItemStack getStackInSlotOnClosing(int slot) {return null;}
		public String getInvName() {return "charinv1";}
		public boolean isInvNameLocalized() {return false;}
		public int getInventoryStackLimit() {return 99;}
		public boolean isUseableByPlayer(EntityPlayer entityplayer) {return true;}
		public void openChest() {}
		public void closeChest() {}
		public boolean isStackValidForSlot(int slot, ItemStack itemstack) {return false;}
	}
	
	public class ArmorInventory implements IInventory {
		public ArrayList<ItemStack> inventory = new ArrayList<ItemStack>(14);
		public EntityPlayer player;

		public ArmorInventory(EntityPlayer player) {
			this.player = player;
			for (int i = 0; i != 14; i++) {
				if (this.inventory.size() > i) this.inventory.set(i, null);
				else this.inventory.add(null);
			}
		}

		public int getSizeInventory() {
			return this.inventory.size();
		}

		public boolean hasStack(int slot) {
			return this.inventory.size() < slot && this.inventory.get(slot) != null;
		}

		public ItemStack getStackInSlot(int slot) {
			return this.inventory.size() < slot ? this.inventory.get(slot) : null;
		}

		public ItemStack decrStackSize(int slot, int quantity) {
			if (this.inventory.size() < slot && this.inventory.get(slot) != null) {
				ItemStack split;
				if (this.inventory.get(slot).stackSize <= quantity) {
					split = this.inventory.get(slot);
					this.inventory.set(slot, null);
					return split;
				} else {
					split = this.inventory.get(slot).splitStack(quantity);
					if (this.inventory.get(slot).stackSize == 0) {
						this.inventory.set(slot, null);
					}
					return split;
				}
			} else {
				return null;
			}
		}

		public void setInventorySlotContents(int slot, ItemStack itemstack) {
			this.inventory.set(slot, itemstack);
			if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
				itemstack.stackSize = this.getInventoryStackLimit();
			}
		}

		public void onInventoryChanged() {
			if (!this.player.worldObj.isRemote && this.player instanceof EntityPlayerMP) {
				for (int slot = 0; slot < this.getSizeInventory(); ++slot) {
					ItemStack stack = this.inventory.get(slot);
					PacketDispatcher.sendPacketToAllPlayers((new PacketInventory_Update(this.player.entityId, 1, slot, stack)).create());
				}
			}
		}
		public ItemStack getStackInSlotOnClosing(int slot) {return null;}
		public String getInvName() {return "charinv2";}
		public boolean isInvNameLocalized() {return false;}
		public int getInventoryStackLimit() {return 99;}
		public boolean isUseableByPlayer(EntityPlayer entityplayer) {return true;}
		public void openChest() {}
		public void closeChest() {}
		public boolean isStackValidForSlot(int slot, ItemStack itemstack) {return false;}
	}
	
	public class HotbarInventory implements IInventory {
		public ArrayList<ItemStack> inventory = new ArrayList<ItemStack>(10);
		public EntityPlayer player;

		public HotbarInventory(EntityPlayer player) {
			this.player = player;
			for (int i = 0; i != 10; i++) {
				if (this.inventory.size() > i) this.inventory.set(i, null);
				else this.inventory.add(null);
			}
		}

		public int getSizeInventory() {
			return this.inventory.size();
		}

		public boolean hasStack(int slot) {
			return this.inventory.size() < slot && this.inventory.get(slot) != null;
		}

		public ItemStack getStackInSlot(int slot) {
			return this.inventory.size() < slot ? this.inventory.get(slot) : null;
		}

		public ItemStack decrStackSize(int slot, int quantity) {
			if (this.inventory.size() < slot && this.inventory.get(slot) != null) {
				ItemStack split;
				if (this.inventory.get(slot).stackSize <= quantity) {
					split = this.inventory.get(slot);
					this.inventory.set(slot, null);
					return split;
				} else {
					split = this.inventory.get(slot).splitStack(quantity);
					if (this.inventory.get(slot).stackSize == 0) {
						this.inventory.set(slot, null);
					}
					return split;
				}
			} else {
				return null;
			}
		}

		public void setInventorySlotContents(int slot, ItemStack itemstack) {
			this.inventory.set(slot, itemstack);
			if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
				itemstack.stackSize = this.getInventoryStackLimit();
			}
		}

		public void onInventoryChanged() {
			if (!this.player.worldObj.isRemote && this.player instanceof EntityPlayerMP) {
				for (int slot = 0; slot < this.getSizeInventory(); ++slot) {
					ItemStack stack = this.inventory.get(slot);
					PacketDispatcher.sendPacketToAllPlayers((new PacketInventory_Update(this.player.entityId, 1, slot, stack)).create());
				}
			}
		}
		public ItemStack getStackInSlotOnClosing(int slot) {return null;}
		public String getInvName() {return "charinv3";}
		public boolean isInvNameLocalized() {return false;}
		public int getInventoryStackLimit() {return 99;}
		public boolean isUseableByPlayer(EntityPlayer entityplayer) {return true;}
		public void openChest() {}
		public void closeChest() {}
		public boolean isStackValidForSlot(int slot, ItemStack itemstack) {return false;}
	}
}
