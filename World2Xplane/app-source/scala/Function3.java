/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001E3q!\001\002\021\002\007\005QAA\005Gk:\034G/[8og)\t1!A\003tG\006d\027m\001\001\026\013\031\021s\005\f\f\024\005\0019\001C\001\005\n\033\005\021\021B\001\006\003\005\031\te.\037*fM\")A\002\001C\001\033\0051A%\0338ji\022\"\022A\004\t\003\021=I!\001\005\002\003\tUs\027\016\036\005\006%\0011\taE\001\006CB\004H.\037\013\005)}!\023\006\005\002\026-1\001AAB\f\001\t\013\007\001DA\001S#\tIB\004\005\002\t5%\0211D\001\002\b\035>$\b.\0338h!\tAQ$\003\002\037\005\t\031\021I\\=\t\013\001\n\002\031A\021\002\005Y\f\004CA\013#\t\031\031\003\001#b\0011\t\021A+\r\005\006KE\001\rAJ\001\003mJ\002\"!F\024\005\r!\002\001R1\001\031\005\t!&\007C\003+#\001\0071&\001\002wgA\021Q\003\f\003\007[\001A)\031\001\r\003\005Q\033\004\"B\030\001\t\003\001\024aB2veJLW\rZ\013\002cA!\001BM\0215\023\t\031$AA\005Gk:\034G/[8ocA!\001B\r\0246!\021A!g\013\013)\0059:\004C\001\035<\033\005I$B\001\036\003\003)\tgN\\8uCRLwN\\\005\003ye\022Q\"\0368ta\026\034\027.\0317ju\026$\007\"\002 \001\t\003y\024A\002;va2,G-F\001A!\021A!'\021\013\021\013!\021\025EJ\026\n\005\r\023!A\002+va2,7\007\013\002>o!)a\t\001C!\017\006AAo\\*ue&tw\rF\001I!\tIe*D\001K\025\tYE*\001\003mC:<'\"A'\002\t)\fg/Y\005\003\037*\023aa\025;sS:<\007C\002\005\001C\031ZC\003")
/*    */ public interface Function3<T1, T2, T3, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, R>>> curried();
/*    */   
/*    */   Function1<Tuple3<T1, T2, T3>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function3$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, R>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, R>> apply(Object x1) {
/* 26 */       return (Function1<T2, Function1<T3, R>>)new Function3$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1);
/*    */     }
/*    */     
/*    */     public Function3$$anonfun$curried$1(Function3 $outer) {}
/*    */     
/*    */     public class Function3$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction1<T2, Function1<T3, R>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Object x1$1;
/*    */       
/*    */       public final Function1<T3, R> apply(Object x2) {
/* 26 */         return (Function1<T3, R>)new Function3$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2(this, ($anonfun$curried$1$$anonfun$apply$1)x2);
/*    */       }
/*    */       
/*    */       public Function3$$anonfun$curried$1$$anonfun$apply$1(Function3$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */       
/*    */       public class Function3$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2 extends AbstractFunction1<T3, R> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         private final Object x2$1;
/*    */         
/*    */         public final R apply(Object x3) {
/* 26 */           return this.$outer.$outer.$outer.apply(this.$outer.x1$1, this.x2$1, x3);
/*    */         }
/*    */         
/*    */         public Function3$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2(Function3$$anonfun$curried$1$$anonfun$apply$1 $outer, Object x2$1) {}
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function3$$anonfun$tupled$1 extends AbstractFunction1<Tuple3<T1, T2, T3>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple3 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function3$$anonfun$tupled$1(Function3 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */