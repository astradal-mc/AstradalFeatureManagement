package net.astradal.astradalFeatureManagement.listeners;

import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class VillagerInteractListener implements Listener {

    private final AstradalFeatureManagement pluginInstance;

    // Plugin instance constructor injection
    public VillagerInteractListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }

    // Helper method to parse the cached string into an Adventure Component
    private Component parseMessage(String message) {
        if (message == null || message.isEmpty()) return Component.empty();
        return LegacyComponentSerializer.legacyAmpersand().deserialize(message);
    }

    @EventHandler
    public void onPlayerVillagerInteract(PlayerInteractEntityEvent event) {
        // 1. Make sure the interacted entity is a villager first
        if (event.getRightClicked().getType() != EntityType.VILLAGER) {
            return;
        }

        // 2. Make sure villager trades are not enabled via cache
        // Assuming your main class has: public ConfigCache configCache;
        if (pluginInstance.configCache.isVillagerTrading()) {
            return;
        }

        // 3. Make sure the villager isn't unemployed
        Villager villager = (Villager) event.getRightClicked();
        if (villager.getProfession() == Villager.Profession.NONE) {
            return;
        }

        // 4. Handle permissions and messaging
        Player player = event.getPlayer();
        if (player.hasPermission("astradal.featureManagement.villagerTrading.bypass")) {
            // Tell them they're bypassing the restrictions using the cached message
            player.sendMessage(parseMessage(pluginInstance.configCache.getBypassMessage()));

            // Log to console
            pluginInstance.getLogger().info(player.getName() + " is bypassing villager trade restrictions.");
            return;
        }

        // 5. Stop the player from interacting with the villager
        event.setCancelled(true);

        // Tell them they can't trade with villagers using the cached message
        player.sendMessage(parseMessage(pluginInstance.configCache.getDenyMessage()));

        // Log to console
        pluginInstance.getLogger().info(player.getName() + " has been prevented from trading.");
    }
}