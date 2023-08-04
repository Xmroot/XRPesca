package dev.xmroot.redehid.Eventos;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.xmroot.redehid.Main;

public class Secundarios implements Listener{

    @EventHandler
	public void onFish(PlayerFishEvent e) {
		if (e.getState() == State.CAUGHT_FISH) {
			e.setCancelled(true);
			return;
		}
	}

    @EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (Main.emPesca.contains(p.getName())) {
			APIGeral.removeItem(p, Material.FISHING_ROD);
			Main.emPesca.remove(p.getName());
			Main.Pescando.remove(p.getName());
		}
	}
	
	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e) {
		Player p = e.getPlayer();
		if (Main.emPesca.contains(p.getName())) {
			APIGeral.removeItem(p, Material.FISHING_ROD);
			Main.emPesca.remove(p.getName());
			Main.Pescando.remove(p.getName());
		}
	}

	@EventHandler
	public void PlayerPreProcess(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (!e.getMessage().toLowerCase().startsWith("/pesca") && !e.getMessage().toLowerCase().startsWith("/g ") && !e.getMessage().toLowerCase().startsWith("/tell") && !e.getMessage().toLowerCase().startsWith("/c ") && !e.getMessage().toLowerCase().startsWith("/stacklivro")) {
			if (Main.emPesca.contains(p.getName())) {
				ActionBar.sendActionBarMessage(p, "§4Digite /pesca para sair do mundo de pesca!");
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (Main.emPesca.contains(p.getName())) {
			if (e.getItemDrop().getItemStack().hasItemMeta()) {
				if (e.getItemDrop().getItemStack().getType() == Material.FISHING_ROD) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntityType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			if (Main.emPesca.contains(p.getName())) {
				Main.emPesca.remove(p.getName());
				Main.Pescando.remove(p.getName());
				e.getDrops().clear();
			}
		}
	}
    
}
