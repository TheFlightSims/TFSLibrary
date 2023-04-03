/*     */ package com.world2xplane.XPlane;
/*     */ 
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.Rules.ObjectDefinitionStore;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class TextureConverter {
/*  42 */   private static Logger log = LoggerFactory.getLogger(TextureConverter.class);
/*     */   
/*     */   public boolean convertTexturesToDDS() throws IOException {
/*  45 */     String ddsPath = DDSTool.getDDSToolPath();
/*  46 */     if (!(new File(ddsPath)).exists()) {
/*  47 */       log.error("No DDSTool available, textures will not be compressed.");
/*  48 */       return false;
/*     */     } 
/*  51 */     File outputPath = new File(GeneratorStore.getGeneratorStore().getOutputFolder());
/*  53 */     Set<String> images = new HashSet<>();
/*  54 */     List<ObjectDefinitionStore.ObjectDefinition> facadePaths = GeneratorStore.getGeneratorStore().getObjectDefinitionStore().getPolygonDefinitions();
/*  55 */     for (ObjectDefinitionStore.ObjectDefinition item : facadePaths) {
/*  56 */       File file = new File(outputPath, item.getPath());
/*  57 */       if (file.exists()) {
/*  63 */         String contents = FileUtils.readFileToString(file);
/*  64 */         String[] lines = contents.split("\n");
/*  65 */         for (String line : lines) {
/*  66 */           line = line.replaceAll("\t", " ");
/*  67 */           if (line.startsWith("TEXTURE ")) {
/*  68 */             String textureName = line.substring(line.indexOf("TEXTURE ") + 7).trim();
/*  69 */             if (textureName.toLowerCase().endsWith(".png")) {
/*  70 */               File texturePath = new File(file.getParent(), textureName);
/*  71 */               images.add(texturePath.toString());
/*     */             } 
/*     */           } 
/*  74 */           if (line.startsWith("TEXTURE_LIT ")) {
/*  75 */             String textureName = line.substring(line.indexOf("TEXTURE_LIT ") + 11).trim();
/*  76 */             if (textureName.toLowerCase().endsWith(".png")) {
/*  77 */               File texturePath = new File(file.getParent(), textureName);
/*  78 */               images.add(texturePath.toString());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  85 */     List<ObjectDefinitionStore.ObjectDefinition> objectPaths = GeneratorStore.getGeneratorStore().getObjectDefinitionStore().getObjectDefinitions();
/*  86 */     for (ObjectDefinitionStore.ObjectDefinition item : objectPaths) {
/*  87 */       File file = new File(outputPath, item.getPath());
/*  88 */       if (file.exists()) {
/*  93 */         String contents = FileUtils.readFileToString(file);
/*  94 */         String[] lines = contents.split("\n");
/*  95 */         for (String line : lines) {
/*  96 */           line = line.replaceAll("\t", " ");
/*  97 */           if (line.startsWith("TEXTURE ")) {
/*  98 */             String textureName = line.substring(line.indexOf("TEXTURE ") + 7).trim();
/*  99 */             if (textureName.toLowerCase().endsWith(".png")) {
/* 100 */               File texturePath = new File(file.getParent(), textureName);
/* 101 */               images.add(texturePath.toString());
/*     */             } 
/*     */           } 
/* 104 */           if (line.startsWith("TEXTURE_LIT ")) {
/* 105 */             String textureName = line.substring(line.indexOf("TEXTURE_LIT ") + 11).trim();
/* 106 */             if (textureName.toLowerCase().endsWith(".png")) {
/* 107 */               File texturePath = new File(file.getParent(), textureName);
/* 108 */               images.add(texturePath.toString());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 115 */     List<File> paths = (new FileSearch()).getFiles(outputPath, ".png");
/* 116 */     for (File item : paths)
/* 117 */       images.add(item.getAbsolutePath()); 
/* 120 */     for (String item : images) {
/* 121 */       if ((new File(item.replaceAll(".png", ".dds"))).exists())
/*     */         continue; 
/* 124 */       log.info("Converting " + item + " to DDS");
/* 125 */       Runtime runtime = Runtime.getRuntime();
/* 126 */       Process p = runtime.exec(new String[] { ddsPath, "--png2dxt", "arg1", "arg2", item, item.replaceAll(".png", ".dds") });
/*     */       try {
/* 129 */         p.waitFor();
/* 130 */       } catch (InterruptedException e) {
/* 131 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 135 */     for (String item : images) {
/* 136 */       item = item.replaceAll("/output/", "/resources/");
/* 137 */       if (!(new File(item)).exists())
/*     */         continue; 
/* 140 */       if ((new File(item.replaceAll(".png", ".dds"))).exists())
/*     */         continue; 
/* 143 */       log.info("Converting " + item + " to DDS");
/* 144 */       Runtime runtime = Runtime.getRuntime();
/* 145 */       Process p = runtime.exec(new String[] { ddsPath, "--png2dxt", "arg1", "arg2", item, item.replaceAll(".png", ".dds") });
/*     */       try {
/* 148 */         p.waitFor();
/* 149 */       } catch (InterruptedException e) {
/* 150 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 154 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\TextureConverter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */