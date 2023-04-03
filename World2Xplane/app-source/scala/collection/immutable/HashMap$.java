/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.generic.BitOperations;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenMapFactory;
/*     */ import scala.collection.generic.ImmutableMapFactory;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class HashMap$ extends ImmutableMapFactory<HashMap> implements BitOperations.Int, Serializable {
/*     */   public static final HashMap$ MODULE$;
/*     */   
/*     */   private final HashMap.Merger<Object, Object> defaultMerger;
/*     */   
/*     */   public boolean zero(int i, int mask) {
/* 121 */     return BitOperations.Int.class.zero(this, i, mask);
/*     */   }
/*     */   
/*     */   public int mask(int i, int mask) {
/* 121 */     return BitOperations.Int.class.mask(this, i, mask);
/*     */   }
/*     */   
/*     */   public boolean hasMatch(int key, int prefix, int m) {
/* 121 */     return BitOperations.Int.class.hasMatch(this, key, prefix, m);
/*     */   }
/*     */   
/*     */   public boolean unsignedCompare(int i, int j) {
/* 121 */     return BitOperations.Int.class.unsignedCompare(this, i, j);
/*     */   }
/*     */   
/*     */   public boolean shorter(int m1, int m2) {
/* 121 */     return BitOperations.Int.class.shorter(this, m1, m2);
/*     */   }
/*     */   
/*     */   public int complement(int i) {
/* 121 */     return BitOperations.Int.class.complement(this, i);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> bits(int num) {
/* 121 */     return BitOperations.Int.class.bits(this, num);
/*     */   }
/*     */   
/*     */   public String bitString(int num, String sep) {
/* 121 */     return BitOperations.Int.class.bitString(this, num, sep);
/*     */   }
/*     */   
/*     */   public int highestOneBit(int j) {
/* 121 */     return BitOperations.Int.class.highestOneBit(this, j);
/*     */   }
/*     */   
/*     */   public String bitString$default$2() {
/* 121 */     return BitOperations.Int.class.bitString$default$2(this);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 121 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private HashMap$() {
/* 121 */     MODULE$ = this;
/* 121 */     BitOperations.Int.class.$init$(this);
/* 133 */     HashMap.$anonfun$1 $anonfun$1 = new HashMap.$anonfun$1();
/* 133 */     this.defaultMerger = new HashMap$$anon$2((Function2)$anonfun$1);
/*     */   }
/*     */   
/*     */   public <A1, B1> HashMap.Merger<A1, B1> scala$collection$immutable$HashMap$$liftMerger(Function2 mergef) {
/*     */     return (mergef == null) ? (HashMap.Merger)this.defaultMerger : new HashMap$$anon$2(mergef);
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction2<Tuple2<Object, Object>, Tuple2<Object, Object>, Tuple2<Object, Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<Object, Object> apply(Tuple2<Object, Object> a, Tuple2 b) {
/* 133 */       return a;
/*     */     }
/*     */   }
/*     */   
/*     */   private <A1, B1> HashMap.Merger<A1, B1> liftMerger0(Function2 mergef) {
/* 135 */     return new HashMap$$anon$2(mergef);
/*     */   }
/*     */   
/*     */   public static class HashMap$$anon$2 extends HashMap.Merger<A1, B1> {
/*     */     public Tuple2<A1, B1> apply(Tuple2 kv1, Tuple2 kv2) {
/* 137 */       return (Tuple2<A1, B1>)this.mergef$1.apply(kv1, kv2);
/*     */     }
/*     */     
/* 138 */     private final HashMap.Merger<A1, B1> invert = new HashMap$$anon$2$$anon$3(this);
/*     */     
/*     */     public final Function2 mergef$1;
/*     */     
/*     */     public HashMap.Merger<A1, B1> invert() {
/* 138 */       return this.invert;
/*     */     }
/*     */     
/*     */     public HashMap$$anon$2(Function2 mergef$1) {}
/*     */     
/*     */     public class HashMap$$anon$2$$anon$3 extends HashMap.Merger<A1, B1> {
/*     */       public HashMap$$anon$2$$anon$3(HashMap$$anon$2 $outer) {}
/*     */       
/*     */       public Tuple2<A1, B1> apply(Tuple2 kv1, Tuple2 kv2) {
/* 139 */         return (Tuple2<A1, B1>)this.$outer.mergef$1.apply(kv2, kv1);
/*     */       }
/*     */       
/*     */       public HashMap.Merger<A1, B1> invert() {
/* 140 */         return this.$outer;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public <A, B> CanBuildFrom<HashMap<?, ?>, Tuple2<A, B>, HashMap<A, B>> canBuildFrom() {
/* 145 */     return (CanBuildFrom<HashMap<?, ?>, Tuple2<A, B>, HashMap<A, B>>)new GenMapFactory.MapCanBuildFrom((GenMapFactory)this);
/*     */   }
/*     */   
/*     */   public <A, B> HashMap<A, B> empty() {
/* 146 */     return HashMap.EmptyHashMap$.MODULE$;
/*     */   }
/*     */   
/*     */   public <A, B> HashMap.HashTrieMap<A, B> scala$collection$immutable$HashMap$$makeHashTrieMap(int hash0, HashMap<?, ?> elem0, int hash1, HashMap<?, ?> elem1, int level, int size) {
/* 152 */     int index0 = hash0 >>> level & 0x1F;
/* 153 */     int index1 = hash1 >>> level & 0x1F;
/* 155 */     int bitmap = 1 << index0 | 1 << index1;
/* 156 */     HashMap[] elems = new HashMap[2];
/* 157 */     if (index0 < index1) {
/* 158 */       elems[0] = elem0;
/* 159 */       elems[1] = elem1;
/*     */     } else {
/* 161 */       elems[0] = elem1;
/* 162 */       elems[1] = elem0;
/*     */     } 
/* 166 */     HashMap[] arrayOfHashMap1 = new HashMap[1];
/* 167 */     int i = 1 << index0;
/* 168 */     arrayOfHashMap1[0] = scala$collection$immutable$HashMap$$makeHashTrieMap(hash0, elem0, hash1, elem1, level + 5, size);
/* 169 */     return (index0 != index1) ? new HashMap.HashTrieMap<A, B>(bitmap, (HashMap<A, B>[])elems, size) : new HashMap.HashTrieMap<A, B>(i, (HashMap<A, B>[])arrayOfHashMap1, size);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\HashMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */