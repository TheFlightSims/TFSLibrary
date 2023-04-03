/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.PropertyUtil;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Image;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.ContextualRenderedImageFactory;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderContext;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.io.Serializable;
/*      */ import java.util.HashSet;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.registry.CRIFRegistry;
/*      */ import javax.media.jai.util.CaselessStringKey;
/*      */ 
/*      */ public class RenderableOp implements RenderableImage, OperationNode, WritablePropertySource, Serializable {
/*  189 */   protected PropertyChangeSupportJAI eventManager = null;
/*      */   
/*  196 */   protected WritablePropertySourceImpl properties = null;
/*      */   
/*      */   protected OperationNodeSupport nodeSupport;
/*      */   
/*      */   protected transient PropertySource thePropertySource;
/*      */   
/*  215 */   protected transient ContextualRenderedImageFactory crif = null;
/*      */   
/*      */   public RenderableOp(OperationRegistry registry, String opName, ParameterBlock pb, RenderingHints hints) {
/*  249 */     if (pb == null) {
/*  251 */       pb = new ParameterBlock();
/*      */     } else {
/*  254 */       pb = (ParameterBlock)pb.clone();
/*      */     } 
/*  258 */     if (hints != null)
/*  259 */       hints = (RenderingHints)hints.clone(); 
/*  263 */     this.eventManager = new PropertyChangeSupportJAI(this);
/*  265 */     this.properties = new WritablePropertySourceImpl(null, null, this.eventManager);
/*  267 */     this.nodeSupport = new OperationNodeSupport(getRegistryModeName(), opName, registry, pb, hints, this.eventManager);
/*      */   }
/*      */   
/*      */   public RenderableOp(OperationRegistry registry, String opName, ParameterBlock pb) {
/*  300 */     this(registry, opName, pb, null);
/*      */   }
/*      */   
/*      */   public RenderableOp(String opName, ParameterBlock pb) {
/*  323 */     this(null, opName, pb);
/*      */   }
/*      */   
/*      */   public String getRegistryModeName() {
/*  334 */     return RegistryMode.getMode("renderable").getName();
/*      */   }
/*      */   
/*      */   public synchronized OperationRegistry getRegistry() {
/*  345 */     return this.nodeSupport.getRegistry();
/*      */   }
/*      */   
/*      */   public synchronized void setRegistry(OperationRegistry registry) {
/*  358 */     this.nodeSupport.setRegistry(registry);
/*      */   }
/*      */   
/*      */   public String getOperationName() {
/*  366 */     return this.nodeSupport.getOperationName();
/*      */   }
/*      */   
/*      */   public synchronized void setOperationName(String opName) {
/*  383 */     this.nodeSupport.setOperationName(opName);
/*      */   }
/*      */   
/*      */   public ParameterBlock getParameterBlock() {
/*  388 */     return (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/*      */   }
/*      */   
/*      */   public synchronized void setParameterBlock(ParameterBlock pb) {
/*  417 */     this.nodeSupport.setParameterBlock((pb == null) ? new ParameterBlock() : (ParameterBlock)pb.clone());
/*      */   }
/*      */   
/*      */   public RenderingHints getRenderingHints() {
/*  429 */     RenderingHints hints = this.nodeSupport.getRenderingHints();
/*  430 */     return (hints == null) ? null : (RenderingHints)hints.clone();
/*      */   }
/*      */   
/*      */   public synchronized void setRenderingHints(RenderingHints hints) {
/*  448 */     if (hints != null)
/*  449 */       hints = (RenderingHints)hints.clone(); 
/*  451 */     this.nodeSupport.setRenderingHints(hints);
/*      */   }
/*      */   
/*      */   private Vector getRenderableSources() {
/*  457 */     Vector sources = null;
/*  459 */     int numSrcs = this.nodeSupport.getParameterBlock().getNumSources();
/*  460 */     if (numSrcs > 0) {
/*  461 */       sources = new Vector();
/*  462 */       for (int i = 0; i < numSrcs; i++) {
/*  463 */         Object o = this.nodeSupport.getParameterBlock().getSource(i);
/*  464 */         if (o instanceof RenderableImage)
/*  465 */           sources.add(o); 
/*      */       } 
/*      */     } 
/*  469 */     return sources;
/*      */   }
/*      */   
/*      */   public Vector getSources() {
/*  482 */     return getRenderableSources();
/*      */   }
/*      */   
/*      */   private synchronized ContextualRenderedImageFactory findCRIF() {
/*  487 */     if (this.crif == null)
/*  489 */       this.crif = CRIFRegistry.get(getRegistry(), getOperationName()); 
/*  491 */     if (this.crif == null)
/*  492 */       throw new RuntimeException(JaiI18N.getString("RenderableOp2")); 
/*  495 */     return this.crif;
/*      */   }
/*      */   
/*      */   public float getWidth() {
/*  504 */     findCRIF();
/*  505 */     ParameterBlock paramBlock = ImageUtil.evaluateParameters(this.nodeSupport.getParameterBlock());
/*  507 */     Rectangle2D boundingBox = this.crif.getBounds2D(paramBlock);
/*  508 */     return (float)boundingBox.getWidth();
/*      */   }
/*      */   
/*      */   public float getHeight() {
/*  517 */     findCRIF();
/*  518 */     ParameterBlock paramBlock = ImageUtil.evaluateParameters(this.nodeSupport.getParameterBlock());
/*  520 */     Rectangle2D boundingBox = this.crif.getBounds2D(paramBlock);
/*  521 */     return (float)boundingBox.getHeight();
/*      */   }
/*      */   
/*      */   public float getMinX() {
/*  528 */     findCRIF();
/*  529 */     ParameterBlock paramBlock = ImageUtil.evaluateParameters(this.nodeSupport.getParameterBlock());
/*  531 */     Rectangle2D boundingBox = this.crif.getBounds2D(paramBlock);
/*  532 */     return (float)boundingBox.getX();
/*      */   }
/*      */   
/*      */   public float getMinY() {
/*  539 */     findCRIF();
/*  540 */     ParameterBlock paramBlock = ImageUtil.evaluateParameters(this.nodeSupport.getParameterBlock());
/*  542 */     Rectangle2D boundingBox = this.crif.getBounds2D(paramBlock);
/*  543 */     return (float)boundingBox.getY();
/*      */   }
/*      */   
/*      */   public RenderedImage createDefaultRendering() {
/*  580 */     Dimension defaultDimension = null;
/*  581 */     RenderingHints hints = this.nodeSupport.getRenderingHints();
/*  582 */     if (hints != null && hints.containsKey(JAI.KEY_DEFAULT_RENDERING_SIZE))
/*  584 */       defaultDimension = (Dimension)hints.get(JAI.KEY_DEFAULT_RENDERING_SIZE); 
/*  587 */     if (defaultDimension == null || (defaultDimension.width <= 0 && defaultDimension.height <= 0))
/*  589 */       defaultDimension = JAI.getDefaultRenderingSize(); 
/*  593 */     double sx = 1.0D;
/*  594 */     double sy = 1.0D;
/*  597 */     if (defaultDimension != null && (defaultDimension.width > 0 || defaultDimension.height > 0))
/*  599 */       if (defaultDimension.width > 0 && defaultDimension.height > 0) {
/*  600 */         sx = (defaultDimension.width / getWidth());
/*  601 */         sy = (defaultDimension.height / getHeight());
/*      */       } else {
/*  603 */         sx = sy = (defaultDimension.width / getWidth());
/*  605 */         sx = sy = (defaultDimension.height / getHeight());
/*      */       }  
/*  610 */     AffineTransform transform = AffineTransform.getScaleInstance(sx, sy);
/*  613 */     return createRendering(new RenderContext(transform));
/*      */   }
/*      */   
/*      */   public RenderedImage createScaledRendering(int w, int h, RenderingHints hints) {
/*  650 */     if (w == 0 && h == 0)
/*  651 */       throw new IllegalArgumentException(JaiI18N.getString("RenderableOp3")); 
/*  654 */     if (w == 0) {
/*  655 */       w = Math.round(h * getWidth() / getHeight());
/*  656 */     } else if (h == 0) {
/*  657 */       h = Math.round(w * getHeight() / getWidth());
/*      */     } 
/*  659 */     double sx = w / getWidth();
/*  660 */     double sy = h / getHeight();
/*  662 */     AffineTransform usr2dev = AffineTransform.getScaleInstance(sx, sy);
/*  663 */     RenderContext renderContext = new RenderContext(usr2dev, hints);
/*  664 */     return createRendering(renderContext);
/*      */   }
/*      */   
/*      */   public RenderedImage createRendering(RenderContext renderContext) {
/*  695 */     findCRIF();
/*  700 */     ParameterBlock nodePB = this.nodeSupport.getParameterBlock();
/*  701 */     Vector nodeParams = ImageUtil.evaluateParameters(nodePB.getParameters());
/*  703 */     ParameterBlock renderedPB = new ParameterBlock((Vector)nodePB.getSources().clone(), nodeParams);
/*  706 */     Vector sources = getRenderableSources();
/*      */     try {
/*  715 */       RenderContext rcIn = renderContext;
/*  716 */       RenderingHints nodeHints = this.nodeSupport.getRenderingHints();
/*  717 */       if (nodeHints != null) {
/*  718 */         RenderingHints hints = renderContext.getRenderingHints();
/*  719 */         RenderingHints mergedHints = JAI.mergeRenderingHints(nodeHints, hints);
/*  721 */         if (mergedHints != hints)
/*  722 */           rcIn = new RenderContext(renderContext.getTransform(), renderContext.getAreaOfInterest(), mergedHints); 
/*      */       } 
/*  728 */       if (sources != null) {
/*  729 */         Vector renderedSources = new Vector();
/*  730 */         for (int i = 0; i < sources.size(); i++) {
/*  731 */           RenderContext rcOut = this.crif.mapRenderContext(i, rcIn, renderedPB, this);
/*  735 */           RenderableImage src = sources.elementAt(i);
/*  737 */           RenderedImage renderedImage = src.createRendering(rcOut);
/*  738 */           if (renderedImage == null)
/*  739 */             return null; 
/*  744 */           renderedSources.addElement(renderedImage);
/*      */         } 
/*  747 */         if (renderedSources.size() > 0)
/*  748 */           renderedPB.setSources((Vector)renderedSources); 
/*      */       } 
/*  752 */       RenderedImage rendering = this.crif.create(rcIn, renderedPB);
/*  755 */       if (rendering instanceof RenderedOp)
/*  756 */         rendering = ((RenderedOp)rendering).getRendering(); 
/*  760 */       if (rendering != null && rendering instanceof WritablePropertySource) {
/*  762 */         String[] propertyNames = getPropertyNames();
/*  763 */         if (propertyNames != null) {
/*  764 */           WritablePropertySource wps = (WritablePropertySource)rendering;
/*  768 */           HashSet wpsNameSet = null;
/*  769 */           String[] wpsNames = wps.getPropertyNames();
/*  770 */           if (wpsNames != null) {
/*  771 */             wpsNameSet = new HashSet();
/*  772 */             for (int i = 0; i < wpsNames.length; i++)
/*  773 */               wpsNameSet.add(new CaselessStringKey(wpsNames[i])); 
/*      */           } 
/*  778 */           for (int j = 0; j < propertyNames.length; j++) {
/*  779 */             String name = propertyNames[j];
/*  780 */             if (wpsNameSet == null || !wpsNameSet.contains(new CaselessStringKey(name))) {
/*  782 */               Object value = getProperty(name);
/*  783 */               if (value != null && value != Image.UndefinedProperty)
/*  785 */                 wps.setProperty(name, value); 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  792 */       return rendering;
/*  793 */     } catch (ArrayIndexOutOfBoundsException e) {
/*  795 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isDynamic() {
/*  804 */     return false;
/*      */   }
/*      */   
/*      */   private synchronized void createPropertySource() {
/*  811 */     if (this.thePropertySource == null) {
/*  814 */       this.thePropertySource = this.nodeSupport.getPropertySource(this, null);
/*  817 */       this.properties.addProperties(this.thePropertySource);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames() {
/*  830 */     createPropertySource();
/*  831 */     return this.properties.getPropertyNames();
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames(String prefix) {
/*  847 */     return PropertyUtil.getPropertyNames(getPropertyNames(), prefix);
/*      */   }
/*      */   
/*      */   public Class getPropertyClass(String name) {
/*  863 */     createPropertySource();
/*  864 */     return this.properties.getPropertyClass(name);
/*      */   }
/*      */   
/*      */   public Object getProperty(String name) {
/*  879 */     createPropertySource();
/*  880 */     return this.properties.getProperty(name);
/*      */   }
/*      */   
/*      */   public void setProperty(String name, Object value) {
/*  897 */     createPropertySource();
/*  898 */     this.properties.setProperty(name, value);
/*      */   }
/*      */   
/*      */   public void removeProperty(String name) {
/*  912 */     createPropertySource();
/*  913 */     this.properties.removeProperty(name);
/*      */   }
/*      */   
/*      */   public synchronized Object getDynamicProperty(String name) {
/*  935 */     createPropertySource();
/*  936 */     return this.thePropertySource.getProperty(name);
/*      */   }
/*      */   
/*      */   public void addPropertyGenerator(PropertyGenerator pg) {
/*  948 */     this.nodeSupport.addPropertyGenerator(pg);
/*      */   }
/*      */   
/*      */   public synchronized void copyPropertyFromSource(String propertyName, int sourceIndex) {
/*  966 */     this.nodeSupport.copyPropertyFromSource(propertyName, sourceIndex);
/*      */   }
/*      */   
/*      */   public void suppressProperty(String name) {
/*  987 */     this.nodeSupport.suppressProperty(name);
/*      */   }
/*      */   
/*      */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/*  999 */     this.eventManager.addPropertyChangeListener(listener);
/*      */   }
/*      */   
/*      */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 1012 */     this.eventManager.addPropertyChangeListener(propertyName, listener);
/*      */   }
/*      */   
/*      */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/* 1023 */     this.eventManager.removePropertyChangeListener(listener);
/*      */   }
/*      */   
/*      */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 1034 */     this.eventManager.removePropertyChangeListener(propertyName, listener);
/*      */   }
/*      */   
/*      */   public Object getSource(int index) {
/* 1045 */     Vector sources = this.nodeSupport.getParameterBlock().getSources();
/* 1046 */     return sources.elementAt(index);
/*      */   }
/*      */   
/*      */   public void setSource(Object source, int index) {
/* 1061 */     if (source == null)
/* 1062 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 1064 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 1066 */     pb.setSource(source, index);
/* 1067 */     this.nodeSupport.setParameterBlock(pb);
/*      */   }
/*      */   
/*      */   public void removeSources() {
/* 1079 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 1081 */     pb.removeSources();
/* 1082 */     this.nodeSupport.setParameterBlock(pb);
/*      */   }
/*      */   
/*      */   public byte getByteParameter(int index) {
/* 1093 */     return this.nodeSupport.getParameterBlock().getByteParameter(index);
/*      */   }
/*      */   
/*      */   public char getCharParameter(int index) {
/* 1102 */     return this.nodeSupport.getParameterBlock().getCharParameter(index);
/*      */   }
/*      */   
/*      */   public short getShortParameter(int index) {
/* 1111 */     return this.nodeSupport.getParameterBlock().getShortParameter(index);
/*      */   }
/*      */   
/*      */   public int getIntParameter(int index) {
/* 1120 */     return this.nodeSupport.getParameterBlock().getIntParameter(index);
/*      */   }
/*      */   
/*      */   public long getLongParameter(int index) {
/* 1129 */     return this.nodeSupport.getParameterBlock().getLongParameter(index);
/*      */   }
/*      */   
/*      */   public float getFloatParameter(int index) {
/* 1138 */     return this.nodeSupport.getParameterBlock().getFloatParameter(index);
/*      */   }
/*      */   
/*      */   public double getDoubleParameter(int index) {
/* 1147 */     return this.nodeSupport.getParameterBlock().getDoubleParameter(index);
/*      */   }
/*      */   
/*      */   public Object getObjectParameter(int index) {
/* 1156 */     return this.nodeSupport.getParameterBlock().getObjectParameter(index);
/*      */   }
/*      */   
/*      */   public void setParameter(byte param, int index) {
/* 1169 */     setParameter(new Byte(param), index);
/*      */   }
/*      */   
/*      */   public void setParameter(char param, int index) {
/* 1182 */     setParameter(new Character(param), index);
/*      */   }
/*      */   
/*      */   public void setParameter(short param, int index) {
/* 1195 */     setParameter(new Short(param), index);
/*      */   }
/*      */   
/*      */   public void setParameter(int param, int index) {
/* 1208 */     setParameter(new Integer(param), index);
/*      */   }
/*      */   
/*      */   public void setParameter(long param, int index) {
/* 1221 */     setParameter(new Long(param), index);
/*      */   }
/*      */   
/*      */   public void setParameter(float param, int index) {
/* 1234 */     setParameter(new Float(param), index);
/*      */   }
/*      */   
/*      */   public void setParameter(double param, int index) {
/* 1247 */     setParameter(new Double(param), index);
/*      */   }
/*      */   
/*      */   public void setParameter(Object param, int index) {
/* 1265 */     ParameterBlock pb = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 1267 */     pb.set(param, index);
/* 1268 */     this.nodeSupport.setParameterBlock(pb);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RenderableOp.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */