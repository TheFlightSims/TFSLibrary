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
/*    */ @ScalaSignature(bytes = "\006\001\005ec\001B\001\003\001&\021\001\003U1sg\026$WI\034;jif$Um\0317\013\005\r!\021a\0013uI*\021QAB\001\004q6d'\"A\004\002\013M\034\027\r\\1\004\001M!\001A\003\b\023!\tYA\"D\001\003\023\ti!A\001\006F]RLG/\037#fG2\004\"a\004\t\016\003\031I!!\005\004\003\017A\023x\016Z;diB\021qbE\005\003)\031\021AbU3sS\006d\027N_1cY\026D\001B\006\001\003\026\004%\taF\001\005]\006lW-F\001\031!\tIBD\004\002\0205%\0211DB\001\007!J,G-\0324\n\005uq\"AB*ue&twM\003\002\034\r!A\001\005\001B\tB\003%\001$A\003oC6,\007\005\003\005#\001\tU\r\021\"\001$\003\031)g\016\0363fMV\tA\005\005\002\fK%\021aE\001\002\n\013:$\030\016^=EK\032D\001\002\013\001\003\022\003\006I\001J\001\bK:$H-\0324!\021\025Q\003\001\"\001,\003\031a\024N\\5u}Q\031A&\f\030\021\005-\001\001\"\002\f*\001\004A\002\"\002\022*\001\004!\003\"\002\031\001\t\003\n\024a\0032vS2$7\013\036:j]\036$\"A\r \021\005MZdB\001\033:\035\t)\004(D\0017\025\t9\004\"\001\004=e>|GOP\005\002\017%\021!HB\001\ba\006\0347.Y4f\023\taTHA\007TiJLgn\032\"vS2$WM\035\006\003u\031AQaP\030A\002I\n!a\0352\t\017\005\003\021\021!C\001\005\006!1m\0349z)\ra3\t\022\005\b-\001\003\n\0211\001\031\021\035\021\003\t%AA\002\021BqA\022\001\022\002\023\005q)\001\bd_BLH\005Z3gCVdG\017J\031\026\003!S#\001G%,\003)\003\"a\023)\016\0031S!!\024(\002\023Ut7\r[3dW\026$'BA(\007\003)\tgN\\8uCRLwN\\\005\003#2\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035\031\006!%A\005\002Q\013abY8qs\022\"WMZ1vYR$#'F\001VU\t!\023\nC\004X\001\005\005I\021\t-\002\033A\024x\016Z;diB\023XMZ5y+\005I\006C\001.`\033\005Y&B\001/^\003\021a\027M\\4\013\003y\013AA[1wC&\021Qd\027\005\bC\002\t\t\021\"\001c\0031\001(o\0343vGR\f%/\033;z+\005\031\007CA\be\023\t)gAA\002J]RDqa\032\001\002\002\023\005\001.\001\bqe>$Wo\031;FY\026lWM\034;\025\005%d\007CA\bk\023\tYgAA\002B]fDq!\0344\002\002\003\0071-A\002yIEBqa\034\001\002\002\023\005\003/A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005\t\bc\001:vS6\t1O\003\002u\r\005Q1m\0347mK\016$\030n\0348\n\005Y\034(\001C%uKJ\fGo\034:\t\017a\004\021\021!C\001s\006A1-\0318FcV\fG\016\006\002{{B\021qb_\005\003y\032\021qAQ8pY\026\fg\016C\004no\006\005\t\031A5\t\021}\004\021\021!C!\003\003\t\001\002[1tQ\016{G-\032\013\002G\"I\021Q\001\001\002\002\023\005\023qA\001\ti>\034FO]5oOR\t\021\fC\005\002\f\001\t\t\021\"\021\002\016\0051Q-];bYN$2A_A\b\021!i\027\021BA\001\002\004Iw!CA\n\005\005\005\t\022AA\013\003A\001\026M]:fI\026sG/\033;z\t\026\034G\016E\002\f\003/1\001\"\001\002\002\002#\005\021\021D\n\006\003/\tYB\005\t\b\003;\t\031\003\007\023-\033\t\tyBC\002\002\"\031\tqA];oi&lW-\003\003\002&\005}!!E!cgR\024\030m\031;Gk:\034G/[8oe!9!&a\006\005\002\005%BCAA\013\021)\t)!a\006\002\002\023\025\023q\001\005\013\003_\t9\"!A\005\002\006E\022!B1qa2LH#\002\027\0024\005U\002B\002\f\002.\001\007\001\004\003\004#\003[\001\r\001\n\005\013\003s\t9\"!A\005\002\006m\022aB;oCB\004H.\037\013\005\003{\tI\005E\003\020\003\t\031%C\002\002B\031\021aa\0249uS>t\007#B\b\002Fa!\023bAA$\r\t1A+\0369mKJB\021\"a\023\0028\005\005\t\031\001\027\002\007a$\003\007\003\006\002P\005]\021\021!C\005\003#\n1B]3bIJ+7o\0347wKR\021\0211\013\t\0045\006U\023bAA,7\n1qJ\0316fGR\004")
/*    */ public class ParsedEntityDecl extends EntityDecl implements Product, Serializable {
/*    */   private final String name;
/*    */   
/*    */   private final EntityDef entdef;
/*    */   
/*    */   public String name() {
/* 57 */     return this.name;
/*    */   }
/*    */   
/*    */   public EntityDef entdef() {
/* 57 */     return this.entdef;
/*    */   }
/*    */   
/*    */   public ParsedEntityDecl copy(String name, EntityDef entdef) {
/* 57 */     return new ParsedEntityDecl(name, entdef);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 57 */     return name();
/*    */   }
/*    */   
/*    */   public EntityDef copy$default$2() {
/* 57 */     return entdef();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 57 */     return "ParsedEntityDecl";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 57 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 57 */     switch (x$1) {
/*    */       default:
/* 57 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 57 */     return name();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 57 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 57 */     return x$1 instanceof ParsedEntityDecl;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 57 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 57 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 107
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/ParsedEntityDecl
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 111
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/ParsedEntityDecl
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
/*    */     //   59: invokevirtual entdef : ()Lscala/xml/dtd/EntityDef;
/*    */     //   62: aload #5
/*    */     //   64: invokevirtual entdef : ()Lscala/xml/dtd/EntityDef;
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
/*    */     //   #57	-> 0
/*    */     //   #236	-> 12
/*    */     //   #57	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	113	0	this	Lscala/xml/dtd/ParsedEntityDecl;
/*    */     //   0	113	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ParsedEntityDecl(String name, EntityDef entdef) {
/* 57 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 59 */     sb.append("<!ENTITY ").append(name()).append(' ');
/* 60 */     return entdef().buildString(sb).append('>');
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<String, EntityDef>, ParsedEntityDecl> tupled() {
/*    */     return ParsedEntityDecl$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<EntityDef, ParsedEntityDecl>> curried() {
/*    */     return ParsedEntityDecl$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ParsedEntityDecl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */