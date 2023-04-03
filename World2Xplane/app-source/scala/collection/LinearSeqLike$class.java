/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function2;
/*    */ import scala.util.hashing.MurmurHash3$;
/*    */ 
/*    */ public abstract class LinearSeqLike$class {
/*    */   public static void $init$(LinearSeqLike $this) {}
/*    */   
/*    */   public static LinearSeq thisCollection(LinearSeqLike $this) {
/* 48 */     return (LinearSeq)$this;
/*    */   }
/*    */   
/*    */   public static LinearSeq toCollection(LinearSeqLike $this, LinearSeqLike repr) {
/* 49 */     return (LinearSeq)repr;
/*    */   }
/*    */   
/*    */   public static int hashCode(LinearSeqLike $this) {
/* 53 */     return MurmurHash3$.MODULE$.seqHash($this.seq());
/*    */   }
/*    */   
/*    */   public static Iterator iterator(LinearSeqLike $this) {
/* 56 */     return new LinearSeqLike$$anon$1((Repr)$this);
/*    */   }
/*    */   
/*    */   public static final boolean corresponds(LinearSeqLike $this, GenSeq that, Function2 p) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: invokeinterface isEmpty : ()Z
/*    */     //   6: ifeq -> 18
/*    */     //   9: aload_1
/*    */     //   10: invokeinterface isEmpty : ()Z
/*    */     //   15: goto -> 75
/*    */     //   18: aload_1
/*    */     //   19: invokeinterface nonEmpty : ()Z
/*    */     //   24: ifeq -> 74
/*    */     //   27: aload_2
/*    */     //   28: aload_0
/*    */     //   29: invokeinterface head : ()Ljava/lang/Object;
/*    */     //   34: aload_1
/*    */     //   35: invokeinterface head : ()Ljava/lang/Object;
/*    */     //   40: invokeinterface apply : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*    */     //   45: invokestatic unboxToBoolean : (Ljava/lang/Object;)Z
/*    */     //   48: ifeq -> 74
/*    */     //   51: aload_0
/*    */     //   52: invokeinterface tail : ()Ljava/lang/Object;
/*    */     //   57: checkcast scala/collection/LinearSeqLike
/*    */     //   60: aload_1
/*    */     //   61: invokeinterface tail : ()Ljava/lang/Object;
/*    */     //   66: checkcast scala/collection/GenSeq
/*    */     //   69: astore_1
/*    */     //   70: astore_0
/*    */     //   71: goto -> 0
/*    */     //   74: iconst_0
/*    */     //   75: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #75	-> 0
/*    */     //   #76	-> 18
/*    */     //   #74	-> 75
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	76	0	$this	Lscala/collection/LinearSeqLike;
/*    */     //   0	76	1	that	Lscala/collection/GenSeq;
/*    */     //   0	76	2	p	Lscala/Function2;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\LinearSeqLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */