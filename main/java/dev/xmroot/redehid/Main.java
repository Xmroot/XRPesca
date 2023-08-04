package dev.xmroot.redehid;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import dev.xmroot.redehid.API.APIGeral;
import dev.xmroot.redehid.API.LocationAPI;
import dev.xmroot.redehid.API.NivelAPI;
import dev.xmroot.redehid.API.PlaceHolder;
import dev.xmroot.redehid.Comandos.Pesca;
import dev.xmroot.redehid.Configs.DataBase;
import dev.xmroot.redehid.Configs.Locs;
import dev.xmroot.redehid.Eventos.Principais;
import dev.xmroot.redehid.Eventos.Secundarios;

public class Main extends JavaPlugin implements Listener {

    public static Main m;
	public static ArrayList<String> emPesca = new ArrayList<String>();
	public static ArrayList<String> Pescando = new ArrayList<String>();

    public void onEnable() {

		m = this;
		saveDefaultConfig();

		DataBase.create();
		DataBase.SaveConfig();
		Locs.create();
		Locs.SaveConfig();

		PlaceHolder.registerNivel();
		PlaceHolder.registerXP();

		Bukkit.getPluginManager().registerEvents(new APIGeral(), this);
		Bukkit.getPluginManager().registerEvents(new LocationAPI(), this);
		Bukkit.getPluginManager().registerEvent(new NivelAPI(), this);
		Bukkit.getPluginManager().registerEvents(new Principais(), this);
		Bukkit.getPluginManager().registerEvents(new Secundarios(), this);
		Bukkit.getPluginManager().registerEvents(this, this);
		getCommand("pesca").setExecutor(new Pesca());

		new BukkitRunnable() {
			public void run() {
				for (String s : Pescando) {
					if (Bukkit.getPlayer(s) != null) {
						Player p = Bukkit.getPlayer(s);
						if (NivelAPI.getNivel(p) != null) {
							for (String command : Main.m.getConfig()
									.getStringList("Nivel." + NivelAPI.getNivel(p) + ".Comandos")) {
								String[] split = command.split(";");
								if (split.length > 0) {
									Double pct = Double.valueOf(split[1]);
									Double xp = Double.valueOf(split[2]);
									String comando = split[0].replace("%p", p.getName());
									if (APIGeral.percentChance(pct)) {
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando);
										NivelAPI.GainXP(p, xp);
										if (p.getInventory().firstEmpty() == -1) {
											p.chat("/stacklivros pilhagem");
											p.chat("/stacklivros fortuna");
										}
									}
								}
							}
						}
					}
				}
			}
		}.runTaskTimer(this, 0L, Integer.valueOf(Main.m.getConfig().getString("Delay")) * 20L);
	}

    public static Main getInstance() {
		return m;
	}
}
