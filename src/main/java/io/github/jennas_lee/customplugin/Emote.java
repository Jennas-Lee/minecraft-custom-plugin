package io.github.jennas_lee.customplugin;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.TreeMap;

public class Emote {
    private TreeMap emotes = new TreeMap<String, Location>(String.CASE_INSENSITIVE_ORDER);

    Emote(Player player) {
        register("angry", "화남", player.spawnParticle(
                Particle.VILLAGER_ANGRY,
                player.getLocation().getX(),
                player.getLocation().getY() + 2.0,
                player.getLocation().getZ(),
                0,
                0.0,
                0.0,
                0.0,
                null
        ));
    }

    private void register(String name, String subname, Location emote) {
        emotes[name] = emote;
        emotes[subname] = emote;
    }

    Location emoteBy(String name) {
        return emotes[name];
    }
}
