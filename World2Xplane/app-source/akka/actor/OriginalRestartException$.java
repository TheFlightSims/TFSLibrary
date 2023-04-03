/*     */ package akka.actor;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ 
/*     */ public final class OriginalRestartException$ {
/*     */   public static final OriginalRestartException$ MODULE$;
/*     */   
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private OriginalRestartException$() {
/* 208 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   private final Option rec$1(PostRestartException ex) {
/*     */     PostRestartException postRestartException;
/*     */     while (true) {
/* 210 */       postRestartException = ex;
/* 210 */       if (postRestartException != null) {
/* 211 */         Throwable e = postRestartException.originalCause();
/* 211 */         if (e instanceof PostRestartException) {
/* 211 */           PostRestartException postRestartException1 = (PostRestartException)e;
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */       break;
/*     */     } 
/*     */     if (postRestartException != null) {
/* 212 */       Throwable e = postRestartException.originalCause();
/* 212 */       return (Option)new Some(e);
/*     */     } 
/*     */     throw new MatchError(postRestartException);
/*     */   }
/*     */   
/*     */   public Option<Throwable> unapply(PostRestartException ex) {
/* 214 */     return rec$1(ex);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\OriginalRestartException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */