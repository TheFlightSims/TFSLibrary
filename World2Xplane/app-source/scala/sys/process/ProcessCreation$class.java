/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.xml.Elem;
/*     */ 
/*     */ public abstract class ProcessCreation$class {
/*     */   public static void $init$(ProcessCreation $this) {}
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, String command) {
/*  51 */     return $this.apply(command, (Option<File>)None$.MODULE$, (Seq<Tuple2<String, String>>)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[0]));
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, Seq<String> command) {
/*  58 */     return $this.apply(command, (Option<File>)None$.MODULE$, (Seq<Tuple2<String, String>>)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[0]));
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, String command, Seq arguments) {
/*  65 */     return $this.apply((Seq<String>)arguments.$plus$colon(command, Seq$.MODULE$.canBuildFrom()), (Option<File>)None$.MODULE$, (Seq<Tuple2<String, String>>)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[0]));
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, String command, File cwd, Seq<Tuple2<String, String>> extraEnv) {
/*  73 */     return $this.apply(command, (Option<File>)new Some(cwd), extraEnv);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, Seq<String> command, File cwd, Seq<Tuple2<String, String>> extraEnv) {
/*  81 */     return $this.apply(command, (Option<File>)new Some(cwd), extraEnv);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, String command, Option<File> cwd, Seq<Tuple2<String, String>> extraEnv) {
/*  89 */     return $this.apply((Seq<String>)Predef$.MODULE$.wrapRefArray((Object[])command.split("\\s+")), cwd, extraEnv);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, Seq command, Option cwd, Seq extraEnv) {
/* 103 */     java.lang.ProcessBuilder jpb = new java.lang.ProcessBuilder((String[])command.toArray(ClassTag$.MODULE$.apply(String.class)));
/* 104 */     if (!cwd.isEmpty()) {
/* 104 */       File file = (File)cwd.get();
/* 104 */       jpb.directory(file);
/*     */     } 
/* 105 */     extraEnv.foreach((Function1)new ProcessCreation$$anonfun$apply$3($this, jpb));
/* 106 */     return $this.apply(jpb);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, java.lang.ProcessBuilder builder) {
/* 115 */     return new ProcessBuilderImpl.Simple(ProcessBuilder$.MODULE$, builder);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder.FileBuilder apply(ProcessCreation $this, File file) {
/* 121 */     return new ProcessBuilderImpl.FileImpl(ProcessBuilder$.MODULE$, file);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder.URLBuilder apply(ProcessCreation $this, URL url) {
/* 127 */     return new ProcessBuilderImpl.URLImpl(ProcessBuilder$.MODULE$, url);
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, Elem command) {
/* 136 */     return $this.apply(command.text().trim());
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, boolean value) {
/* 141 */     return $this.apply(BoxesRunTime.boxToBoolean(value).toString(), (Function0<Object>)new ProcessCreation$$anonfun$apply$1($this, value));
/*     */   }
/*     */   
/*     */   public static ProcessBuilder apply(ProcessCreation $this, String name, Function0<Object> exitValue) {
/* 147 */     return new ProcessBuilderImpl.Dummy(ProcessBuilder$.MODULE$, name, exitValue);
/*     */   }
/*     */   
/*     */   public static Seq applySeq(ProcessCreation $this, Seq builders, Function1 convert) {
/* 152 */     return (Seq)builders.map(convert, Seq$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public static ProcessBuilder cat(ProcessCreation $this, ProcessBuilder.Source file, Seq files) {
/* 172 */     return $this.cat((Seq<ProcessBuilder.Source>)files.$plus$colon(file, Seq$.MODULE$.canBuildFrom()));
/*     */   }
/*     */   
/*     */   public static ProcessBuilder cat(ProcessCreation $this, Seq files) {
/* 181 */     Predef$.MODULE$.require(files.nonEmpty());
/* 182 */     return (ProcessBuilder)((TraversableOnce)files.map((Function1)new ProcessCreation$$anonfun$cat$1($this), Seq$.MODULE$.canBuildFrom())).reduceLeft((Function2)new ProcessCreation$$anonfun$cat$2($this));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessCreation$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */