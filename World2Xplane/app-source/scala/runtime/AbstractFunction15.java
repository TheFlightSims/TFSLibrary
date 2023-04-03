/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function15;
/*    */ import scala.Tuple15;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00153Q!\001\002\002\002\035\021!#\0212tiJ\f7\r\036$v]\016$\030n\03482k)\0211\001B\001\beVtG/[7f\025\005)\021!B:dC2\f7\001A\013\022\021IarDI\023)W9\nDg\016\036>\001\01635c\001\001\n\033A\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021%)q\001c\007\020\"I\035RS\006M\0327sqz$)R\005\003\037\021\021!BR;oGRLwN\\\0316!\t\t\"\003\004\001\005\rM\001\001R1\001\025\005\t!\026'\005\002\0261A\021!BF\005\003/\021\021qAT8uQ&tw\r\005\002\0133%\021!\004\002\002\004\003:L\bCA\t\035\t\031i\002\001#b\001)\t\021AK\r\t\003#}!a\001\t\001\t\006\004!\"A\001+4!\t\t\"\005\002\004$\001!\025\r\001\006\002\003)R\002\"!E\023\005\r\031\002\001R1\001\025\005\t!V\007\005\002\022Q\0211\021\006\001EC\002Q\021!\001\026\034\021\005EYCA\002\027\001\021\013\007AC\001\002UoA\021\021C\f\003\007_\001A)\031\001\013\003\005QC\004CA\t2\t\031\021\004\001#b\001)\t\021A+\017\t\003#Q\"a!\016\001\t\006\004!\"a\001+2aA\021\021c\016\003\007q\001A)\031\001\013\003\007Q\013\024\007\005\002\022u\02111\b\001EC\002Q\0211\001V\0313!\t\tR\b\002\004?\001!\025\r\001\006\002\004)F\032\004CA\tA\t\031\t\005\001#b\001)\t\031A+\r\033\021\005E\031EA\002#\001\021\013\007ACA\002UcU\002\"!\005$\005\r\035\003AQ1\001\025\005\005\021\006\"B%\001\t\003Q\025A\002\037j]&$h\bF\001L!Ia\005\001E\016\037C\021:#&\f\0314mebtHQ#\016\003\t\001")
/*    */ public abstract class AbstractFunction15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> implements Function15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, R>>>>>>>>>>>>>>> curried() {
/* 12 */     return Function15.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>, R> tupled() {
/* 12 */     return Function15.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function15.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction15() {
/* 12 */     Function15.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction15.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */