/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple4;
/*    */ import scala.collection.Seq;
/*    */ 
/*    */ public final class PrefixedAttribute$ implements Serializable {
/*    */   public static final PrefixedAttribute$ MODULE$;
/*    */   
/*    */   private PrefixedAttribute$() {
/* 58 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 58 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public Some<Tuple4<String, String, Seq<Node>, MetaData>> unapply(PrefixedAttribute x) {
/* 59 */     return new Some(new Tuple4(x.pre(), x.key(), x.value(), x.next()));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\PrefixedAttribute$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */