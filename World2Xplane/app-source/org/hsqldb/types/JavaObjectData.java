package org.hsqldb.types;

import java.io.Serializable;
import org.hsqldb.error.Error;
import org.hsqldb.lib.InOutUtil;

public class JavaObjectData {
  private byte[] data;
  
  JavaObjectData() {}
  
  public JavaObjectData(byte[] paramArrayOfbyte) {
    this.data = paramArrayOfbyte;
  }
  
  public JavaObjectData(Serializable paramSerializable) {
    try {
      this.data = InOutUtil.serialize(paramSerializable);
    } catch (Exception exception) {
      throw Error.error(3473, exception.toString());
    } 
  }
  
  public byte[] getBytes() {
    return this.data;
  }
  
  public int getBytesLength() {
    return this.data.length;
  }
  
  public Serializable getObject() {
    try {
      return InOutUtil.deserialize(this.data);
    } catch (Exception exception) {
      throw Error.error(3473, exception.toString());
    } 
  }
  
  public String toString() {
    return super.toString();
  }
  
  public boolean equals(Object paramObject) {
    return paramObject instanceof JavaObjectData;
  }
  
  public int hashCode() {
    return 1;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\JavaObjectData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */