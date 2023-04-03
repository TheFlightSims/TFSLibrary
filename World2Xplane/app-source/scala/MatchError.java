/*    */ package scala;
/*    */ 
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001!2A!\001\002\003\013\tQQ*\031;dQ\026\023(o\034:\013\003\r\tQa]2bY\006\034\001a\005\002\001\rA\021qa\003\b\003\021%i\021AA\005\003\025\t\tq\001]1dW\006<W-\003\002\r\033\t\001\"+\0368uS6,W\t_2faRLwN\034\006\003\025\tA\001b\004\001\003\002\003\006I\001E\001\004_\nT\007C\001\005\022\023\t\021\"AA\002B]fDQ\001\006\001\005\002U\ta\001P5oSRtDC\001\f\030!\tA\001\001C\003\020'\001\007\001\003\003\005\032\001!\025\r\021\"\003\033\003%y'M[*ue&tw-F\001\034!\ta\022%D\001\036\025\tqr$\001\003mC:<'\"\001\021\002\t)\fg/Y\005\003Eu\021aa\025;sS:<\007\002\003\023\001\021\003\005\013\025B\016\002\025=\024'n\025;sS:<\007\005C\003'\001\021\005s%\001\006hKRlUm]:bO\026$\022a\007")
/*    */ public final class MatchError extends RuntimeException {
/*    */   private final Object obj;
/*    */   
/*    */   private String objString;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   public MatchError(Object obj) {}
/*    */   
/*    */   private String objString$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (!this.bitmap$0) {
/* 26 */         this.objString = 
/*    */           
/* 28 */           (this.obj == null) ? "null" : 
/* 29 */           liftedTree1$1();
/*    */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/MatchError}} */
/*    */       return this.objString;
/*    */     } 
/*    */   }
/*    */   
/*    */   private String objString() {
/*    */     return this.bitmap$0 ? this.objString : objString$lzycompute();
/*    */   }
/*    */   
/*    */   private final String ofClass$1() {
/*    */     return (new StringBuilder()).append("of class ").append(this.obj.getClass().getName()).toString();
/*    */   }
/*    */   
/*    */   private final String liftedTree1$1() {
/*    */     try {
/*    */     
/*    */     } finally {}
/* 29 */     return (
/*    */       
/* 32 */       new StringBuilder()).append("an instance ").append(ofClass$1()).toString();
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 36 */     return objString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\MatchError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */