package net.astradal.astradalFeatureManagement.listeners;

import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class IronGolemSpawnListener implements Listener {

    private final AstradalFeatureManagement pluginInstance;

    // plugin instance constructor injection
    public IronGolemSpawnListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }

    @EventHandler
    public void onIronGolemSpawn(CreatureSpawnEvent event) {
        // 1. Fast fail if the spawning entity is not an Iron Golem
        if (event.getEntityType() != EntityType.IRON_GOLEM) {
            return;
        }

        // 2. Check the config cache; if natural spawns are allowed, do nothing
        if (pluginInstance.configCache.isNaturalIronGolemSpawns()) {
            return;
        }

        // 3. Handle the specific spawn reasons
        switch (event.getSpawnReason()) {
            case VILLAGE_DEFENSE:
            case VILLAGE_INVASION:
                // disable natural spawns
                event.setCancelled(true);

                Location location = event.getLocation();

                pluginInstance.getLogger().info("Natural Iron Golem spawn cancelled at: "
                    + location.getBlockX() + ", "
                    + location.getBlockY() + ", "
                    + location.getBlockZ() + "; "
                    + location.getWorld().getName()
                );
                break;
            default:
                // Allow artificial spawns (like players building them)
                break;
        }
    }
}