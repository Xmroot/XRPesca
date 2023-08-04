package dev.xmroot.redehid.Configs;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import dev.xmroot.redehid.Main;

public class Locs {
    
    static File f;
	public static FileConfiguration fc;
	static Locs m;

	public static void create() {
		f = new File(Main.m.getDataFolder() + "/databases/locs.db");
		fc = YamlConfiguration.loadConfiguration(f);
	}

	public static void SaveConfig() {
		try {
			fc.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Locs config() {
		return m;
	}
}
