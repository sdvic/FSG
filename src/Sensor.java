import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Sensor
{
    int diodeXpos;
    int diodeYpos;
    int diodeDiameter = 10;
    
    public Sensor(int diodeXpos, int diadeYpos)
    {
	this.diodeXpos = diodeXpos;
	this.diodeYpos = diadeYpos;
    }
    
    public Ellipse2D.Double getSensorShape()
    {
	return new Ellipse2D.Double(this.diodeXpos, this.diodeYpos, this.diodeDiameter, this.diodeDiameter);
    }
    public Point2D.Double getSensorPoint()
    {
	Point2D.Double sensorPoint = new Point2D.Double(diodeXpos, diodeYpos);
	return sensorPoint;
    }
    public Sensor getSensor()
    {
	return this;
    }
}
