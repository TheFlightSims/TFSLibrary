/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import java.io.Reader;
/*    */ 
/*    */ public final class StreamReader$ {
/*    */   public static final StreamReader$ MODULE$;
/*    */   
/*    */   private final char EofCh;
/*    */   
/*    */   private StreamReader$() {
/* 18 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public final char EofCh() {
/* 19 */     return '\032';
/*    */   }
/*    */   
/*    */   public StreamReader apply(Reader in) {
/* 27 */     return new StreamReader(scala.collection.immutable.PagedSeq$.MODULE$.fromReader(in), 0, 1);
/*    */   }
/*    */   
/*    */   public class StreamReader$$anon$1 implements Position {
/*    */     public String toString() {
/* 70 */       return Position$class.toString(this);
/*    */     }
/*    */     
/*    */     public String longString() {
/* 70 */       return Position$class.longString(this);
/*    */     }
/*    */     
/*    */     public boolean $less(Position that) {
/* 70 */       return Position$class.$less(this, that);
/*    */     }
/*    */     
/*    */     public StreamReader$$anon$1(StreamReader $outer) {
/* 70 */       Position$class.$init$(this);
/*    */     }
/*    */     
/*    */     public int line() {
/* 71 */       return this.$outer.scala$util$parsing$input$StreamReader$$lnum;
/*    */     }
/*    */     
/*    */     public int column() {
/* 72 */       return this.$outer.scala$util$parsing$input$StreamReader$$super$offset() + 1;
/*    */     }
/*    */     
/*    */     public String lineContents() {
/* 73 */       return this.$outer.scala$util$parsing$input$StreamReader$$seq.slice(0, this.$outer.scala$util$parsing$input$StreamReader$$nextEol()).toString();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\StreamReader$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */