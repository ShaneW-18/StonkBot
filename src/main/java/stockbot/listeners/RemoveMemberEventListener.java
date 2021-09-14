package stockbot.listeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class RemoveMemberEventListener extends ListenerAdapter
{
      @Override
      public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event)
      {
            super.onGuildMemberRemove(event);
      }
}
