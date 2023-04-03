/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Predef$;
/*     */ import scala.util.hashing.MurmurHash3$;
/*     */ 
/*     */ public abstract class GenMapLike$class {
/*     */   public static void $init$(GenMapLike $this) {}
/*     */   
/*     */   public static int hashCode(GenMapLike $this) {
/*  34 */     return MurmurHash3$.MODULE$.mapHash($this.seq());
/*     */   }
/*     */   
/*     */   public static boolean equals(GenMapLike $this, Object that) {
/*     */     boolean bool;
/* 112 */     if (that instanceof GenMap) {
/* 112 */       GenMap genMap = (GenMap)that;
/* 114 */       bool = ($this == genMap || (
/* 115 */         genMap.canEqual($this) && 
/* 116 */         $this.size() == genMap.size() && 
/* 117 */         liftedTree1$1($this, genMap))) ? true : false;
/*     */     } else {
/* 130 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   private static final boolean liftedTree1$1(GenMapLike $this, GenMap x2$1) {
/*     */     try {
/*     */     
/*     */     } catch (ClassCastException classCastException) {
/*     */       Predef$.MODULE$.println("class cast ");
/*     */     } 
/*     */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenMapLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */