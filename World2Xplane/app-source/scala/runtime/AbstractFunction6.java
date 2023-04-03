/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function6;
/*    */ import scala.Tuple6;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001I2Q!\001\002\002\002\035\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03487\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001!\006\005\t%qy\"%\n\025,'\r\001\021\"\004\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007#\003\006\017!mq\022\005J\024+\023\tyAAA\005Gk:\034G/[8omA\021\021C\005\007\001\t\031\031\002\001#b\001)\t\021A+M\t\003+a\001\"A\003\f\n\005]!!a\002(pi\"Lgn\032\t\003\025eI!A\007\003\003\007\005s\027\020\005\002\0229\0211Q\004\001EC\002Q\021!\001\026\032\021\005EyBA\002\021\001\021\013\007AC\001\002UgA\021\021C\t\003\007G\001A)\031\001\013\003\005Q#\004CA\t&\t\0311\003\001#b\001)\t\021A+\016\t\003#!\"a!\013\001\t\006\004!\"A\001+7!\t\t2\006\002\004-\001\021\025\r\001\006\002\002%\")a\006\001C\001_\0051A(\0338jiz\"\022\001\r\t\nc\001\0012DH\021%O)j\021A\001")
/*    */ public abstract class AbstractFunction6<T1, T2, T3, T4, T5, T6, R> implements Function6<T1, T2, T3, T4, T5, T6, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>>> curried() {
/* 12 */     return Function6.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple6<T1, T2, T3, T4, T5, T6>, R> tupled() {
/* 12 */     return Function6.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function6.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction6() {
/* 12 */     Function6.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction6.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */