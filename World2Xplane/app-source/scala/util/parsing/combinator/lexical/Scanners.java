/*    */ package scala.util.parsing.combinator.lexical;
/*    */ 
/*    */ import scala.MatchError;
/*    */ import scala.Tuple3;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.input.CharArrayReader;
/*    */ import scala.util.parsing.input.Position;
/*    */ import scala.util.parsing.input.Reader;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\025baB\001\003!\003\r\t!\004\002\t'\016\fgN\\3sg*\0211\001B\001\bY\026D\030nY1m\025\t)a!\001\006d_6\024\027N\\1u_JT!a\002\005\002\017A\f'o]5oO*\021\021BC\001\005kRLGNC\001\f\003\025\0318-\0317b\007\001\0312\001\001\b\023!\ty\001#D\001\013\023\t\t\"B\001\004B]f\024VM\032\t\003'Qi\021\001B\005\003+\021\021q\001U1sg\026\0248\017C\003\030\001\021\005\001$\001\004%S:LG\017\n\013\0023A\021qBG\005\0037)\021A!\0268ji\026!Q\004\001\001\037\005\021)E.Z7\021\005=y\022B\001\021\013\005\021\031\005.\031:\005\013\t\002!\021A\022\003\013Q{7.\0328\022\005\021:\003CA\b&\023\t1#BA\004O_RD\027N\\4\021\005=A\023BA\025\013\005\r\te.\037\005\006W\0011\t\001L\001\013KJ\024xN\035+pW\026tGCA\0270!\tq\023%D\001\001\021\025\001$\0061\0012\003\ri7o\032\t\003eUr!aD\032\n\005QR\021A\002)sK\022,g-\003\0027o\t11\013\036:j]\036T!\001\016\006\t\013e\002a\021\001\036\002\013Q|7.\0328\026\003m\0022A\f\037.\023\tiDC\001\004QCJ\034XM\035\005\006\0011\t\001Q\001\013o\"LG/Z:qC\016,W#A!\021\0079btE\002\003D\001\001!%aB*dC:tWM]\n\003\005\026\0032AR%.\033\0059%B\001%\007\003\025Ig\016];u\023\tQuI\001\004SK\006$WM\035\005\t\031\n\023\t\021)A\005\033\006\021\021N\034\t\004\r&s\002\"B(C\t\003\001\026A\002\037j]&$h\b\006\002R%B\021aF\021\005\006\031:\003\r!\024\005\006\037\n#\t\001\026\013\003#VCQ\001T*A\002EBqa\026\"\002B\003%\001,A\002yIE\002RaD-.7nK!A\027\006\003\rQ+\b\017\\34!\tqC,\003\002^)\t)\021J\0349vi\"9qL\021b\001\n\023\001\027a\001;pWV\tQ\006\003\004c\005\002\006I!L\001\005i>\\\007\005C\004e\005\n\007I\021B3\002\013I,7\017^\031\026\003mCaa\032\"!\002\023Y\026A\002:fgR\f\004\005C\004j\005\n\007I\021B3\002\013I,7\017\036\032\t\r-\024\005\025!\003\\\003\031\021Xm\035;3A!)QN\021C\005]\006!1o[5q)\tiu\016C\003MY\002\007Q\nC\003r\005\022\005#/\001\004t_V\0248-Z\013\002gB\021A/_\007\002k*\021ao^\001\005Y\006twMC\001y\003\021Q\027M^1\n\005i,(\001D\"iCJ\034V-];f]\016,\007\"\002?C\t\003j\030AB8gMN,G/F\001!\tyq0C\002\002\002)\0211!\0238u\021\031\t)A\021C\001A\006)a-\033:ti\"9\021\021\002\"\005\002\005-\021\001\002:fgR,\022!\025\005\b\003\037\021E\021AA\t\003\r\001xn]\013\003\003'\0012ARA\013\023\r\t9b\022\002\t!>\034\030\016^5p]\"9\0211\004\"\005\002\005u\021!B1u\013:$WCAA\020!\ry\021\021E\005\004\003GQ!a\002\"p_2,\027M\034")
/*    */ public interface Scanners extends Parsers {
/*    */   Object errorToken(String paramString);
/*    */   
/*    */   Parsers.Parser<Object> token();
/*    */   
/*    */   Parsers.Parser<Object> whitespace();
/*    */   
/*    */   public class Scanner extends Reader<Object> {
/*    */     private final Reader<Object> in;
/*    */     
/*    */     private final Tuple3<Object, Reader<Object>, Reader<Object>> x$1;
/*    */     
/*    */     private final Object tok;
/*    */     
/*    */     private final Reader<Object> rest1;
/*    */     
/*    */     private final Reader<Object> rest2;
/*    */     
/*    */     public Scanner(Scanners $outer, Reader<Object> in) {
/*    */       Tuple3 tuple31;
/* 47 */       Parsers.ParseResult parseResult = $outer.whitespace().apply(in);
/* 48 */       if (parseResult instanceof Parsers.Success) {
/* 48 */         Parsers.Success success = (Parsers.Success)parseResult;
/* 49 */         Parsers.ParseResult parseResult1 = $outer.token().apply(success.next());
/* 50 */         if (parseResult1 instanceof Parsers.Success)
/* 50 */           Parsers.Success success1 = (Parsers.Success)parseResult1; 
/* 51 */         if (parseResult1 instanceof Parsers.NoSuccess) {
/* 51 */           Parsers.NoSuccess noSuccess = (Parsers.NoSuccess)parseResult1;
/* 51 */           tuple31 = new Tuple3($outer.errorToken(noSuccess.msg()), noSuccess.next(), skip(noSuccess.next()));
/*    */         } else {
/*    */           throw new MatchError(parseResult1);
/*    */         } 
/*    */       } else {
/*    */         Tuple3 tuple3;
/* 53 */         if (parseResult instanceof Parsers.NoSuccess) {
/* 53 */           Parsers.NoSuccess noSuccess = (Parsers.NoSuccess)parseResult;
/* 53 */           tuple3 = new Tuple3($outer.errorToken(noSuccess.msg()), noSuccess.next(), skip(noSuccess.next()));
/*    */         } else {
/*    */           throw new MatchError(parseResult);
/*    */         } 
/*    */         if (tuple3 != null) {
/*    */           this.x$1 = new Tuple3(tuple3._1(), tuple3._2(), tuple3._3());
/*    */           this.tok = this.x$1._1();
/*    */           this.rest1 = (Reader<Object>)this.x$1._2();
/*    */           this.rest2 = (Reader<Object>)this.x$1._3();
/*    */           return;
/*    */         } 
/*    */       } 
/*    */       Tuple3 tuple32 = tuple31;
/*    */       if (tuple32 != null) {
/*    */         this.x$1 = new Tuple3(tuple32._1(), tuple32._2(), tuple32._3());
/*    */         this.tok = this.x$1._1();
/*    */         this.rest1 = (Reader<Object>)this.x$1._2();
/*    */         this.rest2 = (Reader<Object>)this.x$1._3();
/*    */         return;
/*    */       } 
/*    */     }
/*    */     
/*    */     public Scanner(String in) {
/*    */       this((Reader<Object>)new CharArrayReader(in.toCharArray()));
/*    */     }
/*    */     
/*    */     private Object tok() {
/*    */       return this.tok;
/*    */     }
/*    */     
/*    */     private Reader<Object> rest1() {
/*    */       return this.rest1;
/*    */     }
/*    */     
/*    */     private Reader<Object> rest2() {
/*    */       return this.rest2;
/*    */     }
/*    */     
/*    */     private Reader<Object> skip(Reader<Object> in) {
/* 55 */       return in.atEnd() ? in : in.rest();
/*    */     }
/*    */     
/*    */     public CharSequence source() {
/* 57 */       return this.in.source();
/*    */     }
/*    */     
/*    */     public int offset() {
/* 58 */       return this.in.offset();
/*    */     }
/*    */     
/*    */     public Object first() {
/* 59 */       return tok();
/*    */     }
/*    */     
/*    */     public Scanner rest() {
/* 60 */       return new Scanner(rest2());
/*    */     }
/*    */     
/*    */     public Position pos() {
/* 61 */       return rest1().pos();
/*    */     }
/*    */     
/*    */     public boolean atEnd() {
/* 62 */       if (!this.in.atEnd()) {
/*    */         boolean bool;
/* 62 */         Parsers.ParseResult parseResult = scala$util$parsing$combinator$lexical$Scanners$Scanner$$$outer().whitespace().apply(this.in);
/* 62 */         if (parseResult instanceof Parsers.Success) {
/* 62 */           Parsers.Success success = (Parsers.Success)parseResult;
/* 62 */           bool = success.next().atEnd();
/*    */         } else {
/* 62 */           bool = false;
/*    */         } 
/* 62 */         if (bool);
/* 62 */         return false;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\lexical\Scanners.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */