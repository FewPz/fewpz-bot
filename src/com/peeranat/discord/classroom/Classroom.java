package com.peeranat.discord.classroom;

import java.io.IOException;

import org.javacord.api.entity.Attachment;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.mention.AllowedMentions;
import org.javacord.api.entity.message.mention.AllowedMentionsBuilder;

import com.peeranat.discord.FewPzDiscord;
import com.peeranat.discord.util.StringUtil;

public class Classroom {

	private FewPzDiscord discord;
	public Classroom(FewPzDiscord discord) {
		// TODO Auto-generated constructor stub
		this.discord = discord;
	}
	
	public MessageBuilder sendAnnouncement(ClassroomEnum classroomEnum, String tutorDate, String tutorTime, String context) {
		return sendAnnouncement(classroomEnum, tutorDate, tutorTime, context, null, null);
	}
	
	public MessageBuilder sendAnnouncement(ClassroomEnum classroomEnum, String tutorDate, String tutorTime, String context, Attachment attachment, String special) {
		long roleID = discord.getCredential().asLong("IT");
		String mentionTag = discord.getServer().getRoleById(roleID).get().getMentionTag();
		MessageBuilder builder = new MessageBuilder();
		AllowedMentions build = new AllowedMentionsBuilder().setMentionUsers(true).setMentionRoles(true).build();
		
		
		StringBuilder text = new StringBuilder();
		text.append("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n");
		text.append("Dear: " + mentionTag + "\n");
		text.append("ในวันที่ " + tutorDate + " "+ tutorTime +" น." + "\n");
		text.append("\n");
		text.append("เรามีนัดกัน เจอกันในรายวิชา "+ StringUtil.capitalize(classroomEnum.getName()) +"\n");
		text.append("หรือเรียกสั้นๆ ได้ว่าวิชา **"+classroomEnum.getShortName()+"**\n");
		text.append("\n");
		text.append("เนื้อหาที่เราจะได้เรียนกัน มีอะไรกันบ้าง มาดูกัน~!\n");
		text.append("\n");
		for (String value : context.split(":")) {
			text.append("> เรื่อง **" + value + "**\n");
		}
		text.append("\n");
		if (special != null) {
			text.append(special + "\n");
			text.append("\n");
		}
		text.append("แล้วพบกัน, See ya!\n");
		text.append("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n");
		if (attachment != null) {
			try {
				builder.addAttachment(attachment.downloadAsInputStream(), attachment.getFileName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		builder.setAllowedMentions(build);
		builder.append(text.toString());
		return builder;
	}
	
}
