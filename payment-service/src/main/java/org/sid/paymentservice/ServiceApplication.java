package org.sid.paymentservice;

import org.sid.paymentservice.test_upload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ServiceApplication {

	@Autowired
	private StorageService service;

	@GetMapping("/walo")
	public String getWalo(){
		return "walo";
	}

	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] imageData=service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}


	@PostMapping("/fileSystem/{bonana}")
	public ResponseEntity<String> uploadImageToFIleSystem(@RequestParam("image")MultipartFile file,@PathVariable String bonana) throws IOException {
		String uploadImage = service.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(bonana);
	}

	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData=service.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}
