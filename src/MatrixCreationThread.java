
import javax.swing.JTextField;

public class MatrixCreationThread extends Thread {

    @Override
    public void run() {
        int rows = 0;
        int columns = 0;
        boolean doNotContinue = false;
        try {
            rows = Integer.parseInt(GraphicUserInterface.rowsInMatrix.getText());
            columns = Integer.parseInt(GraphicUserInterface.columnsInMatrix.getText());
        } catch (NumberFormatException ex) {
            doNotContinue = true;
        }
        if (!doNotContinue) {
            StringBuilder sb = new StringBuilder();
            sb.append("\\begin{array}{");
            if (GraphicUserInterface.jCheckBox1.isSelected()) {
                sb.append(GraphicUserInterface.jTextField1.getText());
            } else {
                //column separators
                boolean csep = false;
                if (GraphicUserInterface.jCheckBox3.isSelected()) {
                    csep = true;
                }
                //alignment: l c r
                if (GraphicUserInterface.jRadioButton1.isSelected()) {
                    for (int i = 0; i < columns; i++) {
                        sb.append("l");
                        if (csep && columns - 1 != i) {
                            sb.append("|");
                        }
                    }
                } else if (GraphicUserInterface.jRadioButton3.isSelected()) {
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

            GraphicUserInterface.matrixBuilder.getContentPane().removeAll();


            JTextField[][] matrixFields = new JTextField[rows][columns];
            int xBorder = 10;
            int xDelim = 25;
            int x = 10;
            int yBorder = 10;
            int yDelim = 25;
            int y = 10;
            int w = 100;
            int h = 25;

            GraphicUserInterface.confirmMatrix.setSize(w, h);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrixFields[i][j] = new JTextField();
                    matrixFields[i][j].setBounds(x, y, w, h);
                    x += (w + xDelim);
                    matrixFields[i][j].setEnabled(true);
                    matrixFields[i][j].setEditable(true);
                    matrixFields[i][j].setVisible(true);
                    GraphicUserInterface.matrixBuilder.add(matrixFields[i][j]);
                }
                y += (h + yDelim);
                if (i != rows - 1) {
                    x = xBorder;
                } else {
                    GraphicUserInterface.matrixBuilder.add(GraphicUserInterface.confirmMatrix);
                    GraphicUserInterface.confirmMatrix.setLocation(x - (w + xDelim), y);
                    x += xBorder;
                    y += (h + yDelim + yBorder);
                }
            }

            GraphicUserInterface.matrixBuilder.setSize(x, y);
            GraphicUserInterface.matrixBuilder.setResizable(false);
            GraphicUserInterface.matrixBuilder.invalidate();
            GraphicUserInterface.matrixBuilder.revalidate();
            GraphicUserInterface.matrixBuilder.repaint();
            if (GraphicUserInterface.matrixBuilder.getWidth() <= GraphicUserInterface.screenSize.width && GraphicUserInterface.matrixBuilder.getHeight() <= GraphicUserInterface.screenSize.height) {
                GraphicUserInterface.matrixBuilder.setLocation((GraphicUserInterface.screenSize.width - GraphicUserInterface.matrixBuilder.getWidth()) / 2, (GraphicUserInterface.screenSize.height - GraphicUserInterface.matrixBuilder.getHeight()) / 2);
            }
            GraphicUserInterface.matrixBuilder.setVisible(true);

            while (GraphicUserInterface.matrixBuilder.isVisible()) {
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
                    if (GraphicUserInterface.jCheckBox2.isSelected()) {
                        sb.append("\\hline");
                    }
                    sb.append("\n");
                }
            }

            sb.append("\n\\end{array}");
            GraphicUserInterface.insertChars(sb.toString());
            GraphicUserInterface.matrixPropertiesPrompt.setVisible(false);
            GraphicUserInterface.rowsInMatrix.setText("");
            GraphicUserInterface.columnsInMatrix.setText("");
        }
    }
}
