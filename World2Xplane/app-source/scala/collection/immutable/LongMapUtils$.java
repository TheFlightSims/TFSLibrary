/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.BitOperations;
/*    */ 
/*    */ public final class LongMapUtils$ implements BitOperations.Long {
/*    */   public static final LongMapUtils$ MODULE$;
/*    */   
/*    */   public boolean zero(long i, long mask) {
/* 19 */     return BitOperations.Long.class.zero(this, i, mask);
/*    */   }
/*    */   
/*    */   public long mask(long i, long mask) {
/* 19 */     return BitOperations.Long.class.mask(this, i, mask);
/*    */   }
/*    */   
/*    */   public boolean hasMatch(long key, long prefix, long m) {
/* 19 */     return BitOperations.Long.class.hasMatch(this, key, prefix, m);
/*    */   }
/*    */   
/*    */   public boolean unsignedCompare(long i, long j) {
/* 19 */     return BitOperations.Long.class.unsignedCompare(this, i, j);
/*    */   }
/*    */   
/*    */   public boolean shorter(long m1, long m2) {
/* 19 */     return BitOperations.Long.class.shorter(this, m1, m2);
/*    */   }
/*    */   
/*    */   public long complement(long i) {
/* 19 */     return BitOperations.Long.class.complement(this, i);
/*    */   }
/*    */   
/*    */   public IndexedSeq<Object> bits(long num) {
/* 19 */     return BitOperations.Long.class.bits(this, num);
/*    */   }
/*    */   
/*    */   public String bitString(long num, String sep) {
/* 19 */     return BitOperations.Long.class.bitString(this, num, sep);
/*    */   }
/*    */   
/*    */   public long highestOneBit(long j) {
/* 19 */     return BitOperations.Long.class.highestOneBit(this, j);
/*    */   }
/*    */   
/*    */   public String bitString$default$2() {
/* 19 */     return BitOperations.Long.class.bitString$default$2(this);
/*    */   }
/*    */   
/*    */   private LongMapUtils$() {
/* 19 */     MODULE$ = this;
/* 19 */     BitOperations.Long.class.$init$(this);
/*    */   }
/*    */   
/*    */   public long branchMask(long i, long j) {
/* 20 */     return highestOneBit(i ^ j);
/*    */   }
/*    */   
/*    */   public <T> LongMap<T> join(long p1, LongMap<T> t1, long p2, LongMap<T> t2) {
/* 23 */     long m = branchMask(p1, p2);
/* 24 */     long p = mask(p1, m);
/* 25 */     return zero(p1, m) ? new LongMap.Bin<T>(p, m, t1, t2) : 
/* 26 */       new LongMap.Bin<T>(p, m, t2, t1);
/*    */   }
/*    */   
/*    */   public <T> LongMap<T> bin(long prefix, long mask, LongMap left, LongMap right) {
/*    */     // Byte code:
/*    */     //   0: new scala/Tuple2
/*    */     //   3: dup
/*    */     //   4: aload #5
/*    */     //   6: aload #6
/*    */     //   8: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*    */     //   11: astore #7
/*    */     //   13: aload #7
/*    */     //   15: ifnull -> 62
/*    */     //   18: getstatic scala/collection/immutable/LongMap$Nil$.MODULE$ : Lscala/collection/immutable/LongMap$Nil$;
/*    */     //   21: aload #7
/*    */     //   23: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   26: astore #8
/*    */     //   28: dup
/*    */     //   29: ifnonnull -> 41
/*    */     //   32: pop
/*    */     //   33: aload #8
/*    */     //   35: ifnull -> 49
/*    */     //   38: goto -> 62
/*    */     //   41: aload #8
/*    */     //   43: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   46: ifeq -> 62
/*    */     //   49: aload #7
/*    */     //   51: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   54: checkcast scala/collection/immutable/LongMap
/*    */     //   57: astore #9
/*    */     //   59: goto -> 143
/*    */     //   62: aload #7
/*    */     //   64: ifnull -> 111
/*    */     //   67: getstatic scala/collection/immutable/LongMap$Nil$.MODULE$ : Lscala/collection/immutable/LongMap$Nil$;
/*    */     //   70: aload #7
/*    */     //   72: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   75: astore #10
/*    */     //   77: dup
/*    */     //   78: ifnonnull -> 90
/*    */     //   81: pop
/*    */     //   82: aload #10
/*    */     //   84: ifnull -> 98
/*    */     //   87: goto -> 111
/*    */     //   90: aload #10
/*    */     //   92: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   95: ifeq -> 111
/*    */     //   98: aload #7
/*    */     //   100: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   103: checkcast scala/collection/immutable/LongMap
/*    */     //   106: astore #9
/*    */     //   108: goto -> 143
/*    */     //   111: aload #7
/*    */     //   113: ifnull -> 146
/*    */     //   116: new scala/collection/immutable/LongMap$Bin
/*    */     //   119: dup
/*    */     //   120: lload_1
/*    */     //   121: lload_3
/*    */     //   122: aload #7
/*    */     //   124: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   127: checkcast scala/collection/immutable/LongMap
/*    */     //   130: aload #7
/*    */     //   132: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   135: checkcast scala/collection/immutable/LongMap
/*    */     //   138: invokespecial <init> : (JJLscala/collection/immutable/LongMap;Lscala/collection/immutable/LongMap;)V
/*    */     //   141: astore #9
/*    */     //   143: aload #9
/*    */     //   145: areturn
/*    */     //   146: new scala/MatchError
/*    */     //   149: dup
/*    */     //   150: aload #7
/*    */     //   152: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   155: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     //   #30	-> 18
/*    */     //   #29	-> 21
/*    */     //   #30	-> 23
/*    */     //   #29	-> 49
/*    */     //   #30	-> 51
/*    */     //   #29	-> 62
/*    */     //   #31	-> 67
/*    */     //   #29	-> 70
/*    */     //   #31	-> 72
/*    */     //   #29	-> 98
/*    */     //   #31	-> 100
/*    */     //   #29	-> 111
/*    */     //   #32	-> 116
/*    */     //   #29	-> 122
/*    */     //   #32	-> 124
/*    */     //   #29	-> 130
/*    */     //   #32	-> 132
/*    */     //   #29	-> 143
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	156	0	this	Lscala/collection/immutable/LongMapUtils$;
/*    */     //   0	156	1	prefix	J
/*    */     //   0	156	3	mask	J
/*    */     //   0	156	5	left	Lscala/collection/immutable/LongMap;
/*    */     //   0	156	6	right	Lscala/collection/immutable/LongMap;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\LongMapUtils$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */