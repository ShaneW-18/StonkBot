package stockbot.commands;
import net.dv8tion.jda.api.EmbedBuilder;
import org.apache.commons.io.FileUtils;
import stockbot.objects.Command;
import stockbot.objects.CommandEvent;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.awt.*;
import java.io.*;
import java.util.List;

public class RandomStock extends Command
{
      EmbedBuilder eb = new EmbedBuilder();
      public RandomStock(){
            super("RandomStock", "Picks a random stock from the 3,000+ stocks on the NASDAQ.", "Type: $random ");
            addAliases( "r","random");
      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {
            File file = new File("src/main/java/stockbot.utils/Files/NASDAQ.txt");
            if(file.exists()){
                  long lines = 0;
                  Stock stock = null;
                  String data = "";
                  try
                  {
                        FileInputStream fi = new FileInputStream(file);
                  }
                  catch (FileNotFoundException e)
                  {
                        e.printStackTrace();
                  }
                  try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        while (reader.readLine() != null) lines++;
                  } catch (IOException e) {
                        e.printStackTrace();
                  }
                  double line = Math.random() * lines + 1;
                  try
                  {
                        data = FileUtils.readLines(file).get((int)line);
                  }
                  catch (IOException e)
                  {
                        e.printStackTrace();
                  }
                  try
                  {
                        stock = YahooFinance.get(data.substring(0,data.indexOf("\t")));
                  }
                  catch (IOException e)
                  {
                        e.printStackTrace();
                  }
                  eb.setColor(Color.GREEN);
                  eb.setTitle(stock.getName(), "https://finance.yahoo.com/quote/"+stock.getSymbol()+"?p="+stock.getSymbol()+"&.tsrc=fin-srch");
                  eb.addField(stock.getSymbol(),"", true);
                  eb.setThumbnail("https://i.imgur.com/ffvUfao.jpeg");
                  commandEvent.getMessage().getChannel().sendMessage(eb.build()).queue();



            }

      }
}
