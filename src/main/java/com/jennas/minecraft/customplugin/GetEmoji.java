package com.jennas.minecraft.customplugin;

import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class GetEmoji {
    Map<String, Object> configMap = null;

    GetEmoji() {
        try {
            this.configMap = new Yaml().load(new FileReader("./plugins/CustomPlugin/config.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getFullHeartEmoji() {
        try {
            return configMap.get("full_heart_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getHalfHeartEmoji() {
        try {
            return configMap.get("half_heart_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getEmptyHeartEmoji() {
        try {
            return configMap.get("empty_heart_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMcEmoji(String num) {
        try {
            return configMap.get("mc_" + num + "_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc0Emoji() {
        try {
            return configMap.get("mc_0_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc1Emoji() {
        try {
            return configMap.get("mc_1_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc2Emoji() {
        try {
            return configMap.get("mc_2_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc3Emoji() {
        try {
            return configMap.get("mc_3_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc4Emoji() {
        try {
            return configMap.get("mc_4_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc5Emoji() {
        try {
            return configMap.get("mc_5_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc6Emoji() {
        try {
            return configMap.get("mc_6_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc7Emoji() {
        try {
            return configMap.get("mc_7_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc8Emoji() {
        try {
            return configMap.get("mc_8_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    public String getMc9Emoji() {
        try {
            return configMap.get("mc_9_emoji_id").toString();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

}
