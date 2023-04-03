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
/*     */ import org.opengis.style.Fill;
/*     */ import org.opengis.style.Halo;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class HaloImpl implements Halo, Cloneable {
/*  41 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   private FilterFactory filterFactory;
/*     */   
/*     */   private FillImpl fill;
/*     */   
/*  44 */   private Expression radius = null;
/*     */   
/*     */   static HaloImpl cast(Halo halo) {
/*  52 */     if (halo == null)
/*  53 */       return null; 
/*  55 */     if (halo instanceof HaloImpl)
/*  56 */       return (HaloImpl)halo; 
/*  59 */     HaloImpl copy = new HaloImpl();
/*  60 */     copy.setFill(halo.getFill());
/*  61 */     copy.setRadius(halo.getRadius());
/*  63 */     return copy;
/*     */   }
/*     */   
/*     */   public HaloImpl() {
/*  67 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public HaloImpl(FilterFactory factory) {
/*  71 */     this.filterFactory = factory;
/*  72 */     init();
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory factory) {
/*  76 */     this.filterFactory = factory;
/*  77 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*     */     try {
/*  82 */       this.fill = new FillImpl(this.filterFactory);
/*  83 */       this.radius = (Expression)this.filterFactory.literal(1);
/*  84 */     } catch (IllegalFilterException ife) {
/*  85 */       LOGGER.severe("Failed to build defaultHalo: " + ife);
/*     */     } 
/*  88 */     this.fill.setColor((Expression)this.filterFactory.literal("#FFFFFF"));
/*     */   }
/*     */   
/*     */   public FillImpl getFill() {
/*  97 */     return this.fill;
/*     */   }
/*     */   
/*     */   public void setFill(Fill fill) {
/* 106 */     this.fill = FillImpl.cast(fill);
/*     */   }
/*     */   
/*     */   public Expression getRadius() {
/* 115 */     return this.radius;
/*     */   }
/*     */   
/*     */   public void setRadius(Expression radius) {
/* 124 */     this.radius = radius;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 128 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 132 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 144 */       HaloImpl clone = (HaloImpl)super.clone();
/* 145 */       clone.fill = (FillImpl)this.fill.clone();
/* 147 */       return clone;
/* 148 */     } catch (CloneNotSupportedException e) {
/* 149 */       throw new RuntimeException("This will never happen");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 164 */     if (this == obj)
/* 165 */       return true; 
/* 168 */     if (obj instanceof HaloImpl) {
/* 169 */       HaloImpl other = (HaloImpl)obj;
/* 171 */       return (Utilities.equals(this.radius, other.radius) && Utilities.equals(this.fill, other.fill));
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 182 */     int PRIME = 37;
/* 183 */     int result = 17;
/* 185 */     if (this.radius != null)
/* 186 */       result = result * 37 + this.radius.hashCode(); 
/* 189 */     if (this.fill != null)
/* 190 */       result = result * 37 + this.fill.hashCode(); 
/* 193 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\HaloImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */