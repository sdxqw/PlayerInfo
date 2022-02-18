package org.xnotro.playerinfo;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("pinfo")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("you cant use this command in console.");
                return true;
            }

            Player player = (Player) sender;
            Date date = new Date();
            SimpleDateFormat type = new SimpleDateFormat("MM-dd-yyyy");

            if (player.hasPermission("pinfo.command")) {
                if (args.length == 0) {
                    player.sendMessage("[PlayerInfo] > please specify a player.");
                } else if (args.length == 1) {
                    Player playerTarget = Bukkit.getPlayerExact(args[0]);
                    if (playerTarget == null) {
                        player.sendMessage("[PlayerInfo] > the player typed don't exists or is offline.");
                    } else {
                        player.sendMessage("[PlayerInfo] > Here The Info's about " + player.getName() + ":");
                        player.sendMessage(Core.playerNameMessage+" " + player.getName());
                        player.sendMessage(Core.playerLastJoinMessage + type.format(date));
                        player.sendMessage(Core.playerPositionMessage+ " " +
                                        Core.playerPositionMessageX+ " " + player.getLocation().getBlockX() +
                                Core.playerPositionMessageY+ " " + player.getLocation().getBlockY() +
                                Core.playerPositionMessageZ+ " " + player.getLocation().getBlockZ() +
                                Core.playerPositionMessageWorld+ " " + player.getWorld().getName()
                        );
                        player.sendMessage(Core.playerUUIDMessage+ " " + playerTarget.getUniqueId());
                    }
                }
            }
        }
        return true;
    }
}
