import java.io.Serializable;

public class Appointment implements Serializable {
    private String clientName;
    private String clientId;
    private String serviceType;
    private String email;
    private String date;
    private String time;
    private String status;

    public Appointment(String clientName, String clientId, String serviceType, String email, String date, String time, String status) {
        this.clientName = clientName;
        this.clientId = clientId;
        this.serviceType = serviceType;
        this.email = email;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    // Getters and Setters
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return clientName + "," + clientId + "," + serviceType + "," + email + "," + date + "," + time + "," + status;
    }

    public static Appointment fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 7) {
            return new Appointment(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
        }
        return null;
    }
}
