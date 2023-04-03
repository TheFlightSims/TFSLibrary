/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function9;
/*    */ import scala.Tuple9;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001m2Q!\001\002\002\002\035\021\021#\0212tiJ\f7\r\036$v]\016$\030n\0348:\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001!F\006\t%qy\"%\n\025,]E\"4c\001\001\n\033A\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\031)q\001c\007\020\"I\035RS\006M\032\n\005=!!!\003$v]\016$\030n\0348:!\t\t\"\003\004\001\005\rM\001\001R1\001\025\005\t!\026'\005\002\0261A\021!BF\005\003/\021\021qAT8uQ&tw\r\005\002\0133%\021!\004\002\002\004\003:L\bCA\t\035\t\031i\002\001#b\001)\t\021AK\r\t\003#}!a\001\t\001\t\006\004!\"A\001+4!\t\t\"\005\002\004$\001!\025\r\001\006\002\003)R\002\"!E\023\005\r\031\002\001R1\001\025\005\t!V\007\005\002\022Q\0211\021\006\001EC\002Q\021!\001\026\034\021\005EYCA\002\027\001\021\013\007AC\001\002UoA\021\021C\f\003\007_\001A)\031\001\013\003\005QC\004CA\t2\t\031\021\004\001#b\001)\t\021A+\017\t\003#Q\"a!\016\001\005\006\004!\"!\001*\t\013]\002A\021\001\035\002\rqJg.\033;?)\005I\004\003\004\036\001!mq\022\005J\024+[A\032T\"\001\002")
/*    */ public abstract class AbstractFunction9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, R>>>>>>>>> curried() {
/* 12 */     return Function9.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, R> tupled() {
/* 12 */     return Function9.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function9.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction9() {
/* 12 */     Function9.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction9.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */