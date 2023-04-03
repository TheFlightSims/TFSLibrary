/*     */ package org.geotools.styling;
/*     */ 
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.ChannelSelection;
/*     */ import org.opengis.style.ColorMap;
/*     */ import org.opengis.style.ContrastEnhancement;
/*     */ import org.opengis.style.OverlapBehavior;
/*     */ import org.opengis.style.RasterSymbolizer;
/*     */ import org.opengis.style.ShadedRelief;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.style.Symbolizer;
/*     */ 
/*     */ public class RasterSymbolizerImpl extends AbstractSymbolizer implements RasterSymbolizer {
/*     */   private OverlapBehavior behavior;
/*     */   
/*     */   private FilterFactory filterFactory;
/*     */   
/*  46 */   private ChannelSelection channelSelection = new ChannelSelectionImpl();
/*     */   
/*  47 */   private ColorMapImpl colorMap = new ColorMapImpl();
/*     */   
/*  48 */   private ContrastEnhancementImpl contrastEnhancement = new ContrastEnhancementImpl();
/*     */   
/*     */   private ShadedReliefImpl shadedRelief;
/*     */   
/*     */   private String geometryName;
/*     */   
/*     */   private Symbolizer symbolizer;
/*     */   
/*     */   private Expression opacity;
/*     */   
/*     */   private Expression overlap;
/*     */   
/*     */   public RasterSymbolizerImpl() {
/*  56 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public RasterSymbolizerImpl(FilterFactory factory) {
/*  60 */     this(factory, (Description)null, (String)null, (Unit<Length>)null, (OverlapBehavior)null);
/*     */   }
/*     */   
/*     */   public RasterSymbolizerImpl(FilterFactory factory, Description desc, String name, Unit<Length> uom, OverlapBehavior behavior) {
/*  64 */     super(name, desc, (String)null, uom);
/*  65 */     this.filterFactory = factory;
/*  66 */     this.opacity = (Expression)this.filterFactory.literal(1.0D);
/*  67 */     this.overlap = (Expression)this.filterFactory.literal(OverlapBehavior.RANDOM);
/*  68 */     this.behavior = behavior;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  74 */     int prime = 31;
/*  75 */     int result = super.hashCode();
/*  76 */     result = 31 * result + ((this.behavior == null) ? 0 : this.behavior.hashCode());
/*  77 */     result = 31 * result + ((this.channelSelection == null) ? 0 : this.channelSelection.hashCode());
/*  78 */     result = 31 * result + ((this.colorMap == null) ? 0 : this.colorMap.hashCode());
/*  79 */     result = 31 * result + ((this.contrastEnhancement == null) ? 0 : this.contrastEnhancement.hashCode());
/*  81 */     result = 31 * result + ((this.filterFactory == null) ? 0 : this.filterFactory.hashCode());
/*  82 */     result = 31 * result + ((this.opacity == null) ? 0 : this.opacity.hashCode());
/*  83 */     result = 31 * result + ((this.overlap == null) ? 0 : this.overlap.hashCode());
/*  84 */     result = 31 * result + ((this.shadedRelief == null) ? 0 : this.shadedRelief.hashCode());
/*  85 */     result = 31 * result + ((this.symbolizer == null) ? 0 : this.symbolizer.hashCode());
/*  86 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  91 */     if (this == obj)
/*  92 */       return true; 
/*  93 */     if (!super.equals(obj))
/*  94 */       return false; 
/*  95 */     if (getClass() != obj.getClass())
/*  96 */       return false; 
/*  97 */     RasterSymbolizerImpl other = (RasterSymbolizerImpl)obj;
/*  98 */     if (this.behavior == null) {
/*  99 */       if (other.behavior != null)
/* 100 */         return false; 
/* 101 */     } else if (!this.behavior.equals(other.behavior)) {
/* 102 */       return false;
/*     */     } 
/* 103 */     if (this.channelSelection == null) {
/* 104 */       if (other.channelSelection != null)
/* 105 */         return false; 
/* 106 */     } else if (!this.channelSelection.equals(other.channelSelection)) {
/* 107 */       return false;
/*     */     } 
/* 108 */     if (this.colorMap == null) {
/* 109 */       if (other.colorMap != null)
/* 110 */         return false; 
/* 111 */     } else if (!this.colorMap.equals(other.colorMap)) {
/* 112 */       return false;
/*     */     } 
/* 113 */     if (this.contrastEnhancement == null) {
/* 114 */       if (other.contrastEnhancement != null)
/* 115 */         return false; 
/* 116 */     } else if (!this.contrastEnhancement.equals(other.contrastEnhancement)) {
/* 117 */       return false;
/*     */     } 
/* 118 */     if (this.filterFactory == null) {
/* 119 */       if (other.filterFactory != null)
/* 120 */         return false; 
/* 121 */     } else if (!this.filterFactory.equals(other.filterFactory)) {
/* 122 */       return false;
/*     */     } 
/* 123 */     if (this.opacity == null) {
/* 124 */       if (other.opacity != null)
/* 125 */         return false; 
/* 126 */     } else if (!this.opacity.equals(other.opacity)) {
/* 127 */       return false;
/*     */     } 
/* 128 */     if (this.overlap == null) {
/* 129 */       if (other.overlap != null)
/* 130 */         return false; 
/* 131 */     } else if (!this.overlap.equals(other.overlap)) {
/* 132 */       return false;
/*     */     } 
/* 133 */     if (this.shadedRelief == null) {
/* 134 */       if (other.shadedRelief != null)
/* 135 */         return false; 
/* 136 */     } else if (!this.shadedRelief.equals(other.shadedRelief)) {
/* 137 */       return false;
/*     */     } 
/* 138 */     if (this.symbolizer == null) {
/* 139 */       if (other.symbolizer != null)
/* 140 */         return false; 
/* 141 */     } else if (!this.symbolizer.equals(other.symbolizer)) {
/* 142 */       return false;
/*     */     } 
/* 143 */     return true;
/*     */   }
/*     */   
/*     */   public ChannelSelection getChannelSelection() {
/* 160 */     return this.channelSelection;
/*     */   }
/*     */   
/*     */   public ColorMapImpl getColorMap() {
/* 180 */     return this.colorMap;
/*     */   }
/*     */   
/*     */   public ContrastEnhancementImpl getContrastEnhancement() {
/* 202 */     return this.contrastEnhancement;
/*     */   }
/*     */   
/*     */   public Symbolizer getImageOutline() {
/* 229 */     return this.symbolizer;
/*     */   }
/*     */   
/*     */   public Expression getOpacity() {
/* 238 */     return this.opacity;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Expression getOverlap() {
/* 258 */     return this.overlap;
/*     */   }
/*     */   
/*     */   public OverlapBehavior getOverlapBehavior() {
/* 262 */     return this.behavior;
/*     */   }
/*     */   
/*     */   public void setOverlapBehavior(OverlapBehavior overlapBehavior) {
/* 266 */     this.behavior = overlapBehavior;
/*     */   }
/*     */   
/*     */   public ShadedReliefImpl getShadedRelief() {
/* 286 */     return this.shadedRelief;
/*     */   }
/*     */   
/*     */   public void setChannelSelection(ChannelSelection channel) {
/* 303 */     if (this.channelSelection == channel)
/*     */       return; 
/* 306 */     this.channelSelection = ChannelSelectionImpl.cast(channel);
/*     */   }
/*     */   
/*     */   public void setColorMap(ColorMap colorMap) {
/* 326 */     if (this.colorMap == colorMap)
/*     */       return; 
/* 329 */     this.colorMap = ColorMapImpl.cast(colorMap);
/*     */   }
/*     */   
/*     */   public void setContrastEnhancement(ContrastEnhancement contrastEnhancement) {
/* 351 */     if (this.contrastEnhancement == contrastEnhancement)
/*     */       return; 
/* 354 */     this.contrastEnhancement = ContrastEnhancementImpl.cast(contrastEnhancement);
/*     */   }
/*     */   
/*     */   public void setImageOutline(Symbolizer symbolizer) {
/* 384 */     if (symbolizer == null) {
/* 385 */       this.symbolizer = null;
/* 387 */     } else if (symbolizer instanceof LineSymbolizer || symbolizer instanceof PolygonSymbolizer) {
/* 389 */       if (this.symbolizer == symbolizer)
/*     */         return; 
/* 392 */       this.symbolizer = StyleFactoryImpl2.cast(symbolizer);
/*     */     } else {
/* 394 */       throw new IllegalArgumentException("Only a line or polygon symbolizer may be used to outline a raster");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setOpacity(Expression opacity) {
/* 405 */     if (this.opacity == opacity)
/*     */       return; 
/* 408 */     this.opacity = opacity;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setOverlap(Expression overlap) {
/* 428 */     if (this.overlap == overlap)
/*     */       return; 
/* 431 */     this.overlap = overlap;
/*     */   }
/*     */   
/*     */   public void setShadedRelief(ShadedRelief shadedRelief) {
/* 451 */     if (this.shadedRelief == shadedRelief)
/*     */       return; 
/* 454 */     this.shadedRelief = ShadedReliefImpl.cast(shadedRelief);
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 458 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 462 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     Object clone;
/*     */     try {
/* 478 */       clone = super.clone();
/* 479 */     } catch (CloneNotSupportedException e) {
/* 480 */       throw new RuntimeException(e);
/*     */     } 
/* 483 */     return clone;
/*     */   }
/*     */   
/*     */   static RasterSymbolizerImpl cast(Symbolizer symbolizer) {
/* 487 */     if (symbolizer == null)
/* 488 */       return null; 
/* 490 */     if (symbolizer instanceof RasterSymbolizerImpl)
/* 491 */       return (RasterSymbolizerImpl)symbolizer; 
/* 493 */     if (symbolizer instanceof RasterSymbolizer) {
/* 494 */       RasterSymbolizer rasterSymbolizer = (RasterSymbolizer)symbolizer;
/* 495 */       RasterSymbolizerImpl copy = new RasterSymbolizerImpl();
/* 496 */       copy.setChannelSelection(rasterSymbolizer.getChannelSelection());
/* 497 */       copy.setColorMap(rasterSymbolizer.getColorMap());
/* 498 */       copy.setContrastEnhancement(rasterSymbolizer.getContrastEnhancement());
/* 499 */       copy.setDescription(rasterSymbolizer.getDescription());
/* 500 */       copy.setGeometryPropertyName(rasterSymbolizer.getGeometryPropertyName());
/* 501 */       copy.setImageOutline(rasterSymbolizer.getImageOutline());
/* 502 */       copy.setName(rasterSymbolizer.getName());
/* 503 */       copy.setOpacity(rasterSymbolizer.getOpacity());
/* 504 */       copy.setOverlapBehavior(rasterSymbolizer.getOverlapBehavior());
/* 505 */       copy.setShadedRelief(rasterSymbolizer.getShadedRelief());
/* 506 */       copy.setUnitOfMeasure(rasterSymbolizer.getUnitOfMeasure());
/* 508 */       return copy;
/*     */     } 
/* 510 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\RasterSymbolizerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */