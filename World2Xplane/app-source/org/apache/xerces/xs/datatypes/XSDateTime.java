package org.apache.xerces.xs.datatypes;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

public interface XSDateTime {
  int getYears();
  
  int getMonths();
  
  int getDays();
  
  int getHours();
  
  int getMinutes();
  
  double getSeconds();
  
  boolean hasTimeZone();
  
  int getTimeZoneHours();
  
  int getTimeZoneMinutes();
  
  String getLexicalValue();
  
  XSDateTime normalize();
  
  boolean isNormalized();
  
  XMLGregorianCalendar getXMLGregorianCalendar();
  
  Duration getDuration();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\datatypes\XSDateTime.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */