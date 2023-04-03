/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.Node$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055c\001B\001\003\001&\021\001\002U;cY&\034\027\n\022\006\003\007\021\t1\001\032;e\025\t)a!A\002y[2T\021aB\001\006g\016\fG.Y\002\001'\021\001!B\004\n\021\005-aQ\"\001\002\n\0055\021!AC#yi\026\024h.\0317J\tB\021q\002E\007\002\r%\021\021C\002\002\b!J|G-^2u!\ty1#\003\002\025\r\ta1+\032:jC2L'0\0312mK\"Aa\003\001BK\002\023\005q#\001\005qk\nd\027nY%e+\005A\002CA\r\035\035\ty!$\003\002\034\r\0051\001K]3eK\032L!!\b\020\003\rM#(/\0338h\025\tYb\001\003\005!\001\tE\t\025!\003\031\003%\001XO\0317jG&#\007\005\003\005#\001\tU\r\021\"\001\030\003!\031\030p\035;f[&#\007\002\003\023\001\005#\005\013\021\002\r\002\023ML8\017^3n\023\022\004\003\"\002\024\001\t\0039\023A\002\037j]&$h\bF\002)S)\002\"a\003\001\t\013Y)\003\031\001\r\t\013\t*\003\031\001\r\t\0131\002A\021A\027\002\0131\f'-\0327\026\0039\002\"a\f\033\016\003AR!!\r\032\002\t1\fgn\032\006\002g\005!!.\031<b\023\ti\002\007C\0037\001\021\005q'A\005biR\024\030NY;uKV\t\001\b\005\002:u5\tA!\003\002<\t\tAQ*\032;b\t\006$\030\rC\003>\001\021\005a(A\003dQ&dG-F\001@\035\t\001U)D\001B\025\t\0215)A\005j[6,H/\0312mK*\021AIB\001\013G>dG.Z2uS>t\027B\001$B\003\rq\025\016\034\005\b\021\002\t\t\021\"\001J\003\021\031w\016]=\025\007!R5\nC\004\027\017B\005\t\031\001\r\t\017\t:\005\023!a\0011!9Q\nAI\001\n\003q\025AD2paf$C-\0324bk2$H%M\013\002\037*\022\001\004U\026\002#B\021!kV\007\002'*\021A+V\001\nk:\034\007.Z2lK\022T!A\026\004\002\025\005tgn\034;bi&|g.\003\002Y'\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017i\003\021\023!C\001\035\006q1m\0349zI\021,g-Y;mi\022\022\004b\002/\001\003\003%\t%L\001\016aJ|G-^2u!J,g-\033=\t\017y\003\021\021!C\001?\006a\001O]8ek\016$\030I]5usV\t\001\r\005\002\020C&\021!M\002\002\004\023:$\bb\0023\001\003\003%\t!Z\001\017aJ|G-^2u\0132,W.\0328u)\t1\027\016\005\002\020O&\021\001N\002\002\004\003:L\bb\0026d\003\003\005\r\001Y\001\004q\022\n\004b\0027\001\003\003%\t%\\\001\020aJ|G-^2u\023R,'/\031;peV\ta\016E\002pa\032l\021aQ\005\003c\016\023\001\"\023;fe\006$xN\035\005\bg\002\t\t\021\"\001u\003!\031\027M\\#rk\006dGCA;y!\tya/\003\002x\r\t9!i\\8mK\006t\007b\0026s\003\003\005\rA\032\005\bu\002\t\t\021\"\021|\003!A\027m\0355D_\022,G#\0011\t\017u\004\021\021!C!}\0061Q-];bYN$\"!^@\t\017)d\030\021!a\001M\036I\0211\001\002\002\002#\005\021QA\001\t!V\024G.[2J\tB\0311\"a\002\007\021\005\021\021\021!E\001\003\023\031R!a\002\002\fI\001r!!\004\002\024aA\002&\004\002\002\020)\031\021\021\003\004\002\017I,h\016^5nK&!\021QCA\b\005E\t%m\035;sC\016$h)\0368di&|gN\r\005\bM\005\035A\021AA\r)\t\t)\001\003\006\002\036\005\035\021\021!C#\003?\t\001\002^8TiJLgn\032\013\002]!Q\0211EA\004\003\003%\t)!\n\002\013\005\004\b\017\\=\025\013!\n9#!\013\t\rY\t\t\0031\001\031\021\031\021\023\021\005a\0011!Q\021QFA\004\003\003%\t)a\f\002\017Ut\027\r\0359msR!\021\021GA\037!\025y\0211GA\034\023\r\t)D\002\002\007\037B$\030n\0348\021\013=\tI\004\007\r\n\007\005mbA\001\004UkBdWM\r\005\n\003\tY#!AA\002!\n1\001\037\0231\021)\t\031%a\002\002\002\023%\021QI\001\fe\026\fGMU3t_24X\r\006\002\002HA\031q&!\023\n\007\005-\003G\001\004PE*,7\r\036")
/*    */ public class PublicID extends ExternalID implements Product, Serializable {
/*    */   private final String publicId;
/*    */   
/*    */   private final String systemId;
/*    */   
/*    */   public String publicId() {
/* 60 */     return this.publicId;
/*    */   }
/*    */   
/*    */   public String systemId() {
/* 60 */     return this.systemId;
/*    */   }
/*    */   
/*    */   public PublicID copy(String publicId, String systemId) {
/* 60 */     return new PublicID(publicId, systemId);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 60 */     return publicId();
/*    */   }
/*    */   
/*    */   public String copy$default$2() {
/* 60 */     return systemId();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 60 */     return "PublicID";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 60 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 60 */     switch (x$1) {
/*    */       default:
/* 60 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 60 */     return publicId();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 60 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 60 */     return x$1 instanceof PublicID;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 60 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 107
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/PublicID
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 111
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/PublicID
/*    */     //   27: astore #5
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual publicId : ()Ljava/lang/String;
/*    */     //   33: aload #5
/*    */     //   35: invokevirtual publicId : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 103
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 103
/*    */     //   58: aload_0
/*    */     //   59: invokevirtual systemId : ()Ljava/lang/String;
/*    */     //   62: aload #5
/*    */     //   64: invokevirtual systemId : ()Ljava/lang/String;
/*    */     //   67: astore #4
/*    */     //   69: dup
/*    */     //   70: ifnonnull -> 82
/*    */     //   73: pop
/*    */     //   74: aload #4
/*    */     //   76: ifnull -> 90
/*    */     //   79: goto -> 103
/*    */     //   82: aload #4
/*    */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   87: ifeq -> 103
/*    */     //   90: aload #5
/*    */     //   92: aload_0
/*    */     //   93: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   96: ifeq -> 103
/*    */     //   99: iconst_1
/*    */     //   100: goto -> 104
/*    */     //   103: iconst_0
/*    */     //   104: ifeq -> 111
/*    */     //   107: iconst_1
/*    */     //   108: goto -> 112
/*    */     //   111: iconst_0
/*    */     //   112: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #60	-> 0
/*    */     //   #236	-> 12
/*    */     //   #60	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	113	0	this	Lscala/xml/dtd/PublicID;
/*    */     //   0	113	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public PublicID(String publicId, String systemId) {
/* 60 */     Product.class.$init$(this);
/* 61 */     if (checkPubID(publicId)) {
/* 64 */       if (systemId == null || checkSysID(systemId))
/*    */         return; 
/* 65 */       throw new IllegalArgumentException("can't use both \" and ' in systemId");
/*    */     } 
/*    */     throw new IllegalArgumentException("publicId must consist of PubidChars");
/*    */   }
/*    */   
/*    */   public String label() {
/* 68 */     return "#PI";
/*    */   }
/*    */   
/*    */   public MetaData attribute() {
/* 71 */     return Node$.MODULE$.NoAttributes();
/*    */   }
/*    */   
/*    */   public Nil$ child() {
/* 74 */     return Nil$.MODULE$;
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<String, String>, PublicID> tupled() {
/*    */     return PublicID$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<String, PublicID>> curried() {
/*    */     return PublicID$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\PublicID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */