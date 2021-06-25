package by.fxg.aeris.network;

import java.io.IOException;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

public abstract class PacketBase {
	public static final String CHANNEL = "aeris";
	private static final BiMap<Integer, Class<? extends PacketBase>> idMap;

	public PacketBase() {
	}
	
	public static PacketBase constructPacket(int packetId) throws Exception {
		Class clazz = (Class)idMap.get(packetId);
		if (clazz == null) {
			throw new Exception("Unknown Packet Id!");
		} else {
			return (PacketBase) clazz.newInstance();
		}
	}

	public final int getPacketId() {
		if (idMap.inverse().containsKey(this.getClass())) {
			return ((Integer) idMap.inverse().get(this.getClass())).intValue();
		} else {
			throw new RuntimeException("Packet " + this.getClass().getSimpleName() + " is missing a mapping!");
		}
	}

	public final Packet create() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeByte(this.getPacketId());
		this.write(out);
		return PacketDispatcher.getPacket(CHANNEL, out.toByteArray());
	}

	protected abstract void write(ByteArrayDataOutput var1);
	protected abstract void read(ByteArrayDataInput var1);
	protected abstract void execute(EntityPlayer var1, Side var2);

	protected void length(int var1) {}
	
	static {
		Builder builder = ImmutableBiMap.builder();
		builder.put(nextID(), PacketCharacter_Update.class);
		builder.put(nextID(), PacketInventory_Update.class);
		
		idMap = builder.build();
	}

	public void writeTagCompound(NBTTagCompound par0NBTTagCompound, ByteArrayDataOutput par1DataOutputStream) {
		if (par0NBTTagCompound == null) par1DataOutputStream.writeShort(-1);
		else {
			byte[] abyte;
			try {
				abyte = CompressedStreamTools.compress(par0NBTTagCompound);
				par1DataOutputStream.writeShort((short) abyte.length);
				par1DataOutputStream.write(abyte);
			} catch (IOException e) {
				par1DataOutputStream.writeShort(-1);
			}
		}
	}
	
	public static NBTTagCompound readTagCompound(ByteArrayDataInput par0DataInputStream){
		short short1 = par0DataInputStream.readShort();
		if (short1 < 0) return null;
		else {
			byte[] abyte = new byte[short1];
			par0DataInputStream.readFully(abyte);
			try {
				return CompressedStreamTools.decompress((byte[]) abyte);
			} catch (Exception e) {
				return null;
			}
		}
	}

	public void writeItemStack(ItemStack par0ItemStack, ByteArrayDataOutput par1DataOutputStream) {
		if (par0ItemStack != null && par0ItemStack.getItem() != null) {
			par1DataOutputStream.writeShort(par0ItemStack.itemID);
			par1DataOutputStream.writeByte(par0ItemStack.stackSize);
			par1DataOutputStream.writeShort(par0ItemStack.getItemDamage());
			NBTTagCompound nbttagcompound = null;
			if (par0ItemStack.getItem().isDamageable() || par0ItemStack.getItem().getShareTag()) nbttagcompound = par0ItemStack.stackTagCompound;
			writeTagCompound(nbttagcompound, par1DataOutputStream);
		} else par1DataOutputStream.writeShort(-1);
	}
	
	public ItemStack readItemStack(ByteArrayDataInput par0DataInputStream) {
		ItemStack itemstack = null;
		short short1 = par0DataInputStream.readShort();
		if (short1 >= 0) {
			byte b0 = par0DataInputStream.readByte();
			short short2 = par0DataInputStream.readShort();
			itemstack = new ItemStack(short1, b0, short2);
			itemstack.stackTagCompound = readTagCompound(par0DataInputStream);
		}
		return itemstack;
	}
	
	public static int next = 0;
	public static int nextID() {
		next++;
		return next;
	}
}
