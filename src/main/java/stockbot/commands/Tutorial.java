package stockbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.components.Button;
import stockbot.objects.Command;
import stockbot.objects.CommandEvent;

import java.awt.*;
import java.util.List;


public class Tutorial extends Command
{
      public Tutorial()
      {
            super("Tutorial", "Gets information about commands", "");
            addAliases("h", "help");
      }

      @Override
      public void run(CommandEvent commandEvent, List<String> arguments)
      {
            EmbedBuilder eb = new EmbedBuilder();
                  commandEvent.getChannel().sendMessage(
                          eb.setColor(Color.GREEN)
                                  .setTitle(commandEvent.getMessage().getAuthor().getName()+" Welcome to Stonk Bot" )
                                  .setDescription("Stonk Bot gets all it's stock needs and updates from Yahoo Finance")
                                  .addField("Tutorial","Stonk Bot is used to get all your basic stock \nneeds with the help of some useful commands", false)
                                  .setFooter("Stonk Bot", "https://i.imgur.com/ffvUfao.jpeg")
                                  .setImage("https://i.imgur.com/ffvUfao.jpeg")
                                  .build())
                          .setActionRow(net.dv8tion.jda.api.interactions.components.Button.primary("prev","Previous"),
                                  net.dv8tion.jda.api.interactions.components.Button.danger("exit", "Exit"),
                                  Button.success("next","Next"))
                          .queue();
            }

}