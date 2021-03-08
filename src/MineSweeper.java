import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class describing the game
 * @author Dmitriy Stepanov
 */
public class MineSweeper extends JFrame {
    private final Game game;
    private JPanel panel;
    private JLabel label;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    /**
     * Constructor - creating a new game
     * @see MineSweeper#MineSweeper()
     */
    private MineSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();

        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);

        setImages();
        initPanel();

        setTitle("MineSweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
    }


    private void initPanel() {
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(Coord coord: Ranges.getAllCoords())
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE,
                            coord.y * IMAGE_SIZE, this);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if(e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if(e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if(e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private String getMessage() {
        switch(game.getState()){
            case PLAYED: return "Think twice!";
            case BOMBED:
                String lose = "/lose.png";
                String losename = "Defeat";
                String reslose = "YOU LOSE!";
                message(lose, losename, reslose);
                break;
            case WINNER:
                String win = "/win.png";
                String winname = "Victory";
                String reswin = "CONGRATULATIONS!";
                message(win, winname, reswin);
                break;
            default: return "Welcome!";
        }
        return null;
    }

    private void message(String filename, String title, String message) {
        ImageIcon icon = new ImageIcon((getClass().getResource(filename)));
        JOptionPane.showMessageDialog(null, message, title,
                JOptionPane.INFORMATION_MESSAGE, icon);
    }

    private void setImages() {
        for(Box box: Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage(String name) {
        String filename = "/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    public static void main(String[] args) {
        new MineSweeper();
    }
}