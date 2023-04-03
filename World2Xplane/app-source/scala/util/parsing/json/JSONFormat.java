/*    */ package scala.util.parsing.json;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001I:Q!\001\002\t\002-\t!BS*P\035\032{'/\\1u\025\t\031A!\001\003kg>t'BA\003\007\003\035\001\030M]:j]\036T!a\002\005\002\tU$\030\016\034\006\002\023\005)1oY1mC\016\001\001C\001\007\016\033\005\021a!\002\b\003\021\003y!A\003&T\037:3uN]7biN\021Q\002\005\t\003#Ii\021\001C\005\003'!\021a!\0218z%\0264\007\"B\013\016\t\0031\022A\002\037j]&$h\bF\001\f\013\021AR\002A\r\003\035Y\013G.^3G_Jl\027\r\036;feB!\021C\007\017 \023\tY\002BA\005Gk:\034G/[8ocA\021\021#H\005\003=!\0211!\0218z!\t\0013E\004\002\022C%\021!\005C\001\007!J,G-\0324\n\005\021*#AB*ue&twM\003\002#\021!9q%\004b\001\n\003A\023\001\0053fM\006,H\016\036$pe6\fG\017^3s+\005I\003C\001\026\030\033\005i\001B\002\027\016A\003%\021&A\teK\032\fW\017\034;G_Jl\027\r\036;fe\002BQAL\007\005\002=\n1\"];pi\026\034FO]5oOR\021q\004\r\005\006c5\002\raH\001\002g\002")
/*    */ public final class JSONFormat {
/*    */   public static String quoteString(String paramString) {
/*    */     return JSONFormat$.MODULE$.quoteString(paramString);
/*    */   }
/*    */   
/*    */   public static Function1<Object, String> defaultFormatter() {
/*    */     return JSONFormat$.MODULE$.defaultFormatter();
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
/*    */   public static class JSONFormat$$anonfun$quoteString$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object apply(char x0$5) {
/*    */       Predef$ predef$;
/* 67 */       switch (x0$5) {
/*    */         default:
/* 84 */           predef$ = Predef$.MODULE$;
/* 84 */           return ((x0$5 >= Character.MIN_VALUE && x0$5 <= '\037') || (x0$5 >= '' && x0$5 <= '')) ? (new StringOps("\\u%04x")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(x0$5) })) : BoxesRunTime.boxToCharacter(x0$5);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\JSONFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */