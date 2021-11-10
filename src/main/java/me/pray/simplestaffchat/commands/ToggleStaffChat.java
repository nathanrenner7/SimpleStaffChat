package me.pray.simplestaffchat.commands;

import me.pray.simplestaffchat.SimpleStaffChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ToggleStaffChat implements CommandExecutor {

    private final SimpleStaffChat sc;

    public ToggleStaffChat(SimpleStaffChat sc) {
        this.sc = sc;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("staffchat.use")) {
                String enabled = sc.getConfig().getString("staffchat.enabled-msg");
                String disabled = sc.getConfig().getString("staffchat.disabled-msg");
                UUID uuid = player.getUniqueId();

                if (args.length == 0) {
                    if (sc.inStaffChat.contains(uuid)) {
                        sc.inStaffChat.remove(uuid);
                        player.sendMessage(sc.format(disabled, player));
                    } else {
                        sc.inStaffChat.add(uuid);
                        player.sendMessage(sc.format(enabled, player));
                    }
                } else {
                    String message = "";
                    for (int i = 0; i < args.length; i++) {
                        message += args[i] + " ";
                    }

                    sc.sendScMessage(message, player);
                }
            }
        }

        return true;
    }
}
