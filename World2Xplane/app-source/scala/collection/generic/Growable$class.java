/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ 
/*    */ public abstract class Growable$class {
/*    */   public static void $init$(Growable $this) {}
/*    */   
/*    */   public static Growable $plus$eq(Growable<Object> $this, Object elem1, Object elem2, Seq elems) {
/* 41 */     return $this.$plus$eq(elem1).$plus$eq(elem2).$plus$plus$eq((TraversableOnce)elems);
/*    */   }
/*    */   
/*    */   public static Growable $plus$plus$eq(Growable<A> $this, TraversableOnce xs) {
/* 48 */     xs.seq().foreach((Function1)new Growable$$anonfun$$plus$plus$eq$1($this));
/* 48 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\Growable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */