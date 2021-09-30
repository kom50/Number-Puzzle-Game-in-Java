
import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OnDrag extends MouseAdapter{
//    private final Component component;
    private final Component dragComponent;
    private int x, y;
    // onDragComponent is component that is not actually draw, on this component we add mouseListener and mouseMotionListener
    // dragComponent is a component that dragged actually
    public OnDrag(Component onDragComponent, Component dragComponent){
        this.dragComponent = dragComponent;   // Frame
        // set Listener on component
        onDragComponent.addMouseListener(this);
        onDragComponent.addMouseMotionListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int count = e.getClickCount();  // if mouse double clicked on the component then window is dispose
        if(count == -1) {
            if(dragComponent instanceof Window) // If given component is a type of window component then window is dispose otherwise not
                ((Window) dragComponent).dispose();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        dragComponent.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
    }
}