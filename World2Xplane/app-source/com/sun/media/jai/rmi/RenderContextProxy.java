/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import javax.media.jai.ROIShape;
/*     */ 
/*     */ public class RenderContextProxy implements Serializable {
/*     */   private transient RenderContext renderContext;
/*     */   
/*     */   public RenderContextProxy(RenderContext source) {
/*  43 */     this.renderContext = source;
/*     */   }
/*     */   
/*     */   public RenderContext getRenderContext() {
/*  51 */     return this.renderContext;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  60 */     boolean isNull = (this.renderContext == null);
/*  61 */     out.writeBoolean(isNull);
/*  62 */     if (isNull)
/*     */       return; 
/*  66 */     AffineTransform usr2dev = this.renderContext.getTransform();
/*  69 */     RenderingHintsProxy rhp = new RenderingHintsProxy(this.renderContext.getRenderingHints());
/*  72 */     Shape aoi = this.renderContext.getAreaOfInterest();
/*  75 */     out.writeObject(usr2dev);
/*  77 */     out.writeBoolean((aoi != null));
/*  78 */     if (aoi != null)
/*  79 */       if (aoi instanceof Serializable) {
/*  80 */         out.writeObject(aoi);
/*     */       } else {
/*  82 */         out.writeObject(new ROIShape(aoi));
/*     */       }  
/*  86 */     out.writeObject(rhp);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  97 */     if (in.readBoolean()) {
/*  98 */       this.renderContext = null;
/*     */       return;
/*     */     } 
/* 103 */     AffineTransform usr2dev = (AffineTransform)in.readObject();
/* 105 */     Shape shape = null;
/* 106 */     Object aoi = in.readBoolean() ? in.readObject() : null;
/* 108 */     RenderingHintsProxy rhp = (RenderingHintsProxy)in.readObject();
/* 110 */     RenderingHints hints = rhp.getRenderingHints();
/* 113 */     if (aoi != null)
/* 114 */       if (aoi instanceof ROIShape) {
/* 115 */         shape = ((ROIShape)aoi).getAsShape();
/*     */       } else {
/* 117 */         shape = (Shape)aoi;
/*     */       }  
/* 121 */     if (aoi == null && hints.isEmpty()) {
/* 122 */       this.renderContext = new RenderContext(usr2dev);
/* 123 */     } else if (aoi == null) {
/* 124 */       this.renderContext = new RenderContext(usr2dev, hints);
/* 125 */     } else if (hints.isEmpty()) {
/* 126 */       this.renderContext = new RenderContext(usr2dev, shape);
/*     */     } else {
/* 128 */       this.renderContext = new RenderContext(usr2dev, shape, hints);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RenderContextProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */