package by.fxg.aeris;

import by.fxg.aeris.network.PacketHandler_Main;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "aeris", name = "Aeris", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"aeris"}, packetHandler = PacketHandler_Main.class)
public class Aeris {
	@Instance("aeris")
	public static Aeris instance;
	@SidedProxy(clientSide = "by.fxg.aeris.ClientProxy", serverSide = "by.fxg.aeris.ServerProxy")
	public static ServerProxy proxy;
	public CharacterTicker characterTicker;
	public ClientTickHandler clientTickHandler;
	
	
	public EventHandler eventHandler;
	public ServerTickHandler serverTickHandler;
	public GuiHandler guiHandler;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent e) {
		instance = this;
		if (FMLCommonHandler.instance().getSide().isClient()) {
			TickRegistry.registerTickHandler(this.characterTicker = new CharacterTicker(), Side.CLIENT);
			TickRegistry.registerTickHandler(this.clientTickHandler = new ClientTickHandler(), Side.CLIENT);
		}
		TickRegistry.registerTickHandler(this.serverTickHandler = new ServerTickHandler(), Side.SERVER);
		MinecraftForge.EVENT_BUS.register(this.eventHandler = new EventHandler());
		NetworkRegistry.instance().registerGuiHandler(this, this.guiHandler = new GuiHandler());
		proxy.init(this);
	}
	
	public void postInit(FMLPreInitializationEvent e) {
		proxy.post(this);
	}
}
