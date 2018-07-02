import javax.swing.*;
import java.awt.*;


/**
 * @author michalh
 * 
 * Panel for presenting the shapes.
 *
 */
public class ShapesDisplayPanel extends JPanel {
    private boolean displayShape;
    public static final int MAX_SHAPE_SIZE = 250;

    public boolean isDisplayShape() {
        return displayShape;
    }

    public void setDisplayShape(boolean displayShape) {
        this.displayShape = displayShape;
    }

    public ShapesDisplayPanel() {
        setBorder(BorderFactory.createEtchedBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(displayShape){
            Shape shape = new Shape(new Dimension(MAX_SHAPE_SIZE,MAX_SHAPE_SIZE));
            shape.draw(g);
        }else{
            g.clearRect(0,0,500,400);
        }
    }
}
