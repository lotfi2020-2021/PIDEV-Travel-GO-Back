package tn.esprit.TRAVELGO.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.TRAVELGO.entities.ImageNews;
import tn.esprit.TRAVELGO.message.ResponseMessage;
import tn.esprit.TRAVELGO.service.IImageNewsService;
import tn.esprit.TRAVELGO.service.ImageNewsServiceImpl;


@RestController
public class ImageNewsRestController {

	@Autowired
	private ImageNewsServiceImpl usix;
	  
	@Autowired
	IImageNewsService iusx;
			@PostMapping("/uploadedn")
			public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		    String message = "";
		    try {
		    usix.addImage(file);
		    message = "Uploaded the file successfully: " + file.getOriginalFilename();
		    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		    }catch (Exception e) {
		    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		    }
			}
			@PostMapping("/affect-image-to-news/{idimage}/{idNe}")
			@ResponseBody
			public void affectationImageToNews(@PathVariable("idimage") int idimage,@PathVariable("idNe")long idNe) throws IOException{
			usix.affectationImageToNews(idimage, idNe);
			}

			@GetMapping ("/retreive-all-image-n")
			@ResponseBody
			public Iterable<ImageNews> retreiveAllImage(){
				return iusx.retreiveAllImage();
			
			}
}
