/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.util.regexp.Base;
/*     */ 
/*     */ public final class ELEMENTS$ extends AbstractFunction1<Base.RegExp, ELEMENTS> implements Serializable {
/*     */   public static final ELEMENTS$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 117 */     return "ELEMENTS";
/*     */   }
/*     */   
/*     */   public ELEMENTS apply(Base.RegExp r) {
/* 117 */     return new ELEMENTS(r);
/*     */   }
/*     */   
/*     */   public Option<Base.RegExp> unapply(ELEMENTS x$0) {
/* 117 */     return (x$0 == null) ? (Option<Base.RegExp>)scala.None$.MODULE$ : (Option<Base.RegExp>)new Some(x$0.r());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 117 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ELEMENTS$() {
/* 117 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ELEMENTS$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */