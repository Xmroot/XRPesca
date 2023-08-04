package dev.xmroot.redehid.Eventos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import dev.xmroot.redehid.Main;
import dev.xmroot.redehid.API.ActionBar;

public abstract class Principais implements Listener{
    
    @EventHandler
	public void onFish(PlayerFishEvent e) {
		Player p = e.getPlayer();
		if (Main.emPesca.contains(p.getName())) {
			if (Main.Pescando.contains(p.getName())) {
				Main.Pescando.remove(p.getName());
				ActionBar.sendActionBarMessage(p, "§eAahw que pena que você não quer mais pescar!...");
				return;
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.m, new Runnable() {
				public void run() {
					if (e.getState() == State.FISHING) {
						if (p.getWorld().getBlockAt(e.getHook().getLocation()).getType().toString().contains("WATER")) {
							Main.Pescando.add(p.getName());
							ActionBar.sendActionBarMessage(p, "§aComeçando a pescar...");
						} else {
							ActionBar.sendActionBarMessage(p, "§cEii, Você deve pescar na água");
							ItemStack item = p.getItemInHand();
							p.setItemInHand(new ItemStack(Material.AIR));
							Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.m, new Runnable() {
								public void run() {
									p.setItemInHand(item);
								}
							}, 1);
						}
					}
				}
			}, 10);
		}
	}
}
