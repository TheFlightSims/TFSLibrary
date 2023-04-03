/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction8;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U4q!\001\002\021\002\007\005QAA\005Gk:\034G/[8os)\t1!A\003tG\006d\027m\001\001\026\027\031\021s\005L\0317w\001+%JF\n\003\001\035\001\"\001C\005\016\003\tI!A\003\002\003\r\005s\027PU3g\021\025a\001\001\"\001\016\003\031!\023N\\5uIQ\ta\002\005\002\t\037%\021\001C\001\002\005+:LG\017C\003\023\001\031\0051#A\003baBd\027\020\006\006\025?\021Jcf\r\035>\005\036\003\"!\006\f\r\001\0211q\003\001CC\002a\021\021AU\t\0033q\001\"\001\003\016\n\005m\021!a\002(pi\"Lgn\032\t\003\021uI!A\b\002\003\007\005s\027\020C\003!#\001\007\021%\001\002wcA\021QC\t\003\007G\001A)\031\001\r\003\005Q\013\004\"B\023\022\001\0041\023A\001<3!\t)r\005\002\004)\001!\025\r\001\007\002\003)JBQAK\tA\002-\n!A^\032\021\005UaCAB\027\001\021\013\007\001D\001\002Ug!)q&\005a\001a\005\021a\017\016\t\003+E\"aA\r\001\t\006\004A\"A\001+5\021\025!\024\0031\0016\003\t1X\007\005\002\026m\0211q\007\001EC\002a\021!\001V\033\t\013e\n\002\031\001\036\002\005Y4\004CA\013<\t\031a\004\001#b\0011\t\021AK\016\005\006}E\001\raP\001\003m^\002\"!\006!\005\r\005\003\001R1\001\031\005\t!v\007C\003D#\001\007A)\001\002wqA\021Q#\022\003\007\r\002A)\031\001\r\003\005QC\004\"\002%\022\001\004I\025A\001<:!\t)\"\n\002\004L\001!\025\r\001\007\002\003)fBQ!\024\001\005\0029\013qaY;se&,G-F\001P!\021A\001+\t*\n\005E\023!!\003$v]\016$\030n\03482!\021A\001KJ*\021\t!\0016\006\026\t\005\021A\003T\013\005\003\t!V2\006\003\002\005Qu]\003B\001\003)@1B!\001\002\025#Z!\021A\001+\023\013)\0051[\006C\001/`\033\005i&B\0010\003\003)\tgN\\8uCRLwN\\\005\003Av\023Q\"\0368ta\026\034\027.\0317ju\026$\007\"\0022\001\t\003\031\027A\002;va2,G-F\001e!\021A\001+\032\013\021\027!1\027EJ\0261kizD)S\005\003O\n\021a\001V;qY\026L\004FA1\\\021\025Q\007\001\"\021l\003!!xn\025;sS:<G#\0017\021\0055\024X\"\0018\013\005=\004\030\001\0027b]\036T\021!]\001\005U\0064\030-\003\002t]\n11\013\036:j]\036\004B\002\003\001\"M-\002TGO E\023R\001")
/*    */ public interface Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8, T9 paramT9);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, R>>>>>>>>> curried();
/*    */   
/*    */   Function1<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function9$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, R>>>>>>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, R>>>>>>>> apply(Object x1) {
/* 26 */       return (new Function9$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1)).curried();
/*    */     }
/*    */     
/*    */     public Function9$$anonfun$curried$1(Function9 $outer) {}
/*    */     
/*    */     public class Function9$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction8<T2, T3, T4, T5, T6, T7, T8, T9, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2, Object x3, Object x4, Object x5, Object x6, Object x7, Object x8, Object x9) {
/* 26 */         return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7, x8, x9);
/*    */       }
/*    */       
/*    */       public Function9$$anonfun$curried$1$$anonfun$apply$1(Function9$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function9$$anonfun$tupled$1 extends AbstractFunction1<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple9 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7(), x0$1._8(), x0$1._9()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function9$$anonfun$tupled$1(Function9 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function9.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */