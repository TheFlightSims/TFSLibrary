/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.immutable.ParSet$;
/*    */ 
/*    */ public abstract class Set$class {
/*    */   public static void $init$(Set $this) {}
/*    */   
/*    */   public static GenericCompanion companion(Set $this) {
/* 34 */     return (GenericCompanion)Set$.MODULE$;
/*    */   }
/*    */   
/*    */   public static Set toSet(Set $this) {
/* 35 */     return $this;
/*    */   }
/*    */   
/*    */   public static Set seq(Set $this) {
/* 36 */     return $this;
/*    */   }
/*    */   
/*    */   public static Combiner parCombiner(Set $this) {
/* 37 */     return ParSet$.MODULE$.newCombiner();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Set$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */