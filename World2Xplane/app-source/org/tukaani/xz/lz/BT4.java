package org.tukaani.xz.lz;

final class BT4 extends LZEncoder {
  private final Hash234 hash;
  
  private final int[] tree;
  
  private final Matches matches;
  
  private final int depthLimit;
  
  private final int cyclicSize;
  
  private int cyclicPos = -1;
  
  private int lzPos;
  
  static int getMemoryUsage(int paramInt) {
    return Hash234.getMemoryUsage(paramInt) + paramInt / 128 + 10;
  }
  
  BT4(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    this.cyclicSize = paramInt1 + 1;
    this.lzPos = this.cyclicSize;
    this.hash = new Hash234(paramInt1);
    this.tree = new int[this.cyclicSize * 2];
    this.matches = new Matches(paramInt4 - 1);
    this.depthLimit = (paramInt6 > 0) ? paramInt6 : (16 + paramInt4 / 2);
  }
  
  private int movePos() {
    int i = movePos(this.niceLen, 4);
    if (i != 0) {
      if (++this.lzPos == Integer.MAX_VALUE) {
        int j = Integer.MAX_VALUE - this.cyclicSize;
        this.hash.normalize(j);
        normalize(this.tree, j);
        this.lzPos -= j;
      } 
      if (++this.cyclicPos == this.cyclicSize)
        this.cyclicPos = 0; 
    } 
    return i;
  }
  
  public Matches getMatches() {
    this.matches.count = 0;
    int i = this.matchLenMax;
    int j = this.niceLen;
    int k = movePos();
    if (k < i) {
      if (k == 0)
        return this.matches; 
      i = k;
      if (j > k)
        j = k; 
    } 
    this.hash.calcHashes(this.buf, this.readPos);
    int m = this.lzPos - this.hash.getHash2Pos();
    int n = this.lzPos - this.hash.getHash3Pos();
    int i1 = this.hash.getHash4Pos();
    this.hash.updateTables(this.lzPos);
    int i2 = 0;
    if (m < this.cyclicSize && this.buf[this.readPos - m] == this.buf[this.readPos]) {
      i2 = 2;
      this.matches.len[0] = 2;
      this.matches.dist[0] = m - 1;
      this.matches.count = 1;
    } 
    if (m != n && n < this.cyclicSize && this.buf[this.readPos - n] == this.buf[this.readPos]) {
      i2 = 3;
      this.matches.dist[this.matches.count++] = n - 1;
      m = n;
    } 
    if (this.matches.count > 0) {
      while (i2 < i && this.buf[this.readPos + i2 - m] == this.buf[this.readPos + i2])
        i2++; 
      this.matches.len[this.matches.count - 1] = i2;
      if (i2 >= j) {
        skip(j, i1);
        return this.matches;
      } 
    } 
    if (i2 < 3)
      i2 = 3; 
    int i3 = this.depthLimit;
    int i4 = (this.cyclicPos << 1) + 1;
    int i5 = this.cyclicPos << 1;
    int i6 = 0;
    int i7 = 0;
    while (true) {
      int i8 = this.lzPos - i1;
      if (i3-- == 0 || i8 >= this.cyclicSize) {
        this.tree[i4] = 0;
        this.tree[i5] = 0;
        return this.matches;
      } 
      int i9 = this.cyclicPos - i8 + ((i8 > this.cyclicPos) ? this.cyclicSize : 0) << 1;
      int i10 = Math.min(i6, i7);
      if (this.buf[this.readPos + i10 - i8] == this.buf[this.readPos + i10]) {
        do {
        
        } while (++i10 < i && this.buf[this.readPos + i10 - i8] == this.buf[this.readPos + i10]);
        if (i10 > i2) {
          i2 = i10;
          this.matches.len[this.matches.count] = i10;
          this.matches.dist[this.matches.count] = i8 - 1;
          this.matches.count++;
          if (i10 >= j) {
            this.tree[i5] = this.tree[i9];
            this.tree[i4] = this.tree[i9 + 1];
            return this.matches;
          } 
        } 
      } 
      if ((this.buf[this.readPos + i10 - i8] & 0xFF) < (this.buf[this.readPos + i10] & 0xFF)) {
        this.tree[i5] = i1;
        i5 = i9 + 1;
        i1 = this.tree[i5];
        i7 = i10;
        continue;
      } 
      this.tree[i4] = i1;
      i4 = i9;
      i1 = this.tree[i4];
      i6 = i10;
    } 
  }
  
  private void skip(int paramInt1, int paramInt2) {
    int i = this.depthLimit;
    int j = (this.cyclicPos << 1) + 1;
    int k = this.cyclicPos << 1;
    int m = 0;
    int n = 0;
    while (true) {
      int i1 = this.lzPos - paramInt2;
      if (i-- == 0 || i1 >= this.cyclicSize) {
        this.tree[j] = 0;
        this.tree[k] = 0;
        return;
      } 
      int i2 = this.cyclicPos - i1 + ((i1 > this.cyclicPos) ? this.cyclicSize : 0) << 1;
      int i3 = Math.min(m, n);
      if (this.buf[this.readPos + i3 - i1] == this.buf[this.readPos + i3])
        do {
          if (++i3 == paramInt1) {
            this.tree[k] = this.tree[i2];
            this.tree[j] = this.tree[i2 + 1];
            return;
          } 
        } while (this.buf[this.readPos + i3 - i1] == this.buf[this.readPos + i3]); 
      if ((this.buf[this.readPos + i3 - i1] & 0xFF) < (this.buf[this.readPos + i3] & 0xFF)) {
        this.tree[k] = paramInt2;
        k = i2 + 1;
        paramInt2 = this.tree[k];
        n = i3;
        continue;
      } 
      this.tree[j] = paramInt2;
      j = i2;
      paramInt2 = this.tree[j];
      m = i3;
    } 
  }
  
  public void skip(int paramInt) {
    while (paramInt-- > 0) {
      int i = this.niceLen;
      int j = movePos();
      if (j < i) {
        if (j == 0)
          continue; 
        i = j;
      } 
      this.hash.calcHashes(this.buf, this.readPos);
      int k = this.hash.getHash4Pos();
      this.hash.updateTables(this.lzPos);
      skip(i, k);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lz\BT4.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */