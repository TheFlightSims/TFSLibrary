/*    */ package scala.sys;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001-3A!\001\002\001\017\t\0212\013[;uI><h\016S8pWRC'/Z1e\025\t\031A!A\002tsNT\021!B\001\006g\016\fG.Y\002\001'\t\001\001\002\005\002\n\0355\t!B\003\002\f\031\005!A.\0318h\025\005i\021\001\0026bm\006L!a\004\006\003\rQC'/Z1e\021!\t\002A!A!\002\023\021\022\001\0028b[\026\004\"aE\f\017\005Q)R\"\001\003\n\005Y!\021A\002)sK\022,g-\003\002\0313\t11\013\036:j]\036T!A\006\003\t\013m\001A\021\002\017\002\rqJg.\033;?)\tir\004\005\002\037\0015\t!\001C\003\0225\001\007!\003C\003\"\001\021\005!%\001\004sK6|g/\032\013\002GA\021A\003J\005\003K\021\021qAQ8pY\026\fgnB\003(\005!\005\001&\001\nTQV$Hm\\<o\021>|7\016\0265sK\006$\007C\001\020*\r\025\t!\001#\001+'\tI3\006\005\002\025Y%\021Q\006\002\002\007\003:L(+\0324\t\013mIC\021A\030\025\003!Bq!M\025A\002\023%!'A\007i_>\\g*Y7f\007>,h\016^\013\002gA\021A\003N\005\003k\021\0211!\0238u\021\0359\024\0061A\005\na\n\021\003[8pW:\013W.Z\"pk:$x\fJ3r)\tID\b\005\002\025u%\0211\b\002\002\005+:LG\017C\004>m\005\005\t\031A\032\002\007a$\023\007\003\004@S\001\006KaM\001\017Q>|7NT1nK\016{WO\034;!\021\025\t\025\006\"\003C\003!Awn\\6OC6,G#\001\n\t\013\021KC\021A#\002\013\005\004\b\017\\=\025\005u1\005BB$D\t\003\007\001*\001\003c_\022L\bc\001\013Js%\021!\n\002\002\ty\tLh.Y7f}\001")
/*    */ public class ShutdownHookThread extends Thread {
/*    */   public ShutdownHookThread(String name) {
/* 18 */     super(name);
/*    */   }
/*    */   
/*    */   public boolean remove() {
/* 19 */     return package$.MODULE$.runtime().removeShutdownHook(this);
/*    */   }
/*    */   
/*    */   public static ShutdownHookThread apply(Function0<BoxedUnit> paramFunction0) {
/*    */     return ShutdownHookThread$.MODULE$.apply(paramFunction0);
/*    */   }
/*    */   
/*    */   public static class ShutdownHookThread$$anon$1 extends ShutdownHookThread {
/*    */     private final Function0 body$1;
/*    */     
/*    */     public ShutdownHookThread$$anon$1(Function0 body$1) {
/* 32 */       super(ShutdownHookThread$.MODULE$.scala$sys$ShutdownHookThread$$hookName());
/*    */     }
/*    */     
/*    */     public void run() {
/* 33 */       this.body$1.apply$mcV$sp();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\ShutdownHookThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */