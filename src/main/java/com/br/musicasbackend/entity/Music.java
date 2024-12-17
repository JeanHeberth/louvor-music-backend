package com.br.musicasbackend.entity;

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
    private String music;
    private String autor;
    private String versao;

}
