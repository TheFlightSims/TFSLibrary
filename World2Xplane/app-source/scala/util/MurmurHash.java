/*     */ package scala.util;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tmc\001B\001\003\001\035\021!\"T;s[V\024\b*Y:i\025\t\031A!\001\003vi&d'\"A\003\002\013M\034\027\r\\1\004\001U\021\001BE\n\004\001%i\001C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB!!B\004\t9\023\tyAAA\005Gk:\034G/[8ocA\021\021C\005\007\001\t%\031\002\001)A\001\002\013\007ACA\001U#\t)\002\004\005\002\013-%\021q\003\002\002\b\035>$\b.\0338h!\tQ\021$\003\002\033\t\t\031\021I\\=)\rIar$\013\0304!\tQQ$\003\002\037\t\tY1\017]3dS\006d\027N_3ec\025\031\003%I\022#\035\tQ\021%\003\002#\t\005\031\021J\034;2\t\021\"\003&\002\b\003K!j\021A\n\006\003O\031\ta\001\020:p_Rt\024\"A\0032\013\rR3&\f\027\017\005)Y\023B\001\027\005\003\021auN\\42\t\021\"\003&B\031\006G=\002$'\r\b\003\025AJ!!\r\003\002\013\031cw.\031;2\t\021\"\003&B\031\006GQ*tG\016\b\003\025UJ!A\016\003\002\r\021{WO\0317fc\021!C\005K\003\021\005)I\024B\001\036\005\005\021)f.\033;\t\021q\002!\021!Q\001\nu\nAa]3fIB\021!BP\005\003\021\0211!\0238u\021\025\t\005\001\"\001C\003\031a\024N\\5u}Q\0211)\022\t\004\t\002\001R\"\001\002\t\013q\002\005\031A\037\t\017\035\003\001\031!C\005\021\006\t\001.F\001>\021\035Q\005\0011A\005\n-\013Q\001[0%KF$\"\001\017'\t\0175K\025\021!a\001{\005\031\001\020J\031\t\r=\003\001\025)\003>\003\tA\007\005C\004R\001\001\007I\021\002%\002\003\rDqa\025\001A\002\023%A+A\003d?\022*\027\017\006\0029+\"9QJUA\001\002\004i\004BB,\001A\003&Q(\001\002dA!9\021\f\001a\001\n\023A\025!A6\t\017m\003\001\031!C\0059\006)1n\030\023fcR\021\001(\030\005\b\033j\013\t\0211\001>\021\031y\006\001)Q\005{\005\0211\016\t\005\bC\002\001\r\021\"\003c\003\031A\027m\0355fIV\t1\r\005\002\013I&\021Q\r\002\002\b\005>|G.Z1o\021\0359\007\0011A\005\n!\f!\002[1tQ\026$w\fJ3r)\tA\024\016C\004NM\006\005\t\031A2\t\r-\004\001\025)\003d\003\035A\027m\0355fI\002Bq!\034\001A\002\023%\001*A\005iCNDg/\0317vK\"9q\016\001a\001\n\023\001\030!\0045bg\"4\030\r\\;f?\022*\027\017\006\0029c\"9QJ\\A\001\002\004i\004BB:\001A\003&Q(\001\006iCNDg/\0317vK\002BQ!\036\001\005\002Y\fQA]3tKR$\022\001\017\005\006q\002!\t!_\001\006CB\004H.\037\013\003qiDQa_<A\002A\t\021\001\036\005\006{\002!\tA`\001\007CB\004XM\0343\025\005az\bBBA\001y\002\007Q(A\001j\021\031\t)\001\001C\001\021\006!\001.Y:i\021\035\tI\001\001C!\003\027\t\001\002[1tQ\016{G-\032\013\002{!:\001!a\004\002\026\005e\001c\001\006\002\022%\031\0211\003\003\003\025\021,\007O]3dCR,G-\t\002\002\030\005\031Sk]3!i\",\007e\0342kK\016$\b%T;s[V\024\b*Y:ig\001Jgn\035;fC\022t\023EAA\016\003\031\021d&\r\031/a\0359\021q\004\002\t\002\005\005\022AC've6,(\017S1tQB\031A)a\t\007\r\005\021\001\022AA\023'\r\t\031#\003\005\b\003\006\rB\021AA\025)\t\t\t\003\003\006\002.\005\r\"\031!C\007\003_\tAB^5tS\ndW-T1hS\016,\"!!\r\020\005\005MR\004BL\037'mD\021\"a\016\002$\001\006i!!\r\002\033YL7/\0332mK6\013w-[2!\021)\tY$a\tC\002\0235\021QH\001\rQ&$G-\0328NC\036L7-Q\013\003\003y!!!\021\036\tU%vg\"\005\n\003\013\n\031\003)A\007\003\tQ\002[5eI\026tW*Y4jG\006\003\003BCA%\003G\021\r\021\"\004\002L\005a\001.\0333eK:l\025mZ5d\005V\021\021QJ\b\003\003\037jBAKllL!I\0211KA\022A\0035\021QJ\001\016Q&$G-\0328NC\036L7M\021\021\t\025\005]\0231\005b\001\n\033\tI&\001\007wSNL'\r\\3NSb,'/\006\002\002\\=\021\021QL\017\005%r?\037\006C\005\002b\005\r\002\025!\004\002\\\005ia/[:jE2,W*\033=fe\002B!\"!\032\002$\t\007IQBA4\0031A\027\016\0323f]6K\0070\032:B+\t\tIg\004\002\002lu!10`\013\035\022%\ty'a\t!\002\033\tI'A\007iS\022$WM\\'jq\026\024\030\t\t\005\013\003g\n\031C1A\005\016\005U\024\001\0045jI\022,g.T5yKJ\024UCAA<\037\t\tI(\b\003l\035\0204\n\"CA?\003G\001\013QBA<\0035A\027\016\0323f]6K\0070\032:CA!Q\021\021QA\022\005\004%i!a!\002\027\031Lg.\0317NSb,'/M\013\003\003\013{!!a\"\036\t\025]/z\033\005\n\003\027\013\031\003)A\007\003\013\013ABZ5oC2l\025\016_3sc\001B!\"a$\002$\t\007IQBAI\003-1\027N\\1m\033&DXM\035\032\026\005\005MuBAAK;\021\021-Wl\033\t\023\005e\0251\005Q\001\016\005M\025\001\0044j]\006dW*\033=feJ\002\003BCAO\003G\021\r\021\"\004\002 \006Q1/Z3e'R\024\030N\\4\026\005\005\005vBAAR;\02190z j\t\023\005\035\0261\005Q\001\016\005\005\026aC:fK\022\034FO]5oO\002B!\"a+\002$\t\007IQBAW\003%\031X-\0323BeJ\f\0270\006\002\0020>\021\021\021W\017\005y\035Q\025\rC\005\0026\006\r\002\025!\004\0020\006Q1/Z3e\003J\024\030-\037\021\t\025\005e\0261\005b\001\n\003\tY,\001\007ti>\024X\rZ'bO&\034\027)\006\002\002>B!!\"a0>\023\r\t\t\r\002\002\006\003J\024\030-\037\005\n\003\013\f\031\003)A\005\003{\013Qb\035;pe\026$W*Y4jG\006\003\003BCAe\003G\021\r\021\"\001\002<\006a1\017^8sK\022l\025mZ5d\005\"I\021QZA\022A\003%\021QX\001\016gR|'/\0323NC\036L7M\021\021\t\021\005E\0271\005C\001\003'\f\021b\035;beRD\025m\0355\025\007u\n)\016\003\004=\003\037\004\r!\020\005\b\0033\f\031\003\"\001I\003-\031H/\031:u\033\006<\027nY!\t\017\005u\0271\005C\001\021\006Y1\017^1si6\013w-[2C\021!\t\t/a\t\005\002\005\r\030AC3yi\026tG\rS1tQRIQ(!:\002h\006-\030q\036\005\b\003\013\ty\0161\001>\021\035\tI/a8A\002u\nQA^1mk\026Dq!!<\002`\002\007Q(\001\004nC\036L7-\021\005\b\003c\fy\0161\001>\003\031i\027mZ5d\005\"A\021Q_A\022\t\003\t90\001\006oKb$X*Y4jG\006#2!PA}\021\035\ti/a=A\002uB\001\"!@\002$\021\005\021q`\001\013]\026DH/T1hS\016\024EcA\037\003\002!9\021\021_A~\001\004i\004\002\003B\003\003G!\tAa\002\002\031\031Lg.\0317ju\026D\025m\0355\025\007u\022I\001C\004\002\006\t\r\001\031A\037\t\021\t5\0211\005C\001\005\037\t\021\"\031:sCfD\025m\0355\026\t\tE!1\004\013\004{\tM\001\002\003B\013\005\027\001\rAa\006\002\003\005\004RACA`\0053\0012!\005B\016\t)\031\"1\002Q\001\002\003\025\r\001\006\025\004\0057a\002\002\003B\021\003G!\tAa\t\002\025M$(/\0338h\021\006\034\b\016F\002>\005KA\001Ba\n\003 \001\007!\021F\001\002gB!!1\006B\031\035\rQ!QF\005\004\005_!\021A\002)sK\022,g-\003\003\0034\tU\"AB*ue&twMC\002\0030\021A\001B!\017\002$\021\005!1H\001\016gflW.\032;sS\016D\025m\0355\026\t\tu\"\021\013\013\006{\t}\"1\013\005\t\005\003\0229\0041\001\003D\005\021\001p\035\t\007\005\013\022YEa\024\016\005\t\035#b\001B%\t\005Q1m\0347mK\016$\030n\0348\n\t\t5#q\t\002\020)J\fg/\032:tC\ndWm\0248dKB\031\021C!\025\005\rM\0219D1\001\025\021\031a$q\007a\001{!B\0211EA\b\003+\tI\002\013\005\002$\005=\021QCA\rQ!\ti\"a\004\002\026\005e\001")
/*     */ public class MurmurHash<T> implements Function1<T, BoxedUnit> {
/*     */   private final int seed;
/*     */   
/*     */   private int scala$util$MurmurHash$$h;
/*     */   
/*     */   private int scala$util$MurmurHash$$c;
/*     */   
/*     */   private int scala$util$MurmurHash$$k;
/*     */   
/*     */   private boolean scala$util$MurmurHash$$hashed;
/*     */   
/*     */   private int hashvalue;
/*     */   
/*     */   public boolean apply$mcZD$sp(double v1) {
/*  31 */     return Function1.class.apply$mcZD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDD$sp(double v1) {
/*  31 */     return Function1.class.apply$mcDD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFD$sp(double v1) {
/*  31 */     return Function1.class.apply$mcFD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcID$sp(double v1) {
/*  31 */     return Function1.class.apply$mcID$sp(this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJD$sp(double v1) {
/*  31 */     return Function1.class.apply$mcJD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVD$sp(double v1) {
/*  31 */     Function1.class.apply$mcVD$sp(this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZF$sp(float v1) {
/*  31 */     return Function1.class.apply$mcZF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDF$sp(float v1) {
/*  31 */     return Function1.class.apply$mcDF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFF$sp(float v1) {
/*  31 */     return Function1.class.apply$mcFF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIF$sp(float v1) {
/*  31 */     return Function1.class.apply$mcIF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJF$sp(float v1) {
/*  31 */     return Function1.class.apply$mcJF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVF$sp(float v1) {
/*  31 */     Function1.class.apply$mcVF$sp(this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZI$sp(int v1) {
/*  31 */     return Function1.class.apply$mcZI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDI$sp(int v1) {
/*  31 */     return Function1.class.apply$mcDI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFI$sp(int v1) {
/*  31 */     return Function1.class.apply$mcFI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcII$sp(int v1) {
/*  31 */     return Function1.class.apply$mcII$sp(this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJI$sp(int v1) {
/*  31 */     return Function1.class.apply$mcJI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVI$sp(int v1) {
/*  31 */     Function1.class.apply$mcVI$sp(this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZJ$sp(long v1) {
/*  31 */     return Function1.class.apply$mcZJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDJ$sp(long v1) {
/*  31 */     return Function1.class.apply$mcDJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFJ$sp(long v1) {
/*  31 */     return Function1.class.apply$mcFJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIJ$sp(long v1) {
/*  31 */     return Function1.class.apply$mcIJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJJ$sp(long v1) {
/*  31 */     return Function1.class.apply$mcJJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVJ$sp(long v1) {
/*  31 */     Function1.class.apply$mcVJ$sp(this, v1);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose(Function1 g) {
/*  31 */     return Function1.class.compose(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcZD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcDD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcFD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcID$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcJD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcVD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcZF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcDF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcFF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcIF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcJF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcVF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcZI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcDI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcFI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcII$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcJI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcVI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcZJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcDJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcFJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcIJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcJJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  31 */     return Function1.class.compose$mcVJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<T, A> andThen(Function1 g) {
/*  31 */     return Function1.class.andThen(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcZD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcDD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcFD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcID$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcJD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcVD$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcZF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcDF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcFF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcIF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcJF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcVF$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcZI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcDI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcFI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcII$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcJI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcVI$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcZJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcDJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcFJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcIJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcJJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  31 */     return Function1.class.andThen$mcVJ$sp(this, g);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  31 */     return Function1.class.toString(this);
/*     */   }
/*     */   
/*     */   public MurmurHash(int seed) {
/*  31 */     Function1.class.$init$(this);
/*  34 */     this.scala$util$MurmurHash$$h = MurmurHash$.MODULE$.startHash(seed);
/*  35 */     this.scala$util$MurmurHash$$c = -1789642873;
/*  36 */     this.scala$util$MurmurHash$$k = 718793509;
/*  37 */     this.scala$util$MurmurHash$$hashed = false;
/*  38 */     this.hashvalue = scala$util$MurmurHash$$h();
/*     */   }
/*     */   
/*     */   public int scala$util$MurmurHash$$h() {
/*     */     return this.scala$util$MurmurHash$$h;
/*     */   }
/*     */   
/*     */   public void scala$util$MurmurHash$$h_$eq(int x$1) {
/*     */     this.scala$util$MurmurHash$$h = x$1;
/*     */   }
/*     */   
/*     */   public int scala$util$MurmurHash$$c() {
/*     */     return this.scala$util$MurmurHash$$c;
/*     */   }
/*     */   
/*     */   public void scala$util$MurmurHash$$c_$eq(int x$1) {
/*     */     this.scala$util$MurmurHash$$c = x$1;
/*     */   }
/*     */   
/*     */   public int scala$util$MurmurHash$$k() {
/*     */     return this.scala$util$MurmurHash$$k;
/*     */   }
/*     */   
/*     */   public void scala$util$MurmurHash$$k_$eq(int x$1) {
/*     */     this.scala$util$MurmurHash$$k = x$1;
/*     */   }
/*     */   
/*     */   private boolean scala$util$MurmurHash$$hashed() {
/*     */     return this.scala$util$MurmurHash$$hashed;
/*     */   }
/*     */   
/*     */   public void scala$util$MurmurHash$$hashed_$eq(boolean x$1) {
/*     */     this.scala$util$MurmurHash$$hashed = x$1;
/*     */   }
/*     */   
/*     */   private int hashvalue() {
/*  38 */     return this.hashvalue;
/*     */   }
/*     */   
/*     */   private void hashvalue_$eq(int x$1) {
/*  38 */     this.hashvalue = x$1;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  42 */     scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.startHash(this.seed));
/*  43 */     scala$util$MurmurHash$$c_$eq(-1789642873);
/*  44 */     scala$util$MurmurHash$$k_$eq(718793509);
/*  45 */     scala$util$MurmurHash$$hashed_$eq(false);
/*     */   }
/*     */   
/*     */   public void apply$mcD$sp(double t) {
/*  49 */     apply((T)BoxesRunTime.boxToDouble(t));
/*     */   }
/*     */   
/*     */   public void apply$mcF$sp(float t) {
/*  49 */     apply((T)BoxesRunTime.boxToFloat(t));
/*     */   }
/*     */   
/*     */   public void apply$mcI$sp(int t) {
/*  49 */     apply((T)BoxesRunTime.boxToInteger(t));
/*     */   }
/*     */   
/*     */   public void apply$mcJ$sp(long t) {
/*  49 */     apply((T)BoxesRunTime.boxToLong(t));
/*     */   }
/*     */   
/*     */   public void apply(Object t) {
/*  50 */     scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.extendHash(scala$util$MurmurHash$$h(), ScalaRunTime$.MODULE$.hash(t), scala$util$MurmurHash$$c(), scala$util$MurmurHash$$k()));
/*  51 */     scala$util$MurmurHash$$c_$eq(MurmurHash$.MODULE$.nextMagicA(scala$util$MurmurHash$$c()));
/*  52 */     scala$util$MurmurHash$$k_$eq(MurmurHash$.MODULE$.nextMagicB(scala$util$MurmurHash$$k()));
/*  53 */     scala$util$MurmurHash$$hashed_$eq(false);
/*     */   }
/*     */   
/*     */   public void append(int i) {
/*  58 */     scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.extendHash(scala$util$MurmurHash$$h(), i, scala$util$MurmurHash$$c(), scala$util$MurmurHash$$k()));
/*  59 */     scala$util$MurmurHash$$c_$eq(MurmurHash$.MODULE$.nextMagicA(scala$util$MurmurHash$$c()));
/*  60 */     scala$util$MurmurHash$$k_$eq(MurmurHash$.MODULE$.nextMagicB(scala$util$MurmurHash$$k()));
/*  61 */     scala$util$MurmurHash$$hashed_$eq(false);
/*     */   }
/*     */   
/*     */   public int hash() {
/*  66 */     if (!scala$util$MurmurHash$$hashed()) {
/*  67 */       hashvalue_$eq(MurmurHash$.MODULE$.finalizeHash(scala$util$MurmurHash$$h()));
/*  68 */       scala$util$MurmurHash$$hashed_$eq(true);
/*     */     } 
/*  70 */     return hashvalue();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  72 */     return hash();
/*     */   }
/*     */   
/*     */   public static <T> int symmetricHash(TraversableOnce<T> paramTraversableOnce, int paramInt) {
/*     */     return MurmurHash$.MODULE$.symmetricHash(paramTraversableOnce, paramInt);
/*     */   }
/*     */   
/*     */   public static int stringHash(String paramString) {
/*     */     return MurmurHash$.MODULE$.stringHash(paramString);
/*     */   }
/*     */   
/*     */   public static <T> int arrayHash(Object paramObject) {
/*     */     return MurmurHash$.MODULE$.arrayHash(paramObject);
/*     */   }
/*     */   
/*     */   public static int finalizeHash(int paramInt) {
/*     */     return MurmurHash$.MODULE$.finalizeHash(paramInt);
/*     */   }
/*     */   
/*     */   public static int nextMagicB(int paramInt) {
/*     */     return MurmurHash$.MODULE$.nextMagicB(paramInt);
/*     */   }
/*     */   
/*     */   public static int nextMagicA(int paramInt) {
/*     */     return MurmurHash$.MODULE$.nextMagicA(paramInt);
/*     */   }
/*     */   
/*     */   public static int extendHash(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*     */     return MurmurHash$.MODULE$.extendHash(paramInt1, paramInt2, paramInt3, paramInt4);
/*     */   }
/*     */   
/*     */   public static int startMagicB() {
/*     */     return MurmurHash$.MODULE$.startMagicB();
/*     */   }
/*     */   
/*     */   public static int startMagicA() {
/*     */     return MurmurHash$.MODULE$.startMagicA();
/*     */   }
/*     */   
/*     */   public static int startHash(int paramInt) {
/*     */     return MurmurHash$.MODULE$.startHash(paramInt);
/*     */   }
/*     */   
/*     */   public static int[] storedMagicB() {
/*     */     return MurmurHash$.MODULE$.storedMagicB();
/*     */   }
/*     */   
/*     */   public static int[] storedMagicA() {
/*     */     return MurmurHash$.MODULE$.storedMagicA();
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int magicA) {
/* 102 */       return MurmurHash$.MODULE$.nextMagicA(magicA);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int magicA) {
/* 102 */       return MurmurHash$.MODULE$.nextMagicA(magicA);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$2 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int magicB) {
/* 106 */       return MurmurHash$.MODULE$.nextMagicB(magicB);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int magicB) {
/* 106 */       return MurmurHash$.MODULE$.nextMagicB(magicB);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MurmurHash$$anonfun$symmetricHash$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef a$1;
/*     */     
/*     */     private final IntRef b$1;
/*     */     
/*     */     private final IntRef n$1;
/*     */     
/*     */     private final IntRef c$1;
/*     */     
/*     */     public MurmurHash$$anonfun$symmetricHash$1(IntRef a$1, IntRef b$1, IntRef n$1, IntRef c$1) {}
/*     */     
/*     */     public final void apply(Object i) {
/* 185 */       int h = ScalaRunTime$.MODULE$.hash(i);
/* 186 */       this.a$1.elem += h;
/* 187 */       this.b$1.elem ^= h;
/* 188 */       if (h != 0)
/* 188 */         this.c$1.elem *= h; 
/* 189 */       this.n$1.elem++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\MurmurHash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */