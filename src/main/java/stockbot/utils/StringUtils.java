package stockbot.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtils
{
      public static String addCommasToNumbers(BigDecimal number)
      {
            NumberFormat nf = NumberFormat.getInstance(Locale.US);
            return nf.format(number);
      }
      public static String dateFormat(){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return(dateFormat.format(new Date()));
      }
}
