/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.immutable.ParIterable$;
/*    */ 
/*    */ public abstract class Iterable$class {
/*    */   public static void $init$(Iterable $this) {}
/*    */   
/*    */   public static GenericCompanion companion(Iterable $this) {
/* 31 */     return (GenericCompanion)Iterable$.MODULE$;
/*    */   }
/*    */   
/*    */   public static Combiner parCombiner(Iterable $this) {
/* 32 */     return ParIterable$.MODULE$.newCombiner();
/*    */   }
/*    */   
/*    */   public static Iterable seq(Iterable $this) {
/* 33 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Iterable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */