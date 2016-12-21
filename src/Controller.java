import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.annotation.Generated;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Controller extends JComponent
{
    JFrame jf = new JFrame();
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    Rectangle2D.Double room = new Rectangle2D.Double(50, 50, width - 150, height - 250);
    Rectangle2D.Double topFrame = new Rectangle2D.Double(100, 100, width - 350, 25);
    Rectangle2D.Double bottomFrame = new Rectangle2D.Double(100, height - 350, width - 350, 25);
    Rectangle2D.Double leftFrame = new Rectangle2D.Double(100, 125, 25, height - 475);
    Rectangle2D.Double rightFrame = new Rectangle2D.Double(width -275, 125, 25, height - 475);
    Rectangle2D.Double screen = new Rectangle2D.Double(125, 125, width - 400, height - 475);
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
    final int SENSOR_SPACING = 20;
    final int EMITTER_SPACING = 90;
    
    public static void main(String[] args)
    {
	new Controller().getGoing();
    }

    private void getGoing()
    {
	jf.add(this);
	jf.setSize(width, height);
	jf.setVisible(true);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void paint(Graphics g)
    {
	Graphics2D g2 = (Graphics2D)g;
	g2.draw(room);
	g2.draw(topFrame);
	g2.draw(bottomFrame);
	g2.draw(leftFrame);
	g2.draw(rightFrame);
	g2.setColor(new Color(153, 255, 255));
	g2.fill(screen);
	rayLineList.add(new RayLine(new Point2D.Double(100,  100), new Point2D.Double(800,  800)).getLine());
	g2.setColor(Color.black);
	g2.draw(rayLineList.get(0));
	for(int i = 0; i < 50; i ++)
	{
	    sensorList.add(new Sensor(140 + (i * SENSOR_SPACING), 109));
	    sensorList.add(new Sensor(140 + (i * SENSOR_SPACING), height - 340));
	    g2.setColor(Color.green);
	    g2.fill(sensorList.get(i).getSensorShape());
	    g2.fill(sensorList.get(i).getSensorShape());
	}
	for (int i = 0; i < 10; i++)
	{
	    emitterList.add(new Emitter(140 + (i * EMITTER_SPACING), 109));
	    emitterList.add(new Emitter(140 + (i * EMITTER_SPACING), height - 340));
	    g2.setColor(Color.red);
	    g2.fill(emitterList.get(i).getEmitterShape());
	    g2.fill(emitterList.get(i).getEmitterShape());
	}
	for (int i = 0; i < 22; i++)
	{
	    emitterList.add(new Emitter(110, height - 350 - (i * EMITTER_SPACING)));//left emitter
	    emitterList.add(new Emitter(width - 264, (height - 350) - (i * EMITTER_SPACING)));//right emitter
	    g2.setColor(Color.red);
	    g2.fill(emitterList.get(i).getEmitterShape());
	}
	for (int i = 0; i < 58; i++)
	{
	   sensorList.add(new Sensor(110, height - 350 - (i * SENSOR_SPACING)));//left sensor
	   sensorList.add(new Sensor(width - 265, height - 350 - (i * SENSOR_SPACING)));//right sensor
	   g2.setColor(Color.green);
	   g2.fill(sensorList.get(i).getSensorShape());
	}
	
	for (Sensor sensor : sensorList)
	{
	    for (Emitter emitter : emitterList)
	   {
	       g2.draw(new RayLine(emitter.getEmitterPoint(), sensor.getSensorPoint()).getLine());
	   }
	}
    }
}