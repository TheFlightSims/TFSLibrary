/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.VerticalAlignment;
/*     */ 
/*     */ public class ImageTitle extends Title {
/*     */   private Image image;
/*     */   
/*     */   public ImageTitle(Image image) {
/*  94 */     this(image, image.getHeight(null), image.getWidth(null), Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
/*     */   }
/*     */   
/*     */   public ImageTitle(Image image, RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
/* 111 */     this(image, image.getHeight(null), image.getWidth(null), position, horizontalAlignment, verticalAlignment, Title.DEFAULT_PADDING);
/*     */   }
/*     */   
/*     */   public ImageTitle(Image image, int height, int width, RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, RectangleInsets padding) {
/* 135 */     super(position, horizontalAlignment, verticalAlignment, padding);
/* 136 */     if (image == null)
/* 137 */       throw new NullPointerException("Null 'image' argument."); 
/* 139 */     this.image = image;
/* 140 */     setHeight(height);
/* 141 */     setWidth(width);
/*     */   }
/*     */   
/*     */   public Image getImage() {
/* 151 */     return this.image;
/*     */   }
/*     */   
/*     */   public void setImage(Image image) {
/* 161 */     if (image == null)
/* 162 */       throw new NullPointerException("Null 'image' argument."); 
/* 164 */     this.image = image;
/* 165 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D titleArea) {
/* 178 */     RectangleEdge position = getPosition();
/* 179 */     if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
/* 180 */       drawHorizontal(g2, titleArea);
/* 182 */     } else if (position == RectangleEdge.LEFT || position == RectangleEdge.RIGHT) {
/* 184 */       drawVertical(g2, titleArea);
/*     */     } else {
/* 187 */       throw new RuntimeException("Invalid title position.");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Size2D drawHorizontal(Graphics2D g2, Rectangle2D chartArea) {
/* 203 */     double startY = 0.0D;
/* 204 */     double topSpace = 0.0D;
/* 205 */     double bottomSpace = 0.0D;
/* 206 */     double leftSpace = 0.0D;
/* 207 */     double rightSpace = 0.0D;
/* 209 */     double w = getWidth();
/* 210 */     double h = getHeight();
/* 211 */     RectangleInsets padding = getPadding();
/* 212 */     topSpace = padding.calculateTopOutset(h);
/* 213 */     bottomSpace = padding.calculateBottomOutset(h);
/* 214 */     leftSpace = padding.calculateLeftOutset(w);
/* 215 */     rightSpace = padding.calculateRightOutset(w);
/* 217 */     if (getPosition() == RectangleEdge.TOP) {
/* 218 */       startY = chartArea.getY() + topSpace;
/*     */     } else {
/* 221 */       startY = chartArea.getY() + chartArea.getHeight() - bottomSpace - h;
/*     */     } 
/* 225 */     HorizontalAlignment horizontalAlignment = getHorizontalAlignment();
/* 226 */     double startX = 0.0D;
/* 227 */     if (horizontalAlignment == HorizontalAlignment.CENTER) {
/* 228 */       startX = chartArea.getX() + leftSpace + chartArea.getWidth() / 2.0D - w / 2.0D;
/* 231 */     } else if (horizontalAlignment == HorizontalAlignment.LEFT) {
/* 232 */       startX = chartArea.getX() + leftSpace;
/* 234 */     } else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
/* 235 */       startX = chartArea.getX() + chartArea.getWidth() - rightSpace - w;
/*     */     } 
/* 237 */     g2.drawImage(this.image, (int)startX, (int)startY, (int)w, (int)h, null);
/* 240 */     return new Size2D(chartArea.getWidth() + leftSpace + rightSpace, h + topSpace + bottomSpace);
/*     */   }
/*     */   
/*     */   protected Size2D drawVertical(Graphics2D g2, Rectangle2D chartArea) {
/* 257 */     double startX = 0.0D;
/* 258 */     double topSpace = 0.0D;
/* 259 */     double bottomSpace = 0.0D;
/* 260 */     double leftSpace = 0.0D;
/* 261 */     double rightSpace = 0.0D;
/* 263 */     double w = getWidth();
/* 264 */     double h = getHeight();
/* 266 */     RectangleInsets padding = getPadding();
/* 267 */     if (padding != null) {
/* 268 */       topSpace = padding.calculateTopOutset(h);
/* 269 */       bottomSpace = padding.calculateBottomOutset(h);
/* 270 */       leftSpace = padding.calculateLeftOutset(w);
/* 271 */       rightSpace = padding.calculateRightOutset(w);
/*     */     } 
/* 274 */     if (getPosition() == RectangleEdge.LEFT) {
/* 275 */       startX = chartArea.getX() + leftSpace;
/*     */     } else {
/* 278 */       startX = chartArea.getMaxX() - rightSpace - w;
/*     */     } 
/* 282 */     VerticalAlignment alignment = getVerticalAlignment();
/* 283 */     double startY = 0.0D;
/* 284 */     if (alignment == VerticalAlignment.CENTER) {
/* 285 */       startY = chartArea.getMinY() + topSpace + chartArea.getHeight() / 2.0D - h / 2.0D;
/* 288 */     } else if (alignment == VerticalAlignment.TOP) {
/* 289 */       startY = chartArea.getMinY() + topSpace;
/* 291 */     } else if (alignment == VerticalAlignment.BOTTOM) {
/* 292 */       startY = chartArea.getMaxY() - bottomSpace - h;
/*     */     } 
/* 295 */     g2.drawImage(this.image, (int)startX, (int)startY, (int)w, (int)h, null);
/* 298 */     return new Size2D(chartArea.getWidth() + leftSpace + rightSpace, h + topSpace + bottomSpace);
/*     */   }
/*     */   
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
/* 313 */     draw(g2, area);
/* 314 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\title\ImageTitle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */