/*     */ package scala.xml.parsing;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Queue;
/*     */ import scala.collection.mutable.Queue$;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.io.Source;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.TraitSetter;
/*     */ import scala.xml.Document;
/*     */ import scala.xml.MetaData;
/*     */ import scala.xml.NamespaceBinding;
/*     */ import scala.xml.Node;
/*     */ import scala.xml.NodeBuffer;
/*     */ import scala.xml.NodeSeq;
/*     */ import scala.xml.SpecialNode;
/*     */ import scala.xml.Text;
/*     */ import scala.xml.dtd.DTD;
/*     */ import scala.xml.dtd.ExternalID;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r]c!C\001\003!\003\r\t!CB)\0051i\025M]6vaB\013'o]3s\025\t\031A!A\004qCJ\034\030N\\4\013\005\0251\021a\001=nY*\tq!A\003tG\006d\027m\001\001\024\t\001QaB\005\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\005Ii\025M]6vaB\013'o]3s\007>lWn\0348\021\005=\031\022B\001\013\003\005)!vn[3o)\026\034Ho\035\005\006-\001!\taF\001\007I%t\027\016\036\023\025\003a\001\"aC\r\n\005i1!\001B+oSR,A\001\b\001\001;\ta\001k\\:ji&|g\016V=qKB\0211BH\005\003?\031\0211!\0238u\013\021\t\003\001\001\022\003\023%s\007/\036;UsB,\007CA\022'\033\005!#BA\023\007\003\tIw.\003\002(I\t11k\\;sG\026,A!\013\001\001U\tYQ\t\\3nK:$H+\0379f!\tYC&D\001\005\023\tiCAA\004O_\022,7+Z9\006\t=\002\001\001\r\002\017\003R$(/\0332vi\026\034H+\0379f!\021Y\021g\r\034\n\005I2!A\002+va2,'\007\005\002,i%\021Q\007\002\002\t\033\026$\030\rR1uCB\0211fN\005\003q\021\021\001CT1nKN\004\030mY3CS:$\027N\\4\006\ti\002\001A\016\002\016\035\006lWm\0359bG\026$\026\020]3\t\013q\002A\021A\037\002\035Q\024XO\\2bi\026$WI\035:peR\021a(\021\t\003\027}J!\001\021\004\003\0179{G\017[5oO\")!i\017a\001\007\006\031Qn]4\021\005\021;eBA\006F\023\t1e!\001\004Qe\026$WMZ\005\003\021&\023aa\025;sS:<'B\001$\007\021\025Y\005\001\"\001M\003))'O]8s\035>,e\016\032\013\003}5CQA\024&A\002\r\0131\001^1h\021\025\001\006\001\"\001R\0031A\b*\0318eY\026,%O]8s)\rA\"k\026\005\006'>\003\r\001V\001\005i\"\fG\017\005\002\f+&\021aK\002\002\005\007\"\f'\017C\003C\037\002\0071\tC\004Z\001\t\007i\021\001.\002\013%t\007/\036;\026\003\tBq\001\030\001C\002\033\005Q,\001\006qe\026\034XM\035<f/N+\022A\030\t\003\027}K!\001\031\004\003\017\t{w\016\\3b]\")!\r\001D\001G\006qQ\r\037;fe:\fGnU8ve\016,GC\001\022e\021\025)\027\r1\001D\0035\031\030p\035;f[2KG/\032:bY\"9q\r\001a\001\n#Q\026\001C2ve&s\007/\036;\t\017%\004\001\031!C\tU\006a1-\036:J]B,Ho\030\023fcR\021\001d\033\005\bY\"\f\t\0211\001#\003\rAH%\r\005\007]\002\001\013\025\002\022\002\023\r,(/\0238qkR\004c\001\0029\001\tE\024QbV5uQ2{wn[!iK\006$7CA8#\021!\031xN!A!\002\023\021\023AC;oI\026\024H._5oO\")Qo\034C\001m\0061A(\0338jiz\"\"a^=\021\005a|W\"\001\001\t\013M$\b\031\001\022\t\017m|'\031!C\005y\006)\021/^3vKV\tQ\020\005\003\003\017!V\"A@\013\t\005\005\0211A\001\b[V$\030M\0317f\025\r\t)AB\001\013G>dG.Z2uS>t\027bAA\005\n)\021+^3vK\"9\021QB8!\002\023i\030AB9vKV,\007\005C\004\002\022=$\t!a\005\002\0231|wn[1iK\006$GCAA\013!\025\t9\"a\nU\035\021\tI\"a\t\017\t\005m\021\021E\007\003\003;Q1!a\b\t\003\031a$o\\8u}%\tq!C\002\002&\031\tq\001]1dW\006<W-\003\003\002*\005-\"\001\005\"vM\032,'/\0323Ji\026\024\030\r^8s\025\r\t)C\002\005\n\003_y'\031!C\001\003c\tA!\033;feV\021\0211\007\n\006\003kQ\021Q\b\004\b\003o\tI\004AA\032\0051a$/\0324j]\026lWM\034;?\021!\tYd\034Q\001\n\005M\022!B5uKJ\004\003#BA\f\003!\026\002BA!\003W\021\001\"\023;fe\006$xN\035\005\b\003#\001A\021AA\n\021%\t9\005\001b\001\n\023\tI%\001\004iC:$G.Z\013\003\003\027\0022aDA'\023\r\tyE\001\002\016\033\006\0248.\0369IC:$G.\032:\t\021\005M\003\001)A\005\003\027\nq\001[1oI2,\007\005C\005\002X\001\001\r\021\"\001\002Z\005A\021N\0349Ti\006\0347.\006\002\002\\A)\021qCA/E%!\021qLA\026\005\021a\025n\035;\t\023\005\r\004\0011A\005\002\005\025\024\001D5oaN#\030mY6`I\025\fHc\001\r\002h!IA.!\031\002\002\003\007\0211\f\005\t\003W\002\001\025)\003\002\\\005I\021N\0349Ti\006\0347\016\t\005\f\003_\002\001\031!a\001\n\003\t\t(A\002q_N,\022!\b\005\f\003k\002\001\031!a\001\n\003\t9(A\004q_N|F%Z9\025\007a\tI\b\003\005m\003g\n\t\0211\001\036\021\035\ti\b\001Q!\nu\tA\001]8tA!I\021\021\021\001A\002\023\005\021\021O\001\tKb$\030J\0343fq\"I\021Q\021\001A\002\023\005\021qQ\001\rKb$\030J\0343fq~#S-\035\013\0041\005%\005\002\0037\002\004\006\005\t\031A\017\t\017\0055\005\001)Q\005;\005IQ\r\037;J]\022,\007\020\t\005\f\003#\003\001\031!a\001\n\003\t\t(\001\004u[B\004xn\035\005\f\003+\003\001\031!a\001\n\003\t9*\001\006u[B\004xn]0%KF$2\001GAM\021!a\0271SA\001\002\004i\002bBAO\001\001\006K!H\001\bi6\004\bo\\:!\021!\t\t\013\001a\001\n\003i\026\001\0048fqR\034\005NT3fI\026$\007\"CAS\001\001\007I\021AAT\003AqW\r\037;DQ:+W\rZ3e?\022*\027\017F\002\031\003SC\001\002\\AR\003\003\005\rA\030\005\b\003[\003\001\025)\003_\0035qW\r\037;DQ:+W\rZ3eA!A\021\021\027\001A\002\023\005Q,\001\006sK\006\034\007.\0323F_\032D\021\"!.\001\001\004%\t!a.\002\035I,\027m\0315fI\026{gm\030\023fcR\031\001$!/\t\0211\f\031,!AA\002yCq!!0\001A\003&a,A\006sK\006\034\007.\0323F_\032\004\003bCAa\001\001\007\t\031!C\001\003\007\f!\002\\1ti\016C'+Z1e+\005!\006bCAd\001\001\007\t\031!C\001\003\023\fa\002\\1ti\016C'+Z1e?\022*\027\017F\002\031\003\027D\001\002\\Ac\003\003\005\r\001\026\005\b\003\037\004\001\025)\003U\003-a\027m\035;DQJ+\027\r\032\021\t\017\005M\007\001\"\001\002D\006\0211\r\033\005\n\003/\004!\031!C\t\0033\fAa\0312vMV\021\0211\034\t\004}\006u\027bAAp\ni1\013\036:j]\036\024U/\0337eKJD\001\"a9\001A\003%\0211\\\001\006G\n,h\r\t\005\n\003O\004\001\031!C\001\003S\f1\001\032;e+\t\tY\017\005\003\002n\006EXBAAx\025\r\t9\017B\005\005\003g\fyOA\002E)\022C\021\"a>\001\001\004%\t!!?\002\017\021$Hm\030\023fcR\031\001$a?\t\0231\f)0!AA\002\005-\b\002CA\000\001\001\006K!a;\002\t\021$H\r\t\005\n\005\007\001\001\031!C\t\005\013\t1\001Z8d+\t\0219\001E\002,\005\023I1Aa\003\005\005!!unY;nK:$\b\"\003B\b\001\001\007I\021\003B\t\003\035!wnY0%KF$2\001\007B\n\021%a'QBA\001\002\004\0219\001\003\005\003\030\001\001\013\025\002B\004\003\021!wn\031\021\t\r\tm\001\001\"\001^\003\r)wN\032\005\b\005?\001A\021\001B\021\0031AX\016\034)s_\016Len\035;s)\005\031\004b\002B\023\001\021%!qE\001\021aJ|Gn\\4PeR+\007\020\036#fG2$BA!\013\0038AI1Ba\013\0030\t=\"QG\005\004\005[1!A\002+va2,7\007\005\003\f\005c\031\025b\001B\032\r\t1q\n\035;j_:\004Ba\003B\031=\"9!\021\bB\022\001\004q\026\001C5t!J|Gn\\4\t\017\tu\002\001\"\001\003@\0051\001O]8m_\036$\"A!\013\t\017\t\r\003\001\"\001\003F\005AA/\032=u\t\026\034G\016\006\002\003HA11\"\rB\030\005_AqAa\023\001\t\003\021i%\001\005e_\016,X.\0328u)\t\0219\001C\004\003R\001!\tBa\025\002\017A,Ho\0215beR!\0211\034B+\021\035\0219Fa\024A\002Q\013\021a\031\005\b\0057\002A\021\001B/\003)Ig.\033;jC2L'0Z\013\002q\"9!\021\r\001\005\022\005\r\027aE2i?J,G/\036:oS:<wL\\3yi\016D\007b\002B3\001\021\005!qM\001\r[.\fE\017\036:jEV$Xm\035\013\007\005S\022YGa\034\021\005at\003b\002B7\005G\002\raQ\001\005]\006lW\rC\004\003r\t\r\004\031\001\034\002\rA\0348m\0349f\021\035\021)\b\001C\001\005o\n1\"\\6Qe>\034\027J\\:ueRA!\021\020B>\005\022\t\t\005\002yQ!9!Q\020B:\001\004i\022\001\0039pg&$\030n\0348\t\017\t5$1\017a\001\007\"9!1\021B:\001\004\031\025\001\002;fqRDaAa\"\001\t\0039\022A\0028fqR\034\007\016C\004\003\f\002!\tA!$\002\027a\fE\017\036:jEV$Xm\035\013\004a\t=\005b\002B9\005\023\003\rA\016\005\b\005'\003A\021\001BK\0031AXI\034;jif4\026\r\\;f)\005\031\005b\002BM\001\021\005!1T\001\nq\016C\027M\035#bi\006,\022A\013\005\b\005?\003A\021\001BN\003!A8i\\7nK:$\bb\002BR\001\021\005!QU\001\013CB\004XM\0343UKb$Hc\002\r\003(\n%&1\027\005\b\003_\022\t\0131\001\036\021!\021YK!)A\002\t5\026A\001;t!\rY#qV\005\004\005c#!A\003(pI\026\024UO\0324fe\"9!Q\027BQ\001\004\031\025a\001;yi\"9!\021\030\001\005\002\tm\026\001C2p]R,g\016^\031\025\013a\021iLa0\t\017\tE$q\027a\001m!A!1\026B\\\001\004\021i\013C\004\003D\002!\tA!2\002\017\r|g\016^3oiR\031!Fa2\t\017\tE$\021\031a\001m!9!1\032\001\005\002\t5\027AC3yi\026\024h.\0317J\tR\021!q\032\t\005\003[\024\t.\003\003\003T\006=(AC#yi\026\024h.\0317J\t\"1!q\033\001\005\002]\t\001\002]1sg\026$E\013\022\005\b\0057\004A\021\001Bo\003\035)G.Z7f]R$2A\013Bp\021\035\021\tH!7A\002YBqAa9\001\t\003\021)/\001\005fY\026lWM\034;2)\rQ#q\035\005\b\005c\022\t\0171\0017\021\035\021Y\017\001C\005\005[\fQ\001\037+fqR,\022a\021\005\007K\002!\tA!&\t\017\tM\b\001\"\001\003\026\006a\001/\0362jI2KG/\032:bY\"1!q\037\001\005\002]\t\021\"\032=u'V\0247/\032;\t\017\tm\b\001\"\001\003~\006YQ.\031:lkB$Um\03172)\t\021y\020E\002\f\007\003I1aa\001\007\005\r\te.\037\005\007\007\017\001A\021A\f\002\0255\f'o[;q\t\026\034G\016\003\004\004\f\001!\taF\001\nS:$8+\0362tKRDaaa\004\001\t\0039\022aC3mK6,g\016\036#fG2Daaa\005\001\t\0039\022\001C1uiJ$Um\0317\t\r\r]\001\001\"\001\030\003))g\016^5us\022+7\r\034\005\007\0077\001A\021A\f\002\0319|G/\031;j_:$Um\0317\t\017\r}\001\001\"\001\004\"\005\t\"/\0329peR\034\026P\034;bq\026\023(o\034:\025\013a\031\031c!\n\t\017\005=4Q\004a\001;!91qEB\017\001\004\031\025aA:ue\"91q\004\001\005\002\r-Bc\001\r\004.!91qEB\025\001\004\031\005bBB\031\001\021\00511G\001\026e\026\004xN\035;WC2LG-\031;j_:,%O]8s)\025A2QGB\034\021\035\tyga\fA\002uAqaa\n\0040\001\0071\tC\004\004<\001!\ta!\020\002\tA,8\017\033\013\0041\r}\002bBB!\007s\001\raQ\001\013K:$\030\016^=OC6,\007bBB#\001\021\0051qI\001\raV\034\b.\022=uKJt\027\r\034\013\0041\r%\003bBB&\007\007\002\raQ\001\tgf\034H/Z7JI\"11q\n\001\005\002]\t1\001]8q%\031\031\031f!\026\002L\0311\021q\007\001\001\007#\002\"a\004\001")
/*     */ public interface MarkupParser extends MarkupParserCommon {
/*     */   void scala$xml$parsing$MarkupParser$_setter_$scala$xml$parsing$MarkupParser$$handle_$eq(MarkupHandler paramMarkupHandler);
/*     */   
/*     */   void scala$xml$parsing$MarkupParser$_setter_$cbuf_$eq(StringBuilder paramStringBuilder);
/*     */   
/*     */   Nothing$ truncatedError(String paramString);
/*     */   
/*     */   Nothing$ errorNoEnd(String paramString);
/*     */   
/*     */   void xHandleError(char paramChar, String paramString);
/*     */   
/*     */   Source input();
/*     */   
/*     */   boolean preserveWS();
/*     */   
/*     */   Source externalSource(String paramString);
/*     */   
/*     */   Source curInput();
/*     */   
/*     */   @TraitSetter
/*     */   void curInput_$eq(Source paramSource);
/*     */   
/*     */   BufferedIterator<Object> lookahead();
/*     */   
/*     */   MarkupHandler scala$xml$parsing$MarkupParser$$handle();
/*     */   
/*     */   List<Source> inpStack();
/*     */   
/*     */   @TraitSetter
/*     */   void inpStack_$eq(List<Source> paramList);
/*     */   
/*     */   int pos();
/*     */   
/*     */   @TraitSetter
/*     */   void pos_$eq(int paramInt);
/*     */   
/*     */   int extIndex();
/*     */   
/*     */   @TraitSetter
/*     */   void extIndex_$eq(int paramInt);
/*     */   
/*     */   int tmppos();
/*     */   
/*     */   @TraitSetter
/*     */   void tmppos_$eq(int paramInt);
/*     */   
/*     */   boolean nextChNeeded();
/*     */   
/*     */   @TraitSetter
/*     */   void nextChNeeded_$eq(boolean paramBoolean);
/*     */   
/*     */   boolean reachedEof();
/*     */   
/*     */   @TraitSetter
/*     */   void reachedEof_$eq(boolean paramBoolean);
/*     */   
/*     */   char lastChRead();
/*     */   
/*     */   @TraitSetter
/*     */   void lastChRead_$eq(char paramChar);
/*     */   
/*     */   char ch();
/*     */   
/*     */   StringBuilder cbuf();
/*     */   
/*     */   DTD dtd();
/*     */   
/*     */   @TraitSetter
/*     */   void dtd_$eq(DTD paramDTD);
/*     */   
/*     */   Document doc();
/*     */   
/*     */   @TraitSetter
/*     */   void doc_$eq(Document paramDocument);
/*     */   
/*     */   boolean eof();
/*     */   
/*     */   MetaData xmlProcInstr();
/*     */   
/*     */   Tuple3<Option<String>, Option<String>, Option<Object>> prolog();
/*     */   
/*     */   Tuple2<Option<String>, Option<String>> textDecl();
/*     */   
/*     */   Document document();
/*     */   
/*     */   StringBuilder putChar(char paramChar);
/*     */   
/*     */   MarkupHandler initialize();
/*     */   
/*     */   char ch_returning_nextch();
/*     */   
/*     */   Tuple2<MetaData, NamespaceBinding> mkAttributes(String paramString, NamespaceBinding paramNamespaceBinding);
/*     */   
/*     */   NodeSeq mkProcInstr(int paramInt, String paramString1, String paramString2);
/*     */   
/*     */   void nextch();
/*     */   
/*     */   Tuple2<MetaData, NamespaceBinding> xAttributes(NamespaceBinding paramNamespaceBinding);
/*     */   
/*     */   String xEntityValue();
/*     */   
/*     */   NodeSeq xCharData();
/*     */   
/*     */   NodeSeq xComment();
/*     */   
/*     */   void appendText(int paramInt, NodeBuffer paramNodeBuffer, String paramString);
/*     */   
/*     */   void content1(NamespaceBinding paramNamespaceBinding, NodeBuffer paramNodeBuffer);
/*     */   
/*     */   NodeSeq content(NamespaceBinding paramNamespaceBinding);
/*     */   
/*     */   ExternalID externalID();
/*     */   
/*     */   void parseDTD();
/*     */   
/*     */   NodeSeq element(NamespaceBinding paramNamespaceBinding);
/*     */   
/*     */   NodeSeq element1(NamespaceBinding paramNamespaceBinding);
/*     */   
/*     */   String systemLiteral();
/*     */   
/*     */   String pubidLiteral();
/*     */   
/*     */   void extSubset();
/*     */   
/*     */   Object markupDecl1();
/*     */   
/*     */   void markupDecl();
/*     */   
/*     */   void intSubset();
/*     */   
/*     */   void elementDecl();
/*     */   
/*     */   void attrDecl();
/*     */   
/*     */   void entityDecl();
/*     */   
/*     */   void notationDecl();
/*     */   
/*     */   void reportSyntaxError(int paramInt, String paramString);
/*     */   
/*     */   void reportSyntaxError(String paramString);
/*     */   
/*     */   void reportValidationError(int paramInt, String paramString);
/*     */   
/*     */   void push(String paramString);
/*     */   
/*     */   void pushExternal(String paramString);
/*     */   
/*     */   void pop();
/*     */   
/*     */   public class WithLookAhead extends Source {
/*     */     public final Source scala$xml$parsing$MarkupParser$WithLookAhead$$underlying;
/*     */     
/*  59 */     private final Queue<Object> scala$xml$parsing$MarkupParser$WithLookAhead$$queue = (Queue<Object>)Queue$.MODULE$.apply((Seq)Nil$.MODULE$);
/*     */     
/*     */     public Queue<Object> scala$xml$parsing$MarkupParser$WithLookAhead$$queue() {
/*  59 */       return this.scala$xml$parsing$MarkupParser$WithLookAhead$$queue;
/*     */     }
/*     */     
/*     */     public BufferedIterator<Object> lookahead() {
/*  61 */       Iterator iter = scala$xml$parsing$MarkupParser$WithLookAhead$$queue().iterator().$plus$plus((Function0)new MarkupParser$WithLookAhead$$anonfun$3(this));
/*  65 */       return iter.buffered();
/*     */     }
/*     */     
/*     */     public class MarkupParser$WithLookAhead$$anonfun$3 extends AbstractFunction0<MarkupParser$WithLookAhead$$anonfun$3$$anon$3> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final MarkupParser$WithLookAhead$$anonfun$3$$anon$3 apply() {
/*     */         return new MarkupParser$WithLookAhead$$anonfun$3$$anon$3(this);
/*     */       }
/*     */       
/*     */       public MarkupParser$WithLookAhead$$anonfun$3(MarkupParser.WithLookAhead $outer) {}
/*     */       
/*     */       public class MarkupParser$WithLookAhead$$anonfun$3$$anon$3 implements Iterator<Object> {
/*     */         public Iterator<Object> seq() {
/*     */           return Iterator.class.seq(this);
/*     */         }
/*     */         
/*     */         public boolean isEmpty() {
/*     */           return Iterator.class.isEmpty(this);
/*     */         }
/*     */         
/*     */         public boolean isTraversableAgain() {
/*     */           return Iterator.class.isTraversableAgain(this);
/*     */         }
/*     */         
/*     */         public boolean hasDefiniteSize() {
/*     */           return Iterator.class.hasDefiniteSize(this);
/*     */         }
/*     */         
/*     */         public Iterator<Object> take(int n) {
/*     */           return Iterator.class.take(this, n);
/*     */         }
/*     */         
/*     */         public Iterator<Object> drop(int n) {
/*     */           return Iterator.class.drop(this, n);
/*     */         }
/*     */         
/*     */         public Iterator<Object> slice(int from, int until) {
/*     */           return Iterator.class.slice(this, from, until);
/*     */         }
/*     */         
/*     */         public <B> Iterator<B> map(Function1 f) {
/*     */           return Iterator.class.map(this, f);
/*     */         }
/*     */         
/*     */         public <B> Iterator<B> $plus$plus(Function0 that) {
/*     */           return Iterator.class.$plus$plus(this, that);
/*     */         }
/*     */         
/*     */         public <B> Iterator<B> flatMap(Function1 f) {
/*     */           return Iterator.class.flatMap(this, f);
/*     */         }
/*     */         
/*     */         public Iterator<Object> filter(Function1 p) {
/*     */           return Iterator.class.filter(this, p);
/*     */         }
/*     */         
/*     */         public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*     */           return Iterator.class.corresponds(this, that, p);
/*     */         }
/*     */         
/*     */         public Iterator<Object> withFilter(Function1 p) {
/*     */           return Iterator.class.withFilter(this, p);
/*     */         }
/*     */         
/*     */         public Iterator<Object> filterNot(Function1 p) {
/*     */           return Iterator.class.filterNot(this, p);
/*     */         }
/*     */         
/*     */         public <B> Iterator<B> collect(PartialFunction pf) {
/*     */           return Iterator.class.collect(this, pf);
/*     */         }
/*     */         
/*     */         public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*     */           return Iterator.class.scanLeft(this, z, op);
/*     */         }
/*     */         
/*     */         public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*     */           return Iterator.class.scanRight(this, z, op);
/*     */         }
/*     */         
/*     */         public Iterator<Object> takeWhile(Function1 p) {
/*     */           return Iterator.class.takeWhile(this, p);
/*     */         }
/*     */         
/*     */         public Tuple2<Iterator<Object>, Iterator<Object>> partition(Function1 p) {
/*     */           return Iterator.class.partition(this, p);
/*     */         }
/*     */         
/*     */         public Tuple2<Iterator<Object>, Iterator<Object>> span(Function1 p) {
/*     */           return Iterator.class.span(this, p);
/*     */         }
/*     */         
/*     */         public Iterator<Object> dropWhile(Function1 p) {
/*     */           return Iterator.class.dropWhile(this, p);
/*     */         }
/*     */         
/*     */         public <B> Iterator<Tuple2<Object, B>> zip(Iterator that) {
/*     */           return Iterator.class.zip(this, that);
/*     */         }
/*     */         
/*     */         public <A1> Iterator<A1> padTo(int len, Object elem) {
/*     */           return Iterator.class.padTo(this, len, elem);
/*     */         }
/*     */         
/*     */         public Iterator<Tuple2<Object, Object>> zipWithIndex() {
/*     */           return Iterator.class.zipWithIndex(this);
/*     */         }
/*     */         
/*     */         public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*     */           return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */         }
/*     */         
/*     */         public <U> void foreach(Function1 f) {
/*     */           Iterator.class.foreach(this, f);
/*     */         }
/*     */         
/*     */         public boolean forall(Function1 p) {
/*     */           return Iterator.class.forall(this, p);
/*     */         }
/*     */         
/*     */         public boolean exists(Function1 p) {
/*     */           return Iterator.class.exists(this, p);
/*     */         }
/*     */         
/*     */         public boolean contains(Object elem) {
/*     */           return Iterator.class.contains(this, elem);
/*     */         }
/*     */         
/*     */         public Option<Object> find(Function1 p) {
/*     */           return Iterator.class.find(this, p);
/*     */         }
/*     */         
/*     */         public int indexWhere(Function1 p) {
/*     */           return Iterator.class.indexWhere(this, p);
/*     */         }
/*     */         
/*     */         public <B> int indexOf(Object elem) {
/*     */           return Iterator.class.indexOf(this, elem);
/*     */         }
/*     */         
/*     */         public BufferedIterator<Object> buffered() {
/*     */           return Iterator.class.buffered(this);
/*     */         }
/*     */         
/*     */         public <B> Iterator<Object>.GroupedIterator<B> grouped(int size) {
/*     */           return Iterator.class.grouped(this, size);
/*     */         }
/*     */         
/*     */         public <B> Iterator<Object>.GroupedIterator<B> sliding(int size, int step) {
/*     */           return Iterator.class.sliding(this, size, step);
/*     */         }
/*     */         
/*     */         public int length() {
/*     */           return Iterator.class.length(this);
/*     */         }
/*     */         
/*     */         public Tuple2<Iterator<Object>, Iterator<Object>> duplicate() {
/*     */           return Iterator.class.duplicate(this);
/*     */         }
/*     */         
/*     */         public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*     */           return Iterator.class.patch(this, from, patchElems, replaced);
/*     */         }
/*     */         
/*     */         public <B> void copyToArray(Object xs, int start, int len) {
/*     */           Iterator.class.copyToArray(this, xs, start, len);
/*     */         }
/*     */         
/*     */         public boolean sameElements(Iterator that) {
/*     */           return Iterator.class.sameElements(this, that);
/*     */         }
/*     */         
/*     */         public Traversable<Object> toTraversable() {
/*     */           return Iterator.class.toTraversable(this);
/*     */         }
/*     */         
/*     */         public Iterator<Object> toIterator() {
/*     */           return Iterator.class.toIterator(this);
/*     */         }
/*     */         
/*     */         public Stream<Object> toStream() {
/*     */           return Iterator.class.toStream(this);
/*     */         }
/*     */         
/*     */         public String toString() {
/*     */           return Iterator.class.toString(this);
/*     */         }
/*     */         
/*     */         public <B> int sliding$default$2() {
/*     */           return Iterator.class.sliding$default$2(this);
/*     */         }
/*     */         
/*     */         public List<Object> reversed() {
/*     */           return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public int size() {
/*     */           return TraversableOnce.class.size((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public boolean nonEmpty() {
/*     */           return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public int count(Function1 p) {
/*     */           return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */         }
/*     */         
/*     */         public <B> Option<B> collectFirst(PartialFunction pf) {
/*     */           return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */         }
/*     */         
/*     */         public <B> B $div$colon(Object z, Function2 op) {
/*     */           return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */         }
/*     */         
/*     */         public <B> B $colon$bslash(Object z, Function2 op) {
/*     */           return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */         }
/*     */         
/*     */         public <B> B foldLeft(Object z, Function2 op) {
/*     */           return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */         }
/*     */         
/*     */         public <B> B foldRight(Object z, Function2 op) {
/*     */           return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */         }
/*     */         
/*     */         public <B> B reduceLeft(Function2 op) {
/*     */           return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */         }
/*     */         
/*     */         public <B> B reduceRight(Function2 op) {
/*     */           return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */         }
/*     */         
/*     */         public <B> Option<B> reduceLeftOption(Function2 op) {
/*     */           return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */         }
/*     */         
/*     */         public <B> Option<B> reduceRightOption(Function2 op) {
/*     */           return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */         }
/*     */         
/*     */         public <A1> A1 reduce(Function2 op) {
/*     */           return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */         }
/*     */         
/*     */         public <A1> Option<A1> reduceOption(Function2 op) {
/*     */           return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */         }
/*     */         
/*     */         public <A1> A1 fold(Object z, Function2 op) {
/*     */           return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */         }
/*     */         
/*     */         public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*     */           return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */         }
/*     */         
/*     */         public <B> B sum(Numeric num) {
/*     */           return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */         }
/*     */         
/*     */         public <B> B product(Numeric num) {
/*     */           return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */         }
/*     */         
/*     */         public <B> char min(Ordering cmp) {
/*     */           return TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */         }
/*     */         
/*     */         public <B> char max(Ordering cmp) {
/*     */           return TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */         }
/*     */         
/*     */         public <B> char maxBy(Function1 f, Ordering cmp) {
/*     */           return TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */         }
/*     */         
/*     */         public <B> char minBy(Function1 f, Ordering cmp) {
/*     */           return TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */         }
/*     */         
/*     */         public <B> void copyToBuffer(Buffer dest) {
/*     */           TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */         }
/*     */         
/*     */         public <B> void copyToArray(Object xs, int start) {
/*     */           TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */         }
/*     */         
/*     */         public <B> void copyToArray(Object xs) {
/*     */           TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */         }
/*     */         
/*     */         public <B> Object toArray(ClassTag evidence$1) {
/*     */           return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */         }
/*     */         
/*     */         public List<Object> toList() {
/*     */           return TraversableOnce.class.toList((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public Iterable<Object> toIterable() {
/*     */           return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public Seq<Object> toSeq() {
/*     */           return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public IndexedSeq<Object> toIndexedSeq() {
/*     */           return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public <B> Buffer<B> toBuffer() {
/*     */           return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public <B> Set<B> toSet() {
/*     */           return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public Vector<Object> toVector() {
/*     */           return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public <Col> Col to(CanBuildFrom cbf) {
/*     */           return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */         }
/*     */         
/*     */         public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*     */           return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */         }
/*     */         
/*     */         public String mkString(String start, String sep, String end) {
/*     */           return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */         }
/*     */         
/*     */         public String mkString(String sep) {
/*     */           return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */         }
/*     */         
/*     */         public String mkString() {
/*     */           return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */         }
/*     */         
/*     */         public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*     */           return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */         }
/*     */         
/*     */         public StringBuilder addString(StringBuilder b, String sep) {
/*     */           return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */         }
/*     */         
/*     */         public StringBuilder addString(StringBuilder b) {
/*     */           return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */         }
/*     */         
/*     */         public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*     */           return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */         }
/*     */         
/*     */         public MarkupParser$WithLookAhead$$anonfun$3$$anon$3(MarkupParser$WithLookAhead$$anonfun$3 $outer) {
/*     */           GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*     */           TraversableOnce.class.$init$((TraversableOnce)this);
/*     */           Iterator.class.$init$(this);
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/*     */           return this.$outer.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$underlying.hasNext();
/*     */         }
/*     */         
/*     */         public char next() {
/*     */           char x = this.$outer.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$underlying.next();
/*     */           this.$outer.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$queue().$plus$eq(BoxesRunTime.boxToCharacter(x));
/*     */           return x;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  67 */     private final Object iter = new $anon$2(this);
/*     */     
/*     */     public Object iter() {
/*  67 */       return this.iter;
/*     */     }
/*     */     
/*     */     public WithLookAhead(MarkupHandler $outer, Source underlying) {}
/*     */     
/*     */     public class $anon$2 implements Iterator<Object> {
/*     */       public Iterator<Object> seq() {
/*  67 */         return Iterator.class.seq(this);
/*     */       }
/*     */       
/*     */       public boolean isEmpty() {
/*  67 */         return Iterator.class.isEmpty(this);
/*     */       }
/*     */       
/*     */       public boolean isTraversableAgain() {
/*  67 */         return Iterator.class.isTraversableAgain(this);
/*     */       }
/*     */       
/*     */       public boolean hasDefiniteSize() {
/*  67 */         return Iterator.class.hasDefiniteSize(this);
/*     */       }
/*     */       
/*     */       public Iterator<Object> take(int n) {
/*  67 */         return Iterator.class.take(this, n);
/*     */       }
/*     */       
/*     */       public Iterator<Object> drop(int n) {
/*  67 */         return Iterator.class.drop(this, n);
/*     */       }
/*     */       
/*     */       public Iterator<Object> slice(int from, int until) {
/*  67 */         return Iterator.class.slice(this, from, until);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> map(Function1 f) {
/*  67 */         return Iterator.class.map(this, f);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> $plus$plus(Function0 that) {
/*  67 */         return Iterator.class.$plus$plus(this, that);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> flatMap(Function1 f) {
/*  67 */         return Iterator.class.flatMap(this, f);
/*     */       }
/*     */       
/*     */       public Iterator<Object> filter(Function1 p) {
/*  67 */         return Iterator.class.filter(this, p);
/*     */       }
/*     */       
/*     */       public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*  67 */         return Iterator.class.corresponds(this, that, p);
/*     */       }
/*     */       
/*     */       public Iterator<Object> withFilter(Function1 p) {
/*  67 */         return Iterator.class.withFilter(this, p);
/*     */       }
/*     */       
/*     */       public Iterator<Object> filterNot(Function1 p) {
/*  67 */         return Iterator.class.filterNot(this, p);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> collect(PartialFunction pf) {
/*  67 */         return Iterator.class.collect(this, pf);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*  67 */         return Iterator.class.scanLeft(this, z, op);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*  67 */         return Iterator.class.scanRight(this, z, op);
/*     */       }
/*     */       
/*     */       public Iterator<Object> takeWhile(Function1 p) {
/*  67 */         return Iterator.class.takeWhile(this, p);
/*     */       }
/*     */       
/*     */       public Tuple2<Iterator<Object>, Iterator<Object>> partition(Function1 p) {
/*  67 */         return Iterator.class.partition(this, p);
/*     */       }
/*     */       
/*     */       public Tuple2<Iterator<Object>, Iterator<Object>> span(Function1 p) {
/*  67 */         return Iterator.class.span(this, p);
/*     */       }
/*     */       
/*     */       public Iterator<Object> dropWhile(Function1 p) {
/*  67 */         return Iterator.class.dropWhile(this, p);
/*     */       }
/*     */       
/*     */       public <B> Iterator<Tuple2<Object, B>> zip(Iterator that) {
/*  67 */         return Iterator.class.zip(this, that);
/*     */       }
/*     */       
/*     */       public <A1> Iterator<A1> padTo(int len, Object elem) {
/*  67 */         return Iterator.class.padTo(this, len, elem);
/*     */       }
/*     */       
/*     */       public Iterator<Tuple2<Object, Object>> zipWithIndex() {
/*  67 */         return Iterator.class.zipWithIndex(this);
/*     */       }
/*     */       
/*     */       public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*  67 */         return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */       }
/*     */       
/*     */       public <U> void foreach(Function1 f) {
/*  67 */         Iterator.class.foreach(this, f);
/*     */       }
/*     */       
/*     */       public boolean forall(Function1 p) {
/*  67 */         return Iterator.class.forall(this, p);
/*     */       }
/*     */       
/*     */       public boolean exists(Function1 p) {
/*  67 */         return Iterator.class.exists(this, p);
/*     */       }
/*     */       
/*     */       public boolean contains(Object elem) {
/*  67 */         return Iterator.class.contains(this, elem);
/*     */       }
/*     */       
/*     */       public Option<Object> find(Function1 p) {
/*  67 */         return Iterator.class.find(this, p);
/*     */       }
/*     */       
/*     */       public int indexWhere(Function1 p) {
/*  67 */         return Iterator.class.indexWhere(this, p);
/*     */       }
/*     */       
/*     */       public <B> int indexOf(Object elem) {
/*  67 */         return Iterator.class.indexOf(this, elem);
/*     */       }
/*     */       
/*     */       public BufferedIterator<Object> buffered() {
/*  67 */         return Iterator.class.buffered(this);
/*     */       }
/*     */       
/*     */       public <B> Iterator<Object>.GroupedIterator<B> grouped(int size) {
/*  67 */         return Iterator.class.grouped(this, size);
/*     */       }
/*     */       
/*     */       public <B> Iterator<Object>.GroupedIterator<B> sliding(int size, int step) {
/*  67 */         return Iterator.class.sliding(this, size, step);
/*     */       }
/*     */       
/*     */       public int length() {
/*  67 */         return Iterator.class.length(this);
/*     */       }
/*     */       
/*     */       public Tuple2<Iterator<Object>, Iterator<Object>> duplicate() {
/*  67 */         return Iterator.class.duplicate(this);
/*     */       }
/*     */       
/*     */       public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*  67 */         return Iterator.class.patch(this, from, patchElems, replaced);
/*     */       }
/*     */       
/*     */       public <B> void copyToArray(Object xs, int start, int len) {
/*  67 */         Iterator.class.copyToArray(this, xs, start, len);
/*     */       }
/*     */       
/*     */       public boolean sameElements(Iterator that) {
/*  67 */         return Iterator.class.sameElements(this, that);
/*     */       }
/*     */       
/*     */       public Traversable<Object> toTraversable() {
/*  67 */         return Iterator.class.toTraversable(this);
/*     */       }
/*     */       
/*     */       public Iterator<Object> toIterator() {
/*  67 */         return Iterator.class.toIterator(this);
/*     */       }
/*     */       
/*     */       public Stream<Object> toStream() {
/*  67 */         return Iterator.class.toStream(this);
/*     */       }
/*     */       
/*     */       public String toString() {
/*  67 */         return Iterator.class.toString(this);
/*     */       }
/*     */       
/*     */       public <B> int sliding$default$2() {
/*  67 */         return Iterator.class.sliding$default$2(this);
/*     */       }
/*     */       
/*     */       public List<Object> reversed() {
/*  67 */         return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public int size() {
/*  67 */         return TraversableOnce.class.size((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public boolean nonEmpty() {
/*  67 */         return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public int count(Function1 p) {
/*  67 */         return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */       }
/*     */       
/*     */       public <B> Option<B> collectFirst(PartialFunction pf) {
/*  67 */         return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */       }
/*     */       
/*     */       public <B> B $div$colon(Object z, Function2 op) {
/*  67 */         return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B $colon$bslash(Object z, Function2 op) {
/*  67 */         return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B foldLeft(Object z, Function2 op) {
/*  67 */         return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B foldRight(Object z, Function2 op) {
/*  67 */         return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B reduceLeft(Function2 op) {
/*  67 */         return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <B> B reduceRight(Function2 op) {
/*  67 */         return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <B> Option<B> reduceLeftOption(Function2 op) {
/*  67 */         return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <B> Option<B> reduceRightOption(Function2 op) {
/*  67 */         return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <A1> A1 reduce(Function2 op) {
/*  67 */         return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <A1> Option<A1> reduceOption(Function2 op) {
/*  67 */         return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */       }
/*     */       
/*     */       public <A1> A1 fold(Object z, Function2 op) {
/*  67 */         return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  67 */         return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */       }
/*     */       
/*     */       public <B> B sum(Numeric num) {
/*  67 */         return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */       }
/*     */       
/*     */       public <B> B product(Numeric num) {
/*  67 */         return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */       }
/*     */       
/*     */       public <B> char min(Ordering cmp) {
/*  67 */         return TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */       }
/*     */       
/*     */       public <B> char max(Ordering cmp) {
/*  67 */         return TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */       }
/*     */       
/*     */       public <B> char maxBy(Function1 f, Ordering cmp) {
/*  67 */         return TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */       }
/*     */       
/*     */       public <B> char minBy(Function1 f, Ordering cmp) {
/*  67 */         return TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */       }
/*     */       
/*     */       public <B> void copyToBuffer(Buffer dest) {
/*  67 */         TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */       }
/*     */       
/*     */       public <B> void copyToArray(Object xs, int start) {
/*  67 */         TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */       }
/*     */       
/*     */       public <B> void copyToArray(Object xs) {
/*  67 */         TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */       }
/*     */       
/*     */       public <B> Object toArray(ClassTag evidence$1) {
/*  67 */         return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */       }
/*     */       
/*     */       public List<Object> toList() {
/*  67 */         return TraversableOnce.class.toList((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public Iterable<Object> toIterable() {
/*  67 */         return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public Seq<Object> toSeq() {
/*  67 */         return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public IndexedSeq<Object> toIndexedSeq() {
/*  67 */         return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public <B> Buffer<B> toBuffer() {
/*  67 */         return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public <B> Set<B> toSet() {
/*  67 */         return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public Vector<Object> toVector() {
/*  67 */         return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public <Col> Col to(CanBuildFrom cbf) {
/*  67 */         return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */       }
/*     */       
/*     */       public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  67 */         return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */       }
/*     */       
/*     */       public String mkString(String start, String sep, String end) {
/*  67 */         return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */       }
/*     */       
/*     */       public String mkString(String sep) {
/*  67 */         return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */       }
/*     */       
/*     */       public String mkString() {
/*  67 */         return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */       }
/*     */       
/*     */       public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  67 */         return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */       }
/*     */       
/*     */       public StringBuilder addString(StringBuilder b, String sep) {
/*  67 */         return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */       }
/*     */       
/*     */       public StringBuilder addString(StringBuilder b) {
/*  67 */         return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */       }
/*     */       
/*     */       public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  67 */         return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */       }
/*     */       
/*     */       public $anon$2(MarkupParser.WithLookAhead $outer) {
/*  67 */         GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  67 */         TraversableOnce.class.$init$((TraversableOnce)this);
/*  67 */         Iterator.class.$init$(this);
/*     */       }
/*     */       
/*     */       public boolean hasNext() {
/*  68 */         return !(!this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$underlying.hasNext() && this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$queue().isEmpty());
/*     */       }
/*     */       
/*     */       public char next() {
/*  69 */         return this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$queue().isEmpty() ? this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$underlying.next() : BoxesRunTime.unboxToChar(this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$queue().dequeue());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class MarkupParser$$anonfun$document$1 extends AbstractFunction1<Node, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef elemCount$1;
/*     */     
/*     */     private final ObjectRef theNode$1;
/*     */     
/*     */     public MarkupParser$$anonfun$document$1(MarkupHandler $outer, IntRef elemCount$1, ObjectRef theNode$1) {}
/*     */     
/*     */     public final void apply(Node c) {
/* 246 */       if (!(c instanceof scala.xml.ProcInstr))
/* 248 */         if (!(c instanceof scala.xml.Comment))
/* 249 */           if (c instanceof scala.xml.EntityRef) {
/* 250 */             ((MarkupParser)this.$outer).reportSyntaxError("no entity references allowed here");
/* 251 */           } else if (c instanceof SpecialNode) {
/* 251 */             SpecialNode specialNode = (SpecialNode)c;
/* 252 */             if (specialNode.toString().trim().length() > 0)
/* 253 */               this.elemCount$1.elem += 2; 
/*     */           } else {
/* 254 */             if (c != null) {
/* 255 */               this.elemCount$1.elem++;
/* 256 */               this.theNode$1.elem = c;
/*     */               return;
/*     */             } 
/*     */             throw new MatchError(c);
/*     */           }   
/*     */     }
/*     */   }
/*     */   
/*     */   public class MarkupParser$$anonfun$xCharData$2 extends AbstractFunction2<Object, String, NodeSeq> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final NodeSeq apply(int pos, String s) {
/* 368 */       return MarkupParser$class.mkResult$1(this.$outer, pos, s);
/*     */     }
/*     */     
/*     */     public MarkupParser$$anonfun$xCharData$2(MarkupHandler $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParser$$anonfun$xCharData$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply() {
/* 368 */       return ((MarkupParser)this.$outer).pos();
/*     */     }
/*     */     
/*     */     public int apply$mcI$sp() {
/* 368 */       return ((MarkupParser)this.$outer).pos();
/*     */     }
/*     */     
/*     */     public MarkupParser$$anonfun$xCharData$1(MarkupHandler $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParser$$anonfun$appendText$1 extends AbstractFunction1<Text, NodeBuffer> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int pos$1;
/*     */     
/*     */     private final NodeBuffer ts$2;
/*     */     
/*     */     public MarkupParser$$anonfun$appendText$1(MarkupHandler $outer, int pos$1, NodeBuffer ts$2) {}
/*     */     
/*     */     public final NodeBuffer apply(Text t) {
/* 397 */       return this.ts$2.$amp$plus(((MarkupParser)this.$outer).scala$xml$parsing$MarkupParser$$handle().text(this.pos$1, t.text()));
/*     */     }
/*     */   }
/*     */   
/*     */   public class MarkupParser$$anon$1 extends NodeSeq {
/*     */     private final List<Node> theSeq;
/*     */     
/*     */     public List<Node> theSeq() {
/* 429 */       return this.theSeq;
/*     */     }
/*     */     
/*     */     public MarkupParser$$anon$1(MarkupHandler $outer, ObjectRef ts$1) {
/* 429 */       this.theSeq = ((NodeBuffer)ts$1.elem).toList();
/*     */     }
/*     */   }
/*     */   
/*     */   public class MarkupParser$$anonfun$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final char apply() {
/* 450 */       return ((MarkupParser)this.$outer).ch();
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/* 450 */       return ((MarkupParser)this.$outer).ch();
/*     */     }
/*     */     
/*     */     public MarkupParser$$anonfun$1(MarkupHandler $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParser$$anonfun$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 450 */       ((MarkupParser)this.$outer).nextch();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 450 */       ((MarkupParser)this.$outer).nextch();
/*     */     }
/*     */     
/*     */     public MarkupParser$$anonfun$2(MarkupHandler $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParser$$anon$4 extends DTD {
/*     */     public MarkupParser$$anon$4(MarkupHandler $outer, ObjectRef extID$1) {
/* 532 */       externalID_$eq((ExternalID)extID$1.elem);
/* 533 */       decls_$eq(((MarkupParser)$outer).scala$xml$parsing$MarkupParser$$handle().decls().reverse());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\MarkupParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */