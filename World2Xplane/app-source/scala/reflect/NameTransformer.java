/*    */ package scala.reflect;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Y<Q!\001\002\t\002\035\tqBT1nKR\023\030M\\:g_JlWM\035\006\003\007\021\tqA]3gY\026\034GOC\001\006\003\025\0318-\0317b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021qBT1nKR\023\030M\\:g_JlWM]\n\003\0231\001\"!\004\b\016\003\021I!a\004\003\003\r\005s\027PU3g\021\025\t\022\002\"\001\023\003\031a\024N\\5u}Q\tq\001C\004\025\023\t\007I\021A\013\002)5{E)\026'F?N+fIR%Y?N#&+\023(H+\0051\002CA\f\033\035\ti\001$\003\002\032\t\0051\001K]3eK\032L!a\007\017\003\rM#(/\0338h\025\tIB\001\003\004\037\023\001\006IAF\001\026\033>#U\013T#`'V3e)\023-`'R\023\026JT$!\021\035\001\023B1A\005\002U\t\001CT!N\013~Su*\023(`'R\023\026JT$\t\r\tJ\001\025!\003\027\003Eq\025)T#`\025>KejX*U%&su\t\t\005\bI%\021\r\021\"\001&\003Qiu\nR+M\013~Kej\025+B\035\016+uLT!N\013V\ta\005\005\002(Y5\t\001F\003\002*U\005!A.\0318h\025\005Y\023\001\0026bm\006L!a\007\025\t\r9J\001\025!\003'\003Uiu\nR+M\013~Kej\025+B\035\016+uLT!N\013\002Bq\001M\005C\002\023%\021'\001\003o_B\034X#\001\032\021\0055\031\024B\001\033\005\005\rIe\016\036\005\007m%\001\013\021\002\032\002\0139|\007o\035\021\t\017aJ!\031!C\005c\0051anY8eKNDaAO\005!\002\023\021\024a\0028d_\022,7\017\t\004\005y%!QHA\004Pa\016{G-Z:\024\005mb\001\002C <\005\013\007I\021\001!\002\005=\004X#A!\021\0055\021\025BA\"\005\005\021\031\005.\031:\t\021\025[$\021!Q\001\n\005\0131a\0349!\021!95H!b\001\n\003)\022\001B2pI\026D\001\"S\036\003\002\003\006IAF\001\006G>$W\r\t\005\t\027n\022)\031!C\001\031\006!a.\032=u+\005i\005C\001(<\033\005I\001\002\003)<\005\003\005\013\021B'\002\0139,\007\020\036\021\t\013EYD\021\001*\025\t5\033F+\026\005\006E\003\r!\021\005\006\017F\003\rA\006\005\006\027F\003\r!\024\005\b/&\021\r\021\"\003Y\003\035y\007OM2pI\026,\022!\027\t\004\033i3\022BA.\005\005\025\t%O]1z\021\031i\026\002)A\0053\006Aq\016\035\032d_\022,\007\005C\004`\023\t\007I\021\0021\002\017\r|G-\032\032paV\t\021\rE\002\01656CaaY\005!\002\023\t\027\001C2pI\026\024t\016\035\021\t\013\025LA\021\0024\002\017\025tG/\032:PaR\031qM[6\021\0055A\027BA5\005\005\021)f.\033;\t\013}\"\007\031A!\t\013\035#\007\031\001\f\t\0135LA\021\0018\002\r\025t7m\0343f)\t1r\016C\003qY\002\007a#\001\003oC6,\007\"\002:\n\t\003\031\030A\0023fG>$W\r\006\002\027i\")Q/\035a\001-\005)a.Y7fa\001")
/*    */ public final class NameTransformer {
/*    */   public static String decode(String paramString) {
/*    */     return NameTransformer$.MODULE$.decode(paramString);
/*    */   }
/*    */   
/*    */   public static String encode(String paramString) {
/*    */     return NameTransformer$.MODULE$.encode(paramString);
/*    */   }
/*    */   
/*    */   public static String MODULE_INSTANCE_NAME() {
/*    */     return NameTransformer$.MODULE$.MODULE_INSTANCE_NAME();
/*    */   }
/*    */   
/*    */   public static String NAME_JOIN_STRING() {
/*    */     return NameTransformer$.MODULE$.NAME_JOIN_STRING();
/*    */   }
/*    */   
/*    */   public static String MODULE_SUFFIX_STRING() {
/*    */     return NameTransformer$.MODULE$.MODULE_SUFFIX_STRING();
/*    */   }
/*    */   
/*    */   public static class $anonfun$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/* 18 */       return "$";
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anonfun$2 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/* 19 */       return "$";
/*    */     }
/*    */   }
/*    */   
/*    */   public static class OpCodes {
/*    */     private final char op;
/*    */     
/*    */     private final String code;
/*    */     
/*    */     private final OpCodes next;
/*    */     
/*    */     public char op() {
/* 25 */       return this.op;
/*    */     }
/*    */     
/*    */     public String code() {
/* 25 */       return this.code;
/*    */     }
/*    */     
/*    */     public OpCodes next() {
/* 25 */       return this.next;
/*    */     }
/*    */     
/*    */     public OpCodes(char op, String code, OpCodes next) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\NameTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */