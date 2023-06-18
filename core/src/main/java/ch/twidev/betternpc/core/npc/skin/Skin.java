package ch.twidev.betternpc.core.npc.skin;

import ch.twidev.betternpc.api.BetterNPC;
import ch.twidev.betternpc.api.npc.NPC;
import ch.twidev.betternpc.nms.common.utils.SkinUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.ProfileLookupCallback;
import com.mojang.authlib.properties.Property;
import org.bukkit.entity.Entity;
import org.omg.CORBA.DATA_CONVERSION;

import javax.sql.rowset.serial.SerialStruct;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

public class Skin {

    private volatile boolean isValid = true;
    private volatile SkinProperty skinProperties;
    private final String skinName;

    public Skin(String playerName) {
        this.skinName = playerName;
    }

    public boolean isValid() {
        return isValid;
    }

    public SkinProperty getSkinProperties() {
        return skinProperties;
    }

    public String getSkinName() {
        return skinName;
    }

    public boolean apply(SkinnableEntity skinnableEntity) {
        return apply(skinnableEntity, false);
    }

    public boolean apply(SkinnableEntity skinnableEntity, boolean respawn) {
        fetchRequests(new SkinResult(skinName, skinResult -> {
            if(skinResult.getResult() == SkinFetchResultType.SUCCESS) {
                applySkin(skinnableEntity, skinResult.getProfile(), respawn);
            }
        }));

        return true;
    }

    public static void applySkin(SkinnableEntity skinnableEntity, SkinProperty skinProperties, boolean respawn) {
        BetterNPC.get().getNMSManager().getPacketsFactory().applySkin(skinnableEntity.getNPC(), skinProperties.getValue(), skinProperties.getSignature(), respawn);
    }

    private static void fetchRequests(SkinResult skinResult) {
        final GameProfileRepository repo = BetterNPC.get().getNMSManager().getPacketsFactory().getGameProfileRepository();

        repo.findProfilesByNames(new String[]{skinResult.getPlayerName()}, Agent.MINECRAFT, new ProfileLookupCallback() {
            @Override
            public void onProfileLookupSucceeded(GameProfile gameProfile) {
                SkinProperty skinProperty = getSkinProperty(gameProfile);

                if(skinProperty != null) {
                    skinResult.setResult(skinProperty, SkinFetchResultType.SUCCESS);
                }else{
                    skinResult.setResult(null, SkinFetchResultType.FAILED);
                }
            }

            @Override
            public void onProfileLookupFailed(GameProfile gameProfile, Exception e) {
                if(isProfileNotFound(e)) {
                    skinResult.setResult(null, SkinFetchResultType.NOT_FOUND);
                }else if(isTooManyRequests(e)) {
                    skinResult.setResult(null, SkinFetchResultType.REQUESTS_OVERFLOW);
                }else{
                    skinResult.setResult(null, SkinFetchResultType.FAILED);
                }
            }
        });
    }

    private static SkinProperty getSkinProperty(GameProfile gameProfile) {
        String[] data = SkinUtils.cloneSkin(gameProfile.getId());

        if(data == null) return null;
        return new SkinProperty(data[0], data[1]);
    }

    private static boolean isTooManyRequests(Throwable e) {
        String message = e.getMessage();
        String cause = e.getCause() != null ? e.getCause().getMessage() : null;

        return (message != null && message.contains("too many requests"))
                || (cause != null && cause.contains("too many requests"));
    }

    private static boolean isProfileNotFound(Exception e) {
        String message = e.getMessage();
        String cause = e.getCause() != null ? e.getCause().getMessage() : null;

        return (message != null && message.contains("did not find"))
                || (cause != null && cause.contains("did not find"));
    }


}
