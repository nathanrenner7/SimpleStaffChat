package me.pray.simplestaffchat;

import me.pray.simplestaffchat.commands.StaffChatReload;
import me.pray.simplestaffchat.commands.ToggleStaffChat;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class SimpleStaffChat extends JavaPlugin {

    public ArrayList<UUID> inStaffChat = new ArrayList<>();

    public File configf;
    public FileConfiguration config;

    public ConsoleCommandSender console = getServer().getConsoleSender();

    private double version = 1.0;

    @Override
    public void onEnable() {
        console.sendMessage(format("&5[SimpleStaffChat] &f- &dBy &6PrayRNGesus &d- started successfully, version: &6" + version));

        createFiles();
        initCmdsAndEvents();
    }

    private void initCmdsAndEvents() {

        //commands
        getCommand("staffchat").setExecutor(new ToggleStaffChat(this));
        getCommand("screload").setExecutor(new StaffChatReload(this));

        //events
        getServer().getPluginManager().registerEvents(new HandleMessages(this), this);
        getServer().getPluginManager().registerEvents(new JoinHandler(this), this);
    }

    public void createFiles() {
        configf = new File(getDataFolder(), "config.yml");

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        config = new YamlConfiguration();

        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String format(String string) {
        return string.replace("&", "§");
    }

    public String format(String string, Player player) {
        return string.replace("&", "§").replace("%player%", "" + player.getDisplayName());
    }

    public void sendScMessage(String message, Player player) {

        String prefix = getConfig().getString("staffchat.prefix");
        String messageColor = getConfig().getString("staffchat.message-color");

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("staffchat.use")) {
                p.sendMessage(format(prefix + messageColor + message, player));
            }
        }
    }
}
