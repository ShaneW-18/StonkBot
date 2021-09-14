package stockbot.commands;
import net.dv8tion.jda.api.EmbedBuilder;
import stockbot.objects.Command;
import stockbot.objects.CommandEvent;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import stockbot.objects.GetMovers;
import stockbot.utils.StringUtils;
import yahoofinance.YahooFinance;

public class TopMovers extends Command
{
      public TopMovers()
      {
            super("TopMovers", "Gets the top ten movers of the day", "Type $TopMovers");
            addAliases("t", "TopMovers");
      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {
            GetMovers topGains = new GetMovers("https://finance.yahoo.com/gainers");
            GetMovers topLosers = new GetMovers("https://finance.yahoo.com/losers");

            try
            {
                  commandEvent.getChannel().sendMessageEmbeds(new EmbedBuilder()
                          .setTitle("Top Gainers", "https://finance.yahoo.com/gainers")
                          .addField(YahooFinance.get(topGains.getTopStocks().get(0)).getName(), "[" + topGains.getTopStocks().get(0) + "]" + "(https://finance.yahoo.com/quote/" + topGains.getTopStocks().get(0) + "?p=" + topGains.getTopStocks().get(0) + ")" + ":chart_with_upwards_trend: " + YahooFinance.get(topGains.getTopStocks().get(0)).getQuote().getChangeInPercent() + "%", false)
                          .addField(YahooFinance.get(topGains.getTopStocks().get(1)).getName(), "[" + topGains.getTopStocks().get(1) + "]" + "(https://finance.yahoo.com/quote/" + topGains.getTopStocks().get(1) + "?p=" + topGains.getTopStocks().get(1) + ")" + ":chart_with_upwards_trend: " + YahooFinance.get(topGains.getTopStocks().get(1)).getQuote().getChangeInPercent() + "%", false)
                          .addField(YahooFinance.get(topGains.getTopStocks().get(2)).getName(), "[" + topGains.getTopStocks().get(2) + "]" + "(https://finance.yahoo.com/quote/" + topGains.getTopStocks().get(2) + "?p=" + topGains.getTopStocks().get(2) + ")" + ":chart_with_upwards_trend: " + YahooFinance.get(topGains.getTopStocks().get(2)).getQuote().getChangeInPercent() + "%", false)
                          .addField(YahooFinance.get(topGains.getTopStocks().get(3)).getName(), "[" + topGains.getTopStocks().get(3) + "]" + "(https://finance.yahoo.com/quote/" + topGains.getTopStocks().get(3) + "?p=" + topGains.getTopStocks().get(3) + ")" + ":chart_with_upwards_trend: " + YahooFinance.get(topGains.getTopStocks().get(3)).getQuote().getChangeInPercent() + "%", false)
                          .addField(YahooFinance.get(topGains.getTopStocks().get(4)).getName(), "[" + topGains.getTopStocks().get(4) + "]" + "(https://finance.yahoo.com/quote/" + topGains.getTopStocks().get(4) + "?p=" + topGains.getTopStocks().get(4) + ")" + ":chart_with_upwards_trend: " + YahooFinance.get(topGains.getTopStocks().get(4)).getQuote().getChangeInPercent() + "%", false)
                          .setFooter("Stonk Bot", "https://i.imgur.com/ffvUfao.jpeg")
                          .setDescription("Biggest Gainers on: " + StringUtils.dateFormat())
                          .setColor(Color.GREEN)
                          .build()).queue();
                  commandEvent.getChannel().sendMessageEmbeds(new EmbedBuilder()
                          .setTitle("Top Losers", "https://finance.yahoo.com/gainers")
                          .addField(YahooFinance.get(topLosers.getTopStocks().get(0)).getName(), "[" + topLosers.getTopStocks().get(0) + "]" + "(https://finance.yahoo.com/quote/" + topLosers.getTopStocks().get(0) + "?p=" + topLosers.getTopStocks().get(0) + ")" + ":chart_with_downwards_trend: " + YahooFinance.get(topLosers.getTopStocks().get(0)).getQuote().getChangeInPercent() + "%", false)
                          .addField(YahooFinance.get(topLosers.getTopStocks().get(1)).getName(), "[" + topLosers.getTopStocks().get(1) + "]" + "(https://finance.yahoo.com/quote/" + topLosers.getTopStocks().get(1) + "?p=" + topLosers.getTopStocks().get(1) + ")" + ":chart_with_downwards_trend: " + YahooFinance.get(topLosers.getTopStocks().get(1)).getQuote().getChangeInPercent() + "%", false)
                          .addField(YahooFinance.get(topLosers.getTopStocks().get(2)).getName(), "[" + topLosers.getTopStocks().get(2) + "]" + "(https://finance.yahoo.com/quote/" + topLosers.getTopStocks().get(2) + "?p=" + topLosers.getTopStocks().get(2) + ")" + ":chart_with_downwards_trend: " + YahooFinance.get(topLosers.getTopStocks().get(2)).getQuote().getChangeInPercent() + "%", false)
                          .addField(YahooFinance.get(topLosers.getTopStocks().get(3)).getName(), "[" + topLosers.getTopStocks().get(3) + "]" + "(https://finance.yahoo.com/quote/" + topLosers.getTopStocks().get(3) + "?p=" + topLosers.getTopStocks().get(3) + ")" + ":chart_with_downwards_trend: " + YahooFinance.get(topLosers.getTopStocks().get(3)).getQuote().getChangeInPercent() + "%", false)
                          .addField(YahooFinance.get(topLosers.getTopStocks().get(4)).getName(), "[" + topLosers.getTopStocks().get(4) + "]" + "(https://finance.yahoo.com/quote/" + topLosers.getTopStocks().get(4) + "?p=" + topLosers.getTopStocks().get(4) + ")" + ":chart_with_downwards_trend: " + YahooFinance.get(topLosers.getTopStocks().get(4)).getQuote().getChangeInPercent() + "%", false)
                          .setFooter("Stonk Bot", "https://i.imgur.com/ffvUfao.jpeg")
                          .setDescription("Biggest Losers on: " + StringUtils.dateFormat())
                          .setColor(Color.GREEN)
                          .build()).queue();

            }
            catch (IOException e)
            {
                  e.printStackTrace();
            }


//            for (String s : topGains)
//            {
//                  commandEvent.getMessage().getChannel().sendMessage(s).queue();
//            }

      }
}
