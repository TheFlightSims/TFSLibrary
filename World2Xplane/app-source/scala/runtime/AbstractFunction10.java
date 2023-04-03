/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function10;
/*    */ import scala.Tuple10;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y2Q!\001\002\002\002\035\021!#\0212tiJ\f7\r\036$v]\016$\030n\03482a)\0211\001B\001\beVtG/[7f\025\005)\021!B:dC2\f7\001A\013\r\021IarDI\023)W9\nDgN\n\004\001%i\001C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMBi!B\004\t\034=\005\"sEK\0271gYJ!a\004\003\003\025\031+hn\031;j_:\f\004\007\005\002\022%1\001AAB\n\001\021\013\007AC\001\002UcE\021Q\003\007\t\003\025YI!a\006\003\003\0179{G\017[5oOB\021!\"G\005\0035\021\0211!\0218z!\t\tB\004\002\004\036\001!\025\r\001\006\002\003)J\002\"!E\020\005\r\001\002\001R1\001\025\005\t!6\007\005\002\022E\02111\005\001EC\002Q\021!\001\026\033\021\005E)CA\002\024\001\021\013\007AC\001\002UkA\021\021\003\013\003\007S\001A)\031\001\013\003\005Q3\004CA\t,\t\031a\003\001#b\001)\t\021Ak\016\t\003#9\"aa\f\001\t\006\004!\"A\001+9!\t\t\022\007\002\0043\001!\025\r\001\006\002\003)f\002\"!\005\033\005\rU\002\001R1\001\025\005\r!\026\007\r\t\003#]\"a\001\017\001\005\006\004!\"!\001*\t\013i\002A\021A\036\002\rqJg.\033;?)\005a\004#D\037\001!mq\022\005J\024+[A\032d'D\001\003\001")
/*    */ public abstract class AbstractFunction10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> implements Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, R>>>>>>>>>> curried() {
/* 12 */     return Function10.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>, R> tupled() {
/* 12 */     return Function10.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function10.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction10() {
/* 12 */     Function10.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction10.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */