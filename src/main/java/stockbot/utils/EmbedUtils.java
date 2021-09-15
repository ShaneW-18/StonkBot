package stockbot.utils;

import stockbot.handlers.CommandCooldownHandler;
import stockbot.objects.CommandEvent;

public class EmbedUtils
{
      public static void sendIsOnCooldown(CommandEvent event)
      {
            int cooldownTime = CommandCooldownHandler.getCooldownTime(event.getMember(), event.getCommand());
            event.getEvent().getMessage().getChannel().sendMessage("You are on a cooldown \nYou have [" + cooldownTime + "] ***second(s)***").queue();
      }
}
