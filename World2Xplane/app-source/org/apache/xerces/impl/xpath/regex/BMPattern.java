package org.apache.xerces.impl.xpath.regex;

import java.text.CharacterIterator;

public class BMPattern {
  final char[] pattern;
  
  final int[] shiftTable;
  
  final boolean ignoreCase;
  
  public BMPattern(String paramString, boolean paramBoolean) {
    this(paramString, 256, paramBoolean);
  }
  
  public BMPattern(String paramString, int paramInt, boolean paramBoolean) {
    this.pattern = paramString.toCharArray();
    this.shiftTable = new int[paramInt];
    this.ignoreCase = paramBoolean;
    int i = this.pattern.length;
    for (byte b1 = 0; b1 < this.shiftTable.length; b1++)
      this.shiftTable[b1] = i; 
    for (byte b2 = 0; b2 < i; b2++) {
      char c = this.pattern[b2];
      int j = i - b2 - 1;
      int k = c % this.shiftTable.length;
      if (j < this.shiftTable[k])
        this.shiftTable[k] = j; 
      if (this.ignoreCase) {
        c = Character.toUpperCase(c);
        k = c % this.shiftTable.length;
        if (j < this.shiftTable[k])
          this.shiftTable[k] = j; 
        c = Character.toLowerCase(c);
        k = c % this.shiftTable.length;
        if (j < this.shiftTable[k])
          this.shiftTable[k] = j; 
      } 
    } 
  }
  
  public int matches(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2) {
    if (this.ignoreCase)
      return matchesIgnoreCase(paramCharacterIterator, paramInt1, paramInt2); 
    int i = this.pattern.length;
    if (i == 0)
      return paramInt1; 
    int j = paramInt1 + i;
    while (j <= paramInt2) {
      int k = i;
      int m = j + 1;
      char c;
      while ((c = paramCharacterIterator.setIndex(--j)) == this.pattern[--k]) {
        if (k == 0)
          return j; 
        if (k <= 0)
          break; 
      } 
      j += this.shiftTable[c % this.shiftTable.length] + 1;
      if (j < m)
        j = m; 
    } 
    return -1;
  }
  
  public int matches(String paramString, int paramInt1, int paramInt2) {
    if (this.ignoreCase)
      return matchesIgnoreCase(paramString, paramInt1, paramInt2); 
    int i = this.pattern.length;
    if (i == 0)
      return paramInt1; 
    int j = paramInt1 + i;
    while (j <= paramInt2) {
      int k = i;
      int m = j + 1;
      char c;
      while ((c = paramString.charAt(--j)) == this.pattern[--k]) {
        if (k == 0)
          return j; 
        if (k <= 0)
          break; 
      } 
      j += this.shiftTable[c % this.shiftTable.length] + 1;
      if (j < m)
        j = m; 
    } 
    return -1;
  }
  
  public int matches(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.ignoreCase)
      return matchesIgnoreCase(paramArrayOfchar, paramInt1, paramInt2); 
    int i = this.pattern.length;
    if (i == 0)
      return paramInt1; 
    int j = paramInt1 + i;
    while (j <= paramInt2) {
      int k = i;
      int m = j + 1;
      char c;
      while ((c = paramArrayOfchar[--j]) == this.pattern[--k]) {
        if (k == 0)
          return j; 
        if (k <= 0)
          break; 
      } 
      j += this.shiftTable[c % this.shiftTable.length] + 1;
      if (j < m)
        j = m; 
    } 
    return -1;
  }
  
  int matchesIgnoreCase(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2) {
    int i = this.pattern.length;
    if (i == 0)
      return paramInt1; 
    int j = paramInt1 + i;
    while (j <= paramInt2) {
      char c;
      int k = i;
      int m = j + 1;
      do {
        char c1 = c = paramCharacterIterator.setIndex(--j);
        char c2 = this.pattern[--k];
        if (c1 != c2) {
          c1 = Character.toUpperCase(c1);
          c2 = Character.toUpperCase(c2);
          if (c1 != c2 && Character.toLowerCase(c1) != Character.toLowerCase(c2))
            break; 
        } 
        if (k == 0)
          return j; 
      } while (k > 0);
      j += this.shiftTable[c % this.shiftTable.length] + 1;
      if (j < m)
        j = m; 
    } 
    return -1;
  }
  
  int matchesIgnoreCase(String paramString, int paramInt1, int paramInt2) {
    int i = this.pattern.length;
    if (i == 0)
      return paramInt1; 
    int j = paramInt1 + i;
    while (j <= paramInt2) {
      char c;
      int k = i;
      int m = j + 1;
      do {
        char c1 = c = paramString.charAt(--j);
        char c2 = this.pattern[--k];
        if (c1 != c2) {
          c1 = Character.toUpperCase(c1);
          c2 = Character.toUpperCase(c2);
          if (c1 != c2 && Character.toLowerCase(c1) != Character.toLowerCase(c2))
            break; 
        } 
        if (k == 0)
          return j; 
      } while (k > 0);
      j += this.shiftTable[c % this.shiftTable.length] + 1;
      if (j < m)
        j = m; 
    } 
    return -1;
  }
  
  int matchesIgnoreCase(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = this.pattern.length;
    if (i == 0)
      return paramInt1; 
    int j = paramInt1 + i;
    while (j <= paramInt2) {
      char c;
      int k = i;
      int m = j + 1;
      do {
        char c1 = c = paramArrayOfchar[--j];
        char c2 = this.pattern[--k];
        if (c1 != c2) {
          c1 = Character.toUpperCase(c1);
          c2 = Character.toUpperCase(c2);
          if (c1 != c2 && Character.toLowerCase(c1) != Character.toLowerCase(c2))
            break; 
        } 
        if (k == 0)
          return j; 
      } while (k > 0);
      j += this.shiftTable[c % this.shiftTable.length] + 1;
      if (j < m)
        j = m; 
    } 
    return -1;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xpath\regex\BMPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */