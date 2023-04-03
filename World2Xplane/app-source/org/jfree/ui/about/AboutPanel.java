/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Image;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.jfree.ui.RefineryUtilities;
/*     */ 
/*     */ public class AboutPanel extends JPanel {
/*     */   public AboutPanel(String application, String version, String copyright, String info) {
/*  77 */     this(application, version, copyright, info, (Image)null);
/*     */   }
/*     */   
/*     */   public AboutPanel(String application, String version, String copyright, String info, Image logo) {
/*  93 */     setLayout(new BorderLayout());
/*  95 */     JPanel textPanel = new JPanel(new GridLayout(4, 1, 0, 4));
/*  97 */     JPanel appPanel = new JPanel();
/*  98 */     Font f1 = new Font("Dialog", 1, 14);
/*  99 */     JLabel appLabel = RefineryUtilities.createJLabel(application, f1, Color.black);
/* 100 */     appLabel.setHorizontalTextPosition(0);
/* 101 */     appPanel.add(appLabel);
/* 103 */     JPanel verPanel = new JPanel();
/* 104 */     Font f2 = new Font("Dialog", 0, 12);
/* 105 */     JLabel verLabel = RefineryUtilities.createJLabel(version, f2, Color.black);
/* 106 */     verLabel.setHorizontalTextPosition(0);
/* 107 */     verPanel.add(verLabel);
/* 109 */     JPanel copyrightPanel = new JPanel();
/* 110 */     JLabel copyrightLabel = RefineryUtilities.createJLabel(copyright, f2, Color.black);
/* 111 */     copyrightLabel.setHorizontalTextPosition(0);
/* 112 */     copyrightPanel.add(copyrightLabel);
/* 114 */     JPanel infoPanel = new JPanel();
/* 115 */     JLabel infoLabel = RefineryUtilities.createJLabel(info, f2, Color.black);
/* 116 */     infoLabel.setHorizontalTextPosition(0);
/* 117 */     infoPanel.add(infoLabel);
/* 119 */     textPanel.add(appPanel);
/* 120 */     textPanel.add(verPanel);
/* 121 */     textPanel.add(copyrightPanel);
/* 122 */     textPanel.add(infoPanel);
/* 124 */     add(textPanel);
/* 126 */     if (logo != null) {
/* 127 */       JPanel imagePanel = new JPanel(new BorderLayout());
/* 128 */       imagePanel.add(new JLabel(new ImageIcon(logo)));
/* 129 */       imagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
/* 130 */       JPanel imageContainer = new JPanel(new BorderLayout());
/* 131 */       imageContainer.add(imagePanel, "North");
/* 132 */       add(imageContainer, "West");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\AboutPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */