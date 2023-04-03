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
/*     */ import org.opengis.style.Displacement;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class DisplacementImpl implements Displacement, Cloneable {
/*  41 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   private FilterFactory filterFactory;
/*     */   
/*  43 */   private Expression displacementX = null;
/*     */   
/*  44 */   private Expression displacementY = null;
/*     */   
/*     */   public DisplacementImpl() {
/*  47 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public DisplacementImpl(FilterFactory factory) {
/*  51 */     this.filterFactory = factory;
/*     */     try {
/*  54 */       this.displacementX = (Expression)this.filterFactory.literal(0);
/*  55 */       this.displacementY = (Expression)this.filterFactory.literal(0);
/*  56 */     } catch (IllegalFilterException ife) {
/*  57 */       LOGGER.severe("Failed to build defaultDisplacement: " + ife);
/*     */     } 
/*     */   }
/*     */   
/*     */   public DisplacementImpl(Expression dx, Expression dy) {
/*  62 */     this.filterFactory = (FilterFactory)CommonFactoryFinder.getFilterFactory2(null);
/*  63 */     this.displacementX = dx;
/*  64 */     this.displacementY = dy;
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory factory) {
/*  68 */     this.filterFactory = factory;
/*     */     try {
/*  71 */       this.displacementX = (Expression)this.filterFactory.literal(0);
/*  72 */       this.displacementY = (Expression)this.filterFactory.literal(0);
/*  73 */     } catch (IllegalFilterException ife) {
/*  74 */       LOGGER.severe("Failed to build defaultDisplacement: " + ife);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDisplacementX(Expression displacementX) {
/*  84 */     this.displacementX = displacementX;
/*     */   }
/*     */   
/*     */   public void setDisplacementX(double displacementX) {
/*  92 */     this.displacementX = (Expression)this.filterFactory.literal(displacementX);
/*     */   }
/*     */   
/*     */   public void setDisplacementY(Expression displacementY) {
/* 100 */     this.displacementY = displacementY;
/*     */   }
/*     */   
/*     */   public void setDisplacementY(double displacementY) {
/* 108 */     this.displacementY = (Expression)this.filterFactory.literal(displacementY);
/*     */   }
/*     */   
/*     */   public Expression getDisplacementX() {
/* 117 */     return this.displacementX;
/*     */   }
/*     */   
/*     */   public Expression getDisplacementY() {
/* 126 */     return this.displacementY;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 130 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 134 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 142 */       return super.clone();
/* 143 */     } catch (CloneNotSupportedException e) {
/* 144 */       throw new RuntimeException("Will not happen");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 152 */     if (this == obj)
/* 153 */       return true; 
/* 156 */     if (obj instanceof DisplacementImpl) {
/* 157 */       DisplacementImpl other = (DisplacementImpl)obj;
/* 159 */       return (Utilities.equals(this.displacementX, other.displacementX) && Utilities.equals(this.displacementY, other.displacementY));
/*     */     } 
/* 163 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 170 */     int PRIME = 37;
/* 171 */     int result = 17;
/* 173 */     if (this.displacementX != null)
/* 174 */       result = result * 37 + this.displacementX.hashCode(); 
/* 177 */     if (this.displacementY != null)
/* 178 */       result = result * 37 + this.displacementY.hashCode(); 
/* 181 */     return result;
/*     */   }
/*     */   
/*     */   static DisplacementImpl cast(Displacement displacement) {
/* 185 */     if (displacement == null)
/* 186 */       return null; 
/* 187 */     if (displacement instanceof DisplacementImpl)
/* 188 */       return (DisplacementImpl)displacement; 
/* 190 */     DisplacementImpl copy = new DisplacementImpl();
/* 191 */     copy.setDisplacementX(displacement.getDisplacementX());
/* 192 */     copy.setDisplacementY(displacement.getDisplacementY());
/* 194 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\DisplacementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */