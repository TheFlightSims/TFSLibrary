/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005}s!B\001\003\021\0039\021!C!uiJL'-\036;f\025\t\031A!A\002y[2T\021!B\001\006g\016\fG.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005%\tE\017\036:jEV$XmE\002\n\031A\001\"!\004\b\016\003\021I!a\004\003\003\r\005s\027PU3g!\ti\021#\003\002\023\t\ta1+\032:jC2L'0\0312mK\")A#\003C\001+\0051A(\0338jiz\"\022a\002\005\006/%!\t\001G\001\bk:\f\007\017\0357z)\tI\002\bE\002\0165qI!a\007\003\003\r=\003H/[8o!\025iQd\b\0246\023\tqBA\001\004UkBdWm\r\t\003A\rr!!D\021\n\005\t\"\021A\002)sK\022,g-\003\002%K\t11\013\036:j]\036T!A\t\003\021\007\035z#G\004\002)[9\021\021\006L\007\002U)\0211FB\001\007yI|w\016\036 \n\003\025I!A\f\003\002\017A\f7m[1hK&\021\001'\r\002\004'\026\f(B\001\030\005!\tA1'\003\0025\005\t!aj\0343f!\tAa'\003\0028\005\tAQ*\032;b\t\006$\030\rC\003:-\001\007!(A\001y!\tA1HB\004\013\005A\005\031\021\001\037\024\005m*\004\"\002 <\t\003y\024A\002\023j]&$H\005F\001A!\ti\021)\003\002C\t\t!QK\\5u\021\025!5H\"\001F\003\r\001(/Z\013\002?!9qi\017b\001\016\003)\025aA6fs\"9\021j\017b\001\016\003Q\025!\002<bYV,W#\001\024\t\0171[$\031!D\001\033\006!a.\032=u+\005)\004\"B(<\r\003\001\026!B1qa2LHC\001\024R\021\0259e\n1\001 \021\025y5H\"\001T)\0211CKV.\t\013U\023\006\031A\020\002\0239\fW.Z:qC\016,\007\"B,S\001\004A\026!B:d_B,\007C\001\005Z\023\tQ&A\001\tOC6,7\017]1dK\nKg\016Z5oO\")qI\025a\001?!)Ql\017D\001=\006!1m\0349z)\tQt\fC\003M9\002\007Q\007C\003bw\021\005!-\001\004sK6|g/\032\013\003k\rDQa\0221A\002}AQ!Y\036\005\002\025$B!\0164hQ\")Q\013\032a\001?!)q\013\032a\0011\")q\t\032a\001?!)!n\017C\001W\006Q\021n\035)sK\032L\0070\0323\026\0031\004\"!D7\n\0059$!a\002\"p_2,\027M\034\005\006an2\t!]\001\rO\026$h*Y7fgB\f7-\032\013\003?IDQa]8A\002I\nQa\\<oKJDQ!^\036\005\002Y\f!b^3mY\032|'/\\3e)\taw\017C\003Xi\002\007\001\fC\003zw\021\005#0\001\005ji\026\024\030\r^8s+\005Y\bcA\024}k%\021Q0\r\002\t\023R,'/\031;pe\"1qp\017C!\003\003\tAa]5{KV\021\0211\001\t\004\033\005\025\021bAA\004\t\t\031\021J\034;\t\017\005-1\b\"\005\002\016\005IAo\\*ue&tw-\r\013\004\001\006=\001\002CA\t\003\023\001\r!a\005\002\005M\024\007cA\024\002\026%\031\021qC\031\003\033M#(/\0338h\005VLG\016Z3s\021\031y\025\002\"\001\002\034Q9!(!\b\002 \005\005\002BB$\002\032\001\007q\004\003\004J\0033\001\rA\n\005\007\031\006e\001\031A\033\t\r=KA\021AA\023)%Q\024qEA\025\003W\ti\003\003\004E\003G\001\ra\b\005\007\017\006\r\002\031A\020\t\r%\013\031\0031\001 \021\031a\0251\005a\001k!1q*\003C\001\003c!\022BOA\032\003k\t9$!\017\t\r\021\013y\0031\001 \021\0319\025q\006a\001?!1\021*a\fA\002\031Ba\001TA\030\001\004)\004BB(\n\t\003\ti\004F\005;\003\t\031%!\022\002H!9A)a\017A\002\005\005\003cA\007\033?!1q)a\017A\002}Aa!SA\036\001\0041\003B\002'\002<\001\007Q\007C\005\002L%\t\t\021\"\003\002N\005Y!/Z1e%\026\034x\016\034<f)\t\ty\005\005\003\002R\005mSBAA*\025\021\t)&a\026\002\t1\fgn\032\006\003\0033\nAA[1wC&!\021QLA*\005\031y%M[3di\002")
/*    */ public interface Attribute {
/*    */   String pre();
/*    */   
/*    */   String key();
/*    */   
/*    */   Seq<Node> value();
/*    */   
/*    */   MetaData next();
/*    */   
/*    */   Seq<Node> apply(String paramString);
/*    */   
/*    */   Seq<Node> apply(String paramString1, NamespaceBinding paramNamespaceBinding, String paramString2);
/*    */   
/*    */   Attribute copy(MetaData paramMetaData);
/*    */   
/*    */   MetaData remove(String paramString);
/*    */   
/*    */   MetaData remove(String paramString1, NamespaceBinding paramNamespaceBinding, String paramString2);
/*    */   
/*    */   boolean isPrefixed();
/*    */   
/*    */   String getNamespace(Node paramNode);
/*    */   
/*    */   boolean wellformed(NamespaceBinding paramNamespaceBinding);
/*    */   
/*    */   Iterator<MetaData> iterator();
/*    */   
/*    */   int size();
/*    */   
/*    */   void toString1(StringBuilder paramStringBuilder);
/*    */   
/*    */   public class Attribute$$anonfun$iterator$1 extends AbstractFunction0<Iterator<MetaData>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Iterator<MetaData> apply() {
/* 79 */       return this.$outer.next().iterator();
/*    */     }
/*    */     
/*    */     public Attribute$$anonfun$iterator$1(Attribute $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Attribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */