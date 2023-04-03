/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction9;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001m4q!\001\002\021\002\007\005QA\001\006Gk:\034G/[8ocAR\021aA\001\006g\016\fG.Y\002\001+11!e\n\0272mm\002UIS(\027'\t\001q\001\005\002\t\0235\t!!\003\002\013\005\t1\021I\\=SK\032DQ\001\004\001\005\0025\ta\001J5oSR$C#\001\b\021\005!y\021B\001\t\003\005\021)f.\033;\t\013I\001a\021A\n\002\013\005\004\b\017\\=\025\027QyB%\013\0304qu\022u\t\024\t\003+Ya\001\001\002\004\030\001\021\025\r\001\007\002\002%F\021\021\004\b\t\003\021iI!a\007\002\003\0179{G\017[5oOB\021\001\"H\005\003=\t\0211!\0218z\021\025\001\023\0031\001\"\003\t1\030\007\005\002\026E\02111\005\001EC\002a\021!\001V\031\t\013\025\n\002\031\001\024\002\005Y\024\004CA\013(\t\031A\003\001#b\0011\t\021AK\r\005\006UE\001\raK\001\003mN\002\"!\006\027\005\r5\002\001R1\001\031\005\t!6\007C\0030#\001\007\001'\001\002wiA\021Q#\r\003\007e\001A)\031\001\r\003\005Q#\004\"\002\033\022\001\004)\024A\001<6!\t)b\007\002\0048\001!\025\r\001\007\002\003)VBQ!O\tA\002i\n!A\036\034\021\005UYDA\002\037\001\021\013\007\001D\001\002Um!)a(\005a\001\005\021ao\016\t\003+\001#a!\021\001\t\006\004A\"A\001+8\021\025\031\025\0031\001E\003\t1\b\b\005\002\026\013\0221a\t\001EC\002a\021!\001\026\035\t\013!\013\002\031A%\002\005YL\004CA\013K\t\031Y\005\001#b\0011\t\021A+\017\005\006\033F\001\rAT\001\004mF\002\004CA\013P\t\031\001\006\001#b\0011\t\031A+\r\031\t\013I\003A\021A*\002\017\r,(O]5fIV\tA\013\005\003\t+\006:\026B\001,\003\005%1UO\\2uS>t\027\007\005\003\t+\032B\006\003\002\005VWe\003B\001C+15B!\001\"V\033\\!\021AQK\017/\021\t!)v(\030\t\005\021U#e\f\005\003\t+&{\006\003\002\005V\035RA#!U1\021\005\t,W\"A2\013\005\021\024\021AC1o]>$\030\r^5p]&\021am\031\002\016k:\034\b/Z2jC2L'0\0323\t\013!\004A\021A5\002\rQ,\b\017\\3e+\005Q\007\003\002\005VWR\001B\002\0037\"M-\002TGO E\023:K!!\034\002\003\017Q+\b\017\\32a!\022q-\031\005\006a\002!\t%]\001\ti>\034FO]5oOR\t!\017\005\002tq6\tAO\003\002vm\006!A.\0318h\025\0059\030\001\0026bm\006L!!\037;\003\rM#(/\0338h!5A\001!\t\024,aURt\bR%O)\001")
/*    */ public interface Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8, T9 paramT9, T10 paramT10);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, R>>>>>>>>>> curried();
/*    */   
/*    */   Function1<Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function10$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, R>>>>>>>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, R>>>>>>>>> apply(Object x1) {
/* 26 */       return (new Function10$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1)).curried();
/*    */     }
/*    */     
/*    */     public Function10$$anonfun$curried$1(Function10 $outer) {}
/*    */     
/*    */     public class Function10$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction9<T2, T3, T4, T5, T6, T7, T8, T9, T10, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2, Object x3, Object x4, Object x5, Object x6, Object x7, Object x8, Object x9, Object x10) {
/* 26 */         return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7, x8, x9, x10);
/*    */       }
/*    */       
/*    */       public Function10$$anonfun$curried$1$$anonfun$apply$1(Function10$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function10$$anonfun$tupled$1 extends AbstractFunction1<Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple10 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7(), x0$1._8(), x0$1._9(), x0$1._10()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function10$$anonfun$tupled$1(Function10 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function10.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */