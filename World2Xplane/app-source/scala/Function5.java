/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction4;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001u3q!\001\002\021\002\007\005QAA\005Gk:\034G/[8ok)\t1!A\003tG\006d\027m\001\001\026\017\031\021s\005L\0317-M\021\001a\002\t\003\021%i\021AA\005\003\025\t\021a!\0218z%\0264\007\"\002\007\001\t\003i\021A\002\023j]&$H\005F\001\017!\tAq\"\003\002\021\005\t!QK\\5u\021\025\021\002A\"\001\024\003\025\t\007\017\0357z)\031!r\004J\025/gA\021QC\006\007\001\t\0319\002\001\"b\0011\t\t!+\005\002\0329A\021\001BG\005\0037\t\021qAT8uQ&tw\r\005\002\t;%\021aD\001\002\004\003:L\b\"\002\021\022\001\004\t\023A\001<2!\t)\"\005\002\004$\001!\025\r\001\007\002\003)FBQ!J\tA\002\031\n!A\036\032\021\005U9CA\002\025\001\021\013\007\001D\001\002Ue!)!&\005a\001W\005\021ao\r\t\003+1\"a!\f\001\t\006\004A\"A\001+4\021\025y\023\0031\0011\003\t1H\007\005\002\026c\0211!\007\001EC\002a\021!\001\026\033\t\013Q\n\002\031A\033\002\005Y,\004CA\0137\t\0319\004\001#b\0011\t\021A+\016\005\006s\001!\tAO\001\bGV\024(/[3e+\005Y\004\003\002\005=CyJ!!\020\002\003\023\031+hn\031;j_:\f\004\003\002\005=M}\002B\001\003\037,\001B!\001\002\020\031B!\021AA(\016\013)\005a\032\005C\001#H\033\005)%B\001$\003\003)\tgN\\8uCRLwN\\\005\003\021\026\023Q\"\0368ta\026\034\027.\0317ju\026$\007\"\002&\001\t\003Y\025A\002;va2,G-F\001M!\021AA(\024\013\021\017!q\025EJ\0261k%\021qJ\001\002\007)V\004H.Z\033)\005%\033\005\"\002*\001\t\003\032\026\001\003;p'R\024\030N\\4\025\003Q\003\"!\026.\016\003YS!a\026-\002\t1\fgn\032\006\0023\006!!.\031<b\023\tYfK\001\004TiJLgn\032\t\t\021\001\tce\013\0316)\001")
/*    */ public interface Function5<T1, T2, T3, T4, T5, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>>> curried();
/*    */   
/*    */   Function1<Tuple5<T1, T2, T3, T4, T5>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function5$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>> apply(Object x1) {
/* 26 */       return (new Function5$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1)).curried();
/*    */     }
/*    */     
/*    */     public Function5$$anonfun$curried$1(Function5 $outer) {}
/*    */     
/*    */     public class Function5$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction4<T2, T3, T4, T5, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2, Object x3, Object x4, Object x5) {
/* 26 */         return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5);
/*    */       }
/*    */       
/*    */       public Function5$$anonfun$curried$1$$anonfun$apply$1(Function5$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function5$$anonfun$tupled$1 extends AbstractFunction1<Tuple5<T1, T2, T3, T4, T5>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple5 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function5$$anonfun$tupled$1(Function5 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function5.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */