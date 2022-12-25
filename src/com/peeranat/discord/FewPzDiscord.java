package com.peeranat.discord;

import java.util.Arrays;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionType;

import com.peeranat.discord.classroom.Classroom;
import com.peeranat.discord.classroom.ClassroomEnum;
import com.peeranat.discord.cmds.AnnouncementCommand;
import com.peeranat.discord.command.Command;
import com.peeranat.discord.credential.Credential;
import com.peeranat.discord.listener.OnFewPzClickSelectRole;
import com.peeranat.discord.listener.OnFewPzSlashCommand;

public class FewPzDiscord {
	
	private Command command;
	private Classroom classroom;
	private Credential credential;
	private DiscordApi discordApi;
	private boolean isEnable;
	private String token;
	private long serverId;
	
	public FewPzDiscord() {
		command = new Command();
		classroom = new Classroom(this);
		credential = new Credential();
		token = credential.asString("TOKEN");
		serverId = credential.asLong("SERVER_ID");
	}
	
	protected void start() {
		try {
			discordApi = new DiscordApiBuilder().setToken(token).login().join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		isEnable = true;
		
		discordApi.addListener(new OnFewPzClickSelectRole(this));
//		discordApi.addListener(new OnFewPzClickButtonAnnouncement(this));
		discordApi.addListener(new OnFewPzSlashCommand(this));
		
		command.register("announcement", new AnnouncementCommand(this));
		SlashCommand.with("announcement", "To announcement special class", Arrays.asList(
				SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "subject", "เลือกวิชาที่ต้องการจะสอน", true, ClassroomEnum.getChoice()),
				SlashCommandOption.createStringOption("เนื้อหา", "เนื้อหาที่อยากจะสอน หากมีมากกว่า 2 เรื่องให้ใส่ : (semi-colon) ไว้", true),
				SlashCommandOption.createStringOption("วันที่เวลาเรียน", "วันที่", true),
				SlashCommandOption.createStringOption("ช่วงเวลาเรียน", "ช่วงเวลาเรียน", true),
				SlashCommandOption.createStringOption("เนื้อหาเพิ่มเติม", "จะใส่หรือไม่ใส่ก็ได้ ไม่บังคับ", false) 
		)).createForServer(getServer()).join();
	}
	
	protected void stop() {
		discordApi.disconnect();
		discordApi = null;
	}
	
	public DiscordApi getDiscord() {
		return discordApi;
	}
	
	public boolean isEnable() {
		return isEnable;
	}
	
	public Server getServer() {
		return discordApi.getServerById(serverId).get();
	}
	
	public TextChannel getTextChannel(long channelId) {
		TextChannel textChannel = discordApi.getTextChannelById(channelId).get();
		return textChannel;
	}
	
	public Classroom getClassroom() {
		return classroom;
	}
	
	public Credential getCredential() {
		return credential;
	}
	
	public Command getCommand() {
		return command;
	}
	
}
