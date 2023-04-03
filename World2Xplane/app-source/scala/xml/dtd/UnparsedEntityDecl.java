/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005-d\001B\001\003\001&\021!#\0268qCJ\034X\rZ#oi&$\030\020R3dY*\0211\001B\001\004IR$'BA\003\007\003\rAX\016\034\006\002\017\005)1oY1mC\016\0011\003\002\001\013\035I\001\"a\003\007\016\003\tI!!\004\002\003\025\025sG/\033;z\t\026\034G\016\005\002\020!5\ta!\003\002\022\r\t9\001K]8ek\016$\bCA\b\024\023\t!bA\001\007TKJL\027\r\\5{C\ndW\r\003\005\027\001\tU\r\021\"\001\030\003\021q\027-\\3\026\003a\001\"!\007\017\017\005=Q\022BA\016\007\003\031\001&/\0323fM&\021QD\b\002\007'R\024\030N\\4\013\005m1\001\002\003\021\001\005#\005\013\021\002\r\002\0139\fW.\032\021\t\021\t\002!Q3A\005\002\r\nQ!\032=u\023\022+\022\001\n\t\003\027\025J!A\n\002\003\025\025CH/\032:oC2LE\t\003\005)\001\tE\t\025!\003%\003\031)\007\020^%EA!A!\006\001BK\002\023\005q#\001\005o_R\fG/[8o\021!a\003A!E!\002\023A\022!\0038pi\006$\030n\0348!\021\025q\003\001\"\0010\003\031a\024N\\5u}Q!\001'\r\0324!\tY\001\001C\003\027[\001\007\001\004C\003#[\001\007A\005C\003+[\001\007\001\004C\0036\001\021\005c'A\006ck&dGm\025;sS:<GCA\034D!\tA\004I\004\002:}9\021!(P\007\002w)\021A\bC\001\007yI|w\016\036 \n\003\035I!a\020\004\002\017A\f7m[1hK&\021\021I\021\002\016'R\024\030N\\4Ck&dG-\032:\013\005}2\001\"\002#5\001\0049\024AA:c\021\0351\005!!A\005\002\035\013AaY8qsR!\001\007S%K\021\0351R\t%AA\002aAqAI#\021\002\003\007A\005C\004+\013B\005\t\031\001\r\t\0171\003\021\023!C\001\033\006q1m\0349zI\021,g-Y;mi\022\nT#\001(+\005ay5&\001)\021\005E3V\"\001*\013\005M#\026!C;oG\",7m[3e\025\t)f!\001\006b]:|G/\031;j_:L!a\026*\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004Z\001E\005I\021\001.\002\035\r|\007/\037\023eK\032\fW\017\034;%eU\t1L\013\002%\037\"9Q\fAI\001\n\003i\025AD2paf$C-\0324bk2$He\r\005\b?\002\t\t\021\"\021a\0035\001(o\0343vGR\004&/\0324jqV\t\021\r\005\002cO6\t1M\003\002eK\006!A.\0318h\025\0051\027\001\0026bm\006L!!H2\t\017%\004\021\021!C\001U\006a\001O]8ek\016$\030I]5usV\t1\016\005\002\020Y&\021QN\002\002\004\023:$\bbB8\001\003\003%\t\001]\001\017aJ|G-^2u\0132,W.\0328u)\t\tH\017\005\002\020e&\0211O\002\002\004\003:L\bbB;o\003\003\005\ra[\001\004q\022\n\004bB<\001\003\003%\t\005_\001\020aJ|G-^2u\023R,'/\031;peV\t\021\020E\002{{Fl\021a\037\006\003y\032\t!bY8mY\026\034G/[8o\023\tq8P\001\005Ji\026\024\030\r^8s\021%\t\t\001AA\001\n\003\t\031!\001\005dC:,\025/^1m)\021\t)!a\003\021\007=\t9!C\002\002\n\031\021qAQ8pY\026\fg\016C\004v\006\005\t\031A9\t\023\005=\001!!A\005B\005E\021\001\0035bg\"\034u\016Z3\025\003-D\021\"!\006\001\003\003%\t%a\006\002\021Q|7\013\036:j]\036$\022!\031\005\n\0037\001\021\021!C!\003;\ta!Z9vC2\034H\003BA\003\003?A\001\"^A\r\003\003\005\r!]\004\n\003G\021\021\021!E\001\003K\t!#\0268qCJ\034X\rZ#oi&$\030\020R3dYB\0311\"a\n\007\021\005\021\021\021!E\001\003S\031R!a\n\002,I\001\002\"!\f\0024a!\003\004M\007\003\003_Q1!!\r\007\003\035\021XO\034;j[\026LA!!\016\0020\t\t\022IY:ue\006\034GOR;oGRLwN\\\032\t\0179\n9\003\"\001\002:Q\021\021Q\005\005\013\003+\t9#!A\005F\005]\001BCA \003O\t\t\021\"!\002B\005)\021\r\0359msR9\001'a\021\002F\005\035\003B\002\f\002>\001\007\001\004\003\004#\003{\001\r\001\n\005\007U\005u\002\031\001\r\t\025\005-\023qEA\001\n\003\013i%A\004v]\006\004\b\017\\=\025\t\005=\0231\f\t\006\037\005E\023QK\005\004\003'2!AB(qi&|g\016\005\004\020\003/BB\005G\005\004\00332!A\002+va2,7\007C\005\002^\005%\023\021!a\001a\005\031\001\020\n\031\t\025\005\005\024qEA\001\n\023\t\031'A\006sK\006$'+Z:pYZ,GCAA3!\r\021\027qM\005\004\003S\032'AB(cU\026\034G\017")
/*    */ public class UnparsedEntityDecl extends EntityDecl implements Product, Serializable {
/*    */   private final String name;
/*    */   
/*    */   private final ExternalID extID;
/*    */   
/*    */   private final String notation;
/*    */   
/*    */   public String name() {
/* 73 */     return this.name;
/*    */   }
/*    */   
/*    */   public ExternalID extID() {
/* 73 */     return this.extID;
/*    */   }
/*    */   
/*    */   public String notation() {
/* 73 */     return this.notation;
/*    */   }
/*    */   
/*    */   public UnparsedEntityDecl copy(String name, ExternalID extID, String notation) {
/* 73 */     return new UnparsedEntityDecl(name, extID, notation);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 73 */     return name();
/*    */   }
/*    */   
/*    */   public ExternalID copy$default$2() {
/* 73 */     return extID();
/*    */   }
/*    */   
/*    */   public String copy$default$3() {
/* 73 */     return notation();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 73 */     return "UnparsedEntityDecl";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 73 */     return 3;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 73 */     switch (x$1) {
/*    */       default:
/* 73 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 73 */     return name();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 73 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 73 */     return x$1 instanceof UnparsedEntityDecl;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 73 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 73 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 139
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/UnparsedEntityDecl
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 143
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/UnparsedEntityDecl
/*    */     //   27: astore #6
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual name : ()Ljava/lang/String;
/*    */     //   33: aload #6
/*    */     //   35: invokevirtual name : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 135
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 135
/*    */     //   58: aload_0
/*    */     //   59: invokevirtual extID : ()Lscala/xml/dtd/ExternalID;
/*    */     //   62: aload #6
/*    */     //   64: invokevirtual extID : ()Lscala/xml/dtd/ExternalID;
/*    */     //   67: astore #4
/*    */     //   69: dup
/*    */     //   70: ifnonnull -> 82
/*    */     //   73: pop
/*    */     //   74: aload #4
/*    */     //   76: ifnull -> 90
/*    */     //   79: goto -> 135
/*    */     //   82: aload #4
/*    */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   87: ifeq -> 135
/*    */     //   90: aload_0
/*    */     //   91: invokevirtual notation : ()Ljava/lang/String;
/*    */     //   94: aload #6
/*    */     //   96: invokevirtual notation : ()Ljava/lang/String;
/*    */     //   99: astore #5
/*    */     //   101: dup
/*    */     //   102: ifnonnull -> 114
/*    */     //   105: pop
/*    */     //   106: aload #5
/*    */     //   108: ifnull -> 122
/*    */     //   111: goto -> 135
/*    */     //   114: aload #5
/*    */     //   116: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   119: ifeq -> 135
/*    */     //   122: aload #6
/*    */     //   124: aload_0
/*    */     //   125: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   128: ifeq -> 135
/*    */     //   131: iconst_1
/*    */     //   132: goto -> 136
/*    */     //   135: iconst_0
/*    */     //   136: ifeq -> 143
/*    */     //   139: iconst_1
/*    */     //   140: goto -> 144
/*    */     //   143: iconst_0
/*    */     //   144: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #73	-> 0
/*    */     //   #236	-> 12
/*    */     //   #73	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	145	0	this	Lscala/xml/dtd/UnparsedEntityDecl;
/*    */     //   0	145	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public UnparsedEntityDecl(String name, ExternalID extID, String notation) {
/* 73 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 75 */     sb.append("<!ENTITY ").append(name()).append(' ');
/* 76 */     return extID().buildString(sb).append(" NDATA ").append(notation()).append('>');
/*    */   }
/*    */   
/*    */   public static Function1<Tuple3<String, ExternalID, String>, UnparsedEntityDecl> tupled() {
/*    */     return UnparsedEntityDecl$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<ExternalID, Function1<String, UnparsedEntityDecl>>> curried() {
/*    */     return UnparsedEntityDecl$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\UnparsedEntityDecl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */