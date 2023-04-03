package org.hsqldb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.types.TimestampData;

public class HsqlDateTime {
  private static Locale defaultLocale = Locale.UK;
  
  private static long currentDateMillis;
  
  public static final Calendar tempCalDefault = new GregorianCalendar();
  
  public static final Calendar tempCalGMT = new GregorianCalendar(TimeZone.getTimeZone("GMT"), defaultLocale);
  
  private static final Date tempDate = new Date(0L);
  
  private static final String sdfdPattern = "yyyy-MM-dd";
  
  static SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
  
  private static final String sdftPattern = "HH:mm:ss";
  
  static SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
  
  private static final String sdftsPattern = "yyyy-MM-dd HH:mm:ss";
  
  static SimpleDateFormat sdfts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  private static final String sdftsSysPattern = "yyyy-MM-dd HH:mm:ss.SSS";
  
  static SimpleDateFormat sdftsSys = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  
  private static Date sysDate;
  
  private static final char[][] dateTokens;
  
  private static final String[] javaDateTokens;
  
  private static final int[] sqlIntervalCodes;
  
  private static final char e = '￿';
  
  public static long getDateSeconds(String paramString) {
    try {
      synchronized (sdfd) {
        Date date = sdfd.parse(paramString);
        return date.getTime() / 1000L;
      } 
    } catch (Exception exception) {
      throw Error.error(3407);
    } 
  }
  
  public static String getDateString(long paramLong) {
    synchronized (sdfd) {
      sysDate.setTime(paramLong * 1000L);
      return sdfd.format(sysDate);
    } 
  }
  
  public static long getTimestampSeconds(String paramString) {
    try {
      synchronized (sdfts) {
        Date date = sdfts.parse(paramString);
        return date.getTime() / 1000L;
      } 
    } catch (Exception exception) {
      throw Error.error(3407);
    } 
  }
  
  public static void getTimestampString(StringBuffer paramStringBuffer, long paramLong, int paramInt1, int paramInt2) {
    synchronized (sdfts) {
      tempDate.setTime(paramLong * 1000L);
      paramStringBuffer.append(sdfts.format(tempDate));
      if (paramInt2 > 0) {
        paramStringBuffer.append('.');
        paramStringBuffer.append(StringUtil.toZeroPaddedString(paramInt1, 9, paramInt2));
      } 
    } 
  }
  
  public static String getTimestampString(long paramLong) {
    synchronized (sdfts) {
      sysDate.setTime(paramLong);
      return sdfts.format(sysDate);
    } 
  }
  
  public static synchronized long getCurrentDateMillis(long paramLong) {
    if (paramLong - currentDateMillis >= 86400000L)
      currentDateMillis = getNormalisedDate(paramLong); 
    return currentDateMillis;
  }
  
  public static String getSystemTimeString() {
    synchronized (sdftsSys) {
      sysDate.setTime(System.currentTimeMillis());
      return sdftsSys.format(sysDate);
    } 
  }
  
  private static void resetToDate(Calendar paramCalendar) {
    paramCalendar.set(11, 0);
    paramCalendar.set(12, 0);
    paramCalendar.set(13, 0);
    paramCalendar.set(14, 0);
  }
  
  private static void resetToTime(Calendar paramCalendar) {
    paramCalendar.set(1, 1970);
    paramCalendar.set(2, 0);
    paramCalendar.set(5, 1);
    paramCalendar.set(14, 0);
  }
  
  public static long convertMillisToCalendar(Calendar paramCalendar, long paramLong) {
    synchronized (tempCalGMT) {
      synchronized (paramCalendar) {
        paramCalendar.clear();
        tempCalGMT.setTimeInMillis(paramLong);
        paramCalendar.set(tempCalGMT.get(1), tempCalGMT.get(2), tempCalGMT.get(5), tempCalGMT.get(11), tempCalGMT.get(12), tempCalGMT.get(13));
        return paramCalendar.getTimeInMillis();
      } 
    } 
  }
  
  public static long convertMillisFromCalendar(Calendar paramCalendar, long paramLong) {
    synchronized (tempCalGMT) {
      synchronized (paramCalendar) {
        tempCalGMT.clear();
        paramCalendar.setTimeInMillis(paramLong);
        tempCalGMT.set(paramCalendar.get(1), paramCalendar.get(2), paramCalendar.get(5), paramCalendar.get(11), paramCalendar.get(12), paramCalendar.get(13));
        return tempCalGMT.getTimeInMillis();
      } 
    } 
  }
  
  public static void setTimeInMillis(Calendar paramCalendar, long paramLong) {
    paramCalendar.setTimeInMillis(paramLong);
  }
  
  public static long getTimeInMillis(Calendar paramCalendar) {
    return paramCalendar.getTimeInMillis();
  }
  
  public static long convertToNormalisedTime(long paramLong) {
    return convertToNormalisedTime(paramLong, tempCalGMT);
  }
  
  public static long convertToNormalisedTime(long paramLong, Calendar paramCalendar) {
    synchronized (paramCalendar) {
      setTimeInMillis(paramCalendar, paramLong);
      resetToDate(paramCalendar);
      long l = getTimeInMillis(paramCalendar);
      return paramLong - l;
    } 
  }
  
  public static long convertToNormalisedDate(long paramLong, Calendar paramCalendar) {
    synchronized (paramCalendar) {
      setTimeInMillis(paramCalendar, paramLong);
      resetToDate(paramCalendar);
      return getTimeInMillis(paramCalendar);
    } 
  }
  
  public static long getNormalisedTime(long paramLong) {
    Calendar calendar = tempCalGMT;
    synchronized (calendar) {
      setTimeInMillis(calendar, paramLong);
      resetToTime(calendar);
      return getTimeInMillis(calendar);
    } 
  }
  
  public static long getNormalisedTime(Calendar paramCalendar, long paramLong) {
    synchronized (paramCalendar) {
      setTimeInMillis(paramCalendar, paramLong);
      resetToTime(paramCalendar);
      return getTimeInMillis(paramCalendar);
    } 
  }
  
  public static long getNormalisedDate(long paramLong) {
    synchronized (tempCalGMT) {
      setTimeInMillis(tempCalGMT, paramLong);
      resetToDate(tempCalGMT);
      return getTimeInMillis(tempCalGMT);
    } 
  }
  
  public static long getNormalisedDate(Calendar paramCalendar, long paramLong) {
    synchronized (paramCalendar) {
      setTimeInMillis(paramCalendar, paramLong);
      resetToDate(paramCalendar);
      return getTimeInMillis(paramCalendar);
    } 
  }
  
  public static int getZoneSeconds(Calendar paramCalendar) {
    return (paramCalendar.get(15) + paramCalendar.get(16)) / 1000;
  }
  
  public static int getZoneMillis(Calendar paramCalendar, long paramLong) {
    return paramCalendar.getTimeZone().getOffset(paramLong);
  }
  
  public static int getDateTimePart(long paramLong, int paramInt) {
    synchronized (tempCalGMT) {
      tempCalGMT.setTimeInMillis(paramLong);
      return tempCalGMT.get(paramInt);
    } 
  }
  
  public static long getTruncatedPart(long paramLong, int paramInt) {
    synchronized (tempCalGMT) {
      int i;
      tempCalGMT.setTimeInMillis(paramLong);
      switch (paramInt) {
        case 262:
          i = tempCalGMT.get(7);
          tempCalGMT.add(6, 1 - i);
          resetToDate(tempCalGMT);
          return tempCalGMT.getTimeInMillis();
      } 
      zeroFromPart(tempCalGMT, paramInt);
      return tempCalGMT.getTimeInMillis();
    } 
  }
  
  public static long getRoundedPart(long paramLong, int paramInt) {
    synchronized (tempCalGMT) {
      int i;
      int j;
      int k;
      int m;
      tempCalGMT.setTimeInMillis(paramLong);
      switch (paramInt) {
        case 101:
          if (tempCalGMT.get(2) > 6)
            tempCalGMT.add(1, 1); 
          break;
        case 102:
          if (tempCalGMT.get(5) > 15)
            tempCalGMT.add(2, 1); 
          break;
        case 103:
          if (tempCalGMT.get(11) > 11)
            tempCalGMT.add(5, 1); 
          break;
        case 104:
          if (tempCalGMT.get(12) > 29)
            tempCalGMT.add(11, 1); 
          break;
        case 105:
          if (tempCalGMT.get(13) > 29)
            tempCalGMT.add(12, 1); 
          break;
        case 106:
          if (tempCalGMT.get(14) > 499)
            tempCalGMT.add(13, 1); 
          break;
        case 262:
          i = tempCalGMT.get(6);
          j = tempCalGMT.get(1);
          k = tempCalGMT.get(3);
          m = tempCalGMT.get(7);
          tempCalGMT.clear();
          tempCalGMT.set(1, j);
          if (m > 3)
            k++; 
          if (k == 1 && (i > 356 || i < 7)) {
            tempCalGMT.set(6, i);
            while (true) {
              if (tempCalGMT.get(7) == 1)
                return tempCalGMT.getTimeInMillis(); 
              tempCalGMT.add(6, -1);
            } 
          } 
          tempCalGMT.set(3, k);
          return tempCalGMT.getTimeInMillis();
      } 
      zeroFromPart(tempCalGMT, paramInt);
      return tempCalGMT.getTimeInMillis();
    } 
  }
  
  static void zeroFromPart(Calendar paramCalendar, int paramInt) {
    switch (paramInt) {
      case 101:
        paramCalendar.set(2, 0);
      case 102:
        paramCalendar.set(5, 1);
      case 103:
        paramCalendar.set(11, 0);
      case 104:
        paramCalendar.set(12, 0);
      case 105:
        paramCalendar.set(13, 0);
      case 106:
        paramCalendar.set(14, 0);
        break;
    } 
  }
  
  public static TimestampData toDate(String paramString1, String paramString2, SimpleDateFormat paramSimpleDateFormat) {
    Date date;
    String str = toJavaDatePattern(paramString2);
    int i = str.indexOf("*IY");
    if (i >= 0)
      throw Error.error(3472); 
    i = str.indexOf("*WW");
    if (i >= 0)
      throw Error.error(3472); 
    i = str.indexOf("*W");
    if (i >= 0)
      throw Error.error(3472); 
    try {
      paramSimpleDateFormat.applyPattern(str);
      date = paramSimpleDateFormat.parse(paramString1);
    } catch (Exception exception) {
      throw Error.error(3407, exception.toString());
    } 
    int j = (int)(date.getTime() % 1000L) * 1000000;
    return new TimestampData(date.getTime() / 1000L, j, 0);
  }
  
  public static String toFormattedDate(Date paramDate, String paramString, SimpleDateFormat paramSimpleDateFormat) {
    String str1 = toJavaDatePattern(paramString);
    try {
      paramSimpleDateFormat.applyPattern(str1);
    } catch (Exception exception) {
      throw Error.error(3472);
    } 
    String str2 = paramSimpleDateFormat.format(paramDate);
    int i = str2.indexOf("*IY");
    if (i >= 0) {
      Calendar calendar = paramSimpleDateFormat.getCalendar();
      byte b = 3;
      int j = str2.indexOf("*IYYY");
      if (j >= 0) {
        b = 5;
        i = j;
      } 
      int k = calendar.get(1);
      int m = calendar.get(3);
      if (m == 1 && calendar.get(6) > 360)
        k++; 
      String str = String.valueOf(k);
      if (b == 3)
        str = str.substring(str.length() - 2); 
      StringBuilder stringBuilder = new StringBuilder(str2);
      stringBuilder.replace(i, i + b, str);
      str2 = stringBuilder.toString();
    } 
    i = str2.indexOf("*WW");
    if (i >= 0) {
      Calendar calendar = paramSimpleDateFormat.getCalendar();
      byte b = 3;
      int j = calendar.get(6);
      int k = (j - 1) / 7 + 1;
      StringBuilder stringBuilder = new StringBuilder(str2);
      stringBuilder.replace(i, i + b, String.valueOf(k));
      str2 = stringBuilder.toString();
    } 
    i = str2.indexOf("*W");
    if (i >= 0) {
      Calendar calendar = paramSimpleDateFormat.getCalendar();
      byte b = 2;
      int j = calendar.get(5);
      int k = (j - 1) / 7 + 1;
      StringBuilder stringBuilder = new StringBuilder(str2);
      stringBuilder.replace(i, i + b, String.valueOf(k));
      str2 = stringBuilder.toString();
    } 
    return str2;
  }
  
  public static String toJavaDatePattern(String paramString) {
    int i = paramString.length();
    StringBuffer stringBuffer = new StringBuffer(i);
    Tokenizer tokenizer = new Tokenizer();
    for (int j = 0; j <= i; j++) {
      byte b = (j == i) ? 65535 : paramString.charAt(j);
      if (tokenizer.isInQuotes()) {
        if (tokenizer.isQuoteChar(b)) {
          b = 39;
        } else if (b == 39) {
          stringBuffer.append(b);
        } 
        stringBuffer.append(b);
      } else if (!tokenizer.next(b, j)) {
        if (tokenizer.consumed) {
          int k = tokenizer.getLastMatch();
          stringBuffer.append(javaDateTokens[k]);
          j = tokenizer.matchOffset;
        } else if (tokenizer.isQuoteChar(b)) {
          b = 39;
          stringBuffer.append(b);
        } else if (tokenizer.isLiteral(b)) {
          stringBuffer.append(b);
        } else if (b != '￿') {
          throw Error.error(3407, paramString.substring(j));
        } 
        tokenizer.reset();
      } 
    } 
    if (tokenizer.isInQuotes())
      throw Error.error(3407); 
    return stringBuffer.toString();
  }
  
  public static int toStandardIntervalPart(String paramString) {
    int i = paramString.length();
    Tokenizer tokenizer = new Tokenizer();
    for (byte b = 0; b <= i; b++) {
      boolean bool = (b == i) ? true : paramString.charAt(b);
      if (!tokenizer.next(bool, b)) {
        int j = tokenizer.getLastMatch();
        return (j >= 0) ? sqlIntervalCodes[j] : -1;
      } 
    } 
    return -1;
  }
  
  static {
    tempCalGMT.setLenient(false);
    sdfd.setCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"), defaultLocale));
    sdfd.setLenient(false);
    sdft.setCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"), defaultLocale));
    sdft.setLenient(false);
    sdfts.setCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"), defaultLocale));
    sdfts.setLenient(false);
    currentDateMillis = getNormalisedDate(System.currentTimeMillis());
    sysDate = new Date();
    dateTokens = new char[][] { 
        { 'R', 'R', 'R', 'R' }, { 'I', 'Y', 'Y', 'Y' }, { 'Y', 'Y', 'Y', 'Y' }, { 'I', 'Y' }, { 'Y', 'Y' }, { 'B', 'C' }, { 'B', '.', 'C', '.' }, { 'A', 'D' }, { 'A', '.', 'D', '.' }, { 'M', 'O', 'N' }, 
        { 'M', 'O', 'N', 'T', 'H' }, { 'M', 'M' }, { 'D', 'A', 'Y' }, { 'D', 'Y' }, { 'W', 'W' }, { 'I', 'W' }, { 'D', 'D' }, { 'D', 'D', 'D' }, { 'W' }, { 'H', 'H', '2', '4' }, 
        { 'H', 'H', '1', '2' }, { 'H', 'H' }, { 'M', 'I' }, { 'S', 'S' }, { 'A', 'M' }, { 'P', 'M' }, { 'A', '.', 'M', '.' }, { 'P', '.', 'M', '.' }, { 'F', 'F' } };
    javaDateTokens = new String[] { 
        "yyyy", "'*IYYY'", "yyyy", "'*IY'", "yy", "G", "G", "G", "G", "MMM", 
        "MMMMM", "MM", "EEEE", "EE", "'*WW'", "w", "dd", "D", "'*W'", "HH", 
        "KK", "KK", "mm", "ss", "aaa", "aaa", "aaa", "aaa", "S" };
    sqlIntervalCodes = new int[] { 
        -1, -1, 101, -1, 101, -1, -1, -1, -1, 102, 
        102, 102, -1, -1, 262, -1, 103, 103, -1, 104, 
        -1, 104, 105, 106, -1, -1, -1, -1, -1 };
  }
  
  static class Tokenizer {
    private int lastMatched;
    
    private int matchOffset;
    
    private int offset;
    
    private long state;
    
    private boolean consumed;
    
    private boolean isInQuotes;
    
    private boolean matched;
    
    private final char quoteChar = '"';
    
    private final char[] literalChars = defaultLiterals;
    
    private static char[] defaultLiterals = new char[] { ' ', ',', '-', '.', '/', ':', ';' };
    
    char[][] tokens = HsqlDateTime.dateTokens;
    
    public Tokenizer() {
      reset();
    }
    
    public void reset() {
      this.lastMatched = -1;
      this.offset = -1;
      this.state = 0L;
      this.consumed = false;
      this.matched = false;
    }
    
    public int length() {
      return this.offset;
    }
    
    public int getLastMatch() {
      return this.lastMatched;
    }
    
    public boolean isConsumed() {
      return this.consumed;
    }
    
    public boolean wasMatched() {
      return this.matched;
    }
    
    public boolean isInQuotes() {
      return this.isInQuotes;
    }
    
    public boolean isQuoteChar(char param1Char) {
      if (this.quoteChar == param1Char) {
        this.isInQuotes = !this.isInQuotes;
        return true;
      } 
      return false;
    }
    
    public boolean isLiteral(char param1Char) {
      return ArrayUtil.isInSortedArray(param1Char, this.literalChars);
    }
    
    private boolean isZeroBit(int param1Int) {
      return ((this.state & 1L << param1Int) == 0L);
    }
    
    private void setBit(int param1Int) {
      this.state |= 1L << param1Int;
    }
    
    public boolean next(char param1Char, int param1Int) {
      int i = ++this.offset;
      int j = this.offset + 1;
      byte b = 0;
      this.matched = false;
      int k = this.tokens.length;
      while (--k >= 0) {
        if (isZeroBit(k)) {
          if (this.tokens[k][i] == param1Char) {
            if ((this.tokens[k]).length == j) {
              setBit(k);
              this.lastMatched = k;
              this.consumed = true;
              this.matched = true;
              this.matchOffset = param1Int;
              continue;
            } 
            b++;
            continue;
          } 
          setBit(k);
        } 
      } 
      return (b > 0);
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\HsqlDateTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */