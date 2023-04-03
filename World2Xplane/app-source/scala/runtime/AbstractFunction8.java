/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function8;
/*    */ import scala.Tuple8;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001a2Q!\001\002\002\002\035\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03489\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001!\006\006\t%qy\"%\n\025,]E\0322\001A\005\016!\tQ1\"D\001\005\023\taAA\001\004B]f\024VM\032\t\f\0259\0012DH\021%O)j\003'\003\002\020\t\tIa)\0368di&|g\016\017\t\003#Ia\001\001\002\004\024\001!\025\r\001\006\002\003)F\n\"!\006\r\021\005)1\022BA\f\005\005\035qu\016\0365j]\036\004\"AC\r\n\005i!!aA!osB\021\021\003\b\003\007;\001A)\031\001\013\003\005Q\023\004CA\t \t\031\001\003\001#b\001)\t\021Ak\r\t\003#\t\"aa\t\001\t\006\004!\"A\001+5!\t\tR\005\002\004'\001!\025\r\001\006\002\003)V\002\"!\005\025\005\r%\002\001R1\001\025\005\t!f\007\005\002\022W\0211A\006\001EC\002Q\021!\001V\034\021\005EqCAB\030\001\021\013\007AC\001\002UqA\021\021#\r\003\007e\001!)\031\001\013\003\003ICQ\001\016\001\005\002U\na\001P5oSRtD#\001\034\021\027]\002\001c\007\020\"I\035RS\006M\007\002\005\001")
/*    */ public abstract class AbstractFunction8<T1, T2, T3, T4, T5, T6, T7, T8, R> implements Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, R>>>>>>>> curried() {
/* 12 */     return Function8.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, R> tupled() {
/* 12 */     return Function8.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function8.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction8() {
/* 12 */     Function8.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */