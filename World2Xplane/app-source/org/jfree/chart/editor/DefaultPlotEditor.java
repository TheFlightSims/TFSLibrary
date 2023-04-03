/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import org.jfree.chart.axis.Axis;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ColorBar;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.ContourPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*     */ import org.jfree.chart.renderer.category.LineAndShapeRenderer;
/*     */ import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
/*     */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*     */ import org.jfree.layout.LCBLayout;
/*     */ import org.jfree.ui.PaintSample;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.StrokeChooserPanel;
/*     */ import org.jfree.ui.StrokeSample;
/*     */ import org.jfree.util.BooleanUtilities;
/*     */ 
/*     */ class DefaultPlotEditor extends JPanel implements ActionListener {
/*  90 */   private static final String[] orientationNames = new String[] { "Vertical", "Horizontal" };
/*     */   
/*     */   private static final int ORIENTATION_VERTICAL = 0;
/*     */   
/*     */   private static final int ORIENTATION_HORIZONTAL = 1;
/*     */   
/*     */   private PaintSample backgroundPaintSample;
/*     */   
/*     */   private StrokeSample outlineStrokeSample;
/*     */   
/*     */   private PaintSample outlinePaintSample;
/*     */   
/*     */   private DefaultAxisEditor domainAxisPropertyPanel;
/*     */   
/*     */   private DefaultAxisEditor rangeAxisPropertyPanel;
/*     */   
/*     */   private DefaultColorBarEditor colorBarAxisPropertyPanel;
/*     */   
/*     */   private StrokeSample[] availableStrokeSamples;
/*     */   
/*     */   private RectangleInsets plotInsets;
/*     */   
/*     */   private PlotOrientation plotOrientation;
/*     */   
/*     */   private JComboBox orientationCombo;
/*     */   
/*     */   private Boolean drawLines;
/*     */   
/*     */   private JCheckBox drawLinesCheckBox;
/*     */   
/*     */   private Boolean drawShapes;
/*     */   
/*     */   private JCheckBox drawShapesCheckBox;
/*     */   
/* 158 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */   public DefaultPlotEditor(Plot plot) {
/*     */     ValueAxis valueAxis1, valueAxis2;
/* 174 */     this.plotInsets = plot.getInsets();
/* 175 */     this.backgroundPaintSample = new PaintSample(plot.getBackgroundPaint());
/* 176 */     this.outlineStrokeSample = new StrokeSample(plot.getOutlineStroke());
/* 177 */     this.outlinePaintSample = new PaintSample(plot.getOutlinePaint());
/* 178 */     if (plot instanceof CategoryPlot) {
/* 179 */       this.plotOrientation = ((CategoryPlot)plot).getOrientation();
/* 181 */     } else if (plot instanceof XYPlot) {
/* 182 */       this.plotOrientation = ((XYPlot)plot).getOrientation();
/*     */     } 
/* 184 */     if (plot instanceof CategoryPlot) {
/* 185 */       CategoryItemRenderer renderer = ((CategoryPlot)plot).getRenderer();
/* 186 */       if (renderer instanceof LineAndShapeRenderer) {
/* 187 */         LineAndShapeRenderer r = (LineAndShapeRenderer)renderer;
/* 188 */         this.drawLines = BooleanUtilities.valueOf(r.getBaseLinesVisible());
/* 190 */         this.drawShapes = BooleanUtilities.valueOf(r.getBaseShapesVisible());
/*     */       } 
/* 194 */     } else if (plot instanceof XYPlot) {
/* 195 */       XYItemRenderer renderer = ((XYPlot)plot).getRenderer();
/* 196 */       if (renderer instanceof StandardXYItemRenderer) {
/* 197 */         StandardXYItemRenderer r = (StandardXYItemRenderer)renderer;
/* 198 */         this.drawLines = BooleanUtilities.valueOf(r.getPlotLines());
/* 199 */         this.drawShapes = BooleanUtilities.valueOf(r.getBaseShapesVisible());
/*     */       } 
/*     */     } 
/* 203 */     setLayout(new BorderLayout());
/* 205 */     this.availableStrokeSamples = new StrokeSample[3];
/* 206 */     this.availableStrokeSamples[0] = new StrokeSample(new BasicStroke(1.0F));
/* 208 */     this.availableStrokeSamples[1] = new StrokeSample(new BasicStroke(2.0F));
/* 210 */     this.availableStrokeSamples[2] = new StrokeSample(new BasicStroke(3.0F));
/* 214 */     JPanel panel = new JPanel(new BorderLayout());
/* 215 */     panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), plot.getPlotType() + localizationResources.getString(":")));
/* 222 */     JPanel general = new JPanel(new BorderLayout());
/* 223 */     general.setBorder(BorderFactory.createTitledBorder(localizationResources.getString("General")));
/* 229 */     JPanel interior = new JPanel((LayoutManager)new LCBLayout(7));
/* 230 */     interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/* 244 */     interior.add(new JLabel(localizationResources.getString("Outline_stroke")));
/* 247 */     JButton button = new JButton(localizationResources.getString("Select..."));
/* 248 */     button.setActionCommand("OutlineStroke");
/* 249 */     button.addActionListener(this);
/* 250 */     interior.add((Component)this.outlineStrokeSample);
/* 251 */     interior.add(button);
/* 253 */     interior.add(new JLabel(localizationResources.getString("Outline_Paint")));
/* 256 */     button = new JButton(localizationResources.getString("Select..."));
/* 257 */     button.setActionCommand("OutlinePaint");
/* 258 */     button.addActionListener(this);
/* 259 */     interior.add((Component)this.outlinePaintSample);
/* 260 */     interior.add(button);
/* 262 */     interior.add(new JLabel(localizationResources.getString("Background_paint")));
/* 265 */     button = new JButton(localizationResources.getString("Select..."));
/* 266 */     button.setActionCommand("BackgroundPaint");
/* 267 */     button.addActionListener(this);
/* 268 */     interior.add((Component)this.backgroundPaintSample);
/* 269 */     interior.add(button);
/* 271 */     if (this.plotOrientation != null) {
/* 272 */       boolean isVertical = this.plotOrientation.equals(PlotOrientation.VERTICAL);
/* 274 */       int index = isVertical ? 0 : 1;
/* 276 */       interior.add(new JLabel(localizationResources.getString("Orientation")));
/* 279 */       this.orientationCombo = new JComboBox(orientationNames);
/* 280 */       this.orientationCombo.setSelectedIndex(index);
/* 281 */       this.orientationCombo.setActionCommand("Orientation");
/* 282 */       this.orientationCombo.addActionListener(this);
/* 283 */       interior.add(new JPanel());
/* 284 */       interior.add(this.orientationCombo);
/*     */     } 
/* 287 */     if (this.drawLines != null) {
/* 288 */       interior.add(new JLabel(localizationResources.getString("Draw_lines")));
/* 291 */       this.drawLinesCheckBox = new JCheckBox();
/* 292 */       this.drawLinesCheckBox.setSelected(this.drawLines.booleanValue());
/* 293 */       this.drawLinesCheckBox.setActionCommand("DrawLines");
/* 294 */       this.drawLinesCheckBox.addActionListener(this);
/* 295 */       interior.add(new JPanel());
/* 296 */       interior.add(this.drawLinesCheckBox);
/*     */     } 
/* 299 */     if (this.drawShapes != null) {
/* 300 */       interior.add(new JLabel(localizationResources.getString("Draw_shapes")));
/* 303 */       this.drawShapesCheckBox = new JCheckBox();
/* 304 */       this.drawShapesCheckBox.setSelected(this.drawShapes.booleanValue());
/* 305 */       this.drawShapesCheckBox.setActionCommand("DrawShapes");
/* 306 */       this.drawShapesCheckBox.addActionListener(this);
/* 307 */       interior.add(new JPanel());
/* 308 */       interior.add(this.drawShapesCheckBox);
/*     */     } 
/* 311 */     general.add(interior, "North");
/* 313 */     JPanel appearance = new JPanel(new BorderLayout());
/* 314 */     appearance.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 315 */     appearance.add(general, "North");
/* 317 */     JTabbedPane tabs = new JTabbedPane();
/* 318 */     tabs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/* 320 */     Axis domainAxis = null;
/* 321 */     if (plot instanceof CategoryPlot) {
/* 322 */       CategoryAxis categoryAxis = ((CategoryPlot)plot).getDomainAxis();
/* 324 */     } else if (plot instanceof XYPlot) {
/* 325 */       valueAxis1 = ((XYPlot)plot).getDomainAxis();
/*     */     } 
/* 327 */     this.domainAxisPropertyPanel = DefaultAxisEditor.getInstance((Axis)valueAxis1);
/* 329 */     if (this.domainAxisPropertyPanel != null) {
/* 330 */       this.domainAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 333 */       tabs.add(localizationResources.getString("Domain_Axis"), this.domainAxisPropertyPanel);
/*     */     } 
/* 339 */     Axis rangeAxis = null;
/* 340 */     if (plot instanceof CategoryPlot) {
/* 341 */       valueAxis2 = ((CategoryPlot)plot).getRangeAxis();
/* 343 */     } else if (plot instanceof XYPlot) {
/* 344 */       valueAxis2 = ((XYPlot)plot).getRangeAxis();
/*     */     } 
/* 347 */     this.rangeAxisPropertyPanel = DefaultAxisEditor.getInstance((Axis)valueAxis2);
/* 349 */     if (this.rangeAxisPropertyPanel != null) {
/* 350 */       this.rangeAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 353 */       tabs.add(localizationResources.getString("Range_Axis"), this.rangeAxisPropertyPanel);
/*     */     } 
/* 360 */     ColorBar colorBar = null;
/* 361 */     if (plot instanceof ContourPlot)
/* 362 */       colorBar = ((ContourPlot)plot).getColorBar(); 
/* 365 */     this.colorBarAxisPropertyPanel = DefaultColorBarEditor.getInstance(colorBar);
/* 367 */     if (this.colorBarAxisPropertyPanel != null) {
/* 368 */       this.colorBarAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 371 */       tabs.add(localizationResources.getString("Color_Bar"), this.colorBarAxisPropertyPanel);
/*     */     } 
/* 378 */     tabs.add(localizationResources.getString("Appearance"), appearance);
/* 379 */     panel.add(tabs);
/* 381 */     add(panel);
/*     */   }
/*     */   
/*     */   public RectangleInsets getPlotInsets() {
/* 390 */     if (this.plotInsets == null)
/* 391 */       this.plotInsets = new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D); 
/* 393 */     return this.plotInsets;
/*     */   }
/*     */   
/*     */   public Paint getBackgroundPaint() {
/* 402 */     return this.backgroundPaintSample.getPaint();
/*     */   }
/*     */   
/*     */   public Stroke getOutlineStroke() {
/* 411 */     return this.outlineStrokeSample.getStroke();
/*     */   }
/*     */   
/*     */   public Paint getOutlinePaint() {
/* 420 */     return this.outlinePaintSample.getPaint();
/*     */   }
/*     */   
/*     */   public DefaultAxisEditor getDomainAxisPropertyEditPanel() {
/* 430 */     return this.domainAxisPropertyPanel;
/*     */   }
/*     */   
/*     */   public DefaultAxisEditor getRangeAxisPropertyEditPanel() {
/* 440 */     return this.rangeAxisPropertyPanel;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 448 */     String command = event.getActionCommand();
/* 449 */     if (command.equals("BackgroundPaint")) {
/* 450 */       attemptBackgroundPaintSelection();
/* 452 */     } else if (command.equals("OutlineStroke")) {
/* 453 */       attemptOutlineStrokeSelection();
/* 455 */     } else if (command.equals("OutlinePaint")) {
/* 456 */       attemptOutlinePaintSelection();
/* 461 */     } else if (command.equals("Orientation")) {
/* 462 */       attemptOrientationSelection();
/* 464 */     } else if (command.equals("DrawLines")) {
/* 465 */       attemptDrawLinesSelection();
/* 467 */     } else if (command.equals("DrawShapes")) {
/* 468 */       attemptDrawShapesSelection();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void attemptBackgroundPaintSelection() {
/* 477 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Background_Color"), Color.blue);
/* 481 */     if (c != null)
/* 482 */       this.backgroundPaintSample.setPaint(c); 
/*     */   }
/*     */   
/*     */   private void attemptOutlineStrokeSelection() {
/* 490 */     StrokeChooserPanel panel = new StrokeChooserPanel(null, this.availableStrokeSamples);
/* 492 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Stroke_Selection"), 2, -1);
/* 496 */     if (result == 0)
/* 497 */       this.outlineStrokeSample.setStroke(panel.getSelectedStroke()); 
/*     */   }
/*     */   
/*     */   private void attemptOutlinePaintSelection() {
/* 507 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Outline_Color"), Color.blue);
/* 510 */     if (c != null)
/* 511 */       this.outlinePaintSample.setPaint(c); 
/*     */   }
/*     */   
/*     */   private void attemptOrientationSelection() {
/* 538 */     int index = this.orientationCombo.getSelectedIndex();
/* 540 */     if (index == 0) {
/* 541 */       this.plotOrientation = PlotOrientation.VERTICAL;
/*     */     } else {
/* 544 */       this.plotOrientation = PlotOrientation.HORIZONTAL;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void attemptDrawLinesSelection() {
/* 554 */     this.drawLines = BooleanUtilities.valueOf(this.drawLinesCheckBox.isSelected());
/*     */   }
/*     */   
/*     */   private void attemptDrawShapesSelection() {
/* 564 */     this.drawShapes = BooleanUtilities.valueOf(this.drawShapesCheckBox.isSelected());
/*     */   }
/*     */   
/*     */   public void updatePlotProperties(Plot plot) {
/* 577 */     plot.setOutlinePaint(getOutlinePaint());
/* 578 */     plot.setOutlineStroke(getOutlineStroke());
/* 579 */     plot.setBackgroundPaint(getBackgroundPaint());
/* 580 */     plot.setInsets(getPlotInsets());
/* 583 */     if (this.domainAxisPropertyPanel != null) {
/*     */       ValueAxis valueAxis;
/* 584 */       Axis domainAxis = null;
/* 585 */       if (plot instanceof CategoryPlot) {
/* 586 */         CategoryPlot p = (CategoryPlot)plot;
/* 587 */         CategoryAxis categoryAxis = p.getDomainAxis();
/* 589 */       } else if (plot instanceof XYPlot) {
/* 590 */         XYPlot p = (XYPlot)plot;
/* 591 */         valueAxis = p.getDomainAxis();
/*     */       } 
/* 593 */       if (valueAxis != null)
/* 594 */         this.domainAxisPropertyPanel.setAxisProperties((Axis)valueAxis); 
/*     */     } 
/* 598 */     if (this.rangeAxisPropertyPanel != null) {
/*     */       ValueAxis valueAxis;
/* 599 */       Axis rangeAxis = null;
/* 600 */       if (plot instanceof CategoryPlot) {
/* 601 */         CategoryPlot p = (CategoryPlot)plot;
/* 602 */         valueAxis = p.getRangeAxis();
/* 604 */       } else if (plot instanceof XYPlot) {
/* 605 */         XYPlot p = (XYPlot)plot;
/* 606 */         valueAxis = p.getRangeAxis();
/*     */       } 
/* 608 */       if (valueAxis != null)
/* 609 */         this.rangeAxisPropertyPanel.setAxisProperties((Axis)valueAxis); 
/*     */     } 
/* 613 */     if (this.plotOrientation != null)
/* 614 */       if (plot instanceof CategoryPlot) {
/* 615 */         CategoryPlot p = (CategoryPlot)plot;
/* 616 */         p.setOrientation(this.plotOrientation);
/* 618 */       } else if (plot instanceof XYPlot) {
/* 619 */         XYPlot p = (XYPlot)plot;
/* 620 */         p.setOrientation(this.plotOrientation);
/*     */       }  
/* 624 */     if (this.drawLines != null)
/* 625 */       if (plot instanceof CategoryPlot) {
/* 626 */         CategoryPlot p = (CategoryPlot)plot;
/* 627 */         CategoryItemRenderer r = p.getRenderer();
/* 628 */         if (r instanceof LineAndShapeRenderer)
/* 629 */           ((LineAndShapeRenderer)r).setLinesVisible(this.drawLines.booleanValue()); 
/* 634 */       } else if (plot instanceof XYPlot) {
/* 635 */         XYPlot p = (XYPlot)plot;
/* 636 */         XYItemRenderer r = p.getRenderer();
/* 637 */         if (r instanceof StandardXYItemRenderer)
/* 638 */           ((StandardXYItemRenderer)r).setPlotLines(this.drawLines.booleanValue()); 
/*     */       }  
/* 645 */     if (this.drawShapes != null)
/* 646 */       if (plot instanceof CategoryPlot) {
/* 647 */         CategoryPlot p = (CategoryPlot)plot;
/* 648 */         CategoryItemRenderer r = p.getRenderer();
/* 649 */         if (r instanceof LineAndShapeRenderer)
/* 650 */           ((LineAndShapeRenderer)r).setShapesVisible(this.drawShapes.booleanValue()); 
/* 655 */       } else if (plot instanceof XYPlot) {
/* 656 */         XYPlot p = (XYPlot)plot;
/* 657 */         XYItemRenderer r = p.getRenderer();
/* 658 */         if (r instanceof StandardXYItemRenderer)
/* 659 */           ((StandardXYItemRenderer)r).setBaseShapesVisible(this.drawShapes.booleanValue()); 
/*     */       }  
/* 666 */     if (this.colorBarAxisPropertyPanel != null) {
/* 667 */       ColorBar colorBar = null;
/* 668 */       if (plot instanceof ContourPlot) {
/* 669 */         ContourPlot p = (ContourPlot)plot;
/* 670 */         colorBar = p.getColorBar();
/*     */       } 
/* 672 */       if (colorBar != null)
/* 673 */         this.colorBarAxisPropertyPanel.setAxisProperties(colorBar); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\editor\DefaultPlotEditor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */