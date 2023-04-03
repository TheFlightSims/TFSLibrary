package org.hsqldb.map;

public class HashIndex {
  int[] hashTable;
  
  int[] linkTable;
  
  int newNodePointer;
  
  int elementCount;
  
  int reclaimedNodePointer = -1;
  
  boolean fixedSize;
  
  boolean modified;
  
  public HashIndex(int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramInt2 < paramInt1)
      paramInt2 = paramInt1; 
    reset(paramInt1, paramInt2);
    this.fixedSize = paramBoolean;
  }
  
  public void reset(int paramInt1, int paramInt2) {
    int[] arrayOfInt1 = new int[paramInt1];
    int[] arrayOfInt2 = new int[paramInt2];
    this.hashTable = arrayOfInt1;
    this.linkTable = arrayOfInt2;
    resetTables();
  }
  
  public void resetTables() {
    int i = this.hashTable.length;
    int[] arrayOfInt = this.hashTable;
    while (--i >= 0)
      arrayOfInt[i] = -1; 
    this.newNodePointer = 0;
    this.elementCount = 0;
    this.reclaimedNodePointer = -1;
    this.modified = false;
  }
  
  public void clear() {
    int i = this.linkTable.length;
    int[] arrayOfInt = this.linkTable;
    while (--i >= 0)
      arrayOfInt[i] = 0; 
    resetTables();
  }
  
  public int getHashIndex(int paramInt) {
    return (paramInt & Integer.MAX_VALUE) % this.hashTable.length;
  }
  
  public int getLookup(int paramInt) {
    if (this.elementCount == 0)
      return -1; 
    int i = (paramInt & Integer.MAX_VALUE) % this.hashTable.length;
    return this.hashTable[i];
  }
  
  public int getNextLookup(int paramInt) {
    return this.linkTable[paramInt];
  }
  
  public int linkNode(int paramInt1, int paramInt2) {
    int i = this.reclaimedNodePointer;
    if (i == -1) {
      i = this.newNodePointer++;
    } else {
      this.reclaimedNodePointer = this.linkTable[i];
    } 
    if (paramInt2 == -1) {
      this.hashTable[paramInt1] = i;
    } else {
      this.linkTable[paramInt2] = i;
    } 
    this.linkTable[i] = -1;
    this.elementCount++;
    this.modified = true;
    return i;
  }
  
  public void unlinkNode(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt2 == -1) {
      this.hashTable[paramInt1] = this.linkTable[paramInt3];
    } else {
      this.linkTable[paramInt2] = this.linkTable[paramInt3];
    } 
    this.linkTable[paramInt3] = this.reclaimedNodePointer;
    this.reclaimedNodePointer = paramInt3;
    this.elementCount--;
  }
  
  public boolean removeEmptyNode(int paramInt) {
    boolean bool = false;
    int i = -1;
    int j;
    for (j = this.reclaimedNodePointer; j >= 0; j = this.linkTable[j]) {
      if (j == paramInt) {
        if (i == -1) {
          this.reclaimedNodePointer = this.linkTable[paramInt];
        } else {
          this.linkTable[i] = this.linkTable[paramInt];
        } 
        bool = true;
        break;
      } 
      i = j;
    } 
    if (!bool)
      return false; 
    for (j = 0; j < this.newNodePointer; j++) {
      if (this.linkTable[j] > paramInt)
        this.linkTable[j] = this.linkTable[j] - 1; 
    } 
    System.arraycopy(this.linkTable, paramInt + 1, this.linkTable, paramInt, this.newNodePointer - paramInt - 1);
    this.linkTable[this.newNodePointer - 1] = 0;
    this.newNodePointer--;
    for (j = 0; j < this.hashTable.length; j++) {
      if (this.hashTable[j] > paramInt)
        this.hashTable[j] = this.hashTable[j] - 1; 
    } 
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\map\HashIndex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */