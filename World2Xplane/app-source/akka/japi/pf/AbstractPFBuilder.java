/*    */ package akka.japi.pf;
/*    */ 
/*    */ import scala.PartialFunction;
/*    */ 
/*    */ abstract class AbstractPFBuilder<F, T> {
/* 19 */   protected PartialFunction<F, T> statements = null;
/*    */   
/*    */   protected void addStatement(PartialFunction<F, T> paramPartialFunction) {
/* 22 */     if (this.statements == null) {
/* 23 */       this.statements = paramPartialFunction;
/*    */     } else {
/* 25 */       this.statements = this.statements.orElse(paramPartialFunction);
/*    */     } 
/*    */   }
/*    */   
/*    */   public PartialFunction<F, T> build() {
/* 35 */     PartialFunction<?, ?> partialFunction = CaseStatement.empty();
/* 36 */     PartialFunction<F, T> partialFunction1 = this.statements;
/* 38 */     this.statements = null;
/* 39 */     if (partialFunction1 == null)
/* 40 */       return (PartialFunction)partialFunction; 
/* 42 */     return partialFunction1.orElse(partialFunction);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\AbstractPFBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */