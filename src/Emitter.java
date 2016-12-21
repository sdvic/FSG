import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Emitter
{
    int laserXpos;
    int laserYpos;
    int laserDiameter = 10;
    Point2D.Double emitterPoint;
    
    public Emitter(int laserXpos, int laserYpos)
    {
	this.laserXpos = laserXpos;
	this.laserYpos = laserYpos;
	this.emitterPoint = new Point2D.Double(laserXpos, laserYpos);
    }
    
    public Ellipse2D.Double getEmitterShape()
    {
	return new Ellipse2D.Double(this.laserXpos, this.laserYpos, this.laserDiameter, this.laserDiameter);
    }
    public Point2D.Double getEmitterPoint()
    {
	Point2D.Double emitterPoint = new Point2D.Double(laserXpos, laserYpos);
	return emitterPoint;
    }
    public Emitter getEmitter()
    {
	return this;
    }
}
