/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class Routees$ extends AbstractFunction1<IndexedSeq<Routee>, Routees> implements Serializable {
/*     */   public static final Routees$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 379 */     return "Routees";
/*     */   }
/*     */   
/*     */   public Routees apply(IndexedSeq<Routee> routees) {
/* 379 */     return new Routees(routees);
/*     */   }
/*     */   
/*     */   public Option<IndexedSeq<Routee>> unapply(Routees x$0) {
/* 379 */     return (x$0 == null) ? (Option<IndexedSeq<Routee>>)scala.None$.MODULE$ : (Option<IndexedSeq<Routee>>)new Some(x$0.routees());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 379 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Routees$() {
/* 379 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Routees$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */