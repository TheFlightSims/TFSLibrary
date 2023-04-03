/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class OpenHashMap$ {
/*     */   public static final OpenHashMap$ MODULE$;
/*     */   
/*     */   private OpenHashMap$() {
/*  19 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <K, V> OpenHashMap<K, V> apply(Seq elems) {
/*  22 */     return (OpenHashMap)(new OpenHashMap<Object, Object>()).$plus$plus$eq((TraversableOnce<Tuple2<?, ?>>)elems);
/*     */   }
/*     */   
/*     */   public <K, V> OpenHashMap<K, V> empty() {
/*  23 */     return new OpenHashMap<K, V>();
/*     */   }
/*     */   
/*     */   public int nextPowerOfTwo(int i) {
/*  30 */     return scala.collection.generic.BitOperations$Int$.MODULE$.highestOneBit(i) << 1;
/*     */   }
/*     */   
/*     */   public class OpenHashMap$$anonfun$growTable$1 extends AbstractFunction1<OpenHashMap.OpenEntry<Key, Value>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public OpenHashMap$$anonfun$growTable$1(OpenHashMap $outer) {}
/*     */     
/*     */     public final void apply(OpenHashMap.OpenEntry entry) {
/*  92 */       if (entry != null) {
/*  92 */         scala.None$ none$ = scala.None$.MODULE$;
/*  92 */         if (entry.value() == null) {
/*  92 */           entry.value();
/*  92 */           if (none$ != null) {
/*  92 */             this.$outer.scala$collection$mutable$OpenHashMap$$addEntry(entry);
/*     */             return;
/*     */           } 
/*  92 */         } else if (!entry.value().equals(none$)) {
/*  92 */           this.$outer.scala$collection$mutable$OpenHashMap$$addEntry(entry);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class OpenHashMap$$anon$1 extends AbstractIterator<Tuple2<Key, Value>> {
/*     */     private int index;
/*     */     
/*     */     private final int initialModCount;
/*     */     
/*     */     public OpenHashMap$$anon$1(OpenHashMap $outer) {
/* 181 */       this.index = 0;
/* 182 */       this.initialModCount = $outer.scala$collection$mutable$OpenHashMap$$modCount;
/*     */     }
/*     */     
/*     */     private int index() {
/*     */       return this.index;
/*     */     }
/*     */     
/*     */     private void index_$eq(int x$1) {
/*     */       this.index = x$1;
/*     */     }
/*     */     
/*     */     private int initialModCount() {
/* 182 */       return this.initialModCount;
/*     */     }
/*     */     
/*     */     private void advance() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokespecial initialModCount : ()I
/*     */       //   4: aload_0
/*     */       //   5: getfield $outer : Lscala/collection/mutable/OpenHashMap;
/*     */       //   8: getfield scala$collection$mutable$OpenHashMap$$modCount : I
/*     */       //   11: if_icmpeq -> 23
/*     */       //   14: getstatic scala/sys/package$.MODULE$ : Lscala/sys/package$;
/*     */       //   17: ldc 'Concurrent modification'
/*     */       //   19: invokevirtual error : (Ljava/lang/String;)Lscala/runtime/Nothing$;
/*     */       //   22: athrow
/*     */       //   23: aload_0
/*     */       //   24: invokespecial index : ()I
/*     */       //   27: aload_0
/*     */       //   28: getfield $outer : Lscala/collection/mutable/OpenHashMap;
/*     */       //   31: invokevirtual scala$collection$mutable$OpenHashMap$$mask : ()I
/*     */       //   34: if_icmpgt -> 103
/*     */       //   37: aload_0
/*     */       //   38: getfield $outer : Lscala/collection/mutable/OpenHashMap;
/*     */       //   41: invokevirtual scala$collection$mutable$OpenHashMap$$table : ()[Lscala/collection/mutable/OpenHashMap$OpenEntry;
/*     */       //   44: aload_0
/*     */       //   45: invokespecial index : ()I
/*     */       //   48: aaload
/*     */       //   49: ifnull -> 90
/*     */       //   52: aload_0
/*     */       //   53: getfield $outer : Lscala/collection/mutable/OpenHashMap;
/*     */       //   56: invokevirtual scala$collection$mutable$OpenHashMap$$table : ()[Lscala/collection/mutable/OpenHashMap$OpenEntry;
/*     */       //   59: aload_0
/*     */       //   60: invokespecial index : ()I
/*     */       //   63: aaload
/*     */       //   64: invokevirtual value : ()Lscala/Option;
/*     */       //   67: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   70: astore_1
/*     */       //   71: dup
/*     */       //   72: ifnonnull -> 83
/*     */       //   75: pop
/*     */       //   76: aload_1
/*     */       //   77: ifnull -> 90
/*     */       //   80: goto -> 103
/*     */       //   83: aload_1
/*     */       //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   87: ifeq -> 103
/*     */       //   90: aload_0
/*     */       //   91: aload_0
/*     */       //   92: invokespecial index : ()I
/*     */       //   95: iconst_1
/*     */       //   96: iadd
/*     */       //   97: invokespecial index_$eq : (I)V
/*     */       //   100: goto -> 23
/*     */       //   103: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #185	-> 0
/*     */       //   #186	-> 23
/*     */       //   #184	-> 103
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	104	0	this	Lscala/collection/mutable/OpenHashMap$$anon$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 189 */       advance();
/* 189 */       return (index() <= this.$outer.scala$collection$mutable$OpenHashMap$$mask());
/*     */     }
/*     */     
/*     */     public Tuple2<Key, Value> next() {
/* 192 */       advance();
/* 193 */       OpenHashMap.OpenEntry<Key, Value> result = this.$outer.scala$collection$mutable$OpenHashMap$$table()[index()];
/* 194 */       index_$eq(index() + 1);
/* 195 */       return new Tuple2(result.key(), result.value().get());
/*     */     }
/*     */   }
/*     */   
/*     */   public class OpenHashMap$$anonfun$clone$1 extends AbstractFunction1<OpenHashMap.OpenEntry<Key, Value>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final OpenHashMap it$1;
/*     */     
/*     */     public final void apply(OpenHashMap.OpenEntry entry) {
/* 201 */       this.it$1.scala$collection$mutable$OpenHashMap$$put(entry.key(), entry.hash(), entry.value().get());
/*     */     }
/*     */     
/*     */     public OpenHashMap$$anonfun$clone$1(OpenHashMap $outer, OpenHashMap it$1) {}
/*     */   }
/*     */   
/*     */   public class OpenHashMap$$anonfun$foreach$1 extends AbstractFunction1<OpenHashMap.OpenEntry<Key, Value>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$2;
/*     */     
/*     */     private final int startModCount$1;
/*     */     
/*     */     public OpenHashMap$$anonfun$foreach$1(OpenHashMap $outer, Function1 f$2, int startModCount$1) {}
/*     */     
/*     */     public final void apply(OpenHashMap.OpenEntry entry) {
/* 218 */       if (this.$outer.scala$collection$mutable$OpenHashMap$$modCount != this.startModCount$1)
/* 218 */         throw scala.sys.package$.MODULE$.error("Concurrent Modification"); 
/* 219 */       this.f$2.apply(new Tuple2(entry.key(), entry.value().get()));
/*     */     }
/*     */   }
/*     */   
/*     */   public class OpenHashMap$$anonfun$foreachUndeletedEntry$1 extends AbstractFunction1<OpenHashMap.OpenEntry<Key, Value>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public final void apply(OpenHashMap.OpenEntry entry) {
/* 224 */       if (entry != null) {
/* 224 */         scala.None$ none$ = scala.None$.MODULE$;
/* 224 */         if (entry.value() == null) {
/* 224 */           entry.value();
/* 224 */           if (none$ != null) {
/* 224 */             this.f$1.apply(entry);
/*     */             return;
/*     */           } 
/* 224 */         } else if (!entry.value().equals(none$)) {
/* 224 */           this.f$1.apply(entry);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public OpenHashMap$$anonfun$foreachUndeletedEntry$1(OpenHashMap $outer, Function1 f$1) {}
/*     */   }
/*     */   
/*     */   public class OpenHashMap$$anonfun$transform$1 extends AbstractFunction1<OpenHashMap.OpenEntry<Key, Value>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 f$3;
/*     */     
/*     */     public final void apply(OpenHashMap.OpenEntry entry) {
/* 228 */       entry.value_$eq((Option)new Some(this.f$3.apply(entry.key(), entry.value().get())));
/*     */     }
/*     */     
/*     */     public OpenHashMap$$anonfun$transform$1(OpenHashMap $outer, Function2 f$3) {}
/*     */   }
/*     */   
/*     */   public class OpenHashMap$$anonfun$retain$1 extends AbstractFunction1<OpenHashMap.OpenEntry<Key, Value>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 f$4;
/*     */     
/*     */     public final void apply(OpenHashMap.OpenEntry entry) {
/* 233 */       if (!BoxesRunTime.unboxToBoolean(this.f$4.apply(entry.key(), entry.value().get()))) {
/* 233 */         entry.value_$eq((Option)scala.None$.MODULE$);
/* 233 */         this.$outer.scala$collection$mutable$OpenHashMap$$size_$eq(this.$outer.size() - 1);
/* 233 */         this.$outer.scala$collection$mutable$OpenHashMap$$deleted_$eq(this.$outer.scala$collection$mutable$OpenHashMap$$deleted() + 1);
/*     */       } 
/*     */     }
/*     */     
/*     */     public OpenHashMap$$anonfun$retain$1(OpenHashMap $outer, Function2 f$4) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\OpenHashMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */