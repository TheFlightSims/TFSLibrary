/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class ExtDef$ extends AbstractFunction1<ExternalID, ExtDef> implements Serializable {
/*     */   public static final ExtDef$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 117 */     return "ExtDef";
/*     */   }
/*     */   
/*     */   public ExtDef apply(ExternalID extID) {
/* 117 */     return new ExtDef(extID);
/*     */   }
/*     */   
/*     */   public Option<ExternalID> unapply(ExtDef x$0) {
/* 117 */     return (x$0 == null) ? (Option<ExternalID>)scala.None$.MODULE$ : (Option<ExternalID>)new Some(x$0.extID());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 117 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ExtDef$() {
/* 117 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ExtDef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */