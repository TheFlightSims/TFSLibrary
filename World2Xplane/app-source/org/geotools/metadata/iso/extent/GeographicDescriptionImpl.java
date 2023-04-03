/*    */ package org.geotools.metadata.iso.extent;
/*    */ 
/*    */ import org.opengis.metadata.Identifier;
/*    */ import org.opengis.metadata.extent.GeographicDescription;
/*    */ import org.opengis.metadata.extent.GeographicExtent;
/*    */ 
/*    */ public class GeographicDescriptionImpl extends GeographicExtentImpl implements GeographicDescription {
/*    */   private static final long serialVersionUID = 7250161161099782176L;
/*    */   
/*    */   private Identifier geographicIdentifier;
/*    */   
/*    */   public GeographicDescriptionImpl() {}
/*    */   
/*    */   public GeographicDescriptionImpl(GeographicDescription source) {
/* 63 */     super((GeographicExtent)source);
/*    */   }
/*    */   
/*    */   public GeographicDescriptionImpl(Identifier geographicIdentifier) {
/* 70 */     setGeographicIdentifier(geographicIdentifier);
/*    */   }
/*    */   
/*    */   public Identifier getGeographicIdentifier() {
/* 77 */     return this.geographicIdentifier;
/*    */   }
/*    */   
/*    */   public synchronized void setGeographicIdentifier(Identifier newValue) {
/* 84 */     checkWritePermission();
/* 85 */     this.geographicIdentifier = newValue;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\extent\GeographicDescriptionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */