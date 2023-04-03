package org.apache.xerces.util;

public class SymbolHash {
  protected int fTableSize = 101;
  
  protected Entry[] fBuckets;
  
  protected int fNum = 0;
  
  public SymbolHash() {
    this.fBuckets = new Entry[this.fTableSize];
  }
  
  public SymbolHash(int paramInt) {
    this.fTableSize = paramInt;
    this.fBuckets = new Entry[this.fTableSize];
  }
  
  public void put(Object paramObject1, Object paramObject2) {
    int i = (paramObject1.hashCode() & Integer.MAX_VALUE) % this.fTableSize;
    Entry entry = search(paramObject1, i);
    if (entry != null) {
      entry.value = paramObject2;
    } else {
      entry = new Entry(paramObject1, paramObject2, this.fBuckets[i]);
      this.fBuckets[i] = entry;
      this.fNum++;
    } 
  }
  
  public Object get(Object paramObject) {
    int i = (paramObject.hashCode() & Integer.MAX_VALUE) % this.fTableSize;
    Entry entry = search(paramObject, i);
    return (entry != null) ? entry.value : null;
  }
  
  public int getLength() {
    return this.fNum;
  }
  
  public int getValues(Object[] paramArrayOfObject, int paramInt) {
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < this.fTableSize && b2 < this.fNum) {
      for (Entry entry = this.fBuckets[b1]; entry != null; entry = entry.next) {
        paramArrayOfObject[paramInt + b2] = entry.value;
        b2++;
      } 
      b1++;
    } 
    return this.fNum;
  }
  
  public SymbolHash makeClone() {
    SymbolHash symbolHash = new SymbolHash(this.fTableSize);
    symbolHash.fNum = this.fNum;
    for (byte b = 0; b < this.fTableSize; b++) {
      if (this.fBuckets[b] != null)
        symbolHash.fBuckets[b] = this.fBuckets[b].makeClone(); 
    } 
    return symbolHash;
  }
  
  public void clear() {
    for (byte b = 0; b < this.fTableSize; b++)
      this.fBuckets[b] = null; 
    this.fNum = 0;
  }
  
  protected Entry search(Object paramObject, int paramInt) {
    for (Entry entry = this.fBuckets[paramInt]; entry != null; entry = entry.next) {
      if (paramObject.equals(entry.key))
        return entry; 
    } 
    return null;
  }
  
  protected static final class Entry {
    public Object key = null;
    
    public Object value = null;
    
    public Entry next = null;
    
    public Entry() {}
    
    public Entry(Object param1Object1, Object param1Object2, Entry param1Entry) {}
    
    public Entry makeClone() {
      Entry entry = new Entry();
      entry.key = this.key;
      entry.value = this.value;
      if (this.next != null)
        entry.next = this.next.makeClone(); 
      return entry;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\SymbolHash.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */