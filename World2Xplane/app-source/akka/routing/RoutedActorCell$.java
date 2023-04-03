/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorContext;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class RoutedActorCell$ {
/*     */   public static final RoutedActorCell$ MODULE$;
/*     */   
/*     */   private RoutedActorCell$() {
/*  30 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$addRoutees$1 extends AbstractFunction1<Routee, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Routee routee) {
/*  63 */       this.$outer.akka$routing$RoutedActorCell$$watch(routee);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$addRoutees$1(RoutedActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$1 extends AbstractFunction2<IndexedSeq<Routee>, Routee, IndexedSeq<Routee>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final IndexedSeq<Routee> apply(IndexedSeq xs, Routee x) {
/*  77 */       this.$outer.akka$routing$RoutedActorCell$$unwatch(x);
/*  77 */       return (IndexedSeq<Routee>)xs.filterNot((Function1)new RoutedActorCell$$anonfun$1$$anonfun$apply$1(this, x));
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$1(RoutedActorCell $outer) {}
/*     */     
/*     */     public class RoutedActorCell$$anonfun$1$$anonfun$apply$1 extends AbstractFunction1<Routee, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Routee x$12;
/*     */       
/*     */       public final boolean apply(Routee x$1) {
/*  77 */         Routee routee = this.x$12;
/*  77 */         if (x$1 == null) {
/*  77 */           if (routee != null);
/*  77 */         } else if (x$1.equals(routee)) {
/*     */         
/*     */         } 
/*     */       }
/*     */       
/*     */       public RoutedActorCell$$anonfun$1$$anonfun$apply$1(RoutedActorCell$$anonfun$1 $outer, Routee x$12) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$removeRoutees$1 extends AbstractFunction1<Routee, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Routee routee) {
/*  79 */       this.$outer.akka$routing$RoutedActorCell$$stopIfChild(routee);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$removeRoutees$1(RoutedActorCell $outer) {}
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$start$1 extends AbstractFunction0<Routee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final DeprecatedRouterConfig x2$1;
/*     */     
/*     */     public final Routee apply() {
/* 110 */       return this.x2$1.newRoutee(this.$outer.routeeProps(), (ActorContext)this.$outer);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$start$1(RoutedActorCell $outer, DeprecatedRouterConfig x2$1) {}
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$start$2 extends AbstractFunction1<String, Routee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final DeprecatedRouterConfig x2$1;
/*     */     
/*     */     public final Routee apply(String p) {
/* 113 */       return this.x2$1.routeeFor(p, (ActorContext)this.$outer);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$start$2(RoutedActorCell $outer, DeprecatedRouterConfig x2$1) {}
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$start$3 extends AbstractFunction0<Routee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Pool x3$1;
/*     */     
/*     */     public final Routee apply() {
/* 116 */       return this.x3$1.newRoutee(this.$outer.routeeProps(), (ActorContext)this.$outer);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$start$3(RoutedActorCell $outer, Pool x3$1) {}
/*     */   }
/*     */   
/*     */   public class RoutedActorCell$$anonfun$start$4 extends AbstractFunction1<String, Routee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Group x4$1;
/*     */     
/*     */     public final Routee apply(String p) {
/* 120 */       return this.x4$1.routeeFor(p, (ActorContext)this.$outer);
/*     */     }
/*     */     
/*     */     public RoutedActorCell$$anonfun$start$4(RoutedActorCell $outer, Group x4$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RoutedActorCell$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */