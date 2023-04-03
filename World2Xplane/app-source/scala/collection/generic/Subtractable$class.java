/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Function2;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Seq;
/*    */ 
/*    */ public abstract class Subtractable$class {
/*    */   public static void $init$(Subtractable $this) {}
/*    */   
/*    */   public static Subtractable $minus(Subtractable $this, Object elem1, Object elem2, Seq elems) {
/* 50 */     return (Subtractable)$this.$minus(elem1).$minus(elem2).$minus$minus((GenTraversableOnce)elems);
/*    */   }
/*    */   
/*    */   public static Subtractable $minus$minus(Subtractable<A, Repr> $this, GenTraversableOnce xs) {
/* 59 */     Object object = $this.repr();
/* 59 */     return (Subtractable)xs.seq().$div$colon(object, (Function2)new Subtractable$$anonfun$$minus$minus$1($this));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\Subtractable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */