package de.ecconia.bukkit.plugin.playercache.db;

import java.sql.SQLException;
import java.sql.Statement;

import de.ecconia.bukkit.plugin.playercache.logging.CustomLogger;

public class DBAdapter extends GenericDBAdapter
{
	public DBAdapter(String jdbcURL, CustomLogger logger, String prefix) throws SQLException
	{
		super(jdbcURL, logger);
	}
	
	@Override
	protected void setupConnection() throws SQLException
	{
	}

	@Override
	protected void createTables() throws SQLException
	{
		try(Statement stmt = connection.createStatement())
		{
		}
	}

	public void close()
	{
		try
		{
			connection.close();
		}
		catch (SQLException e)
		{
			//Well oops.
		}
	}
}
