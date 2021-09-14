package stockbot.objects;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.io.FileNotFoundException;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public abstract class Command
{
      private String name;
      private String description;
      private String tutorial;
      private List<Permission> permissions;
      private List<Permission> botPermissions;
      private List<String> aliases;

      public Command(String name, String description, String tutorial){
            this.name = name;
            this.description = description;
            this.tutorial = tutorial;
            this.permissions = new ArrayList<>();
            this.botPermissions = new ArrayList<>();
            this.aliases = new ArrayList<>();
      }

      public String getName()
      {
            return name;
      }

      public String getDescription()
      {
            return description;
      }

      public String getTutorial()
      {
            return tutorial;
      }

      public void setTutorial(String tutorial)
      {
            this.tutorial = tutorial;
      }

      public List<String> getAliases()
      {
            return aliases;
      }

      public void addAliases(String...call){
            aliases.addAll(List.of(call));
      }
      public void addPermissions(Permission...permissions){
            this.permissions.addAll(List.of(permissions));
      }
      public void addBotPermissions(Permission...permissions){
            this.botPermissions.addAll(List.of(permissions));
      }
      public void process(CommandEvent commandEvent){
            if(commandEvent.getEvent().getAuthor().isBot()){
                  return;
            }
            run(commandEvent, commandEvent.getArguments());
      }
      public abstract void run(CommandEvent commandEvent, List<String> arguments);
      protected void error(CommandEvent commandEvent, List<String> arguments){
            commandEvent.getEvent().getChannel().sendMessage(new EmbedBuilder()
                    .setColor(Color.RED)
                    .addField("⚠Error⚠", "Stock Does Not Exist", true)
                    .build()).queue();
      }


}
