package stockbot.objects;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

//s
public class WatchListThread implements Serializable
{
      ScheduledExecutorService scheduler;
      String ID;
      CommandEvent event;
      DateFormat dateFormat = new SimpleDateFormat("HH:mm");
      Date date = new Date();
      HashMap<String, Double> lastPercentage = new HashMap<>();
      HashMap<String, Double> lastPrice = new HashMap<>();
      HashMap<String, Boolean> first = new HashMap<>();
      double openToday, currentPrice, priceChange, lastPriceChange = 0;

      //ReadWrite<Map<String, ScheduledExecutorService>> file;

      public WatchListThread(String ID, CommandEvent event)
      {
            this.event = event;
            this.ID = ID;


            //file  = new ReadWrite("WatchListServers", event.getEvent());
      }

      public void start()
      {
            scheduler = Executors.newScheduledThreadPool(1);
            ReadWrite<ArrayList<String>> file = new ReadWrite(ID, event.getEvent());
            for (String e : file.read()){
                  lastPercentage.put(e,0.0);
            }
            for(String e: file.read()){
                  first.put(e,false);
            }
            for(String e: file.read()){
                  lastPrice.put(e,0.0);
            }
            System.out.println("here");
            final Runnable beeper = new Runnable()
            {
                  public void run()
                  {
                        event.getEvent().getChannel().sendMessage("here").queue();
                        Stock stock;
                        dateFormat.format(date);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
//&& (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("09:30")) && dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("16:00"))
                        try
                        {
                              if (cal.get(Calendar.DAY_OF_WEEK) > 1 && cal.get(Calendar.DAY_OF_WEEK) < 7)
                              {
                                    for (String e : file.read())
                                    {
                                          stock = YahooFinance.get(e);
                                          if(!first.get(e)){
                                                System.out.println(e + "checking from open");
                                                priceChange = getPriceChangeFirst(stock);
                                                if (priceChange >= 2 || priceChange <= -2)
                                                {
                                                      event.getEvent().getChannel().sendMessage(priceChange >= 5.0 ? stock.getName() + " Went up from opening " + Math.round(priceChange * 1000) / 1000.0 + "%" : stock.getName() + " Went Down from opening " + Math.round(priceChange * 1000) / 1000.0 + "%").queue();
                                                      first.replace(e, true);
                                                      lastPrice.replace(e, stock.getQuote().getPrice().doubleValue());
                                                      System.out.println(e +"went up or down from open");
                                                }
                                          }
                                          else{
                                                System.out.println(e +"checking went from open to second check");
                                                lastPriceChange = getPriceChangeSecond(stock, lastPrice.get(e));

                                                if (lastPriceChange >= 2 || lastPriceChange <= -2){
                                                      System.out.println(e +"went up or down after open");
                                                      lastPrice.replace(e, stock.getQuote().getPrice().doubleValue());
                                                      event.getEvent().getChannel().sendMessage(lastPriceChange >= 5.0 ? stock.getName() + " Went up " + Math.round(lastPriceChange * 1000) / 1000.0 + "%" : stock.getName() + " Went Down " + Math.round(lastPriceChange * 1000) / 1000.0 + "%").queue();
                                                }
                                          }
                                    }
                              }
                              else
                                    event.getEvent().getChannel().sendMessage("not in hours").queue();

                        }
                        catch (IOException e)
                        {
                              e.printStackTrace();
                        }
                  }
            };
            final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 0, 1, TimeUnit.SECONDS);
      }

      public void stop()
      {
            scheduler.shutdown();

      }
      public double getPriceChangeFirst(Stock stock){
            openToday = stock.getQuote().getOpen().doubleValue();
            currentPrice = stock.getQuote().getPrice().doubleValue();
            priceChange = (Math.abs((openToday - currentPrice)) / ((openToday + currentPrice) / 2) * 100);
            return priceChange;
      }
      public double getPriceChangeSecond(Stock stock, double lastPrice){
            currentPrice = stock.getQuote().getPrice().doubleValue();
            priceChange = (Math.abs((lastPrice - currentPrice)) / ((lastPrice + currentPrice) / 2) * 100);
            return priceChange;
      }

}
