package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Complaint {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private ComplaintType type;
    private int objectId;
    private int patientId;
    private String text;
    private String answer;

    public String toString(Complaint c){
        String answer = "Without answer";
        if(c.answer != null)
            answer = c.answer;
        return "User type: "+ c.type+ "_________"+"Text: "+c.text + "_________" + "Answer: "+answer;
    }
}
