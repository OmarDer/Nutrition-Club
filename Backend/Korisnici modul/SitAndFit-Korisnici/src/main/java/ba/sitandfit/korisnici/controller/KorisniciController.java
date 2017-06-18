package ba.sitandfit.korisnici.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.apache.commons.lang.RandomStringUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.jsonwrappers.RolaJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.model.KorisnikSubmit;
import ba.sitandfit.korisnici.model.Rola;
import ba.sitandfit.korisnici.model.Stanje;
import ba.sitandfit.korisnici.service.KorisnikService;
import ba.sitandfit.korisnici.service.KorisnikSubmitService;
import ba.sitandfit.korisnici.service.RolaService;
import ba.sitandfit.korisnici.service.StanjeService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

@RestController
@CrossOrigin
@RequestMapping(value = "/korisnici")
public class KorisniciController {
	
	@Autowired
	KorisnikService korisnikService;
	@Autowired
	StanjeService stanjeService;
	@Autowired
	RolaService rolaService;
	@Autowired 
	KorisnikSubmitService korisnikSubmitService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Korisnik> getKorisnici(){

		return korisnikService.getKorisnici();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper getKorisnik(@PathVariable(value="id") String id){
		
		try{
			
			Long x = Long.parseLong(id);
			return korisnikService.getKorisnik(x);
			
		}catch(Exception e){
			
		}
		
		return korisnikService.getKorisnikByUserName(id);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper createKorisnik(@RequestBody Korisnik k){
		
		return korisnikService.createKorisnik(k);
		
	}
	
	@RequestMapping(value = "/registriraj", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper registerKorisnik(@RequestBody Korisnik k){
		
		String generatedString = RandomStringUtils.randomAlphanumeric(15).toLowerCase();
		KorisnikSubmit kor= korisnikSubmitService.getKorisnikSubmitByGenString(generatedString).getKs();
		while(kor!=null)
		{
			generatedString = RandomStringUtils.randomAlphanumeric(15).toLowerCase();
		}
		k.setOdobren(0);
		k.setAktivan(false);
		
		//Postavljanje role
		Boolean nasaoRolu = false;
		List<Rola> role = rolaService.getRole();
		for(Rola x : role){

			if(x.getNazivRole().equals("ROLE_USER")){
				
				if(x.getAktivna())
					k.setRola(x);
				else{
					x.setAktivna(true);
					rolaService.updateRola(x.getId(), x);
					
					k.setRola(x);
				}
				nasaoRolu = true;
				break;
				
			}
		}
		
		if(!nasaoRolu){
			Rola r = new Rola();
			
			r.setAktivna(true);
			r.setNazivRole("ROLE_USER");
			r.setOpisRole("Korisnicka rola");
			RolaJSONWrapper x = rolaService.createRola(r);
			
			k.setRola(x.getRola());
		}
	
				
		KorisnikJSONWrapper korisnik = korisnikService.createKorisnik(k);
		if(korisnik.getStatus()!="Error") korisnikSubmitService.createKorisnikSubmitByValues(generatedString,korisnik.getKorisnik().getId());
		return korisnik;
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper updateKorisnik(@PathVariable(value="id") Long id, @RequestBody Korisnik k, HttpServletRequest request){
		
			String token = request.getHeader("Authorization");
			
			token = token.split(" ")[1];

			Jws<Claims> jws = Jwts.parser()
					.setSigningKey("FitAndSitTajna")
					.parseClaimsJws(token);
			
			Claims obj = jws.getBody();
			
			List<String> authorities = (List<String>) obj.get("authorities");
			
			if(authorities.contains("ROLE_ADMIN"))
				return korisnikService.updateKorisnik(id, k);
			
			Long x = ((Integer)obj.get("id")).longValue();

			if(!x.equals(id))
				return new KorisnikJSONWrapper("Error", "403 Forbidden",null);

			return korisnikService.updateKorisnik(id, k);
		
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper deleteKorisnik(@PathVariable(value="id") Long id){
		
		if(korisnikService.deleteKorisnik(id))
			return new KorisnikJSONWrapper("Success", "Korisnik obrisan", null);
		else
			return new KorisnikJSONWrapper("Error", "Korisnik nije obrisan, desila se greska", null);
		
		
		
	}
	
	@RequestMapping(value = "/{id}/stanjakorisnika", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Stanje> getStanjaKorisnikaByDate(@PathVariable(value="id") Long id, @RequestParam(value = "datumAnalize", required=false) Date date, 
																					@RequestParam(value = "startDate", required=false) Date startDate, 
																					@RequestParam(value = "endDate", required=false) Date endDate){
		
		if (date != null){
			return stanjeService.getStanjaKorisnikaByDate(id, date);
		}
		else if (endDate != null && startDate != null)
			return stanjeService.getStanjaKorisnikaBetweenDates(id, startDate, endDate);
		else
			return stanjeService.getStanjaKorisnika(id);
		
	}
	
	@RequestMapping(value = "/{id}/rola", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public RolaJSONWrapper getRolaKorisnika(@PathVariable(value="id") Long id){
		
		return korisnikService.getRolaKorisnika(id);
		
	}
	//Metoda za komunikaciju
	@RequestMapping(value = "/{id}/programi", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getProgrameKorisnika(@PathVariable(value="id") Long id){
		
		return korisnikService.getProgrameKorisnika(id);
		
	}
	

	@RequestMapping(value="/{id}/slika", method = RequestMethod.POST)
    public String UploadFile(@PathVariable(value="id") Long id, MultipartHttpServletRequest request) throws IOException {
		
		
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
	
	@RequestMapping(value = "/odobri/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper odobriRegistrovanogKorisnika(@PathVariable(value="id") Long id){
		
		return korisnikService.odobriRegistrovanogKorisnika(id);
		
	}
	
	@RequestMapping(value = "/zabrani/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper zabraniRegistrovanogKorisnika(@PathVariable(value="id") Long id){
		
		return korisnikService.zabraniRegistrovanogKorisnika(id);
		
	}
	
	
}
