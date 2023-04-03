/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction5;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\r4q!\001\002\021\002\007\005QAA\005Gk:\034G/[8om)\t1!A\003tG\006d\027m\001\001\026\021\031\021s\005L\0317wY\031\"\001A\004\021\005!IQ\"\001\002\n\005)\021!AB!osJ+g\rC\003\r\001\021\005Q\"\001\004%S:LG\017\n\013\002\035A\021\001bD\005\003!\t\021A!\0268ji\")!\003\001D\001'\005)\021\r\0359msR9Ac\b\023*]MB\004CA\013\027\031\001!aa\006\001\005\006\004A\"!\001*\022\005ea\002C\001\005\033\023\tY\"AA\004O_RD\027N\\4\021\005!i\022B\001\020\003\005\r\te.\037\005\006AE\001\r!I\001\003mF\002\"!\006\022\005\r\r\002\001R1\001\031\005\t!\026\007C\003&#\001\007a%\001\002weA\021Qc\n\003\007Q\001A)\031\001\r\003\005Q\023\004\"\002\026\022\001\004Y\023A\001<4!\t)B\006\002\004.\001!\025\r\001\007\002\003)NBQaL\tA\002A\n!A\036\033\021\005U\tDA\002\032\001\021\013\007\001D\001\002Ui!)A'\005a\001k\005\021a/\016\t\003+Y\"aa\016\001\t\006\004A\"A\001+6\021\025I\024\0031\001;\003\t1h\007\005\002\026w\0211A\b\001EC\002a\021!\001\026\034\t\013y\002A\021A \002\017\r,(O]5fIV\t\001\t\005\003\t\003\006\032\025B\001\"\003\005%1UO\\2uS>t\027\007\005\003\t\003\032\"\005\003\002\005BW\025\003B\001C!1\rB!\001\"Q\033H!\021A\021I\017\013)\005uJ\005C\001&N\033\005Y%B\001'\003\003)\tgN\\8uCRLwN\\\005\003\035.\023Q\"\0368ta\026\034\027.\0317ju\026$\007\"\002)\001\t\003\t\026A\002;va2,G-F\001S!\021A\021i\025\013\021\021!!\026EJ\0261kiJ!!\026\002\003\rQ+\b\017\\37Q\ty\025\nC\003Y\001\021\005\023,\001\005u_N#(/\0338h)\005Q\006CA.a\033\005a&BA/_\003\021a\027M\\4\013\003}\013AA[1wC&\021\021\r\030\002\007'R\024\030N\\4\021\023!\001\021EJ\0261ki\"\002")
/*    */ public interface Function6<T1, T2, T3, T4, T5, T6, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>>> curried();
/*    */   
/*    */   Function1<Tuple6<T1, T2, T3, T4, T5, T6>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function6$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>> apply(Object x1) {
/* 26 */       return (new Function6$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1)).curried();
/*    */     }
/*    */     
/*    */     public Function6$$anonfun$curried$1(Function6 $outer) {}
/*    */     
/*    */     public class Function6$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction5<T2, T3, T4, T5, T6, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2, Object x3, Object x4, Object x5, Object x6) {
/* 26 */         return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6);
/*    */       }
/*    */       
/*    */       public Function6$$anonfun$curried$1$$anonfun$apply$1(Function6$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function6$$anonfun$tupled$1 extends AbstractFunction1<Tuple6<T1, T2, T3, T4, T5, T6>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple6 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function6$$anonfun$tupled$1(Function6 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function6.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */