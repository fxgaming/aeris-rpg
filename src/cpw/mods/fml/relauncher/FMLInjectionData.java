package cpw.mods.fml.relauncher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import net.minecraftforge.common.ForgeVersion;

public class FMLInjectionData {
	static File minecraftHome;
	static String major = "6";
	static String minor = "6";
	static String rev = "2";
	static String build = "534";
	static String mccversion = "7.26";
	static String mcpversion = "1.4.7";
	public static List<String> containers = new ArrayList<String>();

	public static Object[] data() {
		return new Object[] { major, minor, rev, build, mccversion, mcpversion, minecraftHome, containers };
	}
}
