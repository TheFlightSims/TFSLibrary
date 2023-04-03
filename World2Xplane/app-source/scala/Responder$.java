/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.runtime.ObjectRef;
/*    */ 
/*    */ public final class Responder$ implements Serializable {
/*    */   public static final Responder$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 21 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Responder$() {
/* 21 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> Responder<A> constant(Object x) {
/* 25 */     return new Responder$$anon$1(x);
/*    */   }
/*    */   
/*    */   public static class Responder$$anon$1 extends Responder<A> {
/*    */     private final Object x$1;
/*    */     
/*    */     public Responder$$anon$1(Object x$1) {}
/*    */     
/*    */     public void respond(Function1 k) {
/* 26 */       k.apply(this.x$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public <A> boolean exec(Function0 x) {
/* 32 */     x.apply$mcV$sp();
/* 32 */     return true;
/*    */   }
/*    */   
/*    */   public <A> Option<A> run(Responder r) {
/* 37 */     ObjectRef result = new ObjectRef(None$.MODULE$);
/* 38 */     r.foreach((Function1)new Responder$$anonfun$run$1(result));
/* 39 */     return (Option<A>)result.elem;
/*    */   }
/*    */   
/*    */   public static class Responder$$anonfun$run$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final ObjectRef result$1;
/*    */     
/*    */     public final void apply(Object x) {
/*    */       this.result$1.elem = new Some(x);
/*    */     }
/*    */     
/*    */     public Responder$$anonfun$run$1(ObjectRef result$1) {}
/*    */   }
/*    */   
/*    */   public <A> Responder<Nothing$> loop(Responder r) {
/* 43 */     return r.flatMap((Function1)new Responder$$anonfun$loop$1(r));
/*    */   }
/*    */   
/*    */   public static class Responder$$anonfun$loop$1 extends AbstractFunction1<BoxedUnit, Responder<Nothing$>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Responder r$1;
/*    */     
/*    */     public final Responder<Nothing$> apply(BoxedUnit _) {
/* 43 */       return Responder$.MODULE$.loop(this.r$1).map((Function1<Nothing$, Nothing$>)new Responder$$anonfun$loop$1$$anonfun$apply$1(this));
/*    */     }
/*    */     
/*    */     public Responder$$anonfun$loop$1(Responder r$1) {}
/*    */     
/*    */     public class Responder$$anonfun$loop$1$$anonfun$apply$1 extends AbstractFunction1<Nothing$, Nothing$> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Nothing$ apply(Nothing$ y) {
/* 43 */         return y;
/*    */       }
/*    */       
/*    */       public Responder$$anonfun$loop$1$$anonfun$apply$1(Responder$$anonfun$loop$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public <A> Responder<BoxedUnit> loopWhile(Function0 cond, Responder r) {
/* 46 */     return cond.apply$mcZ$sp() ? r.flatMap((Function1)new Responder$$anonfun$loopWhile$1(cond, r)) : 
/* 47 */       constant(BoxedUnit.UNIT);
/*    */   }
/*    */   
/*    */   public static class Responder$$anonfun$loopWhile$1 extends AbstractFunction1<BoxedUnit, Responder<BoxedUnit>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function0 cond$1;
/*    */     
/*    */     private final Responder r$2;
/*    */     
/*    */     public final Responder<BoxedUnit> apply(BoxedUnit _) {
/*    */       return Responder$.MODULE$.loopWhile(this.cond$1, this.r$2).map((Function1<BoxedUnit, BoxedUnit>)new Responder$$anonfun$loopWhile$1$$anonfun$apply$2(this));
/*    */     }
/*    */     
/*    */     public Responder$$anonfun$loopWhile$1(Function0 cond$1, Responder r$2) {}
/*    */     
/*    */     public class Responder$$anonfun$loopWhile$1$$anonfun$apply$2 extends AbstractFunction1<BoxedUnit, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final void apply(BoxedUnit y) {}
/*    */       
/*    */       public Responder$$anonfun$loopWhile$1$$anonfun$apply$2(Responder$$anonfun$loopWhile$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Responder$$anon$3 extends Responder<B> {
/*    */     public final Function1 f$2;
/*    */     
/*    */     public Responder$$anon$3(Responder $outer, Function1 f$2) {}
/*    */     
/*    */     public void respond(Function1 k) {
/* 69 */       this.$outer.respond((Function1)new Responder$$anon$3$$anonfun$respond$1(this, ($anon$3)k));
/*    */     }
/*    */     
/*    */     public class Responder$$anon$3$$anonfun$respond$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Function1 k$2;
/*    */       
/*    */       public final void apply(Object x) {
/* 69 */         this.k$2.apply(this.$outer.f$2.apply(x));
/*    */       }
/*    */       
/*    */       public Responder$$anon$3$$anonfun$respond$1(Responder$$anon$3 $outer, Function1 k$2) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Responder$$anon$2 extends Responder<B> {
/*    */     public final Function1 f$1;
/*    */     
/*    */     public Responder$$anon$2(Responder $outer, Function1 f$1) {}
/*    */     
/*    */     public void respond(Function1 k) {
/* 75 */       this.$outer.respond((Function1)new Responder$$anon$2$$anonfun$respond$2(this, ($anon$2)k));
/*    */     }
/*    */     
/*    */     public class Responder$$anon$2$$anonfun$respond$2 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Function1 k$1;
/*    */       
/*    */       public final void apply(Object x) {
/* 75 */         ((Responder)this.$outer.f$1.apply(x)).respond(this.k$1);
/*    */       }
/*    */       
/*    */       public Responder$$anon$2$$anonfun$respond$2(Responder$$anon$2 $outer, Function1 k$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Responder$$anon$4 extends Responder<A> {
/*    */     public final Function1 p$1;
/*    */     
/*    */     public Responder$$anon$4(Responder $outer, Function1 p$1) {}
/*    */     
/*    */     public void respond(Function1 k) {
/* 81 */       this.$outer.respond((Function1)new Responder$$anon$4$$anonfun$respond$3(this, ($anon$4)k));
/*    */     }
/*    */     
/*    */     public class Responder$$anon$4$$anonfun$respond$3 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Function1 k$3;
/*    */       
/*    */       public final void apply(Object x) {
/* 81 */         if (BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(x)))
/* 81 */           this.k$3.apply(x); 
/*    */       }
/*    */       
/*    */       public Responder$$anon$4$$anonfun$respond$3(Responder$$anon$4 $outer, Function1 k$3) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Responder$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */