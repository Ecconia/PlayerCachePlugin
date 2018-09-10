package de.ecconia.bukkit.plugin.playercache.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class ColoredLogger extends Logger
{
	private final String prefix;
	
	public static final char[] BLACK = 			"\u001B[30;22m".toCharArray();
	public static final char[] DARK_RED = 		"\u001B[31;22m".toCharArray();
	public static final char[] DARK_GREEN = 	"\u001B[32;22m".toCharArray();
	public static final char[] GOLD = 			"\u001B[33;22m".toCharArray();
	public static final char[] DARK_BLUE = 		"\u001B[34;22m".toCharArray();
	public static final char[] DARK_PURPLE = 	"\u001B[35;22m".toCharArray();
	public static final char[] DARK_AQUA = 		"\u001B[36;22m".toCharArray();
	public static final char[] GRAY = 			"\u001B[37;22m".toCharArray();
	
	public static final char[] DARK_GRAY = 		"\u001B[30;1m".toCharArray();
	public static final char[] RED = 			"\u001B[31;1m".toCharArray();
	public static final char[] GREEN = 			"\u001B[32;1m".toCharArray();
	public static final char[] YELLOW = 		"\u001B[33;1m".toCharArray();
	public static final char[] BLUE = 			"\u001B[34;1m".toCharArray();
	public static final char[] LIGHT_PURPLE = 	"\u001B[35;1m".toCharArray();
	public static final char[] AQUA = 			"\u001B[36;1m".toCharArray();
	public static final char[] WHITE = 			"\u001B[37;1m".toCharArray();
	
	public static final String SRESET = "\u001B[m";
	public static final char[] RESET = SRESET.toCharArray();
	public static final char[] FAIL = {ChatColor.COLOR_CHAR};
	public static final char[] FAIL_E = {};
	
	public ColoredLogger(Plugin plugin)
	{
		super(plugin.getClass().getCanonicalName(), null);
		
		setParent(plugin.getServer().getLogger());
		setLevel(Level.ALL);
		
		prefix = colorize(ChatColor.WHITE + "[" + ChatColor.GOLD + plugin.getDescription().getName() + ChatColor.WHITE + "] ");
	}
	
	@Override
	public void log(LogRecord logRecord)
	{
		logRecord.setMessage(prefix + colorize(logRecord.getMessage()) + SRESET);
		
		super.log(logRecord);
	}
	
	private static String colorize(String messageIn)
	{
		char[] message = messageIn.toCharArray();
		for(int i = 0; i < message.length-1; i++)
		{
			if(message[i] == '§')
			{
				char nextChar = message[i + 1];
				char[] replacement;
				
				switch(nextChar)
				{
				case '0': replacement = BLACK; break;
				case '1': replacement = DARK_BLUE; break;
				case '2': replacement = DARK_GREEN; break;
				case '3': replacement = DARK_AQUA; break;
				case '4': replacement = DARK_RED; break;
				case '5': replacement = DARK_PURPLE; break;
				case '6': replacement = GOLD; break;
				case '7': replacement = GRAY; break;
				
				case '8': replacement = DARK_GRAY; break;
				case '9': replacement = BLUE; break;
				case 'a': replacement = GREEN; break;
				case 'b': replacement = AQUA; break;
				case 'c': replacement = RED; break;
				case 'd': replacement = LIGHT_PURPLE; break;
				case 'e': replacement = YELLOW; break;
				case 'f': replacement = WHITE; break;
				
				case 'r': replacement = RESET; break;
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
					replacement = FAIL_E; break;
				default: replacement = FAIL;
				}
				
				char[] tmp = message;
				int length = tmp.length + replacement.length - 2;
				message = new char[length];
				
				System.arraycopy(tmp, 			0, 	message, 0, 					i					);
				System.arraycopy(replacement, 	0, 	message, i, 					replacement.length	);
				int p2 = i + 2;
				System.arraycopy(tmp, 			p2, message, i + replacement.length, tmp.length - p2	);
				
				i += replacement.length - 1;
			}
		}
		
		return new String(message);
	}
}
