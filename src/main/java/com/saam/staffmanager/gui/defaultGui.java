package com.saam.staffmanager.gui;

import com.saam.staffmanager.StaffManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class defaultGui implements Listener {

    private final StaffManager plugin;
    private final Inventory defInv;
    private String invName;
    private String type;
    private String menu;

    public defaultGui(StaffManager plugin, Player player) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        type = "default";
        menu = "Menu." + type;
        invName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(menu + ".name"));

        int invSize = plugin.getConfig().getInt(menu + ".rows");
        defInv = Bukkit.createInventory(null, invSize * 9, invName);
        inizializeItems();
        openInventory(player);
    }

    public void inizializeItems() {
        for (String menus : plugin.getConfig().getConfigurationSection("Menu").getKeys(false)) {
            if (menus.equalsIgnoreCase("default")) continue;
            String material = plugin.getConfig().getString("Menu." + menus + ".item");
            String title = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Menu." + menus + ".name"));
            int Slot = plugin.getConfig().getInt("Menu." + menus + ".slot");

            Material roleMat = Material.getMaterial(material);

            defInv.setItem(Slot, createItem(roleMat, title));
        }
    }

    protected ItemStack createItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(HumanEntity ent) {
        ent.openInventory(defInv);
    }


    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != defInv) return;
        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        // verify current item is not null
        for (String menus : plugin.getConfig().getConfigurationSection("Menu").getKeys(false)) {
            String serverItem = plugin.getConfig().getString("Menu." + menus + ".item");
            String itemName = clickedItem.getType().name();

            if (itemName.equals(serverItem)) {
                final Player p = (Player) e.getWhoClicked();
                //Open Custom inventory
                if (menus.equalsIgnoreCase("servers")) {
                    new ServerGui(plugin, p);
                } else if (menus.equalsIgnoreCase("roles")) {
                    new RoleGui(plugin, p);
                }
                break;
            }
        }
    }


}
