/*    */ package scala.sys.process;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import scala.Function1;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Y3A!\001\002\003\023\tI\001K]8dKN\034\030j\024\006\003\007\021\tq\001\035:pG\026\0348O\003\002\006\r\005\0311/_:\013\003\035\tQa]2bY\006\034\001a\005\002\001\025A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\t\021=\001!Q1A\005\002A\t!b\036:ji\026Le\016];u+\005\t\002\003B\006\023)qI!a\005\004\003\023\031+hn\031;j_:\f\004CA\013\032\035\t1r#D\001\003\023\tA\"!A\bqe>\034Wm]:J]R,'O\\1m\023\tQ2D\001\007PkR\004X\017^*ue\026\fWN\003\002\031\005A\0211\"H\005\003=\031\021A!\0268ji\"A\001\005\001B\001B\003%\021#A\006xe&$X-\0238qkR\004\003\002\003\022\001\005\013\007I\021A\022\002\033A\024xnY3tg>+H\017];u+\005!\003\003B\006\023Kq\001\"!\006\024\n\005\035Z\"aC%oaV$8\013\036:fC6D\001\"\013\001\003\002\003\006I\001J\001\017aJ|7-Z:t\037V$\b/\036;!\021!Y\003A!b\001\n\003\031\023\001\0049s_\016,7o]#se>\024\b\002C\027\001\005\003\005\013\021\002\023\002\033A\024xnY3tg\026\023(o\034:!\021!y\003A!b\001\n\003\001\024\001\0053bK6|g.\033>f)\"\024X-\0313t+\005\t\004CA\0063\023\t\031dAA\004C_>dW-\0318\t\021U\002!\021!Q\001\nE\n\021\003Z1f[>t\027N_3UQJ,\027\rZ:!\021\0259\004\001\"\0019\003\031a\024N\\5u}Q)\021HO\036={A\021a\003\001\005\006\037Y\002\r!\005\005\006EY\002\r\001\n\005\006WY\002\r\001\n\005\006_Y\002\r!\r\005\006o\001!\ta\020\013\005s\001\023E\tC\003B}\001\007\021#\001\002j]\")1I\020a\001I\005\031q.\036;\t\013\025s\004\031\001\023\002\007\025\024(\017C\003H\001\021\005\001*A\005xSRD\027J\0349viR\021\021(\023\005\006\025\032\003\r!E\001\006oJLG/\032\005\006\031\002!\t!T\001\013o&$\bnT;uaV$HCA\035O\021\025\0311\n1\001%\021\025\001\006\001\"\001R\003%9\030\016\0365FeJ|'\017\006\002:%\")1a\024a\001I!)A\013\001C\001+\006QA-Y3n_:L'0\0323\025\003e\002")
/*    */ public final class ProcessIO {
/*    */   private final Function1<OutputStream, BoxedUnit> writeInput;
/*    */   
/*    */   private final Function1<InputStream, BoxedUnit> processOutput;
/*    */   
/*    */   private final Function1<InputStream, BoxedUnit> processError;
/*    */   
/*    */   private final boolean daemonizeThreads;
/*    */   
/*    */   public Function1<OutputStream, BoxedUnit> writeInput() {
/* 49 */     return this.writeInput;
/*    */   }
/*    */   
/*    */   public ProcessIO(Function1<OutputStream, BoxedUnit> writeInput, Function1<InputStream, BoxedUnit> processOutput, Function1<InputStream, BoxedUnit> processError, boolean daemonizeThreads) {}
/*    */   
/*    */   public Function1<InputStream, BoxedUnit> processOutput() {
/* 50 */     return this.processOutput;
/*    */   }
/*    */   
/*    */   public Function1<InputStream, BoxedUnit> processError() {
/* 51 */     return this.processError;
/*    */   }
/*    */   
/*    */   public boolean daemonizeThreads() {
/* 52 */     return this.daemonizeThreads;
/*    */   }
/*    */   
/*    */   public ProcessIO(Function1<OutputStream, BoxedUnit> in, Function1<InputStream, BoxedUnit> out, Function1<InputStream, BoxedUnit> err) {
/* 54 */     this(in, out, err, false);
/*    */   }
/*    */   
/*    */   public ProcessIO withInput(Function1<OutputStream, BoxedUnit> write) {
/* 57 */     return new ProcessIO(write, processOutput(), processError(), daemonizeThreads());
/*    */   }
/*    */   
/*    */   public ProcessIO withOutput(Function1<InputStream, BoxedUnit> process) {
/* 60 */     return new ProcessIO(writeInput(), process, processError(), daemonizeThreads());
/*    */   }
/*    */   
/*    */   public ProcessIO withError(Function1<InputStream, BoxedUnit> process) {
/* 63 */     return new ProcessIO(writeInput(), processOutput(), process, daemonizeThreads());
/*    */   }
/*    */   
/*    */   public ProcessIO daemonized() {
/* 66 */     return new ProcessIO(writeInput(), processOutput(), processError(), true);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessIO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */