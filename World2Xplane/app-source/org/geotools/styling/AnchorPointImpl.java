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
/*     */ import org.opengis.style.AnchorPoint;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class AnchorPointImpl implements AnchorPoint, Cloneable {
/*  41 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   private FilterFactory filterFactory;
/*     */   
/*  43 */   private Expression anchorPointX = null;
/*     */   
/*  44 */   private Expression anchorPointY = null;
/*     */   
/*     */   static AnchorPointImpl cast(AnchorPoint anchor) {
/*  48 */     if (anchor == null)
/*  49 */       return null; 
/*  51 */     if (anchor instanceof AnchorPointImpl)
/*  52 */       return (AnchorPointImpl)anchor; 
/*  55 */     AnchorPointImpl copy = new AnchorPointImpl();
/*  56 */     copy.setAnchorPointX(anchor.getAnchorPointX());
/*  57 */     copy.setAnchorPointY(anchor.getAnchorPointY());
/*  58 */     return copy;
/*     */   }
/*     */   
/*     */   public AnchorPointImpl() {
/*  63 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public AnchorPointImpl(FilterFactory filterFactory) {
/*  69 */     this.filterFactory = filterFactory;
/*     */     try {
/*  71 */       this.anchorPointX = (Expression)filterFactory.literal(0.0D);
/*  72 */       this.anchorPointY = (Expression)filterFactory.literal(0.5D);
/*  73 */     } catch (IllegalFilterException ife) {
/*  74 */       LOGGER.severe("Failed to build defaultAnchorPoint: " + ife);
/*     */     } 
/*     */   }
/*     */   
/*     */   public AnchorPointImpl(FilterFactory filterFactory, Expression x, Expression y) {
/*  79 */     this.filterFactory = filterFactory;
/*  80 */     this.anchorPointX = x;
/*  81 */     this.anchorPointY = y;
/*     */   }
/*     */   
/*     */   public Expression getAnchorPointX() {
/*  89 */     return this.anchorPointX;
/*     */   }
/*     */   
/*     */   public void setAnchorPointX(Expression anchorPointX) {
/*  98 */     this.anchorPointX = anchorPointX;
/*     */   }
/*     */   
/*     */   public void setAnchorPointX(double x) {
/* 106 */     this.anchorPointX = (Expression)this.filterFactory.literal(x);
/*     */   }
/*     */   
/*     */   public Expression getAnchorPointY() {
/* 115 */     return this.anchorPointY;
/*     */   }
/*     */   
/*     */   public void setAnchorPointY(Expression anchorPointY) {
/* 124 */     this.anchorPointY = anchorPointY;
/*     */   }
/*     */   
/*     */   public void getAnchorPointY(double x) {
/* 133 */     this.anchorPointY = (Expression)this.filterFactory.literal(x);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 137 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 141 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 149 */       return super.clone();
/* 150 */     } catch (CloneNotSupportedException e) {
/* 151 */       throw new RuntimeException("Never happen");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 159 */     if (this == obj)
/* 160 */       return true; 
/* 163 */     if (obj instanceof AnchorPointImpl) {
/* 164 */       AnchorPointImpl other = (AnchorPointImpl)obj;
/* 166 */       return (Utilities.equals(this.anchorPointX, other.anchorPointX) && Utilities.equals(this.anchorPointY, other.anchorPointY));
/*     */     } 
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 177 */     int PRIME = 37;
/* 178 */     int result = 17;
/* 180 */     if (this.anchorPointX != null)
/* 181 */       result = result * 37 + this.anchorPointX.hashCode(); 
/* 184 */     if (this.anchorPointY != null)
/* 185 */       result = result * 37 + this.anchorPointY.hashCode(); 
/* 188 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\AnchorPointImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */