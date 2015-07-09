package com.adamedw.colorblocker.listener;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.adamedw.colorblocker.configuration.CBConfig;

public class ListenerChat implements Listener {


	CBConfig conf;
	public ListenerChat(CBConfig conf)
	{
		this.conf = conf;
	};


	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent event)
	{
		String message = event.getMessage();
		Player player = event.getPlayer();
		if (this.conf.getTranslateColors() && player.hasPermission("colorblocker.usecolor"))
		{
			message = ChatColor.translateAlternateColorCodes('&', message);
		}
		if (!player.hasPermission("colorblocker.bypass"))
		{
			for (String str : this.conf.getBlockedColors())
			{
				message = message.replace(str, "");
			}
		}
		event.setMessage(message);
	}


}