package org.dreamexposure.discal.client.module.command;

import org.dreamexposure.discal.core.object.GuildSettings;
import org.dreamexposure.discal.core.object.command.CommandInfo;

import java.util.ArrayList;
import java.util.List;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

/**
 * @author NovaFox161
 * Date Created: 9/10/2018
 * For Project: DisCal-Discord-Bot
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
public interface Command {
    default String getCommand() {
        return "COMMAND_NAME";
    }

    default List<String> getAliases() {
        final List<String> aliases = new ArrayList<>();

        aliases.add("ALIAS");

        return aliases;
    }

    default CommandInfo getCommandInfo() {
        final CommandInfo info = new CommandInfo(
            "COMMAND_NAME",
            "COMMAND_DESCRIPTION",
            "!command <sub> (sub2)"
        );

        info.getSubCommands().put("a", "b");

        return info;
    }

    Mono<Void> issueCommand(String[] args, MessageCreateEvent event, GuildSettings settings);
}