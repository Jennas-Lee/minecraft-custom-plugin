package io.github.jennas_lee.customplugin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

import static org.bukkit.Bukkit.getOfflinePlayers;

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
                String configFileTxt = "full_heart_emoji_id: \n" +
                        "half_heart_emoji_id: \n" +
                        "empty_heart_emoji_id: \n" +
                        "mc_0_emoji_id: \n" +
                        "mc_1_emoji_id: \n" +
                        "mc_2_emoji_id: \n" +
                        "mc_3_emoji_id: \n" +
                        "mc_4_emoji_id: \n" +
                        "mc_5_emoji_id: \n" +
                        "mc_6_emoji_id: \n" +
                        "mc_7_emoji_id: \n" +
                        "mc_8_emoji_id: \n" +
                        "mc_9_emoji_id: \n" +
                        "full_hunger_emoji_id: \n" +
                        "half_hunger_emoji_id: \n" +
                        "empty_hunger_emoji_id: \n";

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

        getServer().getPluginManager().registerEvents(new PreventSweetBerriesEnable(), this);
        getCommand("preventsweetberries").setExecutor(new PreventSweetBerries());
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(customPluginMessage(ChatColor.RED, ActiveWhether.DEACTIVE_STATE));
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(customPluginMessage(ChatColor.RED, ActiveWhether.DEACTIVE_STATE));
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
            JDA jda = JDABuilder.createDefault("ODY4NDIzNzA3OTI2ODU5ODI2.YPvcmg.XUiU3abd0E6uwvb7MeMn0nkS3zE")
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
        String[] query = event.getMessage().getContentRaw().split(" ");
        EmbedBuilder eb = new EmbedBuilder();
        GetPlayerStatusEmoji emoji = new GetPlayerStatusEmoji();

        switch (query[0]) {
            case "&list":
                try {

                    boolean playerCountTemp = false;

                    eb.setTitle("현재 접속중인 플레이어는 다음과 같습니다.", null);

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        eb.addField("Name", player.getName(), false);
                        eb.addField("Level", emoji.getLevel(player.getLevel()), true);
                        eb.addField("Health", emoji.getHealth(player.getHealth()), true);
                        eb.addField("Food", emoji.getFood(player.getFoodLevel()), true);
                        playerCountTemp = true;
                    }

                    if (!playerCountTemp) {
                        eb.setDescription("이런... 아무도 존재하지 않습니다.");
                    }

                } catch (NullPointerException e) {
                    eb.setTitle("플러그인 설정이 완료되지 않았습니다.");
                    eb.setDescription(
                            "plugins/CustomPlugin 폴더에 있는 config.yml 파일을 설정해야합니다.\n" +
                                    "서버에 추가한 이모지의 ID를 입력 후, `/reload confirm` 명령어를 사용해\n" +
                                    "플러그인을 재시작하세요."
                    );
                }

                event.getChannel().sendMessage(eb.build()).queue();
                break;

            case "&status":
                String nowPlayingPlayer = "";
                String maxPlayingPlayer = "";
                long nowTime;
                int nowPlayingPlayerCounter = 0;

                for (Player player : Bukkit.getOnlinePlayers()) {
                    nowPlayingPlayerCounter += 1;
                }

                nowPlayingPlayer = Integer.toString(nowPlayingPlayerCounter);
                maxPlayingPlayer = Integer.toString(Bukkit.getMaxPlayers());
                nowTime = Bukkit.getWorld("world").getTime();

                eb.setTitle("서버 상태는 다음과 같습니다.");
                eb.addField("Now Playing", nowPlayingPlayer + "/" + maxPlayingPlayer, true);
                eb.addField("Overworld Time", getTimeStr(nowTime), true);

                event.getChannel().sendMessage(eb.build()).queue();
                break;

            case "&advancement":
            case "&ad":
                eb.setTitle("발전과제 사용 테스트");

                if (query.length == 1) {
                    eb.setTitle("검색된 사용자가 없습니다.");
                    eb.setDescription("사용자가 입력되지 않았습니다. 사용자를 입력해주세요.");
                } else if (query.length > 2) {
                    eb.setTitle("검색된 사용자가 없습니다.");
                    eb.setDescription("명령어가 잘못 입력되었습니다. 사용자를 다시 입력해주세요");
                } else {
                    Player player = Bukkit.getPlayerExact(query[1]);

                    if (player == null) {
                        OfflinePlayer offlinePlayer[] = getOfflinePlayers();

                        for (OfflinePlayer offline : offlinePlayer) {
                            if (offline.getName().equals(query[1])) {
                                eb.setTitle(query[1] + "님의 발전과제 현황은 다음과 같습니다.");
                                eb.setDescription(query[1] + "님은 현재 온라인 상태가 아닙니다.");
                                break;
                            }
                        }

                        eb.setTitle("검색된 사용자가 없습니다.");
                        eb.setDescription(query[1] + "(이)라는 사용자를 찾을 수 없습니다.");
                    } else {
                        eb.setTitle(query[1] + "님의 발전과제 현황은 다음과 같습니다.");

                        Iterator<Advancement> iterator = Bukkit.getServer().advancementIterator();
                        String result = "";
                        while (iterator.hasNext()) {
                            Advancement advancement = iterator.next();

                            if (!advancement.getKey().getKey().split("/")[0].equals("recipes") &&
                                    player.getAdvancementProgress(advancement).isDone()) {
                                result += advancement.getKey().getKey() + "\n";
                            }
                        }
//                        eb.setDescription(result);
                        Bukkit.getServer().broadcast(Component.text(result));
                    }
                }

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

    public String getHelpMessageListStr() {
        StringBuilder description = new StringBuilder();

        String[] desArr = {
                "`&list` : 현재 접속중인 서버의 플레이어를 조회합니다.",
                "`&status` : 서버의 현재 상태를 조회합니다.",
                "`&advancement 사용자` OR `&ad 사용자` : 서버의 현재 상태를 조회합니다.",
                "`&help` : 도움말을 전송합니다.",
        };

        for (String str : desArr) {
            description.append(str + "\n\n");
        }

        return description.toString();
    }

    public String getTimeStr(long time) {
        int second, H, M, S;

        time %= 24000;
        H = ((((int) time + 6000) / 1000)) % 24;
        time %= 1000;
        second = (int) Math.round(time / 0.277777);
        M = second / 60;
        S = second % 60;

        return String.format("%02d:%02d:%02d", H, M, S);
    }
}
