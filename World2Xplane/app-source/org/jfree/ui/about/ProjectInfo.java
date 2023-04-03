/*     */ package org.jfree.ui.about;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.base.BootableProjectInfo;
/*     */ import org.jfree.base.Library;
/*     */ 
/*     */ public class ProjectInfo extends BootableProjectInfo {
/*     */   private Image logo;
/*     */   
/*     */   private String licenceText;
/*     */   
/*     */   private List contributors;
/*     */   
/*     */   public ProjectInfo() {}
/*     */   
/*     */   public ProjectInfo(String name, String version, String info, Image logo, String copyright, String licenceName, String licenceText) {
/*  95 */     super(name, version, info, copyright, licenceName);
/*  96 */     this.logo = logo;
/*  97 */     this.licenceText = licenceText;
/*     */   }
/*     */   
/*     */   public Image getLogo() {
/* 107 */     return this.logo;
/*     */   }
/*     */   
/*     */   public void setLogo(Image logo) {
/* 116 */     this.logo = logo;
/*     */   }
/*     */   
/*     */   public String getLicenceText() {
/* 125 */     return this.licenceText;
/*     */   }
/*     */   
/*     */   public void setLicenceText(String licenceText) {
/* 134 */     this.licenceText = licenceText;
/*     */   }
/*     */   
/*     */   public List getContributors() {
/* 143 */     return this.contributors;
/*     */   }
/*     */   
/*     */   public void setContributors(List contributors) {
/* 152 */     this.contributors = contributors;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 162 */     StringBuffer result = new StringBuffer();
/* 163 */     result.append(getName());
/* 164 */     result.append(" version ");
/* 165 */     result.append(getVersion());
/* 166 */     result.append(".\n");
/* 167 */     result.append(getCopyright());
/* 168 */     result.append(".\n");
/* 169 */     result.append("\n");
/* 170 */     result.append("For terms of use, see the licence below.\n");
/* 171 */     result.append("\n");
/* 172 */     result.append("FURTHER INFORMATION:");
/* 173 */     result.append(getInfo());
/* 174 */     result.append("\n");
/* 175 */     result.append("CONTRIBUTORS:");
/* 176 */     if (this.contributors != null) {
/* 177 */       Iterator iterator = this.contributors.iterator();
/* 178 */       while (iterator.hasNext()) {
/* 179 */         Contributor contributor = iterator.next();
/* 180 */         result.append(contributor.getName());
/* 181 */         result.append(" (");
/* 182 */         result.append(contributor.getEmail());
/* 183 */         result.append(").");
/*     */       } 
/*     */     } else {
/* 187 */       result.append("None");
/*     */     } 
/* 190 */     result.append("\n");
/* 191 */     result.append("OTHER LIBRARIES USED BY ");
/* 192 */     result.append(getName());
/* 193 */     result.append(":");
/* 194 */     Library[] libraries = getLibraries();
/* 195 */     if (libraries.length != 0) {
/* 196 */       for (int i = 0; i < libraries.length; i++) {
/* 197 */         Library lib = libraries[i];
/* 198 */         result.append(lib.getName());
/* 199 */         result.append(" ");
/* 200 */         result.append(lib.getVersion());
/* 201 */         result.append(" (");
/* 202 */         result.append(lib.getInfo());
/* 203 */         result.append(").");
/*     */       } 
/*     */     } else {
/* 207 */       result.append("None");
/*     */     } 
/* 209 */     result.append("\n");
/* 210 */     result.append(getName());
/* 211 */     result.append(" LICENCE TERMS:");
/* 212 */     result.append("\n");
/* 213 */     result.append(getLicenceText());
/* 215 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\ProjectInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */