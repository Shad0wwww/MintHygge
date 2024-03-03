package dk.shadowerlort.minthygge.LinkCache;

import org.bukkit.entity.Player;

import java.util.*;

public class UserLinkHandler {
    public static Map<UUID, UserLink> userLinkMap = new HashMap<>();

    public static void addUserLink(UserLink userLink) {
        userLinkMap.put(userLink.getPlayer().getUniqueId(), userLink);
    }

    public static void removeUserLink(Player player) {
        userLinkMap.remove(player.getUniqueId());
    }

    public static UserLink getUserLink(Player player) {
        return userLinkMap.get(player.getUniqueId());
    }

    public static boolean hasUserLink(Player player) {
        return userLinkMap.containsKey(player.getUniqueId());
    }
}
