/*     */ package org.jdom;
/*     */ 
/*     */ public class EntityRef extends Content {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: EntityRef.java,v $ $Revision: 1.22 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   protected String name;
/*     */   
/*     */   protected String publicID;
/*     */   
/*     */   protected String systemID;
/*     */   
/*     */   protected EntityRef() {}
/*     */   
/*     */   public EntityRef(String name) {
/*  95 */     this(name, null, null);
/*     */   }
/*     */   
/*     */   public EntityRef(String name, String systemID) {
/* 110 */     this(name, null, systemID);
/*     */   }
/*     */   
/*     */   public EntityRef(String name, String publicID, String systemID) {
/* 127 */     setName(name);
/* 128 */     setPublicID(publicID);
/* 129 */     setSystemID(systemID);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 138 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 147 */     return "";
/*     */   }
/*     */   
/*     */   public String getPublicID() {
/* 157 */     return this.publicID;
/*     */   }
/*     */   
/*     */   public String getSystemID() {
/* 167 */     return this.systemID;
/*     */   }
/*     */   
/*     */   public EntityRef setName(String name) {
/* 181 */     String reason = Verifier.checkXMLName(name);
/* 182 */     if (reason != null)
/* 183 */       throw new IllegalNameException(name, "EntityRef", reason); 
/* 185 */     this.name = name;
/* 186 */     return this;
/*     */   }
/*     */   
/*     */   public EntityRef setPublicID(String publicID) {
/* 198 */     String reason = Verifier.checkPublicID(publicID);
/* 199 */     if (reason != null)
/* 200 */       throw new IllegalDataException(publicID, "EntityRef", reason); 
/* 202 */     this.publicID = publicID;
/* 203 */     return this;
/*     */   }
/*     */   
/*     */   public EntityRef setSystemID(String systemID) {
/* 215 */     String reason = Verifier.checkSystemLiteral(systemID);
/* 216 */     if (reason != null)
/* 217 */       throw new IllegalDataException(systemID, "EntityRef", reason); 
/* 219 */     this.systemID = systemID;
/* 220 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 231 */     return "[EntityRef: " + "&" + this.name + ";" + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\EntityRef.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */