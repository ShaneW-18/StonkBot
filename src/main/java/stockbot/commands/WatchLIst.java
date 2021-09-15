package stockbot.commands;

import stockbot.objects.Command;
import stockbot.objects.CommandEvent;
import stockbot.objects.WatchlistCheck;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WatchLIst extends Command
{


      public WatchLIst()
      {
            super("WatchList ", "Starts or stops the watchList notifications", "$watchlist (start/stop)");
            addAliases("watchlist", "w");
      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {

            if (arguments.get(1).equals("start"))
                  WatchlistCheck.start(arguments.get(1));

            else if (arguments.get(1).equals("stop"))
                  WatchlistCheck.stop(arguments.get(1));

            else
                  error(commandEvent,arguments);


      }

}


