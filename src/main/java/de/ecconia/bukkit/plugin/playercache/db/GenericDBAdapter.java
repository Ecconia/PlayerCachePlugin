package de.ecconia.bukkit.plugin.playercache.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.ecconia.bukkit.plugin.playercache.logging.CustomLogger;

public abstract class GenericDBAdapter
{
	//Default timeout of MySQL in seconds ~8h.
	//* 1000 due to Java time.
	private final static int timeout = 28800000;
	private long connectionStart;
	
	private final String jdbcURL;
	
	protected Connection connection;
	protected CustomLogger logger;
	
	public GenericDBAdapter(String jdbcURL, CustomLogger logger) throws SQLException
	{
		this.jdbcURL = jdbcURL;
		this.logger = logger;
		
		startConection();
		
		try
		{
			createTables();
		}
		catch (SQLException e)
		{
			logger.error("Error creating required tables.");
			throw e;
		}
	}
	
	private final void startConection() throws SQLException
	{
		connectionStart = System.currentTimeMillis();
		try
		{
			connection = DriverManager.getConnection(jdbcURL);
		}
		catch (SQLException e)
		{
			logger.error("Error connecting to DB.");
			throw e;
		}
		
		try
		{
			setupConnection();
		}
		catch (SQLException e)
		{
			logger.error("Error setting up DB connection.");
			throw e;
		}
	}
	
	protected final void valid() throws SQLException
	{
		if(System.currentTimeMillis() - connectionStart > timeout)
		{
			try
			{
				startConection();
			}
			catch (SQLException e)
			{
				logger.error("Could not renew the connection to the DB.");
				throw e;
			}
		}
	}
	
	protected void setupConnection() throws SQLException
	{
	}
	
	protected void createTables() throws SQLException
	{
	}
}
