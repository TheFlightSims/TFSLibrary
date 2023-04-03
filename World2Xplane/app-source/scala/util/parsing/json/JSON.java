/*    */ package scala.util.parsing.json;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Function3;
/*    */ import scala.Function4;
/*    */ import scala.Function5;
/*    */ import scala.MatchError;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.HashMap;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.Parsers$;
/*    */ import scala.util.parsing.combinator.Parsers$$tilde$;
/*    */ import scala.util.parsing.combinator.Parsers$Error$;
/*    */ import scala.util.parsing.combinator.Parsers$Failure$;
/*    */ import scala.util.parsing.combinator.Parsers$NoSuccess$;
/*    */ import scala.util.parsing.combinator.Parsers$Success$;
/*    */ import scala.util.parsing.input.Reader;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=;Q!\001\002\t\002-\tAAS*P\035*\0211\001B\001\005UN|gN\003\002\006\r\0059\001/\031:tS:<'BA\004\t\003\021)H/\0337\013\003%\tQa]2bY\006\034\001\001\005\002\r\0335\t!AB\003\017\005!\005qB\001\003K'>s5CA\007\021!\ta\021#\003\002\023\005\t1\001+\031:tKJDQ\001F\007\005\002U\ta\001P5oSRtD#A\006\t\013]iA\021\002\r\002\013Ut'+Y<\025\005ei\002C\001\016\034\033\005A\021B\001\017\t\005\r\te.\037\005\006=Y\001\r!G\001\003S:DQ\001I\007\005\002\005\n\001\002]1sg\026\024\026m\036\013\003E!\0022AG\022&\023\t!\003B\001\004PaRLwN\034\t\003\031\031J!a\n\002\003\021)\033vJ\024+za\026DQ!K\020A\002)\nQ!\0338qkR\004\"a\013\030\017\005ia\023BA\027\t\003\031\001&/\0323fM&\021q\006\r\002\007'R\024\030N\\4\013\0055B\001\"\002\032\016\t\003\031\024!\0039beN,g)\0367m)\t!T\007E\002\033GeAQ!K\031A\002)BQaN\007\005\002a\n1B]3t_24X\rV=qKR\021\021$\017\005\006SY\002\r!\007\005\006w5!\t\001P\001\027O2|'-\0317Ok6\024WM\035)beN,'o\030\023fcR\021Q\b\021\t\0035yJ!a\020\005\003\tUs\027\016\036\005\006\003j\002\rAQ\001\002MB\0211\tR\007\002\033%\021Q)\005\002\016\035VlWM]5d!\006\0248/\032:\t\013\035kA\021\001%\002%\035dwNY1m\035Vl'-\032:QCJ\034XM]\013\002\005\")!*\004C\001\027\006I\002/\032:UQJ,\027\r\032(v[\n,'\017U1sg\026\024x\fJ3r)\tiD\nC\003B\023\002\007!\tC\003O\033\021\005\001*A\013qKJ$\006N]3bI:+XNY3s!\006\0248/\032:")
/*    */ public final class JSON {
/*    */   public static Function1<String, Object> perThreadNumberParser() {
/*    */     return JSON$.MODULE$.perThreadNumberParser();
/*    */   }
/*    */   
/*    */   public static void perThreadNumberParser_$eq(Function1<String, Object> paramFunction1) {
/*    */     JSON$.MODULE$.perThreadNumberParser_$eq(paramFunction1);
/*    */   }
/*    */   
/*    */   public static Function1<String, Object> globalNumberParser() {
/*    */     return JSON$.MODULE$.globalNumberParser();
/*    */   }
/*    */   
/*    */   public static void globalNumberParser_$eq(Function1<String, Object> paramFunction1) {
/*    */     JSON$.MODULE$.globalNumberParser_$eq(paramFunction1);
/*    */   }
/*    */   
/*    */   public static Object resolveType(Object paramObject) {
/*    */     return JSON$.MODULE$.resolveType(paramObject);
/*    */   }
/*    */   
/*    */   public static Option<Object> parseFull(String paramString) {
/*    */     return JSON$.MODULE$.parseFull(paramString);
/*    */   }
/*    */   
/*    */   public static Option<JSONType> parseRaw(String paramString) {
/*    */     return JSON$.MODULE$.parseRaw(paramString);
/*    */   }
/*    */   
/*    */   public static <T> Function1<Parsers$.tilde<T, List<T>>, List<T>> mkList() {
/*    */     return JSON$.MODULE$.mkList();
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> phrase(Parsers.Parser<T> paramParser) {
/*    */     return JSON$.MODULE$.phrase(paramParser);
/*    */   }
/*    */   
/*    */   public static <T extends scala.util.parsing.input.Positional> Parsers.Parser<T> positioned(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return JSON$.MODULE$.positioned(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> guard(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return JSON$.MODULE$.guard(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<BoxedUnit> not(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return JSON$.MODULE$.not(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<Option<T>> opt(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return JSON$.MODULE$.opt(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T, U> Parsers.Parser<U> chainr1(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<Function2<T, U, U>>> paramFunction01, Function2<T, U, U> paramFunction2, U paramU) {
/*    */     return JSON$.MODULE$.chainr1(paramFunction0, paramFunction01, paramFunction2, paramU);
/*    */   }
/*    */   
/*    */   public static <T, U> Parsers.Parser<T> chainl1(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<U>> paramFunction01, Function0<Parsers.Parser<Function2<T, U, T>>> paramFunction02) {
/*    */     return JSON$.MODULE$.chainl1(paramFunction0, paramFunction01, paramFunction02);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> chainl1(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<Function2<T, T, T>>> paramFunction01) {
/*    */     return JSON$.MODULE$.chainl1(paramFunction0, paramFunction01);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> rep1sep(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<Object>> paramFunction01) {
/*    */     return JSON$.MODULE$.rep1sep(paramFunction0, paramFunction01);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> repN(int paramInt, Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return JSON$.MODULE$.repN(paramInt, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> rep1(Function0<Parsers.Parser<T>> paramFunction01, Function0<Parsers.Parser<T>> paramFunction02) {
/*    */     return JSON$.MODULE$.rep1(paramFunction01, paramFunction02);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> rep1(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return JSON$.MODULE$.rep1(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> repsep(Function0<Parsers.Parser<T>> paramFunction0, Function0<Parsers.Parser<Object>> paramFunction01) {
/*    */     return JSON$.MODULE$.repsep(paramFunction0, paramFunction01);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<List<T>> rep(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return JSON$.MODULE$.rep(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> log(Function0<Parsers.Parser<T>> paramFunction0, String paramString) {
/*    */     return JSON$.MODULE$.log(paramFunction0, paramString);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> success(T paramT) {
/*    */     return JSON$.MODULE$.success(paramT);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Nothing$> err(String paramString) {
/*    */     return JSON$.MODULE$.err(paramString);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Nothing$> failure(String paramString) {
/*    */     return JSON$.MODULE$.failure(paramString);
/*    */   }
/*    */   
/*    */   public static <ES> Parsers.Parser<List<Object>> acceptSeq(ES paramES, Function1<ES, Iterable<Object>> paramFunction1) {
/*    */     return JSON$.MODULE$.acceptSeq(paramES, paramFunction1);
/*    */   }
/*    */   
/*    */   public static <U> Parsers.Parser<U> acceptMatch(String paramString, PartialFunction<Object, U> paramPartialFunction) {
/*    */     return JSON$.MODULE$.acceptMatch(paramString, paramPartialFunction);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> acceptIf(Function1<Object, Object> paramFunction1, Function1<Object, String> paramFunction11) {
/*    */     return JSON$.MODULE$.acceptIf(paramFunction1, paramFunction11);
/*    */   }
/*    */   
/*    */   public static <U> Parsers.Parser<U> accept(String paramString, PartialFunction<Object, U> paramPartialFunction) {
/*    */     return JSON$.MODULE$.accept(paramString, paramPartialFunction);
/*    */   }
/*    */   
/*    */   public static <ES> Parsers.Parser<List<Object>> accept(ES paramES, Function1<ES, List<Object>> paramFunction1) {
/*    */     return JSON$.MODULE$.accept(paramES, paramFunction1);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> accept(Object paramObject) {
/*    */     return JSON$.MODULE$.accept(paramObject);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> elem(Object paramObject) {
/*    */     return JSON$.MODULE$.elem(paramObject);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> elem(String paramString, Function1<Object, Object> paramFunction1) {
/*    */     return JSON$.MODULE$.elem(paramString, paramFunction1);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> commit(Function0<Parsers.Parser<T>> paramFunction0) {
/*    */     return JSON$.MODULE$.commit(paramFunction0);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> OnceParser(Function1<Reader<Object>, Parsers.ParseResult<T>> paramFunction1) {
/*    */     return JSON$.MODULE$.OnceParser(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <T> Parsers.Parser<T> Parser(Function1<Reader<Object>, Parsers.ParseResult<T>> paramFunction1) {
/*    */     return JSON$.MODULE$.Parser(paramFunction1);
/*    */   }
/*    */   
/*    */   public static void lastNoSuccess_$eq(Parsers.NoSuccess paramNoSuccess) {
/*    */     JSON$.MODULE$.lastNoSuccess_$eq(paramNoSuccess);
/*    */   }
/*    */   
/*    */   public static Parsers.NoSuccess lastNoSuccess() {
/*    */     return JSON$.MODULE$.lastNoSuccess();
/*    */   }
/*    */   
/*    */   public static Parsers$$tilde$ $tilde() {
/*    */     return JSON$.MODULE$.$tilde();
/*    */   }
/*    */   
/*    */   public static Parsers$Error$ Error() {
/*    */     return JSON$.MODULE$.Error();
/*    */   }
/*    */   
/*    */   public static Parsers$Failure$ Failure() {
/*    */     return JSON$.MODULE$.Failure();
/*    */   }
/*    */   
/*    */   public static Parsers$NoSuccess$ NoSuccess() {
/*    */     return JSON$.MODULE$.NoSuccess();
/*    */   }
/*    */   
/*    */   public static Parsers$Success$ Success() {
/*    */     return JSON$.MODULE$.Success();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<String> ident() {
/*    */     return JSON$.MODULE$.ident();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<String> stringLit() {
/*    */     return JSON$.MODULE$.stringLit();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<String> numericLit() {
/*    */     return JSON$.MODULE$.numericLit();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<String> keyword(String paramString) {
/*    */     return JSON$.MODULE$.keyword(paramString);
/*    */   }
/*    */   
/*    */   public static void scala$util$parsing$combinator$syntactical$StdTokenParsers$_setter_$keywordCache_$eq(HashMap paramHashMap) {
/*    */     JSON$.MODULE$.scala$util$parsing$combinator$syntactical$StdTokenParsers$_setter_$keywordCache_$eq(paramHashMap);
/*    */   }
/*    */   
/*    */   public static HashMap<String, Parsers.Parser<String>> keywordCache() {
/*    */     return JSON$.MODULE$.keywordCache();
/*    */   }
/*    */   
/*    */   public static <A, T> Function1<Parsers$.tilde<A, Option<List<A>>>, T> headOptionTailToFunList(Function1<List<A>, T> paramFunction1) {
/*    */     return JSON$.MODULE$.headOptionTailToFunList(paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A, B, C, D, E, F> Function1<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<A, B>, C>, D>, E>, F> flatten5(Function5<A, B, C, D, E, F> paramFunction5) {
/*    */     return JSON$.MODULE$.flatten5(paramFunction5);
/*    */   }
/*    */   
/*    */   public static <A, B, C, D, E> Function1<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<A, B>, C>, D>, E> flatten4(Function4<A, B, C, D, E> paramFunction4) {
/*    */     return JSON$.MODULE$.flatten4(paramFunction4);
/*    */   }
/*    */   
/*    */   public static <A, B, C, D> Function1<Parsers$.tilde<Parsers$.tilde<A, B>, C>, D> flatten3(Function3<A, B, C, D> paramFunction3) {
/*    */     return JSON$.MODULE$.flatten3(paramFunction3);
/*    */   }
/*    */   
/*    */   public static <A, B, C> Function1<Parsers$.tilde<A, B>, C> flatten2(Function2<A, B, C> paramFunction2) {
/*    */     return JSON$.MODULE$.flatten2(paramFunction2);
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> number() {
/*    */     return JSON$.MODULE$.number();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<String> stringVal() {
/*    */     return JSON$.MODULE$.stringVal();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Object> value() {
/*    */     return JSON$.MODULE$.value();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Tuple2<String, Object>> objEntry() {
/*    */     return JSON$.MODULE$.objEntry();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<JSONArray> jsonArray() {
/*    */     return JSON$.MODULE$.jsonArray();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<JSONObject> jsonObj() {
/*    */     return JSON$.MODULE$.jsonObj();
/*    */   }
/*    */   
/*    */   public static Parsers.Parser<Serializable> root() {
/*    */     return JSON$.MODULE$.root();
/*    */   }
/*    */   
/*    */   public static ThreadLocal<Function1<String, Object>> numberParser() {
/*    */     return JSON$.MODULE$.numberParser();
/*    */   }
/*    */   
/*    */   public static Function1<String, Object> defaultNumberParser() {
/*    */     return JSON$.MODULE$.defaultNumberParser();
/*    */   }
/*    */   
/*    */   public static Lexer lexical() {
/*    */     return JSON$.MODULE$.lexical();
/*    */   }
/*    */   
/*    */   public static class JSON$$anonfun$scala$util$parsing$json$JSON$$unRaw$1 extends AbstractFunction1<Tuple2<String, Object>, Tuple2<String, Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Tuple2<String, Object> apply(Tuple2 x0$1) {
/* 40 */       if (x0$1 != null)
/* 40 */         return new Tuple2(x0$1._1(), JSON$.MODULE$.scala$util$parsing$json$JSON$$unRaw(x0$1._2())); 
/* 40 */       throw new MatchError(x0$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class JSON$$anonfun$scala$util$parsing$json$JSON$$unRaw$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object apply(Object in) {
/* 41 */       return JSON$.MODULE$.scala$util$parsing$json$JSON$$unRaw(in);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class JSON$$anonfun$resolveType$1 extends AbstractFunction2<String, Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object apply(String x0$2, Object x1$1) {
/* 78 */       Tuple2 tuple2 = new Tuple2(x0$2, x1$1);
/* 78 */       if (tuple2 != null)
/* 78 */         return 
/* 79 */           JSON$.MODULE$.resolveType(tuple2._2()); 
/*    */       throw new MatchError(tuple2);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class JSON$$anonfun$resolveType$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object apply(Object input) {
/* 81 */       return JSON$.MODULE$.resolveType(input);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\JSON.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */