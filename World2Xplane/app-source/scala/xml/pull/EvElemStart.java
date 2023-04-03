/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple4;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=d\001B\001\003\001&\0211\"\022<FY\026l7\013^1si*\0211\001B\001\005aVdGN\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001aE\003\001\0259\021R\003\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\021akE*\022<f]R\004\"aC\n\n\005Q1!a\002)s_\022,8\r\036\t\003\027YI!a\006\004\003\031M+'/[1mSj\f'\r\\3\t\021e\001!Q3A\005\002i\t1\001\035:f+\005Y\002C\001\017 \035\tYQ$\003\002\037\r\0051\001K]3eK\032L!\001I\021\003\rM#(/\0338h\025\tqb\001\003\005$\001\tE\t\025!\003\034\003\021\001(/\032\021\t\021\025\002!Q3A\005\002i\tQ\001\\1cK2D\001b\n\001\003\022\003\006IaG\001\007Y\006\024W\r\034\021\t\021%\002!Q3A\005\002)\nQ!\031;ueN,\022a\013\t\003Y5j\021\001B\005\003]\021\021\001\"T3uC\022\013G/\031\005\ta\001\021\t\022)A\005W\0051\021\r\036;sg\002B\001B\r\001\003\026\004%\taM\001\006g\016|\007/Z\013\002iA\021A&N\005\003m\021\021\001CT1nKN\004\030mY3CS:$\027N\\4\t\021a\002!\021#Q\001\nQ\naa]2pa\026\004\003\"\002\036\001\t\003Y\024A\002\037j]&$h\bF\003={yz\004\t\005\002\020\001!)\021$\017a\0017!)Q%\017a\0017!)\021&\017a\001W!)!'\017a\001i!9!\tAA\001\n\003\031\025\001B2paf$R\001\020#F\r\036Cq!G!\021\002\003\0071\004C\004&\003B\005\t\031A\016\t\017%\n\005\023!a\001W!9!'\021I\001\002\004!\004bB%\001#\003%\tAS\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005Y%FA\016MW\005i\005C\001(T\033\005y%B\001)R\003%)hn\0315fG.,GM\003\002S\r\005Q\021M\0348pi\006$\030n\0348\n\005Q{%!E;oG\",7m[3e-\006\024\030.\0318dK\"9a\013AI\001\n\003Q\025AD2paf$C-\0324bk2$HE\r\005\b1\002\t\n\021\"\001Z\0039\031w\016]=%I\0264\027-\0367uIM*\022A\027\026\003W1Cq\001\030\001\022\002\023\005Q,\001\bd_BLH\005Z3gCVdG\017\n\033\026\003yS#\001\016'\t\017\001\004\021\021!C!C\006i\001O]8ek\016$\bK]3gSb,\022A\031\t\003G\"l\021\001\032\006\003K\032\fA\001\\1oO*\tq-\001\003kCZ\f\027B\001\021e\021\035Q\007!!A\005\002-\fA\002\035:pIV\034G/\021:jif,\022\001\034\t\003\0275L!A\034\004\003\007%sG\017C\004q\001\005\005I\021A9\002\035A\024x\016Z;di\026cW-\\3oiR\021!/\036\t\003\027ML!\001\036\004\003\007\005s\027\020C\004w_\006\005\t\031\0017\002\007a$\023\007C\004y\001\005\005I\021I=\002\037A\024x\016Z;di&#XM]1u_J,\022A\037\t\004wz\024X\"\001?\013\005u4\021AC2pY2,7\r^5p]&\021q\020 \002\t\023R,'/\031;pe\"I\0211\001\001\002\002\023\005\021QA\001\tG\006tW)];bYR!\021qAA\007!\rY\021\021B\005\004\003\0271!a\002\"p_2,\027M\034\005\tm\006\005\021\021!a\001e\"I\021\021\003\001\002\002\023\005\0231C\001\tQ\006\034\bnQ8eKR\tA\016C\005\002\030\001\t\t\021\"\021\002\032\005AAo\\*ue&tw\rF\001c\021%\ti\002AA\001\n\003\ny\"\001\004fcV\fGn\035\013\005\003\017\t\t\003\003\005w\0037\t\t\0211\001s\017%\t)CAA\001\022\003\t9#A\006Fm\026cW-\\*uCJ$\bcA\b\002*\031A\021AAA\001\022\003\tYcE\003\002*\0055R\003E\005\0020\005U2dG\0265y5\021\021\021\007\006\004\003g1\021a\002:v]RLW.Z\005\005\003o\t\tDA\tBEN$(/Y2u\rVt7\r^5p]RBqAOA\025\t\003\tY\004\006\002\002(!Q\021qCA\025\003\003%)%!\007\t\025\005\005\023\021FA\001\n\003\013\031%A\003baBd\027\020F\005=\003\013\n9%!\023\002L!1\021$a\020A\002mAa!JA \001\004Y\002BB\025\002@\001\0071\006\003\0043\003\001\r\001\016\005\013\003\037\nI#!A\005\002\006E\023aB;oCB\004H.\037\013\005\003'\ny\006E\003\f\003+\nI&C\002\002X\031\021aa\0249uS>t\007cB\006\002\\mY2\006N\005\004\003;2!A\002+va2,G\007C\005\002b\0055\023\021!a\001y\005\031\001\020\n\031\t\025\005\025\024\021FA\001\n\023\t9'A\006sK\006$'+Z:pYZ,GCAA5!\r\031\0271N\005\004\003[\"'AB(cU\026\034G\017")
/*    */ public class EvElemStart implements XMLEvent, Product, Serializable {
/*    */   private final String pre;
/*    */   
/*    */   private final String label;
/*    */   
/*    */   private final MetaData attrs;
/*    */   
/*    */   private final NamespaceBinding scope;
/*    */   
/*    */   public static Function1<Tuple4<String, String, MetaData, NamespaceBinding>, EvElemStart> tupled() {
/*    */     return EvElemStart$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<String, Function1<MetaData, Function1<NamespaceBinding, EvElemStart>>>> curried() {
/*    */     return EvElemStart$.MODULE$.curried();
/*    */   }
/*    */   
/*    */   public String pre() {
/* 25 */     return this.pre;
/*    */   }
/*    */   
/*    */   public String label() {
/* 25 */     return this.label;
/*    */   }
/*    */   
/*    */   public MetaData attrs() {
/* 25 */     return this.attrs;
/*    */   }
/*    */   
/*    */   public NamespaceBinding scope() {
/* 25 */     return this.scope;
/*    */   }
/*    */   
/*    */   public EvElemStart copy(String pre, String label, MetaData attrs, NamespaceBinding scope) {
/* 25 */     return new EvElemStart(pre, label, attrs, scope);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 25 */     return pre();
/*    */   }
/*    */   
/*    */   public String copy$default$2() {
/* 25 */     return label();
/*    */   }
/*    */   
/*    */   public MetaData copy$default$3() {
/* 25 */     return attrs();
/*    */   }
/*    */   
/*    */   public NamespaceBinding copy$default$4() {
/* 25 */     return scope();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 25 */     return "EvElemStart";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 25 */     return 4;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 25 */     switch (x$1) {
/*    */       default:
/* 25 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 3:
/*    */       
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 25 */     return pre();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 25 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 25 */     return x$1 instanceof EvElemStart;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 25 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 25 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 171
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/pull/EvElemStart
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 175
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/pull/EvElemStart
/*    */     //   27: astore #7
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual pre : ()Ljava/lang/String;
/*    */     //   33: aload #7
/*    */     //   35: invokevirtual pre : ()Ljava/lang/String;
/*    */     //   38: astore_3
/*    */     //   39: dup
/*    */     //   40: ifnonnull -> 51
/*    */     //   43: pop
/*    */     //   44: aload_3
/*    */     //   45: ifnull -> 58
/*    */     //   48: goto -> 167
/*    */     //   51: aload_3
/*    */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   55: ifeq -> 167
/*    */     //   58: aload_0
/*    */     //   59: invokevirtual label : ()Ljava/lang/String;
/*    */     //   62: aload #7
/*    */     //   64: invokevirtual label : ()Ljava/lang/String;
/*    */     //   67: astore #4
/*    */     //   69: dup
/*    */     //   70: ifnonnull -> 82
/*    */     //   73: pop
/*    */     //   74: aload #4
/*    */     //   76: ifnull -> 90
/*    */     //   79: goto -> 167
/*    */     //   82: aload #4
/*    */     //   84: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   87: ifeq -> 167
/*    */     //   90: aload_0
/*    */     //   91: invokevirtual attrs : ()Lscala/xml/MetaData;
/*    */     //   94: aload #7
/*    */     //   96: invokevirtual attrs : ()Lscala/xml/MetaData;
/*    */     //   99: astore #5
/*    */     //   101: dup
/*    */     //   102: ifnonnull -> 114
/*    */     //   105: pop
/*    */     //   106: aload #5
/*    */     //   108: ifnull -> 122
/*    */     //   111: goto -> 167
/*    */     //   114: aload #5
/*    */     //   116: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   119: ifeq -> 167
/*    */     //   122: aload_0
/*    */     //   123: invokevirtual scope : ()Lscala/xml/NamespaceBinding;
/*    */     //   126: aload #7
/*    */     //   128: invokevirtual scope : ()Lscala/xml/NamespaceBinding;
/*    */     //   131: astore #6
/*    */     //   133: dup
/*    */     //   134: ifnonnull -> 146
/*    */     //   137: pop
/*    */     //   138: aload #6
/*    */     //   140: ifnull -> 154
/*    */     //   143: goto -> 167
/*    */     //   146: aload #6
/*    */     //   148: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   151: ifeq -> 167
/*    */     //   154: aload #7
/*    */     //   156: aload_0
/*    */     //   157: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   160: ifeq -> 167
/*    */     //   163: iconst_1
/*    */     //   164: goto -> 168
/*    */     //   167: iconst_0
/*    */     //   168: ifeq -> 175
/*    */     //   171: iconst_1
/*    */     //   172: goto -> 176
/*    */     //   175: iconst_0
/*    */     //   176: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #25	-> 0
/*    */     //   #236	-> 12
/*    */     //   #25	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	177	0	this	Lscala/xml/pull/EvElemStart;
/*    */     //   0	177	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public EvElemStart(String pre, String label, MetaData attrs, NamespaceBinding scope) {
/* 25 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvElemStart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */