package me.pray.simplestaffchat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class HandleMessages implements Listener {

    private final SimpleStaffChat sc;

    public HandleMessages(SimpleStaffChat sc) {
        this.sc = sc;
    }

    @EventHandler
    public void onStaffChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("staffchat.use")) {
            UUID uuid = player.getUniqueId();
            String message = event.getMessage();

            if (!sc.inStaffChat.contains(uuid)) return;

            event.setCancelled(true);

            sc.sendScMessage(message, player);

        }
    }



}
