/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Some;
/*    */ import scala.Tuple4;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ua\001B\001\003\001\035\021\021\003\025:fM&DX\rZ!uiJL'-\036;f\025\t\031A!A\002y[2T\021!B\001\006g\016\fG.Y\002\001'\r\001\001\002\004\t\003\023)i\021AA\005\003\027\t\021\001\"T3uC\022\013G/\031\t\003\0235I!A\004\002\003\023\005#HO]5ckR,\007\002\003\t\001\005\013\007I\021A\t\002\007A\024X-F\001\023!\t\031rC\004\002\025+5\tA!\003\002\027\t\0051\001K]3eK\032L!\001G\r\003\rM#(/\0338h\025\t1B\001\003\005\034\001\t\005\t\025!\003\023\003\021\001(/\032\021\t\021u\001!Q1A\005\002E\t1a[3z\021!y\002A!A!\002\023\021\022\001B6fs\002B\001\"\t\001\003\006\004%\tAI\001\006m\006dW/Z\013\002GA\031A\005L\030\017\005\025RcB\001\024*\033\0059#B\001\025\007\003\031a$o\\8u}%\tQ!\003\002,\t\0059\001/Y2lC\036,\027BA\027/\005\r\031V-\035\006\003W\021\001\"!\003\031\n\005E\022!\001\002(pI\026D\001b\r\001\003\002\003\006IaI\001\007m\006dW/\032\021\t\021U\002!Q1A\005\002Y\nQA\\3yiF*\022\001\003\005\tq\001\021\t\021)A\005\021\0051a.\032=uc\001BQA\017\001\005\002m\na\001P5oSRtD#\002\037>}}\002\005CA\005\001\021\025\001\022\b1\001\023\021\025i\022\b1\001\023\021\025\t\023\b1\001$\021\025)\024\b1\001\t\021\035\021\005A1A\005\002Y\nAA\\3yi\"1A\t\001Q\001\n!\tQA\\3yi\002BQA\017\001\005\002\031#R\001P$I\023*CQ\001E#A\002IAQ!H#A\002IAQ!I#A\002IAQAQ#A\002!AQA\017\001\005\0021#R\001P'O\037NCQ\001E&A\002IAQ!H&A\002IAQ!I&A\002A\0032\001F)$\023\t\021FA\001\004PaRLwN\034\005\006\005.\003\r\001\003\005\006+\002!\tAV\001\005G>\004\030\020\006\002=/\")!\t\026a\001\021!)\021\f\001C\0015\006aq-\032;OC6,7\017]1dKR\021!c\027\005\0069b\003\raL\001\006_^tWM\035\005\006=\002!\taX\001\006CB\004H.\037\013\003G\001DQ!H/A\002IAQA\030\001\005\002\t$BaI2fU\")A-\031a\001%\005Ia.Y7fgB\f7-\032\005\006M\006\004\raZ\001\006g\016|\007/\032\t\003\023!L!!\033\002\003!9\013W.Z:qC\016,')\0338eS:<\007\"B\017b\001\004\021r!\0027\003\021\003i\027!\005)sK\032L\0070\0323BiR\024\030NY;uKB\021\021B\034\004\006\003\tA\ta\\\n\004]B\034\bC\001\013r\023\t\021HA\001\004B]f\024VM\032\t\003)QL!!\036\003\003\031M+'/[1mSj\f'\r\\3\t\013irG\021A<\025\0035DQ!\0378\005\002i\fq!\0368baBd\027\020F\002|\003\007\0012\001\006?\023\tiHA\001\003T_6,\007C\002\013\000%I\031\003\"C\002\002\002\021\021a\001V;qY\026$\004BBA\003q\002\007A(A\001y\021%\tIA\\A\001\n\023\tY!A\006sK\006$'+Z:pYZ,GCAA\007!\021\ty!!\007\016\005\005E!\002BA\n\003+\tA\001\\1oO*\021\021qC\001\005U\0064\030-\003\003\002\034\005E!AB(cU\026\034G\017")
/*    */ public class PrefixedAttribute extends MetaData implements Attribute {
/*    */   private final String pre;
/*    */   
/*    */   private final String key;
/*    */   
/*    */   private final Seq<Node> value;
/*    */   
/*    */   private final MetaData next1;
/*    */   
/*    */   private final MetaData next;
/*    */   
/*    */   public MetaData remove(String key) {
/* 19 */     return Attribute$class.remove(this, key);
/*    */   }
/*    */   
/*    */   public MetaData remove(String namespace, NamespaceBinding scope, String key) {
/* 19 */     return Attribute$class.remove(this, namespace, scope, key);
/*    */   }
/*    */   
/*    */   public boolean isPrefixed() {
/* 19 */     return Attribute$class.isPrefixed(this);
/*    */   }
/*    */   
/*    */   public boolean wellformed(NamespaceBinding scope) {
/* 19 */     return Attribute$class.wellformed(this, scope);
/*    */   }
/*    */   
/*    */   public Iterator<MetaData> iterator() {
/* 19 */     return Attribute$class.iterator(this);
/*    */   }
/*    */   
/*    */   public int size() {
/* 19 */     return Attribute$class.size(this);
/*    */   }
/*    */   
/*    */   public void toString1(StringBuilder sb) {
/* 19 */     Attribute$class.toString1(this, sb);
/*    */   }
/*    */   
/*    */   public String pre() {
/* 20 */     return this.pre;
/*    */   }
/*    */   
/*    */   public PrefixedAttribute(String pre, String key, Seq<Node> value, MetaData next1) {
/*    */     Attribute$class.$init$(this);
/* 26 */     this.next = (value != null) ? next1 : next1.remove(key);
/*    */   }
/*    */   
/*    */   public String key() {
/*    */     return this.key;
/*    */   }
/*    */   
/*    */   public Seq<Node> value() {
/*    */     return this.value;
/*    */   }
/*    */   
/*    */   public MetaData next1() {
/*    */     return this.next1;
/*    */   }
/*    */   
/*    */   public MetaData next() {
/* 26 */     return this.next;
/*    */   }
/*    */   
/*    */   public PrefixedAttribute(String pre, String key, String value, MetaData next) {
/* 30 */     this(pre, key, (value != null) ? (Seq<Node>)Text$.MODULE$.apply(value) : null, next);
/*    */   }
/*    */   
/*    */   public PrefixedAttribute(String pre, String key, Option value, MetaData next) {
/* 34 */     this(pre, key, value.isEmpty() ? (Seq<Node>)less.apply(null) : (Seq<Node>)value.get(), next);
/*    */   }
/*    */   
/*    */   public PrefixedAttribute copy(MetaData next) {
/* 40 */     return new PrefixedAttribute(pre(), key(), value(), next);
/*    */   }
/*    */   
/*    */   public String getNamespace(Node owner) {
/* 43 */     return owner.getNamespace(pre());
/*    */   }
/*    */   
/*    */   public Seq<Node> apply(String key) {
/* 46 */     return next().apply(key);
/*    */   }
/*    */   
/*    */   public Seq<Node> apply(String namespace, NamespaceBinding scope, String key) {
/*    */     // Byte code:
/*    */     //   0: aload_3
/*    */     //   1: aload_0
/*    */     //   2: invokevirtual key : ()Ljava/lang/String;
/*    */     //   5: astore #4
/*    */     //   7: dup
/*    */     //   8: ifnonnull -> 20
/*    */     //   11: pop
/*    */     //   12: aload #4
/*    */     //   14: ifnull -> 28
/*    */     //   17: goto -> 62
/*    */     //   20: aload #4
/*    */     //   22: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   25: ifeq -> 62
/*    */     //   28: aload_2
/*    */     //   29: aload_0
/*    */     //   30: invokevirtual pre : ()Ljava/lang/String;
/*    */     //   33: invokevirtual getURI : (Ljava/lang/String;)Ljava/lang/String;
/*    */     //   36: dup
/*    */     //   37: ifnonnull -> 48
/*    */     //   40: pop
/*    */     //   41: aload_1
/*    */     //   42: ifnull -> 55
/*    */     //   45: goto -> 62
/*    */     //   48: aload_1
/*    */     //   49: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   52: ifeq -> 62
/*    */     //   55: aload_0
/*    */     //   56: invokevirtual value : ()Lscala/collection/Seq;
/*    */     //   59: goto -> 72
/*    */     //   62: aload_0
/*    */     //   63: invokevirtual next : ()Lscala/xml/MetaData;
/*    */     //   66: aload_1
/*    */     //   67: aload_2
/*    */     //   68: aload_3
/*    */     //   69: invokevirtual apply : (Ljava/lang/String;Lscala/xml/NamespaceBinding;Ljava/lang/String;)Lscala/collection/Seq;
/*    */     //   72: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #51	-> 0
/*    */     //   #52	-> 55
/*    */     //   #54	-> 62
/*    */     //   #51	-> 72
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	73	0	this	Lscala/xml/PrefixedAttribute;
/*    */     //   0	73	1	namespace	Ljava/lang/String;
/*    */     //   0	73	2	scope	Lscala/xml/NamespaceBinding;
/*    */     //   0	73	3	key	Ljava/lang/String;
/*    */   }
/*    */   
/*    */   public static Some<Tuple4<String, String, Seq<Node>, MetaData>> unapply(PrefixedAttribute paramPrefixedAttribute) {
/*    */     return PrefixedAttribute$.MODULE$.unapply(paramPrefixedAttribute);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\PrefixedAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */