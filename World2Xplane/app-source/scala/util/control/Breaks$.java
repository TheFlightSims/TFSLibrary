/*    */ package scala.util.control;
/*    */ 
/*    */ import scala.Function0;
/*    */ 
/*    */ public final class Breaks$ extends Breaks {
/*    */   public static final Breaks$ MODULE$;
/*    */   
/*    */   public class Breaks$$anon$1 implements Breaks.TryBlock<T> {
/*    */     public final Function0 op$1;
/*    */     
/*    */     public Breaks$$anon$1(Breaks $outer, Function0 op$1) {}
/*    */     
/*    */     public T catchBreak(Function0 onBreak) {
/*    */       try {
/*    */       
/* 61 */       } catch (BreakControl breakControl2) {
/*    */         BreakControl breakControl1;
/* 61 */         if ((breakControl1 = null) != 
/*    */           
/* 65 */           this.$outer.scala$util$control$Breaks$$breakException())
/* 65 */           throw breakControl1; 
/*    */       } 
/* 66 */       return (T)onBreak.apply();
/*    */     }
/*    */   }
/*    */   
/*    */   private Breaks$() {
/* 91 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\Breaks$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */