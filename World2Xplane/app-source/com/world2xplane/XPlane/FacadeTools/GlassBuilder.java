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
/*     */ public class GlassBuilder extends BuildingBuilder {
/*  43 */   private final String[] TEXTURES = new String[] { "glass/glass1.jpg" };
/*     */   
/*     */   public GlassBuilder(String wallColour, String roofColour, Long wayId, Long relationId) {
/*  50 */     super(wallColour, roofColour, wayId, relationId);
/*     */   }
/*     */   
/*     */   public void generateFacade(GeneratorStore config, String name) throws IOException {
/*  55 */     BufferedImage bImg = new BufferedImage(256, 1024, 1);
/*  56 */     Graphics2D graphicsContext = bImg.createGraphics();
/*  58 */     TexturePaint wallPaint = getTexturePaint(this.TEXTURES[getRandomNumber(0, this.TEXTURES.length)]);
/*  59 */     TexturePaint roofPaint = getTexturePaint(this.ROOFS[getRandomNumber(0, this.ROOFS.length)]);
/*  60 */     float width = 256.0F;
/*  61 */     float height = 1024.0F;
/*  63 */     double metresPer128 = 0.5D;
/*  64 */     double heightMetres = (height / 128.0F) * metresPer128;
/*  66 */     float buildingWidth = 256.0F;
/*  67 */     float buildingHeight = 640.0F;
/*  68 */     float buildingTop = 512.0F;
/*  69 */     float buildingBottom = height;
/*  70 */     buildingHeight = height - buildingTop;
/*  72 */     graphicsContext.setPaint(roofPaint);
/*  73 */     graphicsContext.fillRect(0, 0, 512, (int)buildingHeight / 2);
/*  75 */     if (roofPaint != null) {
/*  76 */       Color roofColor = getColor(this.roofColour);
/*  77 */       if (roofColor != null) {
/*  78 */         Color paint = new Color(roofColor.getRed(), roofColor.getGreen(), roofColor.getBlue(), 170);
/*  79 */         graphicsContext.setPaint(paint);
/*  80 */         graphicsContext.fillRect(0, 0, 512, (int)buildingHeight / 2);
/*     */       } 
/*     */     } 
/*  83 */     graphicsContext.setPaint(wallPaint);
/*  85 */     graphicsContext.fillRect(0, (int)buildingTop, (int)width, (int)buildingHeight);
/*  87 */     applyWallPaint(graphicsContext, wallPaint, (int)width, buildingHeight, buildingTop);
/*  91 */     graphicsContext.setPaint(Color.black);
/*  92 */     graphicsContext.fillRect(0, (int)buildingTop, 256, 18);
/*  93 */     graphicsContext.setPaint(Color.darkGray);
/*  94 */     graphicsContext.fillRect(0, (int)buildingTop + 256 - 18, 256, 18);
/*  99 */     float windowWidth = 150.0F;
/* 100 */     float windowHeight = 150.0F;
/* 101 */     float centreX = buildingWidth / 2.0F;
/* 103 */     Image image = getWindowImage();
/* 106 */     graphicsContext.drawImage(image, (int)(centreX - windowWidth / 2.0F), (int)(buildingTop + windowHeight / 2.0F - 40.0F), (ImageObserver)null);
/* 109 */     DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/* 111 */     NumberFormat numberFormat = new DecimalFormat("#####.###########", formatSymbols);
/* 116 */     StringBuilder facadeText = new StringBuilder();
/* 117 */     facadeText.append("A\n800\n\nFACADE\n\nTEXTURE " + name + ".png\n" + "\n" + "RING 1\n" + "\n" + "TWO_SIDED 0\n" + "\n" + "NO_BLEND 0.500000\n" + "\n" + "\n" + "LOD 0.000000 13000.000000\n" + "\n" + "ROOF 0.0 0.75\n" + "ROOF 1.0 0.75\n" + "ROOF 1.0 1.0\n" + "ROOF 0.0 1.0\n" + "\n" + "WALL 0.000000 10000.000000\n" + "\n" + String.format("SCALE %s %s", new Object[] { numberFormat.format((width / 128.0F) / metresPer128), numberFormat.format((height / 128.0F) / metresPer128) }) + "\n" + "ROOF_SLOPE 0.000000\n");
/* 146 */     float y = 1.0F - buildingTop / height;
/* 147 */     float segmentSize = 16.0F / height;
/* 150 */     String bottom = String.format("BOTTOM %s %s\n", new Object[] { numberFormat.format((y - segmentSize)), numberFormat.format(y) });
/* 151 */     y -= segmentSize;
/* 152 */     String padding = String.format("MIDDLE %s %s\n", new Object[] { numberFormat.format((y - segmentSize)), numberFormat.format(y) });
/* 153 */     y -= segmentSize;
/* 154 */     String window = String.format("MIDDLE %s %s\n", new Object[] { numberFormat.format((y - segmentSize * 13.0F)), numberFormat.format(y) });
/* 155 */     y -= segmentSize * 13.0F;
/* 156 */     String top = String.format("TOP %s %s\n", new Object[] { Float.valueOf(y - segmentSize), Float.valueOf(y) });
/* 158 */     facadeText.append(top);
/* 159 */     facadeText.append(window);
/* 160 */     facadeText.append(padding);
/* 161 */     facadeText.append(bottom);
/* 163 */     facadeText.append("CENTER 0.0 1.0\n");
/* 166 */     saveImage(config, name, bImg);
/* 167 */     FileUtils.writeStringToFile(new File(config.getOutputFolder(), "facades" + File.separator + name + ".fac"), facadeText.toString());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\FacadeTools\GlassBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */