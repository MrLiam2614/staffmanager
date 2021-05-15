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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerGui implements Listener {

    private final StaffManager plugin;
    private Inventory ServerInv;
    private String invName;
    private String type;
    private String menu;
    private List<String> ignoreData;

    public ServerGui(StaffManager plugin, Player player) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;

        ignoreData = new ArrayList<String>();
        ignoreData.add("name");
        ignoreData.add("rows");
        ignoreData.add("slot");
        ignoreData.add("item");

        type = "servers";
        menu = "Menu." + type;
        inizializeItems();
        openInventory(player);
    }

    public void inizializeItems() {
        invName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(menu + ".name"));

        int invSize = plugin.getConfig().getInt(menu + ".rows");
        ServerInv = Bukkit.createInventory(null, invSize * 9, invName);

        for (String server : plugin.getConfig().getConfigurationSection(menu).getKeys(false)) {
            if (ignoreData.contains(server)) continue;
            String material = plugin.getConfig().getString(menu + "." + server + ".item");
            String title = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(menu + "." + server + ".name"));
            int Slot = plugin.getConfig().getInt(menu + "." + server + ".slot");

            Material roleMat = Material.getMaterial(material);

            ServerInv.setItem(Slot, createItem(roleMat, title));
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
        ent.openInventory(ServerInv);
    }


    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != ServerInv) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        // verify current item is not null
        for (String server : plugin.getConfig().getConfigurationSection(menu).getKeys(false)) {
            String serverItem = plugin.getConfig().getString(menu + "." + server + ".item");
            String itemName = clickedItem.getType().name();

            if (itemName.equals(serverItem)) {
                final Player p = (Player) e.getWhoClicked();
                //Open Custom inventory
                new customGui(plugin, type, server, p);
                break;
            }
        }
    }


}