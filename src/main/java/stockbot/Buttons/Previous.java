package stockbot.Buttons;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import stockbot.objects.Button;
import stockbot.objects.ButtonEvent;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Previous extends Button
{
      EmbedBuilder eb = new EmbedBuilder();
      List<String> commands;
      ButtonClickEvent event;

      public Previous(List<String> commands, ButtonClickEvent event)
      {
            super("prev", event.getId(), event);
            this.commands = commands;
            this.event = event;
      }

      @Override
      public void run(ButtonClickEvent event, ButtonEvent buttonEvent, GuildMessageReceivedEvent originalEvent)
      {
            buttonEvent.getStockBot().getCommandHandler().setIndex(-1);

            if (buttonEvent.getStockBot().getCommandHandler().getIndex() == -1)
            {
                  buttonEvent.getStockBot().getCommandHandler().clearIndex();
                  buttonEvent.getStockBot().getCommandHandler().handle(originalEvent);
            }
            eb.setColor(Color.GREEN);
            eb.setTitle("Tutorial");
            eb.addField(commands.get(buttonEvent.getStockBot().getCommandHandler().getIndex()), buttonEvent.getStockBot().getCommandHandler().commands.get(commands.get(buttonEvent.getStockBot().getCommandHandler().getIndex())).getDescription() + "\n" + buttonEvent.getStockBot().getCommandHandler().commands.get(commands.get(buttonEvent.getStockBot().getCommandHandler().getIndex())).getTutorial(), true);
            eb.setThumbnail("https://i.imgur.com/ffvUfao.jpeg");
            Objects.requireNonNull(event.getMessage()).editMessage(eb.build()).queue();
      }
}
