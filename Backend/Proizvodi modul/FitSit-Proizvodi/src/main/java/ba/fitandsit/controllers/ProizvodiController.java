package ba.fitandsit.controllers;
import ba.fitandsit.wrappers.*;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import ba.fitandsit.repository.ProizvodiRepository;
import ba.fitandsit.services.ProizvodiService;
import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Programi;
import ba.fitandsit.model.Proizvodi;

@RestController
@RequestMapping("/proizvodi")
public class ProizvodiController {
	
	@Autowired
	private ProizvodiService ps;
	
	
	@RequestMapping("/all")
	public List<Proizvodi> vratiSve(){	
		
		return ps.vratiSve();
		
	}
	
	@RequestMapping("")
	public List<Proizvodi> vratiAktivne(){	
		
		return ps.vratiAktivne();
		
	}
	@RequestMapping("/{id}")
	public JsonWrapperProizvodi vratiProizvodPoID(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		return ps.vratiProizvodPoID(Id);
		
	}
	
	@RequestMapping(value="",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public JsonWrapperProizvodi kreirajProizvod(@RequestBody Proizvodi p, @RequestHeader("Authorization") String token)
	{
		return ps.kreirajProizvod(p,token);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Boolean obrisiProizvod(@PathVariable String id)
	{
		Long Id=Long.parseLong(id);
	
		return ps.obrisiProizvod(Id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public JsonWrapperProizvodi azurirajProizvod(@PathVariable Long id, @RequestBody Proizvodi p, @RequestHeader("Authorization") String token){
		
		return ps.azurirajProizvod(id,p,token);
	}
	
	@RequestMapping(value="/izlistaj/{programName}",method=RequestMethod.GET)
	public JsonWrapperListProizvodi izlistajProizvodePoProgramu(@PathVariable String programName)
	{
		return ps.izlistajProizvodePoProgramu(programName);
	}
	
	@RequestMapping(value="/image/upload", method=RequestMethod.POST)
	public String uploadImage(MultipartHttpServletRequest request) throws IOException
	{
		
		Iterator<String> itr=request.getFileNames();
        MultipartFile file=request.getFile(itr.next());
        String fileName=file.getOriginalFilename();
      
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        		  "cloud_name", "sitandfitpictures",
        		  "api_key", "334879857999493",
        		  "api_secret", "yoaBNdV36K2ZNNdjBGtIwpZfKro"));
        
        
        File serverFile = new File(fileName);
       
        //file.transferTo(serverFile);
        
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.close();
        
        BufferedImage image = ImageIO.read(serverFile);
        image=Scalr.resize(image,Method.BALANCED, Scalr.Mode.FIT_EXACT,320,150);
        ImageIO.write(image, "jpg" , serverFile);
         
        Map uploadResult = cloudinary.uploader().upload(serverFile, ObjectUtils.emptyMap());
        
        String imageUrl = String.valueOf(uploadResult.get("url"));
        System.out.println(imageUrl);
        
        return imageUrl;
	}
	
}
