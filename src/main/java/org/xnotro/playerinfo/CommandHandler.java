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

        // define the command
        if (command.getName().equalsIgnoreCase("pinfo")) {

            // if sender isn't player start this if statement
            if (!(sender instanceof Player)) {
                sender.sendMessage("you cant use this command in console.");
                return true;
            }

            // define our sender as player
            Player player = (Player) sender;

            // get date
            Date date = new Date();

            // set date format
            SimpleDateFormat type = new SimpleDateFormat("MM-dd-yyyy");

            // if the player have this permission start the if statement
            if (player.hasPermission("pinfo.command")) {

                // if the player type only /pinfo
                if (args.length == 0) {
                    player.sendMessage("[PlayerInfo] > please specify a player.");

                    // if the player type /pinfo [something]
                } else if (args.length == 1) {

                    // get the exact player that the player (sender) typed
                    Player playerTarget = Bukkit.getPlayerExact(args[0]);

                    // if the player is offline or don't exist start this if statement
                    if (playerTarget == null) {
                        player.sendMessage("[PlayerInfo] > the player typed don't exists or is offline.");

                    // if the player exist start sending info's about our player
                    } else {
                        player.sendMessage("[PlayerInfo] > Here The Info's about " + player.getName() + " PAPI:");
                        player.sendMessage(Core.playerNameMessage);
                        player.sendMessage(Core.playerLastJoinMessage);
                        player.sendMessage(Core.playerPositionMessage+ " " + Core.playerPositionMessageX+ " "+ Core.playerPositionMessageY+ " " +Core.playerPositionMessageZ+ " " +Core.playerPositionMessageWorld);
                        player.sendMessage(Core.playerUUIDMessage);
                    }
                }
            }
        }
        return true;
    }
}
