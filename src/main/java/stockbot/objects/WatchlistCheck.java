package stockbot.objects;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class WatchlistCheck
{
      static ScheduledExecutorService scheduler;
      public static void start(String run){
            if(run.equals("start"))
            {
                  if(scheduler == null || scheduler.isShutdown())
                  {
                        scheduler = Executors.newScheduledThreadPool(1);

                        final Runnable beeper = new Runnable()
                        {
                              public void run()
                              {
                                    System.out.println("beep");
                              }
                        };
                        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 0, 2, TimeUnit.SECONDS);
                  }
           }
      }
      public static void stop(String run){
            if(run.equals("stop"))
            {
                  scheduler.shutdown();
            }

      }
}
