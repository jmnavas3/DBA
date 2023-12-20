package pr3;


public class Enviroment {
    public MapParser map;
    public int[][] mapPrint;
    private int goalPositionX, goalPositionY;       
    private int agentPositionX, agentPositionY;
    private int[][] sensors;
    private double[][] utility;
    public String accion;
    
    public Enviroment(){
        this.sensors = new int[3][3];
        this.map = this.setMap("./src/pr3/mapas/mapPr3.txt");
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
            case "moveUpLeft":
                agentPositionX -= 1;
                agentPositionY -= 1;
                break;
            
            case "moveUpRight":
                agentPositionX -= 1;
                agentPositionY += 1;
                break;
                
            case "moveDownLeft":
                agentPositionX += 1;
                agentPositionY -= 1;
                break;
                
            case "moveDownRight":
                agentPositionX += 1;
                agentPositionY += 1;
                break;
          }
        System.out.println(action);
    }
    
    // Prints only an X where the agent is in the moment
    public String printMap() {
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
        //System.out.print(this.printMap());
    }
    
    // Update sensors
    public void see(){
        for(int i=-1; i < sensors.length-1; i++){
            for(int j=-1; j < sensors.length-1; j++){
                if (checkMapLimits(agentPositionX+i, agentPositionY+j))
                    sensors[i+1][j+1] = map.myMap[agentPositionX+i][agentPositionY+j];
                else
                    sensors[i+1][j+1] = -2;
            }
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
    
    public int[] getMinPosUtility(int agentPosX, int agentPosY){
        double minUtility = 20000.0;
        int[] pairMinPosition = new int[2];
        double currentUtility = minUtility;
        
        for(int i=-1; i < sensors.length-1; i++)
            for(int j=-1; j < sensors.length-1; j++){
                if (checkMapLimits(agentPosX+i, agentPosY+j)) {
                    currentUtility = this.utility[agentPosX+i][agentPosY+j];
                    if(currentUtility < minUtility && checkMove(i, j)){
                        pairMinPosition[0] = agentPosX+i;
                        pairMinPosition[1] = agentPosY+j;
                        minUtility = currentUtility;
                    }
                }
            }
                
        return pairMinPosition;
    }
    
    public int getAgentPositionX(){
        return this.agentPositionX;
    }
    
    public int getAgentPositionY(){
        return this.agentPositionY;
    }    
    
    public MapParser setMap(String map){
        MapParser result;
        try {
            result = MapParser.generate(map);
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
    
    public void addUtility(double value,int row, int column){
        if (checkMapLimits(row, column))
            this.utility[row][column] += value;
    }
    
    public double getUtility(int row, int column) throws Exception{
        return this.utility[row][column];
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
    
    public boolean checkMapLimits(int posX, int posY) {
        boolean inX = posX >= 0 && posX < map.rows;
        boolean inY = posY >= 0 && posY < map.columns;
        return inX && inY;
    }

    public boolean checkMove(int posX, int posY) {
        boolean isLateral = posX==0 ^ posY==0; // XOR: 01, 10 true ; 00, 11 false
        boolean inLimit = checkMapLimits(agentPositionX + posX, agentPositionY + posY);
        boolean leftRight, upDown;
        boolean validMove = isLateral;

        if (!isLateral && inLimit) {
            leftRight = (sensors[1][posY + 1] != -1);
            upDown = (sensors[posX + 1][1] != -1);
            validMove = leftRight && upDown;
        }
        
        return validMove;
    }
    
    // Function that returns true if the agent is in the goal position
    public boolean checkGoal() {
        return (agentPositionX == goalPositionX && agentPositionY == goalPositionY);
    }
}

