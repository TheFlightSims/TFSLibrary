/*    */ package scala.runtime;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U3A!\001\002\003\017\tyQ*Z4b\033\026$\bn\0343DC\016DWM\003\002\004\t\0059!/\0368uS6,'\"A\003\002\013M\034\027\r\\1\004\001M\021\001\001\003\t\003\023)i\021AA\005\003\027\t\0211\"T3uQ>$7)Y2iK\"AQ\002\001B\001B\003%a\"A\004g_Jt\025-\\3\021\005=\031bB\001\t\022\033\005!\021B\001\n\005\003\031\001&/\0323fM&\021A#\006\002\007'R\024\030N\\4\013\005I!\001\002C\f\001\005\003\005\013\021\002\r\002#\031|'\017U1sC6,G/\032:UsB,7\017E\002\0213mI!A\007\003\003\013\005\023(/Y=1\005q1\003cA\017#I5\taD\003\002 A\005!A.\0318h\025\005\t\023\001\0026bm\006L!a\t\020\003\013\rc\027m]:\021\005\0252C\002\001\003\nOY\t\t\021!A\003\002!\0221a\030\0236#\tIC\006\005\002\021U%\0211\006\002\002\b\035>$\b.\0338h!\t\001R&\003\002/\t\t\031\021I\\=\t\013A\002A\021A\031\002\rqJg.\033;?)\r\0214\007\016\t\003\023\001AQ!D\030A\0029AQaF\030A\002U\0022\001E\r7a\t9\024\bE\002\036Ea\002\"!J\035\005\023\035\"\024\021!A\001\006\003A\003\"B\036\001\t\003a\024\001\0024j]\022$\"!P\"\021\005y\nU\"A \013\005\001s\022a\002:fM2,7\r^\005\003\005~\022a!T3uQ>$\007\"\002#;\001\004)\025a\0034peJ+7-Z5wKJ\004$A\022%\021\007u\021s\t\005\002&\021\022I\021jQA\001\002\003\025\t\001\013\002\004?\0222\004\"B&\001\t\003a\025aA1eIR\031\001\"T*\t\013\021S\005\031\001(1\005=\013\006cA\017#!B\021Q%\025\003\n%6\013\t\021!A\003\002!\0221a\030\0238\021\025!&\n1\001>\003%1wN]'fi\"|G\r")
/*    */ public final class MegaMethodCache extends MethodCache {
/*    */   private final String forName;
/*    */   
/*    */   private final Class<?>[] forParameterTypes;
/*    */   
/*    */   public MegaMethodCache(String forName, Class[] forParameterTypes) {}
/*    */   
/*    */   public Method find(Class forReceiver) {
/* 48 */     return forReceiver.getMethod(this.forName, this.forParameterTypes);
/*    */   }
/*    */   
/*    */   public MethodCache add(Class forReceiver, Method forMethod) {
/* 50 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\MegaMethodCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */