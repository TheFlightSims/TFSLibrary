/*     */ package com.world2xplane.XPlane.FacadeTools;
/*     */ 
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ public class MetalBuilder extends BuildingBuilder {
/*  43 */   private final String[] TEXTURES = new String[] { "metal/metal1.jpg", "metal/metal2.jpg" };
/*     */   
/*     */   public MetalBuilder(String wallColour, String roofColour, Long wayId, Long relationId) {
/*  52 */     super(wallColour, roofColour, wayId, relationId);
/*     */   }
/*     */   
/*     */   public void generateFacade(GeneratorStore config, String name) throws IOException {
/*  58 */     BufferedImage bImg = new BufferedImage(256, 1024, 1);
/*  59 */     Graphics2D graphicsContext = bImg.createGraphics();
/*  61 */     TexturePaint wallPaint = getTexturePaint(this.TEXTURES[getRandomNumber(0, this.TEXTURES.length)]);
/*  62 */     TexturePaint roofPaint = getTexturePaint(this.ROOFS[getRandomNumber(0, this.ROOFS.length)]);
/*  63 */     float width = 256.0F;
/*  64 */     float height = 1024.0F;
/*  66 */     double metresPer128 = 0.5D;
/*  67 */     double heightMetres = (height / 128.0F) * metresPer128;
/*  69 */     float buildingWidth = 256.0F;
/*  70 */     float buildingHeight = 640.0F;
/*  71 */     float buildingTop = 512.0F;
/*  72 */     float buildingBottom = height;
/*  73 */     buildingHeight = height - buildingTop;
/*  75 */     graphicsContext.setPaint(roofPaint);
/*  76 */     graphicsContext.fillRect(0, 0, 512, (int)buildingHeight / 2);
/*  78 */     if (roofPaint != null) {
/*  79 */       Color roofColor = getColor(this.roofColour);
/*  80 */       if (roofColor != null) {
/*  81 */         Color paint = new Color(roofColor.getRed(), roofColor.getGreen(), roofColor.getBlue(), 170);
/*  82 */         graphicsContext.setPaint(paint);
/*  83 */         graphicsContext.fillRect(0, 0, 512, (int)buildingHeight / 2);
/*     */       } 
/*     */     } 
/*  86 */     graphicsContext.setPaint(wallPaint);
/*  88 */     graphicsContext.fillRect(0, (int)buildingTop, (int)width, (int)buildingHeight);
/*  90 */     applyWallPaint(graphicsContext, wallPaint, (int)width, buildingHeight, buildingTop);
/*  94 */     graphicsContext.setPaint(Color.black);
/*  95 */     graphicsContext.fillRect(0, (int)buildingTop, 256, 18);
/*  96 */     graphicsContext.setPaint(Color.darkGray);
/*  97 */     graphicsContext.fillRect(0, (int)buildingTop + 256 - 18, 256, 18);
/* 102 */     float windowWidth = 150.0F;
/* 103 */     float windowHeight = 150.0F;
/* 104 */     float centreX = buildingWidth / 2.0F;
/* 106 */     Image image = getWindowImage();
/* 109 */     graphicsContext.drawImage(image, (int)(centreX - windowWidth / 2.0F), (int)(buildingTop + windowHeight / 2.0F - 40.0F), (ImageObserver)null);
/* 112 */     DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/* 114 */     NumberFormat numberFormat = new DecimalFormat("#####.###########", formatSymbols);
/* 119 */     StringBuilder facadeText = new StringBuilder();
/* 120 */     facadeText.append("A\n800\n\nFACADE\n\nTEXTURE " + name + ".png\n" + "\n" + "RING 1\n" + "\n" + "TWO_SIDED 0\n" + "\n" + "NO_BLEND 0.500000\n" + "\n" + "\n" + "LOD 0.000000 13000.000000\n" + "\n" + "ROOF 0.0 0.75\n" + "ROOF 1.0 0.75\n" + "ROOF 1.0 1.0\n" + "ROOF 0.0 1.0\n" + "\n" + "WALL 0.000000 10000.000000\n" + "\n" + String.format("SCALE %s %s", new Object[] { numberFormat.format((width / 128.0F) / metresPer128), numberFormat.format((height / 128.0F) / metresPer128) }) + "\n" + "ROOF_SLOPE 0.000000\n");
/* 149 */     float y = 1.0F - buildingTop / height;
/* 150 */     float segmentSize = 16.0F / height;
/* 153 */     String bottom = String.format("BOTTOM %s %s\n", new Object[] { numberFormat.format((y - segmentSize)), numberFormat.format(y) });
/* 154 */     y -= segmentSize;
/* 155 */     String padding = String.format("MIDDLE %s %s\n", new Object[] { numberFormat.format((y - segmentSize)), numberFormat.format(y) });
/* 156 */     y -= segmentSize;
/* 157 */     String window = String.format("MIDDLE %s %s\n", new Object[] { numberFormat.format((y - segmentSize * 13.0F)), numberFormat.format(y) });
/* 158 */     y -= segmentSize * 13.0F;
/* 159 */     String top = String.format("TOP %s %s\n", new Object[] { Float.valueOf(y - segmentSize), Float.valueOf(y) });
/* 161 */     facadeText.append(top);
/* 162 */     facadeText.append(window);
/* 163 */     facadeText.append(padding);
/* 164 */     facadeText.append(bottom);
/* 166 */     facadeText.append("CENTER 0.0 1.0\n");
/* 169 */     saveImage(config, name, bImg);
/* 170 */     FileUtils.writeStringToFile(new File(config.getOutputFolder(), "facades" + File.separator + name + ".fac"), facadeText.toString());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\FacadeTools\MetalBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */