package stockbot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import stockbot.Constants;
import stockbot.StockBot;

public class MessageEventListener extends ListenerAdapter
{
      private StockBot stockBot;
      public MessageEventListener(StockBot stockBot)
      {
            this.stockBot = stockBot;
      }

      @Override
      public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event)
      {
            if (event.getAuthor().isBot()) return;
            Message message = event.getMessage();
            String content = message.getContentRaw();

            if (content.startsWith(Constants.PREFIX))
            {
                  stockBot.setEvent(event);
                  stockBot.getCommandHandler().handle(event);
            }

      }

}
