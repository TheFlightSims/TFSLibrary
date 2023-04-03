/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.SampleModel;
/*     */ 
/*     */ public final class RasterFormatTag {
/*     */   private static final int COPY_MASK = 384;
/*     */   
/*     */   private static final int UNCOPIED = 0;
/*     */   
/*     */   private static final int COPIED = 128;
/*     */   
/*     */   private int formatTagID;
/*     */   
/*     */   private int[] bankIndices;
/*     */   
/*     */   private int numBands;
/*     */   
/*     */   private int[] bandOffsets;
/*     */   
/*     */   private int pixelStride;
/*     */   
/*     */   private boolean isPixelSequential;
/*     */   
/*     */   public RasterFormatTag(SampleModel sampleModel, int formatTagID) {
/*  85 */     this.formatTagID = formatTagID;
/*  86 */     if ((formatTagID & 0x180) == 0) {
/*  87 */       ComponentSampleModel csm = (ComponentSampleModel)sampleModel;
/*  89 */       this.bankIndices = csm.getBankIndices();
/*  90 */       this.numBands = csm.getNumDataElements();
/*  91 */       this.bandOffsets = csm.getBandOffsets();
/*  92 */       this.pixelStride = csm.getPixelStride();
/*  94 */       if (this.pixelStride != this.bandOffsets.length) {
/*  95 */         this.isPixelSequential = false;
/*     */       } else {
/*  97 */         this.isPixelSequential = true;
/*  98 */         for (int i = 0; i < this.bandOffsets.length; i++) {
/*  99 */           if (this.bandOffsets[i] >= this.pixelStride || this.bankIndices[i] != this.bankIndices[0])
/* 101 */             this.isPixelSequential = false; 
/* 103 */           for (int j = i + 1; j < this.bandOffsets.length; j++) {
/* 104 */             if (this.bandOffsets[i] == this.bandOffsets[j])
/* 105 */               this.isPixelSequential = false; 
/*     */           } 
/* 108 */           if (!this.isPixelSequential)
/*     */             break; 
/*     */         } 
/*     */       } 
/* 111 */     } else if ((formatTagID & 0x180) == 128) {
/* 112 */       this.numBands = sampleModel.getNumBands();
/* 113 */       this.bandOffsets = new int[this.numBands];
/* 114 */       this.pixelStride = this.numBands;
/* 115 */       this.bankIndices = new int[this.numBands];
/* 117 */       for (int i = 0; i < this.numBands; i++) {
/* 118 */         this.bandOffsets[i] = i;
/* 119 */         this.bankIndices[i] = 0;
/*     */       } 
/* 121 */       this.isPixelSequential = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final boolean isPixelSequential() {
/* 139 */     return this.isPixelSequential;
/*     */   }
/*     */   
/*     */   public final int getFormatTagID() {
/* 147 */     return this.formatTagID;
/*     */   }
/*     */   
/*     */   public final int[] getBankIndices() {
/* 156 */     if (this.isPixelSequential)
/* 157 */       return this.bankIndices; 
/* 159 */     return null;
/*     */   }
/*     */   
/*     */   public final int getNumBands() {
/* 165 */     return this.numBands;
/*     */   }
/*     */   
/*     */   public final int[] getBandOffsets() {
/* 174 */     if (this.isPixelSequential)
/* 175 */       return this.bandOffsets; 
/* 177 */     return null;
/*     */   }
/*     */   
/*     */   public final int getPixelStride() {
/* 183 */     return this.pixelStride;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RasterFormatTag.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */