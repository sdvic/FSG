import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.sound.sampled.Line;

public class RayLine
{
    Point2D.Double lineStart;
    Point2D.Double lineEnd;
    Line2D.Double rayLine;
    
    public RayLine(Point2D.Double lineStart, Point2D.Double lineEnd)
    {
	this.lineStart = lineStart;
	this.lineEnd = lineEnd;
	rayLine = new Line2D.Double(lineStart, lineEnd);
    }
    
    public Line2D.Double getLine()
    {
	return rayLine;
    }
}
