package com.adamedw.colorblocker.configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import com.adamedw.colorblocker.ColorBlocker;

public class CBConfig {


	protected final ColorBlocker i;
	protected final File file;
	protected FileConfiguration config;

	public CBConfig(ColorBlocker i, String fileName)
	{
		this.i = i;

		File folder = this.i.getDataFolder();
		if (!folder.isDirectory())
		{
			folder.mkdirs();
		}

		this.file = new File(this.i.getDataFolder(), fileName);

		if (!file.exists())
		{
			final InputStream input = i.getClass().getResourceAsStream("/resources/" + fileName);

			try (final OutputStream output = new FileOutputStream(file))
			{
				int read;
				byte[] bytes = new byte[1024];

				while ((read = input.read(bytes)) != -1)
				{
					output.write(bytes, 0, read);
				}

				output.close();
			}
			catch (Exception exception) { exception.printStackTrace(); }
		}

		this.cns();
	}

	private void cns()
	{
		this.config = YamlConfiguration.loadConfiguration(this.file);
		config.options().copyDefaults(true);
		try
		{
			config.save(file);
		}
		catch (IOException exception) { exception.printStackTrace(); }
		this.populate();
	}

	private void populate()
	{
		this.setTranslateColors(this.config.getBoolean("translateColors"));

		Set<String> colors = new HashSet<String>();
		for (String str : this.config.getStringList("blockedColors"))
		{
			colors.add(ChatColor.valueOf(str).toString());
		}
		this.blockedColors = colors;
	}


	private boolean translateColors = true;
	public boolean getTranslateColors() { return this.translateColors; }
	public void setTranslateColors(final boolean newTranslateColors) { this.translateColors = newTranslateColors; }

	private Set<String> blockedColors;
	public Set<String> getBlockedColors() { return this.blockedColors; }


}