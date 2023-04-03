/*     */ package scala.collection;
/*     */ 
/*     */ import scala.util.hashing.MurmurHash3$;
/*     */ 
/*     */ public abstract class GenSetLike$class {
/*     */   public static void $init$(GenSetLike $this) {}
/*     */   
/*     */   public static boolean apply(GenSetLike $this, Object elem) {
/*  43 */     return $this.contains(elem);
/*     */   }
/*     */   
/*     */   public static Object intersect(GenSetLike $this, GenSet that) {
/*  51 */     return $this.filter(that);
/*     */   }
/*     */   
/*     */   public static Object $amp(GenSetLike $this, GenSet that) {
/*  60 */     return $this.intersect(that);
/*     */   }
/*     */   
/*     */   public static Object $bar(GenSetLike $this, GenSet that) {
/*  77 */     return $this.union(that);
/*     */   }
/*     */   
/*     */   public static Object $amp$tilde(GenSetLike $this, GenSet that) {
/*  94 */     return $this.diff(that);
/*     */   }
/*     */   
/*     */   public static boolean subsetOf(GenSetLike $this, GenSet that) {
/* 102 */     return $this.forall(that);
/*     */   }
/*     */   
/*     */   public static boolean equals(GenSetLike $this, Object that) {
/*     */     boolean bool;
/* 114 */     if (that instanceof GenSet) {
/* 114 */       GenSet genSet = (GenSet)that;
/* 116 */       bool = ($this == genSet || (
/* 117 */         genSet.canEqual($this) && 
/* 118 */         $this.size() == genSet.size() && 
/* 119 */         liftedTree1$1($this, genSet))) ? true : false;
/*     */     } else {
/* 122 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   private static final boolean liftedTree1$1(GenSetLike $this, GenSet x2$1) {
/*     */     try {
/*     */     
/*     */     } catch (ClassCastException classCastException) {}
/*     */     return false;
/*     */   }
/*     */   
/*     */   public static int hashCode(GenSetLike $this) {
/* 130 */     return MurmurHash3$.MODULE$.setHash($this.seq());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenSetLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */