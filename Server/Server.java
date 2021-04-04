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
    private int N;

    private Log l = new Log();

    public Server(long address, int prefix, int N){
        this.address = address;
        this.prefix = prefix;
        this.N = N;
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
        state_controll(log);
        logs.add(log);
    }

    void state_controll(Log log){
        if(this.state == null){
            this.state = new State(log.get_date(),0);
            if(log.get_ping() < 0){
                this.state.inc_noreply();
                if(this.state.get_noreply() >= N){
                    this.state.set_status(1);
                }else{
                    this.state.set_status(2);
                }
            }

        }else{
            switch(this.state.get_status()){
                case 0:
                    if(log.get_ping() < 0){
                        this.state.inc_noreply();
                        if(this.state.get_noreply() >= N){
                            this.state = new State(log.get_date(),1,this.state.get_noreply());
                        }else{
                            this.state = new State(log.get_date(),2,this.state.get_noreply());
                        }
                    }else{
                        this.state.reset_noreply();
                    }
                    break;
                case 1:
                    if(log.get_ping() < 0){
                        this.state.inc_noreply();
                    }else{
                        this.state.set_end(log.get_date());
                        this.states.add(this.state);
                        this.state = new State(log.get_date(),0);
                    }
                    break;
                case 2:
                    if(log.get_ping() < 0){
                        this.state.inc_noreply();
                        if(this.state.get_noreply() >= N){
                            this.state.set_status(1);
                        }
                    }else{
                        this.state.reset_noreply();
                        this.state.set_status(0);
                    }
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
