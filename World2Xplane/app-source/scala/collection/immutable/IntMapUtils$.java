/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.BitOperations;
/*    */ 
/*    */ public final class IntMapUtils$ implements BitOperations.Int {
/*    */   public static final IntMapUtils$ MODULE$;
/*    */   
/*    */   public boolean zero(int i, int mask) {
/* 19 */     return BitOperations.Int.class.zero(this, i, mask);
/*    */   }
/*    */   
/*    */   public int mask(int i, int mask) {
/* 19 */     return BitOperations.Int.class.mask(this, i, mask);
/*    */   }
/*    */   
/*    */   public boolean hasMatch(int key, int prefix, int m) {
/* 19 */     return BitOperations.Int.class.hasMatch(this, key, prefix, m);
/*    */   }
/*    */   
/*    */   public boolean unsignedCompare(int i, int j) {
/* 19 */     return BitOperations.Int.class.unsignedCompare(this, i, j);
/*    */   }
/*    */   
/*    */   public boolean shorter(int m1, int m2) {
/* 19 */     return BitOperations.Int.class.shorter(this, m1, m2);
/*    */   }
/*    */   
/*    */   public int complement(int i) {
/* 19 */     return BitOperations.Int.class.complement(this, i);
/*    */   }
/*    */   
/*    */   public IndexedSeq<Object> bits(int num) {
/* 19 */     return BitOperations.Int.class.bits(this, num);
/*    */   }
/*    */   
/*    */   public String bitString(int num, String sep) {
/* 19 */     return BitOperations.Int.class.bitString(this, num, sep);
/*    */   }
/*    */   
/*    */   public int highestOneBit(int j) {
/* 19 */     return BitOperations.Int.class.highestOneBit(this, j);
/*    */   }
/*    */   
/*    */   public String bitString$default$2() {
/* 19 */     return BitOperations.Int.class.bitString$default$2(this);
/*    */   }
/*    */   
/*    */   private IntMapUtils$() {
/* 19 */     MODULE$ = this;
/* 19 */     BitOperations.Int.class.$init$(this);
/*    */   }
/*    */   
/*    */   public int branchMask(int i, int j) {
/* 20 */     return highestOneBit(i ^ j);
/*    */   }
/*    */   
/*    */   public <T> IntMap<T> join(int p1, IntMap<T> t1, int p2, IntMap<T> t2) {
/* 23 */     int m = branchMask(p1, p2);
/* 24 */     int p = mask(p1, m);
/* 25 */     return zero(p1, m) ? new IntMap.Bin<T>(p, m, t1, t2) : 
/* 26 */       new IntMap.Bin<T>(p, m, t2, t1);
/*    */   }
/*    */   
/*    */   public <T> IntMap<T> bin(int prefix, int mask, IntMap left, IntMap right) {
/*    */     // Byte code:
/*    */     //   0: new scala/Tuple2
/*    */     //   3: dup
/*    */     //   4: aload_3
/*    */     //   5: aload #4
/*    */     //   7: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*    */     //   10: astore #5
/*    */     //   12: aload #5
/*    */     //   14: ifnull -> 61
/*    */     //   17: getstatic scala/collection/immutable/IntMap$Nil$.MODULE$ : Lscala/collection/immutable/IntMap$Nil$;
/*    */     //   20: aload #5
/*    */     //   22: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   25: astore #6
/*    */     //   27: dup
/*    */     //   28: ifnonnull -> 40
/*    */     //   31: pop
/*    */     //   32: aload #6
/*    */     //   34: ifnull -> 48
/*    */     //   37: goto -> 61
/*    */     //   40: aload #6
/*    */     //   42: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   45: ifeq -> 61
/*    */     //   48: aload #5
/*    */     //   50: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   53: checkcast scala/collection/immutable/IntMap
/*    */     //   56: astore #7
/*    */     //   58: goto -> 142
/*    */     //   61: aload #5
/*    */     //   63: ifnull -> 110
/*    */     //   66: getstatic scala/collection/immutable/IntMap$Nil$.MODULE$ : Lscala/collection/immutable/IntMap$Nil$;
/*    */     //   69: aload #5
/*    */     //   71: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   74: astore #8
/*    */     //   76: dup
/*    */     //   77: ifnonnull -> 89
/*    */     //   80: pop
/*    */     //   81: aload #8
/*    */     //   83: ifnull -> 97
/*    */     //   86: goto -> 110
/*    */     //   89: aload #8
/*    */     //   91: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   94: ifeq -> 110
/*    */     //   97: aload #5
/*    */     //   99: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   102: checkcast scala/collection/immutable/IntMap
/*    */     //   105: astore #7
/*    */     //   107: goto -> 142
/*    */     //   110: aload #5
/*    */     //   112: ifnull -> 145
/*    */     //   115: new scala/collection/immutable/IntMap$Bin
/*    */     //   118: dup
/*    */     //   119: iload_1
/*    */     //   120: iload_2
/*    */     //   121: aload #5
/*    */     //   123: invokevirtual _1 : ()Ljava/lang/Object;
/*    */     //   126: checkcast scala/collection/immutable/IntMap
/*    */     //   129: aload #5
/*    */     //   131: invokevirtual _2 : ()Ljava/lang/Object;
/*    */     //   134: checkcast scala/collection/immutable/IntMap
/*    */     //   137: invokespecial <init> : (IILscala/collection/immutable/IntMap;Lscala/collection/immutable/IntMap;)V
/*    */     //   140: astore #7
/*    */     //   142: aload #7
/*    */     //   144: areturn
/*    */     //   145: new scala/MatchError
/*    */     //   148: dup
/*    */     //   149: aload #5
/*    */     //   151: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   154: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     //   #30	-> 17
/*    */     //   #29	-> 20
/*    */     //   #30	-> 22
/*    */     //   #29	-> 48
/*    */     //   #30	-> 50
/*    */     //   #29	-> 61
/*    */     //   #31	-> 66
/*    */     //   #29	-> 69
/*    */     //   #31	-> 71
/*    */     //   #29	-> 97
/*    */     //   #31	-> 99
/*    */     //   #29	-> 110
/*    */     //   #32	-> 115
/*    */     //   #29	-> 121
/*    */     //   #32	-> 123
/*    */     //   #29	-> 129
/*    */     //   #32	-> 131
/*    */     //   #29	-> 142
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	155	0	this	Lscala/collection/immutable/IntMapUtils$;
/*    */     //   0	155	1	prefix	I
/*    */     //   0	155	2	mask	I
/*    */     //   0	155	3	left	Lscala/collection/immutable/IntMap;
/*    */     //   0	155	4	right	Lscala/collection/immutable/IntMap;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\IntMapUtils$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */