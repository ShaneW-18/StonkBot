package stockbot.objects;

import net.dv8tion.jda.api.interactions.components.ButtonInteraction;
import stockbot.StockBot;
import stockbot.objects.Button;
import stockbot.objects.Command;
import stockbot.objects.CommandEvent;

import java.util.List;
import java.util.Map;

public class ButtonEvent
{
      ButtonInteraction commandEvent;
      Button button;
      StockBot stockBot;

      public ButtonEvent(ButtonInteraction commandEvent,  StockBot stockBot, Button button){
            this.commandEvent = commandEvent;
            this.button = button;
            this.stockBot = stockBot;
      }

      public StockBot getStockBot()
      {
            return stockBot;
      }

      public ButtonInteraction getCommandEvent()
      {
            return commandEvent;
      }

      public Button getButton()
      {
            return button;
      }
}
