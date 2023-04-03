/*     */ package org.jdom;
/*     */ 
/*     */ public class CDATA extends Text {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: CDATA.java,v $ $Revision: 1.32 $ $Date: 2007/11/10 05:28:58 $ $Name:  $";
/*     */   
/*     */   protected CDATA() {}
/*     */   
/*     */   public CDATA(String string) {
/*  96 */     setText(string);
/*     */   }
/*     */   
/*     */   public Text setText(String str) {
/* 114 */     if (str == null || "".equals(str)) {
/* 115 */       this.value = "";
/* 116 */       return this;
/*     */     } 
/* 119 */     String reason = Verifier.checkCDATASection(str);
/* 120 */     if (reason != null)
/* 121 */       throw new IllegalDataException(str, "CDATA section", reason); 
/* 124 */     this.value = str;
/* 126 */     return this;
/*     */   }
/*     */   
/*     */   public void append(String str) {
/*     */     String tmpValue;
/* 144 */     if (str == null || "".equals(str))
/*     */       return; 
/* 151 */     if (this.value == "") {
/* 152 */       tmpValue = str;
/*     */     } else {
/* 154 */       tmpValue = this.value + str;
/*     */     } 
/* 163 */     String reason = Verifier.checkCDATASection(tmpValue);
/* 164 */     if (reason != null)
/* 165 */       throw new IllegalDataException(str, "CDATA section", reason); 
/* 168 */     this.value = tmpValue;
/*     */   }
/*     */   
/*     */   public void append(Text text) {
/* 182 */     if (text == null)
/*     */       return; 
/* 185 */     append(text.getText());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 199 */     return (new StringBuffer(64)).append("[CDATA: ").append(getText()).append("]").toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\CDATA.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */