/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

/**
 * Nombre de mapas para copiar y pegar:
 * mapWithoutObstacle
 * mapWithVerticalWall
 * mapWithHorizontalWall
 * mapWithDiagonalWall
 * mapWithComplexObstacle1
 *
 * @author galvez
 */
public class Enviroment {
    public MapDto map;
    public int[][] mapPrint;
    private int goalX, goalY;
    private int agentX, agentY;
    private int[][] sensors;
    private double[][] utility;
    public String action;
    
    public Enviroment(){
        this.sensors = new int[3][3];
        this.map = this.setMap("C:\\Users\\jmnavas\\Documents\\NetBeansProjects\\Pr1-HelloWorld\\config\\mapWithVerticalWall.txt");
//        this.map = this.setMap("C:\\Users\\joy111\\OneDrive\\Actual\\DBA\\Practicas\\Practica2\\mapWithVerticalWall.txt");
        this.utility = new double [this.map.rows][this.map.columns];
        this.mapPrint = this.map.myMap;
        this.mapPrint[goalX][goalY] = 10;
    }
    
    public void setAgentPosition(int x, int y) {
        this.agentX = x;
        this.agentY = y;
    }

    public void setGoalPosition(int x, int y) {
        this.goalX = x;
        this.goalY = y;
    }

    public int getGoalX() {
        return this.goalX;
    }

    public int getGoalY() {
        return this.goalY;
    }

    public int getAgentX() {
        return this.agentX;
    }

    public int getAgentY() {
        return this.agentY;
    }
    
    public MapDto setMap(String map) {
        MapDto result;
        try {
            result = MapDto.generate(map);
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    public String setAction(String move) {
        action = move;

        return action;
    }

    public String getAction() {
        return action;
    }
    
    // Update agent position 
    public void doMoveAction(String action){
        switch (action) { 
            case "moveUp":
                agentX -= 1;
                break;
            
            case "moveDown":
                agentX += 1;
                break;
                
            case "moveLeft":
                agentY -= 1;
                break;
                
            case "moveRight":
                agentY += 1;
                break;
          }
//        System.out.println(action);
    }
    
    public void showPath() {
        this.mapPrint[agentX][agentY] = 8;
        System.out.print(this.printMap());
        System.out.print("X=" + agentX + "Y=" + agentY);
        System.out.print(this.printSensors());
        
    }
    
    public String printMap() {
        String mapString = "\n";
        for(int i = 0; i < this.mapPrint.length; i++) {
            for (int j = 0; j < this.mapPrint.length; j++) {
                if (mapPrint[i][j] > 1) {
                    if (this.mapPrint[i][j] == 8) mapString += 'X' + "\t";
                    else if (mapPrint[i][j] == 10) mapString += 'M' + "\t";
                }
                else mapString += Integer.toString(this.mapPrint[i][j]) + "\t";
            }
            mapString += "\n";
        }

        return mapString;
    }
    
    // Update sensors
    public void see(){
        int x = agentX, y = agentY, k = 0, l = 0;
        boolean overX, overY;
        
        int celdaX, celdaY;
        
        for (int i = -1; i < sensors.length - 1; i++) {
            celdaX = agentX + i;
            for (int j = -1; j < sensors.length - 1; j++) {
                celdaY = agentY + j;
                sensors[i+1][j+1] = map.myMap[celdaX][celdaY];
            }
        }
        
//        for (int i = x-1; i < x+1; i++) {
//            overX = i > map.rows+1;
//            for (int j = y-1; j < y+1; j++) {
//                overY = j > map.rows+1;
//                if (overX || overY) {
//                    sensors[k][l++] = -5;
//                } else 
//                    sensors[k][l++] = map.myMap[i][j];
//            }
//            k++;
//            l=0;
//        }
    }
    
    public String printSensors() {
        String sensorsString = "\n";
        for (int i = 0; i < this.sensors.length; i++) {
            for (int j = 0; j < this.sensors.length; j++) {
                sensorsString += Integer.toString(this.sensors[i][j]) + "\t";
            }
            sensorsString += "\n";
        }

        return sensorsString;
    }
    
    // asigna o incrementa el valor de una celda
    public void setUtility(double value,int row, int column){
        if (this.utility[row][column] == 0.0)
            this.utility[row][column] = value;            
    }
    
    public double getUtility(int row, int column){
        return this.utility[row][column];
    }
    
    public int[][] getSensors(){
        return this.sensors;
    }
      
    public double distance (int x1, int y1) {
        return Math.sqrt(Math.pow(this.goalX - x1, 2) +
                Math.pow(this.goalY - y1, 2)
        );
    }
    
    public void criteria() {
        int pared = 100;
        int minX=-1, minY=-1;
        int celdaX, celdaY;
        int movimiento = 0;
        double min=pared, actual, value;
        
        for (int i = -1; i < sensors.length - 1; i++) {
            celdaX = agentX+i;
            for (int j = -1; j < sensors.length - 1; j++) {
                celdaY = agentY+j;
                
                // calculamos distancia
                value = (sensors[i+1][j+1] != -1) ? distance(celdaX, celdaY) : pared;
                
                // asignamos valor de utilidad
                setUtility(value, celdaX, celdaY);
                
                // obtenemos la posición y valor de la celda con menor distancia
                actual = getUtility(celdaX, celdaY);
                if (min > actual) {
                    min = actual;
                    minX = celdaX;
                    minY = celdaY;
                }
            }
        }
        
        if (isAction(minX, minY))
            System.out.println(min + " " + minX + " " + minY + "\n*************");
        else 
            System.out.println("LATERAL");
        
        boolean[] valid = new boolean[4]; // 0: arriba, 1: abajo, 2: izda, 3: dcha
        valid[0] = true;
        valid[1] = true;
        valid[2] = true;
        valid[3] = true;
        
        do {
            if (agentX > goalX && minX == agentX - 1 && valid[0]) {
                action = "moveUp";
                movimiento = sensors[0][1];
                valid[0] = movimiento != -1;
            } else if (agentX < goalX && minX == agentX + 1 && valid[1]) {
                action = "moveDown";
                movimiento = sensors[2][1];
                valid[1] = movimiento != -1;
            } else if (agentY > goalY && minY == agentY - 1 && valid[2]) {
                action = "moveLeft";
                movimiento = sensors[1][0];
                valid[2] = movimiento != -1;
            } else if (agentY < goalY && minY == agentY + 1 && valid[3]) {
                action = "moveRight";
                movimiento = sensors[1][2];
                valid[3] = movimiento != -1;
            }
            
            if (movimiento == -1)
                System.out.println("**MURO**");
            
        } while (movimiento == -1);
        
    }
    
    // returns true if first or second condition is true, but not both of them. XOR operator
    public boolean isAction(int posX, int posY) {
        return (agentX == posX ^ agentY == posY);
    }
    
    // Function that returns true if the agent is in the goal position
    public boolean checkGoal() {
        return (agentX == goalX && agentY == goalY);
    }
}

