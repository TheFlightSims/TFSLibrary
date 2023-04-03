/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Null$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005ea\001B\001\003\001\035\0211#\0268qe\0264\027\016_3e\003R$(/\0332vi\026T!a\001\003\002\007alGNC\001\006\003\025\0318-\0317b\007\001\0312\001\001\005\r!\tI!\"D\001\003\023\tY!A\001\005NKR\fG)\031;b!\tIQ\"\003\002\017\005\tI\021\t\036;sS\n,H/\032\005\t!\001\021)\031!C\001#\005\0311.Z=\026\003I\001\"aE\f\017\005Q)R\"\001\003\n\005Y!\021A\002)sK\022,g-\003\002\0313\t11\013\036:j]\036T!A\006\003\t\021m\001!\021!Q\001\nI\tAa[3zA!AQ\004\001BC\002\023\005a$A\003wC2,X-F\001 !\r\001\003f\013\b\003C\031r!AI\023\016\003\rR!\001\n\004\002\rq\022xn\034;?\023\005)\021BA\024\005\003\035\001\030mY6bO\026L!!\013\026\003\007M+\027O\003\002(\tA\021\021\002L\005\003[\t\021AAT8eK\"Aq\006\001B\001B\003%q$\001\004wC2,X\r\t\005\tc\001\021\t\021)A\005\021\005)a.\032=uc!)1\007\001C\001i\0051A(\0338jiz\"B!\016\0348qA\021\021\002\001\005\006!I\002\rA\005\005\006;I\002\ra\b\005\006cI\002\r\001\003\005\bu\001\021\r\021\"\002<\003\r\001(/Z\013\002y=\tQH\t\001\t\r}\002\001\025!\004=\003\021\001(/\032\021\t\017\005\003!\031!C\001\005\006!a.\032=u+\005A\001B\002#\001A\003%\001\"A\003oKb$\b\005C\0034\001\021\005a\t\006\0036\017\"K\005\"\002\tF\001\004\021\002\"B\017F\001\004\021\002\"B!F\001\004A\001\"B\032\001\t\003YE\003B\033M\033FCQ\001\005&A\002IAQ!\b&A\0029\0032\001F( \023\t\001FA\001\004PaRLwN\034\005\006\003*\003\r\001\003\005\006'\002!\t\001V\001\005G>\004\030\020\006\0026+\")\021I\025a\001\021!)q\013\001C\0031\006aq-\032;OC6,7\017]1dKR\021!#\027\005\0065Z\003\raK\001\006_^tWM\035\005\0069\002!\t!X\001\006CB\004H.\037\013\003?yCQ\001E.A\002IAQ\001\030\001\005\002\001$BaH1dQ\")!m\030a\001%\005Ia.Y7fgB\f7-\032\005\006I~\003\r!Z\001\006g\016|\007/\032\t\003\023\031L!a\032\002\003!9\013W.Z:qC\016,')\0338eS:<\007\"\002\t`\001\004\021r!\0026\003\021\003Y\027aE+oaJ,g-\033=fI\006#HO]5ckR,\007CA\005m\r\025\t!\001#\001n'\rag.\035\t\003)=L!\001\035\003\003\r\005s\027PU3g!\t!\"/\003\002t\t\ta1+\032:jC2L'0\0312mK\")1\007\034C\001kR\t1\016C\003xY\022\005\0010A\004v]\006\004\b\017\\=\025\005e|\bc\001\013{y&\0211\020\002\002\005'>lW\rE\003\025{Jy\002\"\003\002\t\t1A+\0369mKNBa!!\001w\001\004)\024!\001=\t\023\005\025A.!A\005\n\005\035\021a\003:fC\022\024Vm]8mm\026$\"!!\003\021\t\005-\021QC\007\003\003\033QA!a\004\002\022\005!A.\0318h\025\t\t\031\"\001\003kCZ\f\027\002BA\f\003\033\021aa\0242kK\016$\b")
/*    */ public class UnprefixedAttribute extends MetaData implements Attribute {
/*    */   private final String key;
/*    */   
/*    */   private final Seq<Node> value;
/*    */   
/*    */   private final Null$ pre;
/*    */   
/*    */   private final MetaData next;
/*    */   
/*    */   public MetaData remove(String key) {
/* 16 */     return Attribute$class.remove(this, key);
/*    */   }
/*    */   
/*    */   public MetaData remove(String namespace, NamespaceBinding scope, String key) {
/* 16 */     return Attribute$class.remove(this, namespace, scope, key);
/*    */   }
/*    */   
/*    */   public boolean isPrefixed() {
/* 16 */     return Attribute$class.isPrefixed(this);
/*    */   }
/*    */   
/*    */   public boolean wellformed(NamespaceBinding scope) {
/* 16 */     return Attribute$class.wellformed(this, scope);
/*    */   }
/*    */   
/*    */   public Iterator<MetaData> iterator() {
/* 16 */     return Attribute$class.iterator(this);
/*    */   }
/*    */   
/*    */   public int size() {
/* 16 */     return Attribute$class.size(this);
/*    */   }
/*    */   
/*    */   public void toString1(StringBuilder sb) {
/* 16 */     Attribute$class.toString1(this, sb);
/*    */   }
/*    */   
/*    */   public String key() {
/* 17 */     return this.key;
/*    */   }
/*    */   
/*    */   public UnprefixedAttribute(String key, Seq<Node> value, MetaData next1) {
/*    */     Attribute$class.$init$(this);
/* 23 */     this.next = (value != null) ? next1 : next1.remove(key);
/*    */   }
/*    */   
/*    */   public Seq<Node> value() {
/*    */     return this.value;
/*    */   }
/*    */   
/*    */   public final Null$ pre() {
/*    */     return null;
/*    */   }
/*    */   
/*    */   public MetaData next() {
/* 23 */     return this.next;
/*    */   }
/*    */   
/*    */   public UnprefixedAttribute(String key, String value, MetaData next) {
/* 27 */     this(key, (value != null) ? (Seq<Node>)Text$.MODULE$.apply(value) : null, next);
/*    */   }
/*    */   
/*    */   public UnprefixedAttribute(String key, Option value, MetaData next) {
/* 31 */     this(key, value.isEmpty() ? (Seq<Node>)less.apply(null) : (Seq<Node>)value.get(), next);
/*    */   }
/*    */   
/*    */   public UnprefixedAttribute copy(MetaData next) {
/* 34 */     return new UnprefixedAttribute(key(), value(), next);
/*    */   }
/*    */   
/*    */   public Seq<Node> apply(String key) {
/* 45 */     String str = key();
/* 45 */     if (key == null) {
/* 45 */       if (str != null);
/* 45 */     } else if (key.equals(str)) {
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public Seq<Node> apply(String namespace, NamespaceBinding scope, String key) {
/* 56 */     return next().apply(namespace, scope, key);
/*    */   }
/*    */   
/*    */   public static Some<Tuple3<String, Seq<Node>, MetaData>> unapply(UnprefixedAttribute paramUnprefixedAttribute) {
/*    */     return UnprefixedAttribute$.MODULE$.unapply(paramUnprefixedAttribute);
/*    */   }
/*    */   
/*    */   public final String getNamespace(Node owner) {
/*    */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\UnprefixedAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */