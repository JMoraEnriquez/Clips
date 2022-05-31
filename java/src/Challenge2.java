package test;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import net.sf.clipsrules.jni.*;

public class Challenge2 extends Agent {

   Environment env;

  protected void setup() {
      try {
           env = new Environment();
      } catch (Exception e){}
     
    addBehaviour(new TellBehaviour());
    addBehaviour(new AskBehaviour());
  } 

  private class TellBehaviour extends Behaviour {

    boolean tellDone = false;

    public void action() {
        try{
          env.eval("(reset)");
          
          //Person
          env.load("C:/Users/Juanc/OneDrive/Escritorio/CLIPSJNI/persons/load-persons.clp");
          env.load("C:/Users/Juanc/OneDrive/Escritorio/CLIPSJNI/persons/load-persons-rules.clp");

          //Prodcust
          env.load("C:/Users/Juanc/OneDrive/Escritorio/CLIPSJNI/prodcust/load-prod-cust.clp");
          env.load("C:/Users/Juanc/OneDrive/Escritorio/CLIPSJNI/prodcust/load-prodcust-rules.clp");

          //Market
          env.load("C:/Users/Juanc/OneDrive/Escritorio/CLIPSJNI/market/templates.clp");
          env.load("C:/Users/Juanc/OneDrive/Escritorio/CLIPSJNI/market/facts.clp");
          env.load("C:/Users/Juanc/OneDrive/Escritorio/CLIPSJNI/market/rules.clp");

          //env.build("(assert (products (product (part-number 1234) (name USB Memory) (category storage) (price 9.99)) (product (name Amplifier) (category electronics) (part-number 2341) (price 399.99)) (product (name Speakers) (category electronics) (part-number 23241) (price 19.99)) (product (name iPhone 7) (category smartphone) (part-number 3412) (price 99.99)) (product (name Samsung Edge 7) (category smartphone) (part-number 34412) (price 88.99) ) )");
          //env.build("(assert products (product (part-number 1234) (name USB Memory) (category storage) (price 9.99)) (product (name Amplifier) (category electronics) (part-number 2341) (price 399.99)) (product (name Speakers) (category electronics) (part-number 23241) (price 19.99)) (product (name iPhone 7) (category smartphone) (part-number 3412) (price 99.99)) (product (name Samsung Edge 7) (category smartphone) (part-number 34412) (price 88.99) )");
          //env.eval("(assert (name sue))");

/*
          env.build("(deftemplate product (slot part-number) (multislot name) (slot category) (slot price))");
          env.build("(deffacts products (product (part-number 1234) (name USB Memory) (category storage) (price 9.99)) (product (name Amplifier) (category electronics) (part-number 2341) (price 399.99)) (product (name Speakers) (category electronics) (part-number 23241) (price 19.99)) (product (name iPhone 7) (category smartphone) (part-number 3412) (price 99.99)) (product (name Samsung Edge 7) (category smartphone) (part-number 34412) (price 88.99)) )");
          env.build("(defrule my-rule1 (product (name ?n) (price 9.99)) => (printout  t \"Customer name found:\"  ?n crlf ))");                 
*/  
        }catch (Exception e){}

        tellDone = true;
       
    } 
    
    public boolean done() {
      if (tellDone)
        return true;
      else
	return false;
    }
  }    // END of inner class ...Behaviour


  private class AskBehaviour extends Behaviour {

    boolean askDone = false;

    public void action() {
        try{
          System.out.println("Facts:");
          env.eval("(facts)"); 
          System.out.println("Rules:");
          env.eval("(rules)");

          env.eval("(run)");
        }catch(Exception e){}
       askDone = true;
        

    } 
    
    public boolean done() {
      if (askDone)
        return true;
      else
	return false;
    }
   
    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}
