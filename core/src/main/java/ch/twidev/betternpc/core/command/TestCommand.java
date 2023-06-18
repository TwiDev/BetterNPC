package ch.twidev.betternpc.core.command;

import ch.twidev.betternpc.api.BetterNPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            BetterNPC.get().getNPCManager().create(EntityType.PLAYER, "Hello World").spawn(((Player) commandSender).getLocation());
        }

        return false;
    }
}
