/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction10;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\raaB\001\003!\003\r\t!\002\002\013\rVt7\r^5p]F\n$\"A\002\002\013M\034\027\r\\1\004\001UiaAI\024-cYZ\004)\022&P)Z\031\"\001A\004\021\005!IQ\"\001\002\n\005)\021!AB!osJ+g\rC\003\r\001\021\005Q\"\001\004%S:LG\017\n\013\002\035A\021\001bD\005\003!\t\021A!\0268ji\")!\003\001D\001'\005)\021\r\0359msRaAc\b\023*]MBTHQ$M#B\021QC\006\007\001\t\0319\002\001\"b\0011\t\t!+\005\002\0329A\021\001BG\005\0037\t\021qAT8uQ&tw\r\005\002\t;%\021aD\001\002\004\003:L\b\"\002\021\022\001\004\t\023A\001<2!\t)\"\005\002\004$\001!\025\r\001\007\002\003)FBQ!J\tA\002\031\n!A\036\032\021\005U9CA\002\025\001\021\013\007\001D\001\002Ue!)!&\005a\001W\005\021ao\r\t\003+1\"a!\f\001\t\006\004A\"A\001+4\021\025y\023\0031\0011\003\t1H\007\005\002\026c\0211!\007\001EC\002a\021!\001\026\033\t\013Q\n\002\031A\033\002\005Y,\004CA\0137\t\0319\004\001#b\0011\t\021A+\016\005\006sE\001\rAO\001\003mZ\002\"!F\036\005\rq\002\001R1\001\031\005\t!f\007C\003?#\001\007q(\001\002woA\021Q\003\021\003\007\003\002A)\031\001\r\003\005Q;\004\"B\"\022\001\004!\025A\001<9!\t)R\t\002\004G\001!\025\r\001\007\002\003)bBQ\001S\tA\002%\013!A^\035\021\005UQEAB&\001\021\013\007\001D\001\002Us!)Q*\005a\001\035\006\031a/\r\031\021\005UyEA\002)\001\021\013\007\001DA\002UcABQAU\tA\002M\0131A^\0312!\t)B\013\002\004V\001!\025\r\001\007\002\004)F\n\004\"B,\001\t\003A\026aB2veJLW\rZ\013\0023B!\001BW\021]\023\tY&AA\005Gk:\034G/[8ocA!\001B\027\024^!\021A!l\0130\021\t!Q\006g\030\t\005\021i+\004\r\005\003\t5j\n\007\003\002\005[\t\004B\001\003.EGB!\001BW%e!\021A!LT3\021\t!Q6\013\006\025\003-\036\004\"\001[6\016\003%T!A\033\002\002\025\005tgn\034;bi&|g.\003\002mS\niQO\\:qK\016L\027\r\\5{K\022DQA\034\001\005\002=\fa\001^;qY\026$W#\0019\021\t!Q\026\017\006\t\016\021I\fce\013\0316u}\"\025JT*\n\005M\024!a\002+va2,\027'\r\025\003[\036DQA\036\001\005B]\f\001\002^8TiJLgn\032\013\002qB\021\021P`\007\002u*\0211\020`\001\005Y\006twMC\001~\003\021Q\027M^1\n\005}T(AB*ue&tw\r\005\b\t\001\00523\006M\033;\021Kej\025\013")
/*    */ public interface Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8, T9 paramT9, T10 paramT10, T11 paramT11);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, R>>>>>>>>>>> curried();
/*    */   
/*    */   Function1<Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function11$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, R>>>>>>>>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, R>>>>>>>>>> apply(Object x1) {
/* 26 */       return (new Function11$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1)).curried();
/*    */     }
/*    */     
/*    */     public Function11$$anonfun$curried$1(Function11 $outer) {}
/*    */     
/*    */     public class Function11$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction10<T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2, Object x3, Object x4, Object x5, Object x6, Object x7, Object x8, Object x9, Object x10, Object x11) {
/* 26 */         return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11);
/*    */       }
/*    */       
/*    */       public Function11$$anonfun$curried$1$$anonfun$apply$1(Function11$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function11$$anonfun$tupled$1 extends AbstractFunction1<Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple11 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7(), x0$1._8(), x0$1._9(), x0$1._10(), x0$1._11()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function11$$anonfun$tupled$1(Function11 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function11.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */