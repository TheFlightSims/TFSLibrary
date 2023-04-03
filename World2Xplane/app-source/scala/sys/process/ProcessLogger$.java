/*    */ package scala.sys.process;
/*    */ 
/*    */ import java.io.File;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ 
/*    */ public final class ProcessLogger$ {
/*    */   public static final ProcessLogger$ MODULE$;
/*    */   
/*    */   private ProcessLogger$() {
/* 78 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public FileProcessLogger apply(File file) {
/* 80 */     return new FileProcessLogger(file);
/*    */   }
/*    */   
/*    */   public ProcessLogger apply(Function1 fn) {
/* 85 */     return new ProcessLogger$$anon$1(fn, fn);
/*    */   }
/*    */   
/*    */   public ProcessLogger apply(Function1 fout, Function1 ferr) {
/* 95 */     return new ProcessLogger$$anon$1(fout, ferr);
/*    */   }
/*    */   
/*    */   public static class ProcessLogger$$anon$1 implements ProcessLogger {
/*    */     private final Function1 fout$1;
/*    */     
/*    */     private final Function1 ferr$1;
/*    */     
/*    */     public ProcessLogger$$anon$1(Function1 fout$1, Function1 ferr$1) {}
/*    */     
/*    */     public void out(Function0 s) {
/* 96 */       this.fout$1.apply(s.apply());
/*    */     }
/*    */     
/*    */     public void err(Function0 s) {
/* 97 */       this.ferr$1.apply(s.apply());
/*    */     }
/*    */     
/*    */     public <T> T buffer(Function0 f) {
/* 98 */       return (T)f.apply();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessLogger$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */