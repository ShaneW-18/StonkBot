package stockbot.commands;

import stockbot.objects.Command;
import stockbot.objects.CommandEvent;
import stockbot.objects.ReadWrite;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemoveFromWatchList extends Command
{
      public RemoveFromWatchList(){
            super("RemoveFromWatchList", "Removes a stock from the WatchList from the server", "Type: $remove(stock symbol)");
            addAliases( "r","remove");
      }
      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {
            try
            {
                  ReadWrite<ArrayList<String>> read = new ReadWrite(commandEvent.getEvent().getGuild().getId(), commandEvent.getEvent());
                  ArrayList<String> stocks = read.read();
                  if (stocks.remove(arguments.get(1)))
                  {
                        commandEvent.getMessage().getChannel().sendMessage("Successfully removed: " + YahooFinance.get(arguments.get(1)).getSymbol()).queue();

                        read.write(stocks);
                  }


                  else
                        commandEvent.getMessage().getChannel().sendMessage("Failed to remove : " + YahooFinance.get(arguments.get(1)).getSymbol()).queue();
            }
            catch (IOException | NullPointerException e)
            {
                  error(commandEvent, arguments);
                  e.printStackTrace();
            }
      }
}
