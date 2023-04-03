/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction7;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=4q!\001\002\021\002\007\005QAA\005Gk:\034G/[8oq)\t1!A\003tG\006d\027m\001\001\026\025\031\021s\005L\0317w\001+ec\005\002\001\017A\021\001\"C\007\002\005%\021!B\001\002\007\003:L(+\0324\t\0131\001A\021A\007\002\r\021Jg.\033;%)\005q\001C\001\005\020\023\t\001\"A\001\003V]&$\b\"\002\n\001\r\003\031\022!B1qa2LH#\003\013 I%r3\007O\037C!\t)b\003\004\001\005\r]\001AQ1\001\031\005\005\021\026CA\r\035!\tA!$\003\002\034\005\t9aj\034;iS:<\007C\001\005\036\023\tq\"AA\002B]fDQ\001I\tA\002\005\n!A^\031\021\005U\021CAB\022\001\021\013\007\001D\001\002Uc!)Q%\005a\001M\005\021aO\r\t\003+\035\"a\001\013\001\t\006\004A\"A\001+3\021\025Q\023\0031\001,\003\t18\007\005\002\026Y\0211Q\006\001EC\002a\021!\001V\032\t\013=\n\002\031\001\031\002\005Y$\004CA\0132\t\031\021\004\001#b\0011\t\021A\013\016\005\006iE\001\r!N\001\003mV\002\"!\006\034\005\r]\002\001R1\001\031\005\t!V\007C\003:#\001\007!(\001\002wmA\021Qc\017\003\007y\001A)\031\001\r\003\005Q3\004\"\002 \022\001\004y\024A\001<8!\t)\002\t\002\004B\001!\025\r\001\007\002\003)^BQaQ\tA\002\021\013!A\036\035\021\005U)EA\002$\001\021\013\007\001D\001\002Uq!)\001\n\001C\001\023\00691-\036:sS\026$W#\001&\021\t!Y\025%T\005\003\031\n\021\021BR;oGRLwN\\\031\021\t!YeE\024\t\005\021-[s\n\005\003\t\027B\002\006\003\002\005LkE\003B\001C&;%B!\001bS T!\021A1\n\022\013)\005\035+\006C\001,Z\033\0059&B\001-\003\003)\tgN\\8uCRLwN\\\005\0035^\023Q\"\0368ta\026\034\027.\0317ju\026$\007\"\002/\001\t\003i\026A\002;va2,G-F\001_!\021A1j\030\013\021\025!\001\027EJ\0261kizD)\003\002b\005\t1A+\0369mKbB#aW+\t\013\021\004A\021I3\002\021Q|7\013\036:j]\036$\022A\032\t\003O2l\021\001\033\006\003S*\fA\001\\1oO*\t1.\001\003kCZ\f\027BA7i\005\031\031FO]5oOBY\001\002A\021'WA*$h\020#\025\001")
/*    */ public interface Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, R>>>>>>>> curried();
/*    */   
/*    */   Function1<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function8$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, R>>>>>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, R>>>>>>> apply(Object x1) {
/* 26 */       return (new Function8$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1)).curried();
/*    */     }
/*    */     
/*    */     public Function8$$anonfun$curried$1(Function8 $outer) {}
/*    */     
/*    */     public class Function8$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction7<T2, T3, T4, T5, T6, T7, T8, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2, Object x3, Object x4, Object x5, Object x6, Object x7, Object x8) {
/* 26 */         return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7, x8);
/*    */       }
/*    */       
/*    */       public Function8$$anonfun$curried$1$$anonfun$apply$1(Function8$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function8$$anonfun$tupled$1 extends AbstractFunction1<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple8 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7(), x0$1._8()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function8$$anonfun$tupled$1(Function8 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */