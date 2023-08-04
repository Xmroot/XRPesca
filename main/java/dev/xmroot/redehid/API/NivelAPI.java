package dev.xmroot.redehid.API;

import org.bukkit.entity.Player;
import dev.xmroot.redehid.Main;
import dev.xmroot.redehid.Configs.DataBase;

public class NivelAPI {
 
    public static Double getNivel(Player p) {
		if (p != null) {
			if (p.getUniqueId() != null) {
				if (DataBase.fc.contains(p.getUniqueId().toString())) {
					return DataBase.fc.getDouble(String.valueOf(p.getUniqueId()) + ".Nivel");
				} else {
					return 0.0;
				}
			}
		}
		return null;
	}

	public static void setNivel(Player p, Double quantidade) {
		DataBase.fc.set(String.valueOf(p.getUniqueId()) + ".Nivel", quantidade);
		DataBase.SaveConfig();
	}

	public static void addNivel(Player p, Double quantidade) {
		DataBase.fc.set(String.valueOf(p.getUniqueId()) + ".Nivel", getNivel(p) + quantidade);
		DataBase.SaveConfig();
	}

	public static void removeNivel(Player p, Double quantidade) {
		if (getNivel(p) - quantidade <= 0) {
			DataBase.fc.set(String.valueOf(p.getUniqueId()) + ".Nivel", 0.0);
			DataBase.SaveConfig();
		} else {
			DataBase.fc.set(String.valueOf(p.getUniqueId()) + ".Nivel", getNivel(p) - quantidade);
			DataBase.SaveConfig();
		}
	}

	public static Double getXP(Player p) {
		if (DataBase.fc.contains(p.getUniqueId().toString())) {
			return DataBase.fc.getDouble(String.valueOf(p.getUniqueId()) + ".XP");
		} else {
			return 0.0;
		}
	}

	public static void setXP(Player p, Double quantidade) {
		DataBase.fc.set(String.valueOf(p.getUniqueId()) + ".XP", quantidade);
		DataBase.SaveConfig();
	}

	public static void addXP(Player p, Double quantidade) {
		DataBase.fc.set(String.valueOf(p.getUniqueId()) + ".XP", getXP(p) + quantidade);
		DataBase.SaveConfig();
	}

	public static void removeXP(Player p, Double quantidade) {
		if (getXP(p) - quantidade <= 0) {
			DataBase.fc.set(String.valueOf(p.getUniqueId()) + ".XP", 0.0);
			DataBase.SaveConfig();
		} else {
			DataBase.fc.set(String.valueOf(p.getUniqueId()) + ".XP", getXP(p) - quantidade);
			DataBase.SaveConfig();
		}
	}

	public static Double getMaxLevel() {
		return Main.m.getConfig().getDouble("NivelMax");
	}

	public static void GainXP(Player p, Double quantidade) {
		Double Nivel = getNivel(p);
		Double NivelMax = getMaxLevel();
		Double XP = getXP(p);
		Double XPMax = Main.m.getConfig().getDouble("Nivel." + Nivel + ".Limite");
		if (!(Nivel + 1 > NivelMax)) {
			if (XP + quantidade > XPMax) {
				p.sendMessage(String.valueOf(XP + quantidade));
				addNivel(p, 1.0);
				removeXP(p, XPMax);
			} else {
				addXP(p, quantidade);
			}
		}
	}
}
