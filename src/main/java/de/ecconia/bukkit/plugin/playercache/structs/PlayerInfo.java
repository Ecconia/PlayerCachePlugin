package de.ecconia.bukkit.plugin.playercache.structs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PlayerInfo
{
	//UUID of the player, will never change.
	private final UUID uuid;
	//A boolean indicating the rare case, that the name has been owned by someone else and this one is not up to date anymore.
	private boolean dirty;
	
	private NameInfo names[] = new NameInfo[1];
	
	public PlayerInfo(UUID uuid, NameInfo firstName)
	{
		this.uuid = uuid;
		names[0] = firstName;
		firstName.setPlayer(this);
	}
	
	public void updateUsername(NameInfo nameInfo)
	{
		nameInfo.setPlayer(this);
		
		NameInfo namesNew[] = new NameInfo[names.length + 1];
		System.arraycopy(names, 0, namesNew, 1, names.length);
		namesNew[0] = nameInfo;
		names = namesNew;
		
		dirty = false;
	}
	
	public void checkDirty(String name)
	{
		if(name.equalsIgnoreCase(getName()))
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
		return names[0].getName();
	}
	
	public boolean isDirty()
	{
		return dirty;
	}
	
	//Get the name history of this player.
	//The non-final data storage will be copied.
	
	public Set<String> getPlayernames()
	{
		Set<String> nameSet = new HashSet<>(names.length);
		for(NameInfo info : names)
		{
			nameSet.add(info.getName());
		}
		
		return nameSet;
	}
	
	public List<NameInfo> getNameHistory()
	{
		List<NameInfo> nameList = new ArrayList<>(names.length);
		for(NameInfo info : names)
		{
			nameList.add(info);
		}
		
		return nameList;
	}
}
