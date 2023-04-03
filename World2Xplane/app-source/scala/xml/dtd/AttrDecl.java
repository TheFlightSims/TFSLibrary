/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.xml.Utility$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055d\001B\001\003\001&\021\001\"\021;ue\022+7\r\034\006\003\007\021\t1\001\032;e\025\t)a!A\002y[2T\021aB\001\006g\016\fG.Y\002\001'\021\001!BD\t\021\005-aQ\"\001\004\n\00551!AB!osJ+g\r\005\002\f\037%\021\001C\002\002\b!J|G-^2u!\tY!#\003\002\024\r\ta1+\032:jC2L'0\0312mK\"AQ\003\001BK\002\023\005a#\001\003oC6,W#A\f\021\005aYbBA\006\032\023\tQb!\001\004Qe\026$WMZ\005\0039u\021aa\025;sS:<'B\001\016\007\021!y\002A!E!\002\0239\022!\0028b[\026\004\003\002C\021\001\005+\007I\021\001\f\002\007Q\004X\r\003\005$\001\tE\t\025!\003\030\003\021!\b/\032\021\t\021\025\002!Q3A\005\002\031\nq\001Z3gCVdG/F\001(!\tA\023&D\001\003\023\tQ#AA\006EK\032\fW\017\034;EK\016d\007\002\003\027\001\005#\005\013\021B\024\002\021\021,g-Y;mi\002BQA\f\001\005\002=\na\001P5oSRtD\003\002\0312eM\002\"\001\013\001\t\013Ui\003\031A\f\t\013\005j\003\031A\f\t\013\025j\003\031A\024\t\013U\002A\021\t\034\002\021Q|7\013\036:j]\036$\022a\006\005\006q\001!\t!O\001\fEVLG\016Z*ue&tw\r\006\002;\rB\0211h\021\b\003y\005s!!\020!\016\003yR!a\020\005\002\rq\022xn\034;?\023\0059\021B\001\"\007\003\035\001\030mY6bO\026L!\001R#\003\033M#(/\0338h\005VLG\016Z3s\025\t\021e\001C\003Ho\001\007!(\001\002tE\"9\021\nAA\001\n\003Q\025\001B2paf$B\001M&M\033\"9Q\003\023I\001\002\0049\002bB\021I!\003\005\ra\006\005\bK!\003\n\0211\001(\021\035y\005!%A\005\002A\013abY8qs\022\"WMZ1vYR$\023'F\001RU\t9\"kK\001T!\t!\026,D\001V\025\t1v+A\005v]\016DWmY6fI*\021\001LB\001\013C:tw\016^1uS>t\027B\001.V\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b9\002\t\n\021\"\001Q\0039\031w\016]=%I\0264\027-\0367uIIBqA\030\001\022\002\023\005q,\001\bd_BLH\005Z3gCVdG\017J\032\026\003\001T#a\n*\t\017\t\004\021\021!C!G\006i\001O]8ek\016$\bK]3gSb,\022\001\032\t\003K*l\021A\032\006\003O\"\fA\001\\1oO*\t\021.\001\003kCZ\f\027B\001\017g\021\035a\007!!A\005\0025\fA\002\035:pIV\034G/\021:jif,\022A\034\t\003\027=L!\001\035\004\003\007%sG\017C\004s\001\005\005I\021A:\002\035A\024x\016Z;di\026cW-\\3oiR\021Ao\036\t\003\027UL!A\036\004\003\007\005s\027\020C\004yc\006\005\t\031\0018\002\007a$\023\007C\004{\001\005\005I\021I>\002\037A\024x\016Z;di&#XM]1u_J,\022\001 \t\005{\006\005A/D\001\025\tyh!\001\006d_2dWm\031;j_:L1!a\001\005!IE/\032:bi>\024\b\"CA\004\001\005\005I\021AA\005\003!\031\027M\\#rk\006dG\003BA\006\003#\0012aCA\007\023\r\tyA\002\002\b\005>|G.Z1o\021!A\030QAA\001\002\004!\b\"CA\013\001\005\005I\021IA\f\003!A\027m\0355D_\022,G#\0018\t\023\005m\001!!A\005B\005u\021AB3rk\006d7\017\006\003\002\f\005}\001\002\003=\002\032\005\005\t\031\001;\b\023\005\r\"!!A\t\002\005\025\022\001C!uiJ$Um\0317\021\007!\n9C\002\005\002\005\005\005\t\022AA\025'\025\t9#a\013\022!!\ti#a\r\030/\035\002TBAA\030\025\r\t\tDB\001\beVtG/[7f\023\021\t)$a\f\003#\005\0237\017\036:bGR4UO\\2uS>t7\007C\004/\003O!\t!!\017\025\005\005\025\002\"C\033\002(\005\005IQIA\037)\005!\007BCA!\003O\t\t\021\"!\002D\005)\021\r\0359msR9\001'!\022\002H\005%\003BB\013\002@\001\007q\003\003\004\"\003\001\ra\006\005\007K\005}\002\031A\024\t\025\0055\023qEA\001\n\003\013y%A\004v]\006\004\b\017\\=\025\t\005E\023Q\f\t\006\027\005M\023qK\005\004\003+2!AB(qi&|g\016\005\004\f\0033:rcJ\005\004\00372!A\002+va2,7\007C\005\002`\005-\023\021!a\001a\005\031\001\020\n\031\t\025\005\r\024qEA\001\n\023\t)'A\006sK\006$'+Z:pYZ,GCAA4!\r)\027\021N\005\004\003W2'AB(cU\026\034G\017")
/*    */ public class AttrDecl implements Product, Serializable {
/*    */   private final String name;
/*    */   
/*    */   private final String tpe;
/*    */   
/*    */   private final DefaultDecl default;
/*    */   
/*    */   public String name() {
/* 43 */     return this.name;
/*    */   }
/*    */   
/*    */   public String tpe() {
/* 43 */     return this.tpe;
/*    */   }
/*    */   
/*    */   public DefaultDecl default() {
/* 43 */     return this.default;
/*    */   }
/*    */   
/*    */   public AttrDecl copy(String name, String tpe, DefaultDecl default) {
/* 43 */     return new AttrDecl(name, tpe, default);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 43 */     return name();
/*    */   }
/*    */   
/*    */   public String copy$default$2() {
/* 43 */     return tpe();
/*    */   }
/*    */   
/*    */   public DefaultDecl copy$default$3() {
/* 43 */     return default();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 43 */     return "AttrDecl";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 43 */     return 3;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 43 */     switch (x$1) {
/*    */       default:
/* 43 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 43 */     return name();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 43 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 43 */     return x$1 instanceof AttrDecl;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 43 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 139
/*    */     //   5: aload_1
/*    */     //   6: instanceof scala/xml/dtd/AttrDecl
/*    */     //   9: ifeq -> 17
/*    */     //   12: iconst_1
/*    */     //   13: istore_2
/*    */     //   14: goto -> 19
/*    */     //   17: iconst_0
/*    */     //   18: istore_2
/*    */     //   19: iload_2
/*    */     //   20: ifeq -> 143
/*    */     //   23: aload_1
/*    */     //   24: checkcast scala/xml/dtd/AttrDecl
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
/*    */     //   59: invokevirtual tpe : ()Ljava/lang/String;
/*    */     //   62: aload #6
/*    */     //   64: invokevirtual tpe : ()Ljava/lang/String;
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
/*    */     //   91: invokevirtual default : ()Lscala/xml/dtd/DefaultDecl;
/*    */     //   94: aload #6
/*    */     //   96: invokevirtual default : ()Lscala/xml/dtd/DefaultDecl;
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
/*    */     //   #43	-> 0
/*    */     //   #236	-> 12
/*    */     //   #43	-> 19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	145	0	this	Lscala/xml/dtd/AttrDecl;
/*    */     //   0	145	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public AttrDecl(String name, String tpe, DefaultDecl default) {
/* 43 */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     return Utility$.MODULE$.sbToString((Function1)new AttrDecl$$anonfun$toString$1(this));
/*    */   }
/*    */   
/*    */   public class AttrDecl$$anonfun$toString$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(StringBuilder sb) {
/* 44 */       this.$outer.buildString(sb);
/*    */     }
/*    */     
/*    */     public AttrDecl$$anonfun$toString$1(AttrDecl $outer) {}
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 47 */     sb.append("  ").append(name()).append(' ').append(tpe()).append(' ');
/* 48 */     return default().buildString(sb);
/*    */   }
/*    */   
/*    */   public static Function1<Tuple3<String, String, DefaultDecl>, AttrDecl> tupled() {
/*    */     return AttrDecl$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<String, Function1<String, Function1<DefaultDecl, AttrDecl>>> curried() {
/*    */     return AttrDecl$.MODULE$.curried();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\AttrDecl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */