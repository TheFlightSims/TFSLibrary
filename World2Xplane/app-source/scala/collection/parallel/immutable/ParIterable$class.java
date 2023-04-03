/*    */ package scala.collection.parallel.immutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ 
/*    */ public abstract class ParIterable$class {
/*    */   public static void $init$(ParIterable $this) {}
/*    */   
/*    */   public static GenericCompanion companion(ParIterable $this) {
/* 39 */     return (GenericCompanion)ParIterable$.MODULE$;
/*    */   }
/*    */   
/*    */   public static ParIterable toIterable(ParIterable $this) {
/* 42 */     return $this;
/*    */   }
/*    */   
/*    */   public static ParSeq toSeq(ParIterable<T> $this) {
/* 44 */     return (ParSeq)$this.toParCollection((Function0)new ParIterable$$anonfun$toSeq$1($this));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParIterable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */