package stockbot.objects;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ButtonInteraction;

import java.util.List;

public abstract class Button
{
      String name;
      String id;
      ButtonClickEvent event;
      public Button(String name, String id, ButtonClickEvent event){
            this.event = event;
            this.id = id;
            this.name = name;
      }
      public void process(ButtonClickEvent event, GuildMessageReceivedEvent originalEvent, ButtonEvent buttonEvent){
            if(event.getUser().isBot()) return;
            if(event.getInteraction().getUser().equals(originalEvent.getMessage().getAuthor()))
                  run(event, buttonEvent, originalEvent);
      }
      public abstract void run(ButtonClickEvent event, ButtonEvent buttonEvent, GuildMessageReceivedEvent originalEvent);

      public String getId()
      {
            return id;
      }

      public String getName()
      {
            return name;
      }
}
