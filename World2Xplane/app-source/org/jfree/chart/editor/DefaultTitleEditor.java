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
/*     */ import javax.swing.JTextField;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.title.TextTitle;
/*     */ import org.jfree.chart.title.Title;
/*     */ import org.jfree.layout.LCBLayout;
/*     */ import org.jfree.ui.FontChooserPanel;
/*     */ import org.jfree.ui.FontDisplayField;
/*     */ import org.jfree.ui.PaintSample;
/*     */ 
/*     */ class DefaultTitleEditor extends JPanel implements ActionListener {
/*     */   private boolean showTitle;
/*     */   
/*     */   private JCheckBox showTitleCheckBox;
/*     */   
/*     */   private JTextField titleField;
/*     */   
/*     */   private Font titleFont;
/*     */   
/*     */   private JTextField fontfield;
/*     */   
/*     */   private JButton selectFontButton;
/*     */   
/*     */   private PaintSample titlePaint;
/*     */   
/*     */   private JButton selectPaintButton;
/*     */   
/* 101 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */   public DefaultTitleEditor(Title title) {
/* 112 */     TextTitle t = (title != null) ? (TextTitle)title : new TextTitle(localizationResources.getString("Title"));
/* 114 */     this.showTitle = (title != null);
/* 115 */     this.titleFont = t.getFont();
/* 116 */     this.titleField = new JTextField(t.getText());
/* 117 */     this.titlePaint = new PaintSample(t.getPaint());
/* 119 */     setLayout(new BorderLayout());
/* 121 */     JPanel general = new JPanel(new BorderLayout());
/* 122 */     general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("General")));
/* 129 */     JPanel interior = new JPanel((LayoutManager)new LCBLayout(4));
/* 130 */     interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/* 132 */     interior.add(new JLabel(localizationResources.getString("Show_Title")));
/* 133 */     this.showTitleCheckBox = new JCheckBox();
/* 134 */     this.showTitleCheckBox.setSelected(this.showTitle);
/* 135 */     this.showTitleCheckBox.setActionCommand("ShowTitle");
/* 136 */     this.showTitleCheckBox.addActionListener(this);
/* 137 */     interior.add(new JPanel());
/* 138 */     interior.add(this.showTitleCheckBox);
/* 140 */     JLabel titleLabel = new JLabel(localizationResources.getString("Text"));
/* 141 */     interior.add(titleLabel);
/* 142 */     interior.add(this.titleField);
/* 143 */     interior.add(new JPanel());
/* 145 */     JLabel fontLabel = new JLabel(localizationResources.getString("Font"));
/* 146 */     this.fontfield = (JTextField)new FontDisplayField(this.titleFont);
/* 147 */     this.selectFontButton = new JButton(localizationResources.getString("Select..."));
/* 150 */     this.selectFontButton.setActionCommand("SelectFont");
/* 151 */     this.selectFontButton.addActionListener(this);
/* 152 */     interior.add(fontLabel);
/* 153 */     interior.add(this.fontfield);
/* 154 */     interior.add(this.selectFontButton);
/* 156 */     JLabel colorLabel = new JLabel(localizationResources.getString("Color"));
/* 159 */     this.selectPaintButton = new JButton(localizationResources.getString("Select..."));
/* 162 */     this.selectPaintButton.setActionCommand("SelectPaint");
/* 163 */     this.selectPaintButton.addActionListener(this);
/* 164 */     interior.add(colorLabel);
/* 165 */     interior.add((Component)this.titlePaint);
/* 166 */     interior.add(this.selectPaintButton);
/* 168 */     enableOrDisableControls();
/* 170 */     general.add(interior);
/* 171 */     add(general, "North");
/*     */   }
/*     */   
/*     */   public String getTitleText() {
/* 180 */     return this.titleField.getText();
/*     */   }
/*     */   
/*     */   public Font getTitleFont() {
/* 189 */     return this.titleFont;
/*     */   }
/*     */   
/*     */   public Paint getTitlePaint() {
/* 198 */     return this.titlePaint.getPaint();
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 209 */     String command = event.getActionCommand();
/* 211 */     if (command.equals("SelectFont")) {
/* 212 */       attemptFontSelection();
/* 214 */     } else if (command.equals("SelectPaint")) {
/* 215 */       attemptPaintSelection();
/* 217 */     } else if (command.equals("ShowTitle")) {
/* 218 */       attemptModifyShowTitle();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void attemptFontSelection() {
/* 227 */     FontChooserPanel panel = new FontChooserPanel(this.titleFont);
/* 228 */     int result = JOptionPane.showConfirmDialog(this, panel, localizationResources.getString("Font_Selection"), 2, -1);
/* 234 */     if (result == 0) {
/* 235 */       this.titleFont = panel.getSelectedFont();
/* 236 */       this.fontfield.setText(this.titleFont.getFontName() + " " + this.titleFont.getSize());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void attemptPaintSelection() {
/* 249 */     Paint p = this.titlePaint.getPaint();
/* 250 */     Color defaultColor = (p instanceof Color) ? (Color)p : Color.blue;
/* 251 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Title_Color"), defaultColor);
/* 254 */     if (c != null)
/* 255 */       this.titlePaint.setPaint(c); 
/*     */   }
/*     */   
/*     */   private void attemptModifyShowTitle() {
/* 264 */     this.showTitle = this.showTitleCheckBox.isSelected();
/* 265 */     enableOrDisableControls();
/*     */   }
/*     */   
/*     */   private void enableOrDisableControls() {
/* 273 */     boolean enabled = (this.showTitle == true);
/* 274 */     this.titleField.setEnabled(enabled);
/* 275 */     this.selectFontButton.setEnabled(enabled);
/* 276 */     this.selectPaintButton.setEnabled(enabled);
/*     */   }
/*     */   
/*     */   public void setTitleProperties(JFreeChart chart) {
/* 286 */     if (this.showTitle) {
/* 287 */       TextTitle title = chart.getTitle();
/* 288 */       if (title == null) {
/* 289 */         title = new TextTitle();
/* 290 */         chart.setTitle(title);
/*     */       } 
/* 292 */       title.setText(getTitleText());
/* 293 */       title.setFont(getTitleFont());
/* 294 */       title.setPaint(getTitlePaint());
/*     */     } else {
/* 297 */       chart.setTitle((TextTitle)null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\editor\DefaultTitleEditor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */