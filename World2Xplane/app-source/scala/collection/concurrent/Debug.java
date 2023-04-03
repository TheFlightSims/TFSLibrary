/*      */ package scala.collection.concurrent;
/*      */ 
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import scala.Console$;
/*      */ import scala.Serializable;
/*      */ import scala.reflect.ScalaSignature;
/*      */ import scala.runtime.AbstractFunction1;
/*      */ import scala.runtime.BoxedUnit;
/*      */ 
/*      */ @ScalaSignature(bytes = "\006\001M:a!\001\002\t\002\tA\021!\002#fEV<'BA\002\005\003)\031wN\\2veJ,g\016\036\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f\007CA\005\013\033\005\021aAB\006\003\021\003\021ABA\003EK\n,xm\005\002\013\033A\021abD\007\002\r%\021\001C\002\002\007\003:L(+\0324\t\013IQA\021\001\013\002\rqJg.\033;?\007\001!\022\001\003\005\t-)A)\031!C\001/\005IAn\\4ck\0324WM]\013\0021A\031\021dH\007\016\003iQ!aA\016\013\005qi\022\001B;uS2T\021AH\001\005U\0064\030-\003\002!5\t)2i\0348dkJ\024XM\034;MS:\\W\rZ)vKV,\007\002\003\022\013\021\003\005\013\025\002\r\002\0251|wMY;gM\026\024\b\005C\003%\025\021\005Q%A\002m_\036$\"AJ\025\021\00599\023B\001\025\007\005\035\021un\0347fC:DQAK\022A\0025\t\021a\035\005\006Y)!\t!L\001\006M2,8\017\033\013\002]A\021abL\005\003a\031\021A!\0268ji\")!G\003C\001[\005)1\r\\3be\002")
/*      */ public final class Debug {
/*      */   public static void clear() {
/*      */     Debug$.MODULE$.clear();
/*      */   }
/*      */   
/*      */   public static void flush() {
/*      */     Debug$.MODULE$.flush();
/*      */   }
/*      */   
/*      */   public static boolean log(Object paramObject) {
/*      */     return Debug$.MODULE$.log(paramObject);
/*      */   }
/*      */   
/*      */   public static ConcurrentLinkedQueue<Object> logbuffer() {
/*      */     return Debug$.MODULE$.logbuffer();
/*      */   }
/*      */   
/*      */   public static class Debug$$anonfun$flush$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final void apply(Object s) {
/* 1074 */       Console$.MODULE$.out().println(s.toString());
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\Debug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */