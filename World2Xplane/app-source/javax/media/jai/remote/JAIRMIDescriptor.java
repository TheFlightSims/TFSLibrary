/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import com.sun.media.jai.rmi.ImageServer;
/*     */ import com.sun.media.jai.rmi.JAIRMIUtil;
/*     */ import com.sun.media.jai.rmi.RMIServerProxy;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.net.InetAddress;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.rmi.Naming;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptor;
/*     */ import javax.media.jai.OperationNode;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.RenderedOp;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ import javax.media.jai.util.ImagingException;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public class JAIRMIDescriptor extends RemoteDescriptorImpl {
/*     */   public static final String IMAGE_SERVER_BIND_NAME = "JAIRMIRemoteServer1.1";
/*     */   
/*     */   private MessageFormat formatter;
/*     */   
/*     */   public JAIRMIDescriptor() throws MalformedURLException {
/* 162 */     super("jairmi", new URL("http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/remote/JAIRMIDescriptor.html"));
/* 165 */     this.formatter = new MessageFormat("");
/* 166 */     this.formatter.setLocale(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public OperationDescriptor[] getServerSupportedOperationList(String serverName) throws RemoteImagingException {
/* 185 */     List odList = null;
/*     */     try {
/* 187 */       odList = getImageServer(serverName).getOperationDescriptors();
/* 188 */     } catch (Exception e) {
/* 189 */       sendExceptionToListener(JaiI18N.getString("JAIRMIDescriptor12"), (Exception)new RemoteImagingException(JaiI18N.getString("JAIRMIDescriptor12"), e));
/*     */     } 
/* 194 */     OperationDescriptor[] od = new OperationDescriptor[odList.size()];
/* 195 */     int count = 0;
/* 196 */     for (Iterator i = odList.iterator(); i.hasNext();)
/* 197 */       od[count++] = i.next(); 
/* 200 */     return od;
/*     */   }
/*     */   
/*     */   private ImageServer getImageServer(String serverName) {
/* 205 */     if (serverName == null)
/*     */       try {
/* 207 */         serverName = InetAddress.getLocalHost().getHostAddress();
/* 208 */       } catch (Exception e) {
/* 209 */         sendExceptionToListener(JaiI18N.getString("JAIRMIDescriptor13"), (Exception)new ImagingException(JaiI18N.getString("JAIRMIDescriptor13"), e));
/*     */       }  
/* 216 */     String serviceName = new String("rmi://" + serverName + "/" + "JAIRMIRemoteServer1.1");
/* 219 */     ImageServer imageServer = null;
/*     */     try {
/* 222 */       imageServer = (ImageServer)Naming.lookup(serviceName);
/* 223 */     } catch (Exception e) {
/* 224 */       sendExceptionToListener(JaiI18N.getString("JAIRMIDescriptor14"), (Exception)new RemoteImagingException(JaiI18N.getString("JAIRMIDescriptor14"), e));
/*     */     } 
/* 229 */     return imageServer;
/*     */   }
/*     */   
/*     */   public NegotiableCapabilitySet getServerCapabilities(String serverName) throws RemoteImagingException {
/* 246 */     NegotiableCapabilitySet serverCapabilities = null;
/*     */     try {
/* 248 */       serverCapabilities = getImageServer(serverName).getServerCapabilities();
/* 250 */     } catch (Exception e) {
/* 251 */       sendExceptionToListener(JaiI18N.getString("JAIRMIDescriptor15"), (Exception)new RemoteImagingException(JaiI18N.getString("JAIRMIDescriptor15"), e));
/*     */     } 
/* 256 */     return serverCapabilities;
/*     */   }
/*     */   
/*     */   public Object getInvalidRegion(String registryModeName, String oldServerName, ParameterBlock oldParamBlock, RenderingHints oldHints, String newServerName, ParameterBlock newParamBlock, RenderingHints newHints, OperationNode node) throws RemoteImagingException {
/* 312 */     if (registryModeName == null)
/* 313 */       throw new IllegalArgumentException(JaiI18N.getString("JAIRMIDescriptor11")); 
/* 316 */     String operationName = node.getOperationName();
/* 317 */     OperationDescriptor[] oldDescs = getServerSupportedOperationList(oldServerName);
/* 319 */     OperationDescriptor oldOD = getOperationDescriptor(oldDescs, operationName);
/* 322 */     if (oldOD == null)
/* 323 */       throw new IllegalArgumentException(JaiI18N.getString("JAIRMIDescriptor1")); 
/* 326 */     int numSources = oldOD.getNumSources();
/* 332 */     ParameterListDescriptor oldPLD = null;
/* 333 */     if (registryModeName.equalsIgnoreCase("remoteRendered")) {
/* 334 */       oldPLD = oldOD.getParameterListDescriptor("rendered");
/* 335 */     } else if (registryModeName.equalsIgnoreCase("remoteRenderable")) {
/* 336 */       oldPLD = oldOD.getParameterListDescriptor("renderable");
/*     */     } else {
/* 338 */       oldPLD = oldOD.getParameterListDescriptor(registryModeName);
/*     */     } 
/* 341 */     int numParams = oldPLD.getNumParameters();
/* 344 */     if (oldServerName != newServerName) {
/* 348 */       OperationDescriptor[] newDescs = getServerSupportedOperationList(newServerName);
/*     */       OperationDescriptor newOD;
/* 352 */       if ((newOD = getOperationDescriptor(newDescs, operationName)) == null)
/* 354 */         throw new IllegalArgumentException(JaiI18N.getString("JAIRMIDescriptor2")); 
/* 360 */       if (numSources != newOD.getNumSources())
/* 361 */         throw new IllegalArgumentException(JaiI18N.getString("JAIRMIDescriptor3")); 
/* 365 */       ParameterListDescriptor newPLD = newOD.getParameterListDescriptor(registryModeName);
/* 368 */       if (numParams != newPLD.getNumParameters())
/* 369 */         throw new IllegalArgumentException(JaiI18N.getString("JAIRMIDescriptor4")); 
/* 373 */       String[] oldParamNames = oldPLD.getParamNames();
/* 374 */       if (oldParamNames == null)
/* 375 */         oldParamNames = new String[0]; 
/* 376 */       String[] newParamNames = newPLD.getParamNames();
/* 377 */       if (newParamNames == null)
/* 378 */         newParamNames = new String[0]; 
/* 380 */       Hashtable oldHash = hashNames(oldParamNames);
/* 381 */       Hashtable newHash = hashNames(newParamNames);
/* 384 */       if (!containsAll(oldHash, newHash))
/* 385 */         throw new IllegalArgumentException(JaiI18N.getString("JAIRMIDescriptor8")); 
/* 389 */       Class[] thisParamClasses = oldPLD.getParamClasses();
/* 390 */       Class[] otherParamClasses = newPLD.getParamClasses();
/* 391 */       for (int i = 0; i < oldParamNames.length; i++) {
/* 392 */         if (thisParamClasses[i] != otherParamClasses[getIndex(newHash, oldParamNames[i])])
/* 394 */           throw new IllegalArgumentException(JaiI18N.getString("JAIRMIDescriptor9")); 
/*     */       } 
/* 401 */       return null;
/*     */     } 
/* 408 */     if (registryModeName == null || ((numSources > 0 || numParams > 0) && (oldParamBlock == null || newParamBlock == null)))
/* 411 */       throw new IllegalArgumentException(JaiI18N.getString("JAIRMIDescriptor5")); 
/* 417 */     if (numSources > 0 && (oldParamBlock.getNumSources() != numSources || newParamBlock.getNumSources() != numSources)) {
/* 420 */       Object[] msgArg0 = { operationName, new Integer(numParams) };
/* 424 */       this.formatter.applyPattern(JaiI18N.getString("JAIRMIDescriptor6"));
/* 425 */       throw new IllegalArgumentException(this.formatter.format(msgArg0));
/*     */     } 
/* 430 */     if (numParams > 0 && (oldParamBlock.getNumParameters() != numParams || newParamBlock.getNumParameters() != numParams)) {
/* 433 */       Object[] msgArg0 = { operationName, new Integer(numParams) };
/* 437 */       this.formatter.applyPattern(JaiI18N.getString("JAIRMIDescriptor7"));
/* 438 */       throw new IllegalArgumentException(this.formatter.format(msgArg0));
/*     */     } 
/* 443 */     RenderedOp op = (RenderedOp)node;
/* 444 */     Object rendering = op.getRendering();
/* 445 */     Long id = null;
/* 446 */     if (rendering instanceof RMIServerProxy) {
/* 447 */       id = ((RMIServerProxy)rendering).getRMIID();
/*     */     } else {
/* 449 */       throw new RuntimeException(JaiI18N.getString("JAIRMIDescriptor10"));
/*     */     } 
/* 457 */     boolean samePBs = false;
/* 458 */     if (oldParamBlock == newParamBlock)
/* 459 */       samePBs = true; 
/* 461 */     Vector oldSources = oldParamBlock.getSources();
/* 462 */     oldParamBlock.removeSources();
/* 465 */     JAIRMIUtil.checkClientParameters(oldParamBlock, oldServerName);
/* 466 */     oldParamBlock.setSources(JAIRMIUtil.replaceSourcesWithId(oldSources, oldServerName));
/* 469 */     if (samePBs) {
/* 470 */       newParamBlock = oldParamBlock;
/*     */     } else {
/* 472 */       Vector newSources = newParamBlock.getSources();
/* 473 */       newParamBlock.removeSources();
/* 476 */       JAIRMIUtil.checkClientParameters(newParamBlock, oldServerName);
/* 477 */       newParamBlock.setSources(JAIRMIUtil.replaceSourcesWithId(newSources, oldServerName));
/*     */     } 
/* 483 */     SerializableState oldRHS = SerializerFactory.getState(oldHints, null);
/* 484 */     SerializableState newRHS = SerializerFactory.getState(newHints, null);
/* 486 */     SerializableState shapeState = null;
/*     */     try {
/* 488 */       shapeState = getImageServer(oldServerName).getInvalidRegion(id, oldParamBlock, oldRHS, newParamBlock, newRHS);
/* 494 */     } catch (Exception e) {
/* 495 */       sendExceptionToListener(JaiI18N.getString("JAIRMIDescriptor16"), (Exception)new RemoteImagingException(JaiI18N.getString("JAIRMIDescriptor16"), e));
/*     */     } 
/* 500 */     return shapeState.getObject();
/*     */   }
/*     */   
/*     */   private Hashtable hashNames(String[] paramNames) {
/* 505 */     Hashtable h = new Hashtable();
/* 506 */     if (paramNames != null)
/* 507 */       for (int i = 0; i < paramNames.length; i++)
/* 508 */         h.put(new CaselessStringKey(paramNames[i]), new Integer(i));  
/* 512 */     return h;
/*     */   }
/*     */   
/*     */   private int getIndex(Hashtable h, String s) {
/* 516 */     return ((Integer)h.get(new CaselessStringKey(s))).intValue();
/*     */   }
/*     */   
/*     */   private boolean containsAll(Hashtable thisHash, Hashtable otherHash) {
/* 523 */     for (Enumeration i = thisHash.keys(); i.hasMoreElements(); ) {
/* 524 */       CaselessStringKey thisNameKey = i.nextElement();
/* 525 */       if (!otherHash.containsKey(thisNameKey))
/* 526 */         return false; 
/*     */     } 
/* 529 */     return true;
/*     */   }
/*     */   
/*     */   private OperationDescriptor getOperationDescriptor(OperationDescriptor[] descriptors, String operationName) {
/* 537 */     for (int i = 0; i < descriptors.length; i++) {
/* 538 */       OperationDescriptor od = descriptors[i];
/* 539 */       if (od.getName().equalsIgnoreCase(operationName))
/* 540 */         return od; 
/*     */     } 
/* 543 */     return null;
/*     */   }
/*     */   
/*     */   void sendExceptionToListener(String message, Exception e) {
/* 547 */     ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 548 */     listener.errorOccurred(message, e, this, false);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\JAIRMIDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */