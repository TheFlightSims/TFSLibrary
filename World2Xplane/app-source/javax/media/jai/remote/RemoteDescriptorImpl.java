/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.net.URL;
/*     */ import javax.media.jai.OperationNode;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ 
/*     */ public abstract class RemoteDescriptorImpl implements RemoteDescriptor {
/*     */   protected String protocolName;
/*     */   
/*     */   protected URL serverNameDocURL;
/*     */   
/*     */   public RemoteDescriptorImpl(String protocolName, URL serverNameDocURL) {
/*  59 */     if (protocolName == null)
/*  60 */       throw new IllegalArgumentException(JaiI18N.getString("Generic1")); 
/*  63 */     this.protocolName = protocolName;
/*  64 */     this.serverNameDocURL = serverNameDocURL;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  73 */     return this.protocolName;
/*     */   }
/*     */   
/*     */   public String[] getSupportedModes() {
/*  85 */     return new String[] { "remoteRendered", "remoteRenderable" };
/*     */   }
/*     */   
/*     */   public boolean isModeSupported(String modeName) {
/* 103 */     if (modeName == null)
/* 104 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteDescriptorImpl1")); 
/* 108 */     if (modeName.equalsIgnoreCase("remoteRendered") || modeName.equalsIgnoreCase("remoteRenderable"))
/* 110 */       return true; 
/* 113 */     return false;
/*     */   }
/*     */   
/*     */   public boolean arePropertiesSupported() {
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 149 */     if (modeName == null)
/* 150 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteDescriptorImpl1")); 
/* 154 */     throw new UnsupportedOperationException(JaiI18N.getString("RemoteDescriptorImpl2"));
/*     */   }
/*     */   
/*     */   public URL getServerNameDocs() {
/* 164 */     return this.serverNameDocURL;
/*     */   }
/*     */   
/*     */   public Object getInvalidRegion(String registryModeName, String oldServerName, ParameterBlock oldParamBlock, RenderingHints oldHints, String newServerName, ParameterBlock newParamBlock, RenderingHints newHints, OperationNode node) throws RemoteImagingException {
/* 227 */     return null;
/*     */   }
/*     */   
/*     */   public ParameterListDescriptor getParameterListDescriptor(String modeName) {
/* 242 */     if (modeName == null)
/* 243 */       throw new IllegalArgumentException(JaiI18N.getString("RemoteDescriptorImpl1")); 
/* 247 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\RemoteDescriptorImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */