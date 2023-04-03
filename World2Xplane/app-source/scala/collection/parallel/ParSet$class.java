/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.parallel.mutable.ParHashSet$;
/*    */ 
/*    */ public abstract class ParSet$class {
/*    */   public static void $init$(ParSet $this) {}
/*    */   
/*    */   public static ParSet empty(ParSet $this) {
/* 44 */     return (ParSet)ParHashSet$.MODULE$.apply((Seq)Nil$.MODULE$);
/*    */   }
/*    */   
/*    */   public static GenericCompanion companion(ParSet $this) {
/* 48 */     return (GenericCompanion)ParSet$.MODULE$;
/*    */   }
/*    */   
/*    */   public static String stringPrefix(ParSet $this) {
/* 50 */     return "ParSet";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSet$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */