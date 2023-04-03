/*     */ package scala.util.parsing.combinator;
/*     */ 
/*     */ import java.io.Reader;
/*     */ import scala.Function0;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.matching.Regex;
/*     */ import scala.util.parsing.input.Reader;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\rfaB\001\003!\003\r\ta\003\002\r%\026<W\r\037)beN,'o\035\006\003\007\021\t!bY8nE&t\027\r^8s\025\t)a!A\004qCJ\034\030N\\4\013\005\035A\021\001B;uS2T\021!C\001\006g\016\fG.Y\002\001'\r\001A\002\005\t\003\0339i\021\001C\005\003\037!\021a!\0218z%\0264\007CA\t\023\033\005\021\021BA\n\003\005\035\001\026M]:feNDQ!\006\001\005\002Y\ta\001J5oSR$C#A\f\021\0055A\022BA\r\t\005\021)f.\033;\006\tm\001\001\001\b\002\005\0132,W\016\005\002\016;%\021a\004\003\002\005\007\"\f'\017C\004!\001\t\007I\021C\021\002\025]D\027\016^3Ta\006\034W-F\001#!\t\031c%D\001%\025\t)c!\001\005nCR\034\007.\0338h\023\t9CEA\003SK\036,\007\020\003\004*\001\001\006IAI\001\fo\"LG/Z*qC\016,\007\005C\003,\001\021\005A&\001\btW&\004x\013[5uKN\004\030mY3\026\0035\002\"!\004\030\n\005=B!a\002\"p_2,\027M\034\005\006c\001!\tBM\001\021Q\006tG\r\\3XQ&$Xm\0259bG\026$2a\r\034A!\tiA'\003\0026\021\t\031\021J\034;\t\013]\002\004\031\001\035\002\rM|WO]2f!\tId(D\001;\025\tYD(\001\003mC:<'\"A\037\002\t)\fg/Y\005\003i\022Ab\0215beN+\027/^3oG\026DQ!\021\031A\002M\naa\0344gg\026$\b\"B\"\001\t\007!\025a\0027ji\026\024\030\r\034\013\003\013B\0032AR$J\033\005\001\021B\001%\023\005\031\001\026M]:feB\021!*\024\b\003\033-K!\001\024\005\002\rA\023X\rZ3g\023\tquJ\001\004TiJLgn\032\006\003\031\"AQ!\025\"A\002%\013\021a\035\005\006'\002!\031\001V\001\006e\026<W\r\037\013\003\013VCQA\026*A\002\t\n\021A\035\005\0061\002!\t%W\001\013a>\034\030\016^5p]\026$WC\001._)\tY&\016E\002G\017r\003\"!\0300\r\001\021)ql\026b\001A\n\tA+\005\002bIB\021QBY\005\003G\"\021qAT8uQ&tw\r\005\002fQ6\taM\003\002h\t\005)\021N\0349vi&\021\021N\032\002\013!>\034\030\016^5p]\006d\007BB6X\t\003\007A.A\001q!\riQnW\005\003]\"\021\001\002\0202z]\006lWM\020\005\006a\002!\t%]\001\007a\"\024\030m]3\026\005I,HCA:{!\r1u\t\036\t\003;V$QaX8C\002Y\f\"!Y<\021\0055A\030BA=\t\005\r\te.\037\005\006W>\004\ra\035\005\006y\002!\t!`\001\006a\006\0248/Z\013\004}\006\035A#B@\002\n\0055\001#\002$\002\002\005\025\021bAA\002%\tY\001+\031:tKJ+7/\0367u!\ri\026q\001\003\006?n\024\rA\036\005\007Wn\004\r!a\003\021\t\031;\025Q\001\005\b\003\037Y\b\031AA\t\003\tIg\016\005\003f\003'a\022bAA\013M\n1!+Z1eKJDa\001 \001\005\002\005eQ\003BA\016\003C!b!!\b\002$\005\035\002#\002$\002\002\005}\001cA/\002\"\0211q,a\006C\002YDqa[A\f\001\004\t)\003\005\003G\017\006}\001bBA\b\003/\001\r\001\017\005\007y\002!\t!a\013\026\t\0055\0221\007\013\007\003_\t)$!\017\021\013\031\013\t!!\r\021\007u\013\031\004\002\004`\003S\021\rA\036\005\bW\006%\002\031AA\034!\0211u)!\r\t\021\005=\021\021\006a\001\003w\001B!!\020\002D5\021\021q\b\006\004\003\003b\024AA5p\023\021\t)\"a\020\t\017\005\035\003\001\"\001\002J\005A\001/\031:tK\006cG.\006\003\002L\005ECCBA'\003'\n9\006E\003G\003\003\ty\005E\002^\003#\"aaXA#\005\0041\bbB6\002F\001\007\021Q\013\t\005\r\036\013y\005\003\005\002\020\005\025\003\031AA\t\021\035\t9\005\001C\001\0037*B!!\030\002dQ1\021qLA3\003S\002RARA\001\003C\0022!XA2\t\031y\026\021\fb\001m\"91.!\027A\002\005\035\004\003\002$H\003CB\001\"a\004\002Z\001\007\0211\b\005\b\003\017\002A\021AA7+\021\ty'!\036\025\r\005E\024qOA>!\0251\025\021AA:!\ri\026Q\017\003\007?\006-$\031\001<\t\017-\fY\0071\001\002zA!aiRA:\021\035\ty!a\033A\002aBA\"a \001\003\003\005I\021BAA\003\037\013\001c];qKJ$\003o\\:ji&|g.\0323\026\t\005\r\025\021\022\013\005\003\013\013Y\t\005\003G\017\006\035\005cA/\002\n\0221q,! C\002\001D\001b[A?\t\003\007\021Q\022\t\005\0335\f))\003\002Y%!a\0211\023\001\002\002\003%I!!&\002\"\006a1/\0369fe\022\002\bN]1tKV!\021qSAO)\021\tI*a(\021\t\031;\0251\024\t\004;\006uEAB0\002\022\n\007a\017C\004l\003#\003\r!!'\n\005A\024\002")
/*     */ public interface RegexParsers extends Parsers {
/*     */   void scala$util$parsing$combinator$RegexParsers$_setter_$whiteSpace_$eq(Regex paramRegex);
/*     */   
/*     */   <T extends scala.util.parsing.input.Positional> Parsers.Parser<T> scala$util$parsing$combinator$RegexParsers$$super$positioned(Function0<Parsers.Parser<T>> paramFunction0);
/*     */   
/*     */   <T> Parsers.Parser<T> scala$util$parsing$combinator$RegexParsers$$super$phrase(Parsers.Parser<T> paramParser);
/*     */   
/*     */   Regex whiteSpace();
/*     */   
/*     */   boolean skipWhitespace();
/*     */   
/*     */   int handleWhiteSpace(CharSequence paramCharSequence, int paramInt);
/*     */   
/*     */   Parsers.Parser<String> literal(String paramString);
/*     */   
/*     */   Parsers.Parser<String> regex(Regex paramRegex);
/*     */   
/*     */   <T extends scala.util.parsing.input.Positional> Parsers.Parser<T> positioned(Function0<Parsers.Parser<T>> paramFunction0);
/*     */   
/*     */   <T> Parsers.Parser<T> phrase(Parsers.Parser<T> paramParser);
/*     */   
/*     */   <T> Parsers.ParseResult<T> parse(Parsers.Parser<T> paramParser, Reader<Object> paramReader);
/*     */   
/*     */   <T> Parsers.ParseResult<T> parse(Parsers.Parser<T> paramParser, CharSequence paramCharSequence);
/*     */   
/*     */   <T> Parsers.ParseResult<T> parse(Parsers.Parser<T> paramParser, Reader paramReader);
/*     */   
/*     */   <T> Parsers.ParseResult<T> parseAll(Parsers.Parser<T> paramParser, Reader<Object> paramReader);
/*     */   
/*     */   <T> Parsers.ParseResult<T> parseAll(Parsers.Parser<T> paramParser, Reader paramReader);
/*     */   
/*     */   <T> Parsers.ParseResult<T> parseAll(Parsers.Parser<T> paramParser, CharSequence paramCharSequence);
/*     */   
/*     */   public class RegexParsers$$anon$1 extends Parsers.Parser<String> {
/*     */     private final String s$1;
/*     */     
/*     */     public RegexParsers$$anon$1(RegexParsers $outer, String s$1) {
/*  83 */       super($outer);
/*     */     }
/*     */     
/*     */     public Parsers.ParseResult<String> apply(Reader in) {
/*  85 */       CharSequence source = in.source();
/*  86 */       int offset = in.offset();
/*  87 */       int start = this.$outer.handleWhiteSpace(source, offset);
/*  88 */       int i = 0;
/*  89 */       int j = start;
/*  90 */       while (i < this.s$1.length() && j < source.length() && this.s$1.charAt(i) == source.charAt(j)) {
/*  91 */         i++;
/*  92 */         j++;
/*     */       } 
/*  97 */       String found = (start == source.length()) ? "end of source" : (new StringBuilder()).append("`").append(BoxesRunTime.boxToCharacter(source.charAt(start))).append("'").toString();
/*  98 */       return (i == this.s$1.length()) ? new Parsers.Success<String>(this.$outer, source.subSequence(start, j).toString(), in.drop(j - offset)) : new Parsers.Failure(this.$outer, (new StringBuilder()).append("`").append(this.s$1).append("' expected but ").append(found).append(" found").toString(), in.drop(start - offset));
/*     */     }
/*     */   }
/*     */   
/*     */   public class RegexParsers$$anon$2 extends Parsers.Parser<String> {
/*     */     private final Regex r$1;
/*     */     
/*     */     public RegexParsers$$anon$2(RegexParsers $outer, Regex r$1) {
/* 104 */       super($outer);
/*     */     }
/*     */     
/*     */     public Parsers.ParseResult<String> apply(Reader in) {
/* 106 */       CharSequence source = in.source();
/* 107 */       int offset = in.offset();
/* 108 */       int start = this.$outer.handleWhiteSpace(source, offset);
/* 109 */       Option option = this.r$1.findPrefixMatchOf(source.subSequence(start, source.length()));
/* 110 */       if (option instanceof Some)
/* 110 */         Some some = (Some)option; 
/* 113 */       if (None$.MODULE$ == null) {
/* 113 */         if (option != null)
/*     */           throw new MatchError(option); 
/* 113 */       } else if (!None$.MODULE$.equals(option)) {
/*     */         throw new MatchError(option);
/*     */       } 
/* 114 */       String found = (start == source.length()) ? "end of source" : (new StringBuilder()).append("`").append(BoxesRunTime.boxToCharacter(source.charAt(start))).append("'").toString();
/* 115 */       return new Parsers.Failure(this.$outer, (new StringBuilder()).append("string matching regex `").append(this.r$1).append("' expected but ").append(found).append(" found").toString(), in.drop(start - offset));
/*     */     }
/*     */   }
/*     */   
/*     */   public class RegexParsers$$anon$3 extends Parsers.Parser<T> {
/*     */     private final Parsers.Parser pp$1;
/*     */     
/*     */     public RegexParsers$$anon$3(RegexParsers $outer, Parsers.Parser pp$1) {
/* 130 */       super($outer);
/*     */     }
/*     */     
/*     */     public Parsers.ParseResult<T> apply(Reader in) {
/* 132 */       int offset = in.offset();
/* 133 */       int start = this.$outer.handleWhiteSpace(in.source(), offset);
/* 134 */       return this.pp$1.apply(in.drop(start - offset));
/*     */     }
/*     */   }
/*     */   
/*     */   public class RegexParsers$$anonfun$phrase$1 extends AbstractFunction0<Parsers.Parser<Option<String>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<Option<String>> apply() {
/* 140 */       return this.$outer.opt((Function0<Parsers.Parser<String>>)new RegexParsers$$anonfun$phrase$1$$anonfun$apply$1(this));
/*     */     }
/*     */     
/*     */     public RegexParsers$$anonfun$phrase$1(RegexParsers $outer) {}
/*     */     
/*     */     public class RegexParsers$$anonfun$phrase$1$$anonfun$apply$1 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Parsers.Parser<String> apply() {
/* 140 */         Predef$ predef$ = Predef$.MODULE$;
/* 140 */         return this.$outer.$outer.regex((new StringOps("\\z")).r());
/*     */       }
/*     */       
/*     */       public RegexParsers$$anonfun$phrase$1$$anonfun$apply$1(RegexParsers$$anonfun$phrase$1 $outer) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\RegexParsers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */