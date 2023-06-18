package ch.twidev.betternpc.core.npc.skin;

import ch.twidev.betternpc.api.BetterNPC;
import com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;

public class SkinResult {

    private SkinFetchResult handler;
    private volatile SkinFetchResultType result = SkinFetchResultType.PENDING;
    private final String playerName;
    private SkinProperty profile;

    public SkinResult(String playerName, SkinFetchResult skinFetchResult) {
        this.playerName = playerName;

        if(skinFetchResult != null)
            this.handler = skinFetchResult;
    }

    public void setResult(SkinProperty gameProfile, SkinFetchResultType result) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(BetterNPC.get().getPlugin(), () -> {
            SkinResult.this.profile = gameProfile;
            SkinResult.this.result = result;

            this.handler.onResult(SkinResult.this);
        });
    }

    public SkinFetchResultType getResult() {
        return result;
    }

    public String getPlayerName() {
        return playerName;
    }

    public SkinProperty getProfile() {
        return profile;
    }

    public SkinFetchResult getHandler() {
        return handler;
    }
}
