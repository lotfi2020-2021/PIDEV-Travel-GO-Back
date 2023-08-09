package tn.esprit.TRAVELGO.service;


import tn.esprit.TRAVELGO.entities.ImageNews;

public interface IImageNewsService {
void affectationImageToNews(int idImageNews, long idNe);
	
	public Iterable<ImageNews> retreiveAllImage();
}



