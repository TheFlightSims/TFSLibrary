/*    */ package scala.annotation;
/*    */ 
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005%a\001B\001\003\005\035\021\001\"\0327jI\006\024G.\032\006\003\007\021\t!\"\0318o_R\fG/[8o\025\005)\021!B:dC2\f7\001A\n\004\001!a\001CA\005\013\033\005\021\021BA\006\003\005)\teN\\8uCRLwN\034\t\003\0235I!A\004\002\003!M#\030\r^5d\003:tw\016^1uS>t\007\002\003\t\001\005\013\007IQA\t\002\0131,g/\0327\026\003I\001\"a\005\013\016\003\021I!!\006\003\003\007%sG\017\003\005\030\001\t\005\t\025!\004\023\003\031aWM^3mA!)\021\004\001C\0015\0051A(\0338jiz\"\"a\007\017\021\005%\001\001\"\002\t\031\001\004\021r!\002\020\003\021\003y\022\001C3mS\022\f'\r\\3\021\005%\001c!B\001\003\021\003\t3C\001\021#!\t\0312%\003\002%\t\t1\021I\\=SK\032DQ!\007\021\005\002\031\"\022a\b\005\bQ\001\022\r\021\"\002*\003\r\tE\nT\013\002U=\t1&\b\003\001\002\001\001\001BB\027!A\0035!&\001\003B\0312\003\003bB\030!\005\004%)\001M\001\007\r&sUi\025+\026\003Ez\021AM\017\003\0031Ba\001\016\021!\002\033\t\024a\002$J\035\026\033F\013\t\005\bm\001\022\r\021\"\0028\003\0251\025JT#S+\005At\"A\035\036\005\005\001\nBB\036!A\0035\001(\001\004G\023:+%\013\t\005\b{\001\022\r\021\"\002?\003\0211\025JT#\026\003}z\021\001Q\017\003\003QHaA\021\021!\002\033y\024!\002$J\035\026\003\003b\002#!\005\004%)!R\001\007\007>se)S$\026\003\031{\021aR\017\003\005qFa!\023\021!\002\0331\025aB\"P\035\032Ku\t\t\005\b\027\002\022\r\021\"\002M\003\021IeJR(\026\0035{\021AT\017\003\007\001Ba\001\025\021!\002\033i\025!B%O\r>\003\003b\002*!\005\004%)aU\001\b/\006\023f*\023(H+\005!v\"A+\036\005\r!\tBB,!A\0035A+\001\005X\003Js\025JT$!\021\035I\006E1A\005\006i\013aaU#W\013J+U#A.\020\003qk\"a\001u\t\ry\003\003\025!\004\\\003\035\031VIV#S\013\002Bq\001\031\021C\002\023\025\021-A\002P\r\032+\022AY\b\002Gv!qp\000\000\000\022\031)\007\005)A\007E\006!qJ\022$!\021\0359\007E1A\005\006\005\fq!T!Y\0236+V\n\003\004jA\001\006iAY\001\t\033\006C\026*T+NA!91\016\tb\001\n\013I\023aB'J\035&kU+\024\005\007[\002\002\013Q\002\026\002\0215Ke*S'V\033\002Bqa\034\021C\002\023\025\001/A\005B'N+%\013V%P\035V\t\021oD\001s;\t9\001\033\003\004uA\001\006i!]\001\013\003N\033VI\025+J\037:\003\003b\002<!\005\004%\ta^\001\007Eft\025-\\3\026\003a\004B!\037?\000%9\0211C_\005\003w\022\ta\001\025:fI\0264\027BA?\005\ri\025\r\035\006\003w\022\0012!_A\001\023\r\t\031A \002\007'R\024\030N\\4\t\017\005\035\001\005)A\005q\0069!-\037(b[\026\004\003")
/*    */ public final class elidable extends Annotation implements StaticAnnotation {
/*    */   private final int level;
/*    */   
/*    */   public static Map<String, Object> byName() {
/*    */     return elidable$.MODULE$.byName();
/*    */   }
/*    */   
/*    */   public static int ASSERTION() {
/*    */     return elidable$.MODULE$.ASSERTION();
/*    */   }
/*    */   
/*    */   public static int MINIMUM() {
/*    */     return elidable$.MODULE$.MINIMUM();
/*    */   }
/*    */   
/*    */   public static int MAXIMUM() {
/*    */     return elidable$.MODULE$.MAXIMUM();
/*    */   }
/*    */   
/*    */   public static int OFF() {
/*    */     return elidable$.MODULE$.OFF();
/*    */   }
/*    */   
/*    */   public static int SEVERE() {
/*    */     return elidable$.MODULE$.SEVERE();
/*    */   }
/*    */   
/*    */   public static int WARNING() {
/*    */     return elidable$.MODULE$.WARNING();
/*    */   }
/*    */   
/*    */   public static int INFO() {
/*    */     return elidable$.MODULE$.INFO();
/*    */   }
/*    */   
/*    */   public static int CONFIG() {
/*    */     return elidable$.MODULE$.CONFIG();
/*    */   }
/*    */   
/*    */   public static int FINE() {
/*    */     return elidable$.MODULE$.FINE();
/*    */   }
/*    */   
/*    */   public static int FINER() {
/*    */     return elidable$.MODULE$.FINER();
/*    */   }
/*    */   
/*    */   public static int FINEST() {
/*    */     return elidable$.MODULE$.FINEST();
/*    */   }
/*    */   
/*    */   public static int ALL() {
/*    */     return elidable$.MODULE$.ALL();
/*    */   }
/*    */   
/*    */   public final int level() {
/* 65 */     return this.level;
/*    */   }
/*    */   
/*    */   public elidable(int level) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\annotation\elidable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */