package io.github.jennas_lee.customplugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class PlayerList implements Runnable {
    private boolean update = false;

    void update() {
        update = true;
    }

    @Override
    public void run() {
        if (update) {
            update = false;
            updatePlayerList();
        }
    }

    private void updatePlayerList() throws InvocationTargetException {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
        ArrayList list = new ArrayList<PlayerInfoData>();

        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            WrappedGameProfile profile = null;

            if (offlinePlayer instanceof Player) {
                profile = WrappedGameProfile.fromPlayer((Player) offlinePlayer);
            } else {
                profile = WrappedGameProfile.fromOfflinePlayer(offlinePlayer).withName(offlinePlayer.getName());
            }

            list.add(new PlayerInfoData(profile, 0, EnumWrappers.NativeGameMode.NOT_SET, WrappedChatComponent.fromText(offlinePlayer.getName())));
        }

        packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        packet.getPlayerInfoDataLists().write(0, list);

        ProtocolManager pm = ProtocolLibrary.getProtocolManager();

        for (Player player : Bukkit.getOnlinePlayers()) {
            pm.sendServerPacket(player, packet);
        }
    }
}
