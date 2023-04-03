/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0312A!\001\002\001\017\t!Aj\\2l\025\t\031A!\001\006d_:\034WO\035:f]RT\021!B\001\006g\016\fG.Y\002\001'\t\001\001\002\005\002\n\0255\tA!\003\002\f\t\t1\021I\\=SK\032DQ!\004\001\005\0029\ta\001P5oSRtD#A\b\021\005A\001Q\"\001\002\t\017I\001\001\031!C\001'\005I\021M^1jY\006\024G.Z\013\002)A\021\021\"F\005\003-\021\021qAQ8pY\026\fg\016C\004\031\001\001\007I\021A\r\002\033\0054\030-\0337bE2,w\fJ3r)\tQR\004\005\002\n7%\021A\004\002\002\005+:LG\017C\004\037/\005\005\t\031\001\013\002\007a$\023\007\003\004!\001\001\006K\001F\001\013CZ\f\027\016\\1cY\026\004\003\"\002\022\001\t\003\031\023aB1dcVL'/\032\013\0025!)Q\005\001C\001G\0059!/\0327fCN,\007")
/*    */ public class Lock {
/*    */   private boolean available = true;
/*    */   
/*    */   public boolean available() {
/* 19 */     return this.available;
/*    */   }
/*    */   
/*    */   public void available_$eq(boolean x$1) {
/* 19 */     this.available = x$1;
/*    */   }
/*    */   
/*    */   public synchronized void acquire() {
/*    */     while (true) {
/* 22 */       if (available()) {
/* 23 */         available_$eq(false);
/*    */         return;
/*    */       } 
/*    */       wait();
/*    */     } 
/*    */   }
/*    */   
/*    */   public synchronized void release() {
/* 27 */     available_$eq(true);
/* 28 */     notify();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Lock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */