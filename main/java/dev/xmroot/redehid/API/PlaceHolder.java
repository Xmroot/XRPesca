package dev.xmroot.redehid.API;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import dev.xmroot.redehid.Main;
import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;

public class PlaceHolder {

    public static boolean registerXP() {
		if (Bukkit.getPluginManager().getPlugin("MVdWPlaceholderAPI") == null) {
			return false;
		}
		PlaceholderAPI.registerPlaceholder(Main.getInstance(), "pombaxp",
				(PlaceholderReplacer) new PlaceholderReplacer() {
					public String onPlaceholderReplace(final PlaceholderReplaceEvent e) {
						final Player player = e.getPlayer();
						if (player == null) {
							return "";
						}
						Main.getInstance();
						return String.valueOf(NivelAPI.getXP(player) + "/" + Main.m.getConfig().getDouble("Nivel." + NivelAPI.getNivel(player) + ".Limite")).replace(".0", "");
					}
				});
		return true;
	}
	
	public static boolean registerNivel() {
		if (Bukkit.getPluginManager().getPlugin("MVdWPlaceholderAPI") == null) {
			return false;
		}
		PlaceholderAPI.registerPlaceholder(Main.getInstance(), "pombanivel",
				(PlaceholderReplacer) new PlaceholderReplacer() {
					public String onPlaceholderReplace(final PlaceholderReplaceEvent e) {
						final Player player = e.getPlayer();
						if (player == null) {
							return "";
						}
						Main.getInstance();
						return String.valueOf(NivelAPI.getNivel(player)).replace(".0", "");
					}
				});
		return true;
	}
}
