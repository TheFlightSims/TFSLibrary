/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.PropertyUtil;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public final class RenderableImageAdapter implements RenderableImage, WritablePropertySource {
/*     */   private RenderableImage im;
/*     */   
/*  41 */   private PropertyChangeSupportJAI eventManager = null;
/*     */   
/*  44 */   private WritablePropertySourceImpl properties = null;
/*     */   
/*     */   public static RenderableImageAdapter wrapRenderableImage(RenderableImage im) {
/*  59 */     if (im == null)
/*  60 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  61 */     if (im instanceof RenderableImageAdapter)
/*  62 */       return (RenderableImageAdapter)im; 
/*  64 */     return new RenderableImageAdapter(im);
/*     */   }
/*     */   
/*     */   public RenderableImageAdapter(RenderableImage im) {
/*  74 */     if (im == null)
/*  75 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  78 */     this.im = im;
/*  79 */     this.eventManager = new PropertyChangeSupportJAI(this);
/*  80 */     this.properties = new WritablePropertySourceImpl(null, null, this.eventManager);
/*     */   }
/*     */   
/*     */   public final RenderableImage getWrappedImage() {
/*  90 */     return this.im;
/*     */   }
/*     */   
/*     */   public final Vector getSources() {
/* 102 */     return this.im.getSources();
/*     */   }
/*     */   
/*     */   public final Object getProperty(String name) {
/* 118 */     Object property = this.properties.getProperty(name);
/* 121 */     if (property == Image.UndefinedProperty)
/* 122 */       property = this.im.getProperty(name); 
/* 125 */     return property;
/*     */   }
/*     */   
/*     */   public Class getPropertyClass(String name) {
/* 142 */     Class propClass = this.properties.getPropertyClass(name);
/* 145 */     if (propClass == null) {
/* 147 */       Object propValue = getProperty(name);
/* 149 */       if (propValue != Image.UndefinedProperty)
/* 151 */         propClass = propValue.getClass(); 
/*     */     } 
/* 155 */     return propClass;
/*     */   }
/*     */   
/*     */   public final String[] getPropertyNames() {
/* 167 */     return RenderedImageAdapter.mergePropertyNames(this.properties.getPropertyNames(), this.im.getPropertyNames());
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames(String prefix) {
/* 184 */     return PropertyUtil.getPropertyNames(getPropertyNames(), prefix);
/*     */   }
/*     */   
/*     */   public void setProperty(String name, Object value) {
/* 199 */     this.properties.setProperty(name, value);
/*     */   }
/*     */   
/*     */   public void removeProperty(String name) {
/* 211 */     this.properties.removeProperty(name);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/* 221 */     this.eventManager.addPropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 232 */     this.eventManager.addPropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/* 243 */     this.eventManager.removePropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 254 */     this.eventManager.removePropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public final float getWidth() {
/* 265 */     return this.im.getWidth();
/*     */   }
/*     */   
/*     */   public final float getHeight() {
/* 275 */     return this.im.getHeight();
/*     */   }
/*     */   
/*     */   public final float getMinX() {
/* 282 */     return this.im.getMinX();
/*     */   }
/*     */   
/*     */   public final float getMinY() {
/* 289 */     return this.im.getMinY();
/*     */   }
/*     */   
/*     */   public final boolean isDynamic() {
/* 300 */     return this.im.isDynamic();
/*     */   }
/*     */   
/*     */   public final RenderedImage createScaledRendering(int w, int h, RenderingHints hints) {
/* 317 */     return this.im.createScaledRendering(w, h, hints);
/*     */   }
/*     */   
/*     */   public final RenderedImage createDefaultRendering() {
/* 331 */     return this.im.createDefaultRendering();
/*     */   }
/*     */   
/*     */   public final RenderedImage createRendering(RenderContext renderContext) {
/* 343 */     return this.im.createRendering(renderContext);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RenderableImageAdapter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */