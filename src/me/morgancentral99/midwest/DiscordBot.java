package me.morgancentral99.midwest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DiscordBot extends ListenerAdapter{
	
	public MySQL sql = new MySQL();
	private int PlayersOn = 0;
	
	private static JDA jda;
	public static void main(String[] args) {
		try {
			new MySQL().onStart();
			
			jda = new JDABuilder(AccountType.BOT).setToken("NDQxNjU3MTg5MjA0MDk5MDgw.DdM9oQ.T_iRiSmZQpnIlx8B6HGo0jSc4EI").addEventListener(new DiscordBot()).buildBlocking();
			System.out.println(jda.getStatus());
		} catch (LoginException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nonstatic() {
		
	}
		
	 @Override
	public void onMessageReceived(MessageReceivedEvent event)
    {
		 if(event.isFromType(ChannelType.PRIVATE)) {
			 return;
		 }
		 String message = event.getMessage().toString();
		 String msg = event.getMessage().getContentDisplay();
		 String slashdetection = Character.toString(msg.charAt(0));
		 String[] messageSplit = msg.split(" ");
		 System.out.println(msg);
		 System.out.println(slashdetection);
		 System.out.println(messageSplit[0] + " Message");
		 if(slashdetection.equals("/")) {
			 if(messageSplit[0].equalsIgnoreCase("/playerson")) {
				 ResultSet set = sql.readFromDatabase("SELECT `UsersOnline` FROM `Midwest-Discord-Bot`");
				 try {
					while(set.next()) {
						 PlayersOn = set.getInt("UsersOnline");
						 event.getChannel().sendMessage("Players Online: " + PlayersOn);
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			 }
		 }
  
 	}
	
		   public static String removeCharAt(String s, int pos) {
		      return s.substring(0, pos) + s.substring(pos + 1);
		   }


}
