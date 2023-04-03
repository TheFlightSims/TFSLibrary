package org.apache.xerces.impl.dv.xs;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class TimeDV extends AbstractDateTimeDV {
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return parse(paramString);
    } catch (Exception exception) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "time" });
    } 
  }
  
  protected AbstractDateTimeDV.DateTimeData parse(String paramString) throws SchemaDateTimeException {
    AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(paramString, this);
    int i = paramString.length();
    dateTimeData.year = 2000;
    dateTimeData.month = 1;
    dateTimeData.day = 15;
    getTime(paramString, 0, i, dateTimeData);
    validateDateTime(dateTimeData);
    saveUnnormalized(dateTimeData);
    if (dateTimeData.utc != 0 && dateTimeData.utc != 90)
      normalize(dateTimeData); 
    dateTimeData.position = 2;
    return dateTimeData;
  }
  
  protected String dateToString(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    StringBuffer stringBuffer = new StringBuffer(16);
    append(stringBuffer, paramDateTimeData.hour, 2);
    stringBuffer.append(':');
    append(stringBuffer, paramDateTimeData.minute, 2);
    stringBuffer.append(':');
    append(stringBuffer, paramDateTimeData.second);
    append(stringBuffer, (char)paramDateTimeData.utc, 0);
    return stringBuffer.toString();
  }
  
  protected XMLGregorianCalendar getXMLGregorianCalendar(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    return this.factory.newXMLGregorianCalendar((BigInteger)null, -2147483648, -2147483648, paramDateTimeData.unNormHour, paramDateTimeData.unNormMinute, (int)paramDateTimeData.unNormSecond, (paramDateTimeData.unNormSecond != 0.0D) ? new BigDecimal(paramDateTimeData.unNormSecond - (int)paramDateTimeData.unNormSecond) : null, paramDateTimeData.timezoneHr * 60 + paramDateTimeData.timezoneMin);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\TimeDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */