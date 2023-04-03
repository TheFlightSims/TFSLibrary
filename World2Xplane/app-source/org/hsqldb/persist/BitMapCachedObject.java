package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.LongLookup;
import org.hsqldb.map.BitMap;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class BitMapCachedObject extends CachedObjectBase {
  public static final int fileSizeFactor = 4;
  
  BitMap bitMap;
  
  public BitMapCachedObject(int paramInt) {
    this.bitMap = new BitMap(new int[paramInt]);
    this.hasChanged = true;
  }
  
  public CachedObject newInstance(int paramInt) {
    return new BitMapCachedObject(paramInt);
  }
  
  public void read(RowInputInterface paramRowInputInterface) {
    this.position = paramRowInputInterface.getPos();
    int[] arrayOfInt = this.bitMap.getIntArray();
    int i = arrayOfInt.length;
    try {
      for (byte b = 0; b < i; b++)
        arrayOfInt[b] = paramRowInputInterface.readInt(); 
    } catch (IOException iOException) {
      throw Error.error(467, iOException);
    } 
    this.hasChanged = false;
  }
  
  public int getDefaultCapacity() {
    return (this.bitMap.getIntArray()).length;
  }
  
  public int getRealSize(RowOutputInterface paramRowOutputInterface) {
    return (this.bitMap.getIntArray()).length * 4;
  }
  
  public void write(RowOutputInterface paramRowOutputInterface) {
    int[] arrayOfInt = this.bitMap.getIntArray();
    int i = arrayOfInt.length;
    for (byte b = 0; b < i; b++)
      paramRowOutputInterface.writeInt(arrayOfInt[b]); 
    paramRowOutputInterface.writeEnd();
    this.hasChanged = false;
  }
  
  public void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup) {
    write(paramRowOutputInterface);
  }
  
  public BitMap getBitMap() {
    return this.bitMap;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\BitMapCachedObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */