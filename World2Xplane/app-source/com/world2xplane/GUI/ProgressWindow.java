/*     */ package com.world2xplane.GUI;
/*     */ 
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.XPlane.DSFTile;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class ProgressWindow extends JPanel {
/*     */   private BufferedImage logo;
/*     */   
/*     */   public static class PGrid {
/*     */     int x;
/*     */     
/*     */     int y;
/*     */     
/*     */     int status;
/*     */     
/*     */     String regions;
/*     */     
/*     */     File file;
/*     */     
/*  53 */     float progress = 0.0F;
/*     */   }
/*     */   
/*  57 */   Set<JLabel> progressIcons = new HashSet<>();
/*     */   
/*  59 */   Integer minX = null;
/*     */   
/*  60 */   Integer maxX = null;
/*     */   
/*  61 */   Integer minY = null;
/*     */   
/*  62 */   Integer maxY = null;
/*     */   
/*  64 */   Map<Point, PGrid> grid = new HashMap<>();
/*     */   
/*     */   BufferedImage worldImage;
/*     */   
/*     */   ImageIcon waiting;
/*     */   
/*  69 */   private String message = null;
/*     */   
/*     */   public ProgressWindow() {
/*     */     try {
/*  73 */       this.logo = ImageIO.read(new File("./resources/icon.png"));
/*  74 */       this.waiting = new ImageIcon("./resources/wait16.gif");
/*  75 */     } catch (IOException e) {
/*  76 */       e.printStackTrace();
/*     */     } 
/*  78 */     this.message = "";
/*     */   }
/*     */   
/*     */   public void drawCenteredString(String s, int w, int h, Graphics g) {
/*  82 */     FontMetrics fm = g.getFontMetrics();
/*  83 */     int x = (w - fm.stringWidth(s)) / 2;
/*  84 */     int y = fm.getAscent() + (h - fm.getAscent() + fm.getDescent()) / 2;
/*  85 */     g.drawString(s, x, y);
/*     */   }
/*     */   
/*     */   private void doDrawing(Graphics g) {
/*  90 */     Graphics2D g2d = (Graphics2D)g;
/*  92 */     g2d.setColor(Color.blue);
/*  94 */     Dimension size = getSize();
/*  95 */     Insets insets = getInsets();
/*  97 */     int width = size.width - insets.left - insets.right;
/*  98 */     int height = size.height - insets.top - insets.bottom;
/* 100 */     g2d.setPaint(new Color(148, 157, 166));
/* 101 */     g2d.fillRect(0, 0, width, height);
/* 103 */     if (this.minX == null) {
/* 104 */       g2d.setColor(Color.white);
/* 105 */       Font font = new Font("Helvetica", 1, 14);
/* 106 */       g2d.setFont(font);
/* 107 */       g2d.drawString("World2XPlane v0.7.4", width / 2 - 90, 20);
/* 108 */       g2d.setColor(Color.red);
/* 109 */       g2d.drawString("(Beta)", width / 2 + 55, 20);
/*     */       try {
/* 112 */         if (this.logo != null)
/* 113 */           g2d.drawImage(this.logo, (BufferedImageOp)null, width / 2 - 128, 50); 
/* 114 */       } catch (Exception e) {
/* 115 */         e.printStackTrace();
/*     */       } 
/* 118 */       if (this.message != null) {
/* 119 */         g2d.setColor(Color.black);
/* 120 */         font = new Font("Helvetica", 0, 14);
/* 121 */         g2d.setFont(font);
/* 122 */         FontMetrics fm = g.getFontMetrics();
/* 123 */         int i = (width - fm.stringWidth(this.message)) / 2;
/* 125 */         g2d.drawString(this.message, i, 300);
/*     */       } 
/*     */       return;
/*     */     } 
/* 131 */     double west = GeomUtils.merc_x(this.minX.intValue());
/* 132 */     double east = GeomUtils.merc_x(this.maxX.intValue());
/* 133 */     double north = GeomUtils.merc_y(this.minY.intValue());
/* 134 */     double south = GeomUtils.merc_y(this.maxY.intValue());
/* 137 */     double sizeX = east - west;
/* 138 */     double sizeY = south - north;
/* 140 */     double xDivision = width / sizeX;
/* 141 */     double yDivision = height / sizeY;
/* 144 */     for (JLabel item : this.progressIcons)
/* 145 */       remove(item); 
/* 147 */     this.progressIcons.clear();
/* 151 */     if (this.worldImage == null) {
/* 152 */       this.worldImage = new BufferedImage(width, height, 1);
/* 153 */       Graphics2D context = this.worldImage.createGraphics();
/* 154 */       context.setPaint(new Color(148, 157, 166));
/* 155 */       context.fillRect(0, 0, width, height);
/*     */       int i;
/* 157 */       for (i = 0; i <= this.maxX.intValue() - this.minX.intValue(); i++) {
/*     */         int y;
/* 158 */         for (y = 0; y <= this.maxY.intValue() - this.minY.intValue(); y++) {
/* 160 */           PGrid item = this.grid.get(new Point(this.minX.intValue() + i, this.minY.intValue() + y));
/* 161 */           if (item != null) {
/* 164 */             double x1 = GeomUtils.merc_x(item.x);
/* 166 */             double x2 = GeomUtils.merc_x((item.x + 1));
/* 167 */             double y1 = GeomUtils.merc_y(item.y);
/* 168 */             double y2 = GeomUtils.merc_y((item.y + 1));
/* 169 */             x1 -= west;
/* 170 */             x2 -= west;
/* 171 */             y1 -= north;
/* 172 */             y2 -= north;
/* 175 */             int mx = (int)(x1 * xDivision);
/* 176 */             int my = (int)(y1 * yDivision);
/* 177 */             int bx = (int)(x2 * xDivision);
/* 178 */             int by = (int)(y2 * yDivision);
/* 180 */             int bwidth = bx - mx;
/* 181 */             int bheight = by - my;
/* 184 */             my = height - my - bheight;
/* 186 */             if (item.file != null)
/*     */               try {
/* 188 */                 BufferedImage bufferedImage = ImageIO.read(item.file);
/* 189 */                 context.drawImage(bufferedImage, mx, my, bwidth, bheight, null);
/* 190 */               } catch (Exception e) {} 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 199 */     if (this.worldImage != null)
/* 200 */       g2d.drawImage(this.worldImage, 0, 0, width, height, null); 
/*     */     int x;
/* 203 */     for (x = 0; x <= this.maxX.intValue() - this.minX.intValue(); x++) {
/*     */       int y;
/* 204 */       for (y = 0; y <= this.maxY.intValue() - this.minY.intValue(); y++) {
/* 206 */         PGrid item = this.grid.get(new Point(this.minX.intValue() + x, this.minY.intValue() + y));
/* 207 */         if (item != null) {
/* 210 */           double x1 = GeomUtils.merc_x(item.x);
/* 212 */           double x2 = GeomUtils.merc_x((item.x + 1));
/* 213 */           double y1 = GeomUtils.merc_y(item.y);
/* 214 */           double y2 = GeomUtils.merc_y((item.y + 1));
/* 215 */           x1 -= west;
/* 216 */           x2 -= west;
/* 217 */           y1 -= north;
/* 218 */           y2 -= north;
/* 221 */           int mx = (int)(x1 * xDivision);
/* 222 */           int my = (int)(y1 * yDivision);
/* 223 */           int bx = (int)(x2 * xDivision);
/* 224 */           int by = (int)(y2 * yDivision);
/* 226 */           int bwidth = bx - mx;
/* 227 */           int bheight = by - my;
/* 230 */           my = height - my - bheight;
/* 234 */           if (item.status == 0)
/* 235 */             g2d.setColor(new Color(215, 58, 54, 100)); 
/* 236 */           if (item.status == 1)
/* 237 */             g2d.setColor(new Color(19, 137, 225, 100)); 
/* 238 */           if (item.status == 2)
/* 239 */             g2d.setColor(new Color(111, 222, 73, 100)); 
/* 245 */           g2d.fillRect(mx, my, bwidth, bheight);
/* 246 */           g2d.setColor(Color.black);
/* 247 */           g2d.drawRect(mx, my, bwidth, bheight);
/* 249 */           if (item.progress > 0.0F && item.progress < 1.0F) {
/* 250 */             g2d.setColor(new Color(111, 222, 73, 100));
/* 251 */             g2d.fillRect(mx, my, (int)(bwidth * item.progress), bheight);
/*     */           } 
/* 255 */           if (bwidth > 30 && bheight > 30) {
/* 256 */             String n = (item.y >= 0) ? "N" : "S";
/* 257 */             String e = (item.x >= 0) ? "E" : "W";
/* 259 */             if (item.status == 1) {
/* 260 */               g2d.setColor(Color.white);
/*     */             } else {
/* 262 */               g2d.setColor(Color.black);
/*     */             } 
/* 265 */             FontMetrics fm = g.getFontMetrics();
/* 267 */             String text = String.format("%d'%s,%d'%s", new Object[] { Integer.valueOf(FastMath.abs(item.y)), n, Integer.valueOf(FastMath.abs(item.x)), e });
/* 268 */             int fWidth = fm.stringWidth(text);
/* 270 */             g2d.drawString(text, mx + bwidth / 2 - fWidth / 2, my + bheight / 2);
/* 272 */             if (item.regions != null) {
/* 273 */               fWidth = fm.stringWidth(item.regions);
/* 274 */               g2d.drawString(item.regions, mx + bwidth / 2 - fWidth / 2, my + bheight / 2 + 20);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g) {
/* 288 */     super.paintComponent(g);
/* 289 */     doDrawing(g);
/*     */   }
/*     */   
/*     */   public void setTileLoading(DSFTile tile) {
/* 294 */     int x = (int)tile.getArea().getMinX();
/* 295 */     int y = (int)tile.getArea().getMinY();
/* 297 */     PGrid gridItem = this.grid.get(new Point(x, y));
/* 298 */     if (gridItem != null)
/* 299 */       gridItem.status = 1; 
/* 301 */     validate();
/* 302 */     repaint();
/*     */   }
/*     */   
/*     */   public void setTileComplete(DSFTile tile, File file) {
/* 307 */     int x = (int)tile.getArea().getMinX();
/* 308 */     int y = (int)tile.getArea().getMinY();
/* 310 */     PGrid gridItem = this.grid.get(new Point(x, y));
/* 311 */     if (gridItem != null) {
/* 312 */       gridItem.status = 2;
/* 313 */       gridItem.file = file;
/*     */     } 
/* 315 */     this.worldImage = null;
/* 316 */     validate();
/* 317 */     repaint();
/*     */   }
/*     */   
/*     */   public void setTileProgress(DSFTile tile, float progress, String message) {
/* 322 */     int x = (int)tile.getArea().getMinX();
/* 323 */     int y = (int)tile.getArea().getMinY();
/* 325 */     PGrid gridItem = this.grid.get(new Point(x, y));
/* 326 */     if (gridItem != null)
/* 327 */       gridItem.progress = progress; 
/* 329 */     validate();
/* 330 */     repaint();
/*     */   }
/*     */   
/*     */   public void setTiles(Set<DSFTile> dsfTiles) {
/* 335 */     for (DSFTile tile : dsfTiles) {
/* 336 */       if (this.minX == null)
/* 337 */         this.minX = Integer.valueOf((int)tile.getArea().getMinX()); 
/* 339 */       if ((int)tile.getArea().getMinX() < this.minX.intValue())
/* 340 */         this.minX = Integer.valueOf((int)tile.getArea().getMinX()); 
/* 343 */       if (this.maxX == null)
/* 344 */         this.maxX = Integer.valueOf((int)tile.getArea().getMaxX()); 
/* 346 */       if ((int)tile.getArea().getMaxX() > this.maxX.intValue())
/* 347 */         this.maxX = Integer.valueOf((int)tile.getArea().getMaxX()); 
/* 350 */       if (this.minY == null)
/* 351 */         this.minY = Integer.valueOf((int)tile.getArea().getMinY()); 
/* 353 */       if ((int)tile.getArea().getMinY() < this.minY.intValue())
/* 354 */         this.minY = Integer.valueOf((int)tile.getArea().getMinY()); 
/* 357 */       if (this.maxY == null)
/* 358 */         this.maxY = Integer.valueOf((int)tile.getArea().getMaxY()); 
/* 360 */       if ((int)tile.getArea().getMaxY() > this.maxY.intValue())
/* 361 */         this.maxY = Integer.valueOf((int)tile.getArea().getMaxY()); 
/* 364 */       PGrid pGrid = new PGrid();
/* 365 */       pGrid.x = (int)tile.getArea().getMinX();
/* 366 */       pGrid.y = (int)tile.getArea().getMinY();
/* 367 */       pGrid.status = tile.isCompleted() ? 2 : 0;
/* 368 */       pGrid.regions = tile.getRegions();
/* 370 */       File debugImage = tile.getImageDebugFile();
/* 371 */       if (debugImage != null && debugImage.exists())
/* 372 */         pGrid.file = debugImage; 
/* 374 */       this.grid.put(new Point(pGrid.x, pGrid.y), pGrid);
/*     */     } 
/* 381 */     Dimension size = getSize();
/* 382 */     Insets insets = getInsets();
/* 384 */     int width = size.width - insets.left - insets.right;
/* 385 */     int height = size.height - insets.top - insets.bottom;
/*     */     try {
/* 388 */       double west = GeomUtils.merc_x(this.minX.intValue());
/* 389 */       double east = GeomUtils.merc_x(this.maxX.intValue());
/* 390 */       double north = GeomUtils.merc_y(this.minY.intValue());
/* 391 */       double south = GeomUtils.merc_y(this.maxY.intValue());
/* 394 */       double sizeX = east - west;
/* 395 */       double sizeY = south - north;
/* 397 */       double xDivision = width / sizeX;
/* 398 */       double yDivision = height / sizeY;
/* 401 */       validate();
/* 402 */       repaint();
/* 403 */     } catch (Exception e) {
/* 404 */       int bob = 23;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void showStatus(String status) {
/* 409 */     this.message = status;
/* 410 */     validate();
/* 411 */     repaint();
/*     */   }
/*     */   
/*     */   public static String getTileNumber(double lat, double lon, int zoom) {
/* 415 */     int xtile = (int)Math.floor((lon + 180.0D) / 360.0D * (1 << zoom));
/* 416 */     int ytile = (int)Math.floor((1.0D - Math.log(Math.tan(Math.toRadians(lat)) + 1.0D / Math.cos(Math.toRadians(lat))) / Math.PI) / 2.0D * (1 << zoom));
/* 417 */     if (xtile < 0)
/* 418 */       xtile = 0; 
/* 419 */     if (xtile >= 1 << zoom)
/* 420 */       xtile = (1 << zoom) - 1; 
/* 421 */     if (ytile < 0)
/* 422 */       ytile = 0; 
/* 423 */     if (ytile >= 1 << zoom)
/* 424 */       ytile = (1 << zoom) - 1; 
/* 426 */     return "http://tile.openstreetmap.org/" + "" + zoom + "/" + xtile + "/" + ytile + ".png";
/*     */   }
/*     */   
/*     */   public static int lon2Tile(double lon, int zoom) {
/* 430 */     int xtile = (int)Math.floor((lon + 180.0D) / 360.0D * (1 << zoom));
/* 431 */     if (xtile < 0)
/* 432 */       xtile = 0; 
/* 433 */     return xtile;
/*     */   }
/*     */   
/*     */   public static int lat2Tile(double lat, int zoom) {
/* 437 */     int ytile = (int)Math.floor((1.0D - Math.log(Math.tan(Math.toRadians(lat)) + 1.0D / Math.cos(Math.toRadians(lat))) / Math.PI) / 2.0D * (1 << zoom));
/* 438 */     if (ytile < 0)
/* 439 */       ytile = 0; 
/* 440 */     if (ytile >= 1 << zoom)
/* 441 */       ytile = (1 << zoom) - 1; 
/* 442 */     return ytile;
/*     */   }
/*     */   
/*     */   public static class BoundingBox {
/*     */     double north;
/*     */     
/*     */     double south;
/*     */     
/*     */     double east;
/*     */     
/*     */     double west;
/*     */   }
/*     */   
/*     */   public BoundingBox tile2boundingBox(int x, int y, int zoom) {
/* 454 */     BoundingBox bb = new BoundingBox();
/* 455 */     bb.north = tile2lat(y, zoom);
/* 456 */     bb.south = tile2lat(y + 1, zoom);
/* 457 */     bb.west = tile2lon(x, zoom);
/* 458 */     bb.east = tile2lon(x + 1, zoom);
/* 459 */     return bb;
/*     */   }
/*     */   
/*     */   public static double tile2lon(int x, int z) {
/* 463 */     return x / Math.pow(2.0D, z) * 360.0D - 180.0D;
/*     */   }
/*     */   
/*     */   public static double tile2lat(int y, int z) {
/* 467 */     double n = Math.PI - 6.283185307179586D * y / Math.pow(2.0D, z);
/* 468 */     return Math.toDegrees(Math.atan(Math.sinh(n)));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\ProgressWindow.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */