import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private  static final ImageIcon ICON_X = new ImageIcon(Window.class.getResource("X.png"));
    private  static final ImageIcon ICON_O = new ImageIcon(Window.class.getResource("O.png"));
    private  static final ImageIcon ICON_DEFAULT = new ImageIcon(Window.class.getResource("def.png"));

    private static int firstMove = 1;
    private static int coordinateX = 0;
    private static int coordinateY = 0;

    private final JButton[][] map = new JButton[3][3];
    private static int clickCount = 0;

    private void initMap(JPanel panel) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = new JButton();
                JButton btn = map[i][j];
                btn.setIcon(ICON_DEFAULT);
                btn.setDisabledIcon(ICON_DEFAULT);
                int finalI = i;
                int finalJ = j;
                btn.addActionListener(a -> {
                    btn.setIcon(ICON_X);
                    btn.setDisabledIcon(ICON_X);
                    btn.setEnabled(true);
                    //clickCount++;
                    if (isWin()){
                        newJframe("YOU");
                        return;
                    }
                    if (isDraw()){
                        newJframe("Ничья");
                        return;
                    }
                    pcMove();
                    if (isWin()){
                        newJframe("PC");
                        return;
                    }
                    if (isDraw()){
                        newJframe("Ничья");
                    }
                });
                panel.add(btn);
            }
        }
    }
    public void pcMove(){
        int cX = map.length - 1;
        int cO = map.length - 1;
        int countXI = 0;
        int countXJ = 0;
        int countDiagonalOneX = 0;
        int countDiagonalTwoX = 0;
        int countOI = 0;
        int countOJ = 0;
        int countDiagonalOneO = 0;
        int countDiagonalTwoO = 0;
        boolean flag = true;
        // win strategy
        label1:
        for (int i = 0; i < map.length; i++) {
            if (map[1][1].getIcon() == ICON_DEFAULT) {  // чисто
                map[1][1].setIcon(ICON_O);              // мастерски
                map[1][1].setDisabledIcon(ICON_O);      // добавил
                map[1][1].setEnabled(false);            // добавил
                flag = false;                           // этот
                break label1;                           // кусок кода
            }
            countOI = 0;
            countOJ = 0;
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].getIcon() == ICON_O) {
                    countOI++;
                }
                if (map[j][i].getIcon() == ICON_O) {
                    countOJ++;
                }
                if (i == j) {
                    if (map[i][j].getIcon() == ICON_O)
                        countDiagonalOneO++;
                    if (map[i][cO--].getIcon() == ICON_O)
                        countDiagonalTwoO++;
                }
                if (countOI == map.length - 1) {
                    for (int g = 0; g < map.length; g++) {
                        if (map[i][g].getIcon() == ICON_DEFAULT) {
                            map[i][g].setIcon(ICON_O);
                            map[i][g].setDisabledIcon(ICON_O);
                            map[i][g].setEnabled(false);
                            flag = false;
                            break label1;
                        }
                    }
                } else if (countOJ == map.length - 1) {
                    for (int l = 0; l < map.length; l++)
                        if (map[l][i].getIcon() == ICON_DEFAULT) {
                            map[l][i].setIcon(ICON_O);
                            map[l][i].setDisabledIcon(ICON_O);
                            map[l][i].setEnabled(false);
                            flag = false;
                            break label1;
                        }
                } else if (countDiagonalOneO == map.length - 1) {
                    for (int m = 0, n = 0; m < map.length; m++, n++)
                        if (map[m][n].getIcon() == ICON_DEFAULT) {
                            map[m][n].setIcon(ICON_O);
                            map[m][n].setDisabledIcon(ICON_O);
                            map[m][n].setEnabled(false);
                            flag = false;
                            break label1;
                        }
                } else if (countDiagonalTwoO == map.length - 1) {
                    for (int q = 0, p = map.length - 1; q < map.length; q++, p--)
                        if (map[q][p].getIcon() == ICON_DEFAULT) {
                            map[q][p].setIcon(ICON_O);
                            map[q][p].setDisabledIcon(ICON_O);
                            map[q][p].setEnabled(false);
                            flag = false;
                            break label1;
                        }
                }
            }
        } // end of win strategy
        // warning detection
        if (flag) {
            int f = 0;
            if (map.length == 3)
                f = 1;
            else f = map.length-3;
            label2:
            for (int i = 0; i < map.length; i++) {
                countXI = 0;
                countXJ = 0;
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j].getIcon() == ICON_X)
                        countXI++;
                    if (map[j][i].getIcon() == ICON_X)
                        countXJ++;
                    if (i == j) {
                        if (map[i][j].getIcon() == ICON_X)
                            countDiagonalOneX++;
                        if (map[i][cX--].getIcon() == ICON_X)
                            countDiagonalTwoX++;
                    }
                    if (countXI == map.length - f) {
                        for (int k = 0; k < map.length; k++)
                            if (map[i][k].getIcon() == ICON_DEFAULT) {
                                map[i][k].setIcon(ICON_O);
                                map[i][k].setDisabledIcon(ICON_O);
                                map[i][k].setEnabled(false);
                                coordinateX = i;
                                coordinateY = k;
                                flag = false;
                                break label2;
                            }
                    } else if (countXJ == map.length - f) {
                        for (int l = 0; l < map.length; l++)
                            if (map[l][i].getIcon() == ICON_DEFAULT) {
                                map[l][i].setIcon(ICON_O);
                                map[l][i].setDisabledIcon(ICON_O);
                                map[l][i].setEnabled(false);
                                coordinateX = l;
                                coordinateY = i;
                                flag = false;
                                break label2;
                            }
                    } else if (countDiagonalOneX == map.length - f) {
                        for (int m = 0, n = 0; m < map.length; m++, n++)
                            if (map[m][n].getIcon() == ICON_DEFAULT) {
                                map[m][n].setIcon(ICON_O);
                                map[m][n].setDisabledIcon(ICON_O);
                                map[m][n].setEnabled(false);
                                coordinateX = m;
                                coordinateY = n;
                                flag = false;
                                break label2;
                            }
                    } else if (countDiagonalTwoX == map.length - f) {
                        for (int q = 0, p = map.length - 1; q < map.length; q++, p--)
                            if (map[q][p].getIcon() == ICON_DEFAULT) {
                                map[q][p].setIcon(ICON_O);
                                map[q][p].setDisabledIcon(ICON_O);
                                map[q][p].setEnabled(false);
                                coordinateX = q;
                                coordinateY = p;
                                flag = false;
                                break label2;
                            }
                    }
                } // end of columns
            }// end of rows
        }
        // draw strategy
        if(flag){
            int detectXI = 0;
            int detectXJ = 0;
            for (int i = 0; i < map.length; i++){
                if(map[coordinateX][i].getIcon() == ICON_X){
                    detectXI++;
                }
                if (map[i][coordinateY].getIcon() == ICON_X){
                    detectXJ++;
                }
                if (detectXI == 0){
                    if (map[coordinateX][i].getIcon() == ICON_DEFAULT) {
                        map[coordinateX][i].setIcon(ICON_O);
                        map[coordinateX][i].setDisabledIcon(ICON_O);
                        map[coordinateX][i].setEnabled(false);
                        flag = false;
                        break;
                    }
                }
                if (detectXJ == 0){
                    if (map[i][coordinateY].getIcon() == ICON_DEFAULT){
                        map[i][coordinateY].setIcon(ICON_O);
                        map[i][coordinateY].setDisabledIcon(ICON_O);
                        map[i][coordinateY].setEnabled(false);
                        flag = false;
                        break;
                    }
                }
            }
        }
        // random move
        while (flag) {
            coordinateX = (int) (Math.random() * (map.length));
            coordinateY = (int) (Math.random() * (map.length));
            if (map[coordinateX][coordinateY].getIcon() == ICON_DEFAULT) {
                map[coordinateX][coordinateY].setIcon(ICON_O);
                map[coordinateX][coordinateY].setDisabledIcon(ICON_O);
                map[coordinateX][coordinateY].setEnabled(false);
                firstMove++;
                flag = false;
            }
        }

    }
    public Window() throws HeadlessException {
        setSize(1024, 1024);
        // setLocation(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // ctrl + alt + v
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        System.out.println(toolkit.getScreenSize());
//        Dimension size = toolkit.getScreenSize();
//        setLocation(size.width / 2 - 150, size.height / 2 - 150);
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("1");
        menu.add(new JMenuItem("1"));
        menu.add(new JMenuItem("2"));
        menu.add(new JMenuItem("3"));
        bar.add(menu);
        bar.add(new JMenu("2"));
        bar.add(new JMenu("3"));

        JPanel panel = new JPanel(new GridLayout(3,3));
        // panel.add(bar);
        initMap(panel);
        add(panel);
        //setMenuBar(bar);
        setResizable(false);
        setVisible(true);
    }
    public void newJframe(String winner){
        JFrame frame;
        if (winner.equals("Ничья")){
             frame = new JFrame(winner+"!!!");
        }else {
             frame = new JFrame(winner + " WIN!!!");
        }
            frame.setSize(1024, 1024);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            JPanel p = new JPanel();
            JButton close = new JButton("CLOSE");
            close.addActionListener(e -> {
                dispose();
                frame.dispose();
            });
            JButton newGame = new JButton("NEW GAME");
            newGame.addActionListener(e -> {
                clickCount = 0;
                new Window();
                dispose();
                frame.dispose();
            });
            p.add(close);
            p.add(newGame);
            frame.add(p);
            frame.setVisible(true);
    }
    public boolean isWin(){
        int cX = map.length-1;
        int cY = map.length-1;
        int countXI = 0;
        int countYI = 0;
        int countXJ = 0;
        int countYJ = 0;
        int countDiagonalOneX = 0;
        int countDiagonalOneY = 0;
        int countDiagonalTwoX = 0;
        int countDiagonalTwoY = 0;
        for (int i = 0; i < map.length; i++) {
            countXI = 0;
            countYI = 0;
            countXJ = 0;
            countYJ = 0;
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].getIcon() == ICON_X)
                    countXI++;
                if (map[j][i].getIcon() == ICON_X)
                    countXJ++;
                if (map[i][j].getIcon() == ICON_O)
                    countYI++;
                if (map[j][i].getIcon() == ICON_O)
                    countYJ++;
                if (i == j) {
                    if (map[i][j].getIcon() == ICON_X)
                        countDiagonalOneX++;
                    if (map[i][j].getIcon() == ICON_O)
                        countDiagonalOneY++;
                    if (map[i][cX--].getIcon() == ICON_X)
                        countDiagonalTwoX++;
                    if (map[i][cY--].getIcon() == ICON_O)
                        countDiagonalTwoY++;
                }
                int u = 0;
                if (map.length == 3)
                    u = 0;
                else u = 1;
                if (countXI == map.length - u || countXJ == map.length - u) {
                    return true;
                } else if (countYI == map.length - u || countYJ == map.length - u) {
                    return true;
                } else if (countDiagonalOneX == map.length - u || countDiagonalTwoX == map.length - u) {
                    return true;
                } else if (countDiagonalOneY == map.length - u || countDiagonalTwoY == map.length - u) {
                    return true;
                }

            }
        }
        return false;
    }
    public boolean isDraw(){
        int count = 0;
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map.length; j++)
                if (map[i][j].getIcon() == ICON_DEFAULT)
                    count++;
        return count == 0;
    }

    public static void main(String[] args) {
        new Window();
    }
}
