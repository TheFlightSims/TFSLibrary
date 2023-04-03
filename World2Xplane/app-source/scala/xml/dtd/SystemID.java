/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Null$;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005b\001B\001\003\001&\021\001bU=ti\026l\027\n\022\006\003\007\021\t1\001\032;e\025\t)a!A\002y[2T\021aB\001\006g\016\fG.Y\002\001'\021\001!B\004\n\021\005-aQ\"\001\002\n\0055\021!AC#yi\026\024h.\0317J\tB\021q\002E\007\002\r%\021\021C\002\002\b!J|G-^2u!\ty1#\003\002\025\r\ta1+\032:jC2L'0\0312mK\"Aa\003\001BK\002\023\005q#\001\005tsN$X-\\%e+\005A\002CA\r\035\035\ty!$\003\002\034\r\0051\001K]3eK\032L!!\b\020\003\rM#(/\0338h\025\tYb\001\003\005!\001\tE\t\025!\003\031\003%\031\030p\035;f[&#\007\005C\003#\001\021\0051%\001\004=S:LGO\020\013\003I\025\002\"a\003\001\t\013Y\t\003\031\001\r\t\017\035\002!\031!C\001Q\005A\001/\0362mS\016LE-F\001*!\ty!&\003\002,\r\t!a*\0367m\021\031i\003\001)A\005S\005I\001/\0362mS\016LE\r\t\005\b_\001\t\t\021\"\0011\003\021\031w\016]=\025\005\021\n\004b\002\f/!\003\005\r\001\007\005\bg\001\t\n\021\"\0015\0039\031w\016]=%I\0264\027-\0367uIE*\022!\016\026\0031YZ\023a\016\t\003quj\021!\017\006\003um\n\021\"\0368dQ\026\0347.\0323\013\005q2\021AC1o]>$\030\r^5p]&\021a(\017\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007b\002!\001\003\003%\t%Q\001\016aJ|G-^2u!J,g-\033=\026\003\t\003\"a\021%\016\003\021S!!\022$\002\t1\fgn\032\006\002\017\006!!.\031<b\023\tiB\tC\004K\001\005\005I\021A&\002\031A\024x\016Z;di\006\023\030\016^=\026\0031\003\"aD'\n\00593!aA%oi\"9\001\013AA\001\n\003\t\026A\0049s_\022,8\r^#mK6,g\016\036\013\003%V\003\"aD*\n\005Q3!aA!os\"9akTA\001\002\004a\025a\001=%c!9\001\fAA\001\n\003J\026a\0049s_\022,8\r^%uKJ\fGo\034:\026\003i\0032a\0270S\033\005a&BA/\007\003)\031w\016\0347fGRLwN\\\005\003?r\023\001\"\023;fe\006$xN\035\005\bC\002\t\t\021\"\001c\003!\031\027M\\#rk\006dGCA2g!\tyA-\003\002f\r\t9!i\\8mK\006t\007b\002,a\003\003\005\rA\025\005\bQ\002\t\t\021\"\021j\003!A\027m\0355D_\022,G#\001'\t\017-\004\021\021!C!Y\0061Q-];bYN$\"aY7\t\017YS\027\021!a\001%\0369qNAA\001\022\003\001\030\001C*zgR,W.\023#\021\005-\thaB\001\003\003\003E\tA]\n\004cN\024\002\003\002;x1\021j\021!\036\006\003m\032\tqA];oi&lW-\003\002yk\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\013\t\nH\021\001>\025\003ADq\001`9\002\002\023\025S0\001\005u_N#(/\0338h)\005\021\005\002C@r\003\003%\t)!\001\002\013\005\004\b\017\\=\025\007\021\n\031\001C\003\027}\002\007\001\004C\005\002\bE\f\t\021\"!\002\n\0059QO\\1qa2LH\003BA\006\003#\001BaDA\0071%\031\021q\002\004\003\r=\003H/[8o\021%\t\031\"!\002\002\002\003\007A%A\002yIAB\021\"a\006r\003\003%I!!\007\002\027I,\027\r\032*fg>dg/\032\013\003\0037\0012aQA\017\023\r\ty\002\022\002\007\037\nTWm\031;")
/*    */ public class SystemID extends ExternalID implements Product, Serializable {
/*    */   private final String systemId;
/*    */   
/*    */   private final Null$ publicId;
/*    */   
/*    */   public String systemId() {
/* 46 */     return this.systemId;
/*    */   }
/*    */   
/*    */   public SystemID copy(String systemId) {
/* 46 */     return new SystemID(systemId);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 46 */     return systemId();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 46 */     return "SystemID";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 46 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 46 */     switch (x$1) {
/*    */       default:
/* 46 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 46 */     return systemId();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 46 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 46 */     return x$1 instanceof SystemID;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 46 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 75
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/SystemID
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 79
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/SystemID
/*    */     //   27: astore #4
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual systemId : ()Ljava/lang/String;
/*    */     //   33: aload #4
/*    */     //   35: invokevirtual systemId : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 71
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 71
/*    */     //   58: aload #4
/*    */     //   60: aload_0
/*    */     //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   64: ifeq -> 71
/*    */     //   67: iconst_1
/*    */     //   68: goto -> 72
/*    */     //   71: iconst_0
/*    */     //   72: ifeq -> 79
/*    */     //   75: iconst_1
/*    */     //   76: goto -> 80
/*    */     //   79: iconst_0
/*    */     //   80: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #46	-> 0
/*    */     //   #236	-> 12
/*    */     //   #46	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	this	Lscala/xml/dtd/SystemID;
/*    */     //   0	81	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public SystemID(String systemId) {
/* 46 */     Product.class.$init$(this);
/* 47 */     this.publicId = null;
/* 49 */     if (checkSysID(systemId))
/*    */       return; 
/* 50 */     throw new IllegalArgumentException("can't use both \" and ' in systemId");
/*    */   }
/*    */   
/*    */   public Null$ publicId() {
/*    */     return this.publicId;
/*    */   }
/*    */   
/*    */   public static <A> Function1<String, A> andThen(Function1<SystemID, A> paramFunction1) {
/*    */     return SystemID$.MODULE$.andThen(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> Function1<A, SystemID> compose(Function1<A, String> paramFunction1) {
/*    */     return SystemID$.MODULE$.compose(paramFunction1);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\SystemID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */