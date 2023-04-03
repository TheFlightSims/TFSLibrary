/*    */ package scala.util.parsing.json;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.MatchError;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class JSONObject$ extends AbstractFunction1<Map<String, Object>, JSONObject> implements Serializable {
/*    */   public static final JSONObject$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 94 */     return "JSONObject";
/*    */   }
/*    */   
/*    */   public JSONObject apply(Map<String, Object> obj) {
/* 94 */     return new JSONObject(obj);
/*    */   }
/*    */   
/*    */   public Option<Map<String, Object>> unapply(JSONObject x$0) {
/* 94 */     return (x$0 == null) ? (Option<Map<String, Object>>)scala.None$.MODULE$ : (Option<Map<String, Object>>)new Some(x$0.obj());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 94 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private JSONObject$() {
/* 94 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public class JSONObject$$anonfun$toString$1 extends AbstractFunction1<Tuple2<String, Object>, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function1 formatter$1;
/*    */     
/*    */     public final String apply(Tuple2 x0$4) {
/* 96 */       if (x0$4 != null)
/* 96 */         return (new StringBuilder()).append(this.formatter$1.apply(((String)x0$4._1()).toString())).append(" : ").append(this.formatter$1.apply(x0$4._2())).toString(); 
/* 96 */       throw new MatchError(x0$4);
/*    */     }
/*    */     
/*    */     public JSONObject$$anonfun$toString$1(JSONObject $outer, Function1 formatter$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\JSONObject$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */