package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection implements Connection {

    private Connection state;
    public String buffer;
    public TcpConnection(String ip, int port) {
        this.state = new Disconnected(this);
    }

    public void setConnection(Connection connection) {
        this.state = connection;
    }
    @Override
    public String getCurrentState() {
        return state.getCurrentState();
    }

    @Override
    public void connect() {
        this.state.connect();
    }

    @Override
    public void disconnect() {
        this.state.disconnect();
    }

    @Override
    public void write(String data) {
        state.write(data);
    }
}
// END
