/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;
/*     */ import org.jfree.chart.imagemap.URLTagFragmentGenerator;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class ChartEntity implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -4445994133561919083L;
/*     */   
/*     */   private transient Shape area;
/*     */   
/*     */   private String toolTipText;
/*     */   
/*     */   private String urlText;
/*     */   
/*     */   public ChartEntity(Shape area) {
/* 108 */     this(area, null);
/*     */   }
/*     */   
/*     */   public ChartEntity(Shape area, String toolTipText) {
/* 119 */     this(area, toolTipText, null);
/*     */   }
/*     */   
/*     */   public ChartEntity(Shape area, String toolTipText, String urlText) {
/* 131 */     if (area == null)
/* 132 */       throw new IllegalArgumentException("Null 'area' argument."); 
/* 134 */     this.area = area;
/* 135 */     this.toolTipText = toolTipText;
/* 136 */     this.urlText = urlText;
/*     */   }
/*     */   
/*     */   public Shape getArea() {
/* 145 */     return this.area;
/*     */   }
/*     */   
/*     */   public void setArea(Shape area) {
/* 158 */     if (area == null)
/* 159 */       throw new IllegalArgumentException("Null 'area' argument."); 
/* 161 */     this.area = area;
/*     */   }
/*     */   
/*     */   public String getToolTipText() {
/* 170 */     return this.toolTipText;
/*     */   }
/*     */   
/*     */   public void setToolTipText(String text) {
/* 179 */     this.toolTipText = text;
/*     */   }
/*     */   
/*     */   public String getURLText() {
/* 188 */     return this.urlText;
/*     */   }
/*     */   
/*     */   public void setURLText(String text) {
/* 197 */     this.urlText = text;
/*     */   }
/*     */   
/*     */   public String getShapeType() {
/* 207 */     if (this.area instanceof Rectangle2D)
/* 208 */       return "rect"; 
/* 211 */     return "poly";
/*     */   }
/*     */   
/*     */   public String getShapeCoords() {
/* 221 */     if (this.area instanceof Rectangle2D)
/* 222 */       return getRectCoords((Rectangle2D)this.area); 
/* 225 */     return getPolyCoords(this.area);
/*     */   }
/*     */   
/*     */   private String getRectCoords(Rectangle2D rectangle) {
/* 238 */     if (rectangle == null)
/* 239 */       throw new IllegalArgumentException("Null 'rectangle' argument."); 
/* 241 */     int x1 = (int)rectangle.getX();
/* 242 */     int y1 = (int)rectangle.getY();
/* 243 */     int x2 = x1 + (int)rectangle.getWidth();
/* 244 */     int y2 = y1 + (int)rectangle.getHeight();
/* 246 */     if (x2 == x1)
/* 247 */       x2++; 
/* 249 */     if (y2 == y1)
/* 250 */       y2++; 
/* 253 */     return x1 + "," + y1 + "," + x2 + "," + y2;
/*     */   }
/*     */   
/*     */   private String getPolyCoords(Shape shape) {
/* 265 */     if (shape == null)
/* 266 */       throw new IllegalArgumentException("Null 'shape' argument."); 
/* 268 */     StringBuffer result = new StringBuffer();
/* 269 */     boolean first = true;
/* 270 */     float[] coords = new float[6];
/* 271 */     PathIterator pi = shape.getPathIterator(null, 1.0D);
/* 272 */     while (!pi.isDone()) {
/* 273 */       pi.currentSegment(coords);
/* 274 */       if (first) {
/* 275 */         first = false;
/* 276 */         result.append((int)coords[0]);
/* 277 */         result.append(",").append((int)coords[1]);
/*     */       } else {
/* 280 */         result.append(",");
/* 281 */         result.append((int)coords[0]);
/* 282 */         result.append(",");
/* 283 */         result.append((int)coords[1]);
/*     */       } 
/* 285 */       pi.next();
/*     */     } 
/* 287 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String getImageMapAreaTag(ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, URLTagFragmentGenerator urlTagFragmentGenerator) {
/* 303 */     StringBuffer tag = new StringBuffer();
/* 304 */     boolean hasURL = (this.urlText == null) ? false : (!this.urlText.equals(""));
/* 306 */     boolean hasToolTip = (this.toolTipText == null) ? false : (!this.toolTipText.equals(""));
/* 308 */     if (hasURL || hasToolTip) {
/* 309 */       tag.append("<area shape=\"" + getShapeType() + "\"" + " coords=\"" + getShapeCoords() + "\"");
/* 313 */       if (hasToolTip)
/* 314 */         tag.append(toolTipTagFragmentGenerator.generateToolTipFragment(this.toolTipText)); 
/* 318 */       if (hasURL)
/* 319 */         tag.append(urlTagFragmentGenerator.generateURLFragment(this.urlText)); 
/* 325 */       if (!hasToolTip)
/* 326 */         tag.append(" alt=\"\""); 
/* 328 */       tag.append("/>");
/*     */     } 
/* 330 */     return tag.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 340 */     StringBuffer buf = new StringBuffer("ChartEntity: ");
/* 341 */     buf.append("tooltip = ");
/* 342 */     buf.append(this.toolTipText);
/* 343 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 354 */     if (obj == this)
/* 355 */       return true; 
/* 357 */     if (obj instanceof ChartEntity) {
/* 358 */       ChartEntity that = (ChartEntity)obj;
/* 359 */       if (!this.area.equals(that.area))
/* 360 */         return false; 
/* 362 */       if (!ObjectUtilities.equal(this.toolTipText, that.toolTipText))
/* 363 */         return false; 
/* 365 */       if (!ObjectUtilities.equal(this.urlText, that.urlText))
/* 366 */         return false; 
/* 368 */       return true;
/*     */     } 
/* 370 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 382 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 393 */     stream.defaultWriteObject();
/* 394 */     SerialUtilities.writeShape(this.area, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 407 */     stream.defaultReadObject();
/* 408 */     this.area = SerialUtilities.readShape(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\ChartEntity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */