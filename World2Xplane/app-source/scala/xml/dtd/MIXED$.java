/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.util.regexp.Base;
/*     */ 
/*     */ public final class MIXED$ extends AbstractFunction1<Base.RegExp, MIXED> implements Serializable {
/*     */   public static final MIXED$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 105 */     return "MIXED";
/*     */   }
/*     */   
/*     */   public MIXED apply(Base.RegExp r) {
/* 105 */     return new MIXED(r);
/*     */   }
/*     */   
/*     */   public Option<Base.RegExp> unapply(MIXED x$0) {
/* 105 */     return (x$0 == null) ? (Option<Base.RegExp>)scala.None$.MODULE$ : (Option<Base.RegExp>)new Some(x$0.r());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 105 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private MIXED$() {
/* 105 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\MIXED$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */