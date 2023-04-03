/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParIterable$;
/*    */ 
/*    */ public abstract class Iterable$class {
/*    */   public static void $init$(Iterable $this) {}
/*    */   
/*    */   public static GenericCompanion companion(Iterable $this) {
/* 24 */     return (GenericCompanion)Iterable$.MODULE$;
/*    */   }
/*    */   
/*    */   public static Combiner parCombiner(Iterable $this) {
/* 25 */     return ParIterable$.MODULE$.newCombiner();
/*    */   }
/*    */   
/*    */   public static Iterable seq(Iterable $this) {
/* 26 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Iterable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */