package org.xnotro.playerinfo;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    // Static Statements for messages
    public static String playerNameMessage;
    public static String playerLastJoinMessage;
    public static String playerPositionMessage;
    public static String playerPositionMessageX;
    public static String playerPositionMessageY;
    public static String playerPositionMessageZ;
    public static String playerPositionMessageWorld;
    public static String playerUUIDMessage;

    // PAPI THINGS
    public static String playerNameMessagePAPI;
    public static String playerLastJoinMessagePAPI;
    public static String playerPositionMessageXPAPI;
    public static String playerPositionMessageYPAPI;
    public static String playerPositionMessageZPAPI;
    public static String playerPositionMessageWorldPAPI;
    public static String playerUUIDMessagePAPI;

    // Statements for configuration
    public static YamlConfiguration cfPlayerInfo = new YamlConfiguration();
    public static File configPlayerInfo;

    // Bukkit default logger
    Logger logger = Bukkit.getLogger();


    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            logger.info("[PlayerInfo] > Plugin Enabled.");
            getCommand("pinfo").setExecutor(new CommandHandler());
            logger.info("[PlayerInfo] > Command Loaded.");
            saveDefaultConfig();
            configInitializer();
            logger.info("[PlayerInfo] > Config Loaded.");
            Bukkit.getPluginManager().registerEvents(new EventManager(), this);
            logger.info("[PlayerInfo] > Event Loaded.");
        } else {
            logger.warning("[PlayerInfo] > Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

    }

    public void configInitializer() {
        // Config Messages
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
