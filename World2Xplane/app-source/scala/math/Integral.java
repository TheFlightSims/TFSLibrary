/*    */ package scala.math;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001}4q!\001\002\021\002\007\005qA\001\005J]R,wM]1m\025\t\031A!\001\003nCRD'\"A\003\002\013M\034\027\r\\1\004\001U\021\001bF\n\004\001%\t\002C\001\006\020\033\005Y!B\001\007\016\003\021a\027M\\4\013\0039\tAA[1wC&\021\001c\003\002\007\037\nTWm\031;\021\007I\031R#D\001\003\023\t!\"AA\004Ok6,'/[2\021\005Y9B\002\001\003\0061\001\021\r!\007\002\002)F\021!D\b\t\0037qi\021\001B\005\003;\021\021qAT8uQ&tw\r\005\002\034?%\021\001\005\002\002\004\003:L\b\"\002\022\001\t\003\031\023A\002\023j]&$H\005F\001%!\tYR%\003\002'\t\t!QK\\5u\021\025A\003A\"\001*\003\021\tXo\034;\025\007UQC\006C\003,O\001\007Q#A\001y\021\025is\0051\001\026\003\005I\b\"B\030\001\r\003\001\024a\001:f[R\031Q#\r\032\t\013-r\003\031A\013\t\0135r\003\031A\013\007\tQ\002\001!\016\002\f\023:$Xm\032:bY>\0038o\005\0024mA\021q\007O\007\002\001%\021\021h\005\002\004\037B\034\b\002C\0364\005\003\005\013\021B\013\002\0071D7\017C\003>g\021\005a(\001\004=S:LGO\020\013\003\001\003\"aN\032\t\013mb\004\031A\013\t\013\t\033D\021A\"\002\t\021\"\027N\036\013\003+\021CQ!R!A\002U\t1A\0355t\021\02595\007\"\001I\003!!\003/\032:dK:$HCA\013J\021\025)e\t1\001\026\021\025Y5\007\"\001M\0031!C-\033<%a\026\0248-\0328u)\ti\005\013\005\003\034\035V)\022BA(\005\005\031!V\017\0357fe!)QI\023a\001+!)!\013\001C\"'\006aQn\033(v[\026\024\030nY(qgR\021q\b\026\005\006wE\003\r!F\004\006-\nA\taV\001\t\023:$Xm\032:bYB\021!\003\027\004\006\003\tA\t!W\n\0041jk\006CA\016\\\023\taFA\001\004B]f\024VM\032\t\0037yK!a\030\003\003\031M+'/[1mSj\f'\r\\3\t\013uBF\021A1\025\003]3qa\031-\021\002\007\005AM\001\bFqR\024\030-S7qY&\034\027\016^:\024\005\tT\006\"\002\022c\t\003\031\003\"B4c\t\007A\027\001E5oM&D\030J\034;fOJ\fGn\0249t+\tIg\016\006\002kcR\0211n\034\t\003YN\0022A\005\001n!\t1b\016B\003\031M\n\007\021\004C\003qM\002\017A.A\002ok6DQa\0134A\0025<Qa\035-\t\002Q\f\021\"S7qY&\034\027\016^:\021\005U4X\"\001-\007\013]D\006\022\001=\003\023%k\007\017\\5dSR\0348c\001<[sB\021QO\031\005\006{Y$\ta\037\013\002i\"9Q\020WA\001\n\023q\030a\003:fC\022\024Vm]8mm\026$\022!\003")
/*    */ public interface Integral<T> extends Numeric<T> {
/*    */   T quot(T paramT1, T paramT2);
/*    */   
/*    */   T rem(T paramT1, T paramT2);
/*    */   
/*    */   IntegralOps mkNumericOps(T paramT);
/*    */   
/*    */   public class IntegralOps extends Numeric<T>.Ops {
/*    */     private final T lhs;
/*    */     
/*    */     public IntegralOps(Integral<T> $outer, Object lhs) {
/* 22 */       super($outer, (T)lhs);
/*    */     }
/*    */     
/*    */     public T $div(Object rhs) {
/* 23 */       return scala$math$Integral$IntegralOps$$$outer().quot(this.lhs, (T)rhs);
/*    */     }
/*    */     
/*    */     public T $percent(Object rhs) {
/* 24 */       return scala$math$Integral$IntegralOps$$$outer().rem(this.lhs, (T)rhs);
/*    */     }
/*    */     
/*    */     public Tuple2<T, T> $div$percent(Object rhs) {
/* 25 */       return new Tuple2(scala$math$Integral$IntegralOps$$$outer().quot(this.lhs, (T)rhs), scala$math$Integral$IntegralOps$$$outer().rem(this.lhs, (T)rhs));
/*    */     }
/*    */   }
/*    */   
/*    */   public static abstract class ExtraImplicits$class {
/*    */     public static void $init$(Integral.ExtraImplicits $this) {}
/*    */     
/*    */     public static Integral.IntegralOps infixIntegralOps(Integral.ExtraImplicits $this, Object x, Integral<T> num) {
/* 36 */       return new Integral.IntegralOps(num, (T)x);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Implicits$ implements ExtraImplicits {
/*    */     public static final Implicits$ MODULE$;
/*    */     
/*    */     public <T> Integral<T>.IntegralOps infixIntegralOps(Object x, Integral num) {
/* 38 */       return Integral.ExtraImplicits$class.infixIntegralOps(this, x, num);
/*    */     }
/*    */     
/*    */     public Implicits$() {
/* 38 */       MODULE$ = this;
/* 38 */       Integral.ExtraImplicits$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public static interface ExtraImplicits {
/*    */     <T> Integral<T>.IntegralOps infixIntegralOps(T param1T, Integral<T> param1Integral);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Integral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */