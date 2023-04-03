package org.apache.xerces.impl.dv.xs;

import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class MonthDayDV extends AbstractDateTimeDV {
  private static final int MONTHDAY_SIZE = 7;
  
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return parse(paramString);
    } catch (Exception exception) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "gMonthDay" });
    } 
  }
  
  protected AbstractDateTimeDV.DateTimeData parse(String paramString) throws SchemaDateTimeException {
    AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(paramString, this);
    int i = paramString.length();
    dateTimeData.year = 2000;
    if (paramString.charAt(0) != '-' || paramString.charAt(1) != '-')
      throw new SchemaDateTimeException("Invalid format for gMonthDay: " + paramString); 
    dateTimeData.month = parseInt(paramString, 2, 4);
    byte b = 4;
    if (paramString.charAt(b++) != '-')
      throw new SchemaDateTimeException("Invalid format for gMonthDay: " + paramString); 
    dateTimeData.day = parseInt(paramString, b, b + 2);
    if (7 < i) {
      if (!isNextCharUTCSign(paramString, 7, i))
        throw new SchemaDateTimeException("Error in month parsing:" + paramString); 
      getTimeZone(paramString, dateTimeData, 7, i);
    } 
    validateDateTime(dateTimeData);
    saveUnnormalized(dateTimeData);
    if (dateTimeData.utc != 0 && dateTimeData.utc != 90)
      normalize(dateTimeData); 
    dateTimeData.position = 1;
    return dateTimeData;
  }
  
  protected String dateToString(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    StringBuffer stringBuffer = new StringBuffer(8);
    stringBuffer.append('-');
    stringBuffer.append('-');
    append(stringBuffer, paramDateTimeData.month, 2);
    stringBuffer.append('-');
    append(stringBuffer, paramDateTimeData.day, 2);
    append(stringBuffer, (char)paramDateTimeData.utc, 0);
    return stringBuffer.toString();
  }
  
  protected XMLGregorianCalendar getXMLGregorianCalendar(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    return this.factory.newXMLGregorianCalendar(-2147483648, paramDateTimeData.unNormMonth, paramDateTimeData.unNormDay, -2147483648, -2147483648, -2147483648, -2147483648, paramDateTimeData.timezoneHr * 60 + paramDateTimeData.timezoneMin);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\MonthDayDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */