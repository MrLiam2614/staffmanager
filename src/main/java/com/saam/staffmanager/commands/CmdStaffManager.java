package com.saam.staffmanager.commands;

import com.saam.staffmanager.StaffManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CmdStaffManager implements CommandExecutor {
    private final StaffManager plugin;

    public CmdStaffManager(StaffManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //Has user permissions?
        if (sender.hasPermission("staffmanager.admin")) {
            if (args.length > 1) {
                String type = args[0];
                String player = args[1];
                if (type.equalsIgnoreCase("add")) {
                    String role = args[2];
                    String server = "global";

                    if (args.length > 3) {
                        server = args[3];
                    }
                    plugin.getConfig().set("Staff." + player + ".server", server);
                    plugin.getConfig().set("Staff." + player + ".role", role);
                } else if (type.equalsIgnoreCase("remove")) {
                    if(plugin.getConfig().get(player) != null) {
                        plugin.getConfig().set(player, null);
                    }else{
                        sender.sendMessage(ChatColor.RED + "Player doesn't exists");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Try something like [add/remove], maybe it works :D");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Try something like [add/remove], maybe it works :D");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "YOU HAVE NO PERMISSIONS; WHAT THE DUCK ARE YOU DOING!");
        }
        return false;
    }
}
