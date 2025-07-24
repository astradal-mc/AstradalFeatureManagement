package net.astradal.astradalFeatureManagement.listeners.ExplosionDamage;

import org.bukkit.block.BlockType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;


public class RespawnAnchorDamageListener implements Listener {
    @EventHandler
    public void onAnchorExplosion(EntityDamageByBlockEvent event) {
        if ((event.getDamager() != null ? event.getDamager().getType().asBlockType() : null) == BlockType.RESPAWN_ANCHOR) {
            event.setCancelled(true);
        }

    }
}