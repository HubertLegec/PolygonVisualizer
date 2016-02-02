package sample;

import javafx.geometry.Point2D;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hubert.legec on 2016-01-19.
 */
public class FileProcessor {
    private Prism processedPrism = null;

    public static List<Prism> getPrismsFromFile(File file){
        List<Prism> result = new LinkedList<>();
        try {
            Files.lines(file.toPath()).forEach( l -> result.add(parseLine(l)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Prism> getIntersectionPrismsFromFile(File file){
        List<Prism> result = new LinkedList<>();
        try {
            Files.lines(file.toPath()).forEach( l -> {
                if(l.equals("---")){
                    if(processedPrism != null){
                        result.add(processedPrism);
                    }
                    processedPrism = new Prism(-1);
                } else if(l.split(" ").length == 3){
                    processedPrism.addHeightRange(Integer.valueOf(l.split(" ")[0]), Integer.valueOf(l.split(" ")[1]), Integer.valueOf(l.split(" ")[2]));
                } else {
                    String[] parts = l.split(" ");
                    int i = 0;
                    List<Point2D> vertices = new LinkedList<>();
                    while(i < parts.length - 1){
                        vertices.add(new Point2D(Double.valueOf(parts[i]), Double.valueOf(parts[i+1])));
                        i += 2;
                    }
                    processedPrism.setVertexList(vertices);
                }
            });
            if(processedPrism != null){
                result.add(processedPrism);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Prism parseLine(String line){
        int id;
        int top;
        int bottom;
        List<Point2D> points = new LinkedList<>();
        List<String> parts = Arrays.asList(line.split(" "));
        id = Integer.valueOf(parts.get(0));
        bottom = Integer.valueOf(parts.get(1));
        top = Integer.valueOf(parts.get(2));
        int i = 3;
        while(i < parts.size() - 1){
            points.add(new Point2D(Double.valueOf(parts.get(i)), Double.valueOf(parts.get(i+1))));
            i += 2;
        }

        Prism prism = new Prism(id);
        prism.addHeightRange(id, bottom, top);
        prism.setVertexList(points);
        return prism;
    }
}
