package de.ecconia.bukkit.plugin.playercache.structs;

public class NameHistory
{
	private NameInfo names[] = new NameInfo[1];
	
	public NameHistory(NameInfo nameInfo)
	{
		names[0] = nameInfo;
	}
	
	public void update(NameInfo nameInfo)
	{
		getLatestPlayer().checkDirty(nameInfo.getName());
		
		NameInfo namesNew[] = new NameInfo[names.length + 1];
		System.arraycopy(names, 0, namesNew, 1, names.length);
		namesNew[0] = nameInfo;
		names = namesNew;
	}
	
	//Returns the lastest name owner as long as the name is not dirty (unowned).
	public PlayerInfo getCurrentPlayer()
	{
		PlayerInfo player = names[0].getPlayer();
		
		return player.isDirty() ? null : player;
	}
	
	//Returns the lastest name owner regardless if the name is dirty (unowned).
	public PlayerInfo getLatestPlayer()
	{
		return names[0].getPlayer();
	}
}
