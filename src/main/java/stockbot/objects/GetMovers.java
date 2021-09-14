package stockbot.objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GetMovers
{
      private final String url;
      private LinkedList<String> topStocks;

      public GetMovers(String url){
            this.url = url;
            this.topStocks = getTopMovers();
      }
      public LinkedList<String> getTopMovers(){
            topStocks = new LinkedList<>();
            Document page = null;

            try
            {
                  page = Jsoup.connect(url).get();
                  Elements pageElementsTop = page.select("a[data-test]");
                  for (Element e : pageElementsTop)
                  {
                        topStocks.add(e.text());
                  }

            }
            catch (IOException e)
            {
                  e.printStackTrace();
            }
            return topStocks;

      }

      public LinkedList<String> getTopStocks()
      {
            return topStocks;
      }
}
