/*
 * Author: Dabo Ross
 * Website: www.daboross.net
 * Email: daboross@daboross.net
 */
package net.daboross.bungeedev.uberchat.commandexecutors;

import net.daboross.bungeedev.uberchat.UberChat;
import net.daboross.bungeedev.uberchat.UberChatStatics;
import net.daboross.bungeedev.uberchat.data.PlayerDatabaseImpl;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 *
 * @author daboross
 */
public class ColorizorExecutor extends Command {

    private final UberChat uberChat;

    public ColorizorExecutor(UberChat uberChat) {
        super("colorize");
        this.uberChat = uberChat;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Sorry, Players Only");
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        PlayerDatabaseImpl database = uberChat.getPlayerDatabase();
        if (database.isColorizorEnabled(p.getName())) {
            database.setColorizorEnabled(p.getName(), false);
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Colorizor Enabled");
        } else {
            database.setColorizorEnabled(p.getName(), true);
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Colorizor Disabled");
        }
    }
}
