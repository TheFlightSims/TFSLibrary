/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenMapFactory;
/*    */ import scala.collection.generic.MutableMapFactory;
/*    */ 
/*    */ public final class WeakHashMap$ extends MutableMapFactory<WeakHashMap> implements Serializable {
/*    */   public static final WeakHashMap$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 49 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private WeakHashMap$() {
/* 49 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A, B> CanBuildFrom<WeakHashMap<?, ?>, Tuple2<A, B>, WeakHashMap<A, B>> canBuildFrom() {
/* 50 */     return (CanBuildFrom<WeakHashMap<?, ?>, Tuple2<A, B>, WeakHashMap<A, B>>)new GenMapFactory.MapCanBuildFrom((GenMapFactory)this);
/*    */   }
/*    */   
/*    */   public <A, B> WeakHashMap<A, B> empty() {
/* 51 */     return new WeakHashMap<A, B>();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\WeakHashMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */