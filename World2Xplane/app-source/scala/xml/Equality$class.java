/*     */ package scala.xml;
/*     */ 
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public abstract class Equality$class {
/*     */   public static void $init$(Equality $this) {}
/*     */   
/*     */   public static boolean strict_$bang$eq(Equality $this, Equality other) {
/*  72 */     return !$this.strict_$eq$eq(other);
/*     */   }
/*     */   
/*     */   public static boolean canEqual(Equality $this, Object other) {
/*     */     boolean bool;
/*  77 */     if (other instanceof Equality) {
/*  77 */       bool = true;
/*     */     } else {
/*  79 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public static int hashCode(Equality $this) {
/*  88 */     return ScalaRunTime$.MODULE$.hash($this.basisForHashCode());
/*     */   }
/*     */   
/*     */   public static boolean equals(Equality $this, Object other) {
/*  89 */     return doComparison($this, other, false);
/*     */   }
/*     */   
/*     */   public static final boolean xml_$eq$eq(Equality $this, Object other) {
/*  90 */     return doComparison($this, other, true);
/*     */   }
/*     */   
/*     */   public static final boolean xml_$bang$eq(Equality $this, Object other) {
/*  91 */     return !$this.xml_$eq$eq(other);
/*     */   }
/*     */   
/*     */   private static boolean doComparison(Equality $this, Object other, boolean blithe) {
/*     */     boolean bool;
/*  98 */     if (other instanceof Object && $this == other) {
/*  98 */       bool = true;
/* 100 */     } else if (other instanceof Equality) {
/* 100 */       Equality equality = (Equality)other;
/* 100 */       bool = (equality.canEqual($this) && $this.strict_$eq$eq(equality)) ? true : false;
/*     */     } else {
/* 101 */       bool = false;
/*     */     } 
/*     */     return (bool || (blithe && Equality$.MODULE$.compareBlithely($this, Equality$.MODULE$.asRef(other))));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Equality$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */