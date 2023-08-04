package dev.xmroot.redehid.API;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;

import dev.xmroot.redehid.Configs.Locs;

public class LocationAPI implements Listener {
    
    public static boolean hasLoc(String Loc) {
		if (Locs.fc.contains("Locs")) {
			if (Locs.fc.getConfigurationSection("Locs").contains(Loc)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static void setLoc(Location loc, String Loc) {
		Locs.fc.set("Locs." + Loc + ".x", loc.getX());
		Locs.fc.set("Locs." + Loc + ".y", loc.getY());
		Locs.fc.set("Locs." + Loc + ".z", loc.getZ());
		Locs.fc.set("Locs." + Loc + ".world", loc.getWorld().getName());
		Locs.SaveConfig();
	}

	public static void removeLoc(String Loc) {
		Locs.fc.set("Locs." + Loc, null);
		Locs.SaveConfig();
	}

	public static Location getLoc(String Loc) {
		String x = Locs.fc.getString("Locs." + Loc + ".x");
		String y = Locs.fc.getString("Locs." + Loc + ".y");
		String z = Locs.fc.getString("Locs." + Loc + ".z");
		String world = Locs.fc.getString("Locs." + Loc + ".world");

		return new Location(Bukkit.getWorld(world), Double.valueOf(x), Double.valueOf(y) + 1.0, Double.valueOf(z));
	}
}
