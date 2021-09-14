package stockbot.Buttons;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ButtonInteraction;
import stockbot.objects.Button;
import stockbot.objects.ButtonEvent;
import stockbot.objects.Command;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class next extends Button
{
      EmbedBuilder eb = new EmbedBuilder();
      List<String> commands;
      ButtonClickEvent event;

      public next(List<String> commands,ButtonClickEvent event){
            super("next", event.getId(), event);
            this.commands = commands;
            this.event = event;
      }

      @Override
      public void run(ButtonClickEvent event, ButtonEvent buttonEvent, GuildMessageReceivedEvent originalEvent)
      {
            if(buttonEvent.getStockBot().getCommandHandler().getIndex() >= commands.size()){
                  return;
            }
            eb.setColor(Color.GREEN);
            eb.setTitle("Tutorial");
            eb.addField(commands.get(buttonEvent.getStockBot().getCommandHandler().getIndex()), buttonEvent.getStockBot().getCommandHandler().commands.get(commands.get(buttonEvent.getStockBot().getCommandHandler().getIndex())).getDescription() + "\n" + buttonEvent.getStockBot().getCommandHandler().commands.get(commands.get(buttonEvent.getStockBot().getCommandHandler().getIndex())).getTutorial(), true);
            eb.setThumbnail("https://i.imgur.com/ffvUfao.jpeg");
            Objects.requireNonNull(event.getMessage()).editMessage(eb.build()).queue();
            if(buttonEvent.getStockBot().getCommandHandler().getIndex() != commands.size()-1){
                  buttonEvent.getStockBot().getCommandHandler().setIndex(1);
            }

      }
}
