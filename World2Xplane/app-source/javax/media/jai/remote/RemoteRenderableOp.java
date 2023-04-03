/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.PropertyChangeEventJAI;
/*     */ import javax.media.jai.RegistryMode;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ import javax.media.jai.WritablePropertySource;
/*     */ import javax.media.jai.registry.RemoteCRIFRegistry;
/*     */ import javax.media.jai.util.ImagingException;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class RemoteRenderableOp extends RenderableOp {
/*     */   protected String protocolName;
/*     */   
/*     */   protected String serverName;
/*     */   
/*  81 */   private transient RemoteCRIF remoteCRIF = null;
/*     */   
/*  84 */   private NegotiableCapabilitySet negotiated = null;
/*     */   
/*     */   private transient RenderedImage linkToRemoteOp;
/*     */   
/*     */   public RemoteRenderableOp(String protocolName, String serverName, String opName, ParameterBlock pb) {
/* 121 */     this((OperationRegistry)null, protocolName, serverName, opName, pb);
/*     */   }
/*     */   
/*     */   public RemoteRenderableOp(OperationRegistry registry, String protocolName, String serverName, String opName, ParameterBlock pb) {
/* 159 */     super(registry, opName, pb);
/* 161 */     if (protocolName == null || opName == null)
/* 162 */       throw new IllegalArgumentException(); 
/* 165 */     this.protocolName = protocolName;
/* 166 */     this.serverName = serverName;
/*     */   }
/*     */   
/*     */   public String getRegistryModeName() {
/* 176 */     return RegistryMode.getMode("remoteRenderable").getName();
/*     */   }
/*     */   
/*     */   public String getServerName() {
/* 183 */     return this.serverName;
/*     */   }
/*     */   
/*     */   public void setServerName(String serverName) {
/* 201 */     if (serverName == null)
/* 202 */       throw new IllegalArgumentException(JaiI18N.getString("Generic2")); 
/* 204 */     if (serverName.equalsIgnoreCase(this.serverName))
/*     */       return; 
/* 205 */     String oldServerName = this.serverName;
/* 206 */     this.serverName = serverName;
/* 207 */     fireEvent("ServerName", oldServerName, serverName);
/* 208 */     this.nodeSupport.resetPropertyEnvironment(false);
/*     */   }
/*     */   
/*     */   public String getProtocolName() {
/* 216 */     return this.protocolName;
/*     */   }
/*     */   
/*     */   public void setProtocolName(String protocolName) {
/* 234 */     if (protocolName == null)
/* 235 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 238 */     if (protocolName.equalsIgnoreCase(this.protocolName))
/*     */       return; 
/* 240 */     String oldProtocolName = this.protocolName;
/* 241 */     this.protocolName = protocolName;
/* 242 */     fireEvent("ProtocolName", oldProtocolName, protocolName);
/* 243 */     this.nodeSupport.resetPropertyEnvironment(false);
/*     */   }
/*     */   
/*     */   public void setProtocolAndServerNames(String protocolName, String serverName) {
/* 275 */     if (serverName == null)
/* 276 */       throw new IllegalArgumentException(JaiI18N.getString("Generic2")); 
/* 278 */     if (protocolName == null)
/* 279 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/* 281 */     boolean protocolNotChanged = protocolName.equalsIgnoreCase(this.protocolName);
/* 283 */     boolean serverNotChanged = serverName.equalsIgnoreCase(this.serverName);
/* 286 */     if (protocolNotChanged) {
/* 287 */       if (serverNotChanged)
/*     */         return; 
/* 292 */       setServerName(serverName);
/*     */       return;
/*     */     } 
/* 296 */     if (serverNotChanged) {
/* 298 */       setProtocolName(protocolName);
/*     */       return;
/*     */     } 
/* 303 */     String oldProtocolName = this.protocolName;
/* 304 */     String oldServerName = this.serverName;
/* 305 */     this.protocolName = protocolName;
/* 306 */     this.serverName = serverName;
/* 309 */     fireEvent("ProtocolAndServerName", new String[] { oldProtocolName, oldServerName }, new String[] { protocolName, serverName });
/* 312 */     this.nodeSupport.resetPropertyEnvironment(false);
/*     */   }
/*     */   
/*     */   private void fireEvent(String propName, Object oldVal, Object newVal) {
/* 317 */     if (this.eventManager != null) {
/* 318 */       Object eventSource = this.eventManager.getPropertyChangeEventSource();
/* 319 */       PropertyChangeEventJAI evt = new PropertyChangeEventJAI(eventSource, propName, oldVal, newVal);
/* 322 */       this.eventManager.firePropertyChange((PropertyChangeEvent)evt);
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getWidth() {
/* 335 */     findRemoteCRIF();
/* 336 */     Rectangle2D boundingBox = this.remoteCRIF.getBounds2D(this.serverName, this.nodeSupport.getOperationName(), this.nodeSupport.getParameterBlock());
/* 341 */     return (float)boundingBox.getWidth();
/*     */   }
/*     */   
/*     */   public float getHeight() {
/* 353 */     findRemoteCRIF();
/* 354 */     Rectangle2D boundingBox = this.remoteCRIF.getBounds2D(this.serverName, this.nodeSupport.getOperationName(), this.nodeSupport.getParameterBlock());
/* 359 */     return (float)boundingBox.getHeight();
/*     */   }
/*     */   
/*     */   public float getMinX() {
/* 369 */     findRemoteCRIF();
/* 370 */     Rectangle2D boundingBox = this.remoteCRIF.getBounds2D(this.serverName, this.nodeSupport.getOperationName(), this.nodeSupport.getParameterBlock());
/* 375 */     return (float)boundingBox.getX();
/*     */   }
/*     */   
/*     */   public float getMinY() {
/* 385 */     findRemoteCRIF();
/* 386 */     Rectangle2D boundingBox = this.remoteCRIF.getBounds2D(this.serverName, this.nodeSupport.getOperationName(), this.nodeSupport.getParameterBlock());
/* 391 */     return (float)boundingBox.getY();
/*     */   }
/*     */   
/*     */   public RenderedImage createRendering(RenderContext renderContext) {
/* 429 */     findRemoteCRIF();
/* 434 */     ParameterBlock renderedPB = (ParameterBlock)this.nodeSupport.getParameterBlock().clone();
/* 440 */     RenderContext rcIn = renderContext;
/* 441 */     RenderingHints nodeHints = this.nodeSupport.getRenderingHints();
/* 442 */     if (nodeHints != null) {
/* 443 */       RenderingHints mergedHints, hints = renderContext.getRenderingHints();
/* 445 */       if (hints == null) {
/* 446 */         mergedHints = nodeHints;
/* 447 */       } else if (nodeHints == null || nodeHints.isEmpty()) {
/* 448 */         mergedHints = hints;
/*     */       } else {
/* 450 */         mergedHints = new RenderingHints(nodeHints);
/* 451 */         mergedHints.add(hints);
/*     */       } 
/* 454 */       if (mergedHints != hints)
/* 455 */         rcIn = new RenderContext(renderContext.getTransform(), renderContext.getAreaOfInterest(), mergedHints); 
/*     */     } 
/* 462 */     Vector sources = this.nodeSupport.getParameterBlock().getSources();
/*     */     try {
/*     */       PlanarImage planarImage;
/* 465 */       if (sources != null) {
/* 466 */         Vector renderedSources = new Vector();
/* 467 */         for (int i = 0; i < sources.size(); i++) {
/* 469 */           RenderedImage rdrdImage = null;
/* 470 */           Object source = sources.elementAt(i);
/* 471 */           if (source instanceof RenderableImage) {
/* 472 */             RenderContext rcOut = this.remoteCRIF.mapRenderContext(this.serverName, this.nodeSupport.getOperationName(), i, renderContext, this.nodeSupport.getParameterBlock(), (RenderableImage)this);
/* 481 */             RenderableImage src = (RenderableImage)source;
/* 482 */             rdrdImage = src.createRendering(rcOut);
/* 483 */           } else if (source instanceof RenderedOp) {
/* 484 */             PlanarImage planarImage1 = ((RenderedOp)source).getRendering();
/* 485 */           } else if (source instanceof RenderedImage) {
/* 486 */             rdrdImage = (RenderedImage)source;
/*     */           } 
/* 489 */           if (rdrdImage == null)
/* 490 */             return null; 
/* 495 */           renderedSources.addElement(rdrdImage);
/*     */         } 
/* 498 */         if (renderedSources.size() > 0)
/* 499 */           renderedPB.setSources((Vector)renderedSources); 
/*     */       } 
/* 503 */       RenderedImage rendering = this.remoteCRIF.create(this.serverName, this.nodeSupport.getOperationName(), renderContext, renderedPB);
/* 509 */       if (rendering instanceof RenderedOp)
/* 510 */         planarImage = ((RenderedOp)rendering).getRendering(); 
/* 516 */       this.linkToRemoteOp = (RenderedImage)planarImage;
/* 519 */       if (planarImage != null && planarImage instanceof WritablePropertySource) {
/* 521 */         String[] propertyNames = getPropertyNames();
/* 522 */         if (propertyNames != null) {
/* 523 */           WritablePropertySource wps = (WritablePropertySource)planarImage;
/* 525 */           for (int j = 0; j < propertyNames.length; j++) {
/* 526 */             String name = propertyNames[j];
/* 527 */             Object value = getProperty(name);
/* 528 */             if (value != null && value != Image.UndefinedProperty)
/* 530 */               wps.setProperty(name, value); 
/*     */           } 
/*     */         } 
/*     */       } 
/* 536 */       return (RenderedImage)planarImage;
/* 537 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 539 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private RemoteCRIF findRemoteCRIF() {
/* 546 */     if (this.remoteCRIF == null) {
/* 548 */       this.remoteCRIF = RemoteCRIFRegistry.get(this.nodeSupport.getRegistry(), this.protocolName);
/* 551 */       if (this.remoteCRIF == null)
/* 552 */         throw new ImagingException(JaiI18N.getString("RemoteRenderableOp0")); 
/*     */     } 
/* 557 */     return this.remoteCRIF;
/*     */   }
/*     */   
/*     */   public int getRetryInterval() {
/* 569 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 570 */     if (rh == null)
/* 571 */       return 1000; 
/* 573 */     Integer i = (Integer)rh.get(JAI.KEY_RETRY_INTERVAL);
/* 574 */     if (i == null)
/* 575 */       return 1000; 
/* 577 */     return i.intValue();
/*     */   }
/*     */   
/*     */   public void setRetryInterval(int retryInterval) {
/* 600 */     if (retryInterval < 0)
/* 601 */       throw new IllegalArgumentException(JaiI18N.getString("Generic3")); 
/* 603 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 604 */     if (rh == null) {
/* 605 */       RenderingHints hints = new RenderingHints(null);
/* 606 */       this.nodeSupport.setRenderingHints(hints);
/*     */     } 
/* 609 */     this.nodeSupport.getRenderingHints().put(JAI.KEY_RETRY_INTERVAL, new Integer(retryInterval));
/*     */   }
/*     */   
/*     */   public int getNumRetries() {
/* 621 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 622 */     if (rh == null)
/* 623 */       return 5; 
/* 625 */     Integer i = (Integer)rh.get(JAI.KEY_NUM_RETRIES);
/* 626 */     if (i == null)
/* 627 */       return 5; 
/* 629 */     return i.intValue();
/*     */   }
/*     */   
/*     */   public void setNumRetries(int numRetries) {
/* 652 */     if (numRetries < 0)
/* 653 */       throw new IllegalArgumentException(JaiI18N.getString("Generic4")); 
/* 655 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 656 */     if (rh == null) {
/* 657 */       RenderingHints hints = new RenderingHints(null);
/* 658 */       this.nodeSupport.setRenderingHints(hints);
/*     */     } 
/* 661 */     this.nodeSupport.getRenderingHints().put(JAI.KEY_NUM_RETRIES, new Integer(numRetries));
/*     */   }
/*     */   
/*     */   public NegotiableCapabilitySet getNegotiationPreferences() {
/* 671 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 673 */     NegotiableCapabilitySet ncs = (rh == null) ? null : (NegotiableCapabilitySet)rh.get(JAI.KEY_NEGOTIATION_PREFERENCES);
/* 676 */     return ncs;
/*     */   }
/*     */   
/*     */   public void setNegotiationPreferences(NegotiableCapabilitySet preferences) {
/* 714 */     RenderingHints rh = this.nodeSupport.getRenderingHints();
/* 717 */     if (preferences != null) {
/* 720 */       if (rh == null) {
/* 721 */         RenderingHints hints = new RenderingHints(null);
/* 722 */         this.nodeSupport.setRenderingHints(hints);
/*     */       } 
/* 726 */       this.nodeSupport.getRenderingHints().put(JAI.KEY_NEGOTIATION_PREFERENCES, preferences);
/* 731 */     } else if (rh != null) {
/* 732 */       rh.remove(JAI.KEY_NEGOTIATION_PREFERENCES);
/*     */     } 
/* 736 */     this.negotiated = negotiate(preferences);
/*     */   }
/*     */   
/*     */   private NegotiableCapabilitySet negotiate(NegotiableCapabilitySet prefs) {
/*     */     RemoteImagingException remoteImagingException;
/* 741 */     OperationRegistry registry = this.nodeSupport.getRegistry();
/* 743 */     NegotiableCapabilitySet serverCap = null;
/* 746 */     RemoteDescriptor descriptor = (RemoteDescriptor)registry.getDescriptor(RemoteDescriptor.class, this.protocolName);
/* 749 */     if (descriptor == null) {
/* 750 */       Object[] msgArg0 = { new String(this.protocolName) };
/* 751 */       MessageFormat formatter = new MessageFormat("");
/* 752 */       formatter.setLocale(Locale.getDefault());
/* 753 */       formatter.applyPattern(JaiI18N.getString("RemoteJAI16"));
/* 754 */       throw new RuntimeException(formatter.format(msgArg0));
/*     */     } 
/* 757 */     int count = 0;
/* 758 */     int numRetries = getNumRetries();
/* 759 */     int retryInterval = getRetryInterval();
/* 761 */     Exception rieSave = null;
/* 762 */     while (count++ < numRetries) {
/*     */       try {
/* 764 */         serverCap = descriptor.getServerCapabilities(this.serverName);
/*     */         break;
/* 766 */       } catch (RemoteImagingException rie) {
/* 768 */         System.err.println(JaiI18N.getString("RemoteJAI24"));
/* 769 */         remoteImagingException = rie;
/*     */         try {
/* 772 */           Thread.sleep(retryInterval);
/* 773 */         } catch (InterruptedException ie) {
/* 775 */           sendExceptionToListener(JaiI18N.getString("Generic5"), (Exception)new ImagingException(JaiI18N.getString("Generic5"), ie));
/*     */         } 
/*     */       } 
/*     */     } 
/* 781 */     if (serverCap == null && count > numRetries)
/* 782 */       sendExceptionToListener(JaiI18N.getString("RemoteJAI18"), (Exception)remoteImagingException); 
/* 787 */     RemoteRIF rrif = (RemoteRIF)registry.getFactory("remoteRenderable", this.protocolName);
/* 790 */     return RemoteJAI.negotiate(prefs, serverCap, rrif.getClientCapabilities());
/*     */   }
/*     */   
/*     */   public NegotiableCapabilitySet getNegotiatedValues() throws RemoteImagingException {
/* 804 */     return this.negotiated;
/*     */   }
/*     */   
/*     */   public NegotiableCapability getNegotiatedValues(String category) throws RemoteImagingException {
/* 818 */     if (this.negotiated != null)
/* 819 */       return this.negotiated.getNegotiatedValue(category); 
/* 820 */     return null;
/*     */   }
/*     */   
/*     */   void sendExceptionToListener(String message, Exception e) {
/* 824 */     ImagingListener listener = (ImagingListener)getRenderingHints().get(JAI.KEY_IMAGING_LISTENER);
/* 827 */     listener.errorOccurred(message, e, this, false);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\RemoteRenderableOp.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */