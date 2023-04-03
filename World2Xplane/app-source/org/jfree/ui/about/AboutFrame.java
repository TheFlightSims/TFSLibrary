/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Image;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ public class AboutFrame extends JFrame {
/*  74 */   public static final Dimension PREFERRED_SIZE = new Dimension(560, 360);
/*     */   
/*  77 */   public static final Border STANDARD_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
/*     */   
/*     */   private ResourceBundle resources;
/*     */   
/*     */   private String application;
/*     */   
/*     */   private String version;
/*     */   
/*     */   private String copyright;
/*     */   
/*     */   private String info;
/*     */   
/*     */   private Image logo;
/*     */   
/*     */   private List contributors;
/*     */   
/*     */   private String licence;
/*     */   
/*     */   private List libraries;
/*     */   
/*     */   public AboutFrame(String title, ProjectInfo project) {
/* 114 */     this(title, project.getName(), "Version " + project.getVersion(), project.getInfo(), project.getLogo(), project.getCopyright(), project.getLicenceText(), project.getContributors(), Arrays.asList(project.getLibraries()));
/*     */   }
/*     */   
/*     */   public AboutFrame(String title, String application, String version, String info, Image logo, String copyright, String licence, List contributors, List libraries) {
/* 146 */     super(title);
/* 148 */     this.application = application;
/* 149 */     this.version = version;
/* 150 */     this.copyright = copyright;
/* 151 */     this.info = info;
/* 152 */     this.logo = logo;
/* 153 */     this.contributors = contributors;
/* 154 */     this.licence = licence;
/* 155 */     this.libraries = libraries;
/* 157 */     String baseName = "org.jfree.ui.about.resources.AboutResources";
/* 158 */     this.resources = ResourceBundle.getBundle("org.jfree.ui.about.resources.AboutResources");
/* 160 */     JPanel content = new JPanel(new BorderLayout());
/* 161 */     content.setBorder(STANDARD_BORDER);
/* 163 */     JTabbedPane tabs = createTabs();
/* 164 */     content.add(tabs);
/* 165 */     setContentPane(content);
/* 167 */     pack();
/*     */   }
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 177 */     return PREFERRED_SIZE;
/*     */   }
/*     */   
/*     */   private JTabbedPane createTabs() {
/* 187 */     JTabbedPane tabs = new JTabbedPane();
/* 189 */     JPanel aboutPanel = createAboutPanel();
/* 190 */     aboutPanel.setBorder(STANDARD_BORDER);
/* 191 */     String aboutTab = this.resources.getString("about-frame.tab.about");
/* 192 */     tabs.add(aboutTab, aboutPanel);
/* 194 */     JPanel systemPanel = new SystemPropertiesPanel();
/* 195 */     systemPanel.setBorder(STANDARD_BORDER);
/* 196 */     String systemTab = this.resources.getString("about-frame.tab.system");
/* 197 */     tabs.add(systemTab, systemPanel);
/* 199 */     return tabs;
/*     */   }
/*     */   
/*     */   private JPanel createAboutPanel() {
/* 211 */     JPanel about = new JPanel(new BorderLayout());
/* 213 */     JPanel details = new AboutPanel(this.application, this.version, this.copyright, this.info, this.logo);
/* 216 */     boolean includetabs = false;
/* 217 */     JTabbedPane tabs = new JTabbedPane();
/* 219 */     if (this.contributors != null) {
/* 220 */       JPanel contributorsPanel = new ContributorsPanel(this.contributors);
/* 221 */       contributorsPanel.setBorder(STANDARD_BORDER);
/* 222 */       String contributorsTab = this.resources.getString("about-frame.tab.contributors");
/* 223 */       tabs.add(contributorsTab, contributorsPanel);
/* 224 */       includetabs = true;
/*     */     } 
/* 227 */     if (this.licence != null) {
/* 228 */       JPanel licencePanel = createLicencePanel();
/* 229 */       licencePanel.setBorder(STANDARD_BORDER);
/* 230 */       String licenceTab = this.resources.getString("about-frame.tab.licence");
/* 231 */       tabs.add(licenceTab, licencePanel);
/* 232 */       includetabs = true;
/*     */     } 
/* 235 */     if (this.libraries != null) {
/* 236 */       JPanel librariesPanel = new LibraryPanel(this.libraries);
/* 237 */       librariesPanel.setBorder(STANDARD_BORDER);
/* 238 */       String librariesTab = this.resources.getString("about-frame.tab.libraries");
/* 239 */       tabs.add(librariesTab, librariesPanel);
/* 240 */       includetabs = true;
/*     */     } 
/* 243 */     about.add(details, "North");
/* 244 */     if (includetabs)
/* 245 */       about.add(tabs); 
/* 248 */     return about;
/*     */   }
/*     */   
/*     */   private JPanel createLicencePanel() {
/* 259 */     JPanel licencePanel = new JPanel(new BorderLayout());
/* 260 */     JTextArea area = new JTextArea(this.licence);
/* 261 */     area.setLineWrap(true);
/* 262 */     area.setWrapStyleWord(true);
/* 263 */     area.setCaretPosition(0);
/* 264 */     area.setEditable(false);
/* 265 */     licencePanel.add(new JScrollPane(area));
/* 266 */     return licencePanel;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\AboutFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */