/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.LegendItemSource;
/*     */ import org.jfree.chart.block.Arrangement;
/*     */ import org.jfree.chart.block.Block;
/*     */ import org.jfree.chart.block.BlockContainer;
/*     */ import org.jfree.chart.block.BorderArrangement;
/*     */ import org.jfree.chart.block.CenterArrangement;
/*     */ import org.jfree.chart.block.ColumnArrangement;
/*     */ import org.jfree.chart.block.FlowArrangement;
/*     */ import org.jfree.chart.block.LabelBlock;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class LegendTitle extends Title implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 2644010518533854633L;
/*     */   
/* 101 */   public static final Font DEFAULT_ITEM_FONT = new Font("SansSerif", 0, 12);
/*     */   
/* 105 */   public static final Paint DEFAULT_ITEM_PAINT = Color.black;
/*     */   
/*     */   private LegendItemSource[] sources;
/*     */   
/*     */   private transient Paint backgroundPaint;
/*     */   
/*     */   private RectangleEdge legendItemGraphicEdge;
/*     */   
/*     */   private RectangleAnchor legendItemGraphicAnchor;
/*     */   
/*     */   private RectangleAnchor legendItemGraphicLocation;
/*     */   
/*     */   private RectangleInsets legendItemGraphicPadding;
/*     */   
/*     */   private Font itemFont;
/*     */   
/*     */   private transient Paint itemPaint;
/*     */   
/*     */   private RectangleInsets itemLabelPadding;
/*     */   
/*     */   private BlockContainer items;
/*     */   
/*     */   private Arrangement hLayout;
/*     */   
/*     */   private Arrangement vLayout;
/*     */   
/*     */   private BlockContainer wrapper;
/*     */   
/*     */   public LegendTitle(LegendItemSource source) {
/* 155 */     this(source, (Arrangement)new FlowArrangement(), (Arrangement)new ColumnArrangement());
/*     */   }
/*     */   
/*     */   public LegendTitle(LegendItemSource source, Arrangement hLayout, Arrangement vLayout) {
/* 169 */     this.sources = new LegendItemSource[] { source };
/* 170 */     this.items = new BlockContainer(hLayout);
/* 171 */     this.hLayout = hLayout;
/* 172 */     this.vLayout = vLayout;
/* 173 */     this.backgroundPaint = null;
/* 174 */     this.legendItemGraphicEdge = RectangleEdge.LEFT;
/* 175 */     this.legendItemGraphicAnchor = RectangleAnchor.CENTER;
/* 176 */     this.legendItemGraphicLocation = RectangleAnchor.CENTER;
/* 177 */     this.legendItemGraphicPadding = new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D);
/* 178 */     this.itemFont = DEFAULT_ITEM_FONT;
/* 179 */     this.itemPaint = DEFAULT_ITEM_PAINT;
/* 180 */     this.itemLabelPadding = new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D);
/*     */   }
/*     */   
/*     */   public LegendItemSource[] getSources() {
/* 189 */     return this.sources;
/*     */   }
/*     */   
/*     */   public void setSources(LegendItemSource[] sources) {
/* 199 */     if (sources == null)
/* 200 */       throw new IllegalArgumentException("Null 'sources' argument."); 
/* 202 */     this.sources = sources;
/* 203 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getBackgroundPaint() {
/* 212 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */   public void setBackgroundPaint(Paint paint) {
/* 222 */     this.backgroundPaint = paint;
/* 223 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public RectangleEdge getLegendItemGraphicEdge() {
/* 232 */     return this.legendItemGraphicEdge;
/*     */   }
/*     */   
/*     */   public void setLegendItemGraphicEdge(RectangleEdge edge) {
/* 241 */     if (edge == null)
/* 242 */       throw new IllegalArgumentException("Null 'edge' argument."); 
/* 244 */     this.legendItemGraphicEdge = edge;
/* 245 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public RectangleAnchor getLegendItemGraphicAnchor() {
/* 254 */     return this.legendItemGraphicAnchor;
/*     */   }
/*     */   
/*     */   public void setLegendItemGraphicAnchor(RectangleAnchor anchor) {
/* 263 */     if (anchor == null)
/* 264 */       throw new IllegalArgumentException("Null 'anchor' point."); 
/* 266 */     this.legendItemGraphicAnchor = anchor;
/*     */   }
/*     */   
/*     */   public RectangleAnchor getLegendItemGraphicLocation() {
/* 275 */     return this.legendItemGraphicLocation;
/*     */   }
/*     */   
/*     */   public void setLegendItemGraphicLocation(RectangleAnchor anchor) {
/* 284 */     this.legendItemGraphicLocation = anchor;
/*     */   }
/*     */   
/*     */   public RectangleInsets getLegendItemGraphicPadding() {
/* 293 */     return this.legendItemGraphicPadding;
/*     */   }
/*     */   
/*     */   public void setLegendItemGraphicPadding(RectangleInsets padding) {
/* 303 */     if (padding == null)
/* 304 */       throw new IllegalArgumentException("Null 'padding' argument."); 
/* 306 */     this.legendItemGraphicPadding = padding;
/* 307 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Font getItemFont() {
/* 316 */     return this.itemFont;
/*     */   }
/*     */   
/*     */   public void setItemFont(Font font) {
/* 326 */     if (font == null)
/* 327 */       throw new IllegalArgumentException("Null 'font' argument."); 
/* 329 */     this.itemFont = font;
/* 330 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getItemPaint() {
/* 339 */     return this.itemPaint;
/*     */   }
/*     */   
/*     */   public void setItemPaint(Paint paint) {
/* 348 */     if (paint == null)
/* 349 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 351 */     this.itemPaint = paint;
/* 352 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public RectangleInsets getItemLabelPadding() {
/* 361 */     return this.itemLabelPadding;
/*     */   }
/*     */   
/*     */   public void setItemLabelPadding(RectangleInsets padding) {
/* 370 */     if (padding == null)
/* 371 */       throw new IllegalArgumentException("Null 'padding' argument."); 
/* 373 */     this.itemLabelPadding = padding;
/* 374 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   protected void fetchLegendItems() {
/* 381 */     this.items.clear();
/* 382 */     RectangleEdge p = getPosition();
/* 383 */     if (RectangleEdge.isTopOrBottom(p)) {
/* 384 */       this.items.setArrangement(this.hLayout);
/*     */     } else {
/* 387 */       this.items.setArrangement(this.vLayout);
/*     */     } 
/* 389 */     for (int s = 0; s < this.sources.length; s++) {
/* 390 */       LegendItemCollection legendItems = this.sources[s].getLegendItems();
/* 391 */       if (legendItems != null)
/* 392 */         for (int i = 0; i < legendItems.getItemCount(); i++) {
/* 393 */           LegendItem item = legendItems.get(i);
/* 394 */           Block block = createLegendItemBlock(item);
/* 395 */           this.items.add(block);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Block createLegendItemBlock(LegendItem item) {
/* 409 */     BlockContainer result = null;
/* 410 */     LegendGraphic lg = new LegendGraphic(item.getShape(), item.getFillPaint());
/* 413 */     lg.setShapeFilled(item.isShapeFilled());
/* 414 */     lg.setLine(item.getLine());
/* 415 */     lg.setLineStroke(item.getLineStroke());
/* 416 */     lg.setLinePaint(item.getLinePaint());
/* 417 */     lg.setLineVisible(item.isLineVisible());
/* 418 */     lg.setShapeVisible(item.isShapeVisible());
/* 419 */     lg.setShapeOutlineVisible(item.isShapeOutlineVisible());
/* 420 */     lg.setOutlinePaint(item.getOutlinePaint());
/* 421 */     lg.setOutlineStroke(item.getOutlineStroke());
/* 422 */     lg.setPadding(this.legendItemGraphicPadding);
/* 424 */     BlockContainer legendItem = new BlockContainer((Arrangement)new BorderArrangement());
/* 425 */     lg.setShapeAnchor(getLegendItemGraphicAnchor());
/* 426 */     lg.setShapeLocation(getLegendItemGraphicLocation());
/* 427 */     legendItem.add(lg, this.legendItemGraphicEdge);
/* 428 */     LabelBlock labelBlock = new LabelBlock(item.getLabel(), this.itemFont, this.itemPaint);
/* 430 */     labelBlock.setPadding(this.itemLabelPadding);
/* 431 */     labelBlock.setToolTipText(item.getToolTipText());
/* 432 */     legendItem.add((Block)labelBlock);
/* 434 */     result = new BlockContainer((Arrangement)new CenterArrangement());
/* 435 */     result.add((Block)legendItem);
/* 437 */     return (Block)result;
/*     */   }
/*     */   
/*     */   public BlockContainer getItemContainer() {
/* 446 */     return this.items;
/*     */   }
/*     */   
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
/* 459 */     Size2D result = new Size2D();
/* 460 */     fetchLegendItems();
/* 461 */     if (this.items.isEmpty())
/* 462 */       return result; 
/* 464 */     BlockContainer container = this.wrapper;
/* 465 */     if (container == null)
/* 466 */       container = this.items; 
/* 468 */     RectangleConstraint c = toContentConstraint(constraint);
/* 469 */     Size2D size = container.arrange(g2, c);
/* 470 */     result.height = calculateTotalHeight(size.height);
/* 471 */     result.width = calculateTotalWidth(size.width);
/* 472 */     return result;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area) {
/* 483 */     draw(g2, area, (Object)null);
/*     */   }
/*     */   
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
/* 497 */     Rectangle2D target = (Rectangle2D)area.clone();
/* 498 */     target = trimMargin(target);
/* 499 */     if (this.backgroundPaint != null) {
/* 500 */       g2.setPaint(this.backgroundPaint);
/* 501 */       g2.fill(target);
/*     */     } 
/* 503 */     getBorder().draw(g2, target);
/* 504 */     getBorder().getInsets().trim(target);
/* 505 */     BlockContainer container = this.wrapper;
/* 506 */     if (container == null)
/* 507 */       container = this.items; 
/* 509 */     target = trimPadding(target);
/* 510 */     return container.draw(g2, target, params);
/*     */   }
/*     */   
/*     */   public void setWrapper(BlockContainer wrapper) {
/* 519 */     this.wrapper = wrapper;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 530 */     if (obj == this)
/* 531 */       return true; 
/* 533 */     if (!(obj instanceof LegendTitle))
/* 534 */       return false; 
/* 536 */     if (!super.equals(obj))
/* 537 */       return false; 
/* 539 */     LegendTitle that = (LegendTitle)obj;
/* 540 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint))
/* 541 */       return false; 
/* 543 */     if (this.legendItemGraphicEdge != that.legendItemGraphicEdge)
/* 544 */       return false; 
/* 546 */     if (this.legendItemGraphicAnchor != that.legendItemGraphicAnchor)
/* 547 */       return false; 
/* 549 */     if (this.legendItemGraphicLocation != that.legendItemGraphicLocation)
/* 550 */       return false; 
/* 552 */     if (!this.itemFont.equals(that.itemFont))
/* 553 */       return false; 
/* 555 */     if (!this.itemPaint.equals(that.itemPaint))
/* 556 */       return false; 
/* 558 */     if (!this.hLayout.equals(that.hLayout))
/* 559 */       return false; 
/* 561 */     if (!this.vLayout.equals(that.vLayout))
/* 562 */       return false; 
/* 564 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 575 */     stream.defaultWriteObject();
/* 576 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/* 577 */     SerialUtilities.writePaint(this.itemPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 590 */     stream.defaultReadObject();
/* 591 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 592 */     this.itemPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\title\LegendTitle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */