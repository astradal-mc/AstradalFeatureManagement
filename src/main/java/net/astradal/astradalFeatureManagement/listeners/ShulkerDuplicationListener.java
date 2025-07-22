package net.astradal.astradalFeatureManagement.listeners;

import io.papermc.paper.event.entity.ShulkerDuplicateEvent;
import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShulkerDuplicationListener implements Listener{

    //plugin instance constructor injection
    public ShulkerDuplicationListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }
    private final AstradalFeatureManagement pluginInstance;

    @EventHandler
    public void onShulkerDuplicationEvent(ShulkerDuplicateEvent event)
    {
        //ensure that shulker duplication is not enabled
        if(pluginInstance.getConfig().getBoolean("shulker-duplication.enabled")) {
            return;
        }

        //cancel it
        event.setCancelled(true);
        Location location = event.getParent().getLocation();
        //log to console
        pluginInstance.getLogger().info("Shulker duplication event cancelled at -
                + " X: " + location.getBlockX()
                + " Y: " + location.getBlockY()
                + " Z: " + location.getBlockZ()
                + " In world: " + location.getWorld()
        );
    }
}