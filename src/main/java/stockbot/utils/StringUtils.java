package stockbot.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StringUtils
{
      public static String addCommasToNumbers(BigDecimal number)
      {
            NumberFormat nf = NumberFormat.getInstance(Locale.US);
            return nf.format(number);
      }

      public static String getDateTime()
      {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return (dateFormat.format(new Date()));
      }

      public static Calendar getDate()
      {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            dateFormat.format(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
      }
}
