package com.jennas.minecraft.customplugin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileWriter;

public final class CustomPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        File configFolder = new File("./plugins/CustomPlugin");
        File configFile = new File("./plugins/CustomPlugin/config.yml");

        if (!configFolder.exists()) {
            try {
                configFolder.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.RED + "플러그인 활성화 중 오류가 발생하였습니다. 제작자에게 문의해주세요.");
            }
        }

        if (!configFile.exists()) {
            try {
                FileWriter fw = new FileWriter(configFile, false);
                String configFileTxt = "full_heart_emoji_id: \nhalf_heart_emoji_id: \nempty_heart_emoji_id: \n";
                fw.write(configFileTxt);
                fw.flush();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.RED + "플러그인 활성화 중 오류가 발생하였습니다. 제작자에게 문의해주세요.");
            }
        }

        Bukkit.getServer().getConsoleSender().sendMessage(customPluginMessage(ChatColor.AQUA, ActiveWhether.ACTIVE_STATE));
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(customPluginMessage(ChatColor.AQUA, ActiveWhether.ACTIVE_STATE));
        }

        Bot bot = new Bot();
        bot.bulidBot();
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(customPluginMessage(ChatColor.RED, ActiveWhether.DEACTIVE_STATE));
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(customPluginMessage(ChatColor.RED, ActiveWhether.ACTIVE_STATE));
        }
    }

    private String customPluginMessage(ChatColor color, ActiveWhether whether) {
        String msg = "";
        if (whether == whether.ACTIVE_STATE) {
            msg = " 커스텀 플러그인이 활성화 되었습니다.";
        } else if (whether == whether.DEACTIVE_STATE) {
            msg = " 커스텀 플러그인이 비활성화되었습니다.";
        }

        return color + msg;
    }
}

enum ActiveWhether {
    ACTIVE_STATE,
    DEACTIVE_STATE,
}

class Bot extends ListenerAdapter {

    public static JDA jda;

    public void bulidBot() {
        try {
            JDA jda = JDABuilder.createDefault("ODYyMzEyNTkwMjY3NjQ1OTkz.YOWhLg.7r_5rADXNbBu3E2jFkECOzMKkKc")
                    .addEventListeners(new Bot())
                    .build();
            jda.awaitReady();
            jda.getPresence().setActivity(Activity.playing("&help | 현재 서버 온라인"));

        } catch (LoginException le) {
            le.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String query = event.getMessage().getContentRaw();
        EmbedBuilder eb = new EmbedBuilder();

        switch (query) {
            case "&list":
                GetEmoji getEmoji = new GetEmoji();
                String test = getEmoji.getFullHeartEmoji();


//                String userListStr = getOnlineUserListStr();

                eb.setTitle("현재 접속중인 플레이어는 다음과 같습니다.", null);
//                eb.setDescription(userListStr);
//                String test = "<:full:864396944632709120><:half:864396655499673600><:empty:864396165607849995>";
                eb.setDescription(test);

                event.getChannel().sendMessage(eb.build()).queue();
                break;
            case "&help":
                String description = getHelpMessageListStr();

                eb.setTitle("사용 가능한 명령어는 다음과 같습니다.");
                eb.setDescription(description);

                event.getMessage().getMember().getUser().openPrivateChannel()
                        .flatMap(channel -> channel.sendMessage(eb.build()))
                        .queue();
                break;
        }
    }

    public String getOnlineUserListStr() {
        StringBuilder builder = new StringBuilder(); // 메모리 관리 효율성

        for (Player player : Bukkit.getOnlinePlayers()) {
            builder.append(player.getName() + "\n\n");
        }

        if (builder.toString().isEmpty()) {
            builder.append("이런... 아무도 존재하지 않습니다.");
        }

        return builder.toString();
    }

    public String getHelpMessageListStr() {
        StringBuilder description = new StringBuilder();

        String[] desArr = {
                "`&list` : 현재 접속중인 서버의 플레이어를 조회합니다.",
                "`&help` : 도움말을 전송합니다.",
        };

        for (String str : desArr) {
            description.append(str + "\n\n");
        }

        return description.toString();
    }
}
