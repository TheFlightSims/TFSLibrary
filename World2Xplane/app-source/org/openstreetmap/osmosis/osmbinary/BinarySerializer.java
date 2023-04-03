/*     */ package org.openstreetmap.osmosis.osmbinary;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.openstreetmap.osmosis.osmbinary.file.BlockOutputStream;
/*     */ import org.openstreetmap.osmosis.osmbinary.file.FileBlock;
/*     */ 
/*     */ public class BinarySerializer {
/*     */   public void configGranularity(int granularity) {
/*  57 */     this.granularity = granularity;
/*     */   }
/*     */   
/*     */   public void configOmit(boolean omit_metadata) {
/*  62 */     this.omit_metadata = omit_metadata;
/*     */   }
/*     */   
/*     */   public void configBatchLimit(int batch_limit) {
/*  67 */     this.batch_limit = batch_limit;
/*     */   }
/*     */   
/*  71 */   protected final int MIN_DENSE = 10;
/*     */   
/*  72 */   protected int batch_limit = 4000;
/*     */   
/*  76 */   protected int granularity = 100;
/*     */   
/*  77 */   protected int date_granularity = 1000;
/*     */   
/*     */   protected boolean omit_metadata = false;
/*     */   
/*  81 */   protected int batch_size = 0;
/*     */   
/*  82 */   protected int total_entities = 0;
/*     */   
/*  83 */   private StringTable stringtable = new StringTable();
/*     */   
/*  84 */   protected List<PrimGroupWriterInterface> groups = new ArrayList<PrimGroupWriterInterface>();
/*     */   
/*     */   protected BlockOutputStream output;
/*     */   
/*     */   long debug_bytes;
/*     */   
/*     */   public StringTable getStringTable() {
/*  92 */     return this.stringtable;
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/*  96 */     processBatch();
/*  97 */     this.output.flush();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 101 */     flush();
/* 102 */     this.output.close();
/*     */   }
/*     */   
/*     */   public BinarySerializer(BlockOutputStream output) {
/* 105 */     this.debug_bytes = 0L;
/*     */     this.output = output;
/*     */   }
/*     */   
/*     */   public void processBatch() {
/* 109 */     if (this.groups.size() == 0)
/*     */       return; 
/* 111 */     Osmformat.PrimitiveBlock.Builder primblock = Osmformat.PrimitiveBlock.newBuilder();
/* 113 */     this.stringtable.clear();
/* 115 */     for (PrimGroupWriterInterface i : this.groups)
/* 116 */       i.addStringsToStringtable(); 
/* 118 */     this.stringtable.finish();
/* 120 */     for (PrimGroupWriterInterface i : this.groups) {
/* 121 */       Osmformat.PrimitiveGroup group = i.serialize();
/* 122 */       if (group != null)
/* 123 */         primblock.addPrimitivegroup(group); 
/*     */     } 
/* 125 */     primblock.setStringtable(this.stringtable.serialize());
/* 126 */     primblock.setGranularity(this.granularity);
/* 127 */     primblock.setDateGranularity(this.date_granularity);
/* 131 */     Osmformat.PrimitiveBlock message = primblock.build();
/* 134 */     this.debug_bytes += message.getSerializedSize();
/*     */     try {
/* 139 */       this.output.write(FileBlock.newInstance("OSMData", message.toByteString(), null));
/* 141 */     } catch (IOException e) {
/* 143 */       e.printStackTrace();
/* 144 */       throw new Error(e);
/*     */     } finally {
/* 146 */       this.batch_size = 0;
/* 147 */       this.groups.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long mapRawDegrees(double degrees) {
/* 154 */     return (long)(degrees / 1.0E-9D);
/*     */   }
/*     */   
/*     */   public int mapDegrees(double degrees) {
/* 159 */     return (int)(degrees / 1.0E-7D / (this.granularity / 100));
/*     */   }
/*     */   
/*     */   protected static interface PrimGroupWriterInterface {
/*     */     void addStringsToStringtable();
/*     */     
/*     */     Osmformat.PrimitiveGroup serialize();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\BinarySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */