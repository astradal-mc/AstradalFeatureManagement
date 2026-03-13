package net.astradal.astradalFeatureManagement;

public class ConfigCache {
    private final AstradalFeatureManagement pluginInstance;

    private boolean villagerTrading;
    private String denyMessage;
    private String bypassMessage;

    private boolean shulkerDuplication;

    private boolean naturalIronGolemSpawns;

    private boolean respawnAnchorDamage;

    private boolean endCrystalDamage;

    private boolean evokerTotemDrop;

    public ConfigCache(AstradalFeatureManagement pluginInstance) {
        this.pluginInstance = pluginInstance;
        updateCache();
    }

    public void updateCache() {
        villagerTrading = pluginInstance.getConfig().getBoolean("balancing-features.villager-trades.enabled");
        denyMessage = pluginInstance.getConfig().getString("balancing-features.villager-trades.deny-message");
        bypassMessage = pluginInstance.getConfig().getString("balancing-features.villager-trades.bypass-message");

        shulkerDuplication = pluginInstance.getConfig().getBoolean("balancing-features.shulker-duplication");

        naturalIronGolemSpawns = pluginInstance.getConfig().getBoolean("balancing-features.natural-iron-golem-spawns");

        respawnAnchorDamage = pluginInstance.getConfig().getBoolean("balancing-features.respawn-anchor-damage");

        endCrystalDamage = pluginInstance.getConfig().getBoolean("balancing-features.end-crystal-damage");

        evokerTotemDrop = pluginInstance.getConfig().getBoolean("balancing-features.evoker-totem-drop");
    }

    public boolean isVillagerTrading() {
        return villagerTrading;
    }

    public String getDenyMessage() {
        return denyMessage;
    }

    public String getBypassMessage() {
        return bypassMessage;
    }

    public boolean isShulkerDuplication() {
        return shulkerDuplication;
    }

    public boolean isNaturalIronGolemSpawns() {
        return naturalIronGolemSpawns;
    }

    public boolean isRespawnAnchorDamage() {
        return respawnAnchorDamage;
    }

    public boolean isEndCrystalDamage() {
        return endCrystalDamage;
    }

    public boolean isEvokerTotemDrop() {
        return evokerTotemDrop;
    }
}
