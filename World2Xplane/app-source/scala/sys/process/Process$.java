/*    */ package scala.sys.process;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.collection.Seq;
/*    */ import scala.xml.Elem;
/*    */ 
/*    */ public final class Process$ implements ProcessImpl, ProcessCreation {
/*    */   public static final Process$ MODULE$;
/*    */   
/*    */   private volatile ProcessImpl.Spawn$ Spawn$module;
/*    */   
/*    */   private volatile ProcessImpl.Future$ Future$module;
/*    */   
/*    */   public ProcessBuilder apply(String command) {
/* 40 */     return ProcessCreation$class.apply(this, command);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(Seq command) {
/* 40 */     return ProcessCreation$class.apply(this, command);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(String command, Seq arguments) {
/* 40 */     return ProcessCreation$class.apply(this, command, arguments);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(String command, File cwd, Seq extraEnv) {
/* 40 */     return ProcessCreation$class.apply(this, command, cwd, extraEnv);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(Seq command, File cwd, Seq extraEnv) {
/* 40 */     return ProcessCreation$class.apply(this, command, cwd, extraEnv);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(String command, Option cwd, Seq extraEnv) {
/* 40 */     return ProcessCreation$class.apply(this, command, cwd, extraEnv);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(Seq command, Option cwd, Seq extraEnv) {
/* 40 */     return ProcessCreation$class.apply(this, command, cwd, extraEnv);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(java.lang.ProcessBuilder builder) {
/* 40 */     return ProcessCreation$class.apply(this, builder);
/*    */   }
/*    */   
/*    */   public ProcessBuilder.FileBuilder apply(File file) {
/* 40 */     return ProcessCreation$class.apply(this, file);
/*    */   }
/*    */   
/*    */   public ProcessBuilder.URLBuilder apply(URL url) {
/* 40 */     return ProcessCreation$class.apply(this, url);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(Elem command) {
/* 40 */     return ProcessCreation$class.apply(this, command);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(boolean value) {
/* 40 */     return ProcessCreation$class.apply(this, value);
/*    */   }
/*    */   
/*    */   public ProcessBuilder apply(String name, Function0 exitValue) {
/* 40 */     return ProcessCreation$class.apply(this, name, exitValue);
/*    */   }
/*    */   
/*    */   public <T> Seq<ProcessBuilder.Source> applySeq(Seq builders, Function1 convert) {
/* 40 */     return ProcessCreation$class.applySeq(this, builders, convert);
/*    */   }
/*    */   
/*    */   public ProcessBuilder cat(ProcessBuilder.Source file, Seq files) {
/* 40 */     return ProcessCreation$class.cat(this, file, files);
/*    */   }
/*    */   
/*    */   public ProcessBuilder cat(Seq files) {
/* 40 */     return ProcessCreation$class.cat(this, files);
/*    */   }
/*    */   
/*    */   private ProcessImpl.Spawn$ Spawn$lzycompute() {
/* 40 */     synchronized (this) {
/* 40 */       if (this.Spawn$module == null)
/* 40 */         this.Spawn$module = new ProcessImpl.Spawn$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/sys/process/Process$}} */
/* 40 */       return this.Spawn$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public ProcessImpl.Spawn$ Spawn() {
/* 40 */     return (this.Spawn$module == null) ? Spawn$lzycompute() : this.Spawn$module;
/*    */   }
/*    */   
/*    */   private ProcessImpl.Future$ Future$lzycompute() {
/* 40 */     synchronized (this) {
/* 40 */       if (this.Future$module == null)
/* 40 */         this.Future$module = new ProcessImpl.Future$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/sys/process/Process$}} */
/* 40 */       return this.Future$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public ProcessImpl.Future$ Future() {
/* 40 */     return (this.Future$module == null) ? Future$lzycompute() : this.Future$module;
/*    */   }
/*    */   
/*    */   private Process$() {
/* 40 */     MODULE$ = this;
/* 40 */     ProcessImpl$class.$init$(this);
/* 40 */     ProcessCreation$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\Process$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */