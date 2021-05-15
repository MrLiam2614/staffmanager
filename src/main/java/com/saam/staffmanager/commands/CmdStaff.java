package com.saam.staffmanager.commands;

import com.saam.staffmanager.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdStaff implements CommandExecutor {
    private final StaffManager plugin;

    public CmdStaff(StaffManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("staffmanager.user")){
            System.out.println("");
        }
        return false;
    }
}
