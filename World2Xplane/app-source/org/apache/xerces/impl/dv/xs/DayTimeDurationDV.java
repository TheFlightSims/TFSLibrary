package org.apache.xerces.impl.dv.xs;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.Duration;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

class DayTimeDurationDV extends DurationDV {
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return parse(paramString, 2);
    } catch (Exception exception) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "dayTimeDuration" });
    } 
  }
  
  protected Duration getDuration(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    byte b = 1;
    if (paramDateTimeData.day < 0 || paramDateTimeData.hour < 0 || paramDateTimeData.minute < 0 || paramDateTimeData.second < 0.0D)
      b = -1; 
    return this.factory.newDuration((b == 1), (BigInteger)null, (BigInteger)null, (paramDateTimeData.day != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.day)) : null, (paramDateTimeData.hour != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.hour)) : null, (paramDateTimeData.minute != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.minute)) : null, (paramDateTimeData.second != -2.147483648E9D) ? new BigDecimal(String.valueOf(b * paramDateTimeData.second)) : null);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\DayTimeDurationDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */