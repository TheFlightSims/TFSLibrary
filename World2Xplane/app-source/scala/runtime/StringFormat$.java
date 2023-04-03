/*     */ package scala.runtime;
/*     */ 
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ 
/*     */ public final class StringFormat$ {
/*     */   public static final StringFormat$ MODULE$;
/*     */   
/*     */   private StringFormat$() {
/*  13 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(Object $this, Object x$1) {
/*     */     boolean bool;
/*  13 */     if (x$1 instanceof StringFormat) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       Object object = (x$1 == null) ? null : ((StringFormat)x$1).self();
/*     */       if ((($this == object) ? true : (($this == null) ? false : (($this instanceof Number) ? BoxesRunTime.equalsNumObject((Number)$this, object) : (($this instanceof Character) ? BoxesRunTime.equalsCharObject((Character)$this, object) : $this.equals(object))))));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public final int hashCode$extension(Object $this) {
/*     */     return $this.hashCode();
/*     */   }
/*     */   
/*     */   public final String formatted$extension(Object $this, String fmtstr) {
/*     */     return (new StringOps(scala.Predef$.MODULE$.augmentString(fmtstr))).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { $this }));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\StringFormat$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */