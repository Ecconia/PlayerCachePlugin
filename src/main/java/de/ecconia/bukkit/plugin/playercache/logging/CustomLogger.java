package de.ecconia.bukkit.plugin.playercache.logging;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class CustomLogger
{
	private final ColoredLogger logger;
	
	public CustomLogger(Plugin plugin)
	{
		logger = new ColoredLogger(plugin);
	}
	
	public void error(String message)
	{
		logger.severe(ChatColor.RED + message);
	}
	
	public void warn(String message)
	{
		logger.warning(ChatColor.YELLOW + message);
	}
	
	public void info(String message)
	{
		logger.info(message);
	}
}
