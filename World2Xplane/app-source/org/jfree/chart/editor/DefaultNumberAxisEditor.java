/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
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
/*     */ import org.jfree.ui.PaintSample;
/*     */ import org.jfree.ui.StrokeChooserPanel;
/*     */ import org.jfree.ui.StrokeSample;
/*     */ 
/*     */ class DefaultNumberAxisEditor extends DefaultAxisEditor implements FocusListener {
/*     */   private boolean autoRange;
/*     */   
/*     */   private double minimumValue;
/*     */   
/*     */   private double maximumValue;
/*     */   
/*     */   private JCheckBox autoRangeCheckBox;
/*     */   
/*     */   private JTextField minimumRangeValue;
/*     */   
/*     */   private JTextField maximumRangeValue;
/*     */   
/*     */   private PaintSample gridPaintSample;
/*     */   
/*     */   private StrokeSample gridStrokeSample;
/*     */   
/*     */   private StrokeSample[] availableStrokeSamples;
/*     */   
/* 107 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */   public DefaultNumberAxisEditor(NumberAxis axis) {
/* 117 */     super((Axis)axis);
/* 119 */     this.autoRange = axis.isAutoRange();
/* 120 */     this.minimumValue = axis.getLowerBound();
/* 121 */     this.maximumValue = axis.getUpperBound();
/* 123 */     this.gridPaintSample = new PaintSample(Color.blue);
/* 124 */     this.gridStrokeSample = new StrokeSample(new BasicStroke(1.0F));
/* 126 */     this.availableStrokeSamples = new StrokeSample[3];
/* 127 */     this.availableStrokeSamples[0] = new StrokeSample(new BasicStroke(1.0F));
/* 129 */     this.availableStrokeSamples[1] = new StrokeSample(new BasicStroke(2.0F));
/* 131 */     this.availableStrokeSamples[2] = new StrokeSample(new BasicStroke(3.0F));
/* 134 */     JTabbedPane other = getOtherTabs();
/* 136 */     JPanel range = new JPanel((LayoutManager)new LCBLayout(3));
/* 137 */     range.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/* 139 */     range.add(new JPanel());
/* 140 */     this.autoRangeCheckBox = new JCheckBox(localizationResources.getString("Auto-adjust_range"), this.autoRange);
/* 143 */     this.autoRangeCheckBox.setActionCommand("AutoRangeOnOff");
/* 144 */     this.autoRangeCheckBox.addActionListener(this);
/* 145 */     range.add(this.autoRangeCheckBox);
/* 146 */     range.add(new JPanel());
/* 148 */     range.add(new JLabel(localizationResources.getString("Minimum_range_value")));
/* 151 */     this.minimumRangeValue = new JTextField(Double.toString(this.minimumValue));
/* 154 */     this.minimumRangeValue.setEnabled(!this.autoRange);
/* 155 */     this.minimumRangeValue.setActionCommand("MinimumRange");
/* 156 */     this.minimumRangeValue.addActionListener(this);
/* 157 */     this.minimumRangeValue.addFocusListener(this);
/* 158 */     range.add(this.minimumRangeValue);
/* 159 */     range.add(new JPanel());
/* 161 */     range.add(new JLabel(localizationResources.getString("Maximum_range_value")));
/* 164 */     this.maximumRangeValue = new JTextField(Double.toString(this.maximumValue));
/* 167 */     this.maximumRangeValue.setEnabled(!this.autoRange);
/* 168 */     this.maximumRangeValue.setActionCommand("MaximumRange");
/* 169 */     this.maximumRangeValue.addActionListener(this);
/* 170 */     this.maximumRangeValue.addFocusListener(this);
/* 171 */     range.add(this.maximumRangeValue);
/* 172 */     range.add(new JPanel());
/* 174 */     other.add(localizationResources.getString("Range"), range);
/*     */   }
/*     */   
/*     */   public boolean isAutoRange() {
/* 184 */     return this.autoRange;
/*     */   }
/*     */   
/*     */   public double getMinimumValue() {
/* 193 */     return this.minimumValue;
/*     */   }
/*     */   
/*     */   public double getMaximumValue() {
/* 202 */     return this.maximumValue;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 210 */     String command = event.getActionCommand();
/* 211 */     if (command.equals("GridStroke")) {
/* 212 */       attemptGridStrokeSelection();
/* 214 */     } else if (command.equals("GridPaint")) {
/* 215 */       attemptGridPaintSelection();
/* 217 */     } else if (command.equals("AutoRangeOnOff")) {
/* 218 */       toggleAutoRange();
/* 220 */     } else if (command.equals("MinimumRange")) {
/* 221 */       validateMinimum();
/* 223 */     } else if (command.equals("MaximumRange")) {
/* 224 */       validateMaximum();
/*     */     } else {
/* 228 */       super.actionPerformed(event);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void attemptGridStrokeSelection() {
/* 236 */     StrokeChooserPanel panel = new StrokeChooserPanel(null, this.availableStrokeSamples);
/* 239 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Stroke_Selection"), 2, -1);
/* 244 */     if (result == 0)
/* 245 */       this.gridStrokeSample.setStroke(panel.getSelectedStroke()); 
/*     */   }
/*     */   
/*     */   private void attemptGridPaintSelection() {
/* 254 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Grid_Color"), Color.blue);
/* 257 */     if (c != null)
/* 258 */       this.gridPaintSample.setPaint(c); 
/*     */   }
/*     */   
/*     */   public void focusGained(FocusEvent event) {}
/*     */   
/*     */   public void focusLost(FocusEvent event) {
/* 277 */     if (event.getSource() == this.minimumRangeValue) {
/* 278 */       validateMinimum();
/* 280 */     } else if (event.getSource() == this.maximumRangeValue) {
/* 281 */       validateMaximum();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void toggleAutoRange() {
/* 289 */     this.autoRange = this.autoRangeCheckBox.isSelected();
/* 290 */     if (this.autoRange) {
/* 291 */       this.minimumRangeValue.setText(Double.toString(this.minimumValue));
/* 292 */       this.minimumRangeValue.setEnabled(false);
/* 293 */       this.maximumRangeValue.setText(Double.toString(this.maximumValue));
/* 294 */       this.maximumRangeValue.setEnabled(false);
/*     */     } else {
/* 297 */       this.minimumRangeValue.setEnabled(true);
/* 298 */       this.maximumRangeValue.setEnabled(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void validateMinimum() {
/*     */     double newMin;
/*     */     try {
/* 308 */       newMin = Double.parseDouble(this.minimumRangeValue.getText());
/* 309 */       if (newMin >= this.maximumValue)
/* 310 */         newMin = this.minimumValue; 
/* 313 */     } catch (NumberFormatException e) {
/* 314 */       newMin = this.minimumValue;
/*     */     } 
/* 317 */     this.minimumValue = newMin;
/* 318 */     this.minimumRangeValue.setText(Double.toString(this.minimumValue));
/*     */   }
/*     */   
/*     */   public void validateMaximum() {
/*     */     double newMax;
/*     */     try {
/* 327 */       newMax = Double.parseDouble(this.maximumRangeValue.getText());
/* 328 */       if (newMax <= this.minimumValue)
/* 329 */         newMax = this.maximumValue; 
/* 332 */     } catch (NumberFormatException e) {
/* 333 */       newMax = this.maximumValue;
/*     */     } 
/* 336 */     this.maximumValue = newMax;
/* 337 */     this.maximumRangeValue.setText(Double.toString(this.maximumValue));
/*     */   }
/*     */   
/*     */   public void setAxisProperties(Axis axis) {
/* 347 */     super.setAxisProperties(axis);
/* 348 */     NumberAxis numberAxis = (NumberAxis)axis;
/* 349 */     numberAxis.setAutoRange(this.autoRange);
/* 350 */     if (!this.autoRange)
/* 351 */       numberAxis.setRange(this.minimumValue, this.maximumValue); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\editor\DefaultNumberAxisEditor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */