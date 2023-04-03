package org.tukaani.xz.lzma;

import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;

final class LZMAEncoderNormal extends LZMAEncoder {
  private static final int OPTS = 4096;
  
  private static int EXTRA_SIZE_BEFORE = 4096;
  
  private static int EXTRA_SIZE_AFTER = 4096;
  
  private final Optimum[] opts = new Optimum[4096];
  
  private int optCur = 0;
  
  private int optEnd = 0;
  
  private Matches matches;
  
  static final boolean $assertionsDisabled;
  
  static int getMemoryUsage(int paramInt1, int paramInt2, int paramInt3) {
    return LZEncoder.getMemoryUsage(paramInt1, Math.max(paramInt2, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, 273, paramInt3) + 256;
  }
  
  LZMAEncoderNormal(RangeEncoder paramRangeEncoder, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    super(paramRangeEncoder, LZEncoder.getInstance(paramInt4, Math.max(paramInt5, EXTRA_SIZE_BEFORE), EXTRA_SIZE_AFTER, paramInt6, 273, paramInt7, paramInt8), paramInt1, paramInt2, paramInt3, paramInt4, paramInt6);
    for (byte b = 0; b < 'က'; b++)
      this.opts[b] = new Optimum(); 
  }
  
  public void reset() {
    this.optCur = 0;
    this.optEnd = 0;
    super.reset();
  }
  
  private int convertOpts() {
    this.optEnd = this.optCur;
    int i = (this.opts[this.optCur]).optPrev;
    while (true) {
      Optimum optimum = this.opts[this.optCur];
      if (optimum.prev1IsLiteral) {
        (this.opts[i]).optPrev = this.optCur;
        (this.opts[i]).backPrev = -1;
        this.optCur = i--;
        if (optimum.hasPrev2) {
          (this.opts[i]).optPrev = i + 1;
          (this.opts[i]).backPrev = optimum.backPrev2;
          this.optCur = i;
          i = optimum.optPrev2;
        } 
      } 
      int j = (this.opts[i]).optPrev;
      (this.opts[i]).optPrev = this.optCur;
      this.optCur = i;
      i = j;
      if (this.optCur <= 0) {
        this.optCur = (this.opts[0]).optPrev;
        this.back = (this.opts[this.optCur]).backPrev;
        return this.optCur;
      } 
    } 
  }
  
  int getNextSymbol() {
    if (this.optCur < this.optEnd) {
      int i7 = (this.opts[this.optCur]).optPrev - this.optCur;
      this.optCur = (this.opts[this.optCur]).optPrev;
      this.back = (this.opts[this.optCur]).backPrev;
      return i7;
    } 
    assert this.optCur == this.optEnd;
    this.optCur = 0;
    this.optEnd = 0;
    this.back = -1;
    if (this.readAhead == -1)
      this.matches = getMatches(); 
    int i = Math.min(this.lz.getAvail(), 273);
    if (i < 2)
      return 1; 
    int[] arrayOfInt = new int[4];
    int j = 0;
    int k;
    for (k = 0; k < 4; k++) {
      arrayOfInt[k] = this.lz.getMatchLen(this.reps[k], i);
      if (arrayOfInt[k] < 2) {
        arrayOfInt[k] = 0;
      } else if (arrayOfInt[k] > arrayOfInt[j]) {
        j = k;
      } 
    } 
    if (arrayOfInt[j] >= this.niceLen) {
      this.back = j;
      skip(arrayOfInt[j] - 1);
      return arrayOfInt[j];
    } 
    k = 0;
    int m = 0;
    if (this.matches.count > 0) {
      k = this.matches.len[this.matches.count - 1];
      m = this.matches.dist[this.matches.count - 1];
      if (k >= this.niceLen) {
        this.back = m + 4;
        skip(k - 1);
        return k;
      } 
    } 
    int n = this.lz.getByte(0);
    int i1 = this.lz.getByte(this.reps[0] + 1);
    if (k < 2 && n != i1 && arrayOfInt[j] < 2)
      return 1; 
    int i2 = this.lz.getPos();
    int i3 = i2 & this.posMask;
    int i4 = this.lz.getByte(1);
    int i5 = this.literalEncoder.getPrice(n, i1, i4, i2, this.state);
    this.opts[1].set1(i5, 0, -1);
    i4 = getAnyMatchPrice(this.state, i3);
    i5 = getAnyRepPrice(i4, this.state);
    if (i1 == n) {
      int i7 = getShortRepPrice(i5, this.state, i3);
      if (i7 < (this.opts[1]).price)
        this.opts[1].set1(i7, 0, 0); 
    } 
    this.optEnd = Math.max(k, arrayOfInt[j]);
    if (this.optEnd < 2) {
      assert this.optEnd == 0 : this.optEnd;
      this.back = (this.opts[1]).backPrev;
      return 1;
    } 
    updatePrices();
    (this.opts[0]).state.set(this.state);
    System.arraycopy(this.reps, 0, (this.opts[0]).reps, 0, 4);
    int i6;
    for (i6 = this.optEnd; i6 >= 2; i6--)
      this.opts[i6].reset(); 
    for (i6 = 0; i6 < 4; i6++) {
      int i7 = arrayOfInt[i6];
      if (i7 >= 2) {
        int i8 = getLongRepPrice(i5, i6, this.state, i3);
        do {
          int i9 = i8 + this.repLenEncoder.getPrice(i7, i3);
          if (i9 >= (this.opts[i7]).price)
            continue; 
          this.opts[i7].set1(i9, 0, i6);
        } while (--i7 >= 2);
      } 
    } 
    i6 = Math.max(arrayOfInt[0] + 1, 2);
    if (i6 <= k) {
      int i7 = getNormalMatchPrice(i4, this.state);
      byte b;
      for (b = 0; i6 > this.matches.len[b]; b++);
      while (true) {
        int i8 = this.matches.dist[b];
        int i9 = getMatchAndLenPrice(i7, i8, i6, i3);
        if (i9 < (this.opts[i6]).price)
          this.opts[i6].set1(i9, 0, i8 + 4); 
        if (i6 == this.matches.len[b] && ++b == this.matches.count)
          break; 
        i6++;
      } 
    } 
    i = Math.min(this.lz.getAvail(), 4095);
    while (++this.optCur < this.optEnd) {
      this.matches = getMatches();
      if (this.matches.count > 0 && this.matches.len[this.matches.count - 1] >= this.niceLen)
        break; 
      i--;
      i3 = ++i2 & this.posMask;
      updateOptStateAndReps();
      i4 = (this.opts[this.optCur]).price + getAnyMatchPrice((this.opts[this.optCur]).state, i3);
      i5 = getAnyRepPrice(i4, (this.opts[this.optCur]).state);
      calc1BytePrices(i2, i3, i, i5);
      if (i >= 2) {
        i6 = calcLongRepPrices(i2, i3, i, i5);
        if (this.matches.count > 0)
          calcNormalMatchPrices(i2, i3, i, i4, i6); 
      } 
    } 
    return convertOpts();
  }
  
  private void updateOptStateAndReps() {
    int i = (this.opts[this.optCur]).optPrev;
    assert i < this.optCur;
    if ((this.opts[this.optCur]).prev1IsLiteral) {
      i--;
      if ((this.opts[this.optCur]).hasPrev2) {
        (this.opts[this.optCur]).state.set((this.opts[(this.opts[this.optCur]).optPrev2]).state);
        if ((this.opts[this.optCur]).backPrev2 < 4) {
          (this.opts[this.optCur]).state.updateLongRep();
        } else {
          (this.opts[this.optCur]).state.updateMatch();
        } 
      } else {
        (this.opts[this.optCur]).state.set((this.opts[i]).state);
      } 
      (this.opts[this.optCur]).state.updateLiteral();
    } else {
      (this.opts[this.optCur]).state.set((this.opts[i]).state);
    } 
    if (i == this.optCur - 1) {
      assert (this.opts[this.optCur]).backPrev == 0 || (this.opts[this.optCur]).backPrev == -1;
      if ((this.opts[this.optCur]).backPrev == 0) {
        (this.opts[this.optCur]).state.updateShortRep();
      } else {
        (this.opts[this.optCur]).state.updateLiteral();
      } 
      System.arraycopy((this.opts[i]).reps, 0, (this.opts[this.optCur]).reps, 0, 4);
    } else {
      int j;
      if ((this.opts[this.optCur]).prev1IsLiteral && (this.opts[this.optCur]).hasPrev2) {
        i = (this.opts[this.optCur]).optPrev2;
        j = (this.opts[this.optCur]).backPrev2;
        (this.opts[this.optCur]).state.updateLongRep();
      } else {
        j = (this.opts[this.optCur]).backPrev;
        if (j < 4) {
          (this.opts[this.optCur]).state.updateLongRep();
        } else {
          (this.opts[this.optCur]).state.updateMatch();
        } 
      } 
      if (j < 4) {
        (this.opts[this.optCur]).reps[0] = (this.opts[i]).reps[j];
        byte b;
        for (b = 1; b <= j; b++)
          (this.opts[this.optCur]).reps[b] = (this.opts[i]).reps[b - 1]; 
        while (b < 4) {
          (this.opts[this.optCur]).reps[b] = (this.opts[i]).reps[b];
          b++;
        } 
      } else {
        (this.opts[this.optCur]).reps[0] = j - 4;
        System.arraycopy((this.opts[i]).reps, 0, (this.opts[this.optCur]).reps, 1, 3);
      } 
    } 
  }
  
  private void calc1BytePrices(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    boolean bool = false;
    int i = this.lz.getByte(0);
    int j = this.lz.getByte((this.opts[this.optCur]).reps[0] + 1);
    int k = (this.opts[this.optCur]).price + this.literalEncoder.getPrice(i, j, this.lz.getByte(1), paramInt1, (this.opts[this.optCur]).state);
    if (k < (this.opts[this.optCur + 1]).price) {
      this.opts[this.optCur + 1].set1(k, this.optCur, -1);
      bool = true;
    } 
    if (j == i && ((this.opts[this.optCur + 1]).optPrev == this.optCur || (this.opts[this.optCur + 1]).backPrev != 0)) {
      int m = getShortRepPrice(paramInt4, (this.opts[this.optCur]).state, paramInt2);
      if (m <= (this.opts[this.optCur + 1]).price) {
        this.opts[this.optCur + 1].set1(m, this.optCur, 0);
        bool = true;
      } 
    } 
    if (!bool && j != i && paramInt3 > 2) {
      int m = Math.min(this.niceLen, paramInt3 - 1);
      int n = this.lz.getMatchLen(1, (this.opts[this.optCur]).reps[0], m);
      if (n >= 2) {
        State state = new State((this.opts[this.optCur]).state);
        state.updateLiteral();
        int i1 = paramInt1 + 1 & this.posMask;
        int i2 = k + getLongRepAndLenPrice(0, n, state, i1);
        int i3 = this.optCur + 1 + n;
        while (this.optEnd < i3)
          this.opts[++this.optEnd].reset(); 
        if (i2 < (this.opts[i3]).price)
          this.opts[i3].set2(i2, this.optCur, 0); 
      } 
    } 
  }
  
  private int calcLongRepPrices(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = 2;
    int j = Math.min(paramInt3, this.niceLen);
    for (byte b = 0; b < 4; b++) {
      int k = this.lz.getMatchLen((this.opts[this.optCur]).reps[b], j);
      if (k >= 2) {
        while (this.optEnd < this.optCur + k)
          this.opts[++this.optEnd].reset(); 
        int m = getLongRepPrice(paramInt4, b, (this.opts[this.optCur]).state, paramInt2);
        int n;
        for (n = k; n >= 2; n--) {
          int i2 = m + this.repLenEncoder.getPrice(n, paramInt2);
          if (i2 < (this.opts[this.optCur + n]).price)
            this.opts[this.optCur + n].set1(i2, this.optCur, b); 
        } 
        if (b == 0)
          i = k + 1; 
        n = Math.min(this.niceLen, paramInt3 - k - 1);
        int i1 = this.lz.getMatchLen(k + 1, (this.opts[this.optCur]).reps[b], n);
        if (i1 >= 2) {
          int i2 = m + this.repLenEncoder.getPrice(k, paramInt2);
          State state = new State((this.opts[this.optCur]).state);
          state.updateLongRep();
          int i3 = this.lz.getByte(k, 0);
          int i4 = this.lz.getByte(0);
          int i5 = this.lz.getByte(k, 1);
          i2 += this.literalEncoder.getPrice(i3, i4, i5, paramInt1 + k, state);
          state.updateLiteral();
          int i6 = paramInt1 + k + 1 & this.posMask;
          i2 += getLongRepAndLenPrice(0, i1, state, i6);
          int i7 = this.optCur + k + 1 + i1;
          while (this.optEnd < i7)
            this.opts[++this.optEnd].reset(); 
          if (i2 < (this.opts[i7]).price)
            this.opts[i7].set3(i2, this.optCur, b, k, 0); 
        } 
      } 
    } 
    return i;
  }
  
  private void calcNormalMatchPrices(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (this.matches.len[this.matches.count - 1] > paramInt3) {
      this.matches.count = 0;
      while (this.matches.len[this.matches.count] < paramInt3)
        this.matches.count++; 
      this.matches.len[this.matches.count++] = paramInt3;
    } 
    if (this.matches.len[this.matches.count - 1] < paramInt5)
      return; 
    while (this.optEnd < this.optCur + this.matches.len[this.matches.count - 1])
      this.opts[++this.optEnd].reset(); 
    int i = getNormalMatchPrice(paramInt4, (this.opts[this.optCur]).state);
    byte b;
    for (b = 0; paramInt5 > this.matches.len[b]; b++);
    for (int j = paramInt5;; j++) {
      int k = this.matches.dist[b];
      int m = getMatchAndLenPrice(i, k, j, paramInt2);
      if (m < (this.opts[this.optCur + j]).price)
        this.opts[this.optCur + j].set1(m, this.optCur, k + 4); 
      if (j == this.matches.len[b]) {
        int n = Math.min(this.niceLen, paramInt3 - j - 1);
        int i1 = this.lz.getMatchLen(j + 1, k, n);
        if (i1 >= 2) {
          State state = new State((this.opts[this.optCur]).state);
          state.updateMatch();
          int i2 = this.lz.getByte(j, 0);
          int i3 = this.lz.getByte(0);
          int i4 = this.lz.getByte(j, 1);
          int i5 = m + this.literalEncoder.getPrice(i2, i3, i4, paramInt1 + j, state);
          state.updateLiteral();
          int i6 = paramInt1 + j + 1 & this.posMask;
          i5 += getLongRepAndLenPrice(0, i1, state, i6);
          int i7 = this.optCur + j + 1 + i1;
          while (this.optEnd < i7)
            this.opts[++this.optEnd].reset(); 
          if (i5 < (this.opts[i7]).price)
            this.opts[i7].set3(i5, this.optCur, k + 4, j, 0); 
        } 
        if (++b == this.matches.count)
          break; 
      } 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lzma\LZMAEncoderNormal.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */