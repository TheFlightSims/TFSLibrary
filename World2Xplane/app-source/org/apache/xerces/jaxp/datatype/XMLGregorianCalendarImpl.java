package org.apache.xerces.jaxp.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.apache.xerces.util.DatatypeMessageFormatter;

class XMLGregorianCalendarImpl extends XMLGregorianCalendar implements Serializable, Cloneable {
  private BigInteger orig_eon;
  
  private int orig_year = Integer.MIN_VALUE;
  
  private int orig_month = Integer.MIN_VALUE;
  
  private int orig_day = Integer.MIN_VALUE;
  
  private int orig_hour = Integer.MIN_VALUE;
  
  private int orig_minute = Integer.MIN_VALUE;
  
  private int orig_second = Integer.MIN_VALUE;
  
  private BigDecimal orig_fracSeconds;
  
  private int orig_timezone = Integer.MIN_VALUE;
  
  private BigInteger eon = null;
  
  private int year = Integer.MIN_VALUE;
  
  private int month = Integer.MIN_VALUE;
  
  private int day = Integer.MIN_VALUE;
  
  private int timezone = Integer.MIN_VALUE;
  
  private int hour = Integer.MIN_VALUE;
  
  private int minute = Integer.MIN_VALUE;
  
  private int second = Integer.MIN_VALUE;
  
  private BigDecimal fractionalSecond = null;
  
  private static final BigInteger BILLION_B = BigInteger.valueOf(1000000000L);
  
  private static final int BILLION_I = 1000000000;
  
  private static final Date PURE_GREGORIAN_CHANGE = new Date(Long.MIN_VALUE);
  
  private static final int YEAR = 0;
  
  private static final int MONTH = 1;
  
  private static final int DAY = 2;
  
  private static final int HOUR = 3;
  
  private static final int MINUTE = 4;
  
  private static final int SECOND = 5;
  
  private static final int MILLISECOND = 6;
  
  private static final int TIMEZONE = 7;
  
  private static final int[] MIN_FIELD_VALUE = new int[] { Integer.MIN_VALUE, 1, 1, 0, 0, 0, 0, -840 };
  
  private static final int[] MAX_FIELD_VALUE = new int[] { Integer.MAX_VALUE, 12, 31, 23, 59, 60, 999, 840 };
  
  private static final String[] FIELD_NAME = new String[] { "Year", "Month", "Day", "Hour", "Minute", "Second", "Millisecond", "Timezone" };
  
  private static final long serialVersionUID = 1L;
  
  public static final XMLGregorianCalendar LEAP_YEAR_DEFAULT = createDateTime(400, 1, 1, 0, 0, 0, -2147483648, -2147483648);
  
  private static final BigInteger FOUR = BigInteger.valueOf(4L);
  
  private static final BigInteger HUNDRED = BigInteger.valueOf(100L);
  
  private static final BigInteger FOUR_HUNDRED = BigInteger.valueOf(400L);
  
  private static final BigInteger SIXTY = BigInteger.valueOf(60L);
  
  private static final BigInteger TWENTY_FOUR = BigInteger.valueOf(24L);
  
  private static final BigInteger TWELVE = BigInteger.valueOf(12L);
  
  private static final BigDecimal DECIMAL_ZERO = BigDecimal.valueOf(0L);
  
  private static final BigDecimal DECIMAL_ONE = BigDecimal.valueOf(1L);
  
  private static final BigDecimal DECIMAL_SIXTY = BigDecimal.valueOf(60L);
  
  protected XMLGregorianCalendarImpl(String paramString) throws IllegalArgumentException {
    String str1 = null;
    String str2 = paramString;
    int i = str2.length();
    if (str2.indexOf('T') != -1) {
      str1 = "%Y-%M-%DT%h:%m:%s%z";
    } else if (i >= 3 && str2.charAt(2) == ':') {
      str1 = "%h:%m:%s%z";
    } else if (str2.startsWith("--")) {
      if (i >= 3 && str2.charAt(2) == '-') {
        str1 = "---%D%z";
      } else if (i == 4 || (i >= 6 && (str2.charAt(4) == '+' || (str2.charAt(4) == '-' && (str2.charAt(5) == '-' || i == 10))))) {
        str1 = "--%M--%z";
        Parser parser1 = new Parser(str1, str2);
        try {
          parser1.parse();
          if (!isValid())
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCRepresentation", new Object[] { paramString })); 
          save();
          return;
        } catch (IllegalArgumentException illegalArgumentException) {
          str1 = "--%M%z";
        } 
      } else {
        str1 = "--%M-%D%z";
      } 
    } else {
      byte b1 = 0;
      int j = str2.indexOf(':');
      if (j != -1)
        i -= 6; 
      for (byte b2 = 1; b2 < i; b2++) {
        if (str2.charAt(b2) == '-')
          b1++; 
      } 
      if (b1 == 0) {
        str1 = "%Y%z";
      } else if (b1 == 1) {
        str1 = "%Y-%M%z";
      } else {
        str1 = "%Y-%M-%D%z";
      } 
    } 
    Parser parser = new Parser(str1, str2);
    parser.parse();
    if (!isValid())
      throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCRepresentation", new Object[] { paramString })); 
    save();
  }
  
  private void save() {
    this.orig_eon = this.eon;
    this.orig_year = this.year;
    this.orig_month = this.month;
    this.orig_day = this.day;
    this.orig_hour = this.hour;
    this.orig_minute = this.minute;
    this.orig_second = this.second;
    this.orig_fracSeconds = this.fractionalSecond;
    this.orig_timezone = this.timezone;
  }
  
  public XMLGregorianCalendarImpl() {}
  
  protected XMLGregorianCalendarImpl(BigInteger paramBigInteger, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BigDecimal paramBigDecimal, int paramInt6) {
    setYear(paramBigInteger);
    setMonth(paramInt1);
    setDay(paramInt2);
    setTime(paramInt3, paramInt4, paramInt5, paramBigDecimal);
    setTimezone(paramInt6);
    if (!isValid())
      throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCValue-fractional", new Object[] { paramBigInteger, new Integer(paramInt1), new Integer(paramInt2), new Integer(paramInt3), new Integer(paramInt4), new Integer(paramInt5), paramBigDecimal, new Integer(paramInt6) })); 
    save();
  }
  
  private XMLGregorianCalendarImpl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    setYear(paramInt1);
    setMonth(paramInt2);
    setDay(paramInt3);
    setTime(paramInt4, paramInt5, paramInt6);
    setTimezone(paramInt8);
    BigDecimal bigDecimal = null;
    if (paramInt7 != Integer.MIN_VALUE)
      bigDecimal = BigDecimal.valueOf(paramInt7, 3); 
    setFractionalSecond(bigDecimal);
    if (!isValid())
      throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCValue-milli", new Object[] { new Integer(paramInt1), new Integer(paramInt2), new Integer(paramInt3), new Integer(paramInt4), new Integer(paramInt5), new Integer(paramInt6), new Integer(paramInt7), new Integer(paramInt8) })); 
    save();
  }
  
  public XMLGregorianCalendarImpl(GregorianCalendar paramGregorianCalendar) {
    int i = paramGregorianCalendar.get(1);
    if (paramGregorianCalendar.get(0) == 0)
      i = -i; 
    setYear(i);
    setMonth(paramGregorianCalendar.get(2) + 1);
    setDay(paramGregorianCalendar.get(5));
    setTime(paramGregorianCalendar.get(11), paramGregorianCalendar.get(12), paramGregorianCalendar.get(13), paramGregorianCalendar.get(14));
    int j = (paramGregorianCalendar.get(15) + paramGregorianCalendar.get(16)) / 60000;
    setTimezone(j);
    save();
  }
  
  public static XMLGregorianCalendar createDateTime(BigInteger paramBigInteger, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BigDecimal paramBigDecimal, int paramInt6) {
    return new XMLGregorianCalendarImpl(paramBigInteger, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramBigDecimal, paramInt6);
  }
  
  public static XMLGregorianCalendar createDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    return new XMLGregorianCalendarImpl(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, -2147483648, -2147483648);
  }
  
  public static XMLGregorianCalendar createDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    return new XMLGregorianCalendarImpl(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
  }
  
  public static XMLGregorianCalendar createDate(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return new XMLGregorianCalendarImpl(paramInt1, paramInt2, paramInt3, -2147483648, -2147483648, -2147483648, -2147483648, paramInt4);
  }
  
  public static XMLGregorianCalendar createTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return new XMLGregorianCalendarImpl(-2147483648, -2147483648, -2147483648, paramInt1, paramInt2, paramInt3, -2147483648, paramInt4);
  }
  
  public static XMLGregorianCalendar createTime(int paramInt1, int paramInt2, int paramInt3, BigDecimal paramBigDecimal, int paramInt4) {
    return new XMLGregorianCalendarImpl(null, -2147483648, -2147483648, paramInt1, paramInt2, paramInt3, paramBigDecimal, paramInt4);
  }
  
  public static XMLGregorianCalendar createTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    return new XMLGregorianCalendarImpl(-2147483648, -2147483648, -2147483648, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  public BigInteger getEon() {
    return this.eon;
  }
  
  public int getYear() {
    return this.year;
  }
  
  public BigInteger getEonAndYear() {
    return (this.year != Integer.MIN_VALUE && this.eon != null) ? this.eon.add(BigInteger.valueOf(this.year)) : ((this.year != Integer.MIN_VALUE && this.eon == null) ? BigInteger.valueOf(this.year) : null);
  }
  
  public int getMonth() {
    return this.month;
  }
  
  public int getDay() {
    return this.day;
  }
  
  public int getTimezone() {
    return this.timezone;
  }
  
  public int getHour() {
    return this.hour;
  }
  
  public int getMinute() {
    return this.minute;
  }
  
  public int getSecond() {
    return this.second;
  }
  
  private BigDecimal getSeconds() {
    if (this.second == Integer.MIN_VALUE)
      return DECIMAL_ZERO; 
    BigDecimal bigDecimal = BigDecimal.valueOf(this.second);
    return (this.fractionalSecond != null) ? bigDecimal.add(this.fractionalSecond) : bigDecimal;
  }
  
  public int getMillisecond() {
    return (this.fractionalSecond == null) ? Integer.MIN_VALUE : this.fractionalSecond.movePointRight(3).intValue();
  }
  
  public BigDecimal getFractionalSecond() {
    return this.fractionalSecond;
  }
  
  public void setYear(BigInteger paramBigInteger) {
    if (paramBigInteger == null) {
      this.eon = null;
      this.year = Integer.MIN_VALUE;
    } else {
      BigInteger bigInteger = paramBigInteger.remainder(BILLION_B);
      this.year = bigInteger.intValue();
      setEon(paramBigInteger.subtract(bigInteger));
    } 
  }
  
  public void setYear(int paramInt) {
    if (paramInt == Integer.MIN_VALUE) {
      this.year = Integer.MIN_VALUE;
      this.eon = null;
    } else if (Math.abs(paramInt) < 1000000000) {
      this.year = paramInt;
      this.eon = null;
    } else {
      BigInteger bigInteger1 = BigInteger.valueOf(paramInt);
      BigInteger bigInteger2 = bigInteger1.remainder(BILLION_B);
      this.year = bigInteger2.intValue();
      setEon(bigInteger1.subtract(bigInteger2));
    } 
  }
  
  private void setEon(BigInteger paramBigInteger) {
    if (paramBigInteger != null && paramBigInteger.compareTo(BigInteger.ZERO) == 0) {
      this.eon = null;
    } else {
      this.eon = paramBigInteger;
    } 
  }
  
  public void setMonth(int paramInt) {
    checkFieldValueConstraint(1, paramInt);
    this.month = paramInt;
  }
  
  public void setDay(int paramInt) {
    checkFieldValueConstraint(2, paramInt);
    this.day = paramInt;
  }
  
  public void setTimezone(int paramInt) {
    checkFieldValueConstraint(7, paramInt);
    this.timezone = paramInt;
  }
  
  public void setTime(int paramInt1, int paramInt2, int paramInt3) {
    setTime(paramInt1, paramInt2, paramInt3, (BigDecimal)null);
  }
  
  private void checkFieldValueConstraint(int paramInt1, int paramInt2) throws IllegalArgumentException {
    if ((paramInt2 < MIN_FIELD_VALUE[paramInt1] && paramInt2 != Integer.MIN_VALUE) || paramInt2 > MAX_FIELD_VALUE[paramInt1])
      throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidFieldValue", new Object[] { new Integer(paramInt2), FIELD_NAME[paramInt1] })); 
  }
  
  public void setHour(int paramInt) {
    checkFieldValueConstraint(3, paramInt);
    this.hour = paramInt;
  }
  
  public void setMinute(int paramInt) {
    checkFieldValueConstraint(4, paramInt);
    this.minute = paramInt;
  }
  
  public void setSecond(int paramInt) {
    checkFieldValueConstraint(5, paramInt);
    this.second = paramInt;
  }
  
  public void setTime(int paramInt1, int paramInt2, int paramInt3, BigDecimal paramBigDecimal) {
    setHour(paramInt1);
    setMinute(paramInt2);
    setSecond(paramInt3);
    setFractionalSecond(paramBigDecimal);
  }
  
  public void setTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    setHour(paramInt1);
    setMinute(paramInt2);
    setSecond(paramInt3);
    setMillisecond(paramInt4);
  }
  
  public int compare(XMLGregorianCalendar paramXMLGregorianCalendar) {
    int i = 2;
    XMLGregorianCalendarImpl xMLGregorianCalendarImpl = this;
    XMLGregorianCalendar xMLGregorianCalendar1 = paramXMLGregorianCalendar;
    if (xMLGregorianCalendarImpl.getTimezone() == xMLGregorianCalendar1.getTimezone())
      return internalCompare(xMLGregorianCalendarImpl, xMLGregorianCalendar1); 
    if (xMLGregorianCalendarImpl.getTimezone() != Integer.MIN_VALUE && xMLGregorianCalendar1.getTimezone() != Integer.MIN_VALUE) {
      xMLGregorianCalendarImpl = (XMLGregorianCalendarImpl)xMLGregorianCalendarImpl.normalize();
      xMLGregorianCalendar1 = xMLGregorianCalendar1.normalize();
      return internalCompare(xMLGregorianCalendarImpl, xMLGregorianCalendar1);
    } 
    if (xMLGregorianCalendarImpl.getTimezone() != Integer.MIN_VALUE) {
      if (xMLGregorianCalendarImpl.getTimezone() != 0)
        xMLGregorianCalendarImpl = (XMLGregorianCalendarImpl)xMLGregorianCalendarImpl.normalize(); 
      XMLGregorianCalendar xMLGregorianCalendar4 = normalizeToTimezone(xMLGregorianCalendar1, 840);
      i = internalCompare(xMLGregorianCalendarImpl, xMLGregorianCalendar4);
      if (i == -1)
        return i; 
      XMLGregorianCalendar xMLGregorianCalendar5 = normalizeToTimezone(xMLGregorianCalendar1, -840);
      i = internalCompare(xMLGregorianCalendarImpl, xMLGregorianCalendar5);
      return (i == 1) ? i : 2;
    } 
    if (xMLGregorianCalendar1.getTimezone() != 0)
      xMLGregorianCalendar1 = normalizeToTimezone(xMLGregorianCalendar1, xMLGregorianCalendar1.getTimezone()); 
    XMLGregorianCalendar xMLGregorianCalendar2 = normalizeToTimezone(xMLGregorianCalendarImpl, -840);
    i = internalCompare(xMLGregorianCalendar2, xMLGregorianCalendar1);
    if (i == -1)
      return i; 
    XMLGregorianCalendar xMLGregorianCalendar3 = normalizeToTimezone(xMLGregorianCalendarImpl, 840);
    i = internalCompare(xMLGregorianCalendar3, xMLGregorianCalendar1);
    return (i == 1) ? i : 2;
  }
  
  public XMLGregorianCalendar normalize() {
    XMLGregorianCalendar xMLGregorianCalendar = normalizeToTimezone(this, this.timezone);
    if (getTimezone() == Integer.MIN_VALUE)
      xMLGregorianCalendar.setTimezone(-2147483648); 
    if (getMillisecond() == Integer.MIN_VALUE)
      xMLGregorianCalendar.setMillisecond(-2147483648); 
    return xMLGregorianCalendar;
  }
  
  private XMLGregorianCalendar normalizeToTimezone(XMLGregorianCalendar paramXMLGregorianCalendar, int paramInt) {
    int i = paramInt;
    XMLGregorianCalendar xMLGregorianCalendar = (XMLGregorianCalendar)paramXMLGregorianCalendar.clone();
    i = -i;
    DurationImpl durationImpl = new DurationImpl((i >= 0), 0, 0, 0, 0, (i < 0) ? -i : i, 0);
    xMLGregorianCalendar.add(durationImpl);
    xMLGregorianCalendar.setTimezone(0);
    return xMLGregorianCalendar;
  }
  
  private static int internalCompare(XMLGregorianCalendar paramXMLGregorianCalendar1, XMLGregorianCalendar paramXMLGregorianCalendar2) {
    if (paramXMLGregorianCalendar1.getEon() == paramXMLGregorianCalendar2.getEon()) {
      int i = compareField(paramXMLGregorianCalendar1.getYear(), paramXMLGregorianCalendar2.getYear());
      if (i != 0)
        return i; 
    } else {
      int i = compareField(paramXMLGregorianCalendar1.getEonAndYear(), paramXMLGregorianCalendar2.getEonAndYear());
      if (i != 0)
        return i; 
    } 
    null = compareField(paramXMLGregorianCalendar1.getMonth(), paramXMLGregorianCalendar2.getMonth());
    if (null != 0)
      return null; 
    null = compareField(paramXMLGregorianCalendar1.getDay(), paramXMLGregorianCalendar2.getDay());
    if (null != 0)
      return null; 
    null = compareField(paramXMLGregorianCalendar1.getHour(), paramXMLGregorianCalendar2.getHour());
    if (null != 0)
      return null; 
    null = compareField(paramXMLGregorianCalendar1.getMinute(), paramXMLGregorianCalendar2.getMinute());
    if (null != 0)
      return null; 
    null = compareField(paramXMLGregorianCalendar1.getSecond(), paramXMLGregorianCalendar2.getSecond());
    return (null != 0) ? null : compareField(paramXMLGregorianCalendar1.getFractionalSecond(), paramXMLGregorianCalendar2.getFractionalSecond());
  }
  
  private static int compareField(int paramInt1, int paramInt2) {
    return (paramInt1 == paramInt2) ? 0 : ((paramInt1 == Integer.MIN_VALUE || paramInt2 == Integer.MIN_VALUE) ? 2 : ((paramInt1 < paramInt2) ? -1 : 1));
  }
  
  private static int compareField(BigInteger paramBigInteger1, BigInteger paramBigInteger2) {
    return (paramBigInteger1 == null) ? ((paramBigInteger2 == null) ? 0 : 2) : ((paramBigInteger2 == null) ? 2 : paramBigInteger1.compareTo(paramBigInteger2));
  }
  
  private static int compareField(BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2) {
    if (paramBigDecimal1 == paramBigDecimal2)
      return 0; 
    if (paramBigDecimal1 == null)
      paramBigDecimal1 = DECIMAL_ZERO; 
    if (paramBigDecimal2 == null)
      paramBigDecimal2 = DECIMAL_ZERO; 
    return paramBigDecimal1.compareTo(paramBigDecimal2);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject instanceof XMLGregorianCalendar)
      bool = (compare((XMLGregorianCalendar)paramObject) == 0) ? true : false; 
    return bool;
  }
  
  public int hashCode() {
    int i = getTimezone();
    if (i == Integer.MIN_VALUE)
      i = 0; 
    XMLGregorianCalendar xMLGregorianCalendar = this;
    if (i != 0)
      xMLGregorianCalendar = normalizeToTimezone(this, getTimezone()); 
    return xMLGregorianCalendar.getYear() + xMLGregorianCalendar.getMonth() + xMLGregorianCalendar.getDay() + xMLGregorianCalendar.getHour() + xMLGregorianCalendar.getMinute() + xMLGregorianCalendar.getSecond();
  }
  
  public static XMLGregorianCalendar parse(String paramString) {
    return new XMLGregorianCalendarImpl(paramString);
  }
  
  public String toXMLFormat() {
    QName qName = getXMLSchemaType();
    String str = null;
    if (qName == DatatypeConstants.DATETIME) {
      str = "%Y-%M-%DT%h:%m:%s%z";
    } else if (qName == DatatypeConstants.DATE) {
      str = "%Y-%M-%D%z";
    } else if (qName == DatatypeConstants.TIME) {
      str = "%h:%m:%s%z";
    } else if (qName == DatatypeConstants.GMONTH) {
      str = "--%M--%z";
    } else if (qName == DatatypeConstants.GDAY) {
      str = "---%D%z";
    } else if (qName == DatatypeConstants.GYEAR) {
      str = "%Y%z";
    } else if (qName == DatatypeConstants.GYEARMONTH) {
      str = "%Y-%M%z";
    } else if (qName == DatatypeConstants.GMONTHDAY) {
      str = "--%M-%D%z";
    } 
    return format(str);
  }
  
  public QName getXMLSchemaType() {
    if (this.year != Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day != Integer.MIN_VALUE && this.hour != Integer.MIN_VALUE && this.minute != Integer.MIN_VALUE && this.second != Integer.MIN_VALUE)
      return DatatypeConstants.DATETIME; 
    if (this.year != Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day != Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE)
      return DatatypeConstants.DATE; 
    if (this.year == Integer.MIN_VALUE && this.month == Integer.MIN_VALUE && this.day == Integer.MIN_VALUE && this.hour != Integer.MIN_VALUE && this.minute != Integer.MIN_VALUE && this.second != Integer.MIN_VALUE)
      return DatatypeConstants.TIME; 
    if (this.year != Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day == Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE)
      return DatatypeConstants.GYEARMONTH; 
    if (this.year == Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day != Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE)
      return DatatypeConstants.GMONTHDAY; 
    if (this.year != Integer.MIN_VALUE && this.month == Integer.MIN_VALUE && this.day == Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE)
      return DatatypeConstants.GYEAR; 
    if (this.year == Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day == Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE)
      return DatatypeConstants.GMONTH; 
    if (this.year == Integer.MIN_VALUE && this.month == Integer.MIN_VALUE && this.day != Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE)
      return DatatypeConstants.GDAY; 
    throw new IllegalStateException(getClass().getName() + "#getXMLSchemaType() :" + DatatypeMessageFormatter.formatMessage(null, "InvalidXGCFields", null));
  }
  
  public boolean isValid() {
    if (this.month != Integer.MIN_VALUE && this.day != Integer.MIN_VALUE)
      if (this.year != Integer.MIN_VALUE) {
        if (this.eon == null) {
          if (this.day > maximumDayInMonthFor(this.year, this.month))
            return false; 
        } else if (this.day > maximumDayInMonthFor(getEonAndYear(), this.month)) {
          return false;
        } 
      } else if (this.day > maximumDayInMonthFor(2000, this.month)) {
        return false;
      }  
    return (this.hour == 24 && (this.minute != 0 || this.second != 0)) ? false : (!(this.eon == null && this.year == 0));
  }
  
  public void add(Duration paramDuration) {
    BigDecimal bigDecimal1;
    BigInteger bigInteger9;
    int i2;
    boolean[] arrayOfBoolean = { false, false, false, false, false, false };
    int i = paramDuration.getSign();
    int j = getMonth();
    if (j == Integer.MIN_VALUE) {
      j = MIN_FIELD_VALUE[1];
      arrayOfBoolean[1] = true;
    } 
    BigInteger bigInteger1 = sanitize(paramDuration.getField(DatatypeConstants.MONTHS), i);
    BigInteger bigInteger2 = BigInteger.valueOf(j).add(bigInteger1);
    setMonth(bigInteger2.subtract(BigInteger.ONE).mod(TWELVE).intValue() + 1);
    BigInteger bigInteger3 = (new BigDecimal(bigInteger2.subtract(BigInteger.ONE))).divide(new BigDecimal(TWELVE), 3).toBigInteger();
    BigInteger bigInteger4 = getEonAndYear();
    if (bigInteger4 == null) {
      arrayOfBoolean[0] = true;
      bigInteger4 = BigInteger.ZERO;
    } 
    BigInteger bigInteger5 = sanitize(paramDuration.getField(DatatypeConstants.YEARS), i);
    BigInteger bigInteger6 = bigInteger4.add(bigInteger5).add(bigInteger3);
    setYear(bigInteger6);
    if (getSecond() == Integer.MIN_VALUE) {
      arrayOfBoolean[5] = true;
      bigDecimal1 = DECIMAL_ZERO;
    } else {
      bigDecimal1 = getSeconds();
    } 
    BigDecimal bigDecimal2 = DurationImpl.sanitize((BigDecimal)paramDuration.getField(DatatypeConstants.SECONDS), i);
    BigDecimal bigDecimal3 = bigDecimal1.add(bigDecimal2);
    BigDecimal bigDecimal4 = new BigDecimal((new BigDecimal(bigDecimal3.toBigInteger())).divide(DECIMAL_SIXTY, 3).toBigInteger());
    BigDecimal bigDecimal5 = bigDecimal3.subtract(bigDecimal4.multiply(DECIMAL_SIXTY));
    bigInteger3 = bigDecimal4.toBigInteger();
    setSecond(bigDecimal5.intValue());
    BigDecimal bigDecimal6 = bigDecimal5.subtract(new BigDecimal(BigInteger.valueOf(getSecond())));
    if (bigDecimal6.compareTo(DECIMAL_ZERO) < 0) {
      setFractionalSecond(DECIMAL_ONE.add(bigDecimal6));
      if (getSecond() == 0) {
        setSecond(59);
        bigInteger3 = bigInteger3.subtract(BigInteger.ONE);
      } else {
        setSecond(getSecond() - 1);
      } 
    } else {
      setFractionalSecond(bigDecimal6);
    } 
    int k = getMinute();
    if (k == Integer.MIN_VALUE) {
      arrayOfBoolean[4] = true;
      k = MIN_FIELD_VALUE[4];
    } 
    BigInteger bigInteger7 = sanitize(paramDuration.getField(DatatypeConstants.MINUTES), i);
    bigInteger2 = BigInteger.valueOf(k).add(bigInteger7).add(bigInteger3);
    setMinute(bigInteger2.mod(SIXTY).intValue());
    bigInteger3 = (new BigDecimal(bigInteger2)).divide(DECIMAL_SIXTY, 3).toBigInteger();
    int m = getHour();
    if (m == Integer.MIN_VALUE) {
      arrayOfBoolean[3] = true;
      m = MIN_FIELD_VALUE[3];
    } 
    BigInteger bigInteger8 = sanitize(paramDuration.getField(DatatypeConstants.HOURS), i);
    bigInteger2 = BigInteger.valueOf(m).add(bigInteger8).add(bigInteger3);
    setHour(bigInteger2.mod(TWENTY_FOUR).intValue());
    bigInteger3 = (new BigDecimal(bigInteger2)).divide(new BigDecimal(TWENTY_FOUR), 3).toBigInteger();
    int n = getDay();
    if (n == Integer.MIN_VALUE) {
      arrayOfBoolean[2] = true;
      n = MIN_FIELD_VALUE[2];
    } 
    BigInteger bigInteger10 = sanitize(paramDuration.getField(DatatypeConstants.DAYS), i);
    int i1 = maximumDayInMonthFor(getEonAndYear(), getMonth());
    if (n > i1) {
      bigInteger9 = BigInteger.valueOf(i1);
    } else if (n < 1) {
      bigInteger9 = BigInteger.ONE;
    } else {
      bigInteger9 = BigInteger.valueOf(n);
    } 
    BigInteger bigInteger11 = bigInteger9.add(bigInteger10).add(bigInteger3);
    while (true) {
      byte b;
      if (bigInteger11.compareTo(BigInteger.ONE) < 0) {
        BigInteger bigInteger = null;
        if (this.month >= 2) {
          bigInteger = BigInteger.valueOf(maximumDayInMonthFor(getEonAndYear(), getMonth() - 1));
        } else {
          bigInteger = BigInteger.valueOf(maximumDayInMonthFor(getEonAndYear().subtract(BigInteger.valueOf(1L)), 12));
        } 
        bigInteger11 = bigInteger11.add(bigInteger);
        b = -1;
      } else if (bigInteger11.compareTo(BigInteger.valueOf(maximumDayInMonthFor(getEonAndYear(), getMonth()))) > 0) {
        bigInteger11 = bigInteger11.add(BigInteger.valueOf(-maximumDayInMonthFor(getEonAndYear(), getMonth())));
        b = 1;
      } else {
        setDay(bigInteger11.intValue());
        byte b1 = 0;
        while (true)
          b1++; 
        return;
      } 
      int i3 = getMonth() + b;
      i2 = (i3 - 1) % 12;
      if (i2 < 0) {
        i2 = 12 + i2 + 1;
        int i4 = BigDecimal.valueOf((i3 - 1)).divide(new BigDecimal(TWELVE), 0).intValue();
      } else {
        int i4 = (i3 - 1) / 12;
        i2++;
      } 
      setMonth(i2);
      continue;
      if (SYNTHETIC_LOCAL_VARIABLE_29 != null)
        setYear(getEonAndYear().add(BigInteger.valueOf(SYNTHETIC_LOCAL_VARIABLE_29))); 
    } 
    switch (i2) {
      case 0:
        setYear(-2147483648);
        break;
      case 1:
        setMonth(-2147483648);
        break;
      case 2:
        setDay(-2147483648);
        break;
      case 3:
        setHour(-2147483648);
        break;
      case 4:
        setMinute(-2147483648);
        break;
      case 5:
        setSecond(-2147483648);
        setFractionalSecond(null);
        break;
    } 
    continue;
  }
  
  private static int maximumDayInMonthFor(BigInteger paramBigInteger, int paramInt) {
    return (paramInt != 2) ? DaysInMonth.table[paramInt] : ((paramBigInteger.mod(FOUR_HUNDRED).equals(BigInteger.ZERO) || (!paramBigInteger.mod(HUNDRED).equals(BigInteger.ZERO) && paramBigInteger.mod(FOUR).equals(BigInteger.ZERO))) ? 29 : DaysInMonth.table[paramInt]);
  }
  
  private static int maximumDayInMonthFor(int paramInt1, int paramInt2) {
    return (paramInt2 != 2) ? DaysInMonth.table[paramInt2] : ((paramInt1 % 400 == 0 || (paramInt1 % 100 != 0 && paramInt1 % 4 == 0)) ? 29 : DaysInMonth.table[2]);
  }
  
  public GregorianCalendar toGregorianCalendar() {
    GregorianCalendar gregorianCalendar = null;
    TimeZone timeZone = getTimeZone(-2147483648);
    Locale locale = Locale.getDefault();
    gregorianCalendar = new GregorianCalendar(timeZone, locale);
    gregorianCalendar.clear();
    gregorianCalendar.setGregorianChange(PURE_GREGORIAN_CHANGE);
    if (this.year != Integer.MIN_VALUE)
      if (this.eon == null) {
        gregorianCalendar.set(0, (this.year < 0) ? 0 : 1);
        gregorianCalendar.set(1, Math.abs(this.year));
      } else {
        BigInteger bigInteger = getEonAndYear();
        gregorianCalendar.set(0, (bigInteger.signum() == -1) ? 0 : 1);
        gregorianCalendar.set(1, bigInteger.abs().intValue());
      }  
    if (this.month != Integer.MIN_VALUE)
      gregorianCalendar.set(2, this.month - 1); 
    if (this.day != Integer.MIN_VALUE)
      gregorianCalendar.set(5, this.day); 
    if (this.hour != Integer.MIN_VALUE)
      gregorianCalendar.set(11, this.hour); 
    if (this.minute != Integer.MIN_VALUE)
      gregorianCalendar.set(12, this.minute); 
    if (this.second != Integer.MIN_VALUE)
      gregorianCalendar.set(13, this.second); 
    if (this.fractionalSecond != null)
      gregorianCalendar.set(14, getMillisecond()); 
    return gregorianCalendar;
  }
  
  public GregorianCalendar toGregorianCalendar(TimeZone paramTimeZone, Locale paramLocale, XMLGregorianCalendar paramXMLGregorianCalendar) {
    GregorianCalendar gregorianCalendar = null;
    TimeZone timeZone = paramTimeZone;
    if (timeZone == null) {
      int i = Integer.MIN_VALUE;
      if (paramXMLGregorianCalendar != null)
        i = paramXMLGregorianCalendar.getTimezone(); 
      timeZone = getTimeZone(i);
    } 
    if (paramLocale == null)
      paramLocale = Locale.getDefault(); 
    gregorianCalendar = new GregorianCalendar(timeZone, paramLocale);
    gregorianCalendar.clear();
    gregorianCalendar.setGregorianChange(PURE_GREGORIAN_CHANGE);
    if (this.year != Integer.MIN_VALUE) {
      if (this.eon == null) {
        gregorianCalendar.set(0, (this.year < 0) ? 0 : 1);
        gregorianCalendar.set(1, Math.abs(this.year));
      } else {
        BigInteger bigInteger = getEonAndYear();
        gregorianCalendar.set(0, (bigInteger.signum() == -1) ? 0 : 1);
        gregorianCalendar.set(1, bigInteger.abs().intValue());
      } 
    } else if (paramXMLGregorianCalendar != null) {
      int i = paramXMLGregorianCalendar.getYear();
      if (i != Integer.MIN_VALUE)
        if (paramXMLGregorianCalendar.getEon() == null) {
          gregorianCalendar.set(0, (i < 0) ? 0 : 1);
          gregorianCalendar.set(1, Math.abs(i));
        } else {
          BigInteger bigInteger = paramXMLGregorianCalendar.getEonAndYear();
          gregorianCalendar.set(0, (bigInteger.signum() == -1) ? 0 : 1);
          gregorianCalendar.set(1, bigInteger.abs().intValue());
        }  
    } 
    if (this.month != Integer.MIN_VALUE) {
      gregorianCalendar.set(2, this.month - 1);
    } else {
      int i = (paramXMLGregorianCalendar != null) ? paramXMLGregorianCalendar.getMonth() : Integer.MIN_VALUE;
      if (i != Integer.MIN_VALUE)
        gregorianCalendar.set(2, i - 1); 
    } 
    if (this.day != Integer.MIN_VALUE) {
      gregorianCalendar.set(5, this.day);
    } else {
      int i = (paramXMLGregorianCalendar != null) ? paramXMLGregorianCalendar.getDay() : Integer.MIN_VALUE;
      if (i != Integer.MIN_VALUE)
        gregorianCalendar.set(5, i); 
    } 
    if (this.hour != Integer.MIN_VALUE) {
      gregorianCalendar.set(11, this.hour);
    } else {
      int i = (paramXMLGregorianCalendar != null) ? paramXMLGregorianCalendar.getHour() : Integer.MIN_VALUE;
      if (i != Integer.MIN_VALUE)
        gregorianCalendar.set(11, i); 
    } 
    if (this.minute != Integer.MIN_VALUE) {
      gregorianCalendar.set(12, this.minute);
    } else {
      int i = (paramXMLGregorianCalendar != null) ? paramXMLGregorianCalendar.getMinute() : Integer.MIN_VALUE;
      if (i != Integer.MIN_VALUE)
        gregorianCalendar.set(12, i); 
    } 
    if (this.second != Integer.MIN_VALUE) {
      gregorianCalendar.set(13, this.second);
    } else {
      int i = (paramXMLGregorianCalendar != null) ? paramXMLGregorianCalendar.getSecond() : Integer.MIN_VALUE;
      if (i != Integer.MIN_VALUE)
        gregorianCalendar.set(13, i); 
    } 
    if (this.fractionalSecond != null) {
      gregorianCalendar.set(14, getMillisecond());
    } else {
      BigDecimal bigDecimal = (paramXMLGregorianCalendar != null) ? paramXMLGregorianCalendar.getFractionalSecond() : null;
      if (bigDecimal != null)
        gregorianCalendar.set(14, paramXMLGregorianCalendar.getMillisecond()); 
    } 
    return gregorianCalendar;
  }
  
  public TimeZone getTimeZone(int paramInt) {
    TimeZone timeZone = null;
    int i = getTimezone();
    if (i == Integer.MIN_VALUE)
      i = paramInt; 
    if (i == Integer.MIN_VALUE) {
      timeZone = TimeZone.getDefault();
    } else {
      byte b = (i < 0) ? 45 : 43;
      if (b == 45)
        i = -i; 
      int j = i / 60;
      int k = i - j * 60;
      StringBuffer stringBuffer = new StringBuffer(8);
      stringBuffer.append("GMT");
      stringBuffer.append(b);
      stringBuffer.append(j);
      if (k != 0) {
        if (k < 10)
          stringBuffer.append('0'); 
        stringBuffer.append(k);
      } 
      timeZone = TimeZone.getTimeZone(stringBuffer.toString());
    } 
    return timeZone;
  }
  
  public Object clone() {
    return new XMLGregorianCalendarImpl(getEonAndYear(), this.month, this.day, this.hour, this.minute, this.second, this.fractionalSecond, this.timezone);
  }
  
  public void clear() {
    this.eon = null;
    this.year = Integer.MIN_VALUE;
    this.month = Integer.MIN_VALUE;
    this.day = Integer.MIN_VALUE;
    this.timezone = Integer.MIN_VALUE;
    this.hour = Integer.MIN_VALUE;
    this.minute = Integer.MIN_VALUE;
    this.second = Integer.MIN_VALUE;
    this.fractionalSecond = null;
  }
  
  public void setMillisecond(int paramInt) {
    if (paramInt == Integer.MIN_VALUE) {
      this.fractionalSecond = null;
    } else {
      checkFieldValueConstraint(6, paramInt);
      this.fractionalSecond = BigDecimal.valueOf(paramInt, 3);
    } 
  }
  
  public void setFractionalSecond(BigDecimal paramBigDecimal) {
    if (paramBigDecimal != null && (paramBigDecimal.compareTo(DECIMAL_ZERO) < 0 || paramBigDecimal.compareTo(DECIMAL_ONE) > 0))
      throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidFractional", new Object[] { paramBigDecimal })); 
    this.fractionalSecond = paramBigDecimal;
  }
  
  private static boolean isDigit(char paramChar) {
    return ('0' <= paramChar && paramChar <= '9');
  }
  
  private String format(String paramString) {
    StringBuffer stringBuffer = new StringBuffer();
    byte b = 0;
    int i = paramString.length();
    while (b < i) {
      int j;
      char c = paramString.charAt(b++);
      if (c != '%') {
        stringBuffer.append(c);
        continue;
      } 
      switch (paramString.charAt(b++)) {
        case 'Y':
          if (this.eon == null) {
            int k = this.year;
            if (k < 0) {
              stringBuffer.append('-');
              k = -this.year;
            } 
            printNumber(stringBuffer, k, 4);
            continue;
          } 
          printNumber(stringBuffer, getEonAndYear(), 4);
          continue;
        case 'M':
          printNumber(stringBuffer, getMonth(), 2);
          continue;
        case 'D':
          printNumber(stringBuffer, getDay(), 2);
          continue;
        case 'h':
          printNumber(stringBuffer, getHour(), 2);
          continue;
        case 'm':
          printNumber(stringBuffer, getMinute(), 2);
          continue;
        case 's':
          printNumber(stringBuffer, getSecond(), 2);
          if (getFractionalSecond() != null) {
            String str = toString(getFractionalSecond());
            stringBuffer.append(str.substring(1, str.length()));
          } 
          continue;
        case 'z':
          j = getTimezone();
          if (j == 0) {
            stringBuffer.append('Z');
            continue;
          } 
          if (j != Integer.MIN_VALUE) {
            if (j < 0) {
              stringBuffer.append('-');
              j *= -1;
            } else {
              stringBuffer.append('+');
            } 
            printNumber(stringBuffer, j / 60, 2);
            stringBuffer.append(':');
            printNumber(stringBuffer, j % 60, 2);
          } 
          continue;
      } 
      throw new InternalError();
    } 
    return stringBuffer.toString();
  }
  
  private void printNumber(StringBuffer paramStringBuffer, int paramInt1, int paramInt2) {
    String str = String.valueOf(paramInt1);
    for (int i = str.length(); i < paramInt2; i++)
      paramStringBuffer.append('0'); 
    paramStringBuffer.append(str);
  }
  
  private void printNumber(StringBuffer paramStringBuffer, BigInteger paramBigInteger, int paramInt) {
    String str = paramBigInteger.toString();
    for (int i = str.length(); i < paramInt; i++)
      paramStringBuffer.append('0'); 
    paramStringBuffer.append(str);
  }
  
  private String toString(BigDecimal paramBigDecimal) {
    StringBuffer stringBuffer;
    String str = paramBigDecimal.unscaledValue().toString();
    int i = paramBigDecimal.scale();
    if (i == 0)
      return str; 
    int j = str.length() - i;
    if (j == 0)
      return "0." + str; 
    if (j > 0) {
      stringBuffer = new StringBuffer(str);
      stringBuffer.insert(j, '.');
    } else {
      stringBuffer = new StringBuffer(3 - j + str.length());
      stringBuffer.append("0.");
      for (byte b = 0; b < -j; b++)
        stringBuffer.append('0'); 
      stringBuffer.append(str);
    } 
    return stringBuffer.toString();
  }
  
  static BigInteger sanitize(Number paramNumber, int paramInt) {
    return (paramInt == 0 || paramNumber == null) ? BigInteger.ZERO : ((paramInt < 0) ? ((BigInteger)paramNumber).negate() : (BigInteger)paramNumber);
  }
  
  public void reset() {
    this.eon = this.orig_eon;
    this.year = this.orig_year;
    this.month = this.orig_month;
    this.day = this.orig_day;
    this.hour = this.orig_hour;
    this.minute = this.orig_minute;
    this.second = this.orig_second;
    this.fractionalSecond = this.orig_fracSeconds;
    this.timezone = this.orig_timezone;
  }
  
  private final class Parser {
    private final String format;
    
    private final String value;
    
    private final int flen;
    
    private final int vlen;
    
    private int fidx;
    
    private int vidx;
    
    private final XMLGregorianCalendarImpl this$0;
    
    private Parser(XMLGregorianCalendarImpl this$0, String param1String1, String param1String2) {
      XMLGregorianCalendarImpl.this = XMLGregorianCalendarImpl.this;
      this.format = param1String1;
      this.value = param1String2;
      this.flen = param1String1.length();
      this.vlen = param1String2.length();
    }
    
    public void parse() throws IllegalArgumentException {
      while (this.fidx < this.flen) {
        char c2;
        char c1 = this.format.charAt(this.fidx++);
        if (c1 != '%') {
          skip(c1);
          continue;
        } 
        switch (this.format.charAt(this.fidx++)) {
          case 'Y':
            parseYear();
            continue;
          case 'M':
            XMLGregorianCalendarImpl.this.setMonth(parseInt(2, 2));
            continue;
          case 'D':
            XMLGregorianCalendarImpl.this.setDay(parseInt(2, 2));
            continue;
          case 'h':
            XMLGregorianCalendarImpl.this.setHour(parseInt(2, 2));
            continue;
          case 'm':
            XMLGregorianCalendarImpl.this.setMinute(parseInt(2, 2));
            continue;
          case 's':
            XMLGregorianCalendarImpl.this.setSecond(parseInt(2, 2));
            if (peek() == '.')
              XMLGregorianCalendarImpl.this.setFractionalSecond(parseBigDecimal()); 
            continue;
          case 'z':
            c2 = peek();
            if (c2 == 'Z') {
              this.vidx++;
              XMLGregorianCalendarImpl.this.setTimezone(0);
              continue;
            } 
            if (c2 == '+' || c2 == '-') {
              this.vidx++;
              int i = parseInt(2, 2);
              skip(':');
              int j = parseInt(2, 2);
              XMLGregorianCalendarImpl.this.setTimezone((i * 60 + j) * ((c2 == '+') ? 1 : -1));
            } 
            continue;
        } 
        throw new InternalError();
      } 
      if (this.vidx != this.vlen)
        throw new IllegalArgumentException(this.value); 
    }
    
    private char peek() throws IllegalArgumentException {
      return (this.vidx == this.vlen) ? Character.MAX_VALUE : this.value.charAt(this.vidx);
    }
    
    private char read() throws IllegalArgumentException {
      if (this.vidx == this.vlen)
        throw new IllegalArgumentException(this.value); 
      return this.value.charAt(this.vidx++);
    }
    
    private void skip(char param1Char) throws IllegalArgumentException {
      if (read() != param1Char)
        throw new IllegalArgumentException(this.value); 
    }
    
    private void parseYear() throws IllegalArgumentException {
      int i = this.vidx;
      byte b = 0;
      if (peek() == '-') {
        this.vidx++;
        b = 1;
      } 
      while (XMLGregorianCalendarImpl.isDigit(peek()))
        this.vidx++; 
      int j = this.vidx - i - b;
      if (j < 4)
        throw new IllegalArgumentException(this.value); 
      String str = this.value.substring(i, this.vidx);
      if (j < 10) {
        XMLGregorianCalendarImpl.this.setYear(Integer.parseInt(str));
      } else {
        XMLGregorianCalendarImpl.this.setYear(new BigInteger(str));
      } 
    }
    
    private int parseInt(int param1Int1, int param1Int2) throws IllegalArgumentException {
      int i = this.vidx;
      while (XMLGregorianCalendarImpl.isDigit(peek()) && this.vidx - i < param1Int2)
        this.vidx++; 
      if (this.vidx - i < param1Int1)
        throw new IllegalArgumentException(this.value); 
      return Integer.parseInt(this.value.substring(i, this.vidx));
    }
    
    private BigDecimal parseBigDecimal() throws IllegalArgumentException {
      int i = this.vidx;
      if (peek() == '.') {
        this.vidx++;
      } else {
        throw new IllegalArgumentException(this.value);
      } 
      while (XMLGregorianCalendarImpl.isDigit(peek()))
        this.vidx++; 
      return new BigDecimal(this.value.substring(i, this.vidx));
    }
  }
  
  private static class DaysInMonth {
    private static final int[] table = new int[] { 
        0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 
        31, 30, 31 };
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\datatype\XMLGregorianCalendarImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */