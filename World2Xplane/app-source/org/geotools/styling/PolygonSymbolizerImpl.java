/*     */ package org.geotools.styling;
/*     */ 
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Displacement;
/*     */ import org.opengis.style.Fill;
/*     */ import org.opengis.style.PolygonSymbolizer;
/*     */ import org.opengis.style.Stroke;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.style.Symbolizer;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class PolygonSymbolizerImpl extends AbstractSymbolizer implements PolygonSymbolizer, Cloneable {
/*     */   private Expression offset;
/*     */   
/*     */   private DisplacementImpl disp;
/*     */   
/*  44 */   private Fill fill = new FillImpl();
/*     */   
/*  45 */   private StrokeImpl stroke = new StrokeImpl();
/*     */   
/*     */   protected PolygonSymbolizerImpl() {
/*  51 */     this((Stroke)null, (Fill)null, (Displacement)null, (Expression)null, (Unit<Length>)null, (String)null, (String)null, (Description)null);
/*     */   }
/*     */   
/*     */   protected PolygonSymbolizerImpl(Stroke stroke, Fill fill, Displacement disp, Expression offset, Unit<Length> uom, String geom, String name, Description desc) {
/*  62 */     super(name, desc, geom, uom);
/*  63 */     this.stroke = StrokeImpl.cast(stroke);
/*  64 */     this.fill = fill;
/*  65 */     this.disp = DisplacementImpl.cast(disp);
/*  66 */     this.offset = offset;
/*     */   }
/*     */   
/*     */   public Expression getPerpendicularOffset() {
/*  70 */     return this.offset;
/*     */   }
/*     */   
/*     */   public void setPerpendicularOffset(Expression offset) {
/*  74 */     this.offset = offset;
/*     */   }
/*     */   
/*     */   public Displacement getDisplacement() {
/*  78 */     return this.disp;
/*     */   }
/*     */   
/*     */   public void setDisplacement(Displacement displacement) {
/*  82 */     this.disp = DisplacementImpl.cast(displacement);
/*     */   }
/*     */   
/*     */   public Fill getFill() {
/*  91 */     return this.fill;
/*     */   }
/*     */   
/*     */   public void setFill(Fill fill) {
/* 101 */     if (this.fill == fill)
/*     */       return; 
/* 104 */     this.fill = FillImpl.cast(fill);
/*     */   }
/*     */   
/*     */   public StrokeImpl getStroke() {
/* 114 */     return this.stroke;
/*     */   }
/*     */   
/*     */   public void setStroke(Stroke stroke) {
/* 124 */     if (this.stroke == stroke)
/*     */       return; 
/* 127 */     this.stroke = StrokeImpl.cast(stroke);
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 136 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 140 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     PolygonSymbolizerImpl clone;
/*     */     try {
/* 155 */       clone = (PolygonSymbolizerImpl)super.clone();
/* 157 */       if (this.fill != null)
/* 158 */         clone.fill = (Fill)((Cloneable)this.fill).clone(); 
/* 161 */       if (this.stroke != null)
/* 162 */         clone.stroke = (StrokeImpl)this.stroke.clone(); 
/* 164 */     } catch (CloneNotSupportedException e) {
/* 165 */       throw new RuntimeException(e);
/*     */     } 
/* 168 */     return clone;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 173 */     int prime = 31;
/* 174 */     int result = super.hashCode();
/* 175 */     result = 31 * result + ((this.disp == null) ? 0 : this.disp.hashCode());
/* 176 */     result = 31 * result + ((this.fill == null) ? 0 : this.fill.hashCode());
/* 177 */     result = 31 * result + ((this.offset == null) ? 0 : this.offset.hashCode());
/* 178 */     result = 31 * result + ((this.stroke == null) ? 0 : this.stroke.hashCode());
/* 179 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 184 */     if (this == obj)
/* 185 */       return true; 
/* 186 */     if (!super.equals(obj))
/* 187 */       return false; 
/* 188 */     if (getClass() != obj.getClass())
/* 189 */       return false; 
/* 190 */     PolygonSymbolizerImpl other = (PolygonSymbolizerImpl)obj;
/* 191 */     if (this.disp == null) {
/* 192 */       if (other.disp != null)
/* 193 */         return false; 
/* 194 */     } else if (!this.disp.equals(other.disp)) {
/* 195 */       return false;
/*     */     } 
/* 196 */     if (this.fill == null) {
/* 197 */       if (other.fill != null)
/* 198 */         return false; 
/* 199 */     } else if (!this.fill.equals(other.fill)) {
/* 200 */       return false;
/*     */     } 
/* 201 */     if (this.offset == null) {
/* 202 */       if (other.offset != null)
/* 203 */         return false; 
/* 204 */     } else if (!this.offset.equals(other.offset)) {
/* 205 */       return false;
/*     */     } 
/* 206 */     if (this.stroke == null) {
/* 207 */       if (other.stroke != null)
/* 208 */         return false; 
/* 209 */     } else if (!this.stroke.equals(other.stroke)) {
/* 210 */       return false;
/*     */     } 
/* 211 */     return true;
/*     */   }
/*     */   
/*     */   static PolygonSymbolizerImpl cast(Symbolizer symbolizer) {
/* 215 */     if (symbolizer == null)
/* 216 */       return null; 
/* 218 */     if (symbolizer instanceof PolygonSymbolizerImpl)
/* 219 */       return (PolygonSymbolizerImpl)symbolizer; 
/* 221 */     if (symbolizer instanceof PolygonSymbolizer) {
/* 222 */       PolygonSymbolizer polygonSymbolizer = (PolygonSymbolizer)symbolizer;
/* 223 */       PolygonSymbolizerImpl copy = new PolygonSymbolizerImpl();
/* 224 */       copy.setStroke(StrokeImpl.cast(polygonSymbolizer.getStroke()));
/* 225 */       copy.setDescription(polygonSymbolizer.getDescription());
/* 226 */       copy.setDisplacement(polygonSymbolizer.getDisplacement());
/* 227 */       copy.setFill(polygonSymbolizer.getFill());
/* 228 */       copy.setGeometryPropertyName(polygonSymbolizer.getGeometryPropertyName());
/* 229 */       copy.setName(polygonSymbolizer.getName());
/* 230 */       copy.setPerpendicularOffset(polygonSymbolizer.getPerpendicularOffset());
/* 231 */       copy.setUnitOfMeasure(polygonSymbolizer.getUnitOfMeasure());
/* 232 */       return copy;
/*     */     } 
/* 235 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\PolygonSymbolizerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */