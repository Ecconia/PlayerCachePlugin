package de.ecconia.bukkit.plugin.playercache.structs;

public class NameInfo
{
	private final String name;
	private final long time;
	private final PlayerHistory player;
	
	public NameInfo(String name, long time, PlayerHistory player)
	{
		this.name = name;
		this.time = time;
		this.player = player;
	}
	
	public String getName()
	{
		return name;
	}
	
	public long getTime()
	{
		return time;
	}
	
	public PlayerHistory getPlayer()
	{
		return player;
	}
}
