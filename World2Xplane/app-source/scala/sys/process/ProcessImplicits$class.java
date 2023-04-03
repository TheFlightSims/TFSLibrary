/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import scala.Function1;
/*     */ import scala.collection.Seq;
/*     */ import scala.xml.Elem;
/*     */ 
/*     */ public abstract class ProcessImplicits$class {
/*     */   public static void $init$(ProcessImplicits $this) {}
/*     */   
/*     */   public static Seq buildersToProcess(ProcessImplicits $this, Seq builders, Function1 convert) {
/* 197 */     return ProcessCreation$class.applySeq(Process$.MODULE$, builders, convert);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder builderToProcess(ProcessImplicits $this, java.lang.ProcessBuilder builder) {
/* 200 */     return Process$.MODULE$.apply(builder);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder.FileBuilder fileToProcess(ProcessImplicits $this, File file) {
/* 210 */     return Process$.MODULE$.apply(file);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder.URLBuilder urlToProcess(ProcessImplicits $this, URL url) {
/* 220 */     return Process$.MODULE$.apply(url);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder xmlToProcess(ProcessImplicits $this, Elem command) {
/* 228 */     return Process$.MODULE$.apply(command);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder stringToProcess(ProcessImplicits $this, String command) {
/* 231 */     return Process$.MODULE$.apply(command);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder stringSeqToProcess(ProcessImplicits $this, Seq<String> command) {
/* 238 */     return Process$.MODULE$.apply(command);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessImplicits$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */