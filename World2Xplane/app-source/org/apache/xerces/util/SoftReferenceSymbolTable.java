package org.apache.xerces.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftReferenceSymbolTable extends SymbolTable {
  protected SREntry[] fBuckets = null;
  
  private final ReferenceQueue fReferenceQueue;
  
  public SoftReferenceSymbolTable(int paramInt, float paramFloat) {
    if (paramInt < 0)
      throw new IllegalArgumentException("Illegal Capacity: " + paramInt); 
    if (paramFloat <= 0.0F || Float.isNaN(paramFloat))
      throw new IllegalArgumentException("Illegal Load: " + paramFloat); 
    if (paramInt == 0)
      paramInt = 1; 
    this.fLoadFactor = paramFloat;
    this.fTableSize = paramInt;
    this.fBuckets = new SREntry[this.fTableSize];
    this.fThreshold = (int)(this.fTableSize * paramFloat);
    this.fCount = 0;
    this.fReferenceQueue = new ReferenceQueue();
  }
  
  public SoftReferenceSymbolTable(int paramInt) {
    this(paramInt, 0.75F);
  }
  
  public SoftReferenceSymbolTable() {
    this(101, 0.75F);
  }
  
  public String addSymbol(String paramString) {
    clean();
    int i = hash(paramString) % this.fTableSize;
    for (SREntry sREntry1 = this.fBuckets[i]; sREntry1 != null; sREntry1 = sREntry1.next) {
      SREntryData sREntryData = sREntry1.get();
      if (sREntryData != null && sREntryData.symbol.equals(paramString))
        return sREntryData.symbol; 
    } 
    if (this.fCount >= this.fThreshold) {
      rehash();
      i = hash(paramString) % this.fTableSize;
    } 
    paramString = paramString.intern();
    SREntry sREntry2 = new SREntry(paramString, this.fBuckets[i], i, this.fReferenceQueue);
    this.fBuckets[i] = sREntry2;
    this.fCount++;
    return paramString;
  }
  
  public String addSymbol(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    clean();
    int i = hash(paramArrayOfchar, paramInt1, paramInt2) % this.fTableSize;
    for (SREntry sREntry1 = this.fBuckets[i]; sREntry1 != null; sREntry1 = sREntry1.next) {
      SREntryData sREntryData = sREntry1.get();
      if (sREntryData != null && paramInt2 == sREntryData.characters.length)
        for (byte b = 0;; b++) {
          if (b >= paramInt2)
            return sREntryData.symbol; 
          if (paramArrayOfchar[paramInt1 + b] != sREntryData.characters[b])
            break; 
        }  
    } 
    if (this.fCount >= this.fThreshold) {
      rehash();
      i = hash(paramArrayOfchar, paramInt1, paramInt2) % this.fTableSize;
    } 
    String str = (new String(paramArrayOfchar, paramInt1, paramInt2)).intern();
    SREntry sREntry2 = new SREntry(str, paramArrayOfchar, paramInt1, paramInt2, this.fBuckets[i], i, this.fReferenceQueue);
    this.fBuckets[i] = sREntry2;
    this.fCount++;
    return str;
  }
  
  protected void rehash() {
    int i = this.fBuckets.length;
    SREntry[] arrayOfSREntry1 = this.fBuckets;
    int j = i * 2 + 1;
    SREntry[] arrayOfSREntry2 = new SREntry[j];
    this.fThreshold = (int)(j * this.fLoadFactor);
    this.fBuckets = arrayOfSREntry2;
    this.fTableSize = this.fBuckets.length;
    int k = i;
    while (k-- > 0) {
      SREntry sREntry = arrayOfSREntry1[k];
      while (sREntry != null) {
        SREntry sREntry1 = sREntry;
        sREntry = sREntry.next;
        SREntryData sREntryData = sREntry1.get();
        if (sREntryData != null) {
          int m = hash(sREntryData.characters, 0, sREntryData.characters.length) % j;
          if (arrayOfSREntry2[m] != null)
            (arrayOfSREntry2[m]).prev = sREntry1; 
          sREntry1.next = arrayOfSREntry2[m];
          arrayOfSREntry2[m] = sREntry1;
          continue;
        } 
        this.fCount--;
      } 
    } 
  }
  
  public boolean containsSymbol(String paramString) {
    int i = hash(paramString) % this.fTableSize;
    int j = paramString.length();
    for (SREntry sREntry = this.fBuckets[i]; sREntry != null; sREntry = sREntry.next) {
      SREntryData sREntryData = sREntry.get();
      if (sREntryData != null && j == sREntryData.characters.length)
        for (byte b = 0;; b++) {
          if (b >= j)
            return true; 
          if (paramString.charAt(b) != sREntryData.characters[b])
            break; 
        }  
    } 
    return false;
  }
  
  public boolean containsSymbol(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = hash(paramArrayOfchar, paramInt1, paramInt2) % this.fTableSize;
    for (SREntry sREntry = this.fBuckets[i]; sREntry != null; sREntry = sREntry.next) {
      SREntryData sREntryData = sREntry.get();
      if (sREntryData != null && paramInt2 == sREntryData.characters.length)
        for (byte b = 0;; b++) {
          if (b >= paramInt2)
            return true; 
          if (paramArrayOfchar[paramInt1 + b] != sREntryData.characters[b])
            break; 
        }  
    } 
    return false;
  }
  
  private void removeEntry(SREntry paramSREntry) {
    if (paramSREntry.next != null)
      paramSREntry.next.prev = paramSREntry.prev; 
    if (paramSREntry.prev != null) {
      paramSREntry.prev.next = paramSREntry.next;
    } else {
      this.fBuckets[paramSREntry.bucket] = paramSREntry.next;
    } 
    this.fCount--;
  }
  
  private void clean() {
    for (SREntry sREntry = (SREntry)this.fReferenceQueue.poll(); sREntry != null; sREntry = (SREntry)this.fReferenceQueue.poll())
      removeEntry(sREntry); 
  }
  
  protected static final class SREntryData {
    public final String symbol;
    
    public final char[] characters;
    
    public SREntryData(String param1String) {
      this.symbol = param1String;
      this.characters = new char[this.symbol.length()];
      this.symbol.getChars(0, this.characters.length, this.characters, 0);
    }
    
    public SREntryData(String param1String, char[] param1ArrayOfchar, int param1Int1, int param1Int2) {
      this.symbol = param1String;
      this.characters = new char[param1Int2];
      System.arraycopy(param1ArrayOfchar, param1Int1, this.characters, 0, param1Int2);
    }
  }
  
  protected static final class SREntry extends SoftReference {
    public SREntry next;
    
    public SREntry prev;
    
    public int bucket;
    
    public SREntry(String param1String, SREntry param1SREntry, int param1Int, ReferenceQueue param1ReferenceQueue) {
      super((T)new SoftReferenceSymbolTable.SREntryData(param1String), param1ReferenceQueue);
      initialize(param1SREntry, param1Int);
    }
    
    public SREntry(String param1String, char[] param1ArrayOfchar, int param1Int1, int param1Int2, SREntry param1SREntry, int param1Int3, ReferenceQueue param1ReferenceQueue) {
      super((T)new SoftReferenceSymbolTable.SREntryData(param1String, param1ArrayOfchar, param1Int1, param1Int2), param1ReferenceQueue);
      initialize(param1SREntry, param1Int3);
    }
    
    private void initialize(SREntry param1SREntry, int param1Int) {
      this.next = param1SREntry;
      if (param1SREntry != null)
        param1SREntry.prev = this; 
      this.prev = null;
      this.bucket = param1Int;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\SoftReferenceSymbolTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */