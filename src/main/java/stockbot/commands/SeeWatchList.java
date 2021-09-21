package stockbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import stockbot.objects.Command;
import stockbot.objects.CommandEvent;
import stockbot.objects.ReadWrite;
import stockbot.utils.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SeeWatchList extends Command
{
      public SeeWatchList()
      {
            super("SeeWatchLists", "See a List of stocks added to the watchlist for the server", "Type: $SeeWatchList ");
            addAliases("s", "seeWatchList");

      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {
            ReadWrite stocks = new ReadWrite<ArrayList<String>>(commandEvent.getEvent().getGuild().getId(), commandEvent.getEvent());
            commandEvent.getChannel().sendMessageEmbeds(new EmbedBuilder()
                    .setTitle("WatchList for the server")
                    .setFooter("Stonk Bot", "https://i.imgur.com/ffvUfao.jpeg")
                    .addField("Stock Symbols List: ", stocks.read().toString(),false)
                    .setDescription("On server: " + commandEvent.getEvent().getGuild().getName())
                    .setColor(Color.GREEN)
                    .build()).queue();
      }
}

