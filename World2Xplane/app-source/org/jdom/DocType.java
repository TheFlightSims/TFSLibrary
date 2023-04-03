/*     */ package org.jdom;
/*     */ 
/*     */ import org.jdom.output.XMLOutputter;
/*     */ 
/*     */ public class DocType extends Content {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: DocType.java,v $ $Revision: 1.32 $ $Date: 2007/11/10 05:28:58 $ $Name:  $";
/*     */   
/*     */   protected String elementName;
/*     */   
/*     */   protected String publicID;
/*     */   
/*     */   protected String systemID;
/*     */   
/*     */   protected String internalSubset;
/*     */   
/*     */   protected DocType() {}
/*     */   
/*     */   public DocType(String elementName, String publicID, String systemID) {
/* 111 */     setElementName(elementName);
/* 112 */     setPublicID(publicID);
/* 113 */     setSystemID(systemID);
/*     */   }
/*     */   
/*     */   public DocType(String elementName, String systemID) {
/* 131 */     this(elementName, null, systemID);
/*     */   }
/*     */   
/*     */   public DocType(String elementName) {
/* 144 */     this(elementName, null, null);
/*     */   }
/*     */   
/*     */   public String getElementName() {
/* 153 */     return this.elementName;
/*     */   }
/*     */   
/*     */   public DocType setElementName(String elementName) {
/* 169 */     String reason = Verifier.checkXMLName(elementName);
/* 170 */     if (reason != null)
/* 171 */       throw new IllegalNameException(elementName, "DocType", reason); 
/* 173 */     this.elementName = elementName;
/* 174 */     return this;
/*     */   }
/*     */   
/*     */   public String getPublicID() {
/* 185 */     return this.publicID;
/*     */   }
/*     */   
/*     */   public DocType setPublicID(String publicID) {
/* 198 */     String reason = Verifier.checkPublicID(publicID);
/* 199 */     if (reason != null)
/* 200 */       throw new IllegalDataException(publicID, "DocType", reason); 
/* 202 */     this.publicID = publicID;
/* 204 */     return this;
/*     */   }
/*     */   
/*     */   public String getSystemID() {
/* 215 */     return this.systemID;
/*     */   }
/*     */   
/*     */   public DocType setSystemID(String systemID) {
/* 229 */     String reason = Verifier.checkSystemLiteral(systemID);
/* 230 */     if (reason != null)
/* 231 */       throw new IllegalDataException(systemID, "DocType", reason); 
/* 233 */     this.systemID = systemID;
/* 235 */     return this;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 244 */     return "";
/*     */   }
/*     */   
/*     */   public void setInternalSubset(String newData) {
/* 254 */     this.internalSubset = newData;
/*     */   }
/*     */   
/*     */   public String getInternalSubset() {
/* 263 */     return this.internalSubset;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 274 */     return "[DocType: " + (new XMLOutputter()).outputString(this) + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\DocType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */