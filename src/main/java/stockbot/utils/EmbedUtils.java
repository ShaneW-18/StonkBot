package stockbot.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import stockbot.handlers.CommandCooldownHandler;
import stockbot.objects.CommandEvent;

import java.awt.*;
import java.util.List;

public class EmbedUtils
{
      public static void sendIsOnCooldown(CommandEvent event)
      {
            int cooldownTime = CommandCooldownHandler.getCooldownTime(event.getMember(), event.getCommand());
            event.getEvent().getMessage().getChannel().sendMessage("You are on a cooldown \nYou have [" + cooldownTime + "] ***second(s)***").queue();
      }
      public static void error(GuildMessageReceivedEvent commandEvent, String message){
            commandEvent.getChannel().sendMessage(new EmbedBuilder()
                    .setColor(Color.RED)
                    .addField("⚠Error⚠", message, true)
                    .build()).queue();
      }
}
