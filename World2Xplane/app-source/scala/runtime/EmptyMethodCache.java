/*    */ package scala.runtime;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001m2A!\001\002\003\017\t\001R)\0349us6+G\017[8e\007\006\034\007.\032\006\003\007\021\tqA];oi&lWMC\001\006\003\025\0318-\0317b\007\001\031\"\001\001\005\021\005%QQ\"\001\002\n\005-\021!aC'fi\"|GmQ1dQ\026DQ!\004\001\005\0029\ta\001P5oSRtD#A\b\021\005%\001\001\"B\t\001\t\003\021\022\001\0024j]\022$\"aE\017\021\005QYR\"A\013\013\005Y9\022a\002:fM2,7\r\036\006\0031e\tA\001\\1oO*\t!$\001\003kCZ\f\027B\001\017\026\005\031iU\r\0365pI\")a\004\005a\001?\005Yam\034:SK\016,\027N^3sa\t\001c\005E\002\"E\021j\021aF\005\003G]\021Qa\0217bgN\004\"!\n\024\r\001\021Iq%HA\001\002\003\025\t\001\013\002\004?\022\032\024CA\025.!\tQ3&D\001\005\023\taCAA\004O_RD\027N\\4\021\005)r\023BA\030\005\005\r\te.\037\005\006c\001!\tAM\001\004C\022$Gc\001\0054s!)a\004\ra\001iA\022Qg\016\t\004C\t2\004CA\0238\t%A4'!A\001\002\013\005\001FA\002`IQBQA\017\031A\002M\t\021BZ8s\033\026$\bn\0343")
/*    */ public final class EmptyMethodCache extends MethodCache {
/*    */   public Method find(Class forReceiver) {
/*    */     return null;
/*    */   }
/*    */   
/*    */   public MethodCache add(Class<?> forReceiver, Method forMethod) {
/* 38 */     return new PolyMethodCache(this, forReceiver, forMethod, 1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\EmptyMethodCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */