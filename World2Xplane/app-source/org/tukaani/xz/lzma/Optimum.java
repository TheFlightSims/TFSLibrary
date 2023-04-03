package org.tukaani.xz.lzma;

final class Optimum {
  private static final int INFINITY_PRICE = 1073741824;
  
  final State state = new State();
  
  final int[] reps = new int[4];
  
  int price;
  
  int optPrev;
  
  int backPrev;
  
  boolean prev1IsLiteral;
  
  boolean hasPrev2;
  
  int optPrev2;
  
  int backPrev2;
  
  void reset() {
    this.price = 1073741824;
  }
  
  void set1(int paramInt1, int paramInt2, int paramInt3) {
    this.price = paramInt1;
    this.optPrev = paramInt2;
    this.backPrev = paramInt3;
    this.prev1IsLiteral = false;
  }
  
  void set2(int paramInt1, int paramInt2, int paramInt3) {
    this.price = paramInt1;
    this.optPrev = paramInt2 + 1;
    this.backPrev = paramInt3;
    this.prev1IsLiteral = true;
    this.hasPrev2 = false;
  }
  
  void set3(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    this.price = paramInt1;
    this.optPrev = paramInt2 + paramInt4 + 1;
    this.backPrev = paramInt5;
    this.prev1IsLiteral = true;
    this.hasPrev2 = true;
    this.optPrev2 = paramInt2;
    this.backPrev2 = paramInt3;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lzma\Optimum.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */