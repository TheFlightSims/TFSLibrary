/*      */ package javax.media.jai.remote;
/*      */ 
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.text.MessageFormat;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.JAI;
/*      */ import javax.media.jai.OperationDescriptor;
/*      */ import javax.media.jai.OperationRegistry;
/*      */ import javax.media.jai.PlanarImage;
/*      */ import javax.media.jai.TileCache;
/*      */ import javax.media.jai.util.CaselessStringKey;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class RemoteJAI {
/*      */   protected String serverName;
/*      */   
/*      */   protected String protocolName;
/*      */   
/*  177 */   private OperationRegistry operationRegistry = JAI.getDefaultInstance().getOperationRegistry();
/*      */   
/*      */   public static final int DEFAULT_RETRY_INTERVAL = 1000;
/*      */   
/*      */   public static final int DEFAULT_NUM_RETRIES = 5;
/*      */   
/*  189 */   private int retryInterval = 1000;
/*      */   
/*  192 */   private int numRetries = 5;
/*      */   
/*  195 */   private transient TileCache cache = JAI.getDefaultInstance().getTileCache();
/*      */   
/*      */   private RenderingHints renderingHints;
/*      */   
/*  208 */   private NegotiableCapabilitySet preferences = null;
/*      */   
/*      */   private static NegotiableCapabilitySet negotiated;
/*      */   
/*  217 */   private NegotiableCapabilitySet serverCapabilities = null;
/*      */   
/*  218 */   private NegotiableCapabilitySet clientCapabilities = null;
/*      */   
/*  224 */   private Hashtable odHash = null;
/*      */   
/*  227 */   private OperationDescriptor[] descriptors = null;
/*      */   
/*      */   private static MessageFormat formatter;
/*      */   
/*      */   public RemoteJAI(String protocolName, String serverName) {
/*  252 */     this(protocolName, serverName, null, null);
/*      */   }
/*      */   
/*      */   public RemoteJAI(String protocolName, String serverName, OperationRegistry registry, TileCache tileCache) {
/*  284 */     if (protocolName == null)
/*  285 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/*  289 */     formatter = new MessageFormat("");
/*  290 */     formatter.setLocale(Locale.getDefault());
/*  292 */     this.protocolName = protocolName;
/*  293 */     this.serverName = serverName;
/*  298 */     if (registry != null)
/*  299 */       this.operationRegistry = registry; 
/*  302 */     if (tileCache != null)
/*  303 */       this.cache = tileCache; 
/*  306 */     this.renderingHints = new RenderingHints(null);
/*  307 */     this.renderingHints.put(JAI.KEY_OPERATION_REGISTRY, this.operationRegistry);
/*  308 */     this.renderingHints.put(JAI.KEY_TILE_CACHE, this.cache);
/*  309 */     this.renderingHints.put(JAI.KEY_RETRY_INTERVAL, new Integer(this.retryInterval));
/*  311 */     this.renderingHints.put(JAI.KEY_NUM_RETRIES, new Integer(this.numRetries));
/*      */   }
/*      */   
/*      */   public String getServerName() {
/*  318 */     return this.serverName;
/*      */   }
/*      */   
/*      */   public String getProtocolName() {
/*  325 */     return this.protocolName;
/*      */   }
/*      */   
/*      */   public void setRetryInterval(int retryInterval) {
/*  340 */     if (retryInterval < 0)
/*  341 */       throw new IllegalArgumentException(JaiI18N.getString("Generic3")); 
/*  344 */     this.retryInterval = retryInterval;
/*  345 */     this.renderingHints.put(JAI.KEY_RETRY_INTERVAL, new Integer(retryInterval));
/*      */   }
/*      */   
/*      */   public int getRetryInterval() {
/*  352 */     return this.retryInterval;
/*      */   }
/*      */   
/*      */   public void setNumRetries(int numRetries) {
/*  365 */     if (numRetries < 0)
/*  366 */       throw new IllegalArgumentException(JaiI18N.getString("Generic4")); 
/*  369 */     this.numRetries = numRetries;
/*  370 */     this.renderingHints.put(JAI.KEY_NUM_RETRIES, new Integer(numRetries));
/*      */   }
/*      */   
/*      */   public int getNumRetries() {
/*  377 */     return this.numRetries;
/*      */   }
/*      */   
/*      */   public OperationRegistry getOperationRegistry() {
/*  385 */     return this.operationRegistry;
/*      */   }
/*      */   
/*      */   public void setOperationRegistry(OperationRegistry operationRegistry) {
/*  397 */     if (operationRegistry == null)
/*  398 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI4")); 
/*  401 */     this.operationRegistry = operationRegistry;
/*  402 */     this.renderingHints.put(JAI.KEY_OPERATION_REGISTRY, operationRegistry);
/*      */   }
/*      */   
/*      */   public void setTileCache(TileCache tileCache) {
/*  414 */     if (tileCache == null)
/*  415 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI5")); 
/*  418 */     this.cache = tileCache;
/*  419 */     this.renderingHints.put(JAI.KEY_TILE_CACHE, this.cache);
/*      */   }
/*      */   
/*      */   public TileCache getTileCache() {
/*  427 */     return this.cache;
/*      */   }
/*      */   
/*      */   public RenderingHints getRenderingHints() {
/*  437 */     return this.renderingHints;
/*      */   }
/*      */   
/*      */   public void setRenderingHints(RenderingHints hints) {
/*  449 */     if (hints == null)
/*  450 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI6")); 
/*  453 */     this.renderingHints = hints;
/*      */   }
/*      */   
/*      */   public void clearRenderingHints() {
/*  461 */     this.renderingHints = new RenderingHints(null);
/*      */   }
/*      */   
/*      */   public Object getRenderingHint(RenderingHints.Key key) {
/*  472 */     if (key == null)
/*  473 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI7")); 
/*  476 */     return this.renderingHints.get(key);
/*      */   }
/*      */   
/*      */   public void setRenderingHint(RenderingHints.Key key, Object value) {
/*  491 */     if (key == null)
/*  492 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI7")); 
/*  495 */     if (value == null)
/*  496 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI8")); 
/*      */     try {
/*  501 */       this.renderingHints.put(key, value);
/*  502 */     } catch (Exception e) {
/*  503 */       throw new IllegalArgumentException(e.toString());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void removeRenderingHint(RenderingHints.Key key) {
/*  512 */     this.renderingHints.remove(key);
/*      */   }
/*      */   
/*      */   public RemoteRenderedOp create(String opName, ParameterBlock args, RenderingHints hints) {
/*      */     RenderingHints mergedHints;
/*  585 */     if (opName == null)
/*  586 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI9")); 
/*  590 */     if (args == null)
/*  591 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI10")); 
/*  596 */     getServerSupportedOperationList();
/*  599 */     OperationDescriptor odesc = (OperationDescriptor)this.odHash.get(new CaselessStringKey(opName));
/*  602 */     if (odesc == null)
/*  603 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI11")); 
/*  608 */     if (!odesc.isModeSupported("rendered"))
/*  609 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI12")); 
/*  614 */     if (!RenderedImage.class.isAssignableFrom(odesc.getDestClass("rendered")))
/*  616 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI13")); 
/*  623 */     StringBuffer msg = new StringBuffer();
/*  624 */     args = (ParameterBlock)args.clone();
/*  625 */     if (!odesc.validateArguments("rendered", args, msg))
/*  626 */       throw new IllegalArgumentException(msg.toString()); 
/*  631 */     if (hints == null) {
/*  632 */       mergedHints = this.renderingHints;
/*  633 */     } else if (this.renderingHints.isEmpty()) {
/*  634 */       mergedHints = hints;
/*      */     } else {
/*  636 */       mergedHints = new RenderingHints(this.renderingHints);
/*  637 */       mergedHints.add(hints);
/*      */     } 
/*  640 */     RemoteRenderedOp op = new RemoteRenderedOp(this.operationRegistry, this.protocolName, this.serverName, opName, args, mergedHints);
/*  648 */     if (odesc.isImmediate()) {
/*  649 */       PlanarImage im = null;
/*  650 */       im = op.getRendering();
/*  652 */       if (im == null)
/*  654 */         return null; 
/*      */     } 
/*  659 */     return op;
/*      */   }
/*      */   
/*      */   public RemoteRenderableOp createRenderable(String opName, ParameterBlock args) {
/*  720 */     if (opName == null)
/*  721 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI9")); 
/*  725 */     if (args == null)
/*  726 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI10")); 
/*  731 */     getServerSupportedOperationList();
/*  734 */     OperationDescriptor odesc = (OperationDescriptor)this.odHash.get(new CaselessStringKey(opName));
/*  737 */     if (odesc == null)
/*  738 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI11")); 
/*  743 */     if (!odesc.isModeSupported("renderable"))
/*  744 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI14")); 
/*  749 */     if (!RenderableImage.class.isAssignableFrom(odesc.getDestClass("renderable")))
/*  751 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI15")); 
/*  758 */     StringBuffer msg = new StringBuffer();
/*  759 */     args = (ParameterBlock)args.clone();
/*  760 */     if (!odesc.validateArguments("renderable", args, msg))
/*  761 */       throw new IllegalArgumentException(msg.toString()); 
/*  764 */     RemoteRenderableOp op = new RemoteRenderableOp(this.operationRegistry, this.protocolName, this.serverName, opName, args);
/*  770 */     op.setRenderingHints(this.renderingHints);
/*  773 */     return op;
/*      */   }
/*      */   
/*      */   public void setNegotiationPreferences(NegotiableCapabilitySet preferences) {
/*  806 */     this.preferences = preferences;
/*  808 */     if (preferences == null) {
/*  809 */       this.renderingHints.remove(JAI.KEY_NEGOTIATION_PREFERENCES);
/*      */     } else {
/*  811 */       this.renderingHints.put(JAI.KEY_NEGOTIATION_PREFERENCES, preferences);
/*      */     } 
/*  815 */     negotiated = null;
/*  816 */     getNegotiatedValues();
/*      */   }
/*      */   
/*      */   public NegotiableCapabilitySet getNegotiatedValues() throws RemoteImagingException {
/*  838 */     if (negotiated == null) {
/*  840 */       if (this.serverCapabilities == null)
/*  841 */         this.serverCapabilities = getServerCapabilities(); 
/*  844 */       if (this.clientCapabilities == null)
/*  845 */         this.clientCapabilities = getClientCapabilities(); 
/*  849 */       negotiated = negotiate(this.preferences, this.serverCapabilities, this.clientCapabilities);
/*      */     } 
/*  854 */     return negotiated;
/*      */   }
/*      */   
/*      */   public NegotiableCapability getNegotiatedValues(String category) throws RemoteImagingException {
/*  884 */     if (negotiated == null) {
/*  886 */       if (this.serverCapabilities == null)
/*  887 */         this.serverCapabilities = getServerCapabilities(); 
/*  890 */       if (this.clientCapabilities == null)
/*  891 */         this.clientCapabilities = getClientCapabilities(); 
/*  895 */       return negotiate(this.preferences, this.serverCapabilities, this.clientCapabilities, category);
/*      */     } 
/*  903 */     return negotiated.getNegotiatedValue(category);
/*      */   }
/*      */   
/*      */   public static NegotiableCapabilitySet negotiate(NegotiableCapabilitySet preferences, NegotiableCapabilitySet serverCapabilities, NegotiableCapabilitySet clientCapabilities) {
/*  950 */     if (serverCapabilities == null || clientCapabilities == null)
/*  951 */       return null; 
/*  953 */     if (serverCapabilities != null && serverCapabilities.isPreference() == true)
/*  955 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI20")); 
/*  958 */     if (clientCapabilities != null && clientCapabilities.isPreference() == true)
/*  960 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI21")); 
/*  963 */     if (preferences == null)
/*  964 */       return serverCapabilities.negotiate(clientCapabilities); 
/*  966 */     if (!preferences.isPreference())
/*  967 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI19")); 
/*  970 */     NegotiableCapabilitySet clientServerCap = serverCapabilities.negotiate(clientCapabilities);
/*  972 */     if (clientServerCap == null)
/*  973 */       return null; 
/*  974 */     return clientServerCap.negotiate(preferences);
/*      */   }
/*      */   
/*      */   public static NegotiableCapability negotiate(NegotiableCapabilitySet preferences, NegotiableCapabilitySet serverCapabilities, NegotiableCapabilitySet clientCapabilities, String category) {
/* 1024 */     if (serverCapabilities == null || clientCapabilities == null)
/* 1025 */       return null; 
/* 1027 */     if (serverCapabilities != null && serverCapabilities.isPreference() == true)
/* 1029 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI20")); 
/* 1032 */     if (clientCapabilities != null && clientCapabilities.isPreference() == true)
/* 1034 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI21")); 
/* 1037 */     if (preferences != null && !preferences.isPreference())
/* 1038 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI19")); 
/* 1041 */     if (category == null)
/* 1042 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteJAI26")); 
/* 1045 */     if (preferences == null || preferences.isEmpty())
/* 1046 */       return serverCapabilities.getNegotiatedValue(clientCapabilities, category); 
/* 1050 */     List prefList = preferences.get(category);
/* 1051 */     List serverList = serverCapabilities.get(category);
/* 1052 */     List clientList = clientCapabilities.get(category);
/* 1053 */     Iterator p = prefList.iterator();
/* 1057 */     NegotiableCapability pref = null;
/* 1059 */     if (!p.hasNext()) {
/* 1060 */       pref = null;
/*      */     } else {
/* 1062 */       pref = p.next();
/*      */     } 
/* 1064 */     Vector results = new Vector();
/* 1067 */     for (Iterator s = serverList.iterator(); s.hasNext(); ) {
/* 1068 */       NegotiableCapability server = s.next();
/* 1069 */       for (Iterator c = clientList.iterator(); c.hasNext(); ) {
/* 1070 */         NegotiableCapability client = c.next();
/* 1072 */         NegotiableCapability result = server.negotiate(client);
/* 1073 */         if (result == null)
/*      */           continue; 
/* 1079 */         results.add(result);
/* 1081 */         if (pref != null)
/* 1084 */           result = result.negotiate(pref); 
/* 1087 */         if (result != null)
/* 1088 */           return result; 
/*      */       } 
/*      */     } 
/* 1094 */     while (p.hasNext()) {
/* 1095 */       pref = p.next();
/* 1096 */       for (int r = 0; r < results.size(); r++) {
/*      */         NegotiableCapability result;
/* 1097 */         if ((result = pref.negotiate(results.elementAt(r))) != null)
/* 1099 */           return result; 
/*      */       } 
/*      */     } 
/* 1105 */     return null;
/*      */   }
/*      */   
/*      */   public NegotiableCapabilitySet getServerCapabilities() throws RemoteImagingException {
/* 1117 */     if (this.serverCapabilities == null) {
/*      */       RemoteImagingException remoteImagingException;
/* 1120 */       RemoteDescriptor descriptor = (RemoteDescriptor)this.operationRegistry.getDescriptor(RemoteDescriptor.class, this.protocolName);
/* 1124 */       if (descriptor == null) {
/* 1125 */         Object[] msgArg0 = { new String(this.protocolName) };
/* 1126 */         formatter.applyPattern(JaiI18N.getString("RemoteJAI16"));
/* 1127 */         throw new RuntimeException(formatter.format(msgArg0));
/*      */       } 
/* 1129 */       Exception rieSave = null;
/* 1130 */       int count = 0;
/* 1131 */       while (count++ < this.numRetries) {
/*      */         try {
/* 1133 */           this.serverCapabilities = descriptor.getServerCapabilities(this.serverName);
/*      */           break;
/* 1136 */         } catch (RemoteImagingException rie) {
/* 1138 */           System.err.println(JaiI18N.getString("RemoteJAI24"));
/* 1139 */           remoteImagingException = rie;
/*      */           try {
/* 1142 */             Thread.sleep(this.retryInterval);
/* 1143 */           } catch (InterruptedException ie) {
/* 1144 */             sendExceptionToListener(JaiI18N.getString("Generic5"), (Exception)new ImagingException(JaiI18N.getString("Generic5"), ie));
/*      */           } 
/*      */         } 
/*      */       } 
/* 1151 */       if (this.serverCapabilities == null && count > this.numRetries)
/* 1152 */         sendExceptionToListener(JaiI18N.getString("RemoteJAI18"), (Exception)remoteImagingException); 
/*      */     } 
/* 1158 */     return this.serverCapabilities;
/*      */   }
/*      */   
/*      */   public NegotiableCapabilitySet getClientCapabilities() {
/* 1166 */     if (this.clientCapabilities == null) {
/* 1168 */       RemoteRIF rrif = (RemoteRIF)this.operationRegistry.getFactory("remoteRendered", this.protocolName);
/* 1171 */       if (rrif == null)
/* 1172 */         rrif = (RemoteRIF)this.operationRegistry.getFactory("remoteRenderable", this.protocolName); 
/* 1177 */       if (rrif == null) {
/* 1178 */         Object[] msgArg0 = { new String(this.protocolName) };
/* 1179 */         formatter.applyPattern(JaiI18N.getString("RemoteJAI17"));
/* 1180 */         throw new RuntimeException(formatter.format(msgArg0));
/*      */       } 
/* 1183 */       this.clientCapabilities = rrif.getClientCapabilities();
/*      */     } 
/* 1186 */     return this.clientCapabilities;
/*      */   }
/*      */   
/*      */   public OperationDescriptor[] getServerSupportedOperationList() throws RemoteImagingException {
/* 1199 */     if (this.descriptors == null) {
/*      */       RemoteImagingException remoteImagingException;
/* 1202 */       RemoteDescriptor descriptor = (RemoteDescriptor)this.operationRegistry.getDescriptor(RemoteDescriptor.class, this.protocolName);
/* 1206 */       if (descriptor == null) {
/* 1207 */         Object[] msgArg0 = { new String(this.protocolName) };
/* 1208 */         formatter.applyPattern(JaiI18N.getString("RemoteJAI16"));
/* 1209 */         throw new RuntimeException(formatter.format(msgArg0));
/*      */       } 
/* 1211 */       Exception rieSave = null;
/* 1212 */       int count = 0;
/* 1213 */       while (count++ < this.numRetries) {
/*      */         try {
/* 1215 */           this.descriptors = descriptor.getServerSupportedOperationList(this.serverName);
/*      */           break;
/* 1218 */         } catch (RemoteImagingException rie) {
/* 1220 */           System.err.println(JaiI18N.getString("RemoteJAI25"));
/* 1221 */           remoteImagingException = rie;
/*      */           try {
/* 1224 */             Thread.sleep(this.retryInterval);
/* 1225 */           } catch (InterruptedException ie) {
/* 1227 */             sendExceptionToListener(JaiI18N.getString("Generic5"), (Exception)new ImagingException(JaiI18N.getString("Generic5"), ie));
/*      */           } 
/*      */         } 
/*      */       } 
/* 1233 */       if (this.descriptors == null && count > this.numRetries)
/* 1234 */         sendExceptionToListener(JaiI18N.getString("RemoteJAI23"), (Exception)remoteImagingException); 
/* 1241 */       this.odHash = new Hashtable();
/* 1242 */       for (int i = 0; i < this.descriptors.length; i++)
/* 1243 */         this.odHash.put(new CaselessStringKey(this.descriptors[i].getName()), this.descriptors[i]); 
/*      */     } 
/* 1248 */     return this.descriptors;
/*      */   }
/*      */   
/*      */   void sendExceptionToListener(String message, Exception e) {
/* 1252 */     ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 1253 */     listener.errorOccurred(message, e, this, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\RemoteJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */