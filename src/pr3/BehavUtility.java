package pr3;

public class BehavUtility extends BehavGoal{
    @Override
    public void action(){
        ds = this.getDataStore();
        Enviroment env = (Enviroment) ds.get("enviroment");
        
        String action = "nothing";
        double pared = 10000.0;
        double visitada = 10.0;
        int nextPosX, nextPosY;
        int agentX = env.getAgentPositionX();
        int agentY = env.getAgentPositionY();
        int[] pairNextPos = {0,0};
        int[][] sensors = env.getSensors();
        
        for(int i=-1; i < sensors.length-1; i++){          
            for(int j=-1; j < sensors.length-1; j++){      
                
                if(sensors[i+1][j+1] == -1){ // pared se penaliza
                    env.addUtility(pared, agentX+i, agentY+j);
                }
                else{
                    env.addUtility(env.distance(agentX+i, agentY+j),agentX+i, agentY+j);
                }
                
                if(i==0 && j==0){   // posición del agente se penaliza
                    env.addUtility(visitada, agentX+i, agentY+j);
                }
            }
        }

        pairNextPos = env.getMinPosUtility(agentX, agentY);
        nextPosX = pairNextPos[0];
        nextPosY = pairNextPos[1];
        
        System.out.println("next pos: "+nextPosX+"," +nextPosY);
        
        if(nextPosX < agentX && nextPosY == agentY){
            action = "moveUp";
        } else if (nextPosX > agentX && nextPosY == agentY){
            action = "moveDown";
        } else if (nextPosX == agentX && nextPosY < agentY){
            action = "moveLeft";
        } else if (nextPosX == agentX && nextPosY > agentY){
            action = "moveRight";
        } else if(nextPosX < agentX && nextPosY < agentY){
            action = "moveUpLeft";
        } else if (nextPosX < agentX && nextPosY > agentY){
            action = "moveUpRight";
        } else if (nextPosX > agentX && nextPosY < agentY){
            action = "moveDownLeft";
        } else if (nextPosX > agentX && nextPosY > agentY){
            action = "moveDownRight";
        }
        
        env.setAction(action);  
        
        ds.put("enviroment", env);
        this.setDataStore(ds);
    }
}
