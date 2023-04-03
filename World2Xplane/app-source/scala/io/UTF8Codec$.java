/*    */ package scala.io;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ 
/*    */ public final class UTF8Codec$ {
/*    */   public static final UTF8Codec$ MODULE$;
/*    */   
/*    */   private final int UNI_REPLACEMENT_CHAR;
/*    */   
/*    */   private final byte[] UNI_REPLACEMENT_BYTES;
/*    */   
/*    */   private UTF8Codec$() {
/* 17 */     MODULE$ = this;
/* 18 */     this.UNI_REPLACEMENT_CHAR = 65533;
/* 19 */     this.UNI_REPLACEMENT_BYTES = (byte[])scala.Array$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapByteArray(new byte[] { -17, -65, -67 }, ), scala.reflect.ClassTag$.MODULE$.Byte());
/*    */   }
/*    */   
/*    */   public final int UNI_REPLACEMENT_CHAR() {
/*    */     return this.UNI_REPLACEMENT_CHAR;
/*    */   }
/*    */   
/*    */   public final byte[] UNI_REPLACEMENT_BYTES() {
/* 19 */     return this.UNI_REPLACEMENT_BYTES;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\UTF8Codec$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */