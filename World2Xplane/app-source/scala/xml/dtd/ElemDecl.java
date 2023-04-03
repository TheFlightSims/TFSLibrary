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
/*    */ @ScalaSignature(bytes = "\006\001\005ec\001B\001\003\001&\021\001\"\0227f[\022+7\r\034\006\003\007\021\t1\001\032;e\025\t)a!A\002y[2T\021aB\001\006g\016\fG.Y\002\001'\021\001!B\004\n\021\005-aQ\"\001\002\n\0055\021!AC'be.,\b\017R3dYB\021q\002E\007\002\r%\021\021C\002\002\b!J|G-^2u!\ty1#\003\002\025\r\ta1+\032:jC2L'0\0312mK\"Aa\003\001BK\002\023\005q#\001\003oC6,W#\001\r\021\005eabBA\b\033\023\tYb!\001\004Qe\026$WMZ\005\003;y\021aa\025;sS:<'BA\016\007\021!\001\003A!E!\002\023A\022!\0028b[\026\004\003\002\003\022\001\005+\007I\021A\022\002\031\r|g\016^3oi6{G-\0327\026\003\021\002\"aC\023\n\005\031\022!\001D\"p]R,g\016^'pI\026d\007\002\003\025\001\005#\005\013\021\002\023\002\033\r|g\016^3oi6{G-\0327!\021\025Q\003\001\"\001,\003\031a\024N\\5u}Q\031A&\f\030\021\005-\001\001\"\002\f*\001\004A\002\"\002\022*\001\004!\003\"\002\031\001\t\003\n\024a\0032vS2$7\013\036:j]\036$\"A\r \021\005MZdB\001\033:\035\t)\004(D\0017\025\t9\004\"\001\004=e>|GOP\005\002\017%\021!HB\001\ba\006\0347.Y4f\023\taTHA\007TiJLgn\032\"vS2$WM\035\006\003u\031AQaP\030A\002I\n!a\0352\t\017\005\003\021\021!C\001\005\006!1m\0349z)\ra3\t\022\005\b-\001\003\n\0211\001\031\021\035\021\003\t%AA\002\021BqA\022\001\022\002\023\005q)\001\bd_BLH\005Z3gCVdG\017J\031\026\003!S#\001G%,\003)\003\"a\023)\016\0031S!!\024(\002\023Ut7\r[3dW\026$'BA(\007\003)\tgN\\8uCRLwN\\\005\003#2\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035\031\006!%A\005\002Q\013abY8qs\022\"WMZ1vYR$#'F\001VU\t!\023\nC\004X\001\005\005I\021\t-\002\033A\024x\016Z;diB\023XMZ5y+\005I\006C\001.`\033\005Y&B\001/^\003\021a\027M\\4\013\003y\013AA[1wC&\021Qd\027\005\bC\002\t\t\021\"\001c\0031\001(o\0343vGR\f%/\033;z+\005\031\007CA\be\023\t)gAA\002J]RDqa\032\001\002\002\023\005\001.\001\bqe>$Wo\031;FY\026lWM\034;\025\005%d\007CA\bk\023\tYgAA\002B]fDq!\0344\002\002\003\0071-A\002yIEBqa\034\001\002\002\023\005\003/A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005\t\bc\001:vS6\t1O\003\002u\r\005Q1m\0347mK\016$\030n\0348\n\005Y\034(\001C%uKJ\fGo\034:\t\017a\004\021\021!C\001s\006A1-\0318FcV\fG\016\006\002{{B\021qb_\005\003y\032\021qAQ8pY\026\fg\016C\004no\006\005\t\031A5\t\021}\004\021\021!C!\003\003\t\001\002[1tQ\016{G-\032\013\002G\"I\021Q\001\001\002\002\023\005\023qA\001\ti>\034FO]5oOR\t\021\fC\005\002\f\001\t\t\021\"\021\002\016\0051Q-];bYN$2A_A\b\021!i\027\021BA\001\002\004Iw!CA\n\005\005\005\t\022AA\013\003!)E.Z7EK\016d\007cA\006\002\030\031A\021AAA\001\022\003\tIbE\003\002\030\005m!\003E\004\002\036\005\r\002\004\n\027\016\005\005}!bAA\021\r\0059!/\0368uS6,\027\002BA\023\003?\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03483\021\035Q\023q\003C\001\003S!\"!!\006\t\025\005\025\021qCA\001\n\013\n9\001\003\006\0020\005]\021\021!CA\003c\tQ!\0319qYf$R\001LA\032\003kAaAFA\027\001\004A\002B\002\022\002.\001\007A\005\003\006\002:\005]\021\021!CA\003w\tq!\0368baBd\027\020\006\003\002>\005%\003#B\b\002@\005\r\023bAA!\r\t1q\n\035;j_:\004RaDA#1\021J1!a\022\007\005\031!V\017\0357fe!I\0211JA\034\003\003\005\r\001L\001\004q\022\002\004BCA(\003/\t\t\021\"\003\002R\005Y!/Z1e%\026\034x\016\034<f)\t\t\031\006E\002[\003+J1!a\026\\\005\031y%M[3di\002")
/*    */ public class ElemDecl extends MarkupDecl implements Product, Serializable {
/*    */   private final String name;
/*    */   
/*    */   private final ContentModel contentModel;
/*    */   
/*    */   public String name() {
/* 22 */     return this.name;
/*    */   }
/*    */   
/*    */   public ContentModel contentModel() {
/* 22 */     return this.contentModel;
/*    */   }
/*    */   
/*    */   public ElemDecl copy(String name, ContentModel contentModel) {
/* 22 */     return new ElemDecl(name, contentModel);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 22 */     return name();
/*    */   }
/*    */   
/*    */   public ContentModel copy$default$2() {
/* 22 */     return contentModel();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 22 */     return "ElemDecl";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 22 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 22 */     switch (x$1) {
/*    */       default:
/* 22 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 22 */     return name();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 22 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 22 */     return x$1 instanceof ElemDecl;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 22 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 22 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 107
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/ElemDecl
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 111
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/ElemDecl
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
/*    */     //   59: invokevirtual contentModel : ()Lscala/xml/dtd/ContentModel;
/*    */     //   62: aload #5
/*    */     //   64: invokevirtual contentModel : ()Lscala/xml/dtd/ContentModel;
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
/*    */     //   #22	-> 0
/*    */     //   #236	-> 12
/*    */     //   #22	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	113	0	this	Lscala/xml/dtd/ElemDecl;
/*    */     //   0	113	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ElemDecl(String name, ContentModel contentModel) {
/* 22 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 25 */     sb.append("<!ELEMENT ").append(name()).append(' ');
/* 27 */     ContentModel$.MODULE$.buildString(contentModel(), sb);
/* 28 */     return sb.append('>');
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<String, ContentModel>, ElemDecl> tupled() {
/*    */     return ElemDecl$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<ContentModel, ElemDecl>> curried() {
/*    */     return ElemDecl$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ElemDecl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */