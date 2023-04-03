/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ 
/*    */ public abstract class Shrinkable$class {
/*    */   public static void $init$(Shrinkable $this) {}
/*    */   
/*    */   public static Shrinkable $minus$eq(Shrinkable<Object> $this, Object elem1, Object elem2, Seq elems) {
/* 39 */     $this.$minus$eq(elem1);
/* 40 */     $this.$minus$eq(elem2);
/* 41 */     return $this.$minus$minus$eq((TraversableOnce)elems);
/*    */   }
/*    */   
/*    */   public static Shrinkable $minus$minus$eq(Shrinkable<A> $this, TraversableOnce xs) {
/* 49 */     xs.seq().foreach((Function1)new Shrinkable$$anonfun$$minus$minus$eq$1($this));
/* 49 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\Shrinkable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */