package stockbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import stockbot.objects.Command;
import stockbot.objects.CommandEvent;
import stockbot.utils.StringUtils;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StockQuote extends Command
{

      public StockQuote()
      {
            super("StockQuote", "Gets the quote of any stock", "Type $quote (stock symbol)");
            addAliases("quote", "q");
      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {
            Stock stock = null;
            try
            {
                  stock = YahooFinance.get(arguments.get(1));
                  commandEvent.getChannel().sendMessage(new EmbedBuilder()
                          .setColor(Color.GREEN)
                          .setTitle(stock.getName() + "\nQuote: ")
                          .addField("Symbol ", stock.getSymbol(), true)
                          .addField("Price ", "$" + StringUtils.addCommasToNumbers(stock.getQuote().getPrice()), false)
                          .addField("Open today ", "$" + StringUtils.addCommasToNumbers(stock.getQuote().getOpen()), false)
                          .addField("Today's change ", "$" + StringUtils.addCommasToNumbers(stock.getQuote().getChange()), false)
                          .addField("Today's High", "$" + StringUtils.addCommasToNumbers(stock.getQuote().getDayHigh()), true)
                          .addField("Today's Low", "$" + StringUtils.addCommasToNumbers(stock.getQuote().getDayLow()), true)
                          .addField("Market Cap", "$" + stock.getStats().getMarketCap().toPlainString(), false)
                          .addField("Average Volume", stock.getQuote().getAvgVolume().toString(), false)
                          .addField("52 Week High", "$" + StringUtils.addCommasToNumbers(stock.getQuote().getYearHigh()), true)
                          .addField("52 Week Low", "$" + StringUtils.addCommasToNumbers(stock.getQuote().getYearLow()), true)
                          .build()).queue();
            }

            catch (NullPointerException | IOException e)
            {
                        error(commandEvent, arguments);

            }
      }
}
