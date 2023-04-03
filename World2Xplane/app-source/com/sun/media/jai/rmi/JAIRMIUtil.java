/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.RenderedOp;
/*     */ import javax.media.jai.remote.RemoteRenderedOp;
/*     */ import javax.media.jai.remote.SerializableRenderedImage;
/*     */ 
/*     */ public final class JAIRMIUtil {
/*     */   public static Vector replaceIdWithSources(Vector srcs, Hashtable nodes, String opName, RenderingHints hints) {
/*  40 */     Vector replacedSrcs = new Vector();
/*  42 */     for (int i = 0; i < srcs.size(); i++) {
/*  43 */       Object obj = srcs.elementAt(i);
/*  44 */       if (obj instanceof String) {
/*  45 */         String serverNodeDesc = (String)obj;
/*  46 */         int index = serverNodeDesc.indexOf("::");
/*  47 */         boolean diffServer = (index != -1);
/*  48 */         if (diffServer) {
/*  51 */           replacedSrcs.add(new RMIServerProxy(serverNodeDesc, opName, hints));
/*     */         } else {
/*  58 */           replacedSrcs.add((RMIServerProxy)nodes.get(Long.valueOf(serverNodeDesc)));
/*     */         } 
/*     */       } else {
/*  61 */         PlanarImage pi = PlanarImage.wrapRenderedImage((RenderedImage)obj);
/*  63 */         replacedSrcs.add(pi);
/*     */       } 
/*     */     } 
/*  67 */     return replacedSrcs;
/*     */   }
/*     */   
/*     */   public static Vector replaceSourcesWithId(Vector srcs, String serverName) {
/*  78 */     Vector replacedSrcs = new Vector();
/*  80 */     for (int i = 0; i < srcs.size(); i++) {
/*  81 */       Object obj = srcs.elementAt(i);
/*  82 */       if (obj instanceof RMIServerProxy) {
/*  83 */         RMIServerProxy rmisp = (RMIServerProxy)obj;
/*  84 */         if (rmisp.getServerName().equalsIgnoreCase(serverName)) {
/*  85 */           replacedSrcs.add(rmisp.getRMIID().toString());
/*     */         } else {
/*  87 */           String str = new String(rmisp.getServerName() + "::" + rmisp.getRMIID());
/*  90 */           replacedSrcs.add(str);
/*     */         } 
/*  92 */       } else if (obj instanceof RemoteRenderedOp) {
/*  93 */         RemoteRenderedOp rrop = (RemoteRenderedOp)obj;
/*  94 */         Object ai = rrop.getRendering();
/*  95 */         if (ai instanceof RMIServerProxy) {
/*  96 */           RMIServerProxy rmisp = (RMIServerProxy)ai;
/*  97 */           if (rmisp.getServerName().equalsIgnoreCase(serverName)) {
/*  98 */             replacedSrcs.add(rmisp.getRMIID().toString());
/*     */           } else {
/* 100 */             String str = new String(rmisp.getServerName() + "::" + rmisp.getRMIID());
/* 103 */             replacedSrcs.add(str);
/*     */           } 
/*     */         } else {
/* 106 */           RenderedImage ri = (RenderedImage)ai;
/* 107 */           replacedSrcs.add(new SerializableRenderedImage(ri));
/*     */         } 
/* 109 */       } else if (obj instanceof RenderedOp) {
/* 110 */         RenderedOp rop = (RenderedOp)obj;
/* 111 */         replacedSrcs.add(new SerializableRenderedImage((RenderedImage)rop.getRendering()));
/* 113 */       } else if (obj instanceof java.io.Serializable) {
/* 114 */         replacedSrcs.add(obj);
/* 115 */       } else if (obj instanceof RenderedImage) {
/* 116 */         RenderedImage ri = (RenderedImage)obj;
/* 117 */         replacedSrcs.add(new SerializableRenderedImage(ri));
/*     */       } 
/*     */     } 
/* 121 */     return replacedSrcs;
/*     */   }
/*     */   
/*     */   public static Object replaceImage(RenderedImage obj, String thisServerName) {
/* 131 */     if (obj instanceof RMIServerProxy) {
/* 133 */       RMIServerProxy rmisp = (RMIServerProxy)obj;
/* 134 */       if (rmisp.getServerName().equalsIgnoreCase(thisServerName))
/* 135 */         return "::" + rmisp.getRMIID(); 
/* 137 */       return rmisp.getServerName() + "::" + rmisp.getRMIID() + ";;" + rmisp.getOperationName();
/*     */     } 
/* 140 */     if (obj instanceof RenderedOp) {
/* 142 */       PlanarImage planarImage = ((RenderedOp)obj).getRendering();
/* 143 */       return replaceImage((RenderedImage)planarImage, thisServerName);
/*     */     } 
/* 145 */     if (obj instanceof RenderedImage) {
/* 147 */       if (obj instanceof java.io.Serializable)
/* 148 */         return obj; 
/* 150 */       return new SerializableRenderedImage(obj);
/*     */     } 
/* 153 */     return obj;
/*     */   }
/*     */   
/*     */   public static void checkClientParameters(ParameterBlock pb, String thisServerName) {
/* 167 */     if (pb == null)
/*     */       return; 
/* 170 */     int numParams = pb.getNumParameters();
/* 171 */     Vector params = pb.getParameters();
/* 174 */     for (int i = 0; i < numParams; i++) {
/* 175 */       Object obj = params.elementAt(i);
/* 177 */       if (obj != null)
/* 181 */         if (obj instanceof RenderedImage)
/* 183 */           pb.set(replaceImage((RenderedImage)obj, thisServerName), i);  
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void checkClientParameters(Vector parameters, String thisServerName) {
/* 200 */     if (parameters == null)
/*     */       return; 
/* 204 */     for (int i = 0; i < parameters.size(); i++) {
/* 205 */       Object obj = parameters.elementAt(i);
/* 207 */       if (obj != null)
/* 211 */         if (obj instanceof RenderedImage)
/* 213 */           parameters.set(i, replaceImage((RenderedImage)obj, thisServerName));  
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object replaceStringWithImage(String s, Hashtable nodes) {
/* 227 */     int index1 = s.indexOf("::");
/* 228 */     int index2 = s.indexOf(";;");
/* 231 */     if (index1 == -1)
/* 232 */       return s; 
/* 233 */     if (index2 == -1) {
/* 234 */       Long long_ = Long.valueOf(s.substring(index1 + 2));
/* 235 */       return nodes.get(long_);
/*     */     } 
/* 241 */     Long id = Long.valueOf(s.substring(index1 + 2, index2));
/* 242 */     String paramServerName = s.substring(0, index1);
/* 243 */     String opName = s.substring(index2 + 2);
/* 247 */     return new RMIServerProxy(paramServerName + "::" + id, opName, null);
/*     */   }
/*     */   
/*     */   public static void checkServerParameters(ParameterBlock pb, Hashtable nodes) {
/* 260 */     if (pb == null)
/*     */       return; 
/* 263 */     int numParams = pb.getNumParameters();
/* 264 */     Vector params = pb.getParameters();
/* 267 */     for (int i = 0; i < numParams; i++) {
/* 268 */       Object obj = params.elementAt(i);
/* 270 */       if (obj != null)
/* 272 */         if (obj instanceof String)
/* 273 */           pb.set(replaceStringWithImage((String)obj, nodes), i);  
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void checkServerParameters(Vector parameters, Hashtable nodes) {
/* 285 */     if (parameters == null)
/*     */       return; 
/* 289 */     for (int i = 0; i < parameters.size(); i++) {
/* 290 */       Object obj = parameters.elementAt(i);
/* 292 */       if (obj != null)
/* 294 */         if (obj instanceof String)
/* 295 */           parameters.set(i, replaceStringWithImage((String)obj, nodes));  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\JAIRMIUtil.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */