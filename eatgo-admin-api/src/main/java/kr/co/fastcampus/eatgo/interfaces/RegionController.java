package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RegionService;
import kr.co.fastcampus.eatgo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list(){
        return regionService.getRegions();
    }

    @PostMapping("/regions")
    public ResponseEntity<?> create(
            @RequestBody Region resource
    ) throws URISyntaxException {
        //todo
        String name = resource.getName();
        Region region = regionService.addRegion(name);
        String url = "/regions/" + resource.getId();// 원래 region넘기느건데 지금은 그냥 이렇게 함

        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
