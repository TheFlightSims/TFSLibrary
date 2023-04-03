package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.LongLookup;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class IntArrayCachedObject extends CachedObjectBase {
  public static final int fileSizeFactor = 4;
  
  int[] values;
  
  public IntArrayCachedObject(int paramInt) {
    this.values = new int[paramInt];
    this.hasChanged = true;
  }
  
  public CachedObject newInstance(int paramInt) {
    return new IntArrayCachedObject(paramInt);
  }
  
  public void read(RowInputInterface paramRowInputInterface) {
    this.position = paramRowInputInterface.getPos();
    int i = this.values.length;
    try {
      for (byte b = 0; b < i; b++)
        this.values[b] = paramRowInputInterface.readInt(); 
    } catch (IOException iOException) {
      throw Error.error(467, iOException);
    } 
    this.hasChanged = false;
  }
  
  public int getDefaultCapacity() {
    return this.values.length;
  }
  
  public int getRealSize(RowOutputInterface paramRowOutputInterface) {
    return this.values.length * 4;
  }
  
  public void write(RowOutputInterface paramRowOutputInterface) {
    int i = this.values.length;
    for (byte b = 0; b < i; b++)
      paramRowOutputInterface.writeInt(this.values[b]); 
    paramRowOutputInterface.writeEnd();
    this.hasChanged = false;
  }
  
  public void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup) {
    write(paramRowOutputInterface);
  }
  
  public int[] getIntArray() {
    return this.values;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\IntArrayCachedObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */