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
/*    */ @ScalaSignature(bytes = "\006\001\005ec\001B\001\003\001&\0211\003U1sC6,G/\032:F]RLG/\037#fG2T!a\001\003\002\007\021$HM\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001a\005\003\001\0259\021\002CA\006\r\033\005\021\021BA\007\003\005))e\016^5us\022+7\r\034\t\003\037Ai\021AB\005\003#\031\021q\001\025:pIV\034G\017\005\002\020'%\021AC\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\t-\001\021)\032!C\001/\005!a.Y7f+\005A\002CA\r\035\035\ty!$\003\002\034\r\0051\001K]3eK\032L!!\b\020\003\rM#(/\0338h\025\tYb\001\003\005!\001\tE\t\025!\003\031\003\025q\027-\\3!\021!\021\003A!f\001\n\003\031\023AB3oi\022,g-F\001%!\tYQ%\003\002'\005\tIQI\034;jif$UM\032\005\tQ\001\021\t\022)A\005I\0059QM\034;eK\032\004\003\"\002\026\001\t\003Y\023A\002\037j]&$h\bF\002-[9\002\"a\003\001\t\013YI\003\031\001\r\t\013\tJ\003\031\001\023\t\013A\002A\021I\031\002\027\t,\030\016\0343TiJLgn\032\013\003ey\002\"aM\036\017\005QJdBA\0339\033\0051$BA\034\t\003\031a$o\\8u}%\tq!\003\002;\r\0059\001/Y2lC\036,\027B\001\037>\0055\031FO]5oO\n+\030\016\0343fe*\021!H\002\005\006=\002\rAM\001\003g\nDq!\021\001\002\002\023\005!)\001\003d_BLHc\001\027D\t\"9a\003\021I\001\002\004A\002b\002\022A!\003\005\r\001\n\005\b\r\002\t\n\021\"\001H\0039\031w\016]=%I\0264\027-\0367uIE*\022\001\023\026\0031%[\023A\023\t\003\027Bk\021\001\024\006\003\033:\013\021\"\0368dQ\026\0347.\0323\013\005=3\021AC1o]>$\030\r^5p]&\021\021\013\024\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007bB*\001#\003%\t\001V\001\017G>\004\030\020\n3fM\006,H\016\036\0233+\005)&F\001\023J\021\0359\006!!A\005Ba\013Q\002\035:pIV\034G\017\025:fM&DX#A-\021\005i{V\"A.\013\005qk\026\001\0027b]\036T\021AX\001\005U\0064\030-\003\002\0367\"9\021\rAA\001\n\003\021\027\001\0049s_\022,8\r^!sSRLX#A2\021\005=!\027BA3\007\005\rIe\016\036\005\bO\002\t\t\021\"\001i\0039\001(o\0343vGR,E.Z7f]R$\"!\0337\021\005=Q\027BA6\007\005\r\te.\037\005\b[\032\f\t\0211\001d\003\rAH%\r\005\b_\002\t\t\021\"\021q\003=\001(o\0343vGRLE/\032:bi>\024X#A9\021\007I,\030.D\001t\025\t!h!\001\006d_2dWm\031;j_:L!A^:\003\021%#XM]1u_JDq\001\037\001\002\002\023\005\0210\001\005dC:,\025/^1m)\tQX\020\005\002\020w&\021AP\002\002\b\005>|G.Z1o\021\035iw/!AA\002%D\001b \001\002\002\023\005\023\021A\001\tQ\006\034\bnQ8eKR\t1\rC\005\002\006\001\t\t\021\"\021\002\b\005AAo\\*ue&tw\rF\001Z\021%\tY\001AA\001\n\003\ni!\001\004fcV\fGn\035\013\004u\006=\001\002C7\002\n\005\005\t\031A5\b\023\005M!!!A\t\002\005U\021a\005)be\006lW\r^3s\013:$\030\016^=EK\016d\007cA\006\002\030\031A\021AAA\001\022\003\tIbE\003\002\030\005m!\003E\004\002\036\005\r\002\004\n\027\016\005\005}!bAA\021\r\0059!/\0368uS6,\027\002BA\023\003?\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03483\021\035Q\023q\003C\001\003S!\"!!\006\t\025\005\025\021qCA\001\n\013\n9\001\003\006\0020\005]\021\021!CA\003c\tQ!\0319qYf$R\001LA\032\003kAaAFA\027\001\004A\002B\002\022\002.\001\007A\005\003\006\002:\005]\021\021!CA\003w\tq!\0368baBd\027\020\006\003\002>\005%\003#B\b\002@\005\r\023bAA!\r\t1q\n\035;j_:\004RaDA#1\021J1!a\022\007\005\031!V\017\0357fe!I\0211JA\034\003\003\005\r\001L\001\004q\022\002\004BCA(\003/\t\t\021\"\003\002R\005Y!/Z1e%\026\034x\016\034<f)\t\t\031\006E\002[\003+J1!a\026\\\005\031y%M[3di\002")
/*    */ public class ParameterEntityDecl extends EntityDecl implements Product, Serializable {
/*    */   private final String name;
/*    */   
/*    */   private final EntityDef entdef;
/*    */   
/*    */   public String name() {
/* 65 */     return this.name;
/*    */   }
/*    */   
/*    */   public EntityDef entdef() {
/* 65 */     return this.entdef;
/*    */   }
/*    */   
/*    */   public ParameterEntityDecl copy(String name, EntityDef entdef) {
/* 65 */     return new ParameterEntityDecl(name, entdef);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 65 */     return name();
/*    */   }
/*    */   
/*    */   public EntityDef copy$default$2() {
/* 65 */     return entdef();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 65 */     return "ParameterEntityDecl";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 65 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 65 */     switch (x$1) {
/*    */       default:
/* 65 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 65 */     return name();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 65 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 65 */     return x$1 instanceof ParameterEntityDecl;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 65 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 65 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 107
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/ParameterEntityDecl
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 111
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/ParameterEntityDecl
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
/*    */     //   #65	-> 0
/*    */     //   #236	-> 12
/*    */     //   #65	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	113	0	this	Lscala/xml/dtd/ParameterEntityDecl;
/*    */     //   0	113	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ParameterEntityDecl(String name, EntityDef entdef) {
/* 65 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 67 */     sb.append("<!ENTITY % ").append(name()).append(' ');
/* 68 */     return entdef().buildString(sb).append('>');
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<String, EntityDef>, ParameterEntityDecl> tupled() {
/*    */     return ParameterEntityDecl$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<EntityDef, ParameterEntityDecl>> curried() {
/*    */     return ParameterEntityDecl$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ParameterEntityDecl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */