/*    */ package akka.util;
/*    */ 
/*    */ import java.util.concurrent.locks.ReentrantLock;
/*    */ import scala.Function0;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001M2A!\001\002\003\017\tq!+Z3oiJ\fg\016^$vCJ$'BA\002\005\003\021)H/\0337\013\003\025\tA!Y6lC\016\0011C\001\001\t!\tI\021#D\001\013\025\tYA\"A\003m_\016\\7O\003\002\016\035\005Q1m\0348dkJ\024XM\034;\013\005\ry!\"\001\t\002\t)\fg/Y\005\003%)\021QBU3f]R\024\030M\034;M_\016\\\007\"\002\013\001\t\003)\022A\002\037j]&$h\bF\001\027!\t9\002!D\001\003\021\025I\002\001\"\002\033\003%9\030\016\0365Hk\006\024H-\006\002\034=Q\021AD\013\t\003;ya\001\001B\003 1\t\007\001EA\001U#\t\ts\005\005\002#K5\t1EC\001%\003\025\0318-\0317b\023\t13EA\004O_RD\027N\\4\021\005\tB\023BA\025$\005\r\te.\037\005\007Wa!\t\031\001\027\002\t\t|G-\037\t\004E5b\022B\001\030$\005!a$-\0378b[\026t\004F\001\r1!\t\021\023'\003\0023G\t1\021N\0347j]\026\004")
/*    */ public final class ReentrantGuard extends ReentrantLock {
/*    */   public final <T> T withGuard(Function0 body) {
/* 14 */     lock();
/*    */     try {
/* 15 */       return (T)body.apply();
/*    */     } finally {
/* 15 */       unlock();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\ReentrantGuard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */