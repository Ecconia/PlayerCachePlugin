# PlayerCache Plugin
A Bukkit plugin which stores playername and UUID of each joining player, just like the Mojang PlayerCache but with a proper API.

Downside of CraftBukkit implementation/API:

Bukkit itself states that the method of getting an offline player may be slow and even query the mojang services, that should not be the case. Further it ensures that there will always be a result and indeed, if a player is not cached a fake UUID will be returned, that leads to corruption.

The plugin will store the names in a MySQL database, but load them fully to RAM on plugin load.