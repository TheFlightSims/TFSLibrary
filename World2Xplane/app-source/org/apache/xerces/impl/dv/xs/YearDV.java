package org.apache.xerces.impl.dv.xs;

import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class YearDV extends AbstractDateTimeDV {
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return parse(paramString);
    } catch (Exception exception) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "gYear" });
    } 
  }
  
  protected AbstractDateTimeDV.DateTimeData parse(String paramString) throws SchemaDateTimeException {
    AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(paramString, this);
    int i = paramString.length();
    boolean bool = false;
    if (paramString.charAt(0) == '-')
      bool = true; 
    int j = findUTCSign(paramString, bool, i);
    if (j == -1) {
      dateTimeData.year = parseIntYear(paramString, i);
    } else {
      dateTimeData.year = parseIntYear(paramString, j);
      getTimeZone(paramString, dateTimeData, j, i);
    } 
    dateTimeData.month = 1;
    dateTimeData.day = 1;
    validateDateTime(dateTimeData);
    saveUnnormalized(dateTimeData);
    if (dateTimeData.utc != 0 && dateTimeData.utc != 90)
      normalize(dateTimeData); 
    dateTimeData.position = 0;
    return dateTimeData;
  }
  
  protected String dateToString(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    StringBuffer stringBuffer = new StringBuffer(5);
    append(stringBuffer, paramDateTimeData.year, 4);
    append(stringBuffer, (char)paramDateTimeData.utc, 0);
    return stringBuffer.toString();
  }
  
  protected XMLGregorianCalendar getXMLGregorianCalendar(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    return this.factory.newXMLGregorianCalendar(paramDateTimeData.unNormYear, -2147483648, -2147483648, -2147483648, -2147483648, -2147483648, -2147483648, paramDateTimeData.timezoneHr * 60 + paramDateTimeData.timezoneMin);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\YearDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */