/*     */ package scala.math;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import scala.Serializable;
/*     */ import scala.util.Random;
/*     */ 
/*     */ public final class BigInt$ implements Serializable {
/*     */   public static final BigInt$ MODULE$;
/*     */   
/*     */   private final int minCached;
/*     */   
/*     */   private final int maxCached;
/*     */   
/*     */   private final BigInt[] cache;
/*     */   
/*     */   private final BigInteger scala$math$BigInt$$minusOne;
/*     */   
/*     */   private final BigInt MinLong;
/*     */   
/*     */   private final BigInt MaxLong;
/*     */   
/*     */   private Object readResolve() {
/*  19 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private BigInt$() {
/*  19 */     MODULE$ = this;
/*  21 */     this.minCached = -1024;
/*  22 */     this.maxCached = 1024;
/*  23 */     this.cache = new BigInt[maxCached() - minCached() + 1];
/*  24 */     this.scala$math$BigInt$$minusOne = BigInteger.valueOf(-1L);
/*  27 */     this.MinLong = apply(Long.MIN_VALUE);
/*  30 */     this.MaxLong = apply(Long.MAX_VALUE);
/*     */   }
/*     */   
/*     */   private int minCached() {
/*     */     return this.minCached;
/*     */   }
/*     */   
/*     */   private int maxCached() {
/*     */     return this.maxCached;
/*     */   }
/*     */   
/*     */   private BigInt[] cache() {
/*     */     return this.cache;
/*     */   }
/*     */   
/*     */   public BigInteger scala$math$BigInt$$minusOne() {
/*     */     return this.scala$math$BigInt$$minusOne;
/*     */   }
/*     */   
/*     */   public BigInt MinLong() {
/*     */     return this.MinLong;
/*     */   }
/*     */   
/*     */   public BigInt MaxLong() {
/*  30 */     return this.MaxLong;
/*     */   }
/*     */   
/*     */   public BigInt apply(int i) {
/*  40 */     int offset = i - minCached();
/*  41 */     BigInt n = cache()[offset];
/*  42 */     if (n == null) {
/*  42 */       n = new BigInt(BigInteger.valueOf(i));
/*  42 */       cache()[offset] = n;
/*     */     } 
/*  43 */     return (minCached() <= i && i <= maxCached()) ? n : 
/*  44 */       new BigInt(BigInteger.valueOf(i));
/*     */   }
/*     */   
/*     */   public BigInt apply(long l) {
/*  53 */     return (minCached() <= l && l <= maxCached()) ? apply((int)l) : 
/*  54 */       new BigInt(BigInteger.valueOf(l));
/*     */   }
/*     */   
/*     */   public BigInt apply(byte[] x) {
/*  60 */     return new BigInt(new BigInteger(x));
/*     */   }
/*     */   
/*     */   public BigInt apply(int signum, byte[] magnitude) {
/*  65 */     return new BigInt(new BigInteger(signum, magnitude));
/*     */   }
/*     */   
/*     */   public BigInt apply(int bitlength, int certainty, Random rnd) {
/*  71 */     return new BigInt(new BigInteger(bitlength, certainty, rnd.self()));
/*     */   }
/*     */   
/*     */   public BigInt apply(int numbits, Random rnd) {
/*  77 */     return new BigInt(new BigInteger(numbits, rnd.self()));
/*     */   }
/*     */   
/*     */   public BigInt apply(String x) {
/*  82 */     return new BigInt(new BigInteger(x));
/*     */   }
/*     */   
/*     */   public BigInt apply(String x, int radix) {
/*  88 */     return new BigInt(new BigInteger(x, radix));
/*     */   }
/*     */   
/*     */   public BigInt apply(BigInteger x) {
/*  93 */     return new BigInt(x);
/*     */   }
/*     */   
/*     */   public BigInt probablePrime(int bitLength, Random rnd) {
/*  98 */     return new BigInt(BigInteger.probablePrime(bitLength, rnd.self()));
/*     */   }
/*     */   
/*     */   public BigInt int2bigInt(int i) {
/* 102 */     return apply(i);
/*     */   }
/*     */   
/*     */   public BigInt long2bigInt(long l) {
/* 106 */     return apply(l);
/*     */   }
/*     */   
/*     */   public BigInt javaBigInteger2bigInt(BigInteger x) {
/* 110 */     return apply(x);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\BigInt$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */