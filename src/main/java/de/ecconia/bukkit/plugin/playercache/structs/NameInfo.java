package de.ecconia.bukkit.plugin.playercache.structs;

public class NameInfo
{
	private final String name;
	private final long time;
	private final PlayerInfo player;
	
	public NameInfo(String name, long time, PlayerInfo player)
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
	
	public PlayerInfo getPlayer()
	{
		return player;
	}
}
