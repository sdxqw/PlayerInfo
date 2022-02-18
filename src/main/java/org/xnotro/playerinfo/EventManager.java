package org.xnotro.playerinfo;

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
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        UUID playerUUID = event.getPlayer().getUniqueId();
        Player player = event.getPlayer();
        configPlayerInfo = new File("plugins/PlayerInfo/PlayerInfoOf-" + playerUUID + ".yml");
        Core.cfPlayerInfo = YamlConfiguration.loadConfiguration(configPlayerInfo);
        Date date = new Date();
        SimpleDateFormat type = new SimpleDateFormat("MM-dd-yyyy");
        if(!player.hasPlayedBefore()) {
            Core.cfPlayerInfo.set(playerUUID + ".Player.Name", player.getName());
            Core.cfPlayerInfo.set(playerUUID + ".Player.LastJoin", type.format(date));
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.x", player.getLocation().getBlockX());
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.y", player.getLocation().getBlockY());
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.z", player.getLocation().getBlockZ());
            Core.cfPlayerInfo.set(playerUUID + ".Player.Position.World", player.getWorld().getName());
            Core.cfPlayerInfo.save(configPlayerInfo);
            logger.info("[PlayerInfo] > Player file created for " + playerUUID + " and saved.");
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
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws IOException {
        UUID playerUUID = event.getPlayer().getUniqueId();
        Player player = event.getPlayer();
        configPlayerInfo = new File("plugins/PlayerInfo/PlayerInfoOf-" + playerUUID + ".yml");
        Core.cfPlayerInfo = YamlConfiguration.loadConfiguration(configPlayerInfo);
        Date date = new Date();
        SimpleDateFormat type = new SimpleDateFormat("MM-dd-yyyy");
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
