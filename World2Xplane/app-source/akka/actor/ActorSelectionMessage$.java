/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class ActorSelectionMessage$ extends AbstractFunction2<Object, Iterable<SelectionPathElement>, ActorSelectionMessage> implements Serializable {
/*     */   public static final ActorSelectionMessage$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 242 */     return "ActorSelectionMessage";
/*     */   }
/*     */   
/*     */   public ActorSelectionMessage apply(Object msg, Iterable<SelectionPathElement> elements) {
/* 242 */     return new ActorSelectionMessage(msg, elements);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Object, Iterable<SelectionPathElement>>> unapply(ActorSelectionMessage x$0) {
/* 242 */     return (x$0 == null) ? (Option<Tuple2<Object, Iterable<SelectionPathElement>>>)scala.None$.MODULE$ : (Option<Tuple2<Object, Iterable<SelectionPathElement>>>)new Some(new Tuple2(x$0.msg(), x$0.elements()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 242 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ActorSelectionMessage$() {
/* 242 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorSelectionMessage$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */