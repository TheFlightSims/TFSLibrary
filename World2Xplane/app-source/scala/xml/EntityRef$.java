/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class EntityRef$ extends AbstractFunction1<String, EntityRef> implements Serializable {
/*    */   public static final EntityRef$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 17 */     return "EntityRef";
/*    */   }
/*    */   
/*    */   public EntityRef apply(String entityName) {
/* 17 */     return new EntityRef(entityName);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(EntityRef x$0) {
/* 17 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.entityName());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 17 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private EntityRef$() {
/* 17 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public class EntityRef$$anonfun$text$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(StringBuilder sb) {
/* 28 */       this.$outer.buildString(sb);
/*    */     }
/*    */     
/*    */     public EntityRef$$anonfun$text$1(EntityRef $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\EntityRef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */