/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Seq;
/*    */ 
/*    */ public final class UnprefixedAttribute$ implements Serializable {
/*    */   public static final UnprefixedAttribute$ MODULE$;
/*    */   
/*    */   private UnprefixedAttribute$() {
/* 58 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 58 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public Some<Tuple3<String, Seq<Node>, MetaData>> unapply(UnprefixedAttribute x) {
/* 59 */     return new Some(new Tuple3(x.key(), x.value(), x.next()));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\UnprefixedAttribute$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */