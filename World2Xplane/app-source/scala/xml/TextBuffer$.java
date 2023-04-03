/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class TextBuffer$ {
/*    */   public static final TextBuffer$ MODULE$;
/*    */   
/*    */   private TextBuffer$() {
/* 14 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public TextBuffer fromString(String str) {
/* 15 */     return (new TextBuffer()).append((Seq<Object>)scala.Predef$.MODULE$.wrapString(str));
/*    */   }
/*    */   
/*    */   public class TextBuffer$$anonfun$append$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public TextBuffer$$anonfun$append$1(TextBuffer $outer) {}
/*    */     
/*    */     public final Object apply(char c) {
/* 31 */       return Utility$.MODULE$.isSpace(c) ? (
/* 32 */         (!this.$outer.sb().isEmpty() && Utility$.MODULE$.isSpace(BoxesRunTime.unboxToChar(this.$outer.sb().last()))) ? BoxedUnit.UNIT : this.$outer.sb().append(' ')) : this.$outer.sb().append(c);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\TextBuffer$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */