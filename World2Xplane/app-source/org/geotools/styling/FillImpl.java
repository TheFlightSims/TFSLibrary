/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Fill;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.GraphicFill;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class FillImpl implements Fill, Cloneable {
/*  41 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*     */   private FilterFactory filterFactory;
/*     */   
/*  43 */   private Expression color = null;
/*     */   
/*  44 */   private Expression backgroundColor = null;
/*     */   
/*  45 */   private Expression opacity = null;
/*     */   
/*  46 */   private Graphic graphicFill = null;
/*     */   
/*     */   protected FillImpl() {
/*  50 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   public FillImpl(FilterFactory factory) {
/*  54 */     this.filterFactory = factory;
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory factory) {
/*  57 */     this.filterFactory = factory;
/*     */   }
/*     */   
/*     */   public Expression getColor() {
/*  75 */     return this.color;
/*     */   }
/*     */   
/*     */   public void setColor(Expression rgb) {
/*  92 */     if (this.color == rgb)
/*     */       return; 
/*  93 */     this.color = rgb;
/*     */   }
/*     */   
/*     */   public void setColor(String rgb) {
/*  97 */     if (this.color.toString() == rgb)
/*     */       return; 
/*  99 */     setColor((Expression)this.filterFactory.literal(rgb));
/*     */   }
/*     */   
/*     */   public Expression getBackgroundColor() {
/* 116 */     return this.backgroundColor;
/*     */   }
/*     */   
/*     */   public void setBackgroundColor(Expression rgb) {
/* 133 */     if (this.backgroundColor == rgb)
/*     */       return; 
/* 134 */     this.backgroundColor = rgb;
/*     */   }
/*     */   
/*     */   public void setBackgroundColor(String rgb) {
/* 138 */     LOGGER.fine("setting bg color with " + rgb + " as a string");
/* 139 */     if (this.backgroundColor.toString() == rgb)
/*     */       return; 
/* 141 */     setBackgroundColor((Expression)this.filterFactory.literal(rgb));
/*     */   }
/*     */   
/*     */   public Expression getOpacity() {
/* 157 */     return this.opacity;
/*     */   }
/*     */   
/*     */   public void setOpacity(Expression opacity) {
/* 165 */     if (this.opacity == opacity)
/*     */       return; 
/* 167 */     this.opacity = opacity;
/*     */   }
/*     */   
/*     */   public void setOpacity(String opacity) {
/* 171 */     if (this.opacity.toString() == opacity)
/*     */       return; 
/* 173 */     setOpacity((Expression)this.filterFactory.literal(opacity));
/*     */   }
/*     */   
/*     */   public Graphic getGraphicFill() {
/* 184 */     return this.graphicFill;
/*     */   }
/*     */   
/*     */   public void setGraphicFill(Graphic graphicFill) {
/* 192 */     if (this.graphicFill == graphicFill)
/*     */       return; 
/* 193 */     this.graphicFill = GraphicImpl.cast(graphicFill);
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 197 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 201 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 211 */       FillImpl clone = (FillImpl)super.clone();
/* 212 */       if (this.graphicFill != null)
/* 213 */         clone.graphicFill = (Graphic)((Cloneable)this.graphicFill).clone(); 
/* 215 */       return clone;
/* 216 */     } catch (CloneNotSupportedException e) {
/* 218 */       throw new RuntimeException("Failed to clone FillImpl");
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 228 */     int PRIME = 1000003;
/* 229 */     int result = 0;
/* 230 */     if (this.color != null)
/* 231 */       result = 1000003 * result + this.color.hashCode(); 
/* 233 */     if (this.backgroundColor != null)
/* 234 */       result = 1000003 * result + this.backgroundColor.hashCode(); 
/* 236 */     if (this.opacity != null)
/* 237 */       result = 1000003 * result + this.opacity.hashCode(); 
/* 239 */     if (this.graphicFill != null)
/* 240 */       result = 1000003 * result + this.graphicFill.hashCode(); 
/* 243 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 255 */     if (this == oth)
/* 256 */       return true; 
/* 259 */     if (oth instanceof FillImpl) {
/* 260 */       FillImpl other = (FillImpl)oth;
/* 261 */       return (Utilities.equals(this.color, other.color) && Utilities.equals(this.backgroundColor, other.backgroundColor) && Utilities.equals(this.opacity, other.opacity) && Utilities.equals(this.graphicFill, other.graphicFill));
/*     */     } 
/* 267 */     return false;
/*     */   }
/*     */   
/*     */   static FillImpl cast(Fill fill) {
/* 271 */     if (fill == null)
/* 272 */       return null; 
/* 273 */     if (fill instanceof FillImpl)
/* 274 */       return (FillImpl)fill; 
/* 276 */     FillImpl copy = new FillImpl();
/* 277 */     copy.color = fill.getColor();
/* 278 */     copy.graphicFill = GraphicImpl.cast((Graphic)fill.getGraphicFill());
/* 279 */     copy.opacity = fill.getOpacity();
/* 280 */     copy.backgroundColor = null;
/* 281 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\FillImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */