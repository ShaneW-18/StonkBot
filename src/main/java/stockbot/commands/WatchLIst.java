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
      private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

      public WatchLIst()
      {
            super("WatchList ", "Starts or stops the watchList notifications", "$watchlist (start/stop)");
            addAliases("watchlist", "w");
      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {
           WatchlistCheck.start(arguments.get(1));

      }

}


