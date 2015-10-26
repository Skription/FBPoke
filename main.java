package net.skription.Poke;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Thirdattempt
  extends JavaPlugin
{
  public void onEnable()
  {
    getLogger().info("FBPoke has been enabled!");
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
  }
  
  public void onDisable()
  {
    getLogger().info("FBPoke has been disabled!");
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (!sender.hasPermission("fb.poke"))
    {
      sender.sendMessage(ChatColor.RED + "You do not have permission to poke!");
      return true;
    }
    if (cmd.getName().equalsIgnoreCase("poke")) {
      if (args.length == 0)
      {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("poke")));
      }
      else if (args.length == 1)
      {
        String playerName = args[0];
        
        Player selected = Bukkit.getServer().getPlayer(playerName);
        if (selected == null)
        {
          sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("offline")));
          return true;
        }
        selected.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("poked")));
        
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("youpoke")));
      }
    }
    return true;
  }
}
