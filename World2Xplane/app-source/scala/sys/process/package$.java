/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.net.URL;
/*     */ import scala.Function1;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.xml.Elem;
/*     */ 
/*     */ public final class package$ implements ProcessImplicits {
/*     */   public static final package$ MODULE$;
/*     */   
/*     */   public <T> Seq<ProcessBuilder.Source> buildersToProcess(Seq builders, Function1 convert) {
/* 205 */     return ProcessImplicits$class.buildersToProcess(this, builders, convert);
/*     */   }
/*     */   
/*     */   public ProcessBuilder builderToProcess(java.lang.ProcessBuilder builder) {
/* 205 */     return ProcessImplicits$class.builderToProcess(this, builder);
/*     */   }
/*     */   
/*     */   public ProcessBuilder.FileBuilder fileToProcess(File file) {
/* 205 */     return ProcessImplicits$class.fileToProcess(this, file);
/*     */   }
/*     */   
/*     */   public ProcessBuilder.URLBuilder urlToProcess(URL url) {
/* 205 */     return ProcessImplicits$class.urlToProcess(this, url);
/*     */   }
/*     */   
/*     */   public ProcessBuilder xmlToProcess(Elem command) {
/* 205 */     return ProcessImplicits$class.xmlToProcess(this, command);
/*     */   }
/*     */   
/*     */   public ProcessBuilder stringToProcess(String command) {
/* 205 */     return ProcessImplicits$class.stringToProcess(this, command);
/*     */   }
/*     */   
/*     */   public ProcessBuilder stringSeqToProcess(Seq command) {
/* 205 */     return ProcessImplicits$class.stringSeqToProcess(this, command);
/*     */   }
/*     */   
/*     */   private package$() {
/* 205 */     MODULE$ = this;
/* 205 */     ProcessImplicits$class.$init$(this);
/*     */   }
/*     */   
/*     */   public List<String> javaVmArguments() {
/* 210 */     return scala.collection.JavaConversions$.MODULE$.asScalaBuffer(ManagementFactory.getRuntimeMXBean().getInputArguments()).toList();
/*     */   }
/*     */   
/*     */   public InputStream stdin() {
/* 213 */     return System.in;
/*     */   }
/*     */   
/*     */   public PrintStream stdout() {
/* 215 */     return System.out;
/*     */   }
/*     */   
/*     */   public PrintStream stderr() {
/* 217 */     return System.err;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */