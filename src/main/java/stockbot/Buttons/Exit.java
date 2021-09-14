package stockbot.Buttons;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import stockbot.objects.Button;
import stockbot.objects.ButtonEvent;

import java.util.List;
import java.util.Objects;

public class Exit extends Button
{
      public Exit(ButtonClickEvent event){
            super("exit", event.getId(), event);
      }
      @Override
      public void run(ButtonClickEvent event, ButtonEvent buttonEvent, GuildMessageReceivedEvent originalEvent)
      {
            Objects.requireNonNull(event.getMessage()).delete().queue();
            originalEvent.getMessage().delete().queue();
      }
}
