package stockbot.listeners;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ButtonInteraction;
import org.jetbrains.annotations.NotNull;
import stockbot.StockBot;

public class ButtonClickEventListener extends ListenerAdapter
{
      StockBot stockBot;
      public ButtonClickEventListener(StockBot stockBot)
      {
            this.stockBot = stockBot;
      }
      @Override
      public void onButtonClick(@NotNull ButtonClickEvent event)
      {
            event.deferEdit().queue();
            stockBot.getCommandHandler().handleButton(event);
      }

}
