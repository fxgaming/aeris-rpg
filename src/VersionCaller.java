import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import cpw.mods.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

@Mod(modid = "versionCaller")
public class VersionCaller {
	int major = 0;
	int minor = 0;
	int patch = 0;
	int build = 0;
	
	public VersionCaller() {
		this.read();
		this.build++;
		this.write();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@ForgeSubscribe
	public void hud(Post event) {
		if (event.type == event.type.ALL) {
			Minecraft.getMinecraft().fontRenderer.drawString("version: " + this.major + "." + this.minor + "." + this.patch + "." + this.build, 1, 1, Color.WHITE.getRGB());
		}
	}
	
	public void read() {
		try {
			File f = new File("buildVersion");
			if (!f.exists()) {
				f.createNewFile();
				this.write();
			}
			FileReader fr;
			BufferedReader br = new BufferedReader(fr = new FileReader(f));
			String[] l = br.readLine().split("-");
			this.major = Integer.valueOf(l[0]);
			this.minor = Integer.valueOf(l[1]);
			this.patch = Integer.valueOf(l[2]);
			this.build = Integer.valueOf(l[3]);
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write() {
		try {
			File f;
			FileWriter fw;
			BufferedWriter bw = new BufferedWriter(fw = new FileWriter(f = new File("buildVersion")));
			bw.write(this.major + "-" + this.minor + "-" + this.patch + "-" + this.build + System.getProperty("line.separator"));
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
