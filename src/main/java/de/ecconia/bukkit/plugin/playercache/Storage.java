package de.ecconia.bukkit.plugin.playercache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import de.ecconia.bukkit.plugin.playercache.structs.Credentials;
import de.ecconia.bukkit.plugin.playercache.structs.PlayerHistory;

public class Storage
{
	private final Map<String, PlayerHistory> latestNameToPlayer = new HashMap<>();
	private final Map<String, PlayerHistory> nameToPlayer = new HashMap<>();
	private final Map<UUID, PlayerHistory> uuidToPlayer = new HashMap<>();
	
	public void update(String name, long time, UUID uuid)
	{
		PlayerHistory player = uuidToPlayer.get(uuid);
		if(player == null)
		{
			player = new PlayerHistory(uuid);
			uuidToPlayer.put(uuid, player);
		}
		else
		{
			//Remove last playername
			latestNameToPlayer.remove(player.getCredentials().getName().toLowerCase());
		}
		
		player.updateUsername(name, time);
		
		nameToPlayer.put(name.toLowerCase(), player);
		latestNameToPlayer.put(name.toLowerCase(), player);
	}
	
	//Get the current UUID-Name link by Name or UUID
	
	public Credentials findByCurrentName(String name)
	{
		PlayerHistory player = latestNameToPlayer.get(name.toLowerCase());
		
		return player == null ? null : player.getCredentials();
	}
	
	public Credentials findByName(String name)
	{
		PlayerHistory player = nameToPlayer.get(name.toLowerCase());
		
		return player == null ? null : player.getCredentials();
	}
	
	public Credentials findByUUID(UUID uuid)
	{
		PlayerHistory player = uuidToPlayer.get(uuid);
		
		return player == null ? null : player.getCredentials();
	}
	
	//Check if a player had been on this server before
	
	public boolean isKnownByCurrentName(String name)
	{
		return latestNameToPlayer.get(name.toLowerCase()) != null;
	}
	
	public boolean isKnownByName(String name)
	{
		return nameToPlayer.get(name.toLowerCase()) != null;
	}
	
	public boolean isKnownByUUID(UUID uuid)
	{
		return uuidToPlayer.get(uuid) != null;
	}
	
	//Get name history from player
	
	public Set<String> getNameSetByUUID(UUID uuid)
	{
		PlayerHistory player = uuidToPlayer.get(uuid);
		
		return player.getPlayernames();
	}
}
