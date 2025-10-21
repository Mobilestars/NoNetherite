package de.scholle.netheriteBlocker;

import de.scholle.netheriteBlocker.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.entity.Entity;

public class NetheriteBlocker implements Listener {

    private final Main plugin;

    public NetheriteBlocker(Main plugin) { this.plugin = plugin; }

    private boolean isNetheriteArmor(Material m) {
        return m == Material.NETHERITE_HELMET || m == Material.NETHERITE_CHESTPLATE ||
                m == Material.NETHERITE_LEGGINGS || m == Material.NETHERITE_BOOTS;
    }
    private boolean isNetheriteMaterial(Material m) {
        return m == Material.NETHERITE_SWORD || m == Material.NETHERITE_AXE ||
                m == Material.NETHERITE_PICKAXE || m == Material.NETHERITE_SHOVEL ||
                m == Material.NETHERITE_HOE || isNetheriteArmor(m);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!plugin.getConfig().getBoolean("noNetherite", true)) return;
        if (!(e.getWhoClicked() instanceof Player p)) return;

        ItemStack cursor = e.getCursor(), current = e.getCurrentItem();
        if ((cursor != null && isNetheriteArmor(cursor.getType())) ||
                (current != null && isNetheriteArmor(current.getType()))) {
            e.setCancelled(true);
            p.sendMessage("§cNetherite-Rüstung hier nicht erlaubt!");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!plugin.getConfig().getBoolean("noNetherite", true)) return;
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if (item != null && isNetheriteArmor(item.getType()) && e.getHand() == EquipmentSlot.HAND) {
            e.setCancelled(true);
            p.sendMessage("§cNetherite-Rüstung darf nicht angelegt werden!");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!plugin.getConfig().getBoolean("noNetherite", true)) return;
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item != null && isNetheriteMaterial(item.getType())) {
            e.setCancelled(true);
            p.sendMessage("§cNetherite-Werkzeug nicht erlaubt!");
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!plugin.getConfig().getBoolean("noNetherite", true)) return;
        if (!(e.getDamager() instanceof Player p)) return;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item != null && isNetheriteMaterial(item.getType())) {
            e.setCancelled(true);
            p.sendMessage("§cNetherite-Waffe nicht erlaubt!");
        }
    }

    @EventHandler
    public void onBlockDispenseArmor(BlockDispenseArmorEvent e) {
        if (!plugin.getConfig().getBoolean("noNetherite", true)) return;
        Entity target = e.getTargetEntity();
        if (target instanceof Player p) {
            ItemStack armor = e.getItem();
            if (armor != null && isNetheriteArmor(armor.getType())) {
                e.setCancelled(true);
                p.sendMessage("§cNetherite-Rüstung darf nicht vom Dispenser getragen werden!");
            }
        }
    }
}
