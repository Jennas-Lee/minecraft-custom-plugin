package io.github.jennas_lee.customplugin;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Emote {
    private Player player = null;

    Emote(Player player) {
        this.player = player;
    }

    void emoteBy(String message) {
        if (message.equals("angry") || message.equals("화남")) {
            player.getWorld().spawnParticle(
                    Particle.VILLAGER_ANGRY,
                    player.getLocation().getX(),
                    player.getLocation().getY() + 2.0,
                    player.getLocation().getZ(),
                    0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    null,
                    true
            );
        } else if(message.equals("heart") || message.equals("하트")) {
            player.getWorld().spawnParticle(
                    Particle.HEART,
                    player.getLocation().getX(),
                    player.getLocation().getY() + 2.0,
                    player.getLocation().getZ(),
                    0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    null,
                    true
            );
        } else if(message.equals("ㅗ") || message.equals("망할")) {
            player.getWorld().spawnParticle(
                    Particle.HEART,
                    player.getLocation().getX(),
                    player.getLocation().getY() + 2.0,
                    player.getLocation().getZ(),
                    0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    null,
                    true
            );
        } else if(message.equals("damage") || message.equals("상처")) {
            player.getWorld().spawnParticle(
                    Particle.DAMAGE_INDICATOR,
                    player.getLocation().getX(),
                    player.getLocation().getY() + 2.0,
                    player.getLocation().getZ(),
                    0,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    null,
                    true
            );
        } else if(message.equals("critical") || message.equals("많은상처")) {
            player.getWorld().spawnParticle(
                    Particle.DAMAGE_INDICATOR,
                    player.getLocation().getX(),
                    player.getLocation().getY() + 2.0,
                    player.getLocation().getZ(),
                    16,
                    0.5,
                    0.5,
                    0.5,
                    0.0,
                    null,
                    true
            );
        } else if(message.equals("spit") || message.equals("퉤")) {
            player.getWorld().spawnParticle(
                    Particle.SPIT,
                    player.getLocation().getX(),
                    player.getLocation().getY() + 1.62,
                    player.getLocation().getZ(),
                    0,
                    player.getLocation().getDirection().getX(),
                    player.getLocation().getDirection().getX(),
                    player.getLocation().getDirection().getX(),
                    1.0,
                    null,
                    true
            );
            player.getWorld().playSound(
                    player.getLocation(),
                    Sound.ENTITY_LLAMA_SPIT,
                    1.0F,
                    1.0F
            );
        }
    }
}
