/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcDJ$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcDJ.sp {
/*    */   public AbstractPartialFunction$mcDJ$sp() {
/* 25 */     Function1.mcDJ.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public double apply$mcDJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToDouble(applyOrElse(BoxesRunTime.boxToLong(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public double apply(long x) {
/* 33 */     return apply$mcDJ$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcDJ$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */