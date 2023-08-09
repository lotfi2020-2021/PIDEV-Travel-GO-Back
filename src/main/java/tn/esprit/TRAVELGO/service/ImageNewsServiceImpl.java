package tn.esprit.TRAVELGO.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.TRAVELGO.entities.ImageNews;
import tn.esprit.TRAVELGO.entities.News;
import tn.esprit.TRAVELGO.repository.ImageNewsRepository;
import tn.esprit.TRAVELGO.repository.NewsRepository;


@Service
public class ImageNewsServiceImpl implements IImageNewsService {

	@Autowired
	ImageNewsRepository iurx;
	@Autowired
	NewsRepository ur;

	public ImageNews addImage(MultipartFile file) throws IOException {
		String nameImageNews = StringUtils.cleanPath(file.getOriginalFilename());
		ImageNews ImageNews = new ImageNews(nameImageNews, file.getContentType(), file.getBytes());
		return iurx.save(ImageNews);
	}
	@Override
	public void affectationImageToNews(int idImageNews, long idNe) {
		// TODO Auto-generated method stub
		News news = ur.findById(idNe).get();
		ImageNews imageNews = iurx.findById(idImageNews).get();
		imageNews.setNews(news);
		iurx.save(imageNews);
	}
	@Override
	public Iterable<ImageNews> retreiveAllImage() {
		return iurx.findAll();
	}

}
