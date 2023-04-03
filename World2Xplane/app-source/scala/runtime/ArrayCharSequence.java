/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.math.package$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0353A!\001\002\003\017\t\t\022I\035:bs\016C\027M]*fcV,gnY3\013\005\r!\021a\002:v]RLW.\032\006\002\013\005)1oY1mC\016\0011c\001\001\t!A\021\021BD\007\002\025)\0211\002D\001\005Y\006twMC\001\016\003\021Q\027M^1\n\005=Q!AB(cU\026\034G\017\005\002\n#%\021!C\003\002\r\007\"\f'oU3rk\026t7-\032\005\t)\001\021)\031!C\001+\005\021\001p]\013\002-A\031q\003\007\016\016\003\021I!!\007\003\003\013\005\023(/Y=\021\005]Y\022B\001\017\005\005\021\031\005.\031:\t\021y\001!\021!Q\001\nY\t1\001_:!\021!\001\003A!A!\002\023\t\023!B:uCJ$\bCA\f#\023\t\031CAA\002J]RD\001\"\n\001\003\002\003\006I!I\001\004K:$\007\"B\024\001\t\003A\023A\002\037j]&$h\b\006\003*W1j\003C\001\026\001\033\005\021\001\"\002\013'\001\0041\002\"\002\021'\001\004\t\003\"B\023'\001\004\t\003\"B\030\001\t\003\001\024A\0027f]\036$\b\016F\001\"\021\025\021\004\001\"\0014\003\031\031\007.\031:BiR\021!\004\016\005\006kE\002\r!I\001\006S:$W\r\037\005\006o\001!\t\001O\001\fgV\0247+Z9vK:\034W\rF\002\021smBQA\017\034A\002\005\naa\035;beR\004\004\"\002\0377\001\004\t\023\001B3oIBBQA\020\001\005B}\n\001\002^8TiJLgn\032\013\002\001B\021\021\t\022\b\003/\tK!a\021\003\002\rA\023X\rZ3g\023\t)eI\001\004TiJLgn\032\006\003\007\022\001")
/*    */ public final class ArrayCharSequence implements CharSequence {
/*    */   private final char[] xs;
/*    */   
/*    */   private final int start;
/*    */   
/*    */   private final int end;
/*    */   
/*    */   public char[] xs() {
/* 21 */     return this.xs;
/*    */   }
/*    */   
/*    */   public ArrayCharSequence(char[] xs, int start, int end) {}
/*    */   
/*    */   public int length() {
/* 28 */     return package$.MODULE$.max(0, this.end - this.start);
/*    */   }
/*    */   
/*    */   public char charAt(int index) {
/* 30 */     if (0 <= index && index < length())
/* 30 */       return 
/* 31 */         xs()[this.start + index]; 
/* 32 */     throw new ArrayIndexOutOfBoundsException(index);
/*    */   }
/*    */   
/*    */   public CharSequence subSequence(int start0, int end0) {
/* 35 */     if (start0 < 0)
/* 35 */       throw new ArrayIndexOutOfBoundsException(start0); 
/* 36 */     if (end0 > length())
/* 36 */       throw new ArrayIndexOutOfBoundsException(end0); 
/* 39 */     int newlen = end0 - start0;
/* 40 */     int start1 = this.start + start0;
/* 41 */     return (end0 <= start0) ? new ArrayCharSequence(xs(), 0, 0) : new ArrayCharSequence(xs(), start1, start1 + newlen);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     return Predef$.MODULE$.charArrayOps((char[])Predef$.MODULE$.charArrayOps((char[])Predef$.MODULE$.charArrayOps(xs()).drop(this.start)).take(length())).mkString("");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\ArrayCharSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */