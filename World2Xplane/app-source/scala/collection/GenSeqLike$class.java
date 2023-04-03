/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.util.hashing.MurmurHash3$;
/*     */ 
/*     */ public abstract class GenSeqLike$class {
/*     */   public static void $init$(GenSeqLike $this) {}
/*     */   
/*     */   public static boolean isDefinedAt(GenSeqLike $this, int idx) {
/*  71 */     return (idx >= 0 && idx < $this.length());
/*     */   }
/*     */   
/*     */   public static int prefixLength(GenSeqLike $this, Function1 p) {
/*  92 */     return $this.segmentLength(p, 0);
/*     */   }
/*     */   
/*     */   public static int indexWhere(GenSeqLike $this, Function1 p) {
/* 113 */     return $this.indexWhere(p, 0);
/*     */   }
/*     */   
/*     */   public static int indexOf(GenSeqLike $this, Object elem) {
/* 128 */     return $this.indexOf(elem, 0);
/*     */   }
/*     */   
/*     */   public static int indexOf(GenSeqLike $this, Object elem, int from) {
/* 144 */     return $this.indexWhere((Function1)new GenSeqLike$$anonfun$indexOf$1($this, (GenSeqLike<A, Repr>)elem), from);
/*     */   }
/*     */   
/*     */   public static int lastIndexOf(GenSeqLike $this, Object elem) {
/* 159 */     return $this.lastIndexWhere((Function1)new GenSeqLike$$anonfun$lastIndexOf$1($this, (GenSeqLike<A, Repr>)elem));
/*     */   }
/*     */   
/*     */   public static int lastIndexOf(GenSeqLike $this, Object elem, int end) {
/* 172 */     return $this.lastIndexWhere((Function1)new GenSeqLike$$anonfun$lastIndexOf$2($this, (GenSeqLike<A, Repr>)elem), end);
/*     */   }
/*     */   
/*     */   public static int lastIndexWhere(GenSeqLike $this, Function1 p) {
/* 182 */     return $this.lastIndexWhere(p, $this.length() - 1);
/*     */   }
/*     */   
/*     */   public static boolean startsWith(GenSeqLike $this, GenSeq that) {
/* 228 */     return $this.startsWith(that, 0);
/*     */   }
/*     */   
/*     */   public static Object union(GenSeqLike $this, GenSeq that, CanBuildFrom bf) {
/* 410 */     return $this.$plus$plus(that, bf);
/*     */   }
/*     */   
/*     */   public static int hashCode(GenSeqLike $this) {
/* 468 */     return MurmurHash3$.MODULE$.seqHash($this.seq());
/*     */   }
/*     */   
/*     */   public static boolean equals(GenSeqLike $this, Object that) {
/*     */     boolean bool;
/* 476 */     if (that instanceof GenSeq) {
/* 476 */       GenSeq genSeq = (GenSeq)that;
/* 476 */       bool = (genSeq.canEqual($this) && $this.sameElements(genSeq)) ? true : false;
/*     */     } else {
/* 478 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenSeqLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */