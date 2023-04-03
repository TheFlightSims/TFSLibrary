/*     */ package scala.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
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
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rmw!B\001\003\021\0039\021AB*pkJ\034WM\003\002\004\t\005\021\021n\034\006\002\013\005)1oY1mC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!AB*pkJ\034Wm\005\002\n\031A\021QBD\007\002\t%\021q\002\002\002\007\003:L(+\0324\t\013EIA\021\001\n\002\rqJg.\033;?)\0059\001b\002\013\n\005\004%\t!F\001\017\t\0264\027-\0367u\005V47+\033>f+\0051\002CA\007\030\023\tABAA\002J]RDaAG\005!\002\0231\022a\004#fM\006,H\016\036\"vMNK'0\032\021\t\013qIA\021A\017\002\013M$H-\0338\026\003y\001\"\001C\020\n\005\001\022!A\004\"vM\032,'/\0323T_V\0248-\032\005\006E%!\taI\001\rMJ|W.\023;fe\006\024G.\032\013\004I\tM\005C\001\005&\r\025Q!!!\001''\r)Cb\n\t\004QA\032dBA\025/\035\tQS&D\001,\025\tac!\001\004=e>|GOP\005\002\013%\021q\006B\001\ba\006\0347.Y4f\023\t\t$G\001\005Ji\026\024\030\r^8s\025\tyC\001\005\002\016i%\021Q\007\002\002\005\007\"\f'\017C\003\022K\021\005q\007F\001%\021\035ITE1A\007\022i\nA!\033;feV\tq\005C\004=K\001\007I\021A\037\002\013\021,7o\031:\026\003y\002\"a\020\"\017\0055\001\025BA!\005\003\031\001&/\0323fM&\0211\t\022\002\007'R\024\030N\\4\013\005\005#\001b\002$&\001\004%\taR\001\nI\026\0348M]0%KF$\"\001S&\021\0055I\025B\001&\005\005\021)f.\033;\t\0171+\025\021!a\001}\005\031\001\020J\031\t\r9+\003\025)\003?\003\031!Wm]2sA!9\001+\na\001\n\003)\022a\0028feJ|'o\035\005\b%\026\002\r\021\"\001T\003-qWM\035:peN|F%Z9\025\005!#\006b\002'R\003\003\005\rA\006\005\007-\026\002\013\025\002\f\002\0219,'O]8sg\002Bq\001W\023A\002\023\005Q#A\005oo\006\024h.\0338hg\"9!,\na\001\n\003Y\026!\0048xCJt\027N\\4t?\022*\027\017\006\002I9\"9A*WA\001\002\0041\002B\0020&A\003&a#\001\006oo\006\024h.\0338hg\002BQ\001Y\023\005\n\005\fq\001\\5oK:+X\016\006\002?E\")1m\030a\001-\005!A.\0338f\r\021)W\005\0014\003\0311Kg.Z%uKJ\fGo\034:\024\007\021<W\016E\002iWzj\021!\033\006\003U\022\t!bY8mY\026\034G/[8o\023\ta\027N\001\tBEN$(/Y2u\023R,'/\031;peB\031\001\006\r \t\013E!G\021A8\025\003A\004\"!\0353\016\003\025Baa\0353!\002\023!\030AA:c!\t)\b0D\001w\025\t9\030.A\004nkR\f'\r\\3\n\005e4(!D*ue&twMQ;jY\022,'\017\003\005:I\"\025\r\021\"\001|+\005a\bc\001\025~g%\021aP\r\002\021\005V4g-\032:fI&#XM]1u_JD\021\"!\001e\021\003\005\013\025\002?\002\013%$XM\035\021\t\017\005\025A\r\"\001\002\b\005I\021n\035(fo2Lg.\032\013\005\003\023\ty\001E\002\016\003\027I1!!\004\005\005\035\021un\0347fC:Dq!!\005\002\004\001\0071'\001\002dQ\"9\021Q\0033\005\002\005]\021\001B4fi\016$\"!!\003\t\017\005mA\r\"\001\002\036\0059\001.Y:OKb$XCAA\005\021\035\t\t\003\032C\001\003G\tAA\\3yiR\021\021Q\005\t\005\003O\t\t$\004\002\002*)!\0211FA\027\003\021a\027M\\4\013\005\005=\022\001\0026bm\006L1aQA\025\021\035\t)$\nC\001\003o\t\001bZ3u\031&tWm\035\013\002[\"9\0211D\023\005\002\005u\001bBA\021K\021\005\021Q\b\013\002g\0311\021\021I\023\001\003\007\022!\002U8tSRLwN\\3s'\r\ty\004\004\005\f\003\017\nyD!A!\002\023\tI%A\004f]\016|G-\032:\021\007!\tY%C\002\002N\t\021\001\002U8tSRLwN\034\005\b#\005}B\021AA))\021\t\031&!\026\021\007E\fy\004\003\005\002H\005=\003\031AA%\021\035\t\022q\bC\001\0033\"\"!a\025\t\031\005E\021q\ba\001\002\004%\t!!\030\026\003MBA\"!\031\002@\001\007\t\031!C\001\003G\naa\0315`I\025\fHc\001%\002f!AA*a\030\002\002\003\0071\007\003\005\002j\005}\002\025)\0034\003\r\031\007\016\t\005\n\003[\ny\0041A\005\002U\t1\001]8t\021)\t\t(a\020A\002\023\005\0211O\001\ba>\034x\fJ3r)\rA\025Q\017\005\t\031\006=\024\021!a\001-!A\021\021PA A\003&a#\001\003q_N\004\003\"CA?\003\001\r\021\"\001\026\003\025\031G.\0338f\021)\t\t)a\020A\002\023\005\0211Q\001\nG2Lg.Z0%KF$2\001SAC\021!a\025qPA\001\002\0041\002\002CAE\003\001\013\025\002\f\002\r\rd\027N\\3!\021%\ti)a\020A\002\023\005Q#\001\003dG>d\007BCAI\003\001\r\021\"\001\002\024\006A1mY8m?\022*\027\017F\002I\003+C\001\002TAH\003\003\005\rA\006\005\t\0033\013y\004)Q\005-\005)1mY8mA!I\021QTA \001\004%\t!F\001\007i\006\024\027N\\2\t\025\005\005\026q\ba\001\n\003\t\031+\001\006uC\nLgnY0%KF$2\001SAS\021!a\025qTA\001\002\0041\002\002CAU\003\001\013\025\002\f\002\017Q\f'-\0338dA!A\021\021EA \t\003\tidB\004\0020\026B\t!!-\002\037I+G.\031=fIB{7/\033;j_:\0042!]AZ\r\035\t),\nE\001\003o\023qBU3mCb,G\rU8tSRLwN\\\n\005\003g\013I\005C\004\022\003g#\t!a/\025\005\005E\006\002CA`\003g#\t!!1\002\025\rDWmY6J]B,H\017F\003I\003\007\f)\r\003\004d\003{\003\rA\006\005\b\003\017\fi\f1\001\027\003\031\031w\016\\;n]\0369\0211Z\023\t\002\0055\027!\005*fY\006DX\r\032)pg&$\030n\0348feB\031\021/a4\007\017\005EW\005#\001\002T\n\t\"+\0327bq\026$\007k\\:ji&|g.\032:\024\t\005=\0271\013\005\b#\005=G\021AAl)\t\timB\004\002\\\026B\t!!8\002\0319{\007k\\:ji&|g.\032:\021\007E\fyNB\004\002b\026B\t!a9\003\0319{\007k\\:ji&|g.\032:\024\t\005}\0271\013\005\b#\005}G\021AAt)\t\ti\016\003\005\002\"\005}G\021IA\037\021\035\t\t\"\nC\001\003;Ba!!\034&\t\003)\002bBAyK\021\005\0211_\001\fe\026\004xN\035;FeJ|'\017F\004I\003k\f90a?\t\017\0055\024q\036a\001-!9\021\021`Ax\001\004q\024aA7tO\"Q\021Q`Ax!\003\005\r!a@\002\007=,H\017\005\003\003\002\t\025QB\001B\002\025\r\031\021QF\005\005\005\017\021\031AA\006Qe&tGo\025;sK\006l\007b\002B\006K\021%!QB\001\007gB\f7-Z:\025\007y\022y\001C\004\003\022\t%\001\031\001\f\002\0039DqA!\006&\t\003\0219\"\001\004sKB|'\017\036\013\b\021\ne!1\004B\017\021\035\tiGa\005A\002YAq!!?\003\024\001\007a\b\003\005\002~\nM\001\031AA\000\021\035\021\t#\nC\001\005G\tQB]3q_J$x+\031:oS:<Gc\002%\003&\t\035\"\021\006\005\b\003[\022y\0021\001\027\021\035\tIPa\bA\002yB!\"!@\003 A\005\t\031AA\000\021!\021i#\nQ!\n\t=\022!\004:fg\026$h)\0368di&|g\016\005\003\016\005c!\023b\001B\032\t\tIa)\0368di&|g\016\r\005\t\005o)\003\025)\003\003:\005i1\r\\8tK\032+hn\031;j_:\004B!\004B\031\021\"A!QH\023!B\023\t\031&\001\006q_NLG/[8oKJDqA!\021&\t\003\021\031%A\005xSRD'+Z:fiR\031\021O!\022\t\021\t\035#q\ba\001\005_\t\021A\032\005\b\005\027*C\021\001B'\003%9\030\016\0365DY>\034X\rF\002r\005\037B\001Ba\022\003J\001\007!\021\b\005\b\005'*C\021\001B+\003=9\030\016\0365EKN\034'/\0339uS>tGcA9\003X!9!\021\fB)\001\004q\024\001\002;fqRDqA!\030&\t\003\021y&A\bxSRD\007k\\:ji&|g.\0338h)\r\t(\021\r\005\t\005G\022Y\0061\001\002\n\005\021qN\034\005\b\005;*C\021\001B4)\r\t(\021\016\005\t\003[\022)\0071\001\002T!9!QN\023\005\002\t=\024!B2m_N,G#\001%\t\r\tMT\005\"\0018\003\025\021Xm]3u\021%\0219(JI\001\n\003\021I(A\013sKB|'\017^#se>\024H\005Z3gCVdG\017J\032\026\005\tm$\006BA\000\005{Z#Aa \021\t\t\005%1R\007\003\005\007SAA!\"\003\b\006IQO\\2iK\016\\W\r\032\006\004\005\023#\021AC1o]>$\030\r^5p]&!!Q\022BB\005E)hn\0315fG.,GMV1sS\006t7-\032\005\n\005#+\023\023!C\001\005s\nqC]3q_J$x+\031:oS:<G\005Z3gCVdG\017J\032\t\017\tU\025\0051\001\003\030\006A\021\016^3sC\ndW\r\005\003)\0053\033\024b\001BNe\tA\021\n^3sC\ndW\rC\004\003 &!\tA!)\002\021\031\024x.\\\"iCJ$2\001\nBR\021\035\021)K!(A\002M\n\021a\031\005\b\005SKA\021\001BV\003%1'o\\7DQ\006\0248\017F\002%\005[C\001Ba,\003(\002\007!\021W\001\006G\"\f'o\035\t\005\033\tM6'C\002\0036\022\021Q!\021:sCfDqA!/\n\t\003\021Y,\001\006ge>l7\013\036:j]\036$2\001\nB_\021\035\021yLa.A\002y\n\021a\035\005\b\005\007LA\021\001Bc\003!1'o\\7GS2,G\003\002Bd\005'$2A\bBe\021!\021YM!1A\004\t5\027!B2pI\026\034\007c\001\005\003P&\031!\021\033\002\003\013\r{G-Z2\t\017\tU'\021\031a\001}\005!a.Y7f\021\035\021\031-\003C\001\0053$RA\bBn\005;DqA!6\003X\002\007a\bC\004\003`\n]\007\031\001 \002\007\025t7\rC\004\003D&!\tAa9\025\t\t\025(\021\036\013\004=\t\035\b\002\003Bf\005C\004\035A!4\t\021\t-(\021\035a\001\005[\f1!\036:j!\021\021yO!>\016\005\tE(\002\002Bz\003[\t1A\\3u\023\021\0219P!=\003\007U\023\026\nC\004\003D&!\tAa?\025\013y\021iPa@\t\021\t-(\021 a\001\005[DqAa8\003z\002\007a\bC\004\003D&!\taa\001\025\t\r\0251\021\002\013\004=\r\035\001\002\003Bf\007\003\001\035A!4\t\021\r-1\021\001a\001\007\033\tAAZ5mKB!!\021AB\b\023\021\031\tBa\001\003\t\031KG.\032\005\b\005\007LA\021AB\013)\025q2qCB\r\021!\031Yaa\005A\002\r5\001b\002Bp\007'\001\rA\020\005\b\005\007LA\021AB\017)\035q2qDB\021\007GA\001ba\003\004\034\001\0071Q\002\005\b\005?\034Y\0021\001?\021\035\031)ca\007A\002Y\t!BY;gM\026\0248+\033>f\021\035\021\031-\003C\001\007S!baa\013\0040\rEBc\001\020\004.!A!1ZB\024\001\b\021i\r\003\005\004\f\r\035\002\031AB\007\021\035\031)ca\nA\002YAqa!\016\n\t\003\0319$A\005ge>l')\037;fgR!1\021HB\037)\r!31\b\005\t\005\027\034\031\004q\001\003N\"A1qHB\032\001\004\031\t%A\003csR,7\017E\003\016\005g\033\031\005E\002\016\007\013J1aa\022\005\005\021\021\025\020^3\t\017\rU\022\002\"\001\004LQ)Ae!\024\004P!A1qHB%\001\004\031\t\005C\004\003`\016%\003\031\001 \t\017\rM\023\002\"\001\004V\005aaM]8n%\006<()\037;fgR\031Aea\026\t\021\r}2\021\013a\001\007\003Bqaa\027\n\t\003\031i&A\004ge>lWKU%\025\t\r}31\r\013\004=\r\005\004\002\003Bf\0073\002\035A!4\t\021\t-8\021\fa\001\005[Dqaa\032\n\t\003\031I'A\004ge>lWK\025'\025\013y\031Yg!\034\t\017\t}6Q\ra\001}!9!q\\B3\001\004q\004bBB4\023\021\0051\021\017\013\005\007g\0329\bF\002\037\007kB\001Ba3\004p\001\017!Q\032\005\b\005\033y\0071\001?\021\035\0319'\003C\001\007w\"RAHB?\007\017C\001ba \004z\001\0071\021Q\001\004kJd\007\003\002Bx\007\007KAa!\"\003r\n\031QK\025'\t\017\t}7\021\020a\001}!91qM\005\005\002\r-E\003BBG\007##2AHBH\021!\021Ym!#A\004\t5\007\002CB@\007\023\003\ra!!\t\017\rU\025\002\"\001\004\030\006!2M]3bi\026\024UO\0324fe\026$7k\\;sG\026$\"b!'\004\036\016\0356\021VBV)\rq21\024\005\t\005\027\034\031\nq\001\003N\"A1qTBJ\001\004\031\t+A\006j]B,Ho\025;sK\006l\007\003\002B\001\007GKAa!*\003\004\tY\021J\0349viN#(/Z1n\021%\031)ca%\021\002\003\007a\003\003\006\003t\rM\005\023!a\001\005_A!B!\034\004\024B\005\t\031\001B\035\021\035\031y+\003C\001\007c\013qB\032:p[&s\007/\036;TiJ,\027-\034\013\006=\rM6q\027\005\t\007k\033i\0131\001\004\"\006\021\021n\035\005\b\005?\034i\0131\001?\021\035\031y+\003C\001\007w#Ba!0\004BR\031ada0\t\021\t-7\021\030a\002\005\033D\001b!.\004:\002\0071\021\025\005\n\007\013L\021\023!C\001\007\017\fad\031:fCR,')\0364gKJ,GmU8ve\016,G\005Z3gCVdG\017\n\032\026\005\r%'f\001\f\003~!I1QZ\005\022\002\023\0051qZ\001\037GJ,\027\r^3Ck\0324WM]3e'>,(oY3%I\0264\027-\0367uIM*\"a!5+\t\t=\"Q\020\005\n\007+L\021\023!C\001\007/\fad\031:fCR,')\0364gKJ,GmU8ve\016,G\005Z3gCVdG\017\n\033\026\005\re'\006\002B\035\005{\002")
/*     */ public abstract class Source implements Iterator<Object> {
/*     */   private String descr;
/*     */   
/*     */   private int nerrors;
/*     */   
/*     */   private int nwarnings;
/*     */   
/*     */   private Function0<Source> resetFunction;
/*     */   
/*     */   private Function0<BoxedUnit> closeFunction;
/*     */   
/*     */   private Positioner positioner;
/*     */   
/*     */   private volatile RelaxedPosition$ RelaxedPosition$module;
/*     */   
/*     */   private volatile RelaxedPositioner$ RelaxedPositioner$module;
/*     */   
/*     */   private volatile NoPositioner$ NoPositioner$module;
/*     */   
/*     */   public static class Source$$anon$1 extends Source {
/*     */     private final Iterator<Object> iter;
/*     */     
/*     */     public Source$$anon$1(Iterable iterable$1) {
/*  34 */       this.iter = iterable$1.iterator();
/*     */     }
/*     */     
/*     */     public Iterator<Object> iter() {
/*  34 */       return this.iter;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$fromIterable$1 extends AbstractFunction0<Source> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterable iterable$1;
/*     */     
/*     */     public final Source apply() {
/*  35 */       return Source$.MODULE$.fromIterable(this.iterable$1);
/*     */     }
/*     */     
/*     */     public Source$$anonfun$fromIterable$1(Iterable iterable$1) {}
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$fromFile$2 extends AbstractFunction0<BufferedSource> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final File file$1;
/*     */     
/*     */     private final int bufferSize$1;
/*     */     
/*     */     private final Codec codec$2;
/*     */     
/*     */     public final BufferedSource apply() {
/*  95 */       return Source$.MODULE$.fromFile(this.file$1, this.bufferSize$1, this.codec$2);
/*     */     }
/*     */     
/*     */     public Source$$anonfun$fromFile$2(File file$1, int bufferSize$1, Codec codec$2) {}
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$fromFile$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final FileInputStream inputStream$1;
/*     */     
/*     */     public final void apply() {
/*  96 */       this.inputStream$1.close();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*  96 */       this.inputStream$1.close();
/*     */     }
/*     */     
/*     */     public Source$$anonfun$fromFile$1(FileInputStream inputStream$1) {}
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$2 extends AbstractFunction0<BufferedSource> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final InputStream inputStream$2;
/*     */     
/*     */     private final int bufferSize$2;
/*     */     
/*     */     private final Function0 reset$1;
/*     */     
/*     */     private final Function0 close$1;
/*     */     
/*     */     private final Codec codec$3;
/*     */     
/*     */     public final BufferedSource apply() {
/* 159 */       return Source$.MODULE$.createBufferedSource(this.inputStream$2, this.bufferSize$2, this.reset$1, this.close$1, this.codec$3);
/*     */     }
/*     */     
/*     */     public Source$$anonfun$2(InputStream inputStream$2, int bufferSize$2, Function0 reset$1, Function0 close$1, Codec codec$3) {}
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$3 extends AbstractFunction0<BufferedSource> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final InputStream is$1;
/*     */     
/*     */     private final Codec codec$1;
/*     */     
/*     */     public final BufferedSource apply() {
/* 168 */       return Source$.MODULE$.fromInputStream(this.is$1, this.codec$1);
/*     */     }
/*     */     
/*     */     public Source$$anonfun$3(InputStream is$1, Codec codec$1) {}
/*     */   }
/*     */   
/*     */   public static class Source$$anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final InputStream is$1;
/*     */     
/*     */     public final void apply() {
/* 168 */       this.is$1.close();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 168 */       this.is$1.close();
/*     */     }
/*     */     
/*     */     public Source$$anonfun$1(InputStream is$1) {}
/*     */   }
/*     */   
/*     */   public Iterator<Object> seq() {
/* 178 */     return Iterator.class.seq(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 178 */     return Iterator.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean isTraversableAgain() {
/* 178 */     return Iterator.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/* 178 */     return Iterator.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public Iterator<Object> take(int n) {
/* 178 */     return Iterator.class.take(this, n);
/*     */   }
/*     */   
/*     */   public Iterator<Object> drop(int n) {
/* 178 */     return Iterator.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public Iterator<Object> slice(int from, int until) {
/* 178 */     return Iterator.class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> map(Function1 f) {
/* 178 */     return Iterator.class.map(this, f);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> $plus$plus(Function0 that) {
/* 178 */     return Iterator.class.$plus$plus(this, that);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> flatMap(Function1 f) {
/* 178 */     return Iterator.class.flatMap(this, f);
/*     */   }
/*     */   
/*     */   public Iterator<Object> filter(Function1 p) {
/* 178 */     return Iterator.class.filter(this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 178 */     return Iterator.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public Iterator<Object> withFilter(Function1 p) {
/* 178 */     return Iterator.class.withFilter(this, p);
/*     */   }
/*     */   
/*     */   public Iterator<Object> filterNot(Function1 p) {
/* 178 */     return Iterator.class.filterNot(this, p);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> collect(PartialFunction pf) {
/* 178 */     return Iterator.class.collect(this, pf);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 178 */     return Iterator.class.scanLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 178 */     return Iterator.class.scanRight(this, z, op);
/*     */   }
/*     */   
/*     */   public Iterator<Object> takeWhile(Function1 p) {
/* 178 */     return Iterator.class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<Iterator<Object>, Iterator<Object>> partition(Function1 p) {
/* 178 */     return Iterator.class.partition(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<Iterator<Object>, Iterator<Object>> span(Function1 p) {
/* 178 */     return Iterator.class.span(this, p);
/*     */   }
/*     */   
/*     */   public Iterator<Object> dropWhile(Function1 p) {
/* 178 */     return Iterator.class.dropWhile(this, p);
/*     */   }
/*     */   
/*     */   public <B> Iterator<Tuple2<Object, B>> zip(Iterator that) {
/* 178 */     return Iterator.class.zip(this, that);
/*     */   }
/*     */   
/*     */   public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 178 */     return Iterator.class.padTo(this, len, elem);
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<Object, Object>> zipWithIndex() {
/* 178 */     return Iterator.class.zipWithIndex(this);
/*     */   }
/*     */   
/*     */   public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 178 */     return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/* 178 */     Iterator.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/* 178 */     return Iterator.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/* 178 */     return Iterator.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/* 178 */     return Iterator.class.contains(this, elem);
/*     */   }
/*     */   
/*     */   public Option<Object> find(Function1 p) {
/* 178 */     return Iterator.class.find(this, p);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p) {
/* 178 */     return Iterator.class.indexWhere(this, p);
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Object elem) {
/* 178 */     return Iterator.class.indexOf(this, elem);
/*     */   }
/*     */   
/*     */   public BufferedIterator<Object> buffered() {
/* 178 */     return Iterator.class.buffered(this);
/*     */   }
/*     */   
/*     */   public <B> Iterator<Object>.GroupedIterator<B> grouped(int size) {
/* 178 */     return Iterator.class.grouped(this, size);
/*     */   }
/*     */   
/*     */   public <B> Iterator<Object>.GroupedIterator<B> sliding(int size, int step) {
/* 178 */     return Iterator.class.sliding(this, size, step);
/*     */   }
/*     */   
/*     */   public int length() {
/* 178 */     return Iterator.class.length(this);
/*     */   }
/*     */   
/*     */   public Tuple2<Iterator<Object>, Iterator<Object>> duplicate() {
/* 178 */     return Iterator.class.duplicate(this);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 178 */     return Iterator.class.patch(this, from, patchElems, replaced);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/* 178 */     Iterator.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public boolean sameElements(Iterator that) {
/* 178 */     return Iterator.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public Traversable<Object> toTraversable() {
/* 178 */     return Iterator.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public Iterator<Object> toIterator() {
/* 178 */     return Iterator.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public Stream<Object> toStream() {
/* 178 */     return Iterator.class.toStream(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 178 */     return Iterator.class.toString(this);
/*     */   }
/*     */   
/*     */   public <B> int sliding$default$2() {
/* 178 */     return Iterator.class.sliding$default$2(this);
/*     */   }
/*     */   
/*     */   public List<Object> reversed() {
/* 178 */     return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int size() {
/* 178 */     return TraversableOnce.class.size((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/* 178 */     return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/* 178 */     return TraversableOnce.class.count((TraversableOnce)this, p);
/*     */   }
/*     */   
/*     */   public <B> Option<B> collectFirst(PartialFunction pf) {
/* 178 */     return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */   }
/*     */   
/*     */   public <B> B $div$colon(Object z, Function2 op) {
/* 178 */     return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $colon$bslash(Object z, Function2 op) {
/* 178 */     return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/* 178 */     return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/* 178 */     return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/* 178 */     return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/* 178 */     return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceLeftOption(Function2 op) {
/* 178 */     return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceRightOption(Function2 op) {
/* 178 */     return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 reduce(Function2 op) {
/* 178 */     return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> Option<A1> reduceOption(Function2 op) {
/* 178 */     return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 fold(Object z, Function2 op) {
/* 178 */     return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 178 */     return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <B> B sum(Numeric num) {
/* 178 */     return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> B product(Numeric num) {
/* 178 */     return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*     */   }
/*     */   
/*     */   public <B> char min(Ordering cmp) {
/* 178 */     return TraversableOnce.class.min((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> char max(Ordering cmp) {
/* 178 */     return TraversableOnce.class.max((TraversableOnce)this, cmp);
/*     */   }
/*     */   
/*     */   public <B> char maxBy(Function1 f, Ordering cmp) {
/* 178 */     return TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> char minBy(Function1 f, Ordering cmp) {
/* 178 */     return TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> void copyToBuffer(Buffer dest) {
/* 178 */     TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start) {
/* 178 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs) {
/* 178 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/* 178 */     return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<Object> toList() {
/* 178 */     return TraversableOnce.class.toList((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Iterable<Object> toIterable() {
/* 178 */     return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Seq<Object> toSeq() {
/* 178 */     return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> toIndexedSeq() {
/* 178 */     return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Buffer<B> toBuffer() {
/* 178 */     return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/* 178 */     return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Vector<Object> toVector() {
/* 178 */     return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/* 178 */     return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/* 178 */     return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/* 178 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/* 178 */     return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/* 178 */     return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 178 */     return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String sep) {
/* 178 */     return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b) {
/* 178 */     return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 178 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public Source() {
/* 178 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 178 */     TraversableOnce.class.$init$((TraversableOnce)this);
/* 178 */     Iterator.class.$init$(this);
/* 185 */     this.descr = "";
/* 186 */     this.nerrors = 0;
/* 187 */     this.nwarnings = 0;
/* 317 */     this.resetFunction = null;
/* 318 */     this.closeFunction = null;
/* 319 */     this.positioner = RelaxedPositioner();
/*     */   }
/*     */   
/*     */   public String descr() {
/*     */     return this.descr;
/*     */   }
/*     */   
/*     */   public void descr_$eq(String x$1) {
/*     */     this.descr = x$1;
/*     */   }
/*     */   
/*     */   public int nerrors() {
/*     */     return this.nerrors;
/*     */   }
/*     */   
/*     */   public void nerrors_$eq(int x$1) {
/*     */     this.nerrors = x$1;
/*     */   }
/*     */   
/*     */   public int nwarnings() {
/*     */     return this.nwarnings;
/*     */   }
/*     */   
/*     */   public void nwarnings_$eq(int x$1) {
/*     */     this.nwarnings = x$1;
/*     */   }
/*     */   
/*     */   private String lineNum(int line) {
/*     */     return getLines().drop(line - 1).take(1).mkString();
/*     */   }
/*     */   
/*     */   public class LineIterator extends AbstractIterator<String> implements Iterator<String> {
/*     */     private final StringBuilder sb = new StringBuilder();
/*     */     
/*     */     private BufferedIterator<Object> iter;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private BufferedIterator iter$lzycompute() {
/*     */       synchronized (this) {
/*     */         if (!this.bitmap$0) {
/*     */           this.iter = scala$io$Source$LineIterator$$$outer().iter().buffered();
/*     */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/io/Source}.Lscala/io/Source$LineIterator;}} */
/*     */         return this.iter;
/*     */       } 
/*     */     }
/*     */     
/*     */     public BufferedIterator<Object> iter() {
/*     */       return this.bitmap$0 ? this.iter : iter$lzycompute();
/*     */     }
/*     */     
/*     */     public boolean isNewline(char ch) {
/*     */       return (ch == '\r' || ch == '\n');
/*     */     }
/*     */     
/*     */     public boolean getc() {
/*     */       if (iter().hasNext()) {
/*     */         char ch = BoxesRunTime.unboxToChar(iter().next());
/*     */         (iter().hasNext() && BoxesRunTime.unboxToChar(iter().head()) == '\n') ? iter().next() : BoxedUnit.UNIT;
/*     */         this.sb.append(ch);
/*     */         if ((ch == '\n') ? false : (!(ch == '\r')));
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*     */       return iter().hasNext();
/*     */     }
/*     */     
/*     */     public String next() {
/*     */       this.sb.clear();
/*     */       do {
/*     */       
/*     */       } while (getc());
/*     */       return this.sb.toString();
/*     */     }
/*     */     
/*     */     public LineIterator(Source $outer) {}
/*     */   }
/*     */   
/*     */   public Iterator<String> getLines() {
/*     */     return new LineIterator(this);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*     */     return iter().hasNext();
/*     */   }
/*     */   
/*     */   public char next() {
/*     */     return this.positioner.next();
/*     */   }
/*     */   
/*     */   public class Positioner {
/*     */     private final Position encoder;
/*     */     
/*     */     private char ch;
/*     */     
/*     */     public Positioner() {
/*     */       this($outer.RelaxedPosition());
/*     */     }
/*     */     
/*     */     public char ch() {
/*     */       return this.ch;
/*     */     }
/*     */     
/*     */     public void ch_$eq(char x$1) {
/*     */       this.ch = x$1;
/*     */     }
/*     */     
/*     */     private int pos = 0;
/*     */     
/*     */     public int pos() {
/*     */       return this.pos;
/*     */     }
/*     */     
/*     */     public void pos_$eq(int x$1) {
/*     */       this.pos = x$1;
/*     */     }
/*     */     
/*     */     private int cline = 1;
/*     */     
/*     */     public int cline() {
/*     */       return this.cline;
/*     */     }
/*     */     
/*     */     public void cline_$eq(int x$1) {
/*     */       this.cline = x$1;
/*     */     }
/*     */     
/*     */     private int ccol = 1;
/*     */     
/*     */     public int ccol() {
/*     */       return this.ccol;
/*     */     }
/*     */     
/*     */     public void ccol_$eq(int x$1) {
/*     */       this.ccol = x$1;
/*     */     }
/*     */     
/*     */     private int tabinc = 4;
/*     */     
/*     */     public int tabinc() {
/*     */       return this.tabinc;
/*     */     }
/*     */     
/*     */     public void tabinc_$eq(int x$1) {
/*     */       this.tabinc = x$1;
/*     */     }
/*     */     
/*     */     public char next() {
/*     */       ch_$eq(BoxesRunTime.unboxToChar(scala$io$Source$Positioner$$$outer().iter().next()));
/*     */       pos_$eq(this.encoder.encode(cline(), ccol()));
/*     */       char c = ch();
/*     */       switch (c) {
/*     */         default:
/*     */           ccol_$eq(ccol() + 1);
/*     */           return ch();
/*     */         case '\t':
/*     */           ccol_$eq(ccol() + tabinc());
/*     */           return ch();
/*     */         case '\n':
/*     */           break;
/*     */       } 
/*     */       ccol_$eq(1);
/*     */       cline_$eq(cline() + 1);
/*     */       return ch();
/*     */     }
/*     */     
/*     */     public Positioner(Source $outer, Position encoder) {}
/*     */   }
/*     */   
/*     */   private RelaxedPosition$ RelaxedPosition$lzycompute() {
/*     */     synchronized (this) {
/*     */       if (this.RelaxedPosition$module == null)
/*     */         this.RelaxedPosition$module = new RelaxedPosition$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/io/Source}} */
/*     */       return this.RelaxedPosition$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public RelaxedPosition$ RelaxedPosition() {
/*     */     return (this.RelaxedPosition$module == null) ? RelaxedPosition$lzycompute() : this.RelaxedPosition$module;
/*     */   }
/*     */   
/*     */   public class RelaxedPosition$ extends Position {
/*     */     public RelaxedPosition$(Source $outer) {}
/*     */     
/*     */     public void checkInput(int line, int column) {}
/*     */   }
/*     */   
/*     */   private RelaxedPositioner$ RelaxedPositioner$lzycompute() {
/*     */     synchronized (this) {
/*     */       if (this.RelaxedPositioner$module == null)
/*     */         this.RelaxedPositioner$module = new RelaxedPositioner$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/io/Source}} */
/*     */       return this.RelaxedPositioner$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public RelaxedPositioner$ RelaxedPositioner() {
/*     */     return (this.RelaxedPositioner$module == null) ? RelaxedPositioner$lzycompute() : this.RelaxedPositioner$module;
/*     */   }
/*     */   
/*     */   public class RelaxedPositioner$ extends Positioner {
/*     */     public RelaxedPositioner$(Source $outer) {
/*     */       super($outer, $outer.RelaxedPosition());
/*     */     }
/*     */   }
/*     */   
/*     */   private NoPositioner$ NoPositioner$lzycompute() {
/*     */     synchronized (this) {
/*     */       if (this.NoPositioner$module == null)
/*     */         this.NoPositioner$module = new NoPositioner$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/io/Source}} */
/*     */       return this.NoPositioner$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public NoPositioner$ NoPositioner() {
/*     */     return (this.NoPositioner$module == null) ? NoPositioner$lzycompute() : this.NoPositioner$module;
/*     */   }
/*     */   
/*     */   public class NoPositioner$ extends Positioner {
/*     */     public NoPositioner$(Source $outer) {
/*     */       super($outer, Position$.MODULE$);
/*     */     }
/*     */     
/*     */     public char next() {
/*     */       return BoxesRunTime.unboxToChar(scala$io$Source$NoPositioner$$$outer().iter().next());
/*     */     }
/*     */   }
/*     */   
/*     */   public char ch() {
/*     */     return this.positioner.ch();
/*     */   }
/*     */   
/*     */   public int pos() {
/*     */     return this.positioner.pos();
/*     */   }
/*     */   
/*     */   public PrintStream reportError$default$3() {
/*     */     return scala.Console$.MODULE$.err();
/*     */   }
/*     */   
/*     */   public void reportError(int pos, String msg, PrintStream out) {
/*     */     nerrors_$eq(nerrors() + 1);
/*     */     report(pos, msg, out);
/*     */   }
/*     */   
/*     */   private String spaces(int n) {
/*     */     return ((TraversableOnce)scala.collection.immutable.List$.MODULE$.fill(n, (Function0)new Source$$anonfun$spaces$1(this))).mkString();
/*     */   }
/*     */   
/*     */   public class Source$$anonfun$spaces$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final char apply() {
/*     */       return ' ';
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/*     */       return ' ';
/*     */     }
/*     */     
/*     */     public Source$$anonfun$spaces$1(Source $outer) {}
/*     */   }
/*     */   
/*     */   public void report(int pos, String msg, PrintStream out) {
/*     */     int line = Position$.MODULE$.line(pos);
/*     */     int col = Position$.MODULE$.column(pos);
/*     */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */     out.println((new StringOps("%s:%d:%d: %s%s%s^")).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { descr(), BoxesRunTime.boxToInteger(line), BoxesRunTime.boxToInteger(col), msg, lineNum(line), spaces(col - 1) })));
/*     */   }
/*     */   
/*     */   public PrintStream reportWarning$default$3() {
/*     */     return scala.Console$.MODULE$.out();
/*     */   }
/*     */   
/*     */   public void reportWarning(int pos, String msg, PrintStream out) {
/*     */     nwarnings_$eq(nwarnings() + 1);
/*     */     report(pos, (new StringBuilder()).append("warning! ").append(msg).toString(), out);
/*     */   }
/*     */   
/*     */   public Source withReset(Function0<Source> f) {
/* 322 */     this.resetFunction = f;
/* 323 */     return this;
/*     */   }
/*     */   
/*     */   public Source withClose(Function0<BoxedUnit> f) {
/* 326 */     this.closeFunction = f;
/* 327 */     return this;
/*     */   }
/*     */   
/*     */   public Source withDescription(String text) {
/* 330 */     descr_$eq(text);
/* 331 */     return this;
/*     */   }
/*     */   
/*     */   public Source withPositioning(boolean on) {
/* 335 */     this.positioner = on ? RelaxedPositioner() : NoPositioner();
/* 336 */     return this;
/*     */   }
/*     */   
/*     */   public Source withPositioning(Positioner pos) {
/* 339 */     this.positioner = pos;
/* 340 */     return this;
/*     */   }
/*     */   
/*     */   public void close() {
/* 345 */     if (this.closeFunction != null)
/* 345 */       this.closeFunction.apply$mcV$sp(); 
/*     */   }
/*     */   
/*     */   public Source reset() {
/* 350 */     if (this.resetFunction == null)
/* 351 */       throw new UnsupportedOperationException("Source's reset() method was not set."); 
/*     */     return (Source)this.resetFunction.apply();
/*     */   }
/*     */   
/*     */   public static Function0<BoxedUnit> createBufferedSource$default$4() {
/*     */     return Source$.MODULE$.createBufferedSource$default$4();
/*     */   }
/*     */   
/*     */   public static Function0<Source> createBufferedSource$default$3() {
/*     */     return Source$.MODULE$.createBufferedSource$default$3();
/*     */   }
/*     */   
/*     */   public static int createBufferedSource$default$2() {
/*     */     return Source$.MODULE$.createBufferedSource$default$2();
/*     */   }
/*     */   
/*     */   public static BufferedSource fromInputStream(InputStream paramInputStream, Codec paramCodec) {
/*     */     return Source$.MODULE$.fromInputStream(paramInputStream, paramCodec);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromInputStream(InputStream paramInputStream, String paramString) {
/*     */     return Source$.MODULE$.fromInputStream(paramInputStream, paramString);
/*     */   }
/*     */   
/*     */   public static BufferedSource createBufferedSource(InputStream paramInputStream, int paramInt, Function0<Source> paramFunction0, Function0<BoxedUnit> paramFunction01, Codec paramCodec) {
/*     */     return Source$.MODULE$.createBufferedSource(paramInputStream, paramInt, paramFunction0, paramFunction01, paramCodec);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromURL(URL paramURL, Codec paramCodec) {
/*     */     return Source$.MODULE$.fromURL(paramURL, paramCodec);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromURL(URL paramURL, String paramString) {
/*     */     return Source$.MODULE$.fromURL(paramURL, paramString);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromURL(String paramString, Codec paramCodec) {
/*     */     return Source$.MODULE$.fromURL(paramString, paramCodec);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromURL(String paramString1, String paramString2) {
/*     */     return Source$.MODULE$.fromURL(paramString1, paramString2);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromURI(URI paramURI, Codec paramCodec) {
/*     */     return Source$.MODULE$.fromURI(paramURI, paramCodec);
/*     */   }
/*     */   
/*     */   public static Source fromRawBytes(byte[] paramArrayOfbyte) {
/*     */     return Source$.MODULE$.fromRawBytes(paramArrayOfbyte);
/*     */   }
/*     */   
/*     */   public static Source fromBytes(byte[] paramArrayOfbyte, String paramString) {
/*     */     return Source$.MODULE$.fromBytes(paramArrayOfbyte, paramString);
/*     */   }
/*     */   
/*     */   public static Source fromBytes(byte[] paramArrayOfbyte, Codec paramCodec) {
/*     */     return Source$.MODULE$.fromBytes(paramArrayOfbyte, paramCodec);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromFile(File paramFile, int paramInt, Codec paramCodec) {
/*     */     return Source$.MODULE$.fromFile(paramFile, paramInt, paramCodec);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromFile(File paramFile, String paramString, int paramInt) {
/*     */     return Source$.MODULE$.fromFile(paramFile, paramString, paramInt);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromFile(File paramFile, String paramString) {
/*     */     return Source$.MODULE$.fromFile(paramFile, paramString);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromFile(File paramFile, Codec paramCodec) {
/*     */     return Source$.MODULE$.fromFile(paramFile, paramCodec);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromFile(URI paramURI, String paramString) {
/*     */     return Source$.MODULE$.fromFile(paramURI, paramString);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromFile(URI paramURI, Codec paramCodec) {
/*     */     return Source$.MODULE$.fromFile(paramURI, paramCodec);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromFile(String paramString1, String paramString2) {
/*     */     return Source$.MODULE$.fromFile(paramString1, paramString2);
/*     */   }
/*     */   
/*     */   public static BufferedSource fromFile(String paramString, Codec paramCodec) {
/*     */     return Source$.MODULE$.fromFile(paramString, paramCodec);
/*     */   }
/*     */   
/*     */   public static Source fromString(String paramString) {
/*     */     return Source$.MODULE$.fromString(paramString);
/*     */   }
/*     */   
/*     */   public static Source fromChars(char[] paramArrayOfchar) {
/*     */     return Source$.MODULE$.fromChars(paramArrayOfchar);
/*     */   }
/*     */   
/*     */   public static Source fromChar(char paramChar) {
/*     */     return Source$.MODULE$.fromChar(paramChar);
/*     */   }
/*     */   
/*     */   public static Source fromIterable(Iterable<Object> paramIterable) {
/*     */     return Source$.MODULE$.fromIterable(paramIterable);
/*     */   }
/*     */   
/*     */   public static BufferedSource stdin() {
/*     */     return Source$.MODULE$.stdin();
/*     */   }
/*     */   
/*     */   public static int DefaultBufSize() {
/*     */     return Source$.MODULE$.DefaultBufSize();
/*     */   }
/*     */   
/*     */   public abstract Iterator<Object> iter();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\Source.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */