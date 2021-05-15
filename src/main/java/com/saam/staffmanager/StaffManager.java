package com.saam.staffmanager;

import com.saam.staffmanager.commands.CmdStaff;
import com.saam.staffmanager.commands.CmdStaffManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StaffManager extends JavaPlugin {

    private String version;

    @Override
    public void onEnable(){
        version = this.getDescription().getVersion();
        System.out.println("Staff Manager V."+version+" enabled");

        //Include Commands
        getCommand("staff").setExecutor(new CmdStaff(this));
        getCommand("staffmanager").setExecutor(new CmdStaffManager(this));
    }

    @Override
    public void onDisable(){
        System.out.println("Staff Manager V."+version+" disabled succesfully!");
    }
}
