package dk.shadowerlort.minthygge.LinkCache;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dk.manaxi.unikpay.api.HttpsClient;
import dk.manaxi.unikpay.plugin.API.Internal;
import dk.manaxi.unikpay.plugin.Main;
import dk.shadowerlort.minthygge.MintHygge;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.concurrent.CompletableFuture;

public class UserLink {
    @Getter
    private Player player;
    private final float amount;
    private final String packageName;
    private final long startTime;
    private final long duration = 10 * 60 * 1000;
    private int taskId;

    public UserLink(Player player, float amount, String packageName) {
        this.startTime = System.currentTimeMillis();
        this.player = player;
        this.amount = amount;
        this.packageName = packageName;
    }

    public void startVerification() {
         Runnable runnable = () -> {
             if (System.currentTimeMillis() - startTime <= duration) {
                 if (!player.isOnline()) return;

                 isVerified().thenAccept(result -> {
                     if (!result) return;

                     Internal.sendPayRequest(player, packageName, amount);
                 });
                 return;
             }
             stopVerification();
         };

        long tenSeconds = 10 * 20;
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(MintHygge.getInstance(), runnable, tenSeconds, tenSeconds);
    }

    public void stopVerification() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public CompletableFuture<Boolean> isVerified() {
        String url = "https://unikpay.manaxi.dk/v1/verify/isVerified/" + player.getUniqueId();
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();

        Bukkit.broadcastMessage("Trying to verify");
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            String svar = HttpsClient.getRequest(url, Main.getAPIKEY());
            JsonObject response = (JsonObject)(new Gson()).fromJson(svar, JsonObject.class);
            boolean result = response.get("success").getAsBoolean();
            completableFuture.complete(result);
        });

        return completableFuture;
    }
}
