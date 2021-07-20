package io.github.jennas_lee.customplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
import org.jetbrains.annotations.NotNull;

public class PreventSweetBerries implements CommandExecutor {
    public static boolean preventSweetBerriesStatus = true;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = sender instanceof Player ? (Player) sender : null;
        Server server = sender.getServer();

        if (player == null || player.isOp()) {
            if (args.length == 0) {
                sender.sendMessage(
                        ChatColor.RED + "If you want to use this command, you should type " +
                                ChatColor.GREEN + "/preventsweetberries enable " +
                                ChatColor.RED + "or " +
                                ChatColor.GREEN + "/preventsweetberries disable"
                );
            } else if (args[0].equals("enable")) {
                if (preventSweetBerriesStatus) {
                    sender.sendMessage(ChatColor.RED + "The server is already preventing Sweet Berries.");
                } else {
                    preventSweetBerriesStatus = true;
                    server.broadcast(Component.text(ChatColor.GREEN + "The server has started preventing Sweet Berries."));
                }
            } else if (args[0].equals("disable")) {
                if (preventSweetBerriesStatus) {
                    preventSweetBerriesStatus = false;
                    server.broadcast(Component.text(ChatColor.RED + "The server has stopped preventing Sweet Berries."));
                } else {
                    sender.sendMessage(ChatColor.RED + "The server is already stopped preventing Sweet Berries.");
                }
            } else {
                sender.sendMessage(
                        ChatColor.RED + "If you want to use this command, you should type " +
                                ChatColor.GREEN + "/preventsweetberries enable " +
                                ChatColor.RED + "or " +
                                ChatColor.GREEN + "/preventsweetberries disable"
                );
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have permissions to use this command.");
        }

        return true;
    }
}

class PreventSweetBerriesEnable implements Listener {

    @EventHandler
    public void placeSweetBerries(BlockPlaceEvent event) {
        try {
            if (PreventSweetBerries.preventSweetBerriesStatus) {
                Block block = event.getBlockPlaced();
                if (block.getBlockData().getMaterial() == Material.SWEET_BERRY_BUSH) {
                    BlockData grass = Material.GRASS.createBlockData();
                    block.setBlockData(grass);
                }
            }
        } catch (NullPointerException ignore) {
        }
    }

    @EventHandler
    public void pickupSweetBerries(PlayerItemHeldEvent event) {
        try {
            if (PreventSweetBerries.preventSweetBerriesStatus) {
                ItemStack item = event.getPlayer().getInventory().getItem(event.getNewSlot());

                if (item.getType() == Material.SWEET_BERRIES) {
                    item.setType(Material.GRASS);
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    @EventHandler
    public void moveSweetBerries(InventoryMoveItemEvent event) {
        try {
            if (PreventSweetBerries.preventSweetBerriesStatus) {
                ItemStack item = event.getItem();

                if (item.getType() == Material.SWEET_BERRIES) {
                    item.setType(Material.GRASS);
                }
            }
        } catch (NullPointerException ignore) {
        }
    }

    @EventHandler
    public void dropSweetBerries(PlayerDropItemEvent event) {
        try {
            if (PreventSweetBerries.preventSweetBerriesStatus) {
                ItemStack item = event.getItemDrop().getItemStack();

                if (item.getType() == Material.SWEET_BERRIES) {
                    item.setType(Material.GRASS);
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    @EventHandler
    public void pickupSweetBerries(EntityPickupItemEvent event) {
        try {
            if (PreventSweetBerries.preventSweetBerriesStatus) {
                ItemStack item = event.getItem().getItemStack();

                if (item.getType() == Material.SWEET_BERRIES) {
                    item.setType(Material.GRASS);
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    @EventHandler
    public void openInventoryWithSweetBerries(InventoryOpenEvent event) {
        try {
            if (PreventSweetBerries.preventSweetBerriesStatus) {
                for (ItemStack item : event.getInventory().getContents()) {
                    if (item.getType() == Material.SWEET_BERRIES) {
                        item.setType(Material.GRASS);
                    }
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}
