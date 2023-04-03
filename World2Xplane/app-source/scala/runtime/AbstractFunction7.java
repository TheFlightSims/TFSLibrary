/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function7;
/*    */ import scala.Tuple7;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U2Q!\001\002\002\002\035\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03488\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001!F\005\t%qy\"%\n\025,]M\031\001!C\007\021\005)YQ\"\001\003\n\0051!!AB!osJ+g\r\005\006\013\035AYb$\t\023(U5J!a\004\003\003\023\031+hn\031;j_:<\004CA\t\023\031\001!aa\005\001\t\006\004!\"A\001+2#\t)\002\004\005\002\013-%\021q\003\002\002\b\035>$\b.\0338h!\tQ\021$\003\002\033\t\t\031\021I\\=\021\005EaBAB\017\001\021\013\007AC\001\002UeA\021\021c\b\003\007A\001A)\031\001\013\003\005Q\033\004CA\t#\t\031\031\003\001#b\001)\t\021A\013\016\t\003#\025\"aA\n\001\t\006\004!\"A\001+6!\t\t\002\006\002\004*\001!\025\r\001\006\002\003)Z\002\"!E\026\005\r1\002\001R1\001\025\005\t!v\007\005\002\022]\0211q\006\001CC\002Q\021\021A\025\005\006c\001!\tAM\001\007y%t\027\016\036 \025\003M\002\"\002\016\001\0217y\tCe\n\026.\033\005\021\001")
/*    */ public abstract class AbstractFunction7<T1, T2, T3, T4, T5, T6, T7, R> implements Function7<T1, T2, T3, T4, T5, T6, T7, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, R>>>>>>> curried() {
/* 12 */     return Function7.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple7<T1, T2, T3, T4, T5, T6, T7>, R> tupled() {
/* 12 */     return Function7.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function7.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction7() {
/* 12 */     Function7.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction7.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */