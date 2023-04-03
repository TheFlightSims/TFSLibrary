/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public class Tuple2$mcIJ$sp extends Tuple2<Object, Object> implements Product2$mcIJ$sp {
/*    */   public final int _1$mcI$sp;
/*    */   
/*    */   public final long _2$mcJ$sp;
/*    */   
/*    */   public int _1() {
/* 19 */     return _1$mcI$sp();
/*    */   }
/*    */   
/*    */   public long _2() {
/* 19 */     return _2$mcJ$sp();
/*    */   }
/*    */   
/*    */   public <T1, T2> int copy$default$1() {
/* 19 */     return copy$default$1$mcI$sp();
/*    */   }
/*    */   
/*    */   public <T1, T2> int copy$default$1$mcI$sp() {
/* 19 */     return _1();
/*    */   }
/*    */   
/*    */   public <T1, T2> long copy$default$2() {
/* 19 */     return copy$default$2$mcJ$sp();
/*    */   }
/*    */   
/*    */   public <T1, T2> long copy$default$2$mcJ$sp() {
/* 19 */     return _2();
/*    */   }
/*    */   
/*    */   public boolean specInstance$() {
/* 19 */     return true;
/*    */   }
/*    */   
/*    */   public Tuple2$mcIJ$sp(int _1$mcI$sp, long _2$mcJ$sp) {
/* 19 */     super(null, null);
/* 19 */     Product2$mcIJ$sp$class.$init$(this);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> swap() {
/* 28 */     return swap$mcIJ$sp();
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> swap$mcIJ$sp() {
/* 28 */     return new Tuple2$mcJI$sp(_2(), _1());
/*    */   }
/*    */   
/*    */   public int _1$mcI$sp() {
/*    */     return this._1$mcI$sp;
/*    */   }
/*    */   
/*    */   public long _2$mcJ$sp() {
/*    */     return this._2$mcJ$sp;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple2$mcIJ$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */