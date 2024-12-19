package com.br.musicasbackend.controller;

import com.br.musicasbackend.entity.model.Music;
import com.br.musicasbackend.entity.request.MusicRequest;
import com.br.musicasbackend.entity.response.MusicResponse;
import com.br.musicasbackend.service.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class MusicController {

    @Autowired
    MusicService musicService;


    @GetMapping("findMusics")
    public ResponseEntity<List<MusicResponse>> findMusics() {
        return ResponseEntity.ok(musicService.findMusic());
    }

    @GetMapping("findMusicForName")
    public ResponseEntity<List<MusicResponse>> findMusicByName(@RequestParam String music) {
        List<MusicResponse> musics = musicService.findByMusic(music);
        return ResponseEntity.ok(musics);
    }

    @PostMapping("saveMusic")
    public ResponseEntity<Music> saveMusic(@RequestBody @Valid MusicRequest musicRequest) {
        return ResponseEntity.ok(musicService.saveMusic(musicRequest));
    }


    @PutMapping("updateMusic/{uuid}")
    public ResponseEntity<MusicResponse> updateMusic(@PathVariable String uuid, @RequestBody @Valid MusicRequest musicRequest) {
        System.out.println("Json Recebido: " + uuid);
        return ResponseEntity.ok(musicService.updateMusic(uuid, musicRequest));
    }

    @DeleteMapping("deleteMusic/{uuid}")
    public ResponseEntity<Void> deleteMusic(@PathVariable String uuid) {
        musicService.deleteMusic(uuid);
        return ResponseEntity.noContent().build();
    }


}
