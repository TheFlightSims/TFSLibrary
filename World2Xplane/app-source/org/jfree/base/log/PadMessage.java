/*    */ package org.jfree.base.log;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class PadMessage {
/*    */   private final Object text;
/*    */   
/*    */   private final int length;
/*    */   
/*    */   public PadMessage(Object message, int length) {
/* 73 */     this.text = message;
/* 74 */     this.length = length;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 83 */     StringBuffer b = new StringBuffer();
/* 84 */     b.append(this.text);
/* 85 */     if (b.length() < this.length) {
/* 86 */       char[] pad = new char[this.length - b.length()];
/* 87 */       Arrays.fill(pad, ' ');
/* 88 */       b.append(pad);
/*    */     } 
/* 90 */     return b.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\log\PadMessage.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */