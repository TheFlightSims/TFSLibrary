/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRenderedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationRegistry;
/*     */ import javax.media.jai.TiledImage;
/*     */ import javax.media.jai.remote.SerializableRenderedImage;
/*     */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*     */ 
/*     */ public final class RenderedImageState extends SerializableStateImpl {
/*     */   private boolean isWritable;
/*     */   
/*     */   private transient boolean useDeepCopy;
/*     */   
/*     */   private transient OperationRegistry registry;
/*     */   
/*     */   private transient String formatName;
/*     */   
/*     */   private transient TileCodecParameterList encodingParam;
/*     */   
/*     */   private transient TileCodecParameterList decodingParam;
/*     */   
/*     */   public static Class[] getSupportedClasses() {
/*  43 */     return new Class[] { RenderedImage.class, WritableRenderedImage.class };
/*     */   }
/*     */   
/*     */   public RenderedImageState(Class c, Object o, RenderingHints h) {
/*  49 */     super(c, o, h);
/*  51 */     this.isWritable = o instanceof WritableRenderedImage;
/*  53 */     if (h != null) {
/*  54 */       Object value = h.get(JAI.KEY_SERIALIZE_DEEP_COPY);
/*  55 */       if (value != null) {
/*  56 */         this.useDeepCopy = ((Boolean)value).booleanValue();
/*     */       } else {
/*  58 */         this.useDeepCopy = false;
/*     */       } 
/*  61 */       value = h.get(JAI.KEY_OPERATION_REGISTRY);
/*  62 */       if (value != null)
/*  63 */         this.registry = (OperationRegistry)value; 
/*  66 */       value = h.get(JAI.KEY_TILE_CODEC_FORMAT);
/*  67 */       if (value != null)
/*  68 */         this.formatName = (String)value; 
/*  71 */       value = h.get(JAI.KEY_TILE_ENCODING_PARAM);
/*  72 */       if (value != null)
/*  73 */         this.encodingParam = (TileCodecParameterList)value; 
/*  76 */       value = h.get(JAI.KEY_TILE_DECODING_PARAM);
/*  77 */       if (value != null)
/*  78 */         this.decodingParam = (TileCodecParameterList)value; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*     */     SerializableRenderedImage sri;
/*  85 */     out.defaultWriteObject();
/*  89 */     if (this.formatName == null || this.encodingParam == null || this.decodingParam == null) {
/*  92 */       sri = new SerializableRenderedImage((RenderedImage)this.theObject, this.useDeepCopy);
/*     */     } else {
/*  95 */       sri = new SerializableRenderedImage((RenderedImage)this.theObject, this.useDeepCopy, this.registry, this.formatName, this.encodingParam, this.decodingParam);
/*     */     } 
/* 104 */     out.writeObject(sri);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 109 */     in.defaultReadObject();
/* 110 */     this.theObject = in.readObject();
/* 111 */     if (this.isWritable)
/* 112 */       this.theObject = new TiledImage((RenderedImage)this.theObject, true); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\RenderedImageState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */