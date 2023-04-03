/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Creator;
/*     */ import akka.util.Reflect$;
/*     */ import akka.util.Timeout;
/*     */ import scala.Function0;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Seq$;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rUw!B\001\003\021\0039\021A\003+za\026$\007K]8qg*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021!\002V=qK\022\004&o\0349t'\rIAB\005\t\003\033Ai\021A\004\006\002\037\005)1oY1mC&\021\021C\004\002\007\003:L(+\0324\021\0055\031\022B\001\013\017\0051\031VM]5bY&T\030M\0317f\021\0251\022\002\"\001\030\003\031a\024N\\5u}Q\tq\001C\004\032\023\t\007I\021\001\016\002'\021,g-Y;mi\022K7\017]1uG\",'/\0233\026\003m\001\"\001H\020\017\0055i\022B\001\020\017\003\031\001&/\0323fM&\021\001%\t\002\007'R\024\030N\\4\013\005yq\001BB\022\nA\003%1$\001\013eK\032\fW\017\034;ESN\004\030\r^2iKJLE\r\t\005\bK%\021\r\021\"\001'\0039!WMZ1vYR$\026.\\3pkR,\022a\n\t\004\033!R\023BA\025\017\005\031y\005\017^5p]B\0211FL\007\002Y)\021Q\006B\001\005kRLG.\003\0020Y\t9A+[7f_V$\bBB\031\nA\003%q%A\beK\032\fW\017\034;US6,w.\036;!\021\035\031\024B1A\005\002Q\nQ\002Z3gCVdG\017T8bI\026\024X#A\033\021\0075Ac\007\005\0028y5\t\001H\003\002:u\005!A.\0318h\025\005Y\024\001\0026bm\006L!!\020\035\003\027\rc\027m]:M_\006$WM\035\005\007%\001\013\021B\033\002\035\021,g-Y;mi2{\027\rZ3sA!)\021)\003C\001\005\006\tR\r\037;sC\016$\030J\034;fe\032\f7-Z:\025\005\rS\006c\001#J\0276\tQI\003\002G\017\006I\021.\\7vi\006\024G.\032\006\003\021:\t!bY8mY\026\034G/[8o\023\tQUIA\002TKF\004$\001T)\021\007qiu*\003\002OC\t)1\t\\1tgB\021\001+\025\007\001\t%\021\006)!A\001\002\013\0051K\001\003`IE\002\024C\001+X!\tiQ+\003\002W\035\t9aj\034;iS:<\007CA\007Y\023\tIfBA\002B]fDQa\027!A\002q\013Qa\0317buj\004$!X0\021\007qie\f\005\002Q?\022I\001MWA\001\002\003\025\ta\025\002\004?\022J\004\"\0022\n\t\003\031\027!B1qa2LXc\0013\003DR\031QM!2\021\t!1'\021\031\004\005\025\t\001u-F\002i\003\017\031BA\032\007j%A\021QB[\005\003W:\021q\001\025:pIV\034G\017\003\005nM\nU\r\021\"\001o\003)Ig\016^3sM\006\034Wm]\013\002_B\031A)\02391\005E\034\bc\001\017NeB\021\001k\035\003\niV\f\t\021!A\003\002M\023Aa\030\0232i!AaO\032B\tB\003%q/A\006j]R,'OZ1dKN\004\003c\001#JqB\022\021p\037\t\00495S\bC\001)|\t%!X/!A\001\002\013\0051\013\003\005~M\nU\r\021\"\001\003\035\031'/Z1u_J,\022a \t\006\033\005\005\021QA\005\004\003\007q!!\003$v]\016$\030n\03481!\r\001\026q\001\003\b\003\0231'\031AA\006\005\005!\026C\001+\r\021%\tyA\032B\tB\003%q0\001\005de\026\fGo\034:!\021%\t\031B\032BK\002\023\005!$\001\006eSN\004\030\r^2iKJD\021\"a\006g\005#\005\013\021B\016\002\027\021L7\017]1uG\",'\017\t\005\013\00371'Q3A\005\002\005u\021A\0023fa2|\0270\006\002\002 A\031\001\"!\t\n\007\005\r\"A\001\004EKBdw.\037\005\013\003O1'\021#Q\001\n\005}\021a\0023fa2|\027\020\t\005\n\003W1'Q3A\005\002\031\nq\001^5nK>,H\017C\005\0020\031\024\t\022)A\005O\005AA/[7f_V$\b\005C\005\0024\031\024)\032!C\001i\0051An\\1eKJD\021\"a\016g\005#\005\013\021B\033\002\0171|\027\rZ3sA!9aC\032C\tM\006mBCDA\037\003\tY%!\024\002P\005E\0231\013\t\005\021\031\f)\001C\004n\003s\001\r!!\021\021\t\021K\0251\t\031\005\003\013\nI\005\005\003\035\033\006\035\003c\001)\002J\021QA/a\020\002\002\003\005)\021A*\t\ru\fI\0041\001\000\021%\t\031\"!\017\021\002\003\0071\004\003\006\002\034\005e\002\023!a\001\003?A\021\"a\013\002:A\005\t\031A\024\t\023\005M\022\021\bI\001\002\004)\004B\002\fg\t\003\t9\006\006\003\002>\005e\003\002CA.\003+\002\r!!\030\002\035%l\007\017\\3nK:$\030\r^5p]B!A$TA\003\021\0311b\r\"\001\002bQ1\021QHA2\003gB\001\"!\032\002`\001\007\021qM\001\nS:$XM\0354bG\026\004D!!\033\002nA!A$TA6!\r\001\026Q\016\003\r\003_\n\031'!A\001\002\013\005\021\021\017\002\005?\022\nT'E\002\002\006]C\001\"a\027\002`\001\007\021Q\017\t\007\003o\ni(!\002\016\005\005e$bAA>\t\005!!.\0319j\023\021\ty(!\037\003\017\r\023X-\031;pe\"1aC\032C\001\003\007#b!!\020\002\006\006E\005\002CA3\003\003\003\r!a\"1\t\005%\025Q\022\t\00595\013Y\tE\002Q\003\033#A\"a$\002\006\006\005\t\021!B\001\003c\022Aa\030\0232m!A\0211LAA\001\004\ti\006C\004\002\026\032$\t!a&\002\035]LG\017\033#jgB\fGo\0315feR!\021QHAM\021\035\tY*a%A\002m\t\021\001\032\005\b\003?3G\021AAQ\003)9\030\016\0365EKBdw.\037\013\005\003{\t\031\013\003\005\002\034\006u\005\031AA\020\021\035\t9K\032C\001\003S\013!b^5uQ2{\027\rZ3s)\021\ti$a+\t\017\005M\022Q\025a\001m!9\021q\0254\005\002\005=F\003BA\037\003cCq!a\r\002.\002\007Q\007C\004\0026\032$\t!a.\002\027]LG\017\033+j[\026|W\017\036\013\005\003{\tI\fC\004\002,\005M\006\031\001\026\t\017\005Uf\r\"\001\002>R!\021QHA`\021\035\tY#a/A\002\035Bq!a1g\t\003\t)-A\007xSRD\027J\034;fe\032\f7-\032\013\005\003{\t9\r\003\005\002f\005\005\007\031AAea\021\tY-a4\021\tqi\025Q\032\t\004!\006=G\001DAi\003\017\f\t\021!A\003\002\005E$\001B0%c]Bq!!6g\t\003\t9.\001\txSRDw.\036;J]R,'OZ1dKR!\021QHAm\021!\t)'a5A\002\005m\007\007BAo\003C\004B\001H'\002`B\031\001+!9\005\031\005\r\030\021\\A\001\002\003\025\t!!\035\003\t}#\023\007\017\005\b\003O4G\021AAu\003)\t7\r^8s!J|\007o\035\013\003\003W\0042\001CAw\023\r\tyO\001\002\006!J|\007o\035\005\n\003g4\027\021!C\001\003k\fAaY8qsV!\021q_A)9\tI0a@\003\002\t\025!q\001B\005\005\027\001B\001\0034\002|B\031\001+!@\005\021\005%\021\021\037b\001\003\027A\021\"\\Ay!\003\005\r!!\021\t\023u\f\t\020%AA\002\t\r\001#B\007\002\002\005m\b\"CA\n\003c\004\n\0211\001\034\021)\tY\"!=\021\002\003\007\021q\004\005\n\003W\t\t\020%AA\002\035B\021\"a\r\002rB\005\t\031A\033\t\023\t=a-%A\005\002\tE\021AD2paf$C-\0324bk2$H%M\013\005\005'\021I#\006\002\003\026)\032qNa\006,\005\te\001\003\002B\016\005Ki!A!\b\013\t\t}!\021E\001\nk:\034\007.Z2lK\022T1Aa\t\017\003)\tgN\\8uCRLwN\\\005\005\005O\021iBA\tv]\016DWmY6fIZ\013'/[1oG\026$\001\"!\003\003\016\t\007\0211\002\005\n\005[1\027\023!C\001\005_\tabY8qs\022\"WMZ1vYR$#'\006\003\0032\tURC\001B\032U\ry(q\003\003\t\003\023\021YC1\001\002\f!I!\021\b4\022\002\023\005!1H\001\017G>\004\030\020\n3fM\006,H\016\036\0234+\021\021iD!\021\026\005\t}\"fA\016\003\030\021A\021\021\002B\034\005\004\tY\001C\005\003F\031\f\n\021\"\001\003H\005q1m\0349zI\021,g-Y;mi\022\"T\003\002B%\005\033*\"Aa\023+\t\005}!q\003\003\t\003\023\021\031E1\001\002\f!I!\021\0134\022\002\023\005!1K\001\017G>\004\030\020\n3fM\006,H\016\036\0236+\021\021)F!\027\026\005\t]#fA\024\003\030\021A\021\021\002B(\005\004\tY\001C\005\003^\031\f\n\021\"\001\003`\005q1m\0349zI\021,g-Y;mi\0222T\003\002B1\005K*\"Aa\031+\007U\0229\002\002\005\002\n\tm#\031AA\006\021%\021IGZA\001\n\003\022Y'A\007qe>$Wo\031;Qe\0264\027\016_\013\003\005[\0022a\016B8\023\t\001\003\bC\005\003t\031\f\t\021\"\001\003v\005a\001O]8ek\016$\030I]5usV\021!q\017\t\004\033\te\024b\001B>\035\t\031\021J\034;\t\023\t}d-!A\005\002\t\005\025A\0049s_\022,8\r^#mK6,g\016\036\013\004/\n\r\005B\003BC\005{\n\t\0211\001\003x\005\031\001\020J\031\t\023\t%e-!A\005B\t-\025a\0049s_\022,8\r^%uKJ\fGo\034:\026\005\t5\005#\002BH\005#;V\"A$\n\007\tMuI\001\005Ji\026\024\030\r^8s\021%\0219JZA\001\n\003\021I*\001\005dC:,\025/^1m)\021\021YJ!)\021\0075\021i*C\002\003 :\021qAQ8pY\026\fg\016C\005\003\006\nU\025\021!a\001/\"I!Q\0254\002\002\023\005#qU\001\tQ\006\034\bnQ8eKR\021!q\017\005\n\005W3\027\021!C!\005[\013\001\002^8TiJLgn\032\013\003\005[B\021B!-g\003\003%\tEa-\002\r\025\fX/\0317t)\021\021YJ!.\t\023\t\025%qVA\001\002\0049\006&\0024\003:\n}\006cA\007\003<&\031!Q\030\b\003!M+'/[1m-\026\0248/[8o+&#e$A\001\021\007A\023\031\rB\004\002\n\005\024\r!a\003\t\017\005m\023\r1\001\003HB!A$\024Ba\021\031\021\027\002\"\001\003LV!!Q\032Bj)\031\021yM!6\003dB!\001B\032Bi!\r\001&1\033\003\t\003\023\021IM1\001\002\f!A\021Q\rBe\001\004\0219\016\r\003\003Z\nu\007\003\002\017N\0057\0042\001\025Bo\t1\021yN!6\002\002\003\005)\021\001Bq\005\021yF%M\031\022\007\tEw\013\003\005\002\\\t%\007\031\001Bs!\021aRJ!5\t\r\tLA\021\001Bu+\021\021YO!=\025\r\t5(1_B\001!\021AaMa<\021\007A\023\t\020\002\005\002\n\t\035(\031AA\006\021!\t)Ga:A\002\tU\b\007\002B|\005w\004B\001H'\003zB\031\001Ka?\005\031\tu(1_A\001\002\003\025\tAa@\003\t}#\023GM\t\004\005_<\006\002C?\003h\022\005\raa\001\021\0135\031)Aa<\n\007\r\035aB\001\005=Eft\027-\\3?\021\031\021\027\002\"\001\004\fU!1QBB\013)\t\031y\001\006\003\004\022\r]\001\003\002\005g\007'\0012\001UB\013\t!\tIa!\003C\002\005-\001BCB\r\007\023\t\t\021q\001\004\034\005QQM^5eK:\034W\rJ\031\021\r\ru11EB\n\033\t\031yBC\002\004\"9\tqA]3gY\026\034G/\003\003\004&\r}!\001C\"mCN\034H+Y4\t\017\tLA\021\001\003\004*U!11FB\031)\031\031ica\r\004DA!\001BZB\030!\r\0016\021\007\003\t\003\023\0319C1\001\002\f!9Qna\nA\002\rU\002\003\002#J\007o\001Da!\017\004>A!A$TB\036!\r\0016Q\b\003\f\007\031\t%!A\001\002\013\0051K\001\003`IE\032\004bB7\004(\001\0071Q\007\005\t{\016\035B\0211\001\004FA)Qb!\002\0040!A!-CA\001\n\003\033I%\006\003\004L\rECCDB'\007'\032\tg!\032\004h\r%41\016\t\005\021\031\034y\005E\002Q\007#\"\001\"!\003\004H\t\007\0211\002\005\b[\016\035\003\031AB+!\021!\025ja\0261\t\re3Q\f\t\00595\033Y\006E\002Q\007;\"!\002^B0\003\003\005\tQ!\001T\021\035i7q\ta\001\007+Bq!`B$\001\004\031\031\007E\003\016\003\003\031y\005C\005\002\024\r\035\003\023!a\0017!Q\0211DB$!\003\005\r!a\b\t\023\005-2q\tI\001\002\0049\003\"CA\032\007\017\002\n\0211\0016\021%\031y'CA\001\n\003\033\t(A\004v]\006\004\b\017\\=\026\t\rM4\021\021\013\005\007k\032\031\t\005\003\016Q\r]\004cC\007\004z=\034ihGA\020OUJ1aa\037\017\005\031!V\017\0357fmA)Q\"!\001\004\000A\031\001k!!\005\021\005%1Q\016b\001\003\027A!b!\"\004n\005\005\t\031ABD\003\rAH\005\r\t\005\021\031\034y\bC\005\004\f&\t\n\021\"\001\004\016\006y\021\r\0359ms\022\"WMZ1vYR$3'\006\003\003>\r=E\001CA\005\007\023\023\r!a\003\t\023\rM\025\"%A\005\002\rU\025aD1qa2LH\005Z3gCVdG\017\n\033\026\t\t%3q\023\003\t\003\023\031\tJ1\001\002\f!I11T\005\022\002\023\0051QT\001\020CB\004H.\037\023eK\032\fW\017\034;%kU!!QKBP\t!\tIa!'C\002\005-\001\"CBR\023E\005I\021ABS\003=\t\007\017\0357zI\021,g-Y;mi\0222T\003\002B1\007O#\001\"!\003\004\"\n\007\0211\002\005\n\007WK\021\023!C\t\007[\0131\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\032T\003\002B\037\007_#\001\"!\003\004*\n\007\0211\002\005\n\007gK\021\023!C\t\007k\0131\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\"T\003\002B%\007o#\001\"!\003\0042\n\007\0211\002\005\n\007wK\021\023!C\t\007{\0131\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022*T\003\002B+\007#\001\"!\003\004:\n\007\0211\002\005\n\007\007L\021\023!C\t\007\013\f1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\0222T\003\002B1\007\017$\001\"!\003\004B\n\007\0211\002\005\n\007\027L\021\021!C\005\007\033\f1B]3bIJ+7o\0347wKR\0211q\032\t\004o\rE\027bABjq\t1qJ\0316fGR\004")
/*     */ public class TypedProps<T> implements Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Seq<Class<?>> interfaces;
/*     */   
/*     */   private final Function0<T> creator;
/*     */   
/*     */   private final String dispatcher;
/*     */   
/*     */   private final Deploy deploy;
/*     */   
/*     */   private final Option<Timeout> timeout;
/*     */   
/*     */   private final Option<ClassLoader> loader;
/*     */   
/*     */   public <T> TypedProps<T> copy(Seq<Class<?>> interfaces, Function0<T> creator, String dispatcher, Deploy deploy, Option<Timeout> timeout, Option<ClassLoader> loader) {
/* 525 */     return new TypedProps(
/* 526 */         interfaces, 
/* 527 */         creator, 
/* 528 */         dispatcher, 
/* 529 */         deploy, 
/* 530 */         timeout, 
/* 531 */         loader);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "TypedProps";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 6;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 5:
/*     */       
/*     */       case 4:
/*     */       
/*     */       case 3:
/*     */       
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return interfaces();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof TypedProps;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 240
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/TypedProps
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 244
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/TypedProps
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual interfaces : ()Lscala/collection/immutable/Seq;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual interfaces : ()Lscala/collection/immutable/Seq;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 236
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 236
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual creator : ()Lscala/Function0;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual creator : ()Lscala/Function0;
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 236
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 236
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   99: aload #4
/*     */     //   101: invokevirtual dispatcher : ()Ljava/lang/String;
/*     */     //   104: astore #7
/*     */     //   106: dup
/*     */     //   107: ifnonnull -> 119
/*     */     //   110: pop
/*     */     //   111: aload #7
/*     */     //   113: ifnull -> 127
/*     */     //   116: goto -> 236
/*     */     //   119: aload #7
/*     */     //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   124: ifeq -> 236
/*     */     //   127: aload_0
/*     */     //   128: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   131: aload #4
/*     */     //   133: invokevirtual deploy : ()Lakka/actor/Deploy;
/*     */     //   136: astore #8
/*     */     //   138: dup
/*     */     //   139: ifnonnull -> 151
/*     */     //   142: pop
/*     */     //   143: aload #8
/*     */     //   145: ifnull -> 159
/*     */     //   148: goto -> 236
/*     */     //   151: aload #8
/*     */     //   153: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   156: ifeq -> 236
/*     */     //   159: aload_0
/*     */     //   160: invokevirtual timeout : ()Lscala/Option;
/*     */     //   163: aload #4
/*     */     //   165: invokevirtual timeout : ()Lscala/Option;
/*     */     //   168: astore #9
/*     */     //   170: dup
/*     */     //   171: ifnonnull -> 183
/*     */     //   174: pop
/*     */     //   175: aload #9
/*     */     //   177: ifnull -> 191
/*     */     //   180: goto -> 236
/*     */     //   183: aload #9
/*     */     //   185: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   188: ifeq -> 236
/*     */     //   191: aload_0
/*     */     //   192: invokevirtual loader : ()Lscala/Option;
/*     */     //   195: aload #4
/*     */     //   197: invokevirtual loader : ()Lscala/Option;
/*     */     //   200: astore #10
/*     */     //   202: dup
/*     */     //   203: ifnonnull -> 215
/*     */     //   206: pop
/*     */     //   207: aload #10
/*     */     //   209: ifnull -> 223
/*     */     //   212: goto -> 236
/*     */     //   215: aload #10
/*     */     //   217: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   220: ifeq -> 236
/*     */     //   223: aload #4
/*     */     //   225: aload_0
/*     */     //   226: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   229: ifeq -> 236
/*     */     //   232: iconst_1
/*     */     //   233: goto -> 237
/*     */     //   236: iconst_0
/*     */     //   237: ifeq -> 244
/*     */     //   240: iconst_1
/*     */     //   241: goto -> 245
/*     */     //   244: iconst_0
/*     */     //   245: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #525	-> 0
/*     */     //   #63	-> 14
/*     */     //   #525	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	246	0	this	Lakka/actor/TypedProps;
/*     */     //   0	246	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Seq<Class<?>> interfaces() {
/*     */     return this.interfaces;
/*     */   }
/*     */   
/*     */   public <T> Seq<Class<?>> copy$default$1() {
/*     */     return interfaces();
/*     */   }
/*     */   
/*     */   public TypedProps(Seq<Class<?>> interfaces, Function0<T> creator, String dispatcher, Deploy deploy, Option<Timeout> timeout, Option<ClassLoader> loader) {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public Function0<T> creator() {
/*     */     return this.creator;
/*     */   }
/*     */   
/*     */   public <T> Function0<T> copy$default$2() {
/*     */     return creator();
/*     */   }
/*     */   
/*     */   public String dispatcher() {
/*     */     return this.dispatcher;
/*     */   }
/*     */   
/*     */   public <T> String copy$default$3() {
/*     */     return dispatcher();
/*     */   }
/*     */   
/*     */   public Deploy deploy() {
/*     */     return this.deploy;
/*     */   }
/*     */   
/*     */   public <T> Deploy copy$default$4() {
/*     */     return deploy();
/*     */   }
/*     */   
/*     */   public Option<Timeout> timeout() {
/*     */     return this.timeout;
/*     */   }
/*     */   
/*     */   public <T> Option<Timeout> copy$default$5() {
/*     */     return timeout();
/*     */   }
/*     */   
/*     */   public Option<ClassLoader> loader() {
/* 531 */     return this.loader;
/*     */   }
/*     */   
/*     */   public <T> Option<ClassLoader> copy$default$6() {
/* 531 */     return loader();
/*     */   }
/*     */   
/*     */   public TypedProps(Class<?> implementation) {
/* 540 */     this(TypedProps$.MODULE$.extractInterfaces(implementation), 
/* 541 */         Reflect$.MODULE$.instantiator(implementation), TypedProps$.MODULE$.$lessinit$greater$default$3(), TypedProps$.MODULE$.$lessinit$greater$default$4(), TypedProps$.MODULE$.$lessinit$greater$default$5(), TypedProps$.MODULE$.$lessinit$greater$default$6());
/*     */   }
/*     */   
/*     */   public TypedProps(Class<?> interface, Creator implementation) {
/* 550 */     this(TypedProps$.MODULE$.extractInterfaces(interface), 
/* 551 */         (Function0<T>)new TypedProps$$anonfun$$init$$1(implementation), TypedProps$.MODULE$.$lessinit$greater$default$3(), TypedProps$.MODULE$.$lessinit$greater$default$4(), TypedProps$.MODULE$.$lessinit$greater$default$5(), TypedProps$.MODULE$.$lessinit$greater$default$6());
/*     */   }
/*     */   
/*     */   public class TypedProps$$anonfun$$init$$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Creator implementation$1;
/*     */     
/*     */     public final T apply() {
/* 551 */       return (T)this.implementation$1.create();
/*     */     }
/*     */     
/*     */     public TypedProps$$anonfun$$init$$1(Creator implementation$1) {}
/*     */   }
/*     */   
/*     */   public TypedProps(Class<?> interface, Class implementation) {
/* 560 */     this(TypedProps$.MODULE$.extractInterfaces(interface), 
/* 561 */         Reflect$.MODULE$.instantiator(implementation), TypedProps$.MODULE$.$lessinit$greater$default$3(), TypedProps$.MODULE$.$lessinit$greater$default$4(), TypedProps$.MODULE$.$lessinit$greater$default$5(), TypedProps$.MODULE$.$lessinit$greater$default$6());
/*     */   }
/*     */   
/*     */   public TypedProps<T> withDispatcher(String d) {
/* 566 */     String x$6 = d;
/* 566 */     Seq<Class<?>> x$7 = copy$default$1();
/* 566 */     Function0<?> x$8 = copy$default$2();
/* 566 */     Deploy x$9 = copy$default$4();
/* 566 */     Option<Timeout> x$10 = copy$default$5();
/* 566 */     Option<ClassLoader> x$11 = copy$default$6();
/* 566 */     return copy(x$7, (Function0)x$8, x$6, x$9, x$10, x$11);
/*     */   }
/*     */   
/*     */   public TypedProps<T> withDeploy(Deploy d) {
/* 571 */     Deploy x$12 = d;
/* 571 */     Seq<Class<?>> x$13 = copy$default$1();
/* 571 */     Function0<?> x$14 = copy$default$2();
/* 571 */     String x$15 = copy$default$3();
/* 571 */     Option<Timeout> x$16 = copy$default$5();
/* 571 */     Option<ClassLoader> x$17 = copy$default$6();
/* 571 */     return copy(x$13, (Function0)x$14, x$15, x$12, x$16, x$17);
/*     */   }
/*     */   
/*     */   public TypedProps<T> withLoader(ClassLoader loader) {
/* 577 */     return withLoader(Option$.MODULE$.apply(loader));
/*     */   }
/*     */   
/*     */   public TypedProps<T> withLoader(Option<ClassLoader> loader) {
/* 585 */     Option<ClassLoader> x$18 = loader;
/* 585 */     Seq<Class<?>> x$19 = copy$default$1();
/* 585 */     Function0<?> x$20 = copy$default$2();
/* 585 */     String x$21 = copy$default$3();
/* 585 */     Deploy x$22 = copy$default$4();
/* 585 */     Option<Timeout> x$23 = copy$default$5();
/* 585 */     return copy(x$19, (Function0)x$20, x$21, x$22, x$23, x$18);
/*     */   }
/*     */   
/*     */   public TypedProps<T> withTimeout(Timeout timeout) {
/* 591 */     Option<Timeout> x$24 = Option$.MODULE$.apply(timeout);
/* 591 */     Seq<Class<?>> x$25 = copy$default$1();
/* 591 */     Function0<?> x$26 = copy$default$2();
/* 591 */     String x$27 = copy$default$3();
/* 591 */     Deploy x$28 = copy$default$4();
/* 591 */     Option<ClassLoader> x$29 = copy$default$6();
/* 591 */     return copy(x$25, (Function0)x$26, x$27, x$28, x$24, x$29);
/*     */   }
/*     */   
/*     */   public TypedProps<T> withTimeout(Option<Timeout> timeout) {
/* 599 */     Option<Timeout> x$30 = timeout;
/* 599 */     Seq<Class<?>> x$31 = copy$default$1();
/* 599 */     Function0<?> x$32 = copy$default$2();
/* 599 */     String x$33 = copy$default$3();
/* 599 */     Deploy x$34 = copy$default$4();
/* 599 */     Option<ClassLoader> x$35 = copy$default$6();
/* 599 */     return copy(x$31, (Function0)x$32, x$33, x$34, x$30, x$35);
/*     */   }
/*     */   
/*     */   public TypedProps<T> withInterface(Class<?> interface) {
/* 607 */     return copy((Seq<Class<?>>)interfaces().$plus$plus((GenTraversableOnce)TypedProps$.MODULE$.extractInterfaces(interface), Seq$.MODULE$.canBuildFrom()), copy$default$2(), copy$default$3(), copy$default$4(), copy$default$5(), copy$default$6());
/*     */   }
/*     */   
/*     */   public TypedProps<T> withoutInterface(Class<?> interface) {
/* 614 */     return copy((Seq<Class<?>>)interfaces().diff((GenSeq)TypedProps$.MODULE$.extractInterfaces(interface)), copy$default$2(), copy$default$3(), copy$default$4(), copy$default$5(), copy$default$6());
/*     */   }
/*     */   
/*     */   public Props actorProps() {
/* 620 */     String str = Props$.MODULE$.default().dispatcher();
/* 620 */     if (dispatcher() == null) {
/* 620 */       dispatcher();
/* 620 */       if (str != null);
/* 620 */     } else if (dispatcher().equals(str)) {
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <T> Option<ClassLoader> $lessinit$greater$default$6() {
/*     */     return TypedProps$.MODULE$.$lessinit$greater$default$6();
/*     */   }
/*     */   
/*     */   public static <T> Option<Timeout> $lessinit$greater$default$5() {
/*     */     return TypedProps$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static <T> Deploy $lessinit$greater$default$4() {
/*     */     return TypedProps$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static <T> String $lessinit$greater$default$3() {
/*     */     return TypedProps$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static <T> Option<ClassLoader> apply$default$6() {
/*     */     return TypedProps$.MODULE$.apply$default$6();
/*     */   }
/*     */   
/*     */   public static <T> Option<Timeout> apply$default$5() {
/*     */     return TypedProps$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static <T> Deploy apply$default$4() {
/*     */     return TypedProps$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static <T> String apply$default$3() {
/*     */     return TypedProps$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static <T> TypedProps<T> apply(ClassTag<T> paramClassTag) {
/*     */     return TypedProps$.MODULE$.apply(paramClassTag);
/*     */   }
/*     */   
/*     */   public static <T> TypedProps<T> apply(Class<? super T> paramClass, Function0<T> paramFunction0) {
/*     */     return TypedProps$.MODULE$.apply(paramClass, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <T> TypedProps<T> apply(Class<? super T> paramClass, Class<T> paramClass1) {
/*     */     return TypedProps$.MODULE$.apply(paramClass, paramClass1);
/*     */   }
/*     */   
/*     */   public static <T> TypedProps<T> apply(Class<T> paramClass) {
/*     */     return TypedProps$.MODULE$.apply(paramClass);
/*     */   }
/*     */   
/*     */   public static Seq<Class<?>> extractInterfaces(Class<?> paramClass) {
/*     */     return TypedProps$.MODULE$.extractInterfaces(paramClass);
/*     */   }
/*     */   
/*     */   public static Option<ClassLoader> defaultLoader() {
/*     */     return TypedProps$.MODULE$.defaultLoader();
/*     */   }
/*     */   
/*     */   public static Option<Timeout> defaultTimeout() {
/*     */     return TypedProps$.MODULE$.defaultTimeout();
/*     */   }
/*     */   
/*     */   public static String defaultDispatcherId() {
/*     */     return TypedProps$.MODULE$.defaultDispatcherId();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\TypedProps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */