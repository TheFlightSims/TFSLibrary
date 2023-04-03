/*    */ package org.geotools.data.sort;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.simple.SimpleFeatureReader;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ 
/*    */ class MergeSortReader implements SimpleFeatureReader {
/*    */   List<FeatureBlockReader> readers;
/*    */   
/*    */   RandomAccessFile raf;
/*    */   
/*    */   File file;
/*    */   
/*    */   SimpleFeatureType schema;
/*    */   
/*    */   Comparator<SimpleFeature> comparator;
/*    */   
/*    */   public MergeSortReader(SimpleFeatureType schema, RandomAccessFile raf, File file, List<FeatureBlockReader> readers, Comparator<SimpleFeature> comparator) {
/* 51 */     this.schema = schema;
/* 52 */     this.comparator = comparator;
/* 53 */     this.readers = readers;
/* 54 */     this.raf = raf;
/* 55 */     this.file = file;
/*    */   }
/*    */   
/*    */   public SimpleFeatureType getFeatureType() {
/* 59 */     return this.schema;
/*    */   }
/*    */   
/*    */   public SimpleFeature next() throws IOException, IllegalArgumentException, NoSuchElementException {
/* 64 */     if (this.readers.size() == 0)
/* 65 */       throw new NoSuchElementException(); 
/* 69 */     int selected = 0;
/* 70 */     for (int i = 1; i < this.readers.size(); i++) {
/* 71 */       SimpleFeature simpleFeature1 = ((FeatureBlockReader)this.readers.get(selected)).feature();
/* 72 */       SimpleFeature cf = ((FeatureBlockReader)this.readers.get(i)).feature();
/* 73 */       if (this.comparator.compare(simpleFeature1, cf) > 0)
/* 74 */         selected = i; 
/*    */     } 
/* 79 */     FeatureBlockReader reader = this.readers.get(selected);
/* 80 */     SimpleFeature sf = reader.feature();
/* 81 */     if (reader.next() == null)
/* 82 */       this.readers.remove(selected); 
/* 86 */     return sf;
/*    */   }
/*    */   
/*    */   public boolean hasNext() throws IOException {
/* 90 */     return (this.readers.size() > 0);
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/*    */     try {
/* 95 */       this.raf.close();
/*    */     } finally {
/* 97 */       this.file.delete();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\sort\MergeSortReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */