/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.immutable.Nil$;
/*    */ 
/*    */ public abstract class ParSet$class {
/*    */   public static void $init$(ParSet $this) {}
/*    */   
/*    */   public static GenericCompanion companion(ParSet $this) {
/* 37 */     return (GenericCompanion)ParSet$.MODULE$;
/*    */   }
/*    */   
/*    */   public static ParSet empty(ParSet $this) {
/* 38 */     return (ParSet)ParHashSet$.MODULE$.apply((Seq)Nil$.MODULE$);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParSet$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */