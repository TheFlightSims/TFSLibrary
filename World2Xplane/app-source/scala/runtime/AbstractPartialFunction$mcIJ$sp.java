/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.PartialFunction$;
/*    */ 
/*    */ public abstract class AbstractPartialFunction$mcIJ$sp extends AbstractPartialFunction<Object, Object> implements Function1.mcIJ.sp {
/*    */   public AbstractPartialFunction$mcIJ$sp() {
/* 25 */     Function1.mcIJ.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public int apply$mcIJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToInt(applyOrElse(BoxesRunTime.boxToLong(x), (Function1)PartialFunction$.MODULE$.empty()));
/*    */   }
/*    */   
/*    */   public int apply(long x) {
/* 33 */     return apply$mcIJ$sp(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction$mcIJ$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */