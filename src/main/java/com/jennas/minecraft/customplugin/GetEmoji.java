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

    public String getHeartAndFoodEmoji(int amount, int type) {
        try {
            String value = "";
            String typeStr = type == 0 ? "heart" : "hunger";
            switch (amount) {
                case 0:
                    value = configMap.get("empty_" + typeStr + "_emoji_id").toString();
                    break;
                case 1:
                    value = configMap.get("half_" + typeStr + "_emoji_id").toString();
                    break;
                case 2:
                    value = configMap.get("full_" + typeStr + "_emoji_id").toString();
                    break;
            }

            return value;
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
}
