/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.parallel.Combiner;
/*    */ 
/*    */ public abstract class Parallelizable$class {
/*    */   public static void $init$(Parallelizable $this) {}
/*    */   
/*    */   public static Parallel par(Parallelizable $this) {
/* 40 */     Combiner cb = $this.parCombiner();
/* 41 */     $this.seq().foreach((Function1)new Parallelizable$$anonfun$par$1($this, (Parallelizable<A, ParRepr>)cb));
/* 42 */     return (Parallel)cb.result();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Parallelizable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */