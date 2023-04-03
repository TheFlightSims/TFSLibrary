/*     */ package scala.math;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.NumericRange;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class BigDecimal$ implements Serializable {
/*     */   public static final BigDecimal$ MODULE$;
/*     */   
/*     */   private final int minCached;
/*     */   
/*     */   private final int maxCached;
/*     */   
/*     */   private final MathContext defaultMathContext;
/*     */   
/*     */   private final BigDecimal MinLong;
/*     */   
/*     */   private final BigDecimal MaxLong;
/*     */   
/*     */   private BigDecimal[] cache;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   private Object readResolve() {
/*  23 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private BigDecimal$() {
/*  23 */     MODULE$ = this;
/*  24 */     this.minCached = -512;
/*  25 */     this.maxCached = 512;
/*  26 */     this.defaultMathContext = MathContext.DECIMAL128;
/*  29 */     this.MinLong = new BigDecimal(BigDecimal.valueOf(Long.MIN_VALUE), defaultMathContext());
/*  32 */     this.MaxLong = new BigDecimal(BigDecimal.valueOf(Long.MAX_VALUE), defaultMathContext());
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
/*     */   public MathContext defaultMathContext() {
/*     */     return this.defaultMathContext;
/*     */   }
/*     */   
/*     */   public BigDecimal MinLong() {
/*     */     return this.MinLong;
/*     */   }
/*     */   
/*     */   public BigDecimal MaxLong() {
/*  32 */     return this.MaxLong;
/*     */   }
/*     */   
/*     */   private BigDecimal[] cache$lzycompute() {
/*  35 */     synchronized (this) {
/*  35 */       if (!this.bitmap$0) {
/*  35 */         this.cache = new BigDecimal[maxCached() - minCached() + 1];
/*  35 */         this.bitmap$0 = true;
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/math/BigDecimal$}} */
/*  35 */       return this.cache;
/*     */     } 
/*     */   }
/*     */   
/*     */   private BigDecimal[] cache() {
/*  35 */     return this.bitmap$0 ? this.cache : cache$lzycompute();
/*     */   }
/*     */   
/*     */   public BigDecimal valueOf(double d) {
/*  50 */     return apply(BigDecimal.valueOf(d));
/*     */   }
/*     */   
/*     */   public BigDecimal valueOf(double d, MathContext mc) {
/*  51 */     return apply(BigDecimal.valueOf(d), mc);
/*     */   }
/*     */   
/*     */   public BigDecimal apply(int i) {
/*  59 */     return apply(i, defaultMathContext());
/*     */   }
/*     */   
/*     */   public BigDecimal apply(int i, MathContext mc) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: aload_0
/*     */     //   2: invokevirtual defaultMathContext : ()Ljava/math/MathContext;
/*     */     //   5: astore_3
/*     */     //   6: dup
/*     */     //   7: ifnonnull -> 18
/*     */     //   10: pop
/*     */     //   11: aload_3
/*     */     //   12: ifnull -> 25
/*     */     //   15: goto -> 92
/*     */     //   18: aload_3
/*     */     //   19: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   22: ifeq -> 92
/*     */     //   25: aload_0
/*     */     //   26: invokespecial minCached : ()I
/*     */     //   29: iload_1
/*     */     //   30: if_icmpgt -> 92
/*     */     //   33: iload_1
/*     */     //   34: aload_0
/*     */     //   35: invokespecial maxCached : ()I
/*     */     //   38: if_icmpgt -> 92
/*     */     //   41: iload_1
/*     */     //   42: aload_0
/*     */     //   43: invokespecial minCached : ()I
/*     */     //   46: isub
/*     */     //   47: istore #4
/*     */     //   49: aload_0
/*     */     //   50: invokespecial cache : ()[Lscala/math/BigDecimal;
/*     */     //   53: iload #4
/*     */     //   55: aaload
/*     */     //   56: astore #5
/*     */     //   58: aload #5
/*     */     //   60: ifnonnull -> 87
/*     */     //   63: new scala/math/BigDecimal
/*     */     //   66: dup
/*     */     //   67: iload_1
/*     */     //   68: i2l
/*     */     //   69: invokestatic valueOf : (J)Ljava/math/BigDecimal;
/*     */     //   72: aload_2
/*     */     //   73: invokespecial <init> : (Ljava/math/BigDecimal;Ljava/math/MathContext;)V
/*     */     //   76: astore #5
/*     */     //   78: aload_0
/*     */     //   79: invokespecial cache : ()[Lscala/math/BigDecimal;
/*     */     //   82: iload #4
/*     */     //   84: aload #5
/*     */     //   86: aastore
/*     */     //   87: aload #5
/*     */     //   89: goto -> 105
/*     */     //   92: new scala/math/BigDecimal
/*     */     //   95: dup
/*     */     //   96: iload_1
/*     */     //   97: i2l
/*     */     //   98: invokestatic valueOf : (J)Ljava/math/BigDecimal;
/*     */     //   101: aload_2
/*     */     //   102: invokespecial <init> : (Ljava/math/BigDecimal;Ljava/math/MathContext;)V
/*     */     //   105: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #61	-> 0
/*     */     //   #62	-> 41
/*     */     //   #63	-> 49
/*     */     //   #64	-> 58
/*     */     //   #65	-> 87
/*     */     //   #67	-> 92
/*     */     //   #61	-> 105
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	106	0	this	Lscala/math/BigDecimal$;
/*     */     //   0	106	1	i	I
/*     */     //   0	106	2	mc	Ljava/math/MathContext;
/*     */     //   49	40	4	offset	I
/*     */     //   58	31	5	n	Lscala/math/BigDecimal;
/*     */   }
/*     */   
/*     */   public BigDecimal apply(long l) {
/*  76 */     return (minCached() <= l && l <= maxCached()) ? apply((int)l) : 
/*  77 */       new BigDecimal(BigDecimal.valueOf(l), defaultMathContext());
/*     */   }
/*     */   
/*     */   public BigDecimal apply(long l, MathContext mc) {
/*  80 */     return new BigDecimal(new BigDecimal(l, mc), mc);
/*     */   }
/*     */   
/*     */   public BigDecimal apply(long unscaledVal, int scale) {
/*  90 */     return apply(BigInt$.MODULE$.apply(unscaledVal), scale);
/*     */   }
/*     */   
/*     */   public BigDecimal apply(long unscaledVal, int scale, MathContext mc) {
/*  93 */     return apply(BigInt$.MODULE$.apply(unscaledVal), scale, mc);
/*     */   }
/*     */   
/*     */   public BigDecimal apply(double d) {
/* 101 */     return apply(d, defaultMathContext());
/*     */   }
/*     */   
/*     */   public BigDecimal apply(double d, MathContext mc) {
/* 105 */     return new BigDecimal(new BigDecimal(Double.toString(d), mc), mc);
/*     */   }
/*     */   
/*     */   public BigDecimal apply(char[] x) {
/* 110 */     return apply(x, defaultMathContext());
/*     */   }
/*     */   
/*     */   public BigDecimal apply(char[] x, MathContext mc) {
/* 112 */     return new BigDecimal(new BigDecimal(scala.Predef$.MODULE$.charArrayOps(x).mkString(), mc), mc);
/*     */   }
/*     */   
/*     */   public BigDecimal apply(String x) {
/* 117 */     return apply(x, defaultMathContext());
/*     */   }
/*     */   
/*     */   public BigDecimal apply(String x, MathContext mc) {
/* 119 */     return new BigDecimal(new BigDecimal(x, mc), mc);
/*     */   }
/*     */   
/*     */   public BigDecimal apply(BigInt x) {
/* 127 */     return apply(x, defaultMathContext());
/*     */   }
/*     */   
/*     */   public BigDecimal apply(BigInt x, MathContext mc) {
/* 129 */     return new BigDecimal(new BigDecimal(x.bigInteger(), mc), mc);
/*     */   }
/*     */   
/*     */   public BigDecimal apply(BigInt unscaledVal, int scale) {
/* 138 */     return apply(unscaledVal, scale, defaultMathContext());
/*     */   }
/*     */   
/*     */   public BigDecimal apply(BigInt unscaledVal, int scale, MathContext mc) {
/* 140 */     return new BigDecimal(new BigDecimal(unscaledVal.bigInteger(), scale, mc), mc);
/*     */   }
/*     */   
/*     */   public BigDecimal apply(BigDecimal bd) {
/* 142 */     return apply(bd, defaultMathContext());
/*     */   }
/*     */   
/*     */   public BigDecimal apply(BigDecimal bd, MathContext mc) {
/* 143 */     return new BigDecimal(bd, mc);
/*     */   }
/*     */   
/*     */   public BigDecimal int2bigDecimal(int i) {
/* 146 */     return apply(i);
/*     */   }
/*     */   
/*     */   public BigDecimal long2bigDecimal(long l) {
/* 149 */     return apply(l);
/*     */   }
/*     */   
/*     */   public BigDecimal double2bigDecimal(double d) {
/* 152 */     return valueOf(d, defaultMathContext());
/*     */   }
/*     */   
/*     */   public BigDecimal javaBigDecimal2bigDecimal(BigDecimal x) {
/* 155 */     return apply(x);
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$isValidByte$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 192 */       this.$outer.toByteExact();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 192 */       this.$outer.toByteExact();
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$isValidByte$1(BigDecimal $outer) {}
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$isValidShort$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 193 */       this.$outer.toShortExact();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 193 */       this.$outer.toShortExact();
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$isValidShort$1(BigDecimal $outer) {}
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$isValidInt$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 195 */       this.$outer.toIntExact();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 195 */       this.$outer.toIntExact();
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$isValidInt$1(BigDecimal $outer) {}
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$isValidLong$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 196 */       this.$outer.toLongExact();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 196 */       this.$outer.toLongExact();
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$isValidLong$1(BigDecimal $outer) {}
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$until$1 extends AbstractFunction1<BigDecimal, NumericRange.Exclusive<BigDecimal>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BigDecimal end$1;
/*     */     
/*     */     public final NumericRange.Exclusive<BigDecimal> apply(BigDecimal x$2) {
/* 427 */       return this.$outer.until(this.end$1, x$2);
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$until$1(BigDecimal $outer, BigDecimal end$1) {}
/*     */   }
/*     */   
/*     */   public class BigDecimal$$anonfun$to$1 extends AbstractFunction1<BigDecimal, NumericRange.Inclusive<BigDecimal>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BigDecimal end$2;
/*     */     
/*     */     public final NumericRange.Inclusive<BigDecimal> apply(BigDecimal x$3) {
/* 434 */       return this.$outer.to(this.end$2, x$3);
/*     */     }
/*     */     
/*     */     public BigDecimal$$anonfun$to$1(BigDecimal $outer, BigDecimal end$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\BigDecimal$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */