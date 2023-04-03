package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.LongLookup;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class DirectoryBlockCachedObject extends CachedObjectBase {
  public static final int fileSizeFactor = 12;
  
  int[] tableIds;
  
  int[] bitmapAddress;
  
  char[] freeSpace;
  
  char[] freeSpaceBlock;
  
  public DirectoryBlockCachedObject(int paramInt) {
    this.tableIds = new int[paramInt];
    this.bitmapAddress = new int[paramInt];
    this.freeSpace = new char[paramInt];
    this.freeSpaceBlock = new char[paramInt];
    this.hasChanged = true;
  }
  
  public CachedObject newInstance(int paramInt) {
    return new DirectoryBlockCachedObject(paramInt);
  }
  
  public void read(RowInputInterface paramRowInputInterface) {
    this.position = paramRowInputInterface.getPos();
    int i = this.tableIds.length;
    try {
      byte b;
      for (b = 0; b < i; b++)
        this.tableIds[b] = paramRowInputInterface.readInt(); 
      for (b = 0; b < i; b++)
        this.bitmapAddress[b] = paramRowInputInterface.readInt(); 
      for (b = 0; b < i; b++)
        this.freeSpace[b] = paramRowInputInterface.readChar(); 
      for (b = 0; b < i; b++)
        this.freeSpaceBlock[b] = paramRowInputInterface.readChar(); 
    } catch (IOException iOException) {
      throw Error.error(467, iOException);
    } 
    this.hasChanged = false;
  }
  
  public int getDefaultCapacity() {
    return this.tableIds.length;
  }
  
  public int getRealSize(RowOutputInterface paramRowOutputInterface) {
    return this.tableIds.length * 12;
  }
  
  public void write(RowOutputInterface paramRowOutputInterface) {
    int i = this.tableIds.length;
    byte b;
    for (b = 0; b < i; b++)
      paramRowOutputInterface.writeInt(this.tableIds[b]); 
    for (b = 0; b < i; b++)
      paramRowOutputInterface.writeInt(this.bitmapAddress[b]); 
    for (b = 0; b < i; b++)
      paramRowOutputInterface.writeChar(this.freeSpace[b]); 
    for (b = 0; b < i; b++)
      paramRowOutputInterface.writeChar(this.freeSpaceBlock[b]); 
    paramRowOutputInterface.writeEnd();
    this.hasChanged = false;
  }
  
  public void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup) {
    write(paramRowOutputInterface);
  }
  
  public int[] getTableIdArray() {
    return this.tableIds;
  }
  
  public int[] getBitmapAddressArray() {
    return this.bitmapAddress;
  }
  
  public char[] getFreeSpaceArray() {
    return this.freeSpace;
  }
  
  public char[] getFreeBlockArray() {
    return this.freeSpaceBlock;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\DirectoryBlockCachedObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */