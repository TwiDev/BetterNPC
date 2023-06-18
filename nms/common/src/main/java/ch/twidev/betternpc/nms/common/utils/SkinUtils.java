package ch.twidev.betternpc.nms.common.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

public class SkinUtils {

    public static String[] cloneSkin(UUID player) {

        try {
            URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + player.toString() + "?unsigned=false");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JsonObject object = new JsonParser().parse(reader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            return new String[]{object.get("value").getAsString(),  object.get("signature").getAsString()};

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
