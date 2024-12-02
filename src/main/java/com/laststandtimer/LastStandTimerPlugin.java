package com.laststandtimer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ActorDeath;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.ui.overlay.infobox.Timer;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@PluginDescriptor(
	name = "Last Stand Timer"
)
public class LastStandTimerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ItemManager itemManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Override
	protected void startUp() throws Exception
	{

	}

	@Override
	protected void shutDown() throws Exception
	{

	}

	@Subscribe
	public void onActorDeath(ActorDeath event) {
		if (event.getActor() == client.getLocalPlayer()) {
			final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "/icon.png");
			Timer timer = new Timer(3, ChronoUnit.MINUTES, icon, this);
			infoBoxManager.addInfoBox(timer);
		}
	}
}
