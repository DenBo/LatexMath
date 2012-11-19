
import javax.swing.JTextField;

public class MatrixCreationThread extends Thread {

    @Override
    public void run() {
        int rows = Integer.parseInt(NewJFrame.rowsInMatrix.getText());
        int columns = Integer.parseInt(NewJFrame.columnsInMatrix.getText());
        StringBuilder sb = new StringBuilder();
        sb.append("\\begin{array}{");
        if (NewJFrame.jCheckBox1.isSelected()) {
            sb.append(NewJFrame.jTextField1.getText());
        } else {
            //column separators
            boolean csep = false;
            if (NewJFrame.jCheckBox3.isSelected()) {
                csep = true;
            }
            //alignment: l c r
            if (NewJFrame.jRadioButton1.isSelected()) {
                for (int i = 0; i < columns; i++) {
                    sb.append("l");
                    if (csep && columns - 1 != i) {
                        sb.append("|");
                    }
                }
            } else if (NewJFrame.jRadioButton3.isSelected()) {
                for (int i = 0; i < columns; i++) {
                    sb.append("r");
                    if (csep && columns - 1 != i) {
                        sb.append("|");
                    }
                }
            } else {
                for (int i = 0; i < columns; i++) {
                    sb.append("c");
                    if (csep && columns - 1 != i) {
                        sb.append("|");
                    }
                }
            }
        }
        sb.append("}\n");

        NewJFrame.matrixBuilder.getContentPane().removeAll();
        
        
        JTextField[][] matrixFields = new JTextField[rows][columns];
        int xBorder = 10;
        int xDelim = 25;
        int x = 10;
        int yBorder = 10;
        int yDelim = 25;
        int y = 10;
        int w = 100;
        int h = 25;

        NewJFrame.confirmMatrix.setSize(w, h);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixFields[i][j] = new JTextField();
                matrixFields[i][j].setBounds(x, y, w, h);
                x += (w + xDelim);
                matrixFields[i][j].setEnabled(true);
                matrixFields[i][j].setEditable(true);
                matrixFields[i][j].setVisible(true);
                NewJFrame.matrixBuilder.add(matrixFields[i][j]);
            }
            y += (h + yDelim);
            if (i != rows - 1) {
                x = xBorder;
            } else {
                NewJFrame.matrixBuilder.add(NewJFrame.confirmMatrix);
                NewJFrame.confirmMatrix.setLocation(x - (w + xDelim), y);
                x += xBorder;
                y += (h + yDelim + yBorder);
            }
        }

        NewJFrame.matrixBuilder.setSize(x, y);
        NewJFrame.matrixBuilder.setResizable(false);
        NewJFrame.matrixBuilder.invalidate();
        NewJFrame.matrixBuilder.revalidate();
        NewJFrame.matrixBuilder.repaint();
        NewJFrame.matrixBuilder.setVisible(true);

        while (NewJFrame.matrixBuilder.isVisible()) {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j == columns - 1) {
                    sb.append(" ").append(matrixFields[i][j].getText());
                } else {
                    sb.append(matrixFields[i][j].getText()).append(" &");
                }
            }
            if (i != rows - 1) {
                sb.append(" \\\\");
                if (NewJFrame.jCheckBox2.isSelected()) {
                    sb.append("\\hline");
                }
                sb.append("\n");
            }
        }

        sb.append("\n\\end{array}");
        NewJFrame.insertChars(sb.toString());
        NewJFrame.matrixPropertiesPrompt.setVisible(false);
        NewJFrame.rowsInMatrix.setText("");
        NewJFrame.columnsInMatrix.setText("");
    }
}
