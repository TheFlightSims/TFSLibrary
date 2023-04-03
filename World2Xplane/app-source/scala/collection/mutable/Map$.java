/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenMapFactory;
/*    */ import scala.collection.generic.MutableMapFactory;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class Map$ extends MutableMapFactory<Map> {
/*    */   public static final Map$ MODULE$;
/*    */   
/*    */   public class Map$$anonfun$withDefaultValue$1 extends AbstractFunction1<A, B> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object d$2;
/*    */     
/*    */     public final B apply(Object x) {
/* 48 */       return (B)this.d$2;
/*    */     }
/*    */     
/*    */     public Map$$anonfun$withDefaultValue$1(Map $outer, Object d$2) {}
/*    */   }
/*    */   
/*    */   private Map$() {
/* 68 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A, B> CanBuildFrom<Map<?, ?>, Tuple2<A, B>, Map<A, B>> canBuildFrom() {
/* 70 */     return (CanBuildFrom<Map<?, ?>, Tuple2<A, B>, Map<A, B>>)new GenMapFactory.MapCanBuildFrom((GenMapFactory)this);
/*    */   }
/*    */   
/*    */   public <A, B> Map<A, B> empty() {
/* 72 */     return new HashMap<A, B>();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Map$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */