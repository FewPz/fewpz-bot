package com.peeranat.discord.command;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteractionOption;

public class Command {
	
	public ConcurrentHashMap<String, CommandExecutor> command;
	public Command() {
		// TODO Auto-generated constructor stub
		command = new ConcurrentHashMap<>();
	}
	
	public void register(String cmd, CommandExecutor executor) {
		if (command.get(cmd) != null) {
			throw new RuntimeException("This is command has been already register!");
		}
		command.put(cmd, executor);
	}
	
	public boolean isInitial(String cmd) {
		return command.get(cmd) != null;
	}
	
	public CommandExecutor getExecutor(String cmd) {
		return command.get(cmd);
	}
	
	public void doExecute(SlashCommandCreateEvent e) {
		String commandName = e.getSlashCommandInteraction().getCommandName();
		List<SlashCommandInteractionOption> arguments = e.getSlashCommandInteraction().getArguments();
		try {
			getExecutor(commandName).onCommand(e, arguments);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
