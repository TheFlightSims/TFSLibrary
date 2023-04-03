/*     */ package org.openstreetmap.osmosis.osmbinary.test;
/*     */ 
/*     */ import com.google.protobuf.ByteString;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.openstreetmap.osmosis.osmbinary.Osmformat;
/*     */ import org.openstreetmap.osmosis.osmbinary.file.BlockOutputStream;
/*     */ import org.openstreetmap.osmosis.osmbinary.file.FileBlock;
/*     */ 
/*     */ public class BuildTestFile {
/*     */   BlockOutputStream output;
/*     */   
/*     */   public static final long BILLION = 1000000000L;
/*     */   
/*     */   Osmformat.StringTable makeStringTable(String prefix) {
/*  49 */     return Osmformat.StringTable.newBuilder().addS(ByteString.copyFromUtf8("")).addS(ByteString.copyFromUtf8(prefix + "Offset1")).addS(ByteString.copyFromUtf8(prefix + "Offset2")).addS(ByteString.copyFromUtf8(prefix + "Offset3")).addS(ByteString.copyFromUtf8(prefix + "Offset4")).addS(ByteString.copyFromUtf8(prefix + "Offset5")).addS(ByteString.copyFromUtf8(prefix + "Offset6")).addS(ByteString.copyFromUtf8(prefix + "Offset7")).addS(ByteString.copyFromUtf8(prefix + "Offset8")).build();
/*     */   }
/*     */   
/*     */   void makeSimpleFileBlock1() throws IOException {
/*  63 */     Osmformat.PrimitiveBlock.Builder b1 = Osmformat.PrimitiveBlock.newBuilder();
/*  64 */     b1.setStringtable(makeStringTable("B1"));
/*  66 */     b1.addPrimitivegroup(Osmformat.PrimitiveGroup.newBuilder().addNodes(Osmformat.Node.newBuilder().setId(101L).setLat(130000000L).setLon(-140000000L).addKeys(1).addVals(2)).addNodes(Osmformat.Node.newBuilder().setId(101L).setLat(12345678L).setLon(-23456789L)));
/*  74 */     b1.addPrimitivegroup(Osmformat.PrimitiveGroup.newBuilder().addWays(Osmformat.Way.newBuilder().setId(201L).addRefs(101L).addRefs(1L).addRefs(-1L).addRefs(10L).addRefs(-20L).addKeys(2).addVals(1).addKeys(3).addVals(4)).addWays(Osmformat.Way.newBuilder().setId(-301L).addRefs(211L).addRefs(1L).addRefs(-1L).addRefs(10L).addRefs(-300L).addKeys(4).addVals(3).addKeys(5).addVals(6)).addWays(Osmformat.Way.newBuilder().setId(401L).addRefs(211L).addRefs(1L)).addWays(Osmformat.Way.newBuilder().setId(501L)));
/*  90 */     b1.addPrimitivegroup(Osmformat.PrimitiveGroup.newBuilder().addRelations(Osmformat.Relation.newBuilder().setId(601L).addTypes(Osmformat.Relation.MemberType.NODE).addMemids(50L).addRolesSid(2).addTypes(Osmformat.Relation.MemberType.NODE).addMemids(3L).addRolesSid(3).addTypes(Osmformat.Relation.MemberType.WAY).addMemids(3L).addRolesSid(4).addTypes(Osmformat.Relation.MemberType.RELATION).addMemids(3L).addRolesSid(5)).addRelations(Osmformat.Relation.newBuilder().setId(701L).addTypes(Osmformat.Relation.MemberType.RELATION).addMemids(60L).addRolesSid(6).addTypes(Osmformat.Relation.MemberType.RELATION).addMemids(5L).addRolesSid(7).addKeys(1).addVals(2)));
/* 104 */     b1.addPrimitivegroup(Osmformat.PrimitiveGroup.newBuilder().setDense(Osmformat.DenseNodes.newBuilder().addId(1001L).addId(110L).addId(-2000L).addId(8889L).addLat(120000000L).addLat(1500000L).addLat(-120000000L).addLat(-120000000L).addLon(-120000000L).addLon(2500000L).addLon(130000000L).addLon(20000000L).addKeysVals(1).addKeysVals(2).addKeysVals(0).addKeysVals(0).addKeysVals(2).addKeysVals(3).addKeysVals(4).addKeysVals(5).addKeysVals(0).addKeysVals(3).addKeysVals(3).addKeysVals(0)));
/* 116 */     this.output.write(FileBlock.newInstance("OSMData", b1.build().toByteString(), null));
/* 118 */     Osmformat.PrimitiveBlock.Builder b2 = Osmformat.PrimitiveBlock.newBuilder();
/* 119 */     b2.setLatOffset(10109208300L).setLonOffset(20901802700L).setGranularity(1200);
/* 122 */     b2.setStringtable(makeStringTable("B2"));
/* 125 */     b2.addPrimitivegroup(Osmformat.PrimitiveGroup.newBuilder().addNodes(Osmformat.Node.newBuilder().setId(100000L).setLat(0L).setLon(0L)).addNodes(Osmformat.Node.newBuilder().setId(100001L).setLat(1000L).setLon(2000L)).addNodes(Osmformat.Node.newBuilder().setId(100002L).setLat(1001L).setLon(2001L)).addNodes(Osmformat.Node.newBuilder().setId(100003L).setLat(1002L).setLon(2002L)).addNodes(Osmformat.Node.newBuilder().setId(100004L).setLat(1003L).setLon(2003L)).addNodes(Osmformat.Node.newBuilder().setId(100005L).setLat(1004L).setLon(2004L)));
/* 135 */     this.output.write(FileBlock.newInstance("OSMData", b2.build().toByteString(), null));
/*     */   }
/*     */   
/*     */   BuildTestFile(String name, String compress) throws IOException {
/* 140 */     this.output = new BlockOutputStream(new FileOutputStream(name));
/* 141 */     this.output.setCompress(compress);
/* 142 */     Osmformat.HeaderBlock.Builder b = Osmformat.HeaderBlock.newBuilder();
/* 143 */     b.addRequiredFeatures("OsmSchema-V0.6").addRequiredFeatures("DenseNodes").setSource("QuickBrownFox");
/* 144 */     this.output.write(FileBlock.newInstance("OSMHeader", b.build().toByteString(), null));
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 150 */       BuildTestFile out1a = new BuildTestFile("TestFile1-deflate.osm.pbf", "deflate");
/* 151 */       out1a.makeSimpleFileBlock1();
/* 152 */       out1a.output.close();
/* 154 */       BuildTestFile out1b = new BuildTestFile("TestFile1-none.osm.pbf", "none");
/* 155 */       out1b.makeSimpleFileBlock1();
/* 156 */       out1b.output.close();
/* 158 */       BuildTestFile out2 = new BuildTestFile("TestFile2-uncom.osm.pbf", "deflate");
/* 159 */       out2.makeGranFileBlock1();
/* 160 */       out2.output.close();
/* 163 */     } catch (IOException e) {
/* 165 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   void makeGranFileBlock1() throws IOException {
/* 171 */     Osmformat.PrimitiveBlock.Builder b1 = Osmformat.PrimitiveBlock.newBuilder();
/* 172 */     b1.setLatOffset(10109208300L).setLonOffset(20901802700L).setGranularity(1200).setDateGranularity(2500);
/* 176 */     b1.setStringtable(makeStringTable("C1"));
/* 178 */     b1.addPrimitivegroup(Osmformat.PrimitiveGroup.newBuilder().addNodes(Osmformat.Node.newBuilder().setId(100001L).setLat(1000L).setLon(2000L).setInfo(Osmformat.Info.newBuilder().setTimestamp(1001L).setChangeset(-12L).setUid(21).setUserSid(6).build()).build()).addNodes(Osmformat.Node.newBuilder().setId(100002L).setLat(1001L).setLon(2001L).setInfo(Osmformat.Info.newBuilder().setVersion(102).setTimestamp(1002L).setChangeset(12L).setUid(-21).setUserSid(5).build()).build()).addNodes(Osmformat.Node.newBuilder().setId(100003L).setLat(1003L).setLon(2003L).setInfo(Osmformat.Info.newBuilder().setVersion(103).setUserSid(4).build()).build()));
/* 214 */     Osmformat.PrimitiveBlock.Builder b2 = Osmformat.PrimitiveBlock.newBuilder();
/* 215 */     b2.setLatOffset(12000000303L).setLonOffset(22000000404L).setGranularity(1401).setDateGranularity(3003);
/* 219 */     b2.setStringtable(makeStringTable("C2"));
/* 220 */     b2.addPrimitivegroup(Osmformat.PrimitiveGroup.newBuilder().addNodes(Osmformat.Node.newBuilder().setId(100001L).addKeys(1).addVals(2).addKeys(1).addVals(3).addKeys(3).addVals(4).setLat(1000L).setLon(2000L).build()).addNodes(Osmformat.Node.newBuilder().setId(100002L).setLat(1001L).setLon(2001L).build()).addNodes(Osmformat.Node.newBuilder().setId(100003L).setLat(1003L).setLon(2003L).addKeys(5).addVals(6).build()));
/* 239 */     this.output.write(FileBlock.newInstance("OSMData", b1.build().toByteString(), null));
/* 240 */     this.output.write(FileBlock.newInstance("OSMData", b2.build().toByteString(), null));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\test\BuildTestFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */