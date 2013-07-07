package AgressKey;


import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener
{  static YamlConfiguration items;
public static File itemsfile;
public static Map<String,Long> cooldown = new HashMap();

	 public void onEnable() {
		  itemsfile = new File(getDataFolder(), "items.yml");
		  if(!itemsfile.exists()) {
			  try {
				  itemsfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		 Bukkit.getPluginManager().registerEvents(this, this);
	 }
	 public void onDisable() {}
	 
	 
	 public void sendall(String a) {
		   for(Player p : Bukkit.getOnlinePlayers()) {
	      	 p.sendMessage(ChatColor.translateAlternateColorCodes('&', a));
	       }
	  }
	 
	 
	  public List listitem() {
		  File flang = new File(getDataFolder(), "/items.yml");

	      items = YamlConfiguration.loadConfiguration(flang);
	      List<String> itemList = items.getStringList("items");
	      if(itemList == null) {
	    	  itemList = new ArrayList<String>();

	      }	  
		return itemList;
		  
	  }
	 
	 public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	  if(cmd.getName().equalsIgnoreCase("key")) {
	        if (args.length == 1) {
	        if(cooldown.get("ServerTime") != null) {
	        	long time = System.currentTimeMillis()/1000 - cooldown.get("ServerTime");
	        	if(time < 5) {
	        		int sec = (int) (5 - time);
	        	 	sender.sendMessage(ChatColor.RED+"Попробуйте сново через: " + sec + new time().second5(sec));
	        		return true;
	        	}
	       
	        }
	       cooldown.put("ServerTime", System.currentTimeMillis()/1000);
		  Connection c = null;
		    Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:key.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      
		      ResultSet players = stmt.executeQuery( "SELECT * FROM players where name='"+sender.getName().toLowerCase()+"' AND key = '"+args[0]+"';" );
              while(players.next()) {
            	  sender.sendMessage(ChatColor.RED+"Вы уже активировали этот ключ!");
            	  return true;
              }
              ResultSet rs = stmt.executeQuery( "SELECT * FROM keylist where key='"+args[0]+"';" );
	            
	
		      
		      while ( rs.next() ) {
		         Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),rs.getString("command").replaceAll("%u", sender.getName()));
			      for (Player player : Bukkit.getOnlinePlayers()) {
		                if (player.hasPermission("Send.Message.Key")) {
		                  player.sendMessage(args[0] + ChatColor.GREEN + " Активирован " + ChatColor.WHITE + sender.getName());
		                  player.sendMessage(args[0] + ChatColor.GREEN + " Команда " + ChatColor.WHITE + rs.getString("command").replaceAll("%u", sender.getName()));
		                }
		              }
		      }
		      
		      rs = stmt.executeQuery( "SELECT * FROM keylist where key='"+args[0]+"';" );
		      if (rs.next()) {
		          if(rs.getInt("use") == 1) {
			        	String sql = "DELETE from keylist where key='"+args[0]+"';";
			  		    stmt.executeUpdate(sql);
					      c.commit();

			         }
		    	  
		          sender.sendMessage(ChatColor.GREEN + "Ваш код успешно активирован");
			         int a = rs.getInt("use")-1;
			       String sql = "UPDATE keylist set use = "+a+" where key='"+args[0]+"';";
				      stmt.executeUpdate(sql);
				      
				      c.commit();
				      String sqll = "INSERT INTO players VALUES (null,'"+sender.getName().toLowerCase()+"','"+args[0]+"');";
				      stmt.executeUpdate(sqll);
					    c.commit();
				      } else {
		                  sender.sendMessage(ChatColor.RED + "Ваш код не верен, или уже введен!");
		    	}
		      
	
		      
		      rs.close();
		      players.close();
		      stmt.close();
		      c.close();

		    } catch ( Exception e ) {
		    }
	        } else sender.sendMessage(ChatColor.RED + "/key Ключ");

	  } else   if(cmd.getName().equalsIgnoreCase("create-key")) {
		  if(sender.hasPermission("AgressKey.create")) {
	          if (args.length > 3) {
	              String key = args[0].replaceAll("%r", keyRandom.key());
	              Random r = new Random();
	      StringBuilder command = new StringBuilder();
          if(args[1].contentEquals("%s")) {
	      for (int i = 3; i < args.length; i++) {
            if (i > 3) command.append(" ");
            command.append(args[i]);
        

          }	  
	      if(args[1].contentEquals("%s")) sendall("&8[&6Keys&8] &bКто первый напишет &c/key "+ key + "&b получит приз!");
          SQL.add(key, Integer.parseInt(args[2]), command.toString().replaceAll("%i", (String) listitem().get(r.nextInt(listitem().size()))));
          } else {
    	      for (int i = 2; i < args.length; i++) {
    	            if (i > 2) command.append(" ");
    	            command.append(args[i]);
    	      }
              SQL.add(key, Integer.parseInt(args[1]), command.toString().replaceAll("%i", (String) listitem().get(r.nextInt(listitem().size()))));

          }
          sender.sendMessage(ChatColor.GREEN + "Ваш код: "+ key +" успешно Создан");

	          }  else sender.sendMessage(ChatColor.RED + "/create-key ключ отправка количество команда");
	  } else  sender.sendMessage(ChatColor.RED + "Я не знаю как ты узнал об этой команде, но у тебя нет прав.");
	  }
		 return true;
	 }
	}