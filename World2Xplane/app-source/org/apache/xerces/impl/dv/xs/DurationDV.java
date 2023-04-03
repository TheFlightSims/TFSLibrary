package org.apache.xerces.impl.dv.xs;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.Duration;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class DurationDV extends AbstractDateTimeDV {
  public static final int DURATION_TYPE = 0;
  
  public static final int YEARMONTHDURATION_TYPE = 1;
  
  public static final int DAYTIMEDURATION_TYPE = 2;
  
  private static final AbstractDateTimeDV.DateTimeData[] DATETIMES = new AbstractDateTimeDV.DateTimeData[] { new AbstractDateTimeDV.DateTimeData(1696, 9, 1, 0, 0, 0.0D, 90, null, true, null), new AbstractDateTimeDV.DateTimeData(1697, 2, 1, 0, 0, 0.0D, 90, null, true, null), new AbstractDateTimeDV.DateTimeData(1903, 3, 1, 0, 0, 0.0D, 90, null, true, null), new AbstractDateTimeDV.DateTimeData(1903, 7, 1, 0, 0, 0.0D, 90, null, true, null) };
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return parse(paramString, 0);
    } catch (Exception exception) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "duration" });
    } 
  }
  
  protected AbstractDateTimeDV.DateTimeData parse(String paramString, int paramInt) throws SchemaDateTimeException {
    int i = paramString.length();
    AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(paramString, this);
    int j = 0;
    char c = paramString.charAt(j++);
    if (c != 'P' && c != '-')
      throw new SchemaDateTimeException(); 
    dateTimeData.utc = (c == '-') ? 45 : 0;
    if (c == '-' && paramString.charAt(j++) != 'P')
      throw new SchemaDateTimeException(); 
    byte b = 1;
    if (dateTimeData.utc == 45)
      b = -1; 
    boolean bool = false;
    int k = indexOf(paramString, j, i, 'T');
    if (k == -1) {
      k = i;
    } else if (paramInt == 1) {
      throw new SchemaDateTimeException();
    } 
    int m = indexOf(paramString, j, k, 'Y');
    if (m != -1) {
      if (paramInt == 2)
        throw new SchemaDateTimeException(); 
      dateTimeData.year = b * parseInt(paramString, j, m);
      j = m + 1;
      bool = true;
    } 
    m = indexOf(paramString, j, k, 'M');
    if (m != -1) {
      if (paramInt == 2)
        throw new SchemaDateTimeException(); 
      dateTimeData.month = b * parseInt(paramString, j, m);
      j = m + 1;
      bool = true;
    } 
    m = indexOf(paramString, j, k, 'D');
    if (m != -1) {
      if (paramInt == 1)
        throw new SchemaDateTimeException(); 
      dateTimeData.day = b * parseInt(paramString, j, m);
      j = m + 1;
      bool = true;
    } 
    if (i == k && j != i)
      throw new SchemaDateTimeException(); 
    if (i != k) {
      m = indexOf(paramString, ++j, i, 'H');
      if (m != -1) {
        dateTimeData.hour = b * parseInt(paramString, j, m);
        j = m + 1;
        bool = true;
      } 
      m = indexOf(paramString, j, i, 'M');
      if (m != -1) {
        dateTimeData.minute = b * parseInt(paramString, j, m);
        j = m + 1;
        bool = true;
      } 
      m = indexOf(paramString, j, i, 'S');
      if (m != -1) {
        dateTimeData.second = b * parseSecond(paramString, j, m);
        j = m + 1;
        bool = true;
      } 
      if (j != i || paramString.charAt(--j) == 'T')
        throw new SchemaDateTimeException(); 
    } 
    if (!bool)
      throw new SchemaDateTimeException(); 
    return dateTimeData;
  }
  
  protected short compareDates(AbstractDateTimeDV.DateTimeData paramDateTimeData1, AbstractDateTimeDV.DateTimeData paramDateTimeData2, boolean paramBoolean) {
    short s = 2;
    null = compareOrder(paramDateTimeData1, paramDateTimeData2);
    if (null == 0)
      return 0; 
    AbstractDateTimeDV.DateTimeData[] arrayOfDateTimeData = new AbstractDateTimeDV.DateTimeData[2];
    arrayOfDateTimeData[0] = new AbstractDateTimeDV.DateTimeData(null, this);
    arrayOfDateTimeData[1] = new AbstractDateTimeDV.DateTimeData(null, this);
    AbstractDateTimeDV.DateTimeData dateTimeData1 = addDuration(paramDateTimeData1, DATETIMES[0], arrayOfDateTimeData[0]);
    AbstractDateTimeDV.DateTimeData dateTimeData2 = addDuration(paramDateTimeData2, DATETIMES[0], arrayOfDateTimeData[1]);
    null = compareOrder(dateTimeData1, dateTimeData2);
    if (null == 2)
      return 2; 
    dateTimeData1 = addDuration(paramDateTimeData1, DATETIMES[1], arrayOfDateTimeData[0]);
    dateTimeData2 = addDuration(paramDateTimeData2, DATETIMES[1], arrayOfDateTimeData[1]);
    s = compareOrder(dateTimeData1, dateTimeData2);
    null = compareResults(null, s, paramBoolean);
    if (null == 2)
      return 2; 
    dateTimeData1 = addDuration(paramDateTimeData1, DATETIMES[2], arrayOfDateTimeData[0]);
    dateTimeData2 = addDuration(paramDateTimeData2, DATETIMES[2], arrayOfDateTimeData[1]);
    s = compareOrder(dateTimeData1, dateTimeData2);
    null = compareResults(null, s, paramBoolean);
    if (null == 2)
      return 2; 
    dateTimeData1 = addDuration(paramDateTimeData1, DATETIMES[3], arrayOfDateTimeData[0]);
    dateTimeData2 = addDuration(paramDateTimeData2, DATETIMES[3], arrayOfDateTimeData[1]);
    s = compareOrder(dateTimeData1, dateTimeData2);
    return compareResults(null, s, paramBoolean);
  }
  
  private short compareResults(short paramShort1, short paramShort2, boolean paramBoolean) {
    return (paramShort2 == 2) ? 2 : ((paramShort1 != paramShort2 && paramBoolean) ? 2 : ((paramShort1 != paramShort2 && !paramBoolean) ? ((paramShort1 != 0 && paramShort2 != 0) ? 2 : ((paramShort1 != 0) ? paramShort1 : paramShort2)) : paramShort1));
  }
  
  private AbstractDateTimeDV.DateTimeData addDuration(AbstractDateTimeDV.DateTimeData paramDateTimeData1, AbstractDateTimeDV.DateTimeData paramDateTimeData2, AbstractDateTimeDV.DateTimeData paramDateTimeData3) {
    resetDateObj(paramDateTimeData3);
    int i = paramDateTimeData2.month + paramDateTimeData1.month;
    paramDateTimeData3.month = modulo(i, 1, 13);
    int j = fQuotient(i, 1, 13);
    paramDateTimeData3.year = paramDateTimeData2.year + paramDateTimeData1.year + j;
    double d = paramDateTimeData2.second + paramDateTimeData1.second;
    j = (int)Math.floor(d / 60.0D);
    paramDateTimeData3.second = d - (j * 60);
    i = paramDateTimeData2.minute + paramDateTimeData1.minute + j;
    j = fQuotient(i, 60);
    paramDateTimeData3.minute = mod(i, 60, j);
    i = paramDateTimeData2.hour + paramDateTimeData1.hour + j;
    j = fQuotient(i, 24);
    paramDateTimeData3.hour = mod(i, 24, j);
    paramDateTimeData3.day = paramDateTimeData2.day + paramDateTimeData1.day + j;
    while (true) {
      i = maxDayInMonthFor(paramDateTimeData3.year, paramDateTimeData3.month);
      if (paramDateTimeData3.day < 1) {
        paramDateTimeData3.day += maxDayInMonthFor(paramDateTimeData3.year, paramDateTimeData3.month - 1);
        j = -1;
      } else if (paramDateTimeData3.day > i) {
        paramDateTimeData3.day -= i;
        j = 1;
      } else {
        paramDateTimeData3.utc = 90;
        return paramDateTimeData3;
      } 
      i = paramDateTimeData3.month + j;
      paramDateTimeData3.month = modulo(i, 1, 13);
      paramDateTimeData3.year += fQuotient(i, 1, 13);
    } 
  }
  
  protected double parseSecond(String paramString, int paramInt1, int paramInt2) throws NumberFormatException {
    int i = -1;
    for (int j = paramInt1; j < paramInt2; j++) {
      char c = paramString.charAt(j);
      if (c == '.') {
        i = j;
      } else if (c > '9' || c < '0') {
        throw new NumberFormatException("'" + paramString + "' has wrong format");
      } 
    } 
    if (i + 1 == paramInt2)
      throw new NumberFormatException("'" + paramString + "' has wrong format"); 
    double d = Double.parseDouble(paramString.substring(paramInt1, paramInt2));
    if (d == Double.POSITIVE_INFINITY)
      throw new NumberFormatException("'" + paramString + "' has wrong format"); 
    return d;
  }
  
  protected String dateToString(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    StringBuffer stringBuffer = new StringBuffer(30);
    if (paramDateTimeData.year < 0 || paramDateTimeData.month < 0 || paramDateTimeData.day < 0 || paramDateTimeData.hour < 0 || paramDateTimeData.minute < 0 || paramDateTimeData.second < 0.0D)
      stringBuffer.append('-'); 
    stringBuffer.append('P');
    stringBuffer.append(((paramDateTimeData.year < 0) ? -1 : 1) * paramDateTimeData.year);
    stringBuffer.append('Y');
    stringBuffer.append(((paramDateTimeData.month < 0) ? -1 : 1) * paramDateTimeData.month);
    stringBuffer.append('M');
    stringBuffer.append(((paramDateTimeData.day < 0) ? -1 : 1) * paramDateTimeData.day);
    stringBuffer.append('D');
    stringBuffer.append('T');
    stringBuffer.append(((paramDateTimeData.hour < 0) ? -1 : 1) * paramDateTimeData.hour);
    stringBuffer.append('H');
    stringBuffer.append(((paramDateTimeData.minute < 0) ? -1 : 1) * paramDateTimeData.minute);
    stringBuffer.append('M');
    append2(stringBuffer, ((paramDateTimeData.second < 0.0D) ? -1.0D : 1.0D) * paramDateTimeData.second);
    stringBuffer.append('S');
    return stringBuffer.toString();
  }
  
  protected Duration getDuration(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    byte b = 1;
    if (paramDateTimeData.year < 0 || paramDateTimeData.month < 0 || paramDateTimeData.day < 0 || paramDateTimeData.hour < 0 || paramDateTimeData.minute < 0 || paramDateTimeData.second < 0.0D)
      b = -1; 
    return this.factory.newDuration((b == 1), (paramDateTimeData.year != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.year)) : null, (paramDateTimeData.month != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.month)) : null, (paramDateTimeData.day != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.day)) : null, (paramDateTimeData.hour != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.hour)) : null, (paramDateTimeData.minute != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.minute)) : null, (paramDateTimeData.second != -2.147483648E9D) ? new BigDecimal(String.valueOf(b * paramDateTimeData.second)) : null);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\DurationDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */