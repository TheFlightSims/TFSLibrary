/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ import javax.media.jai.remote.SerializerFactory;
/*     */ 
/*     */ public class MultiResolutionRenderableImage implements WritablePropertySource, RenderableImage, Serializable {
/*     */   protected transient RenderedImage[] renderedSource;
/*     */   
/*     */   private int numSources;
/*     */   
/*     */   protected float aspect;
/*     */   
/*     */   protected float minX;
/*     */   
/*     */   protected float minY;
/*     */   
/*     */   protected float width;
/*     */   
/*     */   protected float height;
/*     */   
/*  62 */   protected PropertyChangeSupportJAI eventManager = null;
/*     */   
/*  69 */   protected WritablePropertySourceImpl properties = null;
/*     */   
/*     */   private MultiResolutionRenderableImage() {
/*  72 */     this.eventManager = new PropertyChangeSupportJAI(this);
/*  73 */     this.properties = new WritablePropertySourceImpl(null, null, this.eventManager);
/*     */   }
/*     */   
/*     */   public MultiResolutionRenderableImage(Vector renderedSources, float minX, float minY, float height) {
/*  95 */     this();
/*  98 */     if (height <= 0.0F)
/*  99 */       throw new IllegalArgumentException(JaiI18N.getString("MultiResolutionRenderableImage0")); 
/* 102 */     this.numSources = renderedSources.size();
/* 103 */     this.renderedSource = new RenderedImage[this.numSources];
/* 104 */     for (int i = 0; i < this.numSources; i++)
/* 105 */       this.renderedSource[i] = renderedSources.elementAt(i); 
/* 109 */     int maxResWidth = this.renderedSource[0].getWidth();
/* 110 */     int maxResHeight = this.renderedSource[0].getHeight();
/* 111 */     this.aspect = maxResWidth / maxResHeight;
/* 113 */     this.minX = minX;
/* 114 */     this.width = height * this.aspect;
/* 116 */     this.minY = minY;
/* 117 */     this.height = height;
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/* 127 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 141 */     return this.properties.getPropertyNames();
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames(String prefix) {
/* 160 */     if (prefix == null)
/* 161 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 163 */     return this.properties.getPropertyNames(prefix);
/*     */   }
/*     */   
/*     */   public Class getPropertyClass(String name) {
/* 180 */     return this.properties.getPropertyClass(name);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 197 */     return this.properties.getProperty(name);
/*     */   }
/*     */   
/*     */   public void setProperty(String name, Object value) {
/* 212 */     this.properties.setProperty(name, value);
/*     */   }
/*     */   
/*     */   public void removeProperty(String name) {
/* 229 */     this.properties.removeProperty(name);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/* 239 */     this.eventManager.addPropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 252 */     this.eventManager.addPropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/* 263 */     this.eventManager.removePropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 274 */     this.eventManager.removePropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public float getWidth() {
/* 281 */     return this.width;
/*     */   }
/*     */   
/*     */   public float getHeight() {
/* 288 */     return this.height;
/*     */   }
/*     */   
/*     */   public float getMinX() {
/* 296 */     return this.minX;
/*     */   }
/*     */   
/*     */   public float getMaxX() {
/* 304 */     return this.minX + this.width;
/*     */   }
/*     */   
/*     */   public float getMinY() {
/* 312 */     return this.minY;
/*     */   }
/*     */   
/*     */   public float getMaxY() {
/* 320 */     return this.minY + this.height;
/*     */   }
/*     */   
/*     */   public boolean isDynamic() {
/* 329 */     return false;
/*     */   }
/*     */   
/*     */   public RenderedImage createScaledRendering(int width, int height, RenderingHints hints) {
/* 351 */     if (width <= 0 && height <= 0)
/* 352 */       throw new IllegalArgumentException(JaiI18N.getString("MultiResolutionRenderableImage1")); 
/* 356 */     int res = this.numSources - 1;
/* 357 */     while (res > 0) {
/* 358 */       if (height > 0) {
/* 359 */         int imh = this.renderedSource[res].getHeight();
/* 360 */         if (imh >= height)
/*     */           break; 
/*     */       } else {
/* 364 */         int imw = this.renderedSource[res].getWidth();
/* 365 */         if (imw >= width)
/*     */           break; 
/*     */       } 
/* 369 */       res--;
/*     */     } 
/* 372 */     RenderedImage source = this.renderedSource[res];
/* 373 */     if (width <= 0) {
/* 374 */       width = Math.round((height * source.getWidth() / source.getHeight()));
/* 375 */     } else if (height <= 0) {
/* 376 */       height = Math.round((width * source.getHeight() / source.getWidth()));
/*     */     } 
/* 378 */     double sx = width / source.getWidth();
/* 379 */     double sy = height / source.getHeight();
/* 380 */     double tx = (getMinX() - source.getMinX()) * sx;
/* 381 */     double ty = (getMinY() - source.getMinY()) * sy;
/* 383 */     Interpolation interp = Interpolation.getInstance(0);
/* 385 */     if (hints != null) {
/* 386 */       Object obj = hints.get(JAI.KEY_INTERPOLATION);
/* 387 */       if (obj != null)
/* 388 */         interp = (Interpolation)obj; 
/*     */     } 
/* 392 */     ParameterBlock pb = new ParameterBlock();
/* 393 */     pb.addSource(source);
/* 394 */     pb.add((float)sx);
/* 395 */     pb.add((float)sy);
/* 396 */     pb.add((float)tx);
/* 397 */     pb.add((float)ty);
/* 398 */     pb.add(interp);
/* 400 */     return JAI.create("scale", pb, (RenderingHints)null);
/*     */   }
/*     */   
/*     */   public RenderedImage createDefaultRendering() {
/* 408 */     return this.renderedSource[0];
/*     */   }
/*     */   
/*     */   public RenderedImage createRendering(RenderContext renderContext) {
/* 432 */     if (renderContext == null)
/* 433 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 437 */     AffineTransform usr2dev = renderContext.getTransform();
/* 438 */     RenderingHints hints = renderContext.getRenderingHints();
/* 440 */     int type = usr2dev.getType();
/* 441 */     if (type == 2 || type == 4) {
/* 443 */       int width = (int)Math.ceil(usr2dev.getScaleX() * getWidth());
/* 444 */       int i = (int)Math.ceil(usr2dev.getScaleY() * getHeight());
/* 446 */       return createScaledRendering(width, i, hints);
/*     */     } 
/* 451 */     int height = (int)Math.ceil(Math.sqrt(usr2dev.getDeterminant()) * getHeight());
/* 453 */     int res = this.numSources - 1;
/* 454 */     while (res > 0) {
/* 455 */       int imh = this.renderedSource[res].getHeight();
/* 456 */       if (imh >= height)
/*     */         break; 
/* 459 */       res--;
/*     */     } 
/* 462 */     RenderedImage source = this.renderedSource[res];
/* 463 */     double sx = getWidth() / source.getWidth();
/* 464 */     double sy = getHeight() / source.getHeight();
/* 466 */     AffineTransform transform = new AffineTransform();
/* 467 */     transform.translate(-source.getMinX(), -source.getMinY());
/* 468 */     transform.scale(sx, sy);
/* 469 */     transform.translate(getMinX(), getMinY());
/* 470 */     transform.preConcatenate(usr2dev);
/* 472 */     Interpolation interp = Interpolation.getInstance(0);
/* 474 */     if (hints != null) {
/* 475 */       Object obj = hints.get(JAI.KEY_INTERPOLATION);
/* 476 */       if (obj != null)
/* 477 */         interp = (Interpolation)obj; 
/*     */     } 
/* 481 */     ParameterBlock pb = new ParameterBlock();
/* 482 */     pb.addSource(source);
/* 483 */     pb.add(transform);
/* 484 */     pb.add(interp);
/* 486 */     return JAI.create("affine", pb, (RenderingHints)null);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 496 */     Object[] sources = new Object[this.numSources];
/* 499 */     for (int i = 0; i < this.numSources; i++) {
/* 500 */       if (this.renderedSource[i] instanceof Serializable) {
/* 502 */         sources[i] = this.renderedSource[i];
/*     */       } else {
/* 505 */         sources[i] = SerializerFactory.getState(this.renderedSource[i]);
/*     */       } 
/*     */     } 
/* 510 */     out.defaultWriteObject();
/* 513 */     out.writeObject(sources);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 524 */     in.defaultReadObject();
/* 527 */     Object[] source = (Object[])in.readObject();
/* 528 */     this.numSources = source.length;
/* 529 */     this.renderedSource = new RenderedImage[this.numSources];
/* 530 */     for (int i = 0; i < this.numSources; i++) {
/* 531 */       if (source[i] instanceof SerializableState) {
/* 532 */         SerializableState ss = (SerializableState)source[i];
/* 533 */         this.renderedSource[i] = (RenderedImage)ss.getObject();
/*     */       } else {
/* 534 */         this.renderedSource[i] = (RenderedImage)source[i];
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\MultiResolutionRenderableImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */