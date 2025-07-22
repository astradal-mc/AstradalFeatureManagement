package net.astradal.astradalFeatureManagement.listeners;

import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class IronGolemSpawnListener implements Listener {
    //plugin instance constructor injection
    public IronGolemSpawnListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }
    private final AstradalFeatureManagement pluginInstance;

    @EventHandler
    public void onIronGolemSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() == org.bukkit.entity.EntityType.IRON_GOLEM) {
            switch (event.getSpawnReason()) {
                case VILLAGE_DEFENSE:
                case VILLAGE_INVASION:
                    // disable natural spawns
                    event.setCancelled(true);

                    Location location = event.getLocation();

                    pluginInstance.getLogger().info("Natural Iron Golem spawn cancelled at -"
                            + " X: " + location.getBlockX()
                            + " Y: " + location.getBlockY()
                            + " Z: " + location.getBlockZ()
                            + " In world: " + location.getWorld().getName()
                    );
                    break;
                default:
                    // Allow artificial spawns
                    break;
            }
        }
    }
}
