package by.fxg.aeris.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.potion.Potion;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class DataEncoder {
	public static Object decode(ByteArrayDataInput in) throws IOException {
		byte type = in.readByte();
		short length9;
		int i;
		int z2;
		int var8;
		byte var9;
		int var11;
		switch (type) {
		case 0:
			return Integer.valueOf(in.readInt());
		case 1:
			length9 = in.readShort();
			int[] var21 = new int[length9];

			for (i = 0; i < length9; ++i) {
				var21[i] = in.readInt();
			}

			return var21;
		case 2:
			return Short.valueOf(in.readShort());
		case 3:
			length9 = in.readShort();
			short[] var20 = new short[length9];

			for (i = 0; i < length9; ++i) {
				var20[i] = in.readShort();
			}

			return var20;
		case 4:
			return Byte.valueOf(in.readByte());
		case 5:
			length9 = in.readShort();
			byte[] var19 = new byte[length9];

			for (i = 0; i < length9; ++i) {
				var19[i] = in.readByte();
			}

			return var19;
		case 6:
			return Long.valueOf(in.readLong());
		case 7:
			length9 = in.readShort();
			long[] var17 = new long[length9];

			for (i = 0; i < length9; ++i) {
				var17[i] = in.readLong();
			}

			return var17;
		case 8:
			return Boolean.valueOf(in.readBoolean());
		case 9:
			length9 = in.readShort();
			boolean[] var15 = new boolean[length9];
			byte var23 = 0;

			for (z2 = 0; z2 < length9; ++z2) {
				if (z2 % 8 == 0) {
					var23 = in.readByte();
				}

				var15[z2] = (var23 & 1 << z2 % 8) != 0;
			}

			return var15;
		case 10:
			length9 = in.readShort();
			char[] var14 = new char[length9];

			for (i = 0; i < length9; ++i) {
				var14[i] = in.readChar();
			}

			return new String(var14);
		case 11:
			length9 = in.readShort();
			String[] var13 = new String[length9];

			for (i = 0; i < length9; ++i) {
				short var211 = in.readShort();
				char[] var221 = new char[var211];

				for (int var231 = 0; var231 < var211; ++var231) {
					var221[var231] = in.readChar();
				}

				var13[i] = new String(var221);
			}

			return var13;
		case 12:
			length9 = in.readShort();
			if (length9 == 0) {
				return null;
			}

			byte var12 = in.readByte();
			short var22 = in.readShort();
			ItemStack var16 = new ItemStack(length9, var12, var22);
			if (in.readBoolean()) {
				var16.stackTagCompound = CompressedStreamTools.read(in);
			}

			return var16;
		case 13:
			return NBTBase.readNamedTag(in);
		case 14:
			var9 = in.readByte();
			var11 = in.readInt();
			switch (var9) {
			case 0:
				return Block.blocksList[var11];
			case 1:
				return Item.itemsList[var11];
			case 2:
				return AchievementList.achievementList.get(var11);
			case 3:
				return Potion.potionTypes[var11];
			case 4:
				return Enchantment.enchantmentsList[var11];
			default:
				return Byte.valueOf(type);
			}
		case 15:
			var9 = in.readByte();
			var11 = in.readInt();
			i = 0;
			if (var9 == 1 || var9 == 2) {
				i = in.readInt();
			}

			z2 = in.readInt();
			switch (var9) {
			case 0:
				return new ChunkCoordIntPair(var11, z2);
			case 1:
				return new ChunkCoordinates(var11, i, z2);
			case 2:
				return new ChunkPosition(var11, i, z2);
			default:
				return Byte.valueOf(type);
			}
		case 16:
			var8 = in.readInt();
			var11 = in.readInt();
			i = in.readInt();
			z2 = in.readInt();
			return DimensionManager.getWorld(var8).getBlockTileEntity(var11, i, z2);
		case 17:
			var8 = in.readInt();
			return DimensionManager.getWorld(var8);
		case 18:
			return Float.valueOf(in.readFloat());
		case 19:
			length9 = in.readShort();
			float[] var10 = new float[length9];

			for (i = 0; i < length9; ++i) {
				var10[i] = in.readFloat();
			}

			return var10;
		case 20:
			return Double.valueOf(in.readDouble());
		case 21:
			length9 = in.readShort();
			double[] data9 = new double[length9];

			for (i = 0; i < length9; ++i) {
				data9[i] = in.readDouble();
			}

			return data9;
		case 127:
			return null;
		default:
			return null;
		}
	}

	public static void encode(ByteArrayDataOutput os, Object o) throws IOException {
		if (o instanceof Integer) {
			os.writeByte(0);
			os.writeInt(((Integer) o).intValue());
		} else {
			int i;
			if (o instanceof int[]) {
				os.writeByte(1);
				int[] var18 = (int[]) ((int[]) ((int[]) ((int[]) ((int[]) ((int[]) ((int[]) ((int[]) ((int[]) ((int[]) ((int[]) ((int[]) ((int[]) ((int[]) o)))))))))))));
				os.writeShort(var18.length);

				for (i = 0; i < var18.length; ++i) {
					os.writeInt(var18[i]);
				}
			} else if (o instanceof Short) {
				os.writeByte(2);
				os.writeShort(((Integer) o).intValue());
			} else if (o instanceof short[]) {
				os.writeByte(3);
				short[] var6 = (short[]) ((short[]) ((short[]) ((short[]) ((short[]) ((short[]) ((short[]) ((short[]) ((short[]) ((short[]) ((short[]) ((short[]) ((short[]) ((short[]) o)))))))))))));
				os.writeShort(var6.length);

				for (i = 0; i < var6.length; ++i) {
					os.writeShort(var6[i]);
				}
			} else if (o instanceof Byte) {
				os.writeByte(4);
				os.writeByte(((Integer) o).intValue());
			} else if (o instanceof byte[]) {
				os.writeByte(5);
				byte[] var7 = (byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) ((byte[]) o)))))))))))));
				os.writeShort(var7.length);

				for (i = 0; i < var7.length; ++i) {
					os.writeByte(var7[i]);
				}
			} else if (o instanceof Long) {
				os.writeByte(6);
				os.writeLong(((Long) o).longValue());
			} else if (o instanceof long[]) {
				os.writeByte(7);
				long[] var8 = (long[]) ((long[]) ((long[]) ((long[]) ((long[]) ((long[]) ((long[]) ((long[]) ((long[]) ((long[]) ((long[]) ((long[]) ((long[]) ((long[]) o)))))))))))));
				os.writeShort(var8.length);

				for (i = 0; i < var8.length; ++i) {
					os.writeLong(var8[i]);
				}
			} else if (o instanceof Boolean) {
				os.writeByte(8);
				os.writeBoolean(((Boolean) o).booleanValue());
			} else if (o instanceof boolean[]) {
				os.writeByte(9);
				boolean[] var9 = (boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) ((boolean[]) o)))))))))))));
				os.writeShort(var9.length);
				byte var15 = 0;

				for (int i2 = 0; i2 < var9.length; ++i2) {
					if (i2 % 8 == 0 && i2 > 0) {
						os.writeByte(var15);
						var15 = 0;
					}

					var15 |= (byte) ((var9[i2] ? 1 : 0) << i2 % 8);
				}

				os.writeByte(var15);
			} else if (o instanceof String) {
				os.writeByte(10);
				String var10 = (String) o;
				os.writeShort(var10.length());
				os.writeChars(var10);
			} else if (o instanceof String[]) {
				os.writeByte(11);
				String[] var11 = (String[]) ((String[]) ((String[]) ((String[]) ((String[]) ((String[]) ((String[]) ((String[]) ((String[]) ((String[]) ((String[]) ((String[]) ((String[]) ((String[]) o)))))))))))));
				os.writeShort(var11.length);

				for (i = 0; i < var11.length; ++i) {
					os.writeShort(var11[i].length());
					os.writeChars(var11[i]);
				}
			} else if (o instanceof ItemStack) {
				os.writeByte(12);
				ItemStack var12 = (ItemStack) o;
				os.writeShort(var12.itemID);
				if (var12.itemID == 0) {
					return;
				}

				os.writeByte(var12.stackSize);
				os.writeShort(var12.getItemDamage());
				if ((Item.itemsList[var12.itemID].isDamageable() || Item.itemsList[var12.itemID].getShareTag()) && var12.stackTagCompound != null) {
					os.writeBoolean(true);
					CompressedStreamTools.write(var12.stackTagCompound, os);
				} else {
					os.writeBoolean(false);
				}
			} else if (o instanceof NBTBase) {
				os.writeByte(13);
				NBTBase.writeNamedTag((NBTBase) o, os);
			} else if (o instanceof Block) {
				os.writeByte(14);
				os.writeByte(0);
				os.writeInt(((Block) o).blockID);
			} else if (o instanceof Item) {
				os.writeByte(14);
				os.writeByte(1);
				os.writeInt(((Item) o).itemID);
			} else if (o instanceof Achievement) {
				os.writeByte(14);
				os.writeByte(2);
				os.writeInt(((StatBase) o).statId);
			} else if (o instanceof Potion) {
				os.writeByte(14);
				os.writeByte(3);
				os.writeInt(((Potion) o).id);
			} else if (o instanceof Enchantment) {
				os.writeByte(14);
				os.writeByte(4);
				os.writeInt(((Enchantment) o).effectId);
			} else if (o instanceof ChunkCoordinates) {
				os.writeByte(15);
				os.writeByte(0);
				ChunkCoordinates var13 = (ChunkCoordinates) o;
				os.writeInt(var13.posX);
				os.writeInt(var13.posY);
				os.writeInt(var13.posZ);
			} else if (o instanceof ChunkCoordIntPair) {
				os.writeByte(15);
				os.writeByte(1);
				ChunkCoordIntPair var14 = (ChunkCoordIntPair) o;
				os.writeInt(var14.chunkXPos);
				os.writeInt(var14.getCenterZPosition() - 8 >> 4);
			} else if (o instanceof ChunkPosition) {
				os.writeByte(15);
				os.writeByte(1);
				ChunkPosition var151 = (ChunkPosition) o;
				os.writeInt(var151.x);
				os.writeInt(var151.y);
				os.writeInt(var151.z);
			} else if (o instanceof TileEntity) {
				os.writeByte(16);
				TileEntity var16 = (TileEntity) o;
				os.writeInt(var16.worldObj.provider.dimensionId);
				os.writeInt(var16.xCoord);
				os.writeInt(var16.yCoord);
				os.writeInt(var16.zCoord);
			} else if (o instanceof World) {
				os.writeByte(17);
				os.writeInt(((World) o).provider.dimensionId);
			} else if (o instanceof Float) {
				os.writeByte(18);
				os.writeFloat(((Float) o).floatValue());
			} else if (o instanceof float[]) {
				os.writeByte(19);
				float[] var17 = (float[]) ((float[]) ((float[]) ((float[]) ((float[]) ((float[]) ((float[]) ((float[]) ((float[]) ((float[]) ((float[]) ((float[]) ((float[]) ((float[]) o)))))))))))));
				os.writeShort(var17.length);

				for (i = 0; i < var17.length; ++i) {
					os.writeFloat(var17[i]);
				}
			} else if (o instanceof Double) {
				os.writeByte(20);
				os.writeDouble(((Double) o).doubleValue());
			} else if (o instanceof double[]) {
				os.writeByte(21);
				double[] var181 = (double[]) ((double[]) ((double[]) ((double[]) ((double[]) ((double[]) ((double[]) ((double[]) ((double[]) ((double[]) ((double[]) ((double[]) ((double[]) ((double[]) o)))))))))))));
				os.writeShort(var181.length);

				for (i = 0; i < var181.length; ++i) {
					os.writeDouble(var181[i]);
				}
			} else if (o == null) {
				os.writeByte(127);
			}
		}

	}
}
