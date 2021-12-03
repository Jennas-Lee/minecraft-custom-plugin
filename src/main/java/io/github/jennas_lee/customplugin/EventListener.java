package io.github.jennas_lee.customplugin;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.TabCompleteEvent;

public class EventListener implements Listener {
    @EventHandler
    void onPlayerLogin(PlayerLoginEvent event) {
        if(event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
            event.allow();
        }
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(null);
        new PlayerList().update();

        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()) {
            player.teleport(event.getPlayer().getWorld().getSpawnLocation());
        }

        player.setCompassTarget(Bukkit.getWorld("world").getSpawnLocation());
    }

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent event) {
        event.quitMessage(null);

        new PlayerList().update();
    }

    @EventHandler(ignoreCancelled = true)
    void onTabComplete(TabCompleteEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        event.setCancelled(true);

        String message = event.getMessage().replaceFirst("^/", "");
        new Emote(event.getPlayer()).emoteBy(message);
    }
}
