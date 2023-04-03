/*    */ package akka.util;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.HashMap;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ 
/*    */ public final class WildcardTree$ implements Serializable {
/*    */   public static final WildcardTree$ MODULE$;
/*    */   
/*    */   private final WildcardTree<scala.runtime.Nothing$> empty;
/*    */   
/*    */   private Object readResolve() {
/* 10 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private WildcardTree$() {
/* 10 */     MODULE$ = this;
/* 11 */     this.empty = new WildcardTree<scala.runtime.Nothing$>((Option<scala.runtime.Nothing$>)$lessinit$greater$default$1(), (Map)$lessinit$greater$default$2());
/*    */   }
/*    */   
/*    */   private WildcardTree<scala.runtime.Nothing$> empty() {
/* 11 */     return this.empty;
/*    */   }
/*    */   
/*    */   public <T> WildcardTree<T> apply() {
/* 12 */     return (WildcardTree)empty();
/*    */   }
/*    */   
/*    */   public <T> scala.None$ apply$default$1() {
/* 14 */     return scala.None$.MODULE$;
/*    */   }
/*    */   
/*    */   public <T> HashMap<String, WildcardTree<T>> apply$default$2() {
/* 14 */     return (HashMap<String, WildcardTree<T>>)scala.collection.immutable.HashMap$.MODULE$.apply((Seq)scala.collection.immutable.Nil$.MODULE$);
/*    */   }
/*    */   
/*    */   public <T> WildcardTree<T> apply(Option<T> data, Map<String, WildcardTree<T>> children) {
/* 14 */     return new WildcardTree<T>(data, children);
/*    */   }
/*    */   
/*    */   public <T> Option<Tuple2<Option<T>, Map<String, WildcardTree<T>>>> unapply(WildcardTree x$0) {
/* 14 */     return (x$0 == null) ? (Option<Tuple2<Option<T>, Map<String, WildcardTree<T>>>>)scala.None$.MODULE$ : (Option<Tuple2<Option<T>, Map<String, WildcardTree<T>>>>)new Some(new Tuple2(x$0.data(), x$0.children()));
/*    */   }
/*    */   
/*    */   public <T> scala.None$ $lessinit$greater$default$1() {
/* 14 */     return scala.None$.MODULE$;
/*    */   }
/*    */   
/*    */   public <T> HashMap<String, WildcardTree<T>> $lessinit$greater$default$2() {
/* 14 */     return (HashMap<String, WildcardTree<T>>)scala.collection.immutable.HashMap$.MODULE$.apply((Seq)scala.collection.immutable.Nil$.MODULE$);
/*    */   }
/*    */   
/*    */   public class WildcardTree$$anonfun$1 extends AbstractFunction0<WildcardTree<T>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final WildcardTree<T> apply() {
/* 21 */       return WildcardTree$.MODULE$.apply();
/*    */     }
/*    */     
/*    */     public WildcardTree$$anonfun$1(WildcardTree $outer) {}
/*    */   }
/*    */   
/*    */   public class $anonfun$2 extends AbstractFunction0<Option<WildcardTree<T>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Option<WildcardTree<T>> apply() {
/* 27 */       return this.$outer.children().get("*");
/*    */     }
/*    */     
/*    */     public $anonfun$2(WildcardTree $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\WildcardTree$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */