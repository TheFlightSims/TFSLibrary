/*    */ package scala.collection;
/*    */ 
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ 
/*    */ public abstract class SortedSetLike$class {
/*    */   public static void $init$(SortedSetLike $this) {}
/*    */   
/*    */   public static SortedSet keySet(SortedSetLike $this) {
/* 25 */     return (SortedSet)$this.repr();
/*    */   }
/*    */   
/*    */   public static Object firstKey(SortedSetLike $this) {
/* 27 */     return $this.head();
/*    */   }
/*    */   
/*    */   public static Object lastKey(SortedSetLike $this) {
/* 28 */     return $this.last();
/*    */   }
/*    */   
/*    */   public static SortedSet from(SortedSetLike $this, Object from) {
/* 32 */     return (SortedSet)$this.rangeImpl((Option)new Some(from), (Option)None$.MODULE$);
/*    */   }
/*    */   
/*    */   public static SortedSet until(SortedSetLike $this, Object until) {
/* 33 */     return (SortedSet)$this.rangeImpl((Option)None$.MODULE$, (Option)new Some(until));
/*    */   }
/*    */   
/*    */   public static SortedSet range(SortedSetLike $this, Object from, Object until) {
/* 34 */     return (SortedSet)$this.rangeImpl((Option)new Some(from), (Option)new Some(until));
/*    */   }
/*    */   
/*    */   public static boolean subsetOf(SortedSetLike $this, GenSet that) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: instanceof scala/collection/SortedSet
/*    */     //   4: ifeq -> 61
/*    */     //   7: aload_1
/*    */     //   8: checkcast scala/collection/SortedSet
/*    */     //   11: astore_3
/*    */     //   12: aload_3
/*    */     //   13: invokeinterface ordering : ()Lscala/math/Ordering;
/*    */     //   18: aload_0
/*    */     //   19: invokeinterface ordering : ()Lscala/math/Ordering;
/*    */     //   24: astore_2
/*    */     //   25: dup
/*    */     //   26: ifnonnull -> 37
/*    */     //   29: pop
/*    */     //   30: aload_2
/*    */     //   31: ifnull -> 44
/*    */     //   34: goto -> 61
/*    */     //   37: aload_2
/*    */     //   38: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   41: ifeq -> 61
/*    */     //   44: aload_3
/*    */     //   45: aload_0
/*    */     //   46: invokeinterface iterator : ()Lscala/collection/Iterator;
/*    */     //   51: invokeinterface hasAll : (Lscala/collection/Iterator;)Z
/*    */     //   56: istore #4
/*    */     //   58: goto -> 70
/*    */     //   61: aload_0
/*    */     //   62: aload_1
/*    */     //   63: invokeinterface scala$collection$SortedSetLike$$super$subsetOf : (Lscala/collection/GenSet;)Z
/*    */     //   68: istore #4
/*    */     //   70: iload #4
/*    */     //   72: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #40	-> 0
/*    */     //   #36	-> 0
/*    */     //   #41	-> 61
/*    */     //   #36	-> 70
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	73	0	$this	Lscala/collection/SortedSetLike;
/*    */     //   0	73	1	that	Lscala/collection/GenSet;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SortedSetLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */