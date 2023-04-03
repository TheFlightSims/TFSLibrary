/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class BasicIO$ {
/*     */   public static final BasicIO$ MODULE$;
/*     */   
/*     */   private final int BufferSize;
/*     */   
/*     */   private final String Newline;
/*     */   
/*     */   private BasicIO$() {
/*  30 */     MODULE$ = this;
/*  35 */     this.Newline = (String)scala.sys.package$.MODULE$.props().apply("line.separator");
/*     */   }
/*     */   
/*     */   public final int BufferSize() {
/*     */     return 8192;
/*     */   }
/*     */   
/*     */   public final String Newline() {
/*  35 */     return this.Newline;
/*     */   }
/*     */   
/*     */   public ProcessIO apply(boolean withIn, Function1 output, Option<ProcessLogger> log) {
/*  83 */     return new ProcessIO((Function1<OutputStream, BoxedUnit>)new BasicIO$$anonfun$input$1(withIn), (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$processFully$1(output), getErr(log));
/*     */   }
/*     */   
/*     */   public ProcessIO apply(boolean withIn, StringBuffer buffer, Option<ProcessLogger> log) {
/* 106 */     return new ProcessIO(input(withIn), processFully(buffer), getErr(log));
/*     */   }
/*     */   
/*     */   public ProcessIO apply(boolean withIn, ProcessLogger log) {
/* 116 */     return new ProcessIO(input(withIn), processOutFully(log), processErrFully(log));
/*     */   }
/*     */   
/*     */   public Function1<InputStream, BoxedUnit> getErr(Option log) {
/*     */     Function1<InputStream, BoxedUnit> function1;
/* 129 */     if (log instanceof Some) {
/* 129 */       Some some = (Some)log;
/* 130 */       function1 = processErrFully((ProcessLogger)some.x());
/*     */     } else {
/* 131 */       if (scala.None$.MODULE$ == null) {
/* 131 */         if (log != null)
/*     */           throw new MatchError(log); 
/* 131 */       } else if (!scala.None$.MODULE$.equals(log)) {
/*     */         throw new MatchError(log);
/*     */       } 
/* 131 */       function1 = toStdErr();
/*     */     } 
/*     */     return function1;
/*     */   }
/*     */   
/*     */   private Function1<InputStream, BoxedUnit> processErrFully(ProcessLogger log) {
/* 134 */     BasicIO$$anonfun$processErrFully$1 basicIO$$anonfun$processErrFully$1 = new BasicIO$$anonfun$processErrFully$1(log);
/* 134 */     return (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$processFully$1((Function1)basicIO$$anonfun$processErrFully$1);
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$processErrFully$1 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ProcessLogger log$1;
/*     */     
/*     */     public final void apply(String x$1) {
/* 134 */       this.log$1.err((Function0<String>)new BasicIO$$anonfun$processErrFully$1$$anonfun$apply$4(this, x$1));
/*     */     }
/*     */     
/*     */     public BasicIO$$anonfun$processErrFully$1(ProcessLogger log$1) {}
/*     */     
/*     */     public class BasicIO$$anonfun$processErrFully$1$$anonfun$apply$4 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String x$1$1;
/*     */       
/*     */       public final String apply() {
/* 134 */         return this.x$1$1;
/*     */       }
/*     */       
/*     */       public BasicIO$$anonfun$processErrFully$1$$anonfun$apply$4(BasicIO$$anonfun$processErrFully$1 $outer, String x$1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private Function1<InputStream, BoxedUnit> processOutFully(ProcessLogger log) {
/* 135 */     BasicIO$$anonfun$processOutFully$1 basicIO$$anonfun$processOutFully$1 = new BasicIO$$anonfun$processOutFully$1(log);
/* 135 */     return (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$processFully$1((Function1)basicIO$$anonfun$processOutFully$1);
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$processOutFully$1 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ProcessLogger log$2;
/*     */     
/*     */     public final void apply(String x$2) {
/* 135 */       this.log$2.out((Function0<String>)new BasicIO$$anonfun$processOutFully$1$$anonfun$apply$5(this, x$2));
/*     */     }
/*     */     
/*     */     public BasicIO$$anonfun$processOutFully$1(ProcessLogger log$2) {}
/*     */     
/*     */     public class BasicIO$$anonfun$processOutFully$1$$anonfun$apply$5 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String x$2$1;
/*     */       
/*     */       public final String apply() {
/* 135 */         return this.x$2$1;
/*     */       }
/*     */       
/*     */       public BasicIO$$anonfun$processOutFully$1$$anonfun$apply$5(BasicIO$$anonfun$processOutFully$1 $outer, String x$2$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public void close(Closeable c) {
/*     */     try {
/* 138 */       c.close();
/* 138 */     } catch (IOException iOException) {}
/*     */   }
/*     */   
/*     */   public Function1<InputStream, BoxedUnit> processFully(Appendable buffer) {
/* 149 */     BasicIO$$anonfun$appendLine$1 basicIO$$anonfun$appendLine$1 = new BasicIO$$anonfun$appendLine$1(buffer);
/* 149 */     return (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$processFully$1((Function1)basicIO$$anonfun$appendLine$1);
/*     */   }
/*     */   
/*     */   public Function1<InputStream, BoxedUnit> processFully(Function1 processLine) {
/* 162 */     return (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$processFully$1(processLine);
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$processFully$1 extends AbstractFunction1<InputStream, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 processLine$1;
/*     */     
/*     */     public BasicIO$$anonfun$processFully$1(Function1 processLine$1) {}
/*     */     
/*     */     public final void apply(InputStream in) {
/* 163 */       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
/* 164 */       BasicIO$.MODULE$.processLinesFully(this.processLine$1, (Function0<String>)new BasicIO$$anonfun$processFully$1$$anonfun$apply$6(this, reader));
/* 165 */       reader.close();
/*     */     }
/*     */     
/*     */     public class BasicIO$$anonfun$processFully$1$$anonfun$apply$6 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final BufferedReader reader$1;
/*     */       
/*     */       public final String apply() {
/*     */         return this.reader$1.readLine();
/*     */       }
/*     */       
/*     */       public BasicIO$$anonfun$processFully$1$$anonfun$apply$6(BasicIO$$anonfun$processFully$1 $outer, BufferedReader reader$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private final void readFully$1(Function1 processLine$2, Function0 readLine$1) {
/*     */     while (true) {
/* 173 */       String line = (String)readLine$1.apply();
/* 174 */       if (line == null)
/*     */         return; 
/* 175 */       processLine$2.apply(line);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void processLinesFully(Function1 processLine, Function0 readLine) {
/* 179 */     readFully$1(processLine, readLine);
/*     */   }
/*     */   
/*     */   public void connectToIn(OutputStream o) {
/* 183 */     transferFully(BasicIO.Uncloseable$.MODULE$.protect(package$.MODULE$.stdin()), o);
/*     */   }
/*     */   
/*     */   public Function1<OutputStream, BoxedUnit> input(boolean connect) {
/* 189 */     return (Function1<OutputStream, BoxedUnit>)new BasicIO$$anonfun$input$1(connect);
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$input$1 extends AbstractFunction1<OutputStream, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final boolean connect$1;
/*     */     
/*     */     public BasicIO$$anonfun$input$1(boolean connect$1) {}
/*     */     
/*     */     public final void apply(OutputStream outputToProcess) {
/* 190 */       if (this.connect$1)
/* 190 */         BasicIO$.MODULE$.connectToIn(outputToProcess); 
/* 191 */       outputToProcess.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public ProcessIO standard(boolean connectInput) {
/* 195 */     BasicIO$$anonfun$input$1 basicIO$$anonfun$input$1 = new BasicIO$$anonfun$input$1(connectInput);
/* 195 */     return new ProcessIO((Function1<OutputStream, BoxedUnit>)basicIO$$anonfun$input$1, (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$toStdOut$1(), (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$toStdErr$1());
/*     */   }
/*     */   
/*     */   public ProcessIO standard(Function1<OutputStream, BoxedUnit> in) {
/* 198 */     return new ProcessIO(in, toStdOut(), toStdErr());
/*     */   }
/*     */   
/*     */   public Function1<InputStream, BoxedUnit> toStdErr() {
/* 203 */     return (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$toStdErr$1();
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$toStdErr$1 extends AbstractFunction1<InputStream, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(InputStream in) {
/* 203 */       BasicIO$.MODULE$.transferFully(in, package$.MODULE$.stderr());
/*     */     }
/*     */   }
/*     */   
/*     */   public Function1<InputStream, BoxedUnit> toStdOut() {
/* 208 */     return (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$toStdOut$1();
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$toStdOut$1 extends AbstractFunction1<InputStream, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(InputStream in) {
/* 208 */       BasicIO$.MODULE$.transferFully(in, package$.MODULE$.stdout());
/*     */     }
/*     */   }
/*     */   
/*     */   public void transferFully(InputStream in, OutputStream out) {
/*     */     try {
/* 214 */       transferFullyImpl(in, out);
/*     */     } finally {
/*     */       boolean bool;
/* 214 */       Exception exception = null;
/* 215 */       BasicIO$$anonfun$1 basicIO$$anonfun$1 = new BasicIO$$anonfun$1();
/* 215 */       processInternal$ processInternal$ = processInternal$.MODULE$;
/* 215 */       processInternal$$anonfun$onInterrupt$1 processInternal$$anonfun$onInterrupt$1 = new processInternal$$anonfun$onInterrupt$1((Function0)basicIO$$anonfun$1);
/* 215 */       Throwable throwable = exception;
/* 215 */       if (throwable instanceof InterruptedException) {
/* 215 */         bool = true;
/*     */       } else {
/* 215 */         bool = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {}
/*     */     
/*     */     public void apply$mcV$sp() {}
/*     */   }
/*     */   
/*     */   private Function1<String, BoxedUnit> appendLine(Appendable buffer) {
/* 217 */     return (Function1<String, BoxedUnit>)new BasicIO$$anonfun$appendLine$1(buffer);
/*     */   }
/*     */   
/*     */   public static class BasicIO$$anonfun$appendLine$1 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Appendable buffer$1;
/*     */     
/*     */     public BasicIO$$anonfun$appendLine$1(Appendable buffer$1) {}
/*     */     
/*     */     public final void apply(String line) {
/* 218 */       this.buffer$1.append(line);
/* 219 */       this.buffer$1.append(BasicIO$.MODULE$.Newline());
/*     */     }
/*     */   }
/*     */   
/*     */   private void transferFullyImpl(InputStream in, OutputStream out) {
/* 223 */     byte[] buffer = new byte[8192];
/* 233 */     loop$1(in, out, buffer);
/* 234 */     in.close();
/*     */   }
/*     */   
/*     */   private final void loop$1(InputStream in$2, OutputStream out$2, byte[] buffer$2) {
/*     */     while (true) {
/*     */       int byteCount = in$2.read(buffer$2);
/*     */       if (byteCount <= 0)
/*     */         break; 
/*     */       out$2.write(buffer$2, 0, byteCount);
/*     */       try {
/*     */         out$2.flush();
/*     */       } catch (IOException iOException) {
/*     */         boolean available = false;
/*     */         if (!available)
/*     */           break; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\BasicIO$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */