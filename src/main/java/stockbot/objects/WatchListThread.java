package stockbot.objects;

import net.dv8tion.jda.api.entities.MessageChannel;
import stockbot.utils.StringUtils;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;

public class WatchListThread implements Runnable
{
      String ID;
      CommandEvent event;
      ArrayList<String> stockList = new ArrayList<>();
      HashMap<String, BigDecimal> h = new HashMap<>();
      DateFormat dateFormat = new SimpleDateFormat("HH:mm");
      String stockSymbol;
      BigDecimal currentChangeInPrice;
      BigDecimal lastChangeInPrice;

      public WatchListThread(String ID, CommandEvent event)
      {
            this.event = event;
            this.ID = ID;
            ReadWrite<ArrayList<String>> read = new ReadWrite<ArrayList<String>>(ID, event.getEvent());
            loadStocks();
            //file  = new ReadWrite("WatchListServers", event.getEvent());
      }

      @Override
      public void run()
      {
            System.out.println("checking");
            //&& (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("09:30")) && dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("16:00"))

            try
            {
                  Date date = new Date();
                  System.out.println(dateFormat.parse(dateFormat.format(date)).getTime());
                  if (StringUtils.getDate().get(Calendar.DAY_OF_WEEK) > 0 && StringUtils.getDate().get(Calendar.DAY_OF_WEEK) < 7 && (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("09:29")) && dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("23:00"))))
                  {
                        if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("09:29")) && dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("09:31")))
                        {
                              event.getEvent().getMessage().getChannel().sendMessage("Market has opened!!!").queue();
                              for (String s : stockList)
                              {
                                    event.getEvent().getMessage().getChannel().sendMessage(YahooFinance.get(s).getName() + " opened at: $" + YahooFinance.get(s).getQuote().getOpen()).queue();
                              }
                        }

                        for (String s : stockList)
                        {
                              stockSymbol = YahooFinance.get(s).getSymbol();
                              currentChangeInPrice = YahooFinance.get(s).getQuote().getChangeInPercent();
                              lastChangeInPrice = h.get(YahooFinance.get(s).getSymbol());
                              if (currentChangeInPrice.subtract(lastChangeInPrice).doubleValue() >= 1 || currentChangeInPrice.subtract(lastChangeInPrice).doubleValue() <= -1)
                              {
                                    event.getEvent().getMessage().getChannel().sendMessage(YahooFinance.get(s).getName() + " is now at $" + YahooFinance.get(s).getQuote().getPrice() + " (" + YahooFinance.get(s).getQuote().getChangeInPercent().round(new MathContext(3)) + "%)").queue();
                                    h.replace(stockSymbol, currentChangeInPrice);
                              }
                        }
                  }
            }
            catch (IOException | ParseException e)
            {
                  e.printStackTrace();
            }

            seeIfAddded();


      }
//            elseif
//            {
//                  try
//                  {
//                        if (StringUtils.getDate().get(Calendar.DAY_OF_WEEK) > 1 && StringUtils.getDate().get(Calendar.DAY_OF_WEEK) < 5 && (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("09:30")) && dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse(":00")){
//                              for (Stock s:stockList){
//                                    event.getEvent().getMessage().getChannel().sendMessage(s.getName() " is at " + s.getQuote().).queue();
//                              }
//                  }
//                  }
//                  catch (ParseException e)
//                  {
//                        e.printStackTrace();
//                  }
//            }

      private void seeIfAddded()
      {
            ReadWrite<ArrayList<String>> read = new ReadWrite<ArrayList<String>>(ID, event.getEvent());
            ArrayList<String> tempList = read.read();
            for (String s : tempList)
            {
                  if (!stockList.contains(s))
                  {
                        try
                        {
                              stockList.add(s);
                              h.put(s, YahooFinance.get(s).getQuote().getChangeInPercent());
                              event.getEvent().getMessage().getChannel().sendMessage("now checking " + YahooFinance.get(s).getName()).queue();

                        }
                        catch (IOException e)
                        {
                              e.printStackTrace();
                        }
                  }
            }
            for (String s : stockList)
            {
                  if (!tempList.contains(s))
                  {
                        try
                        {
                              stockList.remove(s);
                              h.remove(s);
                              event.getEvent().getMessage().getChannel().sendMessage("Not checking " + YahooFinance.get(s).getName() + " anymore").queue();
                        }
                        catch (IOException e)
                        {
                              e.printStackTrace();
                        }


                  }
            }
      }

      private void loadStocks()
      {
            ReadWrite<ArrayList<String>> read = new ReadWrite<ArrayList<String>>(ID, event.getEvent());
            ArrayList<String> r = read.read();
            for (String s : r)
            {
                  try
                  {
                        stockList.add(s);
                        h.put(s, YahooFinance.get(s).getQuote().getChangeInPercent());

                  }
                  catch (IOException e)
                  {
                        e.printStackTrace();
                  }
            }
      }

}