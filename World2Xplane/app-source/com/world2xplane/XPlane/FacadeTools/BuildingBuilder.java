/*     */ package com.world2xplane.XPlane.FacadeTools;
/*     */ 
/*     */ import com.world2xplane.OSM.NamedColour;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.ImageIcon;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public abstract class BuildingBuilder {
/*  47 */   private static Logger log = LoggerFactory.getLogger(BuildingBuilder.class);
/*     */   
/*     */   private final Long wayId;
/*     */   
/*     */   private final Long relationId;
/*     */   
/*     */   public int getRandomNumber(int min, int max) {
/*  53 */     if (min == max)
/*  54 */       return max; 
/*  56 */     Random randomGenerator = new Random();
/*  57 */     return randomGenerator.nextInt(max - min) + min;
/*     */   }
/*     */   
/*  62 */   protected final String[] ROOFS = new String[] { "roof/grey-tiled.jpg", "roof/pink-tiled.jpg", "roof/red-tiled.png" };
/*     */   
/*  69 */   protected final String[] WINDOWS = new String[] { "window/window1.jpg", "window/window2.jpg", "window/window3.jpg", "window/window4.jpg" };
/*     */   
/*     */   protected final String wallColour;
/*     */   
/*     */   protected final String roofColour;
/*     */   
/*     */   protected Image getWindowImage() {
/*  77 */     Image image = (new ImageIcon("./resources/textures/" + this.WINDOWS[getRandomNumber(0, this.WINDOWS.length)])).getImage();
/*  80 */     return image;
/*     */   }
/*     */   
/*     */   public BuildingBuilder(String wallColour, String roofColour, Long wayId, Long relationId) {
/*  89 */     this.wallColour = wallColour;
/*  90 */     this.roofColour = roofColour;
/*  91 */     this.wayId = wayId;
/*  92 */     this.relationId = relationId;
/*     */   }
/*     */   
/*     */   public abstract void generateFacade(GeneratorStore paramGeneratorStore, String paramString) throws IOException;
/*     */   
/*     */   protected TexturePaint getTexturePaint(String texture) {
/*  98 */     Image img = (new ImageIcon("./resources/textures/" + texture)).getImage();
/*  99 */     BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), 1);
/* 102 */     Graphics2D g2 = bImage.createGraphics();
/* 103 */     g2.drawImage(img, (AffineTransform)null, (ImageObserver)null);
/* 106 */     Rectangle2D tr = new Rectangle2D.Double(0.0D, 0.0D, bImage.getWidth(), bImage.getHeight());
/* 110 */     return new TexturePaint(bImage, tr);
/*     */   }
/*     */   
/*     */   protected ArrayList<Color> getColors(String colorCode) {
/* 136 */     ArrayList<Color> colors = new ArrayList<>();
/* 137 */     List<String> colours = new ArrayList<>();
/* 138 */     if (colorCode == null)
/* 139 */       log.error("No colour passed in"); 
/* 141 */     if (colorCode.contains(";")) {
/* 142 */       for (String color : colorCode.split(";"))
/* 143 */         colors.add(getColor(color)); 
/* 145 */     } else if (colorCode.contains(",")) {
/* 146 */       for (String color : colorCode.split(","))
/* 147 */         colors.add(getColor(color)); 
/*     */     } else {
/* 150 */       colors.add(getColor(colorCode));
/*     */     } 
/* 152 */     return colors;
/*     */   }
/*     */   
/*     */   protected void applyWallPaint(Graphics2D graphicsContext, TexturePaint wallPaint, int width, float buildingHeight, float buildingTop) {
/* 156 */     if (this.wallColour != null) {
/* 157 */       ArrayList<Color> wallColor = getColors(this.wallColour);
/* 158 */       if (wallColor != null) {
/* 159 */         int count = wallColor.size() * 4;
/* 160 */         double rowHeight = (buildingHeight / count);
/* 161 */         int s = 0;
/*     */         double x;
/* 162 */         for (x = buildingTop; x <= (buildingTop + buildingHeight); x += rowHeight) {
/* 163 */           Color paint = new Color(((Color)wallColor.get(s)).getRed(), ((Color)wallColor.get(s)).getGreen(), ((Color)wallColor.get(s)).getBlue(), 170);
/* 164 */           graphicsContext.setPaint(paint);
/* 165 */           graphicsContext.fillRect(0, (int)x, width, (int)rowHeight);
/* 166 */           s++;
/* 167 */           if (s >= wallColor.size())
/* 168 */             s = 0; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Color getColor(String colorCode) {
/* 177 */     if (colorCode == null)
/* 178 */       return Color.white; 
/* 180 */     if (colorCode.toLowerCase().equals("red"))
/* 181 */       return getColor("#BB4B35"); 
/* 183 */     if (colorCode.toLowerCase().equals("blue"))
/* 184 */       return getColor("#5664AD"); 
/* 186 */     if (colorCode.toLowerCase().equals("green"))
/* 187 */       return getColor("#3B4B32"); 
/* 189 */     if (colorCode.toLowerCase().equals("orange"))
/* 190 */       return getColor("#C47832"); 
/* 192 */     if (colorCode.toLowerCase().equals("purple"))
/* 193 */       return getColor("#5967AC"); 
/* 195 */     if (colorCode.toLowerCase().equals("white"))
/* 196 */       return getColor("#C6C9C9"); 
/* 199 */     if (colorCode.equals("glass"))
/* 200 */       return Color.blue; 
/* 203 */     colorCode = colorCode.toLowerCase().trim();
/* 204 */     if (colorCode.equals("light blue") || colorCode.equals("light_blue"))
/* 205 */       colorCode = "#add8e6"; 
/* 207 */     if (colorCode.equals("light yellow") || colorCode.equals("light_yellow"))
/* 208 */       colorCode = "#ffffe0"; 
/* 210 */     if (colorCode.equals("light green") || colorCode.equals("light_green"))
/* 211 */       colorCode = "#90ee90"; 
/* 213 */     if (colorCode.equals("light blue") || colorCode.equals("light_blue"))
/* 214 */       colorCode = "#add8e6"; 
/* 216 */     if (colorCode.equals("light brown") || colorCode.equals("light_brown"))
/* 217 */       colorCode = "#d2691e"; 
/* 219 */     if (colorCode.equals("dark brown") || colorCode.equals("dark_brown"))
/* 220 */       colorCode = "#d2691e"; 
/* 222 */     if (colorCode.equals("dark_grey") || colorCode.equals("dark grey"))
/* 223 */       colorCode = "#a9a9a9"; 
/* 225 */     if (colorCode.equals("dark_green") || colorCode.equals("dark green"))
/* 226 */       colorCode = "#006400"; 
/* 228 */     if (colorCode.equals("dark_gray") || colorCode.equals("dark gray"))
/* 229 */       colorCode = "#a9a9a9"; 
/* 231 */     if (colorCode.equals("peach"))
/* 232 */       colorCode = "#ffdab9"; 
/* 234 */     if (colorCode.equals("mocassin"))
/* 235 */       colorCode = "#deb887"; 
/* 237 */     if (colorCode.equals("mustard"))
/* 238 */       colorCode = "#556b2f"; 
/* 240 */     if (colorCode.equals("iceberg"))
/* 241 */       colorCode = "#e0ffff"; 
/* 243 */     if (colorCode.equals("creme") || colorCode.equals("cream"))
/* 244 */       colorCode = "#faebd7"; 
/* 246 */     if (colorCode.equals("vanilla"))
/* 247 */       colorCode = "#faebd7"; 
/* 249 */     if (colorCode.equals("red carmine") || colorCode.equals("carmine"))
/* 250 */       colorCode = "#8b0000"; 
/* 252 */     if (colorCode.equals("maron"))
/* 253 */       colorCode = "#800000"; 
/* 257 */     Color color = null;
/* 258 */     if (!colorCode.startsWith("#")) {
/* 259 */       color = NamedColour.getColourByName(colorCode.trim());
/* 260 */       if (color == null) {
/* 261 */         color = getColorByJava(colorCode);
/* 262 */         if (color != null)
/* 263 */           return color; 
/*     */       } else {
/* 266 */         return color;
/*     */       } 
/*     */     } 
/* 270 */     if (color == null)
/*     */       try {
/* 272 */         color = Color.decode(colorCode);
/* 273 */         return color;
/* 274 */       } catch (Exception e) {
/* 277 */         if (colorCode.indexOf("#") >= 0) {
/*     */           try {
/* 279 */             int intValue = Integer.parseInt(colorCode.substring(1), 16);
/* 280 */             return new Color(intValue);
/* 281 */           } catch (Exception ex) {
/* 282 */             log.info("Invalid colour " + colorCode + " for " + ((this.wayId != null) ? (" way http://openstreetmap.org/way/" + this.wayId) : (" http://openstreetmap.org/relation/" + this.relationId)));
/*     */           } 
/*     */         } else {
/*     */           try {
/* 287 */             Field field = Color.class.getField(colorCode);
/* 288 */             return (Color)field.get(null);
/* 289 */           } catch (Exception ex) {
/* 290 */             log.info("Invalid colour " + colorCode + " for " + ((this.wayId != null) ? (" way http://openstreetmap.org/way/" + this.wayId) : (" http://openstreetmap.org/relation/" + this.relationId)));
/*     */           } 
/*     */         } 
/*     */       }  
/* 298 */     if (!colorCode.startsWith("#")) {
/* 299 */       colorCode = "#" + colorCode;
/* 300 */       return getColor(colorCode);
/*     */     } 
/* 304 */     log.info("Invalid colour " + colorCode + " for " + ((this.wayId != null) ? (" way http://openstreetmap.org/way/" + this.wayId) : (" http://openstreetmap.org/relation/" + this.relationId)));
/* 306 */     return Color.gray;
/*     */   }
/*     */   
/*     */   public static Color getColorByJava(String colorName) {
/*     */     try {
/* 312 */       Field field = Class.forName("java.awt.Color").getField(colorName);
/* 313 */       return (Color)field.get(null);
/* 314 */     } catch (Exception e) {
/* 315 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void saveImage(GeneratorStore config, String name, BufferedImage bImg) {
/*     */     try {
/* 321 */       Image img = bImg.getScaledInstance(64, 256, 4);
/* 322 */       BufferedImage bsi = new BufferedImage(img.getWidth(null), img.getHeight(null), 1);
/* 323 */       bsi.getGraphics().drawImage(img, 0, 0, null);
/* 324 */       if (ImageIO.write(bsi, "png", new File(config.getOutputFolder(), "facades" + File.separator + name + ".png")));
/* 327 */     } catch (IOException e) {
/* 329 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\FacadeTools\BuildingBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */