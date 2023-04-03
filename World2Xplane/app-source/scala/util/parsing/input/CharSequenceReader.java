/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001i;Q!\001\002\t\002-\t!c\0215beN+\027/^3oG\026\024V-\0313fe*\0211\001B\001\006S:\004X\017\036\006\003\013\031\tq\001]1sg&twM\003\002\b\021\005!Q\017^5m\025\005I\021!B:dC2\f7\001\001\t\003\0315i\021A\001\004\006\035\tA\ta\004\002\023\007\"\f'oU3rk\026t7-\032*fC\022,'o\005\002\016!A\021\021CE\007\002\021%\0211\003\003\002\007\003:L(+\0324\t\013UiA\021\001\f\002\rqJg.\033;?)\005Y\001b\002\r\016\005\004%)!G\001\006\013>47\t[\013\0025=\t1\004H\001\033\021\031iR\002)A\0075\0051Qi\0344DQ\0022AA\004\002\001?M\021a\004\t\t\004\031\005\032\023B\001\022\003\005\031\021V-\0313feB\021\021\003J\005\003K!\021Aa\0215be\"AqE\bBC\002\023\005\003&\001\004t_V\0248-Z\013\002SA\021!fL\007\002W)\021A&L\001\005Y\006twMC\001/\003\021Q\027M^1\n\005AZ#\001D\"iCJ\034V-];f]\016,\007\002\003\032\037\005\003\005\013\021B\025\002\017M|WO]2fA!AAG\bBC\002\023\005S'\001\004pM\032\034X\r^\013\002mA\021\021cN\005\003q!\0211!\0238u\021!QdD!A!\002\0231\024aB8gMN,G\017\t\005\006+y!\t\001\020\013\004{yz\004C\001\007\037\021\02593\b1\001*\021\025!4\b1\0017\021\025)b\004\"\001B)\ti$\tC\003(\001\002\007\021\006C\003E=\021\005Q)A\003gSJ\034H/F\001$\021\0259e\004\"\001I\003\021\021Xm\035;\026\003uBQA\023\020\005\002-\0131\001]8t+\005a\005C\001\007N\023\tq%A\001\005Q_NLG/[8o\021\025\001f\004\"\001R\003\025\tG/\0228e+\005\021\006CA\tT\023\t!\006BA\004C_>dW-\0318\t\013YsB\021I,\002\t\021\024x\016\035\013\003{aCQ!W+A\002Y\n\021A\034")
/*    */ public class CharSequenceReader extends Reader<Object> {
/*    */   private final CharSequence source;
/*    */   
/*    */   private final int offset;
/*    */   
/*    */   public CharSequence source() {
/* 28 */     return this.source;
/*    */   }
/*    */   
/*    */   public CharSequenceReader(CharSequence source, int offset) {}
/*    */   
/*    */   public int offset() {
/* 29 */     return this.offset;
/*    */   }
/*    */   
/*    */   public CharSequenceReader(CharSequence source) {
/* 35 */     this(source, 0);
/*    */   }
/*    */   
/*    */   public char first() {
/* 40 */     return (offset() < source().length()) ? source().charAt(offset()) : '\032';
/*    */   }
/*    */   
/*    */   public CharSequenceReader rest() {
/* 48 */     return (offset() < source().length()) ? new CharSequenceReader(source(), offset() + 1) : 
/* 49 */       this;
/*    */   }
/*    */   
/*    */   public Position pos() {
/* 53 */     return new OffsetPosition(source(), offset());
/*    */   }
/*    */   
/*    */   public boolean atEnd() {
/* 58 */     return (offset() >= source().length());
/*    */   }
/*    */   
/*    */   public CharSequenceReader drop(int n) {
/* 64 */     return new CharSequenceReader(source(), offset() + n);
/*    */   }
/*    */   
/*    */   public static char EofCh() {
/*    */     return CharSequenceReader$.MODULE$.EofCh();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\CharSequenceReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */