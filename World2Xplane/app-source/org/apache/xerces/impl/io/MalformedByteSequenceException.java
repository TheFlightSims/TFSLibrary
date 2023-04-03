package org.apache.xerces.impl.io;

import java.io.CharConversionException;
import java.util.Locale;
import org.apache.xerces.util.MessageFormatter;

public class MalformedByteSequenceException extends CharConversionException {
  static final long serialVersionUID = 8436382245048328739L;
  
  private MessageFormatter fFormatter;
  
  private Locale fLocale;
  
  private String fDomain;
  
  private String fKey;
  
  private Object[] fArguments;
  
  private String fMessage;
  
  public MalformedByteSequenceException(MessageFormatter paramMessageFormatter, Locale paramLocale, String paramString1, String paramString2, Object[] paramArrayOfObject) {
    this.fFormatter = paramMessageFormatter;
    this.fLocale = paramLocale;
    this.fDomain = paramString1;
    this.fKey = paramString2;
    this.fArguments = paramArrayOfObject;
  }
  
  public String getDomain() {
    return this.fDomain;
  }
  
  public String getKey() {
    return this.fKey;
  }
  
  public Object[] getArguments() {
    return this.fArguments;
  }
  
  public synchronized String getMessage() {
    if (this.fMessage == null) {
      this.fMessage = this.fFormatter.formatMessage(this.fLocale, this.fKey, this.fArguments);
      this.fFormatter = null;
      this.fLocale = null;
    } 
    return this.fMessage;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\io\MalformedByteSequenceException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */