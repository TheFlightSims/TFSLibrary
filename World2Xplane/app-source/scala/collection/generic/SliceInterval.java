/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.math.package$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.RichInt$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001i2Q!\001\002\001\t!\021Qb\0257jG\026Le\016^3sm\006d'BA\002\005\003\0359WM\\3sS\016T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b'\t\001\021\002\005\002\013\0275\ta!\003\002\r\r\t1\021I\\=SK\032D\001B\004\001\003\006\004%\t\001E\001\005MJ|Wn\001\001\026\003E\001\"A\003\n\n\005M1!aA%oi\"AQ\003\001B\001B\003%\021#A\003ge>l\007\005\003\005\030\001\t\025\r\021\"\001\021\003\025)h\016^5m\021!I\002A!A!\002\023\t\022AB;oi&d\007\005C\003\034\001\021%A$\001\004=S:LGO\020\013\004;}\001\003C\001\020\001\033\005\021\001\"\002\b\033\001\004\t\002\"B\f\033\001\004\t\002\"\002\022\001\t\003\001\022!B<jIRD\007\"\002\023\001\t\003)\023a\003:fG\006d7-\0367bi\026$2!\b\024)\021\02593\0051\001\022\003\025yfM]8n\021\025I3\0051\001\022\003\031yVO\034;jY\")A\005\001C\001WQ\021Q\004\f\005\006[)\002\r!H\001\tS:$XM\035<bY\036)qF\001E\001a\005i1\013\\5dK&sG/\032:wC2\004\"AH\031\007\013\005\021\001\022\001\032\024\005EJ\001\"B\0162\t\003!D#\001\031\t\013Y\nD\021A\034\002\013\005\004\b\017\\=\025\007uA\024\bC\003\017k\001\007\021\003C\003\030k\001\007\021\003")
/*    */ public class SliceInterval {
/*    */   private final int from;
/*    */   
/*    */   private final int until;
/*    */   
/*    */   public int from() {
/* 16 */     return this.from;
/*    */   }
/*    */   
/*    */   public int until() {
/* 16 */     return this.until;
/*    */   }
/*    */   
/*    */   public SliceInterval(int from, int until) {}
/*    */   
/*    */   public int width() {
/* 22 */     return until() - from();
/*    */   }
/*    */   
/*    */   public SliceInterval recalculate(int _from, int _until) {
/* 34 */     Predef$ predef$ = Predef$.MODULE$;
/* 34 */     int lo = RichInt$.MODULE$.max$extension(_from, 0);
/* 35 */     int elems = package$.MODULE$.min(_until - lo, width());
/* 36 */     int start = from() + lo;
/* 38 */     return (elems <= 0) ? new SliceInterval(from(), from()) : 
/* 39 */       new SliceInterval(start, start + elems);
/*    */   }
/*    */   
/*    */   public SliceInterval recalculate(SliceInterval interval) {
/* 42 */     return recalculate(interval.from(), interval.until());
/*    */   }
/*    */   
/*    */   public static SliceInterval apply(int paramInt1, int paramInt2) {
/*    */     return SliceInterval$.MODULE$.apply(paramInt1, paramInt2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\SliceInterval.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */