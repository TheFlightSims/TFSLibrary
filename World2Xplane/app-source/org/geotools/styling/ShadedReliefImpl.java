/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.ShadedRelief;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ 
/*     */ public class ShadedReliefImpl implements ShadedRelief {
/*     */   private FilterFactory filterFactory;
/*     */   
/*     */   private Expression reliefFactor;
/*     */   
/*     */   private boolean brightness = false;
/*     */   
/*     */   public ShadedReliefImpl() {
/*  42 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public ShadedReliefImpl(FilterFactory factory) {
/*  46 */     this.filterFactory = factory;
/*  47 */     this.reliefFactor = (Expression)this.filterFactory.literal(55);
/*     */   }
/*     */   
/*     */   public Expression getReliefFactor() {
/*  58 */     return this.reliefFactor;
/*     */   }
/*     */   
/*     */   public boolean isBrightnessOnly() {
/*  67 */     return this.brightness;
/*     */   }
/*     */   
/*     */   public void setBrightnessOnly(boolean flag) {
/*  76 */     this.brightness = flag;
/*     */   }
/*     */   
/*     */   public void setReliefFactor(Expression reliefFactor) {
/*  87 */     this.reliefFactor = reliefFactor;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/*  91 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/*  95 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 100 */     int PRIME = 1000003;
/* 101 */     int result = 0;
/* 103 */     if (this.reliefFactor != null)
/* 104 */       result = 1000003 * result + this.reliefFactor.hashCode(); 
/* 107 */     result = 1000003 * result + (this.brightness ? 1 : 0);
/* 109 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 114 */     if (this == obj)
/* 115 */       return true; 
/* 118 */     if (obj instanceof ShadedReliefImpl) {
/* 119 */       ShadedReliefImpl other = (ShadedReliefImpl)obj;
/* 121 */       return (Utilities.equals(this.reliefFactor, other.reliefFactor) && Utilities.equals(this.brightness, other.brightness));
/*     */     } 
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   static ShadedReliefImpl cast(ShadedRelief shadedRelief) {
/* 129 */     if (shadedRelief == null)
/* 130 */       return null; 
/* 132 */     if (shadedRelief instanceof ShadedReliefImpl)
/* 133 */       return (ShadedReliefImpl)shadedRelief; 
/* 136 */     ShadedReliefImpl copy = new ShadedReliefImpl();
/* 137 */     copy.setBrightnessOnly(shadedRelief.isBrightnessOnly());
/* 138 */     copy.setReliefFactor(shadedRelief.getReliefFactor());
/* 140 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ShadedReliefImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */