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
/*     */ public class BrickBuilder extends BuildingBuilder {
/*  43 */   private final String[] TEXTURES = new String[] { "brick/brick1.jpg", "brick/brick2.jpg" };
/*     */   
/*     */   public BrickBuilder(String wallColour, String roofColour, Long wayId, Long relationId) {
/*  51 */     super(wallColour, roofColour, wayId, relationId);
/*     */   }
/*     */   
/*     */   public void generateFacade(GeneratorStore config, String name) throws IOException {
/*  56 */     BufferedImage bImg = new BufferedImage(256, 1024, 1);
/*  57 */     Graphics2D graphicsContext = bImg.createGraphics();
/*  59 */     TexturePaint wallPaint = getTexturePaint(this.TEXTURES[getRandomNumber(0, this.TEXTURES.length)]);
/*  60 */     TexturePaint roofPaint = getTexturePaint(this.ROOFS[getRandomNumber(0, this.ROOFS.length)]);
/*  61 */     float width = 256.0F;
/*  62 */     float height = 1024.0F;
/*  64 */     double metresPer128 = 0.5D;
/*  65 */     double heightMetres = (height / 128.0F) * metresPer128;
/*  67 */     float buildingWidth = 256.0F;
/*  68 */     float buildingHeight = 640.0F;
/*  69 */     float buildingTop = 512.0F;
/*  70 */     float buildingBottom = height;
/*  71 */     buildingHeight = height - buildingTop;
/*  73 */     graphicsContext.setPaint(roofPaint);
/*  74 */     graphicsContext.fillRect(0, 0, 512, (int)buildingHeight / 2);
/*  76 */     if (roofPaint != null) {
/*  77 */       Color roofColor = getColor(this.roofColour);
/*  78 */       if (roofColor != null) {
/*  79 */         Color paint = new Color(roofColor.getRed(), roofColor.getGreen(), roofColor.getBlue(), 170);
/*  80 */         graphicsContext.setPaint(paint);
/*  81 */         graphicsContext.fillRect(0, 0, 512, (int)buildingHeight / 2);
/*     */       } 
/*     */     } 
/*  84 */     graphicsContext.setPaint(wallPaint);
/*  86 */     graphicsContext.fillRect(0, (int)buildingTop, (int)width, (int)buildingHeight);
/*  88 */     applyWallPaint(graphicsContext, wallPaint, (int)width, buildingHeight, buildingTop);
/*  90 */     graphicsContext.setPaint(Color.black);
/*  91 */     graphicsContext.fillRect(0, (int)buildingTop, 256, 18);
/*  92 */     graphicsContext.setPaint(Color.darkGray);
/*  93 */     graphicsContext.fillRect(0, (int)buildingTop + 256 - 18, 256, 18);
/*  98 */     float windowWidth = 150.0F;
/*  99 */     float windowHeight = 150.0F;
/* 100 */     float centreX = buildingWidth / 2.0F;
/* 103 */     Image image = getWindowImage();
/* 104 */     graphicsContext.drawImage(image, (int)(centreX - windowWidth / 2.0F), (int)(buildingTop + windowHeight / 2.0F - 40.0F), (ImageObserver)null);
/* 106 */     DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
/* 108 */     NumberFormat numberFormat = new DecimalFormat("#####.###########", formatSymbols);
/* 113 */     StringBuilder facadeText = new StringBuilder();
/* 114 */     facadeText.append("A\n800\n\nFACADE\n\nTEXTURE " + name + ".png\n" + "\n" + "RING 1\n" + "\n" + "TWO_SIDED 0\n" + "\n" + "NO_BLEND 0.500000\n" + "\n" + "\n" + "LOD 0.000000 13000.000000\n" + "\n" + "ROOF 0.0 0.75\n" + "ROOF 1.0 0.75\n" + "ROOF 1.0 1.0\n" + "ROOF 0.0 1.0\n" + "\n" + "WALL 0.000000 10000.000000\n" + "\n" + String.format("SCALE %s %s", new Object[] { numberFormat.format((width / 128.0F) / metresPer128), numberFormat.format((height / 128.0F) / metresPer128) }) + "\n" + "ROOF_SLOPE 0.000000\n");
/* 143 */     float y = 1.0F - buildingTop / height;
/* 144 */     float segmentSize = 16.0F / height;
/* 147 */     String bottom = String.format("BOTTOM %s %s\n", new Object[] { numberFormat.format((y - segmentSize)), numberFormat.format(y) });
/* 148 */     y -= segmentSize;
/* 149 */     String padding = String.format("MIDDLE %s %s\n", new Object[] { numberFormat.format((y - segmentSize)), numberFormat.format(y) });
/* 150 */     y -= segmentSize;
/* 151 */     String window = String.format("MIDDLE %s %s\n", new Object[] { numberFormat.format((y - segmentSize * 13.0F)), numberFormat.format(y) });
/* 152 */     y -= segmentSize * 13.0F;
/* 153 */     String top = String.format("TOP %s %s\n", new Object[] { Float.valueOf(y - segmentSize), Float.valueOf(y) });
/* 155 */     facadeText.append(top);
/* 156 */     facadeText.append(window);
/* 157 */     facadeText.append(padding);
/* 158 */     facadeText.append(bottom);
/* 160 */     facadeText.append("CENTER 0.0 1.0\n");
/* 163 */     saveImage(config, name, bImg);
/* 164 */     FileUtils.writeStringToFile(new File(config.getOutputFolder(), "facades" + File.separator + name + ".fac"), facadeText.toString());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\FacadeTools\BrickBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */