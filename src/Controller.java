import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.management.timer.TimerMBean;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Controller extends JComponent implements MouseMotionListener, ActionListener
{
    JFrame jf = new JFrame();
    int jFrameWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int jFraneHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    Rectangle2D.Double room = new Rectangle2D.Double(50, 50, jFrameWidth - 150, jFraneHeight - 250);
    Rectangle2D.Double topFrame = new Rectangle2D.Double(room.getX(), room.getY(), room.getX() + room.getWidth(), 25);
    Rectangle2D.Double bottomFrame = new Rectangle2D.Double(room.getX(), room.getY() + room.getHeight(), room.getX() + room.getWidth(), 25);
    Rectangle2D.Double leftFrame = new Rectangle2D.Double(100, 125, 25, jFraneHeight - 475);
    Rectangle2D.Double rightFrame = new Rectangle2D.Double(jFrameWidth - 275, 125, 25, jFraneHeight - 475);
    Rectangle2D.Double screen = new Rectangle2D.Double(125, 125, jFrameWidth - 400, jFraneHeight - 475);
    ArrayList<Ellipse2D.Double> topSensorList = new ArrayList<Ellipse2D.Double>();
    ArrayList<Ellipse2D.Double> bottomSensorList = new ArrayList<Ellipse2D.Double>();
    ArrayList<Ellipse2D.Double> leftSensorList = new ArrayList<Ellipse2D.Double>();
    ArrayList<Ellipse2D.Double> rightSensorList = new ArrayList<Ellipse2D.Double>();
    ArrayList<Ellipse2D.Double> topEmitterList = new ArrayList<Ellipse2D.Double>();
    ArrayList<Ellipse2D.Double> bottomEmitterList = new ArrayList<Ellipse2D.Double>();
    ArrayList<Ellipse2D.Double> leftEmitterList = new ArrayList<Ellipse2D.Double>();
    ArrayList<Ellipse2D.Double> rightEmitterList = new ArrayList<Ellipse2D.Double>();
    ArrayList<Line2D.Double> rayLineList = new ArrayList<Line2D.Double>();
    ArrayList<Emitter> emitterList = new ArrayList<Emitter>();
    ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
    final int SENSOR_SPACING = 50;
    final int EMITTER_SPACING = 100;
    final int EMITTERS = 10;
    final int TOP_BOTTOM_EMITTERS = 10;
    final int LEFT_RIGHT_SIDE_EMITTERS = 10;
    final int TOP_BOTTOM_SENSORS = 50;
    final int LEFT_RIGHT_SIDE_SENSORS = 50;
    final int SENSORS = 50;
    Point2D.Double mousePoint;
    int mouseRectangleX, mouseRectangleY;
    Rectangle2D.Double mouseRectangle;
    Timer ticker = new Timer(20, this);

    public static void main(String[] args)
    {
	SwingUtilities.invokeLater(new Runnable()
	{
	    @Override
	    public void run()
	    {
		new Controller().getGoing();
	    }
	});
    }

    private void getGoing()
    {
	jf.add(this);
	jf.addMouseMotionListener(this);
	jf.setSize(jFrameWidth, jFraneHeight);
	jf.setVisible(true);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ticker.start();
    }

    public void paint(Graphics g)
    {
	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Color.MAGENTA);
	g2.draw(room);
	g2.setColor(Color.black);
	g2.draw(topFrame);
	g2.draw(bottomFrame);
	g2.draw(leftFrame);
	g2.draw(rightFrame);
	g2.setColor(new Color(153, 255, 255));
	g2.fill(screen);
	g2.setColor(Color.black);
	g2.draw(mouseRectangle = new Rectangle2D.Double(mouseRectangleX, mouseRectangleY, 20, 20));
	for (int i = 0; i < TOP_BOTTOM_SENSORS; i++)
	{
	    sensorList.add(new Sensor(140 + (i * SENSOR_SPACING), 109));// top
	    sensorList.add(new Sensor(140 + (i * SENSOR_SPACING), jFraneHeight - 340));// bottom
	    g2.setColor(Color.green);
	    g2.fill(sensorList.get(i).getSensorShape());
	    g2.fill(sensorList.get(i).getSensorShape());
	}
	for (int i = 0; i < TOP_BOTTOM_EMITTERS; i++)
	{
	    emitterList.add(new Emitter(140 + (i * EMITTER_SPACING), 109));// top
	    emitterList.add(new Emitter(140 + (i * EMITTER_SPACING), jFraneHeight - 340));// bottom
	    g2.setColor(Color.red);
	    g2.fill(emitterList.get(i).getEmitterShape());
	    g2.fill(emitterList.get(i).getEmitterShape());
	}
	for (int i = 0; i < LEFT_RIGHT_SIDE_EMITTERS; i++)
	{
	    emitterList.add(new Emitter(110, jFraneHeight - 350 - (i * EMITTER_SPACING)));// left
	    emitterList.add(new Emitter(jFrameWidth - 264, (jFraneHeight - 350) - (i * EMITTER_SPACING)));// right
	    g2.setColor(Color.red);
	    g2.fill(emitterList.get(i).getEmitterShape());
	}
	for (int i = 0; i < LEFT_RIGHT_SIDE_SENSORS; i++)
	{
	    sensorList.add(new Sensor(110, jFraneHeight - 350 - (i * SENSOR_SPACING)));// left
	    sensorList.add(new Sensor(jFrameWidth - 265, jFraneHeight - 350 - (i * SENSOR_SPACING)));// right
	    g2.setColor(Color.green);
	    g2.fill(sensorList.get(i).getSensorShape());
	}

	for (Sensor sensor : sensorList)
	{
	    for (Emitter emitter : emitterList)
	    {
		if (new RayLine(emitter.getEmitterPoint(), sensor.getSensorPoint()).getLine().intersects(mouseRectangle))
		{
		    g2.draw(new RayLine(emitter.getEmitterPoint(), sensor.getSensorPoint()).getLine()); 
		}
	    }
	}
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
	mouseRectangleX = e.getX();
	mouseRectangleY = e.getY();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	repaint();
    }
}