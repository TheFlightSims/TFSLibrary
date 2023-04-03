package org.apache.xerces.jaxp.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

public class DatatypeFactoryImpl extends DatatypeFactory {
  public Duration newDuration(String paramString) {
    return new DurationImpl(paramString);
  }
  
  public Duration newDuration(long paramLong) {
    return new DurationImpl(paramLong);
  }
  
  public Duration newDuration(boolean paramBoolean, BigInteger paramBigInteger1, BigInteger paramBigInteger2, BigInteger paramBigInteger3, BigInteger paramBigInteger4, BigInteger paramBigInteger5, BigDecimal paramBigDecimal) {
    return new DurationImpl(paramBoolean, paramBigInteger1, paramBigInteger2, paramBigInteger3, paramBigInteger4, paramBigInteger5, paramBigDecimal);
  }
  
  public XMLGregorianCalendar newXMLGregorianCalendar() {
    return new XMLGregorianCalendarImpl();
  }
  
  public XMLGregorianCalendar newXMLGregorianCalendar(String paramString) {
    return new XMLGregorianCalendarImpl(paramString);
  }
  
  public XMLGregorianCalendar newXMLGregorianCalendar(GregorianCalendar paramGregorianCalendar) {
    return new XMLGregorianCalendarImpl(paramGregorianCalendar);
  }
  
  public XMLGregorianCalendar newXMLGregorianCalendar(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    return XMLGregorianCalendarImpl.createDateTime(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
  }
  
  public XMLGregorianCalendar newXMLGregorianCalendar(BigInteger paramBigInteger, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BigDecimal paramBigDecimal, int paramInt6) {
    return new XMLGregorianCalendarImpl(paramBigInteger, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramBigDecimal, paramInt6);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\datatype\DatatypeFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */