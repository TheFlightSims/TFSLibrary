/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ import javax.media.jai.remote.SerializerFactory;
/*     */ 
/*     */ public class RasterProxy implements Serializable {
/*     */   private transient Raster raster;
/*     */   
/*     */   public RasterProxy(Raster source) {
/*  51 */     this.raster = source;
/*     */   }
/*     */   
/*     */   public Raster getRaster() {
/*  59 */     return this.raster;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*     */     Raster r;
/*  70 */     if (this.raster.getParent() != null) {
/*  75 */       r = this.raster.createCompatibleWritableRaster(this.raster.getBounds());
/*  76 */       ((WritableRaster)r).setRect(this.raster);
/*     */     } else {
/*  78 */       r = this.raster;
/*     */     } 
/*  81 */     out.writeInt(r.getWidth());
/*  82 */     out.writeInt(r.getHeight());
/*  83 */     out.writeObject(SerializerFactory.getState(r.getSampleModel(), null));
/*  84 */     out.writeObject(SerializerFactory.getState(r.getDataBuffer(), null));
/*  85 */     out.writeObject(new Point(r.getMinX(), r.getMinY()));
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  97 */     SerializableState sampleModelState = null;
/*  98 */     SerializableState dataBufferState = null;
/*  99 */     Point location = null;
/* 101 */     int width = in.readInt();
/* 102 */     int height = in.readInt();
/* 103 */     sampleModelState = (SerializableState)in.readObject();
/* 104 */     dataBufferState = (SerializableState)in.readObject();
/* 105 */     location = (Point)in.readObject();
/* 108 */     SampleModel sampleModel = (SampleModel)sampleModelState.getObject();
/* 109 */     if (sampleModel == null) {
/* 110 */       this.raster = null;
/*     */       return;
/*     */     } 
/* 115 */     DataBuffer dataBuffer = (DataBuffer)dataBufferState.getObject();
/* 118 */     this.raster = Raster.createRaster(sampleModel, dataBuffer, location);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RasterProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */