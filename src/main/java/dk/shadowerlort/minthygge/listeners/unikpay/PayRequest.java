package dk.shadowerlort.minthygge.listeners.unikpay;

import dk.manaxi.unikpay.api.Config;
import dk.manaxi.unikpay.plugin.event.OnPayRequest;
import dk.shadowerlort.minthygge.LinkCache.UserLink;
import dk.shadowerlort.minthygge.LinkCache.UserLinkHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PayRequest implements Listener {

    @EventHandler
    public void onPayRequest(OnPayRequest event) {
        Player player = Bukkit.getPlayer(event.getPlayer().getUniqueId());
        if (player == null || !player.isOnline()) {
            return;
        }

        if (!event.getSuccess()) {
            if (event.getMessage().trim().equalsIgnoreCase(Config.IKKELINKET_MESSAGE)) {
                if (UserLinkHandler.hasUserLink(player)) {
                    return;
                }

                UserLink userLink = new UserLink(player, event.getAmount(), event.getName());
                UserLinkHandler.addUserLink(userLink);
                userLink.startVerification();

                player.sendMessage("§7");
                player.sendMessage("§8[ §6§lPAY §f§lSYSTEM §8]");
                player.sendMessage("§8▎ §fDu er ikke linket til Unikpay, derfor kan du ikke modtage dine emeralder. (/unikpay discord)");
                player.sendMessage("§8▎ §fDu har 10 minutter til at linke, ellers vil du ikke modtage dine emeralder.");
                player.sendMessage("§7");
                return;
            }

            player.sendMessage("§cDer skete en fejl under betalingen. Kontakt en staff.");
            return;
        }

        if (UserLinkHandler.hasUserLink(player)) {
            UserLink userLink = UserLinkHandler.getUserLink(player);
            userLink.stopVerification();
            UserLinkHandler.removeUserLink(player);
        }

        player.sendMessage("§7");
        player.sendMessage("§8[ §6§lPAY §f§lSYSTEM §8] §fDu vandt en konkurrence, og fik §2" + event.getAmount() + " §aEmeralder§f på Unikpay!");
    }
}
