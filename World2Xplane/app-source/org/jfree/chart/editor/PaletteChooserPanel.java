/*    */ package org.jfree.chart.editor;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import javax.swing.JComboBox;
/*    */ import javax.swing.JPanel;
/*    */ import org.jfree.chart.plot.ColorPalette;
/*    */ import org.jfree.chart.plot.RainbowPalette;
/*    */ 
/*    */ class PaletteChooserPanel extends JPanel {
/*    */   private JComboBox selector;
/*    */   
/*    */   public PaletteChooserPanel(PaletteSample current, PaletteSample[] available) {
/* 71 */     setLayout(new BorderLayout());
/* 72 */     this.selector = new JComboBox(available);
/* 73 */     this.selector.setSelectedItem(current);
/* 74 */     this.selector.setRenderer(new PaletteSample((ColorPalette)new RainbowPalette()));
/* 75 */     add(this.selector);
/*    */   }
/*    */   
/*    */   public ColorPalette getSelectedPalette() {
/* 84 */     PaletteSample sample = (PaletteSample)this.selector.getSelectedItem();
/* 85 */     return sample.getPalette();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\editor\PaletteChooserPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */