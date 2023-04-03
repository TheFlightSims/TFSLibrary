/*     */ package org.jdom;
/*     */ 
/*     */ import org.jdom.output.XMLOutputter;
/*     */ 
/*     */ public class Comment extends Content {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: Comment.java,v $ $Revision: 1.33 $ $Date: 2007/11/10 05:28:58 $ $Name:  $";
/*     */   
/*     */   protected String text;
/*     */   
/*     */   protected Comment() {}
/*     */   
/*     */   public Comment(String text) {
/*  86 */     setText(text);
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  97 */     return this.text;
/*     */   }
/*     */   
/*     */   public String getText() {
/* 106 */     return this.text;
/*     */   }
/*     */   
/*     */   public Comment setText(String text) {
/*     */     String reason;
/* 119 */     if ((reason = Verifier.checkCommentData(text)) != null)
/* 120 */       throw new IllegalDataException(text, "comment", reason); 
/* 123 */     this.text = text;
/* 124 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 138 */     return "[Comment: " + (new XMLOutputter()).outputString(this) + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\Comment.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */