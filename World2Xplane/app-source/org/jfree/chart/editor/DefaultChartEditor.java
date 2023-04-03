/*     */ package org.jfree.chart.editor;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
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
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.title.TextTitle;
/*     */ import org.jfree.chart.title.Title;
/*     */ import org.jfree.layout.LCBLayout;
/*     */ import org.jfree.ui.PaintSample;
/*     */ 
/*     */ class DefaultChartEditor extends JPanel implements ActionListener, ChartEditor {
/*     */   private DefaultTitleEditor titleEditor;
/*     */   
/*     */   private DefaultPlotEditor plotEditor;
/*     */   
/*     */   private JCheckBox antialias;
/*     */   
/*     */   private PaintSample background;
/*     */   
/*  90 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");
/*     */   
/*     */   public DefaultChartEditor(JFreeChart chart) {
/* 100 */     setLayout(new BorderLayout());
/* 102 */     JPanel other = new JPanel(new BorderLayout());
/* 103 */     other.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 105 */     JPanel general = new JPanel(new BorderLayout());
/* 106 */     general.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), localizationResources.getString("General")));
/* 110 */     JPanel interior = new JPanel((LayoutManager)new LCBLayout(6));
/* 111 */     interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/* 113 */     this.antialias = new JCheckBox(localizationResources.getString("Draw_anti-aliased"));
/* 115 */     this.antialias.setSelected(chart.getAntiAlias());
/* 116 */     interior.add(this.antialias);
/* 117 */     interior.add(new JLabel(""));
/* 118 */     interior.add(new JLabel(""));
/* 119 */     interior.add(new JLabel(localizationResources.getString("Background_paint")));
/* 121 */     this.background = new PaintSample(chart.getBackgroundPaint());
/* 122 */     interior.add((Component)this.background);
/* 123 */     JButton button = new JButton(localizationResources.getString("Select..."));
/* 125 */     button.setActionCommand("BackgroundPaint");
/* 126 */     button.addActionListener(this);
/* 127 */     interior.add(button);
/* 129 */     interior.add(new JLabel(localizationResources.getString("Series_Paint")));
/* 131 */     JTextField info = new JTextField(localizationResources.getString("No_editor_implemented"));
/* 133 */     info.setEnabled(false);
/* 134 */     interior.add(info);
/* 135 */     button = new JButton(localizationResources.getString("Edit..."));
/* 136 */     button.setEnabled(false);
/* 137 */     interior.add(button);
/* 139 */     interior.add(new JLabel(localizationResources.getString("Series_Stroke")));
/* 141 */     info = new JTextField(localizationResources.getString("No_editor_implemented"));
/* 143 */     info.setEnabled(false);
/* 144 */     interior.add(info);
/* 145 */     button = new JButton(localizationResources.getString("Edit..."));
/* 146 */     button.setEnabled(false);
/* 147 */     interior.add(button);
/* 149 */     interior.add(new JLabel(localizationResources.getString("Series_Outline_Paint")));
/* 151 */     info = new JTextField(localizationResources.getString("No_editor_implemented"));
/* 153 */     info.setEnabled(false);
/* 154 */     interior.add(info);
/* 155 */     button = new JButton(localizationResources.getString("Edit..."));
/* 156 */     button.setEnabled(false);
/* 157 */     interior.add(button);
/* 159 */     interior.add(new JLabel(localizationResources.getString("Series_Outline_Stroke")));
/* 161 */     info = new JTextField(localizationResources.getString("No_editor_implemented"));
/* 163 */     info.setEnabled(false);
/* 164 */     interior.add(info);
/* 165 */     button = new JButton(localizationResources.getString("Edit..."));
/* 166 */     button.setEnabled(false);
/* 167 */     interior.add(button);
/* 169 */     general.add(interior, "North");
/* 170 */     other.add(general, "North");
/* 172 */     JPanel parts = new JPanel(new BorderLayout());
/* 174 */     TextTitle textTitle = chart.getTitle();
/* 175 */     Plot plot = chart.getPlot();
/* 177 */     JTabbedPane tabs = new JTabbedPane();
/* 179 */     this.titleEditor = new DefaultTitleEditor((Title)textTitle);
/* 180 */     this.titleEditor.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 181 */     tabs.addTab(localizationResources.getString("Title"), this.titleEditor);
/* 183 */     this.plotEditor = new DefaultPlotEditor(plot);
/* 184 */     this.plotEditor.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 185 */     tabs.addTab(localizationResources.getString("Plot"), this.plotEditor);
/* 187 */     tabs.add(localizationResources.getString("Other"), other);
/* 188 */     parts.add(tabs, "North");
/* 189 */     add(parts);
/*     */   }
/*     */   
/*     */   public DefaultTitleEditor getTitleEditor() {
/* 198 */     return this.titleEditor;
/*     */   }
/*     */   
/*     */   public DefaultPlotEditor getPlotEditor() {
/* 207 */     return this.plotEditor;
/*     */   }
/*     */   
/*     */   public boolean getAntiAlias() {
/* 216 */     return this.antialias.isSelected();
/*     */   }
/*     */   
/*     */   public Paint getBackgroundPaint() {
/* 225 */     return this.background.getPaint();
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent event) {
/* 234 */     String command = event.getActionCommand();
/* 235 */     if (command.equals("BackgroundPaint"))
/* 236 */       attemptModifyBackgroundPaint(); 
/*     */   }
/*     */   
/*     */   private void attemptModifyBackgroundPaint() {
/* 247 */     Color c = JColorChooser.showDialog(this, localizationResources.getString("Background_Color"), Color.blue);
/* 249 */     if (c != null)
/* 250 */       this.background.setPaint(c); 
/*     */   }
/*     */   
/*     */   public void updateChart(JFreeChart chart) {
/* 262 */     this.titleEditor.setTitleProperties(chart);
/* 263 */     this.plotEditor.updatePlotProperties(chart.getPlot());
/* 265 */     chart.setAntiAlias(getAntiAlias());
/* 266 */     chart.setBackgroundPaint(getBackgroundPaint());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\editor\DefaultChartEditor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */