/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ import javax.media.jai.remote.SerializerFactory;
/*     */ 
/*     */ public class OperationNodeSupport implements Serializable {
/*     */   private static final int PB_EQUAL = 0;
/*     */   
/*     */   private static final int PB_SOURCES_DIFFER = 1;
/*     */   
/*     */   private static final int PB_PARAMETERS_DIFFER = 2;
/*     */   
/*     */   private static final int PB_DIFFER = 3;
/*     */   
/*     */   private String registryModeName;
/*     */   
/*     */   private String opName;
/*     */   
/*     */   private transient OperationRegistry registry;
/*     */   
/*     */   private transient ParameterBlock pb;
/*     */   
/*     */   private transient RenderingHints hints;
/*     */   
/*     */   private PropertyChangeSupportJAI eventManager;
/*     */   
/*  64 */   private transient PropertyEnvironment propertySource = null;
/*     */   
/*  71 */   private Vector localPropEnv = new Vector();
/*     */   
/*  77 */   private Hashtable paramObservers = new Hashtable();
/*     */   
/*     */   private static int compare(ParameterBlock pb1, ParameterBlock pb2) {
/*  83 */     if (pb1 == null && pb2 == null)
/*  84 */       return 0; 
/*  87 */     if ((pb1 == null && pb2 != null) || (pb1 != null && pb2 == null))
/*  89 */       return 3; 
/*  92 */     int result = 0;
/*  93 */     if (!equals(pb1.getSources(), pb2.getSources()))
/*  94 */       result |= 0x1; 
/*  96 */     if (!equals(pb1.getParameters(), pb2.getParameters()))
/*  97 */       result |= 0x2; 
/* 100 */     return result;
/*     */   }
/*     */   
/*     */   private static boolean equals(ParameterBlock pb1, ParameterBlock pb2) {
/* 104 */     return (pb1 == null) ? ((pb2 == null)) : ((equals(pb1.getSources(), pb2.getSources()) && equals(pb1.getParameters(), pb2.getParameters())));
/*     */   }
/*     */   
/*     */   private static boolean equals(Object o1, Object o2) {
/* 110 */     return (o1 == null) ? ((o2 == null)) : o1.equals(o2);
/*     */   }
/*     */   
/*     */   public OperationNodeSupport(String registryModeName, String opName, OperationRegistry registry, ParameterBlock pb, RenderingHints hints, PropertyChangeSupportJAI eventManager) {
/* 158 */     if (registryModeName == null || opName == null)
/* 159 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 163 */     this.registryModeName = registryModeName;
/* 164 */     this.opName = opName;
/* 165 */     if (registry == null) {
/* 166 */       this.registry = JAI.getDefaultInstance().getOperationRegistry();
/*     */     } else {
/* 168 */       this.registry = registry;
/*     */     } 
/* 169 */     this.pb = pb;
/* 170 */     this.hints = hints;
/* 171 */     this.eventManager = eventManager;
/* 174 */     if (pb != null)
/* 175 */       updateObserverMap(pb.getParameters()); 
/*     */   }
/*     */   
/*     */   private class CopyDirective implements Serializable {
/*     */     private String name;
/*     */     
/*     */     private int index;
/*     */     
/*     */     private final OperationNodeSupport this$0;
/*     */     
/*     */     CopyDirective(OperationNodeSupport this$0, String name, int index) {
/* 197 */       this.this$0 = this$0;
/* 198 */       if (name == null)
/* 199 */         throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 201 */       this.name = name;
/* 202 */       this.index = index;
/*     */     }
/*     */     
/*     */     String getName() {
/* 206 */       return this.name;
/*     */     }
/*     */     
/*     */     int getIndex() {
/* 210 */       return this.index;
/*     */     }
/*     */   }
/*     */   
/*     */   private class ParamObserver implements Observer {
/*     */     final int paramIndex;
/*     */     
/*     */     final DeferredData dd;
/*     */     
/*     */     private final OperationNodeSupport this$0;
/*     */     
/*     */     ParamObserver(OperationNodeSupport this$0, int paramIndex, DeferredData dd) {
/* 231 */       this.this$0 = this$0;
/* 232 */       if (dd == null)
/* 233 */         throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 234 */       if (paramIndex < 0 || (this$0.pb != null && paramIndex >= this$0.pb.getNumParameters()))
/* 238 */         throw new ArrayIndexOutOfBoundsException(); 
/* 241 */       this.paramIndex = paramIndex;
/* 242 */       this.dd = dd;
/* 245 */       dd.addObserver(this);
/*     */     }
/*     */     
/*     */     public synchronized void update(Observable o, Object arg) {
/* 255 */       if (o != this.dd)
/*     */         return; 
/* 260 */       if (arg != null && this.this$0.eventManager != null) {
/* 261 */         Vector params = this.this$0.pb.getParameters();
/* 262 */         Vector oldParams = (Vector)params.clone();
/* 263 */         Vector newParams = (Vector)params.clone();
/* 265 */         oldParams.set(this.paramIndex, arg);
/* 266 */         newParams.set(this.paramIndex, this.dd.getData());
/* 268 */         this.this$0.fireEvent("Parameters", oldParams, newParams);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateObserverMap(Vector parameters) {
/* 279 */     if (parameters == null)
/*     */       return; 
/* 283 */     int numParameters = parameters.size();
/* 284 */     for (int i = 0; i < numParameters; i++) {
/* 285 */       Object oldObs, parameter = parameters.get(i);
/* 286 */       Integer index = new Integer(i);
/* 290 */       if (parameter instanceof DeferredData) {
/* 291 */         Observer obs = new ParamObserver(this, i, (DeferredData)parameter);
/* 293 */         oldObs = this.paramObservers.put(index, obs);
/*     */       } else {
/* 295 */         oldObs = this.paramObservers.remove(index);
/*     */       } 
/* 299 */       if (oldObs != null) {
/* 300 */         ParamObserver obs = (ParamObserver)oldObs;
/* 301 */         obs.dd.deleteObserver(obs);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getRegistryModeName() {
/* 312 */     return this.registryModeName;
/*     */   }
/*     */   
/*     */   public String getOperationName() {
/* 320 */     return this.opName;
/*     */   }
/*     */   
/*     */   public void setOperationName(String opName) {
/* 340 */     if (opName == null)
/* 341 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 344 */     if (opName.equalsIgnoreCase(this.opName))
/*     */       return; 
/* 346 */     String oldOpName = this.opName;
/* 347 */     this.opName = opName;
/* 348 */     fireEvent("OperationName", oldOpName, opName);
/* 349 */     resetPropertyEnvironment(false);
/*     */   }
/*     */   
/*     */   public OperationRegistry getRegistry() {
/* 357 */     return this.registry;
/*     */   }
/*     */   
/*     */   public void setRegistry(OperationRegistry registry) {
/* 376 */     if (registry == null)
/* 377 */       registry = JAI.getDefaultInstance().getOperationRegistry(); 
/* 379 */     if (registry != this.registry) {
/* 380 */       OperationRegistry oldRegistry = this.registry;
/* 381 */       this.registry = registry;
/* 382 */       fireEvent("OperationRegistry", oldRegistry, registry);
/* 383 */       resetPropertyEnvironment(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterBlock getParameterBlock() {
/* 394 */     return this.pb;
/*     */   }
/*     */   
/*     */   public void setParameterBlock(ParameterBlock pb) {
/* 441 */     int comparison = compare(this.pb, pb);
/* 442 */     if (comparison == 0)
/*     */       return; 
/* 446 */     ParameterBlock oldPB = this.pb;
/* 447 */     this.pb = pb;
/* 450 */     if (pb != null)
/* 451 */       updateObserverMap(pb.getParameters()); 
/* 454 */     if (comparison == 1) {
/* 456 */       fireEvent("Sources", oldPB.getSources(), pb.getSources());
/* 457 */     } else if (comparison == 2) {
/* 459 */       fireEvent("Parameters", oldPB.getParameters(), pb.getParameters());
/*     */     } else {
/* 463 */       fireEvent("ParameterBlock", oldPB, pb);
/*     */     } 
/* 466 */     resetPropertyEnvironment(false);
/*     */   }
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 476 */     return this.hints;
/*     */   }
/*     */   
/*     */   public void setRenderingHints(RenderingHints hints) {
/* 498 */     if (equals(this.hints, hints))
/*     */       return; 
/* 501 */     RenderingHints oldHints = this.hints;
/* 502 */     this.hints = hints;
/* 503 */     fireEvent("RenderingHints", oldHints, hints);
/* 504 */     resetPropertyEnvironment(false);
/*     */   }
/*     */   
/*     */   public void addPropertyGenerator(PropertyGenerator pg) {
/* 518 */     if (pg == null)
/* 519 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 521 */     this.localPropEnv.add(pg);
/* 522 */     if (this.propertySource != null)
/* 523 */       this.propertySource.addPropertyGenerator(pg); 
/*     */   }
/*     */   
/*     */   public void copyPropertyFromSource(String propertyName, int sourceIndex) {
/* 539 */     if (propertyName == null)
/* 540 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 542 */     this.localPropEnv.add(new CopyDirective(this, propertyName, sourceIndex));
/* 543 */     if (this.propertySource != null)
/* 544 */       this.propertySource.copyPropertyFromSource(propertyName, sourceIndex); 
/*     */   }
/*     */   
/*     */   public void suppressProperty(String name) {
/* 564 */     if (name == null)
/* 565 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 567 */     this.localPropEnv.add(name);
/* 568 */     if (this.propertySource != null)
/* 569 */       this.propertySource.suppressProperty(name); 
/*     */   }
/*     */   
/*     */   public PropertySource getPropertySource(OperationNode opNode, PropertySource defaultPS) {
/* 607 */     if (opNode == null)
/* 608 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 611 */     if (this.propertySource == null)
/* 612 */       synchronized (this) {
/* 613 */         RegistryMode regMode = RegistryMode.getMode(this.registryModeName);
/* 614 */         if (regMode != null && regMode.arePropertiesSupported()) {
/* 616 */           this.propertySource = (PropertyEnvironment)this.registry.getPropertySource(opNode);
/*     */         } else {
/* 624 */           this.propertySource = new PropertyEnvironment((this.pb != null) ? this.pb.getSources() : null, null, null, null, opNode);
/*     */         } 
/* 632 */         updatePropertyEnvironment(this.propertySource);
/*     */       }  
/* 637 */     this.propertySource.setDefaultPropertySource(defaultPS);
/* 639 */     return this.propertySource;
/*     */   }
/*     */   
/*     */   public void resetPropertyEnvironment(boolean resetLocalEnvironment) {
/* 651 */     this.propertySource = null;
/* 652 */     if (resetLocalEnvironment)
/* 653 */       this.localPropEnv.clear(); 
/*     */   }
/*     */   
/*     */   private void updatePropertyEnvironment(PropertyEnvironment pe) {
/* 659 */     if (pe != null)
/* 660 */       synchronized (this) {
/* 662 */         int size = this.localPropEnv.size();
/* 663 */         for (int i = 0; i < size; i++) {
/* 664 */           Object element = this.localPropEnv.get(i);
/* 665 */           if (element instanceof String) {
/* 666 */             pe.suppressProperty((String)element);
/* 667 */           } else if (element instanceof CopyDirective) {
/* 668 */             CopyDirective cd = (CopyDirective)element;
/* 669 */             pe.copyPropertyFromSource(cd.getName(), cd.getIndex());
/* 670 */           } else if (element instanceof PropertyGenerator) {
/* 671 */             pe.addPropertyGenerator((PropertyGenerator)element);
/*     */           } 
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   private void fireEvent(String propName, Object oldVal, Object newVal) {
/* 679 */     if (this.eventManager != null) {
/* 680 */       Object eventSource = this.eventManager.getPropertyChangeEventSource();
/* 681 */       PropertyChangeEventJAI evt = new PropertyChangeEventJAI(eventSource, propName, oldVal, newVal);
/* 684 */       this.eventManager.firePropertyChange(evt);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 703 */     ParameterBlock pbClone = this.pb;
/* 704 */     boolean pbCloned = false;
/*     */     int index;
/* 707 */     for (index = 0; index < pbClone.getNumSources(); index++) {
/* 708 */       Object source = pbClone.getSource(index);
/* 709 */       if (source != null && !(source instanceof Serializable)) {
/* 711 */         if (!pbCloned) {
/* 712 */           pbClone = (ParameterBlock)this.pb.clone();
/* 713 */           pbCloned = true;
/*     */         } 
/* 715 */         if (source instanceof RenderedImage) {
/* 716 */           SerializableState serializableImage = SerializerFactory.getState(source, null);
/* 718 */           pbClone.setSource(serializableImage, index);
/*     */         } else {
/* 720 */           throw new RuntimeException(source.getClass().getName() + JaiI18N.getString("OperationNodeSupport0"));
/*     */         } 
/*     */       } 
/*     */     } 
/* 729 */     for (index = 0; index < pbClone.getNumParameters(); index++) {
/* 730 */       Object parameter = pbClone.getObjectParameter(index);
/* 731 */       if (parameter != null && !(parameter instanceof Serializable)) {
/* 733 */         if (!pbCloned) {
/* 734 */           pbClone = (ParameterBlock)this.pb.clone();
/* 735 */           pbCloned = true;
/*     */         } 
/* 737 */         if (parameter instanceof java.awt.image.Raster) {
/* 738 */           pbClone.set(SerializerFactory.getState(parameter, null), index);
/* 739 */         } else if (parameter instanceof RenderedImage) {
/* 740 */           RenderedImage ri = (RenderedImage)parameter;
/* 741 */           RenderingHints hints = new RenderingHints(null);
/* 742 */           hints.put(JAI.KEY_SERIALIZE_DEEP_COPY, new Boolean(true));
/* 743 */           pbClone.set(SerializerFactory.getState(ri, hints), index);
/*     */         } else {
/* 746 */           throw new RuntimeException(parameter.getClass().getName() + JaiI18N.getString("OperationNodeSupport1"));
/*     */         } 
/*     */       } 
/*     */     } 
/* 754 */     out.defaultWriteObject();
/* 756 */     out.writeObject(pbClone);
/* 758 */     out.writeObject(SerializerFactory.getState(this.hints, null));
/*     */   }
/*     */   
/*     */   private synchronized void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 768 */     in.defaultReadObject();
/* 770 */     this.pb = (ParameterBlock)in.readObject();
/* 772 */     SerializableState ss = (SerializableState)in.readObject();
/* 773 */     this.hints = (RenderingHints)ss.getObject();
/*     */     int index;
/* 776 */     for (index = 0; index < this.pb.getNumSources(); index++) {
/* 777 */       Object source = this.pb.getSource(index);
/* 778 */       if (source instanceof SerializableState) {
/* 779 */         ss = (SerializableState)source;
/* 780 */         PlanarImage pi = PlanarImage.wrapRenderedImage((RenderedImage)ss.getObject());
/* 782 */         this.pb.setSource(pi, index);
/*     */       } 
/*     */     } 
/* 788 */     for (index = 0; index < this.pb.getNumParameters(); index++) {
/* 789 */       Object parameter = this.pb.getObjectParameter(index);
/* 790 */       if (parameter instanceof SerializableState) {
/* 791 */         Object object = ((SerializableState)parameter).getObject();
/* 792 */         if (object instanceof java.awt.image.Raster) {
/* 793 */           this.pb.set(object, index);
/* 794 */         } else if (object instanceof RenderedImage) {
/* 795 */           this.pb.set(PlanarImage.wrapRenderedImage((RenderedImage)object), index);
/*     */         } else {
/* 797 */           this.pb.set(object, index);
/*     */         } 
/*     */       } 
/*     */     } 
/* 801 */     this.registry = JAI.getDefaultInstance().getOperationRegistry();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\OperationNodeSupport.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */