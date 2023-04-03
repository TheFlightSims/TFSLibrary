/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction14;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005MbaB\001\003!\003\r\t!\002\002\013\rVt7\r^5p]F*$\"A\002\002\013M\034\027\r\\1\004\001U\tbAI\024-cYZ\004)\022&P)fs6\r\033\f\024\005\0019\001C\001\005\n\033\005\021\021B\001\006\003\005\031\te.\037*fM\")A\002\001C\001\033\0051A%\0338ji\022\"\022A\004\t\003\021=I!\001\005\002\003\tUs\027\016\036\005\006%\0011\taE\001\006CB\004H.\037\013\021)}!\023FL\0329{\t;E*\025,\\A\026\004\"!\006\f\r\001\0211q\003\001CC\002a\021\021AU\t\0033q\001\"\001\003\016\n\005m\021!a\002(pi\"Lgn\032\t\003\021uI!A\b\002\003\007\005s\027\020C\003!#\001\007\021%\001\002wcA\021QC\t\003\007G\001A)\031\001\r\003\005Q\013\004\"B\023\022\001\0041\023A\001<3!\t)r\005\002\004)\001!\025\r\001\007\002\003)JBQAK\tA\002-\n!A^\032\021\005UaCAB\027\001\021\013\007\001D\001\002Ug!)q&\005a\001a\005\021a\017\016\t\003+E\"aA\r\001\t\006\004A\"A\001+5\021\025!\024\0031\0016\003\t1X\007\005\002\026m\0211q\007\001EC\002a\021!\001V\033\t\013e\n\002\031\001\036\002\005Y4\004CA\013<\t\031a\004\001#b\0011\t\021AK\016\005\006}E\001\raP\001\003m^\002\"!\006!\005\r\005\003\001R1\001\031\005\t!v\007C\003D#\001\007A)\001\002wqA\021Q#\022\003\007\r\002A)\031\001\r\003\005QC\004\"\002%\022\001\004I\025A\001<:!\t)\"\n\002\004L\001!\025\r\001\007\002\003)fBQ!T\tA\0029\0131A^\0311!\t)r\n\002\004Q\001!\025\r\001\007\002\004)F\002\004\"\002*\022\001\004\031\026a\001<2cA\021Q\003\026\003\007+\002A)\031\001\r\003\007Q\013\024\007C\003X#\001\007\001,A\002wcI\002\"!F-\005\ri\003\001R1\001\031\005\r!\026G\r\005\0069F\001\r!X\001\004mF\032\004CA\013_\t\031y\006\001#b\0011\t\031A+M\032\t\013\005\f\002\031\0012\002\007Y\fD\007\005\002\026G\0221A\r\001EC\002a\0211\001V\0315\021\0251\027\0031\001h\003\r1\030'\016\t\003+!$a!\033\001\t\006\004A\"a\001+2k!)1\016\001C\001Y\00691-\036:sS\026$W#A7\021\t!q\027\005]\005\003_\n\021\021BR;oGRLwN\\\031\021\t!qg%\035\t\005\0219\\#\017\005\003\t]B\032\b\003\002\005okQ\004B\001\0038;kB!\001B\\ w!\021Aa\016R<\021\t!q\027\n\037\t\005\0219t\025\020\005\003\t]NS\b\003\002\005o1n\004B\001\0038^yB!\001B\0342~!\021Aan\032\013)\005)|\b\003BA\001\003\017i!!a\001\013\007\005\025!!\001\006b]:|G/\031;j_:LA!!\003\002\004\tiQO\\:qK\016L\027\r\\5{K\022Dq!!\004\001\t\003\ty!\001\004ukBdW\rZ\013\003\003#\001R\001\0038\002\024Q\001\"\003CA\013C\031Z\003'\016\036@\t&s5\013W/cO&\031\021q\003\002\003\017Q+\b\017\\32k!\032\0211B@\t\017\005u\001\001\"\021\002 \005AAo\\*ue&tw\r\006\002\002\"A!\0211EA\027\033\t\t)C\003\003\002(\005%\022\001\0027b]\036T!!a\013\002\t)\fg/Y\005\005\003_\t)C\001\004TiJLgn\032\t\023\021\001\tce\013\0316u}\"\025JT*Y;\n<G\003")
/*    */ public interface Function15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8, T9 paramT9, T10 paramT10, T11 paramT11, T12 paramT12, T13 paramT13, T14 paramT14, T15 paramT15);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, R>>>>>>>>>>>>>>> curried();
/*    */   
/*    */   Function1<Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function15$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, R>>>>>>>>>>>>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, R>>>>>>>>>>>>>> apply(Object x1) {
/* 26 */       return (new Function15$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1)).curried();
/*    */     }
/*    */     
/*    */     public Function15$$anonfun$curried$1(Function15 $outer) {}
/*    */     
/*    */     public class Function15$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction14<T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2, Object x3, Object x4, Object x5, Object x6, Object x7, Object x8, Object x9, Object x10, Object x11, Object x12, Object x13, Object x14, Object x15) {
/* 26 */         return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15);
/*    */       }
/*    */       
/*    */       public Function15$$anonfun$curried$1$$anonfun$apply$1(Function15$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function15$$anonfun$tupled$1 extends AbstractFunction1<Tuple15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple15 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7(), x0$1._8(), x0$1._9(), x0$1._10(), x0$1._11(), x0$1._12(), x0$1._13(), x0$1._14(), x0$1._15()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function15$$anonfun$tupled$1(Function15 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function15.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */