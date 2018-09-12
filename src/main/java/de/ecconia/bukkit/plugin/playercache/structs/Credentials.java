package de.ecconia.bukkit.plugin.playercache.structs;

import java.util.UUID;

public class Credentials
{
	public final String name;
	public final UUID uuid;
	
	public Credentials(String name, UUID uuid)
	{
		this.name = name;
		this.uuid = uuid;
	}
	
	public String getName()
	{
		return name;
	}
	
	public UUID getUuid()
	{
		return uuid;
	}
}
