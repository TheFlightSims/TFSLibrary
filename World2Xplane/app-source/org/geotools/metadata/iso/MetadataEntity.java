/*    */ package org.geotools.metadata.iso;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.geotools.metadata.InvalidMetadataException;
/*    */ import org.geotools.metadata.MetadataStandard;
/*    */ import org.geotools.metadata.ModifiableMetadata;
/*    */ import org.geotools.resources.i18n.Errors;
/*    */ 
/*    */ public class MetadataEntity extends ModifiableMetadata implements Serializable {
/*    */   private static final long serialVersionUID = 5730550742604669102L;
/*    */   
/*    */   protected MetadataEntity() {}
/*    */   
/*    */   protected MetadataEntity(Object source) throws ClassCastException {
/* 65 */     super(source);
/*    */   }
/*    */   
/*    */   public MetadataStandard getStandard() {
/* 75 */     return MetadataStandard.ISO_19115;
/*    */   }
/*    */   
/*    */   protected static void ensureNonNull(String name, Object object) throws InvalidMetadataException {
/* 91 */     if (object == null)
/* 92 */       throw new InvalidMetadataException(Errors.format(144, name)); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\MetadataEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */