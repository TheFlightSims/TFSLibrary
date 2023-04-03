/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.jfree.chart.block.AbstractBlock;
/*     */ import org.jfree.chart.block.Block;
/*     */ import org.jfree.chart.event.TitleChangeEvent;
/*     */ import org.jfree.chart.event.TitleChangeListener;
/*     */ import org.jfree.ui.HorizontalAlignment;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.VerticalAlignment;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public abstract class Title extends AbstractBlock implements Block, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -6675162505277817221L;
/*     */   
/*  99 */   public static final RectangleEdge DEFAULT_POSITION = RectangleEdge.TOP;
/*     */   
/* 103 */   public static final HorizontalAlignment DEFAULT_HORIZONTAL_ALIGNMENT = HorizontalAlignment.CENTER;
/*     */   
/* 107 */   public static final VerticalAlignment DEFAULT_VERTICAL_ALIGNMENT = VerticalAlignment.CENTER;
/*     */   
/* 110 */   public static final RectangleInsets DEFAULT_PADDING = new RectangleInsets(1.0D, 1.0D, 1.0D, 1.0D);
/*     */   
/*     */   private RectangleEdge position;
/*     */   
/*     */   private HorizontalAlignment horizontalAlignment;
/*     */   
/*     */   private VerticalAlignment verticalAlignment;
/*     */   
/*     */   private transient EventListenerList listenerList;
/*     */   
/*     */   private boolean notify;
/*     */   
/*     */   protected Title() {
/* 135 */     this(DEFAULT_POSITION, DEFAULT_HORIZONTAL_ALIGNMENT, DEFAULT_VERTICAL_ALIGNMENT, DEFAULT_PADDING);
/*     */   }
/*     */   
/*     */   protected Title(RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
/* 157 */     this(position, horizontalAlignment, verticalAlignment, DEFAULT_PADDING);
/*     */   }
/*     */   
/*     */   protected Title(RectangleEdge position, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, RectangleInsets padding) {
/* 184 */     if (position == null)
/* 185 */       throw new IllegalArgumentException("Null 'position' argument."); 
/* 187 */     if (horizontalAlignment == null)
/* 188 */       throw new IllegalArgumentException("Null 'horizontalAlignment' argument."); 
/* 193 */     if (verticalAlignment == null)
/* 194 */       throw new IllegalArgumentException("Null 'verticalAlignment' argument."); 
/* 198 */     if (padding == null)
/* 199 */       throw new IllegalArgumentException("Null 'spacer' argument."); 
/* 202 */     this.position = position;
/* 203 */     this.horizontalAlignment = horizontalAlignment;
/* 204 */     this.verticalAlignment = verticalAlignment;
/* 205 */     setPadding(padding);
/* 206 */     this.listenerList = new EventListenerList();
/* 207 */     this.notify = true;
/*     */   }
/*     */   
/*     */   public RectangleEdge getPosition() {
/* 217 */     return this.position;
/*     */   }
/*     */   
/*     */   public void setPosition(RectangleEdge position) {
/* 227 */     if (position == null)
/* 228 */       throw new IllegalArgumentException("Null 'position' argument."); 
/* 230 */     if (this.position != position) {
/* 231 */       this.position = position;
/* 232 */       notifyListeners(new TitleChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public HorizontalAlignment getHorizontalAlignment() {
/* 242 */     return this.horizontalAlignment;
/*     */   }
/*     */   
/*     */   public void setHorizontalAlignment(HorizontalAlignment alignment) {
/* 253 */     if (alignment == null)
/* 254 */       throw new IllegalArgumentException("Null 'alignment' argument."); 
/* 256 */     if (this.horizontalAlignment != alignment) {
/* 257 */       this.horizontalAlignment = alignment;
/* 258 */       notifyListeners(new TitleChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public VerticalAlignment getVerticalAlignment() {
/* 268 */     return this.verticalAlignment;
/*     */   }
/*     */   
/*     */   public void setVerticalAlignment(VerticalAlignment alignment) {
/* 279 */     if (alignment == null)
/* 280 */       throw new IllegalArgumentException("Null 'alignment' argument."); 
/* 282 */     if (this.verticalAlignment != alignment) {
/* 283 */       this.verticalAlignment = alignment;
/* 284 */       notifyListeners(new TitleChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getNotify() {
/* 295 */     return this.notify;
/*     */   }
/*     */   
/*     */   public void setNotify(boolean flag) {
/* 306 */     this.notify = flag;
/* 307 */     if (flag)
/* 308 */       notifyListeners(new TitleChangeEvent(this)); 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 336 */     Title duplicate = (Title)super.clone();
/* 337 */     duplicate.listenerList = new EventListenerList();
/* 339 */     return duplicate;
/*     */   }
/*     */   
/*     */   public void addChangeListener(TitleChangeListener listener) {
/* 348 */     this.listenerList.add(TitleChangeListener.class, listener);
/*     */   }
/*     */   
/*     */   public void removeChangeListener(TitleChangeListener listener) {
/* 357 */     this.listenerList.remove(TitleChangeListener.class, listener);
/*     */   }
/*     */   
/*     */   protected void notifyListeners(TitleChangeEvent event) {
/* 368 */     if (this.notify) {
/* 369 */       Object[] listeners = this.listenerList.getListenerList();
/* 370 */       for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 371 */         if (listeners[i] == TitleChangeListener.class)
/* 372 */           ((TitleChangeListener)listeners[i + 1]).titleChanged(event); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 388 */     if (obj == this)
/* 389 */       return true; 
/* 391 */     if (!(obj instanceof Title))
/* 392 */       return false; 
/* 394 */     if (!super.equals(obj))
/* 395 */       return false; 
/* 397 */     Title that = (Title)obj;
/* 398 */     if (this.position != that.position)
/* 399 */       return false; 
/* 401 */     if (this.horizontalAlignment != that.horizontalAlignment)
/* 402 */       return false; 
/* 404 */     if (this.verticalAlignment != that.verticalAlignment)
/* 405 */       return false; 
/* 407 */     if (this.notify != that.notify)
/* 408 */       return false; 
/* 410 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 419 */     int result = 193;
/* 420 */     result = 37 * result + ObjectUtilities.hashCode(this.position);
/* 421 */     result = 37 * result + ObjectUtilities.hashCode(this.horizontalAlignment);
/* 423 */     result = 37 * result + ObjectUtilities.hashCode(this.verticalAlignment);
/* 424 */     return result;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 435 */     stream.defaultWriteObject();
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 448 */     stream.defaultReadObject();
/* 449 */     this.listenerList = new EventListenerList();
/*     */   }
/*     */   
/*     */   public abstract void draw(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\title\Title.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */