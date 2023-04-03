/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function4;
/*    */ import scala.Tuple4;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00112Q!\001\002\002\002\035\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03485\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001!\006\004\t%qy\"%J\n\004\001%i\001C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB9!B\004\t\034=\005\"\023BA\b\005\005%1UO\\2uS>tG\007\005\002\022%1\001AAB\n\001\021\013\007AC\001\002UcE\021Q\003\007\t\003\025YI!a\006\003\003\0179{G\017[5oOB\021!\"G\005\0035\021\0211!\0218z!\t\tB\004\002\004\036\001!\025\r\001\006\002\003)J\002\"!E\020\005\r\001\002\001R1\001\025\005\t!6\007\005\002\022E\02111\005\001EC\002Q\021!\001\026\033\021\005E)CA\002\024\001\t\013\007ACA\001S\021\025A\003\001\"\001*\003\031a\024N\\5u}Q\t!\006E\004,\001AYb$\t\023\016\003\t\001")
/*    */ public abstract class AbstractFunction4<T1, T2, T3, T4, R> implements Function4<T1, T2, T3, T4, R> {
/*    */   public Function1<T1, Function1<T2, Function1<T3, Function1<T4, R>>>> curried() {
/* 12 */     return Function4.class.curried(this);
/*    */   }
/*    */   
/*    */   public Function1<Tuple4<T1, T2, T3, T4>, R> tupled() {
/* 12 */     return Function4.class.tupled(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 12 */     return Function4.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractFunction4() {
/* 12 */     Function4.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractFunction4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */