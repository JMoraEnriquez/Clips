package clips.agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import net.sf.clipsrules.jni.Environment;

public class KBAgent extends Agent {

  public Environment env;

  protected void setup() {
    try{
    env = new Environment();
    }catch(Exception e){} 
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new TellBehaviour());
    addBehaviour(new AskBehaviour());
  } 

  private class TellBehaviour extends OneShotBehaviour {

    public void action() {
      System.out.println("Tell");
      try{
        env.eval("(reset)");

        env.eval("(defrule r1 (person ?p) => (printout t ?p crlf))");

        env.eval("(assert (person sue))");
        env.eval("(assert (person bob))");

        env.eval("(facts)");
      }catch(Exception e){}
    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }
  
  private class AskBehaviour extends OneShotBehaviour {

    public void action() {
      System.out.println("Ask");
      try{
        env.eval("(run)");
      }catch(Exception e){}
    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }// END of inner class ...Behaviour
}
