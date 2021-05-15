package com.saam.staffmanager.commands;

import com.saam.staffmanager.StaffManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdStaffManager implements CommandExecutor {
    private final StaffManager plugin;

    public CmdStaffManager(StaffManager plugin) {
        this.plugin = plugin;
    }


    private void addPlayer(CommandSender sender, String[] args) {
        //staffmanager player [add/remove] [player] [role] [roleSlot] [server] [serverSlot]
        if (args.length > 1) {
            String section = "Staff";
            String type = args[1];
            if (type.equalsIgnoreCase("add")) {
                if (args.length == 7) {
                    String player = args[2];
                    String role = args[3];
                    int roleSlot = Integer.parseInt(args[4]);
                    String server = args[5];
                    int serverSlot = Integer.parseInt(args[6]);
                    section += "." + player;

                    plugin.getConfig().set(section + ".role", role);
                    plugin.getConfig().set(section + ".server", server);
                    plugin.getConfig().set(section + ".roleSlot", roleSlot);
                    plugin.getConfig().set(section + ".serverSlot", serverSlot);
                    plugin.saveConfig();

                    if (plugin.getConfig().getConfigurationSection("Menu.servers." + server) == null) {
                        sender.sendMessage(ChatColor.RED + "Remember to add the server " + ChatColor.GOLD + server);
                    }
                    if (plugin.getConfig().getConfigurationSection("Menu.roles." + role) == null) {
                        sender.sendMessage(ChatColor.RED + "Remember to add the role " + ChatColor.GOLD + role);
                    }

                    sender.sendMessage(ChatColor.GREEN + "Player added correctly!");
                } else {
                    sender.sendMessage(ChatColor.RED + "Use: " + ChatColor.GOLD + "/staffmanager player [add] [player] [role] [roleSlot] [server/global] [serverSlot]");
                }
            } else if (type.equalsIgnoreCase("remove")) {
                if (args.length == 3) {
                    String player = args[2];
                    plugin.getConfig().set(section + "." + player, null);
                    plugin.saveConfig();
                } else {
                    sender.sendMessage(ChatColor.RED + "Use: " + ChatColor.GOLD + "/staffmanager player [remove] [player]");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "argument not valid, try with: " + ChatColor.GOLD + "/staffmanager player [add/remove]");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Try with: " + ChatColor.GOLD + "/staffmanager player [add/remove]");
        }
    }

    private void addServer(CommandSender sender, String[] args) {
        //staffmanager server [add/remove] [name] [displayname] [serverSlot] [row] [Item]
        if (args.length > 1) {
            String section = "Menu.servers";
            String type = args[1];
            if (type.equalsIgnoreCase("add")) {
                if (args.length == 7) {
                    String server = args[2];
                    String displayName = args[3];
                    int serverSlot = Integer.parseInt(args[4]);
                    int rowNumber = Integer.parseInt(args[5]);
                    String Item = args[6];
                    section += "." + server;


                    try {
                        Material item = Material.valueOf(Item);

                        plugin.getConfig().set(section + ".slot", serverSlot);
                        plugin.getConfig().set(section + ".rows", rowNumber);
                        plugin.getConfig().set(section + ".item", Item);
                        plugin.getConfig().set(section + ".name", displayName);

                        plugin.saveConfig();

                        sender.sendMessage(ChatColor.GREEN + "Server added correctly!");
                    } catch (Exception ex) {
                        sender.sendMessage(ChatColor.RED + Item + " is not a valid Material!");
                        sender.sendMessage(ChatColor.RED + "Visit: https://helpch.at/docs/1.12.2/org/bukkit/Material.html for valid materials!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Use: " + ChatColor.GOLD + "/staffmanager server [add] [name] [displayname] [serverSlot] [row] [Item]");
                }
            } else if (type.equalsIgnoreCase("remove")) {
                if (args.length == 3) {
                    String server = args[2];
                    plugin.getConfig().set(section + "." + server, null);
                    plugin.saveConfig();
                } else {
                    sender.sendMessage(ChatColor.RED + "Use: " + ChatColor.GOLD + "/staffmanager server [remove] [name]");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "argument not valid, try with: " + ChatColor.GOLD + "/staffmanager server [add/remove]");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Try with: " + ChatColor.GOLD + "/staffmanager server [add/remove]");
        }
    }

    private void addRole(CommandSender sender, String[] args) {
        //staffmanager role [add/remove] [name] [displayname] [serverSlot] [row] [Item]
        if (args.length > 1) {
            String section = "Menu.roles";
            String type = args[1];
            if (type.equalsIgnoreCase("add")) {
                if (args.length == 7) {
                    String role = args[2];
                    String displayName = args[3];
                    int serverSlot = Integer.parseInt(args[4]);
                    int rowNumber = Integer.parseInt(args[5]);
                    String Item = args[6];
                    section += "." + role;


                    try {
                        Material item = Material.valueOf(Item);

                        plugin.getConfig().set(section + ".slot", serverSlot);
                        plugin.getConfig().set(section + ".rows", rowNumber);
                        plugin.getConfig().set(section + ".item", Item);
                        plugin.getConfig().set(section + ".name", displayName);

                        plugin.saveConfig();

                        sender.sendMessage(ChatColor.GREEN + "Role added correctly!");
                    } catch (Exception ex) {
                        sender.sendMessage(ChatColor.RED + Item + " is not a valid Material!");
                        sender.sendMessage(ChatColor.RED + "Visit: https://helpch.at/docs/1.12.2/org/bukkit/Material.html for valid materials!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Use: " + ChatColor.GOLD + "/staffmanager role [add] [name] [displayname] [serverSlot] [row] [Item]");
                }
            } else if (type.equalsIgnoreCase("remove")) {
                if (args.length == 3) {
                    String role = args[2];
                    plugin.getConfig().set(section + "." + role, null);
                    plugin.saveConfig();
                } else {
                    sender.sendMessage(ChatColor.RED + "Use: " + ChatColor.GOLD + "/staffmanager role [remove] [name]");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "argument not valid, try with: " + ChatColor.GOLD + "/staffmanager role [add/remove]");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Try with: " + ChatColor.GOLD + "/staffmanager role [add/remove]");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //Has user permissions?
        if (sender.hasPermission("staffmanager.admin")) {
            if (args.length >= 1) {
                String type = args[0];
                if (type.equalsIgnoreCase("player")) {
                    addPlayer(sender, args);
                } else if (type.equalsIgnoreCase("server")) {
                    addServer(sender, args);
                } else if (type.equalsIgnoreCase("role")) {
                    addRole(sender, args);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Try using " + ChatColor.GOLD + "/staffmanager [player/server/role] " + ChatColor.RED + "to have other informations");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have permissions to use this command!");
        }
        return false;
    }
}
