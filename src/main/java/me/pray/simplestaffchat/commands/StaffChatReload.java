package me.pray.simplestaffchat.commands;

import me.pray.simplestaffchat.SimpleStaffChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StaffChatReload implements CommandExecutor {
    SimpleStaffChat sc;

    public StaffChatReload(SimpleStaffChat sc) {
        this.sc = sc;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("staffchat.reload") || sender.isOp()) {
            String reloadMsg = sc.getConfig().getString("staffchat.reload-msg");
            sc.reloadConfig();
            sender.sendMessage(sc.format(reloadMsg));
        }

        return true;
    }
}
