package com.adamedw.colorblocker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.adamedw.colorblocker.configuration.CBConfig;
import com.adamedw.colorblocker.listener.ListenerChat;

public class ColorBlocker extends JavaPlugin {


	public static ColorBlocker i;

	private CBConfig config;


	@Override
	public void onEnable()
	{
		ColorBlocker.i = this;
		ColorBlocker.i.config = new CBConfig(ColorBlocker.i, "config.yml");

		Bukkit.getPluginManager().registerEvents(new ListenerChat(ColorBlocker.i.config), ColorBlocker.i);
	}


}