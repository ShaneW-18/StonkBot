package stockbot.objects;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import stockbot.StockBot;

import java.util.List;

public class CommandEvent
{
      private Command command;
      private List<String> arguments;
      private GuildMessageReceivedEvent event;
      private StockBot stockBot;
      public CommandEvent(StockBot stockBot, Command command, List<String> arguments, GuildMessageReceivedEvent event)
      {
            this.stockBot = stockBot;
            this.command = command;
            this.arguments = arguments;
            this.event = event;
      }

      public StockBot getStockBot()
      {
            return stockBot;
      }

      public Command getCommand()
      {
            return command;
      }

      public List<String> getArguments()
      {
            return arguments;
      }

      public GuildMessageReceivedEvent getEvent()
      {
            return event;
      }
      public Guild getGuild()
      {
            return event.getGuild();
      }

      public TextChannel getTextChannel()
      {
            return event.getChannel();
      }


      public User getUser()
      {
            return event.getAuthor();
      }

      public Member getMember()
      {
            return event.getMember();
      }

      public MessageChannel getChannel()
      {
            return event.getChannel();
      }

      public JDA getJDA()
      {
            return event.getJDA();
      }

      public Message getMessage()
      {
            return event.getMessage();
      }
}
