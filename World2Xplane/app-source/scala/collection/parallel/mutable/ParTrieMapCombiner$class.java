/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.collection.parallel.Combiner;
/*     */ 
/*     */ public abstract class ParTrieMapCombiner$class {
/*     */   public static void $init$(ParTrieMapCombiner $this) {}
/*     */   
/*     */   public static Combiner combine(ParTrieMapCombiner $this, Combiner other) {
/* 161 */     if ($this == other)
/* 161 */       return $this; 
/* 162 */     throw new UnsupportedOperationException("This shouldn't have been called in the first place.");
/*     */   }
/*     */   
/*     */   public static boolean canBeShared(ParTrieMapCombiner $this) {
/* 174 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParTrieMapCombiner$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */