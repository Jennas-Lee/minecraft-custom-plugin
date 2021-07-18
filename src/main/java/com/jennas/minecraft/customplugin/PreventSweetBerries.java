package com.jennas.minecraft.customplugin;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class PreventSweetBerries implements Listener {
    @EventHandler
    public void placeSweetBerries(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getBlockData().getMaterial() == Material.SWEET_BERRY_BUSH) {
            BlockData grass = Material.GRASS.createBlockData();
            block.setBlockData(grass);
        }
    }

    @EventHandler
    public void pickupSweetBerries(PlayerItemHeldEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItem(event.getNewSlot());

        if (item.getType() == Material.SWEET_BERRIES) {
            item.setType(Material.GRASS);
        }
    }

    @EventHandler
    public void moveSweetBerries(InventoryMoveItemEvent event) {
        ItemStack item = event.getItem();

        if (item.getType() == Material.SWEET_BERRIES) {
            item.setType(Material.GRASS);
        }
    }

    @EventHandler
    public void dropSweetBerries(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();

        if (item.getType() == Material.SWEET_BERRIES) {
            item.setType(Material.GRASS);
        }
    }

    @EventHandler
    public void pickupSweetBerries(EntityPickupItemEvent event) {
        ItemStack item = event.getItem().getItemStack();

        if (item.getType() == Material.SWEET_BERRIES) {
            item.setType(Material.GRASS);
        }
    }

    @EventHandler
    public void openInventoryWithSweetBerries(InventoryOpenEvent event) {
        for (ItemStack item : event.getInventory().getContents()) {
            if (item.getType() == Material.SWEET_BERRIES) {
                item.setType(Material.GRASS);
            }
        }
    }
}
