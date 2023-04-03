/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.regex.PatternSyntaxException;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.IndexedSeqOptimized;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Ordered;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.util.matching.Regex;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tUt!B\001\003\021\003I\021AC*ue&tw\rT5lK*\0211\001B\001\nS6lW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001\001\"AC\006\016\003\t1Q\001\004\002\t\0025\021!b\025;sS:<G*[6f'\tYa\002\005\002\020!5\ta!\003\002\022\r\t1\021I\\=SK\032DQaE\006\005\002Q\ta\001P5oSRtD#A\005\t\017YY!\031!C\007/\005\021AJR\013\0021A\021q\"G\005\0035\031\021Aa\0215be\"1Ad\003Q\001\016a\t1\001\024$!\021\035q2B1A\005\016]\t!A\022$\t\r\001Z\001\025!\004\031\003\r1e\t\t\005\bE-\021\r\021\"\004\030\003\t\031%\013\003\004%\027\001\006i\001G\001\004\007J\003\003b\002\024\f\005\004%iaF\001\003'VCa\001K\006!\002\033A\022aA*VA\0319AB\001I\001\004\003QSCA\0266'\021ICfL\036\021\005=i\023B\001\030\007\005\r\te.\037\t\005aEB2'D\001\005\023\t\021DAA\nJ]\022,\0070\0323TKF|\005\017^5nSj,G\r\005\0025k1\001AA\002\034*\t\013\007qG\001\003SKB\024\030C\001\035-!\ty\021(\003\002;\r\t9aj\034;iS:<\007c\001\037E\017:\021QH\021\b\003}\005k\021a\020\006\003\001\"\ta\001\020:p_Rt\024\"A\004\n\005\r3\021a\0029bG.\fw-Z\005\003\013\032\023qa\024:eKJ,GM\003\002D\rA\021\001j\023\b\003\037%K!A\023\004\002\rA\023X\rZ3g\023\taUJ\001\004TiJLgn\032\006\003\025\032AQaT\025\005\002A\013a\001J5oSR$C#A)\021\005=\021\026BA*\007\005\021)f.\033;\t\rUK\003U\"\005W\003)qWm\036\"vS2$WM]\013\002/B!\001l\027\r4\033\005I&B\001.\005\003\035iW\017^1cY\026L!\001X-\003\017\t+\030\016\0343fe\")a,\013C\001?\006)\021\r\0359msR\021\001\004\031\005\006Cv\003\rAY\001\002]B\021qbY\005\003I\032\0211!\0238u\021\0251\027\006\"\001h\003\031aWM\\4uQV\t!\rC\003jS\021\005#.\001\005nWN#(/\0338h+\0059\005\"\0027*\t\003j\027!B:mS\016,GcA\032oa\")qn\033a\001E\006!aM]8n\021\025\t8\0161\001c\003\025)h\016^5m\021\025\031\030\006\"\001u\003\031!C/[7fgR\021q)\036\005\006CJ\004\rA\031\005\006o&\"\t\005_\001\bG>l\007/\031:f)\t\021\027\020C\003{m\002\007q)A\003pi\",'\017C\003}S\021%Q0A\006jg2Kg.\032\"sK\006\\Gc\001@\002\004A\021qb`\005\004\003\0031!a\002\"p_2,\027M\034\005\007\003\013Y\b\031\001\r\002\003\rDa!!\003*\t\003Q\027\001D:ue&\004H*\0338f\013:$\007bBA\007S\021\005\021qB\001\024Y&tWm],ji\"\034V\r]1sCR|'o]\013\003\003#\001B\001MA\n\017&\031\021Q\003\003\003\021%#XM]1u_JDq!!\007*\t\003\ty!A\003mS:,7\017C\004\002\036%\"\t!a\004\002\0331Lg.Z:Ji\026\024\030\r^8s\021\031\t\t#\013C\001U\006Q1-\0319ji\006d\027N_3\t\017\005\025\022\006\"\001\002(\005Y1\017\036:jaB\023XMZ5y)\021\tI#a\016\021\t\005-\022QG\007\003\003[QA!a\f\0022\005!A.\0318h\025\t\t\031$\001\003kCZ\f\027b\001'\002.!9\021\021HA\022\001\0049\025A\0029sK\032L\007\020C\004\002>%\"\t!a\020\002\027M$(/\0339Tk\0324\027\016\037\013\005\003S\t\t\005C\004\002D\005m\002\031A$\002\rM,hMZ5y\021\035\t9%\013C\001\003\023\n1C]3qY\006\034W-\0217m\031&$XM]1mYf$RaRA&\003\037Bq!!\024\002F\001\007q)A\004mSR,'/\0317\t\017\005E\023Q\ta\001\017\006Y!/\0329mC\016,W.\0328u\021\035\t)&\013C\001\003/\n1b\035;sSBl\025M]4j]R\031q)!\027\t\017\005m\0231\013a\0011\005QQ.\031:hS:\034\005.\031:\t\r\005U\023\006\"\001k\021\035\t\t'\013C\005\003G\na!Z:dCB,GcA$\002f!9\021qMA0\001\004A\022AA2i\021\035\tY'\013C\001\003[\nQa\0359mSR$B!a\034\002vA!q\"!\035H\023\r\t\031H\002\002\006\003J\024\030-\037\005\b\003o\nI\0071\001\031\003%\031X\r]1sCR|'\017\013\004\002j\005m\024\021\023\t\006\037\005u\024\021Q\005\004\0032!A\002;ie><8\017E\0025\003\007#q!!\"\001\005\004\t9IA\001U#\rA\024\021\022\t\005\003\027\013iI\004\002\020\005&\031\021q\022$\003\023QC'o\\<bE2,7EAAJ!\021\t)*a(\016\005\005]%\002BAM\0037\013QA]3hKbTA!!(\0022\005!Q\017^5m\023\021\t\t+a&\003-A\013G\017^3s]NKh\016^1y\013b\034W\r\035;j_:Dq!a\033*\t\003\t)\013\006\003\002p\005\035\006\002CAU\003G\003\r!a+\002\025M,\007/\031:bi>\0248\017\005\003\020\003cB\002FBAR\003_\013\t\nE\003\020\003{\n\t\fE\0025\003g#q!!\"\001\005\004\t9\tC\004\0028&\"\t!!/\002\003I,\"!a/\021\t\005u\026QY\007\003\003SA!!1\002D\006AQ.\031;dQ&twMC\002\002\036\032IA!a2\002@\n)!+Z4fq\"9\021qW\025\005\002\005-G\003BA^\003\033D\001\"a4\002J\002\007\021\021[\001\013OJ|W\017\035(b[\026\034\b\003B\b\002T\036K1!!6\007\005)a$/\0329fCR,GM\020\005\b\0033LC\021AAn\003%!xNQ8pY\026\fg.F\001\021\035\ty.\013C\001\003C\fa\001^8CsR,WCAAr!\ry\021Q]\005\004\003O4!\001\002\"zi\026Dq!a;*\t\003\ti/A\004u_NCwN\035;\026\005\005=\bcA\b\002r&\031\0211\037\004\003\013MCwN\035;\t\r\005]\030\006\"\001h\003\025!x.\0238u\021\035\tY0\013C\001\003{\fa\001^8M_:<WCAA\000!\ry!\021A\005\004\005\0071!\001\002'p]\036DqAa\002*\t\003\021I!A\004u_\032cw.\031;\026\005\t-\001cA\b\003\016%\031!q\002\004\003\013\031cw.\031;\t\017\tM\021\006\"\001\003\026\005AAo\034#pk\ndW-\006\002\003\030A\031qB!\007\n\007\tmaA\001\004E_V\024G.\032\005\b\005?IC\021\002B\021\0031\001\030M]:f\005>|G.Z1o)\rq(1\005\005\b\005K\021i\0021\001H\003\005\031\bb\002B\025S\021\005#1F\001\bi>\f%O]1z+\021\021iCa\r\025\t\t=\"\021\b\t\006\037\005E$\021\007\t\004i\tMB\001\003B\033\005O\021\rAa\016\003\003\t\013\"\001\007\027\t\025\tm\"qEA\001\002\b\021i$\001\006fm&$WM\\2fIE\002bAa\020\003F\tERB\001B!\025\r\021\031EB\001\be\0264G.Z2u\023\021\0219E!\021\003\021\rc\027m]:UC\036DqAa\023*\t\023\021i%A\005v]^\024\030\r]!sOR\031aBa\024\t\017\tE#\021\na\001Y\005\031\021M]4\t\017\tU\023\006\"\001\003X\0051am\034:nCR$2a\022B-\021!\021YFa\025A\002\tu\023\001B1sON\004BaDAjY!9!\021M\025\005\002\t\r\024a\0034pe6\fG\017T8dC2$Ra\022B3\005cB\001Ba\032\003`\001\007!\021N\001\002YB!!1\016B7\033\t\tY*\003\003\003p\005m%A\002'pG\006dW\r\003\005\003\\\t}\003\031\001B/!\rQ\021f\r")
/*     */ public interface StringLike<Repr> extends IndexedSeqOptimized<Object, Repr>, Ordered<String> {
/*     */   Builder<Object, Repr> newBuilder();
/*     */   
/*     */   char apply(int paramInt);
/*     */   
/*     */   int length();
/*     */   
/*     */   String mkString();
/*     */   
/*     */   Repr slice(int paramInt1, int paramInt2);
/*     */   
/*     */   String $times(int paramInt);
/*     */   
/*     */   int compare(String paramString);
/*     */   
/*     */   String stripLineEnd();
/*     */   
/*     */   Iterator<String> linesWithSeparators();
/*     */   
/*     */   Iterator<String> lines();
/*     */   
/*     */   Iterator<String> linesIterator();
/*     */   
/*     */   String capitalize();
/*     */   
/*     */   String stripPrefix(String paramString);
/*     */   
/*     */   String stripSuffix(String paramString);
/*     */   
/*     */   String replaceAllLiterally(String paramString1, String paramString2);
/*     */   
/*     */   String stripMargin(char paramChar);
/*     */   
/*     */   String stripMargin();
/*     */   
/*     */   String[] split(char paramChar) throws PatternSyntaxException;
/*     */   
/*     */   String[] split(char[] paramArrayOfchar) throws PatternSyntaxException;
/*     */   
/*     */   Regex r();
/*     */   
/*     */   Regex r(Seq<String> paramSeq);
/*     */   
/*     */   boolean toBoolean();
/*     */   
/*     */   byte toByte();
/*     */   
/*     */   short toShort();
/*     */   
/*     */   int toInt();
/*     */   
/*     */   long toLong();
/*     */   
/*     */   float toFloat();
/*     */   
/*     */   double toDouble();
/*     */   
/*     */   <B> Object toArray(ClassTag<B> paramClassTag);
/*     */   
/*     */   String format(Seq<Object> paramSeq);
/*     */   
/*     */   String formatLocal(Locale paramLocale, Seq<Object> paramSeq);
/*     */   
/*     */   public class StringLike$$anonfun$$times$1 extends AbstractFunction1<Object, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final StringBuilder buf$1;
/*     */     
/*     */     public final StringBuilder apply(int i) {
/*  71 */       return this.buf$1.append(this.$outer.toString());
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$$times$1(StringLike $outer, StringBuilder buf$1) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anon$1 extends AbstractIterator<String> {
/*     */     private final String str;
/*     */     
/*     */     private final int len;
/*     */     
/*     */     private int index;
/*     */     
/*     */     public StringLike$$anon$1(StringLike $outer) {
/* 111 */       this.str = $outer.toString();
/* 112 */       this.len = str().length();
/* 113 */       this.index = 0;
/*     */     }
/*     */     
/*     */     private String str() {
/*     */       return this.str;
/*     */     }
/*     */     
/*     */     private int len() {
/*     */       return this.len;
/*     */     }
/*     */     
/*     */     private int index() {
/* 113 */       return this.index;
/*     */     }
/*     */     
/*     */     private void index_$eq(int x$1) {
/* 113 */       this.index = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 114 */       return (index() < len());
/*     */     }
/*     */     
/*     */     public String next() {
/* 116 */       if (index() >= len())
/* 116 */         throw new NoSuchElementException("next on empty iterator"); 
/* 117 */       int start = index();
/* 118 */       for (; index() < len() && !StringLike$class.scala$collection$immutable$StringLike$$isLineBreak(this.$outer, this.$outer.apply(index())); index_$eq(index() + 1));
/* 119 */       index_$eq(index() + 1);
/* 120 */       int i = index();
/* 120 */       Predef$ predef$ = Predef$.MODULE$;
/* 120 */       return str().substring(start, RichInt$.MODULE$.min$extension(i, len()));
/*     */     }
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$lines$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String line) {
/* 129 */       return (new WrappedString(line)).stripLineEnd();
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$lines$1(StringLike $outer) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$linesIterator$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String line) {
/* 136 */       return (new WrappedString(line)).stripLineEnd();
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$linesIterator$1(StringLike $outer) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$stripMargin$1 extends AbstractFunction1<String, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final StringBuilder buf$2;
/*     */     
/*     */     private final char marginChar$1;
/*     */     
/*     */     public StringLike$$anonfun$stripMargin$1(StringLike $outer, StringBuilder buf$2, char marginChar$1) {}
/*     */     
/*     */     public final StringBuilder apply(String line) {
/* 182 */       int len = line.length();
/* 183 */       int index = 0;
/* 184 */       for (; index < len && line.charAt(index) <= ' '; index++);
/* 185 */       return this.buf$2.append((
/* 186 */           index < len && line.charAt(index) == this.marginChar$1) ? line.substring(index + 1) : line);
/*     */     }
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$1 extends AbstractFunction2<String, Object, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(String x$1, char x$2) {
/* 205 */       return (new StringBuilder()).append(x$1).append(StringLike$class.scala$collection$immutable$StringLike$$escape(this.$outer, x$2)).toString();
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$1(StringLike $outer) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$format$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object apply(Object arg) {
/* 266 */       return StringLike$class.scala$collection$immutable$StringLike$$unwrapArg(this.$outer, arg);
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$format$1(StringLike $outer) {}
/*     */   }
/*     */   
/*     */   public class StringLike$$anonfun$formatLocal$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object apply(Object arg) {
/* 283 */       return StringLike$class.scala$collection$immutable$StringLike$$unwrapArg(this.$outer, arg);
/*     */     }
/*     */     
/*     */     public StringLike$$anonfun$formatLocal$1(StringLike $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\StringLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */