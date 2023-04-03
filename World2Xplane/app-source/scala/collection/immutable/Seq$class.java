/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.immutable.ParSeq$;
/*    */ 
/*    */ public abstract class Seq$class {
/*    */   public static void $init$(Seq $this) {}
/*    */   
/*    */   public static GenericCompanion companion(Seq $this) {
/* 32 */     return (GenericCompanion)Seq$.MODULE$;
/*    */   }
/*    */   
/*    */   public static Seq toSeq(Seq $this) {
/* 33 */     return $this;
/*    */   }
/*    */   
/*    */   public static Seq seq(Seq $this) {
/* 34 */     return $this;
/*    */   }
/*    */   
/*    */   public static Combiner parCombiner(Seq $this) {
/* 35 */     return ParSeq$.MODULE$.newCombiner();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Seq$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */