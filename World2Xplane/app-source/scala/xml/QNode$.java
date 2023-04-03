/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Some;
/*    */ import scala.Tuple4;
/*    */ import scala.collection.Seq;
/*    */ 
/*    */ public final class QNode$ {
/*    */   public static final QNode$ MODULE$;
/*    */   
/*    */   private QNode$() {
/* 17 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Some<Tuple4<String, String, MetaData, Seq<Node>>> unapplySeq(Node n) {
/* 18 */     return new Some(new Tuple4(n.scope().getURI(n.prefix()), n.label(), n.attributes(), n.child()));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\QNode$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */