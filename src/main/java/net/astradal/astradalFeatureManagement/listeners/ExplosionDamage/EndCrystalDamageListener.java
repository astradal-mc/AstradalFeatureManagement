package net.astradal.astradalFeatureManagement.listeners.ExplosionDamage;

import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EndCrystalDamageListener implements Listener {

    //plugin instance constructor injection
    public EndCrystalDamageListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }
    private final AstradalFeatureManagement pluginInstance;

    @EventHandler
    public void onEndCrystalExplosion(EntityDamageByEntityEvent event) {

        if(event.getDamager().getType().equals(EntityType.END_CRYSTAL)) {
            event.setCancelled(true);

            pluginInstance.getLogger().info("Cancelled End Crystal damage");
        }
    }
}
