/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import scala.collection.immutable.PagedSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001};Q!\001\002\t\002-\tAb\025;sK\006l'+Z1eKJT!a\001\003\002\013%t\007/\036;\013\005\0251\021a\0029beNLgn\032\006\003\017!\tA!\036;jY*\t\021\"A\003tG\006d\027m\001\001\021\0051iQ\"\001\002\007\0139\021\001\022A\b\003\031M#(/Z1n%\026\fG-\032:\024\0055\001\002CA\t\023\033\005A\021BA\n\t\005\031\te.\037*fM\")Q#\004C\001-\0051A(\0338jiz\"\022a\003\005\b15\021\r\021\"\002\032\003\025)uNZ\"i+\005Qr\"A\016\035\003iAa!H\007!\002\033Q\022AB#pM\016C\007\005C\003 \033\021\005\001%A\003baBd\027\020\006\002\"+B\021AB\t\004\005\035\t\0012e\005\002#IA\021A\"J\005\003M\t\021a\002U1hK\022\034V-\035*fC\022,'\017\003\005)E\t\005\t\025!\003*\003\r\031X-\035\t\004U=\nT\"A\026\013\0051j\023!C5n[V$\030M\0317f\025\tq\003\"\001\006d_2dWm\031;j_:L!\001M\026\003\021A\013w-\0323TKF\004\"!\005\032\n\005MB!\001B\"iCJD\021\"\016\022\003\002\003\006IAN\035\002\007=4g\r\005\002\022o%\021\001\b\003\002\004\023:$\030B\001\036&\003\031ygMZ:fi\"AAH\tB\001B\003%a'\001\003m]Vl\007\"B\013#\t\003qD\003B\021@\001\006CQ\001K\037A\002%BQ!N\037A\002YBQ\001P\037A\002YBQa\021\022\005B\021\013AA]3tiV\t\021\005C\003GE\021%q)A\004oKb$Xi\0347\026\003YBQ!\023\022\005B)\013A\001\032:paR\021\021e\023\005\006\031\"\003\rAN\001\002]\")aJ\tC!\037\006\031\001o\\:\026\003A\003\"\001D)\n\005I\023!\001\003)pg&$\030n\0348\t\023Q\023\023\021!A\005\n\035K\024\001D:va\026\024He\0344gg\026$\b\"\002,\037\001\0049\026AA5o!\tAV,D\001Z\025\tQ6,\001\002j_*\tA,\001\003kCZ\f\027B\0010Z\005\031\021V-\0313fe\002")
/*    */ public class StreamReader extends PagedSeqReader {
/*    */   public final PagedSeq<Object> scala$util$parsing$input$StreamReader$$seq;
/*    */   
/*    */   public final int scala$util$parsing$input$StreamReader$$lnum;
/*    */   
/*    */   public StreamReader(PagedSeq<Object> seq, int off, int lnum) {
/* 47 */     super(seq, off);
/*    */   }
/*    */   
/*    */   public StreamReader rest() {
/* 51 */     return (offset() == this.scala$util$parsing$input$StreamReader$$seq.length()) ? this : (
/* 52 */       (BoxesRunTime.unboxToChar(this.scala$util$parsing$input$StreamReader$$seq.apply(offset())) == '\n') ? 
/* 53 */       new StreamReader(this.scala$util$parsing$input$StreamReader$$seq.slice(offset() + 1), 0, this.scala$util$parsing$input$StreamReader$$lnum + 1) : 
/* 54 */       new StreamReader(this.scala$util$parsing$input$StreamReader$$seq, offset() + 1, this.scala$util$parsing$input$StreamReader$$lnum));
/*    */   }
/*    */   
/*    */   public int scala$util$parsing$input$StreamReader$$nextEol() {
/* 57 */     int i = offset();
/* 58 */     for (; i < this.scala$util$parsing$input$StreamReader$$seq.length() && BoxesRunTime.unboxToChar(this.scala$util$parsing$input$StreamReader$$seq.apply(i)) != '\n' && BoxesRunTime.unboxToChar(this.scala$util$parsing$input$StreamReader$$seq.apply(i)) != '\032'; i++);
/* 59 */     return i;
/*    */   }
/*    */   
/*    */   public StreamReader drop(int n) {
/* 63 */     int eolPos = scala$util$parsing$input$StreamReader$$nextEol();
/* 64 */     return (eolPos < offset() + n && eolPos < this.scala$util$parsing$input$StreamReader$$seq.length()) ? (
/* 65 */       new StreamReader(this.scala$util$parsing$input$StreamReader$$seq.slice(eolPos + 1), 0, this.scala$util$parsing$input$StreamReader$$lnum + 1)).drop(offset() + n - eolPos + 1) : 
/*    */       
/* 67 */       new StreamReader(this.scala$util$parsing$input$StreamReader$$seq, offset() + n, this.scala$util$parsing$input$StreamReader$$lnum);
/*    */   }
/*    */   
/*    */   public Position pos() {
/* 70 */     return new StreamReader$$anon$1(this);
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
/*    */   
/*    */   public int scala$util$parsing$input$StreamReader$$super$offset() {
/*    */     return offset();
/*    */   }
/*    */   
/*    */   public static StreamReader apply(Reader paramReader) {
/*    */     return StreamReader$.MODULE$.apply(paramReader);
/*    */   }
/*    */   
/*    */   public static char EofCh() {
/*    */     return StreamReader$.MODULE$.EofCh();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\StreamReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */