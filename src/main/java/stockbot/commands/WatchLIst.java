package stockbot.commands;

import stockbot.objects.*;
import stockbot.utils.EmbedUtils;
import stockbot.utils.ServerThreads;

import java.util.List;
import java.util.concurrent.*;

public class WatchLIst extends Command
{
      //commandEvent.getEvent().getGuild().getId()
      WatchListThread thread;

      public WatchLIst()
      {
            super("WatchList ", "Starts or stops the watchList notifications", "$watchlist (start/stop)");
            addAliases("watchlist", "w");
      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {

            if (arguments.get(1).equals("start"))
            {
                  if (ServerThreads.servers.containsKey(commandEvent.getEvent().getGuild().getId()))
                        EmbedUtils.error(commandEvent.getEvent(), "Watch List Is Already Up!");
                  else
                  {
                        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                        WatchListThread w = new WatchListThread(commandEvent.getEvent().getGuild().getId(), commandEvent);
                        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(w, 0, 50, TimeUnit.SECONDS);
                        ServerThreads.servers.put(commandEvent.getEvent().getGuild().getId(), scheduler);
                  }
//                  {
////                        Thread thread = new Thread(new WatchListThread2(commandEvent.getEvent().getGuild().getId(),commandEvent));
////                        thread.start();
//
//                  }
            }


            else if (arguments.get(1).equals("stop"))
            {
                  if (ServerThreads.servers.containsKey(commandEvent.getEvent().getGuild().getId()))
                  {
                        ServerThreads.servers.get(commandEvent.getEvent().getGuild().getId()).shutdown();
                        ServerThreads.servers.remove(commandEvent.getEvent().getGuild().getId());
                  }

                  else
                        EmbedUtils.error(commandEvent.getEvent(), "Watch List Not Up!");

            }


            else
                  error(commandEvent, arguments);


      }

}


