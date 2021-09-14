package stockbot.objects;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class WatchlistCheck
{
      private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

      public static void start(String run){
//            if(run.equals("start"))
//            {
//                  if(scheduler.isShutdown()){
//                       scheduler.
//                  }
                  final Runnable beeper = new Runnable()
                  {
                        public void run()
                        {
                              System.out.println("beep");
                        }
                  };
                  final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 0, 2, TimeUnit.SECONDS);
//            }
            if(run.equals("stop")){
                  scheduler.schedule(new Runnable() {
                        public void run() { beeperHandle.cancel(true); }
                  }, 1, TimeUnit.SECONDS);
            }
      }
      public static void stop(){

      }
}
