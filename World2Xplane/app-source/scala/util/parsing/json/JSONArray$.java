/*     */ package scala.util.parsing.json;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class JSONArray$ extends AbstractFunction1<List<Object>, JSONArray> implements Serializable {
/*     */   public static final JSONArray$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 103 */     return "JSONArray";
/*     */   }
/*     */   
/*     */   public JSONArray apply(List<Object> list) {
/* 103 */     return new JSONArray(list);
/*     */   }
/*     */   
/*     */   public Option<List<Object>> unapply(JSONArray x$0) {
/* 103 */     return (x$0 == null) ? (Option<List<Object>>)scala.None$.MODULE$ : (Option<List<Object>>)new Some(x$0.list());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 103 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private JSONArray$() {
/* 103 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\JSONArray$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */