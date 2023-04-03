/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.io.Source;
/*    */ import scala.xml.NodeSeq;
/*    */ 
/*    */ public final class XhtmlParser$ {
/*    */   public static final XhtmlParser$ MODULE$;
/*    */   
/*    */   private XhtmlParser$() {
/* 28 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public NodeSeq apply(Source source) {
/* 29 */     return (NodeSeq)((MarkupParser)(new XhtmlParser(source)).initialize()).document();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\XhtmlParser$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */