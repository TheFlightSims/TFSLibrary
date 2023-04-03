/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParSeq$;
/*    */ import scala.runtime.IntRef;
/*    */ 
/*    */ public abstract class SeqLike$class {
/*    */   public static void $init$(SeqLike $this) {}
/*    */   
/*    */   public static Combiner parCombiner(SeqLike $this) {
/* 27 */     return ParSeq$.MODULE$.newCombiner();
/*    */   }
/*    */   
/*    */   public static SeqLike transform(SeqLike $this, Function1 f) {
/* 44 */     IntRef i = new IntRef(0);
/* 45 */     $this.foreach((Function1)new SeqLike$$anonfun$transform$1($this, i, (SeqLike<A, This>)f));
/* 49 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SeqLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */