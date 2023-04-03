/*    */ package scala.runtime;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001)4A!\001\002\003\017\ty\001k\0347z\033\026$\bn\0343DC\016DWM\003\002\004\t\0059!/\0368uS6,'\"A\003\002\013M\034\027\r\\1\004\001M\021\001\001\003\t\003\023)i\021AA\005\003\027\t\0211\"T3uQ>$7)Y2iK\"AQ\002\001B\001B\003%\001\"\001\003oKb$\b\002C\b\001\005\003\005\013\021\002\t\002\021I,7-Z5wKJ\004$!E\016\021\007I9\022$D\001\024\025\t!R#\001\003mC:<'\"\001\f\002\t)\fg/Y\005\0031M\021Qa\0217bgN\004\"AG\016\r\001\021IADDA\001\002\003\025\t!\b\002\004?\022B\024C\001\020#!\ty\002%D\001\005\023\t\tCAA\004O_RD\027N\\4\021\005}\031\023B\001\023\005\005\r\te.\037\005\tM\001\021\t\021)A\005O\0051Q.\032;i_\022\004\"\001K\026\016\003%R!AK\n\002\017I,g\r\\3di&\021A&\013\002\007\033\026$\bn\0343\t\0219\002!\021!Q\001\n=\n!bY8na2,\0070\033;z!\ty\002'\003\0022\t\t\031\021J\034;\t\013M\002A\021\001\033\002\rqJg.\033;?)\025)dg\016\037>!\tI\001\001C\003\016e\001\007\001\002C\003\020e\001\007\001\b\r\002:wA\031!c\006\036\021\005iYD!\003\0178\003\003\005\tQ!\001\036\021\0251#\0071\001(\021\025q#\0071\0010\021\025y\004\001\"\003A\00311\027N\0343J]R,'O\\1m)\t9\023\tC\003C}\001\0071)A\006g_J\024VmY3jm\026\024\bG\001#G!\r\021r#\022\t\0035\031#\021bR!\002\002\003\005)\021A\017\003\007}#\023\b\013\002?\023B\021!*T\007\002\027*\021A\nB\001\013C:tw\016^1uS>t\027B\001(L\005\035!\030-\0337sK\016DQ\001\025\001\005\002E\013AAZ5oIR\021qE\025\005\006\005>\003\ra\025\031\003)Z\0032AE\fV!\tQb\013B\005X%\006\005\t\021!B\001;\t!q\fJ\0311\021\035I\006A1A\005\016i\013Q\"T1y\007>l\007\017\\3ySRLX#A.\020\003qk\"\001\001Q\t\ry\003\001\025!\004\\\0039i\025\r_\"p[BdW\r_5us\002BQ\001\031\001\005\002\005\f1!\0313e)\rA!\r\033\005\006\005~\003\ra\031\031\003I\032\0042AE\ff!\tQb\rB\005hE\006\005\t\021!B\001;\t!q\fJ\0312\021\025Iw\f1\001(\003%1wN]'fi\"|G\r")
/*    */ public final class PolyMethodCache extends MethodCache {
/*    */   private final MethodCache next;
/*    */   
/*    */   private final Class<?> receiver;
/*    */   
/*    */   private final Method method;
/*    */   
/*    */   private final int complexity;
/*    */   
/*    */   private final int MaxComplexity;
/*    */   
/*    */   public PolyMethodCache(MethodCache next, Class<?> receiver, Method method, int complexity) {}
/*    */   
/*    */   private Method findInternal(Class<?> forReceiver) {
/*    */     while (true) {
/* 66 */       MethodCache methodCache = this.next;
/* 67 */       if (methodCache instanceof PolyMethodCache) {
/* 67 */         PolyMethodCache polyMethodCache = (PolyMethodCache)methodCache;
/*    */         continue;
/*    */       } 
/* 68 */       return (forReceiver == this.receiver) ? this.method : this.next.find(forReceiver);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Method find(Class<?> forReceiver) {
/* 71 */     return findInternal(forReceiver);
/*    */   }
/*    */   
/*    */   private final int MaxComplexity() {
/* 74 */     return 160;
/*    */   }
/*    */   
/*    */   public MethodCache add(Class<?> forReceiver, Method forMethod) {
/* 77 */     return (this.complexity < 160) ? 
/* 78 */       new PolyMethodCache(this, forReceiver, forMethod, this.complexity + 1) : 
/*    */       
/* 80 */       new MegaMethodCache(forMethod.getName(), forMethod.getParameterTypes());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\PolyMethodCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */