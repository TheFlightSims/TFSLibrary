/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ec\001B\001\003\001&\021ABT8uCRLwN\034#fG2T!a\001\003\002\007\021$HM\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001a\005\003\001\0259\021\002CA\006\r\033\005\021\021BA\007\003\005)i\025M]6va\022+7\r\034\t\003\037Ai\021AB\005\003#\031\021q\001\025:pIV\034G\017\005\002\020'%\021AC\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\t-\001\021)\032!C\001/\005!a.Y7f+\005A\002CA\r\035\035\ty!$\003\002\034\r\0051\001K]3eK\032L!!\b\020\003\rM#(/\0338h\025\tYb\001\003\005!\001\tE\t\025!\003\031\003\025q\027-\\3!\021!\021\003A!f\001\n\003\031\023!B3yi&#U#\001\023\021\005-)\023B\001\024\003\005))\005\020^3s]\006d\027\n\022\005\tQ\001\021\t\022)A\005I\0051Q\r\037;J\t\002BQA\013\001\005\002-\na\001P5oSRtDc\001\027.]A\0211\002\001\005\006-%\002\r\001\007\005\006E%\002\r\001\n\005\006a\001!\t%M\001\fEVLG\016Z*ue&tw\r\006\0023}A\0211g\017\b\003ier!!\016\035\016\003YR!a\016\005\002\rq\022xn\034;?\023\0059\021B\001\036\007\003\035\001\030mY6bO\026L!\001P\037\003\033M#(/\0338h\005VLG\016Z3s\025\tQd\001C\003@_\001\007!'\001\002tE\"9\021\tAA\001\n\003\021\025\001B2paf$2\001L\"E\021\0351\002\t%AA\002aAqA\t!\021\002\003\007A\005C\004G\001E\005I\021A$\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\t\001J\013\002\031\023.\n!\n\005\002L!6\tAJ\003\002N\035\006IQO\\2iK\016\\W\r\032\006\003\037\032\t!\"\0318o_R\fG/[8o\023\t\tFJA\tv]\016DWmY6fIZ\013'/[1oG\026Dqa\025\001\022\002\023\005A+\001\bd_BLH\005Z3gCVdG\017\n\032\026\003US#\001J%\t\017]\003\021\021!C!1\006i\001O]8ek\016$\bK]3gSb,\022!\027\t\0035~k\021a\027\006\0039v\013A\001\\1oO*\ta,\001\003kCZ\f\027BA\017\\\021\035\t\007!!A\005\002\t\fA\002\035:pIV\034G/\021:jif,\022a\031\t\003\037\021L!!\032\004\003\007%sG\017C\004h\001\005\005I\021\0015\002\035A\024x\016Z;di\026cW-\\3oiR\021\021\016\034\t\003\037)L!a\033\004\003\007\005s\027\020C\004nM\006\005\t\031A2\002\007a$\023\007C\004p\001\005\005I\021\t9\002\037A\024x\016Z;di&#XM]1u_J,\022!\035\t\004eVLW\"A:\013\005Q4\021AC2pY2,7\r^5p]&\021ao\035\002\t\023R,'/\031;pe\"9\001\020AA\001\n\003I\030\001C2b]\026\013X/\0317\025\005il\bCA\b|\023\tahAA\004C_>dW-\0318\t\0175<\030\021!a\001S\"Aq\020AA\001\n\003\n\t!\001\005iCND7i\0343f)\005\031\007\"CA\003\001\005\005I\021IA\004\003!!xn\025;sS:<G#A-\t\023\005-\001!!A\005B\0055\021AB3rk\006d7\017F\002{\003\037A\001\"\\A\005\003\003\005\r![\004\n\003'\021\021\021!E\001\003+\tABT8uCRLwN\034#fG2\0042aCA\f\r!\t!!!A\t\002\005e1#BA\f\0037\021\002cBA\017\003GAB\005L\007\003\003?Q1!!\t\007\003\035\021XO\034;j[\026LA!!\n\002 \t\t\022IY:ue\006\034GOR;oGRLwN\034\032\t\017)\n9\002\"\001\002*Q\021\021Q\003\005\013\003\013\t9\"!A\005F\005\035\001BCA\030\003/\t\t\021\"!\0022\005)\021\r\0359msR)A&a\r\0026!1a#!\fA\002aAaAIA\027\001\004!\003BCA\035\003/\t\t\021\"!\002<\0059QO\\1qa2LH\003BA\037\003\023\002RaDA \003\007J1!!\021\007\005\031y\005\017^5p]B)q\"!\022\031I%\031\021q\t\004\003\rQ+\b\017\\33\021%\tY%a\016\002\002\003\007A&A\002yIAB!\"a\024\002\030\005\005I\021BA)\003-\021X-\0313SKN|GN^3\025\005\005M\003c\001.\002V%\031\021qK.\003\r=\023'.Z2u\001")
/*    */ public class NotationDecl extends MarkupDecl implements Product, Serializable {
/*    */   private final String name;
/*    */   
/*    */   private final ExternalID extID;
/*    */   
/*    */   public String name() {
/* 80 */     return this.name;
/*    */   }
/*    */   
/*    */   public ExternalID extID() {
/* 80 */     return this.extID;
/*    */   }
/*    */   
/*    */   public NotationDecl copy(String name, ExternalID extID) {
/* 80 */     return new NotationDecl(name, extID);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 80 */     return name();
/*    */   }
/*    */   
/*    */   public ExternalID copy$default$2() {
/* 80 */     return extID();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 80 */     return "NotationDecl";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 80 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 80 */     switch (x$1) {
/*    */       default:
/* 80 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 80 */     return name();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 80 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 80 */     return x$1 instanceof NotationDecl;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 80 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 80 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 107
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/NotationDecl
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 111
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/NotationDecl
/*    */     //   27: astore #5
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual name : ()Ljava/lang/String;
/*    */     //   33: aload #5
/*    */     //   35: invokevirtual name : ()Ljava/lang/String;
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
/*    */     //   59: invokevirtual extID : ()Lscala/xml/dtd/ExternalID;
/*    */     //   62: aload #5
/*    */     //   64: invokevirtual extID : ()Lscala/xml/dtd/ExternalID;
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
/*    */     //   #80	-> 0
/*    */     //   #236	-> 12
/*    */     //   #80	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	113	0	this	Lscala/xml/dtd/NotationDecl;
/*    */     //   0	113	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public NotationDecl(String name, ExternalID extID) {
/* 80 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 82 */     sb.append("<!NOTATION ").append(name()).append(' ');
/* 83 */     return extID().buildString(sb);
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<String, ExternalID>, NotationDecl> tupled() {
/*    */     return NotationDecl$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<ExternalID, NotationDecl>> curried() {
/*    */     return NotationDecl$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\NotationDecl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */