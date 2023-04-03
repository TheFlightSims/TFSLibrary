/*     */ package scala.runtime;
/*     */ 
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ 
/*     */ public final class StringAdd$ {
/*     */   public static final StringAdd$ MODULE$;
/*     */   
/*     */   private StringAdd$() {
/*  12 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(Object $this, Object x$1) {
/*     */     boolean bool;
/*  12 */     if (x$1 instanceof StringAdd) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       Object object = (x$1 == null) ? null : ((StringAdd)x$1).self();
/*     */       if ((($this == object) ? true : (($this == null) ? false : (($this instanceof Number) ? BoxesRunTime.equalsNumObject((Number)$this, object) : (($this instanceof Character) ? BoxesRunTime.equalsCharObject((Character)$this, object) : $this.equals(object))))));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public final int hashCode$extension(Object $this) {
/*     */     return $this.hashCode();
/*     */   }
/*     */   
/*     */   public final String $plus$extension(Object $this, String other) {
/*     */     return (new StringBuilder()).append(String.valueOf($this)).append(other).toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\StringAdd$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */