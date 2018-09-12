package de.ecconia.bukkit.plugin.playercache.structs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlayerHistory
{
	//UUID of the player, will never change.
	private final UUID uuid;
	//Last seen name of the player, will be updated as soon as the player joins.
	private String currentName;
	//A boolean indicating the rare case, that the name has been owned by someone else and this one is not up to date anymore.
	private boolean dirty;
	
	private final List<NameInfo> nameHistory = new ArrayList<>();
	
	public PlayerHistory(UUID uuid)
	{
		this.uuid = uuid;
	}
	
	public void addUsername(NameInfo nameInfo)
	{
		nameHistory.add(nameInfo);
	}
	
	public void updateUsername(NameInfo nameInfo)
	{
		addUsername(nameInfo);
		currentName = nameInfo.getName();
	}
	
	public void checkDirty(String name)
	{
		if(name.equalsIgnoreCase(currentName))
		{
			dirty = true;
		}
	}
	
	//Get the last seen name and UUID of this player.
	
	public UUID getUuid()
	{
		return uuid;
	}
	
	public String getName()
	{
		return currentName;
	}
	
	public boolean isDirty()
	{
		return dirty;
	}
	
	//Get the name history of this player.
	//The non-final data storage will be copied.
	
	public Set<String> getPlayernames()
	{
		return nameHistory.stream().map(NameInfo::getName).collect(Collectors.toSet());
	}
	
	public List<NameInfo> getNameHistory()
	{
		return new ArrayList<>(nameHistory);
	}
}
