/*     */ package org.geotools.data.sort;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.io.WKBWriter;
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.collection.ListFeatureCollection;
/*     */ import org.geotools.data.simple.DelegateSimpleFeatureReader;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.data.simple.SimpleFeatureReader;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.filter.sort.SortOrder;
/*     */ 
/*     */ class MergeSortDumper {
/*     */   static final boolean canSort(SimpleFeatureType schema, SortBy[] sortBy) {
/*  49 */     if (sortBy == SortBy.UNSORTED)
/*  50 */       return true; 
/*  54 */     for (AttributeDescriptor ad : schema.getAttributeDescriptors()) {
/*  55 */       Class<?> binding = ad.getType().getBinding();
/*  56 */       if (!Serializable.class.isAssignableFrom(binding))
/*  57 */         return false; 
/*     */     } 
/*  62 */     for (SortBy sb : sortBy) {
/*  63 */       if (sb != SortBy.NATURAL_ORDER && sb != SortBy.REVERSE_ORDER) {
/*  64 */         AttributeDescriptor ad = schema.getDescriptor(sb.getPropertyName().getPropertyName());
/*  66 */         Class<?> binding = ad.getType().getBinding();
/*  67 */         if (ad == null || !Comparable.class.isAssignableFrom(binding) || Geometry.class.isAssignableFrom(binding))
/*  69 */           return false; 
/*     */       } 
/*     */     } 
/*  74 */     return true;
/*     */   }
/*     */   
/*     */   static SimpleFeatureReader getDelegateReader(SimpleFeatureReader reader, Query query) throws IOException {
/*  79 */     Hints hints = query.getHints();
/*  80 */     int maxFeatures = 1000;
/*  81 */     if (hints != null && hints.get(Hints.MAX_MEMORY_SORT) != null) {
/*  82 */       maxFeatures = ((Integer)hints.get(Hints.MAX_MEMORY_SORT)).intValue();
/*  83 */     } else if (Hints.getSystemDefault((RenderingHints.Key)Hints.MAX_MEMORY_SORT) != null) {
/*  84 */       maxFeatures = ((Integer)Hints.getSystemDefault((RenderingHints.Key)Hints.MAX_MEMORY_SORT)).intValue();
/*     */     } 
/*  87 */     return getDelegateReader(reader, query.getSortBy(), maxFeatures);
/*     */   }
/*     */   
/*     */   static SimpleFeatureReader getDelegateReader(SimpleFeatureReader reader, SortBy[] sortBy, int maxFeatures) throws IOException {
/*  92 */     Comparator<SimpleFeature> comparator = getComparator(sortBy);
/*  95 */     if (comparator == null)
/*  96 */       return reader; 
/* 100 */     SimpleFeatureType schema = (SimpleFeatureType)reader.getFeatureType();
/* 101 */     if (!canSort(schema, sortBy))
/* 102 */       throw new IllegalArgumentException("The specified reader cannot be sorted, either the sorting properties are not comparable or the attributes are not serializable"); 
/* 107 */     int count = 0;
/* 108 */     File file = null;
/* 109 */     RandomAccessFile raf = null;
/* 110 */     List<SimpleFeature> features = new ArrayList<SimpleFeature>();
/* 111 */     List<FeatureBlockReader> readers = new ArrayList<FeatureBlockReader>();
/* 112 */     boolean cleanFile = true;
/*     */     try {
/* 115 */       while (reader.hasNext()) {
/* 116 */         SimpleFeature f = (SimpleFeature)reader.next();
/* 117 */         features.add(f);
/* 118 */         count++;
/* 120 */         if (count > maxFeatures) {
/* 121 */           Collections.sort(features, comparator);
/* 122 */           if (raf == null) {
/* 123 */             file = File.createTempFile("sorted", ".features");
/* 124 */             file.delete();
/* 125 */             raf = new RandomAccessFile(file, "rw");
/*     */           } 
/* 127 */           FeatureBlockReader fbr = storeToFile(raf, features, schema);
/* 128 */           readers.add(fbr);
/* 129 */           count = 0;
/* 130 */           features.clear();
/*     */         } 
/*     */       } 
/* 135 */       if (raf == null) {
/* 138 */         Collections.sort(features, comparator);
/* 140 */         SimpleFeatureIterator fi = (new ListFeatureCollection(schema, features)).features();
/* 141 */         return (SimpleFeatureReader)new DelegateSimpleFeatureReader(schema, fi);
/*     */       } 
/* 144 */       cleanFile = false;
/* 145 */       return new MergeSortReader(schema, raf, file, readers, comparator);
/*     */     } finally {
/* 149 */       if (cleanFile && raf != null) {
/* 150 */         raf.close();
/* 151 */         file.delete();
/*     */       } 
/* 154 */       reader.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   static FeatureBlockReader storeToFile(RandomAccessFile raf, List<SimpleFeature> features, SimpleFeatureType schema) throws IOException {
/* 167 */     long start = raf.getFilePointer();
/* 170 */     List<AttributeDescriptor> attributes = schema.getAttributeDescriptors();
/* 171 */     for (SimpleFeature sf : features) {
/* 173 */       raf.writeUTF(sf.getID());
/* 175 */       for (AttributeDescriptor ad : attributes) {
/* 176 */         Object value = sf.getAttribute(ad.getLocalName());
/* 177 */         writeAttribute(raf, ad, value);
/*     */       } 
/*     */     } 
/* 181 */     return new FeatureBlockReader(raf, start, features.size(), schema);
/*     */   }
/*     */   
/*     */   static void writeAttribute(RandomAccessFile raf, AttributeDescriptor ad, Object value) throws IOException {
/* 186 */     if (value == null) {
/* 188 */       raf.writeBoolean(true);
/*     */     } else {
/* 197 */       raf.writeBoolean(false);
/* 198 */       Class<?> binding = ad.getType().getBinding();
/* 199 */       if (binding == Boolean.class) {
/* 200 */         raf.writeBoolean(((Boolean)value).booleanValue());
/* 201 */       } else if (binding == Byte.class || binding == byte.class) {
/* 202 */         raf.writeByte(((Byte)value).byteValue());
/* 203 */       } else if (binding == Short.class || binding == short.class) {
/* 204 */         raf.writeShort(((Short)value).shortValue());
/* 205 */       } else if (binding == Integer.class || binding == int.class) {
/* 206 */         raf.writeInt(((Integer)value).intValue());
/* 207 */       } else if (binding == Long.class || binding == long.class) {
/* 208 */         raf.writeLong(((Long)value).longValue());
/* 209 */       } else if (binding == Float.class || binding == float.class) {
/* 210 */         raf.writeFloat(((Float)value).floatValue());
/* 211 */       } else if (binding == Double.class || binding == double.class) {
/* 212 */         raf.writeDouble(((Double)value).doubleValue());
/* 213 */       } else if (binding == String.class) {
/* 214 */         raf.writeUTF((String)value);
/* 215 */       } else if (binding == Date.class || binding == Time.class || binding == Timestamp.class || binding == Date.class) {
/* 217 */         raf.writeLong(((Date)value).getTime());
/* 218 */       } else if (Geometry.class.isAssignableFrom(binding)) {
/* 219 */         WKBWriter writer = new WKBWriter();
/* 220 */         byte[] buffer = writer.write((Geometry)value);
/* 221 */         int length = buffer.length;
/* 222 */         raf.writeInt(length);
/* 223 */         raf.write(buffer);
/*     */       } else {
/* 227 */         ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 228 */         ObjectOutputStream oos = new ObjectOutputStream(bos);
/* 229 */         oos.writeObject(value);
/* 230 */         oos.flush();
/* 231 */         byte[] bytes = bos.toByteArray();
/* 232 */         raf.writeInt(bytes.length);
/* 233 */         raf.write(bytes);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static Comparator<SimpleFeature> getComparator(SortBy[] sortBy) {
/* 246 */     if (sortBy == SortBy.UNSORTED || sortBy == null)
/* 247 */       return null; 
/* 251 */     List<Comparator<SimpleFeature>> comparators = new ArrayList<Comparator<SimpleFeature>>();
/* 252 */     for (SortBy sb : sortBy) {
/* 253 */       if (sb == SortBy.NATURAL_ORDER) {
/* 254 */         comparators.add(new FidComparator(true));
/* 255 */       } else if (sb == SortBy.REVERSE_ORDER) {
/* 256 */         comparators.add(new FidComparator(false));
/*     */       } else {
/* 258 */         String name = sb.getPropertyName().getPropertyName();
/* 259 */         boolean ascending = (sb.getSortOrder() == SortOrder.ASCENDING);
/* 260 */         comparators.add(new PropertyComparator(name, ascending));
/*     */       } 
/*     */     } 
/* 265 */     if (comparators.size() == 1)
/* 266 */       return comparators.get(0); 
/* 268 */     return new CompositeComparator(comparators);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\sort\MergeSortDumper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */