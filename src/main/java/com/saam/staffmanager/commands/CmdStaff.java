package com.saam.staffmanager.commands;

import com.saam.staffmanager.StaffManager;
import com.saam.staffmanager.gui.RoleGui;
import com.saam.staffmanager.gui.ServerGui;
import com.saam.staffmanager.gui.defaultGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdStaff implements CommandExecutor {
    private final StaffManager plugin;

    public CmdStaff(StaffManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("staffmanager.user") && sender instanceof Player) {
            String defMenu = plugin.getConfig().getString("default_menu");
            if (args.length > 0) {
                String invType = args[0];
                if (invType.equalsIgnoreCase("roles")) {
                    //Open role Menu
                    new RoleGui(plugin, (Player) sender);
                } else if (invType.equalsIgnoreCase("servers")) {
                    //Open server Menu
                    new ServerGui(plugin, (Player) sender);
                } else {
                    //Open default Menu
                    defaultMenu(defMenu, sender);
                }
            } else {
                //Open default Menu
                defaultMenu(defMenu, sender);
            }
        }
        return false;
    }

    private void defaultMenu(String defMenu, CommandSender sender) {
        if (defMenu.equalsIgnoreCase("roles")) {
            //roles
            new RoleGui(plugin, (Player) sender);
        } else if (defMenu.equalsIgnoreCase("servers")) {
            //servers
            new ServerGui(plugin, (Player) sender);
        } else {
            //default!
            new defaultGui(plugin, (Player) sender);
        }
    }
}
