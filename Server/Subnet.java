package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Log.Log;

public class Subnet extends Server{
    Map<String,Server> servers = new HashMap<String,Server>();

    public Subnet(long address, int N, int m, int t){
        this.address = address;
        this.N = N;
        this.m = m;
        this.t = t;
        this.state = null;
    }

    public void add_log(Log log){
        String svkey = log.get_address10();
        if(this.servers.containsKey(svkey)){
            this.servers.get(svkey).add_log(log);
        }else{
            this.servers.put(svkey, new Server(log.get_address2(), log.get_prefix(), N, m, t));
            this.servers.get(svkey).add_log(log);
        }
        this.state_controll(log);
    }

    public Map<String,Server> get_servers(){
        return this.servers;
    }

    public boolean is_having(Server s){
        return this.address == s.get_subnet();
    }

    @Override
    public void state_controll(Log log){
        int subnet_status = 1;
        for(String key: this.servers.keySet()){
            if(this.servers.get(key).state.get_status() != 1){
                subnet_status *= 0;
            }
        }
        if(this.state == null){
            this.state = new State(log.get_date(),subnet_status);
        }else{
            switch(this.state.get_status()){
                case 0:
                    if(subnet_status == 1){
                        this.state = new State(log.get_date(),subnet_status);
                    }
                case 1:
                    if(subnet_status == 0){
                        this.state.set_end(log.get_date());
                        this.states.add(this.state);
                        this.state = new State(log.get_date(),subnet_status);
                    }
            }
        }
    }
}
