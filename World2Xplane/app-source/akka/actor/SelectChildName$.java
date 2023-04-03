/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class SelectChildName$ extends AbstractFunction1<String, SelectChildName> implements Serializable {
/*     */   public static final SelectChildName$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 261 */     return "SelectChildName";
/*     */   }
/*     */   
/*     */   public SelectChildName apply(String name) {
/* 261 */     return new SelectChildName(name);
/*     */   }
/*     */   
/*     */   public Option<String> unapply(SelectChildName x$0) {
/* 261 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.name());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 261 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private SelectChildName$() {
/* 261 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SelectChildName$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */