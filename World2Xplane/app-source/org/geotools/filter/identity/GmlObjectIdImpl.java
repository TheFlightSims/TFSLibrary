/*    */ package org.geotools.filter.identity;
/*    */ 
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.filter.identity.GmlObjectId;
/*    */ 
/*    */ public class GmlObjectIdImpl implements GmlObjectId {
/*    */   String gmlId;
/*    */   
/*    */   public GmlObjectIdImpl(String gmlId) {
/* 37 */     this.gmlId = gmlId;
/* 38 */     if (gmlId == null)
/* 39 */       throw new NullPointerException("id can not be null"); 
/*    */   }
/*    */   
/*    */   public String getID() {
/* 44 */     return this.gmlId;
/*    */   }
/*    */   
/*    */   public boolean matches(Object object) {
/* 48 */     if (object instanceof Feature)
/* 49 */       return (new FeatureIdImpl(this.gmlId)).matches((Feature)object); 
/* 53 */     return false;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 57 */     return this.gmlId;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 61 */     if (obj instanceof GmlObjectIdImpl) {
/* 62 */       GmlObjectIdImpl other = (GmlObjectIdImpl)obj;
/* 63 */       return this.gmlId.equals(other.gmlId);
/*    */     } 
/* 66 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 70 */     return this.gmlId.hashCode();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\identity\GmlObjectIdImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */