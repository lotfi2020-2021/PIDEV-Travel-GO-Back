package tn.esprit.TRAVELGO.controller;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.TRAVELGO.entities.Warn;
import tn.esprit.TRAVELGO.service.WarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class WarnController {
@Autowired
WarnService warnservice;
@PostMapping("/company/warning/{iduser}")
@ResponseBody
public void SendWarning(@PathVariable("iduser") Long idUser,@RequestBody Warn w ) {
	warnservice.Warning(idUser, w);
}
@PostMapping("/company/add-warning")
@ResponseBody
public Warn addWarning(@RequestBody Warn w) {
	return warnservice.addWarn(w);
}
}