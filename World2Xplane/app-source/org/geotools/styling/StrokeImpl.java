/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Arrays;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.style.Graphic;
/*     */ import org.opengis.style.GraphicFill;
/*     */ import org.opengis.style.GraphicStroke;
/*     */ import org.opengis.style.Stroke;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class StrokeImpl implements Stroke, Cloneable {
/*     */   private FilterFactory filterFactory;
/*     */   
/*     */   private Expression color;
/*     */   
/*     */   private float[] dashArray;
/*     */   
/*     */   private Expression dashOffset;
/*     */   
/*     */   private GraphicImpl fillGraphic;
/*     */   
/*     */   private GraphicImpl strokeGraphic;
/*     */   
/*     */   private Expression lineCap;
/*     */   
/*     */   private Expression lineJoin;
/*     */   
/*     */   private Expression opacity;
/*     */   
/*     */   private Expression width;
/*     */   
/*     */   protected StrokeImpl() {
/*  57 */     this(CommonFactoryFinder.getFilterFactory(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   protected StrokeImpl(FilterFactory factory) {
/*  61 */     this.filterFactory = factory;
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory factory) {
/*  65 */     this.filterFactory = factory;
/*     */   }
/*     */   
/*     */   public Expression getColor() {
/*  81 */     return this.color;
/*     */   }
/*     */   
/*     */   public void setColor(Expression color) {
/* 100 */     if (this.color == color)
/*     */       return; 
/* 103 */     this.color = color;
/*     */   }
/*     */   
/*     */   public void setColor(String color) {
/* 119 */     setColor((Expression)this.filterFactory.literal(color));
/*     */   }
/*     */   
/*     */   public float[] getDashArray() {
/* 137 */     float[] ret = null;
/* 139 */     if (this.dashArray != null) {
/* 140 */       ret = new float[this.dashArray.length];
/* 141 */       System.arraycopy(this.dashArray, 0, ret, 0, this.dashArray.length);
/*     */     } else {
/* 143 */       float[] defaultDashArray = Stroke.DEFAULT.getDashArray();
/* 144 */       if (defaultDashArray == null)
/* 145 */         return null; 
/* 147 */       ret = new float[defaultDashArray.length];
/* 148 */       System.arraycopy(defaultDashArray, 0, ret, 0, defaultDashArray.length);
/*     */     } 
/* 151 */     return ret;
/*     */   }
/*     */   
/*     */   public void setDashArray(float[] dashPattern) {
/* 171 */     this.dashArray = dashPattern;
/*     */   }
/*     */   
/*     */   public Expression getDashOffset() {
/* 180 */     if (this.dashOffset == null)
/* 181 */       return Stroke.DEFAULT.getDashOffset(); 
/* 184 */     return this.dashOffset;
/*     */   }
/*     */   
/*     */   public void setDashOffset(Expression dashOffset) {
/* 194 */     if (dashOffset == null)
/*     */       return; 
/* 198 */     this.dashOffset = dashOffset;
/*     */   }
/*     */   
/*     */   public GraphicImpl getGraphicFill() {
/* 209 */     return this.fillGraphic;
/*     */   }
/*     */   
/*     */   public void setGraphicFill(Graphic fillGraphic) {
/* 220 */     if (this.fillGraphic == fillGraphic)
/*     */       return; 
/* 223 */     this.fillGraphic = GraphicImpl.cast(fillGraphic);
/*     */   }
/*     */   
/*     */   public GraphicImpl getGraphicStroke() {
/* 239 */     return this.strokeGraphic;
/*     */   }
/*     */   
/*     */   public void setGraphicStroke(Graphic strokeGraphic) {
/* 255 */     if (this.strokeGraphic == strokeGraphic)
/*     */       return; 
/* 258 */     this.strokeGraphic = GraphicImpl.cast(strokeGraphic);
/*     */   }
/*     */   
/*     */   public Expression getLineCap() {
/* 268 */     if (this.lineCap == null)
/* 270 */       return Stroke.DEFAULT.getLineCap(); 
/* 272 */     return this.lineCap;
/*     */   }
/*     */   
/*     */   public void setLineCap(Expression lineCap) {
/* 282 */     if (lineCap == null)
/*     */       return; 
/* 285 */     this.lineCap = lineCap;
/*     */   }
/*     */   
/*     */   public Expression getLineJoin() {
/* 295 */     if (this.lineCap == null)
/* 297 */       return Stroke.DEFAULT.getLineJoin(); 
/* 299 */     return this.lineJoin;
/*     */   }
/*     */   
/*     */   public void setLineJoin(Expression lineJoin) {
/* 309 */     if (lineJoin == null)
/*     */       return; 
/* 312 */     this.lineJoin = lineJoin;
/*     */   }
/*     */   
/*     */   public Expression getOpacity() {
/* 327 */     if (this.lineCap == null)
/* 328 */       return Stroke.DEFAULT.getOpacity(); 
/* 330 */     return this.opacity;
/*     */   }
/*     */   
/*     */   public void setOpacity(Expression opacity) {
/* 345 */     if (opacity == null)
/*     */       return; 
/* 348 */     this.opacity = opacity;
/*     */   }
/*     */   
/*     */   public Expression getWidth() {
/* 360 */     if (this.width == null)
/* 361 */       return (Expression)this.filterFactory.literal(1.0D); 
/* 363 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(Expression width) {
/* 375 */     this.width = width;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 379 */     StringBuffer out = new StringBuffer("org.geotools.styling.StrokeImpl:\n");
/* 381 */     out.append("\tColor " + this.color + "\n");
/* 382 */     out.append("\tWidth " + this.width + "\n");
/* 383 */     out.append("\tOpacity " + this.opacity + "\n");
/* 384 */     out.append("\tLineCap " + this.lineCap + "\n");
/* 385 */     out.append("\tLineJoin " + this.lineJoin + "\n");
/* 386 */     out.append("\tDash Array " + this.dashArray + "\n");
/* 387 */     out.append("\tDash Offset " + this.dashOffset + "\n");
/* 388 */     out.append("\tFill Graphic " + this.fillGraphic + "\n");
/* 389 */     out.append("\tStroke Graphic " + this.strokeGraphic);
/* 391 */     return out.toString();
/*     */   }
/*     */   
/*     */   public Color getColor(SimpleFeature feature) {
/* 395 */     return Color.decode((String)getColor().evaluate(feature));
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 399 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 403 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 418 */       StrokeImpl clone = (StrokeImpl)super.clone();
/* 420 */       if (this.dashArray != null) {
/* 421 */         clone.dashArray = new float[this.dashArray.length];
/* 422 */         System.arraycopy(this.dashArray, 0, clone.dashArray, 0, this.dashArray.length);
/*     */       } 
/* 426 */       if (this.fillGraphic != null && this.fillGraphic instanceof Cloneable)
/* 427 */         clone.fillGraphic = (GraphicImpl)this.fillGraphic.clone(); 
/* 430 */       if (this.strokeGraphic != null && this.fillGraphic instanceof Cloneable)
/* 431 */         clone.strokeGraphic = (GraphicImpl)this.strokeGraphic.clone(); 
/* 435 */       return clone;
/* 436 */     } catch (CloneNotSupportedException e) {
/* 438 */       throw new RuntimeException("Failed to clone StrokeImpl");
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 443 */     int PRIME = 1000003;
/* 444 */     int result = 0;
/* 446 */     if (this.color != null)
/* 447 */       result = 1000003 * result + this.color.hashCode(); 
/* 450 */     if (this.dashOffset != null)
/* 451 */       result = 1000003 * result + this.dashOffset.hashCode(); 
/* 454 */     if (this.fillGraphic != null)
/* 455 */       result = 1000003 * result + this.fillGraphic.hashCode(); 
/* 458 */     if (this.strokeGraphic != null)
/* 459 */       result = 1000003 * result + this.strokeGraphic.hashCode(); 
/* 462 */     if (this.lineCap != null)
/* 463 */       result = 1000003 * result + this.lineCap.hashCode(); 
/* 466 */     if (this.lineJoin != null)
/* 467 */       result = 1000003 * result + this.lineJoin.hashCode(); 
/* 470 */     if (this.opacity != null)
/* 471 */       result = 1000003 * result + this.opacity.hashCode(); 
/* 474 */     if (this.width != null)
/* 475 */       result = 1000003 * result + this.width.hashCode(); 
/* 478 */     if (this.dashArray != null)
/* 479 */       result = 1000003 * result + hashCodeDashArray(this.dashArray); 
/* 482 */     return result;
/*     */   }
/*     */   
/*     */   private int hashCodeDashArray(float[] a) {
/* 489 */     int PRIME = 1000003;
/* 491 */     if (a == null)
/* 492 */       return 0; 
/* 495 */     int result = 0;
/* 497 */     for (int i = 0; i < a.length; i++)
/* 498 */       result = 1000003 * result + Float.floatToIntBits(a[i]); 
/* 501 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 512 */     if (this == oth)
/* 513 */       return true; 
/* 516 */     if (oth == null)
/* 517 */       return false; 
/* 520 */     if (oth.getClass() != getClass())
/* 521 */       return false; 
/* 524 */     StrokeImpl other = (StrokeImpl)oth;
/* 527 */     if (!Utilities.equals(getColor(), other.getColor()))
/* 528 */       return false; 
/* 532 */     if (!Utilities.equals(getWidth(), other.getWidth()))
/* 533 */       return false; 
/* 536 */     if (!Utilities.equals(getLineCap(), other.getLineCap()))
/* 537 */       return false; 
/* 540 */     if (!Utilities.equals(getLineJoin(), other.getLineJoin()))
/* 541 */       return false; 
/* 544 */     if (!Utilities.equals(getOpacity(), other.getOpacity()))
/* 545 */       return false; 
/* 548 */     if (!Utilities.equals(getGraphicFill(), other.getGraphicFill()))
/* 549 */       return false; 
/* 552 */     if (!Utilities.equals(getGraphicStroke(), other.getGraphicStroke()))
/* 553 */       return false; 
/* 556 */     if (!Arrays.equals(getDashArray(), other.getDashArray()))
/* 557 */       return false; 
/* 560 */     return true;
/*     */   }
/*     */   
/*     */   static StrokeImpl cast(Stroke stroke) {
/* 564 */     if (stroke == null)
/* 565 */       return null; 
/* 567 */     if (stroke instanceof StrokeImpl)
/* 568 */       return (StrokeImpl)stroke; 
/* 571 */     StrokeImpl copy = new StrokeImpl();
/* 572 */     copy.setColor(stroke.getColor());
/* 573 */     if (stroke.getDashArray() != null) {
/* 574 */       float[] dashArray = stroke.getDashArray();
/* 575 */       float[] ret = new float[dashArray.length];
/* 576 */       System.arraycopy(dashArray, 0, ret, 0, dashArray.length);
/* 577 */       copy.setDashArray(ret);
/*     */     } 
/* 579 */     copy.setDashOffset(stroke.getDashOffset());
/* 580 */     copy.setGraphicFill(GraphicImpl.cast((Graphic)stroke.getGraphicFill()));
/* 581 */     copy.setGraphicStroke(GraphicImpl.cast((Graphic)stroke.getGraphicStroke()));
/* 582 */     copy.setLineCap(stroke.getLineCap());
/* 583 */     copy.setLineJoin(stroke.getLineJoin());
/* 584 */     copy.setOpacity(stroke.getOpacity());
/* 585 */     copy.setWidth(stroke.getWidth());
/* 587 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StrokeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */