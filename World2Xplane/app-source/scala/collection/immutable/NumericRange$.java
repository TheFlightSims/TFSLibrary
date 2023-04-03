/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.math.Integral;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class NumericRange$ implements Serializable {
/*     */   public static final NumericRange$ MODULE$;
/*     */   
/*     */   private final Map<Numeric<?>, Ordering<?>> defaultOrdering;
/*     */   
/*     */   public class NumericRange$$anon$1 extends NumericRange<A> {
/*     */     private NumericRange<T> underlyingRange;
/*     */     
/*     */     public final Function1 fm$1;
/*     */     
/*     */     private final Integral unum$1;
/*     */     
/*     */     private final NumericRange self$1;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public NumericRange$$anon$1(NumericRange $outer, Function1 fm$1, Integral<A> unum$1, NumericRange self$1) {
/* 168 */       super((A)fm$1.apply($outer.start()), (A)fm$1.apply($outer.end()), (A)fm$1.apply($outer.step()), $outer.isInclusive(), unum$1);
/*     */     }
/*     */     
/*     */     public NumericRange<A> copy(Object start, Object end, Object step) {
/* 170 */       return isInclusive() ? NumericRange$.MODULE$.<A>inclusive((A)start, (A)end, (A)step, this.unum$1) : 
/* 171 */         NumericRange$.MODULE$.<A>apply((A)start, (A)end, (A)step, this.unum$1);
/*     */     }
/*     */     
/*     */     private NumericRange underlyingRange$lzycompute() {
/* 173 */       synchronized (this) {
/* 173 */         if (!this.bitmap$0) {
/* 173 */           this.underlyingRange = this.self$1;
/* 173 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/NumericRange$$anon$1}} */
/* 173 */         this.self$1 = null;
/* 173 */         return this.underlyingRange;
/*     */       } 
/*     */     }
/*     */     
/*     */     private NumericRange<T> underlyingRange() {
/* 173 */       return this.bitmap$0 ? this.underlyingRange : underlyingRange$lzycompute();
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 174 */       underlyingRange().foreach((Function1<T, ?>)new NumericRange$$anon$1$$anonfun$foreach$1(this, ($anon$1)f));
/*     */     }
/*     */     
/*     */     public class NumericRange$$anon$1$$anonfun$foreach$1 extends AbstractFunction1<T, U> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$1;
/*     */       
/*     */       public final U apply(Object x) {
/* 174 */         return (U)this.f$1.apply(this.$outer.fm$1.apply(x));
/*     */       }
/*     */       
/*     */       public NumericRange$$anon$1$$anonfun$foreach$1(NumericRange$$anon$1 $outer, Function1 f$1) {}
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 175 */       return underlyingRange().isEmpty();
/*     */     }
/*     */     
/*     */     public A apply(int idx) {
/* 176 */       return (A)this.fm$1.apply(underlyingRange().apply(idx));
/*     */     }
/*     */     
/*     */     public boolean containsTyped(Object el) {
/* 177 */       return underlyingRange().exists((Function1)new NumericRange$$anon$1$$anonfun$containsTyped$1(this, ($anon$1)el));
/*     */     }
/*     */     
/*     */     public class NumericRange$$anon$1$$anonfun$containsTyped$1 extends AbstractFunction1<T, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object el$1;
/*     */       
/*     */       public final boolean apply(Object x) {
/* 177 */         Object object2 = this.el$1;
/*     */         Object object1;
/* 177 */         return (((object1 = this.$outer.fm$1.apply(x)) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))));
/*     */       }
/*     */       
/*     */       public NumericRange$$anon$1$$anonfun$containsTyped$1(NumericRange$$anon$1 $outer, Object el$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 215 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private NumericRange$() {
/* 215 */     MODULE$ = this;
/* 277 */     scala.math.Numeric$BigIntIsIntegral$ numeric$BigIntIsIntegral$ = scala.math.Numeric$BigIntIsIntegral$.MODULE$;
/* 277 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/* 277 */     scala.math.Ordering$BigInt$ ordering$BigInt$ = scala.math.Ordering$BigInt$.MODULE$;
/* 277 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$1 = scala.Predef$ArrowAssoc$.MODULE$;
/* 277 */     (new Tuple2[9])[0] = new Tuple2(numeric$BigIntIsIntegral$, ordering$BigInt$);
/* 278 */     scala.math.Numeric$IntIsIntegral$ numeric$IntIsIntegral$ = scala.math.Numeric$IntIsIntegral$.MODULE$;
/* 278 */     scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/* 278 */     scala.math.Ordering$Int$ ordering$Int$ = scala.math.Ordering$Int$.MODULE$;
/* 278 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$2 = scala.Predef$ArrowAssoc$.MODULE$;
/* 278 */     (new Tuple2[9])[1] = new Tuple2(numeric$IntIsIntegral$, ordering$Int$);
/* 279 */     scala.math.Numeric$ShortIsIntegral$ numeric$ShortIsIntegral$ = scala.math.Numeric$ShortIsIntegral$.MODULE$;
/* 279 */     scala.Predef$ predef$3 = scala.Predef$.MODULE$;
/* 279 */     scala.math.Ordering$Short$ ordering$Short$ = scala.math.Ordering$Short$.MODULE$;
/* 279 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$3 = scala.Predef$ArrowAssoc$.MODULE$;
/* 279 */     (new Tuple2[9])[2] = new Tuple2(numeric$ShortIsIntegral$, ordering$Short$);
/* 280 */     scala.math.Numeric$ByteIsIntegral$ numeric$ByteIsIntegral$ = scala.math.Numeric$ByteIsIntegral$.MODULE$;
/* 280 */     scala.Predef$ predef$4 = scala.Predef$.MODULE$;
/* 280 */     scala.math.Ordering$Byte$ ordering$Byte$ = scala.math.Ordering$Byte$.MODULE$;
/* 280 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$4 = scala.Predef$ArrowAssoc$.MODULE$;
/* 280 */     (new Tuple2[9])[3] = new Tuple2(numeric$ByteIsIntegral$, ordering$Byte$);
/* 281 */     scala.math.Numeric$CharIsIntegral$ numeric$CharIsIntegral$ = scala.math.Numeric$CharIsIntegral$.MODULE$;
/* 281 */     scala.Predef$ predef$5 = scala.Predef$.MODULE$;
/* 281 */     scala.math.Ordering$Char$ ordering$Char$ = scala.math.Ordering$Char$.MODULE$;
/* 281 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$5 = scala.Predef$ArrowAssoc$.MODULE$;
/* 281 */     (new Tuple2[9])[4] = new Tuple2(numeric$CharIsIntegral$, ordering$Char$);
/* 282 */     scala.math.Numeric$LongIsIntegral$ numeric$LongIsIntegral$ = scala.math.Numeric$LongIsIntegral$.MODULE$;
/* 282 */     scala.Predef$ predef$6 = scala.Predef$.MODULE$;
/* 282 */     scala.math.Ordering$Long$ ordering$Long$ = scala.math.Ordering$Long$.MODULE$;
/* 282 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$6 = scala.Predef$ArrowAssoc$.MODULE$;
/* 282 */     (new Tuple2[9])[5] = new Tuple2(numeric$LongIsIntegral$, ordering$Long$);
/* 283 */     scala.math.Numeric$FloatAsIfIntegral$ numeric$FloatAsIfIntegral$ = scala.math.Numeric$FloatAsIfIntegral$.MODULE$;
/* 283 */     scala.Predef$ predef$7 = scala.Predef$.MODULE$;
/* 283 */     scala.math.Ordering$Float$ ordering$Float$ = scala.math.Ordering$Float$.MODULE$;
/* 283 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$7 = scala.Predef$ArrowAssoc$.MODULE$;
/* 283 */     (new Tuple2[9])[6] = new Tuple2(numeric$FloatAsIfIntegral$, ordering$Float$);
/* 284 */     scala.math.Numeric$DoubleAsIfIntegral$ numeric$DoubleAsIfIntegral$ = scala.math.Numeric$DoubleAsIfIntegral$.MODULE$;
/* 284 */     scala.Predef$ predef$8 = scala.Predef$.MODULE$;
/* 284 */     (new Tuple2[9])[7] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(numeric$DoubleAsIfIntegral$, scala.math.Ordering$Double$.MODULE$);
/* 285 */     (new Tuple2[9])[8] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(scala.math.Numeric$BigDecimalAsIfIntegral$.MODULE$), scala.math.Ordering$BigDecimal$.MODULE$);
/*     */     this.defaultOrdering = (Map<Numeric<?>, Ordering<?>>)Map$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[9]));
/*     */   }
/*     */   
/*     */   public <T> int count(Object start, Object end, Object step, boolean isInclusive, Integral num) {
/*     */     // Byte code:
/*     */     //   0: aload #5
/*     */     //   2: invokeinterface zero : ()Ljava/lang/Object;
/*     */     //   7: astore #10
/*     */     //   9: aload #5
/*     */     //   11: aload_1
/*     */     //   12: aload_2
/*     */     //   13: invokeinterface lt : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   18: istore #12
/*     */     //   20: aload #5
/*     */     //   22: aload_3
/*     */     //   23: aload #10
/*     */     //   25: invokeinterface gt : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   30: istore #6
/*     */     //   32: aload_3
/*     */     //   33: aload #10
/*     */     //   35: if_acmpne -> 42
/*     */     //   38: iconst_1
/*     */     //   39: goto -> 94
/*     */     //   42: aload_3
/*     */     //   43: ifnonnull -> 50
/*     */     //   46: iconst_0
/*     */     //   47: goto -> 94
/*     */     //   50: aload_3
/*     */     //   51: instanceof java/lang/Number
/*     */     //   54: ifeq -> 69
/*     */     //   57: aload_3
/*     */     //   58: checkcast java/lang/Number
/*     */     //   61: aload #10
/*     */     //   63: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   66: goto -> 94
/*     */     //   69: aload_3
/*     */     //   70: instanceof java/lang/Character
/*     */     //   73: ifeq -> 88
/*     */     //   76: aload_3
/*     */     //   77: checkcast java/lang/Character
/*     */     //   80: aload #10
/*     */     //   82: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   85: goto -> 94
/*     */     //   88: aload_3
/*     */     //   89: aload #10
/*     */     //   91: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   94: ifeq -> 107
/*     */     //   97: new java/lang/IllegalArgumentException
/*     */     //   100: dup
/*     */     //   101: ldc 'step cannot be 0.'
/*     */     //   103: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   106: athrow
/*     */     //   107: aload_1
/*     */     //   108: aload_2
/*     */     //   109: if_acmpne -> 116
/*     */     //   112: iconst_1
/*     */     //   113: goto -> 165
/*     */     //   116: aload_1
/*     */     //   117: ifnonnull -> 124
/*     */     //   120: iconst_0
/*     */     //   121: goto -> 165
/*     */     //   124: aload_1
/*     */     //   125: instanceof java/lang/Number
/*     */     //   128: ifeq -> 142
/*     */     //   131: aload_1
/*     */     //   132: checkcast java/lang/Number
/*     */     //   135: aload_2
/*     */     //   136: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   139: goto -> 165
/*     */     //   142: aload_1
/*     */     //   143: instanceof java/lang/Character
/*     */     //   146: ifeq -> 160
/*     */     //   149: aload_1
/*     */     //   150: checkcast java/lang/Character
/*     */     //   153: aload_2
/*     */     //   154: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   157: goto -> 165
/*     */     //   160: aload_1
/*     */     //   161: aload_2
/*     */     //   162: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   165: ifeq -> 181
/*     */     //   168: iload #4
/*     */     //   170: ifeq -> 177
/*     */     //   173: iconst_1
/*     */     //   174: goto -> 469
/*     */     //   177: iconst_0
/*     */     //   178: goto -> 469
/*     */     //   181: iload #12
/*     */     //   183: iload #6
/*     */     //   185: if_icmpeq -> 192
/*     */     //   188: iconst_0
/*     */     //   189: goto -> 469
/*     */     //   192: aload #5
/*     */     //   194: aload_2
/*     */     //   195: aload_1
/*     */     //   196: invokeinterface minus : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   201: astore #7
/*     */     //   203: aload #5
/*     */     //   205: aload #5
/*     */     //   207: aload #7
/*     */     //   209: aload_3
/*     */     //   210: invokeinterface quot : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   215: invokeinterface toLong : (Ljava/lang/Object;)J
/*     */     //   220: lstore #8
/*     */     //   222: aload #5
/*     */     //   224: aload #7
/*     */     //   226: aload_3
/*     */     //   227: invokeinterface rem : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   232: astore #11
/*     */     //   234: lload #8
/*     */     //   236: iload #4
/*     */     //   238: ifne -> 317
/*     */     //   241: aload #10
/*     */     //   243: aload #11
/*     */     //   245: if_acmpne -> 252
/*     */     //   248: iconst_1
/*     */     //   249: goto -> 310
/*     */     //   252: aload #10
/*     */     //   254: ifnonnull -> 261
/*     */     //   257: iconst_0
/*     */     //   258: goto -> 310
/*     */     //   261: aload #10
/*     */     //   263: instanceof java/lang/Number
/*     */     //   266: ifeq -> 282
/*     */     //   269: aload #10
/*     */     //   271: checkcast java/lang/Number
/*     */     //   274: aload #11
/*     */     //   276: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   279: goto -> 310
/*     */     //   282: aload #10
/*     */     //   284: instanceof java/lang/Character
/*     */     //   287: ifeq -> 303
/*     */     //   290: aload #10
/*     */     //   292: checkcast java/lang/Character
/*     */     //   295: aload #11
/*     */     //   297: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   300: goto -> 310
/*     */     //   303: aload #10
/*     */     //   305: aload #11
/*     */     //   307: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   310: ifeq -> 317
/*     */     //   313: iconst_0
/*     */     //   314: goto -> 318
/*     */     //   317: iconst_1
/*     */     //   318: i2l
/*     */     //   319: ladd
/*     */     //   320: lstore #16
/*     */     //   322: lload #16
/*     */     //   324: lconst_0
/*     */     //   325: lcmp
/*     */     //   326: ifne -> 355
/*     */     //   329: aload #5
/*     */     //   331: aload #5
/*     */     //   333: aload_1
/*     */     //   334: aload_3
/*     */     //   335: invokeinterface plus : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   340: aload_2
/*     */     //   341: invokeinterface lt : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   346: iload #12
/*     */     //   348: if_icmpne -> 355
/*     */     //   351: iconst_1
/*     */     //   352: goto -> 356
/*     */     //   355: iconst_0
/*     */     //   356: istore #13
/*     */     //   358: lload #16
/*     */     //   360: ldc2_w 2147483647
/*     */     //   363: lcmp
/*     */     //   364: ifgt -> 379
/*     */     //   367: lload #16
/*     */     //   369: lconst_0
/*     */     //   370: lcmp
/*     */     //   371: iflt -> 379
/*     */     //   374: iload #13
/*     */     //   376: ifeq -> 466
/*     */     //   379: iload #4
/*     */     //   381: ifeq -> 389
/*     */     //   384: ldc 'to'
/*     */     //   386: goto -> 391
/*     */     //   389: ldc 'until'
/*     */     //   391: astore #14
/*     */     //   393: getstatic scala/collection/immutable/List$.MODULE$ : Lscala/collection/immutable/List$;
/*     */     //   396: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   399: iconst_5
/*     */     //   400: anewarray java/lang/Object
/*     */     //   403: dup
/*     */     //   404: iconst_0
/*     */     //   405: aload_1
/*     */     //   406: aastore
/*     */     //   407: dup
/*     */     //   408: iconst_1
/*     */     //   409: aload #14
/*     */     //   411: aastore
/*     */     //   412: dup
/*     */     //   413: iconst_2
/*     */     //   414: aload_2
/*     */     //   415: aastore
/*     */     //   416: dup
/*     */     //   417: iconst_3
/*     */     //   418: ldc 'by'
/*     */     //   420: aastore
/*     */     //   421: dup
/*     */     //   422: iconst_4
/*     */     //   423: aload_3
/*     */     //   424: aastore
/*     */     //   425: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   428: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/immutable/List;
/*     */     //   431: ldc ' '
/*     */     //   433: invokevirtual mkString : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   436: astore #15
/*     */     //   438: new java/lang/IllegalArgumentException
/*     */     //   441: dup
/*     */     //   442: new scala/collection/mutable/StringBuilder
/*     */     //   445: dup
/*     */     //   446: invokespecial <init> : ()V
/*     */     //   449: aload #15
/*     */     //   451: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   454: ldc ': seqs cannot contain more than Int.MaxValue elements.'
/*     */     //   456: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   459: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   462: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   465: athrow
/*     */     //   466: lload #16
/*     */     //   468: l2i
/*     */     //   469: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #222	-> 0
/*     */     //   #223	-> 9
/*     */     //   #224	-> 20
/*     */     //   #226	-> 32
/*     */     //   #227	-> 107
/*     */     //   #228	-> 181
/*     */     //   #230	-> 192
/*     */     //   #231	-> 203
/*     */     //   #232	-> 222
/*     */     //   #233	-> 234
/*     */     //   #234	-> 236
/*     */     //   #233	-> 319
/*     */     //   #243	-> 322
/*     */     //   #245	-> 358
/*     */     //   #246	-> 379
/*     */     //   #247	-> 393
/*     */     //   #249	-> 438
/*     */     //   #251	-> 466
/*     */     //   #221	-> 469
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	470	0	this	Lscala/collection/immutable/NumericRange$;
/*     */     //   0	470	1	start	Ljava/lang/Object;
/*     */     //   0	470	2	end	Ljava/lang/Object;
/*     */     //   0	470	3	step	Ljava/lang/Object;
/*     */     //   0	470	4	isInclusive	Z
/*     */     //   0	470	5	num	Lscala/math/Integral;
/*     */     //   9	461	10	zero	Ljava/lang/Object;
/*     */     //   20	450	12	upward	Z
/*     */     //   32	438	6	posStep	Z
/*     */     //   203	266	7	diff	Ljava/lang/Object;
/*     */     //   222	247	8	jumps	J
/*     */     //   234	235	11	remainder	Ljava/lang/Object;
/*     */     //   322	147	16	longCount	J
/*     */     //   358	111	13	isOverflow	Z
/*     */     //   393	77	14	word	Ljava/lang/String;
/*     */     //   438	32	15	descr	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   public <T> NumericRange.Exclusive<T> apply(Object start, Object end, Object step, Integral<T> num) {
/*     */     return new NumericRange.Exclusive<T>((T)start, (T)end, (T)step, num);
/*     */   }
/*     */   
/*     */   public <T> NumericRange.Inclusive<T> inclusive(Object start, Object end, Object step, Integral<T> num) {
/*     */     return new NumericRange.Inclusive<T>((T)start, (T)end, (T)step, num);
/*     */   }
/*     */   
/*     */   public Map<Numeric<?>, Ordering<?>> defaultOrdering() {
/*     */     return this.defaultOrdering;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\NumericRange$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */