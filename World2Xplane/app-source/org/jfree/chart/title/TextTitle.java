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
/*     */ import org.jfree.chart.block.BlockResult;
/*     */ import org.jfree.chart.block.EntityBlockParams;
/*     */ import org.jfree.chart.block.LengthConstraintType;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.StandardEntityCollection;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.G2TextMeasurer;
/*     */ import org.jfree.text.TextBlock;
/*     */ import org.jfree.text.TextBlockAnchor;
/*     */ import org.jfree.text.TextMeasurer;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.VerticalAlignment;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class TextTitle extends Title implements Serializable, Cloneable, PublicCloneable {
/*     */   private static final long serialVersionUID = 8372008692127477443L;
/*     */   
/* 124 */   public static final Font DEFAULT_FONT = new Font("SansSerif", 1, 12);
/*     */   
/* 128 */   public static final Paint DEFAULT_TEXT_PAINT = Color.black;
/*     */   
/*     */   private String text;
/*     */   
/*     */   private Font font;
/*     */   
/*     */   private HorizontalAlignment textAlignment;
/*     */   
/*     */   private transient Paint paint;
/*     */   
/*     */   private transient Paint backgroundPaint;
/*     */   
/*     */   private String toolTipText;
/*     */   
/*     */   private String urlText;
/*     */   
/*     */   private TextBlock content;
/*     */   
/*     */   private boolean expandToFitSpace = false;
/*     */   
/*     */   public TextTitle() {
/* 164 */     this("");
/*     */   }
/*     */   
/*     */   public TextTitle(String text) {
/* 173 */     this(text, DEFAULT_FONT, DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
/*     */   }
/*     */   
/*     */   public TextTitle(String text, Font font) {
/* 192 */     this(text, font, DEFAULT_TEXT_PAINT, Title.DEFAULT_POSITION, Title.DEFAULT_HORIZONTAL_ALIGNMENT, Title.DEFAULT_VERTICAL_ALIGNMENT, Title.DEFAULT_PADDING);
/*     */   }
/*     */   
/*     */   public TextTitle(String text, Font font, Paint paint, RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, RectangleInsets padding) {
/* 224 */     super(position, horizontalAlignment, verticalAlignment, padding);
/* 226 */     if (text == null)
/* 227 */       throw new NullPointerException("Null 'text' argument."); 
/* 229 */     if (font == null)
/* 230 */       throw new NullPointerException("Null 'font' argument."); 
/* 232 */     if (paint == null)
/* 233 */       throw new NullPointerException("Null 'paint' argument."); 
/* 235 */     this.text = text;
/* 236 */     this.font = font;
/* 237 */     this.paint = paint;
/* 241 */     this.textAlignment = horizontalAlignment;
/* 242 */     this.backgroundPaint = null;
/* 243 */     this.content = null;
/* 244 */     this.toolTipText = null;
/* 245 */     this.urlText = null;
/*     */   }
/*     */   
/*     */   public String getText() {
/* 255 */     return this.text;
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/* 265 */     if (text == null)
/* 266 */       throw new NullPointerException("Null 'text' argument."); 
/* 268 */     if (!this.text.equals(text)) {
/* 269 */       this.text = text;
/* 270 */       notifyListeners(new TitleChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public HorizontalAlignment getTextAlignment() {
/* 283 */     return this.textAlignment;
/*     */   }
/*     */   
/*     */   public void setTextAlignment(HorizontalAlignment alignment) {
/* 292 */     if (alignment == null)
/* 293 */       throw new IllegalArgumentException("Null 'alignment' argument."); 
/* 295 */     this.textAlignment = alignment;
/* 296 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 305 */     return this.font;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 315 */     if (font == null)
/* 316 */       throw new IllegalArgumentException("Null 'font' argument."); 
/* 318 */     if (!this.font.equals(font)) {
/* 319 */       this.font = font;
/* 320 */       notifyListeners(new TitleChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 330 */     return this.paint;
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 340 */     if (paint == null)
/* 341 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 343 */     if (!this.paint.equals(paint)) {
/* 344 */       this.paint = paint;
/* 345 */       notifyListeners(new TitleChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Paint getBackgroundPaint() {
/* 355 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */   public void setBackgroundPaint(Paint paint) {
/* 366 */     this.backgroundPaint = paint;
/* 367 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public String getToolTipText() {
/* 376 */     return this.toolTipText;
/*     */   }
/*     */   
/*     */   public void setToolTipText(String text) {
/* 386 */     this.toolTipText = text;
/* 387 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public String getURLText() {
/* 396 */     return this.urlText;
/*     */   }
/*     */   
/*     */   public void setURLText(String text) {
/* 406 */     this.urlText = text;
/* 407 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getExpandToFitSpace() {
/* 417 */     return this.expandToFitSpace;
/*     */   }
/*     */   
/*     */   public void setExpandToFitSpace(boolean expand) {
/* 428 */     this.expandToFitSpace = expand;
/* 429 */     notifyListeners(new TitleChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
/* 442 */     RectangleConstraint cc = toContentConstraint(constraint);
/* 443 */     LengthConstraintType w = cc.getWidthConstraintType();
/* 444 */     LengthConstraintType h = cc.getHeightConstraintType();
/* 445 */     Size2D contentSize = null;
/* 446 */     if (w == LengthConstraintType.NONE) {
/* 447 */       if (h == LengthConstraintType.NONE)
/* 448 */         throw new RuntimeException("Not yet implemented."); 
/* 450 */       if (h == LengthConstraintType.RANGE)
/* 451 */         throw new RuntimeException("Not yet implemented."); 
/* 453 */       if (h == LengthConstraintType.FIXED)
/* 454 */         throw new RuntimeException("Not yet implemented."); 
/* 457 */     } else if (w == LengthConstraintType.RANGE) {
/* 458 */       if (h == LengthConstraintType.NONE)
/* 459 */         throw new RuntimeException("Not yet implemented."); 
/* 461 */       if (h == LengthConstraintType.RANGE) {
/* 462 */         contentSize = arrangeRR(g2, cc.getWidthRange(), cc.getHeightRange());
/* 465 */       } else if (h == LengthConstraintType.FIXED) {
/* 466 */         throw new RuntimeException("Not yet implemented.");
/*     */       } 
/* 469 */     } else if (w == LengthConstraintType.FIXED) {
/* 470 */       if (h == LengthConstraintType.NONE)
/* 471 */         throw new RuntimeException("Not yet implemented."); 
/* 473 */       if (h == LengthConstraintType.RANGE)
/* 474 */         throw new RuntimeException("Not yet implemented."); 
/* 476 */       if (h == LengthConstraintType.FIXED)
/* 477 */         throw new RuntimeException("Not yet implemented."); 
/*     */     } 
/* 480 */     return new Size2D(calculateTotalWidth(contentSize.getWidth()), calculateTotalHeight(contentSize.getHeight()));
/*     */   }
/*     */   
/*     */   protected Size2D arrangeRR(Graphics2D g2, Range widthRange, Range heightRange) {
/* 497 */     float maxWidth = (float)widthRange.getUpperBound();
/* 498 */     g2.setFont(this.font);
/* 499 */     this.content = TextUtilities.createTextBlock(this.text, this.font, this.paint, maxWidth, (TextMeasurer)new G2TextMeasurer(g2));
/* 501 */     this.content.setLineAlignment(this.textAlignment);
/* 502 */     Size2D contentSize = this.content.calculateDimensions(g2);
/* 503 */     if (this.expandToFitSpace)
/* 504 */       return new Size2D(maxWidth, contentSize.getHeight()); 
/* 507 */     return contentSize;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area) {
/* 519 */     draw(g2, area, (Object)null);
/*     */   }
/*     */   
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
/* 535 */     if (this.content == null)
/* 536 */       return null; 
/* 538 */     area = trimMargin(area);
/* 539 */     drawBorder(g2, area);
/* 540 */     if (this.text.equals(""))
/* 541 */       return null; 
/* 543 */     ChartEntity entity = null;
/* 544 */     if (params instanceof EntityBlockParams) {
/* 545 */       EntityBlockParams p = (EntityBlockParams)params;
/* 546 */       if (p.getGenerateEntities())
/* 547 */         entity = new ChartEntity(area, this.toolTipText, this.urlText); 
/*     */     } 
/* 550 */     area = trimBorder(area);
/* 551 */     if (this.backgroundPaint != null) {
/* 552 */       g2.setPaint(this.backgroundPaint);
/* 553 */       g2.fill(area);
/*     */     } 
/* 555 */     area = trimPadding(area);
/* 556 */     RectangleEdge position = getPosition();
/* 557 */     if (position == RectangleEdge.TOP || position == RectangleEdge.BOTTOM) {
/* 558 */       drawHorizontal(g2, area);
/* 560 */     } else if (position == RectangleEdge.LEFT || position == RectangleEdge.RIGHT) {
/* 562 */       drawVertical(g2, area);
/*     */     } 
/* 564 */     BlockResult result = new BlockResult();
/* 565 */     if (entity != null) {
/* 566 */       StandardEntityCollection sec = new StandardEntityCollection();
/* 567 */       sec.add(entity);
/* 568 */       result.setEntityCollection((EntityCollection)sec);
/*     */     } 
/* 570 */     return result;
/*     */   }
/*     */   
/*     */   protected void drawHorizontal(Graphics2D g2, Rectangle2D area) {
/* 582 */     Rectangle2D titleArea = (Rectangle2D)area.clone();
/* 583 */     g2.setFont(this.font);
/* 584 */     g2.setPaint(this.paint);
/* 585 */     TextBlockAnchor anchor = null;
/* 586 */     float x = 0.0F;
/* 587 */     HorizontalAlignment horizontalAlignment = getHorizontalAlignment();
/* 588 */     if (horizontalAlignment == HorizontalAlignment.LEFT) {
/* 589 */       x = (float)titleArea.getX();
/* 590 */       anchor = TextBlockAnchor.TOP_LEFT;
/* 592 */     } else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
/* 593 */       x = (float)titleArea.getMaxX();
/* 594 */       anchor = TextBlockAnchor.TOP_RIGHT;
/* 596 */     } else if (horizontalAlignment == HorizontalAlignment.CENTER) {
/* 597 */       x = (float)titleArea.getCenterX();
/* 598 */       anchor = TextBlockAnchor.TOP_CENTER;
/*     */     } 
/* 600 */     float y = 0.0F;
/* 601 */     RectangleEdge position = getPosition();
/* 602 */     if (position == RectangleEdge.TOP) {
/* 603 */       y = (float)titleArea.getY();
/* 605 */     } else if (position == RectangleEdge.BOTTOM) {
/* 606 */       y = (float)titleArea.getMaxY();
/* 607 */       if (horizontalAlignment == HorizontalAlignment.LEFT) {
/* 608 */         anchor = TextBlockAnchor.BOTTOM_LEFT;
/* 610 */       } else if (horizontalAlignment == HorizontalAlignment.CENTER) {
/* 611 */         anchor = TextBlockAnchor.BOTTOM_CENTER;
/* 613 */       } else if (horizontalAlignment == HorizontalAlignment.RIGHT) {
/* 614 */         anchor = TextBlockAnchor.BOTTOM_RIGHT;
/*     */       } 
/*     */     } 
/* 617 */     this.content.draw(g2, x, y, anchor);
/*     */   }
/*     */   
/*     */   protected void drawVertical(Graphics2D g2, Rectangle2D area) {
/* 629 */     Rectangle2D titleArea = (Rectangle2D)area.clone();
/* 630 */     g2.setFont(this.font);
/* 631 */     g2.setPaint(this.paint);
/* 632 */     TextBlockAnchor anchor = null;
/* 633 */     float y = 0.0F;
/* 634 */     VerticalAlignment verticalAlignment = getVerticalAlignment();
/* 635 */     if (verticalAlignment == VerticalAlignment.TOP) {
/* 636 */       y = (float)titleArea.getY();
/* 637 */       anchor = TextBlockAnchor.TOP_RIGHT;
/* 639 */     } else if (verticalAlignment == VerticalAlignment.BOTTOM) {
/* 640 */       y = (float)titleArea.getMaxY();
/* 641 */       anchor = TextBlockAnchor.TOP_LEFT;
/* 643 */     } else if (verticalAlignment == VerticalAlignment.CENTER) {
/* 644 */       y = (float)titleArea.getCenterY();
/* 645 */       anchor = TextBlockAnchor.TOP_CENTER;
/*     */     } 
/* 647 */     float x = 0.0F;
/* 648 */     RectangleEdge position = getPosition();
/* 649 */     if (position == RectangleEdge.LEFT) {
/* 650 */       x = (float)titleArea.getX();
/* 652 */     } else if (position == RectangleEdge.RIGHT) {
/* 653 */       x = (float)titleArea.getMaxX();
/* 654 */       if (verticalAlignment == VerticalAlignment.TOP) {
/* 655 */         anchor = TextBlockAnchor.BOTTOM_RIGHT;
/* 657 */       } else if (verticalAlignment == VerticalAlignment.CENTER) {
/* 658 */         anchor = TextBlockAnchor.BOTTOM_CENTER;
/* 660 */       } else if (verticalAlignment == VerticalAlignment.BOTTOM) {
/* 661 */         anchor = TextBlockAnchor.BOTTOM_LEFT;
/*     */       } 
/*     */     } 
/* 664 */     this.content.draw(g2, x, y, anchor, x, y, -1.5707963267948966D);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 675 */     if (obj == this)
/* 676 */       return true; 
/* 678 */     if (!(obj instanceof TextTitle))
/* 679 */       return false; 
/* 681 */     if (!super.equals(obj))
/* 682 */       return false; 
/* 684 */     TextTitle that = (TextTitle)obj;
/* 685 */     if (!ObjectUtilities.equal(this.text, that.text))
/* 686 */       return false; 
/* 688 */     if (!ObjectUtilities.equal(this.font, that.font))
/* 689 */       return false; 
/* 691 */     if (!PaintUtilities.equal(this.paint, that.paint))
/* 692 */       return false; 
/* 694 */     if (this.textAlignment != that.textAlignment)
/* 695 */       return false; 
/* 697 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint))
/* 698 */       return false; 
/* 700 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 709 */     int result = super.hashCode();
/* 710 */     result = 29 * result + ((this.text != null) ? this.text.hashCode() : 0);
/* 711 */     result = 29 * result + ((this.font != null) ? this.font.hashCode() : 0);
/* 712 */     result = 29 * result + ((this.paint != null) ? this.paint.hashCode() : 0);
/* 713 */     result = 29 * result + ((this.backgroundPaint != null) ? this.backgroundPaint.hashCode() : 0);
/* 716 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 727 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 738 */     stream.defaultWriteObject();
/* 739 */     SerialUtilities.writePaint(this.paint, stream);
/* 740 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 754 */     stream.defaultReadObject();
/* 755 */     this.paint = SerialUtilities.readPaint(stream);
/* 756 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\title\TextTitle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */