/*     */ package org.openstreetmap.osmosis.osmbinary;
/*     */ 
/*     */ import com.google.protobuf.InvalidProtocolBufferException;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.openstreetmap.osmosis.osmbinary.file.BlockReaderAdapter;
/*     */ import org.openstreetmap.osmosis.osmbinary.file.FileBlock;
/*     */ import org.openstreetmap.osmosis.osmbinary.file.FileBlockPosition;
/*     */ 
/*     */ public abstract class BinaryParser implements BlockReaderAdapter {
/*     */   protected int granularity;
/*     */   
/*     */   private long lat_offset;
/*     */   
/*     */   private long lon_offset;
/*     */   
/*     */   protected int date_granularity;
/*     */   
/*     */   private String[] strings;
/*     */   
/*     */   protected Date getDate(Osmformat.Info info) {
/*  40 */     if (info.hasTimestamp())
/*  41 */       return new Date(this.date_granularity * info.getTimestamp()); 
/*  43 */     return NODATE;
/*     */   }
/*     */   
/*  45 */   public static final Date NODATE = new Date(-1L);
/*     */   
/*     */   protected String getStringById(int id) {
/*  54 */     return this.strings[id];
/*     */   }
/*     */   
/*     */   public void handleBlock(FileBlock message) {
/*     */     try {
/*  61 */       if (message.getType().equals("OSMHeader")) {
/*  62 */         Osmformat.HeaderBlock headerblock = Osmformat.HeaderBlock.parseFrom(message.getData());
/*  64 */         parse(headerblock);
/*  65 */       } else if (message.getType().equals("OSMData")) {
/*  66 */         Osmformat.PrimitiveBlock primblock = Osmformat.PrimitiveBlock.parseFrom(message.getData());
/*  68 */         parse(primblock);
/*     */       } 
/*  70 */     } catch (InvalidProtocolBufferException e) {
/*  72 */       e.printStackTrace();
/*  73 */       throw new Error("ParseError");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean skipBlock(FileBlockPosition block) {
/*  82 */     if (block.getType().equals("OSMData"))
/*  83 */       return false; 
/*  84 */     if (block.getType().equals("OSMHeader"))
/*  85 */       return false; 
/*  86 */     System.out.println("Skipped block of type: " + block.getType());
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public double parseLat(long degree) {
/*  94 */     return (this.granularity * degree + this.lat_offset) * 1.0E-9D;
/*     */   }
/*     */   
/*     */   public double parseLon(long degree) {
/* 100 */     return (this.granularity * degree + this.lon_offset) * 1.0E-9D;
/*     */   }
/*     */   
/*     */   public void parse(Osmformat.PrimitiveBlock block) {
/* 105 */     Osmformat.StringTable stablemessage = block.getStringtable();
/* 106 */     this.strings = new String[stablemessage.getSCount()];
/* 108 */     for (int i = 0; i < this.strings.length; i++)
/* 109 */       this.strings[i] = stablemessage.getS(i).toStringUtf8(); 
/* 112 */     this.granularity = block.getGranularity();
/* 113 */     this.lat_offset = block.getLatOffset();
/* 114 */     this.lon_offset = block.getLonOffset();
/* 115 */     this.date_granularity = block.getDateGranularity();
/* 117 */     for (Osmformat.PrimitiveGroup groupmessage : block.getPrimitivegroupList()) {
/* 120 */       parseNodes(groupmessage.getNodesList());
/* 121 */       parseWays(groupmessage.getWaysList());
/* 122 */       parseRelations(groupmessage.getRelationsList());
/* 123 */       if (groupmessage.hasDense())
/* 124 */         parseDense(groupmessage.getDense()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void parseRelations(List<Osmformat.Relation> paramList);
/*     */   
/*     */   protected abstract void parseDense(Osmformat.DenseNodes paramDenseNodes);
/*     */   
/*     */   protected abstract void parseNodes(List<Osmformat.Node> paramList);
/*     */   
/*     */   protected abstract void parseWays(List<Osmformat.Way> paramList);
/*     */   
/*     */   protected abstract void parse(Osmformat.HeaderBlock paramHeaderBlock);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\BinaryParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */