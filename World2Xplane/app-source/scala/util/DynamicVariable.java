/*    */ package scala.util;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t4A!\001\002\001\017\tyA)\0378b[&\034g+\031:jC\ndWM\003\002\004\t\005!Q\017^5m\025\005)\021!B:dC2\f7\001A\013\003\021E\031\"\001A\005\021\005)YQ\"\001\003\n\0051!!AB!osJ+g\r\003\005\017\001\t\005\t\025!\003\020\003\021Ig.\033;\021\005A\tB\002\001\003\006%\001\021\ra\005\002\002)F\021Ac\006\t\003\025UI!A\006\003\003\0179{G\017[5oOB\021!\002G\005\0033\021\0211!\0218z\021\025Y\002\001\"\001\035\003\031a\024N\\5u}Q\021Qd\b\t\004=\001yQ\"\001\002\t\0139Q\002\031A\b\t\017\005\002!\031!C\005E\005\021A\017\\\013\002GI\021A\005\013\004\005K\031\0021E\001\007=e\0264\027N\\3nK:$h\b\003\004(\001\001\006IaI\001\004i2\004\003cA\025/\0375\t!F\003\002,Y\005!A.\0318h\025\005i\023\001\0026bm\006L!a\f\026\003-%s\007.\032:ji\006\024G.\032+ie\026\fG\rT8dC2DQ!\r\023\005BI\nA\"\0338ji&\fGNV1mk\026$\022a\r\n\004i=Ia\001B\0236\001M2AA\016\024\003o\t)A%\0318p]N\021Q\007\013\005\0067U\"\t!\017\013\002uA\021\001#\016\005\006cU\"\t\005\020\013\002{I\031ahD\005\007\t\025*\004!\020\005\006\001\002!\t!Q\001\006m\006dW/Z\013\002\037!)1\t\001C\001\t\006Iq/\033;i-\006dW/Z\013\003\013\"#\"AR(\025\005\035S\005C\001\tI\t\025I%I1\001\024\005\005\031\006BB&C\t\003\007A*A\003uQVt7\016E\002\013\033\036K!A\024\003\003\021q\022\027P\\1nKzBQ\001\025\"A\002=\taA\\3xm\006d\007\"\002*\001\t\003\031\026!\003<bYV,w\fJ3r)\t!v\013\005\002\013+&\021a\013\002\002\005+:LG\017C\003Q#\002\007q\002C\003Z\001\021\005#,\001\005u_N#(/\0338h)\005Y\006C\001/`\035\tQQ,\003\002_\t\0051\001K]3eK\032L!\001Y1\003\rM#(/\0338h\025\tqF\001")
/*    */ public class DynamicVariable<T> {
/*    */   public final T scala$util$DynamicVariable$$init;
/*    */   
/* 40 */   private final InheritableThreadLocal<T> tl = new $anon$1(this);
/*    */   
/*    */   private InheritableThreadLocal<T> tl() {
/* 40 */     return this.tl;
/*    */   }
/*    */   
/*    */   public class $anon$1 extends InheritableThreadLocal<T> {
/*    */     public $anon$1(DynamicVariable $outer) {}
/*    */     
/*    */     public T initialValue() {
/* 41 */       return this.$outer.scala$util$DynamicVariable$$init;
/*    */     }
/*    */   }
/*    */   
/*    */   public T value() {
/* 45 */     return tl().get();
/*    */   }
/*    */   
/*    */   public <S> S withValue(Object newval, Function0 thunk) {
/* 54 */     Object oldval = value();
/* 55 */     tl().set((T)newval);
/*    */     try {
/* 57 */       return (S)thunk.apply();
/*    */     } finally {
/* 58 */       tl().set((T)oldval);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void value_$eq(Object newval) {
/* 64 */     tl().set((T)newval);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 66 */     return (new StringBuilder()).append("DynamicVariable(").append(value()).append(")").toString();
/*    */   }
/*    */   
/*    */   public DynamicVariable(Object init) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\DynamicVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */