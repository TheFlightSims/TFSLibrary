/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Option;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ public final class Try$ {
/*     */   public static final Try$ MODULE$;
/*     */   
/*     */   private Try$() {
/* 155 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> Try<T> apply(Function0 r) {
/*     */     try {
/*     */     
/*     */     } finally {
/* 161 */       Exception exception = null;
/* 162 */       Option option = NonFatal$.MODULE$.unapply(exception);
/*     */     } 
/* 162 */     return new Failure<T>((Throwable)option.get());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Try$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */