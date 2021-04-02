package Server;

public class State {
    private String start;
    private String end;
    private int status; //0:正常 1:故障

    public State(String start){
        this.start = start;
    }

    public State(String start, int status){
        this.start = start;
        this.status = status;
    }

    public String get_start(){
        return this.start;
    }

    public String get_end(){
        return this.end;
    }

    public int get_status(){
        return this.status;
    }

    public void set_end(String end){
        this.end = end;
    }

    public void set_status(int status){
        this.status = status;
    }
    
}
