/*    */ package scala.runtime;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import scala.Function0;
/*    */ import scala.Serializable;
/*    */ 
/*    */ public final class WorksheetSupport$ {
/*    */   public static final WorksheetSupport$ MODULE$;
/*    */   
/*    */   private int scala$runtime$WorksheetSupport$$currentOffset;
/*    */   
/*    */   private final WorksheetSupport.FlushedOutputStream flushedOut;
/*    */   
/*    */   private final PrintStream printOut;
/*    */   
/*    */   private WorksheetSupport$() {
/*  8 */     MODULE$ = this;
/* 11 */     this.scala$runtime$WorksheetSupport$$currentOffset = 0;
/* 53 */     this.flushedOut = new WorksheetSupport.FlushedOutputStream(System.out);
/* 54 */     this.printOut = new PrintStream(flushedOut());
/*    */   }
/*    */   
/*    */   public int scala$runtime$WorksheetSupport$$currentOffset() {
/*    */     return this.scala$runtime$WorksheetSupport$$currentOffset;
/*    */   }
/*    */   
/*    */   private void scala$runtime$WorksheetSupport$$currentOffset_$eq(int x$1) {
/*    */     this.scala$runtime$WorksheetSupport$$currentOffset = x$1;
/*    */   }
/*    */   
/*    */   private WorksheetSupport.FlushedOutputStream flushedOut() {
/*    */     return this.flushedOut;
/*    */   }
/*    */   
/*    */   private PrintStream printOut() {
/* 54 */     return this.printOut;
/*    */   }
/*    */   
/*    */   private void redirected(Function0 op) {
/* 57 */     PrintStream oldSysOut = System.out;
/* 58 */     PrintStream oldSysErr = System.err;
/* 59 */     PrintStream oldConsOut = scala.Console$.MODULE$.out();
/* 60 */     PrintStream oldConsErr = scala.Console$.MODULE$.err();
/* 61 */     System.setOut(printOut());
/* 62 */     System.setErr(printOut());
/* 63 */     scala.Console$.MODULE$.setOut(printOut());
/* 64 */     scala.Console$.MODULE$.setErr(printOut());
/*    */     try {
/* 65 */       op.apply$mcV$sp();
/*    */       return;
/*    */     } finally {
/* 67 */       printOut().close();
/* 68 */       System.setOut(oldSysOut);
/* 69 */       System.setErr(oldSysErr);
/* 70 */       scala.Console$.MODULE$.setOut(oldConsOut);
/* 71 */       scala.Console$.MODULE$.setErr(oldConsErr);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void $execute(Function0 op) {
/* 75 */     redirected(
/* 76 */         new WorksheetSupport$$anonfun$$execute$1(op));
/*    */   }
/*    */   
/*    */   public static class WorksheetSupport$$anonfun$$execute$1 extends AbstractFunction0$mcV$sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function0 op$1;
/*    */     
/*    */     public final void apply() {
/* 76 */       apply$mcV$sp();
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/*    */       try {
/* 76 */         this.op$1.apply$mcV$sp();
/* 76 */       } catch (StopException stopException) {
/*    */       
/*    */       } finally {}
/*    */     }
/*    */     
/*    */     public WorksheetSupport$$anonfun$$execute$1(Function0 op$1) {}
/*    */   }
/*    */   
/*    */   public void $skip(int n) {
/* 84 */     flushedOut().ensureNewLine();
/* 85 */     scala$runtime$WorksheetSupport$$currentOffset_$eq(scala$runtime$WorksheetSupport$$currentOffset() + n);
/*    */   }
/*    */   
/*    */   public Nothing$ $stop() {
/* 88 */     throw new StopException();
/*    */   }
/*    */   
/*    */   public String $show(Object x) {
/* 90 */     return ScalaRunTime$.MODULE$.stringOf(x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\WorksheetSupport$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */