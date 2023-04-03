/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.collection.IndexedSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0213A!\001\002\003\017\ty1+Z9DQ\006\0248+Z9vK:\034WM\003\002\004\t\0059!/\0368uS6,'\"A\003\002\013M\034\027\r\\1\004\001M\031\001\001\003\t\021\005%qQ\"\001\006\013\005-a\021\001\0027b]\036T\021!D\001\005U\0064\030-\003\002\020\025\t1qJ\0316fGR\004\"!C\t\n\005IQ!\001D\"iCJ\034V-];f]\016,\007\002\003\013\001\005\013\007I\021A\013\002\005a\034X#\001\f\021\007]QB$D\001\031\025\tIB!\001\006d_2dWm\031;j_:L!a\007\r\003\025%sG-\032=fIN+\027\017\005\002\036=5\tA!\003\002 \t\t!1\t[1s\021!\t\003A!A!\002\0231\022a\001=tA!)1\005\001C\001I\0051A(\0338jiz\"\"!J\024\021\005\031\002Q\"\001\002\t\013Q\021\003\031\001\f\t\013%\002A\021\001\026\002\r1,gn\032;i)\005Y\003CA\017-\023\tiCAA\002J]RDQa\f\001\005\002A\naa\0315be\006#HC\001\0172\021\025\021d\0061\001,\003\025Ig\016Z3y\021\025!\004\001\"\0016\003-\031XOY*fcV,gnY3\025\007A1\004\bC\0038g\001\0071&A\003ti\006\024H\017C\003:g\001\0071&A\002f]\022DQa\017\001\005Bq\n\001\002^8TiJLgn\032\013\002{A\021a(\021\b\003;}J!\001\021\003\002\rA\023X\rZ3g\023\t\0215I\001\004TiJLgn\032\006\003\001\022\001")
/*    */ public final class SeqCharSequence implements CharSequence {
/*    */   private final IndexedSeq<Object> xs;
/*    */   
/*    */   public IndexedSeq<Object> xs() {
/* 14 */     return this.xs;
/*    */   }
/*    */   
/*    */   public SeqCharSequence(IndexedSeq<Object> xs) {}
/*    */   
/*    */   public int length() {
/* 15 */     return xs().length();
/*    */   }
/*    */   
/*    */   public char charAt(int index) {
/* 16 */     return BoxesRunTime.unboxToChar(xs().apply(index));
/*    */   }
/*    */   
/*    */   public CharSequence subSequence(int start, int end) {
/* 17 */     return new SeqCharSequence((IndexedSeq<Object>)xs().slice(start, end));
/*    */   }
/*    */   
/*    */   public String toString() {
/* 18 */     return xs().mkString("");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\SeqCharSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */