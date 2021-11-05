package de.rexlNico.Signs.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class addServer implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player) {
			
		}else{
			sender.sendMessage("".replaceAll("&", "§"));
		}
		return false;
	}

	
	
}
