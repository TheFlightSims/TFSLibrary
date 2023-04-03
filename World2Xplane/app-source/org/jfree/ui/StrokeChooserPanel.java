/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class StrokeChooserPanel extends JPanel {
/*     */   private JComboBox selector;
/*     */   
/*     */   public StrokeChooserPanel(StrokeSample current, StrokeSample[] available) {
/*  74 */     setLayout(new BorderLayout());
/*  75 */     this.selector = new JComboBox(available);
/*  76 */     this.selector.setSelectedItem(current);
/*  77 */     this.selector.setRenderer(new StrokeSample(new BasicStroke(1.0F)));
/*  78 */     add(this.selector);
/*  80 */     this.selector.addActionListener(new ActionListener(this) {
/*     */           private final StrokeChooserPanel this$0;
/*     */           
/*     */           public void actionPerformed(ActionEvent evt) {
/*  82 */             this.this$0.getSelector().transferFocus();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   protected final JComboBox getSelector() {
/*  95 */     return this.selector;
/*     */   }
/*     */   
/*     */   public Stroke getSelectedStroke() {
/* 104 */     StrokeSample sample = (StrokeSample)this.selector.getSelectedItem();
/* 105 */     return sample.getStroke();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\StrokeChooserPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */