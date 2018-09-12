package de.ecconia.bukkit.plugin.playercache.structs;

import java.util.ArrayList;
import java.util.List;

public class NameHistory
{
	private final List<NameInfo> players = new ArrayList<>();
	
	public void update(NameInfo nameInfo)
	{
		getLatestPlayer().checkDirty(nameInfo.getName());
		players.add(nameInfo);
	}
	
	//Returns the lastest name owner as long as the name is not dirty (unowned).
	public PlayerHistory getCurrentPlayer()
	{
		PlayerHistory player = players.get(players.size() - 1).getPlayer();
		
		return player.isDirty() ? null : player;
	}
	
	//Returns the lastest name owner regardless if the name is dirty (unowned).
	public PlayerHistory getLatestPlayer()
	{
		return players.get(players.size() - 1).getPlayer();
	}
}
