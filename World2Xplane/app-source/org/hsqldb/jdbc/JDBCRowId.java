package org.hsqldb.jdbc;

import java.io.IOException;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.Arrays;
import org.hsqldb.lib.StringConverter;

public final class JDBCRowId implements RowId {
  private int hash;
  
  private final byte[] id;
  
  public JDBCRowId(byte[] paramArrayOfbyte) throws SQLException {
    if (paramArrayOfbyte == null)
      throw JDBCUtil.nullArgument("id"); 
    this.id = paramArrayOfbyte;
  }
  
  public JDBCRowId(RowId paramRowId) throws SQLException {
    this(paramRowId.getBytes());
  }
  
  public JDBCRowId(String paramString) throws SQLException {
    if (paramString == null)
      throw JDBCUtil.nullArgument("hex"); 
    try {
      this.id = StringConverter.hexStringToByteArray(paramString);
    } catch (IOException iOException) {
      throw JDBCUtil.sqlException(423, "hex: " + iOException);
    } 
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof JDBCRowId && Arrays.equals(this.id, ((JDBCRowId)paramObject).id));
  }
  
  public byte[] getBytes() {
    return (byte[])this.id.clone();
  }
  
  public String toString() {
    return StringConverter.byteArrayToHexString(this.id);
  }
  
  public int hashCode() {
    if (this.hash == 0)
      this.hash = Arrays.hashCode(this.id); 
    return this.hash;
  }
  
  Object id() {
    return this.id;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\jdbc\JDBCRowId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */