
public class updateException extends Exception {
    String msg; 
    public updateException(String msg){
        super(msg); 
        this.msg = msg; 
    }

    public String getMessage(){
        return msg; 
    }
}
