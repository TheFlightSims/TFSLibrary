/*    */ package scala.math;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Serializable;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Equiv$ implements LowPriorityEquiv, Serializable {
/*    */   public static final Equiv$ MODULE$;
/*    */   
/*    */   public <T> Equiv<T> universalEquiv() {
/* 44 */     return LowPriorityEquiv$class.universalEquiv(this);
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 44 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Equiv$() {
/* 44 */     MODULE$ = this;
/* 44 */     LowPriorityEquiv$class.$init$(this);
/*    */   }
/*    */   
/*    */   public <T> Equiv<T> reference() {
/* 45 */     return new Equiv$$anon$1();
/*    */   }
/*    */   
/*    */   public static class Equiv$$anon$1 implements Equiv<T> {
/*    */     public boolean equiv(Object x, Object y) {
/* 46 */       return (x == y);
/*    */     }
/*    */   }
/*    */   
/*    */   public <T> Equiv<T> universal() {
/* 48 */     return new Equiv$$anon$2();
/*    */   }
/*    */   
/*    */   public static class Equiv$$anon$2 implements Equiv<T> {
/*    */     public boolean equiv(Object x, Object y) {
/* 49 */       return ((x == y) ? true : ((x == null) ? false : ((x instanceof Number) ? BoxesRunTime.equalsNumObject((Number)x, y) : ((x instanceof Character) ? BoxesRunTime.equalsCharObject((Character)x, y) : x.equals(y)))));
/*    */     }
/*    */   }
/*    */   
/*    */   public <T> Equiv<T> fromComparator(Comparator cmp) {
/* 51 */     return new Equiv$$anon$3(cmp);
/*    */   }
/*    */   
/*    */   public static class Equiv$$anon$3 implements Equiv<T> {
/*    */     private final Comparator cmp$1;
/*    */     
/*    */     public Equiv$$anon$3(Comparator cmp$1) {}
/*    */     
/*    */     public boolean equiv(Object x, Object y) {
/* 52 */       return (this.cmp$1.compare(x, y) == 0);
/*    */     }
/*    */   }
/*    */   
/*    */   public <T> Equiv<T> fromFunction(Function2 cmp) {
/* 54 */     return new Equiv$$anon$4(cmp);
/*    */   }
/*    */   
/*    */   public static class Equiv$$anon$4 implements Equiv<T> {
/*    */     private final Function2 cmp$2;
/*    */     
/*    */     public Equiv$$anon$4(Function2 cmp$2) {}
/*    */     
/*    */     public boolean equiv(Object x, Object y) {
/* 55 */       return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
/*    */     }
/*    */   }
/*    */   
/*    */   public <T, S> Equiv<T> by(Function1 f, Equiv evidence$1) {
/* 58 */     Equiv$$anonfun$by$1 equiv$$anonfun$by$1 = new Equiv$$anonfun$by$1(f, evidence$1);
/* 58 */     return new Equiv$$anon$4((Function2)equiv$$anonfun$by$1);
/*    */   }
/*    */   
/*    */   public static class Equiv$$anonfun$by$1 extends AbstractFunction2<T, T, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function1 f$1;
/*    */     
/*    */     private final Equiv evidence$1$1;
/*    */     
/*    */     public final boolean apply(Object x, Object y) {
/* 58 */       Equiv<Object> equiv = this.evidence$1$1;
/* 58 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 58 */       return equiv.equiv(this.f$1.apply(x), this.f$1.apply(y));
/*    */     }
/*    */     
/*    */     public Equiv$$anonfun$by$1(Function1 f$1, Equiv evidence$1$1) {}
/*    */   }
/*    */   
/*    */   public <T> Equiv<T> apply(Equiv<T> evidence$2) {
/* 60 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 60 */     return evidence$2;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Equiv$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */