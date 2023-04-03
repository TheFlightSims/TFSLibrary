/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function16;
/*    */ import scala.Tuple16;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001A3Q!\001\002\002\002\035\021!#\0212tiJ\f7\r\036$v]\016$\030n\03482m)\0211\001B\001\beVtG/[7f\025\005)\021!B:dC2\f7\001A\013\023\021IarDI\023)W9\nDg\016\036>\001\0163\025jE\002\001\0235\001\"AC\006\016\003\021I!\001\004\003\003\r\005s\027PU3g!MQa\002E\016\037C\021:#&\f\0314mebtHQ#I\023\tyAA\001\006Gk:\034G/[8ocY\002\"!\005\n\r\001\02111\003\001EC\002Q\021!\001V\031\022\005UA\002C\001\006\027\023\t9BAA\004O_RD\027N\\4\021\005)I\022B\001\016\005\005\r\te.\037\t\003#q!a!\b\001\t\006\004!\"A\001+3!\t\tr\004\002\004!\001!\025\r\001\006\002\003)N\002\"!\005\022\005\r\r\002\001R1\001\025\005\t!F\007\005\002\022K\0211a\005\001EC\002Q\021!\001V\033\021\005EACAB\025\001\021\013\007AC\001\002UmA\021\021c\013\003\007Y\001A)\031\001\013\003\005Q;\004CA\t/\t\031y\003\001#b\001)\t\021A\013\017\t\003#E\"aA\r\001\t\006\004!\"A\001+:!\t\tB\007\002\0046\001!\025\r\001\006\002\004)F\002\004CA\t8\t\031A\004\001#b\001)\t\031A+M\031\021\005EQDAB\036\001\021\013\007ACA\002UcI\002\"!E\037\005\ry\002\001R1\001\025\005\r!\026g\r\t\003#\001#a!\021\001\t\006\004!\"a\001+2iA\021\021c\021\003\007\t\002A)\031\001\013\003\007Q\013T\007\005\002\022\r\0221q\t\001EC\002Q\0211\001V\0317!\t\t\022\n\002\004K\001\021\025\r\001\006\002\002%\")A\n\001C\001\033\0061A(\0338jiz\"\022A\024\t\024\037\002\0012DH\021%O)j\003g\r\034:y}\022U\tS\007\002\005\001")
/*    */ public abstract class AbstractFunction16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R> implements Function16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, R>>>>>>>>>>>>>>>> curried() {
/* 12 */     return Function16.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>, R> tupled() {
/* 12 */     return Function16.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function16.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction16() {
/* 12 */     Function16.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction16.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */