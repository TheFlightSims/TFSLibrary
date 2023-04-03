/*      */ package scala.collection.concurrent;
/*      */ 
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import scala.Function1;
/*      */ import scala.Serializable;
/*      */ import scala.runtime.AbstractFunction1;
/*      */ import scala.runtime.BoxedUnit;
/*      */ 
/*      */ public final class Debug$ {
/*      */   public static final Debug$ MODULE$;
/*      */   
/*      */   private ConcurrentLinkedQueue<Object> logbuffer;
/*      */   
/*      */   private volatile boolean bitmap$0;
/*      */   
/*      */   private Debug$() {
/* 1066 */     MODULE$ = this;
/*      */   }
/*      */   
/*      */   private ConcurrentLinkedQueue logbuffer$lzycompute() {
/* 1069 */     synchronized (this) {
/* 1069 */       if (!this.bitmap$0) {
/* 1069 */         this.logbuffer = new ConcurrentLinkedQueue();
/* 1069 */         this.bitmap$0 = true;
/*      */       } 
/*      */       /* monitor exit ThisExpression{ObjectType{scala/collection/concurrent/Debug$}} */
/* 1069 */       return this.logbuffer;
/*      */     } 
/*      */   }
/*      */   
/*      */   public ConcurrentLinkedQueue<Object> logbuffer() {
/* 1069 */     return this.bitmap$0 ? this.logbuffer : logbuffer$lzycompute();
/*      */   }
/*      */   
/*      */   public boolean log(Object s) {
/* 1071 */     return logbuffer().add(s);
/*      */   }
/*      */   
/*      */   public void flush() {
/* 1074 */     scala.collection.JavaConversions$.MODULE$.asScalaIterator(logbuffer().iterator()).foreach((Function1)new Debug$$anonfun$flush$1());
/* 1075 */     logbuffer().clear();
/*      */   }
/*      */   
/*      */   public static class Debug$$anonfun$flush$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final void apply(Object s) {
/*      */       scala.Console$.MODULE$.out().println(s.toString());
/*      */     }
/*      */   }
/*      */   
/*      */   public void clear() {
/* 1079 */     logbuffer().clear();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\Debug$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */