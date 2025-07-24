package net.astradal.astradalFeatureManagement.placeholders.date;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;


public class LoreDate extends PlaceholderExpansion {

    private final LunarCalendar lunarCalendar;
    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        return lunarCalendar.getCurrentDateString();
    }

    public LoreDate(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
        String startDate = plugin.getConfig().getString("calendar.start-date", "2025-07-05");
        this.lunarCalendar = new LunarCalendar(startDate);
    }
    private final AstradalFeatureManagement pluginInstance;

    @Override
    @NotNull
    public String getIdentifier() {
        return "AFM-lore-date";
    }

    @Override
    @NotNull
    public String getAuthor() {
        return String.join(", ", pluginInstance.getPluginMeta().getAuthors());
    }

    @Override
    @NotNull
    public String getVersion() {
        return pluginInstance.getPluginMeta().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

}
