/*     */ package org.geotools.styling;
/*     */ 
/*     */ import org.opengis.style.ColorReplacement;
/*     */ 
/*     */ public class AbstractStyleVisitor implements StyleVisitor {
/*     */   public void visit(StyledLayerDescriptor sld) {
/*  30 */     for (StyledLayer sl : sld.getStyledLayers()) {
/*  31 */       if (sl instanceof UserLayer) {
/*  32 */         ((UserLayer)sl).accept(this);
/*  33 */       } else if (sl instanceof NamedLayer) {
/*  34 */         ((NamedLayer)sl).accept(this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(NamedLayer layer) {
/*  41 */     for (Style s : layer.getStyles())
/*  42 */       s.accept(this); 
/*  44 */     for (FeatureTypeConstraint ftc : layer.getLayerFeatureConstraints())
/*  45 */       ftc.accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(UserLayer layer) {
/*  51 */     for (Style s : layer.getUserStyles())
/*  52 */       s.accept(this); 
/*  54 */     for (FeatureTypeConstraint ftc : layer.getLayerFeatureConstraints())
/*  55 */       ftc.accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(FeatureTypeConstraint ftc) {}
/*     */   
/*     */   public void visit(Style style) {
/*  65 */     for (FeatureTypeStyle fts : style.getFeatureTypeStyles())
/*  66 */       fts.accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(Rule rule) {
/*  72 */     for (Symbolizer sym : rule.getSymbolizers())
/*  73 */       sym.accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(FeatureTypeStyle fts) {
/*  79 */     for (Rule r : fts.getRules())
/*  80 */       r.accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(Fill fill) {
/*  86 */     if (fill.getColor() != null);
/*  89 */     if (fill.getGraphicFill() != null)
/*  90 */       fill.getGraphicFill().accept(this); 
/*  92 */     if (fill.getOpacity() != null);
/*     */   }
/*     */   
/*     */   public void visit(Stroke stroke) {
/*  99 */     if (stroke.getColor() != null);
/* 102 */     if (stroke.getDashOffset() != null);
/* 105 */     if (stroke.getGraphicFill() != null)
/* 106 */       stroke.getGraphicFill().accept(this); 
/* 108 */     if (stroke.getGraphicStroke() != null)
/* 109 */       stroke.getGraphicStroke().accept(this); 
/* 111 */     if (stroke.getLineCap() != null);
/* 114 */     if (stroke.getLineJoin() != null);
/* 117 */     if (stroke.getOpacity() != null);
/* 120 */     if (stroke.getWidth() != null);
/*     */   }
/*     */   
/*     */   public void visit(Symbolizer sym) {
/* 127 */     if (sym instanceof RasterSymbolizer) {
/* 128 */       visit((RasterSymbolizer)sym);
/* 130 */     } else if (sym instanceof LineSymbolizer) {
/* 131 */       visit((LineSymbolizer)sym);
/* 133 */     } else if (sym instanceof PolygonSymbolizer) {
/* 134 */       visit((PolygonSymbolizer)sym);
/* 136 */     } else if (sym instanceof PointSymbolizer) {
/* 137 */       visit((PointSymbolizer)sym);
/* 139 */     } else if (sym instanceof TextSymbolizer) {
/* 140 */       visit((TextSymbolizer)sym);
/*     */     } else {
/* 143 */       throw new RuntimeException("visit(Symbolizer) unsupported");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visit(PointSymbolizer ps) {
/* 149 */     if (ps.getDescription() != null)
/* 150 */       ps.getDescription().accept(this); 
/* 152 */     if (ps.getGeometry() != null);
/* 155 */     if (ps.getGraphic() != null)
/* 156 */       ps.getGraphic().accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(LineSymbolizer line) {
/* 162 */     if (line.getDescription() != null)
/* 163 */       line.getDescription().accept(this); 
/* 165 */     if (line.getGeometry() != null);
/* 168 */     if (line.getPerpendicularOffset() != null);
/* 171 */     if (line.getStroke() != null)
/* 172 */       line.getStroke().accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(PolygonSymbolizer poly) {
/* 178 */     if (poly.getDescription() != null)
/* 179 */       poly.getDescription().accept(this); 
/* 181 */     if (poly.getDisplacement() != null)
/* 182 */       poly.getDisplacement().accept(this); 
/* 184 */     if (poly.getFill() != null)
/* 185 */       poly.getFill().accept(this); 
/* 187 */     if (poly.getGeometry() != null);
/* 190 */     if (poly.getPerpendicularOffset() != null);
/* 193 */     if (poly.getStroke() != null)
/* 194 */       poly.getStroke().accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(TextSymbolizer text) {
/* 200 */     if (text.getDescription() != null)
/* 201 */       text.getDescription().accept(this); 
/* 203 */     if (text.getFill() != null)
/* 204 */       text.getFill().accept(this); 
/* 206 */     if (text.getFont() != null);
/* 209 */     if (text.getGeometry() != null);
/* 212 */     if (text.getHalo() != null)
/* 213 */       text.getHalo().accept(this); 
/* 215 */     if (text.getLabel() != null);
/* 218 */     if (text.getLabelPlacement() != null)
/* 219 */       text.getLabelPlacement().accept(this); 
/* 221 */     if (text.getPriority() != null);
/*     */   }
/*     */   
/*     */   public void visit(RasterSymbolizer raster) {
/* 228 */     if (raster.getChannelSelection() != null)
/* 229 */       raster.getChannelSelection().accept(this); 
/* 231 */     if (raster.getColorMap() != null)
/* 232 */       raster.getColorMap().accept(this); 
/* 234 */     if (raster.getContrastEnhancement() != null)
/* 235 */       raster.getContrastEnhancement().accept(this); 
/* 237 */     if (raster.getDescription() != null)
/* 238 */       raster.getDescription().accept(this); 
/* 240 */     if (raster.getGeometry() != null);
/* 243 */     if (raster.getImageOutline() != null)
/* 244 */       raster.getImageOutline().accept(this); 
/* 246 */     if (raster.getOpacity() != null);
/* 249 */     if (raster.getOverlap() != null);
/* 253 */     if (raster.getShadedRelief() != null)
/* 254 */       raster.getShadedRelief().accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(Graphic gr) {
/* 260 */     if (gr.getAnchorPoint() != null)
/* 261 */       gr.getAnchorPoint().accept(this); 
/* 263 */     if (gr.getDisplacement() != null)
/* 264 */       gr.getDisplacement().accept(this); 
/* 266 */     for (ExternalGraphic eg : gr.getExternalGraphics())
/* 267 */       eg.accept(this); 
/* 270 */     if (gr.getGap() != null);
/* 273 */     if (gr.getInitialGap() != null);
/* 276 */     for (Mark m : gr.getMarks())
/* 277 */       m.accept(this); 
/* 279 */     if (gr.getOpacity() != null);
/* 282 */     if (gr.getRotation() != null);
/* 285 */     if (gr.getSize() != null);
/*     */   }
/*     */   
/*     */   public void visit(Mark mark) {
/* 293 */     if (mark.getExternalMark() != null);
/* 296 */     if (mark.getFill() != null)
/* 297 */       mark.getFill().accept(this); 
/* 299 */     if (mark.getStroke() != null)
/* 300 */       mark.getStroke().accept(this); 
/* 302 */     if (mark.getWellKnownName() != null);
/*     */   }
/*     */   
/*     */   public void visit(ExternalGraphic exgr) {
/* 309 */     for (ColorReplacement cr : exgr.getColorReplacements());
/*     */   }
/*     */   
/*     */   public void visit(PointPlacement pp) {
/* 316 */     if (pp.getAnchorPoint() != null)
/* 317 */       pp.getAnchorPoint().accept(this); 
/* 319 */     if (pp.getDisplacement() != null)
/* 320 */       pp.getDisplacement().accept(this); 
/* 322 */     if (pp.getRotation() != null);
/*     */   }
/*     */   
/*     */   public void visit(AnchorPoint ap) {
/* 329 */     if (ap.getAnchorPointX() != null);
/* 332 */     if (ap.getAnchorPointY() != null);
/*     */   }
/*     */   
/*     */   public void visit(Displacement dis) {
/* 339 */     if (dis.getDisplacementX() != null);
/* 342 */     if (dis.getDisplacementY() != null);
/*     */   }
/*     */   
/*     */   public void visit(LinePlacement lp) {
/* 349 */     if (lp.getGap() != null);
/* 352 */     if (lp.getInitialGap() != null);
/* 355 */     if (lp.getPerpendicularOffset() != null);
/*     */   }
/*     */   
/*     */   public void visit(Halo halo) {
/* 362 */     if (halo.getFill() != null)
/* 363 */       halo.getFill().accept(this); 
/* 365 */     if (halo.getRadius() != null);
/*     */   }
/*     */   
/*     */   public void visit(ColorMap colorMap) {
/* 372 */     for (ColorMapEntry cme : colorMap.getColorMapEntries())
/* 373 */       cme.accept(this); 
/* 375 */     if (colorMap.getFunction() != null);
/*     */   }
/*     */   
/*     */   public void visit(ColorMapEntry colorMapEntry) {
/* 382 */     if (colorMapEntry.getColor() != null);
/* 385 */     if (colorMapEntry.getOpacity() != null);
/* 388 */     if (colorMapEntry.getQuantity() != null);
/*     */   }
/*     */   
/*     */   public void visit(ContrastEnhancement contrastEnhancement) {
/* 395 */     if (contrastEnhancement.getGammaValue() != null);
/*     */   }
/*     */   
/*     */   public void visit(ImageOutline outline) {
/* 402 */     if (outline.getSymbolizer() != null)
/* 403 */       outline.getSymbolizer().accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(ChannelSelection cs) {
/* 409 */     if (cs.getGrayChannel() != null)
/* 410 */       cs.getGrayChannel().accept(this); 
/* 412 */     for (SelectedChannelType ch : cs.getRGBChannels())
/* 413 */       ch.accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(OverlapBehavior ob) {}
/*     */   
/*     */   public void visit(SelectedChannelType sct) {
/* 423 */     if (sct.getContrastEnhancement() != null)
/* 424 */       sct.getContrastEnhancement().accept(this); 
/*     */   }
/*     */   
/*     */   public void visit(ShadedRelief sr) {
/* 430 */     if (sr.getReliefFactor() != null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\AbstractStyleVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */