package net.astradal.astradalFeatureManagement.listeners.ExplosionDamage;

import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.block.BlockType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;


public class RespawnAnchorDamageListener implements Listener {
    //plugin instance constructor injection
    public RespawnAnchorDamageListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }
    private final AstradalFeatureManagement pluginInstance;

    @EventHandler
    public void onAnchorExplosion(EntityDamageByBlockEvent event) {
        if (((event.getDamagerBlockState() != null ? event.getDamagerBlockState().getBlock().getType().asBlockType() : null) == BlockType.RESPAWN_ANCHOR)) {
            event.setCancelled(true);
            pluginInstance.getLogger().info("Event Fired");
        }

    }
}