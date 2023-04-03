/*     */ package org.apache.commons.math3.dfp;
/*     */ 
/*     */ public class DfpDec extends Dfp {
/*     */   protected DfpDec(DfpField factory) {
/*  33 */     super(factory);
/*     */   }
/*     */   
/*     */   protected DfpDec(DfpField factory, byte x) {
/*  41 */     super(factory, x);
/*     */   }
/*     */   
/*     */   protected DfpDec(DfpField factory, int x) {
/*  49 */     super(factory, x);
/*     */   }
/*     */   
/*     */   protected DfpDec(DfpField factory, long x) {
/*  57 */     super(factory, x);
/*     */   }
/*     */   
/*     */   protected DfpDec(DfpField factory, double x) {
/*  65 */     super(factory, x);
/*  66 */     round(0);
/*     */   }
/*     */   
/*     */   public DfpDec(Dfp d) {
/*  73 */     super(d);
/*  74 */     round(0);
/*     */   }
/*     */   
/*     */   protected DfpDec(DfpField factory, String s) {
/*  82 */     super(factory, s);
/*  83 */     round(0);
/*     */   }
/*     */   
/*     */   protected DfpDec(DfpField factory, byte sign, byte nans) {
/*  93 */     super(factory, sign, nans);
/*     */   }
/*     */   
/*     */   public Dfp newInstance() {
/*  99 */     return new DfpDec(getField());
/*     */   }
/*     */   
/*     */   public Dfp newInstance(byte x) {
/* 105 */     return new DfpDec(getField(), x);
/*     */   }
/*     */   
/*     */   public Dfp newInstance(int x) {
/* 111 */     return new DfpDec(getField(), x);
/*     */   }
/*     */   
/*     */   public Dfp newInstance(long x) {
/* 117 */     return new DfpDec(getField(), x);
/*     */   }
/*     */   
/*     */   public Dfp newInstance(double x) {
/* 123 */     return new DfpDec(getField(), x);
/*     */   }
/*     */   
/*     */   public Dfp newInstance(Dfp d) {
/* 131 */     if (getField().getRadixDigits() != d.getField().getRadixDigits()) {
/* 132 */       getField().setIEEEFlagsBits(1);
/* 133 */       Dfp result = newInstance(getZero());
/* 134 */       result.nans = 3;
/* 135 */       return dotrap(1, "newInstance", d, result);
/*     */     } 
/* 138 */     return new DfpDec(d);
/*     */   }
/*     */   
/*     */   public Dfp newInstance(String s) {
/* 145 */     return new DfpDec(getField(), s);
/*     */   }
/*     */   
/*     */   public Dfp newInstance(byte sign, byte nans) {
/* 151 */     return new DfpDec(getField(), sign, nans);
/*     */   }
/*     */   
/*     */   protected int getDecimalDigits() {
/* 160 */     return getRadixDigits() * 4 - 3;
/*     */   }
/*     */   
/*     */   protected int round(int in) {
/*     */     int n;
/*     */     boolean inc;
/* 167 */     int msb = this.mant[this.mant.length - 1];
/* 168 */     if (msb == 0)
/* 170 */       return 0; 
/* 173 */     int cmaxdigits = this.mant.length * 4;
/* 174 */     int lsbthreshold = 1000;
/* 175 */     while (lsbthreshold > msb) {
/* 176 */       lsbthreshold /= 10;
/* 177 */       cmaxdigits--;
/*     */     } 
/* 181 */     int digits = getDecimalDigits();
/* 182 */     int lsbshift = cmaxdigits - digits;
/* 183 */     int lsd = lsbshift / 4;
/* 185 */     lsbthreshold = 1;
/* 186 */     for (int i = 0; i < lsbshift % 4; i++)
/* 187 */       lsbthreshold *= 10; 
/* 190 */     int lsb = this.mant[lsd];
/* 192 */     if (lsbthreshold <= 1 && digits == 4 * this.mant.length - 3)
/* 193 */       return super.round(in); 
/* 196 */     int discarded = in;
/* 198 */     if (lsbthreshold == 1) {
/* 200 */       n = this.mant[lsd - 1] / 1000 % 10;
/* 201 */       this.mant[lsd - 1] = this.mant[lsd - 1] % 1000;
/* 202 */       discarded |= this.mant[lsd - 1];
/*     */     } else {
/* 204 */       n = lsb * 10 / lsbthreshold % 10;
/* 205 */       discarded |= lsb % lsbthreshold / 10;
/*     */     } 
/* 208 */     for (int j = 0; j < lsd; j++) {
/* 209 */       discarded |= this.mant[j];
/* 210 */       this.mant[j] = 0;
/*     */     } 
/* 213 */     this.mant[lsd] = lsb / lsbthreshold * lsbthreshold;
/* 216 */     switch (getField().getRoundingMode()) {
/*     */       case ROUND_DOWN:
/* 218 */         inc = false;
/*     */         break;
/*     */       case ROUND_UP:
/* 222 */         inc = (n != 0 || discarded != 0);
/*     */         break;
/*     */       case ROUND_HALF_UP:
/* 226 */         inc = (n >= 5);
/*     */         break;
/*     */       case ROUND_HALF_DOWN:
/* 230 */         inc = (n > 5);
/*     */         break;
/*     */       case ROUND_HALF_EVEN:
/* 234 */         inc = (n > 5 || (n == 5 && discarded != 0) || (n == 5 && discarded == 0 && (lsb / lsbthreshold & 0x1) == 1));
/*     */         break;
/*     */       case ROUND_HALF_ODD:
/* 240 */         inc = (n > 5 || (n == 5 && discarded != 0) || (n == 5 && discarded == 0 && (lsb / lsbthreshold & 0x1) == 0));
/*     */         break;
/*     */       case ROUND_CEIL:
/* 246 */         inc = (this.sign == 1 && (n != 0 || discarded != 0));
/*     */         break;
/*     */       default:
/* 251 */         inc = (this.sign == -1 && (n != 0 || discarded != 0));
/*     */         break;
/*     */     } 
/* 255 */     if (inc) {
/* 257 */       int rh = lsbthreshold;
/* 258 */       for (int k = lsd; k < this.mant.length; k++) {
/* 259 */         int r = this.mant[k] + rh;
/* 260 */         rh = r / 10000;
/* 261 */         this.mant[k] = r % 10000;
/*     */       } 
/* 264 */       if (rh != 0) {
/* 265 */         shiftRight();
/* 266 */         this.mant[this.mant.length - 1] = rh;
/*     */       } 
/*     */     } 
/* 271 */     if (this.exp < -32767) {
/* 273 */       getField().setIEEEFlagsBits(8);
/* 274 */       return 8;
/*     */     } 
/* 277 */     if (this.exp > 32768) {
/* 279 */       getField().setIEEEFlagsBits(4);
/* 280 */       return 4;
/*     */     } 
/* 283 */     if (n != 0 || discarded != 0) {
/* 285 */       getField().setIEEEFlagsBits(16);
/* 286 */       return 16;
/*     */     } 
/* 288 */     return 0;
/*     */   }
/*     */   
/*     */   public Dfp nextAfter(Dfp x) {
/*     */     Dfp result;
/* 295 */     String trapName = "nextAfter";
/* 298 */     if (getField().getRadixDigits() != x.getField().getRadixDigits()) {
/* 299 */       getField().setIEEEFlagsBits(1);
/* 300 */       Dfp dfp = newInstance(getZero());
/* 301 */       dfp.nans = 3;
/* 302 */       return dotrap(1, "nextAfter", x, dfp);
/*     */     } 
/* 305 */     boolean up = false;
/* 310 */     if (lessThan(x))
/* 311 */       up = true; 
/* 314 */     if (equals(x))
/* 315 */       return newInstance(x); 
/* 318 */     if (lessThan(getZero()))
/* 319 */       up = !up; 
/* 322 */     if (up) {
/* 323 */       Dfp inc = power10(log10() - getDecimalDigits() + 1);
/* 324 */       inc = copysign(inc, this);
/* 326 */       if (equals(getZero()))
/* 327 */         inc = power10K(-32767 - this.mant.length - 1); 
/* 330 */       if (inc.equals(getZero())) {
/* 331 */         result = copysign(newInstance(getZero()), this);
/*     */       } else {
/* 333 */         result = add(inc);
/*     */       } 
/*     */     } else {
/* 336 */       Dfp inc = power10(log10());
/* 337 */       inc = copysign(inc, this);
/* 339 */       if (equals(inc)) {
/* 340 */         inc = inc.divide(power10(getDecimalDigits()));
/*     */       } else {
/* 342 */         inc = inc.divide(power10(getDecimalDigits() - 1));
/*     */       } 
/* 345 */       if (equals(getZero()))
/* 346 */         inc = power10K(-32767 - this.mant.length - 1); 
/* 349 */       if (inc.equals(getZero())) {
/* 350 */         result = copysign(newInstance(getZero()), this);
/*     */       } else {
/* 352 */         result = subtract(inc);
/*     */       } 
/*     */     } 
/* 356 */     if (result.classify() == 1 && classify() != 1) {
/* 357 */       getField().setIEEEFlagsBits(16);
/* 358 */       result = dotrap(16, "nextAfter", x, result);
/*     */     } 
/* 361 */     if (result.equals(getZero()) && !equals(getZero())) {
/* 362 */       getField().setIEEEFlagsBits(16);
/* 363 */       result = dotrap(16, "nextAfter", x, result);
/*     */     } 
/* 366 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\dfp\DfpDec.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */