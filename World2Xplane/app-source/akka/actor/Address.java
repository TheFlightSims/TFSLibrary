/*     */ package akka.actor;
/*     */ 
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.hashing.MurmurHash3$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}e\001B\001\003\005\036\021q!\0213ee\026\0348O\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001A\n\005\001!q\021\003\005\002\n\0315\t!BC\001\f\003\025\0318-\0317b\023\ti!B\001\004B]f\024VM\032\t\003\023=I!\001\005\006\003\017A\023x\016Z;diB\021\021BE\005\003')\021AbU3sS\006d\027N_1cY\026D\001\"\006\001\003\026\004%\tAF\001\taJ|Go\\2pYV\tq\003\005\002\03179\021\021\"G\005\0035)\ta\001\025:fI\0264\027B\001\017\036\005\031\031FO]5oO*\021!D\003\005\t?\001\021\t\022)A\005/\005I\001O]8u_\016|G\016\t\005\tC\001\021)\032!C\001-\00511/_:uK6D\001b\t\001\003\022\003\006IaF\001\bgf\034H/Z7!\021!)\003A!f\001\n\0031\023\001\0025pgR,\022a\n\t\004\023!:\022BA\025\013\005\031y\005\017^5p]\"A1\006\001B\tB\003%q%A\003i_N$\b\005\003\005.\001\tU\r\021\"\001/\003\021\001xN\035;\026\003=\0022!\003\0251!\tI\021'\003\0023\025\t\031\021J\034;\t\021Q\002!\021#Q\001\n=\nQ\001]8si\002BQA\016\001\005\n]\na\001P5oSRtD#\002\035;wqj\004CA\035\001\033\005\021\001\"B\0136\001\0049\002\"B\0216\001\0049\002\"B\0236\001\0049\003\"B\0276\001\004y\003\"\002\034\001\t\003yDc\001\035A\003\")QC\020a\001/!)\021E\020a\001/!)a\007\001C\001\007R)\001\bR#G\017\")QC\021a\001/!)\021E\021a\001/!)QE\021a\001/!)QF\021a\001a!)\021\n\001C\001\025\006i\001.Y:M_\016\fGnU2pa\026,\022a\023\t\003\0231K!!\024\006\003\017\t{w\016\\3b]\")q\n\001C\001\025\006q\001.Y:HY>\024\027\r\\*d_B,\007\002C)\001\021\013\007I\021\t*\002\021!\f7\017[\"pI\026,\022\001\r\005\t)\002A\t\021)Q\005a\005I\001.Y:i\007>$W\r\t\025\003'Z\003\"!C,\n\005aS!!\003;sC:\034\030.\0328u\021!Q\006\001#b\001\n\0032\022\001\003;p'R\024\030N\\4\t\021q\003\001\022!Q!\n]\t\021\002^8TiJLgn\032\021)\005m3\006\"B0\001\t\0031\022\001\0035pgR\004vN\035;\t\017\005\004\021\021!C\001E\006!1m\0349z)\025A4\rZ3g\021\035)\002\r%AA\002]Aq!\t1\021\002\003\007q\003C\004&AB\005\t\031A\024\t\0175\002\007\023!a\001_!9\001\016AI\001\n\003I\027AD2paf$C-\0324bk2$H%M\013\002U*\022qc[\026\002YB\021QN]\007\002]*\021q\016]\001\nk:\034\007.Z2lK\022T!!\035\006\002\025\005tgn\034;bi&|g.\003\002t]\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017U\004\021\023!C\001S\006q1m\0349zI\021,g-Y;mi\022\022\004bB<\001#\003%\t\001_\001\017G>\004\030\020\n3fM\006,H\016\036\0234+\005I(FA\024l\021\035Y\b!%A\005\002q\fabY8qs\022\"WMZ1vYR$C'F\001~U\ty3\016\003\005\000\001\005\005I\021IA\001\0035\001(o\0343vGR\004&/\0324jqV\021\0211\001\t\005\003\013\ty!\004\002\002\b)!\021\021BA\006\003\021a\027M\\4\013\005\0055\021\001\0026bm\006L1\001HA\004\021!\t\031\002AA\001\n\003\021\026\001\0049s_\022,8\r^!sSRL\b\"CA\f\001\005\005I\021AA\r\0039\001(o\0343vGR,E.Z7f]R$B!a\007\002\"A\031\021\"!\b\n\007\005}!BA\002B]fD\021\"a\t\002\026\005\005\t\031\001\031\002\007a$\023\007C\005\002(\001\t\t\021\"\021\002*\005y\001O]8ek\016$\030\n^3sCR|'/\006\002\002,A1\021QFA\032\0037i!!a\f\013\007\005E\"\"\001\006d_2dWm\031;j_:LA!!\016\0020\tA\021\n^3sCR|'\017C\005\002:\001\t\t\021\"\001\002<\005A1-\0318FcV\fG\016F\002L\003{A!\"a\t\0028\005\005\t\031AA\016\021%\t\t\005AA\001\n\003\n\031%\001\004fcV\fGn\035\013\004\027\006\025\003BCA\022\003\t\t\0211\001\002\034!*\001!!\023\002PA\031\021\"a\023\n\007\0055#B\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021aB\004\002T\tA\t!!\026\002\017\005#GM]3tgB\031\021(a\026\007\r\005\021\001\022AA-'\021\t9\006C\t\t\017Y\n9\006\"\001\002^Q\021\021Q\013\005\t\003C\n9\006\"\001\002d\005)\021\r\0359msR)\001(!\032\002h!1Q#a\030A\002]Aa!IA0\001\0049\002\002CA1\003/\"\t!a\033\025\023a\ni'a\034\002r\005M\004BB\013\002j\001\007q\003\003\004\"\003S\002\ra\006\005\007K\005%\004\031A\f\t\r5\nI\0071\0011\021)\t\t'a\026\002\002\023\005\025q\017\013\nq\005e\0241PA?\003Ba!FA;\001\0049\002BB\021\002v\001\007q\003\003\004&\003k\002\ra\n\005\007[\005U\004\031A\030\t\025\005\r\025qKA\001\n\003\013))A\004v]\006\004\b\017\\=\025\t\005\035\025q\022\t\005\023!\nI\tE\004\n\003\027;rcJ\030\n\007\0055%B\001\004UkBdW\r\016\005\n\003#\013\t)!AA\002a\n1\001\037\0231\021)\t)*a\026\002\002\023%\021qS\001\fe\026\fGMU3t_24X\r\006\002\002\032B!\021QAAN\023\021\ti*a\002\003\r=\023'.Z2u\001")
/*     */ public final class Address implements Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final String protocol;
/*     */   
/*     */   private final String system;
/*     */   
/*     */   private final Option<String> host;
/*     */   
/*     */   private final Option<Object> port;
/*     */   
/*     */   private transient int hashCode;
/*     */   
/*     */   private transient String toString;
/*     */   
/*     */   private volatile transient byte bitmap$trans$0;
/*     */   
/*     */   public String protocol() {
/*  21 */     return this.protocol;
/*     */   }
/*     */   
/*     */   public String system() {
/*  21 */     return this.system;
/*     */   }
/*     */   
/*     */   public Option<String> host() {
/*  21 */     return this.host;
/*     */   }
/*     */   
/*     */   public Option<Object> port() {
/*  21 */     return this.port;
/*     */   }
/*     */   
/*     */   public Address copy(String protocol, String system, Option<String> host, Option<Object> port) {
/*  21 */     return new Address(protocol, system, host, port);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/*  21 */     return protocol();
/*     */   }
/*     */   
/*     */   public String copy$default$2() {
/*  21 */     return system();
/*     */   }
/*     */   
/*     */   public Option<String> copy$default$3() {
/*  21 */     return host();
/*     */   }
/*     */   
/*     */   public Option<Object> copy$default$4() {
/*  21 */     return port();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  21 */     return "Address";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  21 */     return 4;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  21 */     int i = x$1;
/*  21 */     switch (i) {
/*     */       default:
/*  21 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 3:
/*     */       
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*  21 */     return protocol();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  21 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  21 */     return x$1 instanceof Address;
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 167
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/Address
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 171
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/Address
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual protocol : ()Ljava/lang/String;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual protocol : ()Ljava/lang/String;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 163
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 163
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual system : ()Ljava/lang/String;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual system : ()Ljava/lang/String;
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 163
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 163
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual host : ()Lscala/Option;
/*     */     //   99: aload #4
/*     */     //   101: invokevirtual host : ()Lscala/Option;
/*     */     //   104: astore #7
/*     */     //   106: dup
/*     */     //   107: ifnonnull -> 119
/*     */     //   110: pop
/*     */     //   111: aload #7
/*     */     //   113: ifnull -> 127
/*     */     //   116: goto -> 163
/*     */     //   119: aload #7
/*     */     //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   124: ifeq -> 163
/*     */     //   127: aload_0
/*     */     //   128: invokevirtual port : ()Lscala/Option;
/*     */     //   131: aload #4
/*     */     //   133: invokevirtual port : ()Lscala/Option;
/*     */     //   136: astore #8
/*     */     //   138: dup
/*     */     //   139: ifnonnull -> 151
/*     */     //   142: pop
/*     */     //   143: aload #8
/*     */     //   145: ifnull -> 159
/*     */     //   148: goto -> 163
/*     */     //   151: aload #8
/*     */     //   153: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   156: ifeq -> 163
/*     */     //   159: iconst_1
/*     */     //   160: goto -> 164
/*     */     //   163: iconst_0
/*     */     //   164: ifeq -> 171
/*     */     //   167: iconst_1
/*     */     //   168: goto -> 172
/*     */     //   171: iconst_0
/*     */     //   172: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #21	-> 0
/*     */     //   #63	-> 14
/*     */     //   #21	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	173	0	this	Lakka/actor/Address;
/*     */     //   0	173	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Address(String protocol, String system, Option<String> host, Option<Object> port) {
/*  21 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public Address(String protocol, String system) {
/*  27 */     this(protocol, system, (Option<String>)None$.MODULE$, (Option<Object>)None$.MODULE$);
/*     */   }
/*     */   
/*     */   public Address(String protocol, String system, String host, int port) {
/*  28 */     this(protocol, system, Option$.MODULE$.apply(host), (Option<Object>)new Some(BoxesRunTime.boxToInteger(port)));
/*     */   }
/*     */   
/*     */   public boolean hasLocalScope() {
/*  34 */     return host().isEmpty();
/*     */   }
/*     */   
/*     */   public boolean hasGlobalScope() {
/*  41 */     return host().isDefined();
/*     */   }
/*     */   
/*     */   private int hashCode$lzycompute() {
/*  44 */     synchronized (this) {
/*  44 */       if ((byte)(this.bitmap$trans$0 & 0x1) == 
/*     */         
/* 438 */         0) {
/*     */         this.hashCode = MurmurHash3$.MODULE$.productHash(this);
/*     */         this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 0x1);
/*     */       } 
/*     */       return this.hashCode;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 438 */     return ((byte)(this.bitmap$trans$0 & 0x1) == 0) ? hashCode$lzycompute() : this.hashCode;
/*     */   }
/*     */   
/*     */   private String toString$lzycompute() {
/*     */     synchronized (this) {
/* 438 */       if ((byte)(this.bitmap$trans$0 & 0x2) == 0) {
/*     */         StringBuilder sb = (new StringBuilder(protocol())).append("://").append(system());
/*     */         host().isDefined() ? sb.append('@').append((String)host().get()) : BoxedUnit.UNIT;
/*     */         port().isDefined() ? sb.append(':').append(BoxesRunTime.unboxToInt(port().get())) : BoxedUnit.UNIT;
/*     */         this.toString = sb.toString();
/*     */         this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 0x2);
/*     */       } 
/*     */       return this.toString;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 438 */     return ((byte)(this.bitmap$trans$0 & 0x2) == 0) ? toString$lzycompute() : this.toString;
/*     */   }
/*     */   
/*     */   public String hostPort() {
/*     */     return toString().substring(protocol().length() + 3);
/*     */   }
/*     */   
/*     */   public static Address apply(String paramString1, String paramString2, String paramString3, int paramInt) {
/*     */     return Address$.MODULE$.apply(paramString1, paramString2, paramString3, paramInt);
/*     */   }
/*     */   
/*     */   public static Address apply(String paramString1, String paramString2) {
/*     */     return Address$.MODULE$.apply(paramString1, paramString2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Address.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */