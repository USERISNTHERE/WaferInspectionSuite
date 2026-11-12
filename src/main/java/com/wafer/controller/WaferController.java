package com.wafer.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

@RestController
public class WaferController {
    @GetMapping("/api/wafer/inspect")
    public Map<String, Object> inspect(@RequestParam(defaultValue = "edge") String profile) {
        Map<String, Object> res = new HashMap<>();
        List<Map<String, Object>> dies = new ArrayList<>();
        int defectiveCount = 0;
        Random rand = new Random();
        
        for(int x = 0; x < 20; x++) {
            for(int y = 0; y < 20; y++) {
                Map<String, Object> die = new HashMap<>();
                die.put("xcoord", x);
                die.put("ycoord", y);
                boolean isDefective = rand.nextDouble() < 0.12;
                if(isDefective) {
                    defectiveCount++;
                }
                die.put("defective", isDefective);
                die.put("defectCategory", isDefective ? "Defect Detected" : "Normal");
                dies.add(die);
            }
        }
        res.put("dies", dies);
        res.put("totalDies", 400);
        res.put("defectiveCount", defectiveCount);
        return res;
    }
}
