package de.ecconia.bukkit.plugin.playercache.structs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerHistory
{
	private final UUID uuid;
	private String currentName;
	
	private final Map<Long, String> nameHistory = new HashMap<>();
	
	public PlayerHistory(UUID uuid)
	{
		this.uuid = uuid;
	}
	
	public void addUsername(String name, long time)
	{
		nameHistory.put(time, name);
	}
	
	public void updateUsername(String name, long time)
	{
		addUsername(name, time);
		currentName = name;
	}
	
	public Credentials getCredentials()
	{
		return new Credentials(currentName, uuid);
	}
	
	public Set<String> getPlayernames()
	{
		return new HashSet<>(nameHistory.values());
	}
	
	public Map<Long, String> getNameHistory()
	{
		//Create a copy, to not update data somewhere.
		return new HashMap<>(nameHistory);
	}
}
