package org.tukaani.xz.lzma;

import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;

final class LZMAEncoderFast extends LZMAEncoder {
  private static int EXTRA_SIZE_BEFORE = 1;
  
  private static int EXTRA_SIZE_AFTER = 272;
  
  private Matches matches = null;
  
  static int getMemoryUsage(int paramInt1, int paramInt2, int paramInt3) {
    return LZEncoder.getMemoryUsage(paramInt1, Math.max(paramInt2, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, 273, paramInt3);
  }
  
  LZMAEncoderFast(RangeEncoder paramRangeEncoder, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    super(paramRangeEncoder, LZEncoder.getInstance(paramInt4, Math.max(paramInt5, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, paramInt6, 273, paramInt7, paramInt8), paramInt1, paramInt2, paramInt3, paramInt4, paramInt6);
  }
  
  private boolean changePair(int paramInt1, int paramInt2) {
    return (paramInt1 < paramInt2 >>> 7);
  }
  
  int getNextSymbol() {
    if (this.readAhead == -1)
      this.matches = getMatches(); 
    this.back = -1;
    int i = Math.min(this.lz.getAvail(), 273);
    if (i < 2)
      return 1; 
    int j = 0;
    int k = 0;
    int m;
    for (m = 0; m < 4; m++) {
      int i2 = this.lz.getMatchLen(this.reps[m], i);
      if (i2 >= 2) {
        if (i2 >= this.niceLen) {
          this.back = m;
          skip(i2 - 1);
          return i2;
        } 
        if (i2 > j) {
          k = m;
          j = i2;
        } 
      } 
    } 
    m = 0;
    int n = 0;
    if (this.matches.count > 0) {
      m = this.matches.len[this.matches.count - 1];
      n = this.matches.dist[this.matches.count - 1];
      if (m >= this.niceLen) {
        this.back = n + 4;
        skip(m - 1);
        return m;
      } 
      while (this.matches.count > 1 && m == this.matches.len[this.matches.count - 2] + 1 && changePair(this.matches.dist[this.matches.count - 2], n)) {
        this.matches.count--;
        m = this.matches.len[this.matches.count - 1];
        n = this.matches.dist[this.matches.count - 1];
      } 
      if (m == 2 && n >= 128)
        m = 1; 
    } 
    if (j >= 2 && (j + 1 >= m || (j + 2 >= m && n >= 512) || (j + 3 >= m && n >= 32768))) {
      this.back = k;
      skip(j - 1);
      return j;
    } 
    if (m < 2 || i <= 2)
      return 1; 
    this.matches = getMatches();
    if (this.matches.count > 0) {
      int i2 = this.matches.len[this.matches.count - 1];
      int i3 = this.matches.dist[this.matches.count - 1];
      if ((i2 >= m && i3 < n) || (i2 == m + 1 && !changePair(n, i3)) || i2 > m + 1 || (i2 + 1 >= m && m >= 3 && changePair(i3, n)))
        return 1; 
    } 
    int i1 = Math.max(m - 1, 2);
    for (byte b = 0; b < 4; b++) {
      if (this.lz.getMatchLen(this.reps[b], i1) == i1)
        return 1; 
    } 
    this.back = n + 4;
    skip(m - 2);
    return m;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lzma\LZMAEncoderFast.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */