package me.pray.simplestaffchat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinHandler implements Listener {

    SimpleStaffChat sc;

    public JoinHandler(SimpleStaffChat sc) {
        this.sc = sc;
    }

    @EventHandler
    public void onStaffJoin(PlayerJoinEvent event) {
        if(event.getPlayer().hasPermission("staffchat.use")) {
            Player player = event.getPlayer();
            if(sc.getConfig().getBoolean("staff-login.enabled")) {
                sendJoinMessage(player);
            }
        }
    }

    @EventHandler
    public void onStaffQuit(PlayerQuitEvent event) {
        if(event.getPlayer().hasPermission("staffchat.use")) {
            Player player = event.getPlayer();
            if(sc.getConfig().getBoolean("staff-logout.enabled")) {
                sendQuitMessage(player);
            }
        }
    }

    private void sendJoinMessage(Player player) {

        String joinMessage = sc.getConfig().getString("staff-login.join-message");

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("staffchat.use")) {
                p.sendMessage(sc.format(joinMessage, player));
            }
        }

    }

    private void sendQuitMessage(Player player) {

        String quitMessage = sc.getConfig().getString("staff-logout.quit-message");

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("staffchat.use")) {
                p.sendMessage(sc.format(quitMessage, player));
            }
        }

    }

}
