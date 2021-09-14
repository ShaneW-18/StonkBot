package stockbot;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import stockbot.handlers.CommandHandler;
import stockbot.listeners.ButtonClickEventListener;
import stockbot.listeners.MessageEventListener;
import stockbot.listeners.RemoveMemberEventListener;

import javax.security.auth.login.LoginException;

public class StockBot extends ListenerAdapter
{
      public static EventWaiter eventWaiter = new EventWaiter();
      private final CommandHandler commandHandler;
      private JDA jda;
      private GuildMessageReceivedEvent event;


      public StockBot()
      {
            this.commandHandler = new CommandHandler(this);
      }

      public void build() throws LoginException
      {
            jda = JDABuilder.createDefault(Token.token)
                    .addEventListeners(new MessageEventListener(this))
                    .addEventListeners(eventWaiter)
                    .addEventListeners(new RemoveMemberEventListener())
                    .addEventListeners(new ButtonClickEventListener(this))
                    .build();
      }

      public GuildMessageReceivedEvent getEvent()
      {
            return event;
      }

      public void setEvent(GuildMessageReceivedEvent event)
      {
            this.event = event;
      }

      @Override
      public void onReady(@NotNull ReadyEvent event)
      {
            super.onReady(event);
      }

      public CommandHandler getCommandHandler()
      {
            return commandHandler;
      }

}



