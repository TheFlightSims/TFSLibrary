/*    */ package scala.util.parsing.json;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.MatchError;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.lexical.Scanners;
/*    */ import scala.util.parsing.input.Reader;
/*    */ 
/*    */ public final class JSON$ extends Parser {
/*    */   public static final JSON$ MODULE$;
/*    */   
/*    */   private JSON$() {
/* 33 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Object scala$util$parsing$json$JSON$$unRaw(Object in) {
/*    */     Object object;
/* 39 */     if (in instanceof JSONObject) {
/* 39 */       JSONObject jSONObject = (JSONObject)in;
/* 40 */       object = ((TraversableOnce)jSONObject.obj().map((Function1)new JSON$$anonfun$scala$util$parsing$json$JSON$$unRaw$1(), scala.collection.immutable.Map$.MODULE$.canBuildFrom())).toList();
/*    */     } else {
/* 41 */       if (in instanceof JSONArray) {
/* 41 */         JSONArray jSONArray = (JSONArray)in;
/* 41 */         Object object1 = jSONArray.list().map((Function1)new JSON$$anonfun$scala$util$parsing$json$JSON$$unRaw$2(), scala.collection.immutable.List$.MODULE$.canBuildFrom());
/*    */       } 
/* 42 */       object = in;
/*    */     } 
/*    */     return object;
/*    */   }
/*    */   
/*    */   public static class JSON$$anonfun$scala$util$parsing$json$JSON$$unRaw$1 extends AbstractFunction1<Tuple2<String, Object>, Tuple2<String, Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Tuple2<String, Object> apply(Tuple2 x0$1) {
/*    */       if (x0$1 != null)
/*    */         return new Tuple2(x0$1._1(), JSON$.MODULE$.scala$util$parsing$json$JSON$$unRaw(x0$1._2())); 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class JSON$$anonfun$scala$util$parsing$json$JSON$$unRaw$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object apply(Object in) {
/*    */       return JSON$.MODULE$.scala$util$parsing$json$JSON$$unRaw(in);
/*    */     }
/*    */   }
/*    */   
/*    */   public Option<JSONType> parseRaw(String input) {
/* 54 */     Parsers.ParseResult parseResult = phrase((Parsers.Parser)root()).apply((Reader)new Scanners.Scanner((Scanners)lexical(), input));
/* 55 */     if (parseResult instanceof Parsers.Success) {
/* 55 */       Parsers.Success success = (Parsers.Success)parseResult;
/* 55 */       Some some = new Some(success.result());
/*    */     } 
/* 56 */     return (Option<JSONType>)scala.None$.MODULE$;
/*    */   }
/*    */   
/*    */   public Option<Object> parseFull(String input) {
/* 68 */     Option<JSONType> option = parseRaw(input);
/* 69 */     if (option instanceof Some)
/* 69 */       Some some1 = (Some)option, some2 = new Some(resolveType(some1.x())); 
/* 70 */     if (scala.None$.MODULE$ == null) {
/* 70 */       if (option != null)
/*    */         throw new MatchError(option); 
/* 70 */     } else if (!scala.None$.MODULE$.equals(option)) {
/*    */       throw new MatchError(option);
/*    */     } 
/* 70 */     return (Option<Object>)scala.None$.MODULE$;
/*    */   }
/*    */   
/*    */   public Object resolveType(Object input) {
/*    */     Object object;
/* 77 */     if (input instanceof JSONObject) {
/* 77 */       JSONObject jSONObject = (JSONObject)input;
/* 78 */       object = jSONObject.obj().transform((Function2)new JSON$$anonfun$resolveType$1(), scala.collection.immutable.Map$.MODULE$.canBuildFrom());
/*    */     } else {
/* 81 */       if (input instanceof JSONArray) {
/* 81 */         JSONArray jSONArray = (JSONArray)input;
/* 81 */         Object object1 = jSONArray.list().map((Function1)new JSON$$anonfun$resolveType$2(), scala.collection.immutable.List$.MODULE$.canBuildFrom());
/*    */       } 
/* 82 */       object = input;
/*    */     } 
/*    */     return object;
/*    */   }
/*    */   
/*    */   public static class JSON$$anonfun$resolveType$1 extends AbstractFunction2<String, Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object apply(String x0$2, Object x1$1) {
/*    */       Tuple2 tuple2 = new Tuple2(x0$2, x1$1);
/*    */       if (tuple2 != null)
/*    */         return JSON$.MODULE$.resolveType(tuple2._2()); 
/*    */       throw new MatchError(tuple2);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class JSON$$anonfun$resolveType$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object apply(Object input) {
/*    */       return JSON$.MODULE$.resolveType(input);
/*    */     }
/*    */   }
/*    */   
/*    */   public void globalNumberParser_$eq(Function1<String, Object> f) {
/* 88 */     defaultNumberParser_$eq(f);
/*    */   }
/*    */   
/*    */   public Function1<String, Object> globalNumberParser() {
/* 89 */     return defaultNumberParser();
/*    */   }
/*    */   
/*    */   public void perThreadNumberParser_$eq(Function1<String, Object> f) {
/* 96 */     numberParser().set(f);
/*    */   }
/*    */   
/*    */   public Function1<String, Object> perThreadNumberParser() {
/* 97 */     return numberParser().get();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\JSON$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */