package net.astradal.astradalFeatureManagement.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class AFMCommand implements Command<CommandSourceStack> {

    public static LiteralCommandNode<CommandSourceStack> create(AstradalFeatureManagement plugin) {
        return Commands.literal("afm")
            .executes(new AFMCommand())

            .then(Commands.literal("reload")
                .requires(sender -> sender.getSender().hasPermission("astradal.featureManagement.command.reload"))
                .executes(new ReloadCommand(plugin)))

            .build();
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        final TextComponent helpText = Component.text("Command: /reload", NamedTextColor.GOLD);


        context.getSource().getSender().sendMessage(helpText);

        return Command.SINGLE_SUCCESS;
    }
}
