
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class NewJPanel extends javax.swing.JPanel implements ClipboardOwner {

    public static String math = "";
    BufferedImage b = null;
    Toolkit kit = Toolkit.getDefaultToolkit();
    final Clipboard clipboard = kit.getSystemClipboard();
    File defaultPath = null;

    /**
     * Creates new form NewJPanel
     */
    public NewJPanel() {
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (!math.equals("")) {
            try {
                TeXFormula fomule = new TeXFormula(math);
                TeXIcon ti = fomule.createTeXIcon(TeXConstants.STYLE_DISPLAY, sizeSlider.getValue());
                Insets ins = new Insets(0, 0, 0, 10); //this is to prevent some letters (like f) from being cut at edges
                ti.setInsets(ins);
                b = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);

                g2.drawImage(b, 10, 50, null);
            } catch (Exception ex) {
                NewJFrame.msgBar.setText(ex.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        renderTex = new javax.swing.JButton();
        exportGraphics = new javax.swing.JButton();
        gfxToClipboard = new javax.swing.JButton();
        sizeSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));

        renderTex.setText("Render");
        renderTex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renderTexActionPerformed(evt);
            }
        });

        exportGraphics.setText("Export");
        exportGraphics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportGraphicsActionPerformed(evt);
            }
        });

        gfxToClipboard.setText("To clipboard");
        gfxToClipboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gfxToClipboardActionPerformed(evt);
            }
        });

        sizeSlider.setBackground(new java.awt.Color(255, 255, 255));
        sizeSlider.setMinimum(1);
        sizeSlider.setValue(40);
        sizeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sizeSliderStateChanged(evt);
            }
        });

        jLabel1.setText("Size: 40");

        jCheckBox1.setText("Set export path");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(renderTex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jCheckBox1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exportGraphics)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gfxToClipboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sizeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(134, 134, 134))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(renderTex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportGraphics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gfxToClipboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sizeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(0, 248, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void renderTexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renderTexActionPerformed
        math = NewJFrame.textPreview.getText();
        repaint();
    }//GEN-LAST:event_renderTexActionPerformed

    private void exportGraphicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportGraphicsActionPerformed
        File f = null;
        JFileChooser chooser = new JFileChooser();
        if (b != null) {
           if (jCheckBox1.isSelected()) {
                if (defaultPath == null) {
                 
                
               
          
                    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int returnVal = chooser.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        f = chooser.getSelectedFile();
                        defaultPath = f;
                    } else {
                        defaultPath = null;
                        NewJFrame.msgBar.setText("Setting export path aborted by user.!");
                        jCheckBox1.setSelected(false);
                    }
                    try {
                        exportImage(defaultPath);
                    } catch (IOException ex) {
                        Logger.getLogger(NewJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    try {
                        exportImage(defaultPath);
                    } catch (IOException ex) {
                        Logger.getLogger(NewJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           }
           else {
                try {
                    defaultPath = null;
                    
                    exportImage(defaultPath);
                } catch (IOException ex) {
                    Logger.getLogger(NewJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        }
        else {
            NewJFrame.msgBar.setText("Nothing to export!");
        }
    }//GEN-LAST:event_exportGraphicsActionPerformed

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("Clipboard contents replaced");
    }

    private void gfxToClipboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gfxToClipboardActionPerformed
        if (b != null) {
            TransferableImage transferableImage = new TransferableImage(b);
            clipboard.setContents(transferableImage, this);
            NewJFrame.msgBar.setText("Graphics exported to clipboard!");
        }
    }//GEN-LAST:event_gfxToClipboardActionPerformed

    private void sizeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sizeSliderStateChanged
        jLabel1.setText(String.format("Size: %d", sizeSlider.getValue()));
    }//GEN-LAST:event_sizeSliderStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportGraphics;
    private javax.swing.JButton gfxToClipboard;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton renderTex;
    private javax.swing.JSlider sizeSlider;
    // End of variables declaration//GEN-END:variables

    private void exportImage(File f) throws IOException {
        
        //Ob prvem exportu vprasa za pot izvoza. To se nastavi kot default path.
        //Mozna razsiritev: Dodaj gumb v orodni vrstici: "Nastavi default export path."
            if (defaultPath == null) {
                f = new File("graphics-0.png");
                }
            else {
                f = new File(defaultPath + "/graphics-0.png");
            }
            for (int i = 0;; i++) {
                if (f.exists()) {
                    if (defaultPath == null) {
                    f = new File("graphics-" + i + ".png");
                    } else f = new File(defaultPath + "/graphics-" + i + ".png");
                } else {
                    break;
                }
            }
            try {
                File outputfile = new File(f.getAbsolutePath());
                ImageIO.write(b, "png", outputfile);
                NewJFrame.msgBar.setText("Graphics exported as " + f.getName() + ".");
            } catch (IOException e) {
                NewJFrame.msgBar.setText(e.getMessage());
            }
          }
}