package de.ecconia.bukkit.plugin.playercache;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCachePlugin extends JavaPlugin implements Listener
{
	@Override
	public void onLoad()
	{
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
