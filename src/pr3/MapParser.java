package pr3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MapParser {
    private final String name;
    public final int rows, columns;
    public final int[][] myMap;
    
    private MapParser(String name, int[][] map) {
        this.name = name;
        this.myMap = map;
        this.rows = this.myMap.length;
        this.columns = this.myMap[0].length;
    }
    
    public static MapParser generate(String map) throws Exception {
        int[][] data;
        int row = 0;
        File mapFile = new File(map);

        try {
            Scanner scanner = validate_file(mapFile);
            
            int rows    = scanner.nextInt();
            int columns = scanner.nextInt();
            data = new int[rows][columns];
            while(scanner.hasNextInt()) {
                for (int column = 0; column < columns; column++) {
                    data[row][column] = scanner.nextInt();
                }
                row++;
            }
        } catch (Exception e) {
            throw new Exception("Map DTO exception: " + e.getMessage());
        }
        
        return new MapParser(map, data);
    }
    
    private static Scanner validate_file(File file) throws Exception {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new Exception("File not found: " + ex.getMessage());
        }
    }
    
    @Override
    public String toString() {
        String mapString = this.name + "\n";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                mapString += Integer.toString(this.myMap[i][j]) + " ";
            }
            mapString += "\n";
        }
        
        return mapString;
    }
}
