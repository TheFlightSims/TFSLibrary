/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\025aaB\001\003!\003\r\t!\002\002\n\rVt7\r^5p]FR\021aA\001\006g\016\fG.Y\002\001+\r1\021JF\n\003\001\035\001\"\001C\005\016\003\tI!A\003\002\003\r\005s\027PU3g\021\025a\001\001\"\001\016\003\031!\023N\\5uIQ\ta\002\005\002\t\037%\021\001C\001\002\005+:LG\017C\003\023\001\031\0051#A\003baBd\027\020\006\002\025\rB\021QC\006\007\001\t%9\002\001)A\001\n\013\007\001DA\001S#\tIB\004\005\002\t5%\0211D\001\002\b\035>$\b.\0338h!\tAQ$\003\002\037\005\t\031\021I\\=)\021Y\0013%\f\0328y\005\003\"\001C\021\n\005\t\022!aC:qK\016L\027\r\\5{K\022\fTa\t\023+Y-r!!\n\026\017\005\031JS\"A\024\013\005!\"\021A\002\037s_>$h(C\001\004\023\tY#!\001\003V]&$\030\007\002\023&S\r\tTa\t\0300cAr!!J\030\n\005A\022\021a\002\"p_2,\027M\\\031\005I\025J3!M\003$gQ2TG\004\002&i%\021QGA\001\004\023:$\030\007\002\023&S\r\tTa\t\035:wir!!J\035\n\005i\022\021!\002$m_\006$\030\007\002\023&S\r\tTaI\037?\001~r!!\n \n\005}\022\021\001\002'p]\036\fD\001J\023*\007E*1EQ\"F\t:\021QeQ\005\003\t\n\ta\001R8vE2,\027\007\002\023&S\rAQaR\tA\002!\013!A^\031\021\005UIE!\003&\001A\003\005\tR1\001\031\005\t!\026\007\013\004JA1s\005KU\031\006GM\"T*N\031\005I\025J3!M\003${yzu(\r\003%K%\032\021'B\0229sES\024\007\002\023&S\r\tTa\t\"D'\022\013D\001J\023*\007!)Q\013\001C\001-\00691m\\7q_N,WCA,[)\tAF\f\005\003\t\001e#\002CA\013[\t\025YFK1\001\031\005\005\t\005\"B/U\001\004q\026!A4\021\t!\001\021\f\023\025\003)\002\004\"!\0313\016\003\tT!a\031\002\002\025\005tgn\034;bi&|g.\003\002fE\niQO\\:qK\016L\027\r\\5{K\022DQa\032\001\005\002!\fq!\0318e)\",g.\006\002jYR\021!.\034\t\005\021\001A5\016\005\002\026Y\022)1L\032b\0011!)QL\032a\001]B!\001\002\001\013lQ\t1\007\rC\003r\001\021\005#/\001\005u_N#(/\0338h)\005\031\bC\001;z\033\005)(B\001<x\003\021a\027M\\4\013\003a\fAA[1wC&\021!0\036\002\007'R\024\030N\\4\021\t!\001\001\n\006\025\005\001u\f\t\001\005\002b}&\021qP\031\002\021S6\004H.[2ji:{GOR8v]\022\f#!a\001\002]9{\007%[7qY&\034\027\016\036\021wS\026<\b%\031<bS2\f'\r\\3!MJ|W\016\t\023|)Fj\b%\020 !Im\024VP\f")
/*    */ public interface Function1<T1, R> {
/*    */   R apply(T1 paramT1);
/*    */   
/*    */   <A> Function1<A, R> compose(Function1<A, T1> paramFunction1);
/*    */   
/*    */   <A> Function1<T1, A> andThen(Function1<R, A> paramFunction1);
/*    */   
/*    */   String toString();
/*    */   
/*    */   boolean apply$mcZD$sp(double paramDouble);
/*    */   
/*    */   double apply$mcDD$sp(double paramDouble);
/*    */   
/*    */   float apply$mcFD$sp(double paramDouble);
/*    */   
/*    */   int apply$mcID$sp(double paramDouble);
/*    */   
/*    */   long apply$mcJD$sp(double paramDouble);
/*    */   
/*    */   void apply$mcVD$sp(double paramDouble);
/*    */   
/*    */   boolean apply$mcZF$sp(float paramFloat);
/*    */   
/*    */   double apply$mcDF$sp(float paramFloat);
/*    */   
/*    */   float apply$mcFF$sp(float paramFloat);
/*    */   
/*    */   int apply$mcIF$sp(float paramFloat);
/*    */   
/*    */   long apply$mcJF$sp(float paramFloat);
/*    */   
/*    */   void apply$mcVF$sp(float paramFloat);
/*    */   
/*    */   boolean apply$mcZI$sp(int paramInt);
/*    */   
/*    */   double apply$mcDI$sp(int paramInt);
/*    */   
/*    */   float apply$mcFI$sp(int paramInt);
/*    */   
/*    */   int apply$mcII$sp(int paramInt);
/*    */   
/*    */   long apply$mcJI$sp(int paramInt);
/*    */   
/*    */   void apply$mcVI$sp(int paramInt);
/*    */   
/*    */   boolean apply$mcZJ$sp(long paramLong);
/*    */   
/*    */   double apply$mcDJ$sp(long paramLong);
/*    */   
/*    */   float apply$mcFJ$sp(long paramLong);
/*    */   
/*    */   int apply$mcIJ$sp(long paramLong);
/*    */   
/*    */   long apply$mcJJ$sp(long paramLong);
/*    */   
/*    */   void apply$mcVJ$sp(long paramLong);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcZD$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcDD$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcFD$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcID$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcJD$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcZF$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcDF$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcFF$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcIF$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcJF$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcZI$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcDI$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcFI$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcII$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcJI$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcZJ$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcDJ$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcFJ$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcIJ$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, Object> compose$mcJJ$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcZD$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcDD$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcFD$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcID$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcJD$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcVD$sp(Function1<BoxedUnit, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcZF$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcDF$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcFF$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcIF$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcJF$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcVF$sp(Function1<BoxedUnit, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcZI$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcDI$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcFI$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcII$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcJI$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcVI$sp(Function1<BoxedUnit, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcZJ$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcDJ$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcFJ$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcIJ$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcJJ$sp(Function1<Object, A> paramFunction1);
/*    */   
/*    */   <A> Function1<Object, A> andThen$mcVJ$sp(Function1<BoxedUnit, A> paramFunction1);
/*    */   
/*    */   public class Function1$$anonfun$compose$1 extends AbstractFunction1<A, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function1 g$1;
/*    */     
/*    */     public final R apply(Object x) {
/* 47 */       return (R)this.$outer.apply(this.g$1.apply(x));
/*    */     }
/*    */     
/*    */     public Function1$$anonfun$compose$1(Function1 $outer, Function1 g$1) {}
/*    */   }
/*    */   
/*    */   public class Function1$$anonfun$andThen$1 extends AbstractFunction1<T1, A> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function1 g$2;
/*    */     
/*    */     public final A apply(Object x) {
/* 55 */       return (A)this.g$2.apply(this.$outer.apply(x));
/*    */     }
/*    */     
/*    */     public Function1$$anonfun$andThen$1(Function1 $outer, Function1 g$2) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */