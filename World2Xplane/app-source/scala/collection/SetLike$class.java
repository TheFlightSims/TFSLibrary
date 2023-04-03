/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.SetBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParSet$;
/*     */ 
/*     */ public abstract class SetLike$class {
/*     */   public static void $init$(SetLike $this) {}
/*     */   
/*     */   public static Builder newBuilder(SetLike $this) {
/*  77 */     return (Builder)new SetBuilder((Set)$this.empty());
/*     */   }
/*     */   
/*     */   public static Combiner parCombiner(SetLike $this) {
/*  79 */     return ParSet$.MODULE$.newCombiner();
/*     */   }
/*     */   
/*     */   public static Seq toSeq(SetLike $this) {
/*  82 */     return (Seq)$this.toBuffer();
/*     */   }
/*     */   
/*     */   public static Buffer toBuffer(SetLike $this) {
/*  84 */     ArrayBuffer result = new ArrayBuffer($this.size());
/*  85 */     $this.copyToBuffer((Buffer)result);
/*  86 */     return (Buffer)result;
/*     */   }
/*     */   
/*     */   public static Object map(SetLike $this, Function1 f, CanBuildFrom bf) {
/*  93 */     return $this.scala$collection$SetLike$$super$map(f, bf);
/*     */   }
/*     */   
/*     */   public static Set $plus(SetLike $this, Object elem1, Object elem2, Seq elems) {
/* 121 */     return $this.$plus(elem1).$plus(elem2).$plus$plus(elems);
/*     */   }
/*     */   
/*     */   public static Set $plus$plus(SetLike<A, This> $this, GenTraversableOnce elems) {
/* 128 */     Set set = (Set)$this.repr();
/* 128 */     return (Set)elems.seq().$div$colon(set, (Function2)new SetLike$$anonfun$$plus$plus$1($this));
/*     */   }
/*     */   
/*     */   public static boolean isEmpty(SetLike $this) {
/* 142 */     return ($this.size() == 0);
/*     */   }
/*     */   
/*     */   public static Set union(SetLike $this, GenSet that) {
/* 150 */     return (Set)$this.$plus$plus(that);
/*     */   }
/*     */   
/*     */   public static Set diff(SetLike $this, GenSet that) {
/* 158 */     return (Set)$this.$minus$minus(that);
/*     */   }
/*     */   
/*     */   public static Iterator subsets(SetLike<A, This> $this, int len) {
/* 167 */     return (len < 0 || len > $this.size()) ? Iterator$.MODULE$.empty() : 
/* 168 */       new SetLike.SubsetsItr($this, (IndexedSeq<A>)$this.toIndexedSeq(), len);
/*     */   }
/*     */   
/*     */   public static Iterator subsets(SetLike<A, This> $this) {
/* 175 */     return new SetLike$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static String stringPrefix(SetLike $this) {
/* 232 */     return "Set";
/*     */   }
/*     */   
/*     */   public static String toString(SetLike $this) {
/* 233 */     return TraversableLike$class.toString($this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SetLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */