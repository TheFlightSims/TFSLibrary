/*    */ package org.geotools.metadata.iso;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.opengis.metadata.PortrayalCatalogueReference;
/*    */ import org.opengis.metadata.citation.Citation;
/*    */ 
/*    */ public class PortrayalCatalogueReferenceImpl extends MetadataEntity implements PortrayalCatalogueReference {
/*    */   private static final long serialVersionUID = -3095277682987563157L;
/*    */   
/*    */   private Collection<Citation> portrayalCatalogueCitations;
/*    */   
/*    */   public PortrayalCatalogueReferenceImpl() {}
/*    */   
/*    */   public PortrayalCatalogueReferenceImpl(PortrayalCatalogueReference source) {
/* 65 */     super(source);
/*    */   }
/*    */   
/*    */   public PortrayalCatalogueReferenceImpl(Collection<Citation> portrayalCatalogueCitations) {
/* 72 */     setPortrayalCatalogueCitations(portrayalCatalogueCitations);
/*    */   }
/*    */   
/*    */   public synchronized Collection<Citation> getPortrayalCatalogueCitations() {
/* 79 */     return this.portrayalCatalogueCitations = nonNullCollection(this.portrayalCatalogueCitations, Citation.class);
/*    */   }
/*    */   
/*    */   public synchronized void setPortrayalCatalogueCitations(Collection<? extends Citation> newValues) {
/* 88 */     this.portrayalCatalogueCitations = copyCollection(newValues, this.portrayalCatalogueCitations, Citation.class);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\PortrayalCatalogueReferenceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */