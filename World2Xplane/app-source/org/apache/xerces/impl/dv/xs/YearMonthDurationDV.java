package org.apache.xerces.impl.dv.xs;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.Duration;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

class YearMonthDurationDV extends DurationDV {
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return parse(paramString, 1);
    } catch (Exception exception) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "yearMonthDuration" });
    } 
  }
  
  protected Duration getDuration(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    byte b = 1;
    if (paramDateTimeData.year < 0 || paramDateTimeData.month < 0)
      b = -1; 
    return this.factory.newDuration((b == 1), (paramDateTimeData.year != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.year)) : null, (paramDateTimeData.month != Integer.MIN_VALUE) ? BigInteger.valueOf((b * paramDateTimeData.month)) : null, (BigInteger)null, (BigInteger)null, (BigInteger)null, (BigDecimal)null);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dv\xs\YearMonthDurationDV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */