package com.br.musicasbackend.service;

import com.br.musicasbackend.entity.Music;
import com.br.musicasbackend.entity.request.MusicRequest;
import com.br.musicasbackend.entity.response.MusicResponse;
import com.br.musicasbackend.repository.MusicRepository;
import com.br.musicasbackend.utils.Utils;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {


    @Autowired
    MusicRepository musicRepository;


    private final ModelMapper mapper = new ModelMapper();
    private final Utils removeAcentos = new Utils();


    public Music saveMusic(@Valid MusicRequest musicRequest) {
        Music music = new Music(musicRequest.uuid(), removeAcentos.removerAcentos(musicRequest.music()), removeAcentos.removerAcentos(musicRequest.autor()), removeAcentos.removerAcentos(musicRequest.versao()));
        return musicRepository.save(music);
    }

    public List<MusicResponse> findMusic() {
        return musicRepository.findAll()
                .stream()
                .map(music -> new MusicResponse(music.getUuid(), music.getMusic(), music.getAutor(), music.getVersao()))
                .collect(Collectors.toList());
    }

    public List<MusicResponse> findByMusic(String music) {
        return musicRepository.findByMusic(music)
                .stream()
                .map(musica -> new MusicResponse(musica.getUuid(), musica.getMusic(), musica.getAutor(), musica.getVersao()))
                .collect(Collectors.toList());
    }

    public MusicResponse updateMusic(String uuid, MusicRequest musicRequest) {
        Music music = musicRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException());
        music.setMusic(musicRequest.music());
        music.setAutor(musicRequest.autor());
        music.setVersao(musicRequest.versao());

        musicRepository.save(music);
        return new MusicResponse(music.getUuid(), music.getMusic(), music.getAutor(), music.getVersao());

    }
}
