package net.astradal.astradalFeatureManagement.listeners.ExplosionDamage;

import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EndCrystalDamageListener implements Listener {

    private final AstradalFeatureManagement pluginInstance;

    // plugin instance constructor injection
    public EndCrystalDamageListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }

    @EventHandler
    public void onEndCrystalExplosion(EntityDamageByEntityEvent event) {
        // 1. Fast fail if the damager isn't an End Crystal
        if (event.getDamager().getType() != EntityType.END_CRYSTAL) {
            return;
        }

        // 2. Check the config cache; if end crystal damage is enabled, do nothing
        if (pluginInstance.configCache.isEndCrystalDamage()) {
            return;
        }

        // 3. Cancel the damage and log it
        event.setCancelled(true);
        pluginInstance.getLogger().info("Cancelled End Crystal damage");
    }
}