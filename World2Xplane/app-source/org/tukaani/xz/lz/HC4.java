package org.tukaani.xz.lz;

final class HC4 extends LZEncoder {
  private final Hash234 hash;
  
  private final int[] chain;
  
  private final Matches matches;
  
  private final int depthLimit;
  
  private final int cyclicSize;
  
  private int cyclicPos = -1;
  
  private int lzPos;
  
  static final boolean $assertionsDisabled;
  
  static int getMemoryUsage(int paramInt) {
    return Hash234.getMemoryUsage(paramInt) + paramInt / 256 + 10;
  }
  
  HC4(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    super(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    this.hash = new Hash234(paramInt1);
    this.cyclicSize = paramInt1 + 1;
    this.chain = new int[this.cyclicSize];
    this.lzPos = this.cyclicSize;
    this.matches = new Matches(paramInt4 - 1);
    this.depthLimit = (paramInt6 > 0) ? paramInt6 : (4 + paramInt4 / 4);
  }
  
  private int movePos() {
    int i = movePos(4, 4);
    if (i != 0) {
      if (++this.lzPos == Integer.MAX_VALUE) {
        int j = Integer.MAX_VALUE - this.cyclicSize;
        this.hash.normalize(j);
        normalize(this.chain, j);
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
    this.chain[this.cyclicPos] = i1;
    byte b = 0;
    if (m < this.cyclicSize && this.buf[this.readPos - m] == this.buf[this.readPos]) {
      b = 2;
      this.matches.len[0] = 2;
      this.matches.dist[0] = m - 1;
      this.matches.count = 1;
    } 
    if (m != n && n < this.cyclicSize && this.buf[this.readPos - n] == this.buf[this.readPos]) {
      b = 3;
      this.matches.dist[this.matches.count++] = n - 1;
      m = n;
    } 
    if (this.matches.count > 0) {
      while (b < i && this.buf[this.readPos + b - m] == this.buf[this.readPos + b])
        b++; 
      this.matches.len[this.matches.count - 1] = b;
      if (b >= j)
        return this.matches; 
    } 
    if (b < 3)
      b = 3; 
    int i2 = this.depthLimit;
    while (true) {
      int i3 = this.lzPos - i1;
      if (i2-- == 0 || i3 >= this.cyclicSize)
        return this.matches; 
      i1 = this.chain[this.cyclicPos - i3 + ((i3 > this.cyclicPos) ? this.cyclicSize : 0)];
      if (this.buf[this.readPos + b - i3] == this.buf[this.readPos + b] && this.buf[this.readPos - i3] == this.buf[this.readPos]) {
        byte b1 = 0;
        do {
        
        } while (++b1 < i && this.buf[this.readPos + b1 - i3] == this.buf[this.readPos + b1]);
        if (b1 > b) {
          b = b1;
          this.matches.len[this.matches.count] = b1;
          this.matches.dist[this.matches.count] = i3 - 1;
          this.matches.count++;
          if (b1 >= j)
            return this.matches; 
        } 
      } 
    } 
  }
  
  public void skip(int paramInt) {
    assert paramInt >= 0;
    while (paramInt-- > 0) {
      if (movePos() != 0) {
        this.hash.calcHashes(this.buf, this.readPos);
        this.chain[this.cyclicPos] = this.hash.getHash4Pos();
        this.hash.updateTables(this.lzPos);
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lz\HC4.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */