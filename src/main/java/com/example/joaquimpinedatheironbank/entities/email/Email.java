package com.example.joaquimpinedatheironbank.entities.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Email {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;


}
