/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.LabelPlacement;
/*     */ import org.opengis.style.LinePlacement;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class LinePlacementImpl implements LinePlacement, Cloneable {
/*  42 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   private FilterFactory filterFactory;
/*     */   
/*     */   private Expression perpendicularOffset;
/*     */   
/*     */   private boolean generalized;
/*     */   
/*     */   private boolean aligned;
/*     */   
/*     */   private boolean repeated;
/*     */   
/*     */   private Expression gap;
/*     */   
/*     */   private Expression initialGap;
/*     */   
/*     */   public LinePlacementImpl() {
/*  53 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public LinePlacementImpl(LinePlacement placement) {
/*  57 */     this.gap = placement.getGap();
/*  58 */     this.initialGap = placement.getInitialGap();
/*  59 */     this.generalized = placement.isGeneralizeLine();
/*  60 */     this.perpendicularOffset = placement.getPerpendicularOffset();
/*  61 */     this.repeated = placement.isRepeated();
/*  62 */     this.aligned = placement.IsAligned();
/*     */   }
/*     */   
/*     */   public LinePlacementImpl(FilterFactory factory) {
/*  66 */     this(factory, false, false, false, null, null);
/*     */   }
/*     */   
/*     */   public LinePlacementImpl(FilterFactory factory, boolean aligned, boolean repeated, boolean generalized, Expression gap, Expression initialGap) {
/*  70 */     this.filterFactory = factory;
/*  71 */     this.gap = gap;
/*  72 */     this.initialGap = initialGap;
/*  73 */     this.generalized = generalized;
/*  74 */     this.aligned = aligned;
/*  75 */     this.repeated = repeated;
/*  76 */     init();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setFilterFactory(FilterFactory factory) {
/*  81 */     this.filterFactory = factory;
/*  82 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*     */     try {
/*  90 */       this.perpendicularOffset = (Expression)this.filterFactory.literal(0);
/*  91 */     } catch (IllegalFilterException ife) {
/*  92 */       LOGGER.severe("Failed to build defaultLinePlacement: " + ife);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Expression getPerpendicularOffset() {
/* 102 */     return this.perpendicularOffset;
/*     */   }
/*     */   
/*     */   public void setPerpendicularOffset(Expression perpendicularOffset) {
/* 111 */     this.perpendicularOffset = perpendicularOffset;
/*     */   }
/*     */   
/*     */   public Expression getInitialGap() {
/* 115 */     return this.initialGap;
/*     */   }
/*     */   
/*     */   public Expression getGap() {
/* 119 */     return this.gap;
/*     */   }
/*     */   
/*     */   public boolean isRepeated() {
/* 123 */     return this.repeated;
/*     */   }
/*     */   
/*     */   public boolean IsAligned() {
/* 127 */     return this.aligned;
/*     */   }
/*     */   
/*     */   public boolean isAligned() {
/* 131 */     return this.aligned;
/*     */   }
/*     */   
/*     */   public boolean isGeneralizeLine() {
/* 135 */     return this.generalized;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 139 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 143 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 151 */       return super.clone();
/* 152 */     } catch (CloneNotSupportedException e) {
/* 153 */       throw new RuntimeException("This can not happen");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 161 */     if (this == obj)
/* 162 */       return true; 
/* 165 */     if (obj instanceof LinePlacementImpl) {
/* 166 */       LinePlacementImpl other = (LinePlacementImpl)obj;
/* 168 */       return (Utilities.equals(this.perpendicularOffset, other.perpendicularOffset) && Utilities.equals(this.repeated, other.repeated) && Utilities.equals(this.generalized, other.generalized) && Utilities.equals(this.aligned, other.aligned) && Utilities.equals(this.initialGap, other.initialGap) && Utilities.equals(this.gap, other.gap));
/*     */     } 
/* 177 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 184 */     int PRIME = 37;
/* 185 */     int result = 17;
/* 187 */     if (this.perpendicularOffset != null)
/* 188 */       result = result * 37 + this.perpendicularOffset.hashCode(); 
/* 191 */     if (this.gap != null)
/* 192 */       result = result * 37 + this.gap.hashCode(); 
/* 195 */     if (this.initialGap != null)
/* 196 */       result = result * 37 + this.initialGap.hashCode(); 
/* 199 */     result = result * 37 + (new Boolean(this.generalized)).hashCode();
/* 200 */     result = result * 37 + (new Boolean(this.aligned)).hashCode();
/* 201 */     result = result * 37 + (new Boolean(this.repeated)).hashCode();
/* 203 */     return result;
/*     */   }
/*     */   
/*     */   static LinePlacementImpl cast(LabelPlacement placement) {
/* 207 */     if (placement == null)
/* 208 */       return null; 
/* 210 */     if (placement instanceof LinePlacementImpl)
/* 211 */       return (LinePlacementImpl)placement; 
/* 213 */     if (placement instanceof LinePlacement) {
/* 214 */       LinePlacementImpl copy = new LinePlacementImpl((LinePlacement)placement);
/* 215 */       return copy;
/*     */     } 
/* 217 */     return null;
/*     */   }
/*     */   
/*     */   public void setRepeated(boolean repeated) {
/* 221 */     this.repeated = repeated;
/*     */   }
/*     */   
/*     */   public void setGeneralized(boolean generalized) {
/* 225 */     this.generalized = generalized;
/*     */   }
/*     */   
/*     */   public void setAligned(boolean aligned) {
/* 229 */     this.aligned = aligned;
/*     */   }
/*     */   
/*     */   public void setGap(Expression gap) {
/* 233 */     this.gap = gap;
/*     */   }
/*     */   
/*     */   public void setInitialGap(Expression initialGap) {
/* 237 */     this.initialGap = initialGap;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\LinePlacementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */