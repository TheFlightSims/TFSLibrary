/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class Spinner extends JPanel implements MouseListener {
/*     */   private int value;
/*     */   
/*     */   private JTextField textField;
/*     */   
/*     */   private JPanel buttonPanel;
/*     */   
/*     */   private ArrowPanel upButton;
/*     */   
/*     */   private ArrowPanel downButton;
/*     */   
/*     */   public Spinner(int value) {
/*  82 */     super(new BorderLayout());
/*  83 */     this.value = value;
/*  84 */     this.textField = new JTextField(Integer.toString(this.value));
/*  85 */     this.textField.setHorizontalAlignment(4);
/*  86 */     add(this.textField);
/*  87 */     this.buttonPanel = new JPanel(new GridLayout(2, 1, 0, 1));
/*  88 */     this.upButton = new ArrowPanel(0);
/*  89 */     this.upButton.addMouseListener(this);
/*  90 */     this.downButton = new ArrowPanel(1);
/*  91 */     this.downButton.addMouseListener(this);
/*  92 */     this.buttonPanel.add(this.upButton);
/*  93 */     this.buttonPanel.add(this.downButton);
/*  94 */     add(this.buttonPanel, "East");
/*     */   }
/*     */   
/*     */   public int getValue() {
/* 103 */     return this.value;
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {
/* 112 */     if (e.getSource() == this.upButton) {
/* 113 */       this.value++;
/* 114 */       this.textField.setText(Integer.toString(this.value));
/* 115 */       firePropertyChange("value", this.value - 1, this.value);
/* 117 */     } else if (e.getSource() == this.downButton) {
/* 118 */       this.value--;
/* 119 */       this.textField.setText(Integer.toString(this.value));
/* 120 */       firePropertyChange("value", this.value + 1, this.value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\Spinner.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */