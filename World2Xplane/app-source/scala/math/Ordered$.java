/*    */ package scala.math;
/*    */ 
/*    */ public final class Ordered$ {
/*    */   public static final Ordered$ MODULE$;
/*    */   
/*    */   private Ordered$() {
/* 94 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> Ordered<T> orderingToOrdered(Object x, Ordering ord) {
/* 97 */     return new Ordered$$anon$1(x, ord);
/*    */   }
/*    */   
/*    */   public static class Ordered$$anon$1 implements Ordered<T> {
/*    */     private final Object x$1;
/*    */     
/*    */     private final Ordering ord$1;
/*    */     
/*    */     public boolean $less(Object that) {
/* 97 */       return Ordered$class.$less(this, that);
/*    */     }
/*    */     
/*    */     public boolean $greater(Object that) {
/* 97 */       return Ordered$class.$greater(this, that);
/*    */     }
/*    */     
/*    */     public boolean $less$eq(Object that) {
/* 97 */       return Ordered$class.$less$eq(this, that);
/*    */     }
/*    */     
/*    */     public boolean $greater$eq(Object that) {
/* 97 */       return Ordered$class.$greater$eq(this, that);
/*    */     }
/*    */     
/*    */     public int compareTo(Object that) {
/* 97 */       return Ordered$class.compareTo(this, that);
/*    */     }
/*    */     
/*    */     public int compare(Object that) {
/* 97 */       return this.ord$1.compare(this.x$1, that);
/*    */     }
/*    */     
/*    */     public Ordered$$anon$1(Object x$1, Ordering ord$1) {
/* 97 */       Ordered$class.$init$(this);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Ordered$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */