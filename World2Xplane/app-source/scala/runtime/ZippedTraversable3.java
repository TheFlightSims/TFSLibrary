/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function$;
/*    */ import scala.Function1;
/*    */ import scala.Function3;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.AbstractTraversable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001I3q!\001\002\021\002G\005qA\001\n[SB\004X\r\032+sCZ,'o]1cY\026\034$BA\002\005\003\035\021XO\034;j[\026T\021!B\001\006g\016\fG.Y\002\001+\021A1DI\023\024\005\001I\001C\001\006\f\033\005!\021B\001\007\005\005\r\te.\037\005\006\035\0011\taD\001\bM>\024X-Y2i+\t\001\002\006\006\002\022)A\021!BE\005\003'\021\021A!\0268ji\")Q#\004a\001-\005\ta\r\005\004\013/e\tCeJ\005\0031\021\021\021BR;oGRLwN\\\032\021\005iYB\002\001\003\0079\001!)\031A\017\003\007\025c\027'\005\002\037\023A\021!bH\005\003A\021\021qAT8uQ&tw\r\005\002\033E\02111\005\001CC\002u\0211!\02273!\tQR\005\002\004'\001\021\025\r!\b\002\004\0132\034\004C\001\016)\t\025ISB1\001\036\005\005)v!B\026\003\021\003a\023A\005.jaB,G\r\026:bm\026\0248/\0312mKN\002\"!\f\030\016\003\t1Q!\001\002\t\002=\032\"A\f\031\021\005)\t\024B\001\032\005\005\031\te.\037*fM\")AG\fC\001k\0051A(\0338jiz\"\022\001\f\005\006o9\"\031\001O\001 u&\004\b/\0323Ue\0064XM]:bE2,7\007V8Ue\0064XM]:bE2,W\003B\035K\031:#\"AO(\021\007m\032eI\004\002=\003:\021Q\bQ\007\002})\021qHB\001\007yI|w\016\036 \n\003\025I!A\021\003\002\017A\f7m[1hK&\021A)\022\002\f)J\fg/\032:tC\ndWM\003\002C\tA)!bR%L\033&\021\001\n\002\002\007)V\004H.Z\032\021\005iQE!\002\0177\005\004i\002C\001\016M\t\025\031cG1\001\036!\tQb\nB\003'm\t\007Q\004C\003Qm\001\007\021+\001\002{uB)Q\006A%L\033\002")
/*    */ public interface ZippedTraversable3<El1, El2, El3> {
/*    */   <U> void foreach(Function3<El1, El2, El3, U> paramFunction3);
/*    */   
/*    */   public static class ZippedTraversable3$$anon$1 extends AbstractTraversable<Tuple3<El1, El2, El3>> {
/*    */     private final ZippedTraversable3 zz$1;
/*    */     
/*    */     public ZippedTraversable3$$anon$1(ZippedTraversable3 zz$1) {}
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 22 */       Function$ function$ = Function$.MODULE$;
/* 22 */       this.zz$1.foreach((Function3)new Object(f));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\ZippedTraversable3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */