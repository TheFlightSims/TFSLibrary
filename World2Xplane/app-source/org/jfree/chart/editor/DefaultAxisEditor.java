/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Paint;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ import org.jfree.chart.axis.Axis;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.layout.LCBLayout;
/*     */ import org.jfree.ui.FontChooserPanel;
/*     */ import org.jfree.ui.FontDisplayField;
/*     */ import org.jfree.ui.PaintSample;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ 
/*     */ class DefaultAxisEditor extends JPanel implements ActionListener {
/*     */   private JTextField label;
/*     */   
/*     */   private Font labelFont;
/*     */   
/*     */   private PaintSample labelPaintSample;
/*     */   
/*     */   private JTextField labelFontField;
/*     */   
/*     */   private Font tickLabelFont;
/*     */   
/*     */   private JTextField tickLabelFontField;
/*     */   
/*     */   private PaintSample tickLabelPaintSample;
/*     */   
/*     */   private JPanel slot1;
/*     */   
/*     */   private JPanel slot2;
/*     */   
/*     */   private JCheckBox showTickLabelsCheckBox;
/*     */   
/*     */   private JCheckBox showTickMarksCheckBox;
/*     */   
/*     */   private RectangleInsets tickLabelInsets;
/*     */   
/*     */   private RectangleInsets labelInsets;
/*     */   
/*     */   private JTabbedPane otherTabs;
/*     */   
/* 135 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */   public static DefaultAxisEditor getInstance(Axis axis) {
/* 149 */     if (axis != null) {
/* 152 */       if (axis instanceof NumberAxis)
/* 153 */         return new DefaultNumberAxisEditor((NumberAxis)axis); 
/* 156 */       return new DefaultAxisEditor(axis);
/*     */     } 
/* 160 */     return null;
/*     */   }
/*     */   
/*     */   public DefaultAxisEditor(Axis axis) {
/* 174 */     this.labelFont = axis.getLabelFont();
/* 175 */     this.labelPaintSample = new PaintSample(axis.getLabelPaint());
/* 176 */     this.tickLabelFont = axis.getTickLabelFont();
/* 177 */     this.tickLabelPaintSample = new PaintSample(axis.getTickLabelPaint());
/* 180 */     this.tickLabelInsets = axis.getTickLabelInsets();
/* 181 */     this.labelInsets = axis.getLabelInsets();
/* 183 */     setLayout(new BorderLayout());
/* 185 */     JPanel general = new JPanel(new BorderLayout());
/* 186 */     general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("General")));
/* 193 */     JPanel interior = new JPanel((LayoutManager)new LCBLayout(5));
/* 194 */     interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/* 195 */     interior.add(new JLabel(localizationResources.getString("Label")));
/* 196 */     this.label = new JTextField(axis.getLabel());
/* 197 */     interior.add(this.label);
/* 198 */     interior.add(new JPanel());
/* 200 */     interior.add(new JLabel(localizationResources.getString("Font")));
/* 201 */     this.labelFontField = (JTextField)new FontDisplayField(this.labelFont);
/* 202 */     interior.add(this.labelFontField);
/* 203 */     JButton b = new JButton(localizationResources.getString("Select..."));
/* 204 */     b.setActionCommand("SelectLabelFont");
/* 205 */     b.addActionListener(this);
/* 206 */     interior.add(b);
/* 208 */     interior.add(new JLabel(localizationResources.getString("Paint")));
/* 209 */     interior.add((Component)this.labelPaintSample);
/* 210 */     b = new JButton(localizationResources.getString("Select..."));
/* 211 */     b.setActionCommand("SelectLabelPaint");
/* 212 */     b.addActionListener(this);
/* 213 */     interior.add(b);
/* 236 */     general.add(interior);
/* 238 */     add(general, "North");
/* 240 */     this.slot1 = new JPanel(new BorderLayout());
/* 242 */     JPanel other = new JPanel(new BorderLayout());
/* 243 */     other.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("Other")));
/* 247 */     this.otherTabs = new JTabbedPane();
/* 248 */     this.otherTabs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/* 250 */     JPanel ticks = new JPanel((LayoutManager)new LCBLayout(3));
/* 251 */     ticks.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/* 253 */     this.showTickLabelsCheckBox = new JCheckBox(localizationResources.getString("Show_tick_labels"), axis.isTickLabelsVisible());
/* 257 */     ticks.add(this.showTickLabelsCheckBox);
/* 258 */     ticks.add(new JPanel());
/* 259 */     ticks.add(new JPanel());
/* 261 */     ticks.add(new JLabel(localizationResources.getString("Tick_label_font")));
/* 264 */     this.tickLabelFontField = (JTextField)new FontDisplayField(this.tickLabelFont);
/* 265 */     ticks.add(this.tickLabelFontField);
/* 266 */     b = new JButton(localizationResources.getString("Select..."));
/* 267 */     b.setActionCommand("SelectTickLabelFont");
/* 268 */     b.addActionListener(this);
/* 269 */     ticks.add(b);
/* 271 */     this.showTickMarksCheckBox = new JCheckBox(localizationResources.getString("Show_tick_marks"), axis.isTickMarksVisible());
/* 275 */     ticks.add(this.showTickMarksCheckBox);
/* 276 */     ticks.add(new JPanel());
/* 277 */     ticks.add(new JPanel());
/* 279 */     this.otherTabs.add(localizationResources.getString("Ticks"), ticks);
/* 281 */     other.add(this.otherTabs);
/* 283 */     this.slot1.add(other);
/* 285 */     this.slot2 = new JPanel(new BorderLayout());
/* 286 */     this.slot2.add(this.slot1, "North");
/* 287 */     add(this.slot2);
/*     */   }
/*     */   
/*     */   public String getLabel() {
/* 297 */     return this.label.getText();
/*     */   }
/*     */   
/*     */   public Font getLabelFont() {
/* 306 */     return this.labelFont;
/*     */   }
/*     */   
/*     */   public Paint getLabelPaint() {
/* 315 */     return this.labelPaintSample.getPaint();
/*     */   }
/*     */   
/*     */   public boolean isTickLabelsVisible() {
/* 324 */     return this.showTickLabelsCheckBox.isSelected();
/*     */   }
/*     */   
/*     */   public Font getTickLabelFont() {
/* 333 */     return this.tickLabelFont;
/*     */   }
/*     */   
/*     */   public Paint getTickLabelPaint() {
/* 342 */     return this.tickLabelPaintSample.getPaint();
/*     */   }
/*     */   
/*     */   public boolean isTickMarksVisible() {
/* 352 */     return this.showTickMarksCheckBox.isSelected();
/*     */   }
/*     */   
/*     */   public RectangleInsets getTickLabelInsets() {
/* 361 */     return (this.tickLabelInsets == null) ? new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D) : this.tickLabelInsets;
/*     */   }
/*     */   
/*     */   public RectangleInsets getLabelInsets() {
/* 372 */     return (this.labelInsets == null) ? new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D) : this.labelInsets;
/*     */   }
/*     */   
/*     */   public JTabbedPane getOtherTabs() {
/* 382 */     return this.otherTabs;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 392 */     String command = event.getActionCommand();
/* 393 */     if (command.equals("SelectLabelFont")) {
/* 394 */       attemptLabelFontSelection();
/* 396 */     } else if (command.equals("SelectLabelPaint")) {
/* 397 */       attemptModifyLabelPaint();
/* 399 */     } else if (command.equals("SelectTickLabelFont")) {
/* 400 */       attemptTickLabelFontSelection();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void attemptLabelFontSelection() {
/* 415 */     FontChooserPanel panel = new FontChooserPanel(this.labelFont);
/* 416 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Font_Selection"), 2, -1);
/* 420 */     if (result == 0) {
/* 421 */       this.labelFont = panel.getSelectedFont();
/* 422 */       this.labelFontField.setText(this.labelFont.getFontName() + " " + this.labelFont.getSize());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void attemptModifyLabelPaint() {
/* 434 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Label_Color"), Color.blue);
/* 437 */     if (c != null)
/* 438 */       this.labelPaintSample.setPaint(c); 
/*     */   }
/*     */   
/*     */   public void attemptTickLabelFontSelection() {
/* 447 */     FontChooserPanel panel = new FontChooserPanel(this.tickLabelFont);
/* 448 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Font_Selection"), 2, -1);
/* 452 */     if (result == 0) {
/* 453 */       this.tickLabelFont = panel.getSelectedFont();
/* 454 */       this.tickLabelFontField.setText(this.tickLabelFont.getFontName() + " " + this.tickLabelFont.getSize());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAxisProperties(Axis axis) {
/* 505 */     axis.setLabel(getLabel());
/* 506 */     axis.setLabelFont(getLabelFont());
/* 507 */     axis.setLabelPaint(getLabelPaint());
/* 508 */     axis.setTickMarksVisible(isTickMarksVisible());
/* 510 */     axis.setTickLabelsVisible(isTickLabelsVisible());
/* 511 */     axis.setTickLabelFont(getTickLabelFont());
/* 512 */     axis.setTickLabelPaint(getTickLabelPaint());
/* 513 */     axis.setTickLabelInsets(getTickLabelInsets());
/* 514 */     axis.setLabelInsets(getLabelInsets());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\editor\DefaultAxisEditor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */