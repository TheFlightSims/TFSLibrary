/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Seq;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class Group$ extends AbstractFunction1<Seq<Node>, Group> implements Serializable {
/*    */   public static final Group$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 16 */     return "Group";
/*    */   }
/*    */   
/*    */   public Group apply(Seq<Node> nodes) {
/* 16 */     return new Group(nodes);
/*    */   }
/*    */   
/*    */   public Option<Seq<Node>> unapply(Group x$0) {
/* 16 */     return (x$0 == null) ? (Option<Seq<Node>>)scala.None$.MODULE$ : (Option<Seq<Node>>)new Some(x$0.nodes());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 16 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Group$() {
/* 16 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Group$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */