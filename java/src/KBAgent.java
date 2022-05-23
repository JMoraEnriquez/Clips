package clips.agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
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

  private class TellBehaviour extends Behaviour {

    boolean tellDone = false;

    public void action() {
      System.out.println("Tell");
      try{
        env.eval("(reset)");

        env.eval("(assert (person sue))");
        env.eval("(assert (person bob))");

        env.eval("(defrule r1 (person ?p) => (printout t ?p crlf))");

        env.eval("(facts)");
      }catch(Exception e){}

      tellDone = true;
    } 
    
    public boolean done() {
      if (tellDone)
        return true;
      else
	return false;
    } 
  }
  
  private class AskBehaviour extends Behaviour {

    boolean askDone = false;

    public void action() {
      System.out.println("Ask");
      try{
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
  }// END of inner class ...Behaviour
}
