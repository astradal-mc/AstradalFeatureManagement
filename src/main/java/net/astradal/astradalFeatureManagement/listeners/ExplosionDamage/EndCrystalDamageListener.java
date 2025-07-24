package net.astradal.astradalFeatureManagement.listeners.ExplosionDamage;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EndCrystalDamageListener implements Listener {
    @EventHandler
    public void onEndCrystalExplosion(EntityDamageByEntityEvent event) {
        if(event.getDamager().getType() == EntityType.END_CRYSTAL) {
            event.setCancelled(true);
        }
    }
}
