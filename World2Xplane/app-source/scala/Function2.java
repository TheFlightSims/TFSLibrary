/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005aaB\001\003!\003\r\t!\002\002\n\rVt7\r^5p]JR\021aA\001\006g\016\fG.Y\002\001+\0211\021*\026\f\024\005\0019\001C\001\005\n\033\005\021\021B\001\006\003\005\031\te.\037*fM\")A\002\001C\001\033\0051A%\0338ji\022\"\022A\004\t\003\021=I!\001\005\002\003\tUs\027\016\036\005\006%\0011\taE\001\006CB\004H.\037\013\004)\031\023\006CA\013\027\031\001!\021b\006\001!\002\003%)\031\001\r\003\003I\013\"!\007\017\021\005!Q\022BA\016\003\005\035qu\016\0365j]\036\004\"\001C\017\n\005y\021!aA!os\"Ba\003I\022.e]b\024\t\005\002\tC%\021!E\001\002\fgB,7-[1mSj,G-M\003$I)b3F\004\002&U9\021a%K\007\002O)\021\001\006B\001\007yI|w\016\036 \n\003\rI!a\013\002\002\tUs\027\016^\031\005I\025J3!M\003$]=\n\004G\004\002&_%\021\001GA\001\b\005>|G.Z1oc\021!S%K\0022\013\r\032DGN\033\017\005\025\"\024BA\033\003\003\rIe\016^\031\005I\025J3!M\003$qeZ$H\004\002&s%\021!HA\001\006\r2|\027\r^\031\005I\025J3!M\003${y\002uH\004\002&}%\021qHA\001\005\031>tw-\r\003%K%\032\021'B\022C\007\026#eBA\023D\023\t!%!\001\004E_V\024G.Z\031\005I\025J3\001C\003H#\001\007\001*\001\002wcA\021Q#\023\003\n\025\002\001\013\021!EC\002a\021!\001V\031)\013%\003CJ\024)2\013\r\032D'T\0332\t\021*\023fA\031\006GurtjP\031\005I\025J3!M\003$\005\016\013F)\r\003%K%\032\001\"B*\022\001\004!\026A\001<3!\t)R\013B\005W\001\001\006\t\021#b\0011\t\021AK\r\025\006+\002B&\fX\031\006GM\"\024,N\031\005I\025J3!M\003${yZv(\r\003%K%\032\021'B\022C\007v#\025\007\002\023&S\rAQa\030\001\005\002\001\fqaY;se&,G-F\001b!\021A!\r\0233\n\005\r\024!!\003$v]\016$\030n\03482!\021A!\r\026\013)\005y3\007CA4k\033\005A'BA5\003\003)\tgN\\8uCRLwN\\\005\003W\"\024Q\"\0368ta\026\034\027.\0317ju\026$\007\"B7\001\t\003q\027A\002;va2,G-F\001p!\021A!\r\035\013\021\t!\t\b\nV\005\003e\n\021a\001V;qY\026\024\004F\0017g\021\025)\b\001\"\021w\003!!xn\025;sS:<G#A<\021\005alX\"A=\013\005i\\\030\001\0027b]\036T\021\001`\001\005U\0064\030-\003\002s\n11\013\036:j]\036\004R\001\003\001I)R\001")
/*    */ public interface Function2<T1, T2, R> {
/*    */   R apply(T1 paramT1, T2 paramT2);
/*    */   
/*    */   Function1<T1, Function1<T2, R>> curried();
/*    */   
/*    */   Function1<Tuple2<T1, T2>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   boolean apply$mcZDD$sp(double paramDouble1, double paramDouble2);
/*    */   
/*    */   double apply$mcDDD$sp(double paramDouble1, double paramDouble2);
/*    */   
/*    */   float apply$mcFDD$sp(double paramDouble1, double paramDouble2);
/*    */   
/*    */   int apply$mcIDD$sp(double paramDouble1, double paramDouble2);
/*    */   
/*    */   long apply$mcJDD$sp(double paramDouble1, double paramDouble2);
/*    */   
/*    */   void apply$mcVDD$sp(double paramDouble1, double paramDouble2);
/*    */   
/*    */   boolean apply$mcZDI$sp(double paramDouble, int paramInt);
/*    */   
/*    */   double apply$mcDDI$sp(double paramDouble, int paramInt);
/*    */   
/*    */   float apply$mcFDI$sp(double paramDouble, int paramInt);
/*    */   
/*    */   int apply$mcIDI$sp(double paramDouble, int paramInt);
/*    */   
/*    */   long apply$mcJDI$sp(double paramDouble, int paramInt);
/*    */   
/*    */   void apply$mcVDI$sp(double paramDouble, int paramInt);
/*    */   
/*    */   boolean apply$mcZDJ$sp(double paramDouble, long paramLong);
/*    */   
/*    */   double apply$mcDDJ$sp(double paramDouble, long paramLong);
/*    */   
/*    */   float apply$mcFDJ$sp(double paramDouble, long paramLong);
/*    */   
/*    */   int apply$mcIDJ$sp(double paramDouble, long paramLong);
/*    */   
/*    */   long apply$mcJDJ$sp(double paramDouble, long paramLong);
/*    */   
/*    */   void apply$mcVDJ$sp(double paramDouble, long paramLong);
/*    */   
/*    */   boolean apply$mcZID$sp(int paramInt, double paramDouble);
/*    */   
/*    */   double apply$mcDID$sp(int paramInt, double paramDouble);
/*    */   
/*    */   float apply$mcFID$sp(int paramInt, double paramDouble);
/*    */   
/*    */   int apply$mcIID$sp(int paramInt, double paramDouble);
/*    */   
/*    */   long apply$mcJID$sp(int paramInt, double paramDouble);
/*    */   
/*    */   void apply$mcVID$sp(int paramInt, double paramDouble);
/*    */   
/*    */   boolean apply$mcZII$sp(int paramInt1, int paramInt2);
/*    */   
/*    */   double apply$mcDII$sp(int paramInt1, int paramInt2);
/*    */   
/*    */   float apply$mcFII$sp(int paramInt1, int paramInt2);
/*    */   
/*    */   int apply$mcIII$sp(int paramInt1, int paramInt2);
/*    */   
/*    */   long apply$mcJII$sp(int paramInt1, int paramInt2);
/*    */   
/*    */   void apply$mcVII$sp(int paramInt1, int paramInt2);
/*    */   
/*    */   boolean apply$mcZIJ$sp(int paramInt, long paramLong);
/*    */   
/*    */   double apply$mcDIJ$sp(int paramInt, long paramLong);
/*    */   
/*    */   float apply$mcFIJ$sp(int paramInt, long paramLong);
/*    */   
/*    */   int apply$mcIIJ$sp(int paramInt, long paramLong);
/*    */   
/*    */   long apply$mcJIJ$sp(int paramInt, long paramLong);
/*    */   
/*    */   void apply$mcVIJ$sp(int paramInt, long paramLong);
/*    */   
/*    */   boolean apply$mcZJD$sp(long paramLong, double paramDouble);
/*    */   
/*    */   double apply$mcDJD$sp(long paramLong, double paramDouble);
/*    */   
/*    */   float apply$mcFJD$sp(long paramLong, double paramDouble);
/*    */   
/*    */   int apply$mcIJD$sp(long paramLong, double paramDouble);
/*    */   
/*    */   long apply$mcJJD$sp(long paramLong, double paramDouble);
/*    */   
/*    */   void apply$mcVJD$sp(long paramLong, double paramDouble);
/*    */   
/*    */   boolean apply$mcZJI$sp(long paramLong, int paramInt);
/*    */   
/*    */   double apply$mcDJI$sp(long paramLong, int paramInt);
/*    */   
/*    */   float apply$mcFJI$sp(long paramLong, int paramInt);
/*    */   
/*    */   int apply$mcIJI$sp(long paramLong, int paramInt);
/*    */   
/*    */   long apply$mcJJI$sp(long paramLong, int paramInt);
/*    */   
/*    */   void apply$mcVJI$sp(long paramLong, int paramInt);
/*    */   
/*    */   boolean apply$mcZJJ$sp(long paramLong1, long paramLong2);
/*    */   
/*    */   double apply$mcDJJ$sp(long paramLong1, long paramLong2);
/*    */   
/*    */   float apply$mcFJJ$sp(long paramLong1, long paramLong2);
/*    */   
/*    */   int apply$mcIJJ$sp(long paramLong1, long paramLong2);
/*    */   
/*    */   long apply$mcJJJ$sp(long paramLong1, long paramLong2);
/*    */   
/*    */   void apply$mcVJJ$sp(long paramLong1, long paramLong2);
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcZDD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcDDD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcFDD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcIDD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcJDD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, BoxedUnit>> curried$mcVDD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcZDI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcDDI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcFDI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcIDI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcJDI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, BoxedUnit>> curried$mcVDI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcZDJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcDDJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcFDJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcIDJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcJDJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, BoxedUnit>> curried$mcVDJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcZID$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcDID$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcFID$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcIID$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcJID$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, BoxedUnit>> curried$mcVID$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcZII$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcDII$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcFII$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcIII$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcJII$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, BoxedUnit>> curried$mcVII$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcZIJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcDIJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcFIJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcIIJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcJIJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, BoxedUnit>> curried$mcVIJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcZJD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcDJD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcFJD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcIJD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcJJD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, BoxedUnit>> curried$mcVJD$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcZJI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcDJI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcFJI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcIJI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcJJI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, BoxedUnit>> curried$mcVJI$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcZJJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcDJJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcFJJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcIJJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, Object>> curried$mcJJJ$sp();
/*    */   
/*    */   Function1<Object, Function1<Object, BoxedUnit>> curried$mcVJJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcZDD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcDDD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcFDD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcIDD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcJDD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, BoxedUnit> tupled$mcVDD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcZDI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcDDI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcFDI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcIDI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcJDI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, BoxedUnit> tupled$mcVDI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcZDJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcDDJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcFDJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcIDJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcJDJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, BoxedUnit> tupled$mcVDJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcZID$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcDID$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcFID$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcIID$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcJID$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, BoxedUnit> tupled$mcVID$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcZII$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcDII$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcFII$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcIII$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcJII$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, BoxedUnit> tupled$mcVII$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcZIJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcDIJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcFIJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcIIJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcJIJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, BoxedUnit> tupled$mcVIJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcZJD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcDJD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcFJD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcIJD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcJJD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, BoxedUnit> tupled$mcVJD$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcZJI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcDJI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcFJI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcIJI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcJJI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, BoxedUnit> tupled$mcVJI$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcZJJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcDJJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcFJJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcIJJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, Object> tupled$mcJJJ$sp();
/*    */   
/*    */   Function1<Tuple2<Object, Object>, BoxedUnit> tupled$mcVJJ$sp();
/*    */   
/*    */   public class Function2$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, R>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, R> apply(Object x1) {
/* 45 */       return (Function1<T2, R>)new Function2$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1);
/*    */     }
/*    */     
/*    */     public Function2$$anonfun$curried$1(Function2 $outer) {}
/*    */     
/*    */     public class Function2$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction1<T2, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2) {
/* 45 */         return this.$outer.$outer.apply(this.x1$1, x2);
/*    */       }
/*    */       
/*    */       public Function2$$anonfun$curried$1$$anonfun$apply$1(Function2$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function2$$anonfun$tupled$1 extends AbstractFunction1<Tuple2<T1, T2>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple2 x0$1) {
/* 53 */       if (x0$1 != null)
/* 53 */         return 
/* 54 */           (R)this.$outer.apply(x0$1._1(), x0$1._2()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function2$$anonfun$tupled$1(Function2 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */