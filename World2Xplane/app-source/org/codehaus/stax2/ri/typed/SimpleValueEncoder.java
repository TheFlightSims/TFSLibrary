/*    */ package org.codehaus.stax2.ri.typed;
/*    */ 
/*    */ import org.codehaus.stax2.typed.Base64Variant;
/*    */ 
/*    */ public class SimpleValueEncoder {
/* 17 */   protected final char[] mBuffer = new char[500];
/*    */   
/*    */   protected final ValueEncoderFactory mEncoderFactory;
/*    */   
/*    */   public SimpleValueEncoder() {
/* 23 */     this.mEncoderFactory = new ValueEncoderFactory();
/*    */   }
/*    */   
/*    */   public String encodeAsString(int[] value, int from, int length) {
/* 28 */     return encode(this.mEncoderFactory.getEncoder(value, from, length));
/*    */   }
/*    */   
/*    */   public String encodeAsString(long[] value, int from, int length) {
/* 33 */     return encode(this.mEncoderFactory.getEncoder(value, from, length));
/*    */   }
/*    */   
/*    */   public String encodeAsString(float[] value, int from, int length) {
/* 38 */     return encode(this.mEncoderFactory.getEncoder(value, from, length));
/*    */   }
/*    */   
/*    */   public String encodeAsString(double[] value, int from, int length) {
/* 43 */     return encode(this.mEncoderFactory.getEncoder(value, from, length));
/*    */   }
/*    */   
/*    */   public String encodeAsString(Base64Variant v, byte[] value, int from, int length) {
/* 48 */     return encode(this.mEncoderFactory.getEncoder(v, value, from, length));
/*    */   }
/*    */   
/*    */   protected String encode(AsciiValueEncoder enc) {
/* 60 */     int last = enc.encodeMore(this.mBuffer, 0, this.mBuffer.length);
/* 61 */     if (enc.isCompleted())
/* 62 */       return new String(this.mBuffer, 0, last); 
/* 65 */     StringBuffer sb = new StringBuffer(this.mBuffer.length << 1);
/* 66 */     sb.append(this.mBuffer, 0, last);
/*    */     while (true) {
/* 68 */       last = enc.encodeMore(this.mBuffer, 0, this.mBuffer.length);
/* 69 */       sb.append(this.mBuffer, 0, last);
/* 70 */       if (enc.isCompleted())
/* 71 */         return sb.toString(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\typed\SimpleValueEncoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */