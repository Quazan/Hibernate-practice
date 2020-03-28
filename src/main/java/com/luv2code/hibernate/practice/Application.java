package com.luv2code.hibernate.practice;

public class Application {

    public static void main(String[] args) {

        Controller controller = new Controller();
        View view = new View(controller);
        try {
            view.run();
        }
        catch (Exception ex){
            ex.printStackTrace();
        } finally {
          controller.closeConnection();
        }
    }
}
