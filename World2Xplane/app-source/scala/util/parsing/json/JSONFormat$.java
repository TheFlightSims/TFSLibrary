/*    */ package scala.util.parsing.json;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.LowPriorityImplicits;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class JSONFormat$ {
/*    */   public static final JSONFormat$ MODULE$;
/*    */   
/*    */   private final Function1<Object, String> defaultFormatter;
/*    */   
/*    */   private JSONFormat$() {
/* 43 */     MODULE$ = this;
/* 55 */     this.defaultFormatter = (Function1<Object, String>)new JSONFormat.$anonfun$1();
/*    */   }
/*    */   
/*    */   public Function1<Object, String> defaultFormatter() {
/* 55 */     return this.defaultFormatter;
/*    */   }
/*    */   
/*    */   public static class $anonfun$1 extends AbstractFunction1<Object, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(Object x) {
/*    */       String str;
/* 55 */       if (x instanceof String) {
/* 55 */         String str1 = (String)x;
/* 55 */         str = (new StringBuilder()).append("\"").append(JSONFormat$.MODULE$.quoteString(str1)).append("\"").toString();
/* 57 */       } else if (x instanceof JSONObject) {
/* 57 */         JSONObject jSONObject = (JSONObject)x;
/* 57 */         str = jSONObject.toString(JSONFormat$.MODULE$.defaultFormatter());
/* 58 */       } else if (x instanceof JSONArray) {
/* 58 */         JSONArray jSONArray = (JSONArray)x;
/* 58 */         str = jSONArray.toString(JSONFormat$.MODULE$.defaultFormatter());
/*    */       } else {
/* 59 */         str = x.toString();
/*    */       } 
/*    */       return str;
/*    */     }
/*    */   }
/*    */   
/*    */   public String quoteString(String s) {
/* 67 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$, predef$2 = scala.Predef$.MODULE$;
/* 86 */     return ((TraversableOnce)TraversableLike.class.map((TraversableLike)new StringOps(s), (Function1)new JSONFormat$$anonfun$quoteString$1(), (CanBuildFrom)new Object((LowPriorityImplicits)predef$2))).mkString();
/*    */   }
/*    */   
/*    */   public static class JSONFormat$$anonfun$quoteString$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object apply(char x0$5) {
/*    */       scala.Predef$ predef$;
/*    */       switch (x0$5) {
/*    */         default:
/*    */           predef$ = scala.Predef$.MODULE$;
/*    */           return ((x0$5 >= Character.MIN_VALUE && x0$5 <= '\037') || (x0$5 >= '' && x0$5 <= '')) ? (new StringOps("\\u%04x")).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(x0$5) })) : BoxesRunTime.boxToCharacter(x0$5);
/*    */         case '\t':
/*    */         
/*    */         case '\r':
/*    */         
/*    */         case '\n':
/*    */         
/*    */         case '\f':
/*    */         
/*    */         case '\b':
/*    */         
/*    */         case '/':
/*    */         
/*    */         case '\\':
/*    */         
/*    */         case '"':
/*    */           break;
/*    */       } 
/*    */       return "\\\"";
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\JSONFormat$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */