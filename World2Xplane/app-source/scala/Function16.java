/*    */ package scala;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction15;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005}baB\001\003!\003\r\t!\002\002\013\rVt7\r^5p]F2$\"A\002\002\013M\034\027\r\\1\004\001U\021bAI\024-cYZ\004)\022&P)fs6\r[7\027'\t\001q\001\005\002\t\0235\t!!\003\002\013\005\t1\021I\\=SK\032DQ\001\004\001\005\0025\ta\001J5oSR$C#\001\b\021\005!y\021B\001\t\003\005\021)f.\033;\t\013I\001a\021A\n\002\013\005\004\b\017\\=\025#QyB%\013\0304qu\022u\tT)W7\002,'\016\005\002\026-1\001AAB\f\001\t\013\007\001DA\001S#\tIB\004\005\002\t5%\0211D\001\002\b\035>$\b.\0338h!\tAQ$\003\002\037\005\t\031\021I\\=\t\013\001\n\002\031A\021\002\005Y\f\004CA\013#\t\031\031\003\001#b\0011\t\021A+\r\005\006KE\001\rAJ\001\003mJ\002\"!F\024\005\r!\002\001R1\001\031\005\t!&\007C\003+#\001\0071&\001\002wgA\021Q\003\f\003\007[\001A)\031\001\r\003\005Q\033\004\"B\030\022\001\004\001\024A\001<5!\t)\022\007\002\0043\001!\025\r\001\007\002\003)RBQ\001N\tA\002U\n!A^\033\021\005U1DAB\034\001\021\013\007\001D\001\002Uk!)\021(\005a\001u\005\021aO\016\t\003+m\"a\001\020\001\t\006\004A\"A\001+7\021\025q\024\0031\001@\003\t1x\007\005\002\026\001\0221\021\t\001EC\002a\021!\001V\034\t\013\r\013\002\031\001#\002\005YD\004CA\013F\t\0311\005\001#b\0011\t\021A\013\017\005\006\021F\001\r!S\001\003mf\002\"!\006&\005\r-\003\001R1\001\031\005\t!\026\bC\003N#\001\007a*A\002wcA\002\"!F(\005\rA\003\001R1\001\031\005\r!\026\007\r\005\006%F\001\raU\001\004mF\n\004CA\013U\t\031)\006\001#b\0011\t\031A+M\031\t\013]\013\002\031\001-\002\007Y\f$\007\005\002\0263\0221!\f\001EC\002a\0211\001V\0313\021\025a\026\0031\001^\003\r1\030g\r\t\003+y#aa\030\001\t\006\004A\"a\001+2g!)\021-\005a\001E\006\031a/\r\033\021\005U\031GA\0023\001\021\013\007\001DA\002UcQBQAZ\tA\002\035\f1A^\0316!\t)\002\016\002\004j\001!\025\r\001\007\002\004)F*\004\"B6\022\001\004a\027a\001<2mA\021Q#\034\003\007]\002A)\031\001\r\003\007Q\013d\007C\003q\001\021\005\021/A\004dkJ\024\030.\0323\026\003I\004B\001C:\"k&\021AO\001\002\n\rVt7\r^5p]F\002B\001C:'mB!\001b]\026x!\021A1\017\r=\021\t!\031X'\037\t\005\021MT$\020\005\003\tg~Z\b\003\002\005t\tr\004B\001C:J{B!\001b\035(!\021A1oU@\021\013!\031\b,!\001\021\013!\031X,a\001\021\013!\031(-!\002\021\013!\031x-a\002\021\t!\031H\016\006\025\004_\006-\001\003BA\007\003'i!!a\004\013\007\005E!!\001\006b]:|G/\031;j_:LA!!\006\002\020\tiQO\\:qK\016L\027\r\\5{K\022Dq!!\007\001\t\003\tY\"\001\004ukBdW\rZ\013\003\003;\001R\001C:\002 Q\0012\003CA\021C\031Z\003'\016\036@\t&s5\013W/cO2L1!a\t\003\005\035!V\017\0357fcYBC!a\006\002\f!9\021\021\006\001\005B\005-\022\001\003;p'R\024\030N\\4\025\005\0055\002\003BA\030\003si!!!\r\013\t\005M\022QG\001\005Y\006twM\003\002\0028\005!!.\031<b\023\021\tY$!\r\003\rM#(/\0338h!MA\001!\t\024,aURt\bR%O'bk&m\0327\025\001")
/*    */ public interface Function16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R> {
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8, T9 paramT9, T10 paramT10, T11 paramT11, T12 paramT12, T13 paramT13, T14 paramT14, T15 paramT15, T16 paramT16);
/*    */   
/*    */   Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, R>>>>>>>>>>>>>>>> curried();
/*    */   
/*    */   Function1<Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>, R> tupled();
/*    */   
/*    */   String toString();
/*    */   
/*    */   public class Function16$$anonfun$curried$1 extends AbstractFunction1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, R>>>>>>>>>>>>>>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, R>>>>>>>>>>>>>>> apply(Object x1) {
/* 26 */       return (new Function16$$anonfun$curried$1$$anonfun$apply$1(this, ($anonfun$curried$1)x1)).curried();
/*    */     }
/*    */     
/*    */     public Function16$$anonfun$curried$1(Function16 $outer) {}
/*    */     
/*    */     public class Function16$$anonfun$curried$1$$anonfun$apply$1 extends AbstractFunction15<T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object x1$1;
/*    */       
/*    */       public final R apply(Object x2, Object x3, Object x4, Object x5, Object x6, Object x7, Object x8, Object x9, Object x10, Object x11, Object x12, Object x13, Object x14, Object x15, Object x16) {
/* 26 */         return this.$outer.$outer.apply(this.x1$1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16);
/*    */       }
/*    */       
/*    */       public Function16$$anonfun$curried$1$$anonfun$apply$1(Function16$$anonfun$curried$1 $outer, Object x1$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Function16$$anonfun$tupled$1 extends AbstractFunction1<Tuple16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>, R> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final R apply(Tuple16 x0$1) {
/* 34 */       if (x0$1 != null)
/* 34 */         return 
/* 35 */           (R)this.$outer.apply(x0$1._1(), x0$1._2(), x0$1._3(), x0$1._4(), x0$1._5(), x0$1._6(), x0$1._7(), x0$1._8(), x0$1._9(), x0$1._10(), x0$1._11(), x0$1._12(), x0$1._13(), x0$1._14(), x0$1._15(), x0$1._16()); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Function16$$anonfun$tupled$1(Function16 $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function16.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */