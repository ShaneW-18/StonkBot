package stockbot.utils;

import stockbot.commands.WatchLIst;
import stockbot.objects.WatchListThread;

import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;

public class ServerThreads
{
      public static HashMap<String, ScheduledExecutorService> servers = new HashMap<>();
}

