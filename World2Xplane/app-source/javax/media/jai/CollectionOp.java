/*      */ package javax.media.jai;
/*      */ 
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import com.sun.media.jai.util.PropertyUtil;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderContext;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Locale;
/*      */ import java.util.Set;
/*      */ import java.util.SortedSet;
/*      */ import java.util.TreeSet;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.registry.CIFRegistry;
/*      */ import javax.media.jai.registry.RCIFRegistry;
/*      */ 
/*      */ public class CollectionOp extends CollectionImage implements OperationNode, PropertyChangeListener {
/*      */   protected OperationNodeSupport nodeSupport;
/*      */   
/*      */   protected PropertySource thePropertySource;
/*      */   
/*      */   protected boolean isRenderable = false;
/*      */   
/*      */   private transient RenderingHints oldHints;
/*      */   
/*  249 */   private static Set nodeEventNames = null;
/*      */   
/*      */   static {
/*  252 */     nodeEventNames = new HashSet();
/*  253 */     nodeEventNames.add("operationname");
/*  254 */     nodeEventNames.add("operationregistry");
/*  255 */     nodeEventNames.add("parameterblock");
/*  256 */     nodeEventNames.add("sources");
/*  257 */     nodeEventNames.add("parameters");
/*  258 */     nodeEventNames.add("renderinghints");
/*      */   }
/*      */   
/*      */   public CollectionOp(OperationRegistry registry, String opName, ParameterBlock pb, RenderingHints hints, boolean isRenderable) {
/*  305 */     if (opName == null)
/*  306 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  309 */     if (pb == null) {
/*  311 */       pb = new ParameterBlock();
/*      */     } else {
/*  314 */       pb = (ParameterBlock)pb.clone();
/*      */     } 
/*  317 */     if (hints != null)
/*  319 */       hints = (RenderingHints)hints.clone(); 
/*  323 */     this.eventManager = new PropertyChangeSupportJAI(this);
/*  325 */     this.properties = new WritablePropertySourceImpl(null, null, this.eventManager);
/*  327 */     this.nodeSupport = new OperationNodeSupport(getRegistryModeName(), opName, registry, pb, hints, this.eventManager);
/*  334 */     this.isRenderable = isRenderable;
/*  339 */     addPropertyChangeListener("OperationName", this);
/*  340 */     addPropertyChangeListener("OperationRegistry", this);
/*  341 */     addPropertyChangeListener("ParameterBlock", this);
/*  342 */     addPropertyChangeListener("Sources", this);
/*  343 */     addPropertyChangeListener("Parameters", this);
/*  344 */     addPropertyChangeListener("RenderingHints", this);
/*  347 */     Vector nodeSources = pb.getSources();
/*  348 */     if (nodeSources != null) {
/*  349 */       Iterator it = nodeSources.iterator();
/*  350 */       while (it.hasNext()) {
/*  351 */         Object src = it.next();
/*  352 */         if (src instanceof CollectionImage) {
/*  353 */           ((CollectionImage)src).addSink(this);
/*      */           continue;
/*      */         } 
/*  354 */         if (src instanceof PlanarImage)
/*  355 */           ((PlanarImage)src).addSink(this); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public CollectionOp(OperationRegistry registry, String opName, ParameterBlock pb, RenderingHints hints) {
/*  396 */     this(registry, opName, pb, hints, false);
/*      */   }
/*      */   
/*      */   public CollectionOp(String opName, ParameterBlock pb, RenderingHints hints) {
/*  430 */     this((OperationRegistry)null, opName, pb, hints);
/*      */   }
/*      */   
/*      */   public CollectionOp(OperationRegistry registry, String opName, ParameterBlock pb) {
/*  469 */     this(registry, opName, pb, (RenderingHints)null);
/*      */   }
/*      */   
/*      */   public boolean isRenderable() {
/*  478 */     return this.isRenderable;
/*      */   }
/*      */   
/*      */   public String getRegistryModeName() {
/*  488 */     return this.isRenderable ? "renderableCollection" : "collection";
/*      */   }
/*      */   
/*      */   public synchronized OperationRegistry getRegistry() {
/*  501 */     return this.nodeSupport.getRegistry();
/*      */   }
/*      */   
/*      */   public synchronized void setRegistry(OperationRegistry registry) {
/*  518 */     this.nodeSupport.setRegistry(registry);
/*      */   }
/*      */   
/*      */   public String getOperationName() {
/*  526 */     return this.nodeSupport.getOperationName();
/*      */   }
/*      */   
/*      */   public synchronized void setOperationName(String opName) {
/*  544 */     this.nodeSupport.setOperationName(opName);
/*      */   }
/*      */   
/*      */   public ParameterBlock getParameterBlock() {
/*  549 */     return (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/*      */   }
/*      */   
/*      */   public synchronized void setParameterBlock(ParameterBlock pb) {
/*  585 */     Vector nodeSources = this.nodeSupport.getParameterBlock().getSources();
/*  586 */     if (nodeSources != null && nodeSources.size() > 0) {
/*  587 */       Iterator it = nodeSources.iterator();
/*  588 */       while (it.hasNext()) {
/*  589 */         Object src = it.next();
/*  590 */         if (src instanceof PlanarImage) {
/*  591 */           ((PlanarImage)src).removeSink(this);
/*      */           continue;
/*      */         } 
/*  592 */         if (src instanceof CollectionImage)
/*  593 */           ((CollectionImage)src).removeSink(this); 
/*      */       } 
/*      */     } 
/*  598 */     if (pb != null) {
/*  599 */       Vector newSources = pb.getSources();
/*  600 */       if (newSources != null && newSources.size() > 0) {
/*  601 */         Iterator it = newSources.iterator();
/*  602 */         while (it.hasNext()) {
/*  603 */           Object src = it.next();
/*  604 */           if (src instanceof PlanarImage) {
/*  605 */             ((PlanarImage)src).addSink(this);
/*      */             continue;
/*      */           } 
/*  606 */           if (src instanceof CollectionImage)
/*  607 */             ((CollectionImage)src).addSink(this); 
/*      */         } 
/*      */       } 
/*      */     } 
/*  613 */     this.nodeSupport.setParameterBlock((pb == null) ? new ParameterBlock() : (ParameterBlock)pb.clone());
/*      */   }
/*      */   
/*      */   public RenderingHints getRenderingHints() {
/*  623 */     RenderingHints hints = this.nodeSupport.getRenderingHints();
/*  624 */     return (hints == null) ? null : (RenderingHints)hints.clone();
/*      */   }
/*      */   
/*      */   public synchronized void setRenderingHints(RenderingHints hints) {
/*  641 */     if (hints != null)
/*  642 */       hints = (RenderingHints)hints.clone(); 
/*  644 */     this.nodeSupport.setRenderingHints(hints);
/*      */   }
/*      */   
/*      */   public Collection getCollection() {
/*  672 */     createCollection();
/*  673 */     return this.imageCollection;
/*      */   }
/*      */   
/*      */   private synchronized void createCollection() {
/*  678 */     if (this.imageCollection == null)
/*  679 */       this.imageCollection = createInstance(true); 
/*      */   }
/*      */   
/*      */   public synchronized Collection createInstance() {
/*  704 */     return createInstance(false);
/*      */   }
/*      */   
/*      */   private synchronized Collection createInstance(boolean isChainFrozen) {
/*  717 */     ParameterBlock args = ImageUtil.evaluateParameters(this.nodeSupport.getParameterBlock());
/*  720 */     ParameterBlock pb = new ParameterBlock();
/*  721 */     pb.setParameters(args.getParameters());
/*  723 */     int numSources = args.getNumSources();
/*  724 */     for (int i = 0; i < numSources; i++) {
/*  725 */       Object source = args.getSource(i);
/*  726 */       Object src = null;
/*  728 */       if (source instanceof RenderedOp) {
/*  729 */         src = isChainFrozen ? ((RenderedOp)source).getRendering() : ((RenderedOp)source).createInstance();
/*  732 */       } else if (source instanceof CollectionOp) {
/*  733 */         CollectionOp co = (CollectionOp)source;
/*  734 */         src = isChainFrozen ? co.getCollection() : co.createInstance();
/*  737 */       } else if (source instanceof RenderedImage || source instanceof RenderableImage || source instanceof Collection) {
/*  740 */         src = source;
/*      */       } else {
/*  743 */         src = source;
/*      */       } 
/*  745 */       pb.addSource(src);
/*      */     } 
/*  748 */     Collection instance = null;
/*  749 */     if (this.isRenderable) {
/*  750 */       instance = RCIFRegistry.create(this.nodeSupport.getRegistry(), this.nodeSupport.getOperationName(), pb);
/*      */     } else {
/*  754 */       CollectionImageFactory cif = CIFRegistry.get(this.nodeSupport.getRegistry(), this.nodeSupport.getOperationName());
/*  757 */       instance = cif.create(pb, this.nodeSupport.getRenderingHints());
/*  760 */       if (instance != null)
/*  761 */         ((CollectionImage)instance).setImageFactory(cif); 
/*      */     } 
/*  766 */     if (instance == null)
/*  767 */       throw new RuntimeException(JaiI18N.getString("CollectionOp0")); 
/*  771 */     this.oldHints = (this.nodeSupport.getRenderingHints() == null) ? null : (RenderingHints)this.nodeSupport.getRenderingHints().clone();
/*  774 */     return instance;
/*      */   }
/*      */   
/*      */   public Collection createRendering(RenderContext renderContext) {
/*  798 */     if (!this.isRenderable)
/*  799 */       return this; 
/*  803 */     RenderingHints mergedHints = JAI.mergeRenderingHints(this.nodeSupport.getRenderingHints(), renderContext.getRenderingHints());
/*  806 */     if (mergedHints != renderContext.getRenderingHints()) {
/*  807 */       renderContext = (RenderContext)renderContext.clone();
/*  808 */       renderContext.setRenderingHints(mergedHints);
/*      */     } 
/*  811 */     return renderCollection(this.imageCollection, renderContext);
/*      */   }
/*      */   
/*      */   private Collection renderCollection(Collection cIn, RenderContext rc) {
/*      */     Collection cOut;
/*  821 */     if (cIn == null || rc == null)
/*  822 */       throw new IllegalArgumentException(); 
/*  826 */     if (cIn instanceof Set) {
/*  827 */       cOut = Collections.synchronizedSet(new HashSet(cIn.size()));
/*  828 */     } else if (cIn instanceof SortedSet) {
/*  829 */       Comparator comparator = ((SortedSet)cIn).comparator();
/*  830 */       cOut = Collections.synchronizedSortedSet(new TreeSet(comparator));
/*      */     } else {
/*  832 */       cOut = new Vector(cIn.size());
/*      */     } 
/*  835 */     Iterator it = cIn.iterator();
/*  836 */     while (it.hasNext()) {
/*  837 */       Object element = it.next();
/*  838 */       if (element instanceof RenderableImage) {
/*  839 */         cOut.add(((RenderableImage)cIn).createRendering(rc));
/*      */         continue;
/*      */       } 
/*  840 */       if (element instanceof Collection) {
/*  841 */         cOut.add(renderCollection((Collection)element, rc));
/*      */         continue;
/*      */       } 
/*  843 */       cOut.add(element);
/*      */     } 
/*  847 */     return cOut;
/*      */   }
/*      */   
/*      */   public synchronized void propertyChange(PropertyChangeEvent evt) {
/*  867 */     if (isRenderable())
/*      */       return; 
/*  878 */     Object evtSrc = evt.getSource();
/*  879 */     Vector nodeSources = this.nodeSupport.getParameterBlock().getSources();
/*  883 */     String propName = evt.getPropertyName().toLowerCase(Locale.ENGLISH);
/*  885 */     if (this.imageCollection != null && ((evt instanceof PropertyChangeEventJAI && evtSrc == this && !(evt instanceof PropertySourceChangeEvent) && nodeEventNames.contains(propName)) || ((evt instanceof CollectionChangeEvent || evt instanceof RenderingChangeEvent) && nodeSources.contains(evtSrc)))) {
/*  895 */       Collection theOldCollection = this.imageCollection;
/*  898 */       boolean fireEvent = false;
/*  900 */       if (!(this.imageCollection instanceof CollectionImage)) {
/*  904 */         fireEvent = true;
/*  905 */         this.imageCollection = null;
/*  907 */       } else if (evtSrc == this && (propName.equals("operationname") || propName.equals("operationregistry"))) {
/*  913 */         fireEvent = true;
/*  914 */         this.imageCollection = null;
/*  916 */       } else if (evt instanceof CollectionChangeEvent) {
/*  919 */         fireEvent = true;
/*  924 */         CollectionImageFactory oldCIF = ((CollectionImage)theOldCollection).getImageFactory();
/*  927 */         if (oldCIF == null) {
/*  930 */           this.imageCollection = null;
/*      */         } else {
/*  935 */           CollectionChangeEvent ccEvent = (CollectionChangeEvent)evt;
/*  938 */           Vector parameters = this.nodeSupport.getParameterBlock().getParameters();
/*  940 */           parameters = ImageUtil.evaluateParameters(parameters);
/*  941 */           ParameterBlock oldPB = new ParameterBlock((Vector)nodeSources.clone(), parameters);
/*  944 */           ParameterBlock newPB = new ParameterBlock((Vector)nodeSources.clone(), parameters);
/*  947 */           int sourceIndex = nodeSources.indexOf(ccEvent.getSource());
/*  948 */           oldPB.setSource(ccEvent.getOldValue(), sourceIndex);
/*  949 */           newPB.setSource(ccEvent.getNewValue(), sourceIndex);
/*  952 */           this.imageCollection = oldCIF.update(oldPB, this.oldHints, newPB, this.oldHints, (CollectionImage)theOldCollection, this);
/*      */         } 
/*      */       } else {
/*  963 */         CollectionImageFactory oldCIF = ((CollectionImage)theOldCollection).getImageFactory();
/*  966 */         if (oldCIF == null || oldCIF != CIFRegistry.get(this.nodeSupport.getRegistry(), this.nodeSupport.getOperationName())) {
/*  972 */           this.imageCollection = null;
/*  975 */           fireEvent = true;
/*      */         } else {
/*  981 */           ParameterBlock oldPB = null;
/*  982 */           ParameterBlock newPB = null;
/*  984 */           boolean updateCollection = false;
/*  986 */           if (propName.equals("parameterblock")) {
/*  987 */             oldPB = (ParameterBlock)evt.getOldValue();
/*  988 */             newPB = (ParameterBlock)evt.getNewValue();
/*  989 */             updateCollection = true;
/*  990 */           } else if (propName.equals("sources")) {
/*  992 */             Vector params = this.nodeSupport.getParameterBlock().getParameters();
/*  994 */             oldPB = new ParameterBlock((Vector)evt.getOldValue(), params);
/*  996 */             newPB = new ParameterBlock((Vector)evt.getNewValue(), params);
/*  998 */             updateCollection = true;
/*  999 */           } else if (propName.equals("parameters")) {
/* 1001 */             oldPB = new ParameterBlock(nodeSources, (Vector)evt.getOldValue());
/* 1003 */             newPB = new ParameterBlock(nodeSources, (Vector)evt.getNewValue());
/* 1005 */             updateCollection = true;
/* 1006 */           } else if (propName.equals("renderinghints")) {
/* 1007 */             oldPB = newPB = this.nodeSupport.getParameterBlock();
/* 1008 */             updateCollection = true;
/* 1009 */           } else if (evt instanceof RenderingChangeEvent) {
/* 1013 */             int renderingIndex = nodeSources.indexOf(evt.getSource());
/* 1015 */             Vector oldSources = (Vector)nodeSources.clone();
/* 1016 */             Vector newSources = (Vector)nodeSources.clone();
/* 1017 */             oldSources.set(renderingIndex, evt.getOldValue());
/* 1018 */             newSources.set(renderingIndex, evt.getNewValue());
/* 1020 */             Vector params = this.nodeSupport.getParameterBlock().getParameters();
/* 1023 */             oldPB = new ParameterBlock(oldSources, params);
/* 1024 */             newPB = new ParameterBlock(newSources, params);
/* 1026 */             updateCollection = true;
/*      */           } 
/* 1029 */           if (updateCollection) {
/* 1031 */             fireEvent = true;
/* 1034 */             oldPB = ImageUtil.evaluateParameters(oldPB);
/* 1035 */             newPB = ImageUtil.evaluateParameters(newPB);
/* 1038 */             RenderingHints newHints = this.nodeSupport.getRenderingHints();
/* 1040 */             if ((this.imageCollection = oldCIF.update(oldPB, this.oldHints, newPB, newHints, (CollectionImage)theOldCollection, this)) != null)
/* 1045 */               this.oldHints = newHints; 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1053 */       getCollection();
/* 1056 */       if (fireEvent) {
/* 1059 */         resetProperties(true);
/* 1062 */         CollectionChangeEvent ccEvent = new CollectionChangeEvent(this, theOldCollection, this.imageCollection);
/* 1068 */         this.eventManager.firePropertyChange(ccEvent);
/* 1071 */         Set sinks = getSinks();
/* 1072 */         if (sinks != null) {
/* 1073 */           Iterator it = sinks.iterator();
/* 1074 */           while (it.hasNext()) {
/* 1075 */             Object sink = it.next();
/* 1076 */             if (sink instanceof PropertyChangeListener)
/* 1077 */               ((PropertyChangeListener)sink).propertyChange(ccEvent); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized void createPropertySource() {
/* 1089 */     if (this.thePropertySource == null) {
/* 1090 */       getCollection();
/* 1092 */       PropertySource defaultPS = null;
/* 1093 */       if (this.imageCollection instanceof PropertySource)
/* 1095 */         defaultPS = new PropertySource(this) {
/*      */             private final CollectionOp this$0;
/*      */             
/*      */             public String[] getPropertyNames() {
/* 1100 */               return ((PropertySource)this.this$0.imageCollection).getPropertyNames();
/*      */             }
/*      */             
/*      */             public String[] getPropertyNames(String prefix) {
/* 1104 */               return PropertyUtil.getPropertyNames(getPropertyNames(), prefix);
/*      */             }
/*      */             
/*      */             public Class getPropertyClass(String name) {
/* 1109 */               return null;
/*      */             }
/*      */             
/*      */             public Object getProperty(String name) {
/* 1117 */               return ((PropertySource)this.this$0.imageCollection).getProperty(name);
/*      */             }
/*      */           }; 
/* 1124 */       this.thePropertySource = this.nodeSupport.getPropertySource(this, defaultPS);
/* 1127 */       this.properties.addProperties(this.thePropertySource);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected synchronized void resetProperties(boolean resetPropertySource) {
/* 1141 */     this.properties.clearCachedProperties();
/* 1142 */     if (resetPropertySource && this.thePropertySource != null) {
/* 1143 */       this.properties.removePropertySource(this.thePropertySource);
/* 1144 */       this.thePropertySource = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized String[] getPropertyNames() {
/* 1159 */     createPropertySource();
/* 1160 */     return this.properties.getPropertyNames();
/*      */   }
/*      */   
/*      */   public Class getPropertyClass(String name) {
/* 1176 */     createPropertySource();
/* 1177 */     return this.properties.getPropertyClass(name);
/*      */   }
/*      */   
/*      */   public Object getProperty(String name) {
/* 1194 */     createPropertySource();
/* 1195 */     return this.properties.getProperty(name);
/*      */   }
/*      */   
/*      */   public void setProperty(String name, Object value) {
/* 1211 */     createPropertySource();
/* 1212 */     this.properties.setProperty(name, value);
/*      */   }
/*      */   
/*      */   public void removeProperty(String name) {
/* 1226 */     createPropertySource();
/* 1227 */     this.properties.removeProperty(name);
/*      */   }
/*      */   
/*      */   public synchronized Object getDynamicProperty(String name) {
/* 1249 */     createPropertySource();
/* 1250 */     return this.thePropertySource.getProperty(name);
/*      */   }
/*      */   
/*      */   public void addPropertyGenerator(PropertyGenerator pg) {
/* 1264 */     this.nodeSupport.addPropertyGenerator(pg);
/*      */   }
/*      */   
/*      */   public synchronized void copyPropertyFromSource(String propertyName, int sourceIndex) {
/* 1282 */     this.nodeSupport.copyPropertyFromSource(propertyName, sourceIndex);
/*      */   }
/*      */   
/*      */   public void suppressProperty(String name) {
/* 1305 */     this.nodeSupport.suppressProperty(name);
/*      */   }
/*      */   
/*      */   public int size() {
/* 1318 */     createCollection();
/* 1319 */     return this.imageCollection.size();
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/* 1328 */     createCollection();
/* 1329 */     return this.imageCollection.isEmpty();
/*      */   }
/*      */   
/*      */   public boolean contains(Object o) {
/* 1338 */     createCollection();
/* 1339 */     return this.imageCollection.contains(o);
/*      */   }
/*      */   
/*      */   public Iterator iterator() {
/* 1348 */     createCollection();
/* 1349 */     return this.imageCollection.iterator();
/*      */   }
/*      */   
/*      */   public Object[] toArray() {
/* 1358 */     createCollection();
/* 1359 */     return this.imageCollection.toArray();
/*      */   }
/*      */   
/*      */   public Object[] toArray(Object[] a) {
/* 1373 */     createCollection();
/* 1374 */     return this.imageCollection.toArray(a);
/*      */   }
/*      */   
/*      */   public boolean add(Object o) {
/* 1382 */     createCollection();
/* 1383 */     return this.imageCollection.add(o);
/*      */   }
/*      */   
/*      */   public boolean remove(Object o) {
/* 1391 */     createCollection();
/* 1392 */     return this.imageCollection.remove(o);
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection c) {
/* 1401 */     createCollection();
/* 1402 */     return this.imageCollection.containsAll(c);
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection c) {
/* 1411 */     createCollection();
/* 1412 */     return this.imageCollection.addAll(c);
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection c) {
/* 1421 */     createCollection();
/* 1422 */     return this.imageCollection.removeAll(c);
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection c) {
/* 1431 */     createCollection();
/* 1432 */     return this.imageCollection.retainAll(c);
/*      */   }
/*      */   
/*      */   public void clear() {
/* 1440 */     createCollection();
/* 1441 */     this.imageCollection.clear();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\CollectionOp.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */