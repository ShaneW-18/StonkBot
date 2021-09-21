package stockbot.objects;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import stockbot.commands.WatchLIst;
import stockbot.objects.ReadWrite;
import stockbot.utils.EmbedUtils;

import java.io.Serializable;
import java.util.Map;
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
      //ReadWrite<Map<String, ScheduledExecutorService>> file;

      public WatchListThread(String ID, CommandEvent event){
            this.event = event;
            this.ID = ID;
            //file  = new ReadWrite("WatchListServers", event.getEvent());
      }

      public void start(){
             scheduler = Executors.newScheduledThreadPool(1);

                              final Runnable beeper = new Runnable()
                              {
                                    public void run()
                                    {
                                          System.out.println();
                                    }
                              };
                              final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 0, 2, TimeUnit.SECONDS);
                        }

      public void stop(){
            scheduler.shutdown();

      }
}
