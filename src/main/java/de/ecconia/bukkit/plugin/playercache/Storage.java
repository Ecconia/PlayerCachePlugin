package de.ecconia.bukkit.plugin.playercache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import de.ecconia.bukkit.plugin.playercache.structs.Credentials;
import de.ecconia.bukkit.plugin.playercache.structs.NameHistory;
import de.ecconia.bukkit.plugin.playercache.structs.PlayerHistory;

public class Storage
{
	private final Map<String, NameHistory> nameToPlayer = new HashMap<>();
	private final Map<UUID, PlayerHistory> uuidToPlayer = new HashMap<>();
	
	public void update(String name, long time, UUID uuid)
	{
		PlayerHistory player = uuidToPlayer.get(uuid);
		if(player == null)
		{
			player = new PlayerHistory(uuid);
			uuidToPlayer.put(uuid, player);
		}
		
		player.updateUsername(name, time);
		
		NameHistory nameHistory = nameToPlayer.get(name.toLowerCase());
		if(nameHistory == null)
		{
			nameHistory = new NameHistory();
			nameToPlayer.put(name.toLowerCase(), nameHistory);
		}
		
		nameHistory.update(player);
	}
	
	/*Minimum API Requirement:
	 * Get the current username of a player by UUID
	 * Get the the UUID of the last player owning a name
	 * Get all playernames a player owned before (get by UUID)
	 */
	
	//Get the current UUID-Name link by Name or UUID
	
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
