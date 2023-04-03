package org.hsqldb.lib;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class BasicTextJdkLogFormatter extends Formatter {
  protected boolean withTime = true;
  
  public static String LS = System.getProperty("line.separator");
  
  protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
  
  public BasicTextJdkLogFormatter(boolean paramBoolean) {
    this.withTime = paramBoolean;
  }
  
  public BasicTextJdkLogFormatter() {}
  
  public String format(LogRecord paramLogRecord) {
    StringBuilder stringBuilder = new StringBuilder();
    if (this.withTime)
      stringBuilder.append(this.sdf.format(new Date(paramLogRecord.getMillis())) + "  "); 
    stringBuilder.append(paramLogRecord.getLevel() + "  " + formatMessage(paramLogRecord));
    if (paramLogRecord.getThrown() != null) {
      StringWriter stringWriter = new StringWriter();
      paramLogRecord.getThrown().printStackTrace(new PrintWriter(stringWriter));
      stringBuilder.append(LS + stringWriter);
    } 
    return stringBuilder.toString() + LS;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\BasicTextJdkLogFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */