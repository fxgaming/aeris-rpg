package by.fxg.aeris.network;

import java.nio.charset.Charset;
import java.util.logging.Logger;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandler_Main implements IPacketHandler {
	private static final Charset charset = Charset.forName("UTF-8");
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		try {
			EntityPlayer var101 = (EntityPlayer) player;
			ByteArrayDataInput in = ByteStreams.newDataInput(packet.data);
			int packetID = in.readUnsignedByte();
			PacketBase demoPacket = PacketBase.constructPacket(packetID);
			demoPacket.length(packet.data.length - 1);
			demoPacket.read(in);
			demoPacket.execute(var101, var101.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
		} catch (Exception var8) {
			if (player instanceof EntityPlayerMP) {
				((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
				Logger.getLogger("DemoMod").warning("Player " + ((EntityPlayer) player).username + " caused a Protocol Exception!");
			}
		}
	}
}
