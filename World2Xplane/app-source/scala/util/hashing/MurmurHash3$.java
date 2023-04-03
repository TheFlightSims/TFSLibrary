/*     */ package scala.util.hashing;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.IntRef;
/*     */ 
/*     */ public final class MurmurHash3$ extends MurmurHash3 {
/*     */   public static final MurmurHash3$ MODULE$;
/*     */   
/*     */   private final int arraySeed;
/*     */   
/*     */   private final int stringSeed;
/*     */   
/*     */   private final int productSeed;
/*     */   
/*     */   private final int symmetricSeed;
/*     */   
/*     */   private final int traversableSeed;
/*     */   
/*     */   private final int seqSeed;
/*     */   
/*     */   private final int mapSeed;
/*     */   
/*     */   private final int setSeed;
/*     */   
/*     */   public class MurmurHash3$$anonfun$unorderedHash$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef a$1;
/*     */     
/*     */     private final IntRef b$1;
/*     */     
/*     */     private final IntRef n$1;
/*     */     
/*     */     private final IntRef c$1;
/*     */     
/*     */     public MurmurHash3$$anonfun$unorderedHash$1(MurmurHash3 $outer, IntRef a$1, IntRef b$1, IntRef n$1, IntRef c$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/*  91 */       int h = scala.runtime.ScalaRunTime$.MODULE$.hash(x);
/*  92 */       this.a$1.elem += h;
/*  93 */       this.b$1.elem ^= h;
/*  94 */       if (h != 0)
/*  94 */         this.c$1.elem *= h; 
/*  95 */       this.n$1.elem++;
/*     */     }
/*     */   }
/*     */   
/*     */   public class MurmurHash3$$anonfun$orderedHash$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef n$2;
/*     */     
/*     */     private final IntRef h$1;
/*     */     
/*     */     public MurmurHash3$$anonfun$orderedHash$1(MurmurHash3 $outer, IntRef n$2, IntRef h$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 109 */       this.h$1.elem = this.$outer.mix(this.h$1.elem, scala.runtime.ScalaRunTime$.MODULE$.hash(x));
/* 110 */       this.n$2.elem++;
/*     */     }
/*     */   }
/*     */   
/*     */   private MurmurHash3$() {
/* 197 */     MODULE$ = this;
/* 203 */     this.seqSeed = "Seq".hashCode();
/* 204 */     this.mapSeed = "Map".hashCode();
/* 205 */     this.setSeed = "Set".hashCode();
/*     */   }
/*     */   
/*     */   public final int arraySeed() {
/*     */     return 1007110753;
/*     */   }
/*     */   
/*     */   public final int stringSeed() {
/*     */     return -137723950;
/*     */   }
/*     */   
/*     */   public final int productSeed() {
/*     */     return -889275714;
/*     */   }
/*     */   
/*     */   public final int symmetricSeed() {
/*     */     return -1248659538;
/*     */   }
/*     */   
/*     */   public final int traversableSeed() {
/*     */     return -415593707;
/*     */   }
/*     */   
/*     */   public final int seqSeed() {
/*     */     return this.seqSeed;
/*     */   }
/*     */   
/*     */   public final int mapSeed() {
/*     */     return this.mapSeed;
/*     */   }
/*     */   
/*     */   public final int setSeed() {
/* 205 */     return this.setSeed;
/*     */   }
/*     */   
/*     */   public <T> int arrayHash(Object a) {
/* 207 */     return arrayHash(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int arrayHash$mZc$sp(boolean[] a) {
/* 207 */     return arrayHash$mZc$sp(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int arrayHash$mBc$sp(byte[] a) {
/* 207 */     return arrayHash$mBc$sp(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int arrayHash$mCc$sp(char[] a) {
/* 207 */     return arrayHash$mCc$sp(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int arrayHash$mDc$sp(double[] a) {
/* 207 */     return arrayHash$mDc$sp(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int arrayHash$mFc$sp(float[] a) {
/* 207 */     return arrayHash$mFc$sp(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int arrayHash$mIc$sp(int[] a) {
/* 207 */     return arrayHash$mIc$sp(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int arrayHash$mJc$sp(long[] a) {
/* 207 */     return arrayHash$mJc$sp(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int arrayHash$mSc$sp(short[] a) {
/* 207 */     return arrayHash$mSc$sp(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int arrayHash$mVc$sp(BoxedUnit[] a) {
/* 207 */     return arrayHash$mVc$sp(a, 1007110753);
/*     */   }
/*     */   
/*     */   public int bytesHash(byte[] data) {
/* 208 */     return bytesHash(data, 1007110753);
/*     */   }
/*     */   
/*     */   public int orderedHash(TraversableOnce<Object> xs) {
/* 209 */     return orderedHash(xs, -1248659538);
/*     */   }
/*     */   
/*     */   public int productHash(Product x) {
/* 210 */     return productHash(x, -889275714);
/*     */   }
/*     */   
/*     */   public int stringHash(String x) {
/* 211 */     return stringHash(x, -137723950);
/*     */   }
/*     */   
/*     */   public int unorderedHash(TraversableOnce<Object> xs) {
/* 212 */     return unorderedHash(xs, -415593707);
/*     */   }
/*     */   
/*     */   public int seqHash(Seq xs) {
/*     */     int i;
/* 216 */     if (xs instanceof List) {
/* 216 */       List<?> list = (List)xs;
/* 216 */       i = listHash(list, seqSeed());
/*     */     } else {
/* 218 */       i = orderedHash((TraversableOnce<Object>)xs, seqSeed());
/*     */     } 
/*     */     return i;
/*     */   }
/*     */   
/*     */   public int mapHash(Map xs) {
/* 221 */     return unorderedHash((TraversableOnce<Object>)xs, mapSeed());
/*     */   }
/*     */   
/*     */   public int setHash(Set xs) {
/* 222 */     return unorderedHash((TraversableOnce<Object>)xs, setSeed());
/*     */   }
/*     */   
/*     */   public <T> MurmurHash3.ArrayHashing<T> arrayHashing() {
/* 228 */     return new MurmurHash3.ArrayHashing<T>();
/*     */   }
/*     */   
/*     */   public MurmurHash3.ArrayHashing<Object> arrayHashing$mZc$sp() {
/* 228 */     return new MurmurHash3.ArrayHashing$mcZ$sp();
/*     */   }
/*     */   
/*     */   public MurmurHash3.ArrayHashing<Object> arrayHashing$mBc$sp() {
/* 228 */     return new MurmurHash3.ArrayHashing$mcB$sp();
/*     */   }
/*     */   
/*     */   public MurmurHash3.ArrayHashing<Object> arrayHashing$mCc$sp() {
/* 228 */     return new MurmurHash3.ArrayHashing$mcC$sp();
/*     */   }
/*     */   
/*     */   public MurmurHash3.ArrayHashing<Object> arrayHashing$mDc$sp() {
/* 228 */     return new MurmurHash3.ArrayHashing$mcD$sp();
/*     */   }
/*     */   
/*     */   public MurmurHash3.ArrayHashing<Object> arrayHashing$mFc$sp() {
/* 228 */     return new MurmurHash3.ArrayHashing$mcF$sp();
/*     */   }
/*     */   
/*     */   public MurmurHash3.ArrayHashing<Object> arrayHashing$mIc$sp() {
/* 228 */     return new MurmurHash3.ArrayHashing$mcI$sp();
/*     */   }
/*     */   
/*     */   public MurmurHash3.ArrayHashing<Object> arrayHashing$mJc$sp() {
/* 228 */     return new MurmurHash3.ArrayHashing$mcJ$sp();
/*     */   }
/*     */   
/*     */   public MurmurHash3.ArrayHashing<Object> arrayHashing$mSc$sp() {
/* 228 */     return new MurmurHash3.ArrayHashing$mcS$sp();
/*     */   }
/*     */   
/*     */   public MurmurHash3.ArrayHashing<BoxedUnit> arrayHashing$mVc$sp() {
/* 228 */     return new MurmurHash3.ArrayHashing$mcV$sp();
/*     */   }
/*     */   
/*     */   public Object bytesHashing() {
/* 230 */     return new MurmurHash3$$anon$1();
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$1 implements Hashing<byte[]> {
/*     */     public int hash(byte[] data) {
/* 231 */       return MurmurHash3$.MODULE$.bytesHash(data);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object orderedHashing() {
/* 234 */     return new MurmurHash3$$anon$2();
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$2 implements Hashing<TraversableOnce<Object>> {
/*     */     public int hash(TraversableOnce<Object> xs) {
/* 235 */       return MurmurHash3$.MODULE$.orderedHash(xs);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object productHashing() {
/* 238 */     return new MurmurHash3$$anon$3();
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$3 implements Hashing<Product> {
/*     */     public int hash(Product x) {
/* 239 */       return MurmurHash3$.MODULE$.productHash(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object stringHashing() {
/* 242 */     return new MurmurHash3$$anon$4();
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$4 implements Hashing<String> {
/*     */     public int hash(String x) {
/* 243 */       return MurmurHash3$.MODULE$.stringHash(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public Object unorderedHashing() {
/* 246 */     return new MurmurHash3$$anon$5();
/*     */   }
/*     */   
/*     */   public static class MurmurHash3$$anon$5 implements Hashing<TraversableOnce<Object>> {
/*     */     public int hash(TraversableOnce<Object> xs) {
/* 247 */       return MurmurHash3$.MODULE$.unorderedHash(xs);
/*     */     }
/*     */   }
/*     */   
/*     */   public final <T> int symmetricHash$default$2() {
/* 279 */     return -1248659538;
/*     */   }
/*     */   
/*     */   public final <T> int symmetricHash(GenTraversableOnce xs, int seed) {
/* 280 */     return unorderedHash(xs.seq(), seed);
/*     */   }
/*     */   
/*     */   public final <T> int traversableHash$default$2() {
/* 283 */     return -415593707;
/*     */   }
/*     */   
/*     */   public final <T> int traversableHash(GenTraversableOnce xs, int seed) {
/* 284 */     return orderedHash(xs.seq(), seed);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\hashing\MurmurHash3$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */