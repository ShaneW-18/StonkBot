package stockbot.commands;

import stockbot.objects.Command;
import stockbot.objects.CommandEvent;
import stockbot.objects.WatchListThread;
import stockbot.objects.ReadWrite;
import stockbot.utils.EmbedUtils;
import stockbot.utils.ServerThreads;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

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
                        thread = new WatchListThread(commandEvent.getEvent().getGuild().getId(), commandEvent);
                        thread.start();
                        ServerThreads.servers.put(commandEvent.getEvent().getGuild().getId(), thread);
                  }
            }


            else if (arguments.get(1).equals("stop"))
            {
                  if (ServerThreads.servers.containsKey(commandEvent.getEvent().getGuild().getId()))
                  {
                        ServerThreads.servers.get(commandEvent.getEvent().getGuild().getId()).stop();
                        ServerThreads.servers.remove(commandEvent.getEvent().getGuild().getId());
                  }

                  else
                        EmbedUtils.error(commandEvent.getEvent(), "Watch List Not Up!");

            }


            else
                  error(commandEvent, arguments);


      }

}


