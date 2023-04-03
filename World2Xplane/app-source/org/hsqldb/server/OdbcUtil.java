package org.hsqldb.server;

import java.io.IOException;
import java.util.Locale;
import org.hsqldb.ColumnBase;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.result.ResultMetaData;

public class OdbcUtil {
  static final int ODBC_SM_DATABASE = 64;
  
  static final int ODBC_SM_USER = 32;
  
  static final int ODBC_SM_OPTIONS = 64;
  
  static final int ODBC_SM_UNUSED = 64;
  
  static final int ODBC_SM_TTY = 64;
  
  static final int ODBC_AUTH_REQ_PASSWORD = 3;
  
  static final int ODBC_AUTH_REQ_OK = 0;
  
  static String[][] hardcodedParams = new String[][] { { "client_encoding", "SQL_ASCII" }, { "DateStyle", "ISO, MDY" }, { "integer_datetimes", "on" }, { "is_superuser", "on" }, { "server_encoding", "SQL_ASCII" }, { "server_version", "8.3.1" }, { "session_authorization", "blaine" }, { "standard_conforming_strings", "off" }, { "TimeZone", "US/Eastern" } };
  
  static final int ODBC_SIMPLE_MODE = 0;
  
  static final int ODBC_EXTENDED_MODE = 1;
  
  static final int ODBC_EXT_RECOVER_MODE = 2;
  
  static final int ODBC_SEVERITY_FATAL = 1;
  
  static final int ODBC_SEVERITY_ERROR = 2;
  
  static final int ODBC_SEVERITY_PANIC = 3;
  
  static final int ODBC_SEVERITY_WARNING = 4;
  
  static final int ODBC_SEVERITY_NOTICE = 5;
  
  static final int ODBC_SEVERITY_DEBUG = 6;
  
  static final int ODBC_SEVERITY_INFO = 7;
  
  static final int ODBC_SEVERITY_LOG = 8;
  
  static IntKeyHashMap odbcSeverityMap = new IntKeyHashMap();
  
  static void validateInputPacketSize(OdbcPacketInputStream paramOdbcPacketInputStream) throws RecoverableOdbcFailure {
    int i = -1;
    try {
      i = paramOdbcPacketInputStream.available();
    } catch (IOException iOException) {}
    if (i < 1)
      return; 
    throw new RecoverableOdbcFailure("Client supplied bad length for " + paramOdbcPacketInputStream.packetType + " packet.  " + i + " bytes available after processing", "Bad length for " + paramOdbcPacketInputStream.packetType + " packet.  " + i + " extra bytes", "08P01");
  }
  
  static String echoBackReplyString(String paramString, int paramInt) {
    String str1 = paramString.trim().toUpperCase(Locale.ENGLISH);
    byte b;
    for (b = 0; b < str1.length() && !Character.isWhitespace(str1.charAt(b)); b++);
    StringBuffer stringBuffer = new StringBuffer(str1.substring(0, b));
    String str2 = stringBuffer.toString();
    if (str2.equals("UPDATE") || str2.equals("DELETE")) {
      stringBuffer.append(" " + paramInt);
    } else if (str2.equals("CREATE") || str2.equals("DROP")) {
      byte b1;
      for (b1 = b; b1 < str1.length() && Character.isWhitespace(str1.charAt(b1)); b1++);
      byte b2;
      for (b2 = b1; b2 < str1.length() && Character.isWhitespace(str1.charAt(b2)); b2++);
      stringBuffer.append(" " + str1.substring(b1, b2));
    } else if (str2.equals("INSERT")) {
      stringBuffer.append(" 0 " + paramInt);
    } 
    return stringBuffer.toString();
  }
  
  static void writeParam(String paramString1, String paramString2, DataOutputStream paramDataOutputStream) throws IOException {
    OdbcPacketOutputStream odbcPacketOutputStream = OdbcPacketOutputStream.newOdbcPacketOutputStream();
    odbcPacketOutputStream.write(paramString1);
    odbcPacketOutputStream.write(paramString2);
    odbcPacketOutputStream.xmit('S', paramDataOutputStream);
    odbcPacketOutputStream.close();
  }
  
  static void alertClient(int paramInt, String paramString, DataOutputStream paramDataOutputStream) throws IOException {
    alertClient(paramInt, paramString, null, paramDataOutputStream);
  }
  
  static void alertClient(int paramInt, String paramString1, String paramString2, DataOutputStream paramDataOutputStream) throws IOException {
    if (paramString2 == null)
      paramString2 = "XX000"; 
    if (!odbcSeverityMap.containsKey(paramInt))
      throw new IllegalArgumentException("Unknown severity value (" + paramInt + ')'); 
    OdbcPacketOutputStream odbcPacketOutputStream = OdbcPacketOutputStream.newOdbcPacketOutputStream();
    odbcPacketOutputStream.write("S" + odbcSeverityMap.get(paramInt));
    if (paramInt < 5)
      odbcPacketOutputStream.write("C" + paramString2); 
    odbcPacketOutputStream.write("M" + paramString1);
    odbcPacketOutputStream.writeByte(0);
    odbcPacketOutputStream.xmit((paramInt < 5) ? 69 : 78, paramDataOutputStream);
    odbcPacketOutputStream.close();
  }
  
  static String revertMungledPreparedQuery(String paramString) {
    return paramString.replaceAll("\\$\\d+", "?");
  }
  
  public static int getTableOidForColumn(int paramInt, ResultMetaData paramResultMetaData) {
    if (!paramResultMetaData.isTableColumn(paramInt))
      return 0; 
    ColumnBase columnBase = paramResultMetaData.columns[paramInt];
    int i = (columnBase.getSchemaNameString() + '.' + columnBase.getTableNameString()).hashCode();
    if (i < 0)
      i *= -1; 
    return i;
  }
  
  public static short getIdForColumn(int paramInt, ResultMetaData paramResultMetaData) {
    if (!paramResultMetaData.isTableColumn(paramInt))
      return 0; 
    short s = (short)paramResultMetaData.getGeneratedColumnNames()[paramInt].hashCode();
    if (s < 0)
      s = (short)(s * -1); 
    return s;
  }
  
  public static String hexCharsToOctalOctets(String paramString) {
    int i = paramString.length();
    if (i != i / 2 * 2)
      throw new IllegalArgumentException("Hex character lists contains an odd number of characters: " + i); 
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < i; b++) {
      int j = 0;
      char c = paramString.charAt(b);
      if (c >= 'a' && c <= 'f') {
        j += 10 + c - 97;
      } else if (c >= 'A' && c <= 'F') {
        j += 10 + c - 65;
      } else if (c >= '0' && c <= '9') {
        j += c - 48;
      } else {
        throw new IllegalArgumentException("Non-hex character in input at offset " + b + ": " + c);
      } 
      j <<= 4;
      c = paramString.charAt(++b);
      if (c >= 'a' && c <= 'f') {
        j += 10 + c - 97;
      } else if (c >= 'A' && c <= 'F') {
        j += 10 + c - 65;
      } else if (c >= '0' && c <= '9') {
        j += c - 48;
      } else {
        throw new IllegalArgumentException("Non-hex character in input at offset " + b + ": " + c);
      } 
      stringBuffer.append('\\');
      stringBuffer.append((char)(48 + (j >> 6)));
      stringBuffer.append((char)(48 + (j >> 3 & 0x7)));
      stringBuffer.append((char)(48 + (j & 0x7)));
    } 
    return stringBuffer.toString();
  }
  
  public static void main(String[] paramArrayOfString) {
    System.out.println("(" + hexCharsToOctalOctets(paramArrayOfString[0]) + ')');
  }
  
  static {
    odbcSeverityMap.put(1, "FATAL");
    odbcSeverityMap.put(2, "ERROR");
    odbcSeverityMap.put(3, "PANIC");
    odbcSeverityMap.put(4, "WARNING");
    odbcSeverityMap.put(5, "NOTICE");
    odbcSeverityMap.put(6, "DEBUG");
    odbcSeverityMap.put(7, "INFO");
    odbcSeverityMap.put(8, "LOG");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\OdbcUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */