/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pr2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmnavas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\jmnavas\\Documents\\NetBeansProjects\\Pr1-HelloWorld\\config\\mapWithComplexObstacle2.txt";
            MapDto dto = MapDto.generate(filePath);
            System.out.println(dto);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
