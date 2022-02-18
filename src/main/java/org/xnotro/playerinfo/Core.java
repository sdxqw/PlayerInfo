package org.xnotro.playerinfo;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    public static String playerNameMessage;
    public static String playerLastJoinMessage;
    public static String playerPositionMessage;
    public static String playerPositionMessageX;
    public static String playerPositionMessageY;
    public static String playerPositionMessageZ;
    public static String playerPositionMessageWorld;
    public static String playerUUIDMessage;

    public static YamlConfiguration cfPlayerInfo = new YamlConfiguration();
    public static File configPlayerInfo;
    Logger logger = Bukkit.getLogger();


    @Override
    public void onEnable() {
        logger.info("[PlayerInfo] > Plugin Enabled.");
        getCommand("pinfo").setExecutor(new CommandHandler());
        logger.info("[PlayerInfo] > Command Loaded.");
        getServer().getPluginManager().registerEvents(new EventManager(), this);
        logger.info("[PlayerInfo] > Event Loaded.");
        saveDefaultConfig();
        configInitializer();
        logger.info("[PlayerInfo] > Config Loaded.");
    }

    public void configInitializer() {
        playerNameMessage = getConfig().getString("messages.playerNameMessage");
        playerLastJoinMessage = getConfig().getString("messages.playerLastJoinMessage");
        playerPositionMessage = getConfig().getString("messages.playerPositionMessage");
        playerPositionMessageX = getConfig().getString("messages.playerPositionMessageX");
        playerPositionMessageY = getConfig().getString("messages.playerPositionMessageY");
        playerPositionMessageZ = getConfig().getString("messages.playerPositionMessageZ");
        playerPositionMessageWorld = getConfig().getString("messages.playerPositionMessageWorld");
        playerUUIDMessage = getConfig().getString("messages.playerUUIDMessage");
    }
}
