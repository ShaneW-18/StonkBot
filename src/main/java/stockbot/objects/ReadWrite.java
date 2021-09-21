package stockbot.objects;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.ArrayList;

public class ReadWrite<t>
{
      GuildMessageReceivedEvent event;
      File file;

      public ReadWrite(String file, GuildMessageReceivedEvent event){
            this.event = event;
            this.file =  new File("src/main/java/stockbot/utils/files/" + file + ".dat");
            System.out.println(this.file.exists());
      }
      public void write(t data)
      {
            FileOutputStream writeData = null;
            try
            {
                  writeData = new FileOutputStream(file);

                  ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

                  writeStream.writeObject(data);
                  writeStream.flush();
                  writeStream.close();
            }
            catch (IOException e)
            {
                  e.printStackTrace();
            }
      }
      public t read(){

            try{
                  if(file.exists() && file.length() != 0)
                  {

                        FileInputStream readData = new FileInputStream(file);
                        ObjectInputStream readStream = new ObjectInputStream(readData);

                        t stocks =(t) readStream.readObject();
                        readStream.close();
                        return stocks;
                  }

            }catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

}
