/*    */ package scala.math;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=4q!\001\002\021\002\007\005qA\001\006Ge\006\034G/[8oC2T!a\001\003\002\t5\fG\017\033\006\002\013\005)1oY1mC\016\001QC\001\005\030'\r\001\021\"\005\t\003\025=i\021a\003\006\003\0315\tA\001\\1oO*\ta\"\001\003kCZ\f\027B\001\t\f\005\031y%M[3diB\031!cE\013\016\003\tI!\001\006\002\003\0179+X.\032:jGB\021ac\006\007\001\t\025A\002A1\001\032\005\005!\026C\001\016\037!\tYB$D\001\005\023\tiBAA\004O_RD\027N\\4\021\005my\022B\001\021\005\005\r\te.\037\005\006E\001!\taI\001\007I%t\027\016\036\023\025\003\021\002\"aG\023\n\005\031\"!\001B+oSRDQ\001\013\001\007\002%\n1\001Z5w)\r)\"\006\f\005\006W\035\002\r!F\001\002q\")Qf\na\001+\005\t\021P\002\0030\001\001\001$!\004$sC\016$\030n\0348bY>\0038o\005\002/cA\021!gM\007\002\001%\021Ag\005\002\004\037B\034\b\002\003\034/\005\003\005\013\021B\013\002\0071D7\017C\0039]\021\005\021(\001\004=S:LGO\020\013\003um\002\"A\r\030\t\013Y:\004\031A\013\t\013urC\021\001 \002\t\021\"\027N\036\013\003+}BQ\001\021\037A\002U\t1A\0355t\021\025\021\005\001b\021D\0031i7NT;nKJL7m\0249t)\tQD\tC\0037\003\002\007QcB\003G\005!\005q)\001\006Ge\006\034G/[8oC2\004\"A\005%\007\013\005\021\001\022A%\024\007!SU\n\005\002\034\027&\021A\n\002\002\007\003:L(+\0324\021\005mq\025BA(\005\0051\031VM]5bY&T\030M\0317f\021\025A\004\n\"\001R)\0059eaB*I!\003\r\t\001\026\002\017\013b$(/Y%na2L7-\033;t'\t\021&\nC\003#%\022\0051\005C\003X%\022\r\001,\001\nj]\032L\007P\022:bGRLwN\\1m\037B\034XCA-_)\tQ\026\r\006\002\\?B\021AL\f\t\004%\001i\006C\001\f_\t\025AbK1\001\032\021\025\001g\013q\001]\003\rqW/\034\005\006WY\003\r!X\004\006G\"C\t\001Z\001\n\0236\004H.[2jiN\004\"!\0324\016\003!3Qa\032%\t\002!\024\021\"S7qY&\034\027\016^:\024\007\031T\025\016\005\002f%\")\001H\032C\001WR\tA\rC\004n\021\006\005I\021\0028\002\027I,\027\r\032*fg>dg/\032\013\002\023\001")
/*    */ public interface Fractional<T> extends Numeric<T> {
/*    */   T div(T paramT1, T paramT2);
/*    */   
/*    */   FractionalOps mkNumericOps(T paramT);
/*    */   
/*    */   public class FractionalOps extends Numeric<T>.Ops {
/*    */     private final T lhs;
/*    */     
/*    */     public FractionalOps(Fractional<T> $outer, Object lhs) {
/* 19 */       super($outer, (T)lhs);
/*    */     }
/*    */     
/*    */     public T $div(Object rhs) {
/* 20 */       return scala$math$Fractional$FractionalOps$$$outer().div(this.lhs, (T)rhs);
/*    */     }
/*    */   }
/*    */   
/*    */   public static abstract class ExtraImplicits$class {
/*    */     public static void $init$(Fractional.ExtraImplicits $this) {}
/*    */     
/*    */     public static Fractional.FractionalOps infixFractionalOps(Fractional.ExtraImplicits $this, Object x, Fractional<T> num) {
/* 28 */       return new Fractional.FractionalOps(num, (T)x);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Implicits$ implements ExtraImplicits {
/*    */     public static final Implicits$ MODULE$;
/*    */     
/*    */     public <T> Fractional<T>.FractionalOps infixFractionalOps(Object x, Fractional num) {
/* 30 */       return Fractional.ExtraImplicits$class.infixFractionalOps(this, x, num);
/*    */     }
/*    */     
/*    */     public Implicits$() {
/* 30 */       MODULE$ = this;
/* 30 */       Fractional.ExtraImplicits$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public static interface ExtraImplicits {
/*    */     <T> Fractional<T>.FractionalOps infixFractionalOps(T param1T, Fractional<T> param1Fractional);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Fractional.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */