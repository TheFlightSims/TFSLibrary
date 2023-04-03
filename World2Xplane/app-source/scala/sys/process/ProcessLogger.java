/*    */ package scala.sys.process;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001u3q!\001\002\021\002G\005\021BA\007Qe>\034Wm]:M_\036<WM\035\006\003\007\021\tq\001\035:pG\026\0348O\003\002\006\r\005\0311/_:\013\003\035\tQa]2bY\006\034\001a\005\002\001\025A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\t\013=\001a\021\001\t\002\007=,H\017\006\002\022)A\0211BE\005\003'\031\021A!\0268ji\"1QC\004CA\002Y\t\021a\035\t\004\027]I\022B\001\r\007\005!a$-\0378b[\026t\004C\001\016\036\035\tY1$\003\002\035\r\0051\001K]3eK\032L!AH\020\003\rM#(/\0338h\025\tab\001C\003\"\001\031\005!%A\002feJ$\"!E\022\t\rU\001C\0211\001\027\021\025)\003A\"\001'\003\031\021WO\0324feV\021qE\013\013\003QM\002\"!\013\026\r\001\021)1\006\nb\001Y\t\tA+\005\002.aA\0211BL\005\003_\031\021qAT8uQ&tw\r\005\002\fc%\021!G\002\002\004\003:L\bB\002\033%\t\003\007Q'A\001g!\rYq\003K\004\006o\tA\t\001O\001\016!J|7-Z:t\031><w-\032:\021\005eRT\"\001\002\007\013\005\021\001\022A\036\024\005iR\001\"B\037;\t\003q\024A\002\037j]&$h\bF\0019\021\025\001%\b\"\001B\003\025\t\007\017\0357z)\t\021U\t\005\002:\007&\021AI\001\002\022\r&dW\r\025:pG\026\0348\017T8hO\026\024\b\"\002$@\001\0049\025\001\0024jY\026\004\"\001S'\016\003%S!AS&\002\005%|'\"\001'\002\t)\fg/Y\005\003\035&\023AAR5mK\")\001I\017C\001!R\021\021K\025\t\003s\001AQaU(A\002Q\013!A\0328\021\t-)\026$E\005\003-\032\021\021BR;oGRLwN\\\031\t\013\001SD\021\001-\025\007EK6\fC\003[/\002\007A+\001\003g_V$\b\"\002/X\001\004!\026\001\0024feJ\004")
/*    */ public interface ProcessLogger {
/*    */   void out(Function0<String> paramFunction0);
/*    */   
/*    */   void err(Function0<String> paramFunction0);
/*    */   
/*    */   <T> T buffer(Function0<T> paramFunction0);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */