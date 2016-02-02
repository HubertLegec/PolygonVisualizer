package sample;

import javafx.geometry.Point2D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hubert.legec on 2016-01-19.
 */
public class Prism {
    private int id;
    private List<Point2D> vertices;
    private Map<Integer, Pair> heightRanges = new HashMap<>();
    private double minX = 2000000;
    private double minY = 2000000;
    private double maxX = -2000000;
    private double maxY = -2000000;

    Prism(int id){
        this.id = id;
    }

    public void setVertexList(List<Point2D> vertices){
        this.vertices = vertices;
        updateMinMax();
    }

    List<Point2D> getVertices(){
        return vertices;
    }

    public void addHeightRange(int id, float bottom, float top){
        heightRanges.put(id, new Pair(bottom, top));
    }

    public Map<Integer, Pair> getHeightRanges(){
        return heightRanges;
    }

    private void updateMinMax(){
        vertices.stream().forEach( v -> {
            if(v.getX() < minX){
                minX = v.getX();
            }
            if(v.getX() > maxX){
                maxX = v.getX();
            }
            if(v.getY() < minY){
                minY = v.getY();
            }
            if(v.getY() > maxY){
                maxY = v.getY();
            }
        });
    }

    public int getId(){
        return id;
    }

    public double getMaxX() {
        return maxX;
    }

    boolean isIntersectionOf(int firstId, int secondId){
        if(firstId != secondId && heightRanges.containsKey(firstId) && heightRanges.containsKey(secondId)){
            return true;
        } else {
            return false;
        }
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    @Override
    public String toString(){
        return "Prism " + id;
    }
}
