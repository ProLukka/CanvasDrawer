package lucastf.canvasdrawer.services;

import java.util.ArrayList;

import lucastf.canvasdrawer.abstractions.IDrawing;
import lucastf.canvasdrawer.abstractions.Selector;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import lucastf.canvasdrawer.shapes.Point;

public class TranslatorService extends Selector
{
	public void translateDrawing(Canvas cv, Color background, IDrawing target, ArrayList<IDrawing> drawnObjects, double mouseX, double mouseY)
	{
		EraserService eraser = new EraserService();
		eraser.eraseDrawing(cv, background, target.getPointList().get(0).getDiameter(), target, drawnObjects);
		
		int deltaX = 0;
		int deltaY = 0;
		Point center = getDrawingCenter(target);
		deltaX = (int) mouseX - center.getX();
		deltaY = (int) mouseY - center.getY();
				
		ArrayList<Point> poi = target.getPointsOfInterest();
		for(Point p : poi)
		{
			Point newP = new Point(p.getX() + deltaX, p.getY() + deltaY, target.getPointList().get(0).getDiameter());
			newP.setColor(target.getColor());
			poi.set(poi.indexOf(p), newP);
		}
		target.setPointsOfInterest(poi);
		target.redraw(cv);
		drawnObjects.add(target);
	}
}
