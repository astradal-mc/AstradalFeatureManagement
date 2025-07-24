package net.astradal.astradalFeatureManagement.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;

public class AFMCommand {
    public static LiteralCommandNode<CommandSourceStack> create(AstradalFeatureManagement plugin) {
        return Commands.literal("afm")
            .then(ReloadCommand.get(plugin))
            .build();
    }
}
