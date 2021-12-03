package io.github.jennas_lee.customplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        for (World world : Bukkit.getWorlds()) {
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, true);
            world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false);
            world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);
            world.setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
        }

        World overworld = Bukkit.getWorld("world");
        World nether = Bukkit.getWorld("world_nether");
        World end = Bukkit.getWorld("world_the_end");

        if (overworld != null) {
            overworld.getWorldBorder().setCenter(new Location(overworld, 0.0, 0.0, 0.0));
            overworld.getWorldBorder().setSize(16384.0);
            overworld.setSpawnLocation(overworld.getHighestBlockAt(0, 0).getLocation());
        }
        if (nether != null) {
            nether.getWorldBorder().setCenter(new Location(nether, 0.0, 0.0, 0.0));
            nether.getWorldBorder().setSize(2048.0);
        }

//        getServer().getPluginManager().registerEvents();
        getServer().getScheduler().runTaskTimer(this, new PlayerList(), 0L, 1);
        getServer().getScheduler().runTaskTimer(this, new Restarter(), 20L * 60L, 20L * 60L);
    }
}

class Restarter implements Runnable {
    private long time = System.currentTimeMillis();

    @Override
    public void run() {
        long elapsedTime = System.currentTimeMillis() - time;
        long restartTime = 1000L * 60L * 60L * 2L;

        if (elapsedTime >= restartTime) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("서버가 재시작됩니다.");
            }
            Bukkit.shutdown();
        } else if (elapsedTime >= restartTime - 60000L) {
            Bukkit.broadcast(Component.text("1분 뒤 서버가 재시작됩니다."));
        }
    }
}