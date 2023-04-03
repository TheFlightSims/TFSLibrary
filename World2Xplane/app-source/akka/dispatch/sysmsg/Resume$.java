/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class Resume$ extends AbstractFunction1<Throwable, Resume> implements Serializable {
/*     */   public static final Resume$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 225 */     return "Resume";
/*     */   }
/*     */   
/*     */   public Resume apply(Throwable causedByFailure) {
/* 225 */     return new Resume(causedByFailure);
/*     */   }
/*     */   
/*     */   public Option<Throwable> unapply(Resume x$0) {
/* 225 */     return (x$0 == null) ? (Option<Throwable>)scala.None$.MODULE$ : (Option<Throwable>)new Some(x$0.causedByFailure());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 225 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Resume$() {
/* 225 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Resume$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */