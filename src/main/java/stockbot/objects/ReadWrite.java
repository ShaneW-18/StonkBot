package stockbot.objects;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.ArrayList;

public class ReadWrite
{
      GuildMessageReceivedEvent event;
      File file;

      public ReadWrite(String file, GuildMessageReceivedEvent event){
            this.event = event;
            this.file = new File("src/main/java/stockbot/utils/files/" + file + ".dat");
            System.out.println(this.file.exists());
      }
      public void write(ArrayList<String> stock) throws IOException
      {
                  FileOutputStream writeData = new FileOutputStream(file);
                  ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

                  writeStream.writeObject(stock);
                  writeStream.flush();
                  writeStream.close();
      }
      public ArrayList<String> read(){

            try{
                  if(file.exists() && file.length() != 0)
                  {

                        FileInputStream readData = new FileInputStream(file);
                        ObjectInputStream readStream = new ObjectInputStream(readData);

                        ArrayList<String> stocks = (ArrayList<String>) readStream.readObject();
                        readStream.close();
                        return stocks;
                  }

            }catch (Exception e) {
                  e.printStackTrace();
            }
            return null;
      }

}
