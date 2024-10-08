package nl.gelton.steunideebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAvatar {


    /* Het opgeslagen bestand staat niet in deze klasse opgeslagen.
       Deze klasse heeft enkel een verwijzing naar de naam van het bestand.
       We weten waar het bestand staat opgeslagen,
       dus met de naam kunnen we naar het bestand verwijzen als "./uploads/{fileName}"
    */
    @Id
    private String fileName;

    public String getFileName() {
        return fileName;
    }

}
