package com.jennas.minecraft.customplugin;

public class GetPlayerStatusEmoji {
    GetEmoji emoji = new GetEmoji();

    //<summary>테스트 중인 코드입니다.</summary>
    public String getLevel(int level) {
        StringBuilder value = new StringBuilder();
        String[] levelStrArr = String.valueOf(level).split("");

        for (String levelId: levelStrArr) {
            value.append("<:mc_" + levelId + ":" + emoji.getMcEmoji(levelId) + ">");
        }

        return value.toString();
    }

    public String getHealth(double health) {
        int healthNum = (int) Math.ceil(health);
        StringBuilder value = new StringBuilder();

        for (int i = 0; i < healthNum / 2; i++) {
            value.append("<:full:" + emoji.getFullHeartEmoji() + ">");
        }
        if (healthNum % 2 != 0) {
            value.append("<:half:" + emoji.getHalfHeartEmoji() + ">");
        }

        for (int i = 0; i < (20 - healthNum) / 2; i++) {
            value.append("<:empty:" + emoji.getEmptyHeartEmoji() + ">");
        }

        return value.toString();
    }
}
