/*     */ package scala.collection;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ public final class DebugUtils$ {
/*     */   public static final DebugUtils$ MODULE$;
/*     */   
/*     */   private DebugUtils$() {
/*  95 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public scala.runtime.Nothing$ unsupported(String msg) {
/*  96 */     throw new UnsupportedOperationException(msg);
/*     */   }
/*     */   
/*     */   public scala.runtime.Nothing$ noSuchElement(String msg) {
/*  97 */     throw new NoSuchElementException(msg);
/*     */   }
/*     */   
/*     */   public scala.runtime.Nothing$ indexOutOfBounds(int index) {
/*  98 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(index).toString());
/*     */   }
/*     */   
/*     */   public scala.runtime.Nothing$ illegalArgument(String msg) {
/*  99 */     throw new IllegalArgumentException(msg);
/*     */   }
/*     */   
/*     */   public String buildString(Function1 closure) {
/* 102 */     ObjectRef output = new ObjectRef("");
/* 103 */     closure.apply(new DebugUtils$$anonfun$buildString$1(output));
/* 105 */     return (String)output.elem;
/*     */   }
/*     */   
/*     */   public static class DebugUtils$$anonfun$buildString$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef output$1;
/*     */     
/*     */     public final void apply(Object x$1) {
/*     */       this.output$1.elem = (new StringBuilder()).append(this.output$1.elem).append(scala.runtime.StringAdd$.MODULE$.$plus$extension(scala.Predef$.MODULE$.any2stringadd(x$1), "\n")).toString();
/*     */     }
/*     */     
/*     */     public DebugUtils$$anonfun$buildString$1(ObjectRef output$1) {}
/*     */   }
/*     */   
/*     */   public <T> String arrayString(Object array, int from, int until) {
/* 112 */     return scala.Predef$.MODULE$.refArrayOps((Object[])scala.Predef$.MODULE$.genericArrayOps(scala.Predef$.MODULE$.genericArrayOps(array).slice(from, until)).map((Function1)new DebugUtils$$anonfun$arrayString$1(), scala.Array$.MODULE$.canBuildFrom(scala.reflect.ClassTag$.MODULE$.apply(String.class)))).mkString(" | ");
/*     */   }
/*     */   
/*     */   public static class DebugUtils$$anonfun$arrayString$1 extends AbstractFunction1<T, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Object x0$1) {
/*     */       String str;
/*     */       if (x0$1 == null) {
/*     */         str = "n/a";
/*     */       } else {
/*     */         str = String.valueOf(x0$1);
/*     */       } 
/*     */       return str;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\DebugUtils$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */