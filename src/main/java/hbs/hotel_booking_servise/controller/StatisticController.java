package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.error.EntityNotFoundEx;
import hbs.hotel_booking_servise.statistics.StatisticServise;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api/v1/statistic")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor

public class StatisticController {

    private final StatisticServise statisticServise;


    @GetMapping

    public ResponseEntity<Resource> getStatisticFile() {

        File file = statisticServise.generateCvsFile().toFile();
        String path = file.getPath();

        if (!file.exists()) {
            throw new EntityNotFoundEx("file " + path + " not found");
        }

        Resource resource = new FileSystemResource(path);

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
        headers.setContentType(MediaType.TEXT_PLAIN);

        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
