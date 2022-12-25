package com.peeranat.discord.cmds;

import java.util.List;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteractionOption;

import com.peeranat.discord.FewPzDiscord;
import com.peeranat.discord.classroom.ClassroomEnum;
import com.peeranat.discord.command.CommandExecutor;

public class AnnouncementCommand implements CommandExecutor {

	private FewPzDiscord discord;

	public AnnouncementCommand(FewPzDiscord discord) {
		// TODO Auto-generated constructor stub
		this.discord = discord;
	}

	@Override
	public void onCommand(SlashCommandCreateEvent event, List<SlashCommandInteractionOption> args) {
		// TODO Auto-generated method stub
		MessageBuilder builder = null;
		ClassroomEnum subject = ClassroomEnum.valueOf(args.get(0).getStringValue().get());
		String text = args.get(1).getStringValue().get();
		String tutorDate = args.get(2).getStringValue().get();
		String tutorTime = args.get(3).getStringValue().get();
		
		if (args.size() == 5) {
			String special = args.get(4).getStringValue().get();
			builder = discord.getClassroom().sendAnnouncement(subject, tutorDate, tutorTime, text, special);
		} else if (args.size() == 4) {
			builder = discord.getClassroom().sendAnnouncement(subject, tutorDate, tutorTime, text);
		}
		
		TextChannel textChannel = discord.getTextChannel(discord.getCredential().asLong("DEV_ID"));
		builder.addComponents(ActionRow.of(Button.success("FEW_ANNOUNCEMENT_CLASSROOM", "ประกาศสาธารณะ")));
		builder.send(textChannel);
	}

}
