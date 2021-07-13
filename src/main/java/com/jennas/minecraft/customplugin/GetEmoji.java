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
        String id = configMap.get("full_heart_emoji_id").toString();
        return id;
    }
}
