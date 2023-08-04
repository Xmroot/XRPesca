package dev.xmroot.redehid.Comandos;

import java.net.http.WebSocket.Listener;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import dev.xmroot.redehid.Main;
import dev.xmroot.redehid.API.APIGeral;
import dev.xmroot.redehid.API.ActionBar;
import dev.xmroot.redehid.API.NivelAPI;
import dev.xmroot.redehid.API.LocationAPI;
import dev.xmroot.redehid.Configs.DataBase;

public class Pesca implements CommandExecutor, Listener{
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("pesca")) {
			if (args.length == 0) {
				if (!DataBase.fc.contains(p.getUniqueId().toString())) {
					NivelAPI.setNivel(p, 0.0);
					NivelAPI.setXP(p, 0.0);
				}
				if (Main.emPesca.contains(p.getName())) {
					if (LocationAPI.hasLoc("Saida")) {
						APIGeral.removeItem(p, Material.FISHING_ROD);
						ActionBar.sendActionBarMessage(p, "§7Teleportado para o §aspawn");
						Main.emPesca.remove(p.getName());
						Main.Pescando.remove(p.getName());
						p.teleport(LocationAPI.getLoc("Saida"));
						return true;
					}
				} else {
					if (LocationAPI.hasLoc("Entrada")) {
						if (APIGeral.isEmpty(p)) {
							ActionBar.sendActionBarMessage(p, "§7Teleportado para a área de §apesca");
							p.setItemInHand(new ItemStack(Material.FISHING_ROD));
							p.teleport(LocationAPI.getLoc("Entrada"));
							Main.emPesca.add(p.getName());
							return true;
						} else {
							ActionBar.sendActionBarMessage(p, "§4Você deve estar com o inventário vazio para pescar");
						}
					} else {
						ActionBar.sendActionBarMessage(p, "§4Posições não encontradas identificadas!");
					}
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("set")) {
					if (p.hasPermission("Pomba.pesca")) {
						if (args[1].equalsIgnoreCase("Entrada")) {
							LocationAPI.setLoc(p.getLocation(), "Entrada");
							ActionBar.sendActionBarMessage(p, "§7Localização setada com sucesso");
						} else if (args[1].equalsIgnoreCase("Saida")) {
							LocationAPI.setLoc(p.getLocation(), "Saida");
							ActionBar.sendActionBarMessage(p, "§7Localização setada com sucesso");
						} else {
							ActionBar.sendActionBarMessage(p, "§4Localização inválida");
						}
					}
				}
			} else {
				if (p.hasPermission("Pomba.pesca")) {
					p.sendMessage("§a---= PombaPesca =---");
					p.sendMessage("§a/pesca set (entrada/saida)");
				}
			}
		}
		return false;
	}
}
