package de.ecconia.bukkit.plugin.playercache;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.ecconia.bukkit.plugin.playercache.db.DBAdapter;
import de.ecconia.bukkit.plugin.playercache.logging.CustomLogger;

public class PlayerCachePlugin extends JavaPlugin implements Listener
{
	private CustomLogger logger;
	private DBAdapter dba;
	private Storage storage;
	
	@Override
	public void onLoad()
	{
		logger = new CustomLogger(this);
		
		saveDefaultConfig();
		String username = getConfig().getString("username");
		String password = getConfig().getString("password");
		String database = getConfig().getString("database");
		String prefix = getConfig().getString("prefix");
		
		String url = "jdbc:mysql://localhost/" + database + "?user=" + username + "&password=" + password + "&useSSL=false";
		try
		{
			dba = new DBAdapter("jdbc:mysql://localhost/" + database + "?user=" + username + "&password=" + password + "&useSSL=false", logger, prefix);
		}
		catch(SQLException e)
		{
			logger.error("Attempted to login using: '" + url + "'.");
			e.printStackTrace();
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		storage = new Storage();
		
		//TODO: Load data from DB.
		//TODO: Provide API.
		//On any error, do not provide, ¿and API and save to file?
	}
	
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onLogin(PlayerLoginEvent event)
	{
		String name = event.getPlayer().getName();
		UUID uuid = event.getPlayer().getUniqueId();
		long time = System.currentTimeMillis();
		
		//TODO: Remove debug
		getLogger().warning("Login: " + uuid + " - " + name);
		storage.update(name, time, uuid);
	}
}
