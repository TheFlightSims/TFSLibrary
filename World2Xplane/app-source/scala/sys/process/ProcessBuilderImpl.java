/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.SyncVar;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.sys.package$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r\rgAC\001\003!\003\r\tA\001\005\004@\n\021\002K]8dKN\034()^5mI\026\024\030*\0349m\025\t\031A!A\004qe>\034Wm]:\013\005\0251\021aA:zg*\tq!A\003tG\006d\027m\005\002\001\023A\021!bC\007\002\r%\021AB\002\002\007\003:L(+\0324\t\0139\001A\021\001\t\002\r\021Jg.\033;%\007\001!\022!\005\t\003\025II!a\005\004\003\tUs\027\016\036\004\006+\001\001!A\006\002\016\t\006,Wn\0348Ck&dG-\032:\024\005Q9\002C\001\r\032\033\005\001aA\002\016\001\003\00311DA\bBEN$(/Y2u\005VLG\016Z3s'\025I\022\002\b\021&!\tib$D\001\003\023\ty\"A\001\bQe>\034Wm]:Ck&dG-\032:\021\005a\t\023B\001\022$\005\021\031\026N\\6\013\005\021\022\021A\004)s_\016,7o\035\"vS2$WM\035\t\0031\031J!aJ\022\003\rM{WO]2f\021\025I\023\004\"\001+\003\031a\024N\\5u}Q\tq\003C\003-3\021EQ&\001\005u_N{WO]2f+\0059\002\"B\030\032\t#i\023A\002;p'&t7\016C\00323\021\005!'A\005%Q\006\034\b\016\n2beR\021Ad\r\005\006iA\002\r\001H\001\006_RDWM\035\005\006me!\taN\001\016I!\f7\017\033\023cCJ$#-\031:\025\005qA\004\"\002\0336\001\004a\002\"\002\036\032\t\003Y\024!\004\023iCNDG%Y7qI\005l\007\017\006\002\035y!)A'\017a\0019!)a(\007C\001\005yA\005[1tQ\022B\027m\0355%Q\006\034\b\016\006\002\035\001\")A'\020a\0019!)!)\007C\001\007\006\031!/\0368\025\003\021\003\"!H#\n\005\031\023!a\002)s_\016,7o\035\005\006\005f!\t\001\023\013\003\t&CQAS$A\002-\013AbY8o]\026\034G/\0238qkR\004\"A\003'\n\00553!a\002\"p_2,\027M\034\005\006\005f!\ta\024\013\003\tBCQ!\025(A\002I\0131\001\\8h!\ti2+\003\002U\005\ti\001K]8dKN\034Hj\\4hKJDQAQ\r\005\002Y#2\001R,Y\021\025\tV\0131\001S\021\025QU\0131\001L\021\025Q\026\004\"\001\\\003)!#-\0318hI\t\fgnZ\013\0029B\021Q\f\031\b\003\025yK!a\030\004\002\rA\023X\rZ3g\023\t\t'M\001\004TiJLgn\032\006\003?\032AQAW\r\005\002\021$\"\001X3\t\013E\033\007\031\001*\t\013\035LB\021A.\002\037\021\022\027M\\4%E\006tw\r\n7fgNDQaZ\r\005\002%$\"\001\0306\t\013EC\007\031\001*\t\0131LB\021A7\002\0131Lg.Z:\026\0039\0042a\\<]\035\t\001XO\004\002ri6\t!O\003\002t\037\0051AH]8pizJ\021aB\005\003m\032\tq\001]1dW\006<W-\003\002ys\n11\013\036:fC6T!A\036\004\t\0131LB\021A>\025\0059d\b\"B){\001\004\021\006\"\002@\032\t\003i\027a\0037j]\026\034x\f\n2b]\036DaA`\r\005\002\005\005Ac\0018\002\004!)\021k a\001%\"9\021qA\r\005\002\005%\021!\002\023cC:<WCAA\006!\rQ\021QB\005\004\003\0371!aA%oi\"9\021qA\r\005\002\005MA\003BA\006\003+A\001\"a\006\002\022\001\007\021\021D\001\003S>\0042!HA\016\023\r\tiB\001\002\n!J|7-Z:t\023>Cq!a\002\032\t\003\t\t\003\006\003\002\f\005\r\002BB)\002 \001\007!\013C\004\002(e!\t!!\003\002\025\021\022\027M\\4%Y\026\0348\017C\004\002(e!\t!a\013\025\t\005-\021Q\006\005\007#\006%\002\031\001*\t\017\005E\022\004\"\001\0024\005QA-Y3n_:L'0\0323\025\003qA\001\"a\016\032A\023%\021\021H\001\006g2,(\017\035\013\0069\006m\0221\t\005\b#\006U\002\031AA\037!\021Q\021q\b*\n\007\005\005cA\001\004PaRLwN\034\005\b\003\013\n)\0041\001L\003\0319\030\016\0365J]\"9A.\007Q\005\n\005%Cc\0028\002L\005=\0231\013\005\b\003\033\n9\0051\001L\003%9\030\016\0365J]B,H\017C\004\002R\005\035\003\031A&\002!9|gNW3s_\026C8-\0329uS>t\007bB)\002H\001\007\021Q\b\005\t\003/J\002\025\"\003\002Z\005Y!/\0368Ck\0324WM]3e)\031\tY!a\027\002^!1\021+!\026A\002ICaASA+\001\004Y\005bBA13\021\005\0211M\001\nG\006t\007+\0339f)>,\022a\023\005\b\003OJB\021AA2\0031A\027m]#ySR4\026\r\\;f\021%\tY\007\006B\001B\003%A$\001\006v]\022,'\017\\=j]\036Da!\013\013\005\002\005=D\003BA9\003g\002\"\001\007\013\t\017\005-\024Q\016a\0019!1!\t\006C\003\003o\"2\001RA=\021!\t9\"!\036A\002\005eaaBA?\001\001\021\021q\020\002\006\tVlW._\n\004\003w:\002BCAB\003w\022)\031!C!7\006AAo\\*ue&tw\r\003\006\002\b\006m$\021!Q\001\nq\013\021\002^8TiJLgn\032\021\t\027\005-\0251\020B\001J\003%\021QR\001\nKbLGOV1mk\026\004RACAH\003\027I1!!%\007\005!a$-\0378b[\026t\004bB\025\002|\021\005\021Q\023\013\007\003/\013I*a'\021\007a\tY\bC\004\002\004\006M\005\031\001/\t\023\005-\0251\023CA\002\0055\005b\002\"\002|\021\005\023q\024\013\004\t\006\005\006\002CA\f\003;\003\r!!\007\t\021\005\005\0241\020C!\003G2q!a*\001\001\t\tIK\001\005V%2Ke\016];u'\021\t)+a+\021\007a\tiKB\004\0020\002\001!!!-\003\035%\033FO]3b[\n+\030\016\0343feN!\021QVAZ!\rA\022Q\027\004\t\003o\003\021\021\001\002\002:\niA\013\033:fC\022\024U/\0337eKJ\0342!!.\030\021)\t\031)!.\003\006\004%\te\027\005\013\003\017\013)L!A!\002\023a\006bCAa\003k\023\t\021)A\005\003\007\fqA];o\0236\004H\016\005\004\013\003\013\fI\"E\005\004\003\0174!!\003$v]\016$\030n\03482\021\035I\023Q\027C\001\003\027$b!a-\002N\006=\007bBAB\003\023\004\r\001\030\005\t\003\003\fI\r1\001\002D\"9!)!.\005B\005MGc\001#\002V\"A\021qCAi\001\004\tI\002C\006\002Z\0065&\021!S\001\n\005m\027AB:ue\026\fW\016E\003\013\003\037\013i\016\005\003\002`\006\025hbA\017\002b&\031\0211\035\002\002\037A\024xnY3tg&sG/\032:oC2LA!a:\002j\nY\021J\0349viN#(/Z1n\025\r\t\031O\001\005\r\003[\fiK!A!\002\023a\0261X\001\006Y\006\024W\r\034\005\bS\0055F\021AAy)\031\tY+a=\002v\"I\021\021\\Ax\t\003\007\0211\034\005\b\003[\fy\0171\001]\021!\t9'!,\005B\005\r\004bCA~\003K\023\t\021)A\005\003{\f1!\036:m!\021\ty.a@\n\t\t\005\021\021\036\002\004+Jc\005bB\025\002&\022\005!Q\001\013\005\005\017\021I\001E\002\031\003KC\001\"a?\003\004\001\007\021Q \004\b\005\033\001\001A\001B\b\005%1\025\016\\3J]B,Ho\005\003\003\f\005-\006b\003B\n\005\027\021\t\021)A\005\005+\tAAZ5mKB!\021q\034B\f\023\021\021I\"!;\003\t\031KG.\032\005\bS\t-A\021\001B\017)\021\021yB!\t\021\007a\021Y\001\003\005\003\024\tm\001\031\001B\013\r\035\021)\003\001\001\003\005O\021!BR5mK>+H\017];u'\021\021\031C!\013\021\007a\021YCB\004\003.\001\001!Aa\f\003\035=\033FO]3b[\n+\030\016\0343feN!!1FAZ\021-\tINa\013\003\002\023\006IAa\r\021\013)\tyI!\016\021\t\005}'qG\005\005\005s\tIO\001\007PkR\004X\017^*ue\026\fW\016\003\007\002n\n-\"\021!Q\001\nq\013Y\fC\004*\005W!\tAa\020\025\r\t%\"\021\tB\"\021%\tIN!\020\005\002\004\021\031\004C\004\002n\nu\002\031\001/\t\021\005\035$1\006C!\003GB1Ba\005\003$\t\005\t\025!\003\003\026!Q!1\nB\022\005\003\005\013\021B&\002\r\005\004\b/\0328e\021\035I#1\005C\001\005\037\"bA!\025\003T\tU\003c\001\r\003$!A!1\003B'\001\004\021)\002C\004\003L\t5\003\031A&\007\017\te\003\001\001\002\003\\\t11+[7qY\026\0342Aa\026\030\021-\021yFa\026\003\002\003\006IA!\031\002\003A\004B!a8\003d%!!QMAu\005=Q\005K]8dKN\034()^5mI\026\024\bbB\025\003X\021\005!\021\016\013\005\005W\022i\007E\002\031\005/B\001Ba\030\003h\001\007!\021\r\005\b\005\n]C\021\tB9)\r!%1\017\005\t\003/\021y\0071\001\002\032!A\0211\021B,\t\003\0229\b\006\002\003zA!!1\020BC\033\t\021iH\003\003\003\000\t\005\025\001\0027b]\036T!Aa!\002\t)\fg/Y\005\004C\nu\004\002CA1\005/\"\t%a\031\007\017\t-\005\001\001\002\003\016\n9QK\025'J[Bd7C\002BE\023\t=U\005E\002\031\005#K1Aa%$\005))&\013\024\"vS2$WM\035\005\f\003w\024II!A!\002\023\ti\020C\004*\005\023#\tA!'\025\t\tm%Q\024\t\0041\t%\005\002CA~\005/\003\r!!@\t\0171\022I\t\"\005\003\"V\021!q\001\004\b\005K\003\001A\001BT\005!1\025\016\\3J[Bd7c\002BR\023\t%\006%\n\t\0041\t-\026b\001BWG\tYa)\0337f\005VLG\016Z3s\021-\021\tLa)\003\002\003\006IA!\006\002\t\t\f7/\032\005\bS\t\rF\021\001B[)\021\0219L!/\021\007a\021\031\013\003\005\0032\nM\006\031\001B\013\021\035a#1\025C\t\005{+\"Aa\b\t\017=\022\031\013\"\005\003BV\021!\021\013\005\t\005\013\024\031\013\"\001\003H\006yA\005[1tQ\022bWm]:%Y\026\0348\017F\002\035\005\023D\001Ba3\003D\002\007!QC\001\002M\"A!Q\031BR\t\003\021y\rF\002\035\005#D\001Ba5\003N\002\007\021Q`\001\002k\"A!Q\031BR\t\003\0219\016F\002\035\0053D\021Ba7\003V\022\005\r!a7\002\003MD\001B!2\003$\022\005!q\034\013\0049\t\005\bb\002Br\005;\004\r\001H\001\002E\032A!q\035\001\002\002\t\021IO\001\007CCNL7MQ;jY\022,'oE\002\003f^Aq!\013Bs\t\003\021i\017\006\002\003pB\031\001D!:\t\023\tM(Q\035Q\005\022\tU\030\001D2iK\016\\gj\034;UQ&\034HcA\t\003x\"9!\021 By\001\004a\022!A1\t\017\t\023)\017\"\002\003~R\031AIa@\t\021\005]!1 a\001\0033A\021ba\001\003f\0026\tb!\002\002\033\r\024X-\031;f!J|7-Z:t)\021\0319aa\006\021\t\r%1q\002\b\004;\r-\021bAB\007\005\0059\001K]8dKN\034\030\002BB\t\007'\021ABQ1tS\016\004&o\\2fgNL1a!\006\003\005-\001&o\\2fgNLU\016\0357\t\021\005]1\021\001a\001\00331\001ba\007\001\003\003\0211Q\004\002\022'\026\fX/\0328uS\006d')^5mI\026\0248\003BB\r\005_D!B!?\004\032\t\005\t\025!\003\035\021)\021\031o!\007\003\002\003\006I\001\b\005\013\007K\031IB!A!\002\023a\026AD8qKJ\fGo\034:TiJLgn\032\005\bS\reA\021AB\025)!\031Yc!\f\0040\rE\002c\001\r\004\032!9!\021`B\024\001\004a\002b\002Br\007O\001\r\001\b\005\b\007K\0319\0031\001]\021!\t\031i!\007\005B\t]daBB\034\001\001\0211\021\b\002\r!&\004X\r\032\"vS2$WM]\n\005\007k\031Y\003\003\006\004>\rU\"\021!Q\001\nq\tQAZ5sgRD!b!\021\0046\t\005\t\025!\003\035\003\031\031XmY8oI\"Q1QIB\033\005\003\005\013\021B&\002\017Q|WI\035:pe\"9\021f!\016\005\002\r%C\003CB&\007\033\032ye!\025\021\007a\031)\004C\004\004>\r\035\003\031\001\017\t\017\r\0053q\ta\0019!91QIB$\001\004Y\005\002CB\002\007k!\te!\026\025\t\r]3Q\f\t\005\007\023\031I&\003\003\004\\\rM!A\004)ja\026$\007K]8dKN\034Xm\035\005\t\003/\031\031\0061\001\002\032\03191\021\r\001\001\005\r\r$AC!oI\n+\030\016\0343feN!1qLB\026\021)\031ida\030\003\002\003\006I\001\b\005\013\007\003\032yF!A!\002\023a\002bB\025\004`\021\00511\016\013\007\007[\032yg!\035\021\007a\031y\006C\004\004>\r%\004\031\001\017\t\017\r\0053\021\016a\0019!A11AB0\t\003\032)\b\006\003\004x\ru\004\003BB\005\007sJAaa\037\004\024\tQ\021I\0343Qe>\034Wm]:\t\021\005]11\017a\001\00331qa!!\001\001\t\031\031IA\005Pe\n+\030\016\0343feN!1qPB\026\021)\031ida \003\002\003\006I\001\b\005\013\007\003\032yH!A!\002\023a\002bB\025\004\000\021\00511\022\013\007\007\033\033yi!%\021\007a\031y\bC\004\004>\r%\005\031\001\017\t\017\r\0053\021\022a\0019!A11AB@\t\003\032)\n\006\003\004\030\016u\005\003BB\005\0073KAaa'\004\024\tIqJ\035)s_\016,7o\035\005\t\003/\031\031\n1\001\002\032\03191\021\025\001\001\005\r\r&aD*fcV,gnY3Ck&dG-\032:\024\t\r}51\006\005\013\007{\031yJ!A!\002\023a\002BCB!\007?\023\t\021)A\0059!9\021fa(\005\002\r-FCBBW\007_\033\t\fE\002\031\007?Cqa!\020\004*\002\007A\004C\004\004B\r%\006\031\001\017\t\021\r\r1q\024C!\007k#Baa.\004>B!1\021BB]\023\021\031Yla\005\003\037A\023xnY3tgN+\027/^3oG\026D\001\"a\006\0044\002\007\021\021\004\b\004;\r\005\027B\001\023\003\001")
/*     */ public interface ProcessBuilderImpl {
/*     */   public class DaemonBuilder extends AbstractBuilder {
/*     */     private final ProcessBuilder underlying;
/*     */     
/*     */     public DaemonBuilder(ProcessBuilder$ $outer, ProcessBuilder underlying) {
/*  21 */       super($outer);
/*     */     }
/*     */     
/*     */     public final Process run(ProcessIO io) {
/*  22 */       return this.underlying.run(io.daemonized());
/*     */     }
/*     */   }
/*     */   
/*     */   public class Dummy extends AbstractBuilder {
/*     */     private final String toString;
/*     */     
/*     */     private final Function0<Object> exitValue;
/*     */     
/*     */     public String toString() {
/*  25 */       return this.toString;
/*     */     }
/*     */     
/*     */     public Dummy(ProcessBuilder$ $outer, String toString, Function0<Object> exitValue) {
/*  25 */       super($outer);
/*     */     }
/*     */     
/*     */     public Process run(ProcessIO io) {
/*  26 */       return new ProcessImpl.DummyProcess(Process$.MODULE$, this.exitValue);
/*     */     }
/*     */     
/*     */     public boolean canPipeTo() {
/*  27 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public class URLInput extends IStreamBuilder {
/*     */     public URLInput(ProcessBuilder$ $outer, URL url) {
/*  30 */       super($outer, (Function0<InputStream>)new ProcessBuilderImpl$URLInput$$anonfun$$init$$1($outer, url), url.toString());
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$URLInput$$anonfun$$init$$1 extends AbstractFunction0<InputStream> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final URL url$1;
/*     */       
/*     */       public final InputStream apply() {
/*  30 */         return this.url$1.openStream();
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$URLInput$$anonfun$$init$$1(ProcessBuilder$ $outer, URL url$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class FileInput extends IStreamBuilder {
/*     */     public FileInput(ProcessBuilder$ $outer, File file) {
/*  31 */       super($outer, (Function0<InputStream>)new ProcessBuilderImpl$FileInput$$anonfun$$init$$2($outer, file), file.getAbsolutePath());
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$FileInput$$anonfun$$init$$2 extends AbstractFunction0<FileInputStream> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final File file$2;
/*     */       
/*     */       public final FileInputStream apply() {
/*  31 */         return new FileInputStream(this.file$2);
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$FileInput$$anonfun$$init$$2(ProcessBuilder$ $outer, File file$2) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class FileOutput extends OStreamBuilder {
/*     */     public FileOutput(ProcessBuilder$ $outer, File file, boolean append) {
/*  32 */       super($outer, (Function0<OutputStream>)new ProcessBuilderImpl$FileOutput$$anonfun$$init$$3($outer, file, append), file.getAbsolutePath());
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$FileOutput$$anonfun$$init$$3 extends AbstractFunction0<FileOutputStream> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final File file$1;
/*     */       
/*     */       private final boolean append$1;
/*     */       
/*     */       public final FileOutputStream apply() {
/*  32 */         return new FileOutputStream(this.file$1, this.append$1);
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$FileOutput$$anonfun$$init$$3(ProcessBuilder$ $outer, File file$1, boolean append$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class OStreamBuilder extends ThreadBuilder {
/*     */     public OStreamBuilder(ProcessBuilder$ $outer, Function0 stream, String label) {
/*  34 */       super($outer, 
/*     */           
/*  37 */           label, (Function1<ProcessIO, BoxedUnit>)new ProcessBuilderImpl$OStreamBuilder$$anonfun$$init$$4($outer, stream));
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$OStreamBuilder$$anonfun$$init$$4 extends AbstractFunction1<ProcessIO, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function0 stream$1;
/*     */       
/*     */       public final void apply(ProcessIO x$1) {
/*  37 */         x$1.writeInput().apply(BasicIO.Uncloseable$.MODULE$.protect((OutputStream)this.stream$1.apply()));
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$OStreamBuilder$$anonfun$$init$$4(ProcessBuilder$ $outer, Function0 stream$1) {}
/*     */     }
/*     */     
/*     */     public boolean hasExitValue() {
/*  38 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public class IStreamBuilder extends ThreadBuilder {
/*     */     public IStreamBuilder(ProcessBuilder$ $outer, Function0 stream, String label) {
/*  41 */       super($outer, 
/*     */           
/*  44 */           label, (Function1<ProcessIO, BoxedUnit>)new ProcessBuilderImpl$IStreamBuilder$$anonfun$$init$$5($outer, stream));
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$IStreamBuilder$$anonfun$$init$$5 extends AbstractFunction1<ProcessIO, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function0 stream$2;
/*     */       
/*     */       public final void apply(ProcessIO x$2) {
/*  44 */         x$2.processOutput().apply(BasicIO.Uncloseable$.MODULE$.protect((InputStream)this.stream$2.apply()));
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$IStreamBuilder$$anonfun$$init$$5(ProcessBuilder$ $outer, Function0 stream$2) {}
/*     */     }
/*     */     
/*     */     public boolean hasExitValue() {
/*  45 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class ThreadBuilder extends AbstractBuilder {
/*     */     private final String toString;
/*     */     
/*     */     public final Function1<ProcessIO, BoxedUnit> scala$sys$process$ProcessBuilderImpl$ThreadBuilder$$runImpl;
/*     */     
/*     */     public String toString() {
/*  49 */       return this.toString;
/*     */     }
/*     */     
/*     */     public ThreadBuilder(ProcessBuilder$ $outer, String toString, Function1<ProcessIO, BoxedUnit> runImpl) {
/*     */       super($outer);
/*     */     }
/*     */     
/*     */     public Process run(ProcessIO io) {
/*  54 */       SyncVar<Object> success = new SyncVar();
/*  55 */       success.put(BoxesRunTime.boxToBoolean(false));
/*  56 */       Thread t = Process$.MODULE$.Spawn().apply((Function0<BoxedUnit>)new ProcessBuilderImpl$ThreadBuilder$$anonfun$1(this, io, success), 
/*     */           
/*  59 */           io.daemonizeThreads());
/*  61 */       return new ProcessImpl.ThreadProcess(Process$.MODULE$, t, success);
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$ThreadBuilder$$anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ProcessIO io$1;
/*     */       
/*     */       private final SyncVar success$1;
/*     */       
/*     */       public final void apply() {
/*     */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$ThreadBuilder$$anonfun$1(ProcessBuilderImpl.ThreadBuilder $outer, ProcessIO io$1, SyncVar success$1) {}
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.$outer.scala$sys$process$ProcessBuilderImpl$ThreadBuilder$$runImpl.apply(this.io$1);
/*     */         this.success$1.set(BoxesRunTime.boxToBoolean(true));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class Simple extends AbstractBuilder {
/*     */     private final java.lang.ProcessBuilder p;
/*     */     
/*     */     public Simple(ProcessBuilder$ $outer, java.lang.ProcessBuilder p) {
/*  66 */       super($outer);
/*     */     }
/*     */     
/*     */     public Process run(ProcessIO io) {
/*  68 */       java.lang.Process process = this.p.start();
/*  72 */       Thread inThread = Process$.MODULE$.Spawn().apply((Function0<BoxedUnit>)new ProcessBuilderImpl$Simple$$anonfun$2(this, io, process), true);
/*  73 */       Thread outThread = Process$.MODULE$.Spawn().apply((Function0<BoxedUnit>)new ProcessBuilderImpl$Simple$$anonfun$3(this, io, process), io.daemonizeThreads());
/*  76 */       (new Thread[1])[0] = Process$.MODULE$.Spawn().apply((Function0<BoxedUnit>)new ProcessBuilderImpl$Simple$$anonfun$4(this, io, process), io.daemonizeThreads());
/*  76 */       List errorThread = this.p.redirectErrorStream() ? (List)Nil$.MODULE$ : List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Thread[1]));
/*  78 */       return new ProcessImpl.SimpleProcess(Process$.MODULE$, process, inThread, errorThread.$colon$colon(outThread));
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$Simple$$anonfun$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ProcessIO io$2;
/*     */       
/*     */       public final java.lang.Process process$1;
/*     */       
/*     */       public final void apply() {
/*     */         this.io$2.writeInput().apply(this.process$1.getOutputStream());
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.io$2.writeInput().apply(this.process$1.getOutputStream());
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$Simple$$anonfun$2(ProcessBuilderImpl.Simple $outer, ProcessIO io$2, java.lang.Process process$1) {}
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$Simple$$anonfun$3 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ProcessIO io$2;
/*     */       
/*     */       public final java.lang.Process process$1;
/*     */       
/*     */       public final void apply() {
/*     */         this.io$2.processOutput().apply(this.process$1.getInputStream());
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.io$2.processOutput().apply(this.process$1.getInputStream());
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$Simple$$anonfun$3(ProcessBuilderImpl.Simple $outer, ProcessIO io$2, java.lang.Process process$1) {}
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$Simple$$anonfun$4 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ProcessIO io$2;
/*     */       
/*     */       public final java.lang.Process process$1;
/*     */       
/*     */       public final void apply() {
/*     */         this.io$2.processError().apply(this.process$1.getErrorStream());
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.io$2.processError().apply(this.process$1.getErrorStream());
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$Simple$$anonfun$4(ProcessBuilderImpl.Simple $outer, ProcessIO io$2, java.lang.Process process$1) {}
/*     */     }
/*     */     
/*     */     public String toString() {
/*  80 */       return this.p.command().toString();
/*     */     }
/*     */     
/*     */     public boolean canPipeTo() {
/*  81 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class AbstractBuilder implements ProcessBuilder {
/*     */     public ProcessBuilder $hash$less(File f) {
/*  84 */       return ProcessBuilder.Sink$class.$hash$less(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less(URL f) {
/*  84 */       return ProcessBuilder.Sink$class.$hash$less(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less(Function0 in) {
/*  84 */       return ProcessBuilder.Sink$class.$hash$less(this, in);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less(ProcessBuilder b) {
/*  84 */       return ProcessBuilder.Sink$class.$hash$less(this, b);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater(File f) {
/*  84 */       return ProcessBuilder.Source$class.$hash$greater(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater$greater(File f) {
/*  84 */       return ProcessBuilder.Source$class.$hash$greater$greater(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater(Function0 out) {
/*  84 */       return ProcessBuilder.Source$class.$hash$greater(this, out);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater(ProcessBuilder b) {
/*  84 */       return ProcessBuilder.Source$class.$hash$greater(this, b);
/*     */     }
/*     */     
/*     */     public ProcessBuilder cat() {
/*  84 */       return ProcessBuilder.Source$class.cat(this);
/*     */     }
/*     */     
/*     */     public AbstractBuilder(ProcessBuilder$ $outer) {
/*  84 */       ProcessBuilder.Source$class.$init$(this);
/*  84 */       ProcessBuilder.Sink$class.$init$(this);
/*     */     }
/*     */     
/*     */     public AbstractBuilder toSource() {
/*  85 */       return this;
/*     */     }
/*     */     
/*     */     public AbstractBuilder toSink() {
/*  86 */       return this;
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$bar(ProcessBuilder other) {
/*  89 */       boolean bool = other.canPipeTo();
/*  89 */       Predef$ predef$ = Predef$.MODULE$;
/*  89 */       if (bool)
/*  90 */         return new ProcessBuilderImpl.PipedBuilder(scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer(), this, other, false); 
/*     */       throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append("Piping to multiple processes is not supported.").toString());
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$bar$bar(ProcessBuilder other) {
/*  92 */       return new ProcessBuilderImpl.OrBuilder(scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer(), this, other);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$amp$amp(ProcessBuilder other) {
/*  93 */       return new ProcessBuilderImpl.AndBuilder(scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer(), this, other);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$hash$hash(ProcessBuilder other) {
/*  94 */       return new ProcessBuilderImpl.SequenceBuilder(scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer(), this, other);
/*     */     }
/*     */     
/*     */     public Process run() {
/*  96 */       return run(false);
/*     */     }
/*     */     
/*     */     public Process run(boolean connectInput) {
/*  97 */       return run(BasicIO$.MODULE$.standard(connectInput));
/*     */     }
/*     */     
/*     */     public Process run(ProcessLogger log) {
/*  98 */       return run(log, false);
/*     */     }
/*     */     
/*     */     public Process run(ProcessLogger log, boolean connectInput) {
/*  99 */       return run(BasicIO$.MODULE$.apply(connectInput, log));
/*     */     }
/*     */     
/*     */     public String $bang$bang() {
/* 101 */       return slurp((Option<ProcessLogger>)None$.MODULE$, false);
/*     */     }
/*     */     
/*     */     public String $bang$bang(ProcessLogger log) {
/* 102 */       return slurp((Option<ProcessLogger>)new Some(log), false);
/*     */     }
/*     */     
/*     */     public String $bang$bang$less() {
/* 103 */       return slurp((Option<ProcessLogger>)None$.MODULE$, true);
/*     */     }
/*     */     
/*     */     public String $bang$bang$less(ProcessLogger log) {
/* 104 */       return slurp((Option<ProcessLogger>)new Some(log), true);
/*     */     }
/*     */     
/*     */     public Stream<String> lines() {
/* 106 */       return lines(false, true, (Option<ProcessLogger>)None$.MODULE$);
/*     */     }
/*     */     
/*     */     public Stream<String> lines(ProcessLogger log) {
/* 107 */       return lines(false, true, (Option<ProcessLogger>)new Some(log));
/*     */     }
/*     */     
/*     */     public Stream<String> lines_$bang() {
/* 108 */       return lines(false, false, (Option<ProcessLogger>)None$.MODULE$);
/*     */     }
/*     */     
/*     */     public Stream<String> lines_$bang(ProcessLogger log) {
/* 109 */       return lines(false, false, (Option<ProcessLogger>)new Some(log));
/*     */     }
/*     */     
/*     */     public int $bang() {
/* 111 */       return run(false).exitValue();
/*     */     }
/*     */     
/*     */     public int $bang(ProcessIO io) {
/* 112 */       return run(io).exitValue();
/*     */     }
/*     */     
/*     */     public int $bang(ProcessLogger log) {
/* 113 */       return runBuffered(log, false);
/*     */     }
/*     */     
/*     */     public int $bang$less() {
/* 114 */       return run(true).exitValue();
/*     */     }
/*     */     
/*     */     public int $bang$less(ProcessLogger log) {
/* 115 */       return runBuffered(log, true);
/*     */     }
/*     */     
/*     */     public ProcessBuilder daemonized() {
/* 124 */       return new ProcessBuilderImpl.DaemonBuilder(scala$sys$process$ProcessBuilderImpl$AbstractBuilder$$$outer(), this);
/*     */     }
/*     */     
/*     */     private String slurp(Option<ProcessLogger> log, boolean withIn) {
/* 127 */       StringBuffer buffer = new StringBuffer();
/* 128 */       int code = $bang(BasicIO$.MODULE$.apply(withIn, buffer, log));
/* 130 */       if (code == 0)
/* 130 */         return buffer.toString(); 
/* 131 */       throw package$.MODULE$.error((new StringBuilder()).append("Nonzero exit value: ").append(BoxesRunTime.boxToInteger(code)).toString());
/*     */     }
/*     */     
/*     */     private Stream<String> lines(boolean withInput, boolean nonZeroException, Option<ProcessLogger> log) {
/* 139 */       BasicIO.Streamed<?> streamed = BasicIO.Streamed$.MODULE$.apply(nonZeroException);
/* 140 */       Function1<?, BoxedUnit> function1 = streamed.process();
/* 140 */       BasicIO$ basicIO$ = BasicIO$.MODULE$;
/* 140 */       Process process = run(new ProcessIO((Function1<OutputStream, BoxedUnit>)new BasicIO$$anonfun$input$1(withInput), (Function1<InputStream, BoxedUnit>)new BasicIO$$anonfun$processFully$1(function1), basicIO$.getErr(log)));
/* 142 */       ProcessBuilderImpl$AbstractBuilder$$anonfun$lines$1 processBuilderImpl$AbstractBuilder$$anonfun$lines$1 = new ProcessBuilderImpl$AbstractBuilder$$anonfun$lines$1(this, streamed, process);
/* 142 */       ProcessImpl.Spawn$ spawn$ = Process$.MODULE$.Spawn();
/* 142 */       Thread thread1 = new ProcessImpl$Spawn$$anon$1(spawn$, (Function0)processBuilderImpl$AbstractBuilder$$anonfun$lines$1);
/* 142 */       thread1.setDaemon(false);
/* 142 */       thread1.start();
/* 143 */       return (Stream<String>)streamed.stream().apply();
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$AbstractBuilder$$anonfun$lines$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final BasicIO.Streamed streamed$1;
/*     */       
/*     */       public final Process process$2;
/*     */       
/*     */       public final void apply() {
/*     */         this.streamed$1.done().apply$mcVI$sp(this.process$2.exitValue());
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.streamed$1.done().apply$mcVI$sp(this.process$2.exitValue());
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$AbstractBuilder$$anonfun$lines$1(ProcessBuilderImpl.AbstractBuilder $outer, BasicIO.Streamed streamed$1, Process process$2) {}
/*     */     }
/*     */     
/*     */     private int runBuffered(ProcessLogger log, boolean connectInput) {
/* 147 */       return BoxesRunTime.unboxToInt(log.buffer((Function0<?>)new ProcessBuilderImpl$AbstractBuilder$$anonfun$runBuffered$1(this, log, connectInput)));
/*     */     }
/*     */     
/*     */     public class ProcessBuilderImpl$AbstractBuilder$$anonfun$runBuffered$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ProcessLogger log$1;
/*     */       
/*     */       public final boolean connectInput$1;
/*     */       
/*     */       public final int apply() {
/* 147 */         return this.$outer.run(this.log$1, this.connectInput$1).exitValue();
/*     */       }
/*     */       
/*     */       public int apply$mcI$sp() {
/* 147 */         return this.$outer.run(this.log$1, this.connectInput$1).exitValue();
/*     */       }
/*     */       
/*     */       public ProcessBuilderImpl$AbstractBuilder$$anonfun$runBuffered$1(ProcessBuilderImpl.AbstractBuilder $outer, ProcessLogger log$1, boolean connectInput$1) {}
/*     */     }
/*     */     
/*     */     public boolean canPipeTo() {
/* 149 */       return false;
/*     */     }
/*     */     
/*     */     public boolean hasExitValue() {
/* 150 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public class URLImpl implements ProcessBuilder.URLBuilder {
/*     */     private final URL url;
/*     */     
/*     */     public ProcessBuilder $hash$greater(File f) {
/* 153 */       return ProcessBuilder.Source$class.$hash$greater(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater$greater(File f) {
/* 153 */       return ProcessBuilder.Source$class.$hash$greater$greater(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater(Function0 out) {
/* 153 */       return ProcessBuilder.Source$class.$hash$greater(this, out);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater(ProcessBuilder b) {
/* 153 */       return ProcessBuilder.Source$class.$hash$greater(this, b);
/*     */     }
/*     */     
/*     */     public ProcessBuilder cat() {
/* 153 */       return ProcessBuilder.Source$class.cat(this);
/*     */     }
/*     */     
/*     */     public URLImpl(ProcessBuilder$ $outer, URL url) {
/* 153 */       ProcessBuilder.Source$class.$init$(this);
/*     */     }
/*     */     
/*     */     public ProcessBuilderImpl.URLInput toSource() {
/* 154 */       return new ProcessBuilderImpl.URLInput(scala$sys$process$ProcessBuilderImpl$URLImpl$$$outer(), this.url);
/*     */     }
/*     */   }
/*     */   
/*     */   public class FileImpl implements ProcessBuilder.FileBuilder {
/*     */     private final File base;
/*     */     
/*     */     public ProcessBuilder $hash$greater(File f) {
/* 156 */       return ProcessBuilder.Source$class.$hash$greater(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater$greater(File f) {
/* 156 */       return ProcessBuilder.Source$class.$hash$greater$greater(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater(Function0 out) {
/* 156 */       return ProcessBuilder.Source$class.$hash$greater(this, out);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$greater(ProcessBuilder b) {
/* 156 */       return ProcessBuilder.Source$class.$hash$greater(this, b);
/*     */     }
/*     */     
/*     */     public ProcessBuilder cat() {
/* 156 */       return ProcessBuilder.Source$class.cat(this);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less(File f) {
/* 156 */       return ProcessBuilder.Sink$class.$hash$less(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less(URL f) {
/* 156 */       return ProcessBuilder.Sink$class.$hash$less(this, f);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less(Function0 in) {
/* 156 */       return ProcessBuilder.Sink$class.$hash$less(this, in);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less(ProcessBuilder b) {
/* 156 */       return ProcessBuilder.Sink$class.$hash$less(this, b);
/*     */     }
/*     */     
/*     */     public FileImpl(ProcessBuilder$ $outer, File base) {
/* 156 */       ProcessBuilder.Sink$class.$init$(this);
/* 156 */       ProcessBuilder.Source$class.$init$(this);
/*     */     }
/*     */     
/*     */     public ProcessBuilderImpl.FileInput toSource() {
/* 157 */       return new ProcessBuilderImpl.FileInput(scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer(), this.base);
/*     */     }
/*     */     
/*     */     public ProcessBuilderImpl.FileOutput toSink() {
/* 158 */       return new ProcessBuilderImpl.FileOutput(scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer(), this.base, false);
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less$less(File f) {
/* 160 */       return $hash$less$less(new ProcessBuilderImpl.FileInput(scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer(), f));
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less$less(URL u) {
/* 161 */       return $hash$less$less(new ProcessBuilderImpl.URLInput(scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer(), u));
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less$less(Function0<InputStream> s) {
/* 162 */       return $hash$less$less(new ProcessBuilderImpl.IStreamBuilder(scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer(), s, "<input stream>"));
/*     */     }
/*     */     
/*     */     public ProcessBuilder $hash$less$less(ProcessBuilder b) {
/* 163 */       return new ProcessBuilderImpl.PipedBuilder(scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer(), b, new ProcessBuilderImpl.FileOutput(scala$sys$process$ProcessBuilderImpl$FileImpl$$$outer(), this.base, true), false);
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class BasicBuilder extends AbstractBuilder {
/*     */     public BasicBuilder(ProcessBuilder$ $outer) {
/* 166 */       super($outer);
/*     */     }
/*     */     
/*     */     public void checkNotThis(ProcessBuilder a) {
/*     */       // Byte code:
/*     */       //   0: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   3: aload_1
/*     */       //   4: dup
/*     */       //   5: ifnonnull -> 16
/*     */       //   8: pop
/*     */       //   9: aload_0
/*     */       //   10: ifnull -> 23
/*     */       //   13: goto -> 27
/*     */       //   16: aload_0
/*     */       //   17: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   20: ifeq -> 27
/*     */       //   23: iconst_0
/*     */       //   24: goto -> 28
/*     */       //   27: iconst_1
/*     */       //   28: istore_3
/*     */       //   29: astore_2
/*     */       //   30: iload_3
/*     */       //   31: ifeq -> 35
/*     */       //   34: return
/*     */       //   35: new java/lang/IllegalArgumentException
/*     */       //   38: dup
/*     */       //   39: new scala/collection/mutable/StringBuilder
/*     */       //   42: dup
/*     */       //   43: invokespecial <init> : ()V
/*     */       //   46: ldc 'requirement failed: '
/*     */       //   48: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   51: new scala/collection/mutable/StringBuilder
/*     */       //   54: dup
/*     */       //   55: invokespecial <init> : ()V
/*     */       //   58: ldc 'Compound process ''
/*     */       //   60: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   63: aload_1
/*     */       //   64: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   67: ldc '' cannot contain itself.'
/*     */       //   69: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   72: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   75: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */       //   78: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   81: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   84: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #167	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	85	0	this	Lscala/sys/process/ProcessBuilderImpl$BasicBuilder;
/*     */       //   0	85	1	a	Lscala/sys/process/ProcessBuilder;
/*     */     }
/*     */     
/*     */     public final Process run(ProcessIO io) {
/* 169 */       ProcessImpl.BasicProcess p = createProcess(io);
/* 170 */       p.start();
/* 171 */       return p;
/*     */     }
/*     */     
/*     */     public abstract ProcessImpl.BasicProcess createProcess(ProcessIO param1ProcessIO);
/*     */   }
/*     */   
/*     */   public abstract class SequentialBuilder extends BasicBuilder {
/*     */     private final ProcessBuilder a;
/*     */     
/*     */     private final ProcessBuilder b;
/*     */     
/*     */     private final String operatorString;
/*     */     
/*     */     public SequentialBuilder(ProcessBuilder$ $outer, ProcessBuilder a, ProcessBuilder b, String operatorString) {
/* 176 */       super($outer);
/* 182 */       checkNotThis(a);
/* 183 */       checkNotThis(b);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 184 */       return (new StringBuilder()).append(" ( ").append(this.a).append(" ").append(this.operatorString).append(" ").append(this.b).append(" ) ").toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public class PipedBuilder extends SequentialBuilder {
/*     */     private final ProcessBuilder first;
/*     */     
/*     */     private final ProcessBuilder second;
/*     */     
/*     */     private final boolean toError;
/*     */     
/*     */     public PipedBuilder(ProcessBuilder$ $outer, ProcessBuilder first, ProcessBuilder second, boolean toError) {
/* 187 */       super($outer, 
/*     */           
/* 191 */           first, second, toError ? "#|!" : "#|");
/*     */     }
/*     */     
/*     */     public ProcessImpl.PipedProcesses createProcess(ProcessIO io) {
/* 193 */       return new ProcessImpl.PipedProcesses(Process$.MODULE$, this.first, this.second, io, this.toError);
/*     */     }
/*     */   }
/*     */   
/*     */   public class AndBuilder extends SequentialBuilder {
/*     */     private final ProcessBuilder first;
/*     */     
/*     */     private final ProcessBuilder second;
/*     */     
/*     */     public AndBuilder(ProcessBuilder$ $outer, ProcessBuilder first, ProcessBuilder second) {
/* 196 */       super($outer, 
/*     */           
/* 199 */           first, second, "#&&");
/*     */     }
/*     */     
/*     */     public ProcessImpl.AndProcess createProcess(ProcessIO io) {
/* 200 */       return new ProcessImpl.AndProcess(Process$.MODULE$, this.first, this.second, io);
/*     */     }
/*     */   }
/*     */   
/*     */   public class OrBuilder extends SequentialBuilder {
/*     */     private final ProcessBuilder first;
/*     */     
/*     */     private final ProcessBuilder second;
/*     */     
/*     */     public OrBuilder(ProcessBuilder$ $outer, ProcessBuilder first, ProcessBuilder second) {
/* 203 */       super($outer, 
/*     */           
/* 206 */           first, second, "#||");
/*     */     }
/*     */     
/*     */     public ProcessImpl.OrProcess createProcess(ProcessIO io) {
/* 207 */       return new ProcessImpl.OrProcess(Process$.MODULE$, this.first, this.second, io);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SequenceBuilder extends SequentialBuilder {
/*     */     private final ProcessBuilder first;
/*     */     
/*     */     private final ProcessBuilder second;
/*     */     
/*     */     public SequenceBuilder(ProcessBuilder$ $outer, ProcessBuilder first, ProcessBuilder second) {
/* 210 */       super($outer, 
/*     */           
/* 213 */           first, second, "###");
/*     */     }
/*     */     
/*     */     public ProcessImpl.ProcessSequence createProcess(ProcessIO io) {
/* 214 */       return new ProcessImpl.ProcessSequence(Process$.MODULE$, this.first, this.second, io);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\ProcessBuilderImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */