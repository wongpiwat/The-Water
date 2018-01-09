package models;

import databases.TreatmentsDBConnector;

import java.util.List;

public class Account {
    private String type;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String status;

    public Account(String type, String firstName, String lastName, String username, String password, String status) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getActive() {
        if (type.equals("Supervisor")) {
            return "-";
        }
        List<Treatment> preTreatments = TreatmentsDBConnector.getPreTreatments();
        for (int i = 0 ; i < preTreatments.size() ; i++) {
            if (preTreatments.get(i).getName().equals(firstName+" "+lastName.subSequence(0,1))) {
                return "✓";
            }
        }
        List<Treatment> postTreatments = TreatmentsDBConnector.getPreTreatments();
        for (int i = 0 ; i < postTreatments.size() ; i++) {
            if (postTreatments.get(i).getName().equals(firstName+" "+lastName.subSequence(0,1))) {
                return "✓";
            }
        }
        return "✕";
    }
}
