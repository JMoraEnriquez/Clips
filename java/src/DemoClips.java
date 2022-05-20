package pepetron;

import net.sf.clipsrules.jni.Environment;

public class DemoClips{
    
    public static void main(String[] nousan) throws Exception {
        Environment env = new Environment();

        env.eval("(reset)");

        env.eval("(assert (name sue))");
        env.eval("(assert (name paula))");
        env.eval("(assert (sintom feber))");
        
        env.eval("(facts)");
    }
}