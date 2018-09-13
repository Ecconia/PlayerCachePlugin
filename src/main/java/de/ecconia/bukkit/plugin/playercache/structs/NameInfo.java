package de.ecconia.bukkit.plugin.playercache.structs;

public class NameInfo
{
	private final String name;
	private final long time;
	private PlayerInfo player;
	
	//TODO: Add origin for colorcoding and meta
	public NameInfo(String name, long time)
	{
		this.name = name;
		this.time = time;
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

	public void setPlayer(PlayerInfo playerInfo)
	{
		playerInfo = player;
	}
}
