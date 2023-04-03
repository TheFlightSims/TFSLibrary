/*    */ package scala.collection.parallel.immutable;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.immutable.Nil$;
/*    */ 
/*    */ public abstract class ParSet$class {
/*    */   public static void $init$(ParSet $this) {}
/*    */   
/*    */   public static ParSet empty(ParSet $this) {
/* 30 */     return (ParSet)ParHashSet$.MODULE$.apply((Seq)Nil$.MODULE$);
/*    */   }
/*    */   
/*    */   public static GenericCompanion companion(ParSet $this) {
/* 32 */     return (GenericCompanion)ParSet$.MODULE$;
/*    */   }
/*    */   
/*    */   public static String stringPrefix(ParSet $this) {
/* 34 */     return "ParSet";
/*    */   }
/*    */   
/*    */   public static ParSet toSet(ParSet $this) {
/* 37 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParSet$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */