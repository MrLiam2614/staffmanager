package com.saam.staffmanager;

import com.saam.staffmanager.commands.CmdStaff;
import com.saam.staffmanager.commands.CmdStaffManager;
import com.saam.staffmanager.gui.RoleGui;
import com.saam.staffmanager.gui.ServerGui;
import com.saam.staffmanager.gui.defaultGui;
import org.bukkit.plugin.java.JavaPlugin;

public class StaffManager extends JavaPlugin {

    private String version;
    public RoleGui roleGui;
    public ServerGui serverGui;
    public defaultGui defaultGui;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        version = this.getDescription().getVersion();
        System.out.println("Staff Manager V." + version + " enabled");

        //Include Commands
        getCommand("staff").setExecutor(new CmdStaff(this));
        getCommand("staffmanager").setExecutor(new CmdStaffManager(this));
    }

    @Override
    public void onDisable() {
        System.out.println("Staff Manager V." + version + " disabled succesfully!");
    }
}
