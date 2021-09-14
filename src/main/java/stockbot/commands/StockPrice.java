package stockbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import stockbot.StockBot;
import stockbot.objects.Command;
import stockbot.objects.CommandEvent;
import stockbot.utils.StringUtils;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StockPrice extends Command
{

      public StockPrice()
      {
            super("StockPrice", "Gets current price of stock.", "Type: $stock(stock symbol) ");
            addAliases( "stonk","stock", "s");
      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {
            try
            {
                  Stock s = YahooFinance.get(arguments.get(1));

                  commandEvent.getChannel().sendMessageEmbeds(new EmbedBuilder()
                          .setColor(Color.green)
                          .addField(s.toString(), s.getCurrency() + "\n", true)
                          .setThumbnail("https://scontent-iad3-2.xx.fbcdn.net/v/t1.18169-9/18893237_1576454562398650_2487229831759873167_n.png?_nc_cat=111&ccb=1-3&_nc_sid=9267fe&_nc_ohc=UtR23ISzooYAX-5KMAc&_nc_ht=scontent-iad3-2.xx&oh=b2619b4dfba4228a00ef68e693b5038b&oe=60F3681E")
                          .setTitle(s.getName(), "https://finance.yahoo.com/quote/" + s.getSymbol() + "?p=INTC&.tsrc=fin-srch")
                          .setFooter(StringUtils.dateFormat())
                          .build()).queue(onMessage ->
                  {
                        String checkMark = "✅";
                        onMessage.addReaction(checkMark).queue();


                        var eventWaiter = StockBot.eventWaiter;


                        eventWaiter.waitForEvent(MessageReactionAddEvent.class,
                                messageReactionAddEventInConsumer ->
                                        messageReactionAddEventInConsumer.getMessageIdLong() == onMessage.getIdLong()
                                        && messageReactionAddEventInConsumer.getUserIdLong() == commandEvent.getEvent().getAuthor().getIdLong()
                                        && (messageReactionAddEventInConsumer.getReaction().getReactionEmote().getName().equals(checkMark)),
                                messageReactionAddEventInConsumer ->
                                {

                                      /**
                                       * () -> Supplier / Thread
                                       * (someVariable) -> consumer <---
                                       *
                                       *
                                       * Consumer<String> l =
                                       */

                                      var reactionEmote = messageReactionAddEventInConsumer.getReactionEmote().getName();
                                      if (reactionEmote.equals(checkMark))
                                      {
                                            commandEvent.getStockBot().getCommandHandler().getCommands().get("StockQuote").process(commandEvent);

                                      }
                                });
                  });
            }
            catch (NullPointerException | IOException e)
            {
                  error(commandEvent, arguments);
            }
      }

}