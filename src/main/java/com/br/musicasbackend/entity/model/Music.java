package com.br.musicasbackend.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "music")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Music {

    @Id
    private String uuid;
    @JsonProperty("musica")
    private String music;
    @JsonProperty("autor")
    private String autor;
    @JsonProperty("versao")
    private String versao;

}
