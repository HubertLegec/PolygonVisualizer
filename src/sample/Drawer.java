package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hubert.legec on 2016-01-19.
 */
public class Drawer {
    private Pane pane;
    BooleanProperty intersection = new SimpleBooleanProperty();
    Prism prism1;
    Prism prism2;
    List<Prism> intersectionList;
    double xRange;
    double yRange;

    public Drawer(Pane pane){
        this.pane = pane;
        xRange = pane.getBoundsInLocal().getWidth()/2;
        yRange = pane.getBoundsInLocal().getHeight()/2;
        intersection.addListener( (observable, oldValue, newValue) -> update());
    }

    public void setFirstPrism(Prism prism){
        prism1 = prism;
        update();
    }

    public void setSecondPrism(Prism prism){
        prism2 = prism;
        update();
    }

    public BooleanProperty getIntersectionProperty(){
        return intersection;
    }

    public void setIntersectionList(List<Prism> intersections){
        this.intersectionList = intersections;
    }

    private void update(){
        double xOffset = 450;
        double yOffset = 400;
        pane.getChildren().clear();
        if(prism1 != null && !intersection.get()){
            Polygon polygon1 = new Polygon();
            polygon1.setStroke(Color.BLACK);
            prism1.getVertices().stream().forEach( point2D -> {
                polygon1.getPoints().add(point2D.getX()+ xOffset);
                polygon1.getPoints().add(-point2D.getY() + yOffset);
            });

            polygon1.setFill(Color.RED);
            polygon1.setStrokeWidth(2);
            polygon1.setOpacity(0.5);
            pane.getChildren().add(polygon1);
        }
        if(prism2 != null && !intersection.get()){
            Polygon polygon2 = new Polygon();
            polygon2.setStroke(Color.BLACK);
            polygon2.setStrokeWidth(1);
            prism2.getVertices().stream().forEach( point2D -> {
                polygon2.getPoints().add(point2D.getX()+ xOffset);
                polygon2.getPoints().add(-point2D.getY() + yOffset);
            });

            polygon2.setFill(Color.GREEN);
            polygon2.setStrokeWidth(2);
            polygon2.setOpacity(0.5);
            pane.getChildren().add(polygon2);
        }

        if(intersection.get() && prism1 != null && prism2 != null){
            List<Prism> toDraw = new LinkedList<>();
            intersectionList.forEach(item -> {
                if(item.isIntersectionOf(prism1.getId(), prism2.getId())){
                    toDraw.add(item);
                }
            });
            toDraw.forEach( item -> {
                Polygon intersPol = new Polygon();
                intersPol.setStroke(Color.BLACK);
                intersPol.setStrokeWidth(1);
                item.getVertices().stream().forEach( point2D -> {
                    intersPol.getPoints().add(point2D.getX()+ xOffset);
                    intersPol.getPoints().add(-point2D.getY() + yOffset);
                });

                intersPol.setFill(Color.BLUE);
                intersPol.setStrokeWidth(2);
                intersPol.setOpacity(0.8);
                pane.getChildren().add(intersPol);
            });
        }
    }

}
