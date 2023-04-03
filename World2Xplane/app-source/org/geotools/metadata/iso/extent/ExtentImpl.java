/*     */ package org.geotools.metadata.iso.extent;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.metadata.extent.GeographicBoundingBox;
/*     */ import org.opengis.metadata.extent.GeographicExtent;
/*     */ import org.opengis.metadata.extent.TemporalExtent;
/*     */ import org.opengis.metadata.extent.VerticalExtent;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ExtentImpl extends MetadataEntity implements Extent {
/*     */   private static final long serialVersionUID = 7812213837337326257L;
/*     */   
/*     */   public static final Extent WORLD;
/*     */   
/*     */   private InternationalString description;
/*     */   
/*     */   private Collection<GeographicExtent> geographicElements;
/*     */   
/*     */   private Collection<TemporalExtent> temporalElements;
/*     */   
/*     */   private Collection<VerticalExtent> verticalElements;
/*     */   
/*     */   static {
/*  64 */     ExtentImpl world = new ExtentImpl();
/*  65 */     world.getGeographicElements().add(GeographicBoundingBoxImpl.WORLD);
/*  66 */     world.freeze();
/*  67 */     WORLD = world;
/*     */   }
/*     */   
/*     */   public ExtentImpl() {}
/*     */   
/*     */   public ExtentImpl(Extent source) {
/* 102 */     super(source);
/*     */   }
/*     */   
/*     */   public InternationalString getDescription() {
/* 109 */     return this.description;
/*     */   }
/*     */   
/*     */   public synchronized void setDescription(InternationalString newValue) {
/* 116 */     checkWritePermission();
/* 117 */     this.description = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<GeographicExtent> getGeographicElements() {
/* 124 */     return this.geographicElements = nonNullCollection(this.geographicElements, GeographicExtent.class);
/*     */   }
/*     */   
/*     */   public synchronized void setGeographicElements(Collection<? extends GeographicExtent> newValues) {
/* 133 */     this.geographicElements = copyCollection(newValues, this.geographicElements, GeographicExtent.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<TemporalExtent> getTemporalElements() {
/* 140 */     return this.temporalElements = nonNullCollection(this.temporalElements, TemporalExtent.class);
/*     */   }
/*     */   
/*     */   public synchronized void setTemporalElements(Collection<? extends TemporalExtent> newValues) {
/* 149 */     this.temporalElements = copyCollection(newValues, this.temporalElements, TemporalExtent.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<VerticalExtent> getVerticalElements() {
/* 156 */     return this.verticalElements = nonNullCollection(this.verticalElements, VerticalExtent.class);
/*     */   }
/*     */   
/*     */   public synchronized void setVerticalElements(Collection<? extends VerticalExtent> newValues) {
/* 165 */     this.verticalElements = copyCollection(newValues, this.verticalElements, VerticalExtent.class);
/*     */   }
/*     */   
/*     */   public static GeographicBoundingBox getGeographicBoundingBox(Extent extent) {
/* 176 */     GeographicBoundingBox candidate = null;
/* 177 */     if (extent != null) {
/* 178 */       GeographicBoundingBoxImpl modifiable = null;
/* 179 */       for (GeographicExtent element : extent.getGeographicElements()) {
/*     */         GeographicBoundingBox bounds;
/* 181 */         if (element instanceof GeographicBoundingBox) {
/* 182 */           bounds = (GeographicBoundingBox)element;
/*     */         } else {
/* 183 */           if (element instanceof org.opengis.metadata.extent.BoundingPolygon);
/*     */           continue;
/*     */         } 
/* 193 */         if (candidate == null) {
/* 200 */           Boolean inclusion = bounds.getInclusion();
/* 201 */           ensureNonNull("inclusion", inclusion);
/* 202 */           if (inclusion.booleanValue())
/* 203 */             candidate = bounds; 
/*     */           continue;
/*     */         } 
/* 206 */         if (modifiable == null) {
/* 207 */           modifiable = new GeographicBoundingBoxImpl(candidate);
/* 208 */           candidate = modifiable;
/*     */         } 
/* 210 */         modifiable.add(bounds);
/*     */       } 
/* 213 */       if (modifiable != null)
/* 214 */         modifiable.freeze(); 
/*     */     } 
/* 217 */     return candidate;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\extent\ExtentImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */