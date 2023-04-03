/*     */ package scala.collection.generic;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.IndexedSeq$;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ public abstract class GenericTraversableTemplate$class {
/*     */   public static void $init$(GenericTraversableTemplate $this) {}
/*     */   
/*     */   public static Builder newBuilder(GenericTraversableTemplate $this) {
/*  64 */     return $this.companion().newBuilder();
/*     */   }
/*     */   
/*     */   public static Builder genericBuilder(GenericTraversableTemplate $this) {
/*  69 */     return $this.companion().newBuilder();
/*     */   }
/*     */   
/*     */   private static TraversableOnce sequential(GenericTraversableTemplate $this) {
/*  71 */     return ((GenTraversableOnce)$this).seq();
/*     */   }
/*     */   
/*     */   public static Tuple2 unzip(GenericTraversableTemplate $this, Function1 asPair) {
/*  84 */     Builder b1 = $this.genericBuilder();
/*  85 */     Builder b2 = $this.genericBuilder();
/*  86 */     sequential($this).foreach((Function1)new GenericTraversableTemplate$$anonfun$unzip$1($this, b1, b2, (GenericTraversableTemplate<A, CC>)asPair));
/*  91 */     return new Tuple2(b1.result(), b2.result());
/*     */   }
/*     */   
/*     */   public static Tuple3 unzip3(GenericTraversableTemplate $this, Function1 asTriple) {
/* 106 */     Builder b1 = $this.genericBuilder();
/* 107 */     Builder b2 = $this.genericBuilder();
/* 108 */     Builder b3 = $this.genericBuilder();
/* 110 */     sequential($this).foreach((Function1)new GenericTraversableTemplate$$anonfun$unzip3$1($this, b1, b2, b3, (GenericTraversableTemplate<A, CC>)asTriple));
/* 116 */     return new Tuple3(b1.result(), b2.result(), b3.result());
/*     */   }
/*     */   
/*     */   public static GenTraversable flatten(GenericTraversableTemplate $this, Function1 asTraversable) {
/* 144 */     Builder b = $this.genericBuilder();
/* 145 */     sequential($this).foreach((Function1)new GenericTraversableTemplate$$anonfun$flatten$1($this, b, (GenericTraversableTemplate<A, CC>)asTraversable));
/* 147 */     return (GenTraversable)b.result();
/*     */   }
/*     */   
/*     */   public static GenTraversable transpose(GenericTraversableTemplate<A, CC> $this, Function1 asTraversable) {
/* 163 */     if ($this.isEmpty())
/* 164 */       return (GenTraversable)$this.genericBuilder().result(); 
/* 168 */     int headSize = ((GenTraversableOnce)asTraversable.apply($this.head())).size();
/* 169 */     IndexedSeq bs = (IndexedSeq)IndexedSeq$.MODULE$.fill(headSize, (Function0)new GenericTraversableTemplate$$anonfun$1($this));
/* 170 */     sequential($this).foreach((Function1)new GenericTraversableTemplate$$anonfun$transpose$1($this, headSize, bs, (GenericTraversableTemplate<A, CC>)asTraversable));
/* 180 */     Builder<?, CC> bb = $this.genericBuilder();
/* 181 */     bs.foreach((Function1)new GenericTraversableTemplate$$anonfun$transpose$2($this, (GenericTraversableTemplate)bb));
/* 182 */     return (GenTraversable)bb.result();
/*     */   }
/*     */   
/*     */   public static final Nothing$ fail$1(GenericTraversableTemplate $this) {
/*     */     throw new IllegalArgumentException("transpose requires all collections have the same size");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericTraversableTemplate$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */