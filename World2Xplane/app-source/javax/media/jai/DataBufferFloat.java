/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.DataBuffer;
/*     */ 
/*     */ public class DataBufferFloat extends DataBuffer {
/*     */   protected float[][] bankdata;
/*     */   
/*     */   protected float[] data;
/*     */   
/*     */   public DataBufferFloat(int size) {
/*  36 */     super(4, size);
/*  37 */     this.data = new float[size];
/*  38 */     this.bankdata = new float[1][];
/*  39 */     this.bankdata[0] = this.data;
/*     */   }
/*     */   
/*     */   public DataBufferFloat(int size, int numBanks) {
/*  53 */     super(4, size, numBanks);
/*  54 */     this.bankdata = new float[numBanks][];
/*  55 */     for (int i = 0; i < numBanks; i++)
/*  56 */       this.bankdata[i] = new float[size]; 
/*  58 */     this.data = this.bankdata[0];
/*     */   }
/*     */   
/*     */   public DataBufferFloat(float[] dataArray, int size) {
/*  73 */     super(4, size);
/*  74 */     if (dataArray.length < size)
/*  75 */       throw new RuntimeException(JaiI18N.getString("DataBuffer0")); 
/*  76 */     this.data = dataArray;
/*  77 */     this.bankdata = new float[1][];
/*  78 */     this.bankdata[0] = this.data;
/*     */   }
/*     */   
/*     */   public DataBufferFloat(float[] dataArray, int size, int offset) {
/*  96 */     super(4, size, 1, offset);
/*  97 */     if (dataArray.length < size)
/*  98 */       throw new RuntimeException(JaiI18N.getString("DataBuffer1")); 
/*  99 */     this.data = dataArray;
/* 100 */     this.bankdata = new float[1][];
/* 101 */     this.bankdata[0] = this.data;
/*     */   }
/*     */   
/*     */   public DataBufferFloat(float[][] dataArray, int size) {
/* 116 */     super(4, size, dataArray.length);
/* 117 */     this.bankdata = dataArray;
/* 118 */     this.data = this.bankdata[0];
/*     */   }
/*     */   
/*     */   public DataBufferFloat(float[][] dataArray, int size, int[] offsets) {
/* 135 */     super(4, size, dataArray.length, offsets);
/* 136 */     this.bankdata = dataArray;
/* 137 */     this.data = this.bankdata[0];
/*     */   }
/*     */   
/*     */   public float[] getData() {
/* 142 */     return this.data;
/*     */   }
/*     */   
/*     */   public float[] getData(int bank) {
/* 147 */     return this.bankdata[bank];
/*     */   }
/*     */   
/*     */   public float[][] getBankData() {
/* 152 */     return this.bankdata;
/*     */   }
/*     */   
/*     */   public int getElem(int i) {
/* 164 */     return Math.round(this.data[i + this.offset]);
/*     */   }
/*     */   
/*     */   public int getElem(int bank, int i) {
/* 177 */     return Math.round(this.bankdata[bank][i + this.offsets[bank]]);
/*     */   }
/*     */   
/*     */   public void setElem(int i, int val) {
/* 188 */     this.data[i + this.offset] = val;
/*     */   }
/*     */   
/*     */   public void setElem(int bank, int i, int val) {
/* 200 */     this.bankdata[bank][i + this.offsets[bank]] = val;
/*     */   }
/*     */   
/*     */   public float getElemFloat(int i) {
/* 212 */     return this.data[i + this.offset];
/*     */   }
/*     */   
/*     */   public float getElemFloat(int bank, int i) {
/* 225 */     return this.bankdata[bank][i + this.offsets[bank]];
/*     */   }
/*     */   
/*     */   public void setElemFloat(int i, float val) {
/* 236 */     this.data[i + this.offset] = val;
/*     */   }
/*     */   
/*     */   public void setElemFloat(int bank, int i, float val) {
/* 248 */     this.bankdata[bank][i + this.offsets[bank]] = val;
/*     */   }
/*     */   
/*     */   public double getElemDouble(int i) {
/* 260 */     return this.data[i + this.offset];
/*     */   }
/*     */   
/*     */   public double getElemDouble(int bank, int i) {
/* 273 */     return this.bankdata[bank][i + this.offsets[bank]];
/*     */   }
/*     */   
/*     */   public void setElemDouble(int i, double val) {
/* 284 */     this.data[i + this.offset] = (float)val;
/*     */   }
/*     */   
/*     */   public void setElemDouble(int bank, int i, double val) {
/* 296 */     this.bankdata[bank][i + this.offsets[bank]] = (float)val;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\DataBufferFloat.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */