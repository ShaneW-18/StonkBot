package stockbot;

import stockbot.StockBot;

import javax.security.auth.login.LoginException;

public class Main
{
      public static void main(String[] args)
      {
            try
            {
                  StockBot bot = new StockBot();
                  bot.build();

            }
            catch (LoginException e)
            {
                  e.printStackTrace();
            }
      }

}