/*    */ package akka.japi.pf;
/*    */ 
/*    */ import scala.PartialFunction;
/*    */ 
/*    */ class AbstractMatch<I, R> {
/*    */   protected final PartialFunction<I, R> statements;
/*    */   
/*    */   AbstractMatch(PartialFunction<I, R> paramPartialFunction) {
/* 23 */     PartialFunction<?, ?> partialFunction = CaseStatement.empty();
/* 24 */     if (paramPartialFunction == null) {
/* 25 */       this.statements = (PartialFunction)partialFunction;
/*    */     } else {
/* 27 */       this.statements = paramPartialFunction.orElse(partialFunction);
/*    */     } 
/*    */   }
/*    */   
/*    */   public PartialFunction<I, R> asPF() {
/* 36 */     return this.statements;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\AbstractMatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */