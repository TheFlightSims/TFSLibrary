package org.hsqldb.map;

public class BitMap {
  int defaultCapacity;
  
  int capacity;
  
  int[] map;
  
  int limitPos;
  
  final boolean extendCapacity;
  
  public BitMap(int paramInt, boolean paramBoolean) {
    int i = paramInt / 32;
    if (paramInt % 32 != 0)
      i++; 
    this.defaultCapacity = this.capacity = i * 32;
    this.map = new int[i];
    this.limitPos = 0;
    this.extendCapacity = paramBoolean;
  }
  
  public BitMap(int[] paramArrayOfint) {
    this.map = paramArrayOfint;
    this.defaultCapacity = this.capacity = paramArrayOfint.length * 32;
    this.limitPos = this.capacity;
    this.extendCapacity = false;
  }
  
  public int size() {
    return this.limitPos;
  }
  
  public void setSize(int paramInt) {
    this.limitPos = paramInt;
  }
  
  public void reset() {
    if (this.capacity == this.defaultCapacity) {
      for (byte b = 0; b < this.map.length; b++)
        this.map[b] = 0; 
    } else {
      this.map = new int[this.defaultCapacity / 32];
      this.capacity = this.defaultCapacity;
    } 
    this.limitPos = 0;
  }
  
  public void setRange(int paramInt1, int paramInt2) {
    setOrUnsetRange(paramInt1, paramInt2, true);
  }
  
  public void unsetRange(int paramInt1, int paramInt2) {
    setOrUnsetRange(paramInt1, paramInt2, false);
  }
  
  private void setOrUnsetRange(int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramInt1 + paramInt2 > this.capacity)
      doubleCapacity(); 
    if (paramInt1 + paramInt2 > this.limitPos)
      this.limitPos = paramInt1 + paramInt2; 
    int i = paramInt1 >> 5;
    int j = paramInt1 + paramInt2 - 1 >> 5;
    if (i == j) {
      int i1 = -1 >>> (paramInt1 & 0x1F);
      int i2 = Integer.MIN_VALUE >> (paramInt1 + paramInt2 - 1 & 0x1F);
      i1 &= i2;
      int i3 = this.map[i];
      if (paramBoolean) {
        this.map[i] = i3 | i1;
      } else {
        i1 ^= 0xFFFFFFFF;
        this.map[i] = i3 & i1;
      } 
      return;
    } 
    int k = -1 >>> (paramInt1 & 0x1F);
    int m = this.map[i];
    if (paramBoolean) {
      this.map[i] = m | k;
    } else {
      k ^= 0xFFFFFFFF;
      this.map[i] = m & k;
    } 
    k = Integer.MIN_VALUE >> (paramInt1 + paramInt2 - 1 & 0x1F);
    m = this.map[j];
    if (paramBoolean) {
      this.map[j] = m | k;
    } else {
      k ^= 0xFFFFFFFF;
      this.map[j] = m & k;
    } 
    for (int n = i + 1; n < j; n++)
      this.map[n] = paramBoolean ? -1 : 0; 
  }
  
  public int setValue(int paramInt, boolean paramBoolean) {
    return paramBoolean ? set(paramInt) : unset(paramInt);
  }
  
  public int set(int paramInt) {
    while (paramInt >= this.capacity)
      doubleCapacity(); 
    if (paramInt >= this.limitPos)
      this.limitPos = paramInt + 1; 
    int i = paramInt >> 5;
    int j = Integer.MIN_VALUE >>> (paramInt & 0x1F);
    int k = this.map[i];
    boolean bool = ((k & j) == 0) ? false : true;
    this.map[i] = k | j;
    return bool;
  }
  
  public int unset(int paramInt) {
    while (paramInt >= this.capacity)
      doubleCapacity(); 
    if (paramInt >= this.limitPos) {
      this.limitPos = paramInt + 1;
      return 0;
    } 
    int i = paramInt >> 5;
    int j = Integer.MIN_VALUE >>> (paramInt & 0x1F);
    int k = this.map[i];
    boolean bool = ((k & j) == 0) ? false : true;
    j ^= 0xFFFFFFFF;
    this.map[i] = k & j;
    return bool;
  }
  
  public int get(int paramInt) {
    if (paramInt >= this.limitPos)
      throw new ArrayIndexOutOfBoundsException(paramInt); 
    int i = paramInt >> 5;
    int j = Integer.MIN_VALUE >>> (paramInt & 0x1F);
    int k = this.map[i];
    return ((k & j) == 0) ? 0 : 1;
  }
  
  public boolean isSet(int paramInt) {
    return (get(paramInt) == 1);
  }
  
  public int countSet(int paramInt1, int paramInt2) {
    byte b = 0;
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++) {
      if (isSet(i))
        b++; 
    } 
    return b;
  }
  
  public int countSetBits() {
    int i = 0;
    int j;
    for (j = 0; j < this.limitPos / 32; j++) {
      int k = this.map[j];
      if (k != 0)
        if (k == -1) {
          i += true;
        } else {
          i += Integer.bitCount(k);
        }  
    } 
    if (this.limitPos % 32 != 0) {
      j = this.map[this.limitPos / 32];
      i += Integer.bitCount(j);
    } 
    return i;
  }
  
  public int countSetBitsEnd() {
    int i = 0;
    for (int j = this.limitPos / 32 - 1; j >= 0; j--) {
      if (this.map[j] == -1) {
        i += true;
      } else {
        int k = countSetBitsLow(this.map[j]);
        i += k;
        break;
      } 
    } 
    return i;
  }
  
  public int[] getIntArray() {
    return this.map;
  }
  
  public byte[] getBytes() {
    byte[] arrayOfByte = new byte[(this.limitPos + 7) / 8];
    if (arrayOfByte.length == 0)
      return arrayOfByte; 
    byte b = 0;
    do {
      int i = this.map[b / 4];
      arrayOfByte[b++] = (byte)(i >>> 24);
      if (b == arrayOfByte.length)
        break; 
      arrayOfByte[b++] = (byte)(i >>> 16);
      if (b == arrayOfByte.length)
        break; 
      arrayOfByte[b++] = (byte)(i >>> 8);
      if (b == arrayOfByte.length)
        break; 
      arrayOfByte[b++] = (byte)i;
    } while (b != arrayOfByte.length);
    return arrayOfByte;
  }
  
  private void doubleCapacity() {
    if (!this.extendCapacity)
      throw new ArrayStoreException("BitMap extend"); 
    int[] arrayOfInt = new int[this.map.length * 2];
    this.capacity *= 2;
    System.arraycopy(this.map, 0, arrayOfInt, 0, this.map.length);
    this.map = arrayOfInt;
  }
  
  public static int countSetBitsLow(int paramInt) {
    byte b1 = 1;
    byte b2;
    for (b2 = 0; b2 < 32 && (paramInt & b1) != 0; b2++)
      paramInt >>= 1; 
    return b2;
  }
  
  public static int setByte(int paramInt1, byte paramByte, int paramInt2) {
    int i = (paramByte & 0xFF) << 24 - paramInt2;
    int j = -16777216 >>> paramInt2;
    j ^= 0xFFFFFFFF;
    paramInt1 &= j;
    return paramInt1 | i;
  }
  
  public static int set(int paramInt1, int paramInt2) {
    int i = Integer.MIN_VALUE >>> paramInt2;
    return paramInt1 | i;
  }
  
  public static byte set(byte paramByte, int paramInt) {
    int i = 128 >>> paramInt;
    return (byte)(paramByte | i);
  }
  
  public static int unset(int paramInt1, int paramInt2) {
    int i = Integer.MIN_VALUE >>> paramInt2;
    i ^= 0xFFFFFFFF;
    return paramInt1 & i;
  }
  
  public static boolean isSet(int paramInt1, int paramInt2) {
    int i = Integer.MIN_VALUE >>> paramInt2;
    return !((paramInt1 & i) == 0);
  }
  
  public static boolean isSet(byte paramByte, int paramInt) {
    int i = 128 >>> paramInt;
    return !((paramByte & i) == 0);
  }
  
  public static boolean isSet(byte[] paramArrayOfbyte, int paramInt) {
    int i = 128 >>> (paramInt & 0x7);
    int j = paramInt / 8;
    if (j >= paramArrayOfbyte.length)
      return false; 
    byte b = paramArrayOfbyte[j];
    return !((b & i) == 0);
  }
  
  public static void unset(byte[] paramArrayOfbyte, int paramInt) {
    int i = 128 >>> (paramInt & 0x7);
    i ^= 0xFFFFFFFF;
    int j = paramInt / 8;
    if (j >= paramArrayOfbyte.length)
      return; 
    byte b = paramArrayOfbyte[j];
    paramArrayOfbyte[j] = (byte)(b & i);
  }
  
  public static void set(byte[] paramArrayOfbyte, int paramInt) {
    int i = 128 >>> (paramInt & 0x7);
    int j = paramInt / 8;
    if (j >= paramArrayOfbyte.length)
      return; 
    byte b = paramArrayOfbyte[j];
    paramArrayOfbyte[j] = (byte)(b | i);
  }
  
  public static void and(byte[] paramArrayOfbyte, int paramInt1, byte paramByte, int paramInt2) {
    int i = paramInt1 & 0x7;
    int j = (paramByte & 0xFF) >>> i;
    int k = 255 >> i;
    int m = paramInt1 / 8;
    if (paramInt2 < 8) {
      k >>>= 8 - paramInt2;
      k <<= 8 - paramInt2;
    } 
    j &= k;
    k ^= 0xFFFFFFFF;
    if (m >= paramArrayOfbyte.length)
      return; 
    byte b = paramArrayOfbyte[m];
    paramArrayOfbyte[m] = (byte)(b & k);
    b = (byte)(b & j);
    paramArrayOfbyte[m] = (byte)(paramArrayOfbyte[m] | b);
    if (i == 0)
      return; 
    i = 8 - i;
    if (paramInt2 > i) {
      j = (paramByte & 0xFF) << 8 >>> i;
      k = 65280 >>> i;
      k ^= 0xFFFFFFFF;
      b = paramArrayOfbyte[m + 1];
      paramArrayOfbyte[m + 1] = (byte)(b & k);
      b = (byte)(b & j);
      paramArrayOfbyte[m + 1] = (byte)(paramArrayOfbyte[m + 1] | b);
    } 
  }
  
  public static void or(byte[] paramArrayOfbyte, int paramInt1, byte paramByte, int paramInt2) {
    int i = paramInt1 & 0x7;
    int j = (paramByte & 0xFF) >>> i;
    int k = paramInt1 / 8;
    if (k >= paramArrayOfbyte.length)
      return; 
    byte b = (byte)(paramArrayOfbyte[k] | j);
    paramArrayOfbyte[k] = b;
    if (i == 0)
      return; 
    i = 8 - i;
    if (paramInt2 > i) {
      j = (paramByte & 0xFF) << 8 >>> i;
      b = (byte)(paramArrayOfbyte[k + 1] | j);
      paramArrayOfbyte[k + 1] = b;
    } 
  }
  
  public static void overlay(byte[] paramArrayOfbyte, int paramInt1, byte paramByte, int paramInt2) {
    int i = paramInt1 & 0x7;
    int j = (paramByte & 0xFF) >>> i;
    int k = 255 >> i;
    int m = paramInt1 / 8;
    if (paramInt2 < 8) {
      k >>>= 8 - paramInt2;
      k <<= 8 - paramInt2;
    } 
    j &= k;
    k ^= 0xFFFFFFFF;
    if (m >= paramArrayOfbyte.length)
      return; 
    byte b = paramArrayOfbyte[m];
    b = (byte)(b & k);
    paramArrayOfbyte[m] = (byte)(b | j);
    if (i == 0)
      return; 
    i = 8 - i;
    if (paramInt2 > i) {
      j = (paramByte & 0xFF) << 8 >>> i;
      k = 65280 >>> i;
      k ^= 0xFFFFFFFF;
      b = paramArrayOfbyte[m + 1];
      b = (byte)(b & k);
      paramArrayOfbyte[m + 1] = (byte)(b | j);
    } 
  }
  
  public static byte[] and(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    int i = (paramArrayOfbyte1.length > paramArrayOfbyte2.length) ? paramArrayOfbyte1.length : paramArrayOfbyte2.length;
    int j = (paramArrayOfbyte1.length > paramArrayOfbyte2.length) ? paramArrayOfbyte2.length : paramArrayOfbyte1.length;
    byte[] arrayOfByte = new byte[i];
    for (byte b = 0; b < j; b++)
      arrayOfByte[b] = (byte)(paramArrayOfbyte1[b] & paramArrayOfbyte2[b]); 
    return arrayOfByte;
  }
  
  public static byte[] or(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    int i = (paramArrayOfbyte1.length > paramArrayOfbyte2.length) ? paramArrayOfbyte1.length : paramArrayOfbyte2.length;
    int j = (paramArrayOfbyte1.length > paramArrayOfbyte2.length) ? paramArrayOfbyte2.length : paramArrayOfbyte1.length;
    byte[] arrayOfByte = new byte[i];
    if (i != j) {
      byte[] arrayOfByte1 = (paramArrayOfbyte1.length > paramArrayOfbyte2.length) ? paramArrayOfbyte1 : paramArrayOfbyte2;
      System.arraycopy(arrayOfByte1, j, arrayOfByte, j, i - j);
    } 
    for (byte b = 0; b < j; b++)
      arrayOfByte[b] = (byte)(paramArrayOfbyte1[b] | paramArrayOfbyte2[b]); 
    return arrayOfByte;
  }
  
  public static byte[] xor(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    int i = (paramArrayOfbyte1.length > paramArrayOfbyte2.length) ? paramArrayOfbyte1.length : paramArrayOfbyte2.length;
    int j = (paramArrayOfbyte1.length > paramArrayOfbyte2.length) ? paramArrayOfbyte2.length : paramArrayOfbyte1.length;
    byte[] arrayOfByte = new byte[i];
    if (i != j) {
      byte[] arrayOfByte1 = (paramArrayOfbyte1.length > paramArrayOfbyte2.length) ? paramArrayOfbyte1 : paramArrayOfbyte2;
      System.arraycopy(arrayOfByte1, j, arrayOfByte, j, i - j);
    } 
    for (byte b = 0; b < j; b++)
      arrayOfByte[b] = (byte)(paramArrayOfbyte1[b] ^ paramArrayOfbyte2[b]); 
    return arrayOfByte;
  }
  
  public static byte[] not(byte[] paramArrayOfbyte) {
    byte[] arrayOfByte = new byte[paramArrayOfbyte.length];
    for (byte b = 0; b < paramArrayOfbyte.length; b++)
      arrayOfByte[b] = (byte)(paramArrayOfbyte[b] ^ 0xFFFFFFFF); 
    return arrayOfByte;
  }
  
  public static boolean hasAnyBitSet(byte[] paramArrayOfbyte) {
    for (byte b = 0; b < paramArrayOfbyte.length; b++) {
      if (paramArrayOfbyte[b] != 0)
        return true; 
    } 
    return false;
  }
  
  public static byte[] leftShift(byte[] paramArrayOfbyte, int paramInt) {
    byte[] arrayOfByte = new byte[paramArrayOfbyte.length];
    int i = paramInt / 8;
    if (i >= paramArrayOfbyte.length)
      return arrayOfByte; 
    paramInt %= 8;
    if (paramInt == 0) {
      byte b = 0;
      for (int j = i; j < paramArrayOfbyte.length; j++) {
        arrayOfByte[b] = paramArrayOfbyte[j];
        b++;
      } 
    } else {
      byte b = 0;
      for (int j = i; j < paramArrayOfbyte.length; j++) {
        int k = (paramArrayOfbyte[j] & 0xFF) << paramInt;
        arrayOfByte[b] = (byte)k;
        if (b)
          arrayOfByte[b - 1] = (byte)(arrayOfByte[b - 1] | (byte)(k >>> 8)); 
        b++;
      } 
    } 
    return arrayOfByte;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\map\BitMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */