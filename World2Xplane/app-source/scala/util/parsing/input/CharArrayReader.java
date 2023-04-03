/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001i:Q!\001\002\t\002-\tqb\0215be\006\023(/Y=SK\006$WM\035\006\003\007\021\tQ!\0338qkRT!!\002\004\002\017A\f'o]5oO*\021q\001C\001\005kRLGNC\001\n\003\025\0318-\0317b\007\001\001\"\001D\007\016\003\t1QA\004\002\t\002=\021qb\0215be\006\023(/Y=SK\006$WM]\n\003\033A\001\"!\005\n\016\003!I!a\005\005\003\r\005s\027PU3g\021\025)R\002\"\001\027\003\031a\024N\\5u}Q\t1\002C\004\031\033\t\007IQA\r\002\013\025{gm\0215\026\003iy\021a\007\017\0025!1Q$\004Q\001\016i\ta!R8g\007\"\004c\001\002\b\003\001}\031\"A\b\021\021\0051\t\023B\001\022\003\005I\031\005.\031:TKF,XM\\2f%\026\fG-\032:\t\021\021r\"\021!Q\001\n\025\nQa\0315beN\0042!\005\024)\023\t9\003BA\003BeJ\f\027\020\005\002\022S%\021!\006\003\002\005\007\"\f'\017C\005-=\t\005\t\025!\003.a\005)\021N\0343fqB\021\021CL\005\003_!\0211!\0238u\023\t\t\024%\001\004pM\032\034X\r\036\005\006+y!\ta\r\013\004iU2\004C\001\007\037\021\025!#\0071\001&\021\025a#\0071\001.\021\025)b\004\"\0019)\t!\024\bC\003%o\001\007Q\005")
/*    */ public class CharArrayReader extends CharSequenceReader {
/*    */   public static char EofCh() {
/*    */     return CharArrayReader$.MODULE$.EofCh();
/*    */   }
/*    */   
/*    */   public CharArrayReader(char[] chars, int index) {
/* 30 */     super(Predef$.MODULE$.arrayToCharSequence(chars), index);
/*    */   }
/*    */   
/*    */   public CharArrayReader(char[] chars) {
/* 32 */     this(chars, 0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\CharArrayReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */