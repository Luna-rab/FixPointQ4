package Server;
import java.util.List;
import java.util.ArrayList;
import Log.Log;

public class Server {
    private long address;
    private int prefix; 
    private List<Log> logs = new ArrayList<Log>();
    private List<State> states = new ArrayList<State>();
    private State state = null;

    private Log l = new Log();

    public Server(long address, int prefix){
        this.address = address;
        this.prefix = prefix;
        this.state = null;
    }

    public long get_address2(){
        return this.address;
    }

    public String get_address10(){
        return this.l.ipv4_2to10(address);
    }

    public int get_prefix(){
        return this.prefix;
    }

    public void add_log(Log log){
        logs.add(log);
        state_controll(log);
    }

    void state_controll(Log log){
        if(this.state == null){
            state = new State(log.get_date());
            if(log.get_ping() < 0){
                state.set_status(1);
            }else{
                state.set_status(0);
            }
        }else{
            switch(state.get_status()){
                case 0:
                    if(log.get_ping() < 0){
                        this.state.set_end(log.get_date());
                        this.states.add(this.state);
                        this.state = new State(log.get_date(),1);
                    }
                    break;
                case 1:
                    if(log.get_ping() >= 0){
                        this.state.set_end(log.get_date());
                        this.states.add(this.state);
                        this.state = new State(log.get_date(),0);
                    }
                    break;
            }
        }
    }

    public List<State> get_states(){
        List<State> states = new ArrayList<State>();
        for(State state: this.states){
            states.add(state);
        }
        states.add(this.state);
        return states;
    }
}
