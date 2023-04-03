/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function11;
/*    */ import scala.Tuple11;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0053Q!\001\002\002\002\035\021!#\0212tiJ\f7\r\036$v]\016$\030n\03482c)\0211\001B\001\beVtG/[7f\025\005)\021!B:dC2\f7\001A\013\016\021IarDI\023)W9\nDg\016\036\024\007\001IQ\002\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032\004bB\003\b\0217y\tCe\n\026.aM2\024(\003\002\020\t\tQa)\0368di&|g.M\031\021\005E\021B\002\001\003\007'\001A)\031\001\013\003\005Q\013\024CA\013\031!\tQa#\003\002\030\t\t9aj\034;iS:<\007C\001\006\032\023\tQBAA\002B]f\004\"!\005\017\005\ru\001\001R1\001\025\005\t!&\007\005\002\022?\0211\001\005\001EC\002Q\021!\001V\032\021\005E\021CAB\022\001\021\013\007AC\001\002UiA\021\021#\n\003\007M\001A)\031\001\013\003\005Q+\004CA\t)\t\031I\003\001#b\001)\t\021AK\016\t\003#-\"a\001\f\001\t\006\004!\"A\001+8!\t\tb\006\002\0040\001!\025\r\001\006\002\003)b\002\"!E\031\005\rI\002\001R1\001\025\005\t!\026\b\005\002\022i\0211Q\007\001EC\002Q\0211\001V\0311!\t\tr\007\002\0049\001!\025\r\001\006\002\004)F\n\004CA\t;\t\031Y\004\001\"b\001)\t\t!\013C\003>\001\021\005a(\001\004=S:LGO\020\013\002Aq\001\t\001\t\034=\005\"sEK\0271gYJT\"\001\002")
/*    */ public abstract class AbstractFunction11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> implements Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, R>>>>>>>>>>> curried() {
/* 12 */     return Function11.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>, R> tupled() {
/* 12 */     return Function11.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function11.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction11() {
/* 12 */     Function11.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction11.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */