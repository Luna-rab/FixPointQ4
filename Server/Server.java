package Server;
import java.util.List;
import java.util.ArrayList;
import Log.Log;

public class Server {
    long address;
    int prefix; 
    List<Log> logs = new ArrayList<Log>();
    List<State> states = new ArrayList<State>();
    State state = null;
    int N;
    int m;
    int t;

    Log l = new Log();
    public Server(){}
    public Server(long address, int prefix, int N, int m, int t){
        this.address = address;
        this.prefix = prefix;
        this.N = N;
        this.m = m;
        this.t = t;
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

    public long get_subnet(){
        return l.get_subnet(this.address, this.prefix);
    }

    public void add_log(Log log){
        logs.add(log);
        state_controll(log);
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
            }else{
                this.state.inc_reply();
                if(this.state.get_reply()>=m){
                    int avetime = 0;
                    for(int i=0; i<m; i++){
                        avetime += this.logs.get(this.logs.size()-1-i).get_ping();
                    }
                    avetime /= m;
                    if(avetime >= this.t){
                        this.state = new State(log.get_date(),3);
                    }
                }
            }

        }else{
            switch(this.state.get_status()){
                case 0:
                    if(log.get_ping() < 0){
                        this.state.inc_noreply();
                        if(this.state.get_noreply() >= N){
                            this.state = new State(log.get_date(),1);
                            this.state.inc_noreply();
                        }else{
                            this.state = new State(log.get_date(),2);
                            this.state.inc_noreply();
                        }
                    }else{
                        this.state.inc_reply();
                        if(this.state.get_reply()>=m){
                            int avetime = 0;
                            for(int i=0; i<m; i++){
                                avetime += this.logs.get(this.logs.size()-1-i).get_ping();
                            }
                            avetime /= m;
                            if(avetime >= this.t){
                                this.state = new State(log.get_date(),3,this.state.get_reply());
                            }
                        }
                    }
                    break;
                case 1:
                    if(log.get_ping() < 0){
                        this.state.inc_noreply();
                    }else{
                        this.state.set_end(log.get_date());
                        this.states.add(this.state);
                        this.state = new State(log.get_date(),0);
                        this.state.inc_reply();
                    }
                    break;
                case 2:
                    if(log.get_ping() < 0){
                        this.state.inc_noreply();
                        if(this.state.get_noreply() >= N){
                            this.state.set_status(1);
                        }
                    }else{
                        this.state = new State(log.get_date(),0);
                        this.state.inc_reply();
                    }
                case 3:
                if(log.get_ping() < 0){
                    this.state.inc_noreply();
                    if(this.state.get_noreply() >= N){
                        this.state.set_end(log.get_date());
                        this.states.add(this.state);
                        this.state = new State(log.get_date(),1);
                        this.state.inc_noreply();
                    }else{
                        this.state.set_end(log.get_date());
                        this.states.add(this.state);
                        this.state = new State(log.get_date(),2);
                        this.state.inc_noreply();
                    }
                }else{
                    this.state.inc_reply();
                    if(this.state.get_reply()>=m){
                        int avetime = 0;
                        for(int i=0; i<m; i++){
                            avetime += this.logs.get(this.logs.size()-1-i).get_ping();
                        }
                        avetime /= m;
                        if(avetime < this.t){
                            this.state.set_end(log.get_date());
                            this.states.add(this.state);
                            this.state = new State(log.get_date(),0,this.state.get_reply());
                        }
                    }
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
