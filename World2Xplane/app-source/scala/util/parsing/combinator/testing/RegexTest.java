/*    */ package scala.util.parsing.combinator.testing;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.util.matching.Regex;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.Parsers$;
/*    */ import scala.util.parsing.combinator.Parsers$$tilde$;
/*    */ import scala.util.parsing.combinator.Parsers$Error$;
/*    */ import scala.util.parsing.combinator.Parsers$Failure$;
/*    */ import scala.util.parsing.combinator.Parsers$NoSuccess$;
/*    */ import scala.util.parsing.combinator.Parsers$Success$;
/*    */ import scala.util.parsing.input.Reader;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\001<Q!\001\002\t\0025\t\021BU3hKb$Vm\035;\013\005\r!\021a\002;fgRLgn\032\006\003\013\031\t!bY8nE&t\027\r^8s\025\t9\001\"A\004qCJ\034\030N\\4\013\005%Q\021\001B;uS2T\021aC\001\006g\016\fG.Y\002\001!\tqq\"D\001\003\r\025\001\"\001#\001\022\005%\021VmZ3y)\026\034HoE\002\020%Y\001\"a\005\013\016\003)I!!\006\006\003\r\005s\027PU3g!\t9\002$D\001\005\023\tIBA\001\007SK\036,\007\020U1sg\026\0248\017C\003\034\037\021\005A$\001\004=S:LGO\020\013\002\033!9ad\004b\001\n\003y\022!B5eK:$X#\001\021\021\007\005\022c%D\001\020\023\t\031CE\001\004QCJ\034XM]\005\003K\021\021q\001U1sg\026\0248\017\005\002\024O%\021\001F\003\002\004\003:L\bB\002\026\020A\003%\001%\001\004jI\026tG\017\t\005\bY=\021\r\021\"\001 \003\031qW/\0342fe\"1af\004Q\001\n\001\nqA\\;nE\026\024\b\005C\0041\037\t\007I\021A\020\002\rM$(/\0338h\021\031\021t\002)A\005A\00591\017\036:j]\036\004\003b\002\033\020\005\004%\t!N\001\007a\006\0248/\032:\026\003Y\0022!\t\0228!\rA\004I\n\b\003syr!AO\037\016\003mR!\001\020\007\002\rq\022xn\034;?\023\005Y\021BA \013\003\035\001\030mY6bO\026L!!\021\"\003\t1K7\017\036\006\003)Aa\001R\b!\002\0231\024a\0029beN,'\017\t\005\006\r>!\taR\001\005[\006Lg\016\006\002I\027B\0211#S\005\003\025*\021A!\0268ji\")A*\022a\001\033\006!\021M]4t!\r\031b\nU\005\003\037*\021Q!\021:sCf\004\"!\025+\017\005M\021\026BA*\013\003\031\001&/\0323fM&\021QK\026\002\007'R\024\030N\\4\013\005MS\001\006B\bY7v\003\"aE-\n\005iS!A\0033faJ,7-\031;fI\006\nA,\001\016UQ&\034\be\0317bgN\004s/\0337mA\t,\007E]3n_Z,G-I\001_\003\031\021d&\r\031/a!\"\001\001W.^\001")
/*    */ public final class RegexTest {
/*    */   public static <T> Function1<Parsers$.tilde<T, List<T>>, List<T>> mkList() {
/*    */     return RegexTest$.MODULE$.mkList();
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> guard(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return RegexTest$.MODULE$.guard(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<BoxedUnit> not(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return RegexTest$.MODULE$.not(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<Option<T>> opt(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return RegexTest$.MODULE$.opt(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T, U> Parsers.Parser<U> chainr1(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<Function2<T, U, U>>> paramFunction01, Function2<T, U, U> paramFunction2, U paramU) {
/*    */     return RegexTest$.MODULE$.chainr1(paramFunction0, paramFunction01, paramFunction2, paramU);
/*    */   }
/*    */   
/*    */   public static <T, U> Parsers.Parser<T> chainl1(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<U>> paramFunction01, Function0<Parsers.Parser<Function2<T, U, T>>> paramFunction02) {
/*    */     return RegexTest$.MODULE$.chainl1(paramFunction0, paramFunction01, paramFunction02);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> chainl1(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<Function2<T, T, T>>> paramFunction01) {
/*    */     return RegexTest$.MODULE$.chainl1(paramFunction0, paramFunction01);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> rep1sep(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<Object>> paramFunction01) {
/*    */     return RegexTest$.MODULE$.rep1sep(paramFunction0, paramFunction01);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> repN(int paramInt, Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return RegexTest$.MODULE$.repN(paramInt, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> rep1(Function0<Parsers.Parser<T>> paramFunction01, Function0<Parsers.Parser<T>> paramFunction02) {
/*    */     return RegexTest$.MODULE$.rep1(paramFunction01, paramFunction02);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> rep1(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return RegexTest$.MODULE$.rep1(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> repsep(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<Object>> paramFunction01) {
/*    */     return RegexTest$.MODULE$.repsep(paramFunction0, paramFunction01);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> rep(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return RegexTest$.MODULE$.rep(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> log(Function0<Parsers.Parser<T>> paramFunction0, String paramString) {
/*    */     return RegexTest$.MODULE$.log(paramFunction0, paramString);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> success(T paramT) {
/*    */     return RegexTest$.MODULE$.success(paramT);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Nothing$> err(String paramString) {
/*    */     return RegexTest$.MODULE$.err(paramString);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Nothing$> failure(String paramString) {
/*    */     return RegexTest$.MODULE$.failure(paramString);
/*    */   }
/*    */   
/*    */   public static <ES> Parsers.Parser<List<Object>> acceptSeq(ES paramES, Function1<ES, Iterable<Object>> paramFunction1) {
/*    */     return RegexTest$.MODULE$.acceptSeq(paramES, paramFunction1);
/*    */   }
/*    */   
/*    */   public static <U> Parsers.Parser<U> acceptMatch(String paramString, PartialFunction<Object, U> paramPartialFunction) {
/*    */     return RegexTest$.MODULE$.acceptMatch(paramString, paramPartialFunction);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> acceptIf(Function1<Object, Object> paramFunction1, Function1<Object, String> paramFunction11) {
/*    */     return RegexTest$.MODULE$.acceptIf(paramFunction1, paramFunction11);
/*    */   }
/*    */   
/*    */   public static <U> Parsers.Parser<U> accept(String paramString, PartialFunction<Object, U> paramPartialFunction) {
/*    */     return RegexTest$.MODULE$.accept(paramString, paramPartialFunction);
/*    */   }
/*    */   
/*    */   public static <ES> Parsers.Parser<List<Object>> accept(ES paramES, Function1<ES, List<Object>> paramFunction1) {
/*    */     return RegexTest$.MODULE$.accept(paramES, paramFunction1);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> accept(Object paramObject) {
/*    */     return RegexTest$.MODULE$.accept(paramObject);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> elem(Object paramObject) {
/*    */     return RegexTest$.MODULE$.elem(paramObject);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> elem(String paramString, Function1<Object, Object> paramFunction1) {
/*    */     return RegexTest$.MODULE$.elem(paramString, paramFunction1);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> commit(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return RegexTest$.MODULE$.commit(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> OnceParser(Function1<Reader<Object>, Parsers.ParseResult<T>> paramFunction1) {
/*    */     return RegexTest$.MODULE$.OnceParser(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> Parser(Function1<Reader<Object>, Parsers.ParseResult<T>> paramFunction1) {
/*    */     return RegexTest$.MODULE$.Parser(paramFunction1);
/*    */   }
/*    */   
/*    */   public static void lastNoSuccess_$eq(Parsers.NoSuccess paramNoSuccess) {
/*    */     RegexTest$.MODULE$.lastNoSuccess_$eq(paramNoSuccess);
/*    */   }
/*    */   
/*    */   public static Parsers.NoSuccess lastNoSuccess() {
/*    */     return RegexTest$.MODULE$.lastNoSuccess();
/*    */   }
/*    */   
/*    */   public static Parsers$$tilde$ $tilde() {
/*    */     return RegexTest$.MODULE$.$tilde();
/*    */   }
/*    */   
/*    */   public static Parsers$Error$ Error() {
/*    */     return RegexTest$.MODULE$.Error();
/*    */   }
/*    */   
/*    */   public static Parsers$Failure$ Failure() {
/*    */     return RegexTest$.MODULE$.Failure();
/*    */   }
/*    */   
/*    */   public static Parsers$NoSuccess$ NoSuccess() {
/*    */     return RegexTest$.MODULE$.NoSuccess();
/*    */   }
/*    */   
/*    */   public static Parsers$Success$ Success() {
/*    */     return RegexTest$.MODULE$.Success();
/*    */   }
/*    */   
/*    */   public static <T> Parsers.ParseResult<T> parseAll(Parsers.Parser<T> paramParser, CharSequence paramCharSequence) {
/*    */     return RegexTest$.MODULE$.parseAll(paramParser, paramCharSequence);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.ParseResult<T> parseAll(Parsers.Parser<T> paramParser, Reader paramReader) {
/*    */     return RegexTest$.MODULE$.parseAll(paramParser, paramReader);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.ParseResult<T> parseAll(Parsers.Parser<T> paramParser, Reader<Object> paramReader) {
/*    */     return RegexTest$.MODULE$.parseAll(paramParser, paramReader);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.ParseResult<T> parse(Parsers.Parser<T> paramParser, Reader paramReader) {
/*    */     return RegexTest$.MODULE$.parse(paramParser, paramReader);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.ParseResult<T> parse(Parsers.Parser<T> paramParser, CharSequence paramCharSequence) {
/*    */     return RegexTest$.MODULE$.parse(paramParser, paramCharSequence);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.ParseResult<T> parse(Parsers.Parser<T> paramParser, Reader<Object> paramReader) {
/*    */     return RegexTest$.MODULE$.parse(paramParser, paramReader);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> phrase(Parsers.Parser<T> paramParser) {
/*    */     return RegexTest$.MODULE$.phrase(paramParser);
/*    */   }
/*    */   
/*    */   public static <T extends scala.util.parsing.input.Positional> Parsers.Parser<T> positioned(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return RegexTest$.MODULE$.positioned(paramFunction0);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<String> regex(Regex paramRegex) {
/*    */     return RegexTest$.MODULE$.regex(paramRegex);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<String> literal(String paramString) {
/*    */     return RegexTest$.MODULE$.literal(paramString);
/*    */   }
/*    */   
/*    */   public static int handleWhiteSpace(CharSequence paramCharSequence, int paramInt) {
/*    */     return RegexTest$.MODULE$.handleWhiteSpace(paramCharSequence, paramInt);
/*    */   }
/*    */   
/*    */   public static boolean skipWhitespace() {
/*    */     return RegexTest$.MODULE$.skipWhitespace();
/*    */   }
/*    */   
/*    */   public static void scala$util$parsing$combinator$RegexParsers$_setter_$whiteSpace_$eq(Regex paramRegex) {
/*    */     RegexTest$.MODULE$.scala$util$parsing$combinator$RegexParsers$_setter_$whiteSpace_$eq(paramRegex);
/*    */   }
/*    */   
/*    */   public static Regex whiteSpace() {
/*    */     return RegexTest$.MODULE$.whiteSpace();
/*    */   }
/*    */   
/*    */   public static void main(String[] paramArrayOfString) {
/*    */     RegexTest$.MODULE$.main(paramArrayOfString);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<List<Object>> parser() {
/*    */     return RegexTest$.MODULE$.parser();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> string() {
/*    */     return RegexTest$.MODULE$.string();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> number() {
/*    */     return RegexTest$.MODULE$.number();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> ident() {
/*    */     return RegexTest$.MODULE$.ident();
/*    */   }
/*    */   
/*    */   public static class $anonfun$1 extends AbstractFunction1<String, Ident> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Ident apply(String s) {
/* 17 */       return new Ident(s);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anonfun$2 extends AbstractFunction1<String, Number> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Number apply(String s) {
/* 18 */       Predef$ predef$ = Predef$.MODULE$;
/* 18 */       return new Number((new StringOps(s)).toInt());
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anonfun$3 extends AbstractFunction1<String, Str> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Str apply(String s) {
/* 19 */       return new Str(s.substring(1, s.length() - 1));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anonfun$4 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/* 20 */       return RegexTest$.MODULE$.number();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anonfun$5 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/* 20 */       return RegexTest$.MODULE$.string();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\testing\RegexTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */