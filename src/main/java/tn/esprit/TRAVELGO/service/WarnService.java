package tn.esprit.TRAVELGO.service;

import tn.esprit.TRAVELGO.entities.Warn;

public interface WarnService {
	public void Warning(Long idUser,Warn w);
	public Warn addWarn(Warn w);
}
