package stockbot.commands;

import stockbot.objects.Command;
import stockbot.objects.CommandEvent;
import stockbot.objects.ReadWrite;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddToWatchlist extends Command
{

      public AddToWatchlist()
      {
            super("Add to Watchlist", "Adds a stock to the servers watchlist", "$add(stock)");
            addAliases("add");
      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {
            ReadWrite file = new ReadWrite(commandEvent.getEvent().getGuild().getId(), commandEvent.getEvent());
            try
            {
                  YahooFinance.get(arguments.get(1)).getName();

                  ArrayList<String> stocks = file.read();
                  if (stocks != null)
                  {
                        for (String s : stocks)
                        {
                              if (arguments.get(1).equals(s))
                              {
                                    commandEvent.getMessage().getChannel().sendMessage("Already in WatchList").queue();
                                    return;
                              }
                        }
                  }
                  else
                        stocks = new ArrayList<>();
                  stocks.add(arguments.get(1));
                  file.write(stocks);
                  commandEvent.getMessage().getChannel().sendMessage("Success fully added: " + arguments.get(1)).queue();

            }
            catch (IOException | NullPointerException e)
            {
                  error(commandEvent, arguments);
            }

      }
}
