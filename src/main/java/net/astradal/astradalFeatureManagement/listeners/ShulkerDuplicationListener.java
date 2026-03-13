package net.astradal.astradalFeatureManagement.listeners;

import io.papermc.paper.event.entity.ShulkerDuplicateEvent;
import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShulkerDuplicationListener implements Listener {

    private final AstradalFeatureManagement pluginInstance;

    // plugin instance constructor injection
    public ShulkerDuplicationListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }

    @EventHandler
    public void onShulkerDuplicationEvent(ShulkerDuplicateEvent event) {
        // ensure that shulker duplication is not enabled via the cache
        if (pluginInstance.configCache.isShulkerDuplication()) {
            return;
        }

        // cancel it
        event.setCancelled(true);
        Location location = event.getParent().getLocation();

        // log to console
        pluginInstance.getLogger().info("Shulker duplication event cancelled at: "
            + location.getBlockX() + ", "
            + location.getBlockY() + ", "
            + location.getBlockZ() + "; "
            + location.getWorld().getName()
        );
    }
}