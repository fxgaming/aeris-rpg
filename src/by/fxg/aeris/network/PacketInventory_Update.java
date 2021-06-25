package by.fxg.aeris.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import by.fxg.aeris.player.CharStats;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PacketInventory_Update extends PacketBase {
	private int inv;
	private int slot;
	private int entityID;
	private ItemStack stack;

	public PacketInventory_Update() {}
	public PacketInventory_Update(int entityID, int inv, int Slot, ItemStack stack) {
		this.entityID = entityID;
		this.inv = inv;
		this.slot = Slot;
		this.stack = stack;
	}

	protected void write(ByteArrayDataOutput out) {
		out.writeInt(this.entityID);
		out.writeInt(this.inv);
		out.writeInt(this.slot);
		this.writeItemStack(this.stack, out);
	}

	protected void read(ByteArrayDataInput in){
		this.entityID = in.readInt();
		this.inv = in.readInt();
		this.slot = in.readInt();
		this.stack = this.readItemStack(in);
	}

	protected void execute(EntityPlayer player, Side side) {
		if (side.isClient()) {
			try {
				Entity e = player.worldObj.getEntityByID(this.entityID);
				if (e != null && e instanceof EntityPlayer) {
					//ArmorExtInventory inv = CharStats.get((EntityPlayer)e).inventoryExt;
					//inv.inventory[this.slot] = this.stack;
				}
			} catch (Exception var5) {
			}
		}
	}
}
