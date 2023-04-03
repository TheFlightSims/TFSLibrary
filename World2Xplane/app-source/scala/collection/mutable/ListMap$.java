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
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class ListMap$ extends MutableMapFactory<ListMap> implements Serializable {
/*    */   public static final ListMap$ MODULE$;
/*    */   
/*    */   public class ListMap$$anonfun$get$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object key$1;
/*    */     
/*    */     public final boolean apply(Tuple2 x$1) {
/* 49 */       Object object2 = this.key$1;
/*    */       Object object1;
/* 49 */       return (((object1 = x$1._1()) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))));
/*    */     }
/*    */     
/*    */     public ListMap$$anonfun$get$1(ListMap $outer, Object key$1) {}
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 71 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ListMap$() {
/* 71 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A, B> CanBuildFrom<ListMap<?, ?>, Tuple2<A, B>, ListMap<A, B>> canBuildFrom() {
/* 72 */     return (CanBuildFrom<ListMap<?, ?>, Tuple2<A, B>, ListMap<A, B>>)new GenMapFactory.MapCanBuildFrom((GenMapFactory)this);
/*    */   }
/*    */   
/*    */   public <A, B> ListMap<A, B> empty() {
/* 73 */     return new ListMap<A, B>();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ListMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */