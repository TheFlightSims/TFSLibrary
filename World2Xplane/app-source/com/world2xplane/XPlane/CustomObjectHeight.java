/*     */ package com.world2xplane.XPlane;
/*     */ 
/*     */ import com.world2xplane.OSM.Node;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.Rules.ObjectDefinitionStore;
/*     */ import com.world2xplane.Rules.Rule;
/*     */ import java.io.File;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class CustomObjectHeight {
/*     */   private final Rule rule;
/*     */   
/*     */   private final DSFObject osmObject;
/*     */   
/*     */   private final Node node;
/*     */   
/*  47 */   private static Logger log = LoggerFactory.getLogger(CustomObjectHeight.class);
/*     */   
/*  48 */   private static final NumberFormat format = NumberFormat.getInstance(Locale.UK);
/*     */   
/*     */   public CustomObjectHeight(Rule rule, DSFObject osmObject, Node node) {
/*  54 */     this.rule = rule;
/*  55 */     this.osmObject = osmObject;
/*  56 */     this.node = node;
/*     */   }
/*     */   
/*     */   public Integer generateObject() {
/*  63 */     File file = new File(GeneratorStore.getGeneratorStore().getOutputFolder(), GeneratorStore.getGeneratorStore().getObjectDefinition(this.rule.getDefinitionNumber(null).intValue()).getPath());
/*  64 */     if (file.exists() && 
/*  65 */       this.node != null && this.node.getMinHeight() > 0.0F)
/*     */       try {
/*  67 */         return rebuildObject(file);
/*  68 */       } catch (Exception e) {
/*  69 */         e.printStackTrace();
/*  70 */         return this.rule.getDefinitionNumber(this.osmObject);
/*     */       }  
/*  74 */     return this.rule.getDefinitionNumber(this.osmObject);
/*     */   }
/*     */   
/*     */   private Integer rebuildObject(File inFile) throws Exception {
/*  80 */     String outName = GeneratorStore.getGeneratorStore().getObjectDefinition(this.rule.getDefinitionNumber(null).intValue()).getPath();
/*  81 */     outName.replaceAll(".obj", "");
/*  82 */     outName = outName + "_" + format.format(this.node.getMinHeight()) + ".obj";
/*  84 */     File newFile = new File(GeneratorStore.getGeneratorStore().getOutputFolder(), outName);
/*  85 */     if (newFile.exists())
/*  86 */       return Integer.valueOf(GeneratorStore.getGeneratorStore().getObjectDefinitionStore().pushObject(ObjectDefinitionStore.ObjectType.OBJECT, outName, null)); 
/*  90 */     String model = FileUtils.readFileToString(inFile);
/*  94 */     StringBuilder output = new StringBuilder();
/*  95 */     for (String line : model.split("\n")) {
/*  96 */       if (line.startsWith("VT ")) {
/*  97 */         String[] coords = line.split("\\s+");
/*  98 */         double x = format.parse(coords[1]).doubleValue();
/*  99 */         double y = format.parse(coords[2]).doubleValue();
/* 100 */         double z = format.parse(coords[3]).doubleValue();
/* 101 */         double nx = format.parse(coords[4]).doubleValue();
/* 102 */         double ny = format.parse(coords[5]).doubleValue();
/* 103 */         double nz = format.parse(coords[6]).doubleValue();
/* 104 */         double u = format.parse(coords[7]).doubleValue();
/* 105 */         double v = format.parse(coords[8]).doubleValue();
/* 107 */         y += this.node.getMinHeight();
/* 109 */         output.append(String.format("VT %s %s %s %s %s %s %s %s", new Object[] { format.format(x), format.format(y), format.format(z), format.format(nx), format.format(ny), format.format(nz), format.format(u), format.format(v) }));
/*     */       } else {
/* 122 */         output.append(line);
/*     */       } 
/* 124 */       output.append("\n");
/*     */     } 
/* 127 */     FileUtils.writeStringToFile(newFile, output.toString());
/* 129 */     return Integer.valueOf(GeneratorStore.getGeneratorStore().getObjectDefinitionStore().pushObject(ObjectDefinitionStore.ObjectType.OBJECT, outName, null));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\CustomObjectHeight.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */