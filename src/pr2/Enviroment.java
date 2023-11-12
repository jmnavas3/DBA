/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr2;

/**
 *
 * @author galvez
 */
public class Enviroment {
    public MapDto map;
    public int[][] mapPrint;
    private int goalPositionX, goalPositionY;       
    private int agentPositionX, agentPositionY;
    private int[][] sensors;
    private double[][] utility;
    
    public String accion; /* variable provisional para pasar mov a BehavMoveAgent por temas de que se ejecute primero*/
                                       /* Unicamente coge esta, no pasa por el behaviour de utilidad */
    public Enviroment(){
        this.sensors = new int[3][3];
      /*this.map = this.setMap("C:\\Users\\jmnavas\\Documents\\NetBeansProjects\\Pr1-HelloWorld\\config\\mapWithoutObstacle.txt");*/
        this.map = this.setMap("C:\\Users\\joy111\\OneDrive\\Actual\\DBA\\Practicas\\Practica2\\mapWithVerticalWall.txt");
      /*this.map = this.setMap("/home/galvez/Universidad/DBA/Pr1-maps/mapWithoutObstacle.txt");*/
        this.utility = new double [this.map.rows][this.map.columns];
        this.mapPrint = this.map.myMap;
    }
    
    // Update agent position 
    public void doMoveAction(String action){
        switch (action) { 
            case "moveUp":
                agentPositionX -= 1;
                break;
            
            case "moveDown":
                agentPositionX += 1;
                break;
                
            case "moveLeft":
                agentPositionY -= 1;
                break;
                
            case "moveRight":
                agentPositionY += 1;
                break;
          }
        System.out.println(action);
    }
    
    public void showPath() {
        this.mapPrint[agentPositionX][agentPositionY] = 8;
        System.out.println(this.utility[0][0]);
        System.out.print(this.printMap());
    }
    
    // Prints an X in all the positions the agent has been
    public String printMap() {
        String mapString = "\n";
        for(int i = 0; i < this.mapPrint.length; i++) {
            for (int j = 0; j < this.mapPrint.length; j++) {
                if (this.mapPrint[i][j] == 8) mapString += 'X' + "\t";
                else mapString += Integer.toString(this.mapPrint[i][j]) + "\t";
            }
            mapString += "\n";
        }

        return mapString;
    }
    
    // Prints only an X where the agent is in the moment
    public String printMap2() {
        String mapString = "\n";
        for(int i = 0; i < this.mapPrint.length; i++) {
            for (int j = 0; j < this.mapPrint.length; j++) {
                if (i == agentPositionX && j == agentPositionY) mapString += 'X' + "\t";
                else if (i == goalPositionX && j == goalPositionY) mapString += 'G' + "\t";
                else mapString += Integer.toString(this.mapPrint[i][j]) + "\t";
            }
            mapString += "\n";
        }

        return mapString;
    }
    
    public void showMapStatus() {
        System.out.println(this.utility[0][0]);
        System.out.print(this.printMap2());
    }
    
    // Update sensors
    public void see(){
        int x = agentPositionX, y = agentPositionY, k = 0, l = 0;
        boolean overX, overY;
        
        for (int i = x-1; i < x+1; i++) {
            overX = i > map.rows+1;
            for (int j = y-1; j < y+1; j++) {
                overY = j > map.rows+1;
                if (overX || overY) {
                    sensors[k][l++] = -1;
                } else 
                    sensors[k][l++] = map.myMap[i][j];
            }
            k++;
            l=0;
        }
    }
    
    
    public void setAgentPosition(int x, int y){
        this.agentPositionX = x;
        this.agentPositionY = y;
    }
    
    public void setGoalPosition(int x, int y){
        this.goalPositionX = x;
        this.goalPositionY = y;
       
    }
    
    
    
    
    public int getGoalPositionX(){
    
        return this.goalPositionX;
    }
    
    public int getGoalPositionY(){
    
        return this.goalPositionY;
    }
    
    public int getAgentPositionX(){
    
        return this.agentPositionX;
    }
    public int getAgentPositionY(){
    
        return this.agentPositionY;
    }    
    
    public MapDto setMap(String map){
        MapDto result;
        try {
            result = MapDto.generate(map);
        } catch (Exception e) {
            return null;
        }
        return result;
    }
  
    
    public String setAction(String move){
        accion = move;
        
        return accion;
    }
    
    public String getAction(){
        
        return accion;
    }
    
    public void setUtility(double value,int row, int colum){
        
        this.utility[row][colum] += value;
        
    }
    
    public double getUtility(int row, int colum) throws Exception{
        if(this.utility.length < row && this.utility.length < colum){
            throw new Exception("Nos hemos pasado de filas o columnas");
        }
        return this.utility[row][colum];
    }
    
    
    public int[][] getSensors(){
        
        return this.sensors;
      
    }
      
    public double distance (int x1, int y1) {
        return Math.sqrt(
                Math.pow(this.goalPositionX - x1, 2) +
                Math.pow(this.goalPositionY - y1, 2)
        );
    }
    
    // Function that returns true if the agent is in the goal position
    public boolean checkGoal() {
        return (agentPositionX == goalPositionX && agentPositionY == goalPositionY);
    }
}

