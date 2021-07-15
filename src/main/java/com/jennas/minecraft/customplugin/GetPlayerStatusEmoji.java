package com.jennas.minecraft.customplugin;

public class GetPlayerStatusEmoji {
    GetEmoji emoji = new GetEmoji();

    public String getLevel(int level) {
        StringBuilder value = new StringBuilder();
        String[] levelStrArr = String.valueOf(level).split("");

        for (String levelId : levelStrArr) {
            value.append("<:mc_" + levelId + ":" + emoji.getMcEmoji(levelId) + ">");
        }

        return value.toString();
    }

    public String getHealth(double health) {
        int healthNum = (int) Math.ceil(health);
        StringBuilder value = new StringBuilder();

        for (int i = 0; i < healthNum / 2; i++) {
            value.append("<:full_heart:" + emoji.getHeartAndFoodEmoji(2, 0) + ">");
        }
        if (healthNum % 2 != 0) {
            value.append("<:half_heart:" + emoji.getHeartAndFoodEmoji(1, 0) + ">");
        }

        for (int i = 0; i < (20 - healthNum) / 2; i++) {
            value.append("<:empty_heart:" + emoji.getHeartAndFoodEmoji(0, 0) + ">");
        }

        return value.toString();
    }

    public String getFood(int foodLevel) {
        StringBuilder value = new StringBuilder();

        for (int i = 0; i < (20 - foodLevel) / 2; i++) {
            value.append("<:empty_hunger:" + emoji.getHeartAndFoodEmoji(0, 1) + ">");
        }
        if (foodLevel % 2 != 0) {
            value.append("<:half_hunger:" + emoji.getHeartAndFoodEmoji(1, 1) + ">");
        }
        for (int i = 0; i < foodLevel / 2; i++) {
            value.append("<:full_hunger:" + emoji.getHeartAndFoodEmoji(2, 1) + ">");
        }

        return value.toString();
    }
}
