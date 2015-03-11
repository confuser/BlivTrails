package net.auscraft.BlivTrails;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import net.auscraft.BlivTrails.config.ConfigAccessor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utilities 
{
	
	private BlivTrails plugin;
	private ConfigAccessor cfg;
	private final String prefix = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "BlivTrails" + ChatColor.WHITE + "] ";
	private String playerPrefix = "";
	private Logger log = Logger.getLogger("Minecraft");
	private boolean debug;
	
	public Utilities()
	{
		//Empty
		debug = cfg.getBoolean("misc.debug");
	}
	
	//Used in Async methods where Bukkit is inaccessible.
	//Variables are assumed in this mode.
	public Utilities(boolean isAsync)
	{
		debug = true;
	}
	
	//------------------------------------------------------------------------------------------------------
	//Extra Setup
	//------------------------------------------------------------------------------------------------------
	
	public void setConfig(ConfigAccessor cfg)
	{
		this.cfg = cfg;
		playerPrefix = translateColours(plugin.getMessages().getString("messages.prefix"));
		if(playerPrefix != "")
		{
			playerPrefix += " ";
		}
	}
	
	
	//------------------------------------------------------------------------------------------------------
	//String Translation
	//------------------------------------------------------------------------------------------------------
	
	public String stripColours(String toFix)
	{
		Pattern chatColorPattern = Pattern.compile("[&](.)");
		String fixedString = chatColorPattern.matcher(toFix).replaceAll("");
		return fixedString;
	}
	
	public String translateColours(String toFix)
	{
		//Convert every single colour code and formatting code, excluding 'magic' (&k), capitals and lowercase are converted.
		Pattern chatColorPattern = Pattern.compile("(?i)&([0-9A-Fa-f-l-oL-OrR])");
		String fixedString = chatColorPattern.matcher(toFix).replaceAll("\u00A7$1");
		return fixedString;
	}
	
	public List<String> translateColours(List<?> lines)
	{
		try
		{
			String[] lineString = null;
			if(lines.size() > 0)
			{
				lineString = lines.toArray(new String[lines.size()]);
			}
			else
			{
				return null;
			}
			for(int i = 0;i < lines.size();i++)
			{
				Pattern chatColourPattern = Pattern.compile("(?i)&([0-9A-Fa-fk-oK-OrR])");
				lineString[i] = chatColourPattern.matcher(lineString[i]).replaceAll("\u00A7$1");
			}
			return Arrays.asList(lineString);
		}
		catch(NullPointerException e)
		{
			return null;
		}
		
	}
	
	//------------------------------------------------------------------------------------------------------
		//Printing
		//------------------------------------------------------------------------------------------------------
		
		public void printSuccess(CommandSender sender, String message)
		{
			sender.sendMessage(playerPrefix + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "SUCCESS: " + ChatColor.GREEN + translateColours(message));
		}
		
		public void printPlain(CommandSender sender, String message)
		{
			sender.sendMessage(playerPrefix + translateColours(message));
		}
		
		public void printInfo(CommandSender sender, String message)
		{
			sender.sendMessage(playerPrefix + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "INFO: " + ChatColor.BLUE + translateColours(message));
		}
		
		public void printError(CommandSender sender, String message)
		{
			sender.sendMessage(playerPrefix + ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + "ERROR: " + ChatColor.RED + translateColours(message));
		}
		
		//------------------------------------------------------------------------------------------------------
		//Broadcasting
		//------------------------------------------------------------------------------------------------------
		
		public void broadcastPlain(String message)
		{
			Bukkit.broadcastMessage(message);
		}
		
		
		//------------------------------------------------------------------------------------------------------
		//Logging
		//------------------------------------------------------------------------------------------------------
		
		public void logSuccess(String message)
		{
			log.log(Level.INFO, prefix + ChatColor.DARK_GREEN + "SUCCESS: " + ChatColor.GREEN + translateColours(message));
		}
		
		public void logPlain(String message)
		{
			log.log(Level.INFO, prefix + message);
		}
		
		public void logInfo(String message)
		{
			log.log(Level.INFO, prefix + ChatColor.DARK_AQUA + "" + "INFO: " + ChatColor.BLUE + translateColours(message));
		}
		
		public void logError(String message)
		{
			log.log(Level.WARNING, prefix + ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + translateColours(message));
		}
		
		public void logDebug(String message)
		{
			if(debug)
			{
				log.log(Level.FINE, prefix + ChatColor.GREEN + "DEBUG: " + ChatColor.RED + translateColours(message));
			}
		}
		
		public void logSevere(String message)
		{
			log.log(Level.SEVERE, prefix + ChatColor.DARK_RED + "SEVERE: " + ChatColor.RED + translateColours(message));
		}
		
		//------------------------------------------------------------------------------------------------------
		//Miscellaneous
		//------------------------------------------------------------------------------------------------------
		
		public BlivTrails getInstance()
		{
			return plugin;
		}
		
		public String trailConfigName(String particleString)
		{
			switch(particleString)
			{
				case "BARRIER": particleString = "barrier"; break;
				case "CLOUD": particleString = "cloud"; break;
				case "CRIT": particleString = "criticals"; break;
				case "CRIT_MAGIC": particleString = "criticals-magic"; break;
				case "DRIP_LAVA": particleString = "drip-lava"; break;
				case "DRIP_WATER": particleString = "drip-water"; break;
				case "ENCHANTMENT_TABLE": particleString = "enchant"; break;
				case "EXPLOSION_NORMAL": particleString = "explosion-smoke"; break;
				case "FIREWORKS_SPARK": particleString = "firework"; break;
				case "FLAME": particleString = "flame"; break;
				case "HEART": particleString = "hearts"; break;
				case "LAVA": particleString = "lava"; break;
				case "NOTE": particleString = "note"; break;
				case "PORTAL": particleString = "portal"; break;
				case "REDSTONE": particleString = "redstone"; break;
				case "SLIME": particleString = "slime"; break;
				case "SMOKE_LARGE": particleString = "smoke"; break;
				case "SNOW_SHOVEL": particleString = "snow-shovel"; break;
				case "SNOWBALL": particleString = "snow-ball"; break;
				case "SPELL": particleString = "spell"; break;
				case "SPELL_INSTANT": particleString = "spell-instant"; break;
				case "SPELL_MOB": particleString = "spell-mob"; break;
				case "SPELL_WITCH": particleString = "spell-witch"; break;
				case "VILLAGER_ANGRY": particleString = "angry-villager"; break;
				case "VILLAGER_HAPPY": particleString = "happy-villager"; break;
				case "TOWN_AURA": particleString = "town-aura"; break;
				case "WATER_DROP": particleString = "water-drop"; break;
				case "WATER_SPLASH": particleString = "water-splash"; break;
				default: particleString = "NULL"; break;
			}
			return particleString;
		}
}
