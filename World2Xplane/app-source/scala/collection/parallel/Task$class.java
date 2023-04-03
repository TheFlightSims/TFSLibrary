/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.util.control.BreakControl;
/*    */ import scala.util.control.Breaks;
/*    */ import scala.util.control.Breaks$;
/*    */ 
/*    */ public abstract class Task$class {
/*    */   public static Object repr(Task $this) {
/* 26 */     return $this;
/*    */   }
/*    */   
/*    */   public static void merge(Task $this, Object that) {}
/*    */   
/*    */   public static void $init$(Task $this) {
/* 47 */     $this.throwable_$eq(null);
/*    */   }
/*    */   
/*    */   public static void forwardThrowable(Task $this) {
/* 48 */     if ($this.throwable() == null)
/*    */       return; 
/* 48 */     throw $this.throwable();
/*    */   }
/*    */   
/*    */   public static void tryLeaf(Task<R, Tp> $this, Option lastres) {
/*    */     try {
/* 53 */       Task$$anonfun$tryLeaf$1 task$$anonfun$tryLeaf$1 = new Task$$anonfun$tryLeaf$1($this, (Task<R, Tp>)lastres);
/* 53 */       Breaks$ breaks$ = Breaks$.MODULE$;
/* 56 */       Task$$anonfun$tryLeaf$2 task$$anonfun$tryLeaf$2 = 
/* 57 */         new Task$$anonfun$tryLeaf$2($this);
/*    */       Object object = new Object((Breaks)breaks$, (Function0)task$$anonfun$tryLeaf$1);
/*    */       try {
/*    */       
/*    */       } catch (BreakControl breakControl2) {
/*    */         BreakControl breakControl1;
/*    */         if ((breakControl1 = null) != object.$outer.scala$util$control$Breaks$$breakException())
/*    */           throw breakControl1; 
/*    */         task$$anonfun$tryLeaf$2.$outer.signalAbort();
/*    */       } 
/*    */     } catch (Exception exception) {
/* 61 */       $this.result_$eq($this.result());
/* 62 */       $this.throwable_$eq(exception);
/* 63 */       $this.signalAbort();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void tryMerge(Task $this, Object t) {
/* 68 */     Task<?, ?> that = (Task)t;
/* 69 */     $this.result();
/* 71 */     if ($this.throwable() == null && that.throwable() == null)
/* 71 */       $this.merge(t); 
/* 72 */     $this.mergeThrowables(that);
/*    */   }
/*    */   
/*    */   private static void checkMerge(Task $this, Task that) {
/* 76 */     if ($this.throwable() == null && that.throwable() == null && ($this.result() == null || that.result() == null)) {
/* 77 */       Predef$.MODULE$.println((new StringBuilder()).append("This: ").append($this).append(", thr=").append($this.throwable()).append("; merged with ").append(that).append(", thr=").append(that.throwable()).toString());
/* 78 */     } else if ($this.throwable() != null || that.throwable() != null) {
/* 79 */       Predef$.MODULE$.println((new StringBuilder()).append("merging this: ").append($this).append(" with thr: ").append($this.throwable()).append(" with ").append(that).append(", thr=").append(that.throwable()).toString());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void mergeThrowables(Task $this, Task that) {
/* 84 */     if ($this.throwable() == null || that.throwable() == null) {
/* 87 */       if (that.throwable() == null) {
/* 88 */         $this.throwable_$eq($this.throwable());
/*    */       } else {
/*    */         $this.throwable_$eq(that.throwable());
/*    */       } 
/*    */     } else {
/*    */       $this.throwable_$eq(package$.MODULE$.throwable2ops($this.throwable()).alongWith(that.throwable()));
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void signalAbort(Task $this) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\Task$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */