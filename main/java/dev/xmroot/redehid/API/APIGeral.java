package dev.xmroot.redehid.API;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class APIGeral implements Listener {
    
    public static boolean isEmpty(Player p) {
		for (ItemStack item : p.getInventory().getContents()) {
			if (item != null)
				return false;
		}
		return true;
	}

	public static void removeItem(Player p, Material Item) {
		for (int i = 0; i < 36; i++) {
			if (p.getInventory().getItem(i) != null) {
				if (p.getInventory().getItem(i).getType() == Item) {
					p.getInventory().setItem(i, new ItemStack(Material.AIR));
				}
			}
		}
	}

	public static boolean percentChance(final double percent) {
		if (percent < 0.0 || percent > 100.0) {
			throw new IllegalArgumentException("A percentagem nao pode ser maior do que 100 nem menor do que 0");
		}
		final double result = new Random().nextDouble() * 100.0;
		return result <= percent;
	}
}
