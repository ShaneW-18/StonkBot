package stockbot.handlers;


import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import stockbot.Buttons.Exit;
import stockbot.Buttons.Previous;
import stockbot.Buttons.next;
import stockbot.Constants;
import stockbot.StockBot;
import stockbot.commands.*;
import stockbot.objects.ButtonEvent;
import stockbot.objects.Button;
import stockbot.objects.Command;
import stockbot.objects.CommandEvent;

import java.util.*;
import java.util.stream.Collectors;

public class CommandHandler
{
      private static int index = 0;
      public Map<String, Command> commands;
      private StockBot stockBot;

      public CommandHandler(StockBot stockBot)

      {
            this.stockBot = stockBot;
            this.commands = getCommands();
      }

      public Map<String, Command> getCommands()
      {
            List<Command> commands = List.of(
                    new StockPrice(),
                    new StockQuote(),
                    new Tutorial(),
                    new RandomStock(),
                    new TopMovers(),
                    new AddToWatchlist(),
                    new SeeWatchList(),
                    new RemoveFromWatchList(),
                    new WatchLIst()
            );
            Map<String, Command> mapOfCommands = new HashMap<>();
            for (Command c : commands)
            {
                  mapOfCommands.put(c.getName(), c);
                  for (String a : c.getAliases())
                  {
                        mapOfCommands.put(a, c);
                  }
            }
            return mapOfCommands;
      }

      public void handle(GuildMessageReceivedEvent event)
      {
            Message message = event.getMessage();
            String content = message.getContentRaw();
            content = content.substring(Constants.PREFIX.length());
            List<String> c = Arrays.asList(content.split("\\s+"));
            String call = c.get(0);
            Command command = commands.get(call);
            if (command == null)
            {
                  return;
            }
            CommandEvent commandEvent = new CommandEvent(stockBot, command, c, event);
            command.process(commandEvent);
      }

      public void handleButton(ButtonClickEvent event)
      {
            List<String> l = stockBot.getCommandHandler().commands.keySet().stream()
                    .filter(x -> x.length() > 8)
                    .collect(Collectors.toList());
            List<Button> buttons = List.of(
                    new next(l, event),
                    new Previous(l, event),
                    new Exit(event)
            );
            Map<String, Button> mapOfButtons = new HashMap<>();
            for (Button b : buttons)
            {
                  mapOfButtons.put(b.getName(), b);
            }
            Button button = mapOfButtons.get(event.getComponentId());
            if (button == null) return;
            ButtonEvent buttonEvent = new ButtonEvent(event, stockBot, button);
            button.process(event, stockBot.getEvent(), buttonEvent);
      }

      public int getIndex()
      {
            return index;
      }
      public void setIndex(int num){
            index = index + num;
      }
      public void clearIndex(){
            index = 0;
      }

}
