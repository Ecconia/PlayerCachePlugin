package de.ecconia.bukkit.plugin.playercache;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.ecconia.bukkit.plugin.playercache.logging.CustomLogger;

public class PlayerCachePlugin extends JavaPlugin implements Listener
{
	private static CustomLogger logger;
	
	@Override
	public void onLoad()
	{
		logger = new CustomLogger(this);
		
		//TODO: Establish DB connection.
		//TODO: Load data from DB.
		//TODO: Provide API.
		//On any error, do not provide, ¿and API and save to file?
	}
	
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPreJoin(PlayerLoginEvent event)
	{
		getLogger().warning("Login: " + event.getPlayer().getUniqueId() + " - " + event.getPlayer().getName());
	}
}
