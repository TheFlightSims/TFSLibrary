/*    */ package akka.util;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectStreamClass;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t3A!\001\002\001\017\ta2\t\\1tg2{\027\rZ3s\037\nTWm\031;J]B,Ho\025;sK\006l'BA\002\005\003\021)H/\0337\013\003\025\tA!Y6lC\016\0011C\001\001\t!\tIa\"D\001\013\025\tYA\"\001\002j_*\tQ\"\001\003kCZ\f\027BA\b\013\005Ey%M[3di&s\007/\036;TiJ,\027-\034\005\t#\001\021\t\021)A\005%\005Y1\r\\1tg2{\027\rZ3s!\t\031b#D\001\025\025\t)B\"\001\003mC:<\027BA\f\025\005-\031E.Y:t\031>\fG-\032:\t\021e\001!\021!Q\001\ni\t!![:\021\005%Y\022B\001\017\013\005-Ie\016];u'R\024X-Y7\t\013y\001A\021A\020\002\rqJg.\033;?)\r\001#e\t\t\003C\001i\021A\001\005\006#u\001\rA\005\005\0063u\001\rA\007\005\006K\001!\tFJ\001\re\026\034x\016\034<f\0072\f7o\035\013\003Ou\002$\001\013\033\021\007%z#G\004\002+[5\t1FC\001-\003\025\0318-\0317b\023\tq3&\001\004Qe\026$WMZ\005\003aE\022Qa\0217bgNT!AL\026\021\005M\"D\002\001\003\nk\021\n\t\021!A\003\002Y\0221a\030\0232#\t9$\b\005\002+q%\021\021h\013\002\b\035>$\b.\0338h!\tQ3(\003\002=W\t\031\021I\\=\t\013y\"\003\031A \002#=\024'.Z2u'R\024X-Y7DY\006\0348\017\005\002\n\001&\021\021I\003\002\022\037\nTWm\031;TiJ,\027-\\\"mCN\034\b")
/*    */ public class ClassLoaderObjectInputStream extends ObjectInputStream {
/*    */   private final ClassLoader classLoader;
/*    */   
/*    */   public ClassLoaderObjectInputStream(ClassLoader classLoader, InputStream is) {
/* 16 */     super(is);
/*    */   }
/*    */   
/*    */   public Class<?> resolveClass(ObjectStreamClass objectStreamClass) {
/*    */     try {
/*    */     
/* 19 */     } catch (ClassNotFoundException classNotFoundException) {}
/* 19 */     return super.resolveClass(objectStreamClass);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\ClassLoaderObjectInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */