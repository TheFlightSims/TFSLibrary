/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function5;
/*    */ import scala.Tuple5;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=2Q!\001\002\002\002\035\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03486\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001!F\004\t%qy\"%\n\025\024\007\001IQ\002\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032\004\002B\003\b\0217y\tCeJ\005\003\037\021\021\021BR;oGRLwN\\\033\021\005E\021B\002\001\003\007'\001A)\031\001\013\003\005Q\013\024CA\013\031!\tQa#\003\002\030\t\t9aj\034;iS:<\007C\001\006\032\023\tQBAA\002B]f\004\"!\005\017\005\ru\001\001R1\001\025\005\t!&\007\005\002\022?\0211\001\005\001EC\002Q\021!\001V\032\021\005E\021CAB\022\001\021\013\007AC\001\002UiA\021\021#\n\003\007M\001A)\031\001\013\003\005Q+\004CA\t)\t\031I\003\001\"b\001)\t\t!\013C\003,\001\021\005A&\001\004=S:LGO\020\013\002[AAa\006\001\t\034=\005\"s%D\001\003\001")
/*    */ public abstract class AbstractFunction5<T1, T2, T3, T4, T5, R> implements Function5<T1, T2, T3, T4, T5, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>>> curried() {
/* 12 */     return Function5.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple5<T1, T2, T3, T4, T5>, R> tupled() {
/* 12 */     return Function5.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function5.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction5() {
/* 12 */     Function5.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction5.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */