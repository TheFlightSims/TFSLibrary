/*    */ package org.java.plugin.registry.xml;
/*    */ 
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.java.plugin.registry.Identity;
/*    */ import org.java.plugin.registry.ManifestProcessingException;
/*    */ 
/*    */ abstract class IdentityImpl implements Identity {
/* 33 */   protected final Log log = LogFactory.getLog(getClass());
/*    */   
/*    */   private final String id;
/*    */   
/*    */   private int hashCode;
/*    */   
/*    */   public String getId() {
/* 51 */     return this.id;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 61 */     if (this == obj)
/* 62 */       return true; 
/* 64 */     if (!(obj instanceof Identity))
/* 65 */       return false; 
/* 67 */     return isEqualTo((Identity)obj);
/*    */   }
/*    */   
/*    */   protected IdentityImpl(String anId) throws ManifestProcessingException {
/* 70 */     this.hashCode = -1;
/*    */     this.id = anId;
/*    */     if (this.id == null || this.id.trim().length() == 0)
/*    */       throw new ManifestProcessingException("org.java.plugin.registry.xml", "manifestElementIdIsBlank"); 
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 77 */     if (this.hashCode == -1)
/* 78 */       this.hashCode = getClass().hashCode() ^ getId().hashCode(); 
/* 80 */     return this.hashCode;
/*    */   }
/*    */   
/*    */   protected abstract boolean isEqualTo(Identity paramIdentity);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\IdentityImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */