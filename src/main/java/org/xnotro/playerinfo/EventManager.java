package org.xnotro.playerinfo;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.util.UUID;
import java.util.logging.Logger;

import static org.xnotro.playerinfo.Core.configPlayerInfo;

public class EventManager implements Listener {

    Logger logger = Bukkit.getLogger();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event, String[] args) throws IOException {

        // get player uuid
        UUID playerUUID = event.getPlayer().getUniqueId();

        // get our player
        Player player = event.getPlayer();

        // create the data file when player join (uuid)
        configPlayerInfo = new File("plugins/PlayerInfo/PlayerInfoOf-" + playerUUID + ".yml");

        // load the data file
        Core.cfPlayerInfo = YamlConfiguration.loadConfiguration(configPlayerInfo);

        // get date
        Date date = new Date();

        // set date format
        SimpleDateFormat type = new SimpleDateFormat("MM-dd-yyyy");

        // if the player never entered our server start if statement that define our data
        if(!player.hasPlayedBefore()) {
            Core.cfPlayerInfo.set(playerUUID + ".Player.Name", player.getName());
            Core.cfPlayerInfo.set(playerUUID + ".Player.LastJoin", type.format(date));
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.x", player.getLocation().getBlockX());
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.y", player.getLocation().getBlockY());
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.z", player.getLocation().getBlockZ());
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.World", player.getWorld().getName());
            Core.cfPlayerInfo.save(configPlayerInfo);
            logger.info("[PlayerInfo] > Player file created for " + playerUUID + " and saved.");

            // if the player is join 1 time update our data
        } else {
            Core.cfPlayerInfo.set(playerUUID + ".Player.Name", player.getName());
            Core.cfPlayerInfo.set(playerUUID + ".Player.LastJoin", type.format(date));
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.x", player.getLocation().getBlockX());
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.y", player.getLocation().getBlockY());
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.z", player.getLocation().getBlockZ());
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.World", player.getWorld().getName());
            Core.cfPlayerInfo.save(configPlayerInfo);
            logger.info("[PlayerInfo] > Updated player file for " + playerUUID);
        }

        Core.playerNameMessage = PlaceholderAPI.setPlaceholders(player, Core.playerNameMessage);
        Core.playerLastJoinMessage = PlaceholderAPI.setPlaceholders(player, Core.playerNameMessage);
        Core.playerPositionMessageX = PlaceholderAPI.setPlaceholders(player, Core.playerNameMessage);
        Core.playerPositionMessageY = PlaceholderAPI.setPlaceholders(player, Core.playerNameMessage);
        Core.playerPositionMessageZ = PlaceholderAPI.setPlaceholders(player, Core.playerNameMessage);
        Core.playerPositionMessageWorld = PlaceholderAPI.setPlaceholders(player, Core.playerNameMessage);
        Core.playerUUIDMessage = PlaceholderAPI.setPlaceholders(player, Core.playerNameMessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws IOException {

        // get player uuid
        UUID playerUUID = event.getPlayer().getUniqueId();

        // get our player
        Player player = event.getPlayer();

        // update the data file when player quit (uuid)
        configPlayerInfo = new File("plugins/PlayerInfo/PlayerInfoOf-" + playerUUID + ".yml");

        // load the data file
        Core.cfPlayerInfo = YamlConfiguration.loadConfiguration(configPlayerInfo);

        // get date
        Date date = new Date();

        // set date format
        SimpleDateFormat type = new SimpleDateFormat("MM-dd-yyyy");

        // update things
        Core.cfPlayerInfo.set(playerUUID + ".Player.Name", player.getName());
        Core.cfPlayerInfo.set(playerUUID + ".Player.LastJoin", type.format(date));
        Core.cfPlayerInfo.set(playerUUID + ".Player.Position.x", player.getLocation().getBlockX());
        Core.cfPlayerInfo.set(playerUUID + ".Player.Position.y", player.getLocation().getBlockY());
        Core.cfPlayerInfo.set(playerUUID + ".Player.Position.z", player.getLocation().getBlockZ());
        Core.cfPlayerInfo.set(playerUUID + ".Player.Position.World", player.getWorld().getName());
        Core.cfPlayerInfo.save(configPlayerInfo);
        logger.info("[PlayerInfo] > Updated player file for " + playerUUID);
    }
}
