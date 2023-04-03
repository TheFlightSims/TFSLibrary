/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ 
/*     */ public final class processInternal$ {
/*     */   public static final processInternal$ MODULE$;
/*     */   
/*     */   private final boolean processDebug;
/*     */   
/*     */   private processInternal$() {
/* 226 */     MODULE$ = this;
/* 227 */     this.processDebug = scala.sys.package$.MODULE$.props().contains("scala.process.debug");
/* 228 */     dbg((Seq<Object>)scala.Predef$.MODULE$.genericWrapArray(new Object[] { "Initializing process package." }));
/*     */   }
/*     */   
/*     */   public final boolean processDebug() {
/*     */     return this.processDebug;
/*     */   }
/*     */   
/*     */   public <T> PartialFunction<Throwable, T> onInterrupt(Function0 handler) {
/* 241 */     return (PartialFunction<Throwable, T>)new processInternal$$anonfun$onInterrupt$1(handler);
/*     */   }
/*     */   
/*     */   public static class processInternal$$anonfun$onInterrupt$1 extends AbstractPartialFunction<Throwable, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function0 handler$1;
/*     */     
/*     */     public processInternal$$anonfun$onInterrupt$1(Function0 handler$1) {}
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       boolean bool;
/* 241 */       if (x1 instanceof InterruptedException) {
/* 241 */         bool = true;
/*     */       } else {
/* 241 */         bool = false;
/*     */       } 
/* 241 */       return bool;
/*     */     }
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*     */       Object object;
/* 241 */       if (x1 instanceof InterruptedException) {
/* 241 */         object = this.handler$1.apply();
/*     */       } else {
/* 241 */         object = default.apply(x1);
/*     */       } 
/* 241 */       return (B1)object;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> PartialFunction<Throwable, T> ioFailure(Function1 handler) {
/* 245 */     return (PartialFunction<Throwable, T>)new processInternal$$anonfun$ioFailure$1(handler);
/*     */   }
/*     */   
/*     */   public static class processInternal$$anonfun$ioFailure$1 extends AbstractPartialFunction<Throwable, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 handler$2;
/*     */     
/*     */     public processInternal$$anonfun$ioFailure$1(Function1 handler$2) {}
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x2) {
/*     */       boolean bool;
/* 245 */       if (x2 instanceof IOException) {
/* 245 */         bool = true;
/*     */       } else {
/* 245 */         bool = false;
/*     */       } 
/* 245 */       return bool;
/*     */     }
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x2, Function1 default) {
/*     */       Object object;
/* 245 */       if (x2 instanceof IOException) {
/* 245 */         IOException iOException = (IOException)x2;
/* 245 */         object = this.handler$2.apply(iOException);
/*     */       } else {
/* 245 */         object = default.apply(x2);
/*     */       } 
/* 245 */       return (B1)object;
/*     */     }
/*     */   }
/*     */   
/*     */   public void dbg(Seq msgs) {
/* 249 */     if (processDebug())
/* 250 */       scala.Console$.MODULE$.println((new StringBuilder()).append("[process] ").append(msgs.mkString(" ")).toString()); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\processInternal$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */