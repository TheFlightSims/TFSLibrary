/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction6;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001%4q!\001\002\021\002\007\005QAA\005Gk:\034G/[8oo)\t1!A\003tG\006d\027m\001\001\026\023\031\021s\005L\0317w\00132C\001\001\b!\tA\021\"D\001\003\023\tQ!A\001\004B]f\024VM\032\005\006\031\001!\t!D\001\007I%t\027\016\036\023\025\0039\001\"\001C\b\n\005A\021!\001B+oSRDQA\005\001\007\002M\tQ!\0319qYf$\002\002F\020%S9\032\004(\020\t\003+Ya\001\001\002\004\030\001\021\025\r\001\007\002\002%F\021\021\004\b\t\003\021iI!a\007\002\003\0179{G\017[5oOB\021\001\"H\005\003=\t\0211!\0218z\021\025\001\023\0031\001\"\003\t1\030\007\005\002\026E\02111\005\001EC\002a\021!\001V\031\t\013\025\n\002\031\001\024\002\005Y\024\004CA\013(\t\031A\003\001#b\0011\t\021AK\r\005\006UE\001\raK\001\003mN\002\"!\006\027\005\r5\002\001R1\001\031\005\t!6\007C\0030#\001\007\001'\001\002wiA\021Q#\r\003\007e\001A)\031\001\r\003\005Q#\004\"\002\033\022\001\004)\024A\001<6!\t)b\007\002\0048\001!\025\r\001\007\002\003)VBQ!O\tA\002i\n!A\036\034\021\005UYDA\002\037\001\021\013\007\001D\001\002Um!)a(\005a\001\005\021ao\016\t\003+\001#a!\021\001\t\006\004A\"A\001+8\021\025\031\005\001\"\001E\003\035\031WO\035:jK\022,\022!\022\t\005\021\031\013\003*\003\002H\005\tIa)\0368di&|g.\r\t\005\021\0313\023\n\005\003\t\r.R\005\003\002\005Ga-\003B\001\003$6\031B!\001B\022\036N!\021Aai\020\013)\005\t{\005C\001)T\033\005\t&B\001*\003\003)\tgN\\8uCRLwN\\\005\003)F\023Q\"\0368ta\026\034\027.\0317ju\026$\007\"\002,\001\t\0039\026A\002;va2,G-F\001Y!\021Aa)\027\013\021\023!Q\026EJ\0261kiz\024BA.\003\005\031!V\017\0357fo!\022Qk\024\005\006=\002!\teX\001\ti>\034FO]5oOR\t\001\r\005\002bM6\t!M\003\002dI\006!A.\0318h\025\005)\027\001\0026bm\006L!a\0322\003\rM#(/\0338h!)A\001!\t\024,aURt\b\006")
/*    */ public interface Function7<T1, T2, T3, T4, T5, T6, T7, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, R>>>>>>> curried();
/*    */   
/*    */   Function1<Tuple7<T1, T2, T3, T4, T5, T6, T7>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function7$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, R>>>>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, R>>>>>> apply(Object x1) {
/* 26 */       return (new Function7$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1)).curried();
/*    */     }
/*    */     
/*    */     public Function7$$anonfun$curried$1(Function7 $outer) {}
/*    */     
/*    */     public class Function7$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction6<T2, T3, T4, T5, T6, T7, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2, Object x3, Object x4, Object x5, Object x6, Object x7) {
/* 26 */         return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7);
/*    */       }
/*    */       
/*    */       public Function7$$anonfun$curried$1$$anonfun$apply$1(Function7$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function7$$anonfun$tupled$1 extends AbstractFunction1<Tuple7<T1, T2, T3, T4, T5, T6, T7>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple7 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function7$$anonfun$tupled$1(Function7 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function7.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */