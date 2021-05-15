package com.saam.staffmanager.gui;

import com.saam.staffmanager.StaffManager;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class customGui implements Listener {

    //Inventory Settings
    private final String invType;
    private final String invString;

    //Other Settings
    private final StaffManager plugin;
    private Inventory customInv;
    private String invName;
    private int invSize;

    public customGui(StaffManager plugin, String type, String content, Player player) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;

        invType = "Menu." + type;
        invString = invType + "." + content;
        inizializeItems(type, content, player);
    }

    private void inizializeItems(String type, String content, Player player) {
        invName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(invString + ".name"));
        invSize = plugin.getConfig().getInt(invString + ".rows");

        customInv = Bukkit.createInventory(null, invSize * 9, invName);

        for (String user : plugin.getConfig().getConfigurationSection("Staff").getKeys(false)) {
            String uRole = plugin.getConfig().getString("Staff." + user + ".role");
            String uServer = plugin.getConfig().getString("Staff." + user + ".server");

            //Is server or Role?
            if (type.equalsIgnoreCase("servers")) {
                int uServerSlot = plugin.getConfig().getInt("Staff." + user + ".serverSlot");
                if (uServer.equalsIgnoreCase(content) || uServer.equalsIgnoreCase("global")) {
                    //Add user to serverMenu
                    customInv.setItem(uServerSlot, createItem(Material.SKULL_ITEM, user, "Role: " + uRole, "Server: " + uServer));
                }
            } else if (type.equalsIgnoreCase("roles")) {
                int uRoleSlot = plugin.getConfig().getInt("Staff." + user + ".roleSlot");
                if (uRole.equalsIgnoreCase(content)) {
                    customInv.setItem(uRoleSlot, createItem(Material.SKULL_ITEM, user, "Role: " + uRole, "Server: " + uServer));
                }
            }
        }

        openInventory(player);
    }

    protected ItemStack createItem(final Material material, final String name, final String... lore) {
        final ItemStack skull = new ItemStack(material, 1, (short) SkullType.PLAYER.ordinal());
        final SkullMeta meta = (SkullMeta) skull.getItemMeta();

        // Set the name of the item
        OfflinePlayer player = Bukkit.getOfflinePlayer(name);
        meta.setOwningPlayer(player);
        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        skull.setItemMeta(meta);

        return skull;
    }

    public void openInventory(HumanEntity ent) {
        ent.openInventory(customInv);
    }


    // Cancel Item click event
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != customInv) return;
        e.setCancelled(true);
    }


}