package tn.esprit.TRAVELGO.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.TRAVELGO.entities.ImageUser;
import tn.esprit.TRAVELGO.entities.User;
import tn.esprit.TRAVELGO.repository.IImageUserRepository;
import tn.esprit.TRAVELGO.repository.UserRepository;


@Service
public class ImageUserServiceImpl implements IImageUserService{

	
	@Autowired
	IImageUserRepository iur;
	
	@Autowired
	UserRepository ur;
	
	 public ImageUser addImage(MultipartFile file) throws IOException {
		    String nameImageUser = StringUtils.cleanPath(file.getOriginalFilename());
		    ImageUser ImageUser = new ImageUser(nameImageUser, file.getContentType(), file.getBytes());

		    return iur.save(ImageUser);
		  }
	 
	 @Override
		public void affectationImageToUser(int idImageUser, long id) {
			// TODO Auto-generated method stub
			User user=ur.findById(id).get();
			ImageUser imageUser=iur.findById(idImageUser).get();
			imageUser.setUser(user);
			iur.save(imageUser);
}


		@Override
		public Iterable<ImageUser> retreiveAllImage() {
			return iur.findAll();
		}


}
