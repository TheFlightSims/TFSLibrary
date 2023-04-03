/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class PEReference$ extends AbstractFunction1<String, PEReference> implements Serializable {
/*     */   public static final PEReference$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 124 */     return "PEReference";
/*     */   }
/*     */   
/*     */   public PEReference apply(String ent) {
/* 124 */     return new PEReference(ent);
/*     */   }
/*     */   
/*     */   public Option<String> unapply(PEReference x$0) {
/* 124 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.ent());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 124 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private PEReference$() {
/* 124 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\PEReference$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */