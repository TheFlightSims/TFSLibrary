/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001]3q!\001\002\021\002\007\005QAA\005Gk:\034G/[8oi)\t1!A\003tG\006d\027m\001\001\026\r\031\021s\005L\031\027'\t\001q\001\005\002\t\0235\t!!\003\002\013\005\t1\021I\\=SK\032DQ\001\004\001\005\0025\ta\001J5oSR$C#\001\b\021\005!y\021B\001\t\003\005\021)f.\033;\t\013I\001a\021A\n\002\013\005\004\b\017\\=\025\013QyB%\013\030\021\005U1B\002\001\003\007/\001!)\031\001\r\003\003I\013\"!\007\017\021\005!Q\022BA\016\003\005\035qu\016\0365j]\036\004\"\001C\017\n\005y\021!aA!os\")\001%\005a\001C\005\021a/\r\t\003+\t\"aa\t\001\t\006\004A\"A\001+2\021\025)\023\0031\001'\003\t1(\007\005\002\026O\0211\001\006\001EC\002a\021!\001\026\032\t\013)\n\002\031A\026\002\005Y\034\004CA\013-\t\031i\003\001#b\0011\t\021Ak\r\005\006_E\001\r\001M\001\003mR\002\"!F\031\005\rI\002\001R1\001\031\005\t!F\007C\0035\001\021\005Q'A\004dkJ\024\030.\0323\026\003Y\002B\001C\034\"s%\021\001H\001\002\n\rVt7\r^5p]F\002B\001C\034'uA!\001bN\026<!\021Aq\007\r\013)\005Mj\004C\001 B\033\005y$B\001!\003\003)\tgN\\8uCRLwN\\\005\003\005~\022Q\"\0368ta\026\034\027.\0317ju\026$\007\"\002#\001\t\003)\025A\002;va2,G-F\001G!\021Aqg\022\013\021\r!A\025EJ\0261\023\tI%A\001\004UkBdW\r\016\025\003\007vBQ\001\024\001\005B5\013\001\002^8TiJLgn\032\013\002\035B\021q\nV\007\002!*\021\021KU\001\005Y\006twMC\001T\003\021Q\027M^1\n\005U\003&AB*ue&tw\rE\004\t\001\00523\006\r\013")
/*    */ public interface Function4<T1, T2, T3, T4, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, R>>>> curried();
/*    */   
/*    */   Function1<Tuple4<T1, T2, T3, T4>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function4$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, R>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, R>>> apply(Object x1) {
/* 26 */       return (Function1<T2, Function1<T3, Function1<T4, R>>>)new Function4$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1);
/*    */     }
/*    */     
/*    */     public Function4$$anonfun$curried$1(Function4 $outer) {}
/*    */     
/*    */     public class Function4$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction1<T2, Function1<T3, Function1<T4, R>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Object x1$1;
/*    */       
/*    */       public final Function1<T3, Function1<T4, R>> apply(Object x2) {
/* 26 */         return (Function1<T3, Function1<T4, R>>)new Function4$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2(this, ($anonfun$curried$1$$anonfun$apply$1)x2);
/*    */       }
/*    */       
/*    */       public Function4$$anonfun$curried$1$$anonfun$apply$1(Function4$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */       
/*    */       public class Function4$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2 extends AbstractFunction1<T3, Function1<T4, R>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Object x2$1;
/*    */         
/*    */         public final Function1<T4, R> apply(Object x3) {
/* 26 */           return (Function1<T4, R>)new Function4$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2$$anonfun$apply$3(this, ($anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2)x3);
/*    */         }
/*    */         
/*    */         public Function4$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2(Function4$$anonfun$curried$1$$anonfun$apply$1 $outer, Object x2$1) {}
/*    */         
/*    */         public class Function4$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2$$anonfun$apply$3 extends AbstractFunction1<T4, R> implements Serializable {
/*    */           public static final long serialVersionUID = 0L;
/*    */           
/*    */           private final Object x3$1;
/*    */           
/*    */           public final R apply(Object x4) {
/* 26 */             return this.$outer.$outer.$outer.$outer.apply(this.$outer.$outer.x1$1, this.$outer.x2$1, this.x3$1, x4);
/*    */           }
/*    */           
/*    */           public Function4$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2$$anonfun$apply$3(Function4$$anonfun$curried$1$$anonfun$apply$1$$anonfun$apply$2 $outer, Object x3$1) {}
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function4$$anonfun$tupled$1 extends AbstractFunction1<Tuple4<T1, T2, T3, T4>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple4 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function4$$anonfun$tupled$1(Function4 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */