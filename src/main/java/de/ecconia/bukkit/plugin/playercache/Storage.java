package de.ecconia.bukkit.plugin.playercache;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.ecconia.bukkit.plugin.playercache.structs.NameHistory;
import de.ecconia.bukkit.plugin.playercache.structs.NameInfo;
import de.ecconia.bukkit.plugin.playercache.structs.PlayerInfo;

public class Storage
{
	private final Map<String, NameHistory> nameToPlayer = new HashMap<>();
	private final Map<UUID, PlayerInfo> uuidToPlayer = new HashMap<>();
	
	public void update(String name, long time, UUID uuid)
	{
		NameInfo nameInfo = new NameInfo(name, time);
		
		PlayerInfo player = uuidToPlayer.get(uuid);
		if(player == null)
		{
			player = new PlayerInfo(uuid, nameInfo);
			uuidToPlayer.put(uuid, player);
		}
		else
		{
			player.updateUsername(nameInfo);
		}
		
		NameHistory nameHistory = nameToPlayer.get(name.toLowerCase());
		if(nameHistory == null)
		{
			nameHistory = new NameHistory(nameInfo);
			nameToPlayer.put(name.toLowerCase(), nameHistory);
		}
		else
		{
			nameHistory.update(nameInfo);
		}
	}
	
	/*Minimum API Requirement:
	 * Get the current username of a player by UUID
	 * Get the the UUID of the last player owning a name
	 * Get all playernames a player owned before (get by UUID)
	 */
	
	//Get the current UUID-Name link by Name or UUID
	
	public PlayerInfo findByName(String name)
	{
		NameHistory nH = nameToPlayer.get(name.toLowerCase());
		
		return nH == null ? null : nH.getLatestPlayer();
	}
	
	public PlayerInfo findByUUID(UUID uuid)
	{
		return uuidToPlayer.get(uuid);
	}
	
	//Check if a player had been on this server before
	
	public boolean isKnownByName(String name)
	{
		NameHistory nH = nameToPlayer.get(name.toLowerCase());
		
		return nH == null;
	}
	
	public boolean isKnownByUUID(UUID uuid)
	{
		return uuidToPlayer.get(uuid) != null;
	}
}
