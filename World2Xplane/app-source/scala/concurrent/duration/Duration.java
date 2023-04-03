/*     */ package scala.concurrent.duration;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Ordered;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.PartialOrdering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichDouble;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\r\035q!B\001\003\021\003I\021\001\003#ve\006$\030n\0348\013\005\r!\021\001\0033ve\006$\030n\0348\013\005\0251\021AC2p]\016,(O]3oi*\tq!A\003tG\006d\027m\001\001\021\005)YQ\"\001\002\007\0131\021\001\022A\007\003\021\021+(/\031;j_:\0342a\003\b\023!\ty\001#D\001\007\023\t\tbA\001\004B]f\024VM\032\t\003\037MI!\001\006\004\003\031M+'/[1mSj\f'\r\\3\t\013YYA\021A\f\002\rqJg.\033;?)\005I\001\"B\r\f\t\003Q\022!B1qa2LH#B\016\002&\006\035\006C\001\006\035\r\025a!!!\t\036'\021abB\005\020\021\007}93D\004\002!K9\021\021\005J\007\002E)\0211\005C\001\007yI|w\016\036 \n\003\035I!A\n\004\002\017A\f7m[1hK&\021\001&\013\002\b\037J$WM]3e\025\t1c\001C\003\0279\021\0051\006F\001\034\021\025iCD\"\001/\003\031aWM\\4uQV\tq\006\005\002\020a%\021\021G\002\002\005\031>tw\rC\00349\031\005A'\001\003v]&$X#A\033\021\005YBdB\001\0068\023\t1#!\003\002:u\tAA+[7f+:LGO\003\002'\005!)A\b\bD\001]\0059Ao\034(b]>\034\b\"\002 \035\r\003q\023\001\003;p\033&\034'o\\:\t\013\001cb\021\001\030\002\021Q|W*\0337mSNDQA\021\017\007\0029\n\021\002^8TK\016|g\016Z:\t\013\021cb\021\001\030\002\023Q|W*\0338vi\026\034\b\"\002$\035\r\003q\023a\002;p\021>,(o\035\005\006\021r1\tAL\001\007i>$\025-_:\t\013)cb\021A&\002\rQ|WK\\5u)\tau\n\005\002\020\033&\021aJ\002\002\007\t>,(\r\\3\t\013MJ\005\031A\033\t\013Ecb\021\001*\002\013\021\002H.^:\025\005m\031\006\"\002+Q\001\004Y\022!B8uQ\026\024\b\"\002,\035\r\0039\026A\002\023nS:,8\017\006\002\0341\")A+\026a\0017!)!\f\bD\0017\0061A\005^5nKN$\"a\007/\t\013uK\006\031\001'\002\r\031\f7\r^8s\021\025yFD\"\001a\003\021!C-\033<\025\005m\t\007\"\0022_\001\004a\025a\0023jm&\034xN\035\005\006?r1\t\001\032\013\003\031\026DQAY2A\002mAQa\032\017\007\002!\fA\"\0368bef|F%\\5okN,\022a\007\005\006Ur1\ta[\001\tSN4\025N\\5uKR\tA\016\005\002\020[&\021aN\002\002\b\005>|G.Z1o\021\025\001H\004\"\001r\003\ri\027N\034\013\0037IDQ\001V8A\002mAQ\001\036\017\005\002U\f1!\\1y)\tYb\017C\003Ug\002\0071\004C\003y9\021\005\0210A\002eSZ$\"a\007>\t\013\t<\b\031\001'\t\013adB\021\001?\025\0051k\b\"\002+|\001\004Y\002BB@\035\t\003\t\t!\001\002hiR\031A.a\001\t\013Qs\b\031A\016\t\017\005\035A\004\"\001\002\n\005!q\r^3r)\ra\0271\002\005\007)\006\025\001\031A\016\t\017\005=A\004\"\001\002\022\005\021A\016\036\013\004Y\006M\001B\002+\002\016\001\0071\004C\004\002\030q!\t!!\007\002\t1$X-\035\013\004Y\006m\001B\002+\002\026\001\0071\004C\004\002 q!\t!!\t\002\0135Lg.^:\025\007m\t\031\003\003\004U\003;\001\ra\007\005\b\003OaB\021AA\025\003\riW\017\034\013\0047\005-\002BB/\002&\001\007A\n\003\004\0020q!\taK\001\004]\026<\007bBA\0329\021\005\021QG\001\005a2,8\017F\002\034\003oAa\001VA\031\001\004Y\022&\002\017\002<\005}\022bAA\037\005\tqa)\0338ji\026$UO]1uS>tgaBA!\027\005\005\0221\t\002\t\023:4\027N\\5uKN\031\021qH\016\t\017Y\ty\004\"\001\002HQ\021\021\021\n\t\005\003\027\ny$D\001\f\021\035\t\026q\bC\001\003\037\"2aGA)\021\031!\026Q\na\0017!9a+a\020\005\002\005UCcA\016\002X!1A+a\025A\002mAqAWA \t\003\tY\006F\002\034\003;Ba!XA-\001\004a\005bB0\002@\021\005\021\021\r\013\0047\005\r\004B\0022\002`\001\007A\nC\004`\003!\t!a\032\025\0071\013I\007\003\004c\003K\002\ra\007\005\007U\006}BQA6\t\023\005=\024q\bQ\005\n\005E\024\001\0024bS2$B!a\035\002zA\031q\"!\036\n\007\005]dAA\004O_RD\027N\\4\t\021\005m\024Q\016a\001\003{\nAa\0365biB!\021qPAC\035\ry\021\021Q\005\004\003\0073\021A\002)sK\022,g-\003\003\002\b\006%%AB*ue&twMC\002\002\004\032Aa!LA \t\013q\003BB\032\002@\021\025A\007\003\004=\003!)A\f\005\007}\005}BQ\001\030\t\r\001\013y\004\"\002/\021\031\021\025q\bC\003]!1A)a\020\005\0069BaARA \t\013q\003B\002%\002@\021\025a&\013\003\002@\005}eaBAQ\003\001\0211\025\002\016y1|7-\0317!G\"LG\016\032 \024\t\005}\025\021\n\005\006[a\001\r\001\024\005\006ga\001\r!\016\005\0073-!\t!a+\025\r\0055\026qVAY!\rQ\0211\b\005\007[\005%\006\031A\030\t\rM\nI\0131\0016\021\031I2\002\"\001\0026R1\021QVA\\\003sCa!LAZ\001\004y\003bB\032\0024\002\007\021Q\020\005\t\003{[\001\025!\004\002@\006\001R.\031=Qe\026\034\027n]3E_V\024G.Z\b\003\003\003\004\003b\021!\001\001\001\001\001\001\001\005\0073-!\t!!2\025\007m\t9\r\003\005\002J\006\r\007\031AA?\003\005\031\b\002CAg\027\001&I!a4\002\013]|'\017Z:\025\t\005E\027Q\035\t\006?\005M\027q[\005\004\003+L#\001\002'jgR\004B!!7\002d6\021\0211\034\006\005\003;\fy.\001\003mC:<'BAAq\003\021Q\027M^1\n\t\005\035\0251\034\005\t\003\023\fY\r1\001\002~!A\021\021^\006!\n\023\tY/\001\007fqB\fg\016\032'bE\026d7\017\006\003\002n\006=\b#B\020\002T\006u\004\002CAy\003O\004\r!! \002\r1\f'-\0327t\021!\t)p\003Q\001\n\005]\030A\004;j[\026,f.\033;MC\n,Gn\035\t\007\003s\024\031A!\002\016\005\005m(\002BA\003\f\021\"[7nkR\f'\r\\3\013\007\t\005a!\001\006d_2dWm\031;j_:LA!!6\002|B9qBa\002\003\f\005]\027b\001B\005\r\t1A+\0369mKJ\002BA!\004\003\0265\021!q\002\006\004\013\tE!\002\002B\n\003?\fA!\036;jY&\031\021Ha\004\t\025\te1B1A\005\022\t\021Y\"\001\007uS6,WK\\5u\035\006lW-\006\002\003\036A9\021q\020B\020k\005u\024\002\002B\021\003\023\0231!T1q\021!\021)c\003Q\001\n\tu\021!\004;j[\026,f.\033;OC6,\007\005\003\006\003*-\021\r\021\"\005\003\005W\t\001\002^5nKVs\027\016^\013\003\005[\001r!a \003 \005uT\007\003\005\0032-\001\013\021\002B\027\003%!\030.\\3V]&$\b\005C\004\0036-!\tAa\016\002\017Ut\027\r\0359msR!!\021\bB!!\025y!1\bB \023\r\021iD\002\002\007\037B$\030n\0348\021\013=\0219aL\033\t\021\005%'1\007a\001\003{BqA!\016\f\t\003\021)\005\006\003\003:\t\035\003b\002B%\005\007\002\raG\001\002I\"9!QJ\006\005\002\t=\023!\0034s_6t\025M\\8t)\rY\"\021\013\005\b\005'\022Y\0051\001M\003\025q\027M\\8t\021!\0219f\003Q\001\016\te\023A\003b6h~\003XM]0og>\021!1\f\020\003\007!H\001Ba\030\fA\0035!\021M\001\n[N|\006/\032:`]N|!Aa\031\037\007=\021\005\t\003\005\003h-\001\013Q\002B5\003!\031x\f]3s?:\034xB\001B6=\021Y$T3\001\t\021\t=4\002)A\007\005c\n!\"\\5o?B,'o\0308t\037\t\021\031HH\003\016q C\006\001\003\005\003x-\001\013Q\002B=\003!Aw\f]3s?:\034xB\001B>=\031\031a\t\r]!\002!A!qP\006!\002\033\021\t)\001\005e?B,'o\0308t\037\t\021\031I\b\004O)G}\005\001\001\005\b\005\033ZA\021\001BD)\021\tiK!#\t\017\tM#Q\021a\001_!I!QR\006C\002\023\005!qR\001\0055\026\024x.\006\002\002.\"A!1S\006!\002\023\ti+A\003[KJ|\007\005C\005\003\030.\021\r\021\"\001\003\032\006IQK\0343fM&tW\rZ\013\003\003\023B\001B!(\fA\003%\021\021J\001\013+:$WMZ5oK\022\004\003\"\003BQ\027\t\007I\021\001BM\003\rIeN\032\005\t\005K[\001\025!\003\002J\005!\021J\0344!\021%\021Ik\003b\001\n\003\021I*\001\005NS:,8/\0238g\021!\021ik\003Q\001\n\005%\023!C'j]V\034\030J\0344!\021\035\021\tl\003C\001\005g\013aa\031:fCR,GCBAW\005k\0239\f\003\004.\005_\003\ra\f\005\007g\t=\006\031A\033\t\017\tE6\002\"\001\003<R)1D!0\003@\"1QF!/A\0021Caa\rB]\001\004)\004b\002BY\027\021\005!1\031\013\007\003[\023)Ma2\t\r5\022\t\r1\0010\021\035\031$\021\031a\001\003{BqA!-\f\t\003\021Y\rF\002\034\005\033D\001\"!3\003J\002\007\021QP\004\b\005#\\\0012\001Bj\003E!UO]1uS>t\027j](sI\026\024X\r\032\t\005\003\027\022)NB\004\003X.A\tA!7\003#\021+(/\031;j_:L5o\024:eKJ,Gm\005\004\003V\nm'\021\035\t\005\0033\024i.\003\003\003`\006m'AB(cU\026\034G\017\005\003 \005G\\\022b\001BsS\tAqJ\0353fe&tw\rC\004\027\005+$\tA!;\025\005\tM\007\002\003Bw\005+$\tAa<\002\017\r|W\016]1sKR1!\021\037B|\005w\0042a\004Bz\023\r\021)P\002\002\004\023:$\bb\002B}\005W\004\raG\001\002C\"9!Q Bv\001\004Y\022!\0012\t\025\r\005!Q[A\001\n\023\031\031!A\006sK\006$'+Z:pYZ,GC\001Bn\021%\031\taCA\001\n\023\031\031\001")
/*     */ public abstract class Duration implements Serializable, Ordered<Duration> {
/*     */   public static class Duration$$anonfun$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(char x$1) {
/*  54 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*  54 */       return scala.runtime.RichChar$.MODULE$.isWhitespace$extension(x$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Duration$$anonfun$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(char x$2) {
/*  59 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*  59 */       return scala.runtime.RichChar$.MODULE$.isLetter$extension(x$2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Duration$$anonfun$scala$concurrent$duration$Duration$$expandLabels$1 extends AbstractFunction1<String, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(String s) {
/*  75 */       (new String[2])[0] = s;
/*  75 */       (new String[2])[1] = (new StringBuilder()).append(s).append("s").toString();
/*  75 */       return scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$3 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String s) {
/*  89 */       return (String)Duration$.MODULE$.scala$concurrent$duration$Duration$$words(s).last();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$4 extends AbstractFunction1<Tuple2<TimeUnit, String>, List<Tuple2<String, TimeUnit>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<Tuple2<String, TimeUnit>> apply(Tuple2 x0$1) {
/*  93 */       if (x0$1 != null)
/*  93 */         return (List<Tuple2<String, TimeUnit>>)Duration$.MODULE$.scala$concurrent$duration$Duration$$expandLabels((String)x0$1._2()).map((Function1)new Duration$$anonfun$4$$anonfun$apply$1(this, x0$1), scala.collection.immutable.List$.MODULE$.canBuildFrom()); 
/*  93 */       throw new MatchError(x0$1);
/*     */     }
/*     */     
/*     */     public class Duration$$anonfun$4$$anonfun$apply$1 extends AbstractFunction1<String, Tuple2<String, TimeUnit>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Tuple2 x1$1;
/*     */       
/*     */       public final Tuple2<String, TimeUnit> apply(String x$5) {
/*  93 */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*  93 */         Object object = this.x1$1._1();
/*  93 */         scala.Predef$ArrowAssoc$ predef$ArrowAssoc$ = scala.Predef$ArrowAssoc$.MODULE$;
/*  93 */         return new Tuple2(x$5, object);
/*     */       }
/*     */       
/*     */       public Duration$$anonfun$4$$anonfun$apply$1(Duration.$anonfun$4 $outer, Tuple2 x1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$1 extends Infinite {
/*     */     public String toString() {
/* 174 */       return "Duration.Undefined";
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 175 */       return false;
/*     */     }
/*     */     
/*     */     public Duration $plus(Duration other) {
/* 176 */       return this;
/*     */     }
/*     */     
/*     */     public Duration $minus(Duration other) {
/* 177 */       return this;
/*     */     }
/*     */     
/*     */     public Duration $times(double factor) {
/* 178 */       return this;
/*     */     }
/*     */     
/*     */     public Duration $div(double factor) {
/* 179 */       return this;
/*     */     }
/*     */     
/*     */     public double $div(Duration other) {
/* 180 */       return Double.NaN;
/*     */     }
/*     */     
/*     */     public int compare(Duration other) {
/* 181 */       return (other == this) ? 0 : 1;
/*     */     }
/*     */     
/*     */     public Duration unary_$minus() {
/* 182 */       return this;
/*     */     }
/*     */     
/*     */     public double toUnit(TimeUnit unit) {
/* 183 */       return Double.NaN;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class Infinite extends Duration {
/*     */     public Duration $plus(Duration other) {
/*     */       Infinite infinite;
/* 187 */       if (other == Duration$.MODULE$.Undefined()) {
/* 187 */         infinite = Duration$.MODULE$.Undefined();
/*     */       } else {
/* 189 */         if (other instanceof Infinite) {
/* 189 */           Infinite infinite1 = (Infinite)other;
/* 189 */           if (infinite1 != this)
/* 189 */             return Duration$.MODULE$.Undefined(); 
/*     */         } 
/* 190 */         infinite = this;
/*     */       } 
/*     */       return infinite;
/*     */     }
/*     */     
/*     */     public Duration $minus(Duration other) {
/*     */       Infinite infinite;
/* 192 */       if (other == Duration$.MODULE$.Undefined()) {
/* 192 */         infinite = Duration$.MODULE$.Undefined();
/*     */       } else {
/* 194 */         if (other instanceof Infinite) {
/* 194 */           Infinite infinite1 = (Infinite)other;
/* 194 */           if (infinite1 == this)
/* 194 */             return Duration$.MODULE$.Undefined(); 
/*     */         } 
/* 195 */         infinite = this;
/*     */       } 
/*     */       return infinite;
/*     */     }
/*     */     
/*     */     public Duration $times(double factor) {
/* 199 */       return (factor == 0.0D || scala.Predef$.MODULE$.double2Double(factor).isNaN()) ? Duration$.MODULE$.Undefined() : (
/* 200 */         (factor < 0.0D) ? unary_$minus() : 
/* 201 */         this);
/*     */     }
/*     */     
/*     */     public Duration $div(double divisor) {
/* 204 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 204 */       return (scala.Predef$.MODULE$.double2Double(divisor).isNaN() || scala.Predef$.MODULE$.double2Double(divisor).isInfinite()) ? Duration$.MODULE$.Undefined() : (((new RichDouble(divisor)).compare(BoxesRunTime.boxToDouble(0.0D)) < 0) ? unary_$minus() : 
/* 205 */         this);
/*     */     }
/*     */     
/*     */     public double $div(Duration divisor) {
/*     */       double d;
/* 206 */       if (divisor instanceof Infinite) {
/* 206 */         d = Double.NaN;
/*     */       } else {
/* 208 */         d = Double.POSITIVE_INFINITY * ((($greater(Duration$.MODULE$.Zero()) ^ divisor.$greater$eq(Duration$.MODULE$.Zero())) != 0) ? -1 : true);
/*     */       } 
/*     */       return d;
/*     */     }
/*     */     
/*     */     public final boolean isFinite() {
/* 211 */       return false;
/*     */     }
/*     */     
/*     */     private scala.runtime.Nothing$ fail(String what) {
/* 213 */       (new String[2])[0] = "";
/* 213 */       (new String[2])[1] = " not allowed on infinite Durations";
/* 213 */       throw new IllegalArgumentException((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { what })));
/*     */     }
/*     */     
/*     */     public final long length() {
/* 214 */       throw fail("length");
/*     */     }
/*     */     
/*     */     public final TimeUnit unit() {
/* 215 */       throw fail("unit");
/*     */     }
/*     */     
/*     */     public final long toNanos() {
/* 216 */       throw fail("toNanos");
/*     */     }
/*     */     
/*     */     public final long toMicros() {
/* 217 */       throw fail("toMicros");
/*     */     }
/*     */     
/*     */     public final long toMillis() {
/* 218 */       throw fail("toMillis");
/*     */     }
/*     */     
/*     */     public final long toSeconds() {
/* 219 */       throw fail("toSeconds");
/*     */     }
/*     */     
/*     */     public final long toMinutes() {
/* 220 */       throw fail("toMinutes");
/*     */     }
/*     */     
/*     */     public final long toHours() {
/* 221 */       throw fail("toHours");
/*     */     }
/*     */     
/*     */     public final long toDays() {
/* 222 */       throw fail("toDays");
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$2 extends Infinite {
/*     */     public String toString() {
/* 231 */       return "Duration.Inf";
/*     */     }
/*     */     
/*     */     public int compare(Duration other) {
/*     */       boolean bool;
/* 232 */       if (other == Duration$.MODULE$.Undefined()) {
/* 232 */         bool = true;
/* 234 */       } else if (other == this) {
/* 234 */         bool = false;
/*     */       } else {
/* 235 */         bool = true;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public Duration unary_$minus() {
/* 237 */       return Duration$.MODULE$.MinusInf();
/*     */     }
/*     */     
/*     */     public double toUnit(TimeUnit unit) {
/* 238 */       return Double.POSITIVE_INFINITY;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$3 extends Infinite {
/*     */     public String toString() {
/* 247 */       return "Duration.MinusInf";
/*     */     }
/*     */     
/*     */     public int compare(Duration other) {
/* 248 */       return (other == this) ? 0 : -1;
/*     */     }
/*     */     
/*     */     public Duration unary_$minus() {
/* 249 */       return Duration$.MODULE$.Inf();
/*     */     }
/*     */     
/*     */     public double toUnit(TimeUnit unit) {
/* 250 */       return Double.NEGATIVE_INFINITY;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DurationIsOrdered$ implements Ordering<Duration> {
/*     */     public static final DurationIsOrdered$ MODULE$;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 291 */       return Ordering.class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 291 */       return Ordering.class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 291 */       return Ordering.class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 291 */       return Ordering.class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 291 */       return Ordering.class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 291 */       return Ordering.class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/* 291 */       return Ordering.class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/* 291 */       return Ordering.class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Duration> reverse() {
/* 291 */       return Ordering.class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 291 */       return Ordering.class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Duration>.Ops mkOrderingOps(Object lhs) {
/* 291 */       return Ordering.class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 291 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public DurationIsOrdered$() {
/* 291 */       MODULE$ = this;
/* 291 */       PartialOrdering.class.$init$((PartialOrdering)this);
/* 291 */       Ordering.class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Duration a, Duration b) {
/* 292 */       return a.compare(b);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean $less(Object that) {
/* 363 */     return Ordered.class.$less(this, that);
/*     */   }
/*     */   
/*     */   public boolean $greater(Object that) {
/* 363 */     return Ordered.class.$greater(this, that);
/*     */   }
/*     */   
/*     */   public boolean $less$eq(Object that) {
/* 363 */     return Ordered.class.$less$eq(this, that);
/*     */   }
/*     */   
/*     */   public boolean $greater$eq(Object that) {
/* 363 */     return Ordered.class.$greater$eq(this, that);
/*     */   }
/*     */   
/*     */   public int compareTo(Object that) {
/* 363 */     return Ordered.class.compareTo(this, that);
/*     */   }
/*     */   
/*     */   public Duration() {
/* 363 */     Ordered.class.$init$(this);
/*     */   }
/*     */   
/*     */   public Duration min(Duration other) {
/* 473 */     return $less(other) ? this : other;
/*     */   }
/*     */   
/*     */   public Duration max(Duration other) {
/* 477 */     return $greater(other) ? this : other;
/*     */   }
/*     */   
/*     */   public Duration div(double divisor) {
/* 487 */     return $div(divisor);
/*     */   }
/*     */   
/*     */   public double div(Duration other) {
/* 492 */     return $div(other);
/*     */   }
/*     */   
/*     */   public boolean gt(Duration other) {
/* 493 */     return $greater(other);
/*     */   }
/*     */   
/*     */   public boolean gteq(Duration other) {
/* 494 */     return $greater$eq(other);
/*     */   }
/*     */   
/*     */   public boolean lt(Duration other) {
/* 495 */     return $less(other);
/*     */   }
/*     */   
/*     */   public boolean lteq(Duration other) {
/* 496 */     return $less$eq(other);
/*     */   }
/*     */   
/*     */   public Duration minus(Duration other) {
/* 503 */     return $minus(other);
/*     */   }
/*     */   
/*     */   public Duration mul(double factor) {
/* 510 */     return $times(factor);
/*     */   }
/*     */   
/*     */   public Duration neg() {
/* 514 */     return unary_$minus();
/*     */   }
/*     */   
/*     */   public Duration plus(Duration other) {
/* 521 */     return $plus(other);
/*     */   }
/*     */   
/*     */   public static Duration create(String paramString) {
/*     */     return Duration$.MODULE$.create(paramString);
/*     */   }
/*     */   
/*     */   public static FiniteDuration create(long paramLong, String paramString) {
/*     */     return Duration$.MODULE$.create(paramLong, paramString);
/*     */   }
/*     */   
/*     */   public static Duration create(double paramDouble, TimeUnit paramTimeUnit) {
/*     */     return Duration$.MODULE$.create(paramDouble, paramTimeUnit);
/*     */   }
/*     */   
/*     */   public static FiniteDuration create(long paramLong, TimeUnit paramTimeUnit) {
/*     */     return Duration$.MODULE$.create(paramLong, paramTimeUnit);
/*     */   }
/*     */   
/*     */   public static Infinite MinusInf() {
/*     */     return Duration$.MODULE$.MinusInf();
/*     */   }
/*     */   
/*     */   public static Infinite Inf() {
/*     */     return Duration$.MODULE$.Inf();
/*     */   }
/*     */   
/*     */   public static Infinite Undefined() {
/*     */     return Duration$.MODULE$.Undefined();
/*     */   }
/*     */   
/*     */   public static FiniteDuration Zero() {
/*     */     return Duration$.MODULE$.Zero();
/*     */   }
/*     */   
/*     */   public static FiniteDuration fromNanos(long paramLong) {
/*     */     return Duration$.MODULE$.fromNanos(paramLong);
/*     */   }
/*     */   
/*     */   public static Duration fromNanos(double paramDouble) {
/*     */     return Duration$.MODULE$.fromNanos(paramDouble);
/*     */   }
/*     */   
/*     */   public static Option<Tuple2<Object, TimeUnit>> unapply(Duration paramDuration) {
/*     */     return Duration$.MODULE$.unapply(paramDuration);
/*     */   }
/*     */   
/*     */   public static Option<Tuple2<Object, TimeUnit>> unapply(String paramString) {
/*     */     return Duration$.MODULE$.unapply(paramString);
/*     */   }
/*     */   
/*     */   public static Duration apply(String paramString) {
/*     */     return Duration$.MODULE$.apply(paramString);
/*     */   }
/*     */   
/*     */   public static FiniteDuration apply(long paramLong, String paramString) {
/*     */     return Duration$.MODULE$.apply(paramLong, paramString);
/*     */   }
/*     */   
/*     */   public static FiniteDuration apply(long paramLong, TimeUnit paramTimeUnit) {
/*     */     return Duration$.MODULE$.apply(paramLong, paramTimeUnit);
/*     */   }
/*     */   
/*     */   public static Duration apply(double paramDouble, TimeUnit paramTimeUnit) {
/*     */     return Duration$.MODULE$.apply(paramDouble, paramTimeUnit);
/*     */   }
/*     */   
/*     */   public abstract long length();
/*     */   
/*     */   public abstract TimeUnit unit();
/*     */   
/*     */   public abstract long toNanos();
/*     */   
/*     */   public abstract long toMicros();
/*     */   
/*     */   public abstract long toMillis();
/*     */   
/*     */   public abstract long toSeconds();
/*     */   
/*     */   public abstract long toMinutes();
/*     */   
/*     */   public abstract long toHours();
/*     */   
/*     */   public abstract long toDays();
/*     */   
/*     */   public abstract double toUnit(TimeUnit paramTimeUnit);
/*     */   
/*     */   public abstract Duration $plus(Duration paramDuration);
/*     */   
/*     */   public abstract Duration $minus(Duration paramDuration);
/*     */   
/*     */   public abstract Duration $times(double paramDouble);
/*     */   
/*     */   public abstract Duration $div(double paramDouble);
/*     */   
/*     */   public abstract double $div(Duration paramDuration);
/*     */   
/*     */   public abstract Duration unary_$minus();
/*     */   
/*     */   public abstract boolean isFinite();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\duration\Duration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */