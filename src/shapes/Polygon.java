package shapes;

import java.util.ArrayList;

import abstractions.IDrawing;
import abstractions.IShape;
import enums.ShapeType;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Polygon implements IShape, IDrawing{
	
	private Point firstPoint;
	private Point lastPoint;
	private ArrayList<Line> lines;
	private Color colorC;
	
	private ArrayList<Point> pointList = new ArrayList<Point>();
	private ArrayList<Point> bufferList = new ArrayList<Point>();
	
	public Polygon(){
		lines = new ArrayList<Line>();
	}

	@Override
	public void setFirstPoint(Point p) {
		this.firstPoint = p;
		lines.add(new Line());
		lines.get(lines.size() - 1).setFirstPoint(p);;
	}
	
	public void setupNextLine(Point p)
	{
		lines.add(new Line());
		lines.get(lines.size() - 1).setFirstPoint(p);
		lines.get(lines.size() - 1).setLastPoint(p);
	}
	
	@Override
	public void setLastPoint(Point p) {
		lastPoint = p;
		lines.get(lines.size() - 1).setLastPoint(p);
	}

	@Override
	public void draw(Canvas gv, Color c, double diameter, double iterations) {
		bufferList.clear();
		lines.get(lines.size() - 1).draw(gv,c,diameter,iterations);
		for(Point pl : lines.get(lines.size() - 1).getPointList()) {
			bufferList.add(pl);
		}
	}
	
	public void setLineForPolygon(Line l){
		lines.add(l);
	}
	
	public void forceDrawPolygon(Canvas cv, Color c, double diameter){
		for(Line l: lines){
			l.draw(cv, c, diameter, 0);
			for(Point p : l.getPointList()) {
				this.pointList.add(p);
			}
		}
		this.firstPoint = lines.get(0).getFirstPoint();
		this.lastPoint = lines.get(lines.size() - 1).getLastPoint();
	}
	
	public void setDrawnLineToPointList() {
		pointList.addAll(bufferList);
	}

	@Override
	public Point getFirstPoint() {
		return firstPoint;
	}

	@Override
	public Point getLastPoint() {
		return lastPoint;
	}
	
	public ArrayList<Line> getLines()
	{
		return this.lines;
	}

	@Override
	public ArrayList<Point> getPointList() {
		return pointList;
	}

	@Override
	public void erasePoints(Canvas cv, Color c, double thickness) {
		for(Point p : this.pointList)
		{
			p.drawTempPoint(cv, c, (int)thickness, 0);
		}
	}

	@Override
	public String getDrawingName() {
		return ShapeType.CLOSEDPOLYGON.getShapeName();
	}
	
	public void redraw(Canvas cv)
	{
		for(Point p : this.pointList)
		{
			p.drawPoint(cv, p.getColor(), p.getDiameter(), 0);
		}
		recalculatePointsOfInterest();
	}

	public void recalculatePointsOfInterest()
	{
		this.firstPoint = this.pointList.get(0);
		this.lastPoint = this.pointList.get(this.pointList.size() - 1);
		
		int pointIndx = 0;
		for(Line l : this.lines)
		{
			for(Point lp : l.getPointList())
			{
				l.getPointList().set(l.getPointList().indexOf(lp), this.pointList.get(pointIndx));
				pointIndx++;
			}
			l.recalculatePointsOfInterest();
		}
	}
	
	@Override
	public Color getColor() {
		return this.pointList.get(0).getColor();
	}

	public Color getColorC() {
		return colorC;
	}

	public void setColorC(Color colorC) {
		this.colorC = colorC;
	}
}
