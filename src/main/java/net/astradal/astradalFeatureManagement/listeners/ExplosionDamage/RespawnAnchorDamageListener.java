package net.astradal.astradalFeatureManagement.listeners.ExplosionDamage;

import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.block.BlockType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

public class RespawnAnchorDamageListener implements Listener {

    private final AstradalFeatureManagement pluginInstance;

    // plugin instance constructor injection
    public RespawnAnchorDamageListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }

    @EventHandler
    public void onAnchorExplosion(EntityDamageByBlockEvent event) {
        // 1. Fast fail if there is no damager block state (e.g., generic block damage)
        if (event.getDamagerBlockState() == null) {
            return;
        }

        // 2. Fast fail if the block isn't a Respawn Anchor
        if (event.getDamagerBlockState().getType().asBlockType() != BlockType.RESPAWN_ANCHOR) {
            return;
        }

        // 3. Check the config cache; if respawn anchor damage is allowed, do nothing
        if (pluginInstance.configCache.isRespawnAnchorDamage()) {
            return;
        }

        // 4. Cancel the damage and log it
        event.setCancelled(true);
        pluginInstance.getLogger().info("Cancelled Respawn Anchor damage");
    }
}