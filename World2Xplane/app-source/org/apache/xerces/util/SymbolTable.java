package org.apache.xerces.util;

public class SymbolTable {
  protected static final int TABLE_SIZE = 101;
  
  protected Entry[] fBuckets = null;
  
  protected int fTableSize;
  
  protected transient int fCount;
  
  protected int fThreshold;
  
  protected float fLoadFactor;
  
  public SymbolTable(int paramInt, float paramFloat) {
    if (paramInt < 0)
      throw new IllegalArgumentException("Illegal Capacity: " + paramInt); 
    if (paramFloat <= 0.0F || Float.isNaN(paramFloat))
      throw new IllegalArgumentException("Illegal Load: " + paramFloat); 
    if (paramInt == 0)
      paramInt = 1; 
    this.fLoadFactor = paramFloat;
    this.fTableSize = paramInt;
    this.fBuckets = new Entry[this.fTableSize];
    this.fThreshold = (int)(this.fTableSize * paramFloat);
    this.fCount = 0;
  }
  
  public SymbolTable(int paramInt) {
    this(paramInt, 0.75F);
  }
  
  public SymbolTable() {
    this(101, 0.75F);
  }
  
  public String addSymbol(String paramString) {
    int i = hash(paramString) % this.fTableSize;
    for (Entry entry1 = this.fBuckets[i]; entry1 != null; entry1 = entry1.next) {
      if (entry1.symbol.equals(paramString))
        return entry1.symbol; 
    } 
    if (this.fCount >= this.fThreshold) {
      rehash();
      i = hash(paramString) % this.fTableSize;
    } 
    Entry entry2 = new Entry(paramString, this.fBuckets[i]);
    this.fBuckets[i] = entry2;
    this.fCount++;
    return entry2.symbol;
  }
  
  public String addSymbol(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = hash(paramArrayOfchar, paramInt1, paramInt2) % this.fTableSize;
    for (Entry entry1 = this.fBuckets[i]; entry1 != null; entry1 = entry1.next) {
      if (paramInt2 == entry1.characters.length)
        for (byte b = 0;; b++) {
          if (b >= paramInt2)
            return entry1.symbol; 
          if (paramArrayOfchar[paramInt1 + b] != entry1.characters[b])
            break; 
        }  
    } 
    if (this.fCount >= this.fThreshold) {
      rehash();
      i = hash(paramArrayOfchar, paramInt1, paramInt2) % this.fTableSize;
    } 
    Entry entry2 = new Entry(paramArrayOfchar, paramInt1, paramInt2, this.fBuckets[i]);
    this.fBuckets[i] = entry2;
    this.fCount++;
    return entry2.symbol;
  }
  
  public int hash(String paramString) {
    return paramString.hashCode() & 0x7FFFFFF;
  }
  
  public int hash(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = 0;
    for (byte b = 0; b < paramInt2; b++)
      i = i * 31 + paramArrayOfchar[paramInt1 + b]; 
    return i & 0x7FFFFFF;
  }
  
  protected void rehash() {
    int i = this.fBuckets.length;
    Entry[] arrayOfEntry1 = this.fBuckets;
    int j = i * 2 + 1;
    Entry[] arrayOfEntry2 = new Entry[j];
    this.fThreshold = (int)(j * this.fLoadFactor);
    this.fBuckets = arrayOfEntry2;
    this.fTableSize = this.fBuckets.length;
    int k = i;
    while (k-- > 0) {
      Entry entry = arrayOfEntry1[k];
      while (entry != null) {
        Entry entry1 = entry;
        entry = entry.next;
        int m = hash(entry1.characters, 0, entry1.characters.length) % j;
        entry1.next = arrayOfEntry2[m];
        arrayOfEntry2[m] = entry1;
      } 
    } 
  }
  
  public boolean containsSymbol(String paramString) {
    int i = hash(paramString) % this.fTableSize;
    int j = paramString.length();
    for (Entry entry = this.fBuckets[i]; entry != null; entry = entry.next) {
      if (j == entry.characters.length)
        for (byte b = 0;; b++) {
          if (b >= j)
            return true; 
          if (paramString.charAt(b) != entry.characters[b])
            break; 
        }  
    } 
    return false;
  }
  
  public boolean containsSymbol(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = hash(paramArrayOfchar, paramInt1, paramInt2) % this.fTableSize;
    for (Entry entry = this.fBuckets[i]; entry != null; entry = entry.next) {
      if (paramInt2 == entry.characters.length)
        for (byte b = 0;; b++) {
          if (b >= paramInt2)
            return true; 
          if (paramArrayOfchar[paramInt1 + b] != entry.characters[b])
            break; 
        }  
    } 
    return false;
  }
  
  protected static final class Entry {
    public final String symbol;
    
    public final char[] characters;
    
    public Entry next;
    
    public Entry(String param1String, Entry param1Entry) {
      this.symbol = param1String.intern();
      this.characters = new char[param1String.length()];
      param1String.getChars(0, this.characters.length, this.characters, 0);
      this.next = param1Entry;
    }
    
    public Entry(char[] param1ArrayOfchar, int param1Int1, int param1Int2, Entry param1Entry) {
      this.characters = new char[param1Int2];
      System.arraycopy(param1ArrayOfchar, param1Int1, this.characters, 0, param1Int2);
      this.symbol = (new String(this.characters)).intern();
      this.next = param1Entry;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\SymbolTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */