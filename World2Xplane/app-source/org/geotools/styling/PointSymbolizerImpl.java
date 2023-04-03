/*     */ package org.geotools.styling;
/*     */ 
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.PointSymbolizer;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.style.Symbolizer;
/*     */ import org.opengis.util.Cloneable;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class PointSymbolizerImpl extends AbstractSymbolizer implements PointSymbolizer, Cloneable {
/*  41 */   private GraphicImpl graphic = new GraphicImpl();
/*     */   
/*     */   protected PointSymbolizerImpl() {
/*  47 */     this(new GraphicImpl(), (Unit<Length>)null, (String)null, (String)null, new DescriptionImpl((InternationalString)new SimpleInternationalString("title"), (InternationalString)new SimpleInternationalString("abstract")));
/*     */   }
/*     */   
/*     */   protected PointSymbolizerImpl(Graphic graphic, Unit<Length> uom, String geom, String name, Description desc) {
/*  57 */     super(name, desc, geom, uom);
/*  58 */     this.graphic = GraphicImpl.cast(graphic);
/*     */   }
/*     */   
/*     */   public GraphicImpl getGraphic() {
/*  68 */     return this.graphic;
/*     */   }
/*     */   
/*     */   public void setGraphic(Graphic graphic) {
/*  77 */     if (this.graphic == graphic)
/*     */       return; 
/*  80 */     this.graphic = GraphicImpl.cast(graphic);
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/*  89 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/*  93 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     PointSymbolizerImpl clone;
/*     */     try {
/* 107 */       clone = (PointSymbolizerImpl)super.clone();
/* 108 */       if (this.graphic != null)
/* 108 */         clone.graphic = (GraphicImpl)this.graphic.clone(); 
/* 109 */     } catch (CloneNotSupportedException e) {
/* 110 */       throw new RuntimeException(e);
/*     */     } 
/* 113 */     return clone;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 118 */     int prime = 31;
/* 119 */     int result = super.hashCode();
/* 120 */     result = 31 * result + ((this.graphic == null) ? 0 : this.graphic.hashCode());
/* 121 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 126 */     if (this == obj)
/* 127 */       return true; 
/* 128 */     if (!super.equals(obj))
/* 129 */       return false; 
/* 130 */     if (getClass() != obj.getClass())
/* 131 */       return false; 
/* 132 */     PointSymbolizerImpl other = (PointSymbolizerImpl)obj;
/* 133 */     if (this.graphic == null) {
/* 134 */       if (other.graphic != null)
/* 135 */         return false; 
/* 136 */     } else if (!this.graphic.equals(other.graphic)) {
/* 137 */       return false;
/*     */     } 
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   static PointSymbolizerImpl cast(Symbolizer symbolizer) {
/* 142 */     if (symbolizer == null)
/* 143 */       return null; 
/* 144 */     if (symbolizer instanceof PointSymbolizerImpl)
/* 145 */       return (PointSymbolizerImpl)symbolizer; 
/* 146 */     if (symbolizer instanceof PointSymbolizer) {
/* 147 */       PointSymbolizer pointSymbolizer = (PointSymbolizer)symbolizer;
/* 148 */       PointSymbolizerImpl copy = new PointSymbolizerImpl();
/* 149 */       copy.setDescription(pointSymbolizer.getDescription());
/* 150 */       copy.setGeometryPropertyName(pointSymbolizer.getGeometryPropertyName());
/* 151 */       copy.setGraphic(pointSymbolizer.getGraphic());
/* 152 */       copy.setName(pointSymbolizer.getName());
/* 153 */       copy.setUnitOfMeasure(pointSymbolizer.getUnitOfMeasure());
/* 154 */       return copy;
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\PointSymbolizerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */