/*    */ package scala.util.parsing.ast;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.collection.immutable.List;
/*    */ 
/*    */ public abstract class Mappable$class {
/*    */   public static void $init$(Mappable $this) {}
/*    */   
/*    */   public static Mappable.Mappable StringIsMappable(Mappable $this, String s) {
/* 45 */     return new Mappable$$anon$2($this, s);
/*    */   }
/*    */   
/*    */   public static Mappable.Mappable ListIsMappable(Mappable $this, List xs, Function1 evidence$3) {
/* 50 */     return new Mappable$$anon$3($this, xs, evidence$3);
/*    */   }
/*    */   
/*    */   public static Mappable.Mappable OptionIsMappable(Mappable $this, Option xs, Function1 evidence$4) {
/* 55 */     return new Mappable$$anon$4($this, xs, evidence$4);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\ast\Mappable$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */