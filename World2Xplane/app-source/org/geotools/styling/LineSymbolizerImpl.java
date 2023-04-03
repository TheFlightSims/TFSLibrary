/*     */ package org.geotools.styling;
/*     */ 
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.LineSymbolizer;
/*     */ import org.opengis.style.Stroke;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.style.Symbolizer;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class LineSymbolizerImpl extends AbstractSymbolizer implements LineSymbolizer, Cloneable {
/*     */   private Expression offset;
/*     */   
/*  43 */   private StrokeImpl stroke = null;
/*     */   
/*     */   protected LineSymbolizerImpl() {
/*  49 */     this((Stroke)null, (Expression)null, (Unit<Length>)null, (String)null, (String)null, (Description)null);
/*     */   }
/*     */   
/*     */   protected LineSymbolizerImpl(Stroke stroke, Expression offset, Unit<Length> uom, String geom, String name, Description desc) {
/*  53 */     super(name, desc, geom, uom);
/*     */   }
/*     */   
/*     */   public Expression getPerpendicularOffset() {
/*  57 */     return this.offset;
/*     */   }
/*     */   
/*     */   public void setPerpendicularOffset(Expression offset) {
/*  61 */     this.offset = offset;
/*     */   }
/*     */   
/*     */   public StrokeImpl getStroke() {
/*  71 */     return this.stroke;
/*     */   }
/*     */   
/*     */   public void setStroke(Stroke stroke) {
/*  81 */     if (this.stroke == stroke)
/*     */       return; 
/*  84 */     this.stroke = StrokeImpl.cast(stroke);
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/*  93 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/*  97 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     LineSymbolizerImpl clone;
/*     */     try {
/* 111 */       clone = (LineSymbolizerImpl)super.clone();
/* 113 */       if (this.stroke != null && this.stroke instanceof Cloneable)
/* 114 */         clone.stroke = (StrokeImpl)this.stroke.clone(); 
/* 117 */     } catch (CloneNotSupportedException e) {
/* 118 */       throw new RuntimeException(e);
/*     */     } 
/* 121 */     return clone;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 125 */     StringBuffer buf = new StringBuffer();
/* 126 */     buf.append("<LineSymbolizerImp property=");
/* 127 */     buf.append(getGeometryPropertyName());
/* 128 */     buf.append(" uom=");
/* 129 */     buf.append(this.unitOfMeasure);
/* 130 */     buf.append(" stroke=");
/* 131 */     buf.append(this.stroke);
/* 132 */     buf.append(">");
/* 133 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 138 */     int prime = 31;
/* 139 */     int result = super.hashCode();
/* 140 */     result = 31 * result + ((this.offset == null) ? 0 : this.offset.hashCode());
/* 141 */     result = 31 * result + ((this.stroke == null) ? 0 : this.stroke.hashCode());
/* 142 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 147 */     if (this == obj)
/* 148 */       return true; 
/* 149 */     if (!super.equals(obj))
/* 150 */       return false; 
/* 151 */     if (getClass() != obj.getClass())
/* 152 */       return false; 
/* 153 */     LineSymbolizerImpl other = (LineSymbolizerImpl)obj;
/* 154 */     if (this.offset == null) {
/* 155 */       if (other.offset != null)
/* 156 */         return false; 
/* 157 */     } else if (!this.offset.equals(other.offset)) {
/* 158 */       return false;
/*     */     } 
/* 159 */     if (this.stroke == null) {
/* 160 */       if (other.stroke != null)
/* 161 */         return false; 
/* 162 */     } else if (!this.stroke.equals(other.stroke)) {
/* 163 */       return false;
/*     */     } 
/* 164 */     return true;
/*     */   }
/*     */   
/*     */   static LineSymbolizerImpl cast(Symbolizer symbolizer) {
/* 168 */     if (symbolizer == null)
/* 169 */       return null; 
/* 171 */     if (symbolizer instanceof LineSymbolizerImpl)
/* 172 */       return (LineSymbolizerImpl)symbolizer; 
/* 173 */     if (symbolizer instanceof LineSymbolizer) {
/* 174 */       LineSymbolizer lineSymbolizer = (LineSymbolizer)symbolizer;
/* 175 */       LineSymbolizerImpl copy = new LineSymbolizerImpl();
/* 176 */       copy.setDescription(lineSymbolizer.getDescription());
/* 177 */       copy.setGeometryPropertyName(lineSymbolizer.getGeometryPropertyName());
/* 178 */       copy.setName(lineSymbolizer.getName());
/* 179 */       copy.setPerpendicularOffset(lineSymbolizer.getPerpendicularOffset());
/* 180 */       copy.setStroke(lineSymbolizer.getStroke());
/* 181 */       copy.setUnitOfMeasure(lineSymbolizer.getUnitOfMeasure());
/* 182 */       return copy;
/*     */     } 
/* 184 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\LineSymbolizerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */